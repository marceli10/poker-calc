package com.example.pokercalc.service.algorithm.handlers;

import com.example.pokercalc.model.card.Card;
import com.example.pokercalc.model.card.CardValue;
import com.example.pokercalc.model.card.Combination;
import com.example.pokercalc.service.exceptions.CombinationNotFound;

import java.util.List;

import static java.util.stream.Collectors.groupingBy;
import static com.example.pokercalc.model.card.Combination.*;

public class PairHandler extends BaseCombinationHandler {

    @Override
    public Combination handleCombination(List<Card> cards) throws CombinationNotFound {
        if (isPair(cards))
            return PAIR;
        return super.handleCombination(cards);
    }

    private boolean isPair(List<Card> cards) {
        return cards.stream()
                .map(Card::value)
                .collect(groupingBy(CardValue::getValue))
                .values()
                .stream()
                .map(List::size)
                .filter(valuesLength -> valuesLength == 2)
                .count() == 1;
    }
}