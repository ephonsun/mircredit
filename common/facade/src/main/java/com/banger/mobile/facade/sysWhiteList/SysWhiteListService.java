/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :通话白名单
 * Author     :yujh
 * Create Date:Aug 22, 2012
 */
package com.banger.mobile.facade.sysWhiteList;

import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.customer.CounterOutPintMessage;
import com.banger.mobile.domain.model.sysWhiteList.SysWhiteList;

/**
 * @author yujh
 * @version $Id: SysWhiteListService.java,v 0.1 Aug 22, 2012 2:52:29 PM Administrator Exp $
 */
public interface SysWhiteListService {
	/**
	 * 添加
	 * @param whiteList
	 */
    public void insertSysWhiteList(SysWhiteList whiteList);
    /**
     * 删除
     * @param id
     */
    public void deleteSysWhiteList(int id);
    /**
     * 根据id查询
     * @param id
     * @return
     */
    public SysWhiteList getWhiteListById(int id);
    /**
     * 更新
     * @param whiteList
     */
    public void updateSysWhiteList(SysWhiteList whiteList);
    /**
     * 分页
     * @param map
     * @param page
     * @return
     */
    public PageUtil<SysWhiteList> getWhiteListPage(Map<String, Object> map, Page page);
    /**
     * 导入结果信息
     * @param fileName
     * @return
     */
    public CounterOutPintMessage excelToDb(String fileName);

    
    /**
     * 根据电话号码查询是否是白名单
     * @param phoneNo
     * @return
     */
    public boolean queryByPhoneNo(String phoneNo);

    /**
     * 验证待导入的电话是否已存在
     * @param String phone
     * @return bool
     */
    public boolean isExitByphone(String phone);
    
    /**
     * 验证待导入的电话是否已存在  (修改 排除自己)
     * @param String phone
     * @return bool
     */
    public boolean isExitByphone(String phone,String id);
}
