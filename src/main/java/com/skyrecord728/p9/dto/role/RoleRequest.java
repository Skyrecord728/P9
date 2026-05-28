package com.skyrecord728.p9.dto.role;

import jakarta.validation.constraints.NotBlank;

public class RoleRequest {
    @NotBlank
    private String roleName;
    @NotBlank
    private String roleCode;

    public String getRoleName() { return roleName; }
    public void setRoleName(String roleName) { this.roleName = roleName; }
    public String getRoleCode() { return roleCode; }
    public void setRoleCode(String roleCode) { this.roleCode = roleCode; }
}
