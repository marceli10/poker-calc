package com.example.pokercalc.controller.dto.response;

import com.example.pokercalc.model.card.Combination;
import com.example.pokercalc.service.simulation.dto.PlayerWonStatistics;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public record ChancesDto(
        PlayerChance firstPlayerChance,
        PlayerChance secondPlayerChance,
        PlayerChance thirdPlayerChance,
        PlayerChance fourthPlayerChance,
        PlayerChance fifthPlayerChance,
        PlayerChance sixthPlayerChance,
        Map<Object, Object> combinationsChances
) {
    public static ChancesDto from(
            List<PlayerWonStatistics> playersWonStatistics, Map<Combination, AtomicInteger> combinationsWonStatistics
    ) {
        int allPossibleRounds = playersWonStatistics
                .stream()
                .map(PlayerWonStatistics::getRoundsWon)
                .reduce(0, Integer::sum);

        return new ChancesDto(
                new PlayerChance(((double) playersWonStatistics.get(0).getRoundsWon() / allPossibleRounds) * 100),
                new PlayerChance(((double) playersWonStatistics.get(1).getRoundsWon() / allPossibleRounds) * 100),
                new PlayerChance(((double) playersWonStatistics.get(2).getRoundsWon() / allPossibleRounds) * 100),
                new PlayerChance(((double) playersWonStatistics.get(3).getRoundsWon() / allPossibleRounds) * 100),
                new PlayerChance(((double) playersWonStatistics.get(4).getRoundsWon() / allPossibleRounds) * 100),
                new PlayerChance(((double) playersWonStatistics.get(5).getRoundsWon() / allPossibleRounds) * 100),
                combinationsWonStatistics
                        .entrySet()
                        .stream()
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                statistic -> (statistic.getValue().doubleValue() / allPossibleRounds) * 100)
                        )
        );
    }
}
