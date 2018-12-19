package com.banger.mobile.domain.model.crRequest;

import com.banger.mobile.domain.model.base.crRequest.BaseCrRequest;
import com.banger.mobile.domain.model.loan.LnLoanInfoDictionary;

/**
 * Created by BH-TCL on 15-1-19.
 */
public class CrPhoto extends BaseCrRequest {
	
	private String fileId;
	private String filePath;
	private String storageType;
	private String fileName;
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getStorageType() {
		return storageType;
	}
	public void setStorageType(String storageType) {
		this.storageType = storageType;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	

	
}
