/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :中信银行所有联系任务列表实体
 * Author     :yujh
 * Create Date:Nov 6, 2012
 */
package com.banger.mobile.domain.model.tskContact;

import java.io.Serializable;
import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;
/**
 * @author huangk
 * @version $Id: TskTaskListBean.java,v 0.1 Nov 6, 2012 7:06:00 PM Administrator Exp $
 */
public class TskTaskListBean extends BaseObject implements Serializable {
    private static final long serialVersionUID = -1950596625741438281L;
    private Integer           contactId;
    private String            contactTitle;                            //任务标题
    private String            taskPurpose;                             //联系目的
    private Date              startDate;                               //开始日期
    private Date              endDate;                                 //结束日期
    private Integer           hasfinishedTask;                         //已完成任务
    private Integer           totalTask;                               //总任务数
    private String            assignName;                              //分配者
    private Integer           assignId;                                //分配者id
    private Integer           exeUserId;                               //执行者id
    private double            percent;                                 //百分比  
    private Integer           isNextContact;                           //是否下次联系
    private Integer           isEmergency;                             //是否紧急
    private Integer			  isSuspend;							   //是否中止
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
	public String getTaskPurpose() {
		return taskPurpose;
	}
	public void setTaskPurpose(String taskPurpose) {
		this.taskPurpose = taskPurpose;
	}
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
	public Integer getHasfinishedTask() {
		return hasfinishedTask;
	}
	public void setHasfinishedTask(Integer hasfinishedTask) {
		this.hasfinishedTask = hasfinishedTask;
	}
	public Integer getTotalTask() {
		return totalTask;
	}
	public void setTotalTask(Integer totalTask) {
		this.totalTask = totalTask;
	}
	public String getAssignName() {
		return assignName;
	}
	public void setAssignName(String assignName) {
		this.assignName = assignName;
	}
	public Integer getAssignId() {
		return assignId;
	}
	public void setAssignId(Integer assignId) {
		this.assignId = assignId;
	}
	public Integer getExeUserId() {
		return exeUserId;
	}
	public void setExeUserId(Integer exeUserId) {
		this.exeUserId = exeUserId;
	}
	public double getPercent() {
		return percent;
	}
	public void setPercent(double percent) {
		this.percent = percent;
	}
	public Integer getIsNextContact() {
		return isNextContact;
	}
	public void setIsNextContact(Integer isNextContact) {
		this.isNextContact = isNextContact;
	}
	public Integer getIsEmergency() {
		return isEmergency;
	}
	public void setIsEmergency(Integer isEmergency) {
		this.isEmergency = isEmergency;
	}
	public Integer getIsSuspend() {
		return isSuspend;
	}
	public void setIsSuspend(Integer isSuspend) {
		this.isSuspend = isSuspend;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((assignId == null) ? 0 : assignId.hashCode());
		result = prime * result
				+ ((assignName == null) ? 0 : assignName.hashCode());
		result = prime * result
				+ ((contactId == null) ? 0 : contactId.hashCode());
		result = prime * result
				+ ((contactTitle == null) ? 0 : contactTitle.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result
				+ ((exeUserId == null) ? 0 : exeUserId.hashCode());
		result = prime * result
				+ ((hasfinishedTask == null) ? 0 : hasfinishedTask.hashCode());
		result = prime * result
				+ ((isEmergency == null) ? 0 : isEmergency.hashCode());
		result = prime * result
				+ ((isNextContact == null) ? 0 : isNextContact.hashCode());
		result = prime * result
				+ ((isSuspend == null) ? 0 : isSuspend.hashCode());
		long temp;
		temp = Double.doubleToLongBits(percent);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result
				+ ((taskPurpose == null) ? 0 : taskPurpose.hashCode());
		result = prime * result
				+ ((totalTask == null) ? 0 : totalTask.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TskTaskListBean other = (TskTaskListBean) obj;
		if (assignId == null) {
			if (other.assignId != null)
				return false;
		} else if (!assignId.equals(other.assignId))
			return false;
		if (assignName == null) {
			if (other.assignName != null)
				return false;
		} else if (!assignName.equals(other.assignName))
			return false;
		if (contactId == null) {
			if (other.contactId != null)
				return false;
		} else if (!contactId.equals(other.contactId))
			return false;
		if (contactTitle == null) {
			if (other.contactTitle != null)
				return false;
		} else if (!contactTitle.equals(other.contactTitle))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (exeUserId == null) {
			if (other.exeUserId != null)
				return false;
		} else if (!exeUserId.equals(other.exeUserId))
			return false;
		if (hasfinishedTask == null) {
			if (other.hasfinishedTask != null)
				return false;
		} else if (!hasfinishedTask.equals(other.hasfinishedTask))
			return false;
		if (isEmergency == null) {
			if (other.isEmergency != null)
				return false;
		} else if (!isEmergency.equals(other.isEmergency))
			return false;
		if (isNextContact == null) {
			if (other.isNextContact != null)
				return false;
		} else if (!isNextContact.equals(other.isNextContact))
			return false;
		if (isSuspend == null) {
			if (other.isSuspend != null)
				return false;
		} else if (!isSuspend.equals(other.isSuspend))
			return false;
		if (Double.doubleToLongBits(percent) != Double
				.doubleToLongBits(other.percent))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (taskPurpose == null) {
			if (other.taskPurpose != null)
				return false;
		} else if (!taskPurpose.equals(other.taskPurpose))
			return false;
		if (totalTask == null) {
			if (other.totalTask != null)
				return false;
		} else if (!totalTask.equals(other.totalTask))
			return false;
		return true;
	}
	public TskTaskListBean(Integer contactId, String contactTitle,
			String taskPurpose, Date startDate, Date endDate,
			Integer hasfinishedTask, Integer totalTask, String assignName,
			Integer assignId, Integer exeUserId, double percent,
			Integer isNextContact, Integer isEmergency, Integer isSuspend) {
		super();
		this.contactId = contactId;
		this.contactTitle = contactTitle;
		this.taskPurpose = taskPurpose;
		this.startDate = startDate;
		this.endDate = endDate;
		this.hasfinishedTask = hasfinishedTask;
		this.totalTask = totalTask;
		this.assignName = assignName;
		this.assignId = assignId;
		this.exeUserId = exeUserId;
		this.percent = percent;
		this.isNextContact = isNextContact;
		this.isEmergency = isEmergency;
		this.isSuspend = isSuspend;
	}
	public TskTaskListBean() {
		super();
		// TODO Auto-generated constructor stub
	}
}
