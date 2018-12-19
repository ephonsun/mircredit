package com.banger.mobile.webapp.action.loan;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.base.loan.BaseLnLoanMonitor;
import com.banger.mobile.domain.model.data.Form;
import com.banger.mobile.domain.model.data.LoanData;
import com.banger.mobile.domain.model.data.Photo;
import com.banger.mobile.domain.model.dept.CusBelongToBean;
import com.banger.mobile.domain.model.dept.SysDept;
import com.banger.mobile.domain.model.loan.*;
import com.banger.mobile.domain.model.uploadFile.SysUploadFile;
import com.banger.mobile.facade.data.CustomerDataService;
import com.banger.mobile.facade.data.DataFormService;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.loan.*;
import com.banger.mobile.facade.system.team.SysTeamUserService;
import com.banger.mobile.facade.uploadFile.SysUploadFileService;
import com.banger.mobile.util.VmHelper;
import com.banger.mobile.webapp.action.BaseAction;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.tools.ant.util.DateUtils;

import com.banger.mobile.util.DateUtil;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: ouyl
 * Date: 13-3-4
 * Time: 下午4:02
 * To change this template use File | Settings | File Templates.
 *
 * 异常催收贷款Action
 */
public class ExceptionDunLoanAction extends BaseAction {
    private static Logger logger = Logger.getLogger(ExceptionDunLoanAction.class);
    private SysTeamUserService sysTeamUserService;
    private LnLoanService lnLoanService;
    private LnLoanTypeService lnLoanTypeService;                                  //贷款类型service
    private LnRepaymentPlanService lnRepaymentPlanService;                        //贷款催收计划service
    private LnExceptionRepaymentPlanService lnExceptionRepaymentPlanService;       //异常催收计划service
    private DeptFacadeService deptFacadeService; //部门组织结构service
    private LnExceptionDunAssignService lnExceptionDunAssignService;  //分配异常催收贷款service
    private LnDunSetingService lnDunSetingService;
    private SysUploadFileService sysUploadFileService;
    private String customer;
    private Integer loanType;
    private Date repaymentStartDate;
    private Date repaymentEndDate;
    private Integer status;
    private Date planDate;
    private BigDecimal owedPrincipal;
    private BigDecimal owedInterest;
    private BigDecimal paidPrincipal;
    private BigDecimal paidInterest;
    private Date repaymentDate;

    private LnLoanDetailService lnLoanDetailService;
    private LnOpHistoryService lnOpHistoryService;
    private LnLoanMonitorService lnLoanMonitorService;
    private DataFormService dataFormService; //表单service
    private LnDunLogService          lnDunLogService;
    private LnExceptionDunLogService lnExceptionDunLogService;
    private Integer loanId;
    private Date monDate;
    private List<LnLoanMonitor> lnLoanMonitorList;//贷后监控
    private LnLoanInfoService lnLoanInfoService;
    private LnLoanInfoDictionaryService lnLoanInfoDictionaryService;
    private LnCreditHistoryService lnCreditHistoryService;
    private LnLoanInfo lnLoanInfo;
    private List<LnLoanCoBorrowerBean> loanCoBorrowerList;       //共同借贷人列表
    private List<LnLoanGuarantorBean> loanGuarantorList;        //担保人列表
    private List<LnCreditHistory> lnCreditHistoryList;
    private CustomerDataService customerDataService; //贷款资料service
    private BaseLnLoanMonitor lnLoanMonitor;
    private LnLoanPledgeService lnLoanPledgeService;

    public String exceptionDunLoanList(){


        	//TODO:修改
   
    	
    	
        try {
            logger.info("web端异常催收贷款exceptionDunLoanList开始，当前登录用户“"+this.getLoginInfo().getAccount()+"”");
            //贷款类型
            List<LnLoanType> typeList = lnLoanTypeService.getLoanTypeList();
            Map<String, Object> conds = new HashMap<String, Object>();
            //是否是业务主管
            // 是否是业务主管
            //  Boolean isInChargeOf = deptFacadeService.isInChargeOfDepartment();
              String belongUserIds = "";
              String[] roles = this.getLoginInfo().getRoles();
              boolean isInChargeOf = false;
              for (String roleId : roles) {
  				if(roleId.equals("5") || roleId.equals("6")){
  					isInChargeOf = true;
  					break;
  				}
  			}
              if (isInChargeOf) {
                  // 当前用户的团队底下的成员
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
              	conds.put("belongUserIds", belongUserIds); // 当前用户所管理的提交申请用户
              } else {
              	
              	String role = roles[0];
              	if(role.equals("7")){  // 客户经理
              		conds.put("surveyUserId", this.getLoginInfo().getUserId());
              	}else if(role.equals("4")){
              		conds.put("approveBackerUserId", this.getLoginInfo().getUserId());
              	}
              }
            
            conds.put("loanStatusId",6);   // 6表示还款中
            conds.put("customer", customer);
            conds.put("loanTypeId", loanType);
            //默认
            /*if(repaymentStartDate==null&&repaymentEndDate==null){
            	repaymentEndDate =org.apache.commons.lang.time.DateUtils.addSeconds(org.apache.poi.ss.usermodel.DateUtil.parseYYYYMMDDDate(org.apache.commons.httpclient.util.DateUtil.formatDate(new Date(),"yyyy-MM-dd")),-1);
            }*/
            conds.put("repaymentStartDate", repaymentStartDate);
            //repaymentEndDate = lnLoanService.addSecondsForDate(repaymentEndDate,59);
            conds.put("repaymentEndDate", repaymentEndDate);
            //conds.put("loanIsCheckout", loanIsCheckout);
            conds.put("isConfirm","isConfirm");
            conds.put("isNogood",0) ;
            conds.put("loanStatusId",6);
            conds.put("isDun","isDun");
            //未还款
            conds.put("isPaidTag","0");
            PageUtil<LnLoan> dataList = lnLoanService.getMakeExLoanPage(conds, this.getPage());
            HashMap<String,Object> checkBoxMessageMap = buildLoanCheckBoxMessage("DKLX","DBFS","DY","ZY","FLXS","HYZK","JYCD","JYCS","JZCSLX","JZZK","XXLY","YSRSP","ZJLX","ZXQK","ZY","ZZXS","JKLX","HFLX","CSFS");
	    	request.setAttribute("checkBoxMessage", checkBoxMessageMap);
            /*if(dataList != null && dataList.getItems() != null){
                for (LnLoan lnLoan : dataList.getItems()) {
                    Date repaymentDate = lnLoan.getRepaymentDate();
                    Date nowDate = new Date();
                    if(repaymentDate !=null){
                        if((nowDate.getTime() - repaymentDate.getTime()) >= 0){
                            //超过还款时间的,前端在高亮显示
                            lnLoan.setIsWillPast(1);
                        }
                    }
                    if(lnLoan.getExceptionAssignUserNames() != null && !lnLoan.getExceptionAssignUserNames().equals("")){
                        String[] assignUserNames =lnLoan.getExceptionAssignUserNames().split(",");
                        for(String s:assignUserNames){
                            if(s.equals(this.getLoginInfo().getUserName())){
                                lnLoan.setIsCanEdit(1);
                            }
                        }
                    }else{
                        if(this.getLoginInfo().getUserId().equals(lnLoan.getSurveyUserId())){
                            lnLoan.setIsCanEdit(1);
                        }
                    }
                }
            }*/
            request.setAttribute("dataList",dataList.getItems());
            request.setAttribute("recordCount", dataList.getPage().getTotalRowsAmount());

            request.setAttribute("typeList",typeList);
            logger.info("web端异常催收贷款exceptionDunLoanList完成，当前登录用户“"+this.getLoginInfo().getAccount()+"”");
            return SUCCESS;
        }catch (Exception e){
            logger.error("ExceptionDunLoanList",e);
            return ERROR;
        }
    }

