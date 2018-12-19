/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :客户数据 dao
 * Author     :yuanme
 * Create Date:2012-5-29
 */
package com.banger.mobile.dao.data;

import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.base.data.BaseDatForm;
import com.banger.mobile.domain.model.data.CustomerData;
import com.banger.mobile.domain.model.data.Event;
import com.banger.mobile.domain.model.data.Form;

/**
 * @author yuanme
 * @version $Id: DataAuthDao.java,v 0.1 2012-5-29 上午10:50:22 yuanme Exp $
 */
public interface DataFormDao extends DataSuperDao{
   
 
	/**
    * 搜索视频
    * @param parmeterMap 存储条件的Map
    * @param page
    * @return
    */
	public List<Form> queryFormData(Map<String, Object> parameterMap, Page page);
	
	
	/**
     * 拿到旧的视频备注信息
     * @param formId 视频的ID
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
    public PageUtil<Form> getCustomerFormDataPage(Map<String, Object> parameterMap, Page page);

    List<Form> getFormOnLoanFlow(Map<String, Object> paramMap);

    void addDatForm(BaseDatForm datForm);

    List<Form> selectFormAttachment(Map<String, Object> paramMap);

    void delFormAttachment(Map<String, Object> paramMap);
    
    List<Form> selectFromListByLoanId(Integer loanId);
}
