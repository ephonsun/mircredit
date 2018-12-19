package com.banger.mobile.facade.pdtProduct;

import java.util.List;

import com.banger.mobile.domain.model.pdtProduct.PdtProductAttachment;

/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：Dec 25, 2012 11:14:36 AM
 * 类说明
 */
public interface PdtProductAttService {

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
    public Integer deleteAttrById(Integer attachmentId);
    
    /**
     * 获取指定附件
     * @param attachmentId
     * @return
     */
    public PdtProductAttachment getPdtAttrById(Integer attachmentId);
}



