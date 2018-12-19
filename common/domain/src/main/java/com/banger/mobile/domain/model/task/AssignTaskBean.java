/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :分配营销任务Bean
 * Author     :liyb
 * Create Date:2012-9-3
 */
package com.banger.mobile.domain.model.task;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author liyb
 * @version $Id: AssignTaskBean.java,v 0.1 2012-9-3 上午10:35:36 liyb Exp $
 */
public class AssignTaskBean implements Serializable {

    private static final long serialVersionUID = 9181574878741473731L;
    private Integer           taskId;                                   //任务ID
    private Integer           id;                                       //父ID
    private Integer           parentId;                                 //子ID
    private String            textName;
    private String            type;                                     //类型 'U:用户 D:机构'
    private BigDecimal        targetMoney;                              //任务目标
    private Double            assignedMoney;                            //已分配目标额
    private Double            unAssignMoney;                            //未分配目标额
    private String            taskRate;                                 //占任务比率
    private Double            buyMoney;                                 //已完成销售总额
    private String            finishRate;                               //完成度
    
    public Integer getTaskId() {
        return taskId;
    }
    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
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
    public BigDecimal getTargetMoney() {
        return targetMoney;
    }
    public void setTargetMoney(BigDecimal targetMoney) {
        this.targetMoney = targetMoney;
    }
    public Double getAssignedMoney() {
        return assignedMoney;
    }
    public void setAssignedMoney(Double assignedMoney) {
        this.assignedMoney = assignedMoney;
    }
    public Double getUnAssignMoney() {
        return unAssignMoney;
    }
    public void setUnAssignMoney(Double unAssignMoney) {
        this.unAssignMoney = unAssignMoney;
    }
    public String getTaskRate() {
        return taskRate;
    }
    public void setTaskRate(String taskRate) {
        this.taskRate = taskRate;
    }
    public Double getBuyMoney() {
        return buyMoney;
    }
    public void setBuyMoney(Double buyMoney) {
        this.buyMoney = buyMoney;
    }
    public String getFinishRate() {
        return finishRate;
    }
    public void setFinishRate(String finishRate) {
        this.finishRate = finishRate;
    }
}
