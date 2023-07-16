package org.project.chess_online.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "chess_piece")
public class ChessPiece {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_chess_piece")
    private Long idChessPiece;

    @ManyToOne
    @JoinColumn(name = "image_id", referencedColumnName = "id_image", nullable = false)
    private Image image;

    @Column(name = "type", length = 55, nullable = false)
    private String type;

    @Column(name = "on_board", nullable = false)
    private boolean onBoard;

    @Column(name = "color", nullable = false)
    private int color;

    @OneToOne
    @JoinColumn(name = "lap_id")
    private Lap lap;
}
