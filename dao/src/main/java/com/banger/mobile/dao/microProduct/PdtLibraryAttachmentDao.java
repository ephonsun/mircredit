/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :知识附件-Dao-接口
 * Author     :QianJie
 * Create Date:Dec 3, 2012
 */
package com.banger.mobile.dao.microProduct;

import java.util.List;
import java.util.Map;

import com.banger.mobile.domain.model.microProduct.PdtLibraryAttachment;

/**
 * @author QianJie
 * @version $Id: PdtLibraryAttachmentDao.java,v 0.1 Dec 3, 2012 2:35:54 PM QianJie Exp $
 */
public interface PdtLibraryAttachmentDao {

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
    public void delPdtLibraryAttachmentByConds(Map<String, Object> conds);
    
    /**
     * 查询知识附件
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
