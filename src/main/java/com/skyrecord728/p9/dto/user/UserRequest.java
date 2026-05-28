package com.skyrecord728.p9.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserRequest {
    @NotBlank
    private String username;
    private String password;
    @NotNull
    private Byte status;

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public Byte getStatus() { return status; }
    public void setStatus(Byte status) { this.status = status; }
}
