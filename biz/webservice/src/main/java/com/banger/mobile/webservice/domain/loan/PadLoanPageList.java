package com.banger.mobile.webservice.domain.loan;

import java.util.List;

public class PadLoanPageList {
	private List<BaseLoanListInfo> dataList;
    private Integer pageCount;

    public List<BaseLoanListInfo> getDataList() {
        return dataList;
    }

    public void setDataList(List<BaseLoanListInfo> dataList) {
        this.dataList = dataList;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }
}
