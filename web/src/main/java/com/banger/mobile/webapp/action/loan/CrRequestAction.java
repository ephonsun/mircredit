package com.banger.mobile.webapp.action.loan;

import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.crRequest.CrRequest;
import com.banger.mobile.domain.model.data.CustomerData;
import com.banger.mobile.domain.model.data.Form;
import com.banger.mobile.domain.model.loan.LnLoan;
import com.banger.mobile.domain.model.loan.LnLoanInfo;
import com.banger.mobile.domain.model.loan.LnLoanInfoDictionary;
import com.banger.mobile.domain.model.uploadFile.SysUploadFile;
import com.banger.mobile.facade.crRequest.CrRequestService;
import com.banger.mobile.facade.data.CustomerDataService;
import com.banger.mobile.facade.data.DataFormService;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.impl.data.CaseHelperServiceImpl;
import com.banger.mobile.facade.loan.LnLoanInfoDictionaryService;
import com.banger.mobile.facade.loan.LnLoanInfoService;
import com.banger.mobile.facade.loan.LnLoanService;
import com.banger.mobile.facade.system.team.SysTeamUserService;
import com.banger.mobile.facade.uploadFile.SysUploadFileService;
import com.banger.mobile.util.DateUtil;
import com.banger.mobile.webapp.action.BaseAction;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.PrintWriter;
import java.util.*;



public class CrRequestAction extends BaseAction{

    private static final long serialVersionUID = -7458269709136501326L;

	private static Logger logger = Logger.getLogger(CrRequestAction.class);

    private CrRequestService crRequestService;
    private DeptFacadeService deptFacadeService;
    private LnLoanInfoDictionaryService lnLoanInfoDictionaryService; 
    private CaseHelperServiceImpl caseHelperService;
    private SysUploadFileService sysUploadFileService; // 文件上传service
    private CustomerDataService customerDataService;
    private SysTeamUserService sysTeamUserService;
    private LnLoanInfoService lnLoanInfoService;
    private LnLoanService  lnLoanService;
    
    private DataFormService  dataFormService;
    
    //查询条件
    private String customer;
    private Integer loanType;
    private String requestName;
    private Integer requestId;
    private File fileInput;
	private String fileInputFileName;
	private Integer upFileId;
    

	
    public DataFormService getDataFormService() {
		return dataFormService;
	}

	public void setDataFormService(DataFormService dataFormService) {
		this.dataFormService = dataFormService;
	}

	public LnLoanService getLnLoanService() {
		return lnLoanService;
	}

	public void setLnLoanService(LnLoanService lnLoanService) {
		this.lnLoanService = lnLoanService;
	}

	public LnLoanInfoService getLnLoanInfoService() {
		return lnLoanInfoService;
	}

	public void setLnLoanInfoService(LnLoanInfoService lnLoanInfoService) {
		this.lnLoanInfoService = lnLoanInfoService;
	}

	public SysTeamUserService getSysTeamUserService() {
		return sysTeamUserService;
	}

	public void setSysTeamUserService(SysTeamUserService sysTeamUserService) {
		this.sysTeamUserService = sysTeamUserService;
	}

	public Integer getUpFileId() {
		return upFileId;
	}

	public void setUpFileId(Integer upFileId) {
		this.upFileId = upFileId;
	}

	/**
     * 首页列表
     * @return
     */
    public String zhengXinLoanList(){
        try{
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 进入征信调查菜单...");
            //贷款类型
            Map<String,Object> paramMap = new HashMap<String,Object>();
            paramMap.put("dictionaryName", "ZXZT");
    		List<LnLoanInfoDictionary> lnLoanInfoDictionaryList = lnLoanInfoDictionaryService.selectLoanInfoDictionaryList(paramMap);
      
            //列表数据
            Map<String, Object> conds = new HashMap<String, Object>();
            
            String belongUserIds ="";
        	List<String> belongUserId = sysTeamUserService.getUserIdsByChiefUserId(this.getLoginInfo().getUserId());
        	int i = 0;
        	for (String str : belongUserId) {
        		if(i == 0){
        			belongUserIds +=  str;
        			i=1;
        		}else{
        			belongUserIds += "," + str;
        		}
			}
        	conds.put("belongUserIds", belongUserIds); 
            
          PageUtil<CrRequest> dataList = crRequestService.getCrRequestByPage(conds, this.getPage());
           logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 查询到所有征信调查，数量为"+dataList.getItems().size()+"条");
           
           request.setAttribute("typeList", lnLoanInfoDictionaryList);
           request.setAttribute("dataList", dataList.getItems());
           request.setAttribute("recordCount", dataList.getPage().getTotalRowsAmount());
           request.setAttribute("sysUserId",this.getLoginInfo().getUserId());
           return SUCCESS;
        }catch (Exception e){
            logger.error("needSubmitLoanList", e);
            e.printStackTrace();
            return ERROR;
        }
    }

