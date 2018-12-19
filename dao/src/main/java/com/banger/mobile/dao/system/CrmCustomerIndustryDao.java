/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :客户所处行业dao接口
 * Author     :QianJie
 * Create Date:May 25, 2012
 */
package com.banger.mobile.dao.system;

import java.util.List;
import java.util.Map;

import com.banger.mobile.domain.model.system.CrmCustomerIndustry;

/**
 * @author QianJie
 * @version $Id: CrmCustomerIndustryDao.java,v 0.1 May 25, 2012 10:28:32 AM QianJie Exp $
 */
public interface CrmCustomerIndustryDao {

    /**
     * 添加一种行业类型
     * @param crmCustomerIndustry
     */
    public void AddCrmCustomerIndustry(CrmCustomerIndustry crmCustomerIndustry);
    
    /**
     * 修改行业类型
     * @param crmCustomerIndustry
     */
    public void updateCrmCustomerIndustry(CrmCustomerIndustry crmCustomerIndustry);
    
    
    
    /**
     * 根据行业名称获取行业类型
     * @param id
     * @return
     */
    public CrmCustomerIndustry getCrmCustomerIndustryByName(String name);
    
    /**
     * 删除一种行业类型
     * @param id
     */
    public void deleteCrmCustomerIndustry(int id);
    
    /**
     * 根据Id获取行业类型
     * @return
     */
    public CrmCustomerIndustry getCrmCustomerIndustryById(int id);
    
    /**
     * 获取所有未删除的行业类型
     * @return
     */
    public List<CrmCustomerIndustry> getAllCrmCustomerIndustry();
    
    /**
     * 根据行业类型名称获取拥有相同行业类型名称的数据
     * @param crmCustomerIndustry
     * @return
     */
    public List<CrmCustomerIndustry> getSameCrmCustomerIndustryByName(CrmCustomerIndustry crmCustomerIndustry);
    
    /**
     * 获取现有行业类型数据按SORTNO排序最大的
     * @return
     */
    public CrmCustomerIndustry getMaxSortNoCrmCustomerIndustry();
    
    /**
     * 获取现有行业类型数据按SORTNO排序最小的
     * @return
     */
    public CrmCustomerIndustry getMinSortNoCrmCustomerIndustry();
    
    /**
     * 获取要移动的行业类型对象
     * @return
     */
    public CrmCustomerIndustry getNeedMoveCrmCustomerIndustry(Map<String, Object> parameters);
}
