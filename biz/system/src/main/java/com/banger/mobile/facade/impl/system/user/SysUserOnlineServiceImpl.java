/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :在线用户维护...
 * Author     :yangy
 * Create Date:2012-8-15
 */
package com.banger.mobile.facade.impl.system.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.user.ObjectDao;
import com.banger.mobile.dao.user.SysUserOnlineDao;
import com.banger.mobile.domain.model.user.SysUserOnline;
import com.banger.mobile.facade.user.SysUserOnlineService;

/**
 * @author yangyang
 * @version $Id: SysUserOnlineServiceImpl.java,v 0.1 2012-8-15 下午1:19:54 yangyong Exp $
 */
public class SysUserOnlineServiceImpl implements SysUserOnlineService {

    private SysUserOnlineDao sysUserOnlineDao;
    
    
    public void setSysUserOnlineDao(SysUserOnlineDao sysUserOnlineDao) {
        this.sysUserOnlineDao = sysUserOnlineDao;
    }

    /**
     * 添加一条信息
     * @param sysUserOnline
     * @see com.banger.mobile.facade.user.SysUserOnlineService#addSysUserOnline(com.banger.mobile.domain.model.user.SysUserOnline)
     */
    public void addSysUserOnline(SysUserOnline sysUserOnline) {
        sysUserOnlineDao.addObject(sysUserOnline);
    }

    /**
     * 删除信息
     * @param id
     * @see com.banger.mobile.facade.user.SysUserOnlineService#deleteSysUserOnline(int)
     */
    public void deleteSysUserOnline(int id) {
        sysUserOnlineDao.deleteObject(id);
    }

    /**
     * 修改信息
     * @param sysUserOnline
     * @see com.banger.mobile.facade.user.SysUserOnlineService#updateSysUserOnline(com.banger.mobile.domain.model.user.SysUserOnline)
     */
    public void updateSysUserOnline(SysUserOnline sysUserOnline) {
        sysUserOnlineDao.updateObject(sysUserOnline);
    }

    /**
     * 根据id得到信息
     * @param id
     * @return
     * @see com.banger.mobile.facade.user.SysUserOnlineService#getSysUserOnlineById(int)
     */
    public SysUserOnline getSysUserOnlineById(int id) {
        return (SysUserOnline)sysUserOnlineDao.getObjectById(id);
    }

    /**
     * 分页查询
     * @param parameters
     * @param page
     * @return
     * @see com.banger.mobile.facade.user.SysUserOnlineService#getSysUserOnlinePage(java.util.Map, com.banger.mobile.Page)
     */
    public PageUtil<SysUserOnline> getSysUserOnlinePage(Map<String, Object> parameters, Page page) {
        return null;
    }

    /**
     * 查询所有模板集合
     * @param parameters
     * @return
     * @see com.banger.mobile.facade.user.SysUserOnlineService#getSysUserOnlineList(java.util.Map)
     */
    public List<SysUserOnline> getSysUserOnlineList(Map<String, Object> parameters) {
        List<SysUserOnline> suo=new ArrayList<SysUserOnline>();
        List<Object> obj=sysUserOnlineDao.getObjectList(parameters);
        for (Object object : obj) {
            suo.add((SysUserOnline)object);
        }
        return suo;
    }

    /**
     * 获得在线用户id集合
     * @return
     */
    public Integer[] getOnlineUserIds(){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("onlineStatusId", 1);
        List<SysUserOnline> suo=new ArrayList<SysUserOnline>();
        List<Object> obj=sysUserOnlineDao.getObjectList(map);
        for (Object object : obj) {
            suo.add((SysUserOnline)object);
        }
        if(suo.size()>0){
            Integer[] ids = new Integer[suo.size()];
            int i=0;
            for (SysUserOnline user : suo) {
                ids[i++] = user.getUserId();
            }
            return ids;
            
        }
        return null;
    }

    /**
     * 查询所有模板集合
     * @return
     * @see com.banger.mobile.facade.user.SysUserOnlineService#getSysUserOnlineList()
     */
    public List<SysUserOnline> getSysUserOnlineList() {
        List<SysUserOnline> suo=new ArrayList<SysUserOnline>();
        List<Object> obj=sysUserOnlineDao.getObjectList();
        for (Object object : obj) {
            suo.add((SysUserOnline)object);
        }
        return suo;
    }

    /**
     * 系统启动时初始化在线用户状态
     * @see com.banger.mobile.facade.user.SysUserOnlineService#initOnlineUserState()
     */
    public void initOnlineUserState() {
        sysUserOnlineDao.initOnlineUserState();
    }

 

    /**
     * 取出所以在线用户的信息包含admin
     * @return
     * @see com.banger.mobile.facade.user.SysUserOnlineService#getALLSysUserOnline()
     */
    public List<SysUserOnline> getALLSysUserOnline(Map<String, Object> parameters) {
        return sysUserOnlineDao.getALLSysUserOnline(parameters);
    }

    /**
     * 修改用户状态
     * @param parameters
     * @see com.banger.mobile.facade.user.SysUserOnlineService#updateSysUserOnlineStatus(java.util.Map)
     */
    public void updateSysUserOnlineStatus(Map<String, Object> parameters) {
        sysUserOnlineDao.updateSysUserOnlineStatus(parameters);
    }

    /**
     * 根据用户编号取用户在线实体
     * @param userId
     * @return
     * @see com.banger.mobile.facade.user.SysUserOnlineService#getSysUserOnlineByUserId(java.lang.Integer)
     */
    public  List<SysUserOnline>  getSysUserOnlineByUserId(Integer userId) {
        return sysUserOnlineDao.getSysUserOnlineByUserId(userId);
    }

    /**
     * 更新用户状态
     * @param userId
     */
    public void userLoginState(Integer userId){
    	sysUserOnlineDao.userLoginState(userId);
    }
}
