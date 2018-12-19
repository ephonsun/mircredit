/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :TaskIns任务
 * Author     :qianj
 * Create Date:Jul 16, 2012
 */
package com.banger.mobile.dao.quartz;

import java.util.List;

import com.banger.mobile.domain.model.quartz.TaskIns;
import com.banger.mobile.ibatis.GenericDao;



public interface TaskInsDao extends GenericDao<TaskIns, Long> { 
	
	/**
	 * 保存当前任务信息及执行信息（执行起、止、所用时间、执行结果等）
	 * 
	 * @param taskIns TaskIns不为null
	 * @throws Exception
	 */
	public void insertTaskInsInfo(TaskIns taskIns) throws Exception;
	
	/**
	 * 添加一条TaskIns记录 
	 * @param taskIns
	 * @throws Exception
	 */
 	void addTaskIns(TaskIns taskIns) throws Exception; 
  
 	/**
 	 * 更新一条TaskIns记录
 	 * @param taskIns
 	 * @throws Exception
 	 */
 	void editTaskIns(TaskIns taskIns) throws Exception; 
  
 	/**
 	 * 删除一条TaskIns记录
 	 * @param taskInsId
 	 * @throws Exception
 	 */
 	void removeTaskIns(Long taskInsId) throws Exception; 
  
 	/**
 	 * 查询一个TaskIns结果集,返回TaskIns对象
 	 * @param taskInsId
 	 * @return
 	 * @throws Exception
 	 */
 	TaskIns getTaskIns(Long taskInsId) throws Exception; 
  
 	/**
 	 * 查询所有TaskIns结果集,返回TaskIns对象的集合
 	 * @return
 	 * @throws Exception
 	 */
 	List <TaskIns> getTaskInss() throws Exception; 
 	
}
