package com.example.pokercalc.controller.dto.request;

import com.example.pokercalc.model.card.Card;

import java.util.List;

public record CardsOnTable(List<Card> cards) {}