    /**
     * 查询列表
     * @return
     */
    public String zhengXinLoanListQuery(){
    	

       try{
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 按条件搜索征信调查...");
            //列表数据
            Map<String, Object> conds = new HashMap<String, Object>();
            String belongUserIds ="";
        	List<String> belongUserId = sysTeamUserService.getUserIdsByChiefUserId(this.getLoginInfo().getUserId());
        	int i = 0;
        	for (String str : belongUserId) {
        		if(i == 0){
        			belongUserIds +=  str;
        			i=1;
        		}else{
        			belongUserIds += "," + str;
        		}
			}
        	conds.put("belongUserIds", belongUserIds);
        	
            conds.put("customer", customer);
            conds.put("requestName", requestName);
            conds.put("loanTypeId", loanType);
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 搜索条件，客户："+customer+",提交人："+requestName+",状态："+loanType);
            PageUtil<CrRequest> dataList =  crRequestService.getCrRequestByPage(conds, this.getPage());
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 总共搜索出"+dataList.getItems().size()+"条贷款");          
            request.setAttribute("dataList", dataList.getItems());
            request.setAttribute("recordCount", dataList.getPage().getTotalRowsAmount());
            request.setAttribute("sysUserId",this.getLoginInfo().getUserId());
          

            return SUCCESS;
        }catch (Exception e){
            logger.error("zhengXinLoanListQuery", e);
            return ERROR;
        }
    }
    //查看
 
	public String zhengXinShow(){
    	try{
    		request.setAttribute("userName",this.getLoginInfo().getUserName());
    		
    		String requestId =request.getParameter("requestId");
    		CrRequest cr = crRequestService.getCrRequestById(Integer.valueOf(requestId));        
    		request.setAttribute("data", cr); 
    		
    		String requestResult =cr.getRequestResult(); 
    		ArrayList<SysUploadFile>  formAttachmentList = new ArrayList<SysUploadFile>();
    		if(requestResult != null && !requestResult.equals("")){
    			String[] sq= requestResult.split(",");    			
    			for(int i=0;i<sq.length;i++){
    				formAttachmentList.add( sysUploadFileService .getSysUploadFileById(Integer.parseInt(sq[i].trim())));
    				}
    			}
    		request.setAttribute("formAttachmentList",formAttachmentList);
    		return SUCCESS;
    	}catch (Exception e){
    		return ERROR;
    	}
    }
	
