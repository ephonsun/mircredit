/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :CommThemeReply主题回复实体类
 * Author     :liyb
 * Create Date:2012-12-24
 */
package com.banger.mobile.domain.model.communication;

import com.banger.mobile.domain.model.base.communication.BaseCommThemeReply;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author liyb
 * @version $Id: CommThemeReply.java,v 0.1 2012-12-24 下午02:12:14 liyb Exp $
 */
public class CommThemeReply extends BaseCommThemeReply {

    private static final long serialVersionUID = -2524082603154255665L;
    private String            userName;                                //作者
    private String            deptName;                                //部门
    private Integer           themeUserCount;                          //个人主题数
    private Integer           themeUserReplayCount;                    //个人帖子数

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-379886597, 1842665339).appendSuper(super.hashCode())
            .toHashCode();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getThemeUserCount() {
        return themeUserCount;
    }

    public void setThemeUserCount(Integer themeUserCount) {
        this.themeUserCount = themeUserCount;
    }

    public Integer getThemeUserReplayCount() {
        return themeUserReplayCount;
    }

    public void setThemeUserReplayCount(Integer themeUserReplayCount) {
        this.themeUserReplayCount = themeUserReplayCount;
    }

}
