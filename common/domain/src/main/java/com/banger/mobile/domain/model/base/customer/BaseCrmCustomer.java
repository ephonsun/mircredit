package com.banger.mobile.domain.model.base.customer;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
/**
 * CrmCustomer entity. @author MyEclipse Persistence Tools
 */

public class BaseCrmCustomer extends BaseObject implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6733978577073367234L;
	private Integer customerId;				//客户ID
	private String customerName;			//客户姓名
	private String customerNamePinyin;		//拼音缩写
	private String sex;						//性别
	private String customerNo;				//客户编号
	private String customerTitle;			//称谓
	private Integer age;					//年龄
	private Date birthday;					//生日
	private Integer customerTypeId;			//客户类型ID
	private Integer customerIndustryId;		//所处行业ID
	private String idCard;					//身份证
	private String company;					//单位
	private String remark;					//简介
	private String headshow;				//头像
	private String province;				//省份
	private String city;					//城市
	private String address;					//地址
	protected Integer defaultPhoneType;		//默认号码类型
	protected String mobilePhone1;			//手机1
	protected String mobilePhone1Remark;		//手机1备注
	protected String mobilePhone2;			//手机2
	protected String mobilePhone2Remark;		//手机2备注
	protected String phone;					//固话
	protected String phoneExt;				//固话分机
	protected String fax;						//传真
	protected String faxExt;					//传真分机
	private String email;					//邮箱
	private String templateIds;				//业务ID
	private Integer isTrash;				//是否在垃圾箱
	private Integer isDel;					//是否彻底删除
	private Integer belongDeptId;			//归属机构ID
	private Integer belongUserId;			//归属人员ID
	private Integer isVisit;				//是否是拜访客户
	private Date lastContactDate;			//最后联系时间
	private String lastContactType;			//最后联系类型
	private Integer isReceiveSms;			//是否愿意接收短信
	private String mobilePhone1Regular;		//手机1号码规则
	private String mobilePhone2Regular;		//手机2号码规刚
	private Date createDate;				//创建时间
	private Date updateDate;				//更新时间
	private Integer createUser;				//新建者
	private Integer updateUser;				//修改者
	private String memo;				    //冗余字段 快速搜索
	private Integer isNoGood;               //是否是不良客户
	//--------------------------------------------------------
	private Integer credentialTypeId; 		//证件类型
	private Integer educationalHistoryId;	//教育程度
	private Integer livingConditionId;		//居住情况
	private String unitProperty;			//单位性质
	private String position;				//工作岗位 职务
	private String workingSeniority;		//工作年限
	//--------------------------------------------------------
	private Integer maritalStatusId;		//婚姻状况
	private String spouseName;				//配偶名称
	private String spouseIdCard;			//配偶身份证号码
	private String spouseMobilePhone;		//手机
	private String spousePhone;				//固话
	private String spouseCompany;			//工作单位
	private String spouseWorkingSeniority;	//工作年限
	private String spouseCompanyPhone;		//单位电话
	private String spouseUnitProperty;		//单位性质
	private String spousePosition;			//工作岗位
	private String spouseCompanyAddress;	//单位地址
	//-------------------------------------------------------
	private String manageCompany;			//企业名称
	private String manageScope;				//经营范围
	private Integer orgTypeId;				//组织形式
	private Date openingDate;				//开业日期
	private String orgCode;					//组织机构代码证
	private String bussinessLicence;		//营业执照
	private Integer legalFormId;			//法律形式
	private String artificialPerson;		//法人代表
	private String marketingManager;		//实际经营者
	private String employeeCount;			//雇员人数
	private Integer isInnerCustomer;		//是否我行客户
	private String managePhone;				//企业固话
	private String manageAddress;			//实际经营地址
	private String manageRemark;			//备注
	private String isContinue;              //未转贷客户标记 0退出|1已转贷

	private String livingAddress;              //--居住住址
	private String dwellCode;              //居住地址邮政编码
	private String censusRegister;              //--户籍所在地
	private String houseatt;              //--住宅属性
	private String rank;              //--职称
	private String occupation;              //--职业
	private String bgnyear;              //--工作起始年份
	private String worktel;              //--单位电话
	private String spouseSex;              //--配偶性别
	private String spouseRank;              //--配偶职称

