package com.example.pokercalc.service.handlers;

import com.example.pokercalc.factory.CardBuilder;
import com.example.pokercalc.model.card.Card;
import com.example.pokercalc.model.card.Combination;
import com.example.pokercalc.service.algorithm.handlers.FlushHandler;
import com.example.pokercalc.service.exceptions.CombinationNotFound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.example.pokercalc.model.card.CardColor.*;
import static com.example.pokercalc.model.card.CardValue.*;
import static com.example.pokercalc.model.card.Combination.FLUSH;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FlushHandlerTest {
    private FlushHandler handler;

    @BeforeEach
    void setup() {
        handler = new FlushHandler();
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("validFlushCardCombinations")
    void shouldPassWithValidCardsCombination(String name, List<Card> validCardsCombination) {
        // given
        // when
        Combination receivedCombination = handler.handleCombination(validCardsCombination);

        // then
        assertThat(receivedCombination).isEqualTo(FLUSH);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidRoyalFlushCardCombinations")
    void shouldFailWhenInvalidCardCombination(String name, List<Card> invalidCardsCombination) {
        // given
        // when
        Throwable thrown = assertThrows(CombinationNotFound.class, () -> handler.handleCombination(invalidCardsCombination));

        // then
        assertThat(thrown).isInstanceOf(CombinationNotFound.class);
    }


    private static Stream<Arguments> validFlushCardCombinations() {
        return Stream.of(
                Arguments.of("Spade flush", List.of(
                        new CardBuilder().withColor(SPADE).withValue(TWO).build(),
                        new CardBuilder().withColor(SPADE).withValue(THREE).build(),
                        new CardBuilder().withColor(SPADE).withValue(FOUR).build(),
                        new CardBuilder().withColor(SPADE).withValue(FIVE).build(),
                        new CardBuilder().withColor(SPADE).withValue(ACE).build(),
                        new CardBuilder().withColor(HEART).withValue(ACE).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(ACE).build())
                ),
                Arguments.of("Diamond flush", List.of(
                        new CardBuilder().withColor(SPADE).withValue(ACE).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(TEN).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(SIX).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(QUEEN).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(FOUR).build(),
                        new CardBuilder().withColor(HEART).withValue(ACE).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(ACE).build())
                ),
                Arguments.of("Heart flush", List.of(
                        new CardBuilder().withColor(SPADE).withValue(ACE).build(),
                        new CardBuilder().withColor(HEART).withValue(QUEEN).build(),
                        new CardBuilder().withColor(HEART).withValue(KING).build(),
                        new CardBuilder().withColor(HEART).withValue(TEN).build(),
                        new CardBuilder().withColor(HEART).withValue(JACK).build(),
                        new CardBuilder().withColor(HEART).withValue(ACE).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(ACE).build())
                ),
                Arguments.of("Club flush", List.of(
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
                Arguments.of("Four cards in same color", List.of(
                        new CardBuilder().withColor(SPADE).withValue(NINE).build(),
                        new CardBuilder().withColor(SPADE).withValue(KING).build(),
                        new CardBuilder().withColor(HEART).withValue(QUEEN).build(),
                        new CardBuilder().withColor(SPADE).withValue(JACK).build(),
                        new CardBuilder().withColor(SPADE).withValue(TEN).build(),
                        new CardBuilder().withColor(HEART).withValue(ACE).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(ACE).build())
                ),
                Arguments.of("Three cards in same color", List.of(
                        new CardBuilder().withColor(SPADE).withValue(ACE).build(),
                        new CardBuilder().withColor(SPADE).withValue(TWO).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(THREE).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(FOUR).build(),
                        new CardBuilder().withColor(HEART).withValue(FIVE).build(),
                        new CardBuilder().withColor(HEART).withValue(ACE).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(ACE).build())
                ),
                Arguments.of("Two cards in same color", List.of(
                        new CardBuilder().withColor(SPADE).withValue(ACE).build(),
                        new CardBuilder().withColor(SPADE).withValue(QUEEN).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(KING).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(TEN).build(),
                        new CardBuilder().withColor(HEART).withValue(JACK).build(),
                        new CardBuilder().withColor(HEART).withValue(ACE).build(),
                        new CardBuilder().withColor(CLUB).withValue(ACE).build())
                )
        );
    }
}
