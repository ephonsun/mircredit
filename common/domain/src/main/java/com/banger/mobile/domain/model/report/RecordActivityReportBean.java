package com.banger.mobile.domain.model.report;

import java.util.Date;

public class RecordActivityReportBean {
	private String deptIds;				//机构Ids
	private String userIds;				//用户Ids
	private String belongTo;			//归属
	private Date dateBegin;				//开始日期
	private Date dateEnd;				//结束日期
	private Integer containSub;			//是否包含下属
	
	public String getDeptIds() {
		return deptIds;
	}
	public void setDeptIds(String deptIds) {
		this.deptIds = deptIds;
	}
	public String getUserIds() {
		return userIds;
	}
	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}
	public String getBelongTo() {
		return belongTo;
	}
	public void setBelongTo(String belongTo) {
		this.belongTo = belongTo;
	}
	public Date getDateBegin() {
		return dateBegin;
	}
	public void setDateBegin(Date dateBegin) {
		this.dateBegin = dateBegin;
	}
	public Date getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}
	public Integer getContainSub() {
		return containSub;
	}
	public void setContainSub(Integer containSub) {
		this.containSub = containSub;
	}
	
}
