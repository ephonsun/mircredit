/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :客户资料 dao实现
 * Author     :yuanme
 * Create Date:2012-5-29
 */
package com.banger.mobile.dao.data.ibatis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.data.DataFormDao;
import com.banger.mobile.domain.model.base.data.BaseDatForm;
import com.banger.mobile.domain.model.data.Form;
import com.banger.mobile.domain.model.data.Photo;
/**
 * @author yuanme
 * @version $Id: DataAuthDaoiBatis.java,v 0.1 2012-5-29 上午10:51:00 yuanme Exp $
 */
public class DataFormDaoiBatis extends DataSuperDaoiBatis implements DataFormDao {

    public DataFormDaoiBatis() {
        super(DataFormDao.class);
    }

    /**
     * @param persistentClass
     */
    @SuppressWarnings("unchecked")
    public DataFormDaoiBatis(Class persistentClass) {
        super(persistentClass);
    }


    public List<Form> queryFormData(Map<String, Object> parameterMap,
                                    Page page) {

        List<Form> formList = this.findQueryPage("queryFormData", "queryFormDataCount", parameterMap, page);
        return formList;
    }

    public String getFormRemark(int formId) {

        return (String) this.getSqlMapClientTemplate().queryForObject("getFormRemark", formId);
    }

    public void updateFormRemark(Form form) {
        this.getSqlMapClientTemplate().update("updateFormRemark", form);

    }

    /**
     * 批量添加附件资料
     * @param photos
     */
    public void addDatFormBatch(List<Form> datForms){
        this.exectuteBatchInsert("addDatForm", datForms);
    }

    //以下为新资料管理功能
    /**
     * 根据客户查看资料列表（分页）
     * @param parameterMap
     * @param page
     * @return
     */
    public PageUtil<Form> getCustomerFormDataPage(Map<String, Object> parameterMap, Page page) {
        List<Form> list = (List<Form>) this.findQueryPage(
                "getCustomerFormDataPage", "getCustomerFormDataCount", parameterMap, page);
        return new PageUtil<Form>(list, page);
    }

    /**
     * 获得贷款中各贷款流程的视频资料信息
     *
     * @param paramMap
     * @return
     */
    @Override
    public List<Form> getFormOnLoanFlow(Map<String,Object> paramMap){
        return this.getSqlMapClientTemplate().queryForList("getFormOnLoanFlow",paramMap);
    }

    /**
     * 插入表单数据
     *
     * @param datForm
     */
    @Override
    public void addDatForm(BaseDatForm datForm){
        this.getSqlMapClientTemplate().insert("addDatForm",datForm);
    }

    /**
     * 查询当前贷款状态下的表单附件
     *
     * @param paramMap
     */
    @Override
    public List<Form> selectFormAttachment(Map<String, Object> paramMap){
        return this.getSqlMapClientTemplate().queryForList("selectFormAttachment",paramMap);
    }

    /**
     * 删除表单附件记录
     *
     * @param paramMap
     */
    @Override
    public void delFormAttachment(Map<String, Object> paramMap){
        this.getSqlMapClientTemplate().delete("delFormAttachment",paramMap);
    }

    public List<Form> selectFromListByLoanId(Integer loanId){
        return this.getSqlMapClientTemplate().queryForList("selectFromListByLoanId",loanId);
    }
}