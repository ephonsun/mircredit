/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :任务附件-Service-接口实现
 * Author     :QianJie
 * Create Date:Mar 2, 2013
 */
package com.banger.mobile.facade.impl.task.microTask;

import java.util.List;
import java.util.Map;

import com.banger.mobile.dao.microTask.TskMicroTaskAttachmentDao;
import com.banger.mobile.domain.model.microTask.TskMicroTaskAttachment;
import com.banger.mobile.facade.microTask.TskMicroTaskAttachmentService;

/**
 * @author QianJie
 * @version $Id: TskMicroTaskAttachmentServiceImpl.java,v 0.1 Mar 2, 2013 11:50:33 AM QianJie Exp $
 */
public class TskMicroTaskAttachmentServiceImpl implements TskMicroTaskAttachmentService {

    private TskMicroTaskAttachmentDao tskMicroTaskAttachmentDao;

    public void setTskMicroTaskAttachmentDao(TskMicroTaskAttachmentDao tskMicroTaskAttachmentDao) {
        this.tskMicroTaskAttachmentDao = tskMicroTaskAttachmentDao;
    }

    /**
     * 添加任务附件
     * @param tskMicroTaskAttachment
     * @see com.banger.mobile.facade.microTask.TskMicroTaskAttachmentService#addTskMicroTaskAttachment(com.banger.mobile.domain.model.microTask.TskMicroTaskAttachment)
     */
    public void addTskMicroTaskAttachment(TskMicroTaskAttachment tskMicroTaskAttachment) {
        tskMicroTaskAttachmentDao.addTskMicroTaskAttachment(tskMicroTaskAttachment);
    }

    /**
     * 删除任务附件
     * @param conds
     * @see com.banger.mobile.facade.microTask.TskMicroTaskAttachmentService#delTskMicroTaskAttachmentByConds(java.util.Map)
     */
    public void delTskMicroTaskAttachmentByConds(Map<String, Object> conds) {
        tskMicroTaskAttachmentDao.delTskMicroTaskAttachmentByConds(conds);
    }

    /**
     * 查询所有务附件数据
     * @param conds
     * @return
     * @see com.banger.mobile.facade.microTask.TskMicroTaskAttachmentService#getAllTskMicroTaskAttachmentByConds(java.util.Map)
     */
    public List<TskMicroTaskAttachment> getAllTskMicroTaskAttachmentByConds(
                                                                            Map<String, Object> conds) {
        return tskMicroTaskAttachmentDao.getAllTskMicroTaskAttachmentByConds(conds);
    }
    
}
