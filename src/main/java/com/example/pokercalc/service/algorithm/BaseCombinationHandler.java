package com.example.pokercalc.service.algorithm;


import com.example.pokercalc.model.card.Card;
import com.example.pokercalc.model.card.Combination;
import com.example.pokercalc.service.exceptions.CombinationNotFound;

import java.util.List;

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
}
