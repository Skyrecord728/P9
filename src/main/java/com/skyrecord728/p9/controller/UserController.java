package com.skyrecord728.p9.controller;

import com.skyrecord728.p9.dto.user.AssignRolesRequest;
import com.skyrecord728.p9.dto.user.UserRequest;
import com.skyrecord728.p9.dto.user.UserResponse;
import com.skyrecord728.p9.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> list() {
        return userService.listAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponse get(@PathVariable Long id) {
        return userService.getById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponse create(@Valid @RequestBody UserRequest request) {
        return userService.create(request);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponse update(@PathVariable Long id, @Valid @RequestBody UserRequest request) {
        return userService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @PatchMapping("/{id}/status/{status}")
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponse updateStatus(@PathVariable Long id, @PathVariable Byte status) {
        return userService.updateStatus(id, status);
    }

    @PostMapping("/{id}/roles")
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponse assignRoles(@PathVariable Long id, @Valid @RequestBody AssignRolesRequest request) {
        return userService.assignRoles(id, request.getRoleIds());
    }
}
