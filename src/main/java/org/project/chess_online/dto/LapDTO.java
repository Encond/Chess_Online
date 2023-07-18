package org.project.chess_online.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LapDTO {
    @NotEmpty
    private Long id;

    @NotEmpty
    private Long userIdFirst;
    @NotEmpty
    private String userUsernameFirst;

    @NotEmpty
    private Long userIdSecond;
    @NotEmpty
    private String userUsernameSecond;

    @NotEmpty
    private Long userIdWinner;
    @NotEmpty
    private String userUsernameWinner;

    @NotEmpty
    private boolean active;

    @NotEmpty
    private Long chatId;
}
