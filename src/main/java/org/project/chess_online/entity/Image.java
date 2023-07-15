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
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_image")
    private Long idImage;

    @Basic
    @Lob
    @Column(name = "url", columnDefinition = "LONGBLOB", nullable = false)
    private byte[] url;

    @OneToMany(mappedBy = "image")
    private List<ChessPiece> chessPieces;
}
