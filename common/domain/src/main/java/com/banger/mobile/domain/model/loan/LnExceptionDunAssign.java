package com.banger.mobile.domain.model.loan;

import com.banger.mobile.domain.model.base.loan.BaseLnExceptionDunAssign;
import com.banger.mobile.domain.model.base.loan.BaseLnLoan;
import com.banger.mobile.domain.model.customer.CrmCustomer;

/**
 * Created with IntelliJ IDEA.
 * User: Zhangfp
 * Date: 13-2-5
 * Time: 下午3:45
 * To change this template use File | Settings | File Templates.
 * <p/>
 *
 * 异常催收贷款分配表
 */
public class LnExceptionDunAssign extends BaseLnExceptionDunAssign {

    private LnLoan lnLoan;
    private LnLoanType lnLoanTypeBean;
    private CrmCustomer crmCustomer;
    private String exceptionAssignUserNames;

    public String getExceptionAssignUserNames() {
        return exceptionAssignUserNames;
    }

    public void setExceptionAssignUserNames(String exceptionAssignUserNames) {
        this.exceptionAssignUserNames = exceptionAssignUserNames;
    }

    public CrmCustomer getCrmCustomer() {
        return crmCustomer;
    }

    public void setCrmCustomer(CrmCustomer crmCustomer) {
        this.crmCustomer = crmCustomer;
    }

    public LnLoanType getLnLoanTypeBean() {
        return lnLoanTypeBean;
    }

    public void setLnLoanTypeBean(LnLoanType lnLoanTypeBean) {
        this.lnLoanTypeBean = lnLoanTypeBean;
    }

    public LnLoan getLnLoan() {
        return lnLoan;
    }

    public void setLnLoan(LnLoan lnLoan) {
        this.lnLoan = lnLoan;
    }

}
