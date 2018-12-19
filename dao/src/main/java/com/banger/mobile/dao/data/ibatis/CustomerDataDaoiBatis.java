/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :客户资料 dao实现
 * Author     :yuanme
 * Create Date:2012-5-29
 */
package com.banger.mobile.dao.data.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.data.CustomerDataDao;
import com.banger.mobile.domain.model.data.CustomerData;
import com.banger.mobile.domain.model.data.Event;
import com.banger.mobile.domain.model.data.LoanData;
import com.banger.mobile.domain.model.data.LoanExportData;
import com.banger.mobile.domain.model.pad.PadLoanData;
import com.banger.mobile.domain.model.pad.PadLoanDun;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author yuanme
 * @version $Id: DataAuthDaoiBatis.java,v 0.1 2012-5-29 上午10:51:00 yuanme Exp $
 */
public class CustomerDataDaoiBatis extends GenericDaoiBatis implements CustomerDataDao {

    public CustomerDataDaoiBatis() {
        super(CustomerData.class);
    }

    /**
     * @param persistentClass
     */
    public CustomerDataDaoiBatis(Class persistentClass) {
        super(CustomerData.class);
    }

    /**
     * 新增客户资料
     * @param data
     */
    public void saveCustomerData(CustomerData data) {
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        parameterMap.put("customerId", data.getCustomerId().toString());
        parameterMap.put("loanId", data.getLoanId().toString());
        parameterMap.put("eventId", data.getEventId().toString());
        CustomerData obj = getCustomerData(parameterMap);
        if(obj==null){
            this.getSqlMapClientTemplate().insert("insertCustomerData", data);
        }
    }

    private CustomerData getCustomerData(Map<String, Object> parameterMap){
        List<CustomerData> ldata = this.getSqlMapClientTemplate().queryForList("getCustomerData",
                parameterMap);
        if(ldata.size()==0){
            return null;
        }else
            return ldata.get(0);
    }
    /**
     * 新增客户资料
     * @param data
     */
    public void saveCustomerDataBatch(List<CustomerData> datas) {
        this.exectuteBatchInsert("insertCustomerData", datas);
    }

    /**
     * 修改客户资料
     * @param data
     */
    public void updateCustomerData(CustomerData data) {
        this.getSqlMapClientTemplate().insert("updateCustomerData", data);
    }

    /**
     *
     * @return
     * @see com.banger.mobile.dao.data.CustomerDataDao#getEventTypeList()
     */
    public List<Event> getEventTypeList() {
        return this.getSqlMapClientTemplate().queryForList("getEventTypeList");
    }

    /**
     *
     * @param parameterMap
     * @param page
     * @return
     * @see com.banger.mobile.dao.data.CustomerDataDao#getCustomerDataListPage(java.util.Map, com.banger.mobile.Page)
     */
    public List<CustomerData> getCustomerDataListPage(Map<String, Object> parameterMap, Page page) {
        List<CustomerData> list = (List<CustomerData>) this.findQueryPage(
                "getCustomerDataListPage", "getCustomerDataListPageCount", parameterMap, page);
        return list;
    }

    /**
     *
     * @param parameterMap
     * @return
     * @see com.banger.mobile.dao.data.CustomerDataDao#getCustomerDataByParameterMap(java.util.Map)
     */
    public List<CustomerData> getCustomerDataByParameterMap(Map<String, Object> parameterMap) {
        return this.getSqlMapClientTemplate().queryForList("getCustomerDataByParameterMap",
                parameterMap);
    }

    public void addNewCustomerData(CustomerData cd) {
        this.getSqlMapClientTemplate().insert("addNewCustomerData" , cd);

    }

    /**
     *
     * @param id
     * @return
     * @see com.banger.mobile.dao.data.CustomerDataDao#getEventTypeListById(java.lang.Integer)
     */
    public Event getEventTypeListById(Integer id) {
        return (Event) this.getSqlMapClientTemplate().queryForObject("getEventTypeListById", id);
    }

