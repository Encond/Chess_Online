package org.project.chess_online.service;

import org.project.chess_online.entity.GameHistory;
import org.project.chess_online.entity.Lap;
import org.project.chess_online.entity.User;
import org.project.chess_online.repository.LapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LapService {
    private final LapRepository lapRepository;
    private final GameHistoryService gameHistoryService;

    @Autowired
    public LapService(LapRepository lapRepository, GameHistoryService gameHistoryService) {
        this.lapRepository = lapRepository;
        this.gameHistoryService = gameHistoryService;
    }

    public Lap findByUsers(User userFirst, User userSecond) {
        return this.lapRepository.findByUsers(userFirst, userSecond); // orElseGet(Lap::new);
    }

    public Lap findByUser(Long userId) {
        return this.lapRepository.findByUser(userId); // orElseGet(Lap::new);
    }

    public void create(User userFirst, User userSecond) {
        Lap tempLap = new Lap();
        tempLap.setUserFirst(userFirst);
        tempLap.setUserSecond(userSecond);
        tempLap.setGameHistory(this.gameHistoryService.create());
        tempLap.setActive(true);

        this.lapRepository.save(tempLap);
    }
}
