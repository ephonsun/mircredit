package com.banger.mobile.domain.model.crRequest;

import com.banger.mobile.domain.model.base.crRequest.BaseCrRequest;
import com.banger.mobile.domain.model.loan.LnLoanInfoDictionary;

/**
 * Created by BH-TCL on 15-1-19.
 */
public class CrRequest extends BaseCrRequest {
	
	private String requestStatusValue;
	private String customerName;
	private String idCard;
	private String requestName; //请求人
	private String conductorName;//处理人
	
	private String photoType;
	
	public String getPhotoType() {
		return photoType;
	}
	public void setPhotoType(String photoType) {
		this.photoType = photoType;
	}
	
	
	public String getConductorName() {
		return conductorName;
	}
	public void setConductorName(String conductorName) {
		this.conductorName = conductorName;
	}
	public String getRequestStatusValue() {
		return requestStatusValue;
	}
	public void setRequestStatusValue(String requestStatusValue) {
		this.requestStatusValue = requestStatusValue;
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
	public String getRequestName() {
		return requestName;
	}
	public void setRequestName(String requestName) {
		this.requestName = requestName;
	}
	
	
}
