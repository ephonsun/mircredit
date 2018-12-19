/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :PAD管理dao接口实现
 * Author     :liyb
 * Create Date:2013-6-17
 */
package com.banger.mobile.dao.padManagement.ibatis;

import java.util.*;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.padManagement.PadInfoDao;

import com.banger.mobile.domain.model.base.padManagement.BasePadInfoCopy;
import com.banger.mobile.domain.model.padManagement.PadInfo;
import com.banger.mobile.domain.model.padManagement.PadInfoCopy;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author liyb
 * @version $Id: PadManagementDaoiBatis.java,v 0.1 2013-6-17 下午01:48:39 liyb Exp $
 */
public class PadInfoDaoiBatis extends GenericDaoiBatis implements PadInfoDao {

    public PadInfoDaoiBatis(){
        super(PadInfo.class);
    }

    /**
     * 添加PAD信息
     * @param padInfo
     */
    public void insertPadInfo(PadInfo padInfo) {
        this.getSqlMapClientTemplate().insert("InsertPadInfo",padInfo);
    }
 /**
     * 添加PAD信息
     * @param padInfo
     */
    public void insertPadInfoCopy (Map<String,Object> paramMap) {
        this.getSqlMapClientTemplate().insert("InsertPadInfoCopy",paramMap);
    }

    /**
     * 根据pad编号查询pad信息
     * @param padInfo
     * @return
     */
    public PadInfo getPadInfoByPadCode(PadInfo padInfo) {
        return (PadInfo) this.getSqlMapClientTemplate().queryForObject("GetPadInfoByPadCode",padInfo);
    }

    /**
     * 根据pad序列号查询pad信息
     * @param padInfo
     * @return
     */
    public PadInfo getPadInfoBySerialNumber(PadInfo padInfo) {
        return (PadInfo) this.getSqlMapClientTemplate().queryForObject("GetPadInfoBySerialNumber",padInfo);
    }

    /**
     * 查询pad信息列表分页
     * @param map
     * @param page
     * @return
     */
    public PageUtil<PadInfo> getPadInfoPage(Map<String, Object> map, Page page) {
        List<PadInfo> list=this.findQueryPage("queryPadInfo", "GetPadInfoPageCount", map, page);
        if(list==null){
            list=new ArrayList<PadInfo>();
        }
        return new PageUtil<PadInfo>(list,page);
    }
    @Override
    public PadInfoCopy queryOneSerialNumber(String serialNumber){
        return  (PadInfoCopy) this.getSqlMapClientTemplate().queryForObject("GetPadInfoBySerialNumber", serialNumber);
    }



    @Override
    public PageUtil<PadInfo> getPadInfoPage(Map<String, Object> map) {
        List<PadInfo> list=this.findQueryPage("GetPadInfoPage", "GetPadInfoPageCount", map, null);
        if(list==null){
            list=new ArrayList<PadInfo>();
        }
        return new PageUtil<PadInfo>(list,null);
    }

    @Override
    public List<BasePadInfoCopy> queryAllInoPage(Map<String, Object> map) {
        List<BasePadInfoCopy> list=this.getSqlMapClientTemplate().queryForList("queryPadInfo", map);
        return list ;
    }


    /**
     * 查询pad信息
     * @return
     */
    public PadInfo getPadInfoById(PadInfo padInfo) {
        return (PadInfo) this.getSqlMapClientTemplate().queryForObject("GetPadInfoById",padInfo);
    }

    /**
     * 编辑pad信息
     * @param padInfo
     */
    public void updatePadInfo(PadInfo padInfo) {
        this.getSqlMapClientTemplate().update("UpdatePadInfo",padInfo);
    }
/**
     * 编辑pad信息
     * @param
     */
    public void updatePadInfoCopy (Map<String,Object> paramMap) {

        this.getSqlMapClientTemplate().update("UpdatePadInfoCopy",paramMap);
    }

    /**
     * 启用、禁用、停用PAD
     * @param padInfo
     */
    public void changePadStatus(PadInfo padInfo) {
        this.getSqlMapClientTemplate().update("ChangePadStatus",padInfo);
    }

    /**
     * PAD管理-禁用停用(登录验证)
     * @param account 账号
     * @param serialNumber 序列号
     * @return 1:启用(正常使用) 2:禁用(不允许登陆和连接系统服务端)，直接退出 3:停用(不允许登陆和连接系统服务端)，直接退出
     */
    public PadInfo getPadUseStatus(String account, String serialNumber) {
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("account", account);
        map.put("serialNumber", serialNumber);
        return (PadInfo) this.getSqlMapClientTemplate().queryForObject("GetPadUseStatus", map);
    }
}
