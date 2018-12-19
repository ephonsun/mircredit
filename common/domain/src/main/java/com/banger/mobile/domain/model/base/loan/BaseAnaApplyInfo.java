package com.banger.mobile.domain.model.base.loan;

import com.banger.mobile.domain.model.base.BaseObject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zhangfp
 * @version $Id: BaseAnaApplyInfo v 0.1 ${} 下午3:03 zhangfp Exp $
 */
public class BaseAnaApplyInfo extends BaseObject implements Serializable {
    private static final long serialVersionUID = -3368547622685409941L;

    private Integer applyInfoId;
    private Integer loanId;   //贷款ID
    private String customerName; //客户姓名
    private String idCard;         //身份证
    private String mobilePhone;      //手机号码
    private Integer nativeProvince;  //户籍所在地_省
    private Integer nativeCity;      //户籍所在地_市
    private Integer nativeDistrict;  // 户籍所在地_区
    private String nativeAddress;    //户籍所在地_地址
    private Integer livingProvince;  //长期居住地_省
    private Integer livingCity;      //长期居住地_市
    private Integer livingDistrict;  //长期居住地_区
    private String livingAddress;    //长期居住地_地址
    private String company;          // 工作单位
    private String job;              //工作职务
    private String companyPhone;     // 单位电话
    private BigDecimal annualIncoming;//年收入
    private Integer familyCount;       // 家庭成员人数
    private String familyPhone;        //家庭电话
    private String memo;               //    基本情况描述
    private Date updateDate;           //    更新时间

    public Integer getApplyInfoId() {
        return applyInfoId;
    }

    public void setApplyInfoId(Integer applyInfoId) {
        this.applyInfoId = applyInfoId;
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

    public Integer getNativeProvince() {
        return nativeProvince;
    }

    public void setNativeProvince(Integer nativeProvince) {
        this.nativeProvince = nativeProvince;
    }

    public Integer getNativeCity() {
        return nativeCity;
    }

    public void setNativeCity(Integer nativeCity) {
        this.nativeCity = nativeCity;
    }

    public Integer getNativeDistrict() {
        return nativeDistrict;
    }

    public void setNativeDistrict(Integer nativeDistrict) {
        this.nativeDistrict = nativeDistrict;
    }

    public String getNativeAddress() {
        return nativeAddress;
    }

    public void setNativeAddress(String nativeAddress) {
        this.nativeAddress = nativeAddress;
    }

    public Integer getLivingProvince() {
        return livingProvince;
    }

    public void setLivingProvince(Integer livingProvince) {
        this.livingProvince = livingProvince;
    }

    public Integer getLivingCity() {
        return livingCity;
    }

    public void setLivingCity(Integer livingCity) {
        this.livingCity = livingCity;
    }

    public Integer getLivingDistrict() {
        return livingDistrict;
    }

    public void setLivingDistrict(Integer livingDistrict) {
        this.livingDistrict = livingDistrict;
    }

    public String getLivingAddress() {
        return livingAddress;
    }

    public void setLivingAddress(String livingAddress) {
        this.livingAddress = livingAddress;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
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

    public void setAnnualIncoming(BigDecimal annualIncomming) {
        this.annualIncoming = annualIncomming;
    }

    public Integer getFamilyCount() {
        return familyCount;
    }

    public void setFamilyCount(Integer familyCount) {
        this.familyCount = familyCount;
    }

    public String getFamilyPhone() {
        return familyPhone;
    }

    public void setFamilyPhone(String familyPhone) {
        this.familyPhone = familyPhone;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
