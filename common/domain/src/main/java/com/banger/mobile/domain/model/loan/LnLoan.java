package com.banger.mobile.domain.model.loan;

import java.math.BigDecimal;

import com.banger.mobile.domain.model.base.loan.BaseLnLoan;
import com.banger.mobile.domain.model.customer.CrmCustomer;

/**
 * Created with IntelliJ IDEA.
 * User: Zhangfp
 * Date: 13-2-5
 * Time: 下午3:45
 * To change this template use File | Settings | File Templates.
 * <p/>
 * 贷款主表
 */
public class LnLoan extends BaseLnLoan {

	private String loanStatusName;                      //贷款状态
    private LnLoanType lnLoanTypeBean;
    private LnRepaymentPlan lnRepaymentPlan; //还款计划表
    private LnExceptionRepaymentPlan lnExceptionRepaymentPlan; //异常还款计划表
    private LnLoanSubType lnLoanSubTypeBean;
    private LnLoanInfo lnLoanInfoBean;
    private CrmCustomer crmCustomer;
    private LnLoanCoBorrowerBean lnLoanCoBorrowerBean;  //共同借贷人
    private LnLoanGuarantorBean lnLoanGuarantorBean;    //担保人
    private String cphNumber;
    private LoanUser sysUser;
    private LnCancelReason lnCancelReason;
    private LnDunLog lnDunLog;
    private LnExceptionDunLog lnExceptionDunLog;
    private String exceptionAssignUserNames;
    private LnLoanStatus lnLoanStatus;
    private LnOpHistory lnOpHistory;
    private String applyUserName;
    private String surveyUserName;

    public String getSurveyUserName() {
        return surveyUserName;
    }

    public void setSurveyUserName(String surveyUserName) {
        this.surveyUserName = surveyUserName;
    }

    public String getApplyUserName() {
        return applyUserName;
    }

    public void setApplyUserName(String applyUserName) {
        this.applyUserName = applyUserName;
    }

    public LnRepaymentPlan getLnRepaymentPlan() {
		return lnRepaymentPlan;
	}

	public void setLnRepaymentPlan(LnRepaymentPlan lnRepaymentPlan) {
		this.lnRepaymentPlan = lnRepaymentPlan;
	}



	public LnLoanInfo getLnLoanInfoBean() {
		return lnLoanInfoBean;
	}

	public void setLnLoanInfoBean(LnLoanInfo lnLoanInfoBean) {
		this.lnLoanInfoBean = lnLoanInfoBean;
	}


    private Integer statusCount;  //贷款状态分组总数

    private Long diffTime; //创建时间与当前时间相差的秒数

    private Integer isCanEdit; //是否可编辑

    private Integer hasPermission; //是否有权限(指当前贷款的客户是否是归当前登录用户管辖)

    private Integer loanInfoId;
    
    private Integer customerId;
    
    private Integer loanTypeId;
    private Integer loanSubTypeId;
    private String 	applyRemark;
    private String appLoanTypeName;
    private BigDecimal appMoney; //贷款金额
    
	
	public BigDecimal getAppMoney() {
		return appMoney;
	}

	public void setAppMoney(BigDecimal appMoney) {
		this.appMoney = appMoney;
	}

	public String getAppLoanTypeName() {
		return appLoanTypeName;
	}

	public void setAppLoanTypeName(String appLoanTypeName) {
		this.appLoanTypeName = appLoanTypeName;
	}

	public String getApplyRemark() {
		return applyRemark;
	}

	public void setApplyRemark(String applyRemark) {
		this.applyRemark = applyRemark;
	}

	public Integer getLoanSubTypeId() {
		return loanSubTypeId;
	}

	public void setLoanSubTypeId(Integer loanSubTypeId) {
		this.loanSubTypeId = loanSubTypeId;
	}

	public Integer getLoanTypeId() {
		return loanTypeId;
	}

