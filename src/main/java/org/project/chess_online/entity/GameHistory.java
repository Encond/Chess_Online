package org.project.chess_online.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "moves_history")
public class GameHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_moves_history")
    private Long idGameHistory;

    @Column(name = "chess_piece_move", length = 15, nullable = false)
    private String chessPieceMove;

    @OneToOne
    @JoinColumn(name = "chess_piece_id", nullable = false)
    private ChessPiece chessPiece;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "move_count", nullable = false)
    private int moveCount;

    @OneToOne(mappedBy = "gameHistory")
    private Lap lap;
}
