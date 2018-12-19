/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :资料管理action
 * Author     :yuanme
 * Create Date:Jun 1, 2012
 */
package com.banger.mobile.webapp.action.data;

import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncodingAttributes;

import java.io.File;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.base.customer.BaseCrmCustomer;
import com.banger.mobile.domain.model.data.Audio;
import com.banger.mobile.domain.model.data.CustomerData;
import com.banger.mobile.domain.model.data.Event;
import com.banger.mobile.domain.model.data.Form;
import com.banger.mobile.domain.model.data.Mms;
import com.banger.mobile.domain.model.data.Photo;
import com.banger.mobile.domain.model.data.SearchData;
import com.banger.mobile.domain.model.data.Sms;
import com.banger.mobile.domain.model.data.Video;
import com.banger.mobile.domain.model.uploadFile.SysUploadFile;
import com.banger.mobile.facade.customer.CrmCustomerService;
import com.banger.mobile.facade.data.CustomerDataService;
import com.banger.mobile.facade.data.DataAudioService;
import com.banger.mobile.facade.data.DataFormService;
import com.banger.mobile.facade.data.DataMmsService;
import com.banger.mobile.facade.data.DataPhotoService;
import com.banger.mobile.facade.data.DataSmsService;
import com.banger.mobile.facade.data.DataVideoService;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.specialDataAuth.SpecialDataAuthService;
import com.banger.mobile.facade.uploadFile.SysUploadFileService;
import com.banger.mobile.util.StringUtil;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * @author yuanme
 * @version $Id: DataAction.java,v 0.1 Jun 1, 2012 11:12:15 AM yuanme Exp $
 */
public class DataAction extends BaseAction {
    private static final long   serialVersionUID = 4848170648131345651L;

    private CustomerDataService customerDataService;
    private DataAudioService dataAudioService;
    private DataPhotoService dataPhotoService;
    private DataVideoService dataVideoService;
    private DataFormService dataFormService;
    private DeptFacadeService deptFacadeService;
    private DataSmsService dataSmsService;
    private DataMmsService dataMmsService;
    private CrmCustomerService crmCustomerService;
    private SysUploadFileService sysUploadFileService;
    private SpecialDataAuthService specialDataAuthService;
    private PageUtil<CustomerData> customerDataList;
    private PageUtil<Audio> audioDataList;
    private PageUtil<Photo> photoDataList;
    private PageUtil<Video> videoDataList;
    private PageUtil<Form> formDataList;
    private PageUtil<Sms> smsDataList;
    private PageUtil<Mms> mmsDataList;
    private List<CustomerData> cusDataList; 
    
    private CustomerData customerData;
    private Audio audio;
    private Photo photo;
    private SearchData searchData;  //存储所有页面的查询条件
    
    SimpleDateFormat df0=new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
    SimpleDateFormat df1=new SimpleDateFormat("yyyy-MM-dd HH:mm:59");
    SimpleDateFormat df2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    /**
     * 点菜单打开
     * @return
     */
    public String firstLoadDataList() {
        try {
            List<Event> eventList = customerDataService.getEventTypeList();
            request.setAttribute("eventList", eventList);

            Map<String, Object> parameterMap = new HashMap<String, Object>();
            List<CustomerData> dataList = customerDataService.getCustomerDataListPage(parameterMap,
                this.getPage());
            int count = this.getPage().getTotalRowsAmount();
            request.setAttribute("count", count);
            request.setAttribute("dataList", dataList);

            return SUCCESS;
        } catch (RuntimeException e) {
            log.error("firstLoadDataList ERROR", e);
            return ERROR;
        }
    }

    /**
     * 查询资料
     * @return
     */
    public String queryDataList() {
        try {
            String customerName = request.getParameter("customerName");
            String eventId = request.getParameter("eventId");
            String userIds = request.getParameter("userIds");

            Map<String, Object> parameterMap = new HashMap<String, Object>();

            if (StringUtils.isNotEmpty(customerName)) {
                parameterMap.put("customerName", customerName);
            }
            if (StringUtils.isNotEmpty(eventId)) {
                parameterMap.put("eventId", Integer.valueOf(eventId));
            }
            if (StringUtils.isNotEmpty(userIds)) {
                parameterMap.put("userIds", userIds);
            }

            List<CustomerData> dataList = customerDataService.getCustomerDataListPage(parameterMap,
                this.getPage());
            int count = this.getPage().getTotalRowsAmount();
            request.setAttribute("dataList", dataList);
            request.setAttribute("count", count);

            return SUCCESS;
        } catch (RuntimeException e) {
            log.error("queryDataList ERROR", e);
            return ERROR;
        }
    }

