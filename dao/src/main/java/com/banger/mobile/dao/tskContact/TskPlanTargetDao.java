/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :cheny
 * Create Date:2012-12-18
 */
package com.banger.mobile.dao.tskContact;

import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.tskContact.TaskPlanCustomerDetailBean;
import com.banger.mobile.domain.model.tskContact.TskPlanDateTarget;
import com.banger.mobile.domain.model.tskContact.TskPlanTarget;

/**
 * @author cheny
 * @version $Id: TskPlanTargetDao.java,v 0.1 2012-12-18 下午2:30:31 cheny Exp $
 */
public interface TskPlanTargetDao {
    /**
     * 新增联系计划目标客户
     */
    public Integer insertTskPlanTarget(TskPlanTarget target);
    /**
     *  编辑 联系计划目标客户
     */
    public void updateTskPlanTarget(TskPlanTarget target);
    /**
     * 查询计划客户实体对象个数
     */
    public int getPlanTargetCountByMap(Map<String,Object> map);
    /**
     * 根据ids删除目标
     */
    public void delCustInPlan(String targetIds);
    /**
     * 查询计划客户实体对象
     */
    public TskPlanDateTarget getPlanTargetByMap(Map<String,Object> map);
    /**
     * 工作台 当天联系计划 客户数
     */
    public int getMainPlanCount(Map<String,Object> map);
    /**
     * 查询计划执行情况客户列表明细
     * @author liyb
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<TaskPlanCustomerDetailBean> getTaskPlanCustomerPage(Map<String, Object> parameters, Page page);
    /**
     * 批量插入计划客户
     * @param bean
     */
    public void addBatchTskPlanTarget(List<TskPlanTarget> list);
    /**
     * 批量更新计划客户
     * @param bean
     */
    public void updateBatchTskPlanTarget(List<TskPlanTarget> list);

}
