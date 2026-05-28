package com.skyrecord728.p9.dto.role;

import jakarta.validation.constraints.NotEmpty;

import java.util.Set;

public class AssignMenusRequest {
    @NotEmpty
    private Set<Long> menuIds;

    public Set<Long> getMenuIds() { return menuIds; }
    public void setMenuIds(Set<Long> menuIds) { this.menuIds = menuIds; }
}
