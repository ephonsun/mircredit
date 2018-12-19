/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :Pad管理service接口
 * Author     :liyb
 * Create Date:2013-6-17
 */
package com.banger.mobile.facade.padManagement;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.banger.mobile.domain.model.base.padManagement.BasePadInfoCopy;
import com.banger.mobile.domain.model.padManagement.PadInfoCopy;
import net.sf.json.JSONArray;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.padManagement.PadInfo;

/**
 * @author liyb
 * @version $Id: PadManagementService.java,v 0.1 2013-6-17 下午01:55:03 liyb Exp $
 */
public interface PadInfoService {
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
     * 查询pad所有
     * @param map
     * @param page
     * @return
     */
    public PageUtil<PadInfo> getPadInfoPage(Map<String,Object> map);

    /**
     * 查询一个，按照根据serialNumber
     * @param map
     * @param page
     * @return
     */
    public PadInfoCopy queryOneSrialNumber(String serialNumber);




    public List<BasePadInfoCopy> queryAllInfoPage(Map<String,Object> map);

    /**
     * PAD管理机构用户树
     * @param flag modify:编辑 add:添加
     * @param belongUserId 当前使用人
     * @return
     */
    public JSONArray getPadDeptUserTree(String flag,Integer belongUserId);
    
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
    public void updatePadInfoCopy ( Map<String,Object> paramMap);
    
    /**
     * 启用、禁用、停用PAD
     * @param padInfo
     */
    public void changePadStatus(PadInfo padInfo);
}
