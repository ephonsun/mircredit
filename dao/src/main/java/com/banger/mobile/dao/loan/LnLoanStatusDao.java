package com.banger.mobile.dao.loan;

import com.banger.mobile.domain.model.loan.LnLoanStatus;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-2-6
 * Time: 下午3:41
 * To change this template use File | Settings | File Templates.
 */
public interface LnLoanStatusDao {
    public List<LnLoanStatus> getLoanStatusList();

    public String getLoanStatusName(String loanStatusId);
}
