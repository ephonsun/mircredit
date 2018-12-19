/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :资料管理action
 * Author     :yuanme
 * Create Date:Jun 1, 2012
 */
package com.banger.mobile.webapp.action.data;

import com.banger.mobile.domain.model.data.Video;
import com.banger.mobile.facade.data.DataVideoService;
import com.banger.mobile.util.ServerRealPathUtil;
import com.banger.mobile.util.StringUtil;
import com.banger.mobile.webapp.action.BaseAction;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yuanme
 * @version $Id: DataAction.java,v 0.1 Jun 1, 2012 11:12:15 AM yuanme Exp $
 */
public class VideoAction extends BaseAction implements ServletResponseAware{
	private static final long serialVersionUID = -5711003817279074329L;
	private DataVideoService dataVideoService;
	private HttpServletResponse response;
  
    /**
     * 查询资料
     * @return
     */
    public String queryDataList() {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
    	
        try {
            String videoName = request.getParameter("videoName");
            String recordDateFrom = request.getParameter("videoRecordDateFrom");
            String recordDateTo = request.getParameter("videoRecordDateTo");
            String uploadDateFrom = request.getParameter("videoUploadDateFrom");
            String uploadDateTo = request.getParameter("videoUploadDateTo");
            String customerDataId = request.getParameter("customerDataId");
            Map<String, Object> parameterMap = new HashMap<String, Object>();
            if (StringUtils.isNotEmpty(videoName)) {
                parameterMap.put("videoName", videoName);
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
            
            List<Video> videoList = dataVideoService.queryVideoData(parameterMap,this.getPage());
            int count = this.getPage().getTotalRowsAmount();
            
            request.setAttribute("videoList", videoList);
            request.setAttribute("count", String.valueOf(count));
           

            return SUCCESS;
        } catch (Exception e) {
            log.error("queryDataList ERROR", e);
            return ERROR;
        }
    }
    public String firstLoadVideoList(){
    	try {
    		String customerDataId = request.getParameter("customerDataId");
    		if(StringUtils.isNotEmpty(customerDataId)){
    			Map<String , Object> parameterMap = new HashMap<String , Object>();
    			parameterMap.put("customerDataId", customerDataId);
    			
    			List<Video> videoList = dataVideoService.queryVideoData(parameterMap, this.getPage());
    			int count = this.getPage().getTotalRowsAmount();
    			String customerName = dataVideoService.getCustomerNameByDataId(Integer.valueOf(customerDataId));
    			
    			request.setAttribute("videoList", videoList);
    			request.setAttribute("customerName", customerName);
    			request.setAttribute("count", String.valueOf(count));
    			request.setAttribute("customerDataId", customerDataId);
    			return SUCCESS;
    		}else{
    			return ERROR;
    		}
			
		} catch (RuntimeException e) {
			log.error("queryVideoList ERROR", e);
			return ERROR;
		}
    }
    public String loadOldRemark(){
    	try {
			String videoId = request.getParameter("videoId");
			
			String videoRemark = dataVideoService.getVideoRemark(Integer.valueOf(videoId));
			
			request.setAttribute("videoId", videoId);
			request.setAttribute("videoRemark", videoRemark);
			return SUCCESS;
		} catch (RuntimeException e) {
			log.error("loadOldRemark ERROR", e);
			return ERROR;
		}
    }
    public String updateRemark(){
    	try {
    		String videoId = request.getParameter("videoId");
        	String remark = request.getParameter("videoRemark");
        	
        	Video v = new Video();
        	v.setVideoId(Integer.valueOf(videoId));
        	v.setRemark(remark);
        	
        	dataVideoService.updateVideoRemark(v);
        	return SUCCESS;
		} catch (RuntimeException e) {
			log.error("updateRemark ERROR", e);
			return ERROR;
		}
    }
    /**
     * 输出视频流
     * 
     */
    public String playVideo(){
//以流形式输出  失败！！！
//    	ActionContext.getContext().getActionInvocation().getProxy().setExecuteResult(false);
//    	InputStream ins = null;
//    	File videoFile = new File("");
//    	response.setContentType("application/x-shockwave-flash");
//    	response.addHeader("Content-Disposition", "attachment; filename=\"" + videoFile.getName() + "\"");
//      response.setContentLength((int)videoFile.length());
//    	try {
//			OutputStream  outs = response.getOutputStream();
//			ins = new FileInputStream(videoFile);
//			int in = ins.read();
//			while(in!=-1){
//				outs.write(in);
//				in = ins.read();
//			}
//			outs.flush();
//			return null;
//		} catch (Exception e) {
//			log.error("platVideo ERROR", e);
//			return ERROR;
//		}
//用拷贝文件方式
    	
    	try {
    		//拿到文件在硬盘上的路径和文件
    		String initPath = request.getParameter("filePath");
    		String initName = request.getParameter("fileName");
    		if(StringUtil.isNotEmpty(initPath) && StringUtil.isNotEmpty(initName)){
	    		//创建该file对象
	    		File videoFile = new File(initPath+"/"+initName);
	    		//获得存储临时文件的项目下目录
	        	String recidesPath = ServerRealPathUtil.getServerRealPath()+"Records/";
	        	//获得一个唯一id， 用来命名文件
	        	String fileName = StringUtil.GetMD5(initName.substring(0, initName.lastIndexOf(".")))+"."+FilenameUtils.getExtension(initName);
	        	
	        	//在项目下创建一个临时文件
	        	File videoTemp = new File(recidesPath+fileName);
	        	//判断文件是否存在
	        	if(!videoTemp.exists()){
					videoTemp.setLastModified(System.currentTimeMillis());
	        		FileUtils.copyFile(videoFile, videoTemp);
	        	}
	        	//输出临时下文件名
				response.getOutputStream().write(fileName.getBytes());
    		}
			return null;
		} catch (Exception e) {
			log.error("platVideo ERROR", e);
			return ERROR;
		}
    }

    /** get set **/
  

	public void setDataVideoService(DataVideoService dataVideoService) {
		this.dataVideoService = dataVideoService;
	}
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
    
	


}