    //保存
    public void addCrLendRemark(){
    	try{
    	   	CrRequest crRequest=new CrRequest();
    	    String strRequestId = request.getParameter("requestId");
    	    Integer requestId = Integer.parseInt(strRequestId.trim());
    	    String lendRemark = request.getParameter("lendRemark");
    	    Integer requestStatus=1;
    	    crRequest.setRequestId(requestId);
    	    crRequest.setRequestStatus(requestStatus);
    	    crRequest.setRemark(lendRemark);
    	    crRequestService.updateCrRequest(crRequest);
    	   }catch (Exception e){
    	 
    	   }
    	}
    //提交
  public void addTjLendRemark(){
	    PrintWriter out = null;
		try{
			out = this.getResponse().getWriter();
    	    String strRequestId = request.getParameter("requestId");
    	    Integer requestId = Integer.parseInt(strRequestId.trim());
    		CrRequest cr = crRequestService.getCrRequestById(requestId);        
    		request.setAttribute("data", cr); 
    		
    		String requestResult =cr.getRequestResult(); 
    		ArrayList<SysUploadFile>  formAttachmentList = new ArrayList<SysUploadFile>();
    		if(requestResult != null && !requestResult.equals("")){
    			String[] sq= requestResult.split(",");    			
    			for(int i=0;i<sq.length;i++){
    				formAttachmentList.add( sysUploadFileService .getSysUploadFileById(Integer.parseInt(sq[i].trim())));
    				}
    			}
    		if(formAttachmentList!=null&&formAttachmentList.size()>0){
    	   	CrRequest crRequest=new CrRequest();  
    	    String lendRemark = request.getParameter("lendRemark");
    	    Integer requestStatus=2;
    	    Integer conductor=this.getLoginInfo().getUserId();
    	    Date conductorDate = new Date();
    	    crRequest.setConductorDate(conductorDate);
    	    crRequest.setConductor(conductor);
    	    crRequest.setRequestId(requestId);
    	    crRequest.setRequestStatus(requestStatus);
    	    crRequest.setRemark(lendRemark);
    	    crRequestService.updateCrRequest(crRequest);
    	    out.write("success");
			if (out != null)
				out.close();
    	    }

    	   }catch (Exception e){
    	 
    	   }
    	}
  //驳回
  public void addBhLendRemark(){
	PrintWriter out = null;
	try {
		out = this.getResponse().getWriter();
  	    String strRequestId = request.getParameter("requestId");
  	    Integer requestId = Integer.parseInt(strRequestId.trim());
  		CrRequest cr = crRequestService.getCrRequestById(requestId);        
  		request.setAttribute("data", cr); 
  		
  		String requestResult =cr.getRequestResult(); 
  		ArrayList<SysUploadFile>  formAttachmentList = new ArrayList<SysUploadFile>();
  		if(requestResult != null && !requestResult.equals("")){
  			String[] sq= requestResult.split(",");    			
  			for(int i=0;i<sq.length;i++){
  				formAttachmentList.add( sysUploadFileService.getSysUploadFileById(Integer.parseInt(sq[i].trim())));
  				}
  			}
//  		if(formAttachmentList!=null&&formAttachmentList.size()>0){
  		 	CrRequest crRequest=new CrRequest();  
    	    String lendRemark = request.getParameter("lendRemark");
    	    Integer requestStatus=3;
    	    Integer conductor=this.getLoginInfo().getUserId();
    	    Date conductorDate = new Date();
    	    crRequest.setConductorDate(conductorDate);
    	    crRequest.setConductor(conductor);
  	        crRequest.setRequestId(requestId);
  	        crRequest.setRequestStatus(requestStatus);
  	        crRequest.setRemark(lendRemark);
  	        crRequestService.updateCrRequest(crRequest);
//  	   }
		out.write("success");
		if (out != null)
			out.close();
	}
		catch (Exception e){
  	 
  	   }
  	}
//查看图片
  public String vZhengXinShow(){
	  try{
		  String strRequestId = request.getParameter("requestId");
		  Integer requestId = Integer.parseInt(strRequestId.trim());	
		  String strZp = request.getParameter("zp");
		  Integer zp = Integer.parseInt(strZp.trim());	
		  Integer upFileId;
		  Integer downFileId;
		  Integer authFileId;
		  Integer fileId=null;
		  String copyFileName;
  		  CrRequest cr = crRequestService.getCrRequestById(requestId);    
    	  if(zp==1){
    		 upFileId=cr.getUpFileId();
    		 fileId=upFileId;     		
           }else  if(zp==2){
        	   downFileId=cr.getDownFileId();
        	   fileId=downFileId;
        	   }
         else{
        	 authFileId=cr.getAuthFileId();
       		  fileId=authFileId;
		 } 
     	//  }
	  String systemPath = this.getRootPath()+File.separator+"Records";   
	  if(fileId!=null){
		 SysUploadFile sysUploadFile=	sysUploadFileService .getSysUploadFileById(fileId);
		 if(sysUploadFile!=null ){
				  copyFileName = customerDataService.readFile(fileId+"", systemPath);
			}else{
				 copyFileName ="0";
			}
 
	  }else{
		 copyFileName="0";
	  }
      request.setAttribute("photoPath", copyFileName.substring(copyFileName.lastIndexOf(File.separator)+1,copyFileName.length()));
	  
	  return SUCCESS;  
	  }catch(Exception e){
		  e.printStackTrace();
		  return ERROR;  
	  }
  }

