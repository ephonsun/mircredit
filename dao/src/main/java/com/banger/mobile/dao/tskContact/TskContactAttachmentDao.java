package com.banger.mobile.dao.tskContact;

import java.util.List;

import com.banger.mobile.domain.model.tskContact.TskContactAttachment;

/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：Dec 17, 2012 9:43:36 AM
 * 类说明
 */
public interface TskContactAttachmentDao {
	
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



