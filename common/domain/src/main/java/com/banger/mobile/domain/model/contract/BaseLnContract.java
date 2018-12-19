package com.banger.mobile.domain.model.contract;

import com.banger.mobile.domain.model.base.BaseObject;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import java.io.Serializable;
import java.util.Date;

/**
 * 合同内容基础信息
 */
public class BaseLnContract extends BaseObject implements Serializable {
    private static final long serialVersionUID = -3697413258419771196L;
    private Integer loanId;
    private String contractNo;
    private String loanUserName = "中山农村商业银行股份有限公司";//贷款人
    private String loanCorporation = "马英文";//法人代表/负责人
    private String loanAddress = "中山市东区中山三路26号之一";//住所地
    private String cusName;//借款人
    private String cusAddress;//住所地
    private String cusIdCard;//身份证号码
    private String otherAgree;//其他约定
    private String contractNum="3";//合同份数
    private Date contractTime;//签约时间
    private String contractPlace;//签约地点
    private String settleDispute;//合同争议解决方式

    private String creditNo="914420006633583105";//统一社会信用代码

    public String getCreditNo() {
        return creditNo;
    }

    public void setCreditNo(String creditNo) {
        this.creditNo = creditNo;
    }

    public Integer getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getLoanUserName() {
        return loanUserName;
    }

    public void setLoanUserName(String loanUserName) {
        this.loanUserName = loanUserName;
    }

    public String getLoanCorporation() {
        return loanCorporation;
    }

    public void setLoanCorporation(String loanCorporation) {
        this.loanCorporation = loanCorporation;
    }

    public String getLoanAddress() {
        return loanAddress;
    }

    public void setLoanAddress(String loanAddress) {
        this.loanAddress = loanAddress;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCusAddress() {
        return cusAddress;
    }

    public void setCusAddress(String cusAddress) {
        this.cusAddress = cusAddress;
    }

    public String getCusIdCard() {
        return cusIdCard;
    }

    public void setCusIdCard(String cusIdCard) {
        this.cusIdCard = cusIdCard;
    }

    public String getOtherAgree() {
        return otherAgree;
    }

    public void setOtherAgree(String otherAgree) {
        this.otherAgree = otherAgree;
    }

    public String getContractNum() {
        return contractNum;
    }

    public void setContractNum(String contractNum) {
        this.contractNum = contractNum;
    }

    public Date getContractTime() {
        return contractTime;
    }

    public void setContractTime(Date contractTime) {
        this.contractTime = contractTime;
    }

    public String getContractPlace() {
        return contractPlace;
    }

    public void setContractPlace(String contractPlace) {
        this.contractPlace = contractPlace;
    }

    public String getSettleDispute() {
        return settleDispute;
    }

    public void setSettleDispute(String settleDispute) {
        this.settleDispute = settleDispute;
    }
}
