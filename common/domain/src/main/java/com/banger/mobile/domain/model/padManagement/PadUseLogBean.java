/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :liyb
 * Create Date:2013-6-21
 */
package com.banger.mobile.domain.model.padManagement;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author liyb
 * @version $Id: PadUseLogBean.java,v 0.1 2013-6-21 下午02:46:46 liyb Exp $
 */
public class PadUseLogBean implements Serializable{

    private static final long serialVersionUID = -8343204913969430746L;

    private Integer           padInfoId;                             //PAD设备ID
    private String            userName;                              //操作人
    private Integer           logType;                               //登录类型
    private Date              loginDate;                             //登录时间
    private BigDecimal        countUpload;                           //上传总流量
    private BigDecimal        countDownLoad;                         //下载总流量
    public Integer getPadInfoId() {
        return padInfoId;
    }
    public void setPadInfoId(Integer padInfoId) {
        this.padInfoId = padInfoId;
    }
    public Date getLoginDate() {
        return loginDate;
    }
    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }
    public BigDecimal getCountUpload() {
        return countUpload;
    }
    public void setCountUpload(BigDecimal countUpload) {
        this.countUpload = countUpload;
    }
    public BigDecimal getCountDownLoad() {
        return countDownLoad;
    }
    public void setCountDownLoad(BigDecimal countDownLoad) {
        this.countDownLoad = countDownLoad;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public Integer getLogType() {
        return logType;
    }
    public void setLogType(Integer logType) {
        this.logType = logType;
    }
    
}
