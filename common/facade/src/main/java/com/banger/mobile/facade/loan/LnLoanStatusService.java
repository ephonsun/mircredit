/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :贷款状态-Service-接口
 * Author     :QianJie
 * Create Date:Feb 17, 2013
 */
package com.banger.mobile.facade.loan;

import com.banger.mobile.domain.model.loan.LnLoanStatus;

import java.util.List;

/**
 * @author QianJie
 * @version $Id: LnLoanStatusService.java,v 0.1 Feb 17, 2013 3:42:16 PM QianJie Exp $
 */
public interface LnLoanStatusService {

    public List<LnLoanStatus> getLoanStatusList();

    public String getLoanStatusName(String loanStatusId);
}
