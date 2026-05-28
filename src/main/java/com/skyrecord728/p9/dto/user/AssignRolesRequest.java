package com.skyrecord728.p9.dto.user;

import jakarta.validation.constraints.NotEmpty;

import java.util.Set;

public class AssignRolesRequest {
    @NotEmpty
    private Set<Long> roleIds;

    public Set<Long> getRoleIds() { return roleIds; }
    public void setRoleIds(Set<Long> roleIds) { this.roleIds = roleIds; }
}
