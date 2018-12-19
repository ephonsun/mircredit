/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :客户地理位置记录实体...
 * Author     :yangy
 * Create Date:2013-3-13
 */
package com.banger.mobile.domain.model.map;

import org.apache.commons.lang.builder.HashCodeBuilder;

import com.banger.mobile.domain.model.base.map.BaseMapCustomerGps;

/**
 * @author yangyang
 * @version $Id: MapCustomerGps.java,v 0.1 2013-3-13 下午2:18:06 yangyong Exp $
 */
public class MapCustomerGps extends BaseMapCustomerGps {

    private static final long serialVersionUID = 8846473043901993749L;
    //客户姓名、称谓、联系电话、客户地址、归属人员、贷款状态，如果客户没有归属人员，则不显示客户归属，如果客户没有贷款，不显示贷款状态
    private String customerName;			//客户姓名
    private String customerTitle;			//称谓
    private String address;					//地址
    private String phoneNo;                 //联系电话
    private String userName;                //归属人员
    private String loanStatus;              //贷款状态
    private Integer isNogood; //是否不良客户

    //追加日程信息
    private String scheduleInfo;
    private Integer scheduleStatus;

    public String getScheduleInfo() {
        return scheduleInfo;
    }

    public void setScheduleInfo(String scheduleInfo) {
        this.scheduleInfo = scheduleInfo;
    }

    public Integer getScheduleStatus() {
        return scheduleStatus;
    }

    public void setScheduleStatus(Integer scheduleStatus) {
        this.scheduleStatus = scheduleStatus;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerTitle() {
        return customerTitle;
    }

    public void setCustomerTitle(String customerTitle) {
        this.customerTitle = customerTitle;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1190071947, -875609599).appendSuper(super.hashCode())
            .toHashCode();
    }
    
    public MapCustomerGps() {
        super();
    }

    public Integer getIsNogood() {
        return isNogood;
    }

    public void setIsNogood(Integer isNogood) {
        this.isNogood = isNogood;
    }
}
