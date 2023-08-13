package org.project.chess_online.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "moves_history")
public class GameHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_moves_history")
    private Long idGameHistory;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_chess_piece_move")
    private List<ChessPieceMove> chessPieceMoves;

    @OneToOne(mappedBy = "gameHistory")
    private Lap lap;

    public GameHistory() {
        this.chessPieceMoves = new ArrayList<ChessPieceMove>();
    }
}
