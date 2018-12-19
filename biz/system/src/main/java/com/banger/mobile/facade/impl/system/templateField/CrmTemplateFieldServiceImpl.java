/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :模版字段维护类...
 * Author     :QianJie
 * Create Date:May 29, 2012
 */
package com.banger.mobile.facade.impl.system.templateField;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.banger.mobile.dao.templateField.CrmTemplateFieldDao;
import com.banger.mobile.domain.model.system.CrmCustomerType;
import com.banger.mobile.domain.model.templateField.CrmTemplateField;
import com.banger.mobile.facade.templateField.CrmTemplateFieldService;

/**
 * @author QianJie
 * @version $Id: CrmTemplateFieldServiceImpl.java,v 0.1 May 29, 2012 2:40:02 PM QianJie Exp $
 */
public class CrmTemplateFieldServiceImpl implements CrmTemplateFieldService {

    private CrmTemplateFieldDao crmTemplateFieldDao;

    public void setCrmTemplateFieldDao(CrmTemplateFieldDao crmTemplateFieldDao) {
        this.crmTemplateFieldDao = crmTemplateFieldDao;
    }

    /**
     *  根据模版ID得到模版字段集合
     * @param templateId
     * @return
     */
    public List<CrmTemplateField> getCrmTemplateFieldListByTemplateId(int templateId) {
        return crmTemplateFieldDao.getCrmTemplateFieldListByTemplateId(templateId);
    }

    /**
     * 添加一个模版字段
     * @param crmTemplateField
     * @see com.banger.mobile.dao.templateField.CrmTemplateFieldDao#addCrmTemplateField(com.banger.mobile.domain.model.templateField.CrmTemplateField)
     */
    public int addCrmTemplateField(CrmTemplateField crmTemplateField) {
        CrmTemplateField maxField = crmTemplateFieldDao
            .getMinSortNoCrmTemplateField(crmTemplateField.getTemplateId());
        if (maxField == null) {
            crmTemplateField.setSortNo(1);//排序+1
        } else {
            crmTemplateField.setSortNo(maxField.getSortNo() + 1);//排序+1
        }
        return crmTemplateFieldDao.addCrmTemplateField(crmTemplateField);
    }

    /**
     * 修改一个模版字段
     * @param crmTemplateField
     * @see com.banger.mobile.dao.templateField.CrmTemplateFieldDao#updateCrmTemplateField(com.banger.mobile.domain.model.templateField.CrmTemplateField)
     */
    public void updateCrmTemplateField(CrmTemplateField crmTemplateField) {
        crmTemplateFieldDao.updateCrmTemplateField(crmTemplateField);
    }

    /**
     * 删除一个模版字段
     * @param id
     * @see com.banger.mobile.dao.templateField.CrmTemplateFieldDao#deleteCrmTemplateField(int)
     */
    public void deleteCrmTemplateField(int id) {
        crmTemplateFieldDao.deleteCrmTemplateField(id);
    }

    /**
     * 根据Id获取模版字段
     * @param id
     * @return
     * @see com.banger.mobile.dao.templateField.CrmTemplateFieldDao#getCrmTemplateFieldById(int)
     */
    public CrmTemplateField getCrmTemplateFieldById(int id) {
        return crmTemplateFieldDao.getCrmTemplateFieldById(id);
    }

    /**
     * 根据模版字段名称获取拥有相同模版字段名称的数据
     * @param crmTemplateField
     * @return
     * @see com.banger.mobile.dao.templateField.CrmTemplateFieldDao#getSameCrmTemplateFieldByName(com.banger.mobile.domain.model.templateField.CrmTemplateField)
     */
    public List<CrmTemplateField> getSameCrmTemplateFieldByName(CrmTemplateField crmTemplateField) {
        return crmTemplateFieldDao.getSameCrmTemplateFieldByName(crmTemplateField);
    }

    /**
     * 获取现有模版字段是弹屏显示的数据
     * @return
     * @see com.banger.mobile.facade.templateField.CrmTemplateFieldService#getPopupCrmTemplateField()
     */
    public List<CrmTemplateField> getPopupCrmTemplateField() {
        return crmTemplateFieldDao.getPopupCrmTemplateField();
    }

