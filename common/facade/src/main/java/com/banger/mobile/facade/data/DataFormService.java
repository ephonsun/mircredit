/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :资料业务接口
 * Author     :yuanme
 * Version    :1.0
 * Create Date:May 3, 2012
 */
package com.banger.mobile.facade.data;

import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.base.data.BaseDatForm;
import com.banger.mobile.domain.model.data.Audio;
import com.banger.mobile.domain.model.data.Form;
import com.banger.mobile.domain.model.data.SearchData;

/**
 * @author yuanme
 * @version $Id: CustomerDataService.java,v 0.1 2012-5-24 下午04:42:52 yuanme Exp $
 */
public interface DataFormService extends DataSuperService{
   
    
    /**
     * 搜索录音
     * @param parmeterMap 存储条件的Map
     * @param page
     * @return 
     */
    public List<Form> queryFormData(Map<String, Object> parameterMap, Page page);
 	
 	/**
     * 拿到旧的录音备注信息
     * @param formId 录音的ID
     * @return  Old remark
     */
 	public String getFormRemark(int formId);
 	
 	/**
     * 修改备注信息
     * @param form 对象
     */
 	public void updateFormRemark(Form form);
 	
 	/**
	 * 批量添加附件资料
	 * @param photos
	 */
	 public void addDatFormBatch(List<Form> datForms);
 	
 	//以下为新资料管理功能
	/**
     * 根据客户查看资料列表（分页）
     * @param parameterMap
     * @param page
     * @return 
     */
    public PageUtil<Form> getCustomerFormDataPage(Map<String, Object> parameterMap, Page page,SearchData searchData);

    List<Form> getFormOnLoanFlow(Map<String, Object> paramMap);

    void addDatForm(BaseDatForm datForm);

    List<Form> selectFormAttachment(Map<String, Object> paramMap);

    void delFormAttachment(Map<String, Object> paramMap);
    
    List<Form> selectFromListByLoanId(Integer loanId);
}
