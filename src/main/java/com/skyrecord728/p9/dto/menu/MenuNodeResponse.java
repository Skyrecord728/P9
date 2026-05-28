package com.skyrecord728.p9.dto.menu;

import java.util.ArrayList;
import java.util.List;

public class MenuNodeResponse {
    private Long id;
    private String menuName;
    private String path;
    private String component;
    private Long parentId;
    private Integer orderNo;
    private Byte visible;
    private List<MenuNodeResponse> children = new ArrayList<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getMenuName() { return menuName; }
    public void setMenuName(String menuName) { this.menuName = menuName; }
    public String getPath() { return path; }
    public void setPath(String path) { this.path = path; }
    public String getComponent() { return component; }
    public void setComponent(String component) { this.component = component; }
    public Long getParentId() { return parentId; }
    public void setParentId(Long parentId) { this.parentId = parentId; }
    public Integer getOrderNo() { return orderNo; }
    public void setOrderNo(Integer orderNo) { this.orderNo = orderNo; }
    public Byte getVisible() { return visible; }
    public void setVisible(Byte visible) { this.visible = visible; }
    public List<MenuNodeResponse> getChildren() { return children; }
    public void setChildren(List<MenuNodeResponse> children) { this.children = children; }
}
