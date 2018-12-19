/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :客户类型...
 * Author     :QianJie
 * Create Date:May 21, 2012
 */
package com.banger.mobile.webapp.action.system;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import com.banger.mobile.domain.Enum.system.EnumSystem;
import com.banger.mobile.domain.model.system.CrmCustomerType;
import com.banger.mobile.facade.customer.CrmCustomerService;
import com.banger.mobile.facade.log.OpeventLogService;
import com.banger.mobile.facade.system.CrmCustomerTypeService;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * @author QianJie
 * @version $Id: CrmCustomerTypeAction.java,v 0.1 May 21, 2012 2:44:37 PM QianJie Exp $
 */
public class CrmCustomerTypeAction extends BaseAction {

    private static final long serialVersionUID = 3431919690384552982L;

    private CrmCustomerTypeService crmCustomerTypeService;

    public void setCrmCustomerTypeService(CrmCustomerTypeService crmCustomerTypeService) {
        this.crmCustomerTypeService = crmCustomerTypeService;
    }
    
    private List<CrmCustomerType> crmCustomerTypeList;
    public List<CrmCustomerType> getCrmCustomerTypeList() {
        return crmCustomerTypeList;
    }

    public void setCrmCustomerTypeList(List<CrmCustomerType> crmCustomerTypeList) {
        this.crmCustomerTypeList = crmCustomerTypeList;
    }
    
    private int id; 
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    private String customerTypeName;
    public String getCustomerTypeName() {
        return customerTypeName;
    }

    public void setCustomerTypeName(String customerTypeName) {
        this.customerTypeName = customerTypeName;
    }
    
    private String moveType;
    public String getMoveType() {
        return moveType;
    }

    public void setMoveType(String moveType) {
        this.moveType = moveType;
    }
    
    private CrmCustomerService crmCustomerService;
    public CrmCustomerService getCrmCustomerService() {
        return crmCustomerService;
    }

    public void setCrmCustomerService(CrmCustomerService crmCustomerService) {
        this.crmCustomerService = crmCustomerService;
    }
    
    private OpeventLogService opeventLogService;
    public OpeventLogService getOpeventLogService() {
        return opeventLogService;
    }

    public void setOpeventLogService(OpeventLogService opeventLogService) {
        this.opeventLogService = opeventLogService;
    }
    
    /**
     * 获取所有客户类型
     * @return
     */
    public String showAllCrmCustomerType() {
        try {
            crmCustomerTypeList = crmCustomerTypeService.getAllCrmCustomerType();
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("showCrmCustomerTypeList action error:" + e.getMessage());
            return ERROR;
        }
    }
    
    /**
     * 删除客户类型(伪删除)
     * @return
     */
    public String deleteCrmCustomerType(){
        try {
            crmCustomerTypeService.deleteCrmCustomerType(id);
            //修改客户数据表中关联客户类型为此id的客户类型设置为默认0
            crmCustomerService.updateCrmCustomerByCrmCustomerTypeById(id);
//            opeventLogService.addOpeventLog(EnumSystem.MODEL.getValue(), EnumSystem.ACTION_DEL_CRMCUSTOMERTYPE.getValue(), 1, EnumSystem.DEL_CRMCUSTOMERTYPE_REMARK.getValue());
            return SUCCESS;
        } catch (Exception e) {
            log.error("deleteCrmCustomerType action error:" + e.getMessage());
//            opeventLogService.addOpeventLog(EnumSystem.MODEL.getValue(), EnumSystem.ACTION_DEL_CRMCUSTOMERTYPE.getValue(), 0, EnumSystem.DEL_CRMCUSTOMERTYPE_REMARK.getValue());
            return ERROR;
        }
    }

