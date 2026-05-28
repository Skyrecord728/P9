package com.skyrecord728.p9.controller;

import com.skyrecord728.p9.domain.entity.SysMenu;
import com.skyrecord728.p9.dto.menu.MenuNodeResponse;
import com.skyrecord728.p9.dto.menu.MenuRequest;
import com.skyrecord728.p9.service.MenuService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menus")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<MenuNodeResponse> listTree() {
        return menuService.listTree();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public SysMenu get(@PathVariable Long id) {
        return menuService.getById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public SysMenu create(@Valid @RequestBody MenuRequest request) {
        return menuService.create(request);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public SysMenu update(@PathVariable Long id, @Valid @RequestBody MenuRequest request) {
        return menuService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) {
        menuService.delete(id);
    }

    @GetMapping("/my-tree")
    public List<MenuNodeResponse> myVisibleTree(Authentication authentication) {
        return menuService.myVisibleTree(authentication.getName());
    }
}
