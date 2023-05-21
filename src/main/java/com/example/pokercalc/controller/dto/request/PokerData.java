package com.example.pokercalc.controller.dto.request;

import java.util.Optional;

public record PokerData(
    PlayerHand firstPlayer,
    PlayerHand secondPlayer,
    PlayerHand thirdPlayer,
    PlayerHand fourthPlayer,
    PlayerHand fifthPlayer,
    PlayerHand sixthPlayer,
    CardsOnTable cardsOnTable
) {
    public Optional<CardsOnTable> getCardsOnTable() {
        return Optional.ofNullable(cardsOnTable);
    }
}
