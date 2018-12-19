package com.banger.mobile.domain.model.tskContact;

import com.banger.mobile.domain.model.base.tskContact.BaseTskContact;

/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：Dec 17, 2012 9:32:31 AM
 * 类说明
 */
public class TskContact  extends BaseTskContact{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8822715101559408869L;
	
	
	private String                    contactPurposeName;
	private String                    assignUserName;
	private Integer                   hasFinished;                             //已完成任务
	private Integer                   total;                               //总任务数
	private double                    percent;                                 //百分比  
	
	public String getContactPurposeName() {
		return contactPurposeName;
	}
	public void setContactPurposeName(String contactPurposeName) {
		this.contactPurposeName = contactPurposeName;
	}
	public String getAssignUserName() {
		return assignUserName;
	}
	public void setAssignUserName(String assignUserName) {
		this.assignUserName = assignUserName;
	}
	public Integer getHasFinished() {
		return hasFinished;
	}
	public void setHasFinished(Integer hasFinished) {
		this.hasFinished = hasFinished;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public double getPercent() {
		return percent;
	}
	public void setPercent(double percent) {
		this.percent = percent;
	}
}



