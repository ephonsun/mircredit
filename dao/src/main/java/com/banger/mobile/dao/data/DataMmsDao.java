package com.banger.mobile.dao.data;

import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.data.Mms;

public interface DataMmsDao extends DataSuperDao{
	//以下为新资料管理功能
	/**
     * 根据客户查看短信列表（分页）
     * @param parameterMap
     * @param page
     * @return 
     */
    public PageUtil<Mms> getCustomerMmsDataPage(Map<String, Object> parameterMap, Page page);
}