package com.skyrecord728.p9.dto.auth;

import java.util.List;

public class LoginResponse {
    private String token;
    private String tokenType;
    private String username;
    private List<String> roles;

    public LoginResponse(String token, String username, List<String> roles) {
        this.token = token;
        this.tokenType = "Bearer";
        this.username = username;
        this.roles = roles;
    }

    public String getToken() { return token; }
    public String getTokenType() { return tokenType; }
    public String getUsername() { return username; }
    public List<String> getRoles() { return roles; }
}
