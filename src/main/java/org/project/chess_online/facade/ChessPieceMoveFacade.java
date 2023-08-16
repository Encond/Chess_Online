package org.project.chess_online.facade;

import org.project.chess_online.dto.ChessPieceMoveDTO;
import org.project.chess_online.entity.ChessPieceMove;
import org.project.chess_online.entity.User;
import org.springframework.stereotype.Component;

@Component
public class ChessPieceMoveFacade {
    public ChessPieceMoveDTO chessPieceMoveToDTO(ChessPieceMove chessPieceMove) {
        ChessPieceMoveDTO chessPieceMoveDTO = new ChessPieceMoveDTO();

        chessPieceMoveDTO.setMoveFromX(chessPieceMove.getMoveFromX());
        chessPieceMoveDTO.setMoveFromY(chessPieceMove.getMoveFromY());

        chessPieceMoveDTO.setMoveToX(chessPieceMove.getMoveToX());
        chessPieceMoveDTO.setMoveToY(chessPieceMove.getMoveToY());

        return chessPieceMoveDTO;
    }

    public ChessPieceMove DTOToChessPieceMove(ChessPieceMoveDTO chessPieceMoveDTO, User user) {
        ChessPieceMove chessPieceMove = new ChessPieceMove();

        chessPieceMove.setMoveFromX(chessPieceMoveDTO.getMoveFromX());
        chessPieceMove.setMoveFromY(chessPieceMoveDTO.getMoveFromY());

        chessPieceMove.setMoveToX(chessPieceMoveDTO.getMoveToX());
        chessPieceMove.setMoveToY(chessPieceMoveDTO.getMoveToY());

        chessPieceMove.setUser(user);

        return chessPieceMove;
    }
}
