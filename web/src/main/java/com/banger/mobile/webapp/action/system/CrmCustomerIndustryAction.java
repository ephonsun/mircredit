/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :客户所处行业...
 * Author     :QianJie
 * Create Date:May 25, 2012
 */
package com.banger.mobile.webapp.action.system;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import com.banger.mobile.domain.Enum.system.EnumSystem;
import com.banger.mobile.domain.model.system.CrmCustomerIndustry;
import com.banger.mobile.facade.customer.CrmCustomerService;
import com.banger.mobile.facade.log.OpeventLogService;
import com.banger.mobile.facade.system.CrmCustomerIndustryService;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * @author QianJie
 * @version $Id: CrmCustomerIndustryAction.java,v 0.1 May 25, 2012 11:35:27 AM QianJie Exp $
 */
public class CrmCustomerIndustryAction extends BaseAction {

    private static final long serialVersionUID = -5007483548583575867L;

    private CrmCustomerIndustryService crmCustomerIndustryService;

    public void setCrmCustomerIndustryService(CrmCustomerIndustryService crmCustomerIndustryService) {
        this.crmCustomerIndustryService = crmCustomerIndustryService;
    }
    
    private List<CrmCustomerIndustry> crmCustomerIndustryList;
    public List<CrmCustomerIndustry> getCrmCustomerIndustryList() {
        return crmCustomerIndustryList;
    }

    public void setCrmCustomerIndustryList(List<CrmCustomerIndustry> crmCustomerIndustryList) {
        this.crmCustomerIndustryList = crmCustomerIndustryList;
    }
    
    private int id; 
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    private String customerIndustryName;
    public String getCustomerIndustryName() {
        return customerIndustryName;
    }

    public void setCustomerIndustryName(String customerIndustryName) {
        this.customerIndustryName = customerIndustryName;
    }
    
    private String moveType;
    public String getMoveType() {
        return moveType;
    }

    public void setMoveType(String moveType) {
        this.moveType = moveType;
    }
    
    private OpeventLogService opeventLogService;
    public OpeventLogService getOpeventLogService() {
        return opeventLogService;
    }

    public void setOpeventLogService(OpeventLogService opeventLogService) {
        this.opeventLogService = opeventLogService;
    }
    private CrmCustomerService crmCustomerService;
    public CrmCustomerService getCrmCustomerService() {
        return crmCustomerService;
    }

    public void setCrmCustomerService(CrmCustomerService crmCustomerService) {
        this.crmCustomerService = crmCustomerService;
    }
    
    /**
     * 获取所有行业类型
     * @return
     */
    public String showAllCrmCustomerIndustry() {
        try {
            crmCustomerIndustryList = crmCustomerIndustryService.getAllCrmCustomerIndustry();
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("showAllCrmCustomerIndustry action error:" + e.getMessage());
            return ERROR;
        }
    }
    
    /**
     * 删除行业类型(伪删除)
     * @return
     */
    public String deleteCrmCustomerIndustry(){
        try {
            crmCustomerIndustryService.deleteCrmCustomerIndustry(id);
            //修改客户数据表中关联行业类型为此id的行业类型设置为默认0
            crmCustomerService.updateCrmCustomerByCustomerIndustryId(id);
//            opeventLogService.addOpeventLog(EnumSystem.MODEL.getValue(), EnumSystem.ACTION_DEL_CRMCUSTOMERINDUSTRY.getValue(), 1, EnumSystem.DEL_CRMCUSTOMERINDUSTRY_REMARK.getValue());
            return SUCCESS;
        } catch (Exception e) {
            // TODO: handle exception
            log.error("deleteCrmCustomerIndustry action error:" + e.getMessage());
//            opeventLogService.addOpeventLog(EnumSystem.MODEL.getValue(), EnumSystem.ACTION_DEL_CRMCUSTOMERINDUSTRY.getValue(), 0, EnumSystem.DEL_CRMCUSTOMERINDUSTRY_REMARK.getValue());
            return ERROR;
        }
    }

