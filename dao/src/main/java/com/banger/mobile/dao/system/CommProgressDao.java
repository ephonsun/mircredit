/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :沟通进度
 * Author     :zsw
 * Create Date:May 21, 2012
 */
package com.banger.mobile.dao.system;
import java.util.List;

import com.banger.mobile.domain.model.system.CommProgress;

/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：May 21, 2012 10:50:46 AM
 * 类说明
 */
public interface CommProgressDao {

	/**
	 * 添加沟通进度
	 * @param commProgress
	 */
	public void insertCommProgress(CommProgress commProgress);

	/**
	 * 修改沟通进度
	 * @param commProgress
	 */
	public void updateCommProgress(CommProgress commProgress);

	/**
	 * 删除沟通进度
	 * @param commProgress
	 */
	public void deleteCommProgress(CommProgress commProgress);

	/**
	 * 根据id获得沟通记录
	 * @param id
	 * @return CommProgress
	 */
	public CommProgress getCommProgressById(int id);
	
	/**
	 * 获得沟通进度列表
	 * @return
	 */
	public List<CommProgress> getCommProgressList();
	
	/**
	 * 获得最大序号
	 * @return
	 */
	public Integer getMaxSortNo();
	
	/**
	 * 获得最小序号
	 * @return
	 */
	public Integer getMinSortNo();
	
	/**
	 * 上移沟通进度
	 */
	public boolean moveUpCommProgress(int id);
	
	/**
	 * 下移沟通进度
	 */
	public boolean moveDownCommProgress(int id);
	
	/**
	 * 判断是否存在相同的名称
	 * @param name
	 */
	public boolean isExistCommProName(String name);
	
	


}
