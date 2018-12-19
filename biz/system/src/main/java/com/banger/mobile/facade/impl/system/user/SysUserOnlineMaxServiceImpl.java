/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :最大在线用户数...
 * Author     :yangy
 * Create Date:2012-9-5
 */
package com.banger.mobile.facade.impl.system.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.user.ObjectDao;
import com.banger.mobile.domain.model.user.SysUserOnline;
import com.banger.mobile.domain.model.user.SysUserOnlineMax;
import com.banger.mobile.facade.user.SysUserOnlineMaxService;

/**
 * @author yangyang
 * @version $Id: SysUserOnlineMaxServiceImpl.java,v 0.1 2012-9-5 下午2:17:09 yangyong Exp $
 */
public class SysUserOnlineMaxServiceImpl implements SysUserOnlineMaxService {

    private ObjectDao sysUserOnlineMaxDao;
    
    
    
    public void setSysUserOnlineMaxDao(ObjectDao sysUserOnlineMaxDao) {
        this.sysUserOnlineMaxDao = sysUserOnlineMaxDao;
    }

    /**
     * 添加一条信息
     * @param sysUserOnlineMax
     * @see com.banger.mobile.facade.user.SysUserOnlineMaxService#addSysUserOnlineMax(com.banger.mobile.domain.model.user.SysUserOnlineMax)
     */
    public void addSysUserOnlineMax(SysUserOnlineMax sysUserOnlineMax) {
        sysUserOnlineMaxDao.addObject(sysUserOnlineMax);
    }

    /**
     * 删除信息
     * @param id
     * @see com.banger.mobile.facade.user.SysUserOnlineMaxService#deleteSysUserOnlineMax(int)
     */
    public void deleteSysUserOnlineMax(int id) {
    }

    /**
     * 修改信息
     * @param sysUserOnlineMax
     * @see com.banger.mobile.facade.user.SysUserOnlineMaxService#updateSysUserOnlineMax(com.banger.mobile.domain.model.user.SysUserOnlineMax)
     */
    public void updateSysUserOnlineMax(SysUserOnlineMax sysUserOnlineMax) {
        sysUserOnlineMaxDao.updateObject(sysUserOnlineMax);
    }

    /**
     * 根据id得到信息
     * @param id
     * @return
     * @see com.banger.mobile.facade.user.SysUserOnlineMaxService#getSysUserOnlineMaxById(int)
     */
    public SysUserOnlineMax getSysUserOnlineMaxById(int id) {
        return (SysUserOnlineMax)sysUserOnlineMaxDao.getObjectById(id);
    }

    /**
     * 分页查询
     * @param parameters
     * @param page
     * @return
     * @see com.banger.mobile.facade.user.SysUserOnlineMaxService#getSysUserOnlineMaxPage(java.util.Map, com.banger.mobile.Page)
     */
    public PageUtil<SysUserOnlineMax> getSysUserOnlineMaxPage(Map<String, Object> parameters,
                                                              Page page) {
    
        return null;
    }

    /**
     * 查询峰值用户数
     * @param parameters
     * @return
     * @see com.banger.mobile.facade.user.SysUserOnlineMaxService#getSysUserOnlineMaxList(java.util.Map)
     */
    public List<SysUserOnlineMax> getSysUserOnlineMaxList(Map<String, Object> parameters) {
        List<SysUserOnlineMax> suo=new ArrayList<SysUserOnlineMax>();
        List<Object> obj=sysUserOnlineMaxDao.getObjectList(parameters);
        for (Object object : obj) {
            suo.add((SysUserOnlineMax)object);
        }
        return suo;
    }

}
