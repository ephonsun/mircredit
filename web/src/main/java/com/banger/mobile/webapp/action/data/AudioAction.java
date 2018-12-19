package com.banger.mobile.webapp.action.data;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.banger.mobile.domain.model.data.Audio;
import com.banger.mobile.facade.data.DataAudioService;
import com.banger.mobile.webapp.action.BaseAction;

public class AudioAction extends BaseAction implements ServletResponseAware{

	private static final long serialVersionUID = 6521705060967238308L;
	
	private DataAudioService dataAudioService;
	
	private HttpServletResponse response;
	  
    /**
     * 查询资料
     * @return
     */
    public String queryDataList() {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
    	
        try {
            String audioName = request.getParameter("audioName");
            String recordDateFrom = request.getParameter("audioRecordDateFrom");
            String recordDateTo = request.getParameter("audioRecordDateTo");
            String uploadDateFrom = request.getParameter("audioUploadDateFrom");
            String uploadDateTo = request.getParameter("audioUploadDateTo");
            String customerDataId = request.getParameter("customerDataId");
            Map<String, Object> parameterMap = new HashMap<String, Object>();
            if (StringUtils.isNotEmpty(audioName)) {
                parameterMap.put("audioName", audioName);
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
            
            List<Audio> audioList = dataAudioService.queryAudioData(parameterMap,this.getPage());
            int count = this.getPage().getTotalRowsAmount();
            request.setAttribute("audioList", audioList);
            request.setAttribute("count", String.valueOf(count));
           

            return SUCCESS;
        } catch (Exception e) {
            log.error("queryDataList ERROR", e);
            return ERROR;
        }
    }
    public String firstLoadAudioList(){
    	try {
    		String customerDataId = request.getParameter("customerDataId");
    		if(StringUtils.isNotEmpty(customerDataId)){
    			Map<String , Object> parameterMap = new HashMap<String , Object>();
    			parameterMap.put("customerDataId", customerDataId);
    			List<Audio> audioList = dataAudioService.queryAudioData(parameterMap, this.getPage());
    			int count = this.getPage().getTotalRowsAmount();
    			String customerName = dataAudioService.getCustomerNameByDataId(Integer.valueOf(customerDataId));
    			
    			request.setAttribute("audioList", audioList);
    			request.setAttribute("customerName", customerName);
    			request.setAttribute("count", String.valueOf(count));
    			request.setAttribute("customerDataId", customerDataId);
    			return SUCCESS;
    		}else{
    			return ERROR;
    		}
			
		} catch (RuntimeException e) {
			log.error("queryAudioList ERROR", e);
			return ERROR;
		}
    }
    public String loadOldRemark(){
    	try {
			String audioId = request.getParameter("audioId");
			
			String audioRemark = dataAudioService.getAudioRemark(Integer.valueOf(audioId));
			
			request.setAttribute("audioId", audioId);
			request.setAttribute("audioRemark", audioRemark);
			return SUCCESS;
		} catch (RuntimeException e) {
			log.error("loadOldRemark ERROR", e);
			return ERROR;
		}
    }
    public String updateRemark(){
    	try {
    		String audioId = request.getParameter("audioId");
        	String remark = request.getParameter("audioRemark");
        	
        	Audio v = new Audio();
        	v.setAudioId(Integer.valueOf(audioId));
        	v.setRemark(remark);
        	
        	dataAudioService.updateAudioRemark(v);
        	return SUCCESS;
		} catch (RuntimeException e) {
			log.error("updateRemark ERROR", e);
			return ERROR;
		}
    }

    /** get set **/
  

	public void setDataAudioService(DataAudioService dataAudioService) {
		this.dataAudioService = dataAudioService;
	}
	
	public void setServletResponse(HttpServletResponse resp) {
		this.response = resp;
		
	}
	public static void main(String[] args) {
		String[] a = {"aaaa","bbbb","cccccc"}; 
		System.out.println(a.toString());
	}
}
