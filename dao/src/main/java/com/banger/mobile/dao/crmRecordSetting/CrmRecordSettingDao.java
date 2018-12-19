/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :录音设置dao
 * Author     :yujh
 * Create Date:Sep 3, 2012
 */
package com.banger.mobile.dao.crmRecordSetting;

import com.banger.mobile.domain.model.crmRecordSetting.CrmRecordSetting;

/**
 * @author yujh
 * @version $Id: CrmRecordSettingDao.java,v 0.1 Sep 3, 2012 4:16:18 PM Administrator Exp $
 */
public interface CrmRecordSettingDao {
    public void insertCrmRecordSetting(CrmRecordSetting crmRecordSetting);
    public void updateCrmRecordSetting(CrmRecordSetting crmRecordSetting);
    public CrmRecordSetting getCrmRecordSettingById(int userId);
    public void deleteCrmRecordSetting(int userId);
}
