package com.example.pokercalc.controller.dto.request;

import com.example.pokercalc.model.card.Card;

import java.util.List;

public record PlayerHand(Card firstCard, Card secondCard) {
    public List<Card> getCards() {
        return List.of(firstCard, secondCard);
    }
}
