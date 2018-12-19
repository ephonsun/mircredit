/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :录音提示音dao
 * Author     :yujh
 * Create Date:Aug 29, 2012
 */
package com.banger.mobile.dao.crmRecordRemind;

import com.banger.mobile.domain.model.crmRecordRemind.CrmRecordRemind;

/**
 * @author yujh
 * @version $Id: CrmRecordRemindDao.java,v 0.1 Aug 29, 2012 6:08:58 PM Administrator Exp $
 */
public interface CrmRecordRemindDao {
    /**
     * 添加
     * @param crmRecordRemind
     */
    public void insertCrmRecordRemind(CrmRecordRemind crmRecordRemind);
    /**
     * 更新
     * @param crmRecordRemind
     */
    public void updateCrmRecordRemind(CrmRecordRemind crmRecordRemind);
    /**
     * 查询
     * @param userId
     * @return
     */
    public CrmRecordRemind getCrmRecordRemindByUserId(int userId);
    /**
     * 删除
     * @param userId
     */
    public void deleteCrmRecordRemind(int userId);
    /**
     * 获得录音提示音配置对象
     */
    public CrmRecordRemind getCrmRecordRemindById(Integer id);
}
