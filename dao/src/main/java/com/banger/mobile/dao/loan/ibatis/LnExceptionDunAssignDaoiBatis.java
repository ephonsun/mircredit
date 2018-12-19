package com.banger.mobile.dao.loan.ibatis;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.loan.LnExceptionDunAssignDao;
import com.banger.mobile.domain.model.loan.LnApproveLimitRole;
import com.banger.mobile.domain.model.loan.LnExceptionDunAssign;
import com.banger.mobile.ibatis.GenericDaoiBatis;
import com.banger.mobile.util.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Zhangfp
 * Date: 13-2-6
 * Time: 上午9:11
 * To change this template use File | Settings | File Templates.
 *
 * 贷款主表
 */
public class LnExceptionDunAssignDaoiBatis extends GenericDaoiBatis implements LnExceptionDunAssignDao {

    public LnExceptionDunAssignDaoiBatis(){
        super(LnExceptionDunAssign.class);
    }
    /**
     * Constructor that takes in a class to see which type of entity to persist
     *
     * @param persistentClass the class type you'd like to persist
     */
    public LnExceptionDunAssignDaoiBatis(Class persistentClass) {
        super(LnExceptionDunAssign.class);
    }

    @Override
    public void insertExpDunAssign(LnExceptionDunAssign model) {
        this.getSqlMapClientTemplate().insert("insertExpDunAssign",model);
    }

    @Override
    public void insertExpDunAssignBatch(List<LnExceptionDunAssign> models){
        this.exectuteBatchInsert("insertExpDunAssign",models);
    }

    @Override
    public Integer selectAssignedCountByLoanId(Map<String,Object> paramMap){
        return (Integer)this.getSqlMapClientTemplate().queryForObject("selectAssignedCountByLoanId",paramMap);
    }

    @Override
    public void deleteAssignLoanByLoanId(Map<String,Object> paramMap){
        this.getSqlMapClientTemplate().delete("deleteAssignLoanByLoanId",paramMap);
    }

    @Override
    public PageUtil<LnExceptionDunAssign> selectMyExceptionDunLoanList(Map<String ,Object> parameterMap,Page page){
        List<LnExceptionDunAssign> list = (List<LnExceptionDunAssign>)this.findQueryPage("selectMyExceptionDunLoanList","selectAssignLoanCount",parameterMap,page);
        if (list == null) {
            list = new ArrayList<LnExceptionDunAssign>();
        }
        return new PageUtil<LnExceptionDunAssign>(list,page);
    }
}