    public String exceptionDunLoanListQuery(){
    	try {
            logger.info("web端异常催收贷款exceptionDunLoanList开始，当前登录用户“"+this.getLoginInfo().getAccount()+"”");
            //贷款类型
            List<LnLoanType> typeList = lnLoanTypeService.getLoanTypeList();
            Map<String, Object> conds = new HashMap<String, Object>();
            // 是否是业务主管
            //  Boolean isInChargeOf = deptFacadeService.isInChargeOfDepartment();
              String belongUserIds = "";
              String[] roles = this.getLoginInfo().getRoles();
              boolean isInChargeOf = false;
              for (String roleId : roles) {
  				if(roleId.equals("5") || roleId.equals("6")){
  					isInChargeOf = true;
  					break;
  				}
  			}
              if (isInChargeOf) {
                  // 当前用户的团队底下的成员
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
              	conds.put("belongUserIds", belongUserIds); // 当前用户所管理的提交申请用户
              } else {
              	
              	String role = roles[0];
              	if(role.equals("7")){  // 客户经理
              		conds.put("surveyUserId", this.getLoginInfo().getUserId());
              	}else if(role.equals("4")){
              		conds.put("approveBackerUserId", this.getLoginInfo().getUserId());
              	}
              }
            
            conds.put("loanStatusId",6);   // 6表示还款中
            conds.put("customer", customer);
            conds.put("loanTypeId", loanType);
          //默认
            /*if(repaymentStartDate==null&&repaymentEndDate==null){
            	repaymentEndDate =org.apache.commons.lang.time.DateUtils.addSeconds(org.apache.poi.ss.usermodel.DateUtil.parseYYYYMMDDDate(org.apache.commons.httpclient.util.DateUtil.formatDate(new Date(),"yyyy-MM-dd")),-1);
            }*/
            conds.put("repaymentStartDate", repaymentStartDate);
          //  repaymentEndDate = lnLoanService.addSecondsForDate(repaymentEndDate,59);
            conds.put("repaymentEndDate", repaymentEndDate);
            //conds.put("loanIsCheckout", loanIsCheckout);
            conds.put("isConfirm","isConfirm");
            conds.put("isNogood",0) ;
            conds.put("loanStatusId",6);
            conds.put("isDun","isDun");
            //未还款
            conds.put("isPaidTag","0");
            PageUtil<LnLoan> dataList = lnLoanService.getMakeExLoanPage(conds, this.getPage());
            HashMap<String,Object> checkBoxMessageMap = buildLoanCheckBoxMessage("DKLX","DBFS","DY","ZY","FLXS","HYZK","JYCD","JYCS","JZCSLX","JZZK","XXLY","YSRSP","ZJLX","ZXQK","ZY","ZZXS","JKLX","HFLX","CSFS");
	    	request.setAttribute("checkBoxMessage", checkBoxMessageMap);
            /*if(dataList != null && dataList.getItems() != null){
                for (LnLoan lnLoan : dataList.getItems()) {
                    Date repaymentDate = lnLoan.getRepaymentDate();
                    Date nowDate = new Date();
                    if(repaymentDate !=null){
                        if((nowDate.getTime() - repaymentDate.getTime()) >= 0){
                            //超过还款时间的,前端在高亮显示
                            lnLoan.setIsWillPast(1);
                        }
                    }
                    if(lnLoan.getExceptionAssignUserNames() != null && !lnLoan.getExceptionAssignUserNames().equals("")){
                        String[] assignUserNames =lnLoan.getExceptionAssignUserNames().split(",");
                        for(String s:assignUserNames){
                            if(s.equals(this.getLoginInfo().getUserName())){
                                lnLoan.setIsCanEdit(1);
                            }
                        }
                    }else{
                        if(this.getLoginInfo().getUserId().equals(lnLoan.getSurveyUserId())){
                            lnLoan.setIsCanEdit(1);
                        }
                    }
                }
            }*/
            request.setAttribute("dataList",dataList.getItems());
            request.setAttribute("recordCount", dataList.getPage().getTotalRowsAmount());

            request.setAttribute("typeList",typeList);
            logger.info("web端异常催收贷款exceptionDunLoanList完成，当前登录用户“"+this.getLoginInfo().getAccount()+"”");
            return SUCCESS;
        }catch (Exception e){
            logger.error("ExceptionDunLoanList",e);
            return ERROR;
        }
    }


