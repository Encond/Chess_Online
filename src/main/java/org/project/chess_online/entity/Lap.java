package org.project.chess_online.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "lap")
public class Lap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lap")
    private Long idLap;

    @OneToOne
    @JoinColumn(name = "user_id_first", nullable = false)
    private User userFirst;

    @OneToOne
    @JoinColumn(name = "user_id_second", nullable = false)
    private User userSecond;

    @OneToOne
    @JoinColumn(name = "user_id_winner")
    private User userWinner;

    @OneToOne
    @JoinColumn(name = "moves_history_id", referencedColumnName = "id_moves_history", nullable = false)
    private GameHistory gameHistory;

    @Column(name = "active")
    private boolean active;

    @OneToOne(mappedBy = "lap")
    private Chat chat;
}
