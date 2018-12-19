/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :QianJie
 * Create Date:May 21, 2012
 */
package com.banger.mobile.service;

import java.util.List;

import com.banger.mobile.domain.model.system.CrmCustomerType;
import com.banger.mobile.facade.BaseServiceTestCase;
import com.banger.mobile.facade.system.CrmCustomerTypeService;

/**
 * @author QianJie
 * @version $Id: CrmCustomerTypeServiceTest.java,v 0.1 May 21, 2012 2:39:16 PM QianJie Exp $
 */
public class CrmCustomerTypeServiceTest extends BaseServiceTestCase{

    private CrmCustomerTypeService crmCustomerTypeService;

    public void setCrmCustomerTypeService(CrmCustomerTypeService crmCustomerTypeService) {
        this.crmCustomerTypeService = crmCustomerTypeService;
    }

    /**
     * 测试获取所有客户类型
     * @throws Exception
     */
    public void testGetAllCrmCustomerType() throws Exception {
        List<CrmCustomerType> list=crmCustomerTypeService.getAllCrmCustomerType();
        log.info("list size====:" + list.size());
    }



}
