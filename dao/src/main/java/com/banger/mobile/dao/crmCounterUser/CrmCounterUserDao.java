/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :柜台人员
 * Author     :yujh
 * Create Date:Sep 4, 2012
 */
package com.banger.mobile.dao.crmCounterUser;

import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.crmCounterUser.CrmCounterUser;

/**
 * @author yujh
 * @version $Id: CrmCounterUserDao.java,v 0.1 Sep 4, 2012 11:39:54 AM Administrator Exp $
 */
public interface CrmCounterUserDao {
    /**
     * 插入
     * @param user
     */
    public void insertCounterUser(CrmCounterUser user);

    /**
     * 删除
     * @param id
     */
    public void deleteCounterUser(int id);

    /**
     * id查询对象
     * @param id
     * @return
     */
    public CrmCounterUser queryById(int id);

    /**
     * 更新
     * @param user
     */
    public void updateCounterUser(CrmCounterUser user);

    /**
     * 分页
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<CrmCounterUser> getAllCrmCounterUser(Map<String, Object> parameters, Page page);
    public List<CrmCounterUser> getAllCrmCounterUserForList(String ids);

    /**
     * 验证是否已存在
     * @param crmCounterUser
     * @return
     */
    public List<CrmCounterUser> validateCrmCounterUser(CrmCounterUser crmCounterUser);
    /**
     * 获取所有柜台人员
     * @return
     */
    public List<CrmCounterUser> getAllCrmCounterUser(String ids);

    /**
     * 根据userId查询所有柜台人员
     * @param userId：用户id  isDel：是否删除（false:删除,true:所有）
     * @return
     */
    public List<CrmCounterUser> getCounterUserByUserId(int userId,boolean flag) ;
}
