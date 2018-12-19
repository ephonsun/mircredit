/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :录音提示音
 * Author     :yujh
 * Create Date:Aug 30, 2012
 */
package com.banger.mobile.facade.crmRecordRemind;

import com.banger.mobile.domain.model.crmRecordRemind.CrmRecordRemind;

/**
 * @author yujh
 * @version $Id: CrmRecordRemindService.java,v 0.1 Aug 30, 2012 10:46:43 AM Administrator Exp $
 */
public interface CrmRecordRemindService {
    /**
     * 添加
     * @param crmRecordRemind
     */
    public void insertCrmRecordRemind(int userId);
    
    /**
     * 
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
     * @param id
     * @return
     */
    public CrmRecordRemind getCrmRecordRemindById(Integer id);
}
