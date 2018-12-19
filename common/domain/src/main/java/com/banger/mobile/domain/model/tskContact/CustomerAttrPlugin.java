/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :客户归属实体
 * Author     :liyb
 * Create Date:2012-8-14
 */
package com.banger.mobile.domain.model.tskContact;

import java.io.Serializable;

/**
 * @author liyb
 * @version $Id: CustomerAttr.java,v 0.1 2012-8-14 下午02:05:44 liyb Exp $
 */
public class CustomerAttrPlugin implements Serializable {

    private static final long serialVersionUID = -7552062751867942776L;
    private Integer           id;                                       //父ID
    private Integer           parentId;                                 //子ID
    private String            textName;
    private String            type;                                     //类型 'U:用户 D:机构'
    private Integer           counts;                                   //总数
    private Integer           finishCount;                              //完成量
    private Integer           taskStatus;                               //执行者任务完成状态
    private Integer           isActived;                                //是否启用
    private Integer           isDel;                                    //是否删除

    public Integer getIsActived() {
        return isActived;
    }

    public void setIsActived(Integer isActived) {
        this.isActived = isActived;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getTextName() {
        return textName;
    }

    public void setTextName(String textName) {
        this.textName = textName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    public Integer getFinishCount() {
        return finishCount;
    }

    public void setFinishCount(Integer finishCount) {
        this.finishCount = finishCount;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
}
