/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :QianJie
 * Create Date:May 21, 2012
 */
package com.banger.mobile.dao;

import java.util.Date;
import java.util.List;

import com.banger.mobile.dao.system.CrmCustomerTypeDao;
import com.banger.mobile.domain.model.system.CrmCustomerType;

/**
 * @author QianJie
 * @version $Id: CrmCustomerTypeTest.java,v 0.1 May 21, 2012 11:45:25 AM QianJie Exp $
 */
public class CrmCustomerTypeTest extends BaseDaoTestCase{
    
    private CrmCustomerTypeDao crmCustomerTypeDao;


    public void setCrmCustomerTypeDao(CrmCustomerTypeDao crmCustomerTypeDao) {
        this.crmCustomerTypeDao = crmCustomerTypeDao;
    }
    
    /**
     * 测试dao是否为空
     * @throws Exception
     */
    public void testDaoNotNull() throws Exception {
        assertNotNull(crmCustomerTypeDao);
    }
    
    /**
     * 测试添加一种客户类型
     * @throws Exception
     */
    public void testaddCrmCustomerType() throws Exception {
        CrmCustomerType crmType = new CrmCustomerType();
        crmType.setCustomerTypeName("白金客户");
        crmType.setIsActived(1);
        crmType.setIsDel(0);
        crmType.setSortNo(1);
        crmType.setUpdateDate(new Date());
        crmType.setCreateDate(new Date());
        crmType.setCreateUser(1);
        crmType.setUpdateUser(1);
        //        for(int i=0;i<99999;i++)
        //        {
        crmCustomerTypeDao.AddCrmCustomerType(crmType);
        assertNotNull(crmType);
        log.info("id====:" + crmType.getCustomerTypeId());
        //        }

    }
    
    /**
     * 测试根据Id得到客户类型
     * @throws Exception
     */
    public void testgetCrmCustomerType() throws Exception {
        CrmCustomerType crmType = new CrmCustomerType();
        crmType = crmCustomerTypeDao.getCrmCustomerTypeById(1);
        assertEquals("白金客户", crmType.getCustomerTypeName());
    }
    
    /**
     * 测试修改客户类型
     * @throws Exception
     */
    public void testupdateCrmCustomerType() throws Exception {
        CrmCustomerType crmType = new CrmCustomerType();
        crmType = crmCustomerTypeDao.getCrmCustomerTypeById(1);
        crmCustomerTypeDao.updateCrmCustomerType(crmType);
        crmType = crmCustomerTypeDao.getCrmCustomerTypeById(1);
        log.info("update date====:" + crmType.getUpdateDate());
    }
    
    /**
     * 测试删除客户类型
     * @throws Exception
     */
    public void testdeleteCrmCustomerType() throws Exception {
        crmCustomerTypeDao.deleteCrmCustomerType(2);
        CrmCustomerType crmType = new CrmCustomerType();
        crmType = crmCustomerTypeDao.getCrmCustomerTypeById(2);
        assertEquals(1, crmType.getIsDel());
    }

    /**
     * 测试获取所有客户类型
     * @throws Exception
     */
    public void testGetAllCrmCustomerType() throws Exception {
        List<CrmCustomerType> list=crmCustomerTypeDao.getAllCrmCustomerType();
        log.info("list size====:" + list.size());
    }

}
