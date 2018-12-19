/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yuanme
 * Create Date:2012-11-14
 */
package com.banger.mobile.facade.impl.data;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.data.CustomerDataDao;
import com.banger.mobile.domain.model.data.CustomerData;
import com.banger.mobile.domain.model.data.Event;
import com.banger.mobile.domain.model.data.LoanData;
import com.banger.mobile.domain.model.data.LoanExportData;
import com.banger.mobile.domain.model.pad.PadLoanData;
import com.banger.mobile.facade.data.CustomerDataService;
import com.banger.mobile.facade.loan.LnLoanInfoService;
import com.banger.mobile.facade.uploadFile.SysUploadFileService;
import com.banger.mobile.util.StringUtil;
import org.apache.commons.io.FileUtils;
import org.jfree.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yuanme
 * @version $Id: CustomerDataServiceImpl.java,v 0.1 2012-11-14 下午5:27:03 Administrator Exp $
 */
public class CustomerDataServiceImpl implements CustomerDataService {
    private CustomerDataDao customerDataDao;
    private SysUploadFileService sysUploadFileService;
    private LnLoanInfoService lnLoanInfoService;
    
    public void setCustomerDataDao(CustomerDataDao customerDataDao) {
        this.customerDataDao = customerDataDao;
    }
    
    public void setSysUploadFileService(SysUploadFileService sysUploadFileService) {
		this.sysUploadFileService = sysUploadFileService;
	}


	public LnLoanInfoService getLnLoanInfoService() {
		return lnLoanInfoService;
	}

	public void setLnLoanInfoService(LnLoanInfoService lnLoanInfoService) {
		this.lnLoanInfoService = lnLoanInfoService;
	}

	/**
     * 
     * @param data
     * @return
     * @see com.banger.mobile.facade.data.CustomerDataService#saveCustomerData(com.banger.mobile.domain.model.data.CustomerData)
     */
    public void saveCustomerData(CustomerData data) {
        customerDataDao.saveCustomerData(data);
    }

    /**
     * 批量新增客户资料
     * @param data
     */
    public void saveCustomerDataBatch(List<CustomerData> datas) {
    	customerDataDao.saveCustomerDataBatch(datas);
    }
    
    /**
     * 
     * @param data
     * @return
     * @see com.banger.mobile.facade.data.CustomerDataService#updateCustomerData(com.banger.mobile.domain.model.data.CustomerData)
     */
    public void updateCustomerData(CustomerData data) {
        customerDataDao.updateCustomerData(data);
    }

    /**
     * 
     * @return
     * @see com.banger.mobile.facade.data.CustomerDataService#getEventTypeList()
     */
    public List<Event> getEventTypeList() {
        return customerDataDao.getEventTypeList();
    }

    /**
     * 
     * @param parameterMap
     * @param page
     * @return
     * @see com.banger.mobile.facade.data.CustomerDataService#getCustomerDataListPage(java.util.Map, com.banger.mobile.Page)
     */
    public List<CustomerData> getCustomerDataListPage(Map<String, Object> parameterMap, Page page) {
        return customerDataDao.getCustomerDataListPage(parameterMap, page);
    }

    /**
     * 
     * @param parameterMap
     * @return
     * @see com.banger.mobile.facade.data.CustomerDataService#getCustomerDataByParameterMap(java.util.Map)
     */
    public List<CustomerData> getCustomerDataByParameterMap(Map<String, Object> parameterMap) {
        return customerDataDao.getCustomerDataByParameterMap(parameterMap);
    }
    
    /**
     * 
     */
	public CustomerData addNewCustomerData(CustomerData cd) {
		
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("loanId", cd.getLoanId());
		if(null!=cd.getCustomerId()&&cd.getCustomerId()==0){
			cd.setCustomerId(lnLoanInfoService.getPanLoanInfoById(cd.getLoanId()).getCustomerId());
		}
		parameterMap.put("customerId", cd.getCustomerId());
		
		if(cd.getEventId()==0 || cd.getEventId()==-1){
			cd.setEventId(1);
		}
		parameterMap.put("eventId", cd.getEventId());
		List<CustomerData> list= customerDataDao.getCustomerDataByParameterMap(parameterMap);
		if(list==null || list.size() < 1){
			this.customerDataDao.addNewCustomerData(cd);
		}else{
			cd = list.get(0);
		}
        return cd;
	}

    /**
     * 
     * @param
     * @return
     * @see com.banger.mobile.facade.data.CustomerDataService#getEventTypeListById(java.lang.Integer)
     */
    public Event getEventTypeListById(Integer id) {
        return customerDataDao.getEventTypeListById(id);
    }

    /**
     * 取得所有单个贷款所有资料
     * @param paramMap
     * @return
     */
    public List<LoanData> getAllLoanDataById(Map<String,Object> paramMap) {
        return customerDataDao.getAllLoanDataById(paramMap);
    }
    
    public Integer getAllLoanDataByIdCount(Map<String,Object> paramMap) {
        return customerDataDao.getAllLoanDataByIdCount(paramMap);
    }
    public List<PadLoanData> getDunDataById(Map<String,Object> paramMap){
        return customerDataDao.getDunDataById(paramMap);
    }
    
