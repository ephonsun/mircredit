/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :任务执行明细-Dao-接口
 * Author     :QianJie
 * Create Date:Mar 2, 2013
 */
package com.banger.mobile.dao.microTask;

import java.util.List;
import java.util.Map;

import com.banger.mobile.domain.model.microTask.TskMicroTaskExecute;
import com.banger.mobile.domain.model.microTask.TskMicroTaskExecuteAssign;

/**
 * @author QianJie
 * @version $Id: TskMicroTaskExecute.java,v 0.1 Mar 2, 2013 11:18:35 AM QianJie
 *          Exp $
 */
public interface TskMicroTaskExecuteDao {
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
	public void editTskMicroTaskExecute(List<TskMicroTaskExecute> list);

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
	 * 查询任务执行者已完成任务笔数
	 * 
	 * @param conds
	 * @return
	 */
	public Integer getComTskNumByConds(Map<String, Object> conds);

	/**
	 * 查询机构下分配任务的人员
	 * 
	 * @param conds
	 * @return
	 */
	public List<TskMicroTaskExecute> getSysUsersByTask(Map<String, Object> conds);

	/**
	 * 批量更新任务执行者
	 * 
	 * @param list
	 */
	public void TskMicroTaskExecuteBatch(List<TskMicroTaskExecute> list);

    /**
     * @param paras
     * @return
     */
    public List<TskMicroTaskExecute> getTaskUserExecutable(Map<String, Object> paras);
    
    /**
     * 通过userId查询哪些user已经分配了任务 
     * @param param
     * @return
     */
    public List<Integer> getIsAllocateTaskByUserIds(Map<String,Object>param);
}
