/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :知识附件-Service-接口
 * Author     :QianJie
 * Create Date:Dec 3, 2012
 */
package com.banger.mobile.facade.microProduct;

import java.util.List;
import java.util.Map;

import com.banger.mobile.domain.model.microProduct.PdtLibraryAttachment;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author QianJie
 * @version $Id: PdtLibraryAttachmentService.java,v 0.1 Dec 3, 2012 2:58:50 PM QianJie Exp $
 */
public interface PdtLibraryAttachmentService {
    /**
     * 添加知识附件
     * @param pdtLibraryAttachment
     * @return
     */
    public int addPdtLibraryAttachment(PdtLibraryAttachment pdtLibraryAttachment);
    
    /**
     * 删除知识附件
     * @param conds
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void delPdtLibraryAttachmentByConds(Map<String, Object> conds, Integer fileId);
    
    /**
     * 查询知识附件（不包括附件基本信息）
     * @param conds
     * @return
     */
    public List<PdtLibraryAttachment> getPdtLibraryAttachmentByConds(Map<String, Object> conds);

    /**
     * 查询知识附件（包括基本附件信息）
     * @param conds
     * @return
     */
    public List<PdtLibraryAttachment> getAllPdtLibraryAttachmentByConds(Map<String, Object> conds);
}
