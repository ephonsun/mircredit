/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :联系目的action
 * Author     :yujh
 * Create Date:Nov 13, 2012
 */
package com.banger.mobile.webapp.action.system;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.banger.mobile.domain.model.tskTaskPurpose.TskTaskPurpose;
import com.banger.mobile.facade.tskTaskPurpose.TskTaskPurposeService;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * @author yujh
 * @version $Id: TskTaskPurposeAction.java,v 0.1 Nov 13, 2012 4:56:37 PM Administrator Exp $
 */
public class TskTaskPurposeAction extends BaseAction{

    private static final long serialVersionUID = 4903131473888496629L;
    private TskTaskPurposeService   tskTaskPurposeService;
    private List<TskTaskPurpose>    purposeList;
    private String                  purposeName;
    private int                     id;
    private TskTaskPurpose          tskTaskPurpose;
    private String                  moveType;
    //列表
    public String showAllPurpose(){
        try {
            purposeList=this.tskTaskPurposeService.getAllTskTaskPurpose(true);
            return SUCCESS;
        } catch (RuntimeException e) {
            log.error("showAllPurpose action error",e);
            return ERROR;
        }
    }
    //添加
    public void addPurpose(){
        try {
            TskTaskPurpose tskTaskPurpose = new TskTaskPurpose();
            tskTaskPurpose.setIsDel(0);
            tskTaskPurpose.setTaskPurposeName(purposeName.trim());
            tskTaskPurpose.setSortNo(0);
            List<TskTaskPurpose> list = this.tskTaskPurposeService.getTskTaskPurposeBySameName(tskTaskPurpose);
            String result="";
            if(list.size()>0){
                result="已存在相同的选项";
            }else{
                this.tskTaskPurposeService.addTskTaskPurpose(tskTaskPurpose);
                result="SUCCESS";
            }
            PrintWriter out = this.getResponse().getWriter();
            out.write(result);
        } catch (Exception e) {
            log.error("addPurpose action error",e);
        }
    } 
    //删除
    public String deletePurpose(){
        try {
            this.tskTaskPurposeService.deleteTskTaskPose(id);
            return SUCCESS;
        } catch (RuntimeException e) {
            log.error("deletePurpose action error",e);
            return ERROR;
        }
    }
  //修改
    public void updatePurpose(){
        try {
            TskTaskPurpose tskTaskPurpose =this.tskTaskPurposeService.getTskTaskPurposeById(id);
            tskTaskPurpose.setTaskPurposeName(purposeName.trim());
            List<TskTaskPurpose> list = this.tskTaskPurposeService.getTskTaskPurposeBySameName(tskTaskPurpose);
            String result="";
            if(list.size()>0){
                result=purposeName+"已存在";
            }else{
                this.tskTaskPurposeService.updateTskTaskPurpose(tskTaskPurpose);
                result="SUCCESS";
            }
            PrintWriter out =this.getResponse().getWriter();
            out.write(result);
        } catch (IOException e) {
            log.error("updatePurpose action error",e);
        }
    }
    //跳转
    public String showUpdatePage(){
        try {
            TskTaskPurpose tskTaskPurpose =this.tskTaskPurposeService.getTskTaskPurposeById(id);
            this.setPurposeName(tskTaskPurpose.getTaskPurposeName());
            return SUCCESS;
        } catch (RuntimeException e) {
            log.error("showUpdatePage action error",e);
            return ERROR;
        }
    }
  //上移下移
    public void move(){
        try {
            String result=this.tskTaskPurposeService.moveTypeItems(id, moveType);
            PrintWriter out =this.getResponse().getWriter();
            out.write(result);
        } catch (IOException e) {
            log.error("move action error",e);
        }
    } 
    //启用停用
    public String changeState(){
        try {
            this.tskTaskPurposeService.changeState(tskTaskPurpose);
            return SUCCESS;
        } catch (RuntimeException e) {
            log.error("changeState action error",e);
            return ERROR;
        }
    }
    
    public List<TskTaskPurpose> getPurposeList() {
        return purposeList;
    }
    public void setPurposeList(List<TskTaskPurpose> purposeList) {
        this.purposeList = purposeList;
    }
    public String getPurposeName() {
        return purposeName;
    }
    public void setPurposeName(String purposeName) {
        this.purposeName = purposeName;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public TskTaskPurpose getTskTaskPurpose() {
        return tskTaskPurpose;
    }
    public void setTskTaskPurpose(TskTaskPurpose tskTaskPurpose) {
        this.tskTaskPurpose = tskTaskPurpose;
    }
    public String getMoveType() {
        return moveType;
    }
    public void setMoveType(String moveType) {
        this.moveType = moveType;
    }
    public void setTskTaskPurposeService(TskTaskPurposeService tskTaskPurposeService) {
        this.tskTaskPurposeService = tskTaskPurposeService;
    }
    
}
