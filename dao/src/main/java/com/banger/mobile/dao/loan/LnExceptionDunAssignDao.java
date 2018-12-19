package com.banger.mobile.dao.loan;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.loan.LnDunLog;
import com.banger.mobile.domain.model.loan.LnExceptionDunAssign;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: zhangfp
 * Date: 13-2-6
 * Time: 上午9:19
 * To change this template use File | Settings | File Templates.
 *
 * 异常催收贷款分配
 */
public interface LnExceptionDunAssignDao {
    void insertExpDunAssign(LnExceptionDunAssign model);

    void insertExpDunAssignBatch(List<LnExceptionDunAssign> models);

    Integer selectAssignedCountByLoanId(Map<String, Object> paramMap);

    void deleteAssignLoanByLoanId(Map<String, Object> paramMap);

    PageUtil<LnExceptionDunAssign> selectMyExceptionDunLoanList(Map<String ,Object> parameterMap,Page page);
}
