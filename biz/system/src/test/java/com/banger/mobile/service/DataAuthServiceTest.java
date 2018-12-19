/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :cheny
 * Create Date:2012-5-29
 */
package com.banger.mobile.service;

import java.util.List;

import com.banger.mobile.domain.model.dataAuth.SysDataAuth;
import com.banger.mobile.facade.BaseServiceTestCase;
import com.banger.mobile.facade.dataAuth.DataAuthService;

/**
 * @author cheny
 * @version $Id: DataAuthServiceTest.java,v 0.1 2012-5-29 下午1:01:40 cheny Exp $
 */
public class DataAuthServiceTest extends BaseServiceTestCase{

    private DataAuthService dataAuthService;

    public void setDataAuthService(DataAuthService dataAuthService) {
        this.dataAuthService = dataAuthService;
    }
    
    /**
     * 测试deptService是否为空
     * @throws Exception
     */
    public void testServiceNotNull() throws Exception{
        assertNotNull(dataAuthService);
    }
    /**
     * 测试新增数据权限
     */
    public void testSaveDataAuth(){
        SysDataAuth dataAuth = new SysDataAuth();
        dataAuth.setRoleId(3);
        dataAuth.setDataAuthCode("SELF");
        dataAuth.setCreateUser(1);
//        dataAuthService.saveDataAuth(dataAuth);
//        assertNotNull(dataAuth);
        setComplete();
        endTransaction();
        log.info("dataId===="+dataAuth.getDataId());
        
    }
    /**
     * 测试删除数据权限
     */
    public void testdeleteDataAuth(){
        SysDataAuth dataAuth = new SysDataAuth();
        dataAuth.setDataId(444);
//        dataAuthService.deleteDataAuth(dataAuth);
        setComplete();
        endTransaction();
    }
    /**
     * 根据roleId查询数据访问权限
     * @param roleId
     * @return
     */
    public void testgetDataAuthByRoleId(){
        List<SysDataAuth> datas = dataAuthService.getDataAuthByRoleId(1);
        assertEquals(2, datas.size());
    }
    
}
