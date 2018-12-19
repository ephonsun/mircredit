/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :huangk
 * Create Date:2013-3-21
 */
package com.banger.mobile.webservice.domain;

import java.util.List;

/**
 * @author huangk
 * @version $Id: CustomerRecordInfo.java,v 0.1 2013-3-21 下午3:48:54 huangk Exp $
 */
public class CustomerRecordPage {
    private List<CustomerRecordInfo> dataList;
    private Integer totalCount;
    public List<CustomerRecordInfo> getDataList() {
        return dataList;
    }
    public void setDataList(List<CustomerRecordInfo> dataList) {
        this.dataList = dataList;
    }
    public Integer getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
    
    
}