    /** get set **/
    public void setCustomerDataService(CustomerDataService customerDataService) {
        this.customerDataService = customerDataService;
    }
    
    
    //以下为新资料管理 (huangk)
    /**
     * 资料管理列表
     * @param parameterMap
     * @param page
     * @return 
     */
    public String getAllCustomerDataPage(){
    	try {
    		String fenye = request.getParameter("fenye");
        	Map<String, Object> parameterMap = new HashMap<String, Object>();
        	String userIds="";//当前用户所管理的所有人员id
            boolean isInChargeof = deptFacadeService.isInChargeOfDepartment();
        	boolean flag = deptFacadeService.isSpecialData(getLoginInfo().getRoles(), "dataForm");
            if(deptFacadeService.isInChargeOfDepartment() && !flag){
                userIds = deptFacadeService.getUserIdsBelongToManager(getLoginInfo().getRoles(),"dataForm");
        		if (isInChargeof) {
        			String inChargeOfDeptIds = getCurrentUserInChargeOfDeptIds();
            		parameterMap.put("deptIds", inChargeOfDeptIds);
        		}
            }else{
                userIds = this.getLoginInfo().getUserId()+"";
            }
            parameterMap.put("userIds", userIds);
        	customerDataList = this.customerDataService.getAllCustomerDataPage(parameterMap, this.getPage(),customerData);
        	request.setAttribute("count", customerDataList.getPage().getTotalRowsAmount());
        	if(fenye!=null&&fenye.equals("1")){
                return "toPage";
            }else{
                return SUCCESS;
            }
    	} catch (Exception e) {
            log.error("DataAction getAllCustomerDataPage error:" + e.getMessage());
            return ERROR;
        }
    }
    
    /**
     * 根据客户id查看客户的录音、照片、视频等信息
     * @param parameterMap
     * @param page
     * @return 
     */
    public String queryDataByCusId(){
    	try {
    		String fenye = request.getParameter("fenye");
    		String type = request.getParameter("type");//1:录音，2:照片,3:视频,4:短信，5:彩信，6:资料表
    		String customerId = request.getParameter("customerId");
    		String eventName = request.getParameter("eventName");
    		String eventId = request.getParameter("eventId");
    		String toPage = "toPage";//设置跳转页面的参数
    		String belongToType = request.getParameter("BelongToType");
    		String userIds = request.getParameter("userIds");
        	Map<String, Object> parameterMap = new HashMap<String, Object>();
        	parameterMap.put("customerId",customerId);
        	parameterMap.put("eventId", eventId);
        	if(StringUtil.isNotEmpty(eventName)){
	        	String s[] = eventName.split(",");
	        	eventName = "";
	        	for(int i = 0; i < s.length; i++){
	        		if(i + 1 == s.length)eventName += "'"+s[i]+"'";
	                else eventName += "'"+s[i]+"',";
	        	}
	        	parameterMap.put("eventName",eventName);
        	}
        	if(belongToType!=null&&belongToType.equals("brMine")){
        	    parameterMap.put("userIds",this.getLoginInfo().getUserId());
        	}else if(belongToType!=null&&belongToType.equals("brUser")){
        	    parameterMap.put("userIds",userIds);
        	}
//        	else{
//        		parameterMap.put("userIds",this.getLoginInfo().getUserId());
//        	}
        	if(type.equals("1")){//录音
        		audioDataList = dataAudioService.getCustomerAudioDataPage(parameterMap, this.getPage(), searchData);
        		request.setAttribute("count", audioDataList.getPage().getTotalRowsAmount());
        		toPage += "1";//设置指定跳转页面
        	}else if(type.equals("2")){//照片
        		photoDataList = dataPhotoService.getCustomerPhotoDataPage(parameterMap, this.getPage(), searchData);
        		request.setAttribute("count", photoDataList.getPage().getTotalRowsAmount());
        		toPage += "2";
        	}else if(type.equals("3")){//视频
        		videoDataList = dataVideoService.getCustomerVideoDataPage(parameterMap, this.getPage(), searchData);
        		request.setAttribute("count", videoDataList.getPage().getTotalRowsAmount());
        		toPage += "3";
        	}else if(type.equals("4")){//短信
        		smsDataList = dataSmsService.getCustomerSmsDataPage(parameterMap, this.getPage(), searchData);
        		request.setAttribute("count", smsDataList.getPage().getTotalRowsAmount());
        		toPage += "4";
        	}else if(type.equals("5")){//彩信
        		mmsDataList = dataMmsService.getCustomerMmsDataPage(parameterMap, this.getPage(), searchData);
        		request.setAttribute("count", mmsDataList.getPage().getTotalRowsAmount());
        		toPage += "5";
        	}else if(type.equals("6")){//资料表
        		formDataList = dataFormService.getCustomerFormDataPage(parameterMap, this.getPage(), searchData);
        		request.setAttribute("count", formDataList.getPage().getTotalRowsAmount());
        		toPage += "6";
        	}
        	request.setAttribute("type", type);
        	request.setAttribute("customerName", crmCustomerService.getCrmCustomerById(Integer.parseInt(customerId)).getCustomerName());
        	request.setAttribute("customerId", customerId);
        	request.setAttribute("eventId", eventId);
        	request.setAttribute("dataCode", this.getLoginInfo().getDataCompetence());
        	String roleIds=StringUtil.getIdsString(getLoginInfo().getRoles());
            request.setAttribute("dataAuth", specialDataAuthService.getSysDataAuthCondition(roleIds,"record"));
        	if(fenye != null && fenye.equals("1")){
                return toPage;
            }else{
                return SUCCESS;
            }
    	} catch (Exception e) {
            log.error("DataAction queryDataByCusId error:" + e.getMessage());
            return ERROR;
        }
    }
    
