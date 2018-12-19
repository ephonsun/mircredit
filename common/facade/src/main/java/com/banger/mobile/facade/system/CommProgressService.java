package com.banger.mobile.facade.system;

import java.util.List;

import com.banger.mobile.domain.model.system.CommProgress;

/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：May 21, 2012 4:44:37 PM
 * 类说明
 */
public interface CommProgressService {
	
	/**
	 * 新增沟通进度
	 * @param commProgress
	 */
	public void insertCommProgress(CommProgress commProgress);
	
	/**
	 * 更新沟通进度
	 * @param commProgress
	 */
	public void updateCommProgress(CommProgress commProgress);
	
	/**
	 * 根据id删除沟通进度
	 * @param id
	 */
	public void deleteCommProgress(CommProgress commProgress);
	
	/**
	 * 根据id查询沟通进度
	 * @param id
	 * @return
	 */
	public CommProgress getCommProgressById(int id);
	
	/**
	 * 查询沟通进度列表
	 * @return
	 */
	public List<CommProgress> getCommProgressList();	

	/**
	 * 上移沟通进度
	 * @param commProgress
	 */
	public boolean moveUpCommProgress(int id);
	
	/**
	 * 下移沟通进度
	 * @param commProgress
	 */
	public boolean moveDownCommProgress(int id);
	
	/**
	 * 判断是否存在相同名称
	 * @param name
	 * @return
	 */
	public boolean isExistCommProName(String name);
}



