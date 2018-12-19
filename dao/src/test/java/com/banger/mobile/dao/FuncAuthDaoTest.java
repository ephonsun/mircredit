/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :cheny
 * Create Date:2012-5-31
 */
package com.banger.mobile.dao;

import java.util.List;

import com.banger.mobile.dao.funcAuth.FuncAuthDao;
import com.banger.mobile.domain.model.funcAuth.FuncAuthBean;
import com.banger.mobile.domain.model.funcAuth.SysFuncAuth;
import com.banger.mobile.domain.model.menuAuth.SysMenuAuth;

/**
 * @author cheny
 * @version $Id: FuncAuthDaoTest.java,v 0.1 2012-5-31 下午1:28:33 cheny Exp $
 */
public class FuncAuthDaoTest extends BaseDaoTestCase{
    private FuncAuthDao funcAuthDao;

    public void setFuncAuthDao(FuncAuthDao funcAuthDao) {
        this.funcAuthDao = funcAuthDao;
    }
    
    
    /**
     * 测试dao是否为空
     * @throws Exception
     */
    public void testDaoNotNull() throws Exception {
        assertNotNull(funcAuthDao);
    }

    /**
     * 测试获得功能权限树集合
     */
    public void testgetFuncAuthTree(){
        List<FuncAuthBean> beans = funcAuthDao.getFuncAuthTree();
        assertEquals(41, beans.size());
        
    }

    
    /**
     *  新增角色功能权限 -->
     */
   public void testinsertFuncAuth(){
       SysFuncAuth funcAuth = new SysFuncAuth();
       funcAuth.setRoleId(1);
       funcAuth.setFuncId(1);
       funcAuth.setCreateUser(1);
//       funcAuthDao.insertFuncAuth(funcAuth);
       setComplete();
       endTransaction();
       log.info("funcAuthId===="+ funcAuth.getFuncAuthId());
   }
   /**
    * 删除角色功能权限  -->
    */
   public void testedeleteFuncAuth(){
//       funcAuthDao.deleteFuncAuth(1623);
       setComplete();
       endTransaction();
   }
   /**
    *  新增角色菜单权限 -->
    */
   public void testinsertMenuAuth(){
       SysMenuAuth menuAuth = new SysMenuAuth();
       menuAuth.setRoleId(1);
       menuAuth.setCreateUser(1);
       menuAuth.setMenuId(1);
//       funcAuthDao.insertMenuAuth(menuAuth);
       setComplete();
       endTransaction();
       log.info("menuAuthId========"+menuAuth.getMenuAuthId());
   }
   /**
    * 删除角色菜单权限  -->
    */
   public void testdeleteMenuAuth(){
//       funcAuthDao.deleteMenuAuth(2422);
       setComplete();
       endTransaction();
   }
   
}
