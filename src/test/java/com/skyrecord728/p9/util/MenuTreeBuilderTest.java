package com.skyrecord728.p9.util;

import com.skyrecord728.p9.domain.entity.SysMenu;
import com.skyrecord728.p9.dto.menu.MenuNodeResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class MenuTreeBuilderTest {

    @Test
    void shouldBuildSortedTree() {
        SysMenu root = menu(1L, "root", 0L, 2);
        SysMenu root2 = menu(3L, "root2", 0L, 1);
        SysMenu child = menu(2L, "child", 1L, 1);

        List<MenuNodeResponse> tree = MenuTreeBuilder.build(List.of(root, child, root2));

        Assertions.assertEquals(2, tree.size());
        Assertions.assertEquals(3L, tree.get(0).getId());
        Assertions.assertEquals(1L, tree.get(1).getId());
        Assertions.assertEquals(1, tree.get(1).getChildren().size());
        Assertions.assertEquals(2L, tree.get(1).getChildren().get(0).getId());
    }

    private SysMenu menu(Long id, String name, Long parentId, int orderNo) {
        SysMenu menu = new SysMenu();
        menu.setId(id);
        menu.setMenuName(name);
        menu.setParentId(parentId);
        menu.setOrderNo(orderNo);
        menu.setVisible((byte) 1);
        return menu;
    }
}
