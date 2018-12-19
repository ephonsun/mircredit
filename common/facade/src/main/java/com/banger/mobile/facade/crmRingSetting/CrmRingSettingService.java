/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       : 铃声设置service
 * Author     :yujh
 * Create Date:Aug 30, 2012
 */
package com.banger.mobile.facade.crmRingSetting;

import com.banger.mobile.domain.model.crmRingSetting.CrmRingSetting;

/**
 * @author yujh
 * @version $Id: CrmRingSettingService.java,v 0.1 Aug 30, 2012 10:46:25 AM Administrator Exp $
 */
public interface CrmRingSettingService {
	/**
	 * 添加
	 * @param userId
	 */
    public void insertCrmRingSetting(int userId);
    
    /**
     * 添加
     * @param crmRingSetting
     */
    public void insertCrmRingSetting(CrmRingSetting crmRingSetting);
    /**
     * 删除
     * @param userId
     */
    public void deleteCrmRingSetting(int userId);
    /**
     * 查询
     * @param userId
     * @return
     */
    public CrmRingSetting getCrmRingSettingByUserId(int userId);
    /**
     * 更新
     * @param crmRingSetting
     */
    public void updateCrmRingSetting(CrmRingSetting crmRingSetting);
    /**
     * 根据id 获得铃声设置
     * @param id
     * @return
     */
    public CrmRingSetting getCrmRingSettingById(Integer id);
}
