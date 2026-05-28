package com.skyrecord728.p9.dto.auth;

import java.util.List;

public class MeResponse {
    private Long id;
    private String username;
    private Byte status;
    private List<String> roles;

    public MeResponse(Long id, String username, Byte status, List<String> roles) {
        this.id = id;
        this.username = username;
        this.status = status;
        this.roles = roles;
    }

    public Long getId() { return id; }
    public String getUsername() { return username; }
    public Byte getStatus() { return status; }
    public List<String> getRoles() { return roles; }
}
