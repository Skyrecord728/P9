package com.skyrecord728.p9.dto.role;

import java.util.Set;

public class RoleResponse {
    private Long id;
    private String roleName;
    private String roleCode;
    private Set<Long> menuIds;

    public RoleResponse(Long id, String roleName, String roleCode, Set<Long> menuIds) {
        this.id = id;
        this.roleName = roleName;
        this.roleCode = roleCode;
        this.menuIds = menuIds;
    }

    public Long getId() { return id; }
    public String getRoleName() { return roleName; }
    public String getRoleCode() { return roleCode; }
    public Set<Long> getMenuIds() { return menuIds; }
}
