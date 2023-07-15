package org.project.chess_online.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;

    @Column(name = "login", length = 55, nullable = false)
    private String login;

    @Column(name = "password", length = 55, nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "moves_history_id", referencedColumnName = "id_moves_history")
    private GameHistory gameHistory;
}
