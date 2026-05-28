package com.skyrecord728.p9.dto.menu;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MenuRequest {
    @NotBlank
    private String menuName;
    private String path;
    private String component;
    @NotNull
    private Long parentId;
    @NotNull
    private Integer orderNo;
    @NotNull
    private Byte visible;

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
}
