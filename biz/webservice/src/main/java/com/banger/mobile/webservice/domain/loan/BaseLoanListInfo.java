package com.banger.mobile.webservice.domain.loan;

import java.util.Date;

import com.banger.mobile.domain.model.loan.LnLoan;
import com.banger.mobile.domain.model.pad.PadLoan;
import com.banger.mobile.util.DateUtil;
public class BaseLoanListInfo {
	private Integer loanId; 					// 主键ID		主表
	private Integer customerId; 				// 客户ID   	申请信息表
	private String customerName;				// 客户名称	客户表
    private String customerTitle;				// 客户称谓	客户表
	private String idCard;						// 证件类型	客户表
    private String phone; 						// 电话号码	客户表
    private Integer loanInfoId; 				// 
    private String lendDate; 						//放贷时间
	private String resultMoney; // 决议金额
	private String resultLimitYear; // 决议期限
	private String resultRate; // 决议利率
	private String resultRepayWayId;//还款方式
	private String resultPurpose;//贷款用途

	public String getResultMoney() {
		return resultMoney;
	}

	public void setResultMoney(String resultMoney) {
		this.resultMoney = resultMoney;
	}

	public String getResultLimitYear() {
		return resultLimitYear;
	}

	public void setResultLimitYear(String resultLimitYear) {
		this.resultLimitYear = resultLimitYear;
	}

	public String getResultRate() {
		return resultRate;
	}

	public void setResultRate(String resultRate) {
		this.resultRate = resultRate;
	}

	public String getResultRepayWayId() {
		return resultRepayWayId;
	}

	public void setResultRepayWayId(String resultRepayWayId) {
		this.resultRepayWayId = resultRepayWayId;
	}

	public String getResultPurpose() {
		return resultPurpose;
	}

	public void setResultPurpose(String resultPurpose) {
		this.resultPurpose = resultPurpose;
	}

	public String getLendDate() {
		return lendDate;
	}
	public void setLendDate(String lendDate) {
		this.lendDate = lendDate;
	}
	public Integer getLoanInfoId() {
		return loanInfoId==null? 0:loanInfoId;
	}
	public void setLoanInfoId(Integer loanInfoId) {
		this.loanInfoId = loanInfoId==null? 0:loanInfoId;
	}
	public Integer getLoanId() {
		return loanId==null ? -1 :loanId;
	}
	public void setLoanId(Integer loanId) {
		this.loanId = loanId==null ? -1 :loanId;
	}
	public Integer getCustomerId() {
		return customerId==null ? -1:customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId==null ? -1:customerId;
	}
	public String getCustomerName() {
		return customerName== null ?"":customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName== null ?"":customerName;
	}
	public String getCustomerTitle() {
		return customerTitle==null ?"":customerTitle;
	}
	public void setCustomerTitle(String customerTitle) {
		this.customerTitle = customerTitle==null ?"":customerTitle;
	}
	public String getIdCard() {
		return idCard==null ? "":idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard==null ? "":idCard;
	}
	public String getPhone() {
		return phone==null ?"":phone;
	}
	public void setPhone(String phone) {
		this.phone = phone==null ?"":phone;
	}
	
	public BaseLoanListInfo(PadLoan info){
		this.setLoanId(info.getLoanId());
		this.setCustomerId(info.getCustomerId());
		this.setCustomerName(info.getCustomerName());
		this.setCustomerTitle(info.getCustomerTitle());
		this.setIdCard(info.getIdCard());
		this.setPhone(info.getPhone());    
	}
	
	public BaseLoanListInfo(LnLoan info){
		
		this.setLoanId(info.getLoanId());
		this.setCustomerId(info.getCustomerId());
		this.setCustomerName(info.getCrmCustomer().getCustomerName());
		this.setCustomerTitle(info.getCrmCustomer().getCustomerTitle());
		this.setIdCard(info.getCrmCustomer().getIdCard());
		this.setPhone(info.getCrmCustomer().getMobilePhone1());  
		this.setLendDate(DateUtil.getDateToString(info.getLendContractCheckDate()));
	}
	
}
