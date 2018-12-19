/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :hk
 * Create Date:Nov 29, 2012
 */
package com.banger.mobile.domain.model.record;

import java.io.Serializable;
import java.util.Date;

/**
 * @author hk 
 * @version $Id: RecordTemp.java,v 0.1 Nov 29, 2012 3:31:56 PM hk Exp $
 * 导入客户关联联系记录的临时实体
 */
public class RecordTemp implements Serializable {
    private static final long serialVersionUID = -1888537856405735357L;
    private Integer recordInfoId;
    private String customerName;
    private String remotePhone;
    private Date startDate;
    private String callTypeName;
    public Integer getRecordInfoId() {
        return recordInfoId;
    }
    public void setRecordInfoId(Integer recordInfoId) {
        this.recordInfoId = recordInfoId;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getRemotePhone() {
        return remotePhone;
    }
    public void setRemotePhone(String remotePhone) {
        this.remotePhone = remotePhone;
    }
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public String getCallTypeName() {
        return callTypeName;
    }
    public void setCallTypeName(String callTypeName) {
        this.callTypeName = callTypeName;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((callTypeName == null) ? 0 : callTypeName.hashCode());
        result = prime * result + ((customerName == null) ? 0 : customerName.hashCode());
        result = prime * result + ((recordInfoId == null) ? 0 : recordInfoId.hashCode());
        result = prime * result + ((remotePhone == null) ? 0 : remotePhone.hashCode());
        result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
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
        final RecordTemp other = (RecordTemp) obj;
        if (callTypeName == null) {
            if (other.callTypeName != null)
                return false;
        } else if (!callTypeName.equals(other.callTypeName))
            return false;
        if (customerName == null) {
            if (other.customerName != null)
                return false;
        } else if (!customerName.equals(other.customerName))
            return false;
        if (recordInfoId == null) {
            if (other.recordInfoId != null)
                return false;
        } else if (!recordInfoId.equals(other.recordInfoId))
            return false;
        if (remotePhone == null) {
            if (other.remotePhone != null)
                return false;
        } else if (!remotePhone.equals(other.remotePhone))
            return false;
        if (startDate == null) {
            if (other.startDate != null)
                return false;
        } else if (!startDate.equals(other.startDate))
            return false;
        return true;
    }
    public RecordTemp(Integer recordInfoId, String customerName, String remotePhone,
                      Date startDate, String callTypeName) {
        super();
        this.recordInfoId = recordInfoId;
        this.customerName = customerName;
        this.remotePhone = remotePhone;
        this.startDate = startDate;
        this.callTypeName = callTypeName;
    }
    public RecordTemp() {
        super();
    }
    
}
