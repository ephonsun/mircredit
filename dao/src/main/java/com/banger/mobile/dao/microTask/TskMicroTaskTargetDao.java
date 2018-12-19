/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :任务目标客户-Dao-接口
 * Author     :QianJie
 * Create Date:Mar 2, 2013
 */
package com.banger.mobile.dao.microTask;

import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.microTask.TskMicroTaskTarget;

/**
 * @author QianJie
 * @version $Id: TskMicroTaskTargetDao.java,v 0.1 Mar 2, 2013 11:21:21 AM
 *          QianJie Exp $
 */
public interface TskMicroTaskTargetDao {

	/**
	 * 添加
	 * 
	 * @param tskSchedule
	 */
	public void addTaskTarget(TskMicroTaskTarget tskMicroTaskTarget);

	/**
	 * 批量添加
	 * 
	 * @param list
	 */
	public void addTaskTargetBatch(List<TskMicroTaskTarget> list);

	/**
	 * 分页查询执行目标
	 * 
	 * @param parameterMap
	 * @param page
	 * @return
	 */
	public PageUtil<TskMicroTaskTarget> getTargetListByPage(
			Map<String, Object> parameterMap, Page page);


    /**
     * PAD分页查询执行目标
     *
     * @param parameterMap
     * @param page
     * @return
     */
    public PageUtil<TskMicroTaskTarget> getTargetListForPad(
            Map<String, Object> parameterMap, Page page);

	/**
	 * 分页查询执行目标
	 * 
	 * @return
	 */
	public PageUtil<TskMicroTaskTarget> getTargetList(
			Map<String, Object> parameterMap, Page page);

	/**
	 * 根据电话号码查询
	 * 
	 * @param customerNoStr
	 * @return
	 */
	public List<TskMicroTaskTarget> queryByPhoneNo(TskMicroTaskTarget taskTarget);

	public Integer getMicroTaskTargetEqualsTarget(TskMicroTaskTarget taskTarget);

	/**
	 * 更新数据库
	 * 
	 * @param updateBeanList
	 */
	public void updateTaskTargetBatch(List<TskMicroTaskTarget> updateBeanList);

	/**
	 * 查询营销任务完成数量
	 * 
	 * @param parameterMap
	 * @return
	 */
	public Integer getTargetListByPageCount(Map<String, Object> parameterMap);

	// 电话营销任务自动完成
	public void autoFinish(Map<String, Object> params);

	// 实地营销任务自动完成
	public void autoFinishFoot(Map<String, Object> params);

	public TskMicroTaskTarget getMicroTaskTargetById(Integer taskTargetId);

	public void editTskTaskTargetCustomer(TskMicroTaskTarget t);

	/**
	 * 修改执行任务时修改任务目标信息
	 * 
	 * @param t
	 */
	public void editTskTaskTargetInfo(TskMicroTaskTarget t);

	/**
	 * 根据任务ID查询
	 * 
	 * @return
	 */
	public List<TskMicroTaskTarget> getTskMicroTaskTargetByNameAndNum(
			Map<String, String> map);

	// 根据查询条件查询所有任务目标
	public List<TskMicroTaskTarget> getMicroTaskTargetByParameterMap(
			Map<String, Object> paras);

	// 电话营销任务自动完成 在行客户
	public void autoFinishInSystem(Map<String, Object> paras);

	// 电话营销任务自动完成 非在行客户
	public void autoFinishOutSystem(Map<String, Object> paras);

	// 通过电话号码,姓名,任务ID,归属客户ID查找
	public List<TskMicroTaskTarget> getTskMicTargetsByPhonesAndNames(
			Map<String, Object> map);

	// 执行营销任务修改任务目标信息
	public void editTargetForExeMarkTask(Map<String, Object> params);

	/**
	 * 通过电话号码，任务ID查询任务目标 
	 * 
	 * @param params
	 * @return
	 */
	public List<TskMicroTaskTarget> getListByPhoneAndTaskId(
			Map<String, Object> params);

    /**
     * @param paras
     */
    public void autoFinishOutSystemOverride(Map<String, Object> paras);

    /**
     * 查询客户在此用户下的所有未完成任务安排
     * @param map
     * @return
     */
    public Integer getCustomerTaskCount(Map<String,Object> map);
}
