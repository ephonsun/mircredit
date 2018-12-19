package com.banger.mobile.dao.webservice.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.webservice.PadLnLoanInfoDao;
import com.banger.mobile.domain.model.pad.PadLoan;
import com.banger.mobile.domain.model.pad.PadLoanInfo;
import com.banger.mobile.domain.model.user.SysUser;
import com.banger.mobile.ibatis.GenericDaoiBatis;
import com.banger.mobile.util.StringUtil;

public class PadLnLoanInfoDaoIbatis extends GenericDaoiBatis implements PadLnLoanInfoDao {

    @SuppressWarnings("unchecked")
    public PadLnLoanInfoDaoIbatis(Class persistentClass) {
        super(persistentClass);
    }
    public PadLnLoanInfoDaoIbatis(){
        super(PadLoan.class);
    }


    @Override
    public PageUtil<PadLoan> getLnLoanListPage(Map<String, Object> parameterMap, Page page) {

        Map<String, Object> fConds = new HashMap<String, Object>();
        for (Map.Entry<String, Object> entry : parameterMap.entrySet()) {
            if (entry.getValue() instanceof String) {
                fConds.put(entry.getKey(), StringUtil.ReplaceSQLEscapeChar(StringUtil
                        .ReplaceSQLChar(entry.getValue().toString())));
            } else {
                fConds.put(entry.getKey(), entry.getValue());
            }
        }
        List<PadLoan> list = (List<PadLoan>) this.findQueryPage("getPadLoanList", "getPadLoanCount", fConds, page);
        return new PageUtil<PadLoan>(list, page);
    }

    public PadLoan getPanLoanById(int loanId){
        return (PadLoan) this.getSqlMapClientTemplate().queryForObject("getPanLoanById", loanId);
    }

    public PadLoanInfo getPanLoanInfoById(int loanId){
        return (PadLoanInfo) this.getSqlMapClientTemplate().queryForObject("getPanLoanInfoById", loanId);
    }

    public int getPadLoanCountByLoanStatus(Map<String, Object> parameterMap){
        return (Integer) this.getSqlMapClientTemplate().queryForObject("getPadLoanCount", parameterMap);
    }

}
