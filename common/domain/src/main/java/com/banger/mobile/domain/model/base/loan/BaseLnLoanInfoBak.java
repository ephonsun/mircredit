package com.banger.mobile.domain.model.base.loan;

import com.banger.mobile.domain.model.base.BaseObject;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ouyl
 * @version $Id: BaseLnLoanInfo.java,v 0.1 13-6-25 ouyl Exp $
 */
public class BaseLnLoanInfoBak extends BaseObject implements Serializable {
    private Integer   loanInfoId;
    private String    caseNo1;
    private String    caseNo2;
    private String    caseNo3;
    private Integer   loanId;
    private String    bizType;
    private String    serialNumber;
    private Date      genDate;
    private String    cusName;
    private String    cusBirthday;
    private String    cusSex;
    private String    cusIdtype;
    private String    cusIdcard;
    private String    cusMarriage;
    private String    isLocal;
    private String    cusLocalYear;
    private String    cusAddress;
    private String    cusFamilyNum;
    private String    cusLivingStatus;
    private String    cusEducation;
    private String    cusVocation;
    private String    cusCompany;
    private String    cusJob;
    private String    cusPhone;
    private String    cusMobilePhone;
    private String    cusCompanyAddress;
    private String    cusWorkYear;
    private String    cusMonthlyIncoming;
    private String    cusSelfBiz;
    private String    mateName;
    private String    mateIdcard;
    private String    mateVocation;
    private String    mateWorkYear;
    private String    matePhone;
    private String    mateMobilePhone;
    private String    mateCompany;
    private String    mateJob;
    private String    mateAddress;
    private String    mateMonthlyIncoming;
    private String    chdName;
    private String    chdSex;
    private String    chdHealth;
    private String    chdBirthday;
    private String    chdCompany;
    private String    chdEducation;
    private String    chdParentInfo;
    private String    appMoney;
    private String    appUsage;
    private String    appLimitYear;
    private String    appRepaymentDate;
    private String    appRepaymentMonth;
    private String    appRepaymentSource;
    private String    appGnt;
    private String    appGntIdcard;
    private String    appGntPhone;
    private String    appGntRelation;
    private String    appGntCompany;
    private String    appGntJob;
    private String    appGntAddress;
    private String    appGntSalary;
    private String    appGntProperty;
    private String    bizCompany;
    private String    bizScope;
    private String    bizAddress;
    private String    bizStartDate;
    private String    bizOrg;
    private String    bizDetail;
    private String    bizOrgCode;
    private String    bizEmployeeNum;
    private String    bizCompanyType;
    private String    bizPlace;
    private String    bizArea;
    private String    bizHouseRent;
    private String    bizLicence;
    private String    cfaAnnualSales;
    private String    cfaGrossMargin;
    private String    cfaYearMargin;
    private String    cfaProfitLevel;
    private String    cfaInventory;
    private String    cfaAccountIncoming;
    private String    cfaDebt;
    private String    cfaTotal;
    private String    cfaOther;
    private String    cfaSupply;
    private Date updateDate;
    private String loanAccount;
    /////////////下面2014-10-20新增字段
    private String resultLimtYear; //决议期限
    private String resultMoney; //决议金额
    private String resultRate; //贷款利率
    private String resultPoint;//小贷积分
    private String lowerRate;//下浮利率
    private String orgCode;//机构编码
    private String orgName;//机构名称
    private String  bizLegal;//法人代表
    private String  bizLegalIdcard;//法人代表身份证
    private String  infoSource;//信息来源
    private String  mateIdtype;//配偶证件类型
    private String  creditType;//授信品种
    private String  appMonthRate;//申请月利率
    private String  appGntType;//担保类型
    private String  appGntMemo;//担保情况描述
    private String  mateOthersInfo;//其他家庭信息
    private String  bizPlaceType;//生意经营场所性质
    private String  cusCompanyPhone;//申请人单位电话

    // Constructors

    /** default constructor */
    public BaseLnLoanInfoBak() {
    }