    /**
     * 上移或下移客户类型
     * @param id
     * @param moveType
     * @return
     * @see com.banger.mobile.facade.system.CrmCustomerTypeService#moveTypeItems(int, java.lang.String)
     */
    public String moveFieldItems(int id, String moveType, int templateId) {
        CrmTemplateField crmField = crmTemplateFieldDao.getCrmTemplateFieldById(id);
        if (crmField != null) {
            int curSortNo = crmField.getSortNo();
            //上移
            if (moveType.equals("Up")) {
                //如果是最顶层则返回SUCCESS
                CrmTemplateField maxField = crmTemplateFieldDao
                    .getMaxSortNoCrmTemplateField(templateId);
                if (maxField.getSortNo() != crmField.getSortNo()) {
                    Map<String, Object> parameterMap = new HashMap<String, Object>();
                    parameterMap.put("moveType", "Up");
                    parameterMap.put("sortNo", curSortNo);
                    parameterMap.put("templateId", templateId);
                    CrmTemplateField needMove = crmTemplateFieldDao
                        .getNeedMoveCrmTemplateField(parameterMap);
                    //移动
                    crmField.setSortNo(needMove.getSortNo());
                    needMove.setSortNo(curSortNo);
                    //数据持久化
                    crmTemplateFieldDao.updateCrmTemplateField(crmField);
                    crmTemplateFieldDao.updateCrmTemplateField(needMove);
                    return "SUCCESS";
                } else {
                    return "ERROR";
                }
            } else {
                //下移
                //如果是最底层则返回SUCCESS
                CrmTemplateField minField = crmTemplateFieldDao
                    .getMinSortNoCrmTemplateField(templateId);
                if (minField.getSortNo() != crmField.getSortNo()) {
                    Map<String, Object> parameterMap = new HashMap<String, Object>();
                    parameterMap.put("moveType", "Down");
                    parameterMap.put("sortNo", curSortNo);
                    parameterMap.put("templateId", templateId);
                    CrmTemplateField needMove = crmTemplateFieldDao
                        .getNeedMoveCrmTemplateField(parameterMap);
                    //移动
                    crmField.setSortNo(needMove.getSortNo());
                    needMove.setSortNo(curSortNo);
                    //数据持久化
                    crmTemplateFieldDao.updateCrmTemplateField(crmField);
                    crmTemplateFieldDao.updateCrmTemplateField(needMove);
                    return "SUCCESS";
                } else {
                    return "ERROR";
                }
            }
        } else {
            return "SUCCESS";
        }
    }

    /**
     * 获取所有客户模版字段
     * @return
     * @see com.banger.mobile.facade.templateField.CrmTemplateFieldService#getAllCrmTemplateFieldList()
     */
    public List<CrmTemplateField> getAllCrmTemplateFieldList() {
        return this.getAllCrmTemplateFieldList(true);
    }

    public List<CrmTemplateField> getAllCrmTemplateFieldList(boolean isCache) {
        return crmTemplateFieldDao.getAllCrmTemplateField(isCache);
    }

    /**
     * 获取所有产品模版字段
     * @return
     * @see com.banger.mobile.facade.templateField.CrmTemplateFieldService#getAllPdtCrmTemplateFieldList()
     */
    public List<CrmTemplateField> getAllPdtCrmTemplateFieldList() {
        return crmTemplateFieldDao.getAllPdtCrmTemplateField();
    }

    /**
     * 根据数据类型返回未被使用的自定义字段，如果没有可以使用的则返回空
     * @param dataType
     * @return
     */
    public String getNotUsedExtField(String dataType) {
        String extField = "";
        Map<String, Object> extFields = new HashMap<String, Object>();
        if (dataType.equals("Date")) {
            extFields = getAllDateExtField();
        } else if (dataType.equals("Number")) {
            extFields = getAllNumberExtField();
        } else if (dataType.equals("TextArea") || dataType.equals("Select")
                   || dataType.equals("MultipleSelect")) {
            extFields = getAllTextAreaExtField();
        } else {
            extFields = getAllTextExtField();
        }

        Map<String, Object> usedFields = new HashMap<String, Object>();
        List<CrmTemplateField> list = getAllCrmTemplateFieldList(false);
        for (CrmTemplateField crmTemplateField : list) {
            //使用短文本的字段类型
            if (crmTemplateField.getTemplateFieldType().equals("YesNo")
                || crmTemplateField.getTemplateFieldType().equals("Text")) {
                //如果用户需要的是短文本字段，则统计改字段为已用
                if (dataType.equals("YesNo") || dataType.equals("Text")) {
                    usedFields.put(crmTemplateField.getExtFieldName(),
                        crmTemplateField.getExtFieldName());
                }
            } else if (crmTemplateField.getTemplateFieldType().equals("TextArea")
                       || crmTemplateField.getTemplateFieldType().equals("Select")
                       || crmTemplateField.getTemplateFieldType().equals("MultipleSelect")) {//使用长文本的字段
                //如果用户需要的是长文本字段，则统计改字段为已用
                if (dataType.equals("TextArea") || dataType.equals("Select")
                    || dataType.equals("MultipleSelect")) {
                    usedFields.put(crmTemplateField.getExtFieldName(),
                        crmTemplateField.getExtFieldName());
                }
            } else {//其他类型的字段，日期，数值
                    //如果用户需要的是日期或者数值字段，则统计改字段为已用
                if (dataType.equals(crmTemplateField.getTemplateFieldType())) {
                    usedFields.put(crmTemplateField.getExtFieldName(),
                        crmTemplateField.getExtFieldName());
                }
            }

        }

        Iterator iter = extFields.keySet().iterator();
        while (iter.hasNext()) {
            Object key = iter.next();
            //Object val = usedFields.get(key); 
            if (!usedFields.containsKey(key)) {
                extField = key.toString();
                break;
            }
        }
        return extField;
    }

