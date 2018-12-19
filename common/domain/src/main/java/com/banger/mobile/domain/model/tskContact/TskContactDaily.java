package com.banger.mobile.domain.model.tskContact;

import java.io.Serializable;

import com.banger.mobile.domain.model.base.BaseObject;

/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：Nov 12, 2012 1:58:09 PM
 * 类说明
 */
public class TskContactDaily extends BaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4204652551300737144L;
	private Integer           userId;                               //执行者ID
	private String            userName;                             //执行者Name
	private double            dailyNum;                             //日均联系量
	private Integer           totalTarget;                          //总联系量

	public double getDailyNum() {
		return dailyNum;
	}
	public void setDailyNum(double dailyNum) {
		this.dailyNum = dailyNum;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getTotalTarget() {
		return totalTarget;
	}
	public void setTotalTarget(Integer totalTarget) {
		this.totalTarget = totalTarget;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(dailyNum);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((totalTarget == null) ? 0 : totalTarget.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result
				+ ((userName == null) ? 0 : userName.hashCode());
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
		final TskContactDaily other = (TskContactDaily) obj;
		if (Double.doubleToLongBits(dailyNum) != Double
				.doubleToLongBits(other.dailyNum))
			return false;
		if (totalTarget == null) {
			if (other.totalTarget != null)
				return false;
		} else if (!totalTarget.equals(other.totalTarget))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}
}



