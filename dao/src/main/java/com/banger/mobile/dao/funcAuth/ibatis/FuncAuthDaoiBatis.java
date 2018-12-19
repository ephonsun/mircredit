/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :功能权限
 * Author     :cheny
 * Create Date:2012-5-31
 */
package com.banger.mobile.dao.funcAuth.ibatis;

import java.util.List;

import com.banger.mobile.dao.funcAuth.FuncAuthDao;
import com.banger.mobile.domain.model.funcAuth.FuncAuthBean;
import com.banger.mobile.domain.model.funcAuth.SysFuncAuth;
import com.banger.mobile.domain.model.menuAuth.SysMenuAuth;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author cheny
 * @version $Id: FuncAuthDaoiBatis.java,v 0.1 2012-5-31 下午1:10:45 cheny Exp $
 */
public class FuncAuthDaoiBatis extends GenericDaoiBatis implements FuncAuthDao{
    /**
     * 
     */
    public FuncAuthDaoiBatis() {
        super(FuncAuthBean.class);
    }
    /**
     * @param persistentClass
     */
    public FuncAuthDaoiBatis(Class persistentClass) {
        super(FuncAuthBean.class);
    }
    /**
     * 获得功能权限树集合
     */
    public List<FuncAuthBean> getFuncAuthTree(){
        return this.getSqlMapClientTemplate().queryForList("getFuncAuthTree");
    }
    

    /**
     *  新增角色功能权限 -->
     */
   public void insertFuncAuth(SysFuncAuth funcAuth){
       this.getSqlMapClientTemplate().insert("insertFuncAuth",funcAuth);
   }
   /**
    * 删除角色功能权限  -->
    */
   public void deleteFuncAuth(int roleId) {
       this.getSqlMapClientTemplate().delete("deleteFuncAuth",roleId);
   }
   
   /**
    *  新增角色菜单权限 -->
    */
   public void insertMenuAuth(SysMenuAuth menuAuth){
       this.getSqlMapClientTemplate().insert("insertMenuAuth",menuAuth);
   }
   /**
    * 删除角色菜单权限  -->
    */
   public void deleteMenuAuth(int roleId){
       this.getSqlMapClientTemplate().delete("deleteMenuAuth",roleId);
   }
   /**
    *  查看角色对应的功能权限 -->
    */
  public List<FuncAuthBean> getDetailFuncByRoleId(int roleId){
      return this.getSqlMapClientTemplate().queryForList("getDetailFuncByRoleId",roleId);
  }
  /**
   * 根据roleId查询功能权限
   */
  public List<SysFuncAuth> getFuncAuthByRoleId(int roleId){
      return this.getSqlMapClientTemplate().queryForList("getFuncAuthByRoleId",roleId);
  }
  /**
   * 根据功能操作删除权限
   * @param funcId
   */
  public void deleteFuncAuthByFuncId(int funcId){
      this.getSqlMapClientTemplate().delete("deleteFuncAuthByFuncId",funcId);
  }
  /**
   * 根据角色id集合查询功能id 
   */
  public List<Integer> getFuncIdListByRoleId(String roleIds){
      return this.getSqlMapClientTemplate().queryForList("getFuncIdListByRoleId",roleIds);
  }
  
}
