/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :web端限流控制
 * Author     :yujh
 * Create Date:Aug 24, 2012
 */
package com.banger.mobile.dao.sysWebFlowControl;

import java.util.List;

import com.banger.mobile.domain.model.sysWebFlowControl.SysWebFlowControl;
import com.banger.mobile.domain.model.sysWebFlowControl.SysWebFlowControlInfo;

/**
 * @author yujh
 * @version $Id: SysWebFlowControlDao.java,v 0.1 Aug 24, 2012 5:32:03 PM Administrator Exp $
 */
public interface SysWebFlowControlDao {
	/**
	 * 添加
	 * @param flowControl
	 */
    public void insertFlowControl(SysWebFlowControl flowControl);
    /**
     * 删除
     * @param deptId
     */
    public void deleteFlowControl(int deptId);
    /**
     * 更新
     * @param flowControl
     */
    public void updateFlowControl(SysWebFlowControl flowControl);
    /**
     * 列表
     * @return
     */
    public List<SysWebFlowControlInfo> getAllFlowControl();
    
    /**
     * 账号流量限制参数
     */
    public SysWebFlowControl getFlowControlByAccount(String account);
}
