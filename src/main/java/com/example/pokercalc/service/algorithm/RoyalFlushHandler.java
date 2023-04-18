package com.example.pokercalc.service.algorithm;

import com.example.pokercalc.model.card.Card;
import com.example.pokercalc.model.card.CardValue;
import com.example.pokercalc.model.card.Combination;
import com.example.pokercalc.service.exceptions.CombinationNotFound;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.example.pokercalc.model.card.CardValue.*;
import static java.util.stream.Collectors.groupingBy;

public class RoyalFlushHandler extends BaseCombinationHandler {
    @Override
    public Combination handleCombination(List<Card> cards) throws CombinationNotFound {
        if (isColorInCombination(cards) && isRoyalFlushCombination(cards))
            return Combination.ROYAL_FLUSH;
        return super.handleCombination(cards);
    }

    private boolean isRoyalFlushCombination(List<Card> cards) {
        return cards.stream()
                .collect(groupingBy(Card::color))
                .values()
                .stream()
                .map(cardsList -> cardsList.stream().map(Card::value).toList())
                .anyMatch(cardsValues -> new HashSet<>(cardsValues).containsAll(List.of(ACE, KING, QUEEN, JACK, TEN)));
    }
}
