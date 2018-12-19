/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :PAD品牌Action
 * Author     :liyb
 * Create Date:2013-6-17
 */
package com.banger.mobile.webapp.action.padManagement;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.banger.mobile.domain.model.padManagement.PadBrands;
import com.banger.mobile.domain.model.padManagement.PadModel;
import com.banger.mobile.facade.padManagement.PadTypeService;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * @author liyb
 * @version $Id: PadTypeAction.java,v 0.1 2013-6-17 下午03:21:50 liyb Exp $
 */
public class PadTypeAction extends BaseAction {

    private static final long serialVersionUID = 5805855828058269826L;
    private PadTypeService padTypeService;
    private List<PadBrands> brandList;
    private List<PadModel> padModelList;
    private PadBrands brand;
    private PadModel padModel;
    private String oldBrandTypeName;
    private String moveType;
    private Integer padFlag;
    private Integer isActived;
    private Integer isDel;

    /**
     * PAD品牌列表
     * @return
     */
    public String padTypeList(){
        try {
            brandList=padTypeService.getPadBrandsList(null);
            return SUCCESS;
        } catch (Exception e) {
            return ERROR;
        }
    }
    
    /**
     * 添加PAD品牌
     */
    public void addPadBrands(){
        try {
            PrintWriter out = this.getResponse().getWriter();
            PadBrands pad=padTypeService.getPadBrandsByName(brand.getBrandTypeName().trim());
            if(pad!=null){
                out.write("exists");
            }else{
                brand.setCreateUser(this.getLoginInfo().getUserId());
                padTypeService.insertPadBrands(brand);
                out.write("SUCCESS");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("addPadBrands action error:", e);
        }
    }
    
    /**
     * 跳转编辑PAD品牌页面
     */
    public String toUpdatePadBrands(){
        try {
            brand=padTypeService.getPadBrandsById(brand.getBrandTypeId());
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("toUpdatePadBrands action error:", e);
            return ERROR;
        }
    }
    
    /**
     * 编辑PAD品牌
     */
    public void updatePadBrands(){
        try {
            PrintWriter out = this.getResponse().getWriter();
            if(!oldBrandTypeName.equals(brand.getBrandTypeName().trim())){
                PadBrands pad=padTypeService.getPadBrandsByName(brand.getBrandTypeName().trim());
                if(pad==null){
                    brand.setUpdateUser(this.getLoginInfo().getUserId());
                    padTypeService.updatePadBrands(brand);
                    out.print("SUCCESS");
                }else out.print("exists");
            }else{
                out.print("SUCCESS");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("updatePadBrands action error:", e);
        }
    }
    
    /**
     * 删除PAD品牌
     */
    public void deletePadBrands(){
        try {
            PrintWriter out = this.getResponse().getWriter();
            padTypeService.deletePadBrands(brand.getBrandTypeId());
            out.print("SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("deletePadBrands action error:", e);
        }
    }
    
    /**
     * 上移、下移PAD品牌
     */
    public void movePadBrands(){
        try {
            PrintWriter out = this.getResponse().getWriter();
            padTypeService.movePadBrandsItems(brand.getBrandTypeId(), moveType);
            out.print("SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("movePadBrands action error:", e);
        }
    }
    
    /**
     * PAD型号列表
     * @return
     */
    public String padModelList(){
        try {
            brandList=padTypeService.getPadBrandsList(null);
            if(brandList.size()>0){
                padModelList=padTypeService.getPadModelList(brandList.get(0).getBrandTypeId(),isActived,isDel);
            }
            return SUCCESS;
        } catch (Exception e) {
            log.error("padModelList action error:", e);
            return ERROR;
        }
    }
    
    public String changePadModel(){
        try {
            padModelList=padTypeService.getPadModelList(brand.getBrandTypeId(),isActived,isDel);
            return SUCCESS;
        } catch (Exception e) {
            log.error("changePadModel action error:", e);
            return ERROR;
        }
    }
    
    /**
     * 添加PAD型号
     */
    public void addPadModel(){
        try {
            PrintWriter out = this.getResponse().getWriter();
            PadModel model=padTypeService.getPadModelById(padModel);
            if(model!=null){
                out.write("exists");
            }else{
                padModel.setCreateUser(this.getLoginInfo().getUserId());
                padTypeService.insertPadModel(padModel);
                out.write("SUCCESS");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("addPadModel action error:", e);
        }
    }
    
    /**
     * 跳转修改PAD型号页面
     * @return
     */
    public String toUpdatePadModel(){
        try {
            padModel=padTypeService.getPadModelById(padModel);
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("toUpdatePadModel action error:", e);
            return ERROR;
        }
    }
    
    /**
     * 编辑PAD型号
     */
    public void updatePadModel(){
        try {
            PrintWriter out = this.getResponse().getWriter();
            if(!oldBrandTypeName.equals(padModel.getPadSubTypeName().trim())){
                PadModel mode=new PadModel();
                mode.setPadTypeId(padModel.getPadTypeId());
                mode.setPadSubTypeName(padModel.getPadSubTypeName().trim());
                mode=padTypeService.getPadModelById(mode);
                if(mode==null){
                    padModel.setUpdateUser(this.getLoginInfo().getUserId());
                    padTypeService.updatePadModel(padModel);
                    out.print("SUCCESS");
                }else out.print("exists");
            }else{
                out.print("SUCCESS");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("updatePadModel action error:", e);
        }
    }
    
    /**
     * 删除Pad型号
     */
    public void deletePadModel(){
        try {
            padModel.setUpdateUser(this.getLoginInfo().getUserId());
            padModel.setIsDel(1);
            padTypeService.updatePadModel(padModel);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("deletePadModel action error:", e);
        }
    }
    
    /**
     * 上移、下移PAD型号
     */
    public void movePadModel(){
        try {
            padTypeService.movePadModelItems(padModel.getPadTypeId(), padModel.getPadSubTypeId(), moveType);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("movePadModel action error:", e);
        }
    }
    
    /**
     * 返回pad型号的JSON数据
     */
    public void getPadModelJson(){
        try {
            this.getResponse().setContentType("text/html;charset=utf-8");
            PrintWriter out = this.getResponse().getWriter();
            Map<String, Object> map = new HashMap<String, Object>();
            JSONArray json = new JSONArray();
            List<PadModel> list=padTypeService.getPadModelList(padModel.getPadTypeId(),isActived,isDel);
            for (PadModel m:list) {
                map.put("code", m.getPadSubTypeId());
                map.put("name", m.getPadSubTypeName());
                json.add(map);
            }
            out.print(json.toString());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("getPadModelJson action error:", e);
        }
    }
    
    /**
     * 启用、停用
     * padFlag 1：PAD品牌  2：PAD型号
     */
    public void isActivedPad(){
        try {
            if(padFlag==1){
                padTypeService.enableOrDisPadBrands(brand.getBrandTypeId(), brand.getIsActived());
            }else if(padFlag==2){
                padTypeService.enableOrDisPadModel(padModel.getPadSubTypeId(), padModel.getIsActived());
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("isActivedPadBrand action error:", e);
        }
    }

    public List<PadBrands> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<PadBrands> brandList) {
        this.brandList = brandList;
    }

    public void setPadTypeService(PadTypeService padTypeService) {
        this.padTypeService = padTypeService;
    }

    public PadBrands getBrand() {
        return brand;
    }

    public void setBrand(PadBrands brand) {
        this.brand = brand;
    }

    public String getOldBrandTypeName() {
        return oldBrandTypeName;
    }

    public void setOldBrandTypeName(String oldBrandTypeName) {
        this.oldBrandTypeName = oldBrandTypeName;
    }

    public String getMoveType() {
        return moveType;
    }

    public void setMoveType(String moveType) {
        this.moveType = moveType;
    }

    public PadModel getPadModel() {
        return padModel;
    }

    public void setPadModel(PadModel padModel) {
        this.padModel = padModel;
    }

    public void setPadModelList(List<PadModel> padModelList) {
        this.padModelList = padModelList;
    }

    public List<PadModel> getPadModelList() {
        return padModelList;
    }

    public Integer getPadFlag() {
        return padFlag;
    }

    public void setPadFlag(Integer padFlag) {
        this.padFlag = padFlag;
    }

    public Integer getIsActived() {
        return isActived;
    }

    public void setIsActived(Integer isActived) {
        this.isActived = isActived;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
    
}
