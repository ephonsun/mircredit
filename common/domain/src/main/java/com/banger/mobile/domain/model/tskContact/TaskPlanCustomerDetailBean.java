/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :计划执行情况报表明细Bean
 * Author     :liyb
 * Create Date:2012-11-21
 */
package com.banger.mobile.domain.model.tskContact;

import java.io.Serializable;
import java.util.Date;

/**
 * @author liyb
 * @version $Id: TaskPlanCustomerDetailBean.java,v 0.1 2012-11-21 下午03:34:17 liyb Exp $
 */
public class TaskPlanCustomerDetailBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer           customerId;            //客户ID
    private Integer           taskPlanId;            //计划ID
    private Integer           executeUserId;         //执行者ID
    private Date              taskPlanDate;          //计划日期
    private Integer           isFinish;              //完成情况
    private String            remark;                //备注
    private Integer           taskPlanCount;         //关联任务
    private String            customerName;         //客户姓名
    private String            sex;                  //性别
    private String            customerTitle;        //称谓
    private Integer           age;                  //年龄
    protected Integer         defaultPhoneType;     //默认号码类型
    protected String          mobilePhone1;         //手机1
    protected String          mobilePhone2;         //手机2
    protected String          phone;                //固话
    protected String          fax;                  //传真
    private String            userName;             //客户归属
    private String            phoneNo;              //外部导入客户电话
    private String            customerNameExternal; //外部导入客户名称
    private Integer           isDel;                //客户是否删除
    
    
    public Integer getIsDel() {
        return isDel;
    }
    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
    public Integer getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    public Integer getExecuteUserId() {
        return executeUserId;
    }
    public void setExecuteUserId(Integer executeUserId) {
        this.executeUserId = executeUserId;
    }
    public Date getTaskPlanDate() {
        return taskPlanDate;
    }
    public void setTaskPlanDate(Date taskPlanDate) {
        this.taskPlanDate = taskPlanDate;
    }
    public Integer getIsFinish() {
        return isFinish;
    }
    public void setIsFinish(Integer isFinish) {
        this.isFinish = isFinish;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Integer getTaskPlanCount() {
        return taskPlanCount;
    }
    public void setTaskPlanCount(Integer taskPlanCount) {
        this.taskPlanCount = taskPlanCount;
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
    public String getCustomerTitle() {
        return customerTitle;
    }
    public void setCustomerTitle(String customerTitle) {
        this.customerTitle = customerTitle;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public Integer getDefaultPhoneType() {
        return defaultPhoneType;
    }
    public void setDefaultPhoneType(Integer defaultPhoneType) {
        this.defaultPhoneType = defaultPhoneType;
    }
    public String getMobilePhone1() {
        return mobilePhone1;
    }
    public void setMobilePhone1(String mobilePhone1) {
        this.mobilePhone1 = mobilePhone1;
    }
    public String getMobilePhone2() {
        return mobilePhone2;
    }
    public void setMobilePhone2(String mobilePhone2) {
        this.mobilePhone2 = mobilePhone2;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getFax() {
        return fax;
    }
    public void setFax(String fax) {
        this.fax = fax;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
	public Integer getTaskPlanId() {
		return taskPlanId;
	}
	public void setTaskPlanId(Integer taskPlanId) {
		this.taskPlanId = taskPlanId;
	}
    public String getPhoneNo() {
        return phoneNo;
    }
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
    public String getCustomerNameExternal() {
        return customerNameExternal;
    }
    public void setCustomerNameExternal(String customerNameExternal) {
        this.customerNameExternal = customerNameExternal;
    }
}
