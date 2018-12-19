package com.banger.mobile.dao.loan;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.loan.LnApproveLimitRole;
import com.banger.mobile.domain.model.loan.LnCancelReason;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-2-6
 * Time: 上午9:19
 * To change this template use File | Settings | File Templates.
 */
public interface LnApproveLimitRoleDao {

	/**
     * 查询所有角色的审核额度
     */
    public PageUtil<LnApproveLimitRole> queryRoleSet(Map<String, Object> parameters,Page page);
    

    Integer selectCountByRoleId(Map<String, Object> paramMap);

    List<LnApproveLimitRole> getLimitRoleByRoleId(Map<String, Object> paramMap);
    
    /**
     * 根据id修改角色的审批额度  
     */
    public void updateLimitRoleById(Map<String,Object> map);
    
    /**
     * 新建角色审批额度
     */
    public void saveLimitRole(LnApproveLimitRole lnApproveLimitRole);
    
    /**
     * 根据角色id查询金额设置表里是否包含
     */
    public int getRoleSetCountByRoleId(Map<String,Object> map);
}
