package com.example.pokercalc.service.handlers;

import com.example.pokercalc.factory.CardBuilder;
import com.example.pokercalc.model.card.Card;
import com.example.pokercalc.model.card.Combination;
import com.example.pokercalc.service.algorithm.handlers.StraightHandler;
import com.example.pokercalc.service.exceptions.CombinationNotFound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.example.pokercalc.model.card.Combination.STRAIGHT;
import static com.example.pokercalc.model.card.CardColor.*;
import static com.example.pokercalc.model.card.CardValue.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StraightHandlerTest {

    private StraightHandler handler;

    @BeforeEach
    void setup() {handler = new StraightHandler();}

    @ParameterizedTest(name = "{0}")
    @MethodSource("validStraightCardCombinations")
    void shouldPassWithValidCardsCombination(String name, List<Card> validCardsCombination) {
        // given
        // when
        Combination receivedCombination = handler.handleCombination(validCardsCombination);

        // then
        assertThat(receivedCombination).isEqualTo(STRAIGHT);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidStraightCardCombinations")
    void shouldFailWhenInvalidCardCombination(String name, List<Card> invalidCardsCombination) {
        // given
        // when
        Throwable thrown = assertThrows(CombinationNotFound.class, () -> handler.handleCombination(invalidCardsCombination));

        // then
        assertThat(thrown).isInstanceOf(CombinationNotFound.class);
    }

    private static Stream<Arguments> validStraightCardCombinations() {
        return Stream.of(
                Arguments.of("Simple Straight", List.of(
                        new CardBuilder().withColor(HEART).withValue(NINE).build(),
                        new CardBuilder().withColor(SPADE).withValue(EIGHT).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(SEVEN).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(SIX).build(),
                        new CardBuilder().withColor(CLUB).withValue(FIVE).build(),
                        new CardBuilder().withColor(CLUB).withValue(THREE).build(),
                        new CardBuilder().withColor(CLUB).withValue(KING).build()
                )),
                Arguments.of("All Straight", List.of(
                        new CardBuilder().withColor(HEART).withValue(EIGHT).build(),
                        new CardBuilder().withColor(SPADE).withValue(FOUR).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(TEN).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(SEVEN).build(),
                        new CardBuilder().withColor(CLUB).withValue(FIVE).build(),
                        new CardBuilder().withColor(CLUB).withValue(NINE).build(),
                        new CardBuilder().withColor(CLUB).withValue(SIX).build()
                )),
                Arguments.of("Straight with same values", List.of(
                        new CardBuilder().withColor(HEART).withValue(ACE).build(),
                        new CardBuilder().withColor(SPADE).withValue(KING).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(TEN).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(QUEEN).build(),
                        new CardBuilder().withColor(CLUB).withValue(JACK).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(JACK).build(),
                        new CardBuilder().withColor(CLUB).withValue(ACE).build()
                )),
                Arguments.of("Straight with Ace", List.of(
                        new CardBuilder().withColor(HEART).withValue(ACE).build(),
                        new CardBuilder().withColor(SPADE).withValue(FIVE).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(FOUR).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(THREE).build(),
                        new CardBuilder().withColor(CLUB).withValue(TWO).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(JACK).build(),
                        new CardBuilder().withColor(CLUB).withValue(ACE).build()
                ))
        );
    }

    private static Stream<Arguments> invalidStraightCardCombinations() {
        return Stream.of(
                Arguments.of("Just not a straight", List.of(
                        new CardBuilder().withColor(HEART).withValue(NINE).build(),
                        new CardBuilder().withColor(CLUB).withValue(TWO).build(),
                        new CardBuilder().withColor(HEART).withValue(FOUR).build(),
                        new CardBuilder().withColor(SPADE).withValue(NINE).build(),
                        new CardBuilder().withColor(HEART).withValue(TWO).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(ACE).build(),
                        new CardBuilder().withColor(HEART).withValue(ACE).build()
                )),
                Arguments.of("Almost straight", List.of(
                        new CardBuilder().withColor(HEART).withValue(KING).build(),
                        new CardBuilder().withColor(SPADE).withValue(FIVE).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(FOUR).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(SIX).build(),
                        new CardBuilder().withColor(CLUB).withValue(TWO).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(JACK).build(),
                        new CardBuilder().withColor(CLUB).withValue(ACE).build()
                ))
        );
    }
}
