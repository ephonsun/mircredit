/*
 * banger Inc.
 * Copyright (c) 2009-2013 All Rights Reserved.
 * ToDo       :菜单权限树实体
 * Author     :yangy
 * Create Date:2013-5-27
 */
package com.banger.mobile.domain.model.menuAuth;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: yangy
 * Date: 13-5-27
 * Time: 下午3:00
 */
public class MenuAuthBean implements Serializable {

    private static final long serialVersionUID = 1051335185489812516L;
    private Integer menuId;       //功能id
    private Integer menuParentId;       //功能父id
    private String menuName;      //功能名称
    private String type;          //功能类型  FUNC功能  MENU菜单
    private String url;           //网页地址

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getMenuParentId() {
        return menuParentId;
    }

    public void setMenuParentId(Integer menuParentId) {
        this.menuParentId = menuParentId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MenuAuthBean that = (MenuAuthBean) o;

        if (menuId != null ? !menuId.equals(that.menuId) : that.menuId != null) return false;
        if (menuParentId != null ? !menuParentId.equals(that.menuParentId) : that.menuParentId != null) return false;
        if (menuName != null ? !menuName.equals(that.menuName) : that.menuName != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;

        return true;
    }

    public int hashCode() {
        int result = menuId != null ? menuId.hashCode() : 0;
        result = 31 * result + (menuParentId != null ? menuParentId.hashCode() : 0);
        result = 31 * result + (menuName != null ? menuName.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }

    public String toString() {
        return "MenuAuthBean{" +
                "menuId=" + menuId +
                ", menuParentId=" + menuParentId +
                ", menuName='" + menuName + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
