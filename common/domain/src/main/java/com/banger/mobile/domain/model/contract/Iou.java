package com.banger.mobile.domain.model.contract;

import com.banger.mobile.domain.model.loan.LnLoanInfo;
import com.banger.mobile.domain.model.loan.LnPledge;
import com.banger.mobile.domain.model.loan.LnRepaymentPlan;
import edu.emory.mathcs.backport.java.util.Arrays;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 合同 主表
 */
public class Iou extends BaseLnContract implements Serializable {
    private static final long serialVersionUID = 1269170432131108007L;


    private String currency="人民币";//币种
    private String productCode;//产品号
    private String code;//编号
    private String cusName;//借款人
    private String cusIdCard;//身份证或法人证码
    private String loanType;//借款种类
    private String loanPurpose;//借款用途
    private String loanBankAccount;//贷款账户账号
    private String depositBankAccount;//存款账户账号
    private String amountWords;//借款金额.大写
    private String amountFigures;//借款金额.小写
    private Date loanBeginTime;//借款日期.开始
    private Date loanEndTime;//借款日期.结束
    private String repaymentType;//还款方式

    private List<RepaymentInfo> repaymentInfoList;
    private String contractNo;//合同号
    private String reviewOpinion;//银行审查意见



    public Iou(LnLoanInfo lnLoanInfo,  List<LnRepaymentPlan> repaymentPlans) {
        PledgorInfo pledgorInfo;
        Calendar ca = Calendar.getInstance();

        StringBuffer sbf = new StringBuffer("");

        SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日");
        RepaymentInfo repaymentInfo;
        int i=1;

        repaymentInfoList=new ArrayList<RepaymentInfo>();
        if(!CollectionUtils.isEmpty(repaymentPlans)){

          /*  Integer[]  po=new Integer[] {5,15,20,21};
            List<Integer> list = Arrays.asList(po);
            Boolean io=true;*/
            this.setLoanBeginTime(repaymentPlans.get(0).getPlanDate());
            for(LnRepaymentPlan bean : repaymentPlans ){
                repaymentInfo=new RepaymentInfo();
                pledgorInfo = new PledgorInfo();
                repaymentInfo.setPlanAmount(lnLoanInfo.getResultMoney());
                repaymentInfo.setPlanDate(sf.format(bean.getPlanDate()));
                repaymentInfo.setRepaymentDate(sf.format(bean.getPlanDate()));
                this.repaymentInfoList.add(repaymentInfo);
                i++;
            }
            this.setLoanEndTime(repaymentPlans.get(repaymentPlans.size()-1).getPlanDate());
        }
        this.setCusIdCard(lnLoanInfo.getCusIdcard());

        this.setLoanPurpose("1".equals(lnLoanInfo.getResultPurpose())?"个人经营":"个人消费");
        this.setAmountFigures(lnLoanInfo.getResultMoney());

        this.setContractNo(lnLoanInfo.getLendContractNum());
        if( lnLoanInfo.getAdviceRepayWayId().equals("1")){
            this.setRepaymentType("等额本息");
        } if( lnLoanInfo.getAdviceRepayWayId().equals("2")){
            this.setRepaymentType("等额本金");
        } if( lnLoanInfo.getAdviceRepayWayId().equals("3")){
            this.setRepaymentType("按月还息，到期还本");
        } if( lnLoanInfo.getAdviceRepayWayId().equals("4")){
            this.setRepaymentType("约定还款");
        } if( lnLoanInfo.getAdviceRepayWayId().equals("5")){
            this.setRepaymentType("随借随还");
        }

        if(lnLoanInfo.getAppLoanTypeId().equals("1")){
            this.setLoanType("经营贷");
        }
        if(lnLoanInfo.getAppLoanTypeId().equals("2")){
            this.setLoanType("经营贷");
        }

        if(StringUtils.isNotBlank(lnLoanInfo.getLendContractNum())){
            this.setContractNo(lnLoanInfo.getLendContractNum());;//合同号
        }
//        this.setLoanUserName(lnLoanInfo.getCusName());
            this.setCusName(lnLoanInfo.getCusName());
            this.setCusIdCard(lnLoanInfo.getCusIdcard());
            this.setAmountFigures(lnLoanInfo.getResultMoney());
            this.setContractNo(lnLoanInfo.getLendContractNum());
            this.setLoanPurpose("1".equals(lnLoanInfo.getResultPurpose())?"个人经营":"个人消费");
        this.setLoanBeginTime(lnLoanInfo.getRegisterLoanDate());



    }



    public Iou() {

    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getCusName() {
        return cusName;
    }

    @Override
    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    @Override
    public String getCusIdCard() {
        return cusIdCard;
    }

    @Override
    public void setCusIdCard(String cusIdCard) {
        this.cusIdCard = cusIdCard;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public String getLoanPurpose() {
        return loanPurpose;
    }

    public void setLoanPurpose(String loanPurpose) {
        this.loanPurpose = loanPurpose;
    }

    public String getLoanBankAccount() {
        return loanBankAccount;
    }

    public void setLoanBankAccount(String loanBankAccount) {
        this.loanBankAccount = loanBankAccount;
    }

    public String getDepositBankAccount() {
        return depositBankAccount;
    }

    public void setDepositBankAccount(String depositBankAccount) {
        this.depositBankAccount = depositBankAccount;
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

    public String getRepaymentType() {
        return repaymentType;
    }

    public void setRepaymentType(String repaymentType) {
        this.repaymentType = repaymentType;
    }

    public List<RepaymentInfo> getRepaymentInfoList() {
        return repaymentInfoList;
    }

    public void setRepaymentInfoList(List<RepaymentInfo> repaymentInfoList) {
        this.repaymentInfoList = repaymentInfoList;
    }

    @Override
    public String getContractNo() {
        return contractNo;
    }

    @Override
    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getReviewOpinion() {
        return reviewOpinion;
    }

    public void setReviewOpinion(String reviewOpinion) {
        this.reviewOpinion = reviewOpinion;
    }
}
