package org.project.chess_online.repository;

import org.project.chess_online.entity.Chat;
import org.project.chess_online.entity.ChessPieceMove;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ChessPieceMoveRepository extends JpaRepository<ChessPieceMove, Long> {
}
