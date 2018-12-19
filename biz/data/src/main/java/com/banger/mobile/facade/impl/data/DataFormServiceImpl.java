/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yuanme
 * Create Date:2012-11-14
 */
package com.banger.mobile.facade.impl.data;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.data.DataFormDao;
import com.banger.mobile.domain.model.base.data.BaseDatForm;
import com.banger.mobile.domain.model.data.Audio;
import com.banger.mobile.domain.model.data.Form;
import com.banger.mobile.domain.model.data.SearchData;
import com.banger.mobile.facade.data.DataFormService;
import com.banger.mobile.util.StringUtil;

/**
 * @author yuanme
 * @version $Id: CustomerDataServiceImpl.java,v 0.1 2012-11-14 下午5:27:03 Administrator Exp $
 */
public class DataFormServiceImpl extends DataSuperServiceImpl implements DataFormService {
    private DataFormDao dataFormDao;

    SimpleDateFormat df0=new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
    SimpleDateFormat df1=new SimpleDateFormat("yyyy-MM-dd HH:mm:59");
    
    public void setDataFormDao(DataFormDao dataFormDao) {
		this.dataFormDao = dataFormDao;
	}
	
    /**
     * 根据查询条件 查找录音信息 搜索
     * @param parameterMap
     * @return
     */
	public List<Form> queryFormData(Map<String, Object> parameterMap,
			Page page) {
		return dataFormDao.queryFormData(parameterMap,page);
	}
	
	/**
     * 获得旧的录音备注信息
     * @param formId
     * @return
     */
	public String getFormRemark(int formId) {
		
		return dataFormDao.getFormRemark(formId);
	}
	
	/**
     * 修改录音备注信息
     * @param form
     * @return
     */
	public void updateFormRemark(Form form) {
		dataFormDao.updateFormRemark(form);
		
	}
	
	/**
	 * 批量添加附件资料
	 * @param photos
	 */
	 public void addDatFormBatch(List<Form> datForms){
		 dataFormDao.addDatFormBatch(datForms);
	 }
	//以下为新资料管理功能
	/**
     * 根据客户查看资料列表（分页）
     * @param parameterMap
     * @param page
     * @return 
     */
    public PageUtil<Form> getCustomerFormDataPage(Map<String, Object> parameterMap, Page page,SearchData searchData){
        if(searchData!=null){
            parameterMap.put("dataName", StringUtil.ReplaceSQLChar(searchData.getDataName().trim()));
            if(searchData.getCreateStartDate()!=null)parameterMap.put("createStartDate", df0.format(searchData.getCreateStartDate()));
            if(searchData.getCreateEndDate()!=null)parameterMap.put("createEndDate", df1.format(searchData.getCreateEndDate()));
            if(searchData.getUploadStartDate()!=null)parameterMap.put("uploadStartDate", df0.format(searchData.getUploadStartDate()));
            if(searchData.getUploadEndDate()!=null)parameterMap.put("uploadEndDate", df1.format(searchData.getUploadEndDate()));
        }
    	return dataFormDao.getCustomerFormDataPage(parameterMap, page);
    }


    /**
     * 获得贷款中各贷款流程的视频资料信息
     *
     * @param paramMap
     * @return
     */
    public List<Form> getFormOnLoanFlow(Map<String, Object> paramMap){
        return dataFormDao.getFormOnLoanFlow(paramMap);
    }

    /**
     * 添加表单数据
     * 
     * @param datForm
     */
    public void addDatForm(BaseDatForm datForm){
        dataFormDao.addDatForm(datForm);
    }

    /**
     * 查询当前贷款状态下的表单附件
     *
     * @param paramMap
     */
    public List<Form> selectFormAttachment(Map<String, Object> paramMap){
        return dataFormDao.selectFormAttachment(paramMap);
    }

    /**
     * 删除表单附件记录
     *
     * @param paramMap
     */
    public void delFormAttachment(Map<String, Object> paramMap){
        dataFormDao.delFormAttachment(paramMap);
    }
    
    public List<Form> selectFromListByLoanId(Integer loanId){
    	return dataFormDao.selectFromListByLoanId(loanId);
    }
}
