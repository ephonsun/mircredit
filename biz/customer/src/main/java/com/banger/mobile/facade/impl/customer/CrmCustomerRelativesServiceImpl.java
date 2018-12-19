/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :录音信息业务实现类
 * Author     :zhangxiang
 * Version    :1.0
 * Create Date:May 3, 2012
 */
package com.banger.mobile.facade.impl.customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.customer.CrmCustomerRelativesDao;
import com.banger.mobile.domain.model.base.customer.BaseCrmCustomerRelatives;
import com.banger.mobile.domain.model.customer.CrmCustomerRelatives;
import com.banger.mobile.domain.model.user.IUserInfo;
import com.banger.mobile.facade.customer.CrmCustomerRelativesService;
import com.banger.mobile.facade.dept.DeptFacadeService;



public class CrmCustomerRelativesServiceImpl implements CrmCustomerRelativesService {
	
	private CrmCustomerRelativesDao crmCustomerRelativesDao;
	private DeptFacadeService		  deptFacadeService;	  //机构Service

	public CrmCustomerRelativesDao getCrmCustomerRelativesDao() {
		return crmCustomerRelativesDao;
	}

	public void setCrmCustomerRelativesDao(
			CrmCustomerRelativesDao crmCustomerRelativesDao) {
		this.crmCustomerRelativesDao = crmCustomerRelativesDao;
	}
	
	public DeptFacadeService getDeptFacadeService() {
		return deptFacadeService;
	}

	public void setDeptFacadeService(DeptFacadeService deptFacadeService) {
		this.deptFacadeService = deptFacadeService;
	}

	/**
	 * 获得登录信息
	 * @return
	 */
    private IUserInfo getUserLoginInfo(){
        HttpServletRequest req = org.apache.struts2.ServletActionContext.getRequest();
        HttpSession session = req.getSession();
        return (IUserInfo)session.getAttribute("LoginInfo");
    }
    
    /**
     * 获取亲友信息
     */
    public PageUtil<CrmCustomerRelatives> selectCustomerRelatives(String customerId, Page page){
    	Map<String, String> map = new HashMap<String, String>();
    	map.put("customerId", customerId);
    	map.put("userIds", getCurrentInChargeUserIds());
    	map.put("UserId", getUserLoginInfo().getUserId().toString());
    	Boolean isInChargeof = false;//deptFacadeService.isInChargeOfDepartment();
    	String inChargeOfDeptIds = "";
    	if(isInChargeof){
    		inChargeOfDeptIds = getCurrentUserInChargeOfDeptIds();
    	}else{
    		inChargeOfDeptIds = "-1";
    	}
    	map.put("InChargeOfDeptIds", inChargeOfDeptIds);
    	map.put("ContainsShare", "ContainsShare");
    	return crmCustomerRelativesDao.selectCustomerRelatives(map,page);
    }
    
    /**
     * 查询客户亲友列表
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<CrmCustomerRelatives> getCustomerRelativesPad(Map<String, String> parameters, Page page, String userid){
        Boolean isInChargeof = deptFacadeService.isInChargeOfDepartment(userid);
        String inChargeOfDeptIds = "";
        if(isInChargeof){
            inChargeOfDeptIds = getInChargeOfDeptIdsByUserId(userid);
        }else{
            inChargeOfDeptIds = "-1";
        }
        parameters.put("InChargeOfDeptIds", inChargeOfDeptIds);
        return crmCustomerRelativesDao.selectCustomerRelatives(parameters,page);
    }
    
    /**
     * 当前用户有权限的      机构ids
     * @return
     */
    private String getInChargeOfDeptIdsByUserId(String userid){
        String deptIds = "";
        Integer[] arr = deptFacadeService.getInChargeOfDeptIds(Integer.parseInt(userid)); 
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
     * @return
     */
  	private String getCurrentInChargeUserIds(){
  		String userIds = "";
  		Integer[] arr = deptFacadeService.getInChargeOfDeptUserIds();
  		if(arr!=null){
  			for(int i=0; i<arr.length; i++){
  				if(userIds.equals(""))
  					userIds = arr[i].toString();
  				else
  					userIds = userIds + "," + arr[i];
  			}
  		}
  		if(userIds.equals("")){
  			userIds = getUserLoginInfo().getUserId().toString();
  		}else{
  			userIds = userIds + "," +getUserLoginInfo().getUserId(); 
  		}
  		return userIds;
  	}
  	/**
  	 * 当前用户有权限的      机构ids
  	 * @return
  	 */
  	private String getCurrentUserInChargeOfDeptIds(){
  		String deptIds = "";
  		Integer[] arr = deptFacadeService.getInChargeOfDeptIds(); 
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
  	 * 新增亲友信息
  	 */
    public void addRelativesCustomer(String customerId, String relativesIds){
    	List<Integer> customerIdList = crmCustomerRelativesDao.getCustomerIdList(customerId);
		String[] relativesIdList = relativesIds.split(",");
		
		List<BaseCrmCustomerRelatives> entityList = new ArrayList<BaseCrmCustomerRelatives>();
		
		for(String relativesId : relativesIdList){
			Boolean boo = true;
			if(relativesId.equals(customerId)){
				boo = false;
			}else{
				for(Integer cusId: customerIdList){
					if(cusId.toString().equals(relativesId)){
						boo = false;
					}
				}
			}
			if(boo){
				BaseCrmCustomerRelatives entity = new BaseCrmCustomerRelatives();
				entity.setCustomerId(Integer.parseInt(customerId));
				entity.setRelativesId(Integer.parseInt(relativesId));
				entity.setCreateUser(getUserLoginInfo().getUserId());
				entity.setUpdateUser(getUserLoginInfo().getUserId());
				entityList.add(entity);
			}
		}
		crmCustomerRelativesDao.addRelativesCustomer(entityList);
    }
    /**
     * 移除亲友信息
     */
  	public void delRelatives(String customerRelativesId){
  		crmCustomerRelativesDao.delRelatives(customerRelativesId);
  	}
  	/**
  	 * 清空垃圾箱 清除关联信息
  	 */
  	public void delRelativesInDustbin(Map<String, String> parameters){
  		crmCustomerRelativesDao.clearRelativesInDustbin(parameters);
  	}
  	/**
  	 * 批量彻底删除客户，清除关联信息
  	 */
  	public void delRelativesByCusId(String customerIds){
  		crmCustomerRelativesDao.delRelativesInDustbin(customerIds);
  	}

    /**
     * 查询客户关联亲友总数
     *
     * @param customerId
     * @return
     */
    @Override
    public Integer selectRelativesByCustomerId(Integer customerId) {
        return (Integer) crmCustomerRelativesDao.selectRelativesByCustomerId(customerId);
    }
}
    
