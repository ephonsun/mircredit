package com.banger.mobile.dao.data;

import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.data.Sms;

public interface DataSmsDao extends DataSuperDao{
	/**
     * 根据客户查看短信列表（分页）
     * @param parameterMap
     * @param page
     * @return 
     */
    public PageUtil<Sms> getCustomerSmsDataPage(Map<String, Object> parameterMap, Page page);
}