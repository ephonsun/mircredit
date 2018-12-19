/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :模版字段action...
 * Author     :QianJie
 * Create Date:May 30, 2012
 */
package com.banger.mobile.webapp.action.pdtTemplateField;

import java.awt.Event;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.tools.ant.util.ClasspathUtils.Delegate;

import com.banger.mobile.domain.model.fieldCodeData.CrmFieldCodeData;
import com.banger.mobile.domain.model.fieldType.CrmFieldType;
import com.banger.mobile.domain.model.templateField.CrmTemplateField;
import com.banger.mobile.facade.fieldCodeData.CrmFieldCodeDataService;
import com.banger.mobile.facade.fieldType.CrmFieldTypeService;
import com.banger.mobile.facade.log.OpeventLogService;
import com.banger.mobile.facade.templateField.CrmTemplateFieldService;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * @author QianJie
 * @version $Id: CrmTemplateFieldAction.java,v 0.1 May 30, 2012 11:41:51 AM QianJie Exp $
 */
public class PdtCrmTemplateFieldAction extends BaseAction {

    private static final long       serialVersionUID = 8898514142744511693L;

    private CrmTemplateFieldService crmTemplateFieldService;

    public void setCrmTemplateFieldService(CrmTemplateFieldService crmTemplateFieldService) {
        this.crmTemplateFieldService = crmTemplateFieldService;
    }

    private OpeventLogService opeventLogService;

    public void setOpeventLogService(OpeventLogService opeventLogService) {
        this.opeventLogService = opeventLogService;
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
            // TODO: handle exception
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
            //是否跟系统字段冲突
            if (IsSystemPdtField(crmTemplateField.getTemplateFieldName())) {
                String extField = crmTemplateFieldService.getNotUsedPdtExtField(crmTemplateField
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
                        result = "已存在相同名称的业务字段！";
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
                result = "已存在相同名称的系统产品字段！";
            }
            PrintWriter out = this.getResponse().getWriter();
            out.write(result);
            opeventLogService.addOpeventLog("模版字段", "新增模版字段", 1, "新增模版字段");
        } catch (Exception e) {
            log.error("addCrmTemplateField action error:" + e.getMessage());
            opeventLogService.addOpeventLog("模版字段", "新增模版字段", 0, "新增模版字段");
        }
    }

    /**
     * 编辑模版字段
     */
    public void updateCrmTemplateField() {
        try {
            String result = "";
            //是否跟系统字段冲突
            if (IsSystemPdtField(crmTemplateField.getTemplateFieldName())) {
                List<CrmTemplateField> listSame = crmTemplateFieldService
                    .getSameCrmTemplateFieldByName(crmTemplateField);

                if (listSame.size() > 0) {
                    result = "已存在相同名称的业务字段！";
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
                        if (!"".equals(codeData)) {
                            List<CrmFieldCodeData> list = crmFieldCodeDataService
                                .getCrmFieldCodeDataListByFieldId(crmTemplateField
                                    .getTemplateFieldId());
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
                                    tempCodeData.setFieldId(crmTemplateField.getTemplateFieldId());
                                    tempCodeData.setIsDel(0);
                                    tempCodeData.setSortNo(i + 1);
                                    crmFieldCodeDataService.addCrmFieldCodeData(tempCodeData);
                                } else {
                                    CrmFieldCodeData tempCodeData = (CrmFieldCodeData) oldCodes
                                        .get(newCode);
                                    tempCodeData.setSortNo(i + 1);
                                    crmFieldCodeDataService.updateCrmFieldCodeData(tempCodeData);
                                }
                            }

                        }
                    }
                    result = "SUCCESS";
                    opeventLogService.addOpeventLog("模版字段", "编辑模版字段", 1, "编辑模版字段");
                }
            } else {
                result = "已存在相同名称的系统产品字段！";
            }
            PrintWriter out = this.getResponse().getWriter();
            out.write(result);
        } catch (Exception e) {
            log.error("updateCrmTemplateField action error:" + e.getMessage());
            opeventLogService.addOpeventLog("模版字段", "编辑模版字段", 0, "编辑模版字段");
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
                    result = crmTemplateField.getTemplateFieldId() + "";
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
            opeventLogService.addOpeventLog("模版字段", "编辑模版字段", 1, "编辑模版字段");
        } catch (Exception e) {
            log.error("updateCrmTemplateFieldIsPopup action error:" + e.getMessage());
            opeventLogService.addOpeventLog("模版字段", "编辑模版字段", 0, "编辑模版字段");
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
            if (crmTemplateFieldService.getPdtProductextFieldIsNotNull(parameters).size() > 0) {
                result = "产品资料中已经使用过此业务字段，不能够删除！";
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
            opeventLogService.addOpeventLog("模版字段", "删除模版字段", 1, "删除模版字段");
        } catch (Exception e) {
            // TODO: handle exception
            log.error("deleteCrmCustomerType action error:" + e.getMessage());
            opeventLogService.addOpeventLog("模版字段", "删除模版字段", 1, "删除模版字段");
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
     * 判断自定义字段是否是产品表里已有的字段
     * @param fieldName
     * @return
     */
    private boolean IsSystemPdtField(String fieldName) {
        Map<String, Object> extFields = new HashMap<String, Object>();
        extFields.put("产品名称", "产品名称");
        extFields.put("从属系列", "从属系列");
        extFields.put("产品编号", "产品编号");
        extFields.put("产品类型", "产品类型");
        extFields.put("收益类型", "收益类型");
        extFields.put("到期日", "到期日");
        extFields.put("发售期", "发售期");
        extFields.put("理财时长", "理财时长");
        extFields.put("预期收益率", "预期收益率");
        extFields.put("风险评级", "风险评级");
        extFields.put("销售状态", "销售状态");
        extFields.put("审核状态", "审核状态");
        extFields.put("选择产品自定义模版", "选择产品自定义模版");
        extFields.put("币种", "币种");
        extFields.put("认购价格", "认购价格");
        extFields.put("首次认购下限", "首次认购下限");
        extFields.put("认购费用比例", "认购费用比例");
        extFields.put("认购基数", "认购基数");
        extFields.put("认购单笔上限", "认购单笔上限");
        extFields.put("认购单笔下限", "认购单笔下限");
        extFields.put("申购费用比例", "申购费用比例");
        extFields.put("申购基数", "申购基数");
        extFields.put("申购单笔上限", "申购单笔上限");
        extFields.put("申购单笔下限", "申购单笔下限");
        extFields.put("赎回费用比例", "赎回费用比例");
        extFields.put("赎回基数", "赎回基数");
        extFields.put("赎回单笔上限", "赎回单笔上限");
        extFields.put("赎回单笔下限", "赎回单笔下限");
        extFields.put("最低申购金额", "最低申购金额");
        if (extFields.containsKey(fieldName)) {
            return false;
        }
        //return extFields;
        return true;
    }
}
