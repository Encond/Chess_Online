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

    public boolean add(GameHistory gameHistory, ChessPieceMove chessPieceMove) {
        boolean result = false;

        if (gameHistory != null && chessPieceMove != null) {
            List<ChessPieceMove> tempChessPieceMoves = gameHistory.getChessPieceMoves();

            if (tempChessPieceMoves != null) {
                result = tempChessPieceMoves.add(chessPieceMove);
                gameHistory.setChessPieceMoves(tempChessPieceMoves);
            }
        }

        return result;
    }

    public boolean checkLastMove(List<ChessPieceMove> chessPieceMoves, Long userId) {
        return chessPieceMoves.get(chessPieceMoves.size() - 1).getUser().getIdUser().equals(userId);
    }
}
