package org.project.chess_online.repository;

import org.project.chess_online.entity.ChessPiece;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChessPieceRepository extends JpaRepository<ChessPiece, Long> {
}
