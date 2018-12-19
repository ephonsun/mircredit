/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :功能权限
 * Author     :cheny
 * Create Date:2012-5-31
 */
package com.banger.mobile.dao.funcAuth;

import java.util.List;

import com.banger.mobile.domain.model.funcAuth.FuncAuthBean;
import com.banger.mobile.domain.model.funcAuth.SysFuncAuth;
import com.banger.mobile.domain.model.menuAuth.SysMenuAuth;

/**
 * @author cheny
 * @version $Id: FuncAuthDao.java,v 0.1 2012-5-31 下午1:10:14 cheny Exp $
 */
public interface FuncAuthDao {

    /**
     * 获得功能权限树集合
     */
    public List<FuncAuthBean> getFuncAuthTree();
    /**
     *  新增角色功能权限 -->
     */
   public void insertFuncAuth(SysFuncAuth funcAuth);
   /**
    * 删除角色功能权限  -->
    */
   public void deleteFuncAuth(int roleId);
   /**
    *  新增角色菜单权限 -->
    */
   public void insertMenuAuth(SysMenuAuth menuAuth);
   /**
    * 删除角色菜单权限  -->
    */
   public void deleteMenuAuth(int roleId);
   /**
    *  查看角色对应的功能权限 -->
    */
  public List<FuncAuthBean> getDetailFuncByRoleId(int roleId);
  /**
   * 根据roleId查询功能权限
   */
  public List<SysFuncAuth> getFuncAuthByRoleId(int roleId);
  /**
   * 根据功能操作删除权限
   * @param funcId
   */
  public void deleteFuncAuthByFuncId(int funcId);
  /**
   * 根据角色id集合查询功能id 
   */
  public List<Integer> getFuncIdListByRoleId(String roleIds);
}
