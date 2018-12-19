/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :TaskCurrent任务
 * Author     :qianj
 * Create Date:Jul 16, 2012
 */
package com.banger.mobile.dao.quartz;

import java.util.List;

import com.banger.mobile.domain.model.quartz.TaskCurrent;
import com.banger.mobile.ibatis.GenericDao;


public interface TaskCurrentDao extends GenericDao<TaskCurrent, Long> { 

	/**
	 * 保存当前需要执行的任务（针对集群情况）
	 * 
	 * @param taskCurrent TaskCurrent不为null
	 * @throws Exception
	 */
	public void insertCurrentTask(TaskCurrent taskCurrent)
			throws Exception;

	/**
	 * 根据任务实例名称查询当前任务实例否是已经存在（针对集群情况）
	 * 
	 * @param taskInsName 不为空或null
	 * @return 查询如果有记录存在则返回true,反之false
	 * @throws Exception
	 */
	public boolean checkCurrentTaskByTaskInsName(String taskInsName)
			throws Exception;
	
	/**
	 * 添加一条TaskCurrent记录
	 * @param taskCurrent
	 * @throws Exception
	 */
 	void addTaskCurrent(TaskCurrent taskCurrent) throws Exception; 
  
 	/**
 	 * 更新一条TaskCurrent记录
 	 * @param taskCurrent
 	 * @throws Exception
 	 */
 	void editTaskCurrent(TaskCurrent taskCurrent) throws Exception; 
  
 	/**
 	 * 删除一条TaskCurrent记录
 	 * @param taskCurrentId
 	 * @throws Exception
 	 */
 	void removeTaskCurrent(Long taskCurrentId) throws Exception; 
  
 	/**
 	 * 查询一个TaskCurrent结果集,返回TaskCurrent对象
 	 * @param taskCurrentId
 	 * @return
 	 * @throws Exception
 	 */
 	TaskCurrent getTaskCurrent(Long taskCurrentId) throws Exception; 
  
 	/**
 	 * 查询所有TaskCurrent结果集,返回TaskCurrent对象的集合
 	 * @return
 	 * @throws Exception
 	 */
 	List <TaskCurrent> getTaskCurrents() throws Exception; 	
	
}
