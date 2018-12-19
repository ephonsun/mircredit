/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :管理面板Bean
 * Author     :liyb
 * Create Date:2012-12-27
 */
package com.banger.mobile.domain.model.communication;

import java.io.Serializable;
import java.util.Date;

/**
 * @author liyb
 * @version $Id: ThemePanel.java,v 0.1 2012-12-27 下午02:49:43 liyb Exp $
 */
public class ThemePanel implements Serializable {

    private static final long serialVersionUID = -6372978437325249959L;

    private Integer           themeId;                                 //主题ID
    private Integer           themeType;                               //主题类型
    private Integer           partitionId;                             //分区ID
    private Integer           templateId;                              //板块ID
    private String            themeTitle;                              //标题
    private String            templateName;                            //版块名称
    private Integer           templateUserId;                          //版块创建人
    private Date              createDate;                              //发布时间
    private String            userName;                                //作者
    private Date              lastReplayDate;                          //最后发表时间
    private Date              collectDate;                             //收藏时间
    public Integer getThemeId() {
        return themeId;
    }
    public void setThemeId(Integer themeId) {
        this.themeId = themeId;
    }
    public Integer getPartitionId() {
        return partitionId;
    }
    public void setPartitionId(Integer partitionId) {
        this.partitionId = partitionId;
    }
    public Integer getTemplateId() {
        return templateId;
    }
    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }
    public String getThemeTitle() {
        return themeTitle;
    }
    public void setThemeTitle(String themeTitle) {
        this.themeTitle = themeTitle;
    }
    public String getTemplateName() {
        return templateName;
    }
    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public Date getLastReplayDate() {
        return lastReplayDate;
    }
    public void setLastReplayDate(Date lastReplayDate) {
        this.lastReplayDate = lastReplayDate;
    }
    public Date getCollectDate() {
        return collectDate;
    }
    public void setCollectDate(Date collectDate) {
        this.collectDate = collectDate;
    }
    public Integer getTemplateUserId() {
        return templateUserId;
    }
    public void setTemplateUserId(Integer templateUserId) {
        this.templateUserId = templateUserId;
    }
    public Integer getThemeType() {
        return themeType;
    }
    public void setThemeType(Integer themeType) {
        this.themeType = themeType;
    }
}
