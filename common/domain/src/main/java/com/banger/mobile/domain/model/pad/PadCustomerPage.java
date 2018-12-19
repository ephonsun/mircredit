package com.banger.mobile.domain.model.pad;

import com.banger.mobile.domain.model.loan.LnLoanCustomerBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: yuanme Date: 13-3-1 Time: 下午2:29 To change
 * this template use File | Settings | File Templates.
 */
public class PadCustomerPage implements Serializable {
    private List<LnLoanCustomerBean> dataList;
    private Integer               pageCount;

    public List<LnLoanCustomerBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<LnLoanCustomerBean> dataList) {
        this.dataList = dataList;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }
}
