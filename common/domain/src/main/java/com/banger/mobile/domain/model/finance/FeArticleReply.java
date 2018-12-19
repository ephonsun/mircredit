package com.banger.mobile.domain.model.finance;

import java.util.Date;

import com.banger.mobile.domain.model.base.finance.BaseFeArticleReply;

/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：Dec 8, 2012 4:54:14 PM
 * 类说明
 * 评论回复domain实体类
 */
public class FeArticleReply extends BaseFeArticleReply {

	/**
	 * 
	 */
	private static final long serialVersionUID = 266431822997157940L;
	
	private String                   userName;             //姓名
	private String                   salesRank;			   //业绩排名、
	private Integer                  deptId;               //归属机构ID
	
	private String                   deptName;             //归属机构全名
	private String                   publishTime;          //发表时间          

	public FeArticleReply() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FeArticleReply(Integer replyId, Integer discessId,
			String replyContent, Date createDate, Date updateDate,
			Integer createUser, Integer updateUser) {
		super(replyId, discessId, replyContent, createDate, updateDate, createUser,
				updateUser);
		// TODO Auto-generated constructor stub
	}

	public FeArticleReply(Integer replyId, Integer discessId,
			String replyContent, Date createDate, Integer createUser) {
		super(replyId, discessId, replyContent, createDate, createUser);
		// TODO Auto-generated constructor stub
	}

	public FeArticleReply(String userName, String salesRank, Integer deptId,
			String deptName, String publishTime) {
		super();
		this.userName = userName;
		this.salesRank = salesRank;
		this.deptId = deptId;
		this.deptName = deptName;
		this.publishTime = publishTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((deptId == null) ? 0 : deptId.hashCode());
		result = prime * result
				+ ((deptName == null) ? 0 : deptName.hashCode());
		result = prime * result
				+ ((publishTime == null) ? 0 : publishTime.hashCode());
		result = prime * result
				+ ((salesRank == null) ? 0 : salesRank.hashCode());
		result = prime * result
				+ ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		final FeArticleReply other = (FeArticleReply) obj;
		if (deptId == null) {
			if (other.deptId != null)
				return false;
		} else if (!deptId.equals(other.deptId))
			return false;
		if (deptName == null) {
			if (other.deptName != null)
				return false;
		} else if (!deptName.equals(other.deptName))
			return false;
		if (publishTime == null) {
			if (other.publishTime != null)
				return false;
		} else if (!publishTime.equals(other.publishTime))
			return false;
		if (salesRank == null) {
			if (other.salesRank != null)
				return false;
		} else if (!salesRank.equals(other.salesRank))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
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

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

}



