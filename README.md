# Spring Boot RBAC（ESSH 表结构）

基于 Spring Boot 3 + Java 17 + MySQL 8 + Spring Security + JWT + JPA 实现的简易 RBAC 权限管理系统。

## 1. 环境要求
- Java 17+
- Maven 3.9+
- MySQL 8

## 2. 数据库准备
1. 创建数据库：
```sql
CREATE DATABASE p9_rbac DEFAULT CHARACTER SET utf8mb4;
```
2. 修改配置：`src/main/resources/application.yml`
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/p9_rbac?useSSL=false&serverTimezone=UTC&characterEncoding=utf8
    username: root
    password: root
```
应用启动时会自动执行 `schema.sql` 和 `data.sql`。

## 3. 启动
```bash
cd <project-root>
mvn -q package
mvn spring-boot:run
```

## 4. 初始化账号
- 管理员：`admin / admin123`
- 普通用户：`user / user123`

## 5. 主要接口
- 登录：`POST /api/auth/login`
- 当前用户信息：`GET /api/auth/me`
- 当前用户可见菜单树：`GET /api/menus/my-tree`
- 用户管理（ADMIN）：`/api/users` CRUD + 分配角色
- 角色管理（ADMIN）：`/api/roles` CRUD + 分配菜单
- 菜单管理（ADMIN）：`/api/menus` CRUD（树形列表）
- 角色权限示例（ADMIN）：`GET /api/admin/ping`

## 6. curl 测试
1) 登录（普通用户）
```bash
curl -s -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"user","password":"user123"}'
```

2) 获取普通用户菜单树（只返回 visible=1 且角色授权菜单）
```bash
TOKEN=<上一步返回的token>
curl -s http://localhost:8080/api/menus/my-tree \
  -H "Authorization: $TOKEN"
```
其中 TOKEN 变量需要以 ******

3) 验证 ADMIN 接口权限
```bash
# 普通用户 token 访问会 403
curl -i http://localhost:8080/api/admin/ping \
  -H "Authorization: $TOKEN"

# admin 登录后拿 token 再访问会 200
```

## 7. Postman Collection
- 文件路径：`postman/P9-RBAC.postman_collection.json`
- 覆盖场景：
  - 登录（USER + ADMIN）
  - 当前用户菜单树：`GET /api/menus/my-tree`
  - ADMIN 权限验证：普通用户访问 `GET /api/admin/ping` 预期 403，管理员访问预期 200
