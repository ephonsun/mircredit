/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :铃声设置
 * Author     :yujh
 * Create Date:Aug 30, 2012
 */
package com.banger.mobile.dao.crmRingSetting;

import com.banger.mobile.domain.model.crmRingSetting.CrmRingSetting;

/**
 * @author yujh
 * @version $Id: CrmRingSettingDao.java,v 0.1 Aug 30, 2012 9:04:40 AM Administrator Exp $
 */
public interface CrmRingSettingDao {
	/**
     * 添加
     */
    public void insertCrmRingSetting(CrmRingSetting crmRingSetting);
    /**
     * 删除
     */
    public void deleteCrmRingSetting(int userId);
    /**
     * 查询
     */
    public CrmRingSetting getCrmRingSettingByUserId(int userId);
    /**
     * 更新
     */
    public void updateCrmRingSetting(CrmRingSetting crmRingSetting);
    /**
     * 根据id获取铃声配置
     * @param id
     * @return
     */
    public CrmRingSetting getCrmRingSettingById(Integer id);
}
