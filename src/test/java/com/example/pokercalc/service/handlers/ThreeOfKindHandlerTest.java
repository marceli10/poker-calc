package com.example.pokercalc.service.handlers;

import com.example.pokercalc.factory.CardBuilder;
import com.example.pokercalc.model.card.Card;
import com.example.pokercalc.model.card.Combination;
import com.example.pokercalc.service.algorithm.handlers.ThreeOfKindHandler;
import com.example.pokercalc.service.exceptions.CombinationNotFound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.example.pokercalc.model.card.CardColor.*;
import static com.example.pokercalc.model.card.CardValue.*;
import static com.example.pokercalc.model.card.Combination.THREE_OF_A_KIND;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ThreeOfKindHandlerTest {
    private ThreeOfKindHandler handler;

    @BeforeEach
    void setup() {
        handler = new ThreeOfKindHandler();

    }

    @ParameterizedTest(name = "{index}{0}")
    @MethodSource("validThreeOfAKindCombination")
    void shouldPassWithValidCardsCombination(String name, List<Card> validCardsCombination) {

        // given
        //
        Combination isFullHouseCombination = handler.handleCombination(validCardsCombination);

        // expect
        assertThat(isFullHouseCombination).isEqualTo(THREE_OF_A_KIND);
    }

    @ParameterizedTest(name = "{index}{0}")
    @MethodSource("invalidThreeOfAKindCombination")
    void shouldFailWhenInvalidCardCombination(String name, List<Card> invalidCardsCombination) {
        // given
        //
        Throwable thrown = assertThrows(CombinationNotFound.class, () -> handler.handleCombination(invalidCardsCombination));

        // expect
        assertThat(thrown).isInstanceOf(CombinationNotFound.class);
    }

    private static Stream<Arguments> validThreeOfAKindCombination() {
        return Stream.of(
                Arguments.of("Three of queens", List.of(
                        new CardBuilder().withValue(QUEEN).withColor(HEART).build(),
                        new CardBuilder().withValue(QUEEN).withColor(CLUB).build(),
                        new CardBuilder().withValue(QUEEN).withColor(DIAMOND).build(),
                        new CardBuilder().withValue(TWO).withColor(SPADE).build(),
                        new CardBuilder().withValue(NINE).withColor(HEART).build(),
                        new CardBuilder().withValue(FIVE).withColor(HEART).build(),
                        new CardBuilder().withValue(FOUR).withColor(SPADE).build())
                ),
                Arguments.of("Three of  kings", List.of(
                        new CardBuilder().withValue(KING).withColor(HEART).build(),
                        new CardBuilder().withValue(KING).withColor(CLUB).build(),
                        new CardBuilder().withValue(KING).withColor(DIAMOND).build(),
                        new CardBuilder().withValue(TEN).withColor(SPADE).build(),
                        new CardBuilder().withValue(TWO).withColor(HEART).build(),
                        new CardBuilder().withValue(FIVE).withColor(HEART).build(),
                        new CardBuilder().withValue(FOUR).withColor(SPADE).build())
                )
        );
    }

    private static Stream<Arguments> invalidThreeOfAKindCombination() {
        return Stream.of(
                Arguments.of("Two pairs", List.of(
                        new CardBuilder().withValue(QUEEN).withColor(HEART).build(),
                        new CardBuilder().withValue(QUEEN).withColor(CLUB).build(),
                        new CardBuilder().withValue(TWO).withColor(DIAMOND).build(),
                        new CardBuilder().withValue(NINE).withColor(SPADE).build(),
                        new CardBuilder().withValue(NINE).withColor(HEART).build(),
                        new CardBuilder().withValue(FIVE).withColor(HEART).build(),
                        new CardBuilder().withValue(FOUR).withColor(SPADE).build())
                ),
                Arguments.of("Pair of a kind", List.of(
                        new CardBuilder().withValue(KING).withColor(HEART).build(),
                        new CardBuilder().withValue(KING).withColor(CLUB).build(),
                        new CardBuilder().withValue(ACE).withColor(DIAMOND).build(),
                        new CardBuilder().withValue(TEN).withColor(SPADE).build(),
                        new CardBuilder().withValue(TWO).withColor(HEART).build(),
                        new CardBuilder().withValue(FIVE).withColor(HEART).build(),
                        new CardBuilder().withValue(FOUR).withColor(SPADE).build())
                )
        );
    }
}
