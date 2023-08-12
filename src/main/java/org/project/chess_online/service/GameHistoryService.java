package org.project.chess_online.service;

import org.project.chess_online.entity.ChessPieceMove;
import org.project.chess_online.entity.GameHistory;
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

    public GameHistory create() {
        return this.gameHistoryRepository.save(new GameHistory());
    }

    public void add(GameHistory gameHistory, ChessPieceMove chessPieceMove) {
        if (gameHistory != null && chessPieceMove != null) {
            List<ChessPieceMove> tempChessPieceMoves = gameHistory.getChessPieceMoves();
            tempChessPieceMoves.add(chessPieceMove);

            gameHistory.setChessPieceMoves(tempChessPieceMoves);
        }
    }
}
