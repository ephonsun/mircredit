/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :PAD管理dao接口
 * Author     :liyb
 * Create Date:2013-6-17
 */
package com.banger.mobile.dao.padManagement;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;

import com.banger.mobile.domain.model.base.padManagement.BasePadInfoCopy;
import com.banger.mobile.domain.model.padManagement.PadInfo;
import com.banger.mobile.domain.model.padManagement.PadInfoCopy;

/**
 * @author liyb
 * @version $Id: PadManagementDao.java,v 0.1 2013-6-17 下午01:46:51 liyb Exp $
 */
public interface PadInfoDao {

    /**
     * 添加PAD信息
     * @param padInfo
     */
    public void insertPadInfo(PadInfo padInfo);
    /**
     * 添加PAD信息
     * @param padInfo
     */
    public void insertPadInfoCopy (Map<String,Object> paramMap);

    /**
     * 根据pad编号查询pad信息
     * @param padInfo
     * @return
     */
    public PadInfo getPadInfoByPadCode(PadInfo padInfo);
    
    /**
     * 根据pad序列号查询pad信息
     * @param padInfo
     * @return
     */
    public PadInfo getPadInfoBySerialNumber(PadInfo padInfo);
    
    /**
     * 查询pad信息列表分页
     * @param map
     * @param page
     * @return
     */
    public PageUtil<PadInfo> getPadInfoPage(Map<String,Object> map, Page page);

    /**
     * 查询pad信息列表分页
     * @param map
     * @param page
     * @return
     */
    public PageUtil<PadInfo> getPadInfoPage(Map<String,Object> map);


   public PadInfoCopy queryOneSerialNumber(String serialNumber);

    public List<BasePadInfoCopy> queryAllInoPage(Map<String,Object> map);

    /**
     * 查询pad信息
     * @return
     */
    public PadInfo getPadInfoById(PadInfo padInfo);
    
    /**
     * 编辑pad信息
     * @param padInfo
     */
    public void updatePadInfo(PadInfo padInfo);
    /**
     * 修改信息
     * @param padInfo
     */
    public void updatePadInfoCopy (Map<String,Object> paramMap );
    
    /**
     * 启用、禁用、停用PAD
     * @param padInfo
     */
    public void changePadStatus(PadInfo padInfo);
    
    /**
     * PAD管理-禁用停用(登录验证)
     * @param account 账号
     * @param serialNumber 序列号
     * @return 1:启用(正常使用) 2:禁用(不允许登陆和连接系统服务端)，直接退出 3:停用(不允许登陆和连接系统服务端)，直接退出
     */
    public PadInfo  getPadUseStatus(String account, String serialNumber);
    
}