    /**
     * 根据数据类型返回未被使用的产品自定义字段，如果没有可以使用的则返回空
     * @param dataType
     * @return
     */
    public String getNotUsedPdtExtField(String dataType) {
        String extField = "";
        Map<String, Object> extFields = new HashMap<String, Object>();
        if (dataType.equals("Date")) {
            extFields = getAllDateExtField();
        } else if (dataType.equals("Number")) {
            extFields = getAllNumberExtField();
        } else if (dataType.equals("TextArea") || dataType.equals("Select")
                   || dataType.equals("MultipleSelect")) {
            extFields = getAllTextAreaExtField();
        } else {
            extFields = getAllTextExtField();
        }

        Map<String, Object> usedFields = new HashMap<String, Object>();
        List<CrmTemplateField> list = getAllPdtCrmTemplateFieldList();
        for (CrmTemplateField crmTemplateField : list) {
            //使用短文本的字段类型
            if (crmTemplateField.getTemplateFieldType().equals("YesNo")
                || crmTemplateField.getTemplateFieldType().equals("Text")) {
                //如果用户需要的是短文本字段，则统计改字段为已用
                if (dataType.equals("YesNo") || dataType.equals("Text")) {
                    usedFields.put(crmTemplateField.getExtFieldName(),
                        crmTemplateField.getExtFieldName());
                }
            } else if (crmTemplateField.getTemplateFieldType().equals("TextArea")
                       || crmTemplateField.getTemplateFieldType().equals("Select")
                       || crmTemplateField.getTemplateFieldType().equals("MultipleSelect")) {//使用长文本的字段
                //如果用户需要的是长文本字段，则统计改字段为已用
                if (dataType.equals("TextArea") || dataType.equals("Select")
                    || dataType.equals("MultipleSelect")) {
                    usedFields.put(crmTemplateField.getExtFieldName(),
                        crmTemplateField.getExtFieldName());
                }
            } else {//其他类型的字段，日期，数值
                    //如果用户需要的是日期或者数值字段，则统计改字段为已用
                if (dataType.equals(crmTemplateField.getTemplateFieldType())) {
                    usedFields.put(crmTemplateField.getExtFieldName(),
                        crmTemplateField.getExtFieldName());
                }
            }
        }

        Iterator iter = extFields.keySet().iterator();
        while (iter.hasNext()) {
            Object key = iter.next();
            //Object val = usedFields.get(key); 
            if (!usedFields.containsKey(key)) {
                extField = key.toString();
                break;
            }
        }
        return extField;
    }

    public Map<String, Object> getAllDateExtField() {
        Map<String, Object> extFields = new HashMap<String, Object>();
        extFields.put("EXT_DATE01", "EXT_DATE01");
        extFields.put("EXT_DATE02", "EXT_DATE02");
        extFields.put("EXT_DATE03", "EXT_DATE03");
        extFields.put("EXT_DATE04", "EXT_DATE04");
        extFields.put("EXT_DATE05", "EXT_DATE05");
        extFields.put("EXT_DATE06", "EXT_DATE06");
        extFields.put("EXT_DATE07", "EXT_DATE07");
        extFields.put("EXT_DATE08", "EXT_DATE08");
        extFields.put("EXT_DATE09", "EXT_DATE09");
        extFields.put("EXT_DATE10", "EXT_DATE10");
        extFields.put("EXT_DATE11", "EXT_DATE11");
        extFields.put("EXT_DATE12", "EXT_DATE12");
        extFields.put("EXT_DATE13", "EXT_DATE13");
        extFields.put("EXT_DATE14", "EXT_DATE14");
        extFields.put("EXT_DATE15", "EXT_DATE15");
        extFields.put("EXT_DATE16", "EXT_DATE16");
        extFields.put("EXT_DATE17", "EXT_DATE17");
        extFields.put("EXT_DATE18", "EXT_DATE18");
        extFields.put("EXT_DATE19", "EXT_DATE19");
        extFields.put("EXT_DATE20", "EXT_DATE20");
        return extFields;
    }

