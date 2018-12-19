/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :投资类型...
 * Author     :Administrator
 * Create Date:Jul 17, 2012
 */
package com.banger.mobile.webapp.action.system;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.banger.mobile.domain.model.rskIntervalType.RskIntervalType;
import com.banger.mobile.facade.rskIntervalType.RskIntervalTypeService;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * @author Administrator
 * @version $Id: RskIntervalTypeAction.java,v 0.1 Jul 17, 2012 10:46:11 AM Administrator Exp $
 */
public class RskIntervalTypeAction extends BaseAction {

    private static final long      serialVersionUID = 1683480822273548919L;
    private RskIntervalTypeService rskIntervalTypeService;
    private List<RskIntervalType>  rskIntervalTypeList;
    private String                 rskIntervalTypeName;
    private int                    id;
    private String                 moveType;
    private RskIntervalType        rskIntervalType;
    
    public RskIntervalType getRskIntervalType() {
        return rskIntervalType;
    }

    public void setRskIntervalType(RskIntervalType rskIntervalType) {
        this.rskIntervalType = rskIntervalType;
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

    public String getRskIntervalTypeName() {
        return rskIntervalTypeName;
    }

    public void setRskIntervalTypeName(String rskIntervalTypeName) {
        this.rskIntervalTypeName = rskIntervalTypeName;
    }

    public void setRskIntervalTypeService(RskIntervalTypeService rskIntervalTypeService) {
        this.rskIntervalTypeService = rskIntervalTypeService;
    }

    public List<RskIntervalType> getRskIntervalTypeList() {
        return rskIntervalTypeList;
    }

    public void setRskIntervalTypeList(List<RskIntervalType> rskIntervalTypeList) {
        this.rskIntervalTypeList = rskIntervalTypeList;
    }

    /**
     * 获取所有投资类型列表
     * @return
     */
    public String getAllRskIntervalTypes() {
        try {
            rskIntervalTypeList = this.rskIntervalTypeService.getAllRskIntervalType();
            return SUCCESS;
        } catch (RuntimeException e) {
            log.error("获取投资类型失败", e);
            return ERROR;
        }
    }
    /**
     * 添加投资偏好
     */
    public void addRskIntervalType() {
        try {
            RskIntervalType rskIntervalType = new RskIntervalType();
            rskIntervalType.setIsDel(0);
            rskIntervalType.setTypeName(rskIntervalTypeName.trim());
            rskIntervalType.setSortNo(0);
            List<RskIntervalType> list = this.rskIntervalTypeService.getRskIntervalTypeBySameName(rskIntervalType);
            String result ="";
            if(list.size()>0){
                result = "已存在相同的选项";
            }else{
                this.rskIntervalTypeService.addRskIntervalType(rskIntervalType);
                result ="SUCCESS";
            }
            PrintWriter out = this.getResponse().getWriter();
            out.write(result);
        } catch (Exception e) {
            log.error("添加投资偏好失败",e);
        }
    }
    /**
     * 删除投资偏好
     * @return
     */
    public String deleteRskIntervalType(){
        try {
            this.rskIntervalTypeService.deleteRskIntervalrType(id);
            return SUCCESS;
        } catch (RuntimeException e) {
            log.error("删除投资偏好失败",e);
            return ERROR;
        }
    }
    /**
     * 修改投资偏好
     */
    public void updateRskIntervalType(){
        try {
            RskIntervalType rskIntervalType =this.rskIntervalTypeService.getRskIntervalTypeById(id);
            rskIntervalType.setTypeName(rskIntervalTypeName.trim());
            List<RskIntervalType> list = this.rskIntervalTypeService.getRskIntervalTypeBySameName(rskIntervalType);
            String result="";
            if(list.size()>0){
                result=rskIntervalTypeName+"已存在";
            }else{
                this.rskIntervalTypeService.updateRskIntervalType(rskIntervalType);
                result="SUCCESS";
            }
            PrintWriter out =this.getResponse().getWriter();
            out.write(result);
        } catch (IOException e) {
            log.error("更新投资偏好失败",e);
        }
    }
    /**
     * 跳转
     * @return
     */
    public String goToUpdateRskIntervalTypePage(){
        try {
            RskIntervalType rskIntervalType =this.rskIntervalTypeService.getRskIntervalTypeById(id);
            this.setRskIntervalTypeName(rskIntervalType.getTypeName());
            return SUCCESS;
        } catch (RuntimeException e) {
            log.error("跳转出错",e);
            return ERROR;
        }
    }
    /**
     * 投资偏好上移下移
     */
    public void moveRskIntervalType(){
        try {
            String result=this.rskIntervalTypeService.moveTypeItems(id, moveType);
            PrintWriter out =this.getResponse().getWriter();
            out.write(result);
        } catch (IOException e) {
            log.error("投资类型上移下移出错",e);
        }
    } 
    /**
     * 启用、停用
     * @return
     */
    public String changeIntervalTypeState(){
        try {
            this.rskIntervalTypeService.changeState(rskIntervalType);
            return SUCCESS;
        } catch (RuntimeException e) {
            log.error("changeIntervalTypeState action error",e);
            return ERROR;
        }
    }
}
