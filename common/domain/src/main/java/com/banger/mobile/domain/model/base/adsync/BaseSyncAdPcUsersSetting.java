/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :AD域帐号同步配置
 * Author     :zsw
 * Create Date:Jun 4, 2012
 */
package com.banger.mobile.domain.model.base.adsync;

import java.util.Date;

public class BaseSyncAdPcUsersSetting {
	private Integer adId;									 //主键
	private Integer adActived;							    //是否启用
	private String adName;							       //域名
	private String adLdapUrl;							  //AD域地址
	private String adAdminName;							 //AD域管理帐号
	private String adAdminPassword;						//AD域管理密码
	private String adAdminPwEnctypt;				   //AD域管理密码加密方式
	private String adSyncDeptNode;					  //AD域部门节点名称
	private Integer adSyncDeptId;					 //同步目标机构
	private String adSyncMode;						//同步模式
	private Date adSyncTime;					   //同步时间
	private String adSyncRate;					  //同步频率类型
	private String adSyncRateSetting;			 //同步频率设定值
	private Date createDate;            		//创建时间
	private Date updateDate;            	   //更新时间
	private Integer createUser;               //创建用户
	private Integer updateUser;              //更新用户
	   
	   
	public Integer getAdId() {
		return adId;
	}
	public void setAdId(Integer adId) {
		this.adId = adId;
	}
	public Integer getAdActived() {
		return adActived;
	}
	public void setAdActived(Integer adActived) {
		this.adActived = adActived;
	}
	public String getAdLdapUrl() {
		return adLdapUrl;
	}
	public void setAdLdapUrl(String adLdapUrl) {
		this.adLdapUrl = adLdapUrl;
	}
	public String getAdAdminName() {
		return adAdminName;
	}
	public void setAdAdminName(String adAdminName) {
		this.adAdminName = adAdminName;
	}
	public String getAdAdminPassword() {
		return adAdminPassword;
	}
	public void setAdAdminPassword(String adAdminPassword) {
		this.adAdminPassword = adAdminPassword;
	}
	public String getAdAdminPwEnctypt() {
		return adAdminPwEnctypt;
	}
	public void setAdAdminPwEnctypt(String adAdminPwEnctypt) {
		this.adAdminPwEnctypt = adAdminPwEnctypt;
	}
	public String getAdSyncDeptNode() {
		return adSyncDeptNode;
	}
	public void setAdSyncDeptNode(String adSyncDeptNode) {
		this.adSyncDeptNode = adSyncDeptNode;
	}
	public Integer getAdSyncDeptId() {
		return adSyncDeptId;
	}
	public void setAdSyncDeptId(Integer adSyncDeptId) {
		this.adSyncDeptId = adSyncDeptId;
	}
	public String getAdSyncMode() {
		return adSyncMode;
	}
	public void setAdSyncMode(String adSyncMode) {
		this.adSyncMode = adSyncMode;
	}
	public Date getAdSyncTime() {
		return adSyncTime;
	}
	public void setAdSyncTime(Date adSyncTime) {
		this.adSyncTime = adSyncTime;
	}
	public String getAdSyncRate() {
		return adSyncRate;
	}
	public void setAdSyncRate(String adSyncRate) {
		this.adSyncRate = adSyncRate;
	}
	public String getAdSyncRateSetting() {
		return adSyncRateSetting;
	}
	public void setAdSyncRateSetting(String adSyncRateSetting) {
		this.adSyncRateSetting = adSyncRateSetting;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Integer getCreateUser() {
		return createUser;
	}
	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}
	public Integer getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(Integer updateUser) {
		this.updateUser = updateUser;
	}
	public String getAdName() {
		return adName;
	}
	public void setAdName(String adName) {
		this.adName = adName;
	}
	
	
}
