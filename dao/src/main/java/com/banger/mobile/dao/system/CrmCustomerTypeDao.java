/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :客户类型dao接口
 * Author     :QianJie
 * Create Date:May 21, 2012
 */
package com.banger.mobile.dao.system;

import java.util.List;
import java.util.Map;

import com.banger.mobile.domain.model.system.CrmCustomerType;

/**
 * @author QianJie
 * @version $Id: CrmCustomerTypeDao.java,v 0.1 May 21, 2012 10:33:17 AM QianJie Exp $
 */
public interface CrmCustomerTypeDao {

    /**
     * 添加一种客户类型
     * @param crmCustomerType
     */
    public void AddCrmCustomerType(CrmCustomerType crmCustomerType);
    
    /**
     * 修改客户类型
     * @param crmCustomerType
     */
    public void updateCrmCustomerType(CrmCustomerType crmCustomerType);
    
    /**
     * 删除一种客户类型
     * @param id
     */
    public void deleteCrmCustomerType(int id);
    
    /**
     * 根据Id获取客户类型
     * @return
     */
    public CrmCustomerType getCrmCustomerTypeById(int id);
    
    /**
     * 获取所有未删除的客户类型
     * @return
     */
    public List<CrmCustomerType> getAllCrmCustomerType();
    
    /**
     * 根据客户类型名称获取拥有相同客户类型名称的数据
     * @param crmCustomerType
     * @return
     */
    public List<CrmCustomerType> getSameCrmCustomerTypeByName(CrmCustomerType crmCustomerType);
    
    /**
     * 获取现有客户类型数据按SORTNO排序最大的
     * @return
     */
    public CrmCustomerType getMaxSortNoCrmCustomerType();
    
    /**
     * 获取现有客户类型数据按SORTNO排序最小的
     * @return
     */
    public CrmCustomerType getMinSortNoCrmCustomerType();
    
    /**
     * 获取要移动的客户类型对象
     * @return
     */
    public CrmCustomerType getNeedMoveCrmCustomerType(Map<String, Object> parameters);
}
