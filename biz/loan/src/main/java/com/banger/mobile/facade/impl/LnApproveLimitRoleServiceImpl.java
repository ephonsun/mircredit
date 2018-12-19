package com.banger.mobile.facade.impl;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.loan.LnApproveLimitRoleDao;
import com.banger.mobile.domain.model.loan.LnApproveLimitRole;
import com.banger.mobile.facade.loan.LnApproveLimitRoleService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Zhangfp
 * Date: 13-2-6
 * Time: 上午10:18
 * To change this template use File | Settings | File Templates.
 *
 * 角色审批额度设置service
 */
public class LnApproveLimitRoleServiceImpl implements LnApproveLimitRoleService {

    private LnApproveLimitRoleDao lnApproveLimitRoleDao;

    public LnApproveLimitRoleDao getLnApproveLimitRoleDao() {
        return lnApproveLimitRoleDao;
    }

    public void setLnApproveLimitRoleDao(LnApproveLimitRoleDao lnApproveLimitRoleDao) {
        this.lnApproveLimitRoleDao = lnApproveLimitRoleDao;
    }
    /**
     * 查询所有角色的审核额度
     */
    public PageUtil<LnApproveLimitRole> queryRoleSet(Map<String, Object> parameters,Page page) {
        return lnApproveLimitRoleDao.queryRoleSet(parameters,page);
    }
    
    /**
     * 根据roleId判断出当前角色是否存在所能审批的贷款额度
     * @param paramMap
     * @return
     */
    public Integer selectCountByRoleId(Map<String, Object> paramMap) {
        return lnApproveLimitRoleDao.selectCountByRoleId(paramMap);
    }

    /**
     * 根据roleId查询出当前角色所能审批的贷款额度
     * @param paramMap
     * @return
     */
    public List<LnApproveLimitRole> getLimitRoleByRoleId(Map<String, Object> paramMap) {
        return lnApproveLimitRoleDao.getLimitRoleByRoleId(paramMap);
    }
    
    /**
     * 根据id修改角色的审批额度  
     */
    public void updateLimitRoleById(Map<String,Object> map){
    	this.lnApproveLimitRoleDao.updateLimitRoleById(map);
    }
    
    /**
     * 新建角色审批额度
     */
    public void saveLimitRole(LnApproveLimitRole lnApproveLimitRole){
    	this.lnApproveLimitRoleDao.saveLimitRole(lnApproveLimitRole);
    }
    
    /**
     * 根据角色id查询金额设置表里是否包含
     */
    public int getRoleSetCountByRoleId(int roleId){
    	Map<String,Object> map = new HashMap<String, Object>();
    	map.put("roleId", roleId);
    	return this.lnApproveLimitRoleDao.getRoleSetCountByRoleId(map);
    }
}
