/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :通话基础配置service
 * Author     :yujh
 * Create Date:Jun 1, 2012
 */
package com.banger.mobile.facade.phoneConfig;

import com.banger.mobile.domain.model.phoneConfig.PhoneConfig;

/**
 * @author yujh
 * @version $Id: PhoneConfigService.java,v 0.1 Jun 1, 2012 10:58:04 AM Administrator Exp $
 */
public interface PhoneConfigService {
    /**
     * 查询通话配置参数
     * @return
     */
    public PhoneConfig query(int userId);

    /**
     * 更新通话配置参数
     * @param phoneConfig
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
     * 新增个人通话基础配置
     * @param userId
     */
    public void addPhoneConfig(int userId);
    /**
     * 返回区号
     * @param userId
     * @return
     */
    public String getCityCodeByUserId(int userId); 
}
