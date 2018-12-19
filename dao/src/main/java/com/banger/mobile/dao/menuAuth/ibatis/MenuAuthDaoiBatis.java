/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :菜单权限
 * Author     :cheny
 * Create Date:2012-8-20
 */
package com.banger.mobile.dao.menuAuth.ibatis;

import java.util.List;

import com.banger.mobile.dao.menuAuth.MenuAuthDao;
import com.banger.mobile.domain.model.menuAuth.MenuAuthBean;
import com.banger.mobile.domain.model.menuAuth.SysMenu;
import com.banger.mobile.domain.model.menuAuth.SysMenuAuth;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author cheny
 * @version $Id: MenuAuthDaoiBatis.java,v 0.1 2012-8-20 下午12:58:13 cheny Exp $
 */
public class MenuAuthDaoiBatis extends GenericDaoiBatis implements MenuAuthDao{

    /**
     * 
     */
    public MenuAuthDaoiBatis() {
        super(SysMenuAuth.class);
    }
    /**
     * @param persistentClass
     */
    public MenuAuthDaoiBatis(Class persistentClass) {
        super(SysMenuAuth.class);
    }
    /**
     * 查询相应的菜单
     */
    public List<SysMenu> getMenuList(String menuIds){
        menuIds=menuIds.equals("")?"-1":menuIds;
        return this.getSqlMapClientTemplate().queryForList("getMenuList",menuIds);
    }
    /**
     * 根据角色查询菜单权限
     */
    public List<SysMenuAuth> getMenuAuthByRoleId(int roleId){
        return this.getSqlMapClientTemplate().queryForList("getMenuAuthByRoleId",roleId);
    }

    /**
     * 获得菜单权限树集合
     */
    public List<MenuAuthBean> getMenuAuthTree() {
        return this.getSqlMapClientTemplate().queryForList("getMenuAuthTree");
    }

    /**
     * 获得角色菜单权限树集合
     */
    public List<MenuAuthBean> getRoleMenuAuthTree() {
        return this.getSqlMapClientTemplate().queryForList("getRoleMenuAuthTree");
    }
    /**
     * 增加菜单权限
     * @param sysMenuAuth
     */
    public void addMenuAuth(SysMenuAuth sysMenuAuth) {
        this.getSqlMapClientTemplate().insert("addMenuAuth",sysMenuAuth);
    }

    /**
     * 删除菜单权限
     * @param menuId
     */
    public void deleteMenuAuthByMenuId(Integer menuId) {
        this.getSqlMapClientTemplate().delete("deleteMenuAuthByMenuId",menuId);
    }

    /**
     * 根据角色查询菜单权限JSON树数据
     */
    public List<MenuAuthBean> getDetailMenuByRoleId(Integer roleId) {
        return this.getSqlMapClientTemplate().queryForList("getDetailMenuByRoleId",roleId);
    }

}
