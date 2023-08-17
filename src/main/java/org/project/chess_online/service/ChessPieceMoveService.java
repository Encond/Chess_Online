package org.project.chess_online.service;

import org.project.chess_online.entity.ChessPieceMove;
import org.project.chess_online.repository.ChessPieceMoveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChessPieceMoveService {
    private final ChessPieceMoveRepository chessPieceMoveRepository;

    @Autowired
    public ChessPieceMoveService(ChessPieceMoveRepository chessPieceMoveRepository) {
        this.chessPieceMoveRepository = chessPieceMoveRepository;
    }

    public void create(ChessPieceMove chessPieceMove) {
        this.chessPieceMoveRepository.save(chessPieceMove);
    }
}
