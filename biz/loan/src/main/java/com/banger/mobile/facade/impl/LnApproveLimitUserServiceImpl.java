package com.banger.mobile.facade.impl;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.loan.LnApproveLimitUserDao;
import com.banger.mobile.domain.model.loan.LnApproveLimitUser;
import com.banger.mobile.facade.loan.LnApproveLimitUserService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Zhangfp
 * Date: 13-2-6
 * Time: 上午10:18
 * To change this template use File | Settings | File Templates.
 *
 * 用户审批额度设置service
 */
public class LnApproveLimitUserServiceImpl implements LnApproveLimitUserService {
    private LnApproveLimitUserDao lnApproveLimitUserDao;

    public LnApproveLimitUserDao getLnApproveLimitUserDao() {
        return lnApproveLimitUserDao;
    }

    public void setLnApproveLimitUserDao(LnApproveLimitUserDao lnApproveLimitUserDao) {
        this.lnApproveLimitUserDao = lnApproveLimitUserDao;
    }
    
    /**
     * 根据userId判断当前用户是否存在所能审批的最大金额额度
     * @param paramMap
     * @return
     */
    public Integer selectCountByUserId(Map<String, Object> paramMap) {
        return lnApproveLimitUserDao.selectCountByUserId(paramMap);
    }

    /**
     * 根据userId查询出当前用户所能审批的最大金额额度
     * @param paramMap
     * @return
     */
    public LnApproveLimitUser getLimitUserByUserId(Map<String, Object> paramMap) {
        return lnApproveLimitUserDao.getLimitUserByUserId(paramMap);
    }
    
    /**
     * 根据部门id查询部门下的人员的审批额度
     */
    public PageUtil<LnApproveLimitUser> getLimitUserByDeptId(Map<String,Object> map,Page page){
    	return lnApproveLimitUserDao.getLimitUserByDeptId(map, page);
    }
    
    /**
     * 根据id修改人员的审批额度  
     */
    public void updateLimitUserById(Map<String,Object> map){
    	this.lnApproveLimitUserDao.updateLimitUserById(map);
    }
    
    /**
     * 新建用户审批额度
     */
    public void saveLimitUser(LnApproveLimitUser lnApproveLimitUser){
    	this.lnApproveLimitUserDao.saveLimitUser(lnApproveLimitUser);
    }
    
    /**
     * 根据用户id查询金额设置表里是否包含
     */
    public int getUserSetCountByUserId(int userId){
    	Map<String,Object> map = new HashMap<String, Object>();
    	map.put("userId", userId);
    	return this.lnApproveLimitUserDao.getUserSetCountByUserId(map);
	}
}
