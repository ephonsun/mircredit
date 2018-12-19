package com.banger.mobile.dao.pdtProduct;

import java.util.List;

import com.banger.mobile.domain.model.pdtProduct.PdtProductAttachment;

/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：Dec 25, 2012 10:59:51 AM
 * 类说明
 */
public interface PdtProductAttachmentDao {
	 /**
     * 插入附件
     */
    public void savePdtAttachment(PdtProductAttachment attachment);
    
    /**
     * 查询产品的附件列表
     * @return
     */
    public List<PdtProductAttachment> getAttrByProductId(Integer productId);
    
    /**
     * 删除指定的附件
     * @return
     */
    public Integer deleteTaskByAttr(Integer attachmentId);
    
    /**
     * 获取指定附件
     * @param attachmentId
     * @return
     */
    public PdtProductAttachment getPdtAttrById(Integer attachmentId);
}



