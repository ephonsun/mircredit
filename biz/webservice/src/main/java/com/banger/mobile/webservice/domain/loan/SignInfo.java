package com.banger.mobile.webservice.domain.loan;

import java.io.Serializable;

import com.banger.mobile.domain.model.base.loan.BaseLnLoanInfo;
import com.banger.mobile.domain.model.pad.PadLoanInfo;
import com.banger.mobile.util.Base64Util;
import com.banger.mobile.util.Uploader;


/**
 * 客户签字
 * @author summerxll
 *
 */
public class SignInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 642858077281200350L;
	private String signInfoPath;	
	private String base64SignInfo;

	public String getSignInfoPath() {
		return signInfoPath==null ? "" : signInfoPath;
	}
	public void setSignInfoPath(String signInfoPath) {
		this.signInfoPath = signInfoPath==null ? "" : signInfoPath;
	}
	
	public String getBase64SignInfo() {
		return base64SignInfo;
	}
	public void setBase64SignInfo(String base64SignInfo) {
		this.base64SignInfo = base64SignInfo;
	}
	
	public SignInfo(){
		
	}
	public SignInfo(PadLoanInfo info){
		this.setSignInfoPath(info.getSignInfoPath());
		Uploader uploader = new Uploader();
		this.setBase64SignInfo(uploader.getImgStr(info.getSignInfoPath()));
	}
	
	public void setlnLoanInfo(BaseLnLoanInfo lnLoanInfo){
		lnLoanInfo.setSignInfoPath(this.getSignInfoPath());
		lnLoanInfo.setSignInfo("Y");
	}

}
