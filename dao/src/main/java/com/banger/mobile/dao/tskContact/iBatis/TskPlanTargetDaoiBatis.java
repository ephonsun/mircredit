/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :cheny
 * Create Date:2012-12-18
 */
package com.banger.mobile.dao.tskContact.iBatis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.tskContact.TskPlanTargetDao;
import com.banger.mobile.domain.model.tskContact.TaskPlanCustomerDetailBean;
import com.banger.mobile.domain.model.tskContact.TskPlanDateTarget;
import com.banger.mobile.domain.model.tskContact.TskPlanTarget;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author cheny
 * @version $Id: TskPlanTargetDaoiBatis.java,v 0.1 2012-12-18 下午2:30:57 cheny Exp $
 */
public class TskPlanTargetDaoiBatis extends GenericDaoiBatis implements TskPlanTargetDao{

    public TskPlanTargetDaoiBatis() {
        super(TskPlanTarget.class);
    }
    /**
     *
     * @param persistentClass
     */
    public TskPlanTargetDaoiBatis(Class persistentClass) {
        super(TskPlanTarget.class);
    }

    /**
     * 新增联系计划目标客户
     */
    public Integer insertTskPlanTarget(TskPlanTarget target){
        return (Integer) this.getSqlMapClientTemplate().insert("insertTskPlanTarget",target);
    }
    /**
     *  编辑 联系计划目标客户
     */
    public void updateTskPlanTarget(TskPlanTarget target){
        this.getSqlMapClientTemplate().update("updateTskPlanTarget",target);
    }
    /**
     * 查询计划客户实体对象个数
     */
    public int getPlanTargetCountByMap(Map<String,Object> map){
        return (Integer) this.getSqlMapClientTemplate().queryForObject("getPlanTargetCountByMap",map);
    }
    /**
     * 根据ids删除目标
     */
    public void delCustInPlan(String targetIds){
        this.getSqlMapClientTemplate().delete("delCustInPlan",targetIds);
    }
    /**
     * 查询计划客户实体对象
     */
    public TskPlanDateTarget getPlanTargetByMap(Map<String,Object> map){
        return (TskPlanDateTarget) this.getSqlMapClientTemplate().queryForObject("getPlanTargetByMap",map);
    }
    /**
     * 工作台 当天联系计划 客户数
     */
    public int getMainPlanCount(Map<String,Object> map){
        return (Integer) this.getSqlMapClientTemplate().queryForObject("getPlanCustBeanCount",map);
    }
    /**
     * 查询计划执行情况客户列表明细
     * @author liyb
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<TaskPlanCustomerDetailBean> getTaskPlanCustomerPage(Map<String, Object> parameters,Page page) {
        ArrayList<TaskPlanCustomerDetailBean> list = (ArrayList<TaskPlanCustomerDetailBean>) this.findQueryPage(
                "GetTaskPlanCustomerPage", "GetTaskPlanCustomerCount", parameters, page);
        if (list == null) {
            list = new ArrayList<TaskPlanCustomerDetailBean>();
        }
        return new PageUtil<TaskPlanCustomerDetailBean>(list, page);
    }
    /**
     * 批量插入计划客户
     * @param bean
     */
    public void addBatchTskPlanTarget(List<TskPlanTarget> list){
        if(list!=null){
            this.exectuteBatchInsert("insertTskPlanTarget", list);
        }
    }
    /**
     * 批量更新计划客户
     * @param bean
     */
    public void updateBatchTskPlanTarget(List<TskPlanTarget> list){
        if(list!=null){
            this.executeBatchUpdate("updateTskPlanTarget", list);
        }
    }
}
