package org.project.chess_online.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @OneToMany(mappedBy = "gameHistory", fetch = FetchType.EAGER)
    private List<ChessPieceMove> chessPieceMoves;

    @OneToOne(mappedBy = "gameHistory")
    @JsonIgnore
    private Lap lap;
}
