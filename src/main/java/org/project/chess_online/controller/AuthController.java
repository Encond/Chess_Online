package org.project.chess_online.controller;

import jakarta.validation.Valid;
import org.project.chess_online.security.JWTTokenProvider;
import org.project.chess_online.security.request.JWTTokenSuccessResponse;
import org.project.chess_online.security.request.LoginRequest;
import org.project.chess_online.security.request.SignupRequest;
import org.project.chess_online.service.UserService;
import org.project.chess_online.validation.ResponseErrorValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {
    private final UserService userService;
    private final ResponseErrorValidation validation;
    private final JWTTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(UserService userService, ResponseErrorValidation validation, JWTTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.validation = validation;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/signin")
    public ResponseEntity<Object> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult) {
        ResponseEntity<Object> errors = validation.mapValidationService(bindingResult);

        if (!ObjectUtils.isEmpty(errors))
            return errors;

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = "Bearer " + jwtTokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JWTTokenSuccessResponse(true, jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody SignupRequest signupRequest, BindingResult bindingResult) {
        ResponseEntity<Object> errors = validation.mapValidationService(bindingResult);

        if (!ObjectUtils.isEmpty(errors))
            return errors;

        userService.createUser(signupRequest);
        return ResponseEntity.ok("User register successfully");
    }
}
