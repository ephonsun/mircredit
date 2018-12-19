/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :任务附件-Service-接口
 * Author     :QianJie
 * Create Date:Mar 2, 2013
 */
package com.banger.mobile.facade.microTask;

import java.util.List;
import java.util.Map;

import com.banger.mobile.domain.model.microTask.TskMicroTaskAttachment;

/**
 * @author QianJie
 * @version $Id: TskMicroTaskAttachmentService.java,v 0.1 Mar 2, 2013 11:46:57 AM QianJie Exp $
 */
public interface TskMicroTaskAttachmentService {
    /**
     * 添加任务附件
     * @param tskMicroTaskAttachment
     */
    public void addTskMicroTaskAttachment(TskMicroTaskAttachment tskMicroTaskAttachment);

    /**
     * 删除任务附件
     * @param conds
     */
    public void delTskMicroTaskAttachmentByConds(Map<String, Object> conds);
    
    /**
     * 查询所有务附件数据
     * @param conds
     * @return
     */
    public List<TskMicroTaskAttachment> getAllTskMicroTaskAttachmentByConds(Map<String, Object> conds);
}
