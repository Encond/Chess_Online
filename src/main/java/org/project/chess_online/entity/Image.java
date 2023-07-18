package org.project.chess_online.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_image")
    private Long idImage;

    @Basic
    @Lob
    @Column(name = "url", columnDefinition = "LONGBLOB", nullable = false)
    private byte[] imageBytes;

    public Image() {
    }

    public Image(byte[] imageBytes) {
        this.imageBytes = imageBytes;
    }
}