    /**
     * 编辑异常催收贷款页面
     *
     * @return
     */
    public String exceptionDunLoanEdit(){
    	//TODO:修改
   	 try{
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 进入催收贷款菜单列表...");
            //贷款类型
            String strLoanId = request.getParameter("loanId");
            String isConfirm =request.getParameter("isConfirm");
            Integer loanId= Integer.parseInt(strLoanId.trim());
            LnLoan lnLoan = lnLoanService.getLnLoanById(loanId);
         /*   Integer isNogood =lnLoan.getIsNogood();
            String nogoodRemark =lnLoan.getNogoodRemark();*/
           // List<LnRepaymentPlan> queryList= lnRepaymentPlanService.queryLnRepaymentPlan(loanId);
            List<LnRepaymentPlan> queryList= lnRepaymentPlanService.queryLnRepaymentPlan(loanId);
            LnRepaymentPlan queryPlan= lnRepaymentPlanService.selectCurLnRepaymentPlanById(loanId);
            //查询贷款余额
            request.setAttribute("queryPlan", queryPlan);
            //查询贷款余额todo
           // LnExceptionRepaymentPlan lerp = lnRepaymentPlanService.getLastExceptionRepaymentPlan(loanId);
        	/*request.setAttribute("loanRemaining", lerp.getRemaining());
        	request.setAttribute("exceptionRepaymentPlanList", lerp);*/
            request.setAttribute("exceptionRepaymentPlanList", queryPlan);
        	Map<String,Object> paramMap = new HashMap<String, Object>();
            paramMap.put("eventId", LoanConstants.LOAN_DUN_EVENT); //贷后流程
            paramMap.put("loanId",lnLoan.getLoanId());
            //贷款表单附件信息
            List<Form> formAttachmentList = dataFormService.selectFormAttachment(paramMap);
            request.setCharacterEncoding("UTF-8");
            request.setAttribute("lnERepaymentPlanList", queryList);
            request.setAttribute("loanId", loanId);
           /* request.setAttribute("isNogood",isNogood);
            request.setAttribute("nogoodRemark",nogoodRemark);*/
            request.setAttribute("loan",lnLoan);
            request.setAttribute("formAttachmentList",formAttachmentList);
            request.setAttribute("isConfirm",isConfirm);
            List<LnExceptionDunLog> lnDunLogList = null;
            // 异常催收
            lnDunLogList = lnExceptionDunLogService.getExpDunLogByLoanId(loanId);  //已优化
            /* for (LnDunLog lnDunLog : lnDunLogList) {
                //web端电话催收特殊处理
                if (lnDunLog.getUpdateUser() != null) {
                    RecordDetail d = recordInfoService.getRecordInfoById(lnDunLog.getUpdateUser());
                    if (d != null) {
                        lnDunLog.setRemark(d.getRemark());
                    }
                }

                if (lnDunLog.getCustomerDataId() != null && !lnDunLog.getCustomerDataId().equals(0)){
                    customerDataIdList.add(lnDunLog.getCustomerDataId());
                }
            }*/
            request.setAttribute("lnDunLogList",lnDunLogList);
            
            //所有资料
	        Map<String,Object> param = new HashMap<String, Object>();
	        param.put("loanId", loanId);
	        lnLoanInfo = lnLoanInfoService.selectLoanInfoList(param).get(0);
	        //查询贷后监控记录
	        lnLoanMonitorList= lnLoanMonitorService.getLoanMonitorList(loanId);
	        for(LnLoanMonitor lm:lnLoanMonitorList){
	        	if(StringUtils.isNotEmpty(lm.getFileIds())){
	        		List<SysUploadFile>  fileIdList = new ArrayList<SysUploadFile> ();
	        		SysUploadFile flie = new SysUploadFile();
	        		if(lm.getFileIds().contains(",")){
	        			for(String id:lm.getFileIds().split(",")){
	        				flie= sysUploadFileService.getSysUploadFileById(Integer.parseInt(id));
	        				fileIdList.add(flie);
	        			}
	        		}else{
	        			flie= sysUploadFileService.getSysUploadFileById(Integer.parseInt(lm.getFileIds()));
        				fileIdList.add(flie);
	        		}
	        		lm.setFileIdList(fileIdList);
	        	}
	        }
	        request.setAttribute("lnLoanMonitorList", lnLoanMonitorList);
	        //查询历史操作记录
	        List<LnOpHistory> hisList = lnOpHistoryService.getAllOpHistoryListByLoanId(loanId);
	        request.setAttribute("hisList", hisList);
	        //抵押之物
	        List<LnPledge> lnPledgeList = lnLoanPledgeService.getLnLoanPledgeByLoanId(loanId);
			request.setAttribute("lnPledgeList", lnPledgeList);

	        //所有资料
	        Map<String,Object> photoMap = new HashMap<String, Object>();
	        photoMap.put("loanId",loanId);
	        photoMap.put("eventId",LoanConstants.LOAN_DUN_EVENT);
	        photoMap.put("statistics",1);
	        List<LoanData> dataList = customerDataService.getAllLoanDataById(photoMap);
	        List<Photo> photoList = new ArrayList<Photo>();
	        //申请人照片资料
	        for (LoanData d : dataList) {
	            if (d.getDataType().equals(1)){
	                if (d.getCustomerId().equals(lnLoan.getCustomerId())){
	                    String photoUrl = d.getFilePath() + "/" + d.getFileName();
	                    Photo photo = new Photo();
	                    photo.setFileId(d.getFileId());
	                    photo.setPhotoId(d.getDataId());
	                    photo.setFilePath(photoUrl);
	
	                    photoList.add(photo);
	                }
	            }
	        }
	        request.setAttribute("photoList",photoList);
	        
	        HashMap<String,Object> checkBoxMessageMap = buildLoanCheckBoxMessage("DKLX","DBFS","DY","ZY","FLXS","HYZK","JYCD","JYCS","JZCSLX","JZZK","XXLY","YSRSP","ZJLX","ZXQK","ZY","ZZXS","JKLX","HFLX","CSFS");
	    	request.setAttribute("checkBoxMessage", checkBoxMessageMap);
	        // 共同借贷人
	        loanCoBorrowerList = lnLoanDetailService.getLoanCoList(loanId, dataList);
	        // 担保人
	        loanGuarantorList = lnLoanDetailService.getLoanGuList(loanId, dataList);
	        //信贷历史
	        lnCreditHistoryList = lnCreditHistoryService.getCreditHistoryByLoanId(loanId);
	        String[] sourcesIds = lnLoanInfo.getRegisterInfoSourceIds().split(",");
	 	       HashMap<String,Integer> sourcesIdMap = new HashMap<String,Integer>();
	 	        for (String string : sourcesIds) {
					sourcesIdMap.put(string, 1);
				}
		        request.setAttribute("soucesIdMap", sourcesIdMap);
		        request.setAttribute("isEdit","yes");
            return SUCCESS;
        }catch (Exception e){
            logger.error("dunLoanList",e);
            return ERROR;
        }
    }
    /**
     * 获取贷款所需要的下拉框信息
     * 
     * @param param
     * @return
     */
    private HashMap<String,Object> buildLoanCheckBoxMessage(String...param){
    	
    	HashMap<String,Object> paramMap = new HashMap<String,Object>();
    	HashMap<String,Object> resultMap = new HashMap<String,Object>();
    	for (int i = 0; i < param.length; i++) {
    		paramMap.put("dictionaryName", param[i]);
    		List<LnLoanInfoDictionary> lnLoanInfoDictionaryList = lnLoanInfoDictionaryService.selectLoanInfoDictionaryList(paramMap);
    		resultMap.put(param[i], lnLoanInfoDictionaryList);
    		paramMap.remove("dictionaryKey");
		}
    	return resultMap;
    }
    public void checkPlanDate(){
        try{
            String loanId = request.getParameter("loanId");
            logger.info("web端异常催收贷款checkPlanDate开始，当前登录用户“"+this.getLoginInfo().getAccount()+"”,loanId:"+loanId);
            // LnLoan
            // loan=lnLoanService.getLnLoanById(Integer.parseInt(loanId));
            // List<LnExceptionRepaymentPlan>
            // plan=lnExceptionRepaymentPlanService.queryLnExceptionRepaymentPlan(Integer.parseInt(loanId));
            // Date currentPlanDate = loan.getRepaymentDate();
            // Integer sortno =loan.getSortno();
            LnExceptionRepaymentPlan plan = lnExceptionRepaymentPlanService
                .getLastExceptionRepaymentPlan(Integer.parseInt(loanId));
            HttpServletResponse response = this.getResponse();
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            Calendar yesterday = Calendar.getInstance();
            yesterday.add(Calendar.DAY_OF_MONTH,-1);
            if (planDate.getTime() < yesterday.getTimeInMillis()) {
                out.print("还款日期不能早于当前日期，请重新填写！");
            } else {
                if (plan != null){
                    Date lastRepaymentDate = plan.getPlanDate();
                    if (planDate.getTime() <= lastRepaymentDate.getTime()) {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
                        String parseDateStr = format.format(lastRepaymentDate);
                        out.print("还款日期不能早于"+parseDateStr+"！");
                    } else {
                        out.print("SUCCESS");
                    }
                }else {
                    out.print("SUCCESS");
                }
            }

            if (out != null) {
                out.flush();
                out.close();
            }
            logger.info("web端异常催收贷款checkPlanDate完成，当前登录用户“"+this.getLoginInfo().getAccount()+"”,loanId:"+loanId);
        }catch (Exception e){
            logger.error("checkPlanDate",e);
        }
    }

