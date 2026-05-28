package com.skyrecord728.p9.controller;

import com.skyrecord728.p9.dto.auth.LoginRequest;
import com.skyrecord728.p9.dto.auth.LoginResponse;
import com.skyrecord728.p9.dto.auth.MeResponse;
import com.skyrecord728.p9.service.AuthService;
import com.skyrecord728.p9.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @GetMapping("/me")
    public MeResponse me(Authentication authentication) {
        return userService.getMe(authentication.getName());
    }
}
