package com.skyrecord728.p9.controller;

import com.skyrecord728.p9.dto.role.AssignMenusRequest;
import com.skyrecord728.p9.dto.role.RoleRequest;
import com.skyrecord728.p9.dto.role.RoleResponse;
import com.skyrecord728.p9.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@PreAuthorize("hasRole('ADMIN')")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public List<RoleResponse> list() {
        return roleService.listAll();
    }

    @GetMapping("/{id}")
    public RoleResponse get(@PathVariable Long id) {
        return roleService.getById(id);
    }

    @PostMapping
    public RoleResponse create(@Valid @RequestBody RoleRequest request) {
        return roleService.create(request);
    }

    @PutMapping("/{id}")
    public RoleResponse update(@PathVariable Long id, @Valid @RequestBody RoleRequest request) {
        return roleService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        roleService.delete(id);
    }

    @PostMapping("/{id}/menus")
    public RoleResponse assignMenus(@PathVariable Long id, @Valid @RequestBody AssignMenusRequest request) {
        return roleService.assignMenus(id, request.getMenuIds());
    }
}
