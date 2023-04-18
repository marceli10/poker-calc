package com.example.pokercalc.service.handlers;

import com.example.pokercalc.factory.CardBuilder;
import com.example.pokercalc.model.card.Card;
import com.example.pokercalc.model.card.Combination;
import com.example.pokercalc.service.algorithm.handlers.StraightFlushHandler;
import com.example.pokercalc.service.exceptions.CombinationNotFound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.example.pokercalc.model.card.CardColor.*;
import static com.example.pokercalc.model.card.CardValue.*;
import static com.example.pokercalc.model.card.Combination.STRAIGHT_FLUSH;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StraightFlushHandlerTest {
    private StraightFlushHandler handler;

    @BeforeEach
    void setup() {
        handler = new StraightFlushHandler();
    }

    @ParameterizedTest(name = "{index}{0}")
    @MethodSource("validStraightFlushCardCombinations")
    void shouldPassWithValidCardsCombination(String name, List<Card> validCardsCombination) {
        // given
        // when
        Combination receivedCombination = handler.handleCombination(validCardsCombination);

        // then
        assertThat(receivedCombination).isEqualTo(STRAIGHT_FLUSH);
    }

    @ParameterizedTest(name = "{index}{0}")
    @MethodSource("invalidStraightFlushCardCombinations")
    void shouldFailWhenInvalidCardCombination(String name, List<Card> invalidCardsCombination) {
        // given
        // when
        Throwable thrown = assertThrows(CombinationNotFound.class, () -> handler.handleCombination(invalidCardsCombination));

        // then
        assertThat(thrown).isInstanceOf(CombinationNotFound.class);
    }


    private static Stream<Arguments> validStraightFlushCardCombinations() {
        return Stream.of(
                Arguments.of("Straight spade flush", List.of(
                        new CardBuilder().withColor(SPADE).withValue(NINE).build(),
                        new CardBuilder().withColor(SPADE).withValue(KING).build(),
                        new CardBuilder().withColor(SPADE).withValue(QUEEN).build(),
                        new CardBuilder().withColor(SPADE).withValue(JACK).build(),
                        new CardBuilder().withColor(SPADE).withValue(TEN).build(),
                        new CardBuilder().withColor(HEART).withValue(ACE).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(ACE).build())
                ),
                Arguments.of("Straight diamond flush", List.of(
                        new CardBuilder().withColor(SPADE).withValue(ACE).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(TWO).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(THREE).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(FOUR).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(FIVE).build(),
                        new CardBuilder().withColor(HEART).withValue(ACE).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(ACE).build())
                ),
                Arguments.of("Straight heart flush", List.of(
                        new CardBuilder().withColor(SPADE).withValue(ACE).build(),
                        new CardBuilder().withColor(HEART).withValue(THREE).build(),
                        new CardBuilder().withColor(HEART).withValue(FOUR).build(),
                        new CardBuilder().withColor(HEART).withValue(FIVE).build(),
                        new CardBuilder().withColor(HEART).withValue(SIX).build(),
                        new CardBuilder().withColor(HEART).withValue(SEVEN).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(ACE).build())
                ),
                Arguments.of("Straight club flush", List.of(
                        new CardBuilder().withColor(CLUB).withValue(EIGHT).build(),
                        new CardBuilder().withColor(CLUB).withValue(NINE).build(),
                        new CardBuilder().withColor(CLUB).withValue(QUEEN).build(),
                        new CardBuilder().withColor(CLUB).withValue(JACK).build(),
                        new CardBuilder().withColor(CLUB).withValue(TEN).build(),
                        new CardBuilder().withColor(HEART).withValue(ACE).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(ACE).build())
                )
        );
    }
    private static Stream<Arguments> invalidStraightFlushCardCombinations() {
        return Stream.of(
                Arguments.of("Straight without flush", List.of(
                        new CardBuilder().withColor(SPADE).withValue(NINE).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(KING).build(),
                        new CardBuilder().withColor(SPADE).withValue(QUEEN).build(),
                        new CardBuilder().withColor(CLUB).withValue(JACK).build(),
                        new CardBuilder().withColor(CLUB).withValue(TEN).build(),
                        new CardBuilder().withColor(HEART).withValue(ACE).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(ACE).build())
                ),
                Arguments.of("Flush without straight", List.of(
                        new CardBuilder().withColor(SPADE).withValue(ACE).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(KING).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(THREE).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(EIGHT).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(FIVE).build(),
                        new CardBuilder().withColor(HEART).withValue(ACE).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(ACE).build())
                ),
                Arguments.of("Combination without straight and flush", List.of(
                        new CardBuilder().withColor(SPADE).withValue(ACE).build(),
                        new CardBuilder().withColor(SPADE).withValue(TWO).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(KING).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(EIGHT).build(),
                        new CardBuilder().withColor(CLUB).withValue(FIVE).build(),
                        new CardBuilder().withColor(CLUB).withValue(ACE).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(ACE).build())
                )
        );
    }
}
