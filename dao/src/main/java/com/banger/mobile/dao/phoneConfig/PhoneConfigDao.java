/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :通话基础配置dao
 * Author     :yujh
 * Create Date:Jun 1, 2012
 */
package com.banger.mobile.dao.phoneConfig;

import com.banger.mobile.domain.model.phoneConfig.PhoneConfig;

/**
 * @author yujh
 * @version $Id: PhoneConfig.java,v 0.1 Jun 1, 2012 9:56:04 AM Administrator Exp $
 */
public interface PhoneConfigDao {
    /**
     * 设置参数
     */
    public void update(PhoneConfig phoneConfig);
    /**
     * 修改是否屏幕取词
     * @param phoneConfig
     */
    public void updatePhoneConfigIsScreamWord(PhoneConfig phoneConfig);
    /**
     * 修改是否弹出……
     * @param phoneConfig
     */
    public void updatePhoneConfigIsPopUp(PhoneConfig phoneConfig);
    /**
     * 查询参数
     * @return
     */
    public PhoneConfig query(int userId);
    /**
     * 新增个人基础配置
     * @param userId 用户id
     */
    public void addPhoneConfig(PhoneConfig phoneConfig);
}
