package com.banger.mobile.domain.model.base.loan;

import com.banger.mobile.domain.model.base.BaseObject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zhangfp
 * @version $Id: BaseAnaCoBorrower v 0.1 ${} 下午2:36 zhangfp Exp $
 */
public class BaseAnaCoBorrower extends BaseObject implements Serializable {

    private static final long serialVersionUID = -8871510420519479446L;

    private Integer coBorrowerId; //主键ID
    private Integer loanId;       //贷款Id
    private String customerName;//客户姓名
    private String idCard;               //身份证
    private String mobilePhone;//手机号码
    private String company;            //工作单位
    private String companyJob;//职务
    private String companyPhone;   //单位电话
    private BigDecimal annualIncoming;     //年收入
    private String relation;//与贷款人关系
    private String relationOther;       //与贷款人关系其他
    private Date updateDate;//更新时间

    private String coAnnualIncoming;  //年收入的字符串表达

    public String getCoAnnualIncoming() {
        return coAnnualIncoming;
    }

    public void setCoAnnualIncoming(String coAnnualIncoming) {
        this.coAnnualIncoming = coAnnualIncoming;
    }

    public Integer getCoBorrowerId() {
        return coBorrowerId;
    }

    public void setCoBorrowerId(Integer coBorrowerId) {
        this.coBorrowerId = coBorrowerId;
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

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getRelationOther() {
        return relationOther;
    }

    public void setRelationOther(String relationOther) {
        this.relationOther = relationOther;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
