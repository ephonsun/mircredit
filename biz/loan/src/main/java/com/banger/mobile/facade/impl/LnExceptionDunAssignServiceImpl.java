package com.banger.mobile.facade.impl;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.loan.LnExceptionDunAssignDao;
import com.banger.mobile.domain.model.loan.LnExceptionDunAssign;
import com.banger.mobile.facade.loan.LnExceptionDunAssignService;
import com.banger.mobile.util.StringUtil;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Zhangfp
 * Date: 13-3-11
 * Time: 上午10:20
 * To change this template use File | Settings | File Templates.
 *
 * 分配异常催收贷款service
 */
public class LnExceptionDunAssignServiceImpl implements LnExceptionDunAssignService {

    private LnExceptionDunAssignDao lnExceptionDunAssignDao;

    public LnExceptionDunAssignDao getLnExceptionDunAssignDao() {
        return lnExceptionDunAssignDao;
    }

    public void setLnExceptionDunAssignDao(LnExceptionDunAssignDao lnExceptionDunAssignDao) {
        this.lnExceptionDunAssignDao = lnExceptionDunAssignDao;
    }

    public void insertExpDunAssignBatch(List<LnExceptionDunAssign> models) {
        lnExceptionDunAssignDao.insertExpDunAssignBatch(models);
    }

    public Integer selectAssignedCountByLoanId(Map<String, Object> paramMap) {
        return lnExceptionDunAssignDao.selectAssignedCountByLoanId(paramMap);
    }

    public void deleteAssignLoanByLoanId(Map<String, Object> paramMap) {
        lnExceptionDunAssignDao.deleteAssignLoanByLoanId(paramMap);
    }

    public PageUtil<LnExceptionDunAssign> selectMyExceptionDunLoanList(Map<String, Object> parameterMap, Page page){
        for (Map.Entry<String, Object> entry : parameterMap.entrySet()) {
            if (entry.getValue() instanceof String) {
                parameterMap.put(entry.getKey(), StringUtil.ReplaceSQLEscapeChar(StringUtil
                        .ReplaceSQLChar(entry.getValue().toString())));
            }
        }
         return lnExceptionDunAssignDao.selectMyExceptionDunLoanList(parameterMap,page);
    }
}
