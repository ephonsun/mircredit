/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :菜单权限...
 * Author     :cheny
 * Create Date:2012-8-20
 */
package com.banger.mobile.facade.menuAuth;

import com.banger.mobile.domain.model.menuAuth.SysMenu;
import com.banger.mobile.domain.model.menuAuth.SysMenuAuth;
import com.banger.mobile.domain.model.menuAuth.MenuAuthBean;
import net.sf.json.JSONArray;

import java.util.List;

/**
 * @author cheny
 * @version $Id: MenuAuthService.java,v 0.1 2012-8-20 下午1:12:40 cheny Exp $
 */
public interface MenuAuthService {
    /**
     * 查询登录用户相应的相应的菜单
     */
    public List<SysMenu> getMenuList();

    /**
     * 增加菜单权限
     * @param sysMenuAuth
     */
    public void addMenuAuth(SysMenuAuth sysMenuAuth);
    /**
     * 根据角色查询菜单权限
     */
    public List<SysMenuAuth> getMenuAuthByRoleId(int roleId);
    /**
     * 根据登录用户(多角色合并)查询相应菜单id
     */
    public String getMenuIdsByRoleId();
    /**
     * 菜单权限json
     */
    public JSONArray getMenuJson();

    /**
     * 获得菜单权限树集合
     */
    public List<MenuAuthBean> getMenuAuthTree();

    /**
     * 树转换为json
     * @return
     */
    public JSONArray getAllMenuJson();

    /**
     * 角色管理树转换为json(无系统配置)
     * @return
     */
    public JSONArray getRoleMenuJson();

    /**
     * 根据角色查询菜单权限JSON树数据
     */
    public JSONArray getDetailMenuByRoleId(Integer roleId);
    /**
     * 返回角色对应菜单权限节点的id集合
     */
    public List<Integer> getTreeNodeIds(int roleId);

    /**
     * 修改菜单权限
     */
    public void updateMenuAuth(int roleId,List<Integer> MenuIds,int userId);
}