  //上传附件
  public String autoUpAttachment() {
		this.getResponse().setContentType("text/html;charset=utf-8");
		try {
			Integer userId = this.getLoginInfo().getUserId();
			String strRequestId = request.getParameter("requestId");
			Integer requestId = Integer.parseInt(strRequestId.trim());
			if (fileInputFileName != null && !fileInputFileName.equals("")) {

				CrRequest cr = crRequestService.getCrRequestById(requestId);
				CustomerData data = getCustomerData(cr.getLoanId());
				Form from = getForm();
				Map<String, Object> parameterMap = new HashMap<String, Object>();
				parameterMap.put("loanId", data.getLoanId());
				parameterMap.put("customerId", data.getCustomerId());
				parameterMap.put("eventId", data.getEventId());


				//文件名称 客户姓名_征信报告1
//				int fileCount = 0;
				String customerName = data.getCustomerName();

//				String requestResult = cr.getRequestResult();
//				ArrayList<SysUploadFile>  formAttachmentList = new ArrayList<SysUploadFile>();
//				if(requestResult != null && !requestResult.equals("")){
//					String[] sq= requestResult.split(",");
//					fileCount = sq.length;
//
//				}
				//文件名称 客户姓名_征信报告_20170630(203546).xxx
				if(StringUtils.isNotBlank(customerName)){
					fileInputFileName = customerName+"_征信报告_"+DateUtil.convertDateToString("yyyyMMdd(HHmmss)", Calendar.getInstance().getTime())+fileInputFileName.substring(fileInputFileName.indexOf("."));
				}
				Integer fileId = sysUploadFileService.saveFile(fileInput,fileInputFileName, userId, true);
				CrRequest crRequest = crRequestService.getCrRequestById(requestId);
				String oldRequestResult = crRequest.getRequestResult();
				String newRequestResult = "";
				if (oldRequestResult == null || oldRequestResult.equals("")) {
					newRequestResult = fileId.toString();
				} else {
					newRequestResult = fileId + "," + oldRequestResult;
				}
				
				crRequestService.updateRequestResult(newRequestResult, requestId);
				
				
				

	            
	            List<CustomerData> datas = customerDataService.getCustomerDataByParameterMap(parameterMap);
	            if (datas.size() > 0) {
	                // 主资料已经存在
	                CustomerData d = datas.get(0);

	                from.setCustomerDataId(d.getCustomerDataId());
	                from.setFileId(fileId);
	                dataFormService.addDatForm(from);
	            } else {
	                customerDataService.addNewCustomerData(data);
	                
	                // 保存照片数据
	                from.setCustomerDataId(data.getCustomerDataId());
	                from.setFileId(fileId);
	                 dataFormService.addDatForm(from);
	            }
				
			   }
          } catch (Exception e) {
			logger.error("CrRequestAction % autoUpAttachment", e);
		  }
		  return SUCCESS;

	      }
  

	/**
	 * 删除表单附件(目前只删除记录，不删除实际文件)
	 */
	public void delFormAttach() {
		PrintWriter out = null;
		try {
			out = this.getResponse().getWriter();
			String fileIdStr = request.getParameter("fileId");
			Integer fileId = Integer.parseInt(fileIdStr);
			sysUploadFileService.deleteSysUploadFile(fileId);
			String strRequestId = request.getParameter("requestId");
			Integer requestId = Integer.parseInt(strRequestId.trim());
			CrRequest crRequest = crRequestService.getCrRequestById(requestId);
			String oldRequestResult = crRequest.getRequestResult();
			String requestResult = "";
			Integer file;
			if (oldRequestResult!=null&&!oldRequestResult.equals("")) {
				if(oldRequestResult.equals(fileIdStr)){
					requestResult="";
				}else{
					String[] sq = oldRequestResult.split(",");
	
					for (int i = 0; i < sq.length; i++) {
						file = Integer.parseInt(sq[i].trim());
						if (!fileId.equals(file)) {
						requestResult += file + ",";
					}
					}
					if (!requestResult.equals("")) {
						requestResult=requestResult.substring(0, requestResult.length() - 1);
					}
				    }			
				crRequestService.updateRequestResult(requestResult, requestId);
			}
			out.write("success");
			if (out != null)
				out.close();
		
		} catch (Exception e) {
			logger.error("CrRequestAction % delFormAttach", e);
		}
	}
    
