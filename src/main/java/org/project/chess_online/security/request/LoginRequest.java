package org.project.chess_online.security.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginRequest {
    @NotEmpty(message = "Username can`t be empty")
    private String username;

    @NotEmpty(message = "Password can`t be empty")
    private String password;

}
