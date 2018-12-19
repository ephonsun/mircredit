package com.banger.mobile.domain.model.pad;

import java.io.Serializable;
import java.util.List;

/**
 * User: xiall
 * Date: 2016-1-4
 * To change this template use File | Settings | File Templates.
 */
public class PadLoanPageList implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 4224926329256721199L;
	private List<PadLoan> dataList;
    private Integer pageCount;

    public List<PadLoan> getDataList() {
        return dataList;
    }

    public void setDataList(List<PadLoan> dataList) {
        this.dataList = dataList;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }
}
