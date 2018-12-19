/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :登录用户基本信息
 * Author     :yangy
 * Create Date:2012-3-29
 */
package com.banger.mobile.domain.model.user;

import java.util.ArrayList;
import java.util.List;

import com.banger.mobile.domain.Enum.user.EnumUserStatus;


/**
 * @author Administrator
 * @version $Id: UserInfo.java,v 0.1 2012-3-29 上午9:06:56 Administrator Exp $
 */
public class UserInfo implements com.banger.mobile.domain.model.user.IUserInfo{
    private static final long serialVersionUID = 157830657597911552L;
    private Integer   userId;           //用户编号
    private Integer   deptId;           //部门编号
    private String    deptname;         //部门名称
    private String[]    roleNames;      //角色名称集合      
    private String    userName;         //用户姓名
    private String    account;          //用户名
    private String    sessionId;        //SessionId
    private String    ip;               //ip地址
    private List<String> funcCodes;         //功能代码集合
    private String[]  roles;            //角色集合
    private List<String>  menuUrl;            //菜单集合
    private EnumUserStatus   status;                 //是否被T   1为被T
    private boolean   businessExecutives; //是否是机构管理员
    private String    excludeActions;    //不被权限拦截的action
    private String    dataCompetence;   //数据权限


    public String getDataCompetence() {
        return dataCompetence;
    }

    public void setDataCompetence(String dataCompetence) {
        this.dataCompetence = dataCompetence;
    }
    
    public EnumUserStatus getStatus() {
        return status;
    }

    public void setStatus(EnumUserStatus status) {
        this.status = status;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public List<String> getFuncCodes() {
        return funcCodes;
    }

    public void setFuncCodes(List<String> funcCodes) {
        this.funcCodes = funcCodes;
    }

    public List<String> getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(List<String> menuUrl) {
        this.menuUrl = menuUrl;
    }

    public boolean isBusinessExecutives() {
        return businessExecutives;
    }

    public void setBusinessExecutives(boolean businessExecutives) {
        this.businessExecutives = businessExecutives;
    }



    public UserInfo(){
        roleNames=new String[0];
        roles=new String[0];
        ip="";
        funcCodes=new ArrayList<String>();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public String[] getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(String[] roleNames) {
        this.roleNames = roleNames;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    public String getIP() {
        return this.ip;
    }

    public void setIP(String ip) {
        this.ip=ip;
    }

    public String getExcludeActions() {
        return excludeActions;
    }

    public void setExcludeActions(String excludeActions) {
        this.excludeActions = excludeActions;
    }



    


    
    
    

}
