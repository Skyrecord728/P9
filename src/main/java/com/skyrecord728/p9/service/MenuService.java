package com.skyrecord728.p9.service;

import com.skyrecord728.p9.domain.entity.SysMenu;
import com.skyrecord728.p9.domain.entity.SysUser;
import com.skyrecord728.p9.dto.menu.MenuNodeResponse;
import com.skyrecord728.p9.dto.menu.MenuRequest;
import com.skyrecord728.p9.repository.SysMenuRepository;
import com.skyrecord728.p9.util.MenuTreeBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    private final SysMenuRepository menuRepository;
    private final UserService userService;

    public MenuService(SysMenuRepository menuRepository, UserService userService) {
        this.menuRepository = menuRepository;
        this.userService = userService;
    }

    public List<MenuNodeResponse> listTree() {
        return MenuTreeBuilder.build(menuRepository.findAllByOrderByOrderNoAscIdAsc());
    }

    public List<MenuNodeResponse> myVisibleTree(String username) {
        SysUser user = userService.findByUsername(username);
        return MenuTreeBuilder.build(menuRepository.findVisibleMenusByUserId(user.getId()));
    }

    public SysMenu getById(Long id) {
        return menuRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("菜单不存在"));
    }

    public SysMenu create(MenuRequest request) {
        return menuRepository.save(toEntity(new SysMenu(), request));
    }

    public SysMenu update(Long id, MenuRequest request) {
        SysMenu menu = getById(id);
        return menuRepository.save(toEntity(menu, request));
    }

    public void delete(Long id) {
        menuRepository.delete(getById(id));
    }

    private SysMenu toEntity(SysMenu menu, MenuRequest request) {
        menu.setMenuName(request.getMenuName());
        menu.setPath(request.getPath());
        menu.setComponent(request.getComponent());
        menu.setParentId(request.getParentId());
        menu.setOrderNo(request.getOrderNo());
        menu.setVisible(request.getVisible());
        return menu;
    }
}
