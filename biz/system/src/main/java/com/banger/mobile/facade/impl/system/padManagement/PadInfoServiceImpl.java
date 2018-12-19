/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :PAD管理service接口实现
 * Author     :liyb
 * Create Date:2013-6-17
 */
package com.banger.mobile.facade.impl.system.padManagement;

import java.util.*;

import com.banger.mobile.domain.model.base.padManagement.BasePadInfoCopy;
import com.banger.mobile.domain.model.padManagement.PadInfoCopy;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.padManagement.PadInfoDao;
import com.banger.mobile.domain.model.dept.CusBelongToBean;
import com.banger.mobile.domain.model.padManagement.PadInfo;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.padManagement.PadInfoService;

/**
 * @author liyb
 * @version $Id: PadManagementServiceImpl.java,v 0.1 2013-6-17 下午01:56:51 liyb Exp $
 */
public class PadInfoServiceImpl implements PadInfoService {

    private PadInfoDao padInfoDao;
    private DeptFacadeService deptFacadeService;

    public void setPadInfoDao(PadInfoDao padInfoDao) {
        this.padInfoDao = padInfoDao;
    }

    public void setDeptFacadeService(DeptFacadeService deptFacadeService) {
        this.deptFacadeService = deptFacadeService;
    }

    /**
     * 添加PAD信息
     * @param padInfo
     */
    public void insertPadInfo(PadInfo padInfo) {
        padInfoDao.insertPadInfo(padInfo);
    }
 /**
     * 添加PAD信息
     * @param padInfo
     */
    public void insertPadInfoCopy (Map<String,Object> paramMap) {
        padInfoDao.insertPadInfoCopy(paramMap);
    }

    /**
     * 根据pad编号查询pad信息
     * @param padInfo
     * @return
     */
    public PadInfo getPadInfoByPadCode(PadInfo padInfo) {
        return padInfoDao.getPadInfoByPadCode(padInfo);
    }

    /**
     * 根据pad序列号查询pad信息
     * @param padInfo
     * @return
     */
    public PadInfo getPadInfoBySerialNumber(PadInfo padInfo) {
        return padInfoDao.getPadInfoBySerialNumber(padInfo);
    }

    /**
     * 查询pad信息列表分页
     * @param map
     * @param page
     * @return
     */
    public PageUtil<PadInfo> getPadInfoPage(Map<String, Object> map, Page page) {
        return padInfoDao.getPadInfoPage(map, page);
    }

  public PageUtil<PadInfo> getPadInfoPage(Map<String, Object> map) {
        return padInfoDao.getPadInfoPage(map);
    }

    @Override
    public List<BasePadInfoCopy> queryAllInfoPage(Map<String, Object> map) {
        List<BasePadInfoCopy> list=padInfoDao.queryAllInoPage(map) ;
        return list;
    }



    public PadInfoCopy queryOneSrialNumber(String serialNumber){


        return padInfoDao.queryOneSerialNumber(serialNumber);
    }



    /**
     * PAD管理机构用户树
     * @param flag modify:编辑 add:添加
     * @param belongUserId 当前使用人
     * @return
     */
    public JSONArray getPadDeptUserTree(String flag,Integer belongUserId) {
        JSONArray array=new JSONArray();
        List<CusBelongToBean> deptlist=deptFacadeService.getAdminToUserAndDeptTreeList();
        Set<Integer> deptSet = new HashSet<Integer>();
        int ii = 0;
        for (CusBelongToBean dept : deptlist) {
            deptSet.add(dept.getId());
        }
        for(CusBelongToBean dept : deptlist){
            if(!deptSet.contains(dept.getParentId())){
                ii++;
            }
        }
        for(CusBelongToBean dept : deptlist){
            JSONObject obj = new JSONObject();
            obj.put("id", dept.getId());
            if(!deptSet.contains(dept.getParentId())){
                obj.put("pId", 0);
                if(ii==1){
                    obj.put("open", true);
                }
            }else{
                obj.put("pId", dept.getParentId());
            }
            obj.put("name", dept.getTextName());
            obj.put("flag", dept.getType());
            obj.put("deptName", dept.getDeptName());
            if(dept.getType().equals("D")){
                obj.put("icon", "../images/deptTreeImage/1.png");
            }else{
                obj.put("icon", "../images/deptTreeImage/2.png");
            } 
            if((flag.equals("modify")) && (dept.getId().equals(belongUserId))
                        && (dept.getType().equals("U"))){
                    obj.put("selected", true);
            }
            array.add(obj);
        }
        return array;
    }

    /**
     * 查询pad信息
     * @return
     */
    public PadInfo getPadInfoById(PadInfo padInfo) {
        return padInfoDao.getPadInfoById(padInfo);
    }

    /**
     * 编辑pad信息
     * @param padInfo
     */
    public void updatePadInfo(PadInfo padInfo) {
        padInfoDao.updatePadInfo(padInfo);
    }
 /**
     * 编辑pad信息
     * @param padInfo
     */
 public void updatePadInfoCopy ( Map<String,Object> paramMap) {
        padInfoDao.updatePadInfoCopy(paramMap);
    }

    /**
     * 启用、禁用、停用PAD
     * @param padInfo
     */
    public void changePadStatus(PadInfo padInfo) {
        padInfoDao.changePadStatus(padInfo);
    }

    
    
}
