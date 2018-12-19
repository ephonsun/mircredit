/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :CommUserRelation用户/交流关系表实体基类
 * Author     :liyb
 * Create Date:2012-12-24
 */
package com.banger.mobile.domain.model.base.communication;

import java.io.Serializable;
import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;

/**
 * @author liyb
 * @version $Id: BaseCommUserRelation.java,v 0.1 2012-12-24 下午02:17:12 liyb Exp $
 */
public class BaseCommUserRelation extends BaseObject implements Serializable {

    private static final long serialVersionUID = -5867724890801393560L;
    private Integer           relationId;                               //主键ID
    private Integer           relationType;                             //主题/投票选项 1主题,2投票
    private Integer           commId;                                   //交流ID
    private Integer           userId;                                   //用户ID
    private Integer           isCollect;                                //是否收藏
    private Integer           isThemeSelect;                            //是否投票
    private Integer           isOptionSelect;                           //针对某个选项投票
    private Date              collectDate;                              //收藏时间
    
    public Integer getRelationId() {
        return relationId;
    }
    public void setRelationId(Integer relationId) {
        this.relationId = relationId;
    }
    public Integer getRelationType() {
        return relationType;
    }
    public void setRelationType(Integer relationType) {
        this.relationType = relationType;
    }
    public Integer getCommId() {
        return commId;
    }
    public void setCommId(Integer commId) {
        this.commId = commId;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getIsCollect() {
        return isCollect;
    }
    public void setIsCollect(Integer isCollect) {
        this.isCollect = isCollect;
    }
    public Integer getIsThemeSelect() {
        return isThemeSelect;
    }
    public void setIsThemeSelect(Integer isThemeSelect) {
        this.isThemeSelect = isThemeSelect;
    }
    public Integer getIsOptionSelect() {
        return isOptionSelect;
    }
    public void setIsOptionSelect(Integer isOptionSelect) {
        this.isOptionSelect = isOptionSelect;
    }
    public Date getCollectDate() {
        return collectDate;
    }
    public void setCollectDate(Date collectDate) {
        this.collectDate = collectDate;
    }

}
