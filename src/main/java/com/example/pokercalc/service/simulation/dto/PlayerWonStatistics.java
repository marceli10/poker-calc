package com.example.pokercalc.service.simulation.dto;

import com.example.pokercalc.controller.dto.request.PlayerHand;

import java.util.concurrent.atomic.AtomicInteger;

public class PlayerWonStatistics {
    private final PlayerHand playerHand;
    private final AtomicInteger roundsWon;

    public PlayerWonStatistics(PlayerHand playerHand) {
        this.playerHand = playerHand;
        roundsWon = new AtomicInteger(0);
    }

    public PlayerHand getPlayerHand() {
        return playerHand;
    }

    public int getRoundsWon() {
        return roundsWon.intValue();
    }
    
    public void incrementWonCount() {
        roundsWon.incrementAndGet();
    }
}
