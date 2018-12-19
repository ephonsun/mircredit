/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :沟通进度...
 * Author     :wumh
 * Create Date:May 22, 2012
 */
package com.banger.mobile.facade.impl.system.basicsdata;

import java.util.List;

import com.banger.mobile.dao.system.CommProgressDao;
import com.banger.mobile.domain.model.system.CommProgress;
import com.banger.mobile.facade.system.CommProgressService;

/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：May 21, 2012 4:49:58 PM
 * 类说明
 */
public class CommProgressServiceImpl implements CommProgressService {
	
	private CommProgressDao commProgressDao;
	
	public void setCommProgressDao(CommProgressDao commProgressDao) {
		this.commProgressDao = commProgressDao;
	}
	/**
	 * 删除沟通记录
	 * @param id
	 */
	public void deleteCommProgress(CommProgress commProgress) {
		// TODO Auto-generated method stub
		commProgressDao.deleteCommProgress(commProgress);		
	}

	/**
	 * 查询沟通进度列表
	 */
	public List<CommProgress> getCommProgressList() {
		// TODO Auto-generated method stub
		return commProgressDao.getCommProgressList();
	}

	/**
	 * 根据id查询沟通进度
	 */
	public CommProgress getCommProgressById(int id) {
		// TODO Auto-generated method stub
		return commProgressDao.getCommProgressById(id);
	}

	/**
	 * 新增沟通进度
	 */
	public void insertCommProgress(CommProgress commProgress) {
		// TODO Auto-generated method stub
		commProgressDao.insertCommProgress(commProgress);
		
	}

	/**
	 * 更新沟通进度
	 */
	public void updateCommProgress(CommProgress commProgress) {
		// TODO Auto-generated method stub
		commProgressDao.updateCommProgress(commProgress);	
	}

	/**
	 * 下移沟通进度
	 */
	public boolean moveDownCommProgress(int id) {
		// TODO Auto-generated method stub
		return commProgressDao.moveDownCommProgress(id);	
	}

	/**
	 * 上移沟通进度
	 */
	public boolean moveUpCommProgress(int id) {
		// TODO Auto-generated method stub
		return commProgressDao.moveUpCommProgress(id);		
	}

	/**
	 * 判断是否存在相同名称
	 */
	public boolean isExistCommProName(String name) {
		// TODO Auto-generated method stub
		return commProgressDao.isExistCommProName(name);
	}	

}



