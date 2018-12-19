package com.banger.mobile.domain.model.loan;

import com.banger.mobile.domain.model.base.data.BaseDatCustomerData;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by ygb on 2017/8/3.
 */
public class LnProfitLossDetail extends BaseDatCustomerData implements Serializable {

    private Integer detailId;
    private Integer itemId;
    private String month;
    private BigDecimal amount;
    private String remark;
    private Integer sortNo;


    public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }
}
