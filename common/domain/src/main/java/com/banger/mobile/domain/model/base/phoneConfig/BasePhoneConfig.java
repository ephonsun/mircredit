/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :通话基础配置实体基类
 * Author     :yujh
 * Create Date:Jun 1, 2012
 */
package com.banger.mobile.domain.model.base.phoneConfig;

import java.io.Serializable;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author yujh
 * @version $Id: BasePhoneConfig.java,v 0.1 Jun 1, 2012 9:16:48 AM Administrator Exp $
 */
public class BasePhoneConfig extends  BaseObject implements Serializable{

    private static final long serialVersionUID = -5232136005293571677L;
    
    private     Integer         phoneConfigId;          //主键id
    private     Integer         userId;                 //用户id
    private     String          cityCode;               //本地区号
    private     String          outsideCallCode;        //外线加拨号
    private     String          insiseExtLength;        //内线分机号位数
    private     Integer         isIpCall;               //是否允许长途使用
    private     String          ipNumber;               //ip前缀号码
    private     Double          outDelay;               //出局延时
    private     Integer         flashBreakTime;         //闪断时间
    private     String          outNumber;              //来电出局号
    private     Integer         isPopUp;                //是否自动弹出通话窗口
    private     Integer         isShowLastWindow;       //是否弹出上次联系窗口
    private     Integer         callOverPopUp;          //通话结束时弹出窗口处理方式
    private     Integer         isScreamWord;           //是否开启屏幕取词
    public Integer getPhoneConfigId() {
        return phoneConfigId;
    }
    public void setPhoneConfigId(Integer phoneConfigId) {
        this.phoneConfigId = phoneConfigId;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getCityCode() {
        return cityCode;
    }
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }
    public String getOutsideCallCode() {
        return outsideCallCode;
    }
    public void setOutsideCallCode(String outsideCallCode) {
        this.outsideCallCode = outsideCallCode;
    }
    public String getInsiseExtLength() {
        return insiseExtLength;
    }
    public void setInsiseExtLength(String insiseExtLength) {
        this.insiseExtLength = insiseExtLength;
    }
    public Integer getIsIpCall() {
        return isIpCall;
    }
    public void setIsIpCall(Integer isIpCall) {
        this.isIpCall = isIpCall;
    }
    public String getIpNumber() {
        return ipNumber;
    }
    public void setIpNumber(String ipNumber) {
        this.ipNumber = ipNumber;
    }
    public Double getOutDelay() {
        return outDelay;
    }
    public void setOutDelay(Double outDelay) {
        this.outDelay = outDelay;
    }
    public Integer getFlashBreakTime() {
        return flashBreakTime;
    }
    public void setFlashBreakTime(Integer flashBreakTime) {
        this.flashBreakTime = flashBreakTime;
    }
    public String getOutNumber() {
        return outNumber;
    }
    public void setOutNumber(String outNumber) {
        this.outNumber = outNumber;
    }
    public Integer getIsPopUp() {
        return isPopUp;
    }
    public void setIsPopUp(Integer isPopUp) {
        this.isPopUp = isPopUp;
    }
    public Integer getIsShowLastWindow() {
        return isShowLastWindow;
    }
    public void setIsShowLastWindow(Integer isShowLastWindow) {
        this.isShowLastWindow = isShowLastWindow;
    }
    public Integer getCallOverPopUp() {
        return callOverPopUp;
    }
    public void setCallOverPopUp(Integer callOverPopUp) {
        this.callOverPopUp = callOverPopUp;
    }
    public Integer getIsScreamWord() {
        return isScreamWord;
    }
    public void setIsScreamWord(Integer isScreamWord) {
        this.isScreamWord = isScreamWord;
    }
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof BasePhoneConfig)) {
            return false;
        }
        BasePhoneConfig rhs = (BasePhoneConfig) object;
        return new EqualsBuilder().appendSuper(super.equals(object)).append(this.insiseExtLength,
            rhs.insiseExtLength).append(this.outDelay, rhs.outDelay)
            .append(this.userId, rhs.userId).append(this.outsideCallCode, rhs.outsideCallCode)
            .append(this.isShowLastWindow, rhs.isShowLastWindow).append(this.phoneConfigId,
                rhs.phoneConfigId).append(this.flashBreakTime, rhs.flashBreakTime).append(
                this.isPopUp, rhs.isPopUp).append(this.ipNumber, rhs.ipNumber).append(
                this.isScreamWord, rhs.isScreamWord).append(this.isIpCall, rhs.isIpCall).append(
                this.outNumber, rhs.outNumber).append(this.callOverPopUp, rhs.callOverPopUp)
            .append(this.cityCode, rhs.cityCode).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1260689355, -1330093871).appendSuper(super.hashCode()).append(
            this.insiseExtLength).append(this.outDelay).append(this.userId).append(
            this.outsideCallCode).append(this.isShowLastWindow).append(this.phoneConfigId).append(
            this.flashBreakTime).append(this.isPopUp).append(this.ipNumber).append(
            this.isScreamWord).append(this.isIpCall).append(this.outNumber).append(
            this.callOverPopUp).append(this.cityCode).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("startRow", this.getStartRow()).append("outDelay",
            this.outDelay).append("isScreamWord", this.isScreamWord).append("ipNumber",
            this.ipNumber).append("endRow", this.getEndRow()).append("phoneConfigId",
            this.phoneConfigId).append("flashBreakTime", this.flashBreakTime).append("isIpCall",
            this.isIpCall).append("outsideCallCode", this.outsideCallCode).append("isPopUp",
            this.isPopUp).append("cityCode", this.cityCode).append("insiseExtLength",
            this.insiseExtLength).append("callOverPopUp", this.callOverPopUp).append("outNumber",
            this.outNumber).append("isShowLastWindow", this.isShowLastWindow).append("userId",
            this.userId).toString();
    }
    
}
