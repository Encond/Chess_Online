package org.project.chess_online.security;

import org.project.chess_online.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;

@Component
public class JWTTokenProvider {
    public String generateToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        HashMap<String, Object> claimsMap = new HashMap<>();
        claimsMap.put("id", Long.toString(user.getIdUser()));
        claimsMap.put("username", user.getUsername());

        return Jwts.builder()
                .setSubject(Long.toString(user.getIdUser()))
                .addClaims(claimsMap)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600_000_000))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SecurityConstants.SECRET).parseClaimsJwt(token);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long getUserIdFromToken(String token) {
        String jwt = token.replace("Bearer", "");
        Claims claims = Jwts.parser().setSigningKey(SecurityConstants.SECRET)
                .parseClaimsJws(jwt).getBody();

        return Long.parseLong((String) claims.get("id"));
    }
}
