/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :快速规划基类
 * Author     :yujh
 * Create Date:Jul 16, 2012
 */
package com.banger.mobile.domain.model.base.plnFastPlan;

import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author yujh
 * @version $Id: BasePlnFastPlan.java,v 0.1 Jul 16, 2012 4:34:49 PM Administrator Exp $
 */
public class BasePlnFastPlan extends BaseObject {

    private static final long serialVersionUID = 5409860159583400997L;
    private Integer         fastPlanId;             //id
    private Integer         userId;                 //用户id
    private Integer         customerId;             //客户id
    private Date            planDate;               //规划日期
    private String          planName;               //规划名称
    private String          customerNo;             //客户编号
    private String          customerName;           //客户姓名
    private String          sex;                    //性别
    private Integer         age;                    //年龄
    private String          phone;                  //联系电话
    private Integer         idTypeId;               //证件类型id
    private String          idNo;                   //证件号码
    private double          monthlyFamilyExpend;    //每月家庭正常消费
    private double          monthlyFamilyOutLay;    //每月家庭固定支出
    private double          monthlyDeposit;         //退休前每月支出
    private double          availableInvestMoney;   //目前可用于投资的资金
    private double          houseValue;             //现有房产价值
    private double          houseCreditRemaining;   //现有房产贷款余额
    private double          houseMonthlyRepayMent;  //现有房产贷款月还款额
    private double          houseCreditRate;        //购房贷款利率
    private Integer         houseCreditYearLimit;   //现有房产贷款剩余年限
    private double          inflation;              //预计通货膨胀
    private Integer         intervalTypeId;         //投资偏好
    private double          investIncomingRate;     //期望的投资收益率
    private Integer         retireAge;              //计划退休年龄
    private Integer         retireYearLimit;         //养老年限
    private double          retireMonthlyCost;      //退休后每月生活支出
    private Integer         isDoHousePlan;          //是否参与规划：购房目标
    private Integer         planHouseYear;          //计划购房时间多少年后
    private double          planHousePrice;         //计划购房价格
    private double          planHouseDownPayMent;   //计划购房首付比例
    private Integer         planHouseYearLimit;     //计划购房贷款年限
    private Integer         isDoCollagePlan;        //是否参与规划：子女教育目标
    private Integer         collageYearLimit;       //孩子接受高等教育年限
    private double          collageCost;            //目前每年高等教育费用
    private Integer         collageYearNeed;        //孩子距离上大学年数
    private Integer         isDel;                  //是否删除
    public Integer getFastPlanId() {
        return fastPlanId;
    }
    public void setFastPlanId(Integer fastPlanId) {
        this.fastPlanId = fastPlanId;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    public Date getPlanDate() {
        return planDate;
    }
    public void setPlanDate(Date planDate) {
        this.planDate = planDate;
    }
    public String getPlanName() {
        return planName;
    }
    public void setPlanName(String planName) {
        this.planName = planName;
    }
    public String getCustomerNo() {
        return customerNo;
    }
    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public Integer getIdTypeId() {
        return idTypeId;
    }
    public void setIdTypeId(Integer idTypeId) {
        this.idTypeId = idTypeId;
    }
    public String getIdNo() {
        return idNo;
    }
    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }
    public double getMonthlyFamilyExpend() {
        return monthlyFamilyExpend;
    }
    public void setMonthlyFamilyExpend(double monthlyFamilyExpend) {
        this.monthlyFamilyExpend = monthlyFamilyExpend;
    }
    public double getMonthlyFamilyOutLay() {
        return monthlyFamilyOutLay;
    }
    public void setMonthlyFamilyOutLay(double monthlyFamilyOutLay) {
        this.monthlyFamilyOutLay = monthlyFamilyOutLay;
    }
    public double getMonthlyDeposit() {
        return monthlyDeposit;
    }
    public void setMonthlyDeposit(double monthlyDeposit) {
        this.monthlyDeposit = monthlyDeposit;
    }
    public double getAvailableInvestMoney() {
        return availableInvestMoney;
    }
    public void setAvailableInvestMoney(double availableInvestMoney) {
        this.availableInvestMoney = availableInvestMoney;
    }
    public double getHouseValue() {
        return houseValue;
    }
    public void setHouseValue(double houseValue) {
        this.houseValue = houseValue;
    }
    public double getHouseCreditRemaining() {
        return houseCreditRemaining;
    }
    public void setHouseCreditRemaining(double houseCreditRemaining) {
        this.houseCreditRemaining = houseCreditRemaining;
    }
    public double getHouseMonthlyRepayMent() {
        return houseMonthlyRepayMent;
    }
    public void setHouseMonthlyRepayMent(double houseMonthlyRepayMent) {
        this.houseMonthlyRepayMent = houseMonthlyRepayMent;
    }
    public double getHouseCreditRate() {
        return houseCreditRate;
    }
    public void setHouseCreditRate(double houseCreditRate) {
        this.houseCreditRate = houseCreditRate;
    }
    public Integer getHouseCreditYearLimit() {
        return houseCreditYearLimit;
    }
    public void setHouseCreditYearLimit(Integer houseCreditYearLimit) {
        this.houseCreditYearLimit = houseCreditYearLimit;
    }
    public double getInflation() {
        return inflation;
    }
    public void setInflation(double inflation) {
        this.inflation = inflation;
    }
    public Integer getIntervalTypeId() {
        return intervalTypeId;
    }
    public void setIntervalTypeId(Integer intervalTypeId) {
        this.intervalTypeId = intervalTypeId;
    }
    public double getInvestIncomingRate() {
        return investIncomingRate;
    }
    public void setInvestIncomingRate(double investIncomingRate) {
        this.investIncomingRate = investIncomingRate;
    }
    public Integer getRetireAge() {
        return retireAge;
    }
    public void setRetireAge(Integer retireAge) {
        this.retireAge = retireAge;
    }
    public Integer getRetireYearLimit() {
        return retireYearLimit;
    }
    public void setRetireYearLimit(Integer retireYearLimit) {
        this.retireYearLimit = retireYearLimit;
    }
    public double getRetireMonthlyCost() {
        return retireMonthlyCost;
    }
    public void setRetireMonthlyCost(double retireMonthlyCost) {
        this.retireMonthlyCost = retireMonthlyCost;
    }
    public Integer getIsDoHousePlan() {
        return isDoHousePlan;
    }
    public void setIsDoHousePlan(Integer isDoHousePlan) {
        this.isDoHousePlan = isDoHousePlan;
    }
    public Integer getPlanHouseYear() {
        return planHouseYear;
    }
    public void setPlanHouseYear(Integer planHouseYear) {
        this.planHouseYear = planHouseYear;
    }
    public double getPlanHousePrice() {
        return planHousePrice;
    }
    public void setPlanHousePrice(double planHousePrice) {
        this.planHousePrice = planHousePrice;
    }
    public double getPlanHouseDownPayMent() {
        return planHouseDownPayMent;
    }
    public void setPlanHouseDownPayMent(double planHouseDownPayMent) {
        this.planHouseDownPayMent = planHouseDownPayMent;
    }
    public Integer getPlanHouseYearLimit() {
        return planHouseYearLimit;
    }
    public void setPlanHouseYearLimit(Integer planHouseYearLimit) {
        this.planHouseYearLimit = planHouseYearLimit;
    }
    public Integer getIsDoCollagePlan() {
        return isDoCollagePlan;
    }
    public void setIsDoCollagePlan(Integer isDoCollagePlan) {
        this.isDoCollagePlan = isDoCollagePlan;
    }
    public Integer getCollageYearLimit() {
        return collageYearLimit;
    }
    public void setCollageYearLimit(Integer collageYearLimit) {
        this.collageYearLimit = collageYearLimit;
    }
    public double getCollageCost() {
        return collageCost;
    }
    public void setCollageCost(double collageCost) {
        this.collageCost = collageCost;
    }
    public Integer getCollageYearNeed() {
        return collageYearNeed;
    }
    public void setCollageYearNeed(Integer collageYearNeed) {
        this.collageYearNeed = collageYearNeed;
    }
    public Integer getIsDel() {
        return isDel;
    }
    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof BasePlnFastPlan)) {
            return false;
        }
        BasePlnFastPlan rhs = (BasePlnFastPlan) object;
        return new EqualsBuilder().appendSuper(super.equals(object)).append(this.phone, rhs.phone)
            .append(this.userId, rhs.userId).append(this.planHousePrice, rhs.planHousePrice)
            .append(this.collageCost, rhs.collageCost)
            .append(this.isDoHousePlan, rhs.isDoHousePlan).append(this.planHouseYear,
                rhs.planHouseYear).append(this.retireMonthlyCost, rhs.retireMonthlyCost).append(
                this.monthlyFamilyOutLay, rhs.monthlyFamilyOutLay).append(this.collageYearNeed,
                rhs.collageYearNeed).append(this.houseValue, rhs.houseValue).append(this.sex,
                rhs.sex).append(this.age, rhs.age).append(this.planName, rhs.planName).append(
                this.isDel, rhs.isDel).append(this.houseCreditRemaining, rhs.houseCreditRemaining)
            .append(this.houseCreditYearLimit, rhs.houseCreditYearLimit).append(this.inflation,
                rhs.inflation).append(this.retireYearLimit, rhs.retireYearLimit).append(
                this.idTypeId, rhs.idTypeId).append(this.availableInvestMoney,
                rhs.availableInvestMoney).append(this.intervalTypeId, rhs.intervalTypeId).append(
                this.idNo, rhs.idNo).append(this.planDate, rhs.planDate).append(
                this.collageYearLimit, rhs.collageYearLimit)
            .append(this.fastPlanId, rhs.fastPlanId).append(this.houseCreditRate,
                rhs.houseCreditRate).append(this.investIncomingRate, rhs.investIncomingRate)
            .append(this.monthlyDeposit, rhs.monthlyDeposit)
            .append(this.customerId, rhs.customerId).append(this.planHouseYearLimit,
                rhs.planHouseYearLimit).append(this.monthlyFamilyExpend, rhs.monthlyFamilyExpend)
            .append(this.isDoCollagePlan, rhs.isDoCollagePlan).append(this.customerNo,
                rhs.customerNo).append(this.customerName, rhs.customerName).append(this.retireAge,
                rhs.retireAge).append(this.houseMonthlyRepayMent, rhs.houseMonthlyRepayMent)
            .append(this.planHouseDownPayMent, rhs.planHouseDownPayMent).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1353221401, 281757069).appendSuper(super.hashCode()).append(
            this.phone).append(this.userId).append(this.planHousePrice).append(this.collageCost)
            .append(this.isDoHousePlan).append(this.planHouseYear).append(this.retireMonthlyCost)
            .append(this.monthlyFamilyOutLay).append(this.collageYearNeed).append(this.houseValue)
            .append(this.sex).append(this.age).append(this.planName).append(this.isDel).append(
                this.houseCreditRemaining).append(this.houseCreditYearLimit).append(this.inflation)
            .append(this.retireYearLimit).append(this.idTypeId).append(this.availableInvestMoney)
            .append(this.intervalTypeId).append(this.idNo).append(this.planDate).append(
                this.collageYearLimit).append(this.fastPlanId).append(this.houseCreditRate).append(
                this.investIncomingRate).append(this.monthlyDeposit).append(this.customerId)
            .append(this.planHouseYearLimit).append(this.monthlyFamilyExpend).append(
                this.isDoCollagePlan).append(this.customerNo).append(this.customerName).append(
                this.retireAge).append(this.houseMonthlyRepayMent)
            .append(this.planHouseDownPayMent).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("fastPlanId", this.fastPlanId).append("inflation",
            this.inflation).append("houseValue", this.houseValue).append("planHouseDownPayMent",
            this.planHouseDownPayMent).append("age", this.age).append("retireYearLimit",
            this.retireYearLimit).append("endRow", this.getEndRow()).append("planHousePrice",
            this.planHousePrice).append("planDate", this.planDate).append("monthlyDeposit",
            this.monthlyDeposit).append("houseMonthlyRepayMent", this.houseMonthlyRepayMent)
            .append("idNo", this.idNo).append("investIncomingRate", this.investIncomingRate)
            .append("collageYearNeed", this.collageYearNeed).append("monthlyFamilyOutLay",
                this.monthlyFamilyOutLay).append("sex", this.sex).append("userId", this.userId)
            .append("intervalTypeId", this.intervalTypeId).append("planHouseYear",
                this.planHouseYear).append("isDoCollagePlan", this.isDoCollagePlan).append(
                "startRow", this.getStartRow()).append("customerId", this.customerId).append(
                "houseCreditRemaining", this.houseCreditRemaining).append("isDoHousePlan",
                this.isDoHousePlan).append("retireAge", this.retireAge).append("houseCreditRate",
                this.houseCreditRate).append("availableInvestMoney", this.availableInvestMoney)
            .append("planName", this.planName).append("customerNo", this.customerNo).append(
                "planHouseYearLimit", this.planHouseYearLimit).append("phone", this.phone).append(
                "idTypeId", this.idTypeId).append("collageYearLimit", this.collageYearLimit)
            .append("monthlyFamilyExpend", this.monthlyFamilyExpend).append("customerName",
                this.customerName).append("houseCreditYearLimit", this.houseCreditYearLimit)
            .append("collageCost", this.collageCost).append("isDel", this.isDel).append(
                "retireMonthlyCost", this.retireMonthlyCost).toString();
    }
    
    
    
}
