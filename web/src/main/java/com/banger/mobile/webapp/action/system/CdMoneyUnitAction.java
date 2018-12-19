/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :销售单位action
 * Author     :yujh
 * Create Date:Aug 14, 2012
 */
package com.banger.mobile.webapp.action.system;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.banger.mobile.domain.model.system.CdMoneyUnit;
import com.banger.mobile.facade.system.CdMoneyUnitService;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * @author yujh
 * @version $Id: CdMoneyUnitAction.java,v 0.1 Aug 14, 2012 5:52:19 PM Administrator Exp $
 */
public class CdMoneyUnitAction extends BaseAction{

    private static final long serialVersionUID = 4081128336274351484L;
    
    private CdMoneyUnitService      moneyUnitService;
    private List<CdMoneyUnit>       cdMoneyUnitList;
    private String                  cdMoneyUnitName;
    private int                     id;
    private String                  moveType;
    /**
     * 列表
     * @return
     */
    public String getAllMoneyUnit(){
        try {
            cdMoneyUnitList =this.moneyUnitService.getAllMoneyUnit();
            return SUCCESS;
        } catch (RuntimeException e) {
            log.error("getAllMoneyUnit action error",e);
            return ERROR;
        }
    }
    /**
     * 添加
     */
    public void addMoneyUnit(){
        try {
            CdMoneyUnit cdmoneyUnit= new CdMoneyUnit();
            cdmoneyUnit.setIsDel(0);
            cdmoneyUnit.setSortNo(0);
            cdmoneyUnit.setMoneyUnitName(cdMoneyUnitName);
            List<CdMoneyUnit> list =this.moneyUnitService.getMoneyUnitBySameName(cdmoneyUnit);
            String result="";
            if(list.size()>0){
                result="已存在相同的选项";
            }else{
                this.moneyUnitService.addMoneyUnit(cdmoneyUnit);
                result=SUCCESS;
            }
            PrintWriter out = this.getResponse().getWriter();
            out.write(result);
        } catch (IOException e) {
            log.error("addMoneyUnit action error",e);
        }
        
    }
    /**
     * 删除
     * @return
     */
    public String deleteMoneyUnit(){
        try {
            this.moneyUnitService.deleteMoneyUnit(id);
            return SUCCESS;
        } catch (RuntimeException e) {
            log.error("deleteMoneyUnit action error",e);
            return ERROR;
        }
    }
    /**
     * 是否是最后一条销售单位
     */
    public void isLastMoneyUnit(){
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");
        try {
            PrintWriter out = response.getWriter();
            if(this.moneyUnitService.queryLaseMoneyUnit()<2){
                out.print(1);
            }
            out.flush();
            out.close();
        }catch (Exception e) {
            log.error("CdMoneyUnitAction isLastMoneyUnit error:"+e.getMessage());
        }
    }
    /**
     * 更新
     * @return
     */
    public String gotoUpdate(){
        try {
            CdMoneyUnit cdMoneyUnit = this.moneyUnitService.getMoneyUnitById(id);
            this.setCdMoneyUnitName(cdMoneyUnit.getMoneyUnitName());
            return SUCCESS;
        } catch (RuntimeException e) {
            log.error("gotoUpdate ERROR",e);
            return ERROR;
        }
    }
    /**
     * 更新
     */
    public void updateMoneyUnit(){
        try {
            CdMoneyUnit cdMoneyUnit =this.moneyUnitService.getMoneyUnitById(id);
            cdMoneyUnit.setMoneyUnitName(cdMoneyUnitName);
            String result="";
            List<CdMoneyUnit> list = this.moneyUnitService.getMoneyUnitBySameName(cdMoneyUnit);
            if(list.size()>0){
                result="该名称已存在";
            }else{
                this.moneyUnitService.updateMoneyUnit(cdMoneyUnit);
                result=SUCCESS;
            }
            PrintWriter out = this.getResponse().getWriter();
            out.write(result);
        } catch (IOException e) {
            log.error("updateMoneyUnit ERROR",e);
        }
    }
    /**
     * 上移下移
     */
    public void moveMoneyUnit(){
        try {
            String result=this.moneyUnitService.moveTypeItems(id, moveType);
            PrintWriter out =this.getResponse().getWriter();
            out.write(result);
        } catch (IOException e) {
            log.error("moveMoneyUnit error",e);
        }
    } 
    
    
    
    public List<CdMoneyUnit> getCdMoneyUnitList() {
        return cdMoneyUnitList;
    }
    public void setCdMoneyUnitList(List<CdMoneyUnit> cdMoneyUnitList) {
        this.cdMoneyUnitList = cdMoneyUnitList;
    }
    public String getCdMoneyUnitName() {
        return cdMoneyUnitName;
    }
    public void setCdMoneyUnitName(String cdMoneyUnitName) {
        this.cdMoneyUnitName = cdMoneyUnitName;
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
    public void setMoneyUnitService(CdMoneyUnitService moneyUnitService) {
        this.moneyUnitService = moneyUnitService;
    }
    
}