    public Map<String, Object> getAllNumberExtField() {
        Map<String, Object> extFields = new HashMap<String, Object>();
        extFields.put("EXT_FLOAT01", "EXT_FLOAT01");
        extFields.put("EXT_FLOAT02", "EXT_FLOAT02");
        extFields.put("EXT_FLOAT03", "EXT_FLOAT03");
        extFields.put("EXT_FLOAT04", "EXT_FLOAT04");
        extFields.put("EXT_FLOAT05", "EXT_FLOAT05");
        extFields.put("EXT_FLOAT06", "EXT_FLOAT06");
        extFields.put("EXT_FLOAT07", "EXT_FLOAT07");
        extFields.put("EXT_FLOAT08", "EXT_FLOAT08");
        extFields.put("EXT_FLOAT09", "EXT_FLOAT09");
        extFields.put("EXT_FLOAT10", "EXT_FLOAT10");
        extFields.put("EXT_FLOAT11", "EXT_FLOAT11");
        extFields.put("EXT_FLOAT12", "EXT_FLOAT12");
        extFields.put("EXT_FLOAT13", "EXT_FLOAT13");
        extFields.put("EXT_FLOAT14", "EXT_FLOAT14");
        extFields.put("EXT_FLOAT15", "EXT_FLOAT15");
        extFields.put("EXT_FLOAT16", "EXT_FLOAT16");
        extFields.put("EXT_FLOAT17", "EXT_FLOAT17");
        extFields.put("EXT_FLOAT18", "EXT_FLOAT18");
        extFields.put("EXT_FLOAT19", "EXT_FLOAT19");
        extFields.put("EXT_FLOAT20", "EXT_FLOAT20");
        return extFields;
    }

    public Map<String, Object> getAllTextAreaExtField() {
        Map<String, Object> extFields = new HashMap<String, Object>();
        extFields.put("EXT_LONG_STRING01", "EXT_LONG_STRING01");
        extFields.put("EXT_LONG_STRING02", "EXT_LONG_STRING02");
        extFields.put("EXT_LONG_STRING03", "EXT_LONG_STRING03");
        extFields.put("EXT_LONG_STRING04", "EXT_LONG_STRING04");
        extFields.put("EXT_LONG_STRING05", "EXT_LONG_STRING05");
        extFields.put("EXT_LONG_STRING06", "EXT_LONG_STRING06");
        extFields.put("EXT_LONG_STRING07", "EXT_LONG_STRING07");
        extFields.put("EXT_LONG_STRING08", "EXT_LONG_STRING08");
        extFields.put("EXT_LONG_STRING09", "EXT_LONG_STRING09");
        extFields.put("EXT_LONG_STRING10", "EXT_LONG_STRING10");
        extFields.put("EXT_LONG_STRING11", "EXT_LONG_STRING11");
        extFields.put("EXT_LONG_STRING12", "EXT_LONG_STRING12");
        extFields.put("EXT_LONG_STRING13", "EXT_LONG_STRING13");
        extFields.put("EXT_LONG_STRING14", "EXT_LONG_STRING14");
        extFields.put("EXT_LONG_STRING15", "EXT_LONG_STRING15");
        extFields.put("EXT_LONG_STRING16", "EXT_LONG_STRING16");
        extFields.put("EXT_LONG_STRING17", "EXT_LONG_STRING17");
        extFields.put("EXT_LONG_STRING18", "EXT_LONG_STRING18");
        extFields.put("EXT_LONG_STRING19", "EXT_LONG_STRING19");
        extFields.put("EXT_LONG_STRING20", "EXT_LONG_STRING20");
        return extFields;
    }

