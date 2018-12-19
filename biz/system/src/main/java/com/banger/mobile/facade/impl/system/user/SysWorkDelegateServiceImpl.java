/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :工作托管...
 * Author     :yangy
 * Create Date:2012-8-18
 */
package com.banger.mobile.facade.impl.system.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.user.ObjectDao;
import com.banger.mobile.domain.model.user.SysWorkDelegate;
import com.banger.mobile.domain.model.user.SysWorkTransfer;
import com.banger.mobile.facade.user.SysWorkDelegateService;

/**
 * @author yangyang
 * @version $Id: SysWorkDelegateServiceImpl.java,v 0.1 2012-8-18 上午11:22:58 yangyong Exp $
 */
public class SysWorkDelegateServiceImpl implements SysWorkDelegateService {

    private ObjectDao sysWorkDelegateDao;
    
   

    public void setSysWorkDelegateDao(ObjectDao sysWorkDelegateDao) {
        this.sysWorkDelegateDao = sysWorkDelegateDao;
    }

    /**
     * 添加一条信息
     * @param sysWorkDelegate
     * @see com.banger.mobile.facade.user.SysWorkDelegateService#addSysWorkDelegate(com.banger.mobile.domain.model.user.SysWorkDelegate)
     */
    public void addSysWorkDelegate(SysWorkDelegate sysWorkDelegate) {
        sysWorkDelegateDao.addObject(sysWorkDelegate);
    }

    /**
     * 删除信息
     * @param id
     * @see com.banger.mobile.facade.user.SysWorkDelegateService#deleteSysWorkDelegate(int)
     */
    public void deleteSysWorkDelegate(int id) {
        sysWorkDelegateDao.deleteObject(id);
    }

    /**
     * 修改信息
     * @param sysWorkDelegate
     * @see com.banger.mobile.facade.user.SysWorkDelegateService#updateSysWorkDelegate(com.banger.mobile.domain.model.user.SysWorkDelegate)
     */
    public void updateSysWorkDelegate(SysWorkDelegate sysWorkDelegate) {
        sysWorkDelegateDao.updateObject(sysWorkDelegate);
    }

    /**
     * 根据id得到信息
     * @param id
     * @return
     * @see com.banger.mobile.facade.user.SysWorkDelegateService#getSysWorkDelegateById(int)
     */
    public SysWorkDelegate getSysWorkDelegateById(int id) {
        return (SysWorkDelegate)sysWorkDelegateDao.getObjectById(id);
    }

    /**
     * 分页查询
     * @param parameters
     * @param page
     * @return
     * @see com.banger.mobile.facade.user.SysWorkDelegateService#getSysWorkDelegatePage(java.util.Map, com.banger.mobile.Page)
     */
    public PageUtil<SysWorkDelegate> getSysWorkDelegatePage(Map<String, Object> parameters,
                                                            Page page) {
        return null;
    }

    /**
     * 查询所有模板集合
     * @param parameters
     * @return
     * @see com.banger.mobile.facade.user.SysWorkDelegateService#getSysWorkDelegateList(java.util.Map)
     */
    public List<SysWorkDelegate> getSysWorkDelegateList(Map<String, Object> parameters) {
        List<SysWorkDelegate> suo=new ArrayList<SysWorkDelegate>();
        List<Object> obj=sysWorkDelegateDao.getObjectList(parameters);
        for (Object object : obj) {
            suo.add((SysWorkDelegate)object);
        }
        return suo;
    }

}
