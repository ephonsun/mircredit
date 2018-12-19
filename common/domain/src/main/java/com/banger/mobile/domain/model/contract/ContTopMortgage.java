package com.banger.mobile.domain.model.contract;

import com.banger.mobile.domain.model.loan.LnLoanInfo;
import com.banger.mobile.domain.model.loan.LnPledge;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 担保主表
 */
public class ContTopMortgage extends BaseLnContract implements Serializable {
    private static final long serialVersionUID = 1269170432131108007L;
    private String code ;//编号
    private String mortgagorNames;//抵押人
//    private String creditNo ;//统一社会信用代码
    private List<MortgagorInfo> mortgagorInfoList;//一组信息为一个抵押人信息，可以有多个抵押人。
    private String mortgageAmountCurrency ="人民币";//抵押担保金额.币种
    private String mortgageAmountWords;//抵押担保金额.大写
    private String mortgageAmountFigures;//抵押担保金额.小写
    private String mortgageValueCurrency="人民币";//抵押物价值.币种
    private String mortgageValueWords;//抵押物价值.大写
    private String mortgageLeaseStatus;//抵押物的租赁情况
    private List<MortgageGoodsInfo> mortgageGoodsInfoList;//抵押物列表信息，多条网格显示
    private Date mortgageBeginTime;//抵押时间.开始
    private Date mortgageEndTime;//抵押时间.结束
    private String principalAmountWords;//本金余额最高不超过.大写
    private String loanAmountWords;//最高债权额.大写
    private String loanAmountFigures;//最高债权额.小写
    private String mortgagorContractNum;//抵押人份数
    private String loanContractNum;//抵押权人份数
    private String otherContractNum;//其余份数
    private String principalCurrency="人民币";//本金余额上限金额.币种
    private String principalAmountFigures;//本金余额上限金额.小写

    public ContTopMortgage(){

    }
    public ContTopMortgage(LnLoanInfo lnLoanInfo, List<LnPledge> lnPledgeList) {


        MortgagorInfo mortgagorInfo;
        MortgageGoodsInfo mortgageGoodsInfo;
        StringBuffer sbf = new StringBuffer("");
        int i=1;
        SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日");
        mortgagorInfoList=new ArrayList<MortgagorInfo>();
        mortgageGoodsInfoList=new ArrayList<MortgageGoodsInfo>();
        if(!CollectionUtils.isEmpty(lnPledgeList)){
            for(LnPledge bean : lnPledgeList ){

                mortgagorInfo = new MortgagorInfo();
                mortgageGoodsInfo=new MortgageGoodsInfo();
                if(StringUtils.isNotBlank(bean.getOwnerName())){
                    mortgagorInfo.setMortgagorName(bean.getOwnerName());
                    mortgagorInfo.setMortgagorCorporation(bean.getOwnerName());
                }
               /* if(StringUtils.isNotBlank(lnLoanInfo.getCusIdtypeId())){
                    mortgagorInfo.setMortgagorCreditNo(lnLoanInfo.getCusIdtypeId());
                }
                if(StringUtils.isNotBlank(lnLoanInfo.getCusLivingAddress())){
                    mortgagorInfo.setMortgagorAddress(lnLoanInfo.getCusLivingAddress());
                    mortgagorInfo.setMortgagorSendAddress(lnLoanInfo.getCusLivingAddress());

                }
                if(StringUtils.isNotBlank(lnLoanInfo.getBizLeaglMobilePhone())){
                    mortgagorInfo.setMortgagorPhoneNum(lnLoanInfo.getBizLeaglMobilePhone());
                }*/
                if(StringUtils.isNotBlank(String.valueOf(bean.getPledgeId()))){
                    mortgageGoodsInfo.setGoodsNo(String.valueOf(i));
                }
                if(StringUtils.isNotBlank(bean.getTitleCertificate())){
                    mortgageGoodsInfo.setGoodsOwnershipNo(bean.getTitleCertificate());
                }
                if(StringUtils.isNotBlank(bean.getTitleCertificate())){
                    mortgageGoodsInfo.setGoodsValue(bean.getGoodsValue());
                }
                mortgageGoodsInfo.setLeaseBeginDate(sf.format(bean.getCreateDate()));
                mortgageGoodsInfo.setGoodsType(bean.getGoods());




                this.mortgagorInfoList.add(mortgagorInfo);
                this.mortgageGoodsInfoList.add(mortgageGoodsInfo);
                    sbf.append(bean.getOwnerName()).append("，");


//                this.setCusName(this.getCusName());
                i++;
            }
            this.setMortgagorNames(sbf.substring(0, sbf.length() - 1).toString());


        }
        if(StringUtils.isNotBlank(lnLoanInfo.getLendContractNum())){
            this.setContractNo(lnLoanInfo.getLendContractNum());;//合同号
        }
//        this.setLoanUserName(lnLoanInfo.getCusName());
        this.setCusName(lnLoanInfo.getCusName());


//        String str =lnLoanInfo.getCusName();
    }


