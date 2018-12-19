/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :hk
 * Create Date:Sep 7, 2012
 */
package com.banger.mobile.domain.model.product;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.axis2.databinding.types.soapencoding.Decimal;

/**
 * 产品销售统计实体
 * @author hk
 * @version $Id: ReportProductCountBean.java,v 0.1 Sep 7, 2012 2:51:03 PM hk Exp $
 */
public class ReportProductCountBean implements Serializable{//任务量,任务完成量,机构任务量,机构任务完成量,总行任务量,总行任务完成量 围绕这六种
    private static final long serialVersionUID = 5093857323397695629L;
    private String deptName;        //机构名称
    private String userName;     //营销人员
    private BigDecimal targetMoney;       //任务量
    private String buyMoney;       //任务完成量
    private String taskCompliteRate;    //任务完成比
    private Double deptTargetMoney;   //本机构任务量
    private String taskDeptRate;    //机构任务完成比
    private String sumTargetMoneyRate;  //总行任务完成比
    private String moneyUnit;     //单位
    public String getDeptName() {
        return deptName;
    }
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public BigDecimal getTargetMoney() {
        return targetMoney;
    }
    public void setTargetMoney(BigDecimal targetMoney) {
        this.targetMoney = targetMoney;
    }
    public String getBuyMoney() {
        return buyMoney;
    }
    public void setBuyMoney(String buyMoney) {
        this.buyMoney = buyMoney;
    }
    public String getTaskCompliteRate() {
        return taskCompliteRate;
    }
    public void setTaskCompliteRate(String taskCompliteRate) {
        this.taskCompliteRate = taskCompliteRate;
    }
    public Double getDeptTargetMoney() {
        return deptTargetMoney;
    }
    public void setDeptTargetMoney(Double deptTargetMoney) {
        this.deptTargetMoney = deptTargetMoney;
    }
    public String getTaskDeptRate() {
        return taskDeptRate;
    }
    public void setTaskDeptRate(String taskDeptRate) {
        this.taskDeptRate = taskDeptRate;
    }
    public String getSumTargetMoneyRate() {
        return sumTargetMoneyRate;
    }
    public void setSumTargetMoneyRate(String sumTargetMoneyRate) {
        this.sumTargetMoneyRate = sumTargetMoneyRate;
    }
    public String getMoneyUnit() {
        return moneyUnit;
    }
    public void setMoneyUnit(String moneyUnit) {
        this.moneyUnit = moneyUnit;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((buyMoney == null) ? 0 : buyMoney.hashCode());
        result = prime * result + ((deptName == null) ? 0 : deptName.hashCode());
        result = prime * result + ((deptTargetMoney == null) ? 0 : deptTargetMoney.hashCode());
        result = prime * result + ((moneyUnit == null) ? 0 : moneyUnit.hashCode());
        result = prime * result
                 + ((sumTargetMoneyRate == null) ? 0 : sumTargetMoneyRate.hashCode());
        result = prime * result + ((targetMoney == null) ? 0 : targetMoney.hashCode());
        result = prime * result + ((taskCompliteRate == null) ? 0 : taskCompliteRate.hashCode());
        result = prime * result + ((taskDeptRate == null) ? 0 : taskDeptRate.hashCode());
        result = prime * result + ((userName == null) ? 0 : userName.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final ReportProductCountBean other = (ReportProductCountBean) obj;
        if (buyMoney == null) {
            if (other.buyMoney != null)
                return false;
        } else if (!buyMoney.equals(other.buyMoney))
            return false;
        if (deptName == null) {
            if (other.deptName != null)
                return false;
        } else if (!deptName.equals(other.deptName))
            return false;
        if (deptTargetMoney == null) {
            if (other.deptTargetMoney != null)
                return false;
        } else if (!deptTargetMoney.equals(other.deptTargetMoney))
            return false;
        if (moneyUnit == null) {
            if (other.moneyUnit != null)
                return false;
        } else if (!moneyUnit.equals(other.moneyUnit))
            return false;
        if (sumTargetMoneyRate == null) {
            if (other.sumTargetMoneyRate != null)
                return false;
        } else if (!sumTargetMoneyRate.equals(other.sumTargetMoneyRate))
            return false;
        if (targetMoney == null) {
            if (other.targetMoney != null)
                return false;
        } else if (!targetMoney.equals(other.targetMoney))
            return false;
        if (taskCompliteRate == null) {
            if (other.taskCompliteRate != null)
                return false;
        } else if (!taskCompliteRate.equals(other.taskCompliteRate))
            return false;
        if (taskDeptRate == null) {
            if (other.taskDeptRate != null)
                return false;
        } else if (!taskDeptRate.equals(other.taskDeptRate))
            return false;
        if (userName == null) {
            if (other.userName != null)
                return false;
        } else if (!userName.equals(other.userName))
            return false;
        return true;
    }
    public ReportProductCountBean(String deptName, String userName, BigDecimal targetMoney,
                                  String buyMoney, String taskCompliteRate,
                                  Double deptTargetMoney, String taskDeptRate,
                                  String sumTargetMoneyRate, String moneyUnit) {
        super();
        this.deptName = deptName;
        this.userName = userName;
        this.targetMoney = targetMoney;
        this.buyMoney = buyMoney;
        this.taskCompliteRate = taskCompliteRate;
        this.deptTargetMoney = deptTargetMoney;
        this.taskDeptRate = taskDeptRate;
        this.sumTargetMoneyRate = sumTargetMoneyRate;
        this.moneyUnit = moneyUnit;
    }
    public ReportProductCountBean() {
        super();
    }
    
    
}
