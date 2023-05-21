package com.example.pokercalc.service.simulation;

import com.example.pokercalc.controller.dto.request.CardsOnTable;
import com.example.pokercalc.controller.dto.request.PlayerHand;
import com.example.pokercalc.controller.dto.request.PokerData;
import com.example.pokercalc.controller.dto.response.ChancesDto;
import com.example.pokercalc.model.card.Card;
import com.example.pokercalc.model.card.Combination;
import com.example.pokercalc.model.deck.Deck;
import com.example.pokercalc.service.algorithm.chain.PokerCombinationChain;
import com.example.pokercalc.service.simulation.dto.PlayerWonStatistics;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.math3.util.CombinatoricsUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class SimulationService {
    private final PokerCombinationChain pokerCombinationChain;

    public SimulationService(PokerCombinationChain pokerCombinationChain) {
        this.pokerCombinationChain = pokerCombinationChain;
    }

    public ChancesDto calculateChancesOnStream(PokerData pokerData) {
        List<Card> remainingCards = filterCardsInDeck(pokerData);
        Iterator<int[]> combinationIterator = getRemainingCardsCombinations(
                remainingCards.size(), pokerData.getCardsOnTable().isPresent()
        );
        List<PlayerWonStatistics> playersWonStatistics = List.of(
                new PlayerWonStatistics(pokerData.firstPlayer()),
                new PlayerWonStatistics(pokerData.secondPlayer()),
                new PlayerWonStatistics(pokerData.thirdPlayer()),
                new PlayerWonStatistics(pokerData.fourthPlayer()),
                new PlayerWonStatistics(pokerData.fifthPlayer()),
                new PlayerWonStatistics(pokerData.sixthPlayer())
        );
        Map<Combination, AtomicInteger> combinationsWonStatistics = new ConcurrentHashMap<>();
        Optional<CardsOnTable> cardsOnTable = pokerData.getCardsOnTable();
        List<int[]> combinationList = IteratorUtils.toList(combinationIterator);

        combinationList
                .stream()
                .parallel()
                .map(combination -> getSelectedCards(combination, remainingCards, cardsOnTable.isPresent()))
                .forEach(selectedCards -> {
                    try {
                        int playerWithHighestCombination =
                                whichPlayerHasHighestCombination(
                                        playersWonStatistics,
                                        cardsOnTable,
                                        selectedCards,
                                        combinationsWonStatistics
                                );
                        playersWonStatistics.get(playerWithHighestCombination).incrementWonCount();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });

        return ChancesDto.from(playersWonStatistics, combinationsWonStatistics);
    }


    private Combination calculatePlayerHighestCombination(
            PlayerHand playerHand, Optional<CardsOnTable> cardsOnTable, List<Card> possibleCards
    ) {
        List<Card> playerCards = allPlayerCards(
                playerHand,
                cardsOnTable.isPresent() ? cardsOnTable.get().cards() : List.of(),
                possibleCards
        );
        return pokerCombinationChain.getCombination(playerCards);
    }

    private Iterator<int[]> getRemainingCardsCombinations(int remainingCardsNumber, boolean withCardsOnTable) {
        return CombinatoricsUtils.combinationsIterator(remainingCardsNumber, withCardsOnTable ? 2 : 5);
    }

    private List<Card> filterCardsInDeck(PokerData pokerData) {
        List<Card> cards = new Deck().getDeck();

        cards.removeAll(pokerData.firstPlayer().getCards());
        cards.removeAll(pokerData.secondPlayer().getCards());
        cards.removeAll(pokerData.thirdPlayer().getCards());
        cards.removeAll(pokerData.fourthPlayer().getCards());
        cards.removeAll(pokerData.fifthPlayer().getCards());
        cards.removeAll(pokerData.sixthPlayer().getCards());
        cards.removeAll(pokerData.getCardsOnTable().isPresent() ? pokerData.cardsOnTable().cards() : List.of());

        return cards;
    }

    private int whichPlayerHasHighestCombination(
            List<PlayerWonStatistics> playersWonStatistics, Optional<CardsOnTable> cardsOnTable, List<Card> selectedCards,
            Map<Combination, AtomicInteger> combinationsWonStatistics) throws Exception {
        List<Combination> playersCombinations = playersWonStatistics
                .stream()
                .map(PlayerWonStatistics::getPlayerHand)
                .map(hand -> calculatePlayerHighestCombination(hand, cardsOnTable, selectedCards))
                .toList();

        Combination highestCombination = playersCombinations
                .stream()
                .max(Comparator.comparing(Combination::getValueOfCombination))
                .orElseThrow(() -> new Exception("Couldn't find a combination"));
        updateCombinationsStatistics(combinationsWonStatistics, highestCombination);
        return playersCombinations.indexOf(highestCombination);
    }

    private void updateCombinationsStatistics(
            Map<Combination, AtomicInteger> combinationsWonStatistics, Combination wonCombination
    ) {
        combinationsWonStatistics.putIfAbsent(wonCombination, new AtomicInteger(0));
        combinationsWonStatistics.get(wonCombination).incrementAndGet();
    }

    private List<Card> allPlayerCards(PlayerHand playerHand, List<Card> cardsOnTable, List<Card> possibleCards) {
        List<Card> playerCards = new ArrayList<>(playerHand.getCards());
        playerCards.addAll(cardsOnTable);
        playerCards.addAll(possibleCards);
        return playerCards;
    }

    private List<Card> getSelectedCards(int[] combination, List<Card> remainingCards, boolean withCardsOnTable) {
        if (withCardsOnTable)
            return List.of(remainingCards.get(combination[0]), remainingCards.get(combination[1]));
        return List.of(
                remainingCards.get(combination[0]),
                remainingCards.get(combination[1]),
                remainingCards.get(combination[2]),
                remainingCards.get(combination[3]),
                remainingCards.get(combination[4])
        );
    }
}
