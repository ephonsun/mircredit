package com.banger.mobile.webservice.domain;

import com.banger.mobile.domain.model.points.JfMyCustomer;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yangy
 * Date: 13-8-22
 * Time: 下午5:15
 * To change this template use File | Settings | File Templates.
 */
public class JfMyCustomerPage {

    private   List<JfMyCustomer> dataList;
    private Integer totalCount;

    public List<JfMyCustomer> getDataList() {
        return dataList;
    }

    public void setDataList(List<JfMyCustomer> dataList) {
        this.dataList = dataList;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
}
