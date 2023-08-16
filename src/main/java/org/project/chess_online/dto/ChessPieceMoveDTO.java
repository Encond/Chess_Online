package org.project.chess_online.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ChessPieceMoveDTO {
    @NotEmpty
    private int moveFromX;

    @NotEmpty
    private int moveFromY;

    @NotEmpty
    private int moveToX;

    @NotEmpty
    private int moveToY;
}
