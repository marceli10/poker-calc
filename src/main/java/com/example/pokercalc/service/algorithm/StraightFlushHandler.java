package com.example.pokercalc.service.algorithm;

import com.example.pokercalc.model.card.Card;
import com.example.pokercalc.model.card.Combination;
import com.example.pokercalc.service.exceptions.CombinationNotFound;

import static com.example.pokercalc.model.card.CardValue.*;

import java.util.HashSet;
import java.util.List;

public class StraightFlushHandler extends BaseCombinationHandler {

    @Override
    public Combination handleCombination(List<Card> cards) throws CombinationNotFound {
        if(isColorInCombination(cards))
            return Combination.STRAIGHT_FLUSH;
        return super.handleCombination(cards);
    }

    public boolean isFlushCombination(List<Card> cards) {
        List<Integer> cardValues = cards.stream().map(el -> el.value().getValue()).sorted().toList();

        for (int i = 0; i < 3; i++) {
            List<Integer> sublistOfCards = cardValues.subList(i, i + 5);

            if (isStraightInCards(sublistOfCards))
                return true;
        }

        if (cardValues.get(0) == 0) {
            return new HashSet<>(cardValues)
                    .containsAll(
                            List.of(ACE.getValue(), KING.getValue(), QUEEN.getValue(), JACK.getValue(), TEN.getValue())
                    );
        }
        return false;
    }

    private boolean isStraightInCards(List<Integer> cardValues) {
        for (int j = 1; j < cardValues.size(); j ++) {
            if (cardValues.get(j) - cardValues.get(j - 1) > 1)
                return false;
        }
        return true;
    }
}
