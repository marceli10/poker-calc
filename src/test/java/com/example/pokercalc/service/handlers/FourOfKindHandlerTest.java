package com.example.pokercalc.service.handlers;

import com.example.pokercalc.factory.CardBuilder;
import com.example.pokercalc.model.card.Card;
import com.example.pokercalc.model.card.Combination;
import com.example.pokercalc.service.algorithm.handlers.FourOfKindHandler;
import com.example.pokercalc.service.exceptions.CombinationNotFound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.example.pokercalc.model.card.CardColor.*;
import static com.example.pokercalc.model.card.CardValue.*;
import static com.example.pokercalc.model.card.Combination.FOUR_OF_A_KIND;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FourOfKindHandlerTest {

    private FourOfKindHandler handler;

    @BeforeEach
    void setup() {
        handler = new FourOfKindHandler();
    }

    @ParameterizedTest(name = "{index} {0}")
    @MethodSource("validFourOfAKindCombinations")
    void shouldPassWithValidCardsCombination(String name, List<Card> validCardsCombination) {

        // given
        Combination isFourOfAKindCombination = handler.handleCombination(validCardsCombination);

        // expect
        assertThat(isFourOfAKindCombination).isEqualTo(FOUR_OF_A_KIND);
    }

    @ParameterizedTest(name = "{index} {0}")
    @MethodSource("invalidFourOfAKindCombinations")
    void shouldFailWhenInvalidCardCombination(String name, List<Card> invalidCardsCombination) {

        // given
        Throwable thrown = assertThrows(CombinationNotFound.class, () -> handler.handleCombination(invalidCardsCombination));

        // expect
        assertThat(thrown).isInstanceOf(CombinationNotFound.class);
    }

    private static Stream<Arguments> validFourOfAKindCombinations() {
        return Stream.of(
                Arguments.of("Four of Aces with three of a kind", List.of(
                        new CardBuilder().withColor(SPADE).withValue(ACE).build(),
                        new CardBuilder().withColor(CLUB).withValue(ACE).build(),
                        new CardBuilder().withColor(HEART).withValue(ACE).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(ACE).build(),
                        new CardBuilder().withColor(SPADE).withValue(TEN).build(),
                        new CardBuilder().withColor(HEART).withValue(TEN).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(TEN).build())
                ),
                Arguments.of("Four of Kings with a pair", List.of(
                        new CardBuilder().withColor(SPADE).withValue(KING).build(),
                        new CardBuilder().withColor(CLUB).withValue(KING).build(),
                        new CardBuilder().withColor(HEART).withValue(KING).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(KING).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(TEN).build(),
                        new CardBuilder().withColor(HEART).withValue(THREE).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(TWO).build())
                ),
                Arguments.of("Four of Threes with a flush of three of a kind", List.of(
                        new CardBuilder().withColor(SPADE).withValue(THREE).build(),
                        new CardBuilder().withColor(CLUB).withValue(THREE).build(),
                        new CardBuilder().withColor(HEART).withValue(THREE).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(THREE).build(),
                        new CardBuilder().withColor(HEART).withValue(TEN).build(),
                        new CardBuilder().withColor(HEART).withValue(FIVE).build(),
                        new CardBuilder().withColor(HEART).withValue(TWO).build())
                ),
                Arguments.of("Four of Jacks", List.of(
                        new CardBuilder().withColor(SPADE).withValue(JACK).build(),
                        new CardBuilder().withColor(CLUB).withValue(JACK).build(),
                        new CardBuilder().withColor(HEART).withValue(JACK).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(JACK).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(TEN).build(),
                        new CardBuilder().withColor(HEART).withValue(THREE).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(TWO).build())
                )
        );
    }
    private static Stream<Arguments> invalidFourOfAKindCombinations() {
        return Stream.of(
                Arguments.of("Four of a color", List.of(
                        new CardBuilder().withColor(SPADE).withValue(ACE).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(TWO).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(THREE).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(FOUR).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(FIVE).build(),
                        new CardBuilder().withColor(HEART).withValue(QUEEN).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(KING).build())
                ),
                Arguments.of("Three and a pair", List.of(
                        new CardBuilder().withColor(SPADE).withValue(ACE).build(),
                        new CardBuilder().withColor(CLUB).withValue(ACE).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(ACE).build(),
                        new CardBuilder().withColor(CLUB).withValue(TEN).build(),
                        new CardBuilder().withColor(HEART).withValue(JACK).build(),
                        new CardBuilder().withColor(HEART).withValue(FOUR).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(FOUR).build())
                ),
                Arguments.of("Combination without four of a kind", List.of(
                        new CardBuilder().withColor(HEART).withValue(ACE).build(),
                        new CardBuilder().withColor(CLUB).withValue(KING).build(),
                        new CardBuilder().withColor(CLUB).withValue(THREE).build(),
                        new CardBuilder().withColor(CLUB).withValue(JACK).build(),
                        new CardBuilder().withColor(CLUB).withValue(SIX).build(),
                        new CardBuilder().withColor(HEART).withValue(TWO).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(SEVEN).build())
                )
        );
    }

}