/*
 * 
 * 	MANAGE_REMARK
	MANAGE_ADDRESS
	MANAGE_PHONE
	IS_INNER_CUSTOMER
	EMPLOYEE_COUNT
	MARKETING_MANAGER
	ARTIFICIAL_PERSON
	LEGAL_FORM_ID
	BUSINESS_LICENCE
	ORG_CODE
	OPENING_DATE
	ORG_TYPE_ID
	MANAGE_SCOPE
	MANAGE_COMPANY
	--
	SPOUSE_COMPANY_ADDRESS
	SPOUSE_POSITON
	SPOUSE_UNIT_PROPERTY
	SPOUSE_COMPANY_PHONE
	SPOUSE_WORKING_SENIORITY
	SPOUSE_COMPANY
	SPOUSE_PHONE
	SPOUSE_MOBILE_PHONE
	SPOUSE_ID_CARD
	SPOUSE_NAME
	MARITAL_STATUS_ID
	----
 * 	WORKING_SENIORITY
	POSITON
	UNIT_PROPERTY
	LIVING_CONDITION_ID
	EDUCATIONAL_HISTORY_ID
	CREDENTIAL_TYPE_ID
	---
*/
	
	// Constructors

	/** default constructor */
	public BaseCrmCustomer() {
	}


	public String getFirstNotNullPhone() {
		if (StringUtils.isNotBlank(this.mobilePhone1)) {
			return this.mobilePhone1;
		} else if (this.mobilePhone2 != null && !"".equals(this.mobilePhone2)) {
			return this.mobilePhone2;
		} else if (this.phone != null && !"".equals(this.phone)) {
			return this.phone;
		} else {
			return "";
		}
	}
	
	/** full constructor */
	public BaseCrmCustomer(Integer customerId, String customerName, String customerNamePinyin,
                           String sex, String customerNo, String customerTitle, Integer age,
                           Date birthday, Integer customerTypeId, Integer customerIndustryId,
                           String idCard, String company, String remark, String headshow,
                           String province, String city, String address, Integer defaultPhoneType,
                           String mobilePhone1, String mobilePhone1Remark, String mobilePhone2,
                           String mobilePhone2Remark, String phone, String phoneExt, String fax,
                           String faxExt, String email, String templateIds, Integer isTrash,
                           Integer isDel, Integer belongDeptId, Integer belongUserId,
                           Integer isVisit, Date lastContactDate, String lastContactType,
                           Integer isReceiveSms, String mobilePhone1Regular,
                           String mobilePhone2Regular, Date createDate, Date updateDate,
                           Integer createUser, Integer updateUser, String memo, Integer isNoGood,
                           Integer credentialTypeId,Integer educationalHistoryId,
                           Integer livingConditionId,String unitProperty,String position,String workingSeniority,
                           Integer maritalStatusId, String spouseName,String spouseIdCard, String spouseMobilePhone,
                           String spousePhone, String spouseCompany,String spouseWorkingSeniority,
                           String spouseCompanyPhone,String spouseUnitProperty, String spousePosition,
                           String spouseCompanyAddress,String manageCompany,String manageScope,Integer orgTypeId,
                           Date openingDate,String orgCode,String bussinessLicence,Integer legalFormId, 
                           String artificialPerson, String employeeCount,Integer isInnerCustomer, 
                           String managePhone,String manageAddress,String manageRemark,String marketingManager) {
        super();
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerNamePinyin = customerNamePinyin;
        this.sex = sex;
        this.customerNo = customerNo;
        this.customerTitle = customerTitle;
        this.age = age;
        this.birthday = birthday;
        this.customerTypeId = customerTypeId;
        this.customerIndustryId = customerIndustryId;
        this.idCard = idCard;
        this.company = company;
        this.remark = remark;
        this.headshow = headshow;
        this.province = province;
        this.city = city;
        this.address = address;
        this.defaultPhoneType = defaultPhoneType;
        this.mobilePhone1 = mobilePhone1;
        this.mobilePhone1Remark = mobilePhone1Remark;
        this.mobilePhone2 = mobilePhone2;
        this.mobilePhone2Remark = mobilePhone2Remark;
        this.phone = phone;
        this.phoneExt = phoneExt;
        this.fax = fax;
        this.faxExt = faxExt;
        this.email = email;
        this.templateIds = templateIds;
        this.isTrash = isTrash;
        this.isDel = isDel;
        this.belongDeptId = belongDeptId;
        this.belongUserId = belongUserId;
        this.isVisit = isVisit;
        this.lastContactDate = lastContactDate;
        this.lastContactType = lastContactType;
        this.isReceiveSms = isReceiveSms;
        this.mobilePhone1Regular = mobilePhone1Regular;
        this.mobilePhone2Regular = mobilePhone2Regular;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.createUser = createUser;
        this.updateUser = updateUser;
        this.memo = memo;
        this.isNoGood = isNoGood;
        this.credentialTypeId=credentialTypeId;
        this.educationalHistoryId=educationalHistoryId;		
        this.livingConditionId=livingConditionId;
        this.unitProperty =unitProperty;
        this.position =position;
        this.workingSeniority =workingSeniority;
        this.maritalStatusId=maritalStatusId;
        this.spouseName =spouseName;
        this.spouseIdCard=spouseIdCard;
        this.spouseMobilePhone=spouseMobilePhone;
        this.spousePhone=spousePhone;
        this.spouseCompany=spouseCompany;
        this.spouseWorkingSeniority=spouseWorkingSeniority;
        this.spouseCompanyPhone=spouseCompanyPhone;
        this.spouseUnitProperty=spouseUnitProperty;
        this.spousePosition=spousePosition;
        this.spouseCompanyAddress=spouseCompanyAddress;
        this.manageCompany=manageCompany;
        this.manageScope=manageScope;
        this.orgTypeId=orgTypeId;
        this.openingDate =openingDate;
        this.orgCode =orgCode;
        this.bussinessLicence=bussinessLicence;
        this.legalFormId=legalFormId;
        this.artificialPerson=artificialPerson;
        this.employeeCount=employeeCount;
        this.isInnerCustomer=isInnerCustomer;
        this.managePhone=managePhone;
        this.manageAddress=manageAddress;
        this.manageRemark=manageRemark;
        this.marketingManager=marketingManager;
    }

    // Property accessors

	public Integer getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
        return this.customerName==null?this.customerName:this.customerName.replaceAll("\\n", "").replaceAll("\\r", "");
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerNamePinyin() {
		return this.customerNamePinyin;
	}

	public void setCustomerNamePinyin(String customerNamePinyin) {
		this.customerNamePinyin = customerNamePinyin;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCustomerNo() {
		return this.customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	public String getCustomerTitle() {
		return this.customerTitle;
	}

	public void setCustomerTitle(String customerTitle) {
		this.customerTitle = customerTitle;
	}

	public String getIsContinue() {
		return isContinue;
	}

	public void setIsContinue(String isContinue) {
		this.isContinue = isContinue;
	}

	public Integer getAge() {
		if(birthday!=null){
			SimpleDateFormat date = new SimpleDateFormat("yyyy");
			Date dd = new Date();
			int year = Integer.parseInt(date.format(birthday));
			int nowYear = Integer.parseInt(date.format(dd));
			Integer newAge = nowYear-year;
			if(newAge<120 && newAge>0){
				return newAge;
			}
		}
		return null;
	}
	
	public boolean isValid(){
		return this.isDel!=null && this.isDel.intValue()==0
				&& this.isTrash!=null && this.isTrash.intValue()==0;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getBirthday() {
	    if(birthday!=null){
	        SimpleDateFormat date = new SimpleDateFormat("yyyy");
	        int year = Integer.parseInt(date.format(birthday));
	        if(year==9999){
	            return null;
	        }
	    }
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getCustomerTypeId() {
		return this.customerTypeId;
	}

	public void setCustomerTypeId(Integer customerTypeId) {
		this.customerTypeId = customerTypeId;
	}

	public Integer getCustomerIndustryId() {
		return this.customerIndustryId;
	}

	public void setCustomerIndustryId(Integer customerIndustryId) {
		this.customerIndustryId = customerIndustryId;
	}

	public String getIdCard() {
		return this.idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getHeadshow() {
		return headshow;
	}

	public void setHeadshow(String headshow) {
		this.headshow = headshow;
	}
	
	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getDefaultPhoneType() {
		return this.defaultPhoneType;
	}

	public void setDefaultPhoneType(Integer defaultPhoneType) {
		this.defaultPhoneType = defaultPhoneType;
	}

	public String getMobilePhone1() {
		return this.mobilePhone1;
	}

	public void setMobilePhone1(String mobilePhone1) {
		this.mobilePhone1 = mobilePhone1;
	}

	public String getMobilePhone1Remark() {
		return mobilePhone1Remark;
	}

	public void setMobilePhone1Remark(String mobilePhone1Remark) {
		this.mobilePhone1Remark = mobilePhone1Remark;
	}

	public String getMobilePhone2() {
		return mobilePhone2;
	}

	public void setMobilePhone2(String mobilePhone2) {
		this.mobilePhone2 = mobilePhone2;
	}

	public String getMobilePhone2Remark() {
		return mobilePhone2Remark;
	}

	public void setMobilePhone2Remark(String mobilePhone2Remark) {
		this.mobilePhone2Remark = mobilePhone2Remark;
	}

	public Integer getIsReceiveSms() {
		return isReceiveSms;
	}

	public void setIsReceiveSms(Integer isReceiveSms) {
		this.isReceiveSms = isReceiveSms;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhoneExt() {
		return this.phoneExt;
	}

	public void setPhoneExt(String phoneExt) {
		this.phoneExt = phoneExt;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getFaxExt() {
		return this.faxExt;
	}

	public void setFaxExt(String faxExt) {
		this.faxExt = faxExt;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTemplateIds() {
		return this.templateIds;
	}

	public void setTemplateIds(String templateIds) {
		this.templateIds = templateIds;
	}

	public Integer getIsTrash() {
		return this.isTrash;
	}

	public void setIsTrash(Integer isTrash) {
		this.isTrash = isTrash;
	}

	public Integer getIsDel() {
		return this.isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public Integer getBelongDeptId() {
		return this.belongDeptId;
	}

	public void setBelongDeptId(Integer belongDeptId) {
		this.belongDeptId = belongDeptId;
	}

	public Integer getBelongUserId() {
		return this.belongUserId;
	}

	public void setBelongUserId(Integer belongUserId) {
		this.belongUserId = belongUserId;
	}

	public Integer getIsVisit() {
		return this.isVisit;
	}

	public void setIsVisit(Integer isVisit) {
		this.isVisit = isVisit;
	}
	
	public Date getLastContactDate() {
		return lastContactDate;
	}

	public void setLastContactDate(Date lastContactDate) {
		this.lastContactDate = lastContactDate;
	}

	public String getLastContactType() {
		return lastContactType;
	}

	public void setLastContactType(String lastContactType) {
		this.lastContactType = lastContactType;
	}

	public String getMobilePhone2Regular() {
		return mobilePhone2Regular;
	}

	public void setMobilePhone2Regular(String mobilePhone2Regular) {
		this.mobilePhone2Regular = mobilePhone2Regular;
	}

	public String getMobilePhone1Regular() {
		return mobilePhone1Regular;
	}

	public void setMobilePhone1Regular(String mobilePhoneRegular) {
		this.mobilePhone1Regular = mobilePhoneRegular;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}

	public Integer getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(Integer updateUser) {
		this.updateUser = updateUser;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	public Integer getIsNoGood() {
        return isNoGood;
    }

    public void setIsNoGood(Integer isNoGood) {
        this.isNoGood = isNoGood;
    }

    
    
    public Integer getCredentialTypeId() {
		return credentialTypeId;
	}

	public void setCredentialTypeId(Integer credentialTypeId) {
		this.credentialTypeId = credentialTypeId;
	}

	public Integer getEducationalHistoryId() {
		return educationalHistoryId;
	}

	public void setEducationalHistoryId(Integer educationalHistoryId) {
		this.educationalHistoryId = educationalHistoryId;
	}

	public Integer getLivingConditionId() {
		return livingConditionId;
	}

	public void setLivingConditionId(Integer livingConditionId) {
		this.livingConditionId = livingConditionId;
	}

	public String getUnitProperty() {
		return unitProperty;
	}

	public void setUnitProperty(String unitProperty) {
		this.unitProperty = unitProperty;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getWorkingSeniority() {
		return workingSeniority;
	}

	public void setWorkingSeniority(String workingSeniority) {
		this.workingSeniority = workingSeniority;
	}

	public Integer getMaritalStatusId() {
		return maritalStatusId;
	}

	public void setMaritalStatusId(Integer maritalStatusId) {
		this.maritalStatusId = maritalStatusId;
	}

	public String getSpouseName() {
		return spouseName;
	}

	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}

	public String getSpouseIdCard() {
		return spouseIdCard;
	}

	public void setSpouseIdCard(String spouseIdCard) {
		this.spouseIdCard = spouseIdCard;
	}

	public String getSpouseMobilePhone() {
		return spouseMobilePhone;
	}

	public void setSpouseMobilePhone(String spouseMobilePhone) {
		this.spouseMobilePhone = spouseMobilePhone;
	}

	public String getSpousePhone() {
		return spousePhone;
	}

	public void setSpousePhone(String spousePhone) {
		this.spousePhone = spousePhone;
	}

	public String getSpouseCompany() {
		return spouseCompany;
	}

	public void setSpouseCompany(String spouseCompany) {
		this.spouseCompany = spouseCompany;
	}

	public String getSpouseWorkingSeniority() {
		return spouseWorkingSeniority;
	}

	public void setSpouseWorkingSeniority(String spouseWorkingSeniority) {
		this.spouseWorkingSeniority = spouseWorkingSeniority;
	}

	public String getSpouseCompanyPhone() {
		return spouseCompanyPhone;
	}

	public void setSpouseCompanyPhone(String spouseCompanyPhone) {
		this.spouseCompanyPhone = spouseCompanyPhone;
	}

	public String getSpouseUnitProperty() {
		return spouseUnitProperty;
	}

	public void setSpouseUnitProperty(String spouseUnitProperty) {
		this.spouseUnitProperty = spouseUnitProperty;
	}

	public String getSpousePosition() {
		return spousePosition;
	}

	public void setSpousePosition(String spousePosition) {
		this.spousePosition = spousePosition;
	}

	public String getSpouseCompanyAddress() {
		return spouseCompanyAddress;
	}

	public void setSpouseCompanyAddress(String spouseCompanyAddress) {
		this.spouseCompanyAddress = spouseCompanyAddress;
	}

	public String getManageCompany() {
		return manageCompany;
	}

	public void setManageCompany(String manageCompany) {
		this.manageCompany = manageCompany;
	}

	public String getManageScope() {
		return manageScope;
	}

	public void setManageScope(String manageScope) {
		this.manageScope = manageScope;
	}

	public Integer getOrgTypeId() {
		return orgTypeId;
	}

	public void setOrgTypeId(Integer orgTypeId) {
		this.orgTypeId = orgTypeId;
	}

	public Date getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(Date openingDate) {
		this.openingDate = openingDate;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getBussinessLicence() {
		return bussinessLicence;
	}

	public void setBussinessLicence(String bussinessLicence) {
		this.bussinessLicence = bussinessLicence;
	}

	public Integer getLegalFormId() {
		return legalFormId;
	}

	public void setLegalFormId(Integer legalFormId) {
		this.legalFormId = legalFormId;
	}

	public String getArtificialPerson() {
		return artificialPerson;
	}

	public void setArtificialPerson(String artificialPerson) {
		this.artificialPerson = artificialPerson;
	}

	public String getEmployeeCount() {
		return employeeCount;
	}

	public void setEmployeeCount(String employeeCount) {
		this.employeeCount = employeeCount;
	}

	public Integer getIsInnerCustomer() {
		return isInnerCustomer;
	}

	public void setIsInnerCustomer(Integer isInnerCustomer) {
		this.isInnerCustomer = isInnerCustomer;
	}

	public String getManagePhone() {
		return managePhone;
	}

	public void setManagePhone(String managePhone) {
		this.managePhone = managePhone;
	}

	public String getManageAddress() {
		return manageAddress;
	}

	public void setManageAddress(String manageAddress) {
		this.manageAddress = manageAddress;
	}

	public String getManageRemark() {
		return manageRemark;
	}

	public void setManageRemark(String manageRemark) {
		this.manageRemark = manageRemark;
	}

	public String getMarketingManager() {
		return marketingManager;
	}

	public void setMarketingManager(String marketingManager) {
		this.marketingManager = marketingManager;
	}

	/**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof BaseCrmCustomer)) {
            return false;
        }
        BaseCrmCustomer rhs = (BaseCrmCustomer) object;
        return new EqualsBuilder().appendSuper(super.equals(object))
            .append(this.mobilePhone2Remark, rhs.mobilePhone2Remark)
            .append(this.birthday, rhs.birthday)
            .append(this.defaultPhoneType, rhs.defaultPhoneType)
            .append(this.belongDeptId, rhs.belongDeptId).append(this.sex, rhs.sex)
            .append(this.phone, rhs.phone).append(this.memo, rhs.memo)
            .append(this.mobilePhone1Regular, rhs.mobilePhone1Regular)
            .append(this.remark, rhs.remark).append(this.customerNo, rhs.customerNo)
            .append(this.faxExt, rhs.faxExt)
            .append(this.customerIndustryId, rhs.customerIndustryId).append(this.city, rhs.city)
            .append(this.isDel, rhs.isDel).append(this.customerId, rhs.customerId)
            .append(this.phoneExt, rhs.phoneExt).append(this.belongUserId, rhs.belongUserId)
            .append(this.mobilePhone2Regular, rhs.mobilePhone2Regular).append(this.age, rhs.age)
            .append(this.province, rhs.province).append(this.isTrash, rhs.isTrash)
            .append(this.createDate, rhs.createDate)
            .append(this.lastContactDate, rhs.lastContactDate)
            .append(this.customerTypeId, rhs.customerTypeId)
            .append(this.customerTitle, rhs.customerTitle)
            .append(this.customerNamePinyin, rhs.customerNamePinyin)
            .append(this.customerName, rhs.customerName).append(this.idCard, rhs.idCard)
            .append(this.fax, rhs.fax).append(this.isReceiveSms, rhs.isReceiveSms)
            .append(this.headshow, rhs.headshow).append(this.updateDate, rhs.updateDate)
            .append(this.templateIds, rhs.templateIds)
            .append(this.lastContactType, rhs.lastContactType)
            .append(this.mobilePhone1Remark, rhs.mobilePhone1Remark)
            .append(this.mobilePhone2, rhs.mobilePhone2).append(this.isNoGood, rhs.isNoGood)
            .append(this.createUser, rhs.createUser).append(this.mobilePhone1, rhs.mobilePhone1)
            .append(this.email, rhs.email).append(this.address, rhs.address)
            .append(this.company, rhs.company).append(this.isVisit, rhs.isVisit)
            .append(this.updateUser, rhs.updateUser)
            .append(this.credentialTypeId, rhs.credentialTypeId)
            .append(this.educationalHistoryId, rhs.educationalHistoryId)
            .append(this.livingConditionId, rhs.livingConditionId)
            .append(this.unitProperty, rhs.unitProperty)
            .append(this.position, rhs.position)
            .append(this.workingSeniority, rhs.workingSeniority)
            .append(this.maritalStatusId, rhs.maritalStatusId)
            .append(this.spouseName, rhs.spouseName)
            .append(this.spouseIdCard, rhs.spouseIdCard)
            .append(this.spouseMobilePhone, rhs.spouseMobilePhone)
            .append(this.spousePhone, rhs.spousePhone)
            .append(this.spouseCompany, rhs.spouseCompany)
            .append(this.spouseWorkingSeniority, rhs.spouseWorkingSeniority)
            .append(this.spouseCompanyPhone, rhs.spouseCompanyPhone)
            .append(this.spouseUnitProperty, rhs.spouseUnitProperty)
            .append(this.spousePosition, rhs.spousePosition)
            .append(this.spouseCompanyAddress, rhs.spouseCompanyAddress)
            .append(this.manageCompany, rhs.manageCompany)
            .append(this.manageScope, rhs.manageScope)
            .append(this.orgTypeId, rhs.orgTypeId)
            .append(this.openingDate, rhs.openingDate)
            .append(this.orgCode, rhs.orgCode)
            .append(bussinessLicence, rhs.bussinessLicence)
            .append(this.legalFormId, rhs.legalFormId)
            .append(this.artificialPerson, rhs.artificialPerson)
            .append(this.employeeCount, rhs.employeeCount)
            .append(this.isInnerCustomer, rhs.isInnerCustomer)
            .append(this.managePhone, rhs.managePhone)
            .append(this.manageAddress, rhs.manageAddress)
            .append(this.manageRemark, rhs.manageRemark)
            .append(this.marketingManager,  rhs.marketingManager)
            .isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1174319825, -203881065).appendSuper(super.hashCode())
            .append(this.mobilePhone2Remark).append(this.birthday).append(this.defaultPhoneType)
            .append(this.belongDeptId).append(this.sex).append(this.phone).append(this.memo)
            .append(this.mobilePhone1Regular).append(this.remark).append(this.customerNo)
            .append(this.faxExt).append(this.customerIndustryId).append(this.city)
            .append(this.isDel).append(this.customerId).append(this.phoneExt)
            .append(this.belongUserId).append(this.mobilePhone2Regular).append(this.age)
            .append(this.province).append(this.isTrash).append(this.createDate)
            .append(this.lastContactDate).append(this.customerTypeId).append(this.customerTitle)
            .append(this.customerNamePinyin).append(this.customerName).append(this.idCard)
            .append(this.fax).append(this.isReceiveSms).append(this.headshow)
            .append(this.updateDate).append(this.templateIds).append(this.lastContactType)
            .append(this.mobilePhone1Remark).append(this.mobilePhone2).append(this.isNoGood)
            .append(this.createUser).append(this.mobilePhone1).append(this.email)
            .append(this.address).append(this.company).append(this.isVisit).append(this.updateUser)
            .append(this.credentialTypeId).append(this.educationalHistoryId).append(this.livingConditionId)
            .append(this.unitProperty).append(this.position).append(this.workingSeniority)
            .append(this.maritalStatusId).append(this.spouseName).append(this.spouseIdCard)
            .append(this.spouseMobilePhone).append(this.spousePhone).append(this.spouseCompany)
            .append(this.spouseWorkingSeniority).append(this.spouseCompanyPhone)
            .append(this.spouseUnitProperty).append(this.spousePosition).append(this.spouseCompanyAddress)
            .append(this.manageCompany).append(this.manageScope).append(this.orgTypeId)
            .append(this.openingDate).append(this.orgCode).append(this.bussinessLicence)
            .append(this.legalFormId).append(this.artificialPerson).append(this.employeeCount)
            .append(this.isInnerCustomer).append(this.managePhone).append(this.manageAddress)
            .append(this.manageRemark).append(this.marketingManager).toHashCode();
    }

	/**
	 * 默认号码
	 * @return
	 */
	public String getDefaultPhone() {
		if (this.defaultPhoneType != null) {
			switch (this.defaultPhoneType.intValue()) {
				case 1:
					return this.mobilePhone1;
				case 2:
					return this.mobilePhone2;
				case 3:
					return this.phone;
				case 4:
					return this.fax;
			}
		}
		return "";
	}


	public String getLivingAddress() {
		return livingAddress;
	}

	public void setLivingAddress(String livingAddress) {
		this.livingAddress = livingAddress;
	}

	public String getDwellCode() {
		return dwellCode;
	}

	public void setDwellCode(String dwellCode) {
		this.dwellCode = dwellCode;
	}

	public String getCensusRegister() {
		return censusRegister;
	}

	public void setCensusRegister(String censusRegister) {
		this.censusRegister = censusRegister;
	}

	public String getHouseatt() {
		return houseatt;
	}

	public void setHouseatt(String houseatt) {
		this.houseatt = houseatt;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getBgnyear() {
		return bgnyear;
	}

	public void setBgnyear(String bgnyear) {
		this.bgnyear = bgnyear;
	}

	public String getWorktel() {
		return worktel;
	}

	public void setWorktel(String worktel) {
		this.worktel = worktel;
	}

	public String getSpouseSex() {
		return spouseSex;
	}

	public void setSpouseSex(String spouseSex) {
		this.spouseSex = spouseSex;
	}

	public String getSpouseRank() {
		return spouseRank;
	}

	public void setSpouseRank(String spouseRank) {
		this.spouseRank = spouseRank;
	}
}