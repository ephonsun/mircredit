/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :贷款操作历史记录-Dao-接口实现
 * Author     :QianJie
 * Create Date:Feb 17, 2013
 */
package com.banger.mobile.dao.loan.ibatis;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.loan.LnApproveLimitUserDao;
import com.banger.mobile.domain.model.loan.LnApproveLimitUser;
import com.banger.mobile.ibatis.GenericDaoiBatis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author QianJie
 * @version $Id: LnOpHistoryDaoiBatis.java,v 0.1 Feb 17, 2013 2:54:46 PM QianJie Exp $
 */
public class LnApproveLimitUserDaoiBatis extends GenericDaoiBatis implements LnApproveLimitUserDao {

    public LnApproveLimitUserDaoiBatis() {
        super(LnApproveLimitUser.class);
    }
    /**
     * @param persistentClass
     */
    public LnApproveLimitUserDaoiBatis(Class persistentClass) {
        super(LnApproveLimitUser.class);
    }


    /**
     * 根据userId判断当前用户是否存在所能审批的最大金额额度
     * @param paramMap
     * @return
     */
    @Override
    public Integer selectCountByUserId(Map<String,Object> paramMap){
        return (Integer)this.getSqlMapClientTemplate().queryForObject("selectCountByUserId",paramMap);
    }

    /**
     * 根据userId查询出当前用户所能审批的最大金额额度
     * @param paramMap
     * @return
     */
    @Override
    public LnApproveLimitUser getLimitUserByUserId(Map<String,Object> paramMap){
        return (LnApproveLimitUser)this.getSqlMapClientTemplate().queryForObject("getLimitUserByUserId",paramMap);
    }

    /**
     * 根据部门id查询部门下的人员的审批额度
     */
    public PageUtil<LnApproveLimitUser> getLimitUserByDeptId(Map<String,Object> map,Page page){
        ArrayList<LnApproveLimitUser> list=(ArrayList<LnApproveLimitUser>) this.findQueryPage("getLimitUserByDeptId", "getLimitUserCountByDeptId",map, page);
        return new PageUtil<LnApproveLimitUser>(list, page);
    }

    /**
     * 根据id修改人员的审批额度  
     */
    public void updateLimitUserById(Map<String,Object> map){
        this.getSqlMapClientTemplate().update("updateLimitUserById",map);
    }

    /**
     * 新建用户审批额度
     */
    public void saveLimitUser(LnApproveLimitUser lnApproveLimitUser){
        this.getSqlMapClientTemplate().insert("saveLimitUser", lnApproveLimitUser);
    }

    /**
     * 根据用户id查询金额设置表里是否包含
     */
    public int getUserSetCountByUserId(Map<String,Object> map){
        return (Integer)this.getSqlMapClientTemplate().queryForObject("getUserSetCountByUserId",map);
    }
}
