package org.project.chess_online.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "chess_piece_move")
public class ChessPieceMove {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_chess_piece_move")
    private Long idChessPieceMove;

    @Column(name = "move_from_x", nullable = false)
    private int moveFromX;

    @Column(name = "move_from_y", nullable = false)
    private int moveFromY;

    @Column(name = "move_to_x", nullable = false)
    private int moveToX;

    @Column(name = "move_to_y", nullable = false)
    private int moveToY;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "moves_history_id", referencedColumnName = "id_moves_history")
    private GameHistory gameHistory;
}
