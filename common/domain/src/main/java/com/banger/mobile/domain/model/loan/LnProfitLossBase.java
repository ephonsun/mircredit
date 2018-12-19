package com.banger.mobile.domain.model.loan;

import com.banger.mobile.domain.model.base.data.BaseDatCustomerData;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ygb on 2017/8/1.
 */
public class LnProfitLossBase extends BaseDatCustomerData implements Serializable {

    private static final long serialVersionUID = -1000L;
          private Date       beginTime;                               //开始时间
          private Date       endTime;                                 //结束时间
          private Integer    lastMonthRate;                           //最后月份百分比
          private Integer    monthNumber;                             //月数
          private String     grossRateRemark;                         //毛利率具体说明

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getLastMonthRate() {
        return lastMonthRate;
    }

    public void setLastMonthRate(Integer lastMonthRate) {
        this.lastMonthRate = lastMonthRate;
    }

    public Integer getMonthNumber() {
        return monthNumber;
    }

    public void setMonthNumber(Integer monthNumber) {
        this.monthNumber = monthNumber;
    }

    public String getGrossRateRemark() {
        return grossRateRemark;
    }

    public void setGrossRateRemark(String grossRateRemark) {
        this.grossRateRemark = grossRateRemark;
    }
}
