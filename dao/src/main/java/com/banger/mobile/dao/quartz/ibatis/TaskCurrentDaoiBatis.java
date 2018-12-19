/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :TaskCurrent任务
 * Author     :qianj
 * Create Date:Jul 16, 2012
 */
package com.banger.mobile.dao.quartz.ibatis;

import java.util.List;

import com.banger.mobile.dao.quartz.TaskCurrentDao;
import com.banger.mobile.domain.model.quartz.TaskCurrent;
import com.banger.mobile.ibatis.GenericDaoiBatis;


public class TaskCurrentDaoiBatis extends GenericDaoiBatis<TaskCurrent, Long> implements TaskCurrentDao{ 

	public TaskCurrentDaoiBatis(Class<TaskCurrent> persistentClass) {
		super(persistentClass);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 根据任务实例名称查询当前任务实例否是已经存在（针对集群情况）
	 * 
	 * @param taskInsName 不为空或null
	 * @return 查询如果有记录存在则返回true,反之false
	 * @throws Exception
	 */
	public boolean checkCurrentTaskByTaskInsName(String taskInsName)
			throws Exception {
		logger.debug("TaskCurrentDaoiBatis.checkCurrentTaskByTaskInsName method.");
		TaskCurrent taskCurrent = (TaskCurrent) this.getSqlMapClientTemplate()
				.queryForObject("find_task_current_by_taskInsName",
						taskInsName);

		if (taskCurrent == null) {
			return false;
		}
		return true;
	}

	/**
	 * 保存当前需要执行的任务（针对集群情况）
	 * 
	 * @param taskCurrent TaskCurrent不为null
	 * @throws Exception
	 */
	public void insertCurrentTask(TaskCurrent taskCurrent) throws Exception {
		logger.debug("TaskCurrentDaoiBatis.insertCurrentTask method.");
		this.getSqlMapClientTemplate().insert("insert_task_current",
				taskCurrent);
	}
	 public TaskCurrentDaoiBatis(){ 
 		 super(TaskCurrent.class); 
 	 }  
  
	 /**
	  * 添加一条TaskCurrent记录 
	  */
 	 public void addTaskCurrent(TaskCurrent taskCurrent)throws Exception{ 
 		 this.getSqlMapClientTemplate().insert("addTaskCurrent", taskCurrent); 
 	 }  
  
 	 /**
 	  * 更新一条TaskCurrent记录
 	  */
 	 public void editTaskCurrent(TaskCurrent taskCurrent)throws Exception{ 
 		 this.getSqlMapClientTemplate().update("editTaskCurrent", taskCurrent); 
 	 }  
  
 	 /**
 	  *  删除一条TaskCurrent记录
 	  */
 	 public void removeTaskCurrent(Long taskCurrentId)throws Exception{ 
 		 this.getSqlMapClientTemplate().delete("removeTaskCurrent",taskCurrentId); 
 	 }  
  
 	 /**
 	  * 查询一个TaskCurrent结果集,返回TaskCurrent对象
 	  */
 	 public TaskCurrent getTaskCurrent(Long taskCurrentId)throws Exception{ 
 		 return (TaskCurrent)this.getSqlMapClientTemplate().queryForObject("getTaskCurrent", taskCurrentId); 
 	 }  
  
 	 /**
 	  * 查询所有TaskCurrent结果集,返回TaskCurrent对象的集合
 	  */
 	 public List<TaskCurrent> getTaskCurrents()throws Exception{ 
 		 return this.getSqlMapClientTemplate().queryForList("getTaskCurrents", null); 
 	 }  	

}
