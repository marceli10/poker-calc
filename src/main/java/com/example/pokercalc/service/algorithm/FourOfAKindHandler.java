package com.example.pokercalc.service.algorithm;

import com.example.pokercalc.model.card.Card;
import com.example.pokercalc.model.card.CardValue;
import com.example.pokercalc.model.card.Combination;
import com.example.pokercalc.service.exceptions.CombinationNotFound;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class FourOfAKindHandler extends BaseCombinationHandler {

    public Combination handleCombination(List<Card> cards) throws CombinationNotFound {
        return null;
    }

    private boolean isFourOfAKind(List<Card> cards) {

        return cards.stream()
                .collect(groupingBy(Card::value))
                .values()
                .stream()
                .anyMatch(cardList -> cardList.size() == 4);
    }

}
