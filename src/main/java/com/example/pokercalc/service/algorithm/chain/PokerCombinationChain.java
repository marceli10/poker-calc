package com.example.pokercalc.service.algorithm.chain;

import com.example.pokercalc.model.card.Card;
import com.example.pokercalc.model.card.Combination;
import com.example.pokercalc.service.algorithm.handlers.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PokerCombinationChain {
    private final CombinationHandler combinationHandler;

    public PokerCombinationChain() {
        combinationHandler = new RoyalFlushHandler();
        CombinationHandler straightFlushHandler = new StraightFlushHandler();
        CombinationHandler fourOfKindHandler = new FourOfKindHandler();
        CombinationHandler fullHouseHandler = new FullHouseHandler();
        CombinationHandler flushHandler = new FlushHandler();
        CombinationHandler straightHandler = new StraightHandler();
        CombinationHandler threeOfKindHandler = new ThreeOfKindHandler();
        CombinationHandler twoPairsHandler= new TwoPairsHandler();
        CombinationHandler pairHandler= new PairHandler();
        CombinationHandler highCardHandler = new HighCardHandler();

        combinationHandler.setNextCombination(straightFlushHandler);
        straightFlushHandler.setNextCombination(fourOfKindHandler);
        fourOfKindHandler.setNextCombination(fullHouseHandler);
        fullHouseHandler.setNextCombination(flushHandler);
        flushHandler.setNextCombination(straightHandler);
        straightHandler.setNextCombination(threeOfKindHandler);
        threeOfKindHandler.setNextCombination(twoPairsHandler);
        twoPairsHandler.setNextCombination(pairHandler);
        pairHandler.setNextCombination(highCardHandler);
    }

    public Combination getCombination(List<Card> cards) {
        return combinationHandler.handleCombination(cards);
    }
}
