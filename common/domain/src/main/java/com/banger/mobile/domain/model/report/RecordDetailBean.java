package com.banger.mobile.domain.model.report;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class RecordDetailBean {
	private String dateType;			//日期类型
	private String dateBegin;			//开始日期
	private String dateEnd;				//结束日期
	private String callType;			//联系类型
	private String userId;				//用户Id
	private String deptId;				//机构Id
	private String title;				//标题
	private String name;				//名称
	
	public String getDateType() {
		return dateType;
	}
	public void setDateType(String dateType) {
		this.dateType = dateType;
	}
	public String getDateBegin() {
		return dateBegin;
	}
	public void setDateBegin(String dateBegin) {
		this.dateBegin = dateBegin; 
	}
	public String getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}
	public String getCallType() {
		return callType;
	}
	public void setCallType(String callType) {
		this.callType = callType;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		try {
			this.title = URLDecoder.decode(title,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		try {
			this.name = URLDecoder.decode(name,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		};
	}
	
	
}