    /**
     * 添加异常还款计划
     *
     */
    public String insertExceptionRepaymentPlan(){
        try {
            LnExceptionRepaymentPlan lnExceptionRepaymentPlan= new LnExceptionRepaymentPlan();
            String loanIdstr = request.getParameter("loanId");
            logger.info("web端异常催收贷款insertExceptionRepaymentPlan开始，当前登录用户“"+this.getLoginInfo().getAccount()+"”,loanId:"+loanIdstr);
            Integer loanId=Integer.parseInt(loanIdstr);
            LnLoan loan = lnLoanService.getLnLoanById(loanId);
            Integer sortNo = lnExceptionRepaymentPlanService.getLastExceptionSortNoByLoanId(loanId);
            List<LnExceptionRepaymentPlan> plans=lnExceptionRepaymentPlanService.queryLnExceptionRepaymentPlan(loanId);

            lnExceptionRepaymentPlan.setLoanId(loanId);
            lnExceptionRepaymentPlan.setPlanDate(planDate);
            lnExceptionRepaymentPlan.setOwedPrincipal(owedPrincipal);
            lnExceptionRepaymentPlan.setPaidPrincipal(paidPrincipal);

            if(sortNo ==null){
                sortNo =1;
            }else{
                sortNo = sortNo+1;
            }
            lnExceptionRepaymentPlan.setSortno(sortNo);

            lnExceptionRepaymentPlanService.insertExceptionRepaymentPlan(lnExceptionRepaymentPlan);

            List<LnExceptionRepaymentPlan> eQueryList =lnExceptionRepaymentPlanService.queryLnExceptionRepaymentPlan(loanId);
            request.setAttribute("eQueryList",eQueryList);
            request.setAttribute("loan",loan);
            logger.info("web端异常催收贷款insertExceptionRepaymentPlan完成，当前登录用户“"+this.getLoginInfo().getAccount()+"”,loanId:"+loanIdstr);
            return SUCCESS;
        }catch (Exception e){
            logger.error("insertExceptionRepaymentPlan",e);
            return ERROR;
        }
    }


    //添加还款计划
    public  String  addExceptionRepaymentPlan(){
        try {
            this.getResponse().setContentType("text/html;charset=utf-8");
            PrintWriter out = this.getResponse().getWriter();
            LnExceptionRepaymentPlan lnExceptionRepaymentPlan= new LnExceptionRepaymentPlan();
            String loanIdstr = request.getParameter("loanId");
            Integer loanId=Integer.parseInt(loanIdstr);
            LnLoan loan = lnLoanService.getLnLoanById(loanId);
            Integer sortNo = lnExceptionRepaymentPlanService.getLastExceptionSortNoByLoanId(loanId);
            lnExceptionRepaymentPlan.setLoanId(loanId);
            lnExceptionRepaymentPlan.setPlanDate(planDate);
            lnExceptionRepaymentPlan.setOwedPrincipal(owedPrincipal);
            lnExceptionRepaymentPlan.setPaidPrincipal(paidPrincipal);
            if(sortNo ==null){
                sortNo =1;
            }else{
                sortNo = sortNo+1;
            }
            lnExceptionRepaymentPlan.setSortno(sortNo);
            lnExceptionRepaymentPlanService.insertExceptionRepaymentPlan(lnExceptionRepaymentPlan);
            List<LnExceptionRepaymentPlan> eQueryList =lnExceptionRepaymentPlanService.queryLnExceptionRepaymentPlan(loanId);
            request.setAttribute("eQueryList",eQueryList);
            request.setAttribute("loan",loan);
            return SUCCESS;
        }catch (Exception e){
            logger.error("insertExceptionRepaymentPlan",e);
            return ERROR;
        }
    }
    /**
     * 点击已还款
     */
    public void alreadyRepayCheck(){
        try{
            String loanIdStr = request.getParameter("loanId");
            logger.info("web端异常催收贷款alreadyRepayCheck开始，当前登录用户“"+this.getLoginInfo().getAccount()+"”,loanId:"+loanIdStr);
            String clicked = request.getParameter("clicked");
            String exceptionRepaymentPlanIdStr = request.getParameter("exceptionRepaymentPlanId");
            Integer exceptionRepaymentPlanId =Integer.parseInt(exceptionRepaymentPlanIdStr);
            Integer loanId = Integer.parseInt(loanIdStr);
            LnLoan lnLoan=lnLoanService.getLnLoanById(loanId);
            LnExceptionRepaymentPlan lnExceptionRepaymentPlan = lnExceptionRepaymentPlanService.queryLnExceptionRepaymentPlanById(exceptionRepaymentPlanId);

            HttpServletResponse response = this.getResponse();
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();

            if (clicked != null && clicked.trim().equals("true")){
                //点击已还款
                out.print("客户“" + lnLoan.getCrmCustomer().getCustomerName() +
                        "”本期应还款" + VmHelper.getUnityDecimalMoney(lnExceptionRepaymentPlan.getOwedPrincipal()) +
                        "元，实际已还" + VmHelper.getUnityDecimalMoney(lnExceptionRepaymentPlan.getPaidPrincipal()) +
                        "元，确定修改为已还款吗？");
            }else {
                //已还款金额改变
                out.print("客户“"+lnLoan.getCrmCustomer().getCustomerName()+
                        "”本期应还" + VmHelper.getUnityDecimalMoney(lnExceptionRepaymentPlan.getOwedPrincipal())+
                        "元，实际已还款" + VmHelper.getUnityDecimalMoney(lnExceptionRepaymentPlan.getPaidPrincipal()) +
                        "元，是否设置本期还款状态为已还款？");
            }
            if(out != null){
                out.flush();
                out.close();
            }
            logger.info("web端异常催收贷款alreadyRepayCheck完成，当前登录用户“"+this.getLoginInfo().getAccount()+"”,loanId:"+loanIdStr);
        }catch (Exception e){
            log.error("overRepayCheck",e);
        }
    }

