package com.banger.mobile.domain.model.tskContact;

import java.io.Serializable;
import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;

/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：Nov 21, 2012 3:01:36 PM
 * 类说明
 */
public class TaskContactExeDetail extends BaseObject implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 522188962359891800L;
	
	private String                      userName;
	private Date                        startDate;
	private Date                        endDate;
	private Integer                     leftTarget;
	private Integer                     totalTarget;
	private double                      leftDaily;
	public TaskContactExeDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TaskContactExeDetail(String userName, Date startDate, Date endDate,
			Integer leftTarget, Integer totalTarget, double leftDaily) {
		super();
		this.userName = userName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.leftTarget = leftTarget;
		this.totalTarget = totalTarget;
		this.leftDaily = leftDaily;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public Integer getLeftTarget() {
		return leftTarget;
	}
	public void setLeftTarget(Integer leftTarget) {
		this.leftTarget = leftTarget;
	}
	public Integer getTotalTarget() {
		return totalTarget;
	}
	public void setTotalTarget(Integer totalTarget) {
		this.totalTarget = totalTarget;
	}
	public double getLeftDaily() {
		return leftDaily;
	}
	public void setLeftDaily(double leftDaily) {
		this.leftDaily = leftDaily;
	}
}



