/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :用户实体
 * Author     :yangy
 * Create Date:2012-5-17
 */
package com.banger.mobile.domain.model.user;

import com.banger.mobile.domain.model.base.map.BaseMapUserGpsLog;
import com.banger.mobile.domain.model.base.user.BaseSysUser;
import com.banger.mobile.domain.model.map.MapUserGpsLog;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.util.List;

/**
 * @author yangy
 * @version $Id: SysUser.java,v 0.1 2012-5-17 1:53:05 PM yangy Exp $
 */
public class SysUser extends BaseSysUser {

    private static final long serialVersionUID = 1383493278476756139L;
    
    private Integer buessRank; //业绩排名
    private String  roleName;  //角色名
    private List<MapUserGpsLog> gpsLogList;

    public List<MapUserGpsLog> getGpsLogList() {
        return gpsLogList;
    }

    public void setGpsLogList(List<MapUserGpsLog> gpsLogList) {
        this.gpsLogList = gpsLogList;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getBuessRank() {
        return buessRank;
    }

    public void setBuessRank(Integer buessRank) {
        this.buessRank = buessRank;
    }

    public SysUser() {
        super();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-488461365, -691026117).appendSuper(super.hashCode())
            .toHashCode();
    }
    

}