	private CustomerData getCustomerData(Integer loanId){
        CustomerData info = new CustomerData();
        info.setLoanId(Integer.valueOf(loanId));
        Map<String,Object> param = new HashMap<String, Object>();
        param.put("loanId", loanId);
        LnLoan lnLoan = lnLoanService.selectLoanById(loanId);
        LnLoanInfo lnLoanInfo = lnLoanInfoService.selectLoanInfoList(param).get(0);        
        info.setCustomerId(lnLoanInfo.getCustomerId());
        info.setCustomerName(lnLoanInfo.getCusName());

        info.setEventId(lnLoan.getEventId()); 
        
        Integer userId = this.getLoginInfo().getUserId();
        info.setUploadUserId(userId);
        info.setCreateUser(userId);
        info.setUpdateUser(userId);
        info.setCreateDate(Calendar.getInstance().getTime());
        info.setUpdateDate(Calendar.getInstance().getTime());
        
        return info;
	}
	
	
	private Form getForm(){
		Form info = new Form();
		Integer userId = this.getLoginInfo().getUserId();
        info.setCreateUser(userId);
        info.setUpdateUser(userId);
        info.setCreateDate(Calendar.getInstance().getTime());
        info.setUpdateDate(Calendar.getInstance().getTime());
		return info;
	}
	
    /** getter setter **/


	public Integer getRequestId() {
		return requestId;
	}

	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}

	public void setCrRequestService(CrRequestService crRequestService) {
		this.crRequestService = crRequestService;
	}

	public Integer getLoanType() {
        return loanType;
    }

    public void setLoanType(Integer loanType) {
        this.loanType = loanType;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

 

	
	public String getRequestName() {
		return requestName;
	}

	public void setRequestName(String requestName) {
		this.requestName = requestName;
	}

	public DeptFacadeService getDeptFacadeService() {
        return deptFacadeService;
    }

    public void setDeptFacadeService(DeptFacadeService deptFacadeService) {
        this.deptFacadeService = deptFacadeService;
    }

	public File getFileInput() {
		return fileInput;
	}

	public void setFileInput(File fileInput) {
		this.fileInput = fileInput;
	}

	public String getFileInputFileName() {
		return fileInputFileName;
	}

	public void setFileInputFileName(String fileInputFileName) {
		this.fileInputFileName = fileInputFileName;
	}
	

	public CaseHelperServiceImpl getCaseHelperService() {
		return caseHelperService;
	}

	public void setCaseHelperService(CaseHelperServiceImpl caseHelperService) {
		this.caseHelperService = caseHelperService;
	}

	public CrRequestService getCrRequestService() {
		return crRequestService;
	}
	

	public SysUploadFileService getSysUploadFileService() {
		return sysUploadFileService;
	}

	public void setSysUploadFileService(SysUploadFileService sysUploadFileService) {
		this.sysUploadFileService = sysUploadFileService;
	}

	public CustomerDataService getCustomerDataService() {
		return customerDataService;
	}

	public void setCustomerDataService(CustomerDataService customerDataService) {
		this.customerDataService = customerDataService;
	}

	public LnLoanInfoDictionaryService getLnLoanInfoDictionaryService() {
		return lnLoanInfoDictionaryService;
	}

	public void setLnLoanInfoDictionaryService(
			LnLoanInfoDictionaryService lnLoanInfoDictionaryService) {
		this.lnLoanInfoDictionaryService = lnLoanInfoDictionaryService;
	}

	

	

	

	
    
    
}
