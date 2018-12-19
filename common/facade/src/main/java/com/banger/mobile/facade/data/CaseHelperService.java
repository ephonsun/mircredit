/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :ThinkPad
 * Create Date:2013-6-30
 */
package com.banger.mobile.facade.data;

import com.banger.mobile.domain.model.data.CmDownloadUrl;
import com.banger.mobile.domain.model.data.CustomerData;

import java.util.List;

/**
 * @author ThinkPad
 * @version $Id: CaseHelperService.java,v 0.1 2013-6-30 下午10:51:57 ThinkPad Exp $
 */
public interface CaseHelperService {
    public String getCaseNoOther(CustomerData data);
    
    public String getCaseNo(CustomerData data);
    
    public boolean uploadImage(String caseNo, String filePath,CustomerData data);

    public List<CmDownloadUrl> getCaseDownloadUrl(String caseNo,CustomerData data);
}
