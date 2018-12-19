/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :客户导入业务接口
 * Author     :yangy
 * Create Date:2012-6-21
 */
package com.banger.mobile.facade.customer;

import java.util.List;
import java.util.Map;

import com.banger.mobile.domain.model.crmModuleExport.CrmModuleExport;
import com.banger.mobile.domain.model.customer.CounterOutPintMessage;
import com.banger.mobile.domain.model.customer.CrmCustomer;
import com.banger.mobile.domain.model.customer.CrmCustomerExtBean;
import com.banger.mobile.domain.model.customer.CrmCustomerTransfer;

/**
 * @author yangyang
 * @version $Id: CustomerImportService.java,v 0.1 2012-6-21 下午4:57:42 yangyong Exp $
 */
public interface CustomerImportService {

    /**
     * 客户导入
     * @param filePath
     * @param map
     * @return
     */
    public CounterOutPintMessage execlToDataBase(Map<String, String> map);
    
    /**
     * 保存导入信息
     * @param crmModuleExport
     */
    public void insertCrmModule(CrmModuleExport crmModuleExport) ;
    
    /**
     * 去除重复客户
     * @param cct
     */
    public void repeatCustomer(CrmCustomerTransfer cct);
    
    /**
     * 新增客户集合
     * @param crmList
     * @return
     */
    public boolean addCrmCustomerBatch(List<CrmCustomer> crmList);
    
    /**
     * 更新客户集合
     * @param crmList
     * @return
     */
    public boolean updateCrmCustomerBatch(List<CrmCustomer> crmList);
    
    /**
     * 新增客户自定义字段
     * @param crmList
     * @param extList
     */
    public void addCrmCustomerExtFields(List<CrmCustomer> crmList, 
    		List<CrmCustomerExtBean> extList);
    
    /**
     * 更新客户自定义字段
     * @param crmList
     * @param extList
     */
    public void updateCrmCustomerExtFieldBatch(List<CrmCustomer> crmList,
            List<CrmCustomerExtBean> extList);
   
    //初始化参数
    public void initParameter(CrmCustomerTransfer cct);
   
}
