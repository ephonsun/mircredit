package com.banger.mobile.webservice.domain.loan;

import com.banger.mobile.domain.model.pad.BaseData;
import java.io.Serializable;
import java.util.List;
/**
 * 申请信息列表
 * @author summerxll
 *
 */
public class PadLoanAppInfoDetail implements Serializable{
	
	private static final long serialVersionUID = -2410010580791223602L;
	
	private RegisterInfo registerInfo;
	private ApplicationInfo applicationInfo;
	private PersonalInfo personalInfo;
	private FamilyInfo familyInfo;
	private BusinessInfo businessInfo;
	private SignInfo signInfo;
	private SaveStatusInfo saveStatusInfo;
	
	private List<BaseData> dklx;		//贷款类型
	private List<BaseData> dbfs;	//担保方式
	private List<BaseData> dy;		//抵押	
	private List<BaseData> zy;		//质押
	private List<BaseData> flxs;	//法律形式
	private List<BaseData> hyzk;	//婚姻状况
	private List<BaseData> jycd;	//教育程度
	private List<BaseData> jycs;	//经营场所
	private List<BaseData> jzcslx;	//居住场所类型
	private List<BaseData> jzzk;	//居住状况
	private List<BaseData> ysrsp;	//月收入水平
	private List<BaseData> zjlx;	//证件类型
	private List<BaseData> zxqk;	//装修情况
	private List<BaseData> zzxs;	//组织形式
	
	private List<BaseData> registerMicroBizCenterList;	
	private List<BaseData> registerRecommendBank;

	
	public RegisterInfo getRegisterInfo() {
		return registerInfo;
	}
	public void setRegisterInfo(RegisterInfo registerInfo) {
		this.registerInfo = registerInfo;
	}
	public ApplicationInfo getApplicationInfo() {
		return applicationInfo;
	}
	public void setApplicationInfo(ApplicationInfo applicationInfo) {
		this.applicationInfo = applicationInfo;
	}
	public PersonalInfo getPersonalInfo() {
		return personalInfo;
	}
	public void setPersonalInfo(PersonalInfo personalInfo) {
		this.personalInfo = personalInfo;
	}
	public FamilyInfo getFamilyInfo() {
		return familyInfo;
	}
	public void setFamilyInfo(FamilyInfo familyInfo) {
		this.familyInfo = familyInfo;
	}
	public BusinessInfo getBusinessInfo() {
		return businessInfo;
	}
	public void setBusinessInfo(BusinessInfo businessInfo) {
		this.businessInfo = businessInfo;
	}
	public SignInfo getSignInfo() {
		return signInfo;
	}
	public void setSignInfo(SignInfo signInfo) {
		this.signInfo = signInfo;
	}
	public SaveStatusInfo getSaveStatusInfo() {
		return saveStatusInfo;
	}
	public void setSaveStatusInfo(SaveStatusInfo saveStatusInfo) {
		this.saveStatusInfo = saveStatusInfo;
	}
	public List<BaseData> getDklx() {
		return dklx;
	}
	public void setDklx(List<BaseData> dklx) {
		this.dklx = dklx;
	}
	public List<BaseData> getDbfs() {
		return dbfs;
	}
	public void setDbfs(List<BaseData> dbfs) {
		this.dbfs = dbfs;
	}
	public List<BaseData> getDy() {
		return dy;
	}
	public void setDy(List<BaseData> dy) {
		this.dy = dy;
	}
	public List<BaseData> getZy() {
		return zy;
	}
	public void setZy(List<BaseData> zy) {
		this.zy = zy;
	}
	public List<BaseData> getFlxs() {
		return flxs;
	}
	public void setFlxs(List<BaseData> flxs) {
		this.flxs = flxs;
	}
	public List<BaseData> getHyzk() {
		return hyzk;
	}
	public void setHyzk(List<BaseData> hyzk) {
		this.hyzk = hyzk;
	}
	public List<BaseData> getJycd() {
		return jycd;
	}
	public void setJycd(List<BaseData> jycd) {
		this.jycd = jycd;
	}
	public List<BaseData> getJycs() {
		return jycs;
	}
	public void setJycs(List<BaseData> jycs) {
		this.jycs = jycs;
	}
	public List<BaseData> getJzcslx() {
		return jzcslx;
	}
	public void setJzcslx(List<BaseData> jzcslx) {
		this.jzcslx = jzcslx;
	}
	public List<BaseData> getJzzk() {
		return jzzk;
	}
	public void setJzzk(List<BaseData> jzzk) {
		this.jzzk = jzzk;
	}
	public List<BaseData> getYsrsp() {
		return ysrsp;
	}
	public void setYsrsp(List<BaseData> ysrsp) {
		this.ysrsp = ysrsp;
	}
	public List<BaseData> getZjlx() {
		return zjlx;
	}
	public void setZjlx(List<BaseData> zjlx) {
		this.zjlx = zjlx;
	}
	public List<BaseData> getZxqk() {
		return zxqk;
	}
	public void setZxqk(List<BaseData> zxqk) {
		this.zxqk = zxqk;
	}
	public List<BaseData> getZzxs() {
		return zzxs;
	}
	public void setZzxs(List<BaseData> zzxs) {
		this.zzxs = zzxs;
	}
	
	public List<BaseData> getRegisterMicroBizCenterList() {
		return registerMicroBizCenterList;
	}
	public void setRegisterMicroBizCenterList(List<BaseData> registerMicroBizCenterList) {
		this.registerMicroBizCenterList = registerMicroBizCenterList;
	}
	public List<BaseData> getRegisterRecommendBank() {
		return registerRecommendBank;
	}
	public void setRegisterRecommendBank(List<BaseData> registerRecommendBank) {
		this.registerRecommendBank = registerRecommendBank;
	}
	
}
