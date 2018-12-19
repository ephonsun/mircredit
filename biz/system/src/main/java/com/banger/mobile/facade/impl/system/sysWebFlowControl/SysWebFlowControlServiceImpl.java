/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :web端流量控制
 * Author     :yujh
 * Create Date:Aug 24, 2012
 */
package com.banger.mobile.facade.impl.system.sysWebFlowControl;

import java.util.List;

import com.banger.mobile.dao.sysWebFlowControl.SysWebFlowControlDao;
import com.banger.mobile.domain.model.sysWebFlowControl.SysWebFlowControl;
import com.banger.mobile.domain.model.sysWebFlowControl.SysWebFlowControlInfo;
import com.banger.mobile.facade.sysWebFlowControl.SysWebFlowControlService;

/**
 * @author yujh
 * @version $Id: SysWebFlowControlServiceImpl.java,v 0.1 Aug 24, 2012 7:35:51 PM Administrator Exp $
 */
public class SysWebFlowControlServiceImpl implements SysWebFlowControlService{
    
    private     SysWebFlowControlDao  sysWebFlowControlDao;

    public void setSysWebFlowControlDao(SysWebFlowControlDao sysWebFlowControlDao) {
        this.sysWebFlowControlDao = sysWebFlowControlDao;
    }
    /**
     * 删除
     */
    public void deleteFlowControl(int deptId) {
        this.sysWebFlowControlDao.deleteFlowControl(deptId);
    }
    /**
     * 列表
     */
    public List<SysWebFlowControlInfo> getAllFlowControl() {
        return this.sysWebFlowControlDao.getAllFlowControl();
    }
    /**
     * 添加
     */
    public void insertFlowControl(int deptId) {
        SysWebFlowControl flowControl= new SysWebFlowControl();
        flowControl.setIsActive(1);
        flowControl.setMaxBps(0);
        flowControl.setDeptId(deptId);
        this.sysWebFlowControlDao.insertFlowControl(flowControl);
    }
    /**
     * 更新
     */
    public void updateFlowControl(SysWebFlowControl flowControl) {
        this.sysWebFlowControlDao.updateFlowControl(flowControl);
    }
    
    /**
     * 账号流量限制参数
     */
    public SysWebFlowControl getFlowControlByAccount(String account) {
        return this.sysWebFlowControlDao.getFlowControlByAccount(account);
    }

}
