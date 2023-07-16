package org.project.chess_online.security;

import org.project.chess_online.entity.User;
import org.project.chess_online.service.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class AuthProvider implements AuthenticationProvider {
    private final UserService userService;

    public AuthProvider(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // Authentication is a User from outside

        String username = authentication.getName();
        User user = userService.findUserByUsername(username); // User from database
        if (user == null)
            throw new UsernameNotFoundException("User not found");

        String password = user.getPassword(); // Password from database
        String userPassword = authentication.getCredentials().toString();

        if (!userPassword.equals(password))
            throw new BadCredentialsException("Bad Credentials");

        ArrayList<GrantedAuthority> authorities = new ArrayList<>();

        // AuthenticationToken is a registered User
        return new UsernamePasswordAuthenticationToken(user, null, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
