/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :电话通话
 * Author     :zsw
 * Create Date:2012-5-24
 */
package com.banger.mobile.webapp.action.talk;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.banger.mobile.domain.model.base.customer.BaseCrmCustomer;
import com.banger.mobile.domain.model.customer.CrmCustomer;
import com.banger.mobile.domain.model.phoneConfig.PhoneConfig;
import com.banger.mobile.domain.model.record.RecordDetail;
import com.banger.mobile.facade.customer.CrmCustomerService;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.phoneConfig.PhoneConfigService;
import com.banger.mobile.facade.record.RecordInfoService;
import com.banger.mobile.facade.talk.TelephoneTalkService;
import com.banger.mobile.facade.user.SysUserService;
import com.banger.mobile.util.StringUtil;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * @author zsw
 * @version $Id: DailNumberAction.java,v 0.1 Aug 22, 2012 3:05:35 PM zsw Exp $
 */
public class DailNumberAction  extends BaseAction {

	private static final long serialVersionUID = -5354251759706523132L;
	private PhoneConfigService phoneConfigService;				//电话配置Service
	private RecordInfoService 			 recordInfoService;
	private CrmCustomerService        crmCustomerService;   //客户Service
	private DeptFacadeService		  deptFacadeService;	  //机构Service
    private SysUserService login;					//登入服务
	private TelephoneTalkService talk;				//通话服务类
	private RecordDetail 				 recordDetail;
	private String telNum;
    private List<CrmCustomer> custs;				//客户
    
	public List<CrmCustomer> getCusts() {
		return custs;
	}

	public void setCusts(List<CrmCustomer> custs) {
		this.custs = custs;
	}

	public String getNumber() {
		return telNum;
	}

	public void setNumber(String telNum) {
		this.telNum = telNum;
	}

	public CrmCustomerService getCrmCustomerService() {
		return crmCustomerService;
	}

	public void setCrmCustomerService(CrmCustomerService crmCustomerService) {
		this.crmCustomerService = crmCustomerService;
	}

	public DeptFacadeService getDeptFacadeService() {
		return deptFacadeService;
	}

	public void setDeptFacadeService(DeptFacadeService deptFacadeService) {
		this.deptFacadeService = deptFacadeService;
	}

	public RecordDetail getRecordDetail() {
		return recordDetail;
	}

	public void setRecordDetail(RecordDetail recordDetail) {
		this.recordDetail = recordDetail;
	}

	public RecordInfoService getRecordInfoService() {
		return recordInfoService;
	}

	public void setRecordInfoService(RecordInfoService recordInfoService) {
		this.recordInfoService = recordInfoService;
	}

	public PhoneConfigService getPhoneConfigService() {
		return phoneConfigService;
	}

	public void setPhoneConfigService(PhoneConfigService phoneConfigService) {
		this.phoneConfigService = phoneConfigService;
	}

	public TelephoneTalkService getTalk() {
		return talk;
	}

	public void setTalk(TelephoneTalkService talk) {
		this.talk = talk;
	}
	
	

	public SysUserService getLogin() {
		return login;
	}

	public void setLogin(SysUserService login) {
		this.login = login;
	}

