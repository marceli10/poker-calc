package com.example.pokercalc.service.algorithm.handlers;

import com.example.pokercalc.model.card.Card;
import com.example.pokercalc.model.card.Combination;
import com.example.pokercalc.service.exceptions.CombinationNotFound;

import java.util.List;

public interface CombinationHandler {
    void setNextCombination(CombinationHandler combinationHandler);
    Combination handleCombination(List<Card> cards) throws CombinationNotFound;
}