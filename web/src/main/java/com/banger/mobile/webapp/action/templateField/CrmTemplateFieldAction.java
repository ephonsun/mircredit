/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :模版字段ACTION
 * Author     :QianJie
 * Create Date:May 30, 2012
 */
package com.banger.mobile.webapp.action.templateField;

import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.domain.model.fieldCodeData.CrmFieldCodeData;
import com.banger.mobile.domain.model.fieldType.CrmFieldType;
import com.banger.mobile.domain.model.templateField.CrmTemplateField;
import com.banger.mobile.facade.fieldCodeData.CrmFieldCodeDataService;
import com.banger.mobile.facade.fieldType.CrmFieldTypeService;
import com.banger.mobile.facade.log.OpeventLogService;
import com.banger.mobile.facade.log.OpeventLoginLogService;
import com.banger.mobile.facade.template.CrmTemplateService;
import com.banger.mobile.facade.templateField.CrmTemplateFieldService;
import com.banger.mobile.util.StringUtil;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * @author QianJie
 * @version $Id: CrmTemplateFieldAction.java,v 0.1 May 30, 2012 11:41:51 AM QianJie Exp $
 */
public class CrmTemplateFieldAction extends BaseAction {

    private static final long       serialVersionUID = 8898514142744511693L;

    private CrmTemplateFieldService crmTemplateFieldService;
    private CrmTemplateService crmTemplateService;

    public void setCrmTemplateFieldService(CrmTemplateFieldService crmTemplateFieldService) {
        this.crmTemplateFieldService = crmTemplateFieldService;
    }
    
    public void setCrmTemplateService(CrmTemplateService crmTemplateService) {
		this.crmTemplateService = crmTemplateService;
	}



	private OpeventLoginLogService  opeventLoginLogService;

    public void setOpeventLoginLogService(OpeventLoginLogService opeventLoginLogService) {
        this.opeventLoginLogService = opeventLoginLogService;
    }

    private CrmFieldTypeService crmFieldTypeService;

    public void setCrmFieldTypeService(CrmFieldTypeService crmFieldTypeService) {
        this.crmFieldTypeService = crmFieldTypeService;
    }

    private CrmFieldCodeDataService crmFieldCodeDataService;

    public void setCrmFieldCodeDataService(CrmFieldCodeDataService crmFieldCodeDataService) {
        this.crmFieldCodeDataService = crmFieldCodeDataService;
    }

    private List<CrmTemplateField> crmTemplateFieldList;

    public List<CrmTemplateField> getCrmTemplateFieldList() {
        return crmTemplateFieldList;
    }

    public void setCrmTemplateFieldList(List<CrmTemplateField> crmTemplateFieldList) {
        this.crmTemplateFieldList = crmTemplateFieldList;
    }

    private List<CrmFieldType> crmFieldTypeList;

    public List<CrmFieldType> getCrmFieldTypeList() {
        return crmFieldTypeList;
    }

    public void setCrmFieldTypeList(List<CrmFieldType> crmFieldTypeList) {
        this.crmFieldTypeList = crmFieldTypeList;
    }

    private CrmTemplateField crmTemplateField;

    public CrmTemplateField getCrmTemplateField() {
        return crmTemplateField;
    }

    public void setCrmTemplateField(CrmTemplateField crmTemplateField) {
        this.crmTemplateField = crmTemplateField;
    }

    private int templateFieldId;

    public int getTemplateFieldId() {
        return templateFieldId;
    }

    public void setTemplateFieldId(int templateFieldId) {
        this.templateFieldId = templateFieldId;
    }

    //模版ID
    private int templateId;

    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    private String codeData;

    public String getCodeData() {
        return codeData;
    }

    public void setCodeData(String codeData) {
        this.codeData = codeData;
    }

    private String saveType;

    public String getSaveType() {
        return saveType;
    }

    public void setSaveType(String saveType) {
        this.saveType = saveType;
    }

    private String moveType;

    public String getMoveType() {
        return moveType;
    }

    public void setMoveType(String moveType) {
        this.moveType = moveType;
    }

    private List<CrmFieldCodeData> crmFieldCodeDataList;

    public List<CrmFieldCodeData> getCrmFieldCodeDataList() {
        return crmFieldCodeDataList;
    }

    public void setCrmFieldCodeDataList(List<CrmFieldCodeData> crmFieldCodeDataList) {
        this.crmFieldCodeDataList = crmFieldCodeDataList;
    }

    /**
     * 获取对应的模版字段列表
     * @return
     */
    public String showCrmTemplateFieldByTemplateId() {
        try {
            //获取对应的模版字段列表
            crmTemplateFieldList = crmTemplateFieldService
                .getCrmTemplateFieldListByTemplateId(templateId);
            return SUCCESS;
        } catch (Exception e) {
            return ERROR;
        }
    }

    /**
     * 调整到新增页面
     * @return
     */
    public String toAddTemplateFieldPage() {
        crmFieldTypeList = crmFieldTypeService.getAllCrmFieldType();
        return SUCCESS;
    }

    /**
     * 调转到编辑页面
     * @return
     */
    public String toUpdateTemplateFieldPage() {
        crmFieldTypeList = crmFieldTypeService.getAllCrmFieldType();
        crmTemplateField = crmTemplateFieldService.getCrmTemplateFieldById(templateFieldId);
        crmFieldCodeDataList = crmFieldCodeDataService
            .getCrmFieldCodeDataListByFieldId(templateFieldId);
        return SUCCESS;
    }

    /**
     * 添加模版字段
     */
    public void addCrmTemplateField() {
        try {
            String result = "";
            //判断是否存在客户系统字段里
            if (IsSystemCusField(crmTemplateField.getTemplateFieldName())) {
                //判断是否已在基础模版出现该字段
                if (!crmTemplateFieldService.getHasSameBasicCrmTemplateField(crmTemplateField)) {
                    String extField = crmTemplateFieldService.getNotUsedExtField(crmTemplateField
                        .getTemplateFieldType().trim());

                    if (!extField.equals("")) {
                        crmTemplateField.setCreateDate(new Date());
                        crmTemplateField.setCreateUser(this.getLoginInfo().getUserId());
                        crmTemplateField.setUpdateDate(new Date());
                        crmTemplateField.setUpdateUser(this.getLoginInfo().getUserId());
                        crmTemplateField.setIsDel(0);
                        crmTemplateField.setSortNo(0);
                        crmTemplateField.setIsPopUp(0);
                        crmTemplateField.setExtFieldName(extField);
                        //crmTemplateField.setMeasurement("");//单位
                        List<CrmTemplateField> list = crmTemplateFieldService
                            .getSameCrmTemplateFieldByName(crmTemplateField);

                        if (list.size() > 0) {
                            result = "已存在相同名称的自定义字段！";
                        } else {
                            int rid = crmTemplateFieldService.addCrmTemplateField(crmTemplateField);
                            //添加代码表数据
                            String[] codeDataStrings = codeData.split(",");
                            for (int i = 0; i < codeDataStrings.length; i++) {
                                if (!codeDataStrings[i].toString().trim().equals("")) {
                                    CrmFieldCodeData crmCodeData = new CrmFieldCodeData();
                                    crmCodeData.setCreateUser(this.getLoginInfo().getUserId());
                                    crmCodeData.setFieldCodeDataKey(codeDataStrings[i].toString()
                                        .trim());
                                    crmCodeData.setFieldCodeDataValue(codeDataStrings[i].toString()
                                        .trim());
                                    crmCodeData.setFieldId(rid);
                                    crmCodeData.setIsDel(0);
                                    crmCodeData.setSortNo(i + 1);
                                    crmCodeData.setUpdateUser(this.getLoginInfo().getUserId());
                                    crmFieldCodeDataService.addCrmFieldCodeData(crmCodeData);
                                }
                            }
                            //opeventLogService.addOpeventLog(EnumSystem.MODEL.getValue(), EnumSystem.ACTION_ADD_CRMCUSTOMERTYPE.getValue(), 1, EnumSystem.ADD_CRMCUSTOMERTYPE_REMARK.getValue());
                            if (saveType.equals("Save")) {
                                result = "SUCCESS";//仅仅保存
                            } else {
                                result = "Continue";//保存并继续
                            }
                        }
                    } else {
                        result = "已无该类型的自定义字段类型可用，无法保存！";
                    }
                } else {
                    result = "已存在相同名称的系统基础客户字段！";
                }
            } else {
                result = "已存在相同名称的系统客户字段！";
            }
            PrintWriter out = this.getResponse().getWriter();
            out.write(result);
        } catch (Exception e) {
            log.error("addCrmTemplateField action error:" + e.getMessage());
           // opeventLogService.addOpeventLog("模版字段", "新增模版字段", 0, "新增模版字段");
        }
    }

    /**
     * 编辑模版字段
     */
    public void updateCrmTemplateField() {
        try {
            String result = "";
            //判断是否存在客户系统字段里
            if (IsSystemCusField(crmTemplateField.getTemplateFieldName())) {
                //判断是否已在基础模版出现该字段
                if (!crmTemplateFieldService.getHasSameBasicCrmTemplateField(crmTemplateField)) {
                    List<CrmTemplateField> listSame = crmTemplateFieldService
                        .getSameCrmTemplateFieldByName(crmTemplateField);

                    if (listSame.size() > 0) {
                        result = "已存在相同名称的自定义字段！";
                    } else {
                        CrmTemplateField crmField = crmTemplateFieldService
                            .getCrmTemplateFieldById(crmTemplateField.getTemplateFieldId());
                        crmField.setTemplateFieldName(crmTemplateField.getTemplateFieldName());
                        crmField.setTemplateFieldType(crmTemplateField.getTemplateFieldType());
                        crmField.setMeasurement(crmTemplateField.getMeasurement());
                        crmTemplateFieldService.updateCrmTemplateField(crmField);

                        if (crmTemplateField.getTemplateFieldType().equals("Select")
                            || crmTemplateField.getTemplateFieldType().equals("MultipleSelect")) {
                            //得到删除的代码集合delIds、得到新增的代码集合addIds
                        	
                        	List<CrmFieldCodeData> list = crmFieldCodeDataService
                                    .getCrmFieldCodeDataListByFieldId(crmTemplateField
                                        .getTemplateFieldId());
                        	
                            if (!"".equals(codeData)) {
                                
                                Map<String, Object> newCodes = new HashMap<String, Object>();
                                Map<String, Object> oldCodes = new HashMap<String, Object>();
                                String[] codeStrings = codeData.split(",");
                                for (String code : codeStrings) {
                                    newCodes.put(code, code);
                                }
                                //获取已删除的数据
                                for (CrmFieldCodeData crmCodeData : list) {
                                    oldCodes.put(crmCodeData.getFieldCodeDataKey(), crmCodeData);
                                    if (!newCodes.containsKey(crmCodeData.getFieldCodeDataKey())) {
                                        crmFieldCodeDataService.deleteCrmFieldCodeData(crmCodeData
                                            .getFieldCodeDataId());
                                    }
                                }
                                //获取新增的数据
                                for (int i = 0; i < codeStrings.length; i++) {
                                    String newCode = codeStrings[i];
                                    if (!oldCodes.containsKey(newCode)) {
                                        CrmFieldCodeData tempCodeData = new CrmFieldCodeData();
                                        tempCodeData.setCreateUser(this.getLoginInfo().getUserId());
                                        tempCodeData.setFieldCodeDataKey(newCode);
                                        tempCodeData.setFieldCodeDataValue(newCode);
                                        tempCodeData.setFieldId(crmTemplateField
                                            .getTemplateFieldId());
                                        tempCodeData.setIsDel(0);
                                        tempCodeData.setSortNo(i + 1);
                                        crmFieldCodeDataService.addCrmFieldCodeData(tempCodeData);
                                    } else {
                                        CrmFieldCodeData tempCodeData = (CrmFieldCodeData) oldCodes
                                            .get(newCode);
                                        tempCodeData.setSortNo(i + 1);
                                        crmFieldCodeDataService
                                            .updateCrmFieldCodeData(tempCodeData);
                                    }
                                }

                            } else{
                            	for(CrmFieldCodeData cd : list){
                            		 crmFieldCodeDataService.deleteCrmFieldCodeData(cd.getFieldCodeDataId());
                            	}
                            }
                        }
                        
                        result = "SUCCESS";
                        //opeventLogService.addOpeventLog("模版字段", "编辑模版字段", 1, "编辑模版字段");
                    }
                } else {
                    result = "已存在相同名称的系统基础客户字段！";
                }
            } else {
                result = "已存在相同名称的系统客户字段！";
            }
            PrintWriter out = this.getResponse().getWriter();
            out.write(result);
        } catch (Exception e) {
            log.error("updateCrmTemplateField action error:" + e.getMessage());
            //opeventLogService.addOpeventLog("模版字段", "编辑模版字段", 0, "编辑模版字段");
        }
    }

