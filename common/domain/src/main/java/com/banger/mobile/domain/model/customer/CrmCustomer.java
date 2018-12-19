package com.banger.mobile.domain.model.customer;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;
import com.banger.mobile.domain.model.base.customer.BaseCrmCustomer;

/**
 * CrmCustomer entity. @author MyEclipse Persistence Tools
 */

public class CrmCustomer extends BaseCrmCustomer {

    /**
     * 
     */
    private static final long serialVersionUID = 2662494650172162955L;

    private String            phoneNo;                                //

    private String            userName;                               //归属人员

    private String            deptName;                                //归属机构

    private String            customerTypeName;                       //客户类型

    private String            customerIndustryName;                    //所处行业

    private String            exSmsPhone;

    private Integer           isShare;                                //是否是共享客户

    private String            lineNumber;                             //行号
    
    private String            loanStatus;                             //客户最后一条贷款状态
    
    private String            scheduleInfo;                           //客户最后一条日程信息



    private Integer isCustomerEdit;   //判断是否是编辑客户(编辑客户在很多地方都用到，这主要是区分客户管理模块与其他模块的编辑客户)
    
    private String credentialTypeName;
    private String educationalHistoryName;		
    private String livingConditionName;
    private String maritalStatusName;
    private String orgTypeName;
    private String legalFormName;
    
    

    public Integer getIsCustomerEdit() {
        return isCustomerEdit;
    }

    public void setIsCustomerEdit(Integer customerEdit) {
        isCustomerEdit = customerEdit;
    }

    /**
     * 获得短信号码
     * @return
     */
    public String getSmsPhone() {
        if (this.defaultPhoneType != null) {
            switch (this.defaultPhoneType.intValue()) {
                case 1:
                    return this.mobilePhone1;
                case 2:
                    return this.mobilePhone2;
                default:
                    if (this.mobilePhone1 != null && !"".equals(this.mobilePhone1))
                        return this.mobilePhone1;
                    if (this.mobilePhone2 != null && !"".equals(this.mobilePhone2))
                        return this.mobilePhone2;
            }
        }
        if (this.exSmsPhone != null && !"".equals(this.exSmsPhone))
            return this.exSmsPhone;
        return "";
    }

    public Integer getFirstNotNullPhoneType() {
        if (this.mobilePhone1 != null && !"".equals(this.mobilePhone1)) {
            return 1;
        } else if (this.mobilePhone2 != null && !"".equals(this.mobilePhone2)) {
            return 2;
        } else if (this.phone != null && !"".equals(this.phone)) {
            return 3;
        } else {
            return 4;
        }
    }




    /**
     * 自动增和的年龄
     * @return
     */
    public String getAutoAge() {
        if (this.getBirthday() != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(this.getBirthday());
            int y1 = c.get(Calendar.YEAR);
            c.setTime(new Date());
            int y2 = c.get(Calendar.YEAR);
            int g = y2 - y1;
            return (g > 0) ? String.valueOf(g) : "";
        }
        return "";
    }

    /**
     * 设置短信扩展号码
     * @param exPhone
     */
    public void setExSmsPhone(String exPhone) {
        this.exSmsPhone = exPhone;
    }

    public String getCustomerIndustryName() {
        return customerIndustryName;
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
        return new HashCodeBuilder(-1446133015, 1051904819).appendSuper(super.hashCode())
            .toHashCode();
    }

    public String getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
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

    public Integer getIsShare() {
        return isShare;
    }

    public void setIsShare(Integer isShare) {
        this.isShare = isShare;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    public String getScheduleInfo() {
        return scheduleInfo;
    }

    public void setScheduleInfo(String scheduleInfo) {
        this.scheduleInfo = scheduleInfo;
    }

	public String getCredentialTypeName() {
		return credentialTypeName;
	}

	public void setCredentialTypeName(String credentialTypeName) {
		this.credentialTypeName = credentialTypeName;
	}

	public String getEducationalHistoryName() {
		return educationalHistoryName;
	}

	public void setEducationalHistoryName(String educationalHistoryName) {
		this.educationalHistoryName = educationalHistoryName;
	}

	public String getLivingConditionName() {
		return livingConditionName;
	}

	public void setLivingConditionName(String livingConditionName) {
		this.livingConditionName = livingConditionName;
	}

	public String getMaritalStatusName() {
		return maritalStatusName;
	}

	public void setMaritalStatusName(String maritalStatusName) {
		this.maritalStatusName = maritalStatusName;
	}

	public String getOrgTypeName() {
		return orgTypeName;
	}

	public void setOrgTypeName(String orgTypeName) {
		this.orgTypeName = orgTypeName;
	}

	public String getLegalFormName() {
		return legalFormName;
	}

	public void setLegalFormName(String legalFormName) {
		this.legalFormName = legalFormName;
	}

}