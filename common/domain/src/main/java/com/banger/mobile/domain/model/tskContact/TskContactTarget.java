package com.banger.mobile.domain.model.tskContact;

import java.io.Serializable;
import java.util.Date;
/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：Dec 17, 2012 3:29:42 PM
 * 类说明
 */
public class TskContactTarget implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5908581085394209801L;
	
    private Integer           contactTargetId;                        //任务目标ID
    private Integer           customerId;                             //客户ID
    private String            customerName;                           //客户姓名
    private Integer           defaultPhoneType;                       //默认号码类型
    private String            mobilePhone1;                           //手机1
    private String            mobilePhone2;                           //手机2
    private String            phone;                                  //固话
    private String            fax;                                    //传真
    private String            sex;                                    //性别
    private String            customerTitle;                          //称谓
    private Integer           age;                                    //年龄
    private String            deptName;                               //归属机构
    private String            userName;                               //归属人员
    private Integer           isFinish;                               //是否已联系'如果是联系任务，如果勾选联系情况，则表示已完成。 1:已联系0:未联系'
    private Integer           executeUserId;                          //执行者id
    private String            executeName;                            //执行者name
    private Date              planDate;                               //计划联系日期
    private Integer           relatedTaskCount;                       //关联任务个数
    private String            remark;                                 //备注
    private String            phoneNo;                                //外部客户的联系电话
    private Integer           isDel;                                  //客户删除

	public TskContactTarget() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getContactTargetId() {
		return contactTargetId;
	}

	public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public void setContactTargetId(Integer contactTargetId) {
		this.contactTargetId = contactTargetId;
	}

	public Integer getIsFinish() {
		return isFinish;
	}

	public void setIsFinish(Integer isFinish) {
		this.isFinish = isFinish;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Integer getDefaultPhoneType() {
		return defaultPhoneType;
	}

	public void setDefaultPhoneType(Integer defaultPhoneType) {
		this.defaultPhoneType = defaultPhoneType;
	}

	public String getMobilePhone1() {
		return mobilePhone1;
	}

	public void setMobilePhone1(String mobilePhone1) {
		this.mobilePhone1 = mobilePhone1;
	}

	public String getMobilePhone2() {
		return mobilePhone2;
	}

	public void setMobilePhone2(String mobilePhone2) {
		this.mobilePhone2 = mobilePhone2;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCustomerTitle() {
		return customerTitle;
	}

	public void setCustomerTitle(String customerTitle) {
		this.customerTitle = customerTitle;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getExecuteName() {
		return executeName;
	}

	public void setExecuteName(String executeName) {
		this.executeName = executeName;
	}

	public Date getPlanDate() {
		return planDate;
	}

	public void setPlanDate(Date planDate) {
		this.planDate = planDate;
	}
	
	public Integer getRelatedTaskCount() {
		return relatedTaskCount;
	}

	public void setRelatedTaskCount(Integer relatedTaskCount) {
		this.relatedTaskCount = relatedTaskCount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Integer getExecuteUserId() {
		return executeUserId;
	}

	public void setExecuteUserId(Integer executeUserId) {
		this.executeUserId = executeUserId;
	}	

}



