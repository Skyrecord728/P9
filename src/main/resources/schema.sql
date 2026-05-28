DROP TABLE IF EXISTS sys_role_menu;
DROP TABLE IF EXISTS sys_user_role;
DROP TABLE IF EXISTS sys_menu;
DROP TABLE IF EXISTS sys_role;
DROP TABLE IF EXISTS sys_user;

CREATE TABLE sys_user (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(50) NOT NULL UNIQUE,
  password VARCHAR(100) NOT NULL,
  status TINYINT DEFAULT 1
);

CREATE TABLE sys_role (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  role_name VARCHAR(50),
  role_code VARCHAR(50) UNIQUE
);

CREATE TABLE sys_menu (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  menu_name VARCHAR(50),
  path VARCHAR(100),
  component VARCHAR(100),
  parent_id BIGINT DEFAULT 0,
  order_no INT DEFAULT 0,
  visible TINYINT DEFAULT 1
);

CREATE TABLE sys_user_role (
  user_id BIGINT,
  role_id BIGINT,
  PRIMARY KEY (user_id, role_id)
);

CREATE TABLE sys_role_menu (
  role_id BIGINT,
  menu_id BIGINT,
  PRIMARY KEY (role_id, menu_id)
);
