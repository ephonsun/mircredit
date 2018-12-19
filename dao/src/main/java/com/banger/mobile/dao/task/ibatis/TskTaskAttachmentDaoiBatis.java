/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :任务附件Dao接口实现
 * Author     :liyb
 * Create Date:2012-8-13
 */
package com.banger.mobile.dao.task.ibatis;

import java.util.List;

import com.banger.mobile.dao.task.TskTaskAttachmentDao;
import com.banger.mobile.domain.model.task.TskTaskAttachment;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author liyb
 * @version $Id: TskTaskAttachmentDaoiBatis.java,v 0.1 2012-8-13 下午03:39:01 liyb Exp $
 */
public class TskTaskAttachmentDaoiBatis extends GenericDaoiBatis implements TskTaskAttachmentDao {
    
    public TskTaskAttachmentDaoiBatis(){
        super(TskTaskAttachment.class);
    }

    /**
     * 插入任务附件
     * @param attachment
     */
    public void saveTaskAttachment(TskTaskAttachment attachment) {
        this.getSqlMapClientTemplate().insert("InsertTaskAttachment",attachment);
    }

    /**
     * 删除任务指定的附件
     * @param attachment
     * @return
     */
    public Integer deleteTaskByAttr(TskTaskAttachment attachment) {
        return this.getSqlMapClientTemplate().delete("DelTaskByAttr",attachment);
    }

    /**
     * 查询任务的附件
     * @param taskId
     * @return
     */
    public List<TskTaskAttachment> getTaskByAttr(Integer taskId) {
        return this.getSqlMapClientTemplate().queryForList("GetTaskByAttr",taskId);
    }

    /**
     * 获取指定附件
     * @param attachmentId
     * @return
     */
    public TskTaskAttachment getTaskAttrById(Integer attachmentId) {
        return (TskTaskAttachment) this.getSqlMapClientTemplate().queryForObject("GetTaskAttrById",attachmentId);
    }

}
