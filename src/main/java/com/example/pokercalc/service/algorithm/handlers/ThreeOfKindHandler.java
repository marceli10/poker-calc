package com.example.pokercalc.service.algorithm.handlers;

import com.example.pokercalc.model.card.Card;
import com.example.pokercalc.model.card.CardValue;
import com.example.pokercalc.model.card.Combination;
import com.example.pokercalc.service.exceptions.CombinationNotFound;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

public class ThreeOfKindHandler extends BaseCombinationHandler {
    @Override
    public Combination handleCombination(List<Card> cards) throws CombinationNotFound {
        if (isThreeOfAKind(cards))
            return Combination.THREE_OF_A_KIND;
        return super.handleCombination(cards);
    }


    private boolean isThreeOfAKind(List<Card> cards) {
        Map<CardValue, List<Card>> groupedCards = cards.stream().collect(groupingBy(Card::value));

        return groupedCards.values().stream()
                .anyMatch(cardsList -> cardsList.size() == 3);
    }
}
