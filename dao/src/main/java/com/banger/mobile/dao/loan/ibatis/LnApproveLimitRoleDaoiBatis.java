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
import com.banger.mobile.dao.loan.LnApproveLimitRoleDao;
import com.banger.mobile.domain.model.loan.LnApproveLimitRole;
import com.banger.mobile.ibatis.GenericDaoiBatis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author QianJie
 * @version $Id: LnOpHistoryDaoiBatis.java,v 0.1 Feb 17, 2013 2:54:46 PM QianJie Exp $
 */
public class LnApproveLimitRoleDaoiBatis extends GenericDaoiBatis implements LnApproveLimitRoleDao {

    public LnApproveLimitRoleDaoiBatis() {
        super(LnApproveLimitRole.class);
    }
    /**
     * @param persistentClass
     */
    public LnApproveLimitRoleDaoiBatis(Class persistentClass) {
        super(LnApproveLimitRole.class);
    }


    /**
     * 查询所有角色的审核额度
     */
    public PageUtil<LnApproveLimitRole> queryRoleSet(Map<String, Object> parameters,Page page) {
        ArrayList<LnApproveLimitRole> list = (ArrayList<LnApproveLimitRole>) this.findQueryPage(
                "getRoleSetPage", "getRoleSetCount", parameters, page);
        if (list == null) {
            list = new ArrayList<LnApproveLimitRole>();
        }
        return new PageUtil<LnApproveLimitRole>(list, page);
    }

    /**
     * 根据roleId判断出当前角色是否存在所能审批的贷款额度
     * @param paramMap
     * @return
     */
    @Override
    public Integer selectCountByRoleId(Map<String,Object> paramMap){
        return (Integer)this.getSqlMapClientTemplate().queryForObject("selectCountByRoleId",paramMap);
    }

    /**
     * 根据roleId查询出当前角色所能审批的贷款额度
     * @param paramMap
     * @return
     */
    @Override
    public List<LnApproveLimitRole> getLimitRoleByRoleId(Map<String,Object> paramMap){
        return this.getSqlMapClientTemplate().queryForList("getLimitRoleByRoleId",paramMap);
    }

    /**
     * 根据id修改角色的审批额度  
     */
    public void updateLimitRoleById(Map<String,Object> map){
        this.getSqlMapClientTemplate().update("updateLimitRoleById",map);
    }

    /**
     * 新建角色审批额度
     */
    public void saveLimitRole(LnApproveLimitRole lnApproveLimitRole){
        this.getSqlMapClientTemplate().insert("saveLimitRole", lnApproveLimitRole);
    }

    /**
     * 根据角色id查询金额设置表里是否包含
     */
    public int getRoleSetCountByRoleId(Map<String,Object> map){
        return (Integer)this.getSqlMapClientTemplate().queryForObject("getRoleSetCountByRoleId",map);
    }
}
