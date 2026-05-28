package com.skyrecord728.p9.util;

import com.skyrecord728.p9.domain.entity.SysMenu;
import com.skyrecord728.p9.dto.menu.MenuNodeResponse;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class MenuTreeBuilder {

    private MenuTreeBuilder() {
    }

    public static List<MenuNodeResponse> build(List<SysMenu> menus) {
        List<SysMenu> sorted = new ArrayList<>(menus);
        sorted.sort(Comparator.comparing(SysMenu::getOrderNo).thenComparing(SysMenu::getId));

        Map<Long, MenuNodeResponse> nodeMap = new LinkedHashMap<>();
        for (SysMenu menu : sorted) {
            MenuNodeResponse node = toNode(menu);
            nodeMap.put(menu.getId(), node);
        }

        List<MenuNodeResponse> roots = new ArrayList<>();
        for (SysMenu menu : sorted) {
            MenuNodeResponse node = nodeMap.get(menu.getId());
            Long parentId = menu.getParentId();
            if (parentId == null || parentId == 0) {
                roots.add(node);
                continue;
            }
            MenuNodeResponse parent = nodeMap.get(parentId);
            if (parent == null) {
                roots.add(node);
                continue;
            }
            parent.getChildren().add(node);
        }
        return roots;
    }

    private static MenuNodeResponse toNode(SysMenu menu) {
        MenuNodeResponse node = new MenuNodeResponse();
        node.setId(menu.getId());
        node.setMenuName(menu.getMenuName());
        node.setPath(menu.getPath());
        node.setComponent(menu.getComponent());
        node.setParentId(menu.getParentId());
        node.setOrderNo(menu.getOrderNo());
        node.setVisible(menu.getVisible());
        return node;
    }
}
