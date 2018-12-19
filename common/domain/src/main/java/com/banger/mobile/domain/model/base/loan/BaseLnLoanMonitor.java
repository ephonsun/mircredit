package com.banger.mobile.domain.model.base.loan;

import com.banger.mobile.domain.model.base.BaseObject;

import java.io.Serializable;
import java.util.Date;

/**
 * 贷后监控
 * 
 * @author linkin
 * @version $Id: BaseLnLoanMonitor.java, v 0.1 2016-1-10 下午2:34:25 linkin Exp $
 */
public class  BaseLnLoanMonitor extends BaseObject implements Serializable {

    private Integer loanMonId;      //主键ID
    private Integer loanId;     //贷款ID
    private Integer monUserId;           //监控用户id
    private Date monDate;       //监控日期
    private Integer monType;          //监控类型
    private Integer revisitType;           //回访类型
    private String userResult;           //结果
    private String fileIds;           //材料编号FILE_IDS
    private Date createDate;         //创建时间
    private Date updateDate;         //更新时间
    private Integer createUser;      //创建用户
    private Integer updateUser;      //更新用户
    private Integer readTag;		 //已阅标志
    private String  remak;          //备注
	private String customerStatus;//客户基本情况
	private String bizStatus;//生产经营情况
	private String financeStatus;//财务情况
	private String guarantorStatus;//保证担保
	private String ledgeStatus;//抵质押担保

    public BaseLnLoanMonitor(){
    }

	public String getCustomerStatus() {
		return customerStatus;
	}

	public void setCustomerStatus(String customerStatus) {
		this.customerStatus = customerStatus;
	}

	public String getBizStatus() {
		return bizStatus;
	}

	public void setBizStatus(String bizStatus) {
		this.bizStatus = bizStatus;
	}

	public String getFinanceStatus() {
		return financeStatus;
	}

	public void setFinanceStatus(String financeStatus) {
		this.financeStatus = financeStatus;
	}

	public String getGuarantorStatus() {
		return guarantorStatus;
	}

	public void setGuarantorStatus(String guarantorStatus) {
		this.guarantorStatus = guarantorStatus;
	}

	public String getLedgeStatus() {
		return ledgeStatus;
	}

	public void setLedgeStatus(String ledgeStatus) {
		this.ledgeStatus = ledgeStatus;
	}

	public BaseLnLoanMonitor(Integer loanMonId){
        this.loanMonId = loanMonId;
    }

	public Integer getLoanMonId() {
		return loanMonId;
	}

	public void setLoanMonId(Integer loanMonId) {
		this.loanMonId = loanMonId;
	}



	public Integer getMonUserId() {
		return monUserId;
	}

	public void setMonUserId(Integer monUserId) {
		this.monUserId = monUserId;
	}



	public Date getMonDate() {
		return monDate;
	}

	public void setMonDate(Date monDate) {
		this.monDate = monDate;
	}

	public Integer getMonType() {
		return monType;
	}

	public void setMonType(Integer monType) {
		this.monType = monType;
	}





	public Integer getRevisitType() {
		return revisitType;
	}

	public void setRevisitType(Integer revisitType) {
		this.revisitType = revisitType;
	}

	public String getUserResult() {
		return userResult;
	}

	public void setUserResult(String userResult) {
		this.userResult = userResult;
	}



	public Integer getLoanId() {
		return loanId;
	}

	public void setLoanId(Integer loanId) {
		this.loanId = loanId;
	}


	public String getFileIds() {
		return fileIds;
	}

	public void setFileIds(String fileIds) {
		this.fileIds = fileIds;
	}


	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}

	public Integer getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(Integer updateUser) {
		this.updateUser = updateUser;
	}

	public Integer getReadTag() {
		return readTag;
	}

	public void setReadTag(Integer readTag) {
		this.readTag = readTag;
	}

    public String getRemak() {
        return remak;
    }

    public void setRemak(String remak) {
        this.remak = remak;
    }


}
