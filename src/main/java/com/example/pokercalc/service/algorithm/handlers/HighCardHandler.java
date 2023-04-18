package com.example.pokercalc.service.algorithm.handlers;


import com.example.pokercalc.model.card.Card;
import com.example.pokercalc.model.card.Combination;
import com.example.pokercalc.service.exceptions.CombinationNotFound;

import java.util.List;

public class HighCardHandler extends  BaseCombinationHandler{
    @Override
    public Combination handleCombination(List<Card> cards) throws CombinationNotFound {
        return Combination.HIGH_CARD;
    }
}
