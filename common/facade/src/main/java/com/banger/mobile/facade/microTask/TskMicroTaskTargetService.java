/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :任务目标客户-Service-接口
 * Author     :QianJie
 * Create Date:Mar 2, 2013
 */
package com.banger.mobile.facade.microTask;

import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.customer.CounterOutPintMessage;
import com.banger.mobile.domain.model.microTask.TskMicroTaskTarget;

/**
 * @author QianJie
 * @version $Id: TskMicroTaskTargetService.java,v 0.1 Mar 2, 2013 11:48:04 AM
 *          QianJie Exp $
 */
public interface TskMicroTaskTargetService {

	public void addTaskTarget(TskMicroTaskTarget tskMicroTaskTarget);

	/**
	 * 批量添加
	 * 
	 * @param list
	 */
	public void addTaskTargetBatch(List<TskMicroTaskTarget> list);

	/**
	 * 通过phoneNum查询
	 * 
	 * @param
	 * 
	 * @return
	 */
	public List<TskMicroTaskTarget> getMicroTaskTarget(TskMicroTaskTarget target);

	public Integer getMicroTaskTargetEqualsTarget(TskMicroTaskTarget target);

	public PageUtil<TskMicroTaskTarget> getTargetListByPage(
			Map<String, Object> parameterMap, Page page);

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
	 * 通过路径找到Excel
	 * 
	 * @param Excel文件路径
	 * @return
	 */
	public CounterOutPintMessage excelToDb(String fileName,
			TskMicroTaskTarget taskTarget);

	/**
	 * 查询营销任务完成数量
	 * 
	 * @param parameterMap
	 * @return
	 */
	public Integer getTargetListByPageCount(Map<String, Object> parameterMap);

	// 取得对象byID
	public TskMicroTaskTarget getMicroTaskTargetById(Integer taskTargetId);

	public void editTskTaskTargetCustomer(TskMicroTaskTarget t);

	/**
	 * 修改执行任务时修改任务目标信息
	 * 
	 * @param t
	 */
	public void editTskTaskTargetInfo(TskMicroTaskTarget t);

	/**
	 * 修改执行任务时修改任务目标信息
	 * 
	 * @param （MAP）params
	 */
	public void editTskTaskTargetInfo(Map<String, Object> params);

	/**
	 * 通过电话号码，任务ID查询任务目标
	 * 
	 * @param params
	 * @return
	 */
	public List<TskMicroTaskTarget> getListByPhoneAndTaskId(
			Map<String, Object> params);


    /**
     * 查询客户在此用户下的所有未完成任务安排
     * @param map
     * @return
     */
    public Integer getCustomerTaskCount(Map<String,Object> map);
}
