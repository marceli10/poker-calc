package com.example.pokercalc.service.algorithm;
package com.example.pokercalc.service.algorithm;

import com.example.pokercalc.factory.CardBuilder;
import com.example.pokercalc.model.card.Card;
import com.example.pokercalc.model.card.Combination;
import org.junit.jupiter.api.BeforeEach;

import static com.example.pokercalc.model.card.CardValue.*;
import static com.example.pokercalc.model.card.CardColor.*;

import java.util.List;

public class FullHouseHandlerTest {
    private FullHouseHandler handler;

    @BeforeEach
    void setup() {
        handler = new FullHouseHandler();

    }

    void shouldPassWithValidCardsCombination() {
        List<Card> cardsWithFullHouse = List.of(
                new CardBuilder().withValue(QUEEN).withColor(HEART).build(),
                new CardBuilder().withValue(QUEEN).withColor(CLUB).build(),
                new CardBuilder().withValue(QUEEN).withColor(DIAMOND).build(),
                new CardBuilder().withValue(NINE).withColor(SPADE).build(),
                new CardBuilder().withValue(NINE).withColor(HEART).build(),
                new CardBuilder().withValue(FIVE).withColor(HEART).build(),
                new CardBuilder().withValue(FOUR).withColor(SPADE).build();
        )
    }
}
