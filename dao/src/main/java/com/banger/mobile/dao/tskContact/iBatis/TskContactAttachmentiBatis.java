package com.banger.mobile.dao.tskContact.iBatis;

import java.util.List;

import com.banger.mobile.dao.tskContact.TskContactAttachmentDao;
import com.banger.mobile.domain.model.tskContact.TskContactAttachment;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：Dec 17, 2012 9:46:26 AM
 * 类说明
 */
public class TskContactAttachmentiBatis extends GenericDaoiBatis  implements TskContactAttachmentDao{
	 
		public TskContactAttachmentiBatis(){
	        super(TskContactAttachment.class);
	    }

	    /**
	     * 插入任务附件
	     * @param attachment
	     */
	    public void saveTaskAttachment(TskContactAttachment attachment) {
	        this.getSqlMapClientTemplate().insert("InsertTaskAttachment",attachment);
	    }

	    /**
	     * 删除任务指定的附件
	     * @param attachment
	     * @return
	     */
	    public Integer deleteTaskByAttr(TskContactAttachment attachment) {
	        return this.getSqlMapClientTemplate().delete("DelTaskByAttr",attachment);
	    }

	    /**
	     * 查询任务的附件
	     * @param taskId
	     * @return
	     */
	    public List<TskContactAttachment> getTaskByAttr(Integer taskId) {
	        return this.getSqlMapClientTemplate().queryForList("GetTaskByAttr",taskId);
	    }

	    /**
	     * 获取指定附件
	     * @param attachmentId
	     * @return
	     */
	    public TskContactAttachment getTaskAttrById(Integer attachmentId) {
	        return (TskContactAttachment) this.getSqlMapClientTemplate().queryForObject("GetTaskAttrById",attachmentId);
	    }    

	    /**
	     *  根据任务Id删除所有附件
	     * @param taskId
	     * @return
	     */
	    public Integer delTaskByTaskId(int taskId){
	    	return this.getSqlMapClientTemplate().delete("DelTaskByTaskId",taskId);
	    }
}



