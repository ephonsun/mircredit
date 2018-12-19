/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :ThinkPad
 * Create Date:2013-7-2
 */
package com.banger.mobile.facade.transport;

/**
 * @author ThinkPad
 * @version $Id: ClientSocketLoanStatusService.java,v 0.1 2013-7-2 下午02:30:46 ThinkPad Exp $
 */
public interface ClientSocketLoanStatusService {
    // 根据贷款流水号，查询放贷状态
    public String getLoanStatusMessage(String serialNumber);
}
