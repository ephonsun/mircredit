package com.banger.mobile.domain.model.finance;

import com.banger.mobile.domain.model.base.finance.BaseFeArticleDiscess;

/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：Dec 8, 2012 4:54:01 PM
 * 类说明
 */
public class FeArticleDiscess extends BaseFeArticleDiscess {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4928999154158037821L;
	
	private String                   userName;             //姓名
	private String                   salesRank;			   //业绩排名、
	private Integer                  deptId;               //归属机构ID
	private Integer                  isSupport;            //是否支持过了
	
	private String                   deptName;             //归属机构全名
	private String                   publishTime;          //发表时间
	
	public FeArticleDiscess() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public FeArticleDiscess(String userName, String salesRank,Integer deptId, Integer isSupport, String deptName,String publishTime) {
		super();
		this.userName = userName;
		this.salesRank = salesRank;
		this.deptId = deptId;
		this.isSupport = isSupport;
		this.deptName = deptName;
		this.publishTime = publishTime;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSalesRank() {
		return salesRank;
	}
	public void setSalesRank(String salesRank) {
		this.salesRank = salesRank;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	public Integer getIsSupport() {
		return isSupport;
	}
	public void setIsSupport(Integer isSupport) {
		this.isSupport = isSupport;
	}

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

}



