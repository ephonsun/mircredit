/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :贷款状态-Service-接口实现
 * Author     :QianJie
 * Create Date:Feb 17, 2013
 */
package com.banger.mobile.facade.impl;

import com.banger.mobile.dao.loan.LnLoanStatusDao;
import com.banger.mobile.domain.model.loan.LnLoanStatus;
import com.banger.mobile.facade.loan.LnLoanStatusService;

import java.util.List;

/**
 * @author QianJie
 * @version $Id: LnLoanStatusServiceImpl.java,v 0.1 Feb 17, 2013 3:48:42 PM QianJie Exp $
 */
public class LnLoanStatusServiceImpl implements LnLoanStatusService {

    private LnLoanStatusDao lnLoanStatusDao;

    public void setLnLoanStatusDao(LnLoanStatusDao lnLoanStatusDao) {
        this.lnLoanStatusDao = lnLoanStatusDao;
    }

    public List<LnLoanStatus> getLoanStatusList() {
        return lnLoanStatusDao.getLoanStatusList();
    }

    public String getLoanStatusName(String loanStatusId){
        return lnLoanStatusDao.getLoanStatusName(loanStatusId);
    }
}
