package com.banger.mobile.domain.model.loan;

import com.banger.mobile.domain.model.base.loan.BaseLnLoanCoBorrower;

/**
 * Created with IntelliJ IDEA.
 * User: Zhangfp
 * Date: 13-2-5
 * Time: 下午3:46
 * To change this template use File | Settings | File Templates.
 *
 * 共同借贷人
 */
public class LnLoanCoBorrower extends BaseLnLoanCoBorrower {

    private static final long serialVersionUID = -8189729061540007826L;

    private Integer oldCustomerId;

    public Integer getOldCustomerId() {
        return oldCustomerId;
    }

    public void setOldCustomerId(Integer oldCustomerId) {
        this.oldCustomerId = oldCustomerId;
    }
}
