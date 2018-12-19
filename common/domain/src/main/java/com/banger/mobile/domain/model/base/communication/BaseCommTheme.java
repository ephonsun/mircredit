/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :CommTheme交流主题实体基类
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
 * @version $Id: BaseCommTheme.java,v 0.1 2012-12-24 下午01:55:24 liyb Exp $
 */
public class BaseCommTheme extends BaseObject implements Serializable {

    private static final long serialVersionUID = -2311608513827520193L;
    private Integer           themeId;                                  //主键ID
    private Integer           partitionId;                              //分区ID
    private Integer           templateId;                               //板块ID
    private Integer           themeType;                                //主题类型   1普通主题,2投票主题
    private String            themeTitle;                               //标题
    @EscapeFieldFilter
    private String            themeContent;                             //内容
    private Integer           isTop;                                    //是否置顶    1:是 0:否
    private Date              themeTopDate;                             //置顶时间
    private Integer           themeReplyCount;                          //回复数
    private Integer           themeReadCount;                           //查看数
    private Integer           themeCollectCount;                        //收藏数
    private Integer           selectOptionCount;                        //最多可选项
    private Integer           countVotesDays;                           //计票天数
    private Integer           showOptionResult;                         //投票后结果可见
    private Integer           showOptionUser;                           //公开投票参与人
    private Integer           isDel;                                   //是否删除  0：不删除  1：删除
    private Date              createDate;                              //创建时间
    private Date              updateDate;                              //更新时间
    private Integer           createUser;                              //创建用户
    private Integer           updateUser;                              //更新用户
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
    public Integer getThemeType() {
        return themeType;
    }
    public void setThemeType(Integer themeType) {
        this.themeType = themeType;
    }
    public String getThemeTitle() {
        return themeTitle;
    }
    public void setThemeTitle(String themeTitle) {
        this.themeTitle = themeTitle;
    }
    public String getThemeContent() {
        return themeContent;
    }
    public void setThemeContent(String themeContent) {
        this.themeContent = themeContent;
    }
    public Integer getIsTop() {
        return isTop;
    }
    public void setIsTop(Integer isTop) {
        this.isTop = isTop;
    }
    public Date getThemeTopDate() {
        return themeTopDate;
    }
    public void setThemeTopDate(Date themeTopDate) {
        this.themeTopDate = themeTopDate;
    }
    public Integer getThemeReplyCount() {
        return themeReplyCount;
    }
    public void setThemeReplyCount(Integer themeReplyCount) {
        this.themeReplyCount = themeReplyCount;
    }
    public Integer getThemeReadCount() {
        return themeReadCount;
    }
    public void setThemeReadCount(Integer themeReadCount) {
        this.themeReadCount = themeReadCount;
    }
    public Integer getThemeCollectCount() {
        return themeCollectCount;
    }
    public void setThemeCollectCount(Integer themeCollectCount) {
        this.themeCollectCount = themeCollectCount;
    }
    public Integer getSelectOptionCount() {
        return selectOptionCount;
    }
    public void setSelectOptionCount(Integer selectOptionCount) {
        this.selectOptionCount = selectOptionCount;
    }
    public Integer getCountVotesDays() {
        return countVotesDays;
    }
    public void setCountVotesDays(Integer countVotesDays) {
        this.countVotesDays = countVotesDays;
    }
    public Integer getShowOptionResult() {
        return showOptionResult;
    }
    public void setShowOptionResult(Integer showOptionResult) {
        this.showOptionResult = showOptionResult;
    }
    public Integer getShowOptionUser() {
        return showOptionUser;
    }
    public void setShowOptionUser(Integer showOptionUser) {
        this.showOptionUser = showOptionUser;
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
