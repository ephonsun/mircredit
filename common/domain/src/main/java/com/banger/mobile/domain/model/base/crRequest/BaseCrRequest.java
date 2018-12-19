package com.banger.mobile.domain.model.base.crRequest;

import com.banger.mobile.domain.model.base.BaseObject;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by BH-TCL on 15-1-19.
 */
public class BaseCrRequest extends BaseObject implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 4164057546226669205L;
	private Integer requestId;//主键
    private Integer loanId;//贷款id
    private String serno;//征信调查影像案卷号
    private Integer customerId;//客户ID
    private Integer customerType;//客户类型
    private Integer requestUser;//请求人
    private Date requestDate;//请求日期
    private String requestReason;//调查原因
    private Integer idauthFlag;//查询类型
    private Integer requestStatus;//请求状态
    private String requestResult;//征信结果
    private Integer upFileId;//征信文件正面
    private Integer downFileId;//征信文件反面
    private Integer authFileId;//征信文件授权书
    private String remark;//备注
    private Date createDate;//创建时间
    private Date updateDate;//更新时间
    private Integer createUser;
    private Integer updateUser;
    private Integer conductor; //处理人
    private Date conductorDate;//处理时间
    

    public Integer getConductor() {
		return conductor;
	}

	public void setConductor(Integer conductor) {
		this.conductor = conductor;
	}

	public Date getConductorDate() {
		return conductorDate;
	}

	public void setConductorDate(Date conductorDate) {
		this.conductorDate = conductorDate;
	}

	public Integer getCustomerType() {
        return customerType;
    }

    public void setCustomerType(Integer customerType) {
        this.customerType = customerType;
    }

    public String getRequestReason() {
        return requestReason;
    }

    public void setRequestReason(String requestReason) {
        this.requestReason = requestReason;
    }

    public Integer getIdauthFlag() {
        return idauthFlag;
    }

    public void setIdauthFlag(Integer idauthFlag) {
        this.idauthFlag = idauthFlag;
    }

    public String getRemark() {
        return remark==null ? "":remark;
    }

    public void setRemark(String remark) {
        this.remark = remark==null ? "":remark;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public Integer getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    public void setSerno(String serno) {
        this.serno = serno;
    }

    public String getSerno() {
        return serno;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getRequestUser() {
        return requestUser;
    }

    public void setRequestUser(Integer requestUser) {
        this.requestUser = requestUser;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Integer getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(Integer requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getRequestResult() {
        return requestResult;
    }

    public void setRequestResult(String requestResult) {
        this.requestResult = requestResult;
    }

    public Integer getUpFileId() {
        return upFileId;
    }

    public void setUpFileId(Integer upFileId) {
        this.upFileId = upFileId;
    }

    public Integer getDownFileId() {
        return downFileId;
    }

    public void setDownFileId(Integer downFileId) {
        this.downFileId = downFileId;
    }

    public Integer getAuthFileId() {
        return authFileId;
    }

    public void setAuthFileId(Integer authFileId) {
        this.authFileId = authFileId;
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
}