    /** minimal constructor */
    public BaseLnLoanInfoBak(Integer loanInfoId, Integer loanId) {
        this.loanInfoId = loanInfoId;
        this.loanId = loanId;
    }

    /** full constructor */
    public BaseLnLoanInfoBak(Integer loanInfoId, Integer loanId,String bizType,String serialNumber,
                      Date genDate, String cusName, String cusBirthday,
                      String cusSex, String cusIdtype, String cusIdcard, String cusMarriage,String  isLocal,
                      String cusLocalYear, String cusAddress, String cusFamilyNum,
                      String cusLivingStatus, String cusEducation, String cusVocation,
                      String cusCompany, String cusJob, String cusPhone, String cusMobilePhone,
                      String cusCompanyAddress, String cusWorkYear, String cusMonthlyIncoming,
                      String cusSelfBiz, String mateName, String mateIdcard, String mateVocation,
                      String mateWorkYear, String matePhone, String mateMobilePhone,
                      String mateCompany, String mateJob, String mateAddress,
                      String mateMonthlyIncoming, String chdName, String chdSex, String chdHealth,
                      String chdBirthday, String chdCompany, String chdEducation,
                      String chdParentInfo, String appMoney, String appUsage, String appLimitYear,
                      String appRepaymentDate, String appRepaymentMonth, String appRepaymentSource,
                      String appGnt, String appGntIdcard, String appGntPhone,
                      String appGntRelation, String appGntCompany, String appGntJob,
                      String appGntAddress, String appGntSalary, String appGntProperty,
                      String bizCompany, String bizScope, String bizAddress, String bizStartDate,
                      String bizOrg, String bizDetail, String bizOrgCode, String bizEmployeeNum,
                      String bizCompanyType, String bizPlace, String bizArea, String bizHouseRent,
                      String bizLicence, String cfaAnnualSales, String cfaGrossMargin,
                      String cfaYearMargin, String cfaProfitLevel, String cfaInventory,
                      String cfaAccountIncoming, String cfaDebt, String cfaTotal, String cfaOther,
                      String cfaSupply, Date updateDate, String loanAccount,String resultLimtYear,
                      String resultMoney,String resultRate,String resultPoint,String lowerRate,
                      String orgCode,String orgName,String  bizLegal,String  bizLegalIdcard,
                      String  infoSource,String  mateIdtype,String  creditType,String  appMonthRate,
                      String  appGntType,String  appGntMemo,String  mateOthersInfo,String  bizPlaceType,
                      String  cusCompanyPhone) {
        this.loanInfoId = loanInfoId;
        this.loanId = loanId;
        this.bizType=bizType;
        this.serialNumber=serialNumber;
        this.genDate=genDate;
        this.cusName = cusName;
        this.cusBirthday = cusBirthday;
        this.cusSex = cusSex;
        this.cusIdtype = cusIdtype;
        this.cusIdcard = cusIdcard;
        this.cusMarriage = cusMarriage;
        this. isLocal = isLocal;
        this.cusLocalYear = cusLocalYear;
        this.cusAddress = cusAddress;
        this.cusFamilyNum = cusFamilyNum;
        this.cusLivingStatus = cusLivingStatus;
        this.cusEducation = cusEducation;
        this.cusVocation = cusVocation;
        this.cusCompany = cusCompany;
        this.cusJob = cusJob;
        this.cusPhone = cusPhone;
        this.cusMobilePhone = cusMobilePhone;
        this.cusCompanyAddress = cusCompanyAddress;
        this.cusWorkYear = cusWorkYear;
        this.cusMonthlyIncoming = cusMonthlyIncoming;
        this.cusSelfBiz = cusSelfBiz;
        this.mateName = mateName;
        this.mateIdcard = mateIdcard;
        this.mateVocation = mateVocation;
        this.mateWorkYear = mateWorkYear;
        this.matePhone = matePhone;
        this.mateMobilePhone = mateMobilePhone;
        this.mateCompany = mateCompany;
        this.mateJob = mateJob;
        this.mateAddress = mateAddress;
        this.mateMonthlyIncoming = mateMonthlyIncoming;
        this.chdName = chdName;
        this.chdSex = chdSex;
        this.chdHealth = chdHealth;
        this.chdBirthday = chdBirthday;
        this.chdCompany = chdCompany;
        this.chdEducation = chdEducation;
        this.chdParentInfo = chdParentInfo;
        this.appMoney = appMoney;
        this.appUsage = appUsage;
        this.appLimitYear = appLimitYear;
        this.appRepaymentDate = appRepaymentDate;
        this.appRepaymentMonth = appRepaymentMonth;
        this.appRepaymentSource = appRepaymentSource;
        this.appGnt = appGnt;
        this.appGntIdcard = appGntIdcard;
        this.appGntPhone = appGntPhone;
        this.appGntRelation = appGntRelation;
        this.appGntCompany = appGntCompany;
        this.appGntJob = appGntJob;
        this.appGntAddress = appGntAddress;
        this.appGntSalary = appGntSalary;
        this.appGntProperty = appGntProperty;
        this.bizCompany = bizCompany;
        this.bizScope = bizScope;
        this.bizAddress = bizAddress;
        this.bizStartDate = bizStartDate;
        this.bizOrg = bizOrg;
        this.bizDetail = bizDetail;
        this.bizOrgCode = bizOrgCode;
        this.bizEmployeeNum = bizEmployeeNum;
        this.bizCompanyType = bizCompanyType;
        this.bizPlace = bizPlace;
        this.bizArea = bizArea;
        this.bizHouseRent = bizHouseRent;
        this.bizLicence = bizLicence;
        this.cfaAnnualSales = cfaAnnualSales;
        this.cfaGrossMargin = cfaGrossMargin;
        this.cfaYearMargin = cfaYearMargin;
        this.cfaProfitLevel = cfaProfitLevel;
        this.cfaInventory = cfaInventory;
        this.cfaAccountIncoming = cfaAccountIncoming;
        this.cfaDebt = cfaDebt;
        this.cfaTotal = cfaTotal;
        this.cfaOther = cfaOther;
        this.cfaSupply = cfaSupply;
        this.updateDate = updateDate;
        this.loanAccount =loanAccount;
        this.resultLimtYear=resultLimtYear;
        this.resultMoney=resultMoney;
        this.resultPoint=resultPoint;
        this.resultRate=resultRate;
        this.lowerRate=lowerRate;
        this.orgCode=orgCode;
        this.orgName=orgName;
        this.bizLegal=bizLegal;
        this.bizLegalIdcard=bizLegalIdcard;
        this.infoSource=infoSource;
        this.mateIdtype=mateIdtype;
        this.creditType=creditType;
        this.appMonthRate=appMonthRate;
        this.appGntType=appGntType;
        this.appGntMemo=appGntMemo;
        this.mateOthersInfo=mateOthersInfo;
        this.bizPlaceType=bizPlaceType;
        this.cusCompanyPhone=cusCompanyPhone;

    }

