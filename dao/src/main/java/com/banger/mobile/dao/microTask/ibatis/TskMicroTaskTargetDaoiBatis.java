/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :任务目标客户-Dao-接口实现
 * Author     :QianJie
 * Create Date:Mar 2, 2013
 */
package com.banger.mobile.dao.microTask.ibatis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.microTask.TskMicroTaskTargetDao;
import com.banger.mobile.domain.model.microTask.TskMicroTaskTarget;
import com.banger.mobile.ibatis.GenericDaoiBatis;
import com.banger.mobile.util.StringUtil;

/**
 * @author QianJie
 * @version $Id: TskMicroTaskTargetDaoiBatis.java,v 0.1 Mar 2, 2013 11:22:20 AM
 *          QianJie Exp $
 */
public class TskMicroTaskTargetDaoiBatis extends GenericDaoiBatis implements TskMicroTaskTargetDao {

    public TskMicroTaskTargetDaoiBatis() {
        super(TskMicroTaskTarget.class);
    }

    public TskMicroTaskTargetDaoiBatis(Class persistentClass) {
        super(TskMicroTaskTarget.class);
    }

    public void addTaskTarget(TskMicroTaskTarget tskMicroTaskTarget) {
        this.getSqlMapClientTemplate().insert("addTskTaskTarget", tskMicroTaskTarget);
    }

    public void addTaskTargetBatch(List<TskMicroTaskTarget> list) {
        this.exectuteBatchInsert("addTskTaskTarget", list);
    }

    @SuppressWarnings("unchecked")
    public PageUtil<TskMicroTaskTarget> getTargetListByPage(Map<String, Object> parameterMap,
                                                            Page page) {
        Map<String,Object> fConds = new HashMap<String, Object>();
        for(Map.Entry<String, Object> entry : parameterMap.entrySet())
        {
            if(entry.getValue() instanceof String){
                fConds.put(entry.getKey(), StringUtil.ReplaceSQLEscapeChar(StringUtil.ReplaceSQLChar(entry.getValue().toString())));
            }else{
                fConds.put(entry.getKey(), entry.getValue());
            }
        }
        List<TskMicroTaskTarget> list = this.findQueryPage("getTargetListByPage",
                "getTargetListByPageCount", fConds, page);
        return new PageUtil<TskMicroTaskTarget>(list, page);

    }
    public PageUtil<TskMicroTaskTarget> getTargetListForPad(Map<String, Object> parameterMap,
                                                            Page page) {
        List<TskMicroTaskTarget> list = this.findQueryPage("getTargetListForPad",
                "getTargetListForPadCount", parameterMap, page);
        return new PageUtil<TskMicroTaskTarget>(list, page);

    }

    @SuppressWarnings("unchecked")
    public PageUtil<TskMicroTaskTarget> getTargetList(Map<String, Object> parameterMap, Page page) {
        List<TskMicroTaskTarget> list = this.findQueryPage("getTargetList", "getTargetListCount",
                parameterMap, page);
        return new PageUtil<TskMicroTaskTarget>(list, page);

    }

    /**
     * 根据电话号码查询
     *
     * @param phoneNoStr
     * @return
     * @see com.banger.mobile.dao.microTask.TskMicroTaskTargetDao#queryByPhoneNo(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public List<TskMicroTaskTarget> queryByPhoneNo(TskMicroTaskTarget tskTarget) {
        List<TskMicroTaskTarget> list = new ArrayList<TskMicroTaskTarget>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("phoneNumber", tskTarget.getPhoneNumber());
        list = this.getSqlMapClientTemplate().queryForList("getTskMicroTaskTargetByPhone",
                tskTarget.getPhoneNumber());
        return list;
    }

    /**
     * 更新数据库
     *
     * @param updateBeanList
     * @see com.banger.mobile.dao.microTask.TskMicroTaskTargetDao#updateTaskTargetBatch(java.util.List)
     */
    public void updateTaskTargetBatch(List<TskMicroTaskTarget> updateBeanList) {
        this.executeBatchUpdate("editTskTaskTargets", updateBeanList);
    }

    /**
     * 查询营销任务完成数量
     *
     * @param parameterMap
     * @return
     */
    public Integer getTargetListByPageCount(Map<String, Object> parameterMap) {
        return (Integer) this.getSqlMapClientTemplate().queryForObject("getTargetListByPageCount",
                parameterMap);
    }

    // 电话营销任务自动完成
    public void autoFinish(Map<String, Object> params) {
        this.getSqlMapClientTemplate().update("autoFinish", params);
    }