	/**
	 * 处理外拨号码
	 * @param number
	 * @return
	 * @throws IOException
	 */
	public String getDialNumber() throws IOException
	{
		String number=this.request.getParameter("number");
		String cusId=this.request.getParameter("cusId");
		//根据配置是否弹上次联系
		boolean needShowLastContact = true;
		
		PrintWriter out = this.getResponse().getWriter();
		
		PhoneConfig config = phoneConfigService.query(this.getLoginInfo().getUserId());
		needShowLastContact = (config.getIsShowLastWindow()!=null && config.getIsShowLastWindow()>0)?true:false;
		
		BaseCrmCustomer cust = null;
		if(!StringUtil.isNullOrEmpty(cusId) && Integer.parseInt(cusId) > 0){
			cust = crmCustomerService.getCrmCustomerById(Integer.parseInt(cusId));
			if(cust==null || !cust.isValid()){
				needShowLastContact = false;
				cusId="";
				cust=null;
			}
		}
		
		if(needShowLastContact){
			if(cust!=null){
				//根据客户id获取最后一天联系记录
				recordDetail = recordInfoService.getRecordInfoByCustomerId(Integer.parseInt(cusId));
				if(recordDetail==null){
					needShowLastContact = false;
				}
			}else{
				this.custs = talk.getCrmCustomerByTel(telNum,config.getCityCode());
				if(this.custs.size()==1)
				{
					recordDetail = recordInfoService.getRecordInfoByCustomerId(this.custs.get(0).getCustomerId());
					if(recordDetail==null){
						needShowLastContact = false;
					}
				}
				else
				{
					needShowLastContact = false;
				}
			}
		}
		JSONObject jo = new JSONObject();
		String tel = this.outNumber(number);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("needShowLastContact", needShowLastContact);
		map.put("phoneNumber", tel);
		map.put("cusId", cusId);
		jo.putAll(map);
		out.print(jo.toString());
		return null;
	}
	/**
	 * 外拨号码处理
	 * @param number
	 * @return
	 */
	private String outNumber(String number)
	{
		if(number!=null && !"".equals(number))
		{
			Integer userId = this.getLoginInfo().getUserId();
			PhoneConfig config = phoneConfigService.query(userId);
			
			String outNumber = "";
			boolean isLocal = true;			//是否为本地
			boolean inline = false;			   //是否为内线
			if(!StringUtil.isNumber(number))
			{
				outNumber = number;
			}
			if(talk.isMobileNumber(number))    //判断是否为手机号码
			{
				if(number.length()==11)
				{
					String areaCode = talk.getAreaCodeByNumber(number.substring(0,7));
					if(config.getCityCode()!=null && areaCode!=null && !"".equals(config.getCityCode()) )			//判断是否为本地手机
					{
						if(!config.getCityCode().equals(areaCode))
						{
							outNumber="0"+number;
							isLocal = false;
						}
					}
				}
				if(outNumber.equals(""))outNumber=number;
			}
			else if(number.length()>6)
			{
				if(config.getCityCode()!=null && !"".equals(config.getCityCode()))
				{
					int cityLen = config.getCityCode().length();
					if(number.length()>cityLen && number.substring(0,cityLen).equals(config.getCityCode()))
					{
						outNumber = number.substring(cityLen);
					}
					else
					{
						outNumber = number;
						isLocal = number.charAt(0)!='0';
					}
				}
				else
				{
					outNumber = number;
				}
			}
			else
			{
				int inLen = 0;
				if(config.getInsiseExtLength()!=null && !"".equals(config.getInsiseExtLength()) )
					inLen = Integer.parseInt(config.getInsiseExtLength());
				if(!this.talk.isSpecialNumber(number)){
					if(number.length()==inLen)inline=true;
				}
				outNumber = number;
			}
			
			if(inline)			//是否拨分机
			{
				return outNumber;
			}
			else
			{
				if(config.getIsIpCall()!=null && config.getIsIpCall().intValue()>0)
				{
					if(config.getIpNumber()!=null && !"".equals(config.getIpNumber()))
					{
						if(!isLocal)outNumber=config.getIpNumber()+outNumber;
					}
				}
				if(config.getOutsideCallCode()!=null && !"".equals(config.getOutsideCallCode()) )
				{
					outNumber=config.getOutsideCallCode()+outNumber;
				}
			}
			return outNumber;
		}
		return "";
	}
	/**
	 * 显示最近10条联系记录
	 * @return
	 */
	public String showLastContact(){
		String cusId=this.request.getParameter("cusId");
		Integer hasPerssion = 1;
		if(!StringUtil.isNullOrEmpty(cusId) && Integer.parseInt(cusId) > 0){
			//根据客户id获取最后一天联系记录  set get
			recordDetail = recordInfoService.getRecordInfoByCustomerId(Integer.parseInt(cusId));
//			boolean isInChargeof = deptFacadeService.isInChargeOfDepartment();
//			Integer[] arr = isInChargeof?deptFacadeService.getInChargeOfDeptUserIds():null;
//			hasPerssion = recordInfoService.getRecordPermissionById(Integer.parseInt(recordDetail.getRecordInfoId()), arr)?1:0;
			
			//判断客户是否有权限
			 CrmCustomer crmCustomer = crmCustomerService.getCustomerById(Integer.parseInt(cusId));
			 Integer hasRight = crmCustomerService.checkPermission(crmCustomer, deptFacadeService.getInChargeOfDeptIds());
			 boolean isShareCus = crmCustomerService.checkShareCus(Integer.parseInt(cusId), this.getLoginInfo().getUserId());
			 if(hasRight==0 && !isShareCus) hasPerssion=0;//无权限
		}else{
			//根据number获取最后一天联系记录  set get
			PhoneConfig config = phoneConfigService.query(this.getLoginInfo().getUserId());
			this.custs = talk.getCrmCustomerByTel(telNum,config.getCityCode());
			if(this.custs.size()>0)
			{
				recordDetail = recordInfoService.getRecordInfoByCustomerId(this.custs.get(0).getCustomerId());
//				boolean isInChargeof = deptFacadeService.isInChargeOfDepartment();
//				Integer[] arr = isInChargeof?deptFacadeService.getInChargeOfDeptUserIds():null;
//				hasPerssion = recordInfoService.getRecordPermissionById(Integer.parseInt(recordDetail.getRecordInfoId()), arr)?1:0;
    	         //判断客户是否有权限
                 CrmCustomer crmCustomer = crmCustomerService.getCustomerById(custs.get(0).getCustomerId());
                 Integer hasRight = crmCustomerService.checkPermission(crmCustomer, deptFacadeService.getInChargeOfDeptIds());
                 boolean isShareCus = crmCustomerService.checkShareCus(custs.get(0).getCustomerId(), this.getLoginInfo().getUserId());
                 if(hasRight==0 && !isShareCus) hasPerssion=0;//无权限
			}
		}
		
		request.setAttribute("hasPerssion", hasPerssion);
		
		return "success";
	}
}
