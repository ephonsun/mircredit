/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :任务联系客户bean
 * Author     :liyb
 * Create Date:2012-7-16
 */
package com.banger.mobile.domain.model.task;

import java.io.Serializable;
import java.util.Date;

/**
 * @author liyb
 * @version $Id: TaskTargetCustomer.java,v 0.1 2012-7-16 下午03:35:21 liyb Exp $
 */
public class TaskTargetCustomer implements Serializable {

    private static final long serialVersionUID = -372177104826434042L;
    private Integer           taskId;                                 //任务ID
    private Integer           taskTargetId;                           //任务目标ID
    private Integer           isFinish;                               //是否已联系'如果是联系任务，如果勾选联系情况，则表示已完成。 1:已联系0:未联系'
    private Integer           commProgressId;                         //跟踪进度ID
    private Integer           customerId;                             //客户ID
    private String            customerNo;                             //客户编号
    private String            customerName;                           //客户姓名
    private String            customerTypeName;                       //客户类型
    private Integer           defaultPhoneType;                       //默认号码类型
    private String            mobilePhone1;                           //手机1
    private String            mobilePhone2;                           //手机2
    private String            phone;                                  //固话
    private String            fax;                                    //传真
    private String            sex;                                    //性别
    private String            customerTitle;                          //称谓
    private Integer           age;                                    //年龄
    private String            company;                                //单位
    private String            deptName;                               //归属机构
    private String            userName;                               //归属人员
    private String            executeName;                            //执行者
    private Integer           executeUserId;                          //执行者ID
    private Date              startDate;                              //联系记录时间
    private String            calltypeName;                           //联系类型
    private Integer           deptId;                                 //任务联系客户所属部门
    public Integer getTaskId() {
        return taskId;
    }
    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }
    public Integer getTaskTargetId() {
        return taskTargetId;
    }
    public void setTaskTargetId(Integer taskTargetId) {
        this.taskTargetId = taskTargetId;
    }
    public Integer getIsFinish() {
        return isFinish;
    }
    public void setIsFinish(Integer isFinish) {
        this.isFinish = isFinish;
    }
    public Integer getCommProgressId() {
        return commProgressId;
    }
    public void setCommProgressId(Integer commProgressId) {
        this.commProgressId = commProgressId;
    }
    public Integer getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
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
    public String getCustomerTypeName() {
        return customerTypeName;
    }
    public void setCustomerTypeName(String customerTypeName) {
        this.customerTypeName = customerTypeName;
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
    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }
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
    public String getExecuteName() {
        return executeName;
    }
    public void setExecuteName(String executeName) {
        this.executeName = executeName;
    }
    public Integer getExecuteUserId() {
        return executeUserId;
    }
    public void setExecuteUserId(Integer executeUserId) {
        this.executeUserId = executeUserId;
    }
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public String getCalltypeName() {
        return calltypeName;
    }
    public void setCalltypeName(String calltypeName) {
        this.calltypeName = calltypeName;
    }
    public Integer getDeptId() {
        return deptId;
    }
    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }
    
}
