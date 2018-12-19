package com.banger.mobile.webapp.action.data;

import com.banger.mobile.domain.model.data.CustomerData;
import com.banger.mobile.domain.model.data.Form;
import com.banger.mobile.facade.data.DataFormService;
import com.banger.mobile.facade.uploadFile.SysUploadFileService;
import com.banger.mobile.webapp.action.BaseAction;
import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FormAction extends BaseAction {

	private static final long serialVersionUID = -1558023055728438122L;
	
	private DataFormService dataFormService;
	
	private SysUploadFileService sysUploadFileService;
	/**
     * 查询资料
     * @return
     */
    public String queryDataList() {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
    	
        try {
            String formName = request.getParameter("formName");
            String recordDateFrom = request.getParameter("formRecordDateFrom");
            String recordDateTo = request.getParameter("formRecordDateTo");
            String uploadDateFrom = request.getParameter("formUploadDateFrom");
            String uploadDateTo = request.getParameter("formUploadDateTo");
            String customerDataId = request.getParameter("customerDataId");
            Map<String, Object> parameterMap = new HashMap<String, Object>();
            if (StringUtils.isNotEmpty(formName)) {
                parameterMap.put("formName", formName);
            }
            if (StringUtils.isNotEmpty(recordDateFrom)) {
                parameterMap.put("recordDateFrom", sdf.parse(recordDateFrom));
            }
            if (StringUtils.isNotEmpty(recordDateTo)) {
                parameterMap.put("recordDateTo", sdf.parse(recordDateTo));
            }
            if (StringUtils.isNotEmpty(uploadDateFrom)) {
                parameterMap.put("uploadDateFrom", sdf.parse(uploadDateFrom));
            }
            if (StringUtils.isNotEmpty(uploadDateTo)) {
                parameterMap.put("uploadDateTo", sdf.parse(uploadDateTo));
            }
            if (StringUtils.isNotEmpty(customerDataId)){
            	parameterMap.put("customerDataId", customerDataId);
            }
            
            List<Form> formList = dataFormService.queryFormData(parameterMap,this.getPage());
            int count = this.getPage().getTotalRowsAmount();
            request.setAttribute("formList", formList);
            request.setAttribute("count", String.valueOf(count));
           

            return SUCCESS;
        } catch (Exception e) {
            log.error("queryDataList ERROR", e);
            return ERROR;
        }
    }
    public String firstLoadFormList(){
    	try {
    		String customerDataId = request.getParameter("customerDataId");
    		if(StringUtils.isNotEmpty(customerDataId)){
    			Map<String , Object> parameterMap = new HashMap<String , Object>();
    			parameterMap.put("customerDataId", customerDataId);
    			List<Form> formList = dataFormService.queryFormData(parameterMap, this.getPage());
    			int count = this.getPage().getTotalRowsAmount();
    			String customerName = dataFormService.getCustomerNameByDataId(Integer.valueOf(customerDataId));
    			request.setAttribute("formList", formList);
    			request.setAttribute("customerName", customerName);
    			request.setAttribute("count", String.valueOf(count));
    			request.setAttribute("customerDataId", customerDataId);
    			return SUCCESS;
    		}else{
    			return ERROR;
    		}
			
		} catch (RuntimeException e) {
			log.error("queryFormList ERROR", e);
			return ERROR;
		}
    }
    public String loadOldRemark(){
    	try {
			String formId = request.getParameter("formId");
			
			String formRemark = dataFormService.getFormRemark(Integer.valueOf(formId));
			
			request.setAttribute("formId", formId);
			request.setAttribute("formRemark", formRemark);
			return SUCCESS;
		} catch (RuntimeException e) {
			log.error("loadOldRemark ERROR", e);
			return ERROR;
		}
    }
    public String updateRemark(){
    	try {
    		String formId = request.getParameter("formId");
        	String remark = request.getParameter("formRemark");
        	
        	Form v = new Form();
        	v.setFormId(Integer.valueOf(formId));
        	v.setRemark(remark);
        	
        	dataFormService.updateFormRemark(v);
        	return SUCCESS;
		} catch (RuntimeException e) {
			log.error("updateRemark ERROR", e);
			return ERROR;
		}
    }
	/* getter and setter */
	public void setDataFormService(DataFormService dataFormService) {
		this.dataFormService = dataFormService;
	}
	
    public void setSysUploadFileService(SysUploadFileService sysUploadFileService) {
		this.sysUploadFileService = sysUploadFileService;
	}
	/**
	 * 下载资料附件
	 * 
	 * @return
	 */
	public void downForm() {
		try {
			String fileId = request.getParameter("fileId");
            CustomerData data = new CustomerData();
            data.setCreateUser(this.getLoginInfo().getUserId());
			sysUploadFileService.downFile(Integer.parseInt(fileId),data);
		}catch (RuntimeException e) {
			log.error("FormAction downForm error:" + e);
		}
	}

}
