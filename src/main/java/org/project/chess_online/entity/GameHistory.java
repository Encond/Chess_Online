package org.project.chess_online.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

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

    @OneToMany
    private List<ChessPieceMove> chessPieceMoves;

    @OneToOne(mappedBy = "gameHistory")
    private Lap lap;
}
