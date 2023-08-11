package org.project.chess_online.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LapDTO {
    @NotEmpty
    private Long id;

    @NotEmpty
    private String enemyName;

    @NotEmpty
    private boolean chessPiecesColor;

    @NotEmpty
    private boolean active;

    @NotEmpty
    private Long chatId;
}
