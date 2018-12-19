/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :任务执行明细-Service-接口
 * Author     :QianJie
 * Create Date:Mar 2, 2013
 */
package com.banger.mobile.facade.microTask;

import java.util.List;
import java.util.Map;

import com.banger.mobile.domain.model.microTask.TskMicroTask;
import com.banger.mobile.domain.model.microTask.TskMicroTaskExecute;
import com.banger.mobile.domain.model.microTask.TskMicroTaskExecuteAssign;

/**
 * @author QianJie
 * @version $Id: TskMicroTaskExecuteService.java,v 0.1 Mar 2, 2013 11:47:43 AM
 *          QianJie Exp $
 */
public interface TskMicroTaskExecuteService {
	/**
	 * 添加任务执行者
	 * 
	 * @param tskMicroTask
	 */
	public void addTskMicroTaskExecute(TskMicroTaskExecute tskMicroTaskExecute);

	/**
	 * 批量添加任务执行者
	 * 
	 * @param list
	 */
	public void addTskMicroTaskExecuteBatch(List<TskMicroTaskExecute> list);

	/**
	 * 删除任务执行者
	 * 
	 * @param conds
	 */
	public void delTskMicroTaskExecuteByConds(Map<String, Object> conds);

	/**
	 * 编辑任务执行者
	 * 
	 * @param tskMicroTask
	 */
	public void editTskMicroTaskExecute(TskMicroTaskExecute tskMicroTaskExecute);

	/**
	 * 批量编辑任务执行者
	 * 
	 * @param list
	 */
	public void editTskMicroTaskExecuteBatch(List<TskMicroTaskExecute> list);

	/**
	 * 查询所有任务执行者数据
	 * 
	 * @param conds
	 * @return
	 */
	public List<TskMicroTaskExecute> getAllTskMicroTaskExecuteByConds(
			Map<String, Object> conds);

	/**
	 * 查询所有任务执行者数据
	 * 
	 * @param conds
	 * @return
	 */
	public List<TskMicroTaskExecute> getAllTskMicroTaskExecuteByCondsProgress(
			Map<String, Object> conds);

	/**
	 * 查询我已经分配出去的任务目标
	 * 
	 * @param conds
	 * @return
	 */
	public Integer getMyTargetByConds(Map<String, Object> conds);

	/**
	 * 查询所有任务执行者完成情况列表
	 * 
	 * @param tskMicroTask
	 * @param userId
	 * @return
	 */
	public List<TskMicroTaskExecuteAssign> getAllTskMicroTaskExecuteAssignByConds(
			TskMicroTask tskMicroTask, Integer userId);

	/**
	 * 查询机构下分配任务的人员
	 * 
	 * @param conds
	 * @return
	 */
	public List<TskMicroTaskExecute> getSysUsersByTask(Map<String, Object> conds);

	/**
	 * 查询任务执行者已完成任务笔数
	 * 
	 * @param conds
	 * @return
	 */
	public Integer getComTskNumByConds(Map<String, Object> conds);

	/**
	 * @param paras
	 * @return
	 */
	public List<TskMicroTaskExecute> getTaskUserExecutable(
			Map<String, Object> paras);

	/**
	 * 通过userId查询哪些user已经分配了任务
	 * 
	 * @param map
	 * @return
	 */
	public List<Integer> getAllocateTaskByUsers(Map<String, Object> map);

}
