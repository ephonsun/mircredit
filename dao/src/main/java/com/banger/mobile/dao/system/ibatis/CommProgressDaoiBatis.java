/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :沟通进度
 * Author     :zsw
 * Create Date:May 21, 2012
 */
package com.banger.mobile.dao.system.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.dao.system.CommProgressDao;
import com.banger.mobile.domain.model.system.CommProgress;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：May 21, 2012 10:57:44 AM 类说明
 */
public class CommProgressDaoiBatis extends GenericDaoiBatis implements
		CommProgressDao {

	public CommProgressDaoiBatis(Class persistentClass) {
		super(CommProgress.class);
	}

	public CommProgressDaoiBatis() {
		super(CommProgress.class);
	}
	
	/**
	 * 删除沟通进度
	 * @param commProgress
	 */

	public void deleteCommProgress(CommProgress commProgress) {
		this.getSqlMapClientTemplate().delete("deleteCommProgress", commProgress);
	}
	
	/**
	 * 根据id获得沟通记录
	 * @param id
	 * @return CommProgress
	 */
	public CommProgress getCommProgressById(int id) {
		return (CommProgress) this.getSqlMapClientTemplate().queryForObject(
				"getCommProgressById", id);
	}
	/**
	 * 添加沟通进度
	 * @param commProgress
	 */
	public void insertCommProgress(CommProgress commProgress) {
		this.getSqlMapClientTemplate().insert("addCommProgress", commProgress);
	}

	/**
	 * 更新沟通进度
	 */
	public void updateCommProgress(CommProgress commProgress) {
		this.getSqlMapClientTemplate().update("updateCommProgress",
				commProgress);
	}

	/**
	 * 获得沟通进度列表
	 */
	@SuppressWarnings("unchecked")
	public List<CommProgress> getCommProgressList() {
		return (List<CommProgress>) this.getSqlMapClientTemplate()
				.queryForList("getCommProgressList");
	}

	/**
	 * 获得最大或最小序号
	 * @param type
	 * @return
	 */
	private int getMaxOrMinSortNo(String type) {
		Map<String, String> parameterMap = new HashMap<String, String>();
		parameterMap.put("Type", type);
		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"getMaxOrMinSortNo", parameterMap);
	}

	/**
	 * 上移和下移沟通进度
	 * 
	 * @param type
	 *            "up"为上移 "down"为下移
	 */
	private boolean moveCommprogress(int id, String type) {
		CommProgress commProgress = this.getCommProgressById(id);
		if ((type.equals("up") && (this.getMinSortNo() == commProgress.getSortNo()))
			|| (type.equals("down") && (this.getMaxSortNo() == commProgress.getSortNo()))) {
			return false;
		} else {
			Map<String, String> map = new HashMap<String, String>();
			map.put("Type", type);
			map.put("SortNo", commProgress.getSortNo().toString());
			CommProgress commProgress2 = (CommProgress) this
					.getSqlMapClientTemplate().queryForObject(
							"getDesCommProgress", map);
			int desSort = commProgress2.getSortNo();
			commProgress2.setSortNo(commProgress.getSortNo());
			commProgress.setSortNo(desSort);
			this.getSqlMapClientTemplate().update("updateCommProSort",
					commProgress);
			this.getSqlMapClientTemplate().update("updateCommProSort",
					commProgress2);
			return true;
		}
	}

	public Integer getMaxSortNo() {
		return getMaxOrMinSortNo("max");
	}

	public Integer getMinSortNo() {
		return getMaxOrMinSortNo("min");
	}

	public boolean moveDownCommProgress(int id) {
		return this.moveCommprogress(id, "down");
	}

	public boolean moveUpCommProgress(int id) {
		return this.moveCommprogress(id, "up");
	}

	/**
	 * 判断是否已经存在该名称
	 */
	public boolean isExistCommProName(String name) {
		List<CommProgress> commProList = this.getSqlMapClientTemplate().queryForList("getCommProByName", name);
		return commProList.size()!= 0;		
	}

}
