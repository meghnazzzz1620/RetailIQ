package com.retailiq.auth;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {

        this.authService = authService;

    }

    @PostMapping("/register")
    public JwtResponse register(

            @RequestBody RegisterRequest request) {

        return authService.register(request);

    }

    @PostMapping("/login")
    public JwtResponse login(

            @RequestBody LoginRequest request) {

        return authService.login(request);

    }

}