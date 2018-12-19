/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :TaskIns任务
 * Author     :qianj
 * Create Date:Jul 16, 2012
 */
package com.banger.mobile.dao.quartz.ibatis;

import java.util.List;

import com.banger.mobile.dao.quartz.TaskInsDao;
import com.banger.mobile.domain.model.quartz.TaskIns;
import com.banger.mobile.ibatis.GenericDaoiBatis;


public class TaskInsDaoiBatis extends GenericDaoiBatis<TaskIns, Long> implements TaskInsDao{ 
	
	public TaskInsDaoiBatis(Class<TaskIns> persistentClass) {
		super(persistentClass);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 保存当前任务信息及执行信息（执行起、止、所用时间、执行结果等）
	 * 
	 * @param taskIns TaskIns不为null
	 * @throws Exception
	 */
	public void insertTaskInsInfo(TaskIns taskIns) throws Exception {
		logger.debug("TaskInsDaoiBatis.insertTaskInsInfo method.");
		this.getSqlMapClientTemplate().insert("insert_task_ins",
				taskIns);
		
	}
	
	 public TaskInsDaoiBatis(){ 
 		 super(TaskIns.class); 
 	 }  
  
	 /**
	  * 添加一条TaskIns记录 
	  */
 	 public void addTaskIns(TaskIns taskIns)throws Exception{ 
 		 this.getSqlMapClientTemplate().insert("addTaskIns", taskIns); 
 	 }  
  
 	 /**
 	  * 更新一条TaskIns记录
 	  */
 	 public void editTaskIns(TaskIns taskIns)throws Exception{ 
 		 this.getSqlMapClientTemplate().update("editTaskIns", taskIns); 
 	 }  
  
 	 /**
 	  * 删除一条TaskIns记录
 	  */
 	 public void removeTaskIns(Long taskInsId)throws Exception{ 
 		 this.getSqlMapClientTemplate().delete("removeTaskIns",taskInsId); 
 	 }  
  
 	 /**
 	  * 查询一个TaskIns结果集,返回TaskIns对象
 	  */
 	 public TaskIns getTaskIns(Long taskInsId)throws Exception{ 
 		 return (TaskIns)this.getSqlMapClientTemplate().queryForObject("getTaskIns", taskInsId); 
 	 }  
  
 	 /**
 	  * 查询所有TaskIns结果集,返回TaskIns对象的集合 
 	  */
 	 public List<TaskIns> getTaskInss()throws Exception{ 
 		 return this.getSqlMapClientTemplate().queryForList("getTaskInss", null); 
 	 }  	

}
