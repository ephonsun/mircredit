/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :菜单权限
 * Author     :cheny
 * Create Date:2012-8-20
 */
package com.banger.mobile.dao.menuAuth;

import com.banger.mobile.domain.model.menuAuth.SysMenu;
import com.banger.mobile.domain.model.menuAuth.SysMenuAuth;
import com.banger.mobile.domain.model.menuAuth.MenuAuthBean;
import java.util.List;

/**
 * @author cheny
 * @version $Id: MenuAuthDao.java,v 0.1 2012-8-20 下午12:57:41 cheny Exp $
 */
public interface MenuAuthDao {

    /**
     * 查询相应的菜单
     */
    public List<SysMenu> getMenuList(String menuIds);
    /**
     * 根据角色查询菜单权限
     */
    public List<SysMenuAuth> getMenuAuthByRoleId(int roleId);
    /**
     * 获得菜单权限树集合
     */
    public List<MenuAuthBean> getMenuAuthTree();

    /**
     * 增加菜单权限
     * @param sysMenuAuth
     */
    public void addMenuAuth(SysMenuAuth sysMenuAuth);

    /**
     * 删除菜单权限
     * @param menuId
     */
    public void deleteMenuAuthByMenuId(Integer menuId);

    /**
     * 获得角色菜单权限树集合
     */
    public List<MenuAuthBean> getRoleMenuAuthTree();
    /**
     * 根据角色查询菜单权限JSON树数据
     */
    public List<MenuAuthBean> getDetailMenuByRoleId(Integer roleId);

}