    /**
     * 取得所有单个贷款所有资料
     * @param paramMap
     * @return
     */
    public List<LoanData> getAllLoanDataById(Map<String,Object> paramMap) {
        return this.getSqlMapClientTemplate().queryForList("getAllLoanDataById", paramMap);
    }

    public Integer getAllLoanDataByIdCount(Map<String,Object> paramMap) {
        return (Integer) this.getSqlMapClientTemplate().queryForObject("getAllLoanDataByIdCount", paramMap);
    }
    //以下为新资料管理 (huangk)
    /**
     * 资料管理列表
     * @param parameterMap
     * @param page
     * @return
     */
    public PageUtil<CustomerData> getAllCustomerDataPage(Map<String, Object> parameterMap, Page page) {
        List<CustomerData> list = (List<CustomerData>) this.findQueryPage(
                "getAllCustomerDataPage", "getAllCustomerDataCount", parameterMap, page);
        return new PageUtil<CustomerData>(list, page);
    }


    public List<PadLoanData> getDunDataById(Map<String,Object> paramMap){
        return this.getSqlMapClientTemplate().queryForList("getDunDataById",paramMap);
    }

    /**
     * 客户管理资料列表
     * @param parameterMap
     * @return
     */
    public List<CustomerData> QueryDataByCusId(Map<String, Object> parameterMap) {
        return this.getSqlMapClientTemplate().queryForList("queryDataByCusId",parameterMap);
    }

    /**
     * 得到贷款客户相应资料的数量及类型
     *
     * @param paramMap
     * @return
     */
    @Override
    public List<HashMap<String,Object>> getCustomerDataCount(Map<String, Object> paramMap){
        return this.getSqlMapClientTemplate().queryForList("getCustomerDataCount",paramMap);
    }

    /**
     * 更新客户资料表
     * @param paramMap
     */
    @Override
    public void updateDatCustomerData(Map<String,Object> paramMap){
        this.getSqlMapClientTemplate().update("updateDatCustomerData",paramMap);
    }

    /**
     * 删除客户资料
     * @param paramMap
     */
    @Override
    public void delCustomerData(Map<String,Object> paramMap){
        this.getSqlMapClientTemplate().delete("delCustomerData",paramMap);
    }

    /**
     * 根据贷款和客户查找客户资料id列表
     * @param paramMap
     * @return
     */
    @Override
    public List<Integer> getCustomerDataIdList(Map<String,Object> paramMap){
        return (List<Integer>)this.getSqlMapClientTemplate().queryForList("getCustomerDataIdList",paramMap);
    }

    @Override
    public Integer getCustomerDataId(Map<String,Object> paramMap){
        return (Integer)this.getSqlMapClientTemplate().queryForObject("getCustomerDataId",paramMap);
    }

    //==========优化后的代码============

    /**
     * 取得所有单个贷款所有资料(优化)
     * @param paramMap
     * @return
     */
    @Override
    public List<LoanData> getAllLoanDataByIdOptimize(Map<String,Object> paramMap) {
        return this.getSqlMapClientTemplate().queryForList("getAllLoanDataByIdOptimize", paramMap);
    }

    public void relationRecord(Map<String, Object> paramMap) {
        this.getSqlMapClientTemplate().update("relationRecord", paramMap);
    }

    /**
     * 获取贷款所有导出资料信息
     *
     * @param paramMap
     * @return
     */
    public List<LoanExportData> getFileIdListByLoan(Map<String,Object> paramMap){
        return this.getSqlMapClientTemplate().queryForList("getFileIdListByLoan",paramMap);
    }

    /**
     * 得到贷款资料总数
     *
     * @param paramMap
     * @return
     */
    public Integer getFileIdCountByLoan(Map<String, Object> paramMap){
        return (Integer)this.getSqlMapClientTemplate().queryForObject("getFileIdCountByLoan",paramMap);
    }

    public List<String> getFileByDatType(Map<String, Object> paramMap){
        return (List<String>)this.getSqlMapClientTemplate().queryForList("getFileByDatType",paramMap);
    }
}
