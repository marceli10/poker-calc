package com.example.pokercalc.service.algorithm.handlers;

import com.example.pokercalc.model.card.Card;
import com.example.pokercalc.model.card.Combination;
import com.example.pokercalc.service.exceptions.CombinationNotFound;

import java.util.List;

import static com.example.pokercalc.model.card.Combination.FOUR_OF_A_KIND;
import static java.util.stream.Collectors.groupingBy;

public class FourOfKindHandler extends BaseCombinationHandler {

    @Override
    public Combination handleCombination(List<Card> cards) throws CombinationNotFound {
        if (isFourOfAKind(cards))
            return FOUR_OF_A_KIND;
        return super.handleCombination(cards);
    }

    private boolean isFourOfAKind(List<Card> cards) {

        return cards.stream()
                .collect(groupingBy(Card::value))
                .values()
                .stream()
                .anyMatch(cardList -> cardList.size() == 4);
    }

}
