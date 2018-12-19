/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :通话白名单
 * Author     :yujh
 * Create Date:Aug 22, 2012
 */
package com.banger.mobile.dao.sysWhiteList;

import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.sysWhiteList.SysWhiteList;

/**
 * @author yujh
 * @version $Id: SysWhiteListDao.java,v 0.1 Aug 22, 2012 1:27:28 PM Administrator Exp $
 */
public interface SysWhiteListDao {
	/**
	 * 添加
	 * @param whiteList
	 */
    public void insertSysWhiteList(SysWhiteList whiteList);
    
    /**
     * 批量添加
     * @param List
     */
    public void insertSysWhiteListBatch(List<SysWhiteList> List);
    
    /**
     * 批量更新
     * @param List
     */
    public void updateSysWhiteListBatch(List<SysWhiteList> List);
    
    /**
     * 删除
     * @param id
     */
    public void deleteSysWhiteList(Map<String, Object> map);
    /**
     * id查询
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
     * 导入查询
     * @param sl
     * @return
     */
    public List<SysWhiteList> getWhiteListByObj(Map<String, Object> map);
    /**
     * 导入更新
     * @param sl
     */
    public void updateByImport(SysWhiteList sl);
    /**
     * 根据电话号码查询
     * @param phoneNo
     * @return
     */
    public List<SysWhiteList> queryByPhoneNo(String phoneNo);
    /**
     * 得到所有通话白名单
     * @return
     */
    public Map<String,SysWhiteList> getAllPhones();
}
