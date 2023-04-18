package com.example.pokercalc.model.deck;

import com.example.pokercalc.model.card.Card;
import com.example.pokercalc.model.card.CardColor;
import com.example.pokercalc.model.card.CardValue;

import java.util.LinkedList;
import java.util.List;

public class Deck {

    private final List<Card> deck = new LinkedList<>();

    public Deck() {
        deck.addAll(createClubCards());
        deck.addAll(createDiamondCards());
        deck.addAll(createHeartCards());
        deck.addAll(createSpadeCards());
    }

    public List<Card> getDeck() {
        return deck;
    }

    private List<Card> createDiamondCards() {
        return List.of(
                new Card(CardColor.DIAMOND, CardValue.ACE),
                new Card(CardColor.DIAMOND, CardValue.TWO),
                new Card(CardColor.DIAMOND, CardValue.THREE),
                new Card(CardColor.DIAMOND, CardValue.FOUR),
                new Card(CardColor.DIAMOND, CardValue.FIVE),
                new Card(CardColor.DIAMOND, CardValue.SIX),
                new Card(CardColor.DIAMOND, CardValue.SEVEN),
                new Card(CardColor.DIAMOND, CardValue.EIGHT),
                new Card(CardColor.DIAMOND, CardValue.NINE),
                new Card(CardColor.DIAMOND, CardValue.TEN),
                new Card(CardColor.DIAMOND, CardValue.JACK),
                new Card(CardColor.DIAMOND, CardValue.QUEEN),
                new Card(CardColor.DIAMOND, CardValue.KING)
        );
    }

    private List<Card> createClubCards() {
        return List.of(
                new Card(CardColor.CLUB, CardValue.ACE),
                new Card(CardColor.CLUB, CardValue.TWO),
                new Card(CardColor.CLUB, CardValue.THREE),
                new Card(CardColor.CLUB, CardValue.FOUR),
                new Card(CardColor.CLUB, CardValue.FIVE),
                new Card(CardColor.CLUB, CardValue.SIX),
                new Card(CardColor.CLUB, CardValue.SEVEN),
                new Card(CardColor.CLUB, CardValue.EIGHT),
                new Card(CardColor.CLUB, CardValue.NINE),
                new Card(CardColor.CLUB, CardValue.TEN),
                new Card(CardColor.CLUB, CardValue.JACK),
                new Card(CardColor.CLUB, CardValue.QUEEN),
                new Card(CardColor.CLUB, CardValue.KING)
        );
    }

    private List<Card> createHeartCards() {
        return List.of(
                new Card(CardColor.HEART, CardValue.ACE),
                new Card(CardColor.HEART, CardValue.TWO),
                new Card(CardColor.HEART, CardValue.THREE),
                new Card(CardColor.HEART, CardValue.FOUR),
                new Card(CardColor.HEART, CardValue.FIVE),
                new Card(CardColor.HEART, CardValue.SIX),
                new Card(CardColor.HEART, CardValue.SEVEN),
                new Card(CardColor.HEART, CardValue.EIGHT),
                new Card(CardColor.HEART, CardValue.NINE),
                new Card(CardColor.HEART, CardValue.TEN),
                new Card(CardColor.HEART, CardValue.JACK),
                new Card(CardColor.HEART, CardValue.QUEEN),
                new Card(CardColor.HEART, CardValue.KING)
        );
    }

    private List<Card> createSpadeCards() {
        return List.of(
                new Card(CardColor.SPADE, CardValue.ACE),
                new Card(CardColor.SPADE, CardValue.TWO),
                new Card(CardColor.SPADE, CardValue.THREE),
                new Card(CardColor.SPADE, CardValue.FOUR),
                new Card(CardColor.SPADE, CardValue.FIVE),
                new Card(CardColor.SPADE, CardValue.SIX),
                new Card(CardColor.SPADE, CardValue.SEVEN),
                new Card(CardColor.SPADE, CardValue.EIGHT),
                new Card(CardColor.SPADE, CardValue.NINE),
                new Card(CardColor.SPADE, CardValue.TEN),
                new Card(CardColor.SPADE, CardValue.JACK),
                new Card(CardColor.SPADE, CardValue.QUEEN),
                new Card(CardColor.SPADE, CardValue.KING)
        );
    }

}