    public Integer getLoanInfoId() {
        return this.loanInfoId;
    }

    public void setLoanInfoId(Integer loanInfoId) {
        this.loanInfoId = loanInfoId;
    }

    public Integer getLoanId() {
        return this.loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Date getGenDate() {
        return genDate;
    }

    public void setGenDate(Date genDate) {
        this.genDate = genDate;
    }

    public String getCusName() {
        return this.cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCusBirthday() {
        return this.cusBirthday;
    }

    public void setCusBirthday(String cusBirthday) {
        this.cusBirthday = cusBirthday;
    }

    public String getCusSex() {
        return this.cusSex;
    }

    public void setCusSex(String cusSex) {
        this.cusSex = cusSex;
    }

    public String getCusIdtype() {
        return this.cusIdtype;
    }

    public void setCusIdtype(String cusIdtype) {
        this.cusIdtype = cusIdtype;
    }

    public String getCusIdcard() {
        return this.cusIdcard;
    }

    public void setCusIdcard(String cusIdcard) {
        this.cusIdcard = cusIdcard;
    }

    public String getCusMarriage() {
        return this.cusMarriage;
    }

    public void setCusMarriage(String cusMarriage) {
        this.cusMarriage = cusMarriage;
    }

    public String getIsLocal() {
        return isLocal;
    }

    public void setIsLocal(String isLocal) {
        this.isLocal = isLocal;
    }

    public String getCusLocalYear() {
        return this.cusLocalYear;
    }

    public void setCusLocalYear(String cusLocalYear) {
        this.cusLocalYear = cusLocalYear;
    }

    public String getCusAddress() {
        return this.cusAddress;
    }

    public void setCusAddress(String cusAddress) {
        this.cusAddress = cusAddress;
    }

    public String getCusFamilyNum() {
        return this.cusFamilyNum;
    }

    public void setCusFamilyNum(String cusFamilyNum) {
        this.cusFamilyNum = cusFamilyNum;
    }

    public String getCusLivingStatus() {
        return this.cusLivingStatus;
    }

    public void setCusLivingStatus(String cusLivingStatus) {
        this.cusLivingStatus = cusLivingStatus;
    }

    public String getCusEducation() {
        return this.cusEducation;
    }

    public void setCusEducation(String cusEducation) {
        this.cusEducation = cusEducation;
    }

    public String getCusVocation() {
        return this.cusVocation;
    }

    public void setCusVocation(String cusVocation) {
        this.cusVocation = cusVocation;
    }

    public String getCusCompany() {
        return this.cusCompany;
    }

    public void setCusCompany(String cusCompany) {
        this.cusCompany = cusCompany;
    }

    public String getCusJob() {
        return this.cusJob;
    }

    public void setCusJob(String cusJob) {
        this.cusJob = cusJob;
    }

    public String getCusPhone() {
        return this.cusPhone;
    }

    public void setCusPhone(String cusPhone) {
        this.cusPhone = cusPhone;
    }

    public String getCusMobilePhone() {
        return this.cusMobilePhone;
    }

    public void setCusMobilePhone(String cusMobilePhone) {
        this.cusMobilePhone = cusMobilePhone;
    }

    public String getCusCompanyAddress() {
        return this.cusCompanyAddress;
    }

    public void setCusCompanyAddress(String cusCompanyAddress) {
        this.cusCompanyAddress = cusCompanyAddress;
    }

    public String getCusWorkYear() {
        return this.cusWorkYear;
    }

    public void setCusWorkYear(String cusWorkYear) {
        this.cusWorkYear = cusWorkYear;
    }

    public String getCusMonthlyIncoming() {
        return this.cusMonthlyIncoming;
    }

    public void setCusMonthlyIncoming(String cusMonthlyIncoming) {
        this.cusMonthlyIncoming = cusMonthlyIncoming;
    }

    public String getCusSelfBiz() {
        return this.cusSelfBiz;
    }

    public void setCusSelfBiz(String cusSelfBiz) {
        this.cusSelfBiz = cusSelfBiz;
    }

    public String getMateName() {
        return this.mateName;
    }

    public void setMateName(String mateName) {
        this.mateName = mateName;
    }

    public String getMateIdcard() {
        return this.mateIdcard;
    }

    public void setMateIdcard(String mateIdcard) {
        this.mateIdcard = mateIdcard;
    }

    public String getMateVocation() {
        return this.mateVocation;
    }

    public void setMateVocation(String mateVocation) {
        this.mateVocation = mateVocation;
    }

    public String getMateWorkYear() {
        return this.mateWorkYear;
    }

    public void setMateWorkYear(String mateWorkYear) {
        this.mateWorkYear = mateWorkYear;
    }

    public String getMatePhone() {
        return this.matePhone;
    }

    public void setMatePhone(String matePhone) {
        this.matePhone = matePhone;
    }

    public String getMateMobilePhone() {
        return this.mateMobilePhone;
    }

    public void setMateMobilePhone(String mateMobilePhone) {
        this.mateMobilePhone = mateMobilePhone;
    }

    public String getMateCompany() {
        return this.mateCompany;
    }

    public void setMateCompany(String mateCompany) {
        this.mateCompany = mateCompany;
    }

    public String getMateJob() {
        return this.mateJob;
    }

    public void setMateJob(String mateJob) {
        this.mateJob = mateJob;
    }

    public String getMateAddress() {
        return this.mateAddress;
    }

    public void setMateAddress(String mateAddress) {
        this.mateAddress = mateAddress;
    }

    public String getMateMonthlyIncoming() {
        return this.mateMonthlyIncoming;
    }

    public void setMateMonthlyIncoming(String mateMonthlyIncoming) {
        this.mateMonthlyIncoming = mateMonthlyIncoming;
    }

    public String getChdName() {
        return this.chdName;
    }

    public void setChdName(String chdName) {
        this.chdName = chdName;
    }

    public String getChdSex() {
        return this.chdSex;
    }

    public void setChdSex(String chdSex) {
        this.chdSex = chdSex;
    }

    public String getChdHealth() {
        return this.chdHealth;
    }

    public void setChdHealth(String chdHealth) {
        this.chdHealth = chdHealth;
    }

    public String getChdBirthday() {
        return this.chdBirthday;
    }

    public void setChdBirthday(String chdBirthday) {
        this.chdBirthday = chdBirthday;
    }

    public String getChdCompany() {
        return this.chdCompany;
    }

    public void setChdCompany(String chdCompany) {
        this.chdCompany = chdCompany;
    }

    public String getChdEducation() {
        return this.chdEducation;
    }

    public void setChdEducation(String chdEducation) {
        this.chdEducation = chdEducation;
    }

    public String getChdParentInfo() {
        return this.chdParentInfo;
    }

    public void setChdParentInfo(String chdParentInfo) {
        this.chdParentInfo = chdParentInfo;
    }

    public String getAppMoney() {
        return this.appMoney;
    }

    public void setAppMoney(String appMoney) {
        this.appMoney = appMoney;
    }

    public String getAppUsage() {
        return this.appUsage;
    }

    public void setAppUsage(String appUsage) {
        this.appUsage = appUsage;
    }

    public String getAppLimitYear() {
        return this.appLimitYear;
    }

    public void setAppLimitYear(String appLimitYear) {
        this.appLimitYear = appLimitYear;
    }

    public String getAppRepaymentDate() {
        return this.appRepaymentDate;
    }

    public void setAppRepaymentDate(String appRepaymentDate) {
        this.appRepaymentDate = appRepaymentDate;
    }

    public String getAppRepaymentMonth() {
        return this.appRepaymentMonth;
    }

    public void setAppRepaymentMonth(String appRepaymentMonth) {
        this.appRepaymentMonth = appRepaymentMonth;
    }

    public String getAppRepaymentSource() {
        return this.appRepaymentSource;
    }

    public void setAppRepaymentSource(String appRepaymentSource) {
        this.appRepaymentSource = appRepaymentSource;
    }

    public String getAppGnt() {
        return this.appGnt;
    }

    public void setAppGnt(String appGnt) {
        this.appGnt = appGnt;
    }

    public String getAppGntIdcard() {
        return this.appGntIdcard;
    }

    public void setAppGntIdcard(String appGntIdcard) {
        this.appGntIdcard = appGntIdcard;
    }

    public String getAppGntPhone() {
        return this.appGntPhone;
    }

    public void setAppGntPhone(String appGntPhone) {
        this.appGntPhone = appGntPhone;
    }

    public String getAppGntRelation() {
        return this.appGntRelation;
    }

    public void setAppGntRelation(String appGntRelation) {
        this.appGntRelation = appGntRelation;
    }

    public String getAppGntCompany() {
        return this.appGntCompany;
    }

    public void setAppGntCompany(String appGntCompany) {
        this.appGntCompany = appGntCompany;
    }

    public String getAppGntJob() {
        return this.appGntJob;
    }

    public void setAppGntJob(String appGntJob) {
        this.appGntJob = appGntJob;
    }

    public String getAppGntAddress() {
        return this.appGntAddress;
    }

    public void setAppGntAddress(String appGntAddress) {
        this.appGntAddress = appGntAddress;
    }

    public String getAppGntSalary() {
        return this.appGntSalary;
    }

    public void setAppGntSalary(String appGntSalary) {
        this.appGntSalary = appGntSalary;
    }

    public String getAppGntProperty() {
        return this.appGntProperty;
    }

    public void setAppGntProperty(String appGntProperty) {
        this.appGntProperty = appGntProperty;
    }

    public String getBizCompany() {
        return this.bizCompany;
    }

    public void setBizCompany(String bizCompany) {
        this.bizCompany = bizCompany;
    }

    public String getBizScope() {
        return this.bizScope;
    }

    public void setBizScope(String bizScope) {
        this.bizScope = bizScope;
    }

    public String getBizAddress() {
        return this.bizAddress;
    }

    public void setBizAddress(String bizAddress) {
        this.bizAddress = bizAddress;
    }

    public String getBizStartDate() {
        return this.bizStartDate;
    }

    public void setBizStartDate(String bizStartDate) {
        this.bizStartDate = bizStartDate;
    }

    public String getBizOrg() {
        return this.bizOrg;
    }

    public void setBizOrg(String bizOrg) {
        this.bizOrg = bizOrg;
    }

    public String getBizDetail() {
        return this.bizDetail;
    }

    public void setBizDetail(String bizDetail) {
        this.bizDetail = bizDetail;
    }

    public String getBizOrgCode() {
        return this.bizOrgCode;
    }

    public void setBizOrgCode(String bizOrgCode) {
        this.bizOrgCode = bizOrgCode;
    }

    public String getBizEmployeeNum() {
        return this.bizEmployeeNum;
    }

    public void setBizEmployeeNum(String bizEmployeeNum) {
        this.bizEmployeeNum = bizEmployeeNum;
    }

    public String getBizCompanyType() {
        return this.bizCompanyType;
    }

    public void setBizCompanyType(String bizCompanyType) {
        this.bizCompanyType = bizCompanyType;
    }

    public String getBizPlace() {
        return this.bizPlace;
    }

    public void setBizPlace(String bizPlace) {
        this.bizPlace = bizPlace;
    }

    public String getBizArea() {
        return this.bizArea;
    }

    public void setBizArea(String bizArea) {
        this.bizArea = bizArea;
    }

    public String getBizHouseRent() {
        return this.bizHouseRent;
    }

    public void setBizHouseRent(String bizHouseRent) {
        this.bizHouseRent = bizHouseRent;
    }

    public String getBizLicence() {
        return this.bizLicence;
    }

    public void setBizLicence(String bizLicence) {
        this.bizLicence = bizLicence;
    }

    public String getCfaAnnualSales() {
        return this.cfaAnnualSales;
    }

    public void setCfaAnnualSales(String cfaAnnualSales) {
        this.cfaAnnualSales = cfaAnnualSales;
    }

    public String getCfaGrossMargin() {
        return this.cfaGrossMargin;
    }

    public void setCfaGrossMargin(String cfaGrossMargin) {
        this.cfaGrossMargin = cfaGrossMargin;
    }

    public String getCfaYearMargin() {
        return this.cfaYearMargin;
    }

    public void setCfaYearMargin(String cfaYearMargin) {
        this.cfaYearMargin = cfaYearMargin;
    }

    public String getCfaProfitLevel() {
        return this.cfaProfitLevel;
    }

    public void setCfaProfitLevel(String cfaProfitLevel) {
        this.cfaProfitLevel = cfaProfitLevel;
    }

    public String getCfaInventory() {
        return this.cfaInventory;
    }

    public void setCfaInventory(String cfaInventory) {
        this.cfaInventory = cfaInventory;
    }

    public String getCfaAccountIncoming() {
        return this.cfaAccountIncoming;
    }

    public void setCfaAccountIncoming(String cfaAccountIncoming) {
        this.cfaAccountIncoming = cfaAccountIncoming;
    }

    public String getCfaDebt() {
        return this.cfaDebt;
    }

    public void setCfaDebt(String cfaDebt) {
        this.cfaDebt = cfaDebt;
    }

    public String getCfaTotal() {
        return this.cfaTotal;
    }

    public void setCfaTotal(String cfaTotal) {
        this.cfaTotal = cfaTotal;
    }

    public String getCfaOther() {
        return this.cfaOther;
    }

    public void setCfaOther(String cfaOther) {
        this.cfaOther = cfaOther;
    }

    public String getCfaSupply() {
        return this.cfaSupply;
    }

    public void setCfaSupply(String cfaSupply) {
        this.cfaSupply = cfaSupply;
    }

    public Date getUpdateDate() {
        return this.updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getLoanAccount() {
        return loanAccount;
    }

    public void setLoanAccount(String loanAccount) {
        this.loanAccount = loanAccount;
    }

    public void setResultLimtYear(String resultLimtYear) {
        this.resultLimtYear = resultLimtYear;
    }

    public String getResultLimtYear() {
        return resultLimtYear;
    }

    public String getResultMoney() {
        return resultMoney;
    }

    public void setResultMoney(String resultMoney) {
        this.resultMoney = resultMoney;
    }

    public String getResultPoint() {
        return resultPoint;
    }

    public void setResultPoint(String resultPoint) {
        this.resultPoint = resultPoint;
    }

    public String getResultRate() {
        return resultRate;
    }

    public void setResultRate(String resultRate) {
        this.resultRate = resultRate;
    }

    public String getLowerRate() {
        return lowerRate;
    }

    public void setLowerRate(String lowerRate) {
        this.lowerRate = lowerRate;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public String getBizLegal() {
        return bizLegal;
    }

    public String getBizLegalIdcard() {
        return bizLegalIdcard;
    }

    public String getInfoSource() {
        return infoSource;
    }

    public String getMateIdtype() {
        return mateIdtype;
    }

    public String getCreditType() {
        return creditType;
    }

    public String getAppMonthRate() {
        return appMonthRate;
    }

    public String getAppGntType() {
        return appGntType;
    }

    public String getAppGntMemo() {
        return appGntMemo;
    }

    public String getMateOthersInfo() {
        return mateOthersInfo;
    }

    public String getBizPlaceType() {
        return bizPlaceType;
    }

    public String getCusCompanyPhone() {
        return cusCompanyPhone;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public void setBizLegal(String bizLegal) {
        this.bizLegal = bizLegal;
    }

    public void setBizLegalIdcard(String bizLegalIdcard) {
        this.bizLegalIdcard = bizLegalIdcard;
    }

    public void setInfoSource(String infoSource) {
        this.infoSource = infoSource;
    }

    public void setMateIdtype(String mateIdtype) {
        this.mateIdtype = mateIdtype;
    }

    public void setCreditType(String creditType) {
        this.creditType = creditType;
    }

    public void setAppMonthRate(String appMonthRate) {
        this.appMonthRate = appMonthRate;
    }

    public void setAppGntType(String appGntType) {
        this.appGntType = appGntType;
    }

    public void setAppGntMemo(String appGntMemo) {
        this.appGntMemo = appGntMemo;
    }

    public void setMateOthersInfo(String mateOthersInfo) {
        this.mateOthersInfo = mateOthersInfo;
    }

    public void setBizPlaceType(String bizPlaceType) {
        this.bizPlaceType = bizPlaceType;
    }

    public void setCusCompanyPhone(String cusCompanyPhone) {
        this.cusCompanyPhone = cusCompanyPhone;
    }

    public String getCaseNo1() {
        return caseNo1;
    }

    public String getCaseNo2() {
        return caseNo2;
    }

    public String getCaseNo3() {
        return caseNo3;
    }

    public void setCaseNo1(String caseNo1) {
        this.caseNo1 = caseNo1;
    }

    public void setCaseNo2(String caseNo2) {
        this.caseNo2 = caseNo2;
    }

    public void setCaseNo3(String caseNo3) {
        this.caseNo3 = caseNo3;
    }
}