    /**
     * 修改是否弹屏显示
     */
    public void updateCrmTemplateFieldIsPopup() {
        try {
            String result = "";
            if (crmTemplateField.getIsPopUp() == 0) {
                CrmTemplateField crmField = crmTemplateFieldService
                    .getCrmTemplateFieldById(crmTemplateField.getTemplateFieldId());
                crmField.setIsPopUp(crmTemplateField.getIsPopUp());
                crmTemplateFieldService.updateCrmTemplateField(crmField);
                result = "SUCCESS";
            } else {
                List<CrmTemplateField> list = crmTemplateFieldService.getPopupCrmTemplateField();
                if (list.size() >= 2) {
                	result = StringUtil.format("你已选择了 \"{0}>>{1}\",\"{2}>>{3}\" 目前只支持两个业务字段弹屏显示"
                			,crmTemplateService.getCrmTemplateById(list.get(0).getTemplateId()).getTemplateName(),list.get(0).getTemplateFieldName()
                			,crmTemplateService.getCrmTemplateById(list.get(1).getTemplateId()).getTemplateName(),list.get(1).getTemplateFieldName());
                } else {
                    CrmTemplateField crmField = crmTemplateFieldService
                        .getCrmTemplateFieldById(crmTemplateField.getTemplateFieldId());
                    crmField.setIsPopUp(crmTemplateField.getIsPopUp());
                    crmTemplateFieldService.updateCrmTemplateField(crmField);
                    result = "SUCCESS";
                }
            }
            PrintWriter out = this.getResponse().getWriter();
            out.write(result);
           // opeventLogService.addOpeventLog("模版字段", "编辑模版字段", 1, "编辑模版字段");
        } catch (Exception e) {
            log.error("updateCrmTemplateFieldIsPopup action error:" + e.getMessage());
           // opeventLogService.addOpeventLog("模版字段", "编辑模版字段", 0, "编辑模版字段");
        }
    }

    /**
     * 删除模版字段(伪删除)
     * @return
     */
    public void deleteCrmTemplateField() {
        try {
            String result = "";
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("queryColumn", crmTemplateField.getExtFieldName());
            if (crmTemplateFieldService.getCrmCustomerExtFieldIsNotNull(parameters).size() > 0) {
                result = "客户资料中已经使用过此业务字段，不能够删除！";
            } else {
                crmTemplateFieldService.deleteCrmTemplateField(crmTemplateField
                    .getTemplateFieldId());
                //关联删除
                if (crmTemplateField.getTemplateFieldType().equals("Select")
                    || crmTemplateField.getTemplateFieldType().equals("MultipleSelect")) {
                    crmFieldCodeDataService.deleteCrmFieldCodeDataByFieldId(crmTemplateField
                        .getTemplateFieldId());
                }
                result = "SUCCESS";
            }
            PrintWriter out = this.getResponse().getWriter();
            out.write(result);
           // opeventLogService.addOpeventLog("模版字段", "删除模版字段", 1, "删除模版字段");
        } catch (Exception e) {
            // TODO: handle exception
            log.error("deleteCrmCustomerType action error:" + e.getMessage());
          //  opeventLogService.addOpeventLog("模版字段", "删除模版字段", 1, "删除模版字段");
        }
    }

    /**
     * 上移和下移字段
     */
    public void moveFieldItems() {
        try {
            String result = crmTemplateFieldService.moveFieldItems(crmTemplateField
                .getTemplateFieldId(), moveType, crmTemplateField.getTemplateId());
            
            PrintWriter out = this.getResponse().getWriter();
            out.write(result);

        } catch (Exception e) {
            log.error("moveTypeItems action error:" + e.getMessage());
        }
    }

    /**
     * 判断自定义字段是否是客户表里已有的字段
     * @param fieldName
     * @return
     */
    private boolean IsSystemCusField(String fieldName){
        Map<String, Object> extFields = new HashMap<String, Object>();
        extFields.put("客户姓名","客户姓名");
        extFields.put("客户归属","客户归属");
        extFields.put("性别","性别");
        extFields.put("客户编号","客户编号");
        extFields.put("称谓","称谓");
        extFields.put("年龄","年龄");
        extFields.put("生日","生日");
        extFields.put("客户类型","客户类型");
        extFields.put("所处行业","所处行业");
        extFields.put("身份证","身份证");
        extFields.put("单位","单位");
        extFields.put("客户简介","客户简介");
        extFields.put("省份","省份");
        extFields.put("城市","城市");
        extFields.put("联系地址","联系地址");
        extFields.put("手机","手机");
        extFields.put("固话","固话");
        extFields.put("传真","传真");
        extFields.put("邮箱","邮箱");
        
        if (extFields.containsKey(fieldName)) {
            return false;
        } 
        return true;
    } 
}