    /**
     * 更新异常还款计划状态
     *
     */
    public String updateExceptionRepaymentStatus(){
    	//TODO:修改
    	
    	return SUCCESS;
    	
    	/*
        try{
            Map<String, Object> paramMap = new HashMap<String, Object>();
            Map<String, Object> eParamMap = new HashMap<String, Object>();
            String loanIdStr = request.getParameter("loanId");
            Integer loanId= Integer.parseInt(loanIdStr.trim());
            logger.info("web端异常催收贷款updateExceptionRepaymentStatus开始，当前登录用户“"+this.getLoginInfo().getAccount()+"”,loanId:"+loanIdStr);
            String exceptionRepaymentPlanIdStr= request.getParameter("exceptionRepaymentPlanId");
            Integer exceptionRepaymentPlanId =  Integer.parseInt(exceptionRepaymentPlanIdStr.trim());
            Integer loanSortno = lnLoanService.getLnLoanById(loanId).getSortno();
            Integer eLoanSortno =lnExceptionRepaymentPlanService.queryLnExceptionRepaymentPlanById(exceptionRepaymentPlanId).getSortno();
            //更新lnLoan status
            paramMap.put("loanId",loanId);
            paramMap.put("repaymentStatus",2);

            // 更新 异常还款计划 status
            eParamMap.put("status",2);
            eParamMap.put("exceptionRepaymentPlanId",exceptionRepaymentPlanId);

            //插入历史记录
            LnLoan lnLoan = lnLoanService.getLnLoanById(loanId);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            LnOpHistory lnOpHistory = new LnOpHistory();
            lnOpHistory.setContent("修改贷款"+sdf.format(lnLoan.getRepaymentDate())+
                    "还款状态为：已还款,本期应还"+ VmHelper.getDecimalMoney(lnLoan.getOwedPrincipal())+
                    "元，已还"+VmHelper.getDecimalMoney(lnLoan.getPaidPrincipal())+"元");
            lnOpHistory.setBeforeStatusId(6);
            lnOpHistory.setAfterStatusId(6);
            lnOpHistory.setOpHistoryDate(new Date());
            lnOpHistory.setUserId(this.getLoginInfo().getUserId());
            lnOpHistory.setLoanId(loanId);
            if(loanSortno.equals(eLoanSortno)){
                lnLoanService.editRepaymentStatus(paramMap,eParamMap,lnOpHistory);
            }
            List<LnExceptionRepaymentPlan> eQueryList =lnExceptionRepaymentPlanService.queryLnExceptionRepaymentPlan(loanId);
            BigDecimal loanRemaining =lnLoanService.getLnLoanById(loanId).getLoanRemaining();
            request.setAttribute("loanRemaining",loanRemaining);
            request.setAttribute("eQueryList",eQueryList);
            logger.info("web端异常催收贷款updateExceptionRepaymentStatus完成，当前登录用户“"+this.getLoginInfo().getAccount()+"”,loanId:"+loanIdStr);
            return SUCCESS;
        }catch (Exception e){
            log.error("updateExceptionRepaymentStatus",e);
            return ERROR;
        }
    */}

    /**
     * 删除异常还款计划
     */
    public String deleteExceptionRepaymentPlan(){
        try{
            String loanIdStr = request.getParameter("loanId");
            logger.info("web端异常催收贷款deleteExceptionRepaymentPlan开始，当前登录用户“"+this.getLoginInfo().getAccount()+"”,loanId:"+loanIdStr);
            Integer loanId = Integer.parseInt(loanIdStr.trim());
            LnLoan loan = lnLoanService.getLnLoanById(loanId);
            String exceptionRepaymentPlanIdStr = request.getParameter("exceptionRepaymentPlanId");
            Integer exceptionRepaymentPlanId= Integer.parseInt(exceptionRepaymentPlanIdStr.trim());
//            lnExceptionRepaymentPlanService.deleteExceptionRepaymentPlan(exceptionRepaymentPlanId);
            lnExceptionRepaymentPlanService.delExceptionRepaymentPlan(loan,exceptionRepaymentPlanId);

            List<LnExceptionRepaymentPlan> eQueryList =lnExceptionRepaymentPlanService.queryLnExceptionRepaymentPlan(loanId);
            request.setAttribute("loan",loan);
            request.setAttribute("eQueryList",eQueryList);
            logger.info("web端异常催收贷款deleteExceptionRepaymentPlan完成，当前登录用户“"+this.getLoginInfo().getAccount()+"”,loanId:"+loanIdStr);
            return SUCCESS;
        }catch (Exception e){
            logger.error("",e);
            return ERROR;
        }
    }


    /**
     * 修改本期异常还款计划页面
     *
     * @return
     */
    public String editExceptionPlanPage(){
    	//TODO:修改
    	return SUCCESS;
    	/*
        try{
            String loanIdStr = request.getParameter("loanId");
            logger.info("web端异常催收贷款editExceptionPlanPage开始，当前登录用户“"+this.getLoginInfo().getAccount()+"”,loanId:"+loanIdStr);
            Integer loanId= Integer.parseInt(loanIdStr.trim());
            LnLoan lnLoanl = lnLoanService.getLnLoanById(loanId);
            BigDecimal owedPrincipal =lnLoanl.getOwedPrincipal();
            BigDecimal paidPrincipal =lnLoanl.getPaidPrincipal();
            Date repaymentDate =lnLoanl.getRepaymentDate();
            String customerName = lnLoanl.getCrmCustomer().getCustomerName();
            String customerTitle = lnLoanl.getCrmCustomer().getCustomerTitle();
            request.setAttribute("owedPrincipal",owedPrincipal);
            request.setAttribute("paidPrincipal",paidPrincipal);
            request.setAttribute("repaymentDate",repaymentDate);
            request.setAttribute("customerName",customerName);
            request.setAttribute("customerTitle",customerTitle);
            request.setAttribute("loanId",loanId);
            logger.info("web端异常催收贷款editExceptionPlanPage完成，当前登录用户“"+this.getLoginInfo().getAccount()+"”,loanId:"+loanIdStr);
            return SUCCESS;
        }catch (Exception e){
            logger.error("editExceptionPlanPage",e);
            return ERROR;
        }
    */}

    /**
     * 更改本期异常还款计划
     *
     */
    public void editExceptionSort(){
    	//TODO:修改
    	/*
        //更新贷款
        Map<String ,Object> paramMap = new HashMap<String , Object>();
        String loanIdStr = request.getParameter("loanId");
        logger.info("web端异常催收贷款editExceptionSort开始，当前登录用户“"+this.getLoginInfo().getAccount()+"”,loanId:"+loanIdStr);
        Integer loanId =  Integer.parseInt(loanIdStr.trim());
        paramMap.put("owedPrincipal",owedPrincipal);
        paramMap.put("paidPrincipal",paidPrincipal);
        paramMap.put("loanId",loanId);
        //更新异常还款计划
        Integer sortno = lnLoanService.getLnLoanById(loanId).getSortno();
        Map<String ,Object> eParamMap = new HashMap<String , Object>();
        eParamMap.put("paidPrincipal",paidPrincipal);
        eParamMap.put("owedPrincipal",owedPrincipal);
        eParamMap.put("sortno",sortno);

        lnLoanService.editPaidMoney(paramMap,eParamMap);
        logger.info("web端异常催收贷款editExceptionSort完成，当前登录用户“"+this.getLoginInfo().getAccount()+"”,loanId:"+loanIdStr);
    */}

    public void overRepayCheck(){
    	//TODO:修改
    	/*
        try{
            String loanIdStr = request.getParameter("loanId");
            logger.info("web端异常催收贷款overRepayCheck开始，当前登录用户“"+this.getLoginInfo().getAccount()+"”,loanId:"+loanIdStr);
            Integer loanId = Integer.parseInt(loanIdStr);
            LnLoan lnLoan=lnLoanService.getLnLoanById(loanId);

            HttpServletResponse response = this.getResponse();
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            float repay =Float.parseFloat(VmHelper.getDecimalMoney(lnLoan.getLoanMoney()))-
                    Float.parseFloat(VmHelper.getDecimalMoney(lnLoan.getLoanRemaining()));

            out.print("客户“"+lnLoan.getCrmCustomer().getCustomerName()+
                    "”应还贷款"+VmHelper.getUnityDecimalMoney(lnLoan.getLoanMoney())+"元，" +
                    "实际已还"+repay+
                    "元，已结清贷款将不再进行催收。确定修改为已结清吗？");
            if(out != null){
                out.flush();
                out.close();
            }
            logger.info("web端异常催收贷款overRepayCheck完成，当前登录用户“"+this.getLoginInfo().getAccount()+"”,loanId:"+loanIdStr);
        }catch (Exception e){
            log.error("overRepayCheck",e);
        }
    */}

