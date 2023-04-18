package com.example.pokercalc.service.algorithm.handlers;


import com.example.pokercalc.model.card.Card;
import com.example.pokercalc.model.card.Combination;
import com.example.pokercalc.service.exceptions.CombinationNotFound;

import java.util.HashSet;
import java.util.List;

import static com.example.pokercalc.model.card.CardValue.*;
import static java.util.stream.Collectors.groupingBy;

public abstract class BaseCombinationHandler implements CombinationHandler {
    private CombinationHandler nextCombinationHandler;

    @Override
    public void setNextCombination(CombinationHandler combinationHandler) {
        nextCombinationHandler = combinationHandler;
    }

    @Override
    public Combination handleCombination(List<Card> cards) throws CombinationNotFound {
        if (nextCombinationHandler != null)
            return nextCombinationHandler.handleCombination(cards);
        throw new CombinationNotFound("Cannot find any combination");
    }

    protected boolean isColorInCombination(List<Card> cards) {
        Integer largestSameColorCardsNumber = cards
                .stream()
                .collect(groupingBy(Card::color))
                .values()
                .stream()
                .map(List::size)
                .max(Integer::compareTo)
                .orElse(0);

        return largestSameColorCardsNumber >= 5;
    }

    protected boolean isStraightInCombination(List<Card> cards) {
        List<Integer> cardValues = cards.stream().map(el -> el.value().getValue()).sorted().toList();

        for (int i = 0; i < 3; i++) {
            List<Integer> sublistOfCards = cardValues.subList(i, i + 5);

            if (isStraightInCards(sublistOfCards))
                return true;
        }
        if (cardValues.contains(ACE.getValue())) {
            return new HashSet<>(cardValues)
                    .containsAll(
                            List.of(ACE.getValue(), TWO.getValue(), THREE.getValue(), FOUR.getValue(), FIVE.getValue())
                    );
        }
        return false;
    }

    private boolean isStraightInCards(List<Integer> cardValues) {
        for (int j = 1; j < cardValues.size(); j++) {
            if (cardValues.get(j) - cardValues.get(j - 1) > 1)
                return false;
        }
        return true;
    }
}