    public Map<String, Object> getAllTextExtField() {
        Map<String, Object> extFields = new HashMap<String, Object>();
        extFields.put("EXT_STRING01", "");
        extFields.put("EXT_STRING02", "");
        extFields.put("EXT_STRING03", "");
        extFields.put("EXT_STRING04", "");
        extFields.put("EXT_STRING05", "");
        extFields.put("EXT_STRING06", "");
        extFields.put("EXT_STRING07", "");
        extFields.put("EXT_STRING08", "");
        extFields.put("EXT_STRING09", "");
        extFields.put("EXT_STRING10", "");
        extFields.put("EXT_STRING11", "");
        extFields.put("EXT_STRING12", "");
        extFields.put("EXT_STRING13", "");
        extFields.put("EXT_STRING14", "");
        extFields.put("EXT_STRING15", "");
        extFields.put("EXT_STRING16", "");
        extFields.put("EXT_STRING17", "");
        extFields.put("EXT_STRING18", "");
        extFields.put("EXT_STRING19", "");
        extFields.put("EXT_STRING20", "");
        extFields.put("EXT_STRING21", "");
        extFields.put("EXT_STRING22", "");
        extFields.put("EXT_STRING23", "");
        extFields.put("EXT_STRING24", "");
        extFields.put("EXT_STRING25", "");
        extFields.put("EXT_STRING26", "");
        extFields.put("EXT_STRING27", "");
        extFields.put("EXT_STRING28", "");
        extFields.put("EXT_STRING29", "");
        extFields.put("EXT_STRING30", "");
        extFields.put("EXT_STRING31", "");
        extFields.put("EXT_STRING32", "");
        extFields.put("EXT_STRING33", "");
        extFields.put("EXT_STRING34", "");
        extFields.put("EXT_STRING35", "");
        extFields.put("EXT_STRING36", "");
        extFields.put("EXT_STRING37", "");
        extFields.put("EXT_STRING38", "");
        extFields.put("EXT_STRING39", "");
        extFields.put("EXT_STRING40", "");
        extFields.put("EXT_STRING41", "");
        extFields.put("EXT_STRING42", "");
        extFields.put("EXT_STRING43", "");
        extFields.put("EXT_STRING44", "");
        extFields.put("EXT_STRING45", "");
        extFields.put("EXT_STRING46", "");
        extFields.put("EXT_STRING47", "");
        extFields.put("EXT_STRING48", "");
        extFields.put("EXT_STRING49", "");
        extFields.put("EXT_STRING50", "");
        extFields.put("EXT_STRING51", "");
        extFields.put("EXT_STRING52", "");
        extFields.put("EXT_STRING53", "");
        extFields.put("EXT_STRING54", "");
        extFields.put("EXT_STRING55", "");
        extFields.put("EXT_STRING56", "");
        extFields.put("EXT_STRING57", "");
        extFields.put("EXT_STRING58", "");
        extFields.put("EXT_STRING59", "");
        extFields.put("EXT_STRING60", "");
        return extFields;
    }

    /**
     * 获取所有模版自定义字段值
     * @param parameters
     * @return
     * @see com.banger.mobile.facade.templateField.CrmTemplateFieldService#getCrmCustomerExtFieldByCustomerId(java.util.Map)
     */
    public List getCrmCustomerExtFieldByCustomerId(Map<String, Object> parameters) {
        return crmTemplateFieldDao.getCrmCustomerExtFieldByCustomerId(parameters);
    }

    /**
     * 根据模版Id删除模版字段
     * @param templateId
     */
    public void deleteCrmTemplateFieldByTemplateId(int templateId) {
        crmTemplateFieldDao.deleteCrmTemplateFieldByTemplateId(templateId);
    }

    /**
     * 获取某个自定义字段值不为空的数据
     * @param parameters
     * @return
     * @see com.banger.mobile.facade.templateField.CrmTemplateFieldService#getCrmCustomerExtFieldIsNotNull(java.util.Map)
     */
    public List getCrmCustomerExtFieldIsNotNull(Map<String, Object> parameters) {
        return crmTemplateFieldDao.getCrmCustomerExtFieldIsNotNull(parameters);
    }

    /**
     * 获取某个产品自定义字段值不为空的数据
     */
    public List getPdtProductextFieldIsNotNull(Map<String, Object> parameters) {
        return crmTemplateFieldDao.getPdtProductextFieldIsNotNull(parameters);
    }

    /**
     * 获取新增或编辑模版字段是否已存在在基础模版字段中
     */
    public boolean getHasSameBasicCrmTemplateField(CrmTemplateField crmTemplateField) {
        List<CrmTemplateField> list = crmTemplateFieldDao
            .getHasSameBasicCrmTemplateField(crmTemplateField);
        if (list.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

}
