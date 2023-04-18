package com.example.pokercalc.service.chain;

import com.example.pokercalc.factory.CardBuilder;
import com.example.pokercalc.model.card.Card;
import com.example.pokercalc.model.card.Combination;
import com.example.pokercalc.service.algorithm.chain.PokerCombinationChain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.example.pokercalc.model.card.CardColor.*;
import static com.example.pokercalc.model.card.CardValue.*;
import static com.example.pokercalc.model.card.Combination.*;
import static org.assertj.core.api.Assertions.assertThat;

class PokerCombinationChainTest {

    private PokerCombinationChain pokerCombinationChain;

    @BeforeEach
    void setup() {
        pokerCombinationChain = new PokerCombinationChain();
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("validRoyalFlushCardCombinations")
    void shouldReturnRoyalFlushCombination(String name, List<Card> cards) {
        // given
        // when
        Combination receivedCombination = pokerCombinationChain.getCombination(cards);
        // then
        assertThat(receivedCombination).isEqualTo(ROYAL_FLUSH);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("validStraightFlushCardCombinations")
    void shouldReturnStraightFlushCombination(String name, List<Card> cards) {
        // given
        // when
        Combination receivedCombination = pokerCombinationChain.getCombination(cards);
        // then
        assertThat(receivedCombination).isEqualTo(STRAIGHT_FLUSH);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("validFourOfAKindCombinations")
    void shouldReturnFourOfAKindCombination(String name, List<Card> cards) {
        // given
        // when
        Combination receivedCombination = pokerCombinationChain.getCombination(cards);
        // then
        assertThat(receivedCombination).isEqualTo(FOUR_OF_A_KIND);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("validFullHouseCombination")
    void shouldReturnFullHouseCombination(String name, List<Card> cards) {
        // given
        // when
        Combination receivedCombination = pokerCombinationChain.getCombination(cards);
        // then
        assertThat(receivedCombination).isEqualTo(FULL_HOUSE);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("validFlushCardCombinations")
    void shouldReturnFlushCombination(String name, List<Card> cards) {
        // given
        // when
        Combination receivedCombination = pokerCombinationChain.getCombination(cards);
        // then
        assertThat(receivedCombination).isEqualTo(FLUSH);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("validStraightCardCombinations")
    void shouldReturnStraightCombination(String name, List<Card> cards) {
        // given
        // when
        Combination receivedCombination = pokerCombinationChain.getCombination(cards);
        // then
        assertThat(receivedCombination).isEqualTo(STRAIGHT);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("validThreeOfAKindCombination")
    void shouldReturnThreeOfAKindCombination(String name, List<Card> cards) {
        // given
        // when
        Combination receivedCombination = pokerCombinationChain.getCombination(cards);
        // then
        assertThat(receivedCombination).isEqualTo(THREE_OF_A_KIND);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("validTwoPairsCardCombinations")
    void shouldReturnTwoPairsCombination(String name, List<Card> cards) {
        // given
        // when
        Combination receivedCombination = pokerCombinationChain.getCombination(cards);
        // then
        assertThat(receivedCombination).isEqualTo(TWO_PAIRS);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("validPairCardCombinations")
    void shouldReturnPairCombination(String name, List<Card> cards) {
        // given
        // when
        Combination receivedCombination = pokerCombinationChain.getCombination(cards);
        // then
        assertThat(receivedCombination).isEqualTo(PAIR);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("validHighCardCombinations")
    void shouldReturnHighCardCombination(String name, List<Card> cards) {
        // given
        // when
        Combination receivedCombination = pokerCombinationChain.getCombination(cards);
        // then
        assertThat(receivedCombination).isEqualTo(HIGH_CARD);
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
                                new CardBuilder().withColor(DIAMOND).withValue(ACE).build()
                        )
                )
        );
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

    private static Stream<Arguments> validFullHouseCombination() {
        return Stream.of(
                Arguments.of("Full with queens and nines", List.of(
                        new CardBuilder().withValue(QUEEN).withColor(HEART).build(),
                        new CardBuilder().withValue(QUEEN).withColor(CLUB).build(),
                        new CardBuilder().withValue(QUEEN).withColor(DIAMOND).build(),
                        new CardBuilder().withValue(NINE).withColor(SPADE).build(),
                        new CardBuilder().withValue(NINE).withColor(HEART).build(),
                        new CardBuilder().withValue(FIVE).withColor(HEART).build(),
                        new CardBuilder().withValue(FOUR).withColor(SPADE).build())
                ),
                Arguments.of("Full with kings and tens", List.of(
                        new CardBuilder().withValue(KING).withColor(HEART).build(),
                        new CardBuilder().withValue(KING).withColor(CLUB).build(),
                        new CardBuilder().withValue(KING).withColor(DIAMOND).build(),
                        new CardBuilder().withValue(TEN).withColor(SPADE).build(),
                        new CardBuilder().withValue(TEN).withColor(HEART).build(),
                        new CardBuilder().withValue(FIVE).withColor(HEART).build(),
                        new CardBuilder().withValue(FOUR).withColor(SPADE).build())
                )
        );
    }

    private static Stream<Arguments> validFlushCardCombinations() {
        return Stream.of(
                Arguments.of("Spade flush", List.of(
                        new CardBuilder().withColor(SPADE).withValue(KING).build(),
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
                        new CardBuilder().withColor(SPADE).withValue(FIVE).build(),
                        new CardBuilder().withColor(HEART).withValue(QUEEN).build(),
                        new CardBuilder().withColor(HEART).withValue(KING).build(),
                        new CardBuilder().withColor(HEART).withValue(EIGHT).build(),
                        new CardBuilder().withColor(HEART).withValue(JACK).build(),
                        new CardBuilder().withColor(HEART).withValue(TWO).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(THREE).build())
                ),
                Arguments.of("Club flush", List.of(
                        new CardBuilder().withColor(CLUB).withValue(ACE).build(),
                        new CardBuilder().withColor(CLUB).withValue(KING).build(),
                        new CardBuilder().withColor(CLUB).withValue(QUEEN).build(),
                        new CardBuilder().withColor(CLUB).withValue(TWO).build(),
                        new CardBuilder().withColor(CLUB).withValue(TEN).build(),
                        new CardBuilder().withColor(HEART).withValue(THREE).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(THREE).build())
                )
        );
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

    private static Stream<Arguments> validPairCardCombinations() {
        return Stream.of(
                Arguments.of("One Pair", List.of(
                        new CardBuilder().withColor(SPADE).withValue(TWO).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(TWO).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(FOUR).build(),
                        new CardBuilder().withColor(CLUB).withValue(FIVE).build(),
                        new CardBuilder().withColor(HEART).withValue(ACE).build(),
                        new CardBuilder().withColor(HEART).withValue(KING).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(JACK).build())
                )
        );
    }

    private static Stream<Arguments> validHighCardCombinations() {
        return Stream.of(
                Arguments.of("High card", List.of(
                        new CardBuilder().withColor(SPADE).withValue(TWO).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(NINE).build(),
                        new CardBuilder().withColor(CLUB).withValue(FOUR).build(),
                        new CardBuilder().withColor(CLUB).withValue(FIVE).build(),
                        new CardBuilder().withColor(HEART).withValue(ACE).build(),
                        new CardBuilder().withColor(HEART).withValue(KING).build(),
                        new CardBuilder().withColor(DIAMOND).withValue(JACK).build())
                )
        );
    }

}
