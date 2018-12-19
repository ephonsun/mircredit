package com.banger.mobile.facade.loan;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.loan.LnApproveLimitRole;
import com.banger.mobile.domain.model.loan.LnExceptionDunAssign;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Zhangfp
 * Date: 13-3-11
 * Time: ����9:34
 * To change this template use File | Settings | File Templates.
 *
 * �����쳣���մ��service
 */
public interface LnExceptionDunAssignService {

    void insertExpDunAssignBatch(List<LnExceptionDunAssign> models);

    Integer selectAssignedCountByLoanId(Map<String,Object> paramMap);

    void deleteAssignLoanByLoanId(Map<String,Object> paramMap);

    public PageUtil<LnExceptionDunAssign> selectMyExceptionDunLoanList(Map<String, Object> parameterMap, Page page);
}
