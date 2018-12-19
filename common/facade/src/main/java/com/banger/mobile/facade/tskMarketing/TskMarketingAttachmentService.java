/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :营销任务附件-Service-接口
 * Author     :QianJie
 * Create Date:Jan 5, 2013
 */
package com.banger.mobile.facade.tskMarketing;

import java.util.List;
import java.util.Map;

import com.banger.mobile.domain.model.tskMarketing.TskMarketingAttachment;

/**
 * @author QianJie
 * @version $Id: TskMarketingAttachmentService.java,v 0.1 Jan 5, 2013 11:00:44 AM QianJie Exp $
 */
public interface TskMarketingAttachmentService {
    /**
     * 添加营销任务附件
     * @param tskMarketingAttachment
     */
    public void addTskMarketingAttachment(TskMarketingAttachment tskMarketingAttachment);

    /**
     * 删除营销任务附件
     * @param conds
     */
    public void delTskMarketingAttachmentByConds(Map<String, Object> conds);
    
    /**
     * 查询所有营销任务附件数据
     * @param conds
     * @return
     */
    public List<TskMarketingAttachment> getAllTskMarketingAttachmentByConds(Map<String, Object> conds);
}
