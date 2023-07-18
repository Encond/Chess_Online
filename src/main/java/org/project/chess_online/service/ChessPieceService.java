package org.project.chess_online.service;

import org.project.chess_online.entity.ChessPiece;
import org.project.chess_online.entity.Image;
import org.project.chess_online.repository.ChessPieceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChessPieceService {
    private final ChessPieceRepository chessPieceRepository;

    @Autowired
    public ChessPieceService(ChessPieceRepository chessPieceRepository) {
        this.chessPieceRepository = chessPieceRepository;
    }

    public List<ChessPiece> getAll() {
        return this.chessPieceRepository.findAll();
    }

    public ChessPiece findById(Long id) {
        return this.chessPieceRepository.findById(id).get(); // orElseGet(ChessPiece::new)
    }

    public ChessPiece createChessPiece(ChessPiece chessPiece) {
        return this.chessPieceRepository.save(chessPiece);
    }

    public void createPawnChessPieces() {
        for (int i = 0; i < 8; i++)
            this.chessPieceRepository.save(this.createChessPiece(new ChessPiece(new Image("images/unknownImage.png".getBytes()), "pawn", true, 0)));

        for (int i = 0; i < 8; i++)
            this.chessPieceRepository.save(this.createChessPiece(new ChessPiece(new Image("images/unknownImage.png".getBytes()), "pawn", true, 1)));
    }

    private void createRookChessPieces() {
        for (int i = 0; i < 2; i++)
            this.chessPieceRepository.save(this.createChessPiece(new ChessPiece(new Image("images/unknownImage.png".getBytes()), "rook", true, 0)));

        for (int i = 0; i < 2; i++)
            this.chessPieceRepository.save(this.createChessPiece(new ChessPiece(new Image("images/unknownImage.png".getBytes()), "rook", true, 1)));
    }

    private void createKnightChessPieces() {
        for (int i = 0; i < 2; i++)
            this.chessPieceRepository.save(this.createChessPiece(new ChessPiece(new Image("images/unknownImage.png".getBytes()), "knight", true, 0)));

        for (int i = 0; i < 2; i++)
            this.chessPieceRepository.save(this.createChessPiece(new ChessPiece(new Image("images/unknownImage.png".getBytes()), "knight", true, 1)));
    }

    private void createBishopChessPieces() {
        for (int i = 0; i < 2; i++)
            this.chessPieceRepository.save(this.createChessPiece(new ChessPiece(new Image("images/unknownImage.png".getBytes()), "bishop", true, 0)));

        for (int i = 0; i < 2; i++)
            this.chessPieceRepository.save(this.createChessPiece(new ChessPiece(new Image("images/unknownImage.png".getBytes()), "bishop", true, 1)));
    }

    private void createQueenChessPieces() {
        this.chessPieceRepository.save(this.createChessPiece(new ChessPiece(new Image("images/unknownImage.png".getBytes()), "queen", true, 0)));
        this.chessPieceRepository.save(this.createChessPiece(new ChessPiece(new Image("images/unknownImage.png".getBytes()), "queen", true, 1)));
    }

    private void createKingChessPieces() {
        this.chessPieceRepository.save(this.createChessPiece(new ChessPiece(new Image("images/unknownImage.png".getBytes()), "king", true, 0)));
        this.chessPieceRepository.save(this.createChessPiece(new ChessPiece(new Image("images/unknownImage.png".getBytes()), "king", true, 1)));
    }

    public void createChessPieces() {
        this.createPawnChessPieces();
        this.createRookChessPieces();
        this.createKnightChessPieces();
        this.createBishopChessPieces();
        this.createQueenChessPieces();
        this.createKingChessPieces();
    }
}
