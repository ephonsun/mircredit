/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :收益类型
 * Author     :yujh
 * Create Date:Aug 15, 2012
 */
package com.banger.mobile.webapp.action.system;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.banger.mobile.domain.model.system.ProfitType;
import com.banger.mobile.facade.system.ProfitTypeService;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * @author yujh
 * @version $Id: ProfitAction.java,v 0.1 Aug 15, 2012 7:15:13 PM Administrator Exp $
 */
public class ProfitAction extends BaseAction{

    private static final long serialVersionUID = 6841453213033523878L;
    private ProfitTypeService   profitTypeService;
    private List<ProfitType>    profitTypeList;
    private String              profitTypeName;
    private String              moveType;
    private int                 id;
    /**
     * 列表
     * @return
     */
    public String getAllProfitType(){
        try {
            profitTypeList=this.profitTypeService.getAllProfitType();
            return SUCCESS;
        } catch (RuntimeException e) {
            log.error("getAllProfitType error",e);
            return ERROR;
        }
    }
    /**
     * 添加
     */
    public void addProfitType(){
        try {
            ProfitType profitType = new ProfitType();
            profitType.setIsDel(0);
            profitType.setSortNo(0);
            profitType.setProfitTypeName(profitTypeName);
            List<ProfitType> list = this.profitTypeService.getProfitTypeBySameName(profitType);
            String result="";
            if(list.size()>0){
                result="已存在相同的选项";
            }else{
                this.profitTypeService.addProfitType(profitType);
                result=SUCCESS;
            }
            PrintWriter out = this.getResponse().getWriter();
            out.write(result);
        } catch (IOException e) {
            log.error("addProfitType error",e);
        }
    }
    /**
     * 删除
     * @return
     */
    public String deleteProfitType(){
        try {
            this.profitTypeService.deleteProfitType(id);
            return SUCCESS;
        } catch (RuntimeException e) {
            log.error("deleteProfitType ERROR",e);
            return ERROR;
        }
    }
    /**
     * 更新
     * @return
     */
    public String gotoUpdateprofitTypePage(){
        try {
            ProfitType profitType =this.profitTypeService.getProfitTypeById(id);
            this.setProfitTypeName(profitType.getProfitTypeName());
            return SUCCESS;
        } catch (RuntimeException e) {
            log.error("gotoUpdateprofitTypePage error",e);
            return ERROR;
        }
    }
    /**
     * 更新收益类型
     */
    public void updateProfitType(){
        try {
            ProfitType profitType =this.profitTypeService.getProfitTypeById(id);
            profitType.setProfitTypeName(profitTypeName);
            String result="";
            List<ProfitType> list = this.profitTypeService.getProfitTypeBySameName(profitType);
            if(list.size()>0){
                result="该名称已存在";
            }else{
                this.profitTypeService.updateProfitType(profitType);
                result=SUCCESS;
            }
            PrintWriter out = this.getResponse().getWriter();
            out.write(result);
        } catch (IOException e) {
            log.error("updateProfitType ERROR",e);
        }
    }
    /**
     * 移动收益类型
     */
    public void moveProfitType(){
        try {
            String result=this.profitTypeService.moveTypeItems(id, moveType);
            PrintWriter out = this.getResponse().getWriter();
            out.write(result);
        } catch (IOException e) {
            log.error("moveProfitType error",e);
        }
    }
    
    
    
    public List<ProfitType> getProfitTypeList() {
        return profitTypeList;
    }
    public void setProfitTypeList(List<ProfitType> profitTypeList) {
        this.profitTypeList = profitTypeList;
    }
    public String getMoveType() {
        return moveType;
    }
    public void setMoveType(String moveType) {
        this.moveType = moveType;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setProfitTypeService(ProfitTypeService profitTypeService) {
        this.profitTypeService = profitTypeService;
    }
    public String getProfitTypeName() {
        return profitTypeName;
    }
    public void setProfitTypeName(String profitTypeName) {
        this.profitTypeName = profitTypeName;
    }
    
    
    

}
