/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :电话通话类
 * Author     :zsw
 * Create Date:2012-5-24
 */
package com.banger.mobile.facade.impl.customer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.customer.CrmCustomerDao;
import com.banger.mobile.dao.talk.TlkMobileAttibutionDao;
import com.banger.mobile.dao.talk.TlkSpecialNumberDao;
import com.banger.mobile.dao.talk.TlkTelephoneCodeDao;
import com.banger.mobile.dao.user.SysUserDao;
import com.banger.mobile.domain.model.customer.CrmCustomer;
import com.banger.mobile.domain.model.talk.TlkSpecialNumber;
import com.banger.mobile.domain.model.user.SysTalkUserBean;
import com.banger.mobile.domain.model.user.SysUser;
import com.banger.mobile.facade.customer.CrmCustomerService;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.record.RecordInfoService;
import com.banger.mobile.facade.talk.TelephoneTalkService;
import com.banger.mobile.facade.user.SysUserService;
import com.banger.mobile.util.StringUtil;
 
public class TelephoneTalkServiceImpl implements TelephoneTalkService {
	private CrmCustomerDao crmCustomerDao;
	private TlkMobileAttibutionDao mobileAreaDao;
	private TlkSpecialNumberDao specialNumberDao;
	private TlkTelephoneCodeDao telephoneAreaDao;
	private RecordInfoService recordService;
    private DeptFacadeService deptService;
    private SysUserService userService;
    private CrmCustomerService custService;
	private SysUserDao userDao;
	private Map<String,TlkSpecialNumber> specialNumberMap;
	
	public TlkSpecialNumberDao getSpecialNumberDao() {
		return specialNumberDao;
	}
	public void setSpecialNumberDao(TlkSpecialNumberDao specialNumberDao) {
		this.specialNumberDao = specialNumberDao;
	}
	public CrmCustomerService getCustService() {
		return custService;
	}
	public void setCustService(CrmCustomerService custService) {
		this.custService = custService;
	}
	public DeptFacadeService getDeptService() {
		return deptService;
	}
	public void setDeptService(DeptFacadeService deptService) {
		this.deptService = deptService;
	}
	public SysUserService getUserService() {
		return userService;
	}
	public void setUserService(SysUserService userService) {
		this.userService = userService;
	}
	public RecordInfoService getRecordService() {
		return recordService;
	}
	public void setRecordService(RecordInfoService recordService) {
		this.recordService = recordService;
	}
	public SysUserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(SysUserDao userDao) {
		this.userDao = userDao;
	}
	public CrmCustomerDao getCrmCustomerDao() {
		return crmCustomerDao;
	}
	public void setCrmCustomerDao(CrmCustomerDao crmCustomerDao) {
		this.crmCustomerDao = crmCustomerDao;
	}
	
	public TlkMobileAttibutionDao getMobileAreaDao() {
		return mobileAreaDao;
	}
	public void setMobileAreaDao(TlkMobileAttibutionDao mobileAreaDao) {
		this.mobileAreaDao = mobileAreaDao;
	}
	public TlkTelephoneCodeDao getTelephoneAreaDao() {
		return telephoneAreaDao;
	}
	public void setTelephoneAreaDao(TlkTelephoneCodeDao telephoneAreaDao) {
		this.telephoneAreaDao = telephoneAreaDao;
	}
	/**
	 * 通过电话号码查找客户
	 */
	public List<CrmCustomer> getCrmCustomerByTel(String tel) {
		return crmCustomerDao.getCustomersByTel(tel);
	}
	
	/**
	 * 通过电话号码查找客户
	 */
	public List<CrmCustomer> getCrmCustomerByTel(String tel,String cityCode) {
		return crmCustomerDao.getCustomersByTel(tel,cityCode);
	}
	
	/**
	 * 通过电话号码查地区码
	 * @param code
	 * @return
	 */
	public String getAreaCodeByNumber(String number)
	{
		return this.mobileAreaDao.getAreaCodeByNumber(number);
	}
	
