package com.banger.mobile.domain.model.loan;

import com.banger.mobile.domain.model.base.loan.BaseLnLoanGuarantor;

/**
 * Created with IntelliJ IDEA.
 * User: Zhangfp
 * Date: 13-2-5
 * Time: 下午3:50
 * To change this template use File | Settings | File Templates.
 *
 * 担保人
 */
public class LnLoanGuarantor extends BaseLnLoanGuarantor {

    private Integer oldCustomerId;

    public Integer getOldCustomerId() {
        return oldCustomerId;
    }

    public void setOldCustomerId(Integer oldCustomerId) {
        this.oldCustomerId = oldCustomerId;
    }
}
