/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :用户地理位置实体类...
 * Author     :yangy
 * Create Date:2013-3-13
 */
package com.banger.mobile.domain.model.map;

import com.banger.mobile.domain.model.base.map.BaseMapUserGps;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author yangyang
 * @version $Id: MapUserGps.java,v 0.1 2013-3-13 下午2:15:19 yangyong Exp $
 */
public class MapUserGps extends BaseMapUserGps {

    private static final long serialVersionUID = -7379943206612782449L;

    private String workPlan;       //工作进度
    private String roleName;       //角色名
    private String leadName;       //主管名字
    private String userName;       //用户姓名
    private String account;          //用户名

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getWorkPlan() {
        return workPlan;
    }

    public void setWorkPlan(String workPlan) {
        this.workPlan = workPlan;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getLeadName() {
        return leadName;
    }

    public void setLeadName(String leadName) {
        this.leadName = leadName;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-964275181, -1746748635).appendSuper(super.hashCode())
                .toHashCode();
    }

    public MapUserGps() {
        super();
    }

}
