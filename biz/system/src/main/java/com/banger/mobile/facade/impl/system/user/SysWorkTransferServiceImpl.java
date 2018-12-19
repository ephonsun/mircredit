/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :工作转交...
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
import com.banger.mobile.domain.model.user.SysRoleAuth;
import com.banger.mobile.domain.model.user.SysWorkTransfer;
import com.banger.mobile.facade.user.SysWorkTransferService;

/**
 * @author yangyang
 * @version $Id: SysWorkTransferServiceImpl.java,v 0.1 2012-8-18 上午11:08:25 yangyong Exp $
 */
public class SysWorkTransferServiceImpl implements SysWorkTransferService {

    private ObjectDao sysWorkTransferDao;
    

    public void setSysWorkTransferDao(ObjectDao sysWorkTransferDao) {
        this.sysWorkTransferDao = sysWorkTransferDao;
    }

    /**
     * 删除信息
     * @param id
     * @see com.banger.mobile.facade.user.SysWorkTransferService#deleteSysWorkTransfer(int)
     */
    public void deleteSysWorkTransfer(int id) {
        sysWorkTransferDao.deleteObject(id);
    }

    /**
     * 修改信息
     * @param sysWorkTransfer
     * @see com.banger.mobile.facade.user.SysWorkTransferService#updateSysWorkTransfer(com.banger.mobile.domain.model.user.SysWorkTransfer)
     */
    public void updateSysWorkTransfer(SysWorkTransfer sysWorkTransfer) {
        sysWorkTransferDao.updateObject(sysWorkTransfer);
    }
    

    /**
     * 根据id得到信息
     * @param id
     * @return
     * @see com.banger.mobile.facade.user.SysWorkTransferService#getSysWorkTransferById(int)
     */
    public SysWorkTransfer getSysWorkTransferById(int id) {
        return (SysWorkTransfer)sysWorkTransferDao.getObjectById(id);
    }

    /**
     * 分页查询
     * @param parameters
     * @param page
     * @return
     * @see com.banger.mobile.facade.user.SysWorkTransferService#getSysWorkTransferPage(java.util.Map, com.banger.mobile.Page)
     */
    public PageUtil<SysWorkTransfer> getSysWorkTransferPage(Map<String, Object> parameters,
                                                            Page page) {
        return null;
    }

    /**
     * 查询所有模板集合
     * @param parameters
     * @return
     * @see com.banger.mobile.facade.user.SysWorkTransferService#getSysWorkTransferList(java.util.Map)
     */
    public List<SysWorkTransfer> getSysWorkTransferList(Map<String, Object> parameters) {
        List<SysWorkTransfer> suo=new ArrayList<SysWorkTransfer>();
        List<Object> obj=sysWorkTransferDao.getObjectList(parameters);
        for (Object object : obj) {
            suo.add((SysWorkTransfer)object);
        }
        return suo;
    }

    /**
     * 添加一条信息
     * @param sysWorkTransfer
     * @see com.banger.mobile.facade.user.SysWorkTransferService#addSysWorkTransfer(com.banger.mobile.domain.model.user.SysWorkTransfer)
     */
    public void addSysWorkTransfer(SysWorkTransfer sysWorkTransfer) {
        sysWorkTransferDao.addObject(sysWorkTransfer);
    }

}