    public void overRepay(){
    	//TODO:修改
    	/*
        try{
            String loanIdStr = request.getParameter("loanId");
            logger.info("web端异常催收贷款overRepay开始，当前登录用户“"+this.getLoginInfo().getAccount()+"”,loanId:"+loanIdStr);
            Integer loanId = Integer.parseInt(loanIdStr);
            Map<String ,Object> param = new HashMap<String, Object>();
            param.put("loanId",loanId);
            param.put("loanStatusId",7);
            LnLoan lnLoan=lnLoanService.getLnLoanById(loanId);
            float repay =Float.parseFloat(VmHelper.getDecimalMoney(lnLoan.getLoanMoney()))-
                    Float.parseFloat(VmHelper.getDecimalMoney(lnLoan.getLoanRemaining()));

            LnOpHistory lnOpHistory = new LnOpHistory();
            lnOpHistory.setBeforeStatusId(6);
            lnOpHistory.setAfterStatusId(7);
            lnOpHistory.setOpHistoryDate(new Date());
            lnOpHistory.setUserId(this.getLoginInfo().getUserId());
            lnOpHistory.setLoanId(loanId);
            lnOpHistory.setContent("贷款已结清，应还"+lnLoan.getLoanMoney()+"元，实还"+repay+"元");

            lnLoanService.checkoutLoan(param,lnOpHistory);
            logger.info("web端异常催收贷款overRepay完成，当前登录用户“"+this.getLoginInfo().getAccount()+"”,loanId:"+loanIdStr);
        }catch (Exception e){
            log.error("overRepay",e);
        }
    */}

    public void paidPrincipalChange(){
    	//TODO:修改
    	/*
        try{
            String loanIdStr = request.getParameter("loanId");
            logger.info("web端异常催收贷款paidPrincipalChange开始，当前登录用户“"+this.getLoginInfo().getAccount()+"”,loanId:"+loanIdStr);
            Integer loanId = Integer.parseInt(loanIdStr.trim());
            String exceptionRepaymentPlanIdStr = request.getParameter("exceptionRepaymentPlanId");
            Integer exceptionRepaymentPlanId= Integer.parseInt(exceptionRepaymentPlanIdStr.trim());

            Map<String ,Object> paramMap = new HashMap<String , Object>();
            paramMap.put("paidPrincipal",paidPrincipal);
            paramMap.put("loanId",loanId);
            //更新异常还款计划
            Integer sortno = lnLoanService.getLnLoanById(loanId).getSortno();
            Map<String ,Object> eParamMap = new HashMap<String , Object>();
            eParamMap.put("paidPrincipal",paidPrincipal);
            eParamMap.put("exceptionRepaymentPlanId",exceptionRepaymentPlanId);

            lnLoanService.editPaidPrincipal(paramMap,eParamMap);

            HttpServletResponse response = this.getResponse();
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();

            owedPrincipal = lnLoanService.getLnLoanById(loanId).getOwedPrincipal();
            paidPrincipal = lnLoanService.getLnLoanById(loanId).getPaidPrincipal();
            if(paidPrincipal.compareTo(owedPrincipal) == 1 || paidPrincipal.compareTo(owedPrincipal) == 0){
                out.print("success");
            }
            logger.info("web端异常催收贷款paidPrincipalChange完成，当前登录用户“"+this.getLoginInfo().getAccount()+"”,loanId:"+loanIdStr);
        }catch (Exception e){
            log.error("paidPrincipalChange",e);
        }
    */}



    /**
     * 分配异常催收贷款页面
     * @author zhangfp
     *
     * @return
     */
    public String allotExceptionLoanPage(){
        try{
            logger.info("web端异常催收贷款allotExceptionLoanPage开始，当前登录用户“"+this.getLoginInfo().getAccount());
            String flag = "insert";
            String belongToType = request.getParameter("BelongToType");
            String belongDeptId = request.getParameter("belongDeptId");
            String belongUserId = request.getParameter("belongUserId");
            if(belongDeptId!=null && !belongDeptId.equals("0")&&!belongDeptId.equals("")){
                flag = "modify";	//编辑的情况
            }
            if(belongToType.equalsIgnoreCase("brDept")){
                List<SysDept> list = deptFacadeService.getBusinessManagerInCharegeDeptTreeList();
                JSONArray array = this.toTreeJson(list, flag, belongDeptId);
                request.setAttribute("array",array);
            }else if(belongToType.equalsIgnoreCase("brUser")){
                JSONArray array = this.toUserOrDeptJson(flag, belongUserId);
                request.setAttribute("array", array);
            }else if(belongToType.equalsIgnoreCase("brShareUser")){
                flag = "shareUser";
                JSONArray array = this.toUserOrDeptJson(flag, belongUserId);
                request.setAttribute("array", array);
            }

            request.setAttribute("userName",this.getLoginInfo().getUserName());
            request.setAttribute("userId",this.getLoginInfo().getUserId());
            logger.info("web端异常催收贷款allotExceptionLoanPage完成，当前登录用户“"+this.getLoginInfo().getAccount());
            return SUCCESS;
        }catch (Exception e){
            logger.error("ExceptionDunLoanAction % allotExceptionLoanPage",e);
            return ERROR;
        }
    }

    /**
     * 验证选择的贷款是否已经被分配了异常催收记录
     */
    public void checkExceptionDunLoan(){
        try{
            String loanIds = request.getParameter("loanIds");
            logger.info("web端异常催收贷款checkExceptionDunLoan开始，当前登录用户“"+this.getLoginInfo().getAccount()+",loanIds:"+loanIds);
            List<Integer> loanIdList = this.stringToIntegerList(loanIds);
            Map<String,Object> paramMap = new HashMap<String, Object>();
            paramMap.put("loanIds",loanIdList);
            //检验当前选择的贷款是否已被分配
            Integer count = lnExceptionDunAssignService.selectAssignedCountByLoanId(paramMap);

            HttpServletResponse response = this.getResponse();
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();

            if (count != null && count.intValue() > 0){
                if(loanIdList != null && loanIdList.size() == 1){
                    //只选择了一个贷款分配
                    out.print("true_single");
                }else if(loanIdList != null && loanIdList.size() > 1){
                    //选择了多个贷款分配
                    out.print("true_multi");
                }
            }else if(count != null && count.intValue() == 0){
                out.print("false");
            }
            if(out != null){
                out.flush();
                out.close();
            }
            logger.info("web端异常催收贷款checkExceptionDunLoan完成，当前登录用户“"+this.getLoginInfo().getAccount()+",loanIds:"+loanIds);
        }catch (Exception e){
            logger.error("ExceptionDunLoanAction % checkExceptionDunLoan",e);
        }
    }

