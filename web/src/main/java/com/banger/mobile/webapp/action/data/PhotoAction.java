package com.banger.mobile.webapp.action.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.banger.mobile.domain.model.data.Photo;
import com.banger.mobile.domain.model.loan.LnCreditHistory;
import com.banger.mobile.domain.model.loan.LnLoan;
import com.banger.mobile.domain.model.loan.LnLoanInfo;
import com.banger.mobile.domain.model.loan.LnLoanType;
import com.banger.mobile.facade.data.DataPhotoService;
import com.banger.mobile.facade.loan.LnLoanInfoService;
import com.banger.mobile.webapp.action.BaseAction;

public class PhotoAction extends BaseAction implements ServletResponseAware{

	private static final long serialVersionUID = 24168369698884653L;
	
	private DataPhotoService dataPhotoService;
	private LnLoanInfoService lnLoanInfoService;
	private HttpServletResponse response;
	
	private List<Photo> photoMenuList;
	private List<Photo> photoList;
    /**
     * 查询资料
     * @return
     */
    public String queryDataList() {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
    	
        try {
            String photoName = request.getParameter("photoName");
            String recordDateFrom = request.getParameter("photoRecordDateFrom");
            String recordDateTo = request.getParameter("photoRecordDateTo");
            String uploadDateFrom = request.getParameter("photoUploadDateFrom");
            String uploadDateTo = request.getParameter("photoUploadDateTo");
            String customerDataId = request.getParameter("customerDataId");
            Map<String, Object> parameterMap = new HashMap<String, Object>();
            if (StringUtils.isNotEmpty(photoName)) {
                parameterMap.put("photoName", photoName);
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
            
            List<Photo> photoList = dataPhotoService.queryPhotoData(parameterMap,this.getPage());
            int count = this.getPage().getTotalRowsAmount();
            request.setAttribute("photoList", photoList);
            request.setAttribute("count", String.valueOf(count));
           

            return SUCCESS;
        } catch (Exception e) {
            log.error("queryDataList ERROR", e);
            return ERROR;
        }
    }
    public String firstLoadPhotoList(){
    	try {
    		String customerDataId = request.getParameter("customerDataId");
    		if(StringUtils.isNotEmpty(customerDataId)){
    			Map<String , Object> parameterMap = new HashMap<String , Object>();
    			parameterMap.put("customerDataId", customerDataId);
    			List<Photo> photoList = dataPhotoService.queryPhotoData(parameterMap, this.getPage());
    			int count = this.getPage().getTotalRowsAmount();
    			String customerName = dataPhotoService.getCustomerNameByDataId(Integer.valueOf(customerDataId));
    			request.setAttribute("photoList", photoList);
    			request.setAttribute("customerName", customerName);
    			request.setAttribute("count", String.valueOf(count));
    			request.setAttribute("customerDataId", customerDataId);
    			return SUCCESS;
    		}else{
    			return ERROR;
    		}
			
		} catch (RuntimeException e) {
			log.error("queryPhotoList ERROR", e);
			return ERROR;
		}
    }
    public String loadOldRemark(){
    	try {
			String photoId = request.getParameter("photoId");
			
			String photoRemark = dataPhotoService.getPhotoRemark(Integer.valueOf(photoId));
			
			request.setAttribute("photoId", photoId);
			request.setAttribute("photoRemark", photoRemark);
			return SUCCESS;
		} catch (RuntimeException e) {
			log.error("loadOldRemark ERROR", e);
			return ERROR;
		}
    }
    public String updateRemark(){
    	try {
    		String photoId = request.getParameter("photoId");
        	String remark = request.getParameter("photoRemark");
        	
        	Photo v = new Photo();
        	v.setPhotoId(Integer.valueOf(photoId));
        	v.setRemark(remark);
        	
        	dataPhotoService.updatePhotoRemark(v);
        	return SUCCESS;
		} catch (RuntimeException e) {
			log.error("updateRemark ERROR", e);
			return ERROR;
		}
    }
    //照片分类
    public String showPhotosClassification(){
    	try{
			int loanId = Integer.parseInt(request.getParameter("loanId"));
			int eventId = Integer.parseInt(request.getParameter("eventId"));
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("loanId", loanId);
			LnLoanInfo lnLoanInfo = lnLoanInfoService.selectLoanInfoList(param)
					.get(0);
			photoMenuList = dataPhotoService.getPhotoTtpeMenu(eventId, loanId);
			photoList = dataPhotoService.getPhotoListInfo(eventId, loanId, 0);
			int n1=0,n2=0,n3=0,n4=0,n5=0;
			for(Photo photo:photoMenuList){
				int type = photo.getPhotoTypeId();
				if(type==21||type==22||type==23){
					n2+=photo.getPhotoCount();
				}else if(type==5||type==6||type==7||type==8||type==9||type==10||type==12||type==14||type==24||type==25||type==43||type==44||type==45||type==46){
					n3+=photo.getPhotoCount();
				}else if(type==34||type==35){
					n4+=photo.getPhotoCount();
				}else if(type==13||type==26||type==27||type==28){
					n5+=photo.getPhotoCount();
				}else {
					n1+=photo.getPhotoCount();
				}
			}
			request.setAttribute("n1",n1);
			request.setAttribute("n2",n2);
			request.setAttribute("n3",n3);
			request.setAttribute("n4",n4);
			request.setAttribute("n5",n5);
			request.setAttribute("photoNoTypeCount", photoList.size());
			request.setAttribute("loanId", loanId);
			request.setAttribute("eventId", eventId);
			
			if (lnLoanInfo!=null) {
				request.setAttribute("customerId", lnLoanInfo.getCustomerId());
			}
		
    	return SUCCESS;
    	}catch(Exception e){
    		e.printStackTrace();
    		return ERROR;
    	}
    }
  //照片分类
    public String showPhotosList(){
    	try{
    	int loanId = Integer.parseInt(request.getParameter("loanId"));
        int eventId = Integer.parseInt(request.getParameter("eventId"));
        int photoTypeId = Integer.parseInt(request.getParameter("photoTypeId"));
    	photoList=dataPhotoService.getPhotoListInfo(eventId,loanId, photoTypeId);
    	request.setAttribute("loanId", loanId);
		request.setAttribute("eventId", eventId);
    	return SUCCESS;
    	}catch(Exception e){
    		e.printStackTrace();
    		return ERROR;
    	}
    }
    /**
     * 分配图片
     */
    public void updatePhotosType() {
        try {
        	String photoIds = request.getParameter("photoIds");
            int photoTypeId = Integer.parseInt(request.getParameter("photoTypeId"));
        	dataPhotoService.updatePhotoTypeById(photoIds, photoTypeId);
            PrintWriter out = this.getResponse().getWriter();
            out.print("success");
        } catch (Exception e) {
        }
    }
    /**
     * 分配图片
     */
    public void deletePhotos() {
        try {
        	String photoIds = request.getParameter("photoIds");
        	Map<String, Object> paramMap = new HashMap<String, Object>();
        	paramMap.put("photoId", photoIds);
        	dataPhotoService.updatePhotoById(paramMap);
            PrintWriter out = this.getResponse().getWriter();
            out.print("success");
        } catch (Exception e) {
        }
    }
    public String showPhoto(){
    	//以流形式输出  
    	try {
	    	String photoId = request.getParameter("photoId");
	    	Photo photo = dataPhotoService.getPhotoById(Integer.valueOf(photoId));
	    	File photoFile = new File(photo.getFilePath()+"/"+photo.getFileName());
	    	
	    	InputStream ins = null;
	    	response.setContentType("image/*");
	    	response.addHeader("Content-Disposition", "attachment; filename=\"" + photoFile.getName() + "\"");
	    	response.setContentLength((int)photoFile.length());
	    	
			OutputStream  outs = response.getOutputStream();
			ins = new FileInputStream(photoFile);
			int in = ins.read();
			while(in!=-1){
				outs.write(in);
				in = ins.read();
			}
			outs.flush();
			return null;
		} catch (Exception e) {
			log.error("platVideo ERROR", e);
			return ERROR;
		}
    }
	public String choosePhotosType(){
		try{
			String ftype = request.getParameter("ftype");
			int loanId = Integer.parseInt(request.getParameter("loanId"));
			int eventId = Integer.parseInt(request.getParameter("eventId"));
			photoMenuList = dataPhotoService.getPhotoTtpeMenu(eventId, loanId);
			request.setAttribute("ftype",ftype);
			request.setAttribute("photoMenuList",photoMenuList);
			return SUCCESS;
		}
		catch (Exception e){
			log.error("choosePhotosType ERROR", e);
			return ERROR;
		}
	}
    /** get set **/
  

	public void setDataPhotoService(DataPhotoService dataPhotoService) {
		this.dataPhotoService = dataPhotoService;
	}
	
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	public List<Photo> getPhotoMenuList() {
		return photoMenuList;
	}
	public void setPhotoMenuList(List<Photo> photoMenuList) {
		this.photoMenuList = photoMenuList;
	}
	public List<Photo> getPhotoList() {
		return photoList;
	}
	public void setPhotoList(List<Photo> photoList) {
		this.photoList = photoList;
	}
	public LnLoanInfoService getLnLoanInfoService() {
		return lnLoanInfoService;
	}
	public void setLnLoanInfoService(LnLoanInfoService lnLoanInfoService) {
		this.lnLoanInfoService = lnLoanInfoService;
	}
    
}
