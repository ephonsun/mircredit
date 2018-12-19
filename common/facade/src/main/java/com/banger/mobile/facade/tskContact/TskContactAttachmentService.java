/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :任务附件Service
 * Author     :liyb
 * Create Date:2012-8-13
 */
package com.banger.mobile.facade.tskContact;

import java.util.List;

import com.banger.mobile.domain.model.tskContact.TskContactAttachment;



/**
 * @author liyb
 * @version $Id: TskTaskAttachmentService.java,v 0.1 2012-8-13 下午03:49:20 liyb Exp $
 */
public interface TskContactAttachmentService {
    /**
     * 插入任务附件
     */
    public void saveTaskAttachment(TskContactAttachment attachment);
    
    /**
     * 查询任务的附件
     * @return
     */
    public List<TskContactAttachment> getTaskByAttr(Integer taskId);
    
    /**
     * 删除任务指定的附件
     * @return
     */
    public Integer deleteTaskByAttr(TskContactAttachment attachment);
    
    /**
     * 获取指定附件
     * @param attachmentId
     * @return
     */
    public TskContactAttachment getTaskAttrById(Integer attachmentId);
    
    /**
     *  根据任务Id删除所有附件
     * @param taskId
     * @return
     */
    public Integer delTaskByTaskId(int taskId);
}