    /**
     * 保存录音备注
     * @return
     */
    public void saveAudioRemark(){
        try {
            String id = request.getParameter("id");
            String remark = request.getParameter("remark");
            Audio v = new Audio();
            v.setAudioId(Integer.valueOf(id));
            v.setRemark(remark);
            dataAudioService.updateAudioRemark(v);
        } catch (Exception e) {
            log.error("DataAction saveAudioRemark error:" + e.getMessage());
        }
    }    
    
    /**
     * 保存视频备注
     * @return
     */
    public void saveVideoRemark(){
        try {
            String id = request.getParameter("id");
            String remark = request.getParameter("remark");
            Video v = new Video();
            v.setVideoId(Integer.valueOf(id));
            v.setRemark(remark);
            dataVideoService.updateVideoRemark(v);
        } catch (Exception e) {
            log.error("DataAction saveVideoRemark error:" + e.getMessage());
        }
    }   
    
    /**
     * 客户管理查看资料小页卡
     * @return
     */
    public String showDataByCusId(){
        try {
            Map<String, Object> parameterMap = new HashMap<String, Object>();
            parameterMap.put("customerId", request.getParameter("customerId"));
            BaseCrmCustomer baseCrmCustomer = crmCustomerService.getCrmCustomerById(Integer.parseInt(request.getParameter("customerId")));
            cusDataList = customerDataService.QueryDataByCusId(parameterMap);
            request.setAttribute("dataCustomerId",baseCrmCustomer.getCustomerId());
            request.setAttribute("dataCustomerName",baseCrmCustomer.getCustomerName());
            request.setAttribute("needLine", request.getParameter("needLine")==null?1:request.getParameter("needLine"));
            return SUCCESS;
        } catch (Exception e) {
            log.error("DataAction QueryDataByCusId error:" + e.getMessage());
            return ERROR;
        }
    }
    
    /**
     * 查看客户资料照片详情层
     * @return
     */
    public String queryPhotoDetail(){
        try {
            photo= dataPhotoService.getPhotoById(Integer.parseInt(request.getParameter("photoId")));
            String systemPath = this.getRootPath()+File.separator+"Records";
            request.setAttribute("pid", request.getParameter("pid"));
            String copyFileName = customerDataService.readFile(photo.getFileId()+"", systemPath);
            request.setAttribute("photoPath", copyFileName.substring(copyFileName.lastIndexOf(File.separator)+1,copyFileName.length()));
            request.setAttribute("photoCount", request.getParameter("photoCount"));
            request.setAttribute("customerName", crmCustomerService.getCrmCustomerById(Integer.parseInt(request.getParameter("customerId"))).getCustomerName());
            String eventName = request.getParameter("eventName");
            request.setAttribute("photo", photo); 
            request.setAttribute("eventId", request.getParameter("eventId"));
            return SUCCESS;
        } catch (Exception e) {
            log.error("DataAction queryPhotoDetail error:" + e.getMessage());
            return ERROR;
        }
    }
    
