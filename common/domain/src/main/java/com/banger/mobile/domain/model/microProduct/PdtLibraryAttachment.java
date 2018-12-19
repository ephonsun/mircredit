/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :知识附件Domain-扩展1
 * Author     :QianJie
 * Create Date:Dec 3, 2012
 */
package com.banger.mobile.domain.model.microProduct;

import com.banger.mobile.domain.model.base.microProduct.BasePdtLibraryAttachment;
import com.banger.mobile.domain.model.uploadFile.SysUploadFile;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author QianJie
 * @version $Id: PdtLibraryAttachment.java,v 0.1 Dec 3, 2012 1:38:41 PM QianJie Exp $
 */
public class PdtLibraryAttachment extends BasePdtLibraryAttachment {

    private static final long serialVersionUID = -152454189952330405L;

    public PdtLibraryAttachment() {
        super();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-2057000393, 2048011891).appendSuper(super.hashCode())
            .toHashCode();
    }

    private SysUploadFile sysUploadFile;

    public SysUploadFile getSysUploadFile() {
        return sysUploadFile;
    }

    public void setSysUploadFile(SysUploadFile sysUploadFile) {
        this.sysUploadFile = sysUploadFile;
    }
}
