/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :Administrator
 * Create Date:2013-5-20
 */
package com.banger.mobile.facade.microTask;

import java.util.Map;

import com.banger.mobile.domain.model.microTask.CounterOutPintMessage;

/**
 * @author Administrator
 * @version $Id: TskMicroTaskAssignService.java,v 0.1 2013-5-20 上午10:08:58 Administrator Exp $
 */
public interface TskMicroTaskAssignService {
   public CounterOutPintMessage excelToDataBase(Map<String, Object>paramMap);
}
