/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :产品类型
 * Author     :yujh
 * Create Date:Aug 15, 2012
 */
package com.banger.mobile.webapp.action.system;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.banger.mobile.domain.model.system.ProductType;
import com.banger.mobile.facade.system.ProductTypeService;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * @author yujh
 * @version $Id: ProductTypeAction.java,v 0.1 Aug 15, 2012 7:14:59 PM Administrator Exp $
 */
public class ProductTypeAction extends BaseAction{

    private static final long serialVersionUID = -883939632865970563L;
    private ProductTypeService  productTypeService;
    private List<ProductType>   productTypeList;
    private int                 id;
    private String              moveType;
    private String              productTypeName;
    
    /**
     * 列表
     * @return
     */
    public String getAllProductType(){
        try {
            productTypeList =this.productTypeService.getAllProductType();
            return SUCCESS;
        } catch (RuntimeException e) {
            log.error("getAllProductType action error",e);
            return ERROR;
        }
    }
    /**
     * 添加
     */
    public void addProductType(){
        try {
            ProductType productType= new ProductType();
            productType.setIsDel(0);
            productType.setSortNo(0);
            productType.setProductTypeName(productTypeName);
            List<ProductType> list =this.productTypeService.getProductTypeBySameName(productType);
            String result="";
            if(list.size()>0){
                result="已存在相同的选项";
            }else{
                this.productTypeService.addProductType(productType);
                result=SUCCESS;
            }
            PrintWriter out = this.getResponse().getWriter();
            out.write(result);
        } catch (IOException e) {
            log.error("addProductType action error",e);
        }
        
    }
    /**
     * 删除
     * @return
     */
    public String deleteProductType(){
        try {
            this.productTypeService.deleteProductType(id);
            return SUCCESS;
        } catch (RuntimeException e) {
            log.error("deleteProductType action error",e);
            return ERROR;
        }
    }
    public String gotoUpdateProductTypePage(){
        try {
            ProductType productType = this.productTypeService.getProductTypeById(id);
            this.setProductTypeName(productType.getProductTypeName());
            return SUCCESS;
        } catch (RuntimeException e) {
            log.error("gotoUpdateProductTypePage ERROR",e);
            return ERROR;
        }
    }
    /**
     * 更新
     */
    public void updateProductType(){
        try {
            ProductType productType =this.productTypeService.getProductTypeById(id);
            productType.setProductTypeName(productTypeName);
            String result="";
            List<ProductType> list = this.productTypeService.getProductTypeBySameName(productType);
            if(list.size()>0){
                result="该名称已存在";
            }else{
                this.productTypeService.updateProductType(productType);
                result=SUCCESS;
            }
            PrintWriter out = this.getResponse().getWriter();
            out.write(result);
        } catch (IOException e) {
            log.error("updateProductType ERROR",e);
        }
    }
    /**
     * 上移下移
     */
    public void moveProductType(){
        try {
            String result=this.productTypeService.moveTypeItems(id, moveType);
            PrintWriter out =this.getResponse().getWriter();
            out.write(result);
        } catch (IOException e) {
            log.error("moveProductType error",e);
        }
    } 
    public List<ProductType> getProductTypeList() {
        return productTypeList;
    }
    public void setProductTypeList(List<ProductType> productTypeList) {
        this.productTypeList = productTypeList;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getMoveType() {
        return moveType;
    }
    public void setMoveType(String moveType) {
        this.moveType = moveType;
    }
    public void setProductTypeService(ProductTypeService productTypeService) {
        this.productTypeService = productTypeService;
    }
    public String getProductTypeName() {
        return productTypeName;
    }
    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }
    

}
