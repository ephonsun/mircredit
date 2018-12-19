/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :huangk
 * Create Date:2013-3-22
 */
package com.banger.mobile.webservice.domain;

import java.util.List;

/**
 * @author huangk
 * @version $Id: CustomerDataPage.java,v 0.1 2013-3-22 上午11:26:55 huangk Exp $
 */
public class CustomerDataPage {
    private List<CustomerDataInfo> dataList;
    private Integer totalCount;
    public List<CustomerDataInfo> getDataList() {
        return dataList;
    }
    public void setDataList(List<CustomerDataInfo> dataList) {
        this.dataList = dataList;
    }
    public Integer getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
    
    
}
