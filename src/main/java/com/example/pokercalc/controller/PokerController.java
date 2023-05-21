package com.example.pokercalc.controller;

import com.example.pokercalc.controller.dto.request.PokerData;
import com.example.pokercalc.model.card.Card;
import com.example.pokercalc.model.deck.Deck;
import com.example.pokercalc.service.simulation.SimulationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PokerController {

    private final SimulationService simulationService;

    public PokerController(SimulationService simulationService) {
        this.simulationService = simulationService;
    }

    @GetMapping("/poker-calc")
    public ResponseEntity<?> calculateChancesOnStream(@RequestBody PokerData pokerData) {
        return ResponseEntity
                .ok()
                .body(simulationService.calculateChancesOnStream(pokerData));
    }

    @GetMapping("/deck")
    public ResponseEntity<List<Card>> getDeck() {
        return ResponseEntity.ok().body(new Deck().getDeck());
    }

}
