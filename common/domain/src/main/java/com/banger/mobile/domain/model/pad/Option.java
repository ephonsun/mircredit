/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yuanme
 * Create Date:2012-7-18
 */
package com.banger.mobile.domain.model.pad;

/**
 * @author yuanme
 * @version Option.java,v 0.1 2012-7-18 上午11:31:28
 */
public class Option {
    private Integer optionId;
    private String  sortno;
    private String  optionDetail;

    public Integer getOptionId() {
        return optionId;
    }

    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
    }

    public String getSortno() {
        return sortno;
    }

    public void setSortno(String sortno) {
        this.sortno = sortno;
    }

    public String getOptionDetail() {
        return optionDetail;
    }

    public void setOptionDetail(String optionDetail) {
        this.optionDetail = optionDetail;
    }
}
