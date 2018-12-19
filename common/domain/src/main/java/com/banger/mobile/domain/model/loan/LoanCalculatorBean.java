package com.banger.mobile.domain.model.loan;

import com.banger.mobile.domain.model.base.loan.BaseLnRepaymentPlan;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Zhangfp
 * Date: 13-2-5
 * Time: 下午3:47
 * To change this template use File | Settings | File Templates.
 *
 * 还款计划
 */
public class LoanCalculatorBean implements Serializable {
    //当前期数
    private int numPeriod;
    //月供金额
    private double numMonthPay;
    //月供本金
    private double numMonthCapital;
    //月供利息
    private double numMonthInterest;
    //剩余本金
    private double numLeftCapital;

    public int getNumPeriod() {
        return numPeriod;
    }

    public void setNumPeriod(int numPeriod) {
        this.numPeriod = numPeriod;
    }

    public double getNumMonthPay() {
        return numMonthPay;
    }

    public void setNumMonthPay(double numMonthPay) {
        this.numMonthPay = numMonthPay;
    }

    public double getNumMonthCapital() {
        return numMonthCapital;
    }

    public void setNumMonthCapital(double numMonthCapital) {
        this.numMonthCapital = numMonthCapital;
    }

    public double getNumMonthInterest() {
        return numMonthInterest;
    }

    public void setNumMonthInterest(double numMonthInterest) {
        this.numMonthInterest = numMonthInterest;
    }

    public double getNumLeftCapital() {
        return numLeftCapital;
    }

    public void setNumLeftCapital(double numLeftCapital) {
        this.numLeftCapital = numLeftCapital;
    }

    @Override
    public String toString() {
        return "LoanCalculatorBean{" +
                "numPeriod=" + numPeriod +
                ", numMonthPay=" + numMonthPay +
                ", numMonthCapital=" + numMonthCapital +
                ", numMonthInterest=" + numMonthInterest +
                ", numLeftCapital=" + numLeftCapital +
                '}';
    }
}
