package com.example.pokercalc.service.algorithm;

import com.example.pokercalc.model.card.Card;
import com.example.pokercalc.model.card.CardValue;
import com.example.pokercalc.model.card.Combination;
import com.example.pokercalc.service.exceptions.CombinationNotFound;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class FullHouseHandler extends BaseCombinationHandler{
    @Override
    public Combination handleCombination (List<Card> cards) throws CombinationNotFound {
            if (isFullHouse(cards))
                return Combination.FULL_HOUSE;
            return super.handleCombination(cards);
        }
    }

  private boolean isFullHouse(List<Card> cards){

      Map<CardValue, List<Card>> cardGroups = cards.stream().collect(groupingBy(Card::value));
      boolean hasThreeOfAKind = false;
      boolean hasPair = false;

      for (List<Card> cardsList : cardGroups.values()) {
          if (cardsList.size() == 3) {
              hasThreeOfAKind = true;
          } else if (cardsList.size() == 2) {
              hasPair = true;
          }
      }

      return hasThreeOfAKind && hasPair;
  }

}
