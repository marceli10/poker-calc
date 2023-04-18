package com.example.pokercalc.service.algorithm.handlers;

import com.example.pokercalc.model.card.Card;
import com.example.pokercalc.model.card.Combination;
import com.example.pokercalc.service.exceptions.CombinationNotFound;

import java.util.List;

public class FlushHandler extends BaseCombinationHandler {

    @Override
    public Combination handleCombination(List<Card> cards) throws CombinationNotFound {
        if (isColorInCombination(cards))
            return Combination.FLUSH;
        return super.handleCombination(cards);
    }
}