	/**
	 * 通过区域代码查名称
	 * @param code
	 * @return
	 */
	public String getPhoneAreaNameByCode(String code){
		return this.telephoneAreaDao.getPhoneAreaNameByCode(code);
	}
	
	/**
	 * 提取电话号码
	 * @param number
	 * @return
	 */
	public String formatTelephoneNumber(String number){
		if(number!=null && !"".equals(number))
		{
			
		}
		return "";
	}
	
	/**
	 * 判断是否为手机号码
	 * @param number
	 * @return
	 */
	public boolean isMobileNumber(String number)
	{
		if((number.length()==12 && number.charAt(0)=='0') || number.length()==11)
		{
			String str= (number.length()==12)?number.substring(1,12):number;
			if(str.charAt(0)=='1' && str.charAt(1)!='0')
				return true;
		}
		return false;
	}
	
	public Map<String,TlkSpecialNumber> getSpecialNumbers(){
		if(this.specialNumberMap==null){
			this.specialNumberMap=new HashMap<String,TlkSpecialNumber>();
			List<TlkSpecialNumber> sns = this.specialNumberDao.getSpecialNumbers();
			for(TlkSpecialNumber sn : sns){
				this.specialNumberMap.put(sn.getNumber(), sn);
			}
		}
		return this.specialNumberMap;
	}
	
	/**
	 * 是否为特殊号码
	 * @param number
	 * @return
	 */
	public boolean isSpecialNumber(String number){
		return this.getSpecialNumbers().containsKey(number);
	}
	
	/**
	 * 得到来电转接用户
	 * @param condition
	 * @param page
	 * @return
	 */
	public PageUtil<SysTalkUserBean> getTalkForwardUsers(Map<String, Object> condition,Page page)
	{
		return this.userDao.getTalkForwardUsers(condition, page);
	}
	
	/**
	 * 当前用户有权限的      机构ids
	 * @param userId
	 * @return
	 */
  	@SuppressWarnings("unused")
	private String getCurrentUserInChargeOfDeptIds(Integer userId){
  		String deptIds = "";
  		Integer[] arr = deptService.getInChargeOfDeptIds(userId); 
  		if(arr!=null){
  			for(int i=0; i<arr.length; i++){
  				if(deptIds.equals(""))
  					deptIds = arr[i].toString();
  				else
  					deptIds = deptIds + "," + arr[i];
  			}
  		}
  		return deptIds;
  	}
  	
  	/**
  	 * 当前用户有权限的      用户ids
  	 * @param userId
  	 * @return
  	 */
  	private String getCurrentInChargeUserIds(Integer userId){
  		String userIds = "";
  		Integer[] arr = deptService.getInChargeOfDeptUserIds(userId);
  		if(arr!=null){
  			for(int i=0; i<arr.length; i++){
  				if(userIds.equals(""))
  					userIds = arr[i].toString();
  				else
  					userIds = userIds + "," + arr[i];
  			}
  		}
  		userIds = "".equals(userIds)?userId.toString()
  				:userIds + ","+userId.toString();
  		return userIds;
  	}
	
	/**
     * 根据帐号和电话查询客户
     * @param account
     * @param telNum
     * @return
     */
    public JSONArray getRecentlyCustomer(String account,String telNum){
    	SysUser user = userService.getUserByAccount(account);
    	String userIds="";
    	String deptIds="";
    	
    	boolean isInChargeof = deptService.isInChargeOfDepartment();
    	
    	if(isInChargeof){
    		deptIds = StringUtil.getIdsString(deptService.getInChargeOfDeptIds());
    		if(StringUtil.isNullOrEmpty(deptIds)){
    			deptIds = "-1";
    		}
    		userIds = getCurrentInChargeUserIds(user.getUserId());
    	}else{
    		deptIds = "-1";
    		userIds = user.getUserId().toString();
    	}
    	
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("userIds", userIds);
    	map.put("deptIds", deptIds);
    	if(StringUtil.isNumber(telNum))
    	{
    		map.put("tel", telNum);
    		List<CrmCustomer> custs = this.custService.getRecentlyCustomers(map);
    		return buildCustomerJsons(custs,telNum);
    	}
    	else
    	{
    		map.put("py",telNum.toUpperCase());			//拼音查找客户
    		List<CrmCustomer> custs = this.custService.getRecentlyCustomers(map);
    		return buildCustomerJsons(custs);
    	}
    }
    
