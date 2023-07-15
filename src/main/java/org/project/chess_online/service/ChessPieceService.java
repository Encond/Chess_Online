package org.project.chess_online.service;

import org.project.chess_online.entity.ChessPiece;
import org.project.chess_online.repository.ChessPieceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChessPieceService {
    private final ChessPieceRepository chessPieceRepository;

    @Autowired
    public ChessPieceService(ChessPieceRepository chessPieceRepository) {
        this.chessPieceRepository = chessPieceRepository;
    }

    private List<ChessPiece> getAll() {
        return this.chessPieceRepository.findAll();
    }

    private ChessPiece findById(Long id) {
        return this.chessPieceRepository.findById(id).get(); // orElseGet(ChessPiece::new)
    }

    private ChessPiece createChessPiece() {
        return new ChessPiece();
    }
}
