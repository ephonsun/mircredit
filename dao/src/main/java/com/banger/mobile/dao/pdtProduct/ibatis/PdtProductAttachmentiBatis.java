package com.banger.mobile.dao.pdtProduct.ibatis;

import java.util.List;

import com.banger.mobile.dao.pdtProduct.PdtProductAttachmentDao;
import com.banger.mobile.domain.model.pdtProduct.PdtProductAttachment;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：Dec 25, 2012 11:03:06 AM
 * 类说明
 */
public class PdtProductAttachmentiBatis extends GenericDaoiBatis implements PdtProductAttachmentDao{

	public PdtProductAttachmentiBatis() {
		super(PdtProductAttachment.class);
		// TODO Auto-generated constructor stub
	}
	public PdtProductAttachmentiBatis(Class persistentClass) {
		super(PdtProductAttachment.class);
			// TODO Auto-generated constructor stub
	}

	/**
     * 插入附件
     */
    public void savePdtAttachment(PdtProductAttachment attachment){
    	this.getSqlMapClientTemplate().insert("InsertProductAttachment",attachment);
    }
    
    /**
     * 查询产品的附件列表
     * @return
     */
    public List<PdtProductAttachment> getAttrByProductId(Integer productId){
    	return (List<PdtProductAttachment>)this.getSqlMapClientTemplate().queryForList("GetAttrByProductId",productId);
    }
    
    /**
     * 删除指定的附件
     * @return
     */
    public Integer deleteTaskByAttr(Integer attachmentId){
    	return this.getSqlMapClientTemplate().delete("DelAttrByAttId", attachmentId);
    }
    
    /**
     * 获取指定附件
     * @param attachmentId
     * @return
     */
    public PdtProductAttachment getPdtAttrById(Integer attachmentId){
    	return (PdtProductAttachment)this.getSqlMapClientTemplate().queryForObject("GetPdtAttrById",attachmentId);
    }
}



