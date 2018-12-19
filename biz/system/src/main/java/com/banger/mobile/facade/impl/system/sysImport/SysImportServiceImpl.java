/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :系统导入...
 * Author     :yangy
 * Create Date:2012-8-27
 */
package com.banger.mobile.facade.impl.system.sysImport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.user.ObjectDao;
import com.banger.mobile.domain.model.sysImport.SysImport;
import com.banger.mobile.domain.model.user.SysDeptAuth;
import com.banger.mobile.facade.sysImport.SysImportService;

/**
 * @author yangyang
 * @version $Id: SysImportServiceImpl.java,v 0.1 2012-8-27 下午1:13:02 yangyong Exp $
 */
public class SysImportServiceImpl implements SysImportService {

    private ObjectDao sysImportDao;
    
  
    public void setSysImportDao(ObjectDao sysImportDao) {
        this.sysImportDao = sysImportDao;
    }

    /**
     * @param sysImport
     * @see com.banger.mobile.facade.sysImport.SysImportService#addSysImport(com.banger.mobile.domain.model.sysImport.SysImport)
     */
    public void addSysImport(SysImport sysImport) {
        sysImportDao.addObject(sysImport);
    }

    /**
     * @param id
     * @see com.banger.mobile.facade.sysImport.SysImportService#deleteSysImport(int)
     */
    public void deleteSysImport(int id) {
        sysImportDao.deleteObject(id);
    }

    /**
     * @param sysImport
     * @see com.banger.mobile.facade.sysImport.SysImportService#updateSysImport(com.banger.mobile.domain.model.sysImport.SysImport)
     */
    public void updateSysImport(SysImport sysImport) {
        sysImportDao.updateObject(sysImport);
    }

    /**
     * @param id
     * @return
     * @see com.banger.mobile.facade.sysImport.SysImportService#getSysImportById(int)
     */
    public SysImport getSysImportById(int id) {
        return (SysImport)sysImportDao.getObjectById(id);
    }

    /**
     * @param parameters
     * @param page
     * @return
     * @see com.banger.mobile.facade.sysImport.SysImportService#getSysImportPage(java.util.Map, com.banger.mobile.Page)
     */
    public PageUtil<SysImport> getSysImportPage(Map<String, Object> parameters, Page page) {
        return null;
    }

    /**
     * @param parameters
     * @return
     * @see com.banger.mobile.facade.sysImport.SysImportService#getSysImportList(java.util.Map)
     */
    public List<SysImport> getSysImportList(Map<String, Object> parameters) {
        List<SysImport> suo=new ArrayList<SysImport>();
        List<Object> obj=sysImportDao.getObjectList(parameters);
        for (Object object : obj) {
            suo.add((SysImport)object);
        }
        return suo;
    }

}
