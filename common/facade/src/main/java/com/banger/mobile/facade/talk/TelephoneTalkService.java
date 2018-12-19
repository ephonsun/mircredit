/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :录音信息业务实现类
 * Author     :zhangxiang
 * Version    :1.0
 * Create Date:May 3, 2012
 */
package com.banger.mobile.facade.talk;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.customer.CrmCustomer;
import com.banger.mobile.domain.model.talk.TlkSpecialNumber;
import com.banger.mobile.domain.model.user.SysTalkUserBean;

public interface TelephoneTalkService {
	/**
	 * 通过电话号码查找客户
	 */
	public List<CrmCustomer> getCrmCustomerByTel(String tel);
	
	/**
	 * 通过电话号码查找客户
	 */
	public List<CrmCustomer> getCrmCustomerByTel(String tel,String cityCode);
	
	/**
	 * 通过电话号码查地区码
	 * @param code
	 * @return
	 */
	public String getAreaCodeByNumber(String number);
	
	/**
	 * 通过区域代码查名称
	 * @param code
	 * @return
	 */
	public String getPhoneAreaNameByCode(String code);
	
    /**
	 * 判断是否为手机号码
	 * @param number
	 * @return
	 */
	public boolean isMobileNumber(String number);
	
	/**
	 * 得到来电转接用户
	 * @param condition
	 * @param page
	 * @return
	 */
	public PageUtil<SysTalkUserBean> getTalkForwardUsers(Map<String, Object> condition,Page page);
	
	/**
	 * 得到屏幕取词的客户
	 * @param account
	 * @param telNum
	 * @return
	 */
	public JSONArray getRecentlyCustomer(String account,String telNum);
	
	/**
	 * 得到所有特殊号码
	 * @return
	 */
	public Map<String,TlkSpecialNumber> getSpecialNumbers();
	
	/**
	 * 是否为特殊号码
	 * @param number
	 * @return
	 */
	public boolean isSpecialNumber(String number);
	
}
