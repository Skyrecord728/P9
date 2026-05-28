package com.skyrecord728.p9.service;

import com.skyrecord728.p9.domain.entity.SysRole;
import com.skyrecord728.p9.domain.entity.SysUser;
import com.skyrecord728.p9.dto.auth.LoginRequest;
import com.skyrecord728.p9.dto.auth.LoginResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;

    public AuthService(AuthenticationManager authenticationManager, JwtService jwtService, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    public LoginResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        SysUser user = userService.findByUsername(request.getUsername());
        String token = jwtService.generateToken(user.getUsername());
        return new LoginResponse(token, user.getUsername(), user.getRoles().stream().map(SysRole::getRoleCode).sorted().toList());
    }
}