	public void setLoanTypeId(Integer loanTypeId) {
		this.loanTypeId = loanTypeId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getLoanInfoId() {
        return loanInfoId;
    }

    public void setLoanInfoId(Integer loanInfoId) {
        this.loanInfoId = loanInfoId;
    }

    public Integer getHasPermission() {
        return hasPermission;
    }

    public void setHasPermission(Integer hasPermission) {
        this.hasPermission = hasPermission;
    }

    public Long getDiffTime() {
        return diffTime;
    }

    public void setDiffTime(Long diffTime) {
        this.diffTime = diffTime;
    }

    public Integer getStatusCount() {
        return statusCount;
    }

    public void setStatusCount(Integer statusCount) {
        this.statusCount = statusCount;
    }

    public String getLoanStatusName() {
        return loanStatusName;
    }

    public void setLoanStatusName(String loanStatusName) {
        this.loanStatusName = loanStatusName;
    }

    private Integer isWillPast;  //是否超时

    public Integer getIsWillPast(){
        return isWillPast;
    }

    public void setIsWillPast(Integer isWillPast){
        this.isWillPast = isWillPast;
    }

    public CrmCustomer getCrmCustomer() {
        return crmCustomer;
    }

    public void setCrmCustomer(CrmCustomer crmCustomer) {
        this.crmCustomer = crmCustomer;
    }

    public LnLoanType getLnLoanTypeBean() {
        return lnLoanTypeBean;
    }

    public void setLnLoanTypeBean(LnLoanType lnLoanTypeBean) {
        this.lnLoanTypeBean = lnLoanTypeBean;
    }

    public LnLoanSubType getLnLoanSubTypeBean() {
        return lnLoanSubTypeBean;
    }

    public void setLnLoanSubTypeBean(LnLoanSubType lnLoanSubTypeBean) {
        this.lnLoanSubTypeBean = lnLoanSubTypeBean;
    }

    public LnLoanCoBorrowerBean getLnLoanCoBorrowerBean() {
        return lnLoanCoBorrowerBean;
    }

    public void setLnLoanCoBorrowerBean(LnLoanCoBorrowerBean lnLoanCoBorrowerBean) {
        this.lnLoanCoBorrowerBean = lnLoanCoBorrowerBean;
    }

    public LnLoanGuarantorBean getLnLoanGuarantorBean() {
        return lnLoanGuarantorBean;
    }

    public void setLnLoanGuarantorBean(LnLoanGuarantorBean lnLoanGuarantorBean) {
        this.lnLoanGuarantorBean = lnLoanGuarantorBean;
    }

    public String getCphNumber() {
        if (this.crmCustomer != null) {
            if (this.crmCustomer.getDefaultPhoneType() != null) {
                switch (this.crmCustomer.getDefaultPhoneType().intValue()) {
                    case 1:
                        return this.crmCustomer.getMobilePhone1();
                    case 2:
                        return this.crmCustomer.getMobilePhone2();
                    case 3:
                        return this.crmCustomer.getPhone();
                    case 4:
                        return this.crmCustomer.getFax();
                    default:
                        if (this.crmCustomer.getMobilePhone1() != null && !"".equals(this.crmCustomer.getMobilePhone1()))
                            return this.crmCustomer.getMobilePhone1();
                        if (this.crmCustomer.getMobilePhone2() != null && !"".equals(this.crmCustomer.getMobilePhone2()))
                            return this.crmCustomer.getMobilePhone2();
                        if (this.crmCustomer.getPhone() != null && !"".equals(this.crmCustomer.getPhone()))
                            return this.crmCustomer.getPhone();
                        if (this.crmCustomer.getFax() != null && !"".equals(this.crmCustomer.getFax()))
                            return this.crmCustomer.getFax();
                }
            }
        }
        if (this.cphNumber != null && !"".equals(this.cphNumber))
            return this.cphNumber;
        return "";
    }

    public void setCphNumber(String cphNumber) {
        this.cphNumber = cphNumber;
    }

    public LoanUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(LoanUser sysUser) {
        this.sysUser = sysUser;
    }

    public LnCancelReason getLnCancelReason() {
        return lnCancelReason;
    }

    public void setLnCancelReason(LnCancelReason lnCancelReason) {
        this.lnCancelReason = lnCancelReason;
    }

    public LnDunLog getLnDunLog() {
        return lnDunLog;
    }

    public void setLnDunLog(LnDunLog lnDunLog) {
        this.lnDunLog = lnDunLog;
    }

    public LnExceptionDunLog getLnExceptionDunLog() {
        return lnExceptionDunLog;
    }

    public void setLnExceptionDunLog(LnExceptionDunLog lnExceptionDunLog) {
        this.lnExceptionDunLog = lnExceptionDunLog;
    }

    public String getExceptionAssignUserNames() {
        return exceptionAssignUserNames;
    }

    public void setExceptionAssignUserNames(String exceptionAssignUserNames) {
        this.exceptionAssignUserNames = exceptionAssignUserNames;
    }

    public LnLoanStatus getLnLoanStatus() {
        return lnLoanStatus;
    }

    public void setLnLoanStatus(LnLoanStatus lnLoanStatus) {
        this.lnLoanStatus = lnLoanStatus;
    }

    public LnOpHistory getLnOpHistory() {
        return lnOpHistory;
    }

    public void setLnOpHistory(LnOpHistory lnOpHistory) {
        this.lnOpHistory = lnOpHistory;
    }

    public Integer getIsCanEdit() {
        return isCanEdit;
    }

    public void setIsCanEdit(Integer isCanEdit) {
        this.isCanEdit = isCanEdit;
    }

	public LnExceptionRepaymentPlan getLnExceptionRepaymentPlan() {
		return lnExceptionRepaymentPlan;
	}

	public void setLnExceptionRepaymentPlan(
			LnExceptionRepaymentPlan lnExceptionRepaymentPlan) {
		this.lnExceptionRepaymentPlan = lnExceptionRepaymentPlan;
	}

    
}
