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
@Table(name = "chat")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_chat")
    private Long idChat;

    @OneToOne
    @JoinColumn(name = "lap_id", nullable = false)
    private Lap lap;

    @OneToMany(mappedBy = "chat")
    private List<Message> messages;
}
