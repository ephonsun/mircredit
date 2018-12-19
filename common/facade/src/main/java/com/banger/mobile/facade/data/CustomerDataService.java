/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :资料业务接口
 * Author     :yuanme
 * Version    :1.0
 * Create Date:May 3, 2012
 */
package com.banger.mobile.facade.data;

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
 * @version $Id: CustomerDataService.java,v 0.1 2012-5-24 下午04:42:52 yuanme Exp $
 */
public interface CustomerDataService {
    /**
     * 新增
     * @param data	客户资料
     * @return
     */
    public void saveCustomerData(CustomerData data);

    /**
     * 批量新增客户资料
     * @param data
     */
    public void saveCustomerDataBatch(List<CustomerData> datas);
    
    /**
     * 修改
     * @param data  客户资料
     * @return
     */
    public void updateCustomerData(CustomerData data);

    /**
     * 取得事件对象列表
     * @return
     */
    public List<Event> getEventTypeList();

    /**
     * 查询资料列表
     * @param parameterMap
     * @param page
     * @return
     */
    public List<CustomerData> getCustomerDataListPage(Map<String, Object> parameterMap, Page page);

    /**
     * 根据条件查询资料
     * @param parameterMap
     * @return
     */
    public List<CustomerData> getCustomerDataByParameterMap(Map<String, Object> parameterMap);
    
    /**
     * 增加
     * @param cd
     */
    public CustomerData addNewCustomerData(CustomerData cd);

    /**
     * 根据ID查找事件名称
     * @param valueOf
     * @return
     */
    public Event getEventTypeListById(Integer valueOf);

    //取得所有单个贷款所有资料
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
    public PageUtil<CustomerData> getAllCustomerDataPage(Map<String, Object> parameterMap, Page page,CustomerData customerData);

    /**
     * 客户管理资料列表
     * @param parameterMap
     * @return 
     */
    public List<CustomerData> QueryDataByCusId(Map<String, Object> parameterMap);

    List<HashMap<String,Object>> getCustomerDataCount(Map<String, Object> paramMap);

    void updateDatCustomerData(Map<String, Object> paramMap);

    void delCustomerData(Map<String, Object> paramMap);

    List<Integer> getCustomerDataIdList(Map<String, Object> paramMap);

    Integer getCustomerDataId(Map<String, Object> paramMap);    
    /**
     * 根据fileId获取文件对象并复制
     */
    public String readFile(String fileId,String systemPath);

    List<LoanData> getAllLoanDataByIdOptimize(Map<String, Object> paramMap);

    public void relationRecord(Integer recordInfoId, Integer customerId);

    List<LoanExportData> getFileIdListByLoan(Map<String, Object> paramMap);

    Integer getFileIdCountByLoan(Map<String, Object> paramMap);

    List<String> getFileByDatType(Map<String, Object> paramMap);
}
