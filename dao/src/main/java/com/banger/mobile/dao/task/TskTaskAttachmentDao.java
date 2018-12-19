/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :任务附件Dao
 * Author     :liyb
 * Create Date:2012-8-13
 */
package com.banger.mobile.dao.task;

import java.util.List;

import com.banger.mobile.domain.model.task.TskTaskAttachment;

/**
 * @author liyb
 * @version $Id: TskTaskAttachmentDao.java,v 0.1 2012-8-13 下午03:36:51 liyb Exp $
 */
public interface TskTaskAttachmentDao {
    
    /**
     * 插入任务附件
     */
    public void saveTaskAttachment(TskTaskAttachment attachment);
    
    /**
     * 查询任务的附件
     * @return
     */
    public List<TskTaskAttachment> getTaskByAttr(Integer taskId);
    
    /**
     * 删除任务指定的附件
     * @return
     */
    public Integer deleteTaskByAttr(TskTaskAttachment attachment);
    
    /**
     * 获取指定附件
     * @param attachmentId
     * @return
     */
    public TskTaskAttachment getTaskAttrById(Integer attachmentId);
}
