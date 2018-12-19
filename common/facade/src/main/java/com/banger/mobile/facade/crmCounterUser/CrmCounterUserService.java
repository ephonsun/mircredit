/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :柜台人员
 * Author     :yujh
 * Create Date:Sep 4, 2012
 */
package com.banger.mobile.facade.crmCounterUser;

import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.crmCounterUser.CrmCounterUser;

/**
 * @author yujh
 * @version $Id: CrmCounterUserService.java,v 0.1 Sep 4, 2012 1:25:34 PM Administrator Exp $
 */
public interface CrmCounterUserService {
	/**
	 * 新增柜台人员
	 * @param user
	 */
    public void insertCounterUser(CrmCounterUser user);

    /**
     * 删除柜台人员
     * @param id
     */
    public void deleteCounterUser(int id);

    /**
     * 根据ID查询柜台人员信息
     * @param id
     * @return
     */
    public CrmCounterUser queryById(int id);

    /**
     * 修改柜台人员信息
     * @param user
     */
    public void updateCounterUser(CrmCounterUser user);
    
    /**
     * 分页获得所有柜台人员列表
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<CrmCounterUser> getAllCrmCounterUser(Map<String, Object> parameters, Page page);
    
    /**
     * 校验柜台人员
     * @param crmCounterUser
     * @return
     */
    public List<CrmCounterUser> validateCrmCounterUser(CrmCounterUser crmCounterUser);
    /**
     * 当前用户管辖的柜台人员用户id集合
     * @param roleId
     * @param userId
     * @return
     */
    public Integer[] getAllCrmCounterIds(int roleId,int userId);
    
    /**
     * 根据部门id查询包含删除的柜台人员
     */
    public String getAllCrmCounterUser(String ids) ;
    
    /**
     * 根据userId查询所有柜台人员
     * @param userId：用户id  isDel：是否删除（false:删除,true:所有）
     * @return
     */
    public List<CrmCounterUser> getCounterUserByUserId(int userId,boolean flag);
}
