/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :用户登录信息对外调用接口
 * Author     :yangy
 * Create Date:2012-3-28
 */
package com.banger.mobile.domain.model.user;

import java.io.Serializable;
import java.util.List;

import com.banger.mobile.domain.Enum.user.EnumUserStatus;


public interface IUserInfo extends Serializable   {
    /**
     * 取用户姓名
     * @return
     */
	String getUserName();
	/**
	 * 设置用户姓名
	 * @param name
	 */
	void setUserName(String name);
	/**
	 * 取用户ID
	 * @return
	 */
	Integer getUserId();

	/**
     * 获取登录用户状态
     * @return
     */
	EnumUserStatus getStatus();
	/**
     * 设置登录用户状态
     * @return
     */
	void setStatus(EnumUserStatus status);
	
	/**
	 * 取部门编号
	 * @return
	 */
    Integer getDeptId();

    /**
     * 取部门名称
     * @return
     */
    String getDeptname();
    /**
     * 取角色名集合
     * @return
     */
    String[] getRoleNames();
    /**
     * 取用户名
     * @return
     */
    String getAccount();
    /**
     * 取IP地址
     * @return
     */
    String getIP();
    
    /**
     * 取SessionId
     * @return
     */
    String getSessionId();
    
    /**
     * 设置SessionId
     */
    void setSessionId(String sessionId);
    
    /**
     * 设置IP地址
     * @param ip
     */
    void setIP(String ip);

    /**
     * 取角色ID集合
     * @return
     */
    String[] getRoles();
    
    /**
     * 取功能集合
     * @return
     */
    List<String> getFuncCodes();
    
    /**
     * 设置功能集合
     * @param funccodes
     */
    void setFuncCodes(List<String> funcCodes);
    /**
     * 设置角色集合
     * @param roles
     */
    void setRoles(String[] roles);
    
    /**
     * 判断当前用户是否是机构管理员
     * @return
     */
    boolean isBusinessExecutives();
    
    
    /**
     * 设置当前用户是否是业务主管
     * @return
     */
    void setBusinessExecutives(boolean businessExecutives);
    
   /**
    * 设置用户菜单URL
    */
    void setMenuUrl(List<String> menuUrl);
    
    /**
     * 获取用户菜单URL
     */
    List<String> getMenuUrl();
    
    public String getDataCompetence();

    public void setDataCompetence(String dataCompetence);

//
//    SysOrganization getOrg();
//    
//    void setOrg(SysOrganization org);
    /**
     * 设置不被权限拦截的action
     */
    void setExcludeActions(String excludeActions);
    /**
     * 获得不被权限拦截的action
     */
    String getExcludeActions();
}
