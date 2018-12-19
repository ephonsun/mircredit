package com.banger.mobile.domain.model.loan;

import java.util.ArrayList;
import java.util.List;

import com.banger.mobile.domain.model.base.loan.BaseLnLoanMonitor;
import com.banger.mobile.domain.model.uploadFile.SysUploadFile;


/**
 * 
 * 
 * @author linkin
 * @version $Id: LnLoanMonitor.java, v 0.1 2016-1-10 下午3:04:52 linkin Exp $
 */
public class LnLoanMonitor extends BaseLnLoanMonitor {
	private String userName;     //用户名
	private String monTypeName;	 //监控类型名称
	private String revisitTypeName; //回访类型
	private List<SysUploadFile> fileIdList;
	List<String> fileUrls = new ArrayList<String>();
	List<String> fileNames = new ArrayList<String>();
	
	public String getMonTypeName() {
		return monTypeName;
	}

	public void setMonTypeName(String monTypeName) {
		this.monTypeName = monTypeName;
	}

	public String getRevisitTypeName() {
		return revisitTypeName;
	}

	public void setRevisitTypeName(String revisitTypeName) {
		this.revisitTypeName = revisitTypeName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<SysUploadFile> getFileIdList() {
		return fileIdList;
	}

	public void setFileIdList(List<SysUploadFile> fileIdList) {
		this.fileIdList = fileIdList;
	}

	public List<String> getFileUrls() {
		return fileUrls;
	}

	public void setFileUrls(List<String> fileUrls) {
		this.fileUrls = fileUrls;
	}

	public List<String> getFileNames() {
		return fileNames;
	}

	public void setFileNames(List<String> fileNames) {
		this.fileNames = fileNames;
	}

	

	
	
}
