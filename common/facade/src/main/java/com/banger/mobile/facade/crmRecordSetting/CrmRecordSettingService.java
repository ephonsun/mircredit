/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :录音设置service
 * Author     :yujh
 * Create Date:Sep 3, 2012
 */
package com.banger.mobile.facade.crmRecordSetting;

import com.banger.mobile.domain.model.crmRecordSetting.CrmRecordSetting;

/**
 * @author yujh
 * @version $Id: CrmRecordSettingService.java,v 0.1 Sep 3, 2012 4:41:28 PM Administrator Exp $
 */
public interface CrmRecordSettingService {
	/**
	 * 新增录音配置
	 * @param userId
	 */
    public void insertCrmRecordSetting(int userId);
    
    /**
     * 修改录音配置
     * @param crmRecordSetting
     */
    public void updateCrmRecordSetting(CrmRecordSetting crmRecordSetting);
    
    /**
     * 获得录音配置
     * @param userId
     * @return
     */
    public CrmRecordSetting getCrmRecordSettingById(int userId);
    
    /**
     * 删除录音配置
     * @param userId
     */
    public void deleteCrmRecordSetting(int userId);
}
