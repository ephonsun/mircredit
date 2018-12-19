/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :CommTheme交流主题实体类
 * Author     :liyb
 * Create Date:2012-12-24
 */
package com.banger.mobile.domain.model.communication;

import java.util.Date;

import com.banger.mobile.domain.model.base.communication.BaseCommTheme;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author liyb
 * @version $Id: CommTheme.java,v 0.1 2012-12-24 下午02:05:01 liyb Exp $
 */
public class CommTheme extends BaseCommTheme {

    private static final long serialVersionUID = 4771808289283021504L;
    private String            userName;                                //作者
    private String            deptName;                                //部门
    private Date              lastReplyDate;                           //最后发表时间
    private Integer           themeUserCount;                          //个人主题数
    private Integer           themeUserReplayCount;                    //个人帖子数

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1290460571, -878808449).appendSuper(super.hashCode())
            .toHashCode();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getLastReplyDate() {
        return lastReplyDate;
    }

    public void setLastReplyDate(Date lastReplyDate) {
        this.lastReplyDate = lastReplyDate;
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

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
