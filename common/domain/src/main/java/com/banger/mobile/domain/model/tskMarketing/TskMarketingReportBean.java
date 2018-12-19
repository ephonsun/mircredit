package com.banger.mobile.domain.model.tskMarketing;

import java.io.Serializable;

public class TskMarketingReportBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8983523860408118245L;
	
	private String excuteId;//用户ID
	
	private String excuteName;//用户名称
	
	
	private String taskCount;//任务总量
	
	private String unAssignRate;//未分配占比
	
	private String unFinish;//未完成数
	
	private String unFinishRate;//未完成数占比
	
	private String finish;//完成数
	
	private String finishRate;//完成占比数

	

	public String getTaskCount() {
		return taskCount;
	}

	public void setTaskCount(String taskCount) {
		this.taskCount = taskCount;
	}

	public String getUnAssignRate() {
		return unAssignRate;
	}

	public void setUnAssignRate(String unAssignRate) {
		this.unAssignRate = unAssignRate;
	}

	public String getUnFinish() {
		return unFinish;
	}

	public void setUnFinish(String unFinish) {
		this.unFinish = unFinish;
	}

	public String getUnFinishRate() {
		return unFinishRate;
	}

	public void setUnFinishRate(String unFinishRate) {
		this.unFinishRate = unFinishRate;
	}

	public String getFinish() {
		return finish;
	}

	public void setFinish(String finish) {
		this.finish = finish;
	}

	public String getFinishRate() {
		return finishRate;
	}

	public void setFinishRate(String finishRate) {
		this.finishRate = finishRate;
	}

	public String getExcuteId() {
		return excuteId;
	}

	public void setExcuteId(String excuteId) {
		this.excuteId = excuteId;
	}

	public String getExcuteName() {
		return excuteName;
	}

	public void setExcuteName(String excuteName) {
		this.excuteName = excuteName;
	}

	

	

}
