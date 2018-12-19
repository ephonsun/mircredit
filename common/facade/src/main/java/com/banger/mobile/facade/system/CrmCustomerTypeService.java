/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :客户类型...
 * Author     :QianJie
 * Create Date:May 21, 2012
 */
package com.banger.mobile.facade.system;

import java.util.List;

import com.banger.mobile.domain.model.system.CrmCustomerType;

/**
 * @author QianJie
 * @version $Id: CrmCustomerTypeService.java,v 0.1 May 21, 2012 2:34:36 PM QianJie Exp $
 */
public interface CrmCustomerTypeService {

    /**
     * 获取所有未删除的客户类型
     * @return
     */
    public List<CrmCustomerType> getAllCrmCustomerType();
    
    /**
     * 根据Id删除客户类型
     * @param id
     */
    public void deleteCrmCustomerType(int id);
    
    /**
     * 添加客户类型
     * @param crmCustomerType
     */
    public void addCrmCustomerType(CrmCustomerType crmCustomerType);
    
    /**
     * 根据客户类型名称获取拥有相同客户类型名称的数据
     * @param crmCustomerType
     * @return
     */
    public List<CrmCustomerType> getSameCrmCustomerTypeByName(CrmCustomerType crmCustomerType);
    
    /**
     * 根据Id获取客户类型
     * @return
     */
    public CrmCustomerType getCrmCustomerTypeById(int id);
    
    /**
     * 修改客户类型
     * @param crmCustomerType
     */
    public void updateCrmCustomerType(CrmCustomerType crmCustomerType);
    
    /**
     * 上移或下移客户类型
     * @param id
     * @param moveType
     */
    public String moveTypeItems(int id,String moveType);
}
