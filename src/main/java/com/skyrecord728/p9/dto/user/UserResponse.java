package com.skyrecord728.p9.dto.user;

import java.util.List;

public class UserResponse {
    private Long id;
    private String username;
    private Byte status;
    private List<String> roleCodes;

    public UserResponse(Long id, String username, Byte status, List<String> roleCodes) {
        this.id = id;
        this.username = username;
        this.status = status;
        this.roleCodes = roleCodes;
    }

    public Long getId() { return id; }
    public String getUsername() { return username; }
    public Byte getStatus() { return status; }
    public List<String> getRoleCodes() { return roleCodes; }
}
