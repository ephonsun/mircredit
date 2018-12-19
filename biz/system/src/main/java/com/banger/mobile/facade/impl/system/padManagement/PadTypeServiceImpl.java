/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :PAD品牌型号service接口实现
 * Author     :liyb
 * Create Date:2013-6-18
 */
package com.banger.mobile.facade.impl.system.padManagement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.banger.mobile.dao.padManagement.PadTypeDao;
import com.banger.mobile.domain.model.padManagement.PadBrands;
import com.banger.mobile.domain.model.padManagement.PadModel;
import com.banger.mobile.domain.model.user.IUserInfo;
import com.banger.mobile.facade.padManagement.PadTypeService;

/**
 * @author liyb
 * @version $Id: PadTypeServiceImpl.java,v 0.1 2013-6-18 下午12:49:33 liyb Exp $
 */
public class PadTypeServiceImpl implements PadTypeService {
    private PadTypeDao padTypeDao;

    public void setPadTypeDao(PadTypeDao padTypeDao) {
        this.padTypeDao = padTypeDao;
    }

    /**
     * 删除Pad品牌
     * @param brandTypeId
     */
    public void deletePadBrands(Integer brandTypeId) {
        padTypeDao.deletePadBrands(brandTypeId);
    }

    /**
     * 查询所有的PAD品牌
     * @return
     */
    public List<PadBrands> getPadBrandsList(Map<String,Object> map) {
        return padTypeDao.getPadBrandsList(map);
    }

    /**
     * 添加Pad品牌
     * @param brand
     */
    public void insertPadBrands(PadBrands brand) {
        padTypeDao.insertPadBrands(brand);
    }

    /**
     * 编辑Pad品牌
     * @param brand
     */
    public void updatePadBrands(PadBrands brand) {
        padTypeDao.updatePadBrands(brand);
    }

    /**
     * 根据PAD品牌ID查询品牌信息
     * @param brandTypeId
     * @return
     */
    public PadBrands getPadBrandsById(Integer brandTypeId) {
        return padTypeDao.getPadBrandsById(brandTypeId);
    }

    /**
     * 根据PAD品牌名称查询品牌信息
     * @param brandName
     * @return
     */
    public PadBrands getPadBrandsByName(String brandName) {
        return padTypeDao.getPadBrandsByName(brandName);
    }

    /**
     * 上移、下移PAD品牌
     * @param brandTypeId
     * @param moveType
     */
    public void movePadBrandsItems(Integer brandTypeId, String moveType) {
        int move = 1;
        if (moveType.equals("Up")) {
            move = -1;
        }
        Map<String,Object> map=new HashMap<String, Object>();
        List<PadBrands> list=padTypeDao.getPadBrandsList(map);
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getBrandTypeId().equals(brandTypeId)){
                PadBrands upIndex=new PadBrands();
                upIndex=list.get(i+move);
                PadBrands nowIndex=new PadBrands();
                nowIndex=list.get(i);
                int indexSort = upIndex.getSortNo();
                int userId = getUserLoginInfo().getUserId();
                upIndex.setSortNo(nowIndex.getSortNo());
                upIndex.setUpdateUser(userId);
                nowIndex.setSortNo(indexSort);
                nowIndex.setUpdateUser(userId);
                list.clear();
                list.add(upIndex);
                list.add(nowIndex);
                padTypeDao.movePadBrandsItems(list);
            }
        }
    }
    /**
     * 获取登录信息
     * @return
     */
    private IUserInfo getUserLoginInfo() {
        HttpServletRequest res = org.apache.struts2.ServletActionContext.getRequest();
        HttpSession session = res.getSession();
        return (IUserInfo) session.getAttribute("LoginInfo");
    }

    /**
     * 删除PAD型号
     * @param padModel
     */
    public void deletePadModel(PadModel padModel) {
        padTypeDao.deletePadModel(padModel);
    }

    /**
     * 查询PAD某个品牌型号
     * @param brandTypeId
     * @param isActived
     * @param isDel
     * @return
     */
    public List<PadModel> getPadModelList(Integer brandTypeId,Integer isActived,Integer isDel) {
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("padTypeId", brandTypeId);
        map.put("isActived", isActived);
        map.put("isDel", isDel);
        return padTypeDao.getPadModelList(map);
    }

    /**
     * 添加PAD型号
     * @param padModel
     */
    public void insertPadModel(PadModel padModel) {
        padTypeDao.insertPadModel(padModel);
    }

    /**
     * 根据条件查询PAD型号信息
     * @param padModelId
     * @return
     */
    public PadModel getPadModelById(PadModel padModel) {
        return padTypeDao.getPadModelById(padModel);
    }

    /**
     * 编辑PAD型号
     * @param padModel
     */
    public void updatePadModel(PadModel padModel) {
        padTypeDao.updatePadModel(padModel);
    }

    /**
     * 上移、下移PAD型号
     * @param brandTypeId
     * @param padSubTypeId
     * @param moveType
     */
    public void movePadModelItems(Integer brandTypeId, Integer padSubTypeId, String moveType) {
        int move = 1;
        if (moveType.equals("Up")) {
            move = -1;
        }
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("padTypeId", brandTypeId);
        List<PadModel> list=padTypeDao.getPadModelList(map);
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getPadSubTypeId().equals(padSubTypeId)){
                PadModel upIndex=new PadModel();
                upIndex=list.get(i+move);
                PadModel nowIndex=new PadModel();
                nowIndex=list.get(i);
                int indexSort = upIndex.getSortNo();
                int userId = getUserLoginInfo().getUserId();
                upIndex.setSortNo(nowIndex.getSortNo());
                upIndex.setUpdateUser(userId);
                nowIndex.setSortNo(indexSort);
                nowIndex.setUpdateUser(userId);
                list.clear();
                list.add(upIndex);
                list.add(nowIndex);
                padTypeDao.movePadModelItems(list);
            }
        }
    }

    /**
     * 启用、停用Pad
     * @param brandTypeId pad品牌ID
     * @param isActived 1:启用 0:停用
     */
    public void enableOrDisPadBrands(Integer brandTypeId, Integer isActived) {
        PadBrands brand=new PadBrands();
        int userId = getUserLoginInfo().getUserId();
        brand.setUpdateUser(userId);
        brand.setIsActived(isActived);
        brand.setBrandTypeId(brandTypeId);
        padTypeDao.updatePadBrands(brand);
    }

    /**
     * 启用、停用Pad型号
     * @param padSubTypeId pad型号ID
     * @param isActived 1:启用 0:停用
     */
    public void enableOrDisPadModel(Integer padSubTypeId, Integer isActived) {
        PadModel padModel=new PadModel();
        int userId = getUserLoginInfo().getUserId();
        padModel.setUpdateUser(userId);
        padModel.setIsActived(isActived);
        padModel.setPadSubTypeId(padSubTypeId);
        padTypeDao.updatePadModel(padModel);
    }

}
