package com.banger.mobile.webservice.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-3-20
 * Time: 下午3:01
 * To change this template use File | Settings | File Templates.
 */
public class CustomerTaskInfoPage implements Serializable {
    private List<CustomerTaskInfo> dataList;
    private Integer totalCount;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<CustomerTaskInfo> getDataList() {
        return dataList;
    }

    public void setDataList(List<CustomerTaskInfo> dataList) {
        this.dataList = dataList;
    }
}
