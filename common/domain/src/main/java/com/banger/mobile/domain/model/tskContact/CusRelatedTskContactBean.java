package com.banger.mobile.domain.model.tskContact;

import java.io.Serializable;
import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;

/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：Nov 13, 2012 1:18:28 PM
 * 类说明
 */
public class CusRelatedTskContactBean extends BaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6413773514350248481L;
	
	private Integer                       contactId;               //任务ID
	private String                        contactTitle;            //任务标题
	private Date                          startDate;               //开始日期
	private Date                          endDate;                //结束日期	
	private Integer                       isEmergency;             //0：不紧急 1：紧急
	private String                        contactPurposeName;     //联系目的name
	private String                        assignUserName;         //分配者名字
    private Integer                       hasFinishedTask;        //已经完成的任务
    private Integer                       totalTask;              //总任务数
    private double                        finishRate;             //完成情况
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public double getFinishRate() {
		return finishRate;
	}
	public void setFinishRate(double finishRate) {
		this.finishRate = finishRate;
	}
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
	public Integer getIsEmergency() {
		return isEmergency;
	}
	public void setIsEmergency(Integer isEmergency) {
		this.isEmergency = isEmergency;
	}
	public Integer getHasFinishedTask() {
		return hasFinishedTask;
	}
	public void setHasFinishedTask(Integer hasFinishedTask) {
		this.hasFinishedTask = hasFinishedTask;
	}
	public Integer getTotalTask() {
		return totalTask;
	}
	public void setTotalTask(Integer totalTask) {
		this.totalTask = totalTask;
	}
	public Integer getContactId() {
		return contactId;
	}
	public void setContactId(Integer contactId) {
		this.contactId = contactId;
	}
	public String getContactTitle() {
		return contactTitle;
	}
	public void setContactTitle(String contactTitle) {
		this.contactTitle = contactTitle;
	}
}



