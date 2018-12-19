package com.banger.mobile.domain.model.loan;

import com.banger.mobile.domain.model.base.loan.BaseAnaApplyInfo;

import java.util.List;

/**
 * @author zhangfp
 * @version $Id: AnaApplyInfo v 0.1 ${} 下午3:05 zhangfp Exp $
 */
public class AnaApplyInfo extends BaseAnaApplyInfo{
    private static final long serialVersionUID = 6580253141043502037L;

    private List<AnaChildren> childrenList;

    public List<AnaChildren> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<AnaChildren> childrenList) {
        this.childrenList = childrenList;
    }
}
