package com.example.pokercalc.service.algorithm.handlers;

import com.example.pokercalc.model.card.Card;
import com.example.pokercalc.model.card.Combination;
import com.example.pokercalc.service.exceptions.CombinationNotFound;

import java.util.List;

import static com.example.pokercalc.model.card.Combination.STRAIGHT;

public class StraightHandler extends BaseCombinationHandler {
    @Override
    public Combination handleCombination(List<Card> cards) throws CombinationNotFound {
        if (isStraightInCombination(cards))
            return STRAIGHT;
        return super.handleCombination(cards);
    }
}
