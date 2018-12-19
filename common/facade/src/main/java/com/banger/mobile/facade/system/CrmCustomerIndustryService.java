/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :所处行业类型...
 * Author     :QianJie
 * Create Date:May 25, 2012
 */
package com.banger.mobile.facade.system;

import java.util.List;

import com.banger.mobile.domain.model.system.CrmCustomerIndustry;

/**
 * @author QianJie
 * @version $Id: CrmCustomerIndustryService.java,v 0.1 May 25, 2012 10:59:02 AM QianJie Exp $
 */
public interface CrmCustomerIndustryService {

    /**
     * 获取所有未删除的行业类型
     * @return
     */
    public List<CrmCustomerIndustry> getAllCrmCustomerIndustry();
    
    /**
     * 根据Id删除行业类型
     * @param id
     */
    public void deleteCrmCustomerIndustry(int id);
    
    /**
     * 添加行业类型
     * @param crmCustomerIndustry
     */
    public void addCrmCustomerIndustry(CrmCustomerIndustry crmCustomerIndustry);
    
    /**
     * 根据行业类型名称获取拥有相同行业类型名称的数据
     * @param crmCustomerIndustry
     * @return
     */
    public List<CrmCustomerIndustry> getSameCrmCustomerIndustryByName(CrmCustomerIndustry crmCustomerIndustry);
    
    /**
     * 根据Id获取行业类型
     * @return
     */
    public CrmCustomerIndustry getCrmCustomerIndustryById(int id);
    
    /**
     * 修改行业类型
     * @param crmCustomerIndustry
     */
    public void updateCrmCustomerIndustry(CrmCustomerIndustry crmCustomerIndustry);
    
    /**
     * 上移或下移行业类型
     * @param id
     * @param moveType
     */
    public String moveTypeItems(int id,String moveType);
}