    /**
     * 分配异常催收贷款
     * @author zhangfp
     */
    public void allotExceptionLoan(){
        try{
            String loanIds = request.getParameter("loanIds");
            logger.info("web端异常催收贷款allotExceptionLoan开始，当前登录用户“"+this.getLoginInfo().getAccount()+",loanIds:"+loanIds);
            String userIds = request.getParameter("assignUserIds");
            String userNames = request.getParameter("assignUserNames");
            String reAssign = request.getParameter("reAssign");

            List<Integer> loanIdList = this.stringToIntegerList(loanIds);
            List<Integer> assignUserIdList = this.stringToIntegerList(userIds);

            List<LnExceptionDunAssign> dunAssignList = new ArrayList<LnExceptionDunAssign>();
            List<LnOpHistory> lnOpHistoryList = new ArrayList<LnOpHistory>();
            for (Integer loanId : loanIdList){
                LnOpHistory lnOpHistory = new LnOpHistory();
                lnOpHistory.setUserId(this.getLoginInfo().getUserId());
                lnOpHistory.setOpHistoryDate(new Date());
                lnOpHistory.setBeforeStatusId(6);
                lnOpHistory.setAfterStatusId(6);
                lnOpHistory.setContent("分配异常催收贷款给" + userNames);
                lnOpHistory.setLoanId(loanId);

                for (Integer userId : assignUserIdList){
                    LnExceptionDunAssign dunAssign = new LnExceptionDunAssign();
                    dunAssign.setAssignDate(new Date());
                    dunAssign.setAssignUserId(this.getLoginInfo().getUserId());
                    dunAssign.setLoanId(loanId);
                    dunAssign.setDunUserId(userId);

                    dunAssignList.add(dunAssign);
                }
                lnOpHistoryList.add(lnOpHistory);
            }
            if(reAssign.equals("true")){
                //重新分配。先删除已经分配了的记录，然后再重新分配
                lnLoanService.reAssignExpDunLoan(loanIdList,dunAssignList,lnOpHistoryList);
            }else if(reAssign.equals("false")){
                //不重新分配
                //分配异常催收贷款
                lnLoanService.assignExpDunLoan(dunAssignList,lnOpHistoryList);
            }
            logger.info("web端异常催收贷款allotExceptionLoan完成，当前登录用户“"+this.getLoginInfo().getAccount()+",loanIds:"+loanIds);
        }catch (Exception e){
            logger.error("RecordWebServicesImpl % allotExceptionLoan",e);
        }
    }

    private List<Integer> stringToIntegerList(String str){
        if(StringUtils.isNotEmpty(str)){
            List<Integer> idList = new ArrayList<Integer>();
            String[] ids = str.split(",");
            for (String id : ids){
                idList.add(Integer.parseInt(id));
            }
            return idList ;
        }
        return null;
    }

    /**
     * 下属人员树
     * @param flag
     * @param belongUserId
     * @return 人员树json
     */
    private JSONArray toUserOrDeptJson(String flag, String belongUserId){
        JSONArray array = new JSONArray();
        List<CusBelongToBean> deptlist = null;
        if(flag.equals("shareUser")){
            deptlist = deptFacadeService.getBelongToUserAll();
        }else{
            deptlist = deptFacadeService.getBelongToUserAndDeptTreeList(getCurrentUserInChargeOfDeptIds());
        }
        Set<Integer> deptSet = new HashSet<Integer>();
        int ii = 0;
        for (CusBelongToBean dept : deptlist) {
            deptSet.add(dept.getId());
        }
        for(CusBelongToBean dept : deptlist){
            if(!deptSet.contains(dept.getParentId())){
                ii++;
            }
        }

        for(CusBelongToBean dept : deptlist){
            JSONObject obj = new JSONObject();
            obj.put("id", dept.getId());
            if(!deptSet.contains(dept.getParentId())){
                obj.put("pId", 0);
                if(ii==1){
                    obj.put("open", true);
                }
            }else{
                obj.put("pId", dept.getParentId());
            }

            obj.put("name", dept.getTextName());
            obj.put("flag", dept.getType());
            obj.put("deptName", dept.getDeptName());
            if(dept.getType().equals("D")){
                obj.put("icon", "../images/deptTreeImage/1.png");
            }else{
                obj.put("icon", "../images/deptTreeImage/2.png");
            }
            if((flag.equals("modify"))
                    && (dept.getId().toString().equals(belongUserId))
                    && (dept.getType().equals("U"))){
                obj.put("selected", true);
            }
            array.add(obj);
        }
        return array;
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
     * 下属机构树
     * @param depts
     * @param flag
     * @param belongDeptId
     * @return 机构json
     */
    public JSONArray toTreeJson( List<SysDept> depts, String flag, String belongDeptId){
        try {
            Set<Integer> deptSet = new HashSet<Integer>();
            int ii = 0;
            for (SysDept sysDept : depts) {
                deptSet.add(sysDept.getDeptId());
            }
            for(SysDept sysDept : depts){
                if(!deptSet.contains(sysDept.getDeptParentId())){
                    ii++;
                }
            }

            JSONArray jsonArray = new JSONArray();
            if(depts.size()>0){
                for (SysDept dept : depts) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("id", dept.getDeptId());
                    if(!deptSet.contains(dept.getDeptParentId())){
                        map.put("pId", 0);
                        if(ii==1){
                            map.put("open", true);
                        }
                    }else{
                        map.put("pId", dept.getDeptParentId());
                    }
                    map.put("name", dept.getDeptName());
                    if(flag.equals("modify") && dept.getDeptId().toString().equals(belongDeptId)){
                        map.put("selected", true);
                    }
                    jsonArray.add(map);
                }
            }
            return jsonArray;
        } catch (Exception e) {
            //add by zhangxiang 2012-08-09
            log.error("CustomerBelongToAction toTreeJson action error:"+e.getMessage());
            return null;
        }
    }

    public LnExceptionDunAssignService getLnExceptionDunAssignService() {
        return lnExceptionDunAssignService;
    }

    public void setLnExceptionDunAssignService(LnExceptionDunAssignService lnExceptionDunAssignService) {
        this.lnExceptionDunAssignService = lnExceptionDunAssignService;
    }

    public DeptFacadeService getDeptFacadeService() {
        return deptFacadeService;
    }

    public void setDeptFacadeService(DeptFacadeService deptFacadeService) {
        this.deptFacadeService = deptFacadeService;
    }

    public LnLoanService getLnLoanService() {
        return lnLoanService;
    }

    public void setLnLoanService(LnLoanService lnLoanService) {
        this.lnLoanService = lnLoanService;
    }

