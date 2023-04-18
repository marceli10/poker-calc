package com.example.pokercalc.service.handlers;

import com.example.pokercalc.factory.CardBuilder;
import com.example.pokercalc.model.card.Card;
import com.example.pokercalc.model.card.Combination;
import com.example.pokercalc.service.algorithm.handlers.TwoPairsHandler;
import com.example.pokercalc.service.exceptions.CombinationNotFound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.example.pokercalc.model.card.CardColor.*;
import static com.example.pokercalc.model.card.CardValue.*;
import static com.example.pokercalc.model.card.CardValue.ACE;
import static com.example.pokercalc.model.card.Combination.TWO_PAIRS;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TwoPairsHandlerTest {
    private TwoPairsHandler handler;

    @BeforeEach
    void setup() {
        handler = new TwoPairsHandler();
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("validTwoPairsCardCombinations")
    void shouldPassWithValidCardsCombination(String name, List<Card> validCardsCombination) {
        // given
        // when
        Combination receivedCombination = handler.handleCombination(validCardsCombination);

        // then
        assertThat(receivedCombination).isEqualTo(TWO_PAIRS);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidTwoPairsCardCombinations")
    void shouldFailWhenInvalidCardCombination(String name, List<Card> invalidCardsCombination) {
        // given
        // when
        Throwable thrown = assertThrows(CombinationNotFound.class, () -> handler.handleCombination(invalidCardsCombination));

        // then
        assertThat(thrown).isInstanceOf(CombinationNotFound.class);
    }


    private static Stream<Arguments> validTwoPairsCardCombinations() {
        return Stream.of(
                Arguments.of("Two pairs", List.of(
                        new CardBuilder().withColor(SPADE).withValue(TWO).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(TWO).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(FOUR).build(),
                        new CardBuilder().withColor(CLUB).withValue(FOUR).build(),
                        new CardBuilder().withColor(HEART).withValue(ACE).build(),
                        new CardBuilder().withColor(HEART).withValue(KING).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(JACK).build())
                ),
                Arguments.of("Two pairs", List.of(
                        new CardBuilder().withColor(SPADE).withValue(THREE).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(TWO).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(FOUR).build(),
                        new CardBuilder().withColor(CLUB).withValue(KING).build(),
                        new CardBuilder().withColor(HEART).withValue(THREE).build(),
                        new CardBuilder().withColor(HEART).withValue(KING).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(JACK).build())
                )
        );
    }
    private static Stream<Arguments> invalidTwoPairsCardCombinations() {
        return Stream.of(
                Arguments.of("Only one pair", List.of(
                        new CardBuilder().withColor(SPADE).withValue(NINE).build(),
                        new CardBuilder().withColor(CLUB).withValue(KING).build(),
                        new CardBuilder().withColor(HEART).withValue(QUEEN).build(),
                        new CardBuilder().withColor(CLUB).withValue(JACK).build(),
                        new CardBuilder().withColor(SPADE).withValue(TEN).build(),
                        new CardBuilder().withColor(HEART).withValue(ACE).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(ACE).build())
                ),
                Arguments.of("No pair in combination", List.of(
                        new CardBuilder().withColor(SPADE).withValue(NINE).build(),
                        new CardBuilder().withColor(SPADE).withValue(TWO).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(THREE).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(FOUR).build(),
                        new CardBuilder().withColor(HEART).withValue(FIVE).build(),
                        new CardBuilder().withColor(HEART).withValue(ACE).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(QUEEN).build())
                )
        );
    }
}