    /**
     * 添加行业类型
     */
    public void addCrmCustomerIndustry(){
        try {
            CrmCustomerIndustry crmIndustry=new CrmCustomerIndustry();
            crmIndustry.setCreateDate(new Date());
            crmIndustry.setCreateUser(this.getLoginInfo().getUserId());
            crmIndustry.setCustomerIndustryName(customerIndustryName.trim());
            crmIndustry.setIsActived(1);
            crmIndustry.setIsDel(0);
            crmIndustry.setUpdateDate(new Date());
            crmIndustry.setUpdateUser(this.getLoginInfo().getUserId());
            crmIndustry.setSortNo(0);
            List<CrmCustomerIndustry> list = crmCustomerIndustryService.getSameCrmCustomerIndustryByName(crmIndustry);
            String result = "";
            if(list.size()>0){
                result = EnumSystem.CRMCUSTOMERINDUSTRYNAME_REPEAT.getValue();
            }else{
                crmCustomerIndustryService.addCrmCustomerIndustry(crmIndustry);
//                opeventLogService.addOpeventLog(EnumSystem.MODEL.getValue(), EnumSystem.ACTION_ADD_CRMCUSTOMERINDUSTRY.getValue(), 1, EnumSystem.ADD_CRMCUSTOMERINDUSTRY_REMARK.getValue());
                result = "SUCCESS";
            }
            PrintWriter out = this.getResponse().getWriter();
            out.write(result);
            
        } catch (Exception e) {
            log.error("addCrmCustomerIndustry action error:" + e.getMessage());
//            opeventLogService.addOpeventLog(EnumSystem.MODEL.getValue(), EnumSystem.ACTION_ADD_CRMCUSTOMERINDUSTRY.getValue(), 0, EnumSystem.ADD_CRMCUSTOMERINDUSTRY_REMARK.getValue());
        }
    }

    /**
     * 修改行业类型
     */
    public void updateCrmCustomerIndustry(){
        try {
            CrmCustomerIndustry crmIndustry = crmCustomerIndustryService.getCrmCustomerIndustryById(id);
            crmIndustry.setCustomerIndustryName(customerIndustryName.trim());
            crmIndustry.setUpdateDate(new Date());
            crmIndustry.setUpdateUser(this.getLoginInfo().getUserId());
            List<CrmCustomerIndustry> list = crmCustomerIndustryService.getSameCrmCustomerIndustryByName(crmIndustry);
            String result = "";
            if(list.size()>0){
                result = EnumSystem.CRMCUSTOMERINDUSTRYNAME_REPEAT.getValue();
            }else{
                crmCustomerIndustryService.updateCrmCustomerIndustry(crmIndustry);
//                opeventLogService.addOpeventLog(EnumSystem.MODEL.getValue(), EnumSystem.ACTION_UPDATE_CRMCUSTOMERINDUSTRY.getValue(), 1, EnumSystem.UPDATE_CRMCUSTOMERINDUSTRY_REMARK.getValue());
                result = "SUCCESS";
            }
            PrintWriter out = this.getResponse().getWriter();
            out.write(result);
        } catch (Exception e) {
            log.error("updateCrmCustomerIndustry action error:" + e.getMessage());
//            opeventLogService.addOpeventLog(EnumSystem.MODEL.getValue(), EnumSystem.ACTION_UPDATE_CRMCUSTOMERINDUSTRY.getValue(), 0, EnumSystem.UPDATE_CRMCUSTOMERINDUSTRY_REMARK.getValue());
        }
    }
    
    /**
     * 跳转到修改行业类型页面
     * @return
     */
    public String updateCrmCustomerIndustryPage(){
        try {
            CrmCustomerIndustry crmIndustry = crmCustomerIndustryService
                    .getCrmCustomerIndustryById(id);
            this.setCustomerIndustryName(crmIndustry.getCustomerIndustryName());
            return SUCCESS;
        } catch (Exception e) {
            log.error("updateCrmCustomerIndustryPage action error:"
                    + e.getMessage());
            return ERROR;
        }
    }
   
    /**
     * 上移和下移行业类型
     * 
     * @return
     */
    public void moveTypeItems(){
        try {
            String result = crmCustomerIndustryService.moveTypeItems(id, moveType);
            PrintWriter out = this.getResponse().getWriter();
            out.write(result);
        } catch (Exception e) {
            log.error("moveTypeItems action error:" + e.getMessage());
        }
    }
}
