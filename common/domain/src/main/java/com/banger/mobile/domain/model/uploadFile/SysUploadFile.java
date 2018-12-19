package com.banger.mobile.domain.model.uploadFile;

import com.banger.mobile.domain.model.base.uploadFile.BaseSysUploadFile;


/**
 * @author ouyl
 * @version $Id: SysUploadFile.java v 0.1 ${} 下午3:08 ouyl Exp $
 */
public class SysUploadFile extends BaseSysUploadFile{
	
    private static final long serialVersionUID = -7837535032901372130L;
    private String userName;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
    
}
