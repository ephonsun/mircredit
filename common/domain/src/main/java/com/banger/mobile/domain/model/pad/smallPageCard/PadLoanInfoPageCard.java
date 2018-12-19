/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :贷款信息小页卡
 * Author     :zhangfp
 * Create Date:2013-3-20
 */
package com.banger.mobile.domain.model.pad.smallPageCard;

import java.util.List;

/**
 * @author Administrator
 * @version $Id: PadLoanInfoPageCard.java v 0.1 ${} 下午2:28 Administrator Exp $
 */
public class PadLoanInfoPageCard {
    private Integer totalCount;
    private List<PadLoanInfoCard> dataList;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<PadLoanInfoCard> getDataList() {
        return dataList;
    }

    public void setDataList(List<PadLoanInfoCard> dataList) {
        this.dataList = dataList;
    }
}