    /**
     * 添加客户类型
     */
    public void addCrmCustomerType(){
        try {
            CrmCustomerType crmType=new CrmCustomerType();
            crmType.setCreateDate(new Date());
            crmType.setCreateUser(this.getLoginInfo().getUserId());
            crmType.setCustomerTypeName(customerTypeName.trim());
            crmType.setIsActived(1);
            crmType.setIsDel(0);
            crmType.setUpdateDate(new Date());
            crmType.setUpdateUser(this.getLoginInfo().getUserId());
            crmType.setSortNo(0);
            List<CrmCustomerType> list = crmCustomerTypeService.getSameCrmCustomerTypeByName(crmType);
            String result = "";
            if(list.size()>0){
                result = EnumSystem.CRMCUSTOMERTYPENAME_REPEAT.getValue();
            }else{
                crmCustomerTypeService.addCrmCustomerType(crmType);
//                opeventLogService.addOpeventLog(EnumSystem.MODEL.getValue(), EnumSystem.ACTION_ADD_CRMCUSTOMERTYPE.getValue(), 1, EnumSystem.ADD_CRMCUSTOMERTYPE_REMARK.getValue());
                result = "SUCCESS";
            }
            PrintWriter out = this.getResponse().getWriter();
            out.write(result);
        } catch (Exception e) {
            log.error("addCrmCustomerType action error:" + e.getMessage());
//            opeventLogService.addOpeventLog(EnumSystem.MODEL.getValue(), EnumSystem.ACTION_ADD_CRMCUSTOMERTYPE.getValue(), 0, EnumSystem.ADD_CRMCUSTOMERTYPE_REMARK.getValue());
        }
    }

    /**
     * 修改客户类型
     */
    public void updateCrmCustomerType(){
        try {
            CrmCustomerType crmType = crmCustomerTypeService.getCrmCustomerTypeById(id);
            crmType.setCustomerTypeName(customerTypeName.trim());
            crmType.setUpdateDate(new Date());
            crmType.setUpdateUser(this.getLoginInfo().getUserId());
            List<CrmCustomerType> list = crmCustomerTypeService.getSameCrmCustomerTypeByName(crmType);
            String result = "";
            if(list.size()>0){
                result = EnumSystem.CRMCUSTOMERTYPENAME_REPEAT.getValue();
            }else{
                crmCustomerTypeService.updateCrmCustomerType(crmType);
//                opeventLogService.addOpeventLog(EnumSystem.MODEL.getValue(), EnumSystem.ACTION_UPDATE_CRMCUSTOMERTYPE.getValue(), 1, EnumSystem.UPDATE_CRMCUSTOMERTYPE_REMARK.getValue());
                result = "SUCCESS";
            }
            PrintWriter out = this.getResponse().getWriter();
            out.write(result);
        } catch (Exception e) {
            log.error("updateCrmCustomerType action error:" + e.getMessage());
//            opeventLogService.addOpeventLog(EnumSystem.MODEL.getValue(), EnumSystem.ACTION_UPDATE_CRMCUSTOMERTYPE.getValue(), 0, EnumSystem.UPDATE_CRMCUSTOMERTYPE_REMARK.getValue());
        }
    }
    
    /**
     * 跳转到修改客户类型页面
     * @return
     */
    public String updateCrmCustomerTypePage(){
    	try {
			CrmCustomerType crmType = crmCustomerTypeService
					.getCrmCustomerTypeById(id);
			this.setCustomerTypeName(crmType.getCustomerTypeName());
			return SUCCESS;
		} catch (Exception e) {
			log.error("updateCrmCustomerTypePage action error:"
					+ e.getMessage());
			return ERROR;
		}
    }
   
    /**
	 * 上移和下移客户类型
	 * 
	 * @return
	 */
    public void moveTypeItems(){
        try {
            String result = crmCustomerTypeService.moveTypeItems(id, moveType);
            PrintWriter out = this.getResponse().getWriter();
            out.write(result);
        } catch (Exception e) {
            log.error("moveTypeItems action error:" + e.getMessage());
        }
    }
}
