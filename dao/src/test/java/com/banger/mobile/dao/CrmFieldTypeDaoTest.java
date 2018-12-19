/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :QianJie
 * Create Date:May 28, 2012
 */
package com.banger.mobile.dao;

import java.util.List;

import com.banger.mobile.dao.fieldType.CrmFieldTypeDao;
import com.banger.mobile.domain.model.fieldType.CrmFieldType;

/**
 * @author QianJie
 * @version $Id: CrmFieldTypeDaoTest.java,v 0.1 May 28, 2012 11:49:08 AM QianJie Exp $
 */
public class CrmFieldTypeDaoTest extends BaseDaoTestCase{

    private CrmFieldTypeDao crmFieldTypeDao;

    public void setCrmFieldTypeDao(CrmFieldTypeDao crmFieldTypeDao) {
        this.crmFieldTypeDao = crmFieldTypeDao;
    }
    /**
     * 测试dao是否为空
     * @throws Exception
     */
    public void testDaoNotNull() throws Exception {
        assertNotNull(crmFieldTypeDao);
    }

    /**
     * 测试获取所有模版自定义字段类型
     * @throws Exception
     */
    public void testGetAllCrmCustomerType() throws Exception {
        List<CrmFieldType> list=crmFieldTypeDao.getAllCrmFieldType();
        log.info("list size====:" + list.size());
    }
}
