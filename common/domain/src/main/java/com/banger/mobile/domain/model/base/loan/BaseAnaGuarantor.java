package com.banger.mobile.domain.model.base.loan;

import com.banger.mobile.domain.model.base.BaseObject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zhangfp
 * @version $Id: BaseAnaGuarantor v 0.1 ${} 下午2:49 zhangfp Exp $
 */
public class BaseAnaGuarantor extends BaseObject implements Serializable {

    private static final long serialVersionUID = 865504450898165736L;

    private Integer guarantorId;  //主键Id
    private Integer loanId;       //贷款ID
    private String customerName;//客户姓名
    private String idCard;//身份证
    private String mobilePhone;  //手机号码
    private String company;//工作单位
    private String companyJob;     //职务
    private String companyPhone;//单位电话
    private BigDecimal annualIncoming;  //年收入
    private String mainProperty;//主要资产
    private String hasDebt;             //是否有对外债务
    private BigDecimal debt;//对外债务金额
    private String ensure;//担保
    private String relation;   //与贷款人关系
    private Date updateDate;  //更新时间

    private String guaAnnualIncoming;         //年收入的字符串表达
    private String guaDebt;   //债务金额的字符表达

    public String getGuaAnnualIncoming() {
        return guaAnnualIncoming;
    }

    public void setGuaAnnualIncoming(String guaAnnualIncoming) {
        this.guaAnnualIncoming = guaAnnualIncoming;
    }

    public String getGuaDebt() {
        return guaDebt;
    }

    public void setGuaDebt(String guaDebt) {
        this.guaDebt = guaDebt;
    }

    public Integer getGuarantorId() {
        return guarantorId;
    }

    public void setGuarantorId(Integer gurantorId) {
        this.guarantorId = gurantorId;
    }

    public Integer getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompanyJob() {
        return companyJob;
    }

    public void setCompanyJob(String companyJob) {
        this.companyJob = companyJob;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public BigDecimal getAnnualIncoming() {
        return annualIncoming;
    }

    public void setAnnualIncoming(BigDecimal annualIncoming) {
        this.annualIncoming = annualIncoming;
    }

    public String getMainProperty() {
        return mainProperty;
    }

    public void setMainProperty(String mainProperty) {
        this.mainProperty = mainProperty;
    }

    public String getHasDebt() {
        return hasDebt;
    }

    public void setHasDebt(String hasDebt) {
        this.hasDebt = hasDebt;
    }

    public BigDecimal getDebt() {
        return debt;
    }

    public void setDebt(BigDecimal debt) {
        this.debt = debt;
    }

    public String getEnsure() {
        return ensure;
    }

    public void setEnsure(String ensure) {
        this.ensure = ensure;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