    //以下为新资料管理 (huangk)
    /**
     * 资料管理列表
     * @param parameterMap
     * @param page
     * @return 
     */
    public PageUtil<CustomerData> getAllCustomerDataPage(Map<String, Object> parameterMap, Page page, CustomerData customerData){
    	if(customerData!=null){
    		if(!StringUtil.isBlank(customerData.getCustomerName())){
    			parameterMap.put("customerName", StringUtil.ReplaceSQLChar(customerData.getCustomerName().trim()));
    		}
    		if(!StringUtil.isBlank(customerData.getPhone())){
    			parameterMap.put("phone", StringUtil.ReplaceSQLChar(customerData.getPhone().trim()));
    		}
    		if(!StringUtil.isBlank(customerData.getEventName())){
    			String event = "";
    			if(customerData.getEventName().contains("营销")){
    				event += "1,";
    			}
    			if(customerData.getEventName().contains("申请")){
    				event += "2,";
    			}
    			if(customerData.getEventName().contains("调查")){
    				event += "3,";
    			}
    			if(customerData.getEventName().contains("审批")){
    				event += "4,";
    			}
    			if(customerData.getEventName().contains("落实")){
    				event += "5,";
    			}
    			if(customerData.getEventName().contains("催收")){
    				event += "6,";
    			}
    			if(event.length()>0)//祛逗号
    			parameterMap.put("eventId", event.substring(0, event.length()-1));
    		}
    	}
    	return this.customerDataDao.getAllCustomerDataPage(parameterMap, page);
    }
    
    /**
     * 客户管理资料列表
     * @param parameterMap
     * @return 
     */
    public List<CustomerData> QueryDataByCusId(Map<String, Object> parameterMap) {
        return this.customerDataDao.QueryDataByCusId(parameterMap);
    }

    /**
     * 得到贷款客户相应资料的数量及类型
     *
     * @param paramMap
     * @return
     */
    public List<HashMap<String,Object>> getCustomerDataCount(Map<String, Object> paramMap){
        return customerDataDao.getCustomerDataCount(paramMap);
    }

    /**
     * 更新客户资料表
     * @param paramMap
     */
    public void updateDatCustomerData(Map<String, Object> paramMap){
        customerDataDao.updateDatCustomerData(paramMap);
    }

    /**
     * 删除客户资料
     * @param paramMap
     */
    public void delCustomerData(Map<String, Object> paramMap){
        customerDataDao.delCustomerData(paramMap);
    }


    /**
     * 根据贷款和客户查找客户资料id列表
     * @param paramMap
     * @return
     */
    public List<Integer> getCustomerDataIdList(Map<String, Object> paramMap){
        return customerDataDao.getCustomerDataIdList(paramMap);
    }

    public Integer getCustomerDataId(Map<String, Object> paramMap){
        return customerDataDao.getCustomerDataId(paramMap);
    }

    

    /**
     * 根据fileId获取文件对象并复制
     */
    public String readFile(String fileId,String systemPath){
    	File file = null;
    	String flag = "0";
        if(StringUtil.isNotEmpty(fileId)){
            Integer curUserId = sysUploadFileService.getSysUploadFileById(Integer.parseInt(fileId)).getUploadUserId();
            CustomerData data = new CustomerData();
            data.setCreateUser(curUserId);
        	file = sysUploadFileService.getRealFile(Integer.parseInt(fileId),data);
        }
    	if(null!=file&&StringUtil.isNotEmpty(file.getPath())){
            if(file.exists()){
                file.setLastModified(System.currentTimeMillis());
                File newFile=new File(systemPath+File.separator+file.getName());
                try {
					FileUtils.copyFile(file,newFile);
				} catch (IOException e) {
					Log.error("dateService readFile action error:"+e.getMessage());
				}
                flag = file.toString();//考虑到有下载功能要用该方法，所以返回绝对路径 
            }
        }
    	return flag;
    }
    

    //==========优化后的代码============

    /**
     * 取得所有单个贷款所有资料(优化)
     * @param paramMap
     * @return
     */
    public List<LoanData> getAllLoanDataByIdOptimize(Map<String, Object> paramMap){
        return customerDataDao.getAllLoanDataByIdOptimize(paramMap);
    }

    public void relationRecord(Integer recordInfoId, Integer customerId) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("customerId", 0 - recordInfoId);
        paramMap.put("customerIdNew", customerId);
        customerDataDao.relationRecord(paramMap);
    }

    /**
     * 获取贷款所有导出资料信息
     *
     * @param paramMap
     * @return
     */
    public List<LoanExportData> getFileIdListByLoan(Map<String, Object> paramMap){
        return customerDataDao.getFileIdListByLoan(paramMap);
    }

    /**
     * 得到贷款资料总数
     *
     * @param paramMap
     * @return
     */
    public Integer getFileIdCountByLoan(Map<String, Object> paramMap){
        return customerDataDao.getFileIdCountByLoan(paramMap);
    }

    public List<String> getFileByDatType(Map<String, Object> paramMap){
        return customerDataDao.getFileByDatType(paramMap);
    }
}
