/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :主题回复CommThemeReply实体基类
 * Author     :liyb
 * Create Date:2012-12-24
 */
package com.banger.mobile.domain.model.base.communication;

import java.io.Serializable;
import java.util.Date;

import com.banger.mobile.domain.annotation.EscapeFieldFilter;
import com.banger.mobile.domain.model.base.BaseObject;

/**
 * @author liyb
 * @version $Id: BaseCommThemeReply.java,v 0.1 2012-12-24 下午02:08:10 liyb Exp $
 */
public class BaseCommThemeReply extends BaseObject implements Serializable {

    private static final long serialVersionUID = -3082541080733120851L;
    private Integer           replyId;                                  //主键ID
    private Integer           themeId;                                  //主题ID
    @EscapeFieldFilter
    private String            replyContent;                             //内容
    private Integer           isDel;                                   //是否删除  0：不删除  1：删除
    private Date              createDate;                              //创建时间
    private Date              updateDate;                              //更新时间
    private Integer           createUser;                              //创建用户
    private Integer           updateUser;                              //更新用户
    
    public Integer getReplyId() {
        return replyId;
    }
    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }
    public Integer getThemeId() {
        return themeId;
    }
    public void setThemeId(Integer themeId) {
        this.themeId = themeId;
    }
    public String getReplyContent() {
        return replyContent;
    }
    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }
    public Integer getIsDel() {
        return isDel;
    }
    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public Date getUpdateDate() {
        return updateDate;
    }
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    public Integer getCreateUser() {
        return createUser;
    }
    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }
    public Integer getUpdateUser() {
        return updateUser;
    }
    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

}
