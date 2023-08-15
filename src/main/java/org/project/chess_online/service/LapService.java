package org.project.chess_online.service;

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

    public Lap findById(Long lapId) {
        if(lapId > 0)
            return this.lapRepository.findLapById(lapId);

        return null;
    }

    public Lap findByUsersId(Long userFirstId, Long userSecondId) {
        if (this.lapRepository.findByUserId(userFirstId) == null && this.lapRepository.findByUserId(userFirstId) == null)
            return this.lapRepository.findByUsersId(userFirstId, userSecondId);

        return null;
    }

    public Lap findByUserId(Long userId) {
        return this.lapRepository.findByUserId(userId);
    }

    public Lap create(User userFirst, User userSecond) {
        Lap tempLap = new Lap();
        tempLap.setUserFirst(userFirst);
        tempLap.setUserSecond(userSecond);
        tempLap.setGameHistory(this.gameHistoryService.create());
        tempLap.setActive(true);

        return this.lapRepository.save(tempLap);
    }
}
