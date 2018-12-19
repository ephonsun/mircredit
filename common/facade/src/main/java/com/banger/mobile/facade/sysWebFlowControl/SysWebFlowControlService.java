/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :web端流量控制
 * Author     :yujh
 * Create Date:Aug 24, 2012
 */
package com.banger.mobile.facade.sysWebFlowControl;

import java.util.List;

import com.banger.mobile.domain.model.sysWebFlowControl.SysWebFlowControl;
import com.banger.mobile.domain.model.sysWebFlowControl.SysWebFlowControlInfo;

/**
 * @author yujh
 * @version $Id: SysWebFlowControlService.java,v 0.1 Aug 24, 2012 7:33:15 PM Administrator Exp $
 */
public interface SysWebFlowControlService {
	/**
	 * 添加
	 * @param deptId
	 */
    public void insertFlowControl(int deptId);

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
     * 得到账号流量限制参数
     * @param account
     * @return
     */
    public SysWebFlowControl getFlowControlByAccount(String account);
}
