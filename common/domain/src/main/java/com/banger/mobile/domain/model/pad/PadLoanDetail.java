/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yuanme
 * Create Date:2012-11-22
 */
package com.banger.mobile.domain.model.pad;

import com.banger.mobile.domain.model.loan.LnCreditHistory;
import com.banger.mobile.domain.model.loan.LnExceptionRepaymentPlan;
import com.banger.mobile.domain.model.loan.LnLoanCoBorrowerBean;
import com.banger.mobile.domain.model.loan.LnLoanGuarantorBean;
import com.banger.mobile.domain.model.loan.LnPledge;
import com.banger.mobile.domain.model.loan.LnRepaymentPlan;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author zhangfp 
 * 2016-1-8 瑞丰修改   xiall
 * @version $Id: PadLoanDetail.java,v 0.1 2012-11-22 下午1:03:55 Administrator Exp $
 */
public class PadLoanDetail implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -1059117129615417830L;
	//贷款基本信息
    //贷款ID
    private Integer loanId;                       	//贷款主表 PadLoan
    //贷款状态ID
    private Integer loanStatusId;					//贷款主表 PadLoan
    //贷款状态名称
    private String loanStatusName;					//贷款主表 PadLoan
    //事件ID
    private Integer eventId;						//贷款主表 PadLoan
    //拒绝理由
    private String cancelReason; 
    
    private Integer loanInfoId;						//贷款信息表 PadLoanInfo
    
    private Integer customerId;						//贷款信息表 PadLoanInfo
    //客户姓名
    private String customerName;					//贷款信息表 PadLoanInfo
    //客户称呼
    private String customerTitle;					//贷款信息表 PadLoanInfo
    //电话
    private String phone;							//贷款信息表 PadLoanInfo   
    // 申请人证件类型
    private String cusIdtypeId; 					//贷款信息表 PadLoanInfo
    // 申请人证件类型
    private String cusIdtypeName;					//贷款信息表 PadLoanInfo
    //身份证
    private String idCard;							//贷款信息表 PadLoanInfo
    //贷款类型ID
    private Integer loanTypeId;						//贷款信息表 PadLoanInfo					
    //贷款类型名
    private String loanTypeName;					//贷款信息表 PadLoanInfo
	//贷款子类型ID
	private Integer loanSubTypeId;						//贷款信息表 PadLoanInfo
	//贷款子类型名
	private String loanSubTypeName;					//贷款信息表 PadLoanInfo
    //贷款金额
    private String loanMoney;						//贷款信息表 PadLoanInfo						
    //账户余额
    private String accountRemaining;				//贷款信息表 PadLoanInfo	
    
    //归属人员id
    private Integer belongUserId;					//贷款主表 PadLoan
    
    //贷款是否驳回
    private Integer isReject;	
    
    private Map<String, Object> adviceResult;
    
    private Map<String, Object> approveResult;
    
	private List<BaseData> approveProcess;	//审批流
	private List<BaseData> fkfs;	//放款方式
	private List<BaseData> hkfs;	//还款方式
	private List<BaseData> sdhjy;	//审贷会决议
	private List<BaseData> dkyt;	//贷款用途

	public List<BaseData> getSdhjy() {
		return sdhjy;
	}

	public void setSdhjy(List<BaseData> sdhjy) {
		this.sdhjy = sdhjy;
	}

	public List<BaseData> getDkyt() {
		return dkyt;
	}

	public void setDkyt(List<BaseData> dkyt) {
		this.dkyt = dkyt;
	}

	public Integer getLoanSubTypeId() {
		return loanSubTypeId;
	}

	public void setLoanSubTypeId(Integer loanSubTypeId) {
		this.loanSubTypeId = loanSubTypeId;
	}

	public String getLoanSubTypeName() {
		return loanSubTypeName;
	}

	public void setLoanSubTypeName(String loanSubTypeName) {
		this.loanSubTypeName = loanSubTypeName;
	}

	//还款计划
    private List<LnRepaymentPlan> repaymentPlanList;
    //共同借贷人
    private List<LnLoanCoBorrowerBean> coBorrowerList;
    //担保人
    private List<LnLoanGuarantorBean> guarantorList;
    //贷款资料
    private List<PadLoanData> dataList;
    //贷款历史操作
    private List<PadLoanOpHistory> opHistoryList;
    //异常还款计划
    private List<LnExceptionRepaymentPlan> exceptionRepaymentPlanList;
    //信贷历史
    private List<LnCreditHistory> creditHistoryList;
    //抵质押物
    private List<LnPledge> pledgeList;

    private String togetherSurveyUserAccont; //陪调人员账号
    
    private Double needRepay; //本息合计
    
    
	public Double getNeedRepay() {
		return needRepay;
	}
	public void setNeedRepay(Double needRepay) {
		this.needRepay = needRepay;
	}
	public String getTogetherSurveyUserAccont() {
		return togetherSurveyUserAccont;
	}
	public void setTogetherSurveyUserAccont(String togetherSurveyUserAccont) {
		this.togetherSurveyUserAccont = togetherSurveyUserAccont;
	}
	public List<BaseData> getApproveProcess() {
		return approveProcess;
	}
	public void setApproveProcess(List<BaseData> approveProcess) {
		this.approveProcess = approveProcess;
	}
	public List<BaseData> getFkfs() {
		return fkfs;
	}
	public void setFkfs(List<BaseData> fkfs) {
		this.fkfs = fkfs;
	}
	public List<BaseData> getHkfs() {
		return hkfs;
	}
	public void setHkfs(List<BaseData> hkfs) {
		this.hkfs = hkfs;
	}
	public Map<String, Object> getAdviceResult() {
		return adviceResult;
	}
	public void setAdviceResult(Map<String, Object> adviceResult) {
		this.adviceResult = adviceResult;
	}
	
	public Map<String, Object> getApproveResult() {
		return approveResult;
	}
	public void setApproveResult(Map<String, Object> approveResult) {
		this.approveResult = approveResult;
	}
	public Integer getLoanInfoId() {
		return loanInfoId;
	}
	public void setLoanInfoId(Integer loanInfoId) {
		this.loanInfoId = loanInfoId;
	}
	public Integer getLoanId() {
		return loanId==null?0:loanId;
	}
	public void setLoanId(Integer loanId) {
		this.loanId = loanId==null?0:loanId;
	}
	public Integer getLoanStatusId() {
		return loanStatusId==null ?0:loanStatusId;
	}
	public void setLoanStatusId(Integer loanStatusId) {
		this.loanStatusId = loanStatusId==null ? 0:loanStatusId;
	}	
	public String getLoanStatusName() {
		return loanStatusName==null ?"":loanStatusName;
	}
	public void setLoanStatusName(String loanStatusName) {
		this.loanStatusName = loanStatusName==null ?"":loanStatusName;
	}
	public Integer getEventId() {
		return eventId==null?0:eventId;
	}
	public void setEventId(Integer eventId) {
		this.eventId = eventId==null?0:eventId;
	}
	public Integer getCustomerId() {
		return customerId==null?0:customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId==null?0:customerId;
	}
	public String getCustomerName() {
		return customerName==null ?"":customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName==null ?"":customerName;
	}
	public String getCustomerTitle() {
		return customerTitle==null ?"":customerTitle;
	}
	public void setCustomerTitle(String customerTitle) {
		this.customerTitle = customerTitle==null ?"":customerTitle;
	}
	public String getPhone() {
		return phone==null ?"":phone;
	}
	public void setPhone(String phone) {
		this.phone = phone==null?"":phone;
	}
	public String getCusIdtypeId() {
		return cusIdtypeId==null?"":cusIdtypeId;
	}
	public void setCusIdtypeId(String cusIdtypeId) {
		this.cusIdtypeId = cusIdtypeId==null?"":cusIdtypeId;
	}
	public String getCusIdtypeName() {
		return cusIdtypeName==null?"":cusIdtypeName;
	}
	public void setCusIdtypeName(String cusIdtypeName) {
		this.cusIdtypeName = cusIdtypeName==null?"":cusIdtypeName;
	}
	public String getIdCard() {
		return idCard==null?"":idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard==null?"":idCard;
	}
	public Integer getLoanTypeId() {
		return loanTypeId==null?0:loanTypeId;
	}
	public void setLoanTypeId(Integer loanTypeId) {
		this.loanTypeId = loanTypeId==null?0:loanTypeId;
	}
	public String getLoanTypeName() {
		return loanTypeName==null?"":loanTypeName;
	}
	public void setLoanTypeName(String loanTypeName) {
		this.loanTypeName = loanTypeName==null?"":loanTypeName;
	}
	public String getLoanMoney() {
		return loanMoney==null?"0":loanMoney;
	}
	public void setLoanMoney(String loanMoney) {
		this.loanMoney = loanMoney==null?"0":loanMoney;;
	}
	public String getAccountRemaining() {
		return accountRemaining==null?"0":accountRemaining;
	}
	public void setAccountRemaining(String accountRemaining) {
		this.accountRemaining = accountRemaining==null?"0":accountRemaining;
	}
	
    public Integer getIsReject() {
		return isReject==null ?0:isReject;
	}
	public void setIsReject(Integer isReject) {
		this.isReject = isReject==null ?0:isReject;
	}
	public Integer getBelongUserId() {
        return belongUserId==null?0:belongUserId;
    }
    public void setBelongUserId(Integer belongUserId) {
        this.belongUserId = belongUserId==null?0:belongUserId;
    }
	public List<LnRepaymentPlan> getRepaymentPlanList() {
		return repaymentPlanList;
	}
	public void setRepaymentPlanList(List<LnRepaymentPlan> repaymentPlanList) {
		this.repaymentPlanList = repaymentPlanList;
	}
	public List<LnLoanCoBorrowerBean> getCoBorrowerList() {
		return coBorrowerList;
	}
	public void setCoBorrowerList(List<LnLoanCoBorrowerBean> coBorrowerList) {
		this.coBorrowerList = coBorrowerList;
	}
	public List<LnLoanGuarantorBean> getGuarantorList() {
		return guarantorList;
	}
	public void setGuarantorList(List<LnLoanGuarantorBean> guarantorList) {
		this.guarantorList = guarantorList;
	}
	public List<PadLoanData> getDataList() {
		return dataList;
	}
	public void setDataList(List<PadLoanData> dataList) {
		this.dataList = dataList;
	}
	public List<PadLoanOpHistory> getOpHistoryList() {
		return opHistoryList;
	}
	public void setOpHistoryList(List<PadLoanOpHistory> opHistoryList) {
		this.opHistoryList = opHistoryList;
	}
	public List<LnExceptionRepaymentPlan> getExceptionRepaymentPlanList() {
		return exceptionRepaymentPlanList;
	}
	public void setExceptionRepaymentPlanList(
			List<LnExceptionRepaymentPlan> exceptionRepaymentPlanList) {
		this.exceptionRepaymentPlanList = exceptionRepaymentPlanList;
	}
	public List<LnCreditHistory> getCreditHistoryList() {
		return creditHistoryList;
	}
	public void setCreditHistoryList(List<LnCreditHistory> creditHistoryList) {
		this.creditHistoryList = creditHistoryList;
	}
	public List<LnPledge> getPledgeList() {
		return pledgeList;
	}
	public void setPledgeList(List<LnPledge> pledgeList) {
		this.pledgeList = pledgeList;
	}
	public String getCancelReason() {
		return cancelReason;
	}
	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}
    
    
    
    /*
    //拒绝原因
    private String cancelReason;
    //本期合计
    private String needRepay;
    //是否余额已足
    private Integer isFull;
    //审计是否合规
    private Integer isVerifyPass;
    //审计备注
    private String verifyRemark;
    
	//催收记录资料(包括异常催收)
    private List<PadLoanDun> dunList;    
	public List<PadLoanDun> getDunList() {
		return dunList;
	}
	public void setDunList(List<PadLoanDun> dunList) {
		this.dunList = dunList;
	}
    

    
    public void setIsReject(Integer isReject){
        this.isReject = isReject;
    }

    public Integer getIsReject(){
        return isReject;
    }



    public Integer getIsVerifyPass(){
        return isVerifyPass;
    }

    public void setIsVerifyPass(Integer isVerifyPass){
        this.isVerifyPass = isVerifyPass;
    }

    public String getVerifyRemark() {
        return verifyRemark;
    }

    public void setVerifyRemark(String verifyRemark) {
        this.verifyRemark = verifyRemark;
    }

    public Integer getIsFull(){
        return isFull;
    }

    public void setIsFull(Integer isFull){
        this.isFull = isFull;
    }

    public String getNeedRepay() {
        return needRepay;
    }

    public void setNeedRepay(String needRepay) {
        this.needRepay = needRepay;
    }
*/
     
}