    public LnLoanTypeService getLnLoanTypeService() {
        return lnLoanTypeService;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Integer getLoanType() {
        return loanType;
    }

    public void setLoanType(Integer loanType) {
        this.loanType = loanType;
    }

    public Date getRepaymentStartDate() {
        return repaymentStartDate;
    }

    public void setRepaymentStartDate(Date repaymentStartDate) {
        this.repaymentStartDate = repaymentStartDate;
    }

    public Date getRepaymentEndDate() {
        return repaymentEndDate;
    }

    public void setRepaymentEndDate(Date repaymentEndDate) {
        this.repaymentEndDate = repaymentEndDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setLnLoanTypeService(LnLoanTypeService lnLoanTypeService) {
        this.lnLoanTypeService = lnLoanTypeService;

    }

    public LnRepaymentPlanService getLnRepaymentPlanService() {
        return lnRepaymentPlanService;
    }

    public void setLnRepaymentPlanService(LnRepaymentPlanService lnRepaymentPlanService) {
        this.lnRepaymentPlanService = lnRepaymentPlanService;
    }

    public LnExceptionRepaymentPlanService getLnExceptionRepaymentPlanService() {
        return lnExceptionRepaymentPlanService;
    }

    public void setLnExceptionRepaymentPlanService(LnExceptionRepaymentPlanService lnExceptionRepaymentPlanService) {
        this.lnExceptionRepaymentPlanService = lnExceptionRepaymentPlanService;
    }

    public Date getPlanDate() {
        return planDate;
    }

    public void setPlanDate(Date planDate) {
        this.planDate = planDate;
    }

    public BigDecimal getOwedPrincipal() {
        return owedPrincipal;
    }

    public void setOwedPrincipal(BigDecimal owedPrincipal) {
        this.owedPrincipal = owedPrincipal;
    }

    public BigDecimal getOwedInterest() {
        return owedInterest;
    }

    public void setOwedInterest(BigDecimal owedInterest) {
        this.owedInterest = owedInterest;
    }

    public BigDecimal getPaidPrincipal() {
        return paidPrincipal;
    }

    public void setPaidPrincipal(BigDecimal paidPrincipal) {
        this.paidPrincipal = paidPrincipal;
    }

    public BigDecimal getPaidInterest() {
        return paidInterest;
    }

    public void setPaidInterest(BigDecimal paidInterest) {
        this.paidInterest = paidInterest;
    }

    public Date getRepaymentDate() {
        return repaymentDate;
    }

    public void setRepaymentDate(Date repaymentDate) {
        this.repaymentDate = repaymentDate;
    }

	public LnDunSetingService getLnDunSetingService() {
		return lnDunSetingService;
	}

	public void setLnDunSetingService(LnDunSetingService lnDunSetingService) {
		this.lnDunSetingService = lnDunSetingService;
	}

	public LnLoanDetailService getLnLoanDetailService() {
		return lnLoanDetailService;
	}

	public void setLnLoanDetailService(LnLoanDetailService lnLoanDetailService) {
		this.lnLoanDetailService = lnLoanDetailService;
	}

	public LnOpHistoryService getLnOpHistoryService() {
		return lnOpHistoryService;
	}

	public void setLnOpHistoryService(LnOpHistoryService lnOpHistoryService) {
		this.lnOpHistoryService = lnOpHistoryService;
	}

	public LnLoanMonitorService getLnLoanMonitorService() {
		return lnLoanMonitorService;
	}

	public void setLnLoanMonitorService(LnLoanMonitorService lnLoanMonitorService) {
		this.lnLoanMonitorService = lnLoanMonitorService;
	}

	public DataFormService getDataFormService() {
		return dataFormService;
	}

	public void setDataFormService(DataFormService dataFormService) {
		this.dataFormService = dataFormService;
	}

	public LnDunLogService getLnDunLogService() {
		return lnDunLogService;
	}

	public void setLnDunLogService(LnDunLogService lnDunLogService) {
		this.lnDunLogService = lnDunLogService;
	}

	public Integer getLoanId() {
		return loanId;
	}

	public void setLoanId(Integer loanId) {
		this.loanId = loanId;
	}

	public Date getMonDate() {
		return monDate;
	}

	public void setMonDate(Date monDate) {
		this.monDate = monDate;
	}

	public List<LnLoanMonitor> getLnLoanMonitorList() {
		return lnLoanMonitorList;
	}

	public void setLnLoanMonitorList(List<LnLoanMonitor> lnLoanMonitorList) {
		this.lnLoanMonitorList = lnLoanMonitorList;
	}

	public LnLoanInfoService getLnLoanInfoService() {
		return lnLoanInfoService;
	}

	public void setLnLoanInfoService(LnLoanInfoService lnLoanInfoService) {
		this.lnLoanInfoService = lnLoanInfoService;
	}

	public LnLoanInfoDictionaryService getLnLoanInfoDictionaryService() {
		return lnLoanInfoDictionaryService;
	}

	public void setLnLoanInfoDictionaryService(
			LnLoanInfoDictionaryService lnLoanInfoDictionaryService) {
		this.lnLoanInfoDictionaryService = lnLoanInfoDictionaryService;
	}

	public LnCreditHistoryService getLnCreditHistoryService() {
		return lnCreditHistoryService;
	}

	public void setLnCreditHistoryService(
			LnCreditHistoryService lnCreditHistoryService) {
		this.lnCreditHistoryService = lnCreditHistoryService;
	}

	public LnLoanInfo getLnLoanInfo() {
		return lnLoanInfo;
	}

	public void setLnLoanInfo(LnLoanInfo lnLoanInfo) {
		this.lnLoanInfo = lnLoanInfo;
	}

	public List<LnLoanCoBorrowerBean> getLoanCoBorrowerList() {
		return loanCoBorrowerList;
	}

	public void setLoanCoBorrowerList(List<LnLoanCoBorrowerBean> loanCoBorrowerList) {
		this.loanCoBorrowerList = loanCoBorrowerList;
	}

	public List<LnLoanGuarantorBean> getLoanGuarantorList() {
		return loanGuarantorList;
	}

	public void setLoanGuarantorList(List<LnLoanGuarantorBean> loanGuarantorList) {
		this.loanGuarantorList = loanGuarantorList;
	}

	public List<LnCreditHistory> getLnCreditHistoryList() {
		return lnCreditHistoryList;
	}

	public void setLnCreditHistoryList(List<LnCreditHistory> lnCreditHistoryList) {
		this.lnCreditHistoryList = lnCreditHistoryList;
	}

	public CustomerDataService getCustomerDataService() {
		return customerDataService;
	}

	public void setCustomerDataService(CustomerDataService customerDataService) {
		this.customerDataService = customerDataService;
	}

	public BaseLnLoanMonitor getLnLoanMonitor() {
		return lnLoanMonitor;
	}

	public void setLnLoanMonitor(BaseLnLoanMonitor lnLoanMonitor) {
		this.lnLoanMonitor = lnLoanMonitor;
	}

	public LnLoanPledgeService getLnLoanPledgeService() {
		return lnLoanPledgeService;
	}

	public void setLnLoanPledgeService(LnLoanPledgeService lnLoanPledgeService) {
		this.lnLoanPledgeService = lnLoanPledgeService;
	}

	public LnExceptionDunLogService getLnExceptionDunLogService() {
		return lnExceptionDunLogService;
	}

	public void setLnExceptionDunLogService(
			LnExceptionDunLogService lnExceptionDunLogService) {
		this.lnExceptionDunLogService = lnExceptionDunLogService;
	}

	public SysUploadFileService getSysUploadFileService() {
		return sysUploadFileService;
	}

	public void setSysUploadFileService(SysUploadFileService sysUploadFileService) {
		this.sysUploadFileService = sysUploadFileService;
	}

	public SysTeamUserService getSysTeamUserService() {
		return sysTeamUserService;
	}

	public void setSysTeamUserService(SysTeamUserService sysTeamUserService) {
		this.sysTeamUserService = sysTeamUserService;
	}
    
}

