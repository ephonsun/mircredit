package com.banger.mobile.domain.model.loan;

import com.banger.mobile.domain.model.base.loan.BaseCrdDbdktz;
import com.banger.mobile.domain.model.base.loan.BaseCunkuanfhAjdk;

/**
 * @author zhangfp
 * @version $Id: CrdDbdktz v 0.1 ${} 上午10:44 zhangfp Exp $
 */
public class CrdDbdktz extends BaseCrdDbdktz {
    private BaseCunkuanfhAjdk baseCunkuanfhAjdk;  //账号余额表
    private LnLoanInfo lnLoanInfo;  //贷款申请信息
    private Integer loanSortNo;     //贷款期号

    public LnLoanInfo getLnLoanInfo() {
        return lnLoanInfo;
    }

    public void setLnLoanInfo(LnLoanInfo lnLoanInfo) {
        this.lnLoanInfo = lnLoanInfo;
    }

    public Integer getLoanSortNo() {
        return loanSortNo;
    }

    public void setLoanSortNo(Integer loanSortNo) {
        this.loanSortNo = loanSortNo;
    }

    public BaseCunkuanfhAjdk getBaseCunkuanfhAjdk() {
        return baseCunkuanfhAjdk;
    }

    public void setBaseCunkuanfhAjdk(BaseCunkuanfhAjdk baseCunkuanfhAjdk) {
        this.baseCunkuanfhAjdk = baseCunkuanfhAjdk;
    }
}
