package com.banger.mobile.facade.finance;

import java.util.List;

import com.banger.mobile.domain.model.finance.FeArticleAttachment;

public interface FeArticleAttachmentService {
	
	
	 /**
     * 插入文章附件
     */
    public void saveFeArticleAttachment(FeArticleAttachment attachment);
    
    /**
     * 查询文章的附件
     * @return
     */
    public List<FeArticleAttachment> getFeArticleAttachmentByAttr(Integer acticleId);
    
    /**
     * 删除文章指定的附件
     * @return
     */
    public Integer deleteFeArticleAttachmentByAttr(FeArticleAttachment attachment);
    
    /**
     * 获取指定附件
     * @param attachmentId
     * @return
     */
    public FeArticleAttachment getFeArticleAttachmentById(Integer attachmentId);

    
    /**
     * 删除文章全部附件
     * @return
     */
    public Integer deleteFeArticleAttachmentById(Integer acticleId);
 
}
