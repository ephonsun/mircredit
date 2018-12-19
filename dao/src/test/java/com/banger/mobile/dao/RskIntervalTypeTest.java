/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :投资偏好dao测试类
 * Author     :yujh
 * Create Date:Jul 17, 2012
 */
package com.banger.mobile.dao;

import com.banger.mobile.dao.rskIntervalType.RskIntervalTypeDao;
import com.banger.mobile.domain.model.rskIntervalType.RskIntervalType;

/**
 * @author yujh
 * @version $Id: RskIntervalTypeTest.java,v 0.1 Jul 17, 2012 9:20:35 AM Administrator Exp $
 */
public class RskIntervalTypeTest extends BaseDaoTestCase{
    private RskIntervalTypeDao rskIntervalTypeDao;

    public void setRskIntervalTypeDao(RskIntervalTypeDao rskIntervalTypeDao) {
        this.rskIntervalTypeDao = rskIntervalTypeDao;
    }

//    public void testDaoNotNull(){
//        assertNotNull(rskIntervalTypeDao);
//    }
//    public void testAdd(){
//        RskIntervalType type= new RskIntervalType();
//        type.setTypeName("ceshi");
//        type.setDescription("测试的");
//        type.setIsDel(0);
//        type.setSortNo(1);
//        this.rskIntervalTypeDao.addRskIntervalType(type);
//    }
//    public void testUpdate(){
//        RskIntervalType type= new RskIntervalType();
//        type.setTypeName("ceshiaaaaa");
//        type.setDescription("测试的aaaaa");
//        type.setIsDel(2);
//        type.setSortNo(1);
//        type.setTypeId(1);
//        this.rskIntervalTypeDao.updateRskIntervalType(type);
//    }
//      public void testDel(){
//          this.rskIntervalTypeDao.deleteRskIntervalType(2);
//      }
      public void testQuery(){
          this.rskIntervalTypeDao.getAllRskIntervalType();
      }
}
