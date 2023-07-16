package org.project.chess_online.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_message")
    private Long idChat;

    @Column(name = "text")
    private String text;

    @Column(name = "date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "chat_id", nullable = false)
    private Chat chat;
}
