package com.banger.mobile.webservice.domain.loan;

import com.banger.mobile.domain.model.base.loan.BaseLnLoanInfo;
import com.banger.mobile.domain.model.pad.PadLoanInfo;

import java.io.Serializable;

/**
 * 经营信息
 * @author summerxll
 *
 */
public class BusinessInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -423724887411322716L;
	private String bizCompany; // 企业名称
	private String bizScope; // 经营范围
	private String bizOrgId; // 组织形式
	private String bizOrgName;
	private String bizOrgCode; // 组织机构代码
	private String bizLicenseNum; // 营业执照号码
	private String bizSocialCreditNum; // 社会信用号码
	private String bizShareholderProportion; // 股东占比情况	
	private String bizActualOperator; // 实际经营者
	private String bizPlaceId; // 经营场所
	private String bizPlaceName;	
	private String bizArea; // 经营场所面积
	private String bizAddress; // 实际经营地址	
	private String bizOpenDate; // 开业日期
	private String bizEmployeeNum; // 雇员人数	
	private String bizLawFormId; // 法律形式
	private String bizLawFormName;
	private String bizLeagl; // 法人代表
	private String bizLeaglMobilePhone; // 电话号码
	private String bizIsBelongMybank; // 是否我行客户
	private String bizNote; // 生意信息备注	
	private String cafMonthlySales; // 月销售额
	private String cafGrossMargin; // 毛利润
	private String cafNetMargin; // 净利润率
	private String cafBusySeason; // 旺季
	private String cafLowSeason; // 淡季
	private String cafReceivableMoney; // 应收账款
	private String cafTotalMoney; // 总资产
	private String cafInventory; // 存货
	private String cafDebt; // 负债
	private String cafOther; // 其它收入
	private String isSeason; // 是否有淡旺季
	private String seasonRemark; // 淡旺季描述

	private String cusCommonOperaterName; // 共同经营者姓名
	private String cusCommonOperaterIdcard; // 共同经营者身份证号
	private String cusCommonOperaterMobilePhone; // 共同经营者手机号

	public String getIsSeason() {
		return isSeason;
	}

	public void setIsSeason(String isSeason) {
		this.isSeason = isSeason;
	}

	public String getSeasonRemark() {
		return seasonRemark;
	}

	public void setSeasonRemark(String seasonRemark) {
		this.seasonRemark = seasonRemark;
	}

	public String getCusCommonOperaterName() {
		return cusCommonOperaterName;
	}

	public void setCusCommonOperaterName(String cusCommonOperaterName) {
		this.cusCommonOperaterName = cusCommonOperaterName;
	}

	public String getCusCommonOperaterIdcard() {
		return cusCommonOperaterIdcard;
	}

	public void setCusCommonOperaterIdcard(String cusCommonOperaterIdcard) {
		this.cusCommonOperaterIdcard = cusCommonOperaterIdcard;
	}

	public String getCusCommonOperaterMobilePhone() {
		return cusCommonOperaterMobilePhone;
	}

	public void setCusCommonOperaterMobilePhone(String cusCommonOperaterMobilePhone) {
		this.cusCommonOperaterMobilePhone = cusCommonOperaterMobilePhone;
	}

	public String getBizCompany() {
		return bizCompany==null ? "":bizCompany;
	}
	public void setBizCompany(String bizCompany) {
		this.bizCompany = bizCompany==null ? "":bizCompany;
	}
	public String getBizScope() {
		return bizScope==null ?"":bizScope;
	}
	public void setBizScope(String bizScope) {
		this.bizScope = bizScope==null ?"":bizScope;
	}
	public String getBizOrgId() {
		return bizOrgId==null ?"":bizOrgId;
	}
	public void setBizOrgId(String bizOrgId) {
		this.bizOrgId = bizOrgId==null ?"":bizOrgId;
	}
	public String getBizOrgName() {
		return bizOrgName==null?"":bizOrgName;
	}
	public void setBizOrgName(String bizOrgName) {
		this.bizOrgName = bizOrgName==null?"":bizOrgName;
	}
	public String getBizOrgCode() {
		return bizOrgCode==null?"":bizOrgCode;
	}
	public void setBizOrgCode(String bizOrgCode) {
		this.bizOrgCode = bizOrgCode==null?"":bizOrgCode;
	}
	public String getBizLicenseNum() {
		return bizLicenseNum==null?"":bizLicenseNum;
	}
	public void setBizLicenseNum(String bizLicenseNum) {
		this.bizLicenseNum = bizLicenseNum==null?"":bizLicenseNum;
	}
	public String getBizSocialCreditNum() {
		return bizSocialCreditNum==null?"":bizSocialCreditNum;
	}
	public void setBizSocialCreditNum(String bizSocialCreditNum) {
		this.bizSocialCreditNum = bizSocialCreditNum==null?"":bizSocialCreditNum;
	}
	public String getBizShareholderProportion() {
		return bizShareholderProportion==null?"":bizShareholderProportion;
	}
	public void setBizShareholderProportion(String bizShareholderProportion) {
		this.bizShareholderProportion = bizShareholderProportion==null?"":bizShareholderProportion;
	}
	public String getBizActualOperator() {
		return bizActualOperator==null?"":bizActualOperator;
	}
	public void setBizActualOperator(String bizActualOperator) {
		this.bizActualOperator = bizActualOperator==null?"":bizActualOperator;
	}
	public String getBizPlaceId() {
		return bizPlaceId==null?"":bizPlaceId;
	}
	public void setBizPlaceId(String bizPlaceId) {
		this.bizPlaceId = bizPlaceId==null?"":bizPlaceId;
	}
	public String getBizPlaceName() {
		return bizPlaceName==null?"":bizPlaceName;
	}
	public void setBizPlaceName(String bizPlaceName) {
		this.bizPlaceName = bizPlaceName==null?"":bizPlaceName;
	}
	public String getBizArea() {
		return bizArea==null?"":bizArea;
	}
	public void setBizArea(String bizArea) {
		this.bizArea = bizArea==null?"":bizArea;
	}
	public String getBizAddress() {
		return bizAddress==null?"":bizAddress;
	}
	public void setBizAddress(String bizAddress) {
		this.bizAddress = bizAddress==null?"":bizAddress;
	}
	public String getBizOpenDate() {
		return bizOpenDate==null?"":bizOpenDate;
	}
	public void setBizOpenDate(String bizOpenDate) {
		this.bizOpenDate = bizOpenDate==null?"":bizOpenDate;
	}
	
	public String getBizEmployeeNum() {
		return bizEmployeeNum;
	}
	public void setBizEmployeeNum(String bizEmployeeNum) {
		this.bizEmployeeNum = bizEmployeeNum;
	}
	public String getBizLawFormId() {
		return bizLawFormId==null?"":bizLawFormId;
	}
	public void setBizLawFormId(String bizLawFormId) {
		this.bizLawFormId = bizLawFormId==null?"":bizLawFormId;
	}
	public String getBizLawFormName() {
		return bizLawFormName==null?"":bizLawFormName;
	}
	public void setBizLawFormName(String bizLawFormName) {
		this.bizLawFormName = bizLawFormName==null?"":bizLawFormName;
	}
	public String getBizLeagl() {
		return bizLeagl==null?"":bizLeagl;
	}
	public void setBizLeagl(String bizLeagl) {
		this.bizLeagl = bizLeagl==null?"":bizLeagl;
	}
	public String getBizLeaglMobilePhone() {
		return bizLeaglMobilePhone==null?"":bizLeaglMobilePhone;
	}
	public void setBizLeaglMobilePhone(String bizLeaglMobilePhone) {
		this.bizLeaglMobilePhone = bizLeaglMobilePhone==null?"":bizLeaglMobilePhone;
	}
	public String getBizIsBelongMybank() {
		return bizIsBelongMybank==null?"":bizIsBelongMybank;
	}
	public void setBizIsBelongMybank(String bizIsBelongMybank) {
		this.bizIsBelongMybank = bizIsBelongMybank==null?"":bizIsBelongMybank;
	}
	public String getBizNote() {
		return bizNote==null?"":bizNote;
	}
	public void setBizNote(String bizNote) {
		this.bizNote = bizNote==null?"":bizNote;
	}
	public String getCafMonthlySales() {
		return cafMonthlySales==null?"":cafMonthlySales;
	}
	public void setCafMonthlySales(String cafMonthlySales) {
		this.cafMonthlySales = cafMonthlySales==null?"":cafMonthlySales;
	}
	public String getCafGrossMargin() {
		return cafGrossMargin==null?"":cafGrossMargin;
	}
	public void setCafGrossMargin(String cafGrossMargin) {
		this.cafGrossMargin = cafGrossMargin==null?"":cafGrossMargin;
	}
	public String getCafNetMargin() {
		return cafNetMargin==null?"":cafNetMargin;
	}
	public void setCafNetMargin(String cafNetMargin) {
		this.cafNetMargin = cafNetMargin==null?"":cafNetMargin;
	}
	public String getCafBusySeason() {
		return cafBusySeason==null?"":cafBusySeason;
	}
	public void setCafBusySeason(String cafBusySeason) {
		this.cafBusySeason = cafBusySeason==null?"":cafBusySeason;
	}
	public String getCafLowSeason() {
		return cafLowSeason==null?"":cafLowSeason;
	}
	public void setCafLowSeason(String cafLowSeason) {
		this.cafLowSeason = cafLowSeason==null?"":cafLowSeason;
	}
	public String getCafReceivableMoney() {
		return cafReceivableMoney==null?"":cafReceivableMoney;
	}
	public void setCafReceivableMoney(String cafReceivableMoney) {
		this.cafReceivableMoney = cafReceivableMoney==null?"":cafReceivableMoney;
	}
	public String getCafTotalMoney() {
		return cafTotalMoney==null?"":cafTotalMoney;
	}
	public void setCafTotalMoney(String cafTotalMoney) {
		this.cafTotalMoney = cafTotalMoney==null?"":cafTotalMoney;
	}
	public String getCafInventory() {
		return cafInventory==null?"":cafInventory;
	}
	public void setCafInventory(String cafInventory) {
		this.cafInventory = cafInventory==null?"":cafInventory;
	}
	public String getCafDebt() {
		return cafDebt==null?"":cafDebt;
	}
	public void setCafDebt(String cafDebt) {
		this.cafDebt = cafDebt==null?"":cafDebt;
	}
	public String getCafOther() {
		return cafOther==null?"":cafOther;
	}
	public void setCafOther(String cafOther) {
		this.cafOther = cafOther==null?"":cafOther;
	}

	public BusinessInfo(){
		
	}
	public BusinessInfo(PadLoanInfo info){
		
		this.setBizCompany(info.getBizCompany());
		this.setBizScope(info.getBizScope());
		this.setBizOrgId(info.getBizOrgId());
		this.setBizOrgName(info.getBizOrgName());
		this.setBizOrgCode(info.getBizOrgCode());
		this.setBizLicenseNum(info.getBizLicenseNum());
		this.setBizSocialCreditNum(info.getBizSocialCreditNum());
		this.setBizShareholderProportion(info.getBizShareholderProportion());
		this.setBizActualOperator(info.getBizActualOperator());
		this.setBizPlaceId(info.getBizPlaceId());
		this.setBizPlaceName(info.getBizPlaceName());
		this.setBizArea(info.getBizArea());
		this.setBizAddress(info.getBizAddress());
		this.setBizOpenDate(info.getBizOpenDate());
		this.setBizEmployeeNum(info.getBizEmployeeNum());
		this.setBizLawFormId(info.getBizLawFormId());
		this.setBizLawFormName(info.getBizLawFormName());
		this.setBizLeagl(info.getBizLeagl());
		this.setBizLeaglMobilePhone(info.getBizLeaglMobilePhone());
		this.setBizIsBelongMybank(info.getBizIsBelongMybank());
		this.setBizNote(info.getBizNote());
		this.setCafMonthlySales(info.getCafMonthlySales());
		this.setCafGrossMargin(info.getCafGrossMargin());
		this.setCafNetMargin(info.getCafNetMargin());
		this.setCafBusySeason(info.getCafBusySeason());
		this.setCafLowSeason(info.getCafLowSeason());
		this.setCafReceivableMoney(info.getCafReceivableMoney());
		this.setCafTotalMoney(info.getCafTotalMoney());
		this.setCafInventory(info.getCafInventory());
		this.setCafDebt(info.getCafDebt());
		this.setCafOther(info.getCafOther());
		this.setCusCommonOperaterName(info.getCusCommonOperaterName());
		this.setCusCommonOperaterIdcard(info.getCusCommonOperaterIdcard());
		this.setCusCommonOperaterMobilePhone(info.getCusCommonOperaterMobilePhone());
		this.setIsSeason(info.getIsSeason());
		this.setSeasonRemark(info.getSeasonRemark());
	}
	
	public void setlnLoanInfo(BaseLnLoanInfo lnLoanInfo){
		lnLoanInfo.setBizCompany(this.getBizCompany());
		lnLoanInfo.setBizScope(this.getBizScope());
		lnLoanInfo.setBizOrgId(this.getBizOrgId());
		lnLoanInfo.setBizOrgCode(this.getBizOrgCode());
		lnLoanInfo.setBizLicenseNum(this.getBizLicenseNum());
		lnLoanInfo.setBizSocialCreditNum(this.getBizSocialCreditNum());
		lnLoanInfo.setBizShareholderProportion(this.getBizShareholderProportion());
		lnLoanInfo.setBizActualOperator(this.getBizActualOperator());
		lnLoanInfo.setBizPlaceId(this.getBizPlaceId());
		lnLoanInfo.setBizArea(this.getBizArea());
		lnLoanInfo.setBizAddress(this.getBizAddress());
		lnLoanInfo.setBizOpenDate(this.getBizOpenDate());
		lnLoanInfo.setBizEmployeeNum(this.getBizEmployeeNum());
		lnLoanInfo.setBizLawFormId(this.getBizLawFormId());
		lnLoanInfo.setBizLeagl(this.getBizLeagl());
		lnLoanInfo.setBizLeaglMobilePhone(this.getBizLeaglMobilePhone());
		lnLoanInfo.setBizIsBelongMybank(this.getBizIsBelongMybank());
		lnLoanInfo.setBizNote(this.getBizNote());
		lnLoanInfo.setCafMonthlySales(this.getCafMonthlySales());
		lnLoanInfo.setCafGrossMargin(this.getCafGrossMargin());
		lnLoanInfo.setCafNetMargin(this.getCafNetMargin());
		lnLoanInfo.setCafBusySeason(this.getCafBusySeason());
		lnLoanInfo.setCafLowSeason(this.getCafLowSeason());
		lnLoanInfo.setCafReceivableMoney(this.getCafReceivableMoney());
		lnLoanInfo.setCafTotalMoney(this.getCafTotalMoney());
		lnLoanInfo.setCafInventory(this.getCafInventory());
		lnLoanInfo.setCafDebt(this.getCafDebt());
		lnLoanInfo.setCafOther(this.getCafOther());
		lnLoanInfo.setCusCommonOperaterName(this.getCusCommonOperaterName());
		lnLoanInfo.setCusCommonOperaterIdcard(this.getCusCommonOperaterIdcard());
		lnLoanInfo.setCusCommonOperaterMobilePhone(this.getCusCommonOperaterMobilePhone());
		lnLoanInfo.setIsSeason(this.getIsSeason());
		lnLoanInfo.setSeasonRemark(this.getSeasonRemark());
		lnLoanInfo.setBusinessInfo("Y");
	}
}
