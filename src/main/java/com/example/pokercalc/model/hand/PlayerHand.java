package com.example.pokercalc.model.hand;

import com.example.pokercalc.model.card.Card;
import com.example.pokercalc.model.card.Combination;

import java.util.ArrayList;
import java.util.List;

public class PlayerHand {

    private final List<Card> cardsInHand;
    private Combination highestCombination;

    public PlayerHand(Card firstCard, Card secondHand) {
        this.cardsInHand = new ArrayList<>();
        cardsInHand.add(firstCard);
        cardsInHand.add(secondHand);
    }
}
