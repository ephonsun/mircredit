/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :客户数据 dao
 * Author     :yuanme
 * Create Date:2012-5-29
 */
package com.banger.mobile.dao.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.data.CustomerData;
import com.banger.mobile.domain.model.data.Event;
import com.banger.mobile.domain.model.data.LoanData;
import com.banger.mobile.domain.model.data.LoanExportData;
import com.banger.mobile.domain.model.pad.PadLoanData;
import com.banger.mobile.domain.model.pad.PadLoanDun;

/**
 * @author yuanme
 * @version $Id: DataAuthDao.java,v 0.1 2012-5-29 上午10:50:22 yuanme Exp $
 */
public interface CustomerDataDao {
    /**
     * 新增客户资料
     * @param data
     */
    public void saveCustomerData(CustomerData data);

    /**
     * 新增客户资料
     * @param data
     */
    public void saveCustomerDataBatch(List<CustomerData> datas);
    
    /**
     * 修改客户资料
     * @param data
     */
    public void updateCustomerData(CustomerData data);

    /**
     * 事件对象列表
     * @return
     */
    public List<Event> getEventTypeList();

    /**
     * 查询资料
     * @param parameterMap
     * @param page
     * @return
     */
    public List<CustomerData> getCustomerDataListPage(Map<String, Object> parameterMap, Page page);

    /**
     * 查找资料
     * @param parameterMap
     * @return
     */
    public List<CustomerData> getCustomerDataByParameterMap(Map<String, Object> parameterMap);
    
    /**
     * 增加
     * @param cd
     */
    public void addNewCustomerData(CustomerData cd);

    /**
     * 根据id查找事件
     * @param id
     * @return
     */
    public Event getEventTypeListById(Integer id);

    /**
     * 取得所有单个贷款所有资料
     * @param loanId
     * @return
     */
    List<LoanData> getAllLoanDataById(Map<String,Object> paramMap);
    Integer getAllLoanDataByIdCount(Map<String,Object> paramMap);
    public List<PadLoanData> getDunDataById(Map<String,Object> paramMap);
    
    
    
	//以下为新资料管理 (huangk)
    /**
     * 资料管理列表
     * @param parameterMap
     * @param page
     * @return 
     */
    public PageUtil<CustomerData> getAllCustomerDataPage(Map<String, Object> parameterMap, Page page);
    
    /**
     * 客户管理资料列表
     * @param parameterMap
     * @return 
     */
    public List<CustomerData> QueryDataByCusId(Map<String, Object> parameterMap) ;

    List<HashMap<String,Object>> getCustomerDataCount(Map<String, Object> paramMap);

    void updateDatCustomerData(Map<String, Object> paramMap);

    void delCustomerData(Map<String, Object> paramMap);

    List<Integer> getCustomerDataIdList(Map<String, Object> paramMap);

    Integer getCustomerDataId(Map<String, Object> paramMap);

    List<LoanData> getAllLoanDataByIdOptimize(Map<String, Object> paramMap);

    void relationRecord(Map<String,Object> paramMap);

    List<LoanExportData> getFileIdListByLoan(Map<String, Object> paramMap);

    Integer getFileIdCountByLoan(Map<String, Object> paramMap);

    List<String> getFileByDatType(Map<String, Object> paramMap);
}
