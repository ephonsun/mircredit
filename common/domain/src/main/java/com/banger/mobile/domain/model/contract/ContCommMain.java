package com.banger.mobile.domain.model.contract;

import com.banger.mobile.domain.model.loan.LnLoanInfo;
import com.banger.mobile.domain.model.loan.LnRepaymentPlan;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 合同 主表
 */
public class ContCommMain extends BaseLnContract implements Serializable {
    private static final long serialVersionUID = 1269170432131108007L;


    private String currency="人民币";//借款金额.币种
    private String amountWords;//借款金额.大写
    private String amountFigures;//借款金额.小写
    private Date loanBeginTime;//借款期限.开始
    private Date loanEndTime;//借款期限.结束
    private String loanPurpose;//贷款用途
    private String interestType;//结息方式
    private String interestDay;//结息日
    private String loanRepaymentType;//还本方式
    private String loanMonthRepayment;//按月还款方式
    private String loanUserRepayment;//自定义还款计划
    private String bankDeposit;//指定账户.开户行
    private String bankAccountName;//指定账户.户名
    private String bankAccount;//指定账户.账号
    private String guarantyType;//担保方式



    public ContCommMain( LnLoanInfo lnLoanInfo, List<LnRepaymentPlan> repaymentPlans) {

        if(null!=lnLoanInfo){
            this.setCusName(lnLoanInfo.getCusName());;//借款人
            this.setCusAddress(lnLoanInfo.getCusAddress());//住所地
            this.setCusIdCard(lnLoanInfo.getCusIdcard());//身份证号码
            this.amountFigures=lnLoanInfo.getResultMoney();//借款金额.小写
            this.loanPurpose=lnLoanInfo.getAppLoanPurpose();//贷款用途
            if(StringUtils.isNotBlank(lnLoanInfo.getLendContractNum())){
                this.setContractNo(lnLoanInfo.getLendContractNum());;//合同号
            }
            this.setGuarantyType(lnLoanInfo.getAppGuarantorWayId());

        }
        if(!CollectionUtils.isEmpty(repaymentPlans)){
            StringBuffer sf = new StringBuffer("");
            for (LnRepaymentPlan plan : repaymentPlans) {
                sf.append(plan.getPlanDate()).append("应还本金：").append(plan.getPrincipal()).append("\\r");
            }
        }

    }

    public ContCommMain() {

    }



    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAmountWords() {
        return amountWords;
    }

    public void setAmountWords(String amountWords) {
        this.amountWords = amountWords;
    }

    public String getAmountFigures() {
        return amountFigures;
    }

    public void setAmountFigures(String amountFigures) {
        this.amountFigures = amountFigures;
    }

    public Date getLoanBeginTime() {
        return loanBeginTime;
    }

    public void setLoanBeginTime(Date loanBeginTime) {
        this.loanBeginTime = loanBeginTime;
    }

    public Date getLoanEndTime() {
        return loanEndTime;
    }

    public void setLoanEndTime(Date loanEndTime) {
        this.loanEndTime = loanEndTime;
    }

    public String getLoanPurpose() {
        return loanPurpose;
    }

    public void setLoanPurpose(String loanPurpose) {
        this.loanPurpose = loanPurpose;
    }

    public String getInterestType() {
        return interestType;
    }

    public void setInterestType(String interestType) {
        this.interestType = interestType;
    }

    public String getInterestDay() {
        return interestDay;
    }

    public void setInterestDay(String interestDay) {
        this.interestDay = interestDay;
    }

    public String getLoanRepaymentType() {
        return loanRepaymentType;
    }

    public void setLoanRepaymentType(String loanRepaymentType) {
        this.loanRepaymentType = loanRepaymentType;
    }

    public String getLoanMonthRepayment() {
        return loanMonthRepayment;
    }

    public void setLoanMonthRepayment(String loanMonthRepayment) {
        this.loanMonthRepayment = loanMonthRepayment;
    }

    public String getLoanUserRepayment() {
        return loanUserRepayment;
    }

    public void setLoanUserRepayment(String loanUserRepayment) {
        this.loanUserRepayment = loanUserRepayment;
    }

    public String getBankDeposit() {
        return bankDeposit;
    }

    public void setBankDeposit(String bankDeposit) {
        this.bankDeposit = bankDeposit;
    }

    public String getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getGuarantyType() {
        return guarantyType;
    }

    public void setGuarantyType(String guarantyType) {
        this.guarantyType = guarantyType;
    }



}
