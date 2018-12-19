package com.banger.mobile.webservice.domain.loan;

import com.banger.mobile.domain.model.pad.PadLoanInfo;
/**
 * 保存状态
 * @author summerxll
 *
 */
public class SaveStatusInfo {

	private String registerSaveStatus; //等级信息保存状态
	private String applicationInfo;    //申请信息保存状态
	private String personalInfo;	   //个人信息保存状态
	private String familyInfo;		   //家庭信息保存状态
	private String businessInfo;	   //经营信息保存状态
	private String signInfo;		   //客户签字保存状态
	
	public String getRegisterSaveStatus() {
		return registerSaveStatus==null?"N":registerSaveStatus;
	}
	public void setRegisterSaveStatus(String registerSaveStatus) {
		this.registerSaveStatus = registerSaveStatus==null?"N":registerSaveStatus;
	}
	public String getApplicationInfo() {
		return applicationInfo==null ?"N":applicationInfo;
	}
	public void setApplicationInfo(String applicationInfo) {
		this.applicationInfo = applicationInfo==null ?"N":applicationInfo;
	}
	public String getPersonalInfo() {
		return personalInfo==null ?"N":personalInfo;
	}
	public void setPersonalInfo(String personalInfo) {
		this.personalInfo = personalInfo==null ?"N":personalInfo;
	}
	public String getFamilyInfo() {
		return familyInfo==null ?"N":familyInfo;
	}
	public void setFamilyInfo(String familyInfo) {
		this.familyInfo = familyInfo==null ?"N":familyInfo;
	}
	public String getBusinessInfo() {
		return businessInfo==null ?"N":businessInfo;
	}
	public void setBusinessInfo(String businessInfo) {
		this.businessInfo = businessInfo==null ?"N":businessInfo;
	}
	public String getSignInfo() {
		return signInfo==null ?"N":signInfo;
	}
	public void setSignInfo(String signInfo) {
		this.signInfo = signInfo==null ?"N":signInfo;
	}
	
	/**
	 * 构造函数
	 * @param info
	 */
	public SaveStatusInfo (PadLoanInfo info){
		this.setRegisterSaveStatus(info.getRegisterSaveStatus());
		this.setApplicationInfo(info.getApplicationInfo());
		this.setPersonalInfo(info.getPersonalInfo());
		this.setFamilyInfo(info.getFamilyInfo());
		this.setBusinessInfo(info.getBusinessInfo());
		this.setSignInfo(info.getSignInfo());				
	}
}
