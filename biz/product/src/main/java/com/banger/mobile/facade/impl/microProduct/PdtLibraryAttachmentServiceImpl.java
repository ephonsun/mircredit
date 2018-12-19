/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :知识附件-Service-接口实现
 * Author     :QianJie
 * Create Date:Dec 3, 2012
 */
package com.banger.mobile.facade.impl.microProduct;

import java.util.List;
import java.util.Map;

import com.banger.mobile.dao.microProduct.PdtLibraryAttachmentDao;
import com.banger.mobile.domain.model.microProduct.PdtLibraryAttachment;
import com.banger.mobile.facade.microProduct.PdtLibraryAttachmentService;
import com.banger.mobile.facade.uploadFile.SysUploadFileService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author QianJie
 * @version $Id: PdtLibraryAttachmentServiceImpl.java,v 0.1 Dec 3, 2012 3:00:12 PM QianJie Exp $
 */
public class PdtLibraryAttachmentServiceImpl implements PdtLibraryAttachmentService {
    
    private PdtLibraryAttachmentDao pdtLibraryAttachmentDao;
    private SysUploadFileService sysUploadFileService;

    public void setSysUploadFileService(SysUploadFileService sysUploadFileService) {
        this.sysUploadFileService = sysUploadFileService;
    }

    public void setPdtLibraryAttachmentDao(PdtLibraryAttachmentDao pdtLibraryAttachmentDao) {
        this.pdtLibraryAttachmentDao = pdtLibraryAttachmentDao;
    }

    /**
     * 添加知识附件
     * @param pdtLibraryAttachment
     * @return
     * @see com.banger.mobile.facade.product.PdtLibraryAttachmentService#addPdtLibraryAttachment(com.banger.mobile.domain.model.product.PdtLibraryAttachment)
     */
    public int addPdtLibraryAttachment(PdtLibraryAttachment pdtLibraryAttachment) {
        return pdtLibraryAttachmentDao.addPdtLibraryAttachment(pdtLibraryAttachment);
    }

    /**
     * 删除知识附件
     * @param conds
     * @see com.banger.mobile.facade.product.PdtLibraryAttachmentService#delPdtLibraryAttachmentByConds(java.util.Map)
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void delPdtLibraryAttachmentByConds(Map<String, Object> conds,Integer fileId) {
        pdtLibraryAttachmentDao.delPdtLibraryAttachmentByConds(conds);
        sysUploadFileService.deleteSysUploadFile(fileId);
    }

    /**
     * 查询知识附件
     * @param conds
     * @return
     * @see com.banger.mobile.facade.product.PdtLibraryAttachmentService#getPdtLibraryAttachmentByConds(java.util.Map)
     */
    public List<PdtLibraryAttachment> getPdtLibraryAttachmentByConds(Map<String, Object> conds) {
        return pdtLibraryAttachmentDao.getPdtLibraryAttachmentByConds(conds);
    }

    /**
     * 查询知识附件（包括基本附件信息）
     * @param conds
     * @return
     */
    public List<PdtLibraryAttachment> getAllPdtLibraryAttachmentByConds(Map<String, Object> conds){
        return pdtLibraryAttachmentDao.getAllPdtLibraryAttachmentByConds(conds);
    }

}