    // 实地营销任务自动完成
    public void autoFinishFoot(Map<String, Object> params) {
        this.getSqlMapClientTemplate().update("autoFinishFoot", params);
    }

    public TskMicroTaskTarget getMicroTaskTargetById(Integer taskTargetId) {
        return (TskMicroTaskTarget) this.getSqlMapClientTemplate().queryForObject(
                "getMicroTaskTargetById", taskTargetId);
    }

    public void editTskTaskTarget(TskMicroTaskTarget t) {
        this.getSqlMapClientTemplate().update("editTskTaskTarget", t);
    }

    public void editTskTaskTargetCustomer(TskMicroTaskTarget t) {
        this.getSqlMapClientTemplate().update("editTskTaskTargetCustomer", t);
    }

    /**
     * 修改执行任务时修改任务目标信息
     */
    public void editTskTaskTargetInfo(TskMicroTaskTarget t) {
        this.getSqlMapClientTemplate().update("editTskTaskTargetInfo", t);
    }

    /**
     * 通过店换号码和任务ID查询
     *
     * @param taskTarget
     * @return
     * @see com.banger.mobile.dao.microTask.TskMicroTaskTargetDao#queryByPhoneNoAndTaskId(com.banger.mobile.domain.model.microTask.TskMicroTaskTarget)
     */
    public Integer getMicroTaskTargetEqualsTarget(TskMicroTaskTarget taskTarget) {
        return (Integer) this.getSqlMapClientTemplate().queryForObject(
                "getMicroTaskTargetEqualsTarget", taskTarget);

    }

    /**
     * 根据任务ID查询
     *
     * @return
     * @see com.banger.mobile.dao.microTask.TskMicroTaskTargetDao#getTskMicroTaskTargetByTaskId()
     */
    @SuppressWarnings("unchecked")
    public List<TskMicroTaskTarget> getTskMicroTaskTargetByNameAndNum(Map<String, String> map) {
        List<TskMicroTaskTarget> list = new ArrayList<TskMicroTaskTarget>();
        list = this.getSqlMapClientTemplate().queryForList("getMicroTaskTargetByNameAndNum", map);
        return list != null ? list : null;
    }

    // 根据查询条件查询所有任务目标
    public List<TskMicroTaskTarget> getMicroTaskTargetByParameterMap(Map<String, Object> paras) {
        return this.getSqlMapClientTemplate().queryForList("getMicroTaskTargetByParameterMap",
                paras);
    }

    // // 电话营销任务自动完成 在行客户
    public void autoFinishInSystem(Map<String, Object> paras) {
        this.getSqlMapClientTemplate().update("autoFinishInSystem", paras);
    }

    // 电话营销任务自动完成 非在行客户
    public void autoFinishOutSystem(Map<String, Object> paras) {
        this.getSqlMapClientTemplate().update("autoFinishOutSystem", paras);
    }

    /**
     * 通过电话号码,姓名,任务ID,归属客户ID查找
     *
     * @param map
     * @return
     * @see com.banger.mobile.dao.microTask.TskMicroTaskTargetDao#getTskMicTargetsByPhones(java.util.Map)
     */
    @SuppressWarnings("unchecked")
    public List<TskMicroTaskTarget> getTskMicTargetsByPhonesAndNames(Map<String, Object> map) {
        return this.getSqlMapClientTemplate().queryForList("queryByPhonesAndNames", map);
    }

    /**
     * 执行营销任务修改任务目标信息
     *
     * @param map
     */
    public void editTargetForExeMarkTask(Map<String, Object> params) {
        this.getSqlMapClientTemplate().update("exeMarkTaskAssignCusId", params);
    }

    /**
     * 通过电话号码，任务ID查询任务目标
     */
    public List<TskMicroTaskTarget> getListByPhoneAndTaskId(Map<String, Object> params) {
        return this.getSqlMapClientTemplate().queryForList("getListByPhoneAndTaskId", params);
    }

    /**
     * @param paras
     * @see com.banger.mobile.dao.microTask.TskMicroTaskTargetDao#autoFinishOutSystemOverride(java.util.Map)
     */
    public void autoFinishOutSystemOverride(Map<String, Object> paras) {
        this.getSqlMapClientTemplate().update("autoFinishOutSystemOverride", paras);
    }

    /**
     * 查询客户在此用户下的所有未完成任务安排
     * @param map
     * @return
     */
    @Override
    public Integer getCustomerTaskCount(Map<String, Object> map) {
        return (Integer) this.getSqlMapClientTemplate().queryForObject("getCustomerTaskCount",map);
    }
}
