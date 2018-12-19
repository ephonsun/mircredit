package com.banger.mobile.facade.loan;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.loan.LnApproveLimitRole;
import com.banger.mobile.domain.model.loan.LnApproveLimitUser;
import com.banger.mobile.domain.model.loan.LnCancelReason;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Zhangfp
 * Date: 13-2-6
 * Time: 上午9:34
 * To change this template use File | Settings | File Templates.
 *
 * 用户审批额度设置service
 */
public interface LnApproveLimitUserService {
    Integer selectCountByUserId(Map<String, Object> paramMap);

    LnApproveLimitUser getLimitUserByUserId(Map<String, Object> paramMap);
    
    /**
     * 根据部门id查询部门下的人员的审批额度
     */
    public PageUtil<LnApproveLimitUser> getLimitUserByDeptId(Map<String,Object> map,Page page);
    
    /**
     * 根据id修改人员的审批额度  
     */
    public void updateLimitUserById(Map<String,Object> map);
    
    /**
     * 新建用户审批额度
     */
    public void saveLimitUser(LnApproveLimitUser lnApproveLimitUser);
    
    /**
     * 根据用户id查询金额设置表里是否包含
     */
    public int getUserSetCountByUserId(int userId);
}
