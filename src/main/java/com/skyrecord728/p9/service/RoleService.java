package com.skyrecord728.p9.service;

import com.skyrecord728.p9.domain.entity.SysMenu;
import com.skyrecord728.p9.domain.entity.SysRole;
import com.skyrecord728.p9.dto.role.RoleRequest;
import com.skyrecord728.p9.dto.role.RoleResponse;
import com.skyrecord728.p9.repository.SysMenuRepository;
import com.skyrecord728.p9.repository.SysRoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleService {

    private final SysRoleRepository roleRepository;
    private final SysMenuRepository menuRepository;

    public RoleService(SysRoleRepository roleRepository, SysMenuRepository menuRepository) {
        this.roleRepository = roleRepository;
        this.menuRepository = menuRepository;
    }

    public List<RoleResponse> listAll() {
        return roleRepository.findAll().stream().map(this::toResponse).toList();
    }

    public RoleResponse getById(Long id) {
        return toResponse(findRole(id));
    }

    public RoleResponse create(RoleRequest request) {
        SysRole role = new SysRole();
        role.setRoleName(request.getRoleName());
        role.setRoleCode(request.getRoleCode());
        return toResponse(roleRepository.save(role));
    }

    public RoleResponse update(Long id, RoleRequest request) {
        SysRole role = findRole(id);
        role.setRoleName(request.getRoleName());
        role.setRoleCode(request.getRoleCode());
        return toResponse(roleRepository.save(role));
    }

    public void delete(Long id) {
        roleRepository.delete(findRole(id));
    }

    @Transactional
    public RoleResponse assignMenus(Long id, Set<Long> menuIds) {
        SysRole role = findRole(id);
        Set<SysMenu> menus = new HashSet<>(menuRepository.findAllById(menuIds));
        if (menus.size() != menuIds.size()) {
            throw new IllegalArgumentException("部分菜单不存在");
        }
        role.setMenus(menus);
        return toResponse(role);
    }

    private SysRole findRole(Long id) {
        return roleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("角色不存在"));
    }

    private RoleResponse toResponse(SysRole role) {
        return new RoleResponse(
                role.getId(),
                role.getRoleName(),
                role.getRoleCode(),
                role.getMenus().stream().map(SysMenu::getId).collect(java.util.stream.Collectors.toSet())
        );
    }
}
