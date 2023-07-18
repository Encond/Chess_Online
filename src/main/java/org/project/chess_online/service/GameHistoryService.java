package org.project.chess_online.service;

import org.project.chess_online.entity.GameHistory;
import org.project.chess_online.entity.Lap;
import org.project.chess_online.repository.GameHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameHistoryService {
    private final GameHistoryRepository gameHistoryRepository;

    @Autowired
    public GameHistoryService(GameHistoryRepository gameHistoryRepository) {
        this.gameHistoryRepository = gameHistoryRepository;
    }

    public GameHistory findById(Long id) {
        return this.gameHistoryRepository.findById(id).get(); //.orElseGet(GameHistory::new);
    }

    public void create(GameHistory gameHistory) {
        this.gameHistoryRepository.save(gameHistory);
    }
}
