package com.example.pokercalc.model.card;

public enum Combination {
    HIGH_CARD(1),
    PAIR(2),
    TWO_PARIS(3),
    THREE_OF_A_KIND(4),
    STRAIGHT(5),
    FLUSH(6),
    FULL_HOUSE(7),
    FOUR_OF_A_KIND(8),
    STRAIGHT_FLUSH(9),
    ROYAL_FLUSH(10);

    private final int valueOfCombination;

    Combination(int valueOfCombination) {
        this.valueOfCombination = valueOfCombination;
    }

    public int getValueOfCombination() {
        return valueOfCombination;
    }
}
