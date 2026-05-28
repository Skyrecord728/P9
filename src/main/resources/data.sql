INSERT INTO sys_user (id, username, password, status) VALUES
(1, 'admin', '$2b$12$ieO2GtoUAp/pWUCOJxt3PeGWgZfuT4NsCoCntpxE/d6aRaEXmDrYO', 1),
(2, 'user', '$2b$12$rMYUq1esa/t9nsj3fP/BUeElaeJMSdDfNHFY.i.e67zUC3miImnBO', 1);

INSERT INTO sys_role (id, role_name, role_code) VALUES
(1, '管理员', 'ADMIN'),
(2, '普通用户', 'USER');

INSERT INTO sys_menu (id, menu_name, path, component, parent_id, order_no, visible) VALUES
(1, '系统管理', '/system', 'Layout', 0, 1, 1),
(2, '用户管理', '/system/users', 'system/UserPage', 1, 1, 1),
(3, '角色管理', '/system/roles', 'system/RolePage', 1, 2, 1),
(4, '菜单管理', '/system/menus', 'system/MenuPage', 1, 3, 1),
(5, '个人中心', '/profile', 'profile/ProfilePage', 0, 2, 1),
(6, '隐藏菜单示例', '/hidden', 'demo/HiddenPage', 0, 99, 0);

INSERT INTO sys_user_role (user_id, role_id) VALUES
(1, 1),
(2, 2);

INSERT INTO sys_role_menu (role_id, menu_id) VALUES
(1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6),
(2, 5);