    /**
     * 移附不符合的号码
     * @param custs
     */
    private JSONArray buildCustomerJsons(List<CrmCustomer> custs,String telNum)
    {
    	JSONArray ja = new JSONArray();
    	if(custs.size()>0)
    	{
    		for(CrmCustomer cust : custs)
    		{
    			boolean firstFlag = true;
				if(cust.getMobilePhone1()!=null && !"".equals(cust.getMobilePhone1())){
					if(cust.getMobilePhone1().indexOf(telNum)>-1)
					{
						ja.add(buildCustomerJson(cust,"手机一",cust.getMobilePhone1(),firstFlag));
						firstFlag=false;
					}
				}
				if(cust.getMobilePhone2()!=null && !"".equals(cust.getMobilePhone2())){
					if(cust.getMobilePhone2().indexOf(telNum)>-1)
					{
						ja.add(buildCustomerJson(cust,"手机二",cust.getMobilePhone2(),firstFlag));
						firstFlag=false;
					}
				}
				if(cust.getPhone()!=null && !"".equals(cust.getPhone())){
					if(cust.getPhone().indexOf(telNum)>-1)
					{
						ja.add(buildCustomerJson(cust,"固话",cust.getPhone(),firstFlag));
						firstFlag=false;
					}
				}
				if(cust.getFax()!=null && !"".equals(cust.getFax())){
					if(cust.getFax().indexOf(telNum)>-1)
					{
						ja.add(buildCustomerJson(cust,"传真",cust.getFax(),firstFlag));
						firstFlag=false;
					}
				}
    		}
    	}
    	return ja;
    }
    /**
     * 生成客户json
     * @param custs
     * @return
     */
    private JSONArray buildCustomerJsons(List<CrmCustomer> custs)
    {
    	JSONArray ja = new JSONArray();
    	if(custs.size()>0)
    	{
    		for(CrmCustomer cust : custs)
    		{
    			boolean firstFlag = true;
				if(cust.getMobilePhone1()!=null && !"".equals(cust.getMobilePhone1())){
					ja.add(buildCustomerJson(cust,"手机一",cust.getMobilePhone1(),firstFlag));
					firstFlag=false;
				}
				if(cust.getMobilePhone2()!=null && !"".equals(cust.getMobilePhone2())){
					ja.add(buildCustomerJson(cust,"手机二",cust.getMobilePhone2(),firstFlag));
					firstFlag=false;
				}
				if(cust.getPhone()!=null && !"".equals(cust.getPhone())){
					ja.add(buildCustomerJson(cust,"固话",cust.getPhone(),firstFlag));
					firstFlag=false;
				}
				if(cust.getFax()!=null && !"".equals(cust.getFax())){
					ja.add(buildCustomerJson(cust,"传真",cust.getFax(),firstFlag));
					firstFlag=false;
				}
    		}
    	}
    	return ja;
    }
    /**
     * 生成客户json
     * @param cust
     * @param type
     * @param telNum
     * @param fisrtFlag
     * @return
     */
    private JSONObject buildCustomerJson(CrmCustomer cust,String type,String telNum,boolean fisrtFlag)
    {
    	JSONObject jo = new JSONObject();
    	jo.put("id",cust.getCustomerId());
    	jo.put("name",fisrtFlag?cust.getCustomerName():"");
    	jo.put("sort",type);
    	jo.put("number",telNum);
    	jo.put("title",cust.getCustomerTitle());
    	return jo;
    }
}
