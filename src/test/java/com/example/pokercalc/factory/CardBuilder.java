package com.example.pokercalc.factory;

import com.example.pokercalc.model.card.Card;
import com.example.pokercalc.model.card.CardColor;
import com.example.pokercalc.model.card.CardValue;

public class CardBuilder {
    private CardColor color;
    private CardValue value;

    public CardBuilder() {
        color = CardColor.SPADE;
        value = CardValue.ACE;
    }

    public Card build() {
        return new Card(color, value);
    }

    public CardBuilder withColor(CardColor color) {
        this.color = color;
        return this;
    }

    public CardBuilder withValue(CardValue value) {
        this.value = value;
        return this;
    }
}