    public String getPrincipalCurrency() {
        return principalCurrency;
    }

    public void setPrincipalCurrency(String principalCurrency) {
        this.principalCurrency = principalCurrency;
    }

    public String getPrincipalAmountFigures() {
        return principalAmountFigures;
    }

    public void setPrincipalAmountFigures(String principalAmountFigures) {
        this.principalAmountFigures = principalAmountFigures;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMortgagorNames() {
        return mortgagorNames;
    }

    public void setMortgagorNames(String mortgagorNames) {
        this.mortgagorNames = mortgagorNames;
    }

//    public String getCreditNo() {
//        return creditNo;
//    }
//
//    public void setCreditNo(String creditNo) {
//        this.creditNo = creditNo;
//    }

    public List<MortgagorInfo> getMortgagorInfoList() {
        return mortgagorInfoList;
    }

    public void setMortgagorInfoList(List<MortgagorInfo> mortgagorInfoList) {
        this.mortgagorInfoList = mortgagorInfoList;
    }

    public String getMortgageAmountCurrency() {
        return mortgageAmountCurrency;
    }

    public void setMortgageAmountCurrency(String mortgageAmountCurrency) {
        this.mortgageAmountCurrency = mortgageAmountCurrency;
    }

    public String getMortgageAmountWords() {
        return mortgageAmountWords;
    }

    public void setMortgageAmountWords(String mortgageAmountWords) {
        this.mortgageAmountWords = mortgageAmountWords;
    }

    public String getMortgageAmountFigures() {
        return mortgageAmountFigures;
    }

    public void setMortgageAmountFigures(String mortgageAmountFigures) {
        this.mortgageAmountFigures = mortgageAmountFigures;
    }

    public String getMortgageValueCurrency() {
        return mortgageValueCurrency;
    }

    public void setMortgageValueCurrency(String mortgageValueCurrency) {
        this.mortgageValueCurrency = mortgageValueCurrency;
    }

    public String getMortgageValueWords() {
        return mortgageValueWords;
    }

    public void setMortgageValueWords(String mortgageValueWords) {
        this.mortgageValueWords = mortgageValueWords;
    }

    public String getMortgageLeaseStatus() {
        return mortgageLeaseStatus;
    }

    public void setMortgageLeaseStatus(String mortgageLeaseStatus) {
        this.mortgageLeaseStatus = mortgageLeaseStatus;
    }

    public List<MortgageGoodsInfo> getMortgageGoodsInfoList() {
        return mortgageGoodsInfoList;
    }

    public void setMortgageGoodsInfoList(List<MortgageGoodsInfo> mortgageGoodsInfoList) {
        this.mortgageGoodsInfoList = mortgageGoodsInfoList;
    }

    public Date getMortgageBeginTime() {
        return mortgageBeginTime;
    }

    public void setMortgageBeginTime(Date mortgageBeginTime) {
        this.mortgageBeginTime = mortgageBeginTime;
    }

    public Date getMortgageEndTime() {
        return mortgageEndTime;
    }

    public void setMortgageEndTime(Date mortgageEndTime) {
        this.mortgageEndTime = mortgageEndTime;
    }

    public String getPrincipalAmountWords() {
        return principalAmountWords;
    }

    public void setPrincipalAmountWords(String principalAmountWords) {
        this.principalAmountWords = principalAmountWords;
    }

    public String getLoanAmountWords() {
        return loanAmountWords;
    }

    public void setLoanAmountWords(String loanAmountWords) {
        this.loanAmountWords = loanAmountWords;
    }

    public String getLoanAmountFigures() {
        return loanAmountFigures;
    }

    public void setLoanAmountFigures(String loanAmountFigures) {
        this.loanAmountFigures = loanAmountFigures;
    }

    public String getMortgagorContractNum() {
        return mortgagorContractNum;
    }

    public void setMortgagorContractNum(String mortgagorContractNum) {
        this.mortgagorContractNum = mortgagorContractNum;
    }

    public String getLoanContractNum() {
        return loanContractNum;
    }

    public void setLoanContractNum(String loanContractNum) {
        this.loanContractNum = loanContractNum;
    }

    public String getOtherContractNum() {
        return otherContractNum;
    }

    public void setOtherContractNum(String otherContractNum) {
        this.otherContractNum = otherContractNum;
    }
}
