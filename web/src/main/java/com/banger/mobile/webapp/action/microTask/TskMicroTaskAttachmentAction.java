/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :任务附件-Action
 * Author     :QianJie
 * Create Date:Mar 2, 2013
 */
package com.banger.mobile.webapp.action.microTask;

import com.banger.mobile.facade.microTask.TskMicroTaskAttachmentService;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * @author QianJie
 * @version $Id: TskMicroTaskAttachmentAction.java,v 0.1 Mar 2, 2013 11:59:19 AM QianJie Exp $
 */
public class TskMicroTaskAttachmentAction extends BaseAction{

    private static final long serialVersionUID = 5185417010159120811L;
    private TskMicroTaskAttachmentService tskMicroTaskAttachmentService;
    
    public TskMicroTaskAttachmentService getTskMicroTaskAttachmentService() {
        return tskMicroTaskAttachmentService;
    }
    public void setTskMicroTaskAttachmentService(
                                                 TskMicroTaskAttachmentService tskMicroTaskAttachmentService) {
        this.tskMicroTaskAttachmentService = tskMicroTaskAttachmentService;
    }
    
    

}