    /**
     * 保存照片备注
     * @return
     */
    public void savePhotoRemark(){
        try {
            String id = request.getParameter("id");
            String remark = request.getParameter("remark");
            Photo v = new Photo();
            v.setPhotoId(Integer.valueOf(id));
            v.setRemark(remark);
            dataPhotoService.updatePhotoRemark(v);
        } catch (Exception e) {
            log.error("DataAction saveVideoRemark error:" + e.getMessage());
        }
    }   
    
    /**
     * 切换照片
     * @return
     */
    public void changePhoto(){
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter out = response.getWriter();
            String customerId = request.getParameter("customerId");
            String eventId = request.getParameter("eventId");
            String belongToType = request.getParameter("BelongToType");
            String userIds = request.getParameter("userIds");
            String numRow = request.getParameter("numRow");
            String systemPath = this.getRootPath()+File.separator+"Records";
            
            Map<String, Object> parameterMap = new HashMap<String, Object>();
            parameterMap.put("customerId",customerId);
            parameterMap.put("eventId",eventId);
            parameterMap.put("dataName", searchData.getDataName());
            if(searchData.getCreateStartDate()!=null)parameterMap.put("createStartDate", df0.format(searchData.getCreateStartDate()));
            if(searchData.getCreateEndDate()!=null)parameterMap.put("createEndDate", df1.format(searchData.getCreateEndDate()));
            if(searchData.getUploadStartDate()!=null)parameterMap.put("uploadStartDate", df0.format(searchData.getUploadStartDate()));
            if(searchData.getUploadEndDate()!=null)parameterMap.put("uploadEndDate", df1.format(searchData.getUploadEndDate()));
            
            if(belongToType!=null&&belongToType.equals("brMine")){
                parameterMap.put("userIds",this.getLoginInfo().getUserId());
            }else if(belongToType!=null&&belongToType.equals("brUser")){
                parameterMap.put("userIds",userIds);
            }
            
            parameterMap.put("numRow",numRow);
            photo = dataPhotoService.getPhotoByNumRow(parameterMap);
            
            JSONObject jobj = JSONObject.fromObject(photo);
            String copyFileName = customerDataService.readFile(photo.getFileId()+"", systemPath);
            jobj.put("photoPath", copyFileName.substring(copyFileName.lastIndexOf(File.separator)+1,copyFileName.length()));
            jobj.put("uploadDateStr", df2.format(photo.getUploadDate()));
            if(photo.getRecordDate()!=null){
            	jobj.put("recordDateStr", df2.format(photo.getRecordDate()));
            }else{
            	jobj.put("recordDateStr","");
            }
            out.print(jobj.toString());
            out.close();
            out.flush();
        } catch (Exception e) {
            log.error("DataAction saveVideoRemark error:" + e.getMessage());
        }
    }   

    /**
     * 文件读取接口(验证文件是否有效并复制到服务器下再输出文件名)
     */
    public void readFile(){
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        try {
        PrintWriter out = response.getWriter();
        String fileId=request.getParameter("fileId");
        String systemPath = this.getRootPath()+File.separator+"Records";
        String copyFileName = customerDataService.readFile(fileId, systemPath);
    	out.print(copyFileName.substring(copyFileName.lastIndexOf(File.separator)+1,copyFileName.length()));
        out.close();
        } catch (Exception e) {
            log.error("dateAction readFile action error:"+e.getMessage());
        }
    }
    
    /**
     * 文件读取接口(验证文件是否有效并复制到服务器下再输出文件名并转换格式mp3)
     */
    public void transcoding(){
    	HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        try {
        PrintWriter out = response.getWriter();
        String fileId=request.getParameter("fileId");
        String systemPath = this.getRootPath()+File.separator+"Records";
        String copyFileName = customerDataService.readFile(fileId, systemPath);//复制后的文件名
        copyFileName = copyFileName.substring(copyFileName.lastIndexOf(File.separator)+1, copyFileName.length());//文件复制方法返回的是绝对地址所以要去掉文件路径只要名称
        int i = copyFileName.lastIndexOf(".");
        if(!(copyFileName.substring(i+1,copyFileName.length())).equals("aac") && !copyFileName.equals("0")){
	        //开始转码
	        File target = new File(systemPath + File.separator + copyFileName.substring(0,i+1)+"mp3");
	        File source = new File(systemPath + File.separator + copyFileName);
	        AudioAttributes audio = new AudioAttributes();
	        audio.setCodec("libmp3lame");
	        EncodingAttributes attrs = new EncodingAttributes();
	        attrs.setFormat("mp3");
	        attrs.setAudioAttributes(audio);
	        Encoder encoder = new Encoder();
	        try {
		        encoder.encode(source, target, attrs);
			} catch (Exception e) {
				log.error("dateAction transcoding-encoder error:"+e.getMessage());
			}
	     	out.print(target.getName());
        }else{
        	out.print(copyFileName);
        }
        out.close();
        } catch (Exception e) {
            log.error("dateAction transcoding action error:", e);
        }
    }
    
    /**
     * 文件读取接口(根据fileId验证文件是否存在)
     */
    public void isExistFileById(){
    	HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        try {
        	PrintWriter out = response.getWriter();
        	String fileId=request.getParameter("fileId");
	    	if(StringUtil.isEmpty(fileId)){
	            out.print(0);
	        }else{
                Integer curUserId = sysUploadFileService.getSysUploadFileById(Integer.parseInt(fileId)).getUploadUserId();
                CustomerData data = new CustomerData();
                data.setCreateUser(curUserId);
	        	File file = sysUploadFileService.getRealFile(Integer.parseInt(fileId),data);
	            if(file.exists()){
	                out.print(1);
	            }else{
	                out.print(0);
	            } 
	        }
	        out.close();
        } catch (Exception e) {
            log.error("dateAction isExistFileById action error:"+e.getMessage());
        }
    }
    
    /**
     * 根据文件id获取文件路径及名称
     */
    public void queryFileByFileId(){
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        try {
        PrintWriter out = response.getWriter();
        String fileId=request.getParameter("fileId");
        if(StringUtil.isNotEmpty(fileId)){
        	SysUploadFile sysUploadFile = new SysUploadFile();
            sysUploadFile = sysUploadFileService.getSysUploadFileById(Integer.parseInt(fileId));
            out.print(sysUploadFile.getFilePath()+File.separator+sysUploadFile.getFileName());
        }else{
        	out.print(0);
        }
        out.close();
        } catch (Exception e) {
            log.error("dateAction readFile action error:"+e.getMessage());
        }
    }
    
    /**
     * 根据id和类型获取对应的备注
     * 
     */
    public void queryDataRemark(){
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        try {
        PrintWriter out = response.getWriter();
        String id=request.getParameter("id");
        String type=request.getParameter("type");
        if(StringUtil.isNotEmpty(id)){
        	if(type.equals("1")){//录音
        		out.print(dataAudioService.getAudioRemark(Integer.parseInt(id)));
        	}else if(type.equals("2")){//图片
        		out.print(dataPhotoService.getPhotoRemark(Integer.parseInt(id)));
        	}else if(type.equals("3")){//视频
        		out.print(dataVideoService.getVideoRemark(Integer.parseInt(id)));
        	}
        }
        out.close();
        } catch (Exception e) {
            log.error("dateAction queryDataRemark action error:"+e.getMessage());
        }
    }
    
    
    /**
	 * 当前用户有权限的 机构ids
	 * 
	 * @return
	 */
	private String getCurrentUserInChargeOfDeptIds() {
		String deptIds = "";
		Integer[] arr = deptFacadeService.getInChargeOfDeptIds();
		if (arr != null) {
			for (int i = 0; i < arr.length; i++) {
				if (deptIds.equals(""))
					deptIds = arr[i].toString();
				else
					deptIds = deptIds + "," + arr[i];
			}
		}
		return deptIds;
	}
    
	public void reNameToFile(){
		HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        try {
        PrintWriter out = response.getWriter();
        String oldName=request.getParameter("oldName");
        String newName=request.getParameter("newName");
        if(oldName == newName){
        	out.print(this.getRootPath()+"/Records/"+newName);
        }else{
        	File oldFile = new File(this.getRootPath()+"/Records/"+oldName);
        	if (oldFile.exists()){
        		File newFile = new File(this.getRootPath()+"/Records/"+newName);
        		if (newFile.exists()){
        			newFile.delete();
        		}
        		if (oldFile.renameTo(newFile)) {
        			out.print(this.getRootPath()+"/Records/"+newName);
    			}
        	}
        }
        out.close();
        out.flush();
        } catch (Exception e) {
            log.error("dateAction reNameToFile action error:"+e.getMessage());
        }
	}
	
	public PageUtil<CustomerData> getcustomerDataList() {
		return customerDataList;
	}

	public void setcustomerDataList(PageUtil<CustomerData> customerDataList) {
		this.customerDataList = customerDataList;
	}

	public CustomerData getCustomerData() {
		return customerData;
	}

	public void setCustomerData(CustomerData customerData) {
		this.customerData = customerData;
	}

	public void setDeptFacadeService(DeptFacadeService deptFacadeService) {
		this.deptFacadeService = deptFacadeService;
	}

	public PageUtil<Audio> getAudioDataList() {
		return audioDataList;
	}

	public void setAudioDataList(PageUtil<Audio> audioDataList) {
		this.audioDataList = audioDataList;
	}

	public PageUtil<Photo> getPhotoDataList() {
		return photoDataList;
	}

	public void setPhotoDataList(PageUtil<Photo> photoDataList) {
		this.photoDataList = photoDataList;
	}

	public PageUtil<Video> getVideoDataList() {
		return videoDataList;
	}

	public void setVideoDataList(PageUtil<Video> videoDataList) {
		this.videoDataList = videoDataList;
	}

	public PageUtil<Form> getFormDataList() {
		return formDataList;
	}

	public void setFormDataList(PageUtil<Form> formDataList) {
		this.formDataList = formDataList;
	}

	public void setDataAudioService(DataAudioService dataAudioService) {
		this.dataAudioService = dataAudioService;
	}

	public void setDataPhotoService(DataPhotoService dataPhotoService) {
		this.dataPhotoService = dataPhotoService;
	}

	public void setDataVideoService(DataVideoService dataVideoService) {
		this.dataVideoService = dataVideoService;
	}

	public void setDataFormService(DataFormService dataFormService) {
		this.dataFormService = dataFormService;
	}

	public Audio getAudio() {
		return audio;
	}

	public void setAudio(Audio audio) {
		this.audio = audio;
	}

	public PageUtil<CustomerData> getCustomerDataList() {
		return customerDataList;
	}

	public void setCustomerDataList(PageUtil<CustomerData> customerDataList) {
		this.customerDataList = customerDataList;
	}

	public PageUtil<Sms> getSmsDataList() {
		return smsDataList;
	}

	public void setSmsDataList(PageUtil<Sms> smsDataList) {
		this.smsDataList = smsDataList;
	}

	public PageUtil<Mms> getMmsDataList() {
		return mmsDataList;
	}

	public void setMmsDataList(PageUtil<Mms> mmsDataList) {
		this.mmsDataList = mmsDataList;
	}

	public void setDataSmsService(DataSmsService dataSmsService) {
		this.dataSmsService = dataSmsService;
	}

	public void setDataMmsService(DataMmsService dataMmsService) {
		this.dataMmsService = dataMmsService;
	}

    public SearchData getSearchData() {
        return searchData;
    }

    public void setSearchData(SearchData searchData) {
        this.searchData = searchData;
    }

    public List<CustomerData> getCusDataList() {
        return cusDataList;
    }

    public void setCusDataList(List<CustomerData> cusDataList) {
        this.cusDataList = cusDataList;
    }

    public void setCrmCustomerService(CrmCustomerService crmCustomerService) {
        this.crmCustomerService = crmCustomerService;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

	public void setSysUploadFileService(SysUploadFileService sysUploadFileService) {
		this.sysUploadFileService = sysUploadFileService;
	}

	public void setSpecialDataAuthService(
			SpecialDataAuthService specialDataAuthService) {
		this.specialDataAuthService = specialDataAuthService;
	}

    
}
