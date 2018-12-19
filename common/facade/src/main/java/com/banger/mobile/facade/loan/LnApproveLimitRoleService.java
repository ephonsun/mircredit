package com.banger.mobile.facade.loan;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.loan.LnApproveLimitRole;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Zhangfp
 * Date: 13-2-6
 * Time: 上午9:34
 * To change this template use File | Settings | File Templates.
 *
 * 角色审批额度设置service
 */
public interface LnApproveLimitRoleService {

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
    public int getRoleSetCountByRoleId(int roleId);
}
