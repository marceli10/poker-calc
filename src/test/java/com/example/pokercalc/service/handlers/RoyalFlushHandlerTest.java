package com.example.pokercalc.service.handlers;

import com.example.pokercalc.factory.CardBuilder;
import com.example.pokercalc.model.card.Card;
import com.example.pokercalc.model.card.Combination;
import com.example.pokercalc.service.algorithm.handlers.RoyalFlushHandler;
import com.example.pokercalc.service.exceptions.CombinationNotFound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.example.pokercalc.model.card.CardColor.*;
import static com.example.pokercalc.model.card.CardValue.*;
import static com.example.pokercalc.model.card.Combination.ROYAL_FLUSH;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RoyalFlushHandlerTest {

    private RoyalFlushHandler handler;

    @BeforeEach
    void setup() {
        handler = new RoyalFlushHandler();
    }

    @ParameterizedTest(name = "{index}{0}")
    @MethodSource("validRoyalFlushCardCombinations")
    void shouldPassWithValidCardsCombination(String name, List<Card> validCardsCombination) {
        // given
        // when
        Combination isRoyalFlushCombination = handler.handleCombination(validCardsCombination);

        // then
        assertThat(isRoyalFlushCombination).isEqualTo(ROYAL_FLUSH);
    }

    @ParameterizedTest(name = "{index}{0}")
    @MethodSource("invalidRoyalFlushCardCombinations")
    void shouldFailWhenInvalidCardCombination(String name, List<Card> invalidCardsCombination) {
        // given
        // when
        Throwable thrown = assertThrows(CombinationNotFound.class, () -> handler.handleCombination(invalidCardsCombination));

        // then
        assertThat(thrown).isInstanceOf(CombinationNotFound.class);
    }


    private static Stream<Arguments> validRoyalFlushCardCombinations() {
        return Stream.of(
                Arguments.of("Royal spade flush", List.of(
                        new CardBuilder().withColor(SPADE).withValue(ACE).build(),
                        new CardBuilder().withColor(SPADE).withValue(KING).build(),
                        new CardBuilder().withColor(SPADE).withValue(QUEEN).build(),
                        new CardBuilder().withColor(SPADE).withValue(JACK).build(),
                        new CardBuilder().withColor(SPADE).withValue(TEN).build(),
                        new CardBuilder().withColor(HEART).withValue(ACE).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(ACE).build())
                ),
                Arguments.of("Royal diamond flush", List.of(
                        new CardBuilder().withColor(SPADE).withValue(ACE).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(TEN).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(JACK).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(QUEEN).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(KING).build(),
                        new CardBuilder().withColor(HEART).withValue(ACE).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(ACE).build())
                ),
                Arguments.of("Royal heart flush", List.of(
                        new CardBuilder().withColor(SPADE).withValue(ACE).build(),
                        new CardBuilder().withColor(HEART).withValue(QUEEN).build(),
                        new CardBuilder().withColor(HEART).withValue(KING).build(),
                        new CardBuilder().withColor(HEART).withValue(TEN).build(),
                        new CardBuilder().withColor(HEART).withValue(JACK).build(),
                        new CardBuilder().withColor(HEART).withValue(ACE).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(ACE).build())
                ),
                Arguments.of("Royal club flush", List.of(
                        new CardBuilder().withColor(CLUB).withValue(ACE).build(),
                        new CardBuilder().withColor(CLUB).withValue(KING).build(),
                        new CardBuilder().withColor(CLUB).withValue(QUEEN).build(),
                        new CardBuilder().withColor(CLUB).withValue(JACK).build(),
                        new CardBuilder().withColor(CLUB).withValue(TEN).build(),
                        new CardBuilder().withColor(HEART).withValue(ACE).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(ACE).build())
                )
        );
    }
    private static Stream<Arguments> invalidRoyalFlushCardCombinations() {
        return Stream.of(
                Arguments.of("Almost valid", List.of(
                        new CardBuilder().withColor(SPADE).withValue(NINE).build(),
                        new CardBuilder().withColor(SPADE).withValue(KING).build(),
                        new CardBuilder().withColor(SPADE).withValue(QUEEN).build(),
                        new CardBuilder().withColor(SPADE).withValue(JACK).build(),
                        new CardBuilder().withColor(SPADE).withValue(TEN).build(),
                        new CardBuilder().withColor(HEART).withValue(ACE).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(ACE).build())
                ),
                Arguments.of("Color and straight combination but not royal", List.of(
                        new CardBuilder().withColor(SPADE).withValue(ACE).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(TWO).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(THREE).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(FOUR).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(FIVE).build(),
                        new CardBuilder().withColor(HEART).withValue(ACE).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(ACE).build())
                ),
                Arguments.of("Straight combination without color", List.of(
                        new CardBuilder().withColor(SPADE).withValue(ACE).build(),
                        new CardBuilder().withColor(CLUB).withValue(QUEEN).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(KING).build(),
                        new CardBuilder().withColor(CLUB).withValue(TEN).build(),
                        new CardBuilder().withColor(HEART).withValue(JACK).build(),
                        new CardBuilder().withColor(HEART).withValue(ACE).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(ACE).build())
                ),
                Arguments.of("Combination without straight and color", List.of(
                        new CardBuilder().withColor(HEART).withValue(ACE).build(),
                        new CardBuilder().withColor(CLUB).withValue(KING).build(),
                        new CardBuilder().withColor(CLUB).withValue(THREE).build(),
                        new CardBuilder().withColor(CLUB).withValue(JACK).build(),
                        new CardBuilder().withColor(CLUB).withValue(SIX).build(),
                        new CardBuilder().withColor(HEART).withValue(ACE).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(SEVEN).build())
                )
        );
    }
}
