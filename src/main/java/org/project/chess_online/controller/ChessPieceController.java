package org.project.chess_online.controller;

import org.project.chess_online.entity.ChessPiece;
import org.project.chess_online.service.ChessPieceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chessPiece")
@CrossOrigin
public class ChessPieceController {
    private final ChessPieceService chessPieceService;

    private List<ChessPiece> chessPieces;

    @Autowired
    public ChessPieceController(ChessPieceService chessPieceService) {
        this.chessPieceService = chessPieceService;

        if (this.chessPieceService.getAll().size() == 0)
            this.chessPieceService.createChessPieces();

        this.chessPieces = this.chessPieceService.getAll();
    }

    @GetMapping("/all")
    public ResponseEntity<List<ChessPiece>> getChessPieces() {
        return new ResponseEntity<>(this.chessPieces, HttpStatus.OK);
    }
}
