package com.banger.mobile.domain.model.base.loan;

import com.banger.mobile.domain.model.base.BaseObject;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Zhangfp
 * Date: 13-2-5
 * Time: 下午1:52
 * To change this template use File | Settings | File Templates.
 *
 * 担保人
 */
public class BaseLnLoanGuarantor extends BaseObject implements Serializable {
    private Integer guarantorId;  //主键ID(担保人ID)
    private Integer loanId;       //贷款ID
    private Integer customerId;   //担保人客户ID
    private String companyAddress;	//公司地址
    private String petitionerRelate;	//与借款人关系
    private Integer isKownLoan; //是否知晓贷款
    private Integer isException; //是否存在异常
    private String checkMessage; //软信息核实
    private Date createDate;      //创建时间
    private Date updateDate;      //更新时间
    private Integer createUser;   //创建用户
    private Integer updateUser;   //更新时间

    public BaseLnLoanGuarantor(){
    }
    
    public Integer getIsKownLoan() {
		return isKownLoan;
	}



	public void setIsKownLoan(Integer isKownLoan) {
		this.isKownLoan = isKownLoan;
	}



	public Integer getIsException() {
		return isException;
	}



	public void setIsException(Integer isException) {
		this.isException = isException;
	}



	public String getCheckMessage() {
		return checkMessage;
	}



	public void setCheckMessage(String checkMessage) {
		this.checkMessage = checkMessage;
	}



	public BaseLnLoanGuarantor(Integer guarantorId){
        this.guarantorId = guarantorId;
    }

    public Integer getGuarantorId() {
        return guarantorId;
    }

    public void setGuarantorId(Integer guarantorId) {
        this.guarantorId = guarantorId;
    }

    public Integer getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
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

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getPetitionerRelate() {
		return petitionerRelate;
	}

	public void setPetitionerRelate(String petitionerRelate) {
		this.petitionerRelate = petitionerRelate;
	}
    
    
}
