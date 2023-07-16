package org.project.chess_online.security.request;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JWTTokenSuccessResponse {
    private boolean success;
    private String token;
}
