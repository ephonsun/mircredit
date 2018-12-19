package com.banger.mobile.domain.model.customer;


import org.apache.commons.lang.builder.HashCodeBuilder;

import com.banger.mobile.domain.model.base.customer.BaseCrmCustomer;
/**
 * CrmCustomer entity. @author MyEclipse Persistence Tools
 */

public class CrmCustomerEva extends BaseCrmCustomer {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2662494650172162955L;
	
	private String testRemark;//投资偏好
	
	 private String  testDate;
	 
	private String phoneNo;
	
	private String userName;
	
	private String deptName;
	
    private Integer isShare;        //是否是共享客户
	
	private String customerTypeName;
	
	private String customerIndustryName;
	
	public String getCustomerIndustryName() {
        return customerIndustryName;
    }
	
	

    public Integer getIsShare() {
        return isShare;
    }



    public void setIsShare(Integer isShare) {
        this.isShare = isShare;
    }



    public String getTestRemark() {
        return testRemark;
    }


    
    public String getTestDate() {
        return testDate;
    }



    public void setTestDate(String testDate) {
        this.testDate = testDate;
    }



    public void setTestRemark(String testRemark) {
        this.testRemark = testRemark;
    }



    public void setCustomerIndustryName(String customerIndustryName) {
        this.customerIndustryName = customerIndustryName;
    }

    private String lastContactDateStr;

	public String getLastContactDateStr() {
		return lastContactDateStr;
	}

	public void setLastContactDateStr(String lastContactDateStr) {
		this.lastContactDateStr = lastContactDateStr;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-1446133015, 1051904819).appendSuper(
				super.hashCode()).toHashCode();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getCustomerTypeName() {
		return customerTypeName;
	}

	public void setCustomerTypeName(String customerTypeName) {
		this.customerTypeName = customerTypeName;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
}