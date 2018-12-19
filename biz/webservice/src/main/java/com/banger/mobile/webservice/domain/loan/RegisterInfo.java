package com.banger.mobile.webservice.domain.loan;

import com.banger.mobile.domain.model.base.loan.BaseLnLoanInfo;
import com.banger.mobile.domain.model.pad.PadLoanInfo;
import com.banger.mobile.util.DateUtil;

import java.io.Serializable;
/**
 * 贷款申请。登记信息
 * @author summerxll
 *
 */
public class RegisterInfo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3308842935401991167L;
	
	private Integer registerMicroBizCenter; // 微贷事业中心
	private String registerMicroBizCenterName;
	//private Integer registerRecommendBank; // 推荐支行	
	//private String registerRecommendBankName;	
	private String registerApplyDate; // 申请日期
	private String registerLoanDate; // 需要贷款日期
	private String registerInfoSourceIds; // 信息来源编号
	private String isDirectMarketSelected;
	private String registerDirectMarket; // 直接营销
	private String isExistCusIntroductSelected;
	private String registerExistCusIntroduct; // 现有客户介绍
	private String isInfoSourceOtherSelected;
	private String registerInfoSourceOther; // 信息来源其他
	

	//REGISTER_SOURCE_FFKKHJS --非放款客户/朋友/同学介绍
	private String registerSourceFFKKHJS;
	//REGISTER_SOURCE_WX --微信
	private String registerSourceWX;
	//REGISTER_SOURCE_MM --陌陌
	private String registerSourceMM;
	//REGISTER_SOURCE_CCK  --插车卡
	private String registerSourceCCK;
	//REGISTER_SOURCE_HNYGJS   --行内员工介绍
	private String registerSourceHNYGJS;
	//REGISTER_SOURCE_DHYX  --电话营销
	private String registerSourceDHYX;
	//REGISTER_SOURCE_HZSHTJ  --合作商户推荐
	private String registerSourceHZSHTJ;
	//REGISTER_SOURCE_ZXSQ   --在线申请
	private String registerSourceZXSQ;	
	
	public Integer getRegisterMicroBizCenter() {
		return registerMicroBizCenter==null ? -1:registerMicroBizCenter;
	}
	
	public void setRegisterMicroBizCenter(Integer registerMicroBizCenter) {
		this.registerMicroBizCenter = registerMicroBizCenter==null ? -1:registerMicroBizCenter;
	}
	
	public String getRegisterMicroBizCenterName() {
		return registerMicroBizCenterName==null ? "":registerMicroBizCenterName;
	}
	
	public void setRegisterMicroBizCenterName(String registerMicroBizCenterName) {
		this.registerMicroBizCenterName = registerMicroBizCenterName==null ? "":registerMicroBizCenterName;
	}
	
	public String getRegisterApplyDate() {
		return registerApplyDate==null ? "":registerApplyDate;
	}
	
	public void setRegisterApplyDate(String registerApplyDate) {
		this.registerApplyDate = registerApplyDate==null?"":registerApplyDate;
	}
	
	public String getRegisterLoanDate() {
		return registerLoanDate==null ? "" : registerLoanDate;
	}
	
	public void setRegisterLoanDate(String registerLoanDate) {
		this.registerLoanDate = registerLoanDate==null ? "" : registerLoanDate;
	}
	
	public String getRegisterInfoSourceIds() {
		return registerInfoSourceIds==null ? "":registerInfoSourceIds;
	}
	
	public void setRegisterInfoSourceIds(String registerInfoSourceIds) {
		this.registerInfoSourceIds = registerInfoSourceIds==null ? "":registerInfoSourceIds;
	}
	
	public String getIsDirectMarketSelected() {
		return isDirectMarketSelected ==null ? "": isDirectMarketSelected;
	}
	
	public void setIsDirectMarketSelected(String isDirectMarketSelected) {
		this.isDirectMarketSelected = isDirectMarketSelected ==null ? "": isDirectMarketSelected;
	}
	
	public String getRegisterDirectMarket() {
		return registerDirectMarket==null ? "":registerDirectMarket;
	}
	
	public void setRegisterDirectMarket(String registerDirectMarket) {
		this.registerDirectMarket = registerDirectMarket==null ? "":registerDirectMarket;
	}
	
	public String getIsExistCusIntroductSelected() {
		return isExistCusIntroductSelected==null ?"":isExistCusIntroductSelected;
	}
	
	public void setIsExistCusIntroductSelected(String isExistCusIntroductSelected) {
		this.isExistCusIntroductSelected = isExistCusIntroductSelected==null ?"":isExistCusIntroductSelected;
	}
	
	public String getRegisterExistCusIntroduct() {
		return registerExistCusIntroduct==null ? "":registerExistCusIntroduct;
	}
	
	public void setRegisterExistCusIntroduct(String registerExistCusIntroduct) {
		this.registerExistCusIntroduct = registerExistCusIntroduct==null ? "":registerExistCusIntroduct;
	}
	
	public String getIsInfoSourceOtherSelected() {
		return isInfoSourceOtherSelected==null ? "": isInfoSourceOtherSelected;
	}
	
	public void setIsInfoSourceOtherSelected(String isInfoSourceOtherSelected) {
		this.isInfoSourceOtherSelected = isInfoSourceOtherSelected==null ? "": isInfoSourceOtherSelected;
	}
	
	public String getRegisterInfoSourceOther() {
		return registerInfoSourceOther==null ? "":registerInfoSourceOther;
	}
	
	public void setRegisterInfoSourceOther(String registerInfoSourceOther) {
		this.registerInfoSourceOther = registerInfoSourceOther==null ? "":registerInfoSourceOther;
	}
	
	
	public String getRegisterSourceFFKKHJS() {
		return registerSourceFFKKHJS== null ? "":registerSourceFFKKHJS;
	}

	public void setRegisterSourceFFKKHJS(String registerSourceFFKKHJS) {
		this.registerSourceFFKKHJS = registerSourceFFKKHJS== null ? "":registerSourceFFKKHJS;
	}

	public String getRegisterSourceWX() {
		return registerSourceWX==null ? "" : registerSourceWX;
	}

	public void setRegisterSourceWX(String registerSourceWX) {
		this.registerSourceWX = registerSourceWX==null ? "" : registerSourceWX;
	}

	public String getRegisterSourceMM() {
		return registerSourceMM == null ? "" : registerSourceMM;
	}

	public void setRegisterSourceMM(String registerSourceMM) {
		this.registerSourceMM = registerSourceMM == null ? "" : registerSourceMM;
	}

	public String getRegisterSourceCCK() {
		return registerSourceCCK == null ? "":registerSourceCCK ;
	}

	public void setRegisterSourceCCK(String registerSourceCCK) {
		this.registerSourceCCK = registerSourceCCK == null ? "":registerSourceCCK;
	}

	public String getRegisterSourceHNYGJS() {
		return registerSourceHNYGJS==null ?"":registerSourceHNYGJS;
	}

	public void setRegisterSourceHNYGJS(String registerSourceHNYGJS) {
		this.registerSourceHNYGJS = registerSourceHNYGJS==null ?"":registerSourceHNYGJS;
	}

	public String getRegisterSourceDHYX() {
		return registerSourceDHYX==null?"":registerSourceDHYX;
	}

	public void setRegisterSourceDHYX(String registerSourceDHYX) {
		this.registerSourceDHYX = registerSourceDHYX==null?"":registerSourceDHYX;
	}

	public String getRegisterSourceHZSHTJ() {
		return registerSourceHZSHTJ==null ? "":registerSourceHZSHTJ;
	}

	public void setRegisterSourceHZSHTJ(String registerSourceHZSHTJ) {
		this.registerSourceHZSHTJ = registerSourceHZSHTJ==null ? "":registerSourceHZSHTJ;
	}

	public String getRegisterSourceZXSQ() {
		return registerSourceZXSQ == null ?"": registerSourceZXSQ;
	}

	public void setRegisterSourceZXSQ(String registerSourceZXSQ) {
		this.registerSourceZXSQ = registerSourceZXSQ == null ?"": registerSourceZXSQ;
	}

	public RegisterInfo(){
		
	}
	
	public RegisterInfo(PadLoanInfo info){
		this.setRegisterMicroBizCenter(info.getRegisterMicroBizCenter());
		this.setRegisterMicroBizCenterName(info.getRegisterMicroBizCenterName());
		//this.setRegisterRecommendBank(info.getRegisterRecommendBank());
		//this.setRegisterRecommendBankName(info.getRegisterRecommendBankName());
		this.setRegisterApplyDate(DateUtil.getCNDateToString(info.getRegisterApplyDate()));
		this.setRegisterLoanDate(DateUtil.getCNDateToString(info.getRegisterLoanDate()));
		this.setRegisterInfoSourceIds(info.getRegisterInfoSourceIds());
		this.setIsDirectMarketSelected(info.getRegisterInfoSourceIds().contains("1")?"Y":"N");
		this.setRegisterDirectMarket(info.getRegisterDirectMarket());
		this.setIsExistCusIntroductSelected(info.getRegisterInfoSourceIds().contains("4")?"Y":"N");
		this.setRegisterExistCusIntroduct(info.getRegisterExistCusIntroduct());
		this.setIsInfoSourceOtherSelected(info.getRegisterInfoSourceIds().contains("12")?"Y":"N");
		this.setRegisterInfoSourceOther(info.getRegisterInfoSourceOther());	
		
		this.setRegisterSourceFFKKHJS(info.getRegisterSourceFFKKHJS());		
		this.setRegisterSourceWX(info.getRegisterSourceWX());		
		this.setRegisterSourceMM(info.getRegisterSourceMM());
		this.setRegisterSourceCCK(info.getRegisterSourceCCK());
		this.setRegisterSourceHNYGJS(info.getRegisterSourceHNYGJS());
		this.setRegisterSourceDHYX(info.getRegisterSourceDHYX());
		this.setRegisterSourceHZSHTJ(info.getRegisterSourceHZSHTJ());
		this.setRegisterSourceZXSQ(info.getRegisterSourceZXSQ());
		


	}
	
	public void setlnLoanInfo(BaseLnLoanInfo lnLoanInfo){
    	lnLoanInfo.setRegisterMicroBizCenter(this.getRegisterMicroBizCenter());
    	//lnLoanInfo.setRegisterRecommendBank(this.getRegisterRecommendBank());
    	lnLoanInfo.setRegisterApplyDate(DateUtil.strToDate(this.getRegisterApplyDate(),"yyyy年MM月dd日"));
    	lnLoanInfo.setRegisterLoanDate(DateUtil.strToDate(this.getRegisterLoanDate(),"yyyy年MM月dd日"));
    	lnLoanInfo.setRegisterInfoSourceIds(this.getRegisterInfoSourceIds());
    	lnLoanInfo.setRegisterDirectMarket(this.getRegisterDirectMarket());
    	lnLoanInfo.setRegisterExistCusIntroduct(this.getRegisterExistCusIntroduct());
    	lnLoanInfo.setRegisterInfoSourceOther(this.getRegisterInfoSourceOther());
    	lnLoanInfo.setRegisterSaveStatus("Y");
    	
    	lnLoanInfo.setRegisterSourceFFKKHJS(this.getRegisterSourceFFKKHJS());    	
    	lnLoanInfo.setRegisterSourceWX(this.getRegisterSourceWX());
    	lnLoanInfo.setRegisterSourceMM(this.getRegisterSourceMM());
    	lnLoanInfo.setRegisterSourceCCK(this.getRegisterSourceCCK());
    	lnLoanInfo.setRegisterSourceHNYGJS(this.getRegisterSourceHNYGJS());
    	lnLoanInfo.setRegisterSourceDHYX(this.getRegisterSourceDHYX());
    	lnLoanInfo.setRegisterSourceHZSHTJ(this.getRegisterSourceHZSHTJ());
    	lnLoanInfo.setRegisterSourceZXSQ(this.getRegisterSourceZXSQ());
    	
    }
}
