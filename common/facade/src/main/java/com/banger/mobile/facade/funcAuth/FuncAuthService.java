/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :功能权限...
 * Author     :cheny
 * Create Date:2012-5-31
 */
package com.banger.mobile.facade.funcAuth;

import java.util.List;

import net.sf.json.JSONArray;

import com.banger.mobile.domain.model.funcAuth.FuncAuthBean;
import com.banger.mobile.domain.model.funcAuth.SysFuncAuth;
import com.banger.mobile.domain.model.menuAuth.SysMenuAuth;

/**
 * @author cheny
 * @version $Id: FuncAuthService.java,v 0.1 2012-5-31 下午1:56:38 cheny Exp $
 */
public interface FuncAuthService {

    /**
     * 获得功能权限树集合
     */
    public List<FuncAuthBean> getFuncAuthTree();
    /**
     * 权限树转换为json
     * @return
     */
    public JSONArray getAllFuncJson();
    
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
    * 根据roleId查询功能权限
    */
   public List<SysFuncAuth> getFuncAuthByRoleId(int roleId);
   /**
    * 修改功能权限
    */
   public void updateFuncAuth(int roleId,List<Integer> funcIds,int userId);
   /**
    *  查看角色对应的功能权限 -->
    */
  public List<FuncAuthBean> getDetailFuncByRoleId(int roleId);
  /**
   * 查看角色功能权限树
   */
  public JSONArray getRoleDetailFuncJson(int roleId);
  /**
   * 返回角色对应功能权限节点的id集合
   */
  public List<Integer> getTreeNodeIds(int roleId);
  /**
   * 登录用户返回菜单url
   */
  public List<String> getMenuUrlByRoleIds(String[] roleIds);
  /**
   * 登录用户返回功能操作url
   */
  public List<String> getFuncUrlByRoleIds(String[] roleIds);
  /**
   * 登录用户返回功能操作的id集合
   */
  public String[] getFuncIdsByRolesIds(String[] roleIds);
  /**
   * 根据功能操作删除权限
   * @param funcId
   */
  public void deleteFuncAuthByFuncId(int funcId);
  /**
   * 通话过程是否自动录音
   */
  public boolean isAutoRecord();
  /**
   * 来电时是否自动播放录音提示音 
   */
  public boolean isPlayRecord();
  /**
   * 是否有新建风险测评操作
   */
  public boolean isRiskEvaluation();
  /**
   * 是否有新建理财规划操作
   */
  public boolean isFinancialPlan();
  /**
   * 发送短信无需审核    true 是，false 否
   */
  public boolean isAuditedSms();
  /**
   * 发送彩信无需审核    true 是，false 否
   */
  public boolean isAuditedMms();
}
