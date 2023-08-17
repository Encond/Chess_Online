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
    private final ChessPieceMoveService chessPieceMoveService;

    @Autowired
    public GameHistoryService(GameHistoryRepository gameHistoryRepository, ChessPieceMoveService chessPieceMoveService) {
        this.gameHistoryRepository = gameHistoryRepository;
        this.chessPieceMoveService = chessPieceMoveService;
    }

    public GameHistory findById(Long id) {
        return this.gameHistoryRepository.findById(id).orElseGet(GameHistory::new);
    }

    public GameHistory create() {
        return this.gameHistoryRepository.save(new GameHistory());
    }

    public boolean add(Long gameHistoryId, ChessPieceMove chessPieceMove) {
        GameHistory gameHistory = this.findById(gameHistoryId);
        if (gameHistory != null && gameHistory.getChessPieceMoves() != null) {
            if (chessPieceMove != null) {
                chessPieceMove.setGameHistory(gameHistory);
                this.chessPieceMoveService.create(chessPieceMove);

                return true;
            }
        }

        return false;
    }

    public boolean checkLastMove(GameHistory gameHistory, Long userId) {
        if (gameHistory != null) {
            List<ChessPieceMove> chessPieceMoves = gameHistory.getChessPieceMoves();

            if (!chessPieceMoves.isEmpty())
                return chessPieceMoves.get(chessPieceMoves.size() - 1).getUser().getIdUser().equals(userId);
        }

        return false;
    }
}
