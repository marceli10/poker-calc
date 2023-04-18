package com.example.pokercalc.service.algorithm;

import com.example.pokercalc.factory.CardBuilder;
import com.example.pokercalc.model.card.Card;
import com.example.pokercalc.model.card.Combination;
import org.junit.jupiter.api.BeforeEach;

import static com.example.pokercalc.model.card.CardValue.*;
import static com.example.pokercalc.model.card.CardColor.*;
import static com.example.pokercalc.model.card.Combination.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;

public class FourOfAKindHandlerTest {

    private FourOfAKindHandler handler;

    @BeforeEach
    void setup() {
        handler = new FourOfAKindHandler();
    }

    void shouldPassWithValidCardsCombination() {

        List<Card> cardsWithFourOfAKind = List.of(
                new CardBuilder().withValue(NINE).withColor(HEART).build(),
                new CardBuilder().withValue(NINE).withColor(CLUB).build(),
                new CardBuilder().withValue(NINE).withColor(DIAMOND).build(),
                new CardBuilder().withValue(NINE).withColor(SPADE).build(),
                new CardBuilder().withValue(ACE).withColor(HEART).build(),
                new CardBuilder().withValue(KING).withColor(HEART).build(),
                new CardBuilder().withValue(THREE).withColor(HEART).build()
        );

        Combination isFourOfAKindCombination = handler.handleCombination(cardsWithFourOfAKind);

        assertThat(isFourOfAKindCombination).isEqualTo(FOUR_OF_A_KIND);
    }


}
