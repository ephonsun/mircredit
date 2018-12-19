package com.banger.mobile.webservice.domain.loan;

import com.banger.mobile.domain.model.base.loan.BaseLnLoanInfo;
import com.banger.mobile.domain.model.pad.PadLoanInfo;

import java.io.Serializable;

/**
 * 个人信息
 * @author summerxll
 *
 */
public class PersonalInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7012502905625620180L;
	private String cusName; // 申请人姓名
	private String cusIdtypeId; // 申请人证件类型
	private String cusIdtypeName;
	private String cusIdcard; // 申请人证件号码
	private String cusRegisteredResidence; // 申请人户口
	private String cusSex; // 申请人性别
	private String cusBirthday; // 申请人出生日期
	private String cusMobilePhone; // 申请人手机号
	private String cusPhone; // 申请人固定电话
	private String cusEducationId; // 申请人教育程度
	private String cusEducationName;
	private String companyMonthlyIncoming; // 月收入水平
	private String companyMonthlyIncomingValue;
	private String cusNetAge; // 申请人网龄
	private String companyName; // 工作单位
	private String companyNature; // 单位性质
	private String companyJob; // 工作岗位
	private String companyWorkYear; // 工作年限	
	private String companyAddress; // 单位地址
	private String cusFirstImpress; // 第一印象
	private String isLocal ; // 是否本地人
	private String cusLocalYear ; // 本地居住年限

	public String getIsLocal() {
		return isLocal;
	}

	public void setIsLocal(String isLocal) {
		this.isLocal = isLocal;
	}

	public String getCusLocalYear() {
		return cusLocalYear;
	}

	public void setCusLocalYear(String cusLocalYear) {
		this.cusLocalYear = cusLocalYear;
	}

	public String getCusName() {
		return cusName==null ? "":cusName;
	}
	public void setCusName(String cusName) {
		this.cusName = cusName==null ? "":cusName;
	}
	public String getCusIdtypeId() {
		return cusIdtypeId==null?"":cusIdtypeId;
	}
	public void setCusIdtypeId(String cusIdtypeId) {
		this.cusIdtypeId = cusIdtypeId==null?"":cusIdtypeId;
	}
	public String getCusIdtypeName() {
		return cusIdtypeName==null ?"":cusIdtypeName;
	}
	public void setCusIdtypeName(String cusIdtypeName) {
		this.cusIdtypeName = cusIdtypeName==null ?"":cusIdtypeName;
	}
	public String getCusIdcard() {
		return cusIdcard==null ? "":cusIdcard;
	}
	public void setCusIdcard(String cusIdcard) {
		this.cusIdcard = cusIdcard==null ? "":cusIdcard;
	}
	public String getCusRegisteredResidence() {
		return cusRegisteredResidence==null ?"":cusRegisteredResidence;
	}
	public void setCusRegisteredResidence(String cusRegisteredResidence) {
		this.cusRegisteredResidence = cusRegisteredResidence==null ?"":cusRegisteredResidence;
	}
	public String getCusSex() {
		return cusSex==null ?"":cusSex;
	}
	public void setCusSex(String cusSex) {
		this.cusSex = cusSex==null ?"":cusSex;
	}
	public String getCusBirthday() {
		return cusBirthday==null ?"":cusBirthday;
	}
	public void setCusBirthday(String cusBirthday) {
		this.cusBirthday = cusBirthday==null ?"":cusBirthday;
	}
	public String getCusMobilePhone() {
		return cusMobilePhone==null ? "": cusMobilePhone;
	}
	public void setCusMobilePhone(String cusMobilePhone) {
		this.cusMobilePhone = cusMobilePhone==null ? "": cusMobilePhone;
	}
	public String getCusPhone() {
		return cusPhone==null ?"":cusPhone;
	}
	public void setCusPhone(String cusPhone) {
		this.cusPhone = cusPhone==null ?"":cusPhone;
	}
	public String getCusEducationId() {
		return cusEducationId==null ?"":cusEducationId;
	}
	public void setCusEducationId(String cusEducationId) {
		this.cusEducationId = cusEducationId==null ?"":cusEducationId;
	}
	public String getCusEducationName() {
		return cusEducationName==null?"":cusEducationName;
	}
	public void setCusEducationName(String cusEducationName) {
		this.cusEducationName = cusEducationName==null?"":cusEducationName;
	}
	public String getCompanyMonthlyIncoming() {
		return companyMonthlyIncoming==null ? "":companyMonthlyIncoming;
	}
	public void setCompanyMonthlyIncoming(String companyMonthlyIncoming) {
		this.companyMonthlyIncoming = companyMonthlyIncoming==null ? "":companyMonthlyIncoming;
	}
	public String getCompanyMonthlyIncomingValue() {
		return companyMonthlyIncomingValue==null ? "":companyMonthlyIncomingValue;
	}
	public void setCompanyMonthlyIncomingValue(String companyMonthlyIncomingValue) {
		this.companyMonthlyIncomingValue = companyMonthlyIncomingValue==null ? "":companyMonthlyIncomingValue;
	}
	public String getCusNetAge() {
		return cusNetAge==null ?"":cusNetAge;
	}
	public void setCusNetAge(String cusNetAge) {
		this.cusNetAge = cusNetAge==null ?"":cusNetAge;
	}
	public String getCompanyName() {
		return companyName==null ? "":companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName==null ? "":companyName;
	}
	public String getCompanyNature() {
		return companyNature==null ?"":companyNature;
	}
	public void setCompanyNature(String companyNature) {
		this.companyNature = companyNature==null ?"":companyNature;
	}
	public String getCompanyJob() {
		return companyJob==null ?"":companyJob;
	}
	public void setCompanyJob(String companyJob) {
		this.companyJob = companyJob==null ?"":companyJob;
	}
	public String getCompanyWorkYear() {
		return companyWorkYear==null ?"":companyWorkYear;
	}
	public void setCompanyWorkYear(String companyWorkYear) {
		this.companyWorkYear = companyWorkYear==null ?"":companyWorkYear;
	}
	public String getCompanyAddress() {
		return companyAddress==null?"":companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress==null?"":companyAddress;
	}
	public String getCusFirstImpress() {
		return cusFirstImpress==null ?"":cusFirstImpress;
	}
	public void setCusFirstImpress(String cusFirstImpress) {
		this.cusFirstImpress = cusFirstImpress==null ?"":cusFirstImpress;
	}
	
	public  PersonalInfo(){
		
	}
	public PersonalInfo (PadLoanInfo info){
		this.setCusName(info.getCusName());
		this.setCusIdtypeId(info.getCusIdtypeId());
		this.setCusIdtypeName(info.getCusIdtypeName());
		this.setCusIdcard(info.getCusIdcard());		
		this.setCusRegisteredResidence(info.getCusRegisteredResidence());
		this.setCusSex(info.getCusSex());
		this.setCusBirthday(info.getCusBirthday());
		this.setCusMobilePhone(info.getCusMobilePhone());
		this.setCusPhone(info.getCusPhone());
		this.setCusEducationId(info.getCusEducationId());
		this.setCusEducationName(info.getCusEducationName());
		this.setCompanyMonthlyIncoming(info.getCompanyMonthlyIncoming());
		this.setCompanyMonthlyIncomingValue(info.getCompanyMonthlyIncomingValue());		
		this.setCusNetAge(info.getCusNetAge());
		this.setCompanyName(info.getCompanyName());
		this.setCompanyNature(info.getCompanyNature());
		this.setCompanyJob(info.getCompanyJob());
		this.setCompanyWorkYear(info.getCompanyWorkYear());
		this.setCompanyAddress(info.getCompanyAddress());
		this.setCusFirstImpress(info.getCusFirstImpress());
		this.setIsLocal(info.getIsLocal());
		this.setCusLocalYear(info.getCusLocalYear());
	}
	
	public void setlnLoanInfo(BaseLnLoanInfo lnLoanInfo){
		lnLoanInfo.setCusName(this.getCusName());
		lnLoanInfo.setCusIdtypeId(this.getCusIdtypeId());
		lnLoanInfo.setCusIdcard(this.getCusIdcard());
		lnLoanInfo.setCusRegisteredResidence(this.getCusRegisteredResidence());
		lnLoanInfo.setCusSex(this.getCusSex());
		lnLoanInfo.setCusBirthday(this.getCusBirthday());
		lnLoanInfo.setCusMobilePhone(this.getCusMobilePhone());
		lnLoanInfo.setCusPhone(this.getCusPhone());
		lnLoanInfo.setCusEducationId(this.getCusEducationId());
		lnLoanInfo.setCompanyMonthlyIncoming(this.getCompanyMonthlyIncoming());
		lnLoanInfo.setCusNetAge(this.getCusNetAge());
		lnLoanInfo.setCompanyName(this.getCompanyName());
		lnLoanInfo.setCompanyNature(this.getCompanyNature());
		lnLoanInfo.setCompanyJob(this.getCompanyJob());
		lnLoanInfo.setCompanyWorkYear(this.getCompanyWorkYear());
		lnLoanInfo.setCompanyAddress(this.getCompanyAddress());
		lnLoanInfo.setCusFirstImpress(this.getCusFirstImpress());
		lnLoanInfo.setIsLocal(this.getIsLocal());
		lnLoanInfo.setCusLocalYear(this.getCusLocalYear());
		lnLoanInfo.setPersonalInfo("Y");
    }
	
}
