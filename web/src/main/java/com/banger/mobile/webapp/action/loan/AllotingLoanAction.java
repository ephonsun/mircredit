package com.banger.mobile.webapp.action.loan;

import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.customer.CrmCustomer;
import com.banger.mobile.domain.model.data.LoanData;
import com.banger.mobile.domain.model.data.Photo;
import com.banger.mobile.domain.model.dept.SysDept;
import com.banger.mobile.domain.model.loan.*;
import com.banger.mobile.domain.model.system.SysTeam;
import com.banger.mobile.domain.model.user.SysUser;
import com.banger.mobile.facade.data.CustomerDataService;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.loan.*;
import com.banger.mobile.facade.specialDataAuth.SpecialDataAuthService;
import com.banger.mobile.facade.system.team.SysTeamService;
import com.banger.mobile.facade.user.SysUserService;
import com.banger.mobile.util.StringUtil;
import com.banger.mobile.webapp.action.BaseAction;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Zhangfp
 * Date: 13-2-5
 * Time: 下午4:13
 * To change this template use File | Settings | File Templates.
 *
 * 未分配贷款 Action
 */
public class AllotingLoanAction extends BaseAction{

    private static Logger logger = Logger.getLogger(AllotingLoanAction.class);

    //services
    private LnLoanService lnLoanService; //贷款service
    private DeptFacadeService deptFacadeService; //部门组织结构service
    private LnLoanTypeService lnLoanTypeService; //贷款类型service
    private LnCancelReasonService lnCancelReasonService; //撤销贷款原因service
    private LnOpHistoryService lnOpHistoryService; //贷款操作历史记录service
    private CustomerDataService customerDataService; //客户资料service
    private SpecialDataAuthService specialDataAuthService;
    private LnLoanInfoService lnLoanInfoService;
    private LnLoanInfoDictionaryService lnLoanInfoDictionaryService;
    private SysUserService sysUserService;
    
    //查询条件
    private String customer;
    private Integer loanType;
    private Date startDate;
    private Date endDate;
    private String applyUser;

    /**
     * 首页列表 未分配贷款列表
     * 
     * @return
     */
    public String allotingLoanList(){
        try{
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 进入分配贷款菜单...");
            Map<String,Object> parameterMap = new HashMap<String, Object>();
            parameterMap.put("assignUserId", this.getLoginInfo().getUserId()); //2期改成提交给我分配的贷款
            //parameterMap.put("applyUserIds",applyUserIds); //当前用户所管理的提交申请用户
            parameterMap.put("loanStatusId",2);  //贷款状态：待分配
            //分页查询出ln_loan表中的待分配贷款记录
            PageUtil<LnLoan> dataList = lnLoanService.getApplyLoanByPage(parameterMap,this.getPage());
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 总共查询出"+dataList.getItems().size()+"条符合条件的分配贷款");

            if(dataList != null && dataList.getItems() != null){
                for (LnLoan lnLoan : dataList.getItems()) {
                    Date applyDate = lnLoan.getApplySubmitDate();
                    if(applyDate == null){
                    	continue;
                    }
                    Date nowDate = new Date();
                    if((nowDate.getTime() - applyDate.getTime()) >= 1000 * 60 * 60 * 10){
                        //超过10小时未分配,前端在高亮显示
                        lnLoan.setIsWillPast(1);
                    }
                }
            }

            //处理用户范围内的客户的数据权限
            //lnLoanService.proCusAuthority(isInChargeOf,belongUserIds,dataList,this.getLoginInfo());

            //贷款类型
            //贷款类型
            Map<String,Object> paramMap = new HashMap<String,Object>();
            paramMap.put("dictionaryName", "DKLX");
    		List<LnLoanInfoDictionary> typeList = lnLoanInfoDictionaryService.selectLoanInfoDictionaryList(paramMap);


            request.setAttribute("typeList",typeList);
            request.setAttribute("dataList",dataList.getItems());
            request.setAttribute("recordCount",dataList.getPage().getTotalRowsAmount());
            String roleIds= StringUtil.getIdsString(getLoginInfo().getRoles());
            boolean flag=specialDataAuthService.getSysDataAuthCondition(roleIds,"loanInfo");
            if (flag){
                request.setAttribute("flag",flag);
                request.setAttribute("loginUserId",this.getLoginInfo().getUserId());
                request.setAttribute("loginUserAccount",this.getLoginInfo().getAccount());
            }

            request.setAttribute("userName",this.getLoginInfo().getUserName());
            request.setAttribute("dataCode", this.getLoginInfo().getDataCompetence()); //用户数据权限

            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 完全进入分配贷款页面");
            return SUCCESS;
        }catch (Exception e){
            logger.error("AllotingLoanAction % allotingLoanList",e);
            e.printStackTrace();
            return ERROR;
        }
    }

    /**
     * 查询列表
     * 
     * @return
     */
    public String allotingLoanListQuery(){
        try {
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 按条件搜索分配贷款...");
            String userIds = request.getParameter("userIds");
            List<Integer> applyUserIds = new ArrayList<Integer>();
            //列表数据
            Map<String,Object> conds = new HashMap<String, Object>();
            conds.put("loanStatusId",2); //2表示待分配

            conds.put("assignUserId", this.getLoginInfo().getUserId()); //2期改成提交给我分配的贷款


            //贷款归属
            String belongToType = request.getParameter("BelongToType");
            if (StringUtils.isNotEmpty(belongToType)){
                if (belongToType.equals("brAll")){
                    //所有，包括下属用户和下属机构
//                    parameterMap.put("cusBelongUserIds",belongUserIds);
                }else if (belongToType.equals("brMine")){
                    applyUserIds.clear();
                    applyUserIds.add(this.getLoginInfo().getUserId());
                    //我的
                    conds.put("applyUserIds",applyUserIds);
                }else if (belongToType.equals("brUser")){
                    //下属用户
                    if (StringUtils.isNotEmpty(userIds) && userIds.length() > 0){
                        applyUserIds.clear();
                        String[] userIdArr = userIds.split(",");
                        for (String userId : userIdArr){
                            applyUserIds.add(Integer.parseInt(userId));
                        }
                        conds.put("applyUserIds",applyUserIds);
                    }
                }
            }

            conds.put("customer",customer);
            conds.put("loanTypeId",loanType);
            conds.put("applyStartDate",startDate);
            endDate = lnLoanService.addSecondsForDate(endDate,59);
            conds.put("applyEndDate", endDate);
            String appUserIds = "";
            StringBuilder sb = new StringBuilder();
            for (Integer appUserId : applyUserIds){
                sb.append(appUserId + ",");
            }
            if (sb.length() > 0){
                appUserIds = sb.substring(0,sb.length() - 1);
            }
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 搜索条件，客户："+customer+",贷款类型："+loanType+",提交人员："+appUserIds+",提交时间："+startDate+"——"+endDate);
            PageUtil<LnLoan> dataList = lnLoanService.getApplyLoanByPage(conds,this.getPage());
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 总共搜索到"+dataList.getItems().size()+"条符合条件的分配贷款");

            if(dataList != null && dataList.getItems() != null){
                for (LnLoan lnLoan : dataList.getItems()) {
                    Date applyDate = lnLoan.getApplySubmitDate();
                    if(applyDate == null){
                    	continue;
                    }
                    Date nowDate = new Date();
                    if((nowDate.getTime() - applyDate.getTime()) >= 1000 * 60 * 60 * 10){
                        //超过10小时未分配,前端在高亮显示
                        lnLoan.setIsWillPast(1);
                    }
                }
            }

            //处理用户范围内的客户的数据权限
            //lnLoanService.proCusAuthority(isInChargeOf,belongUserIds,dataList,this.getLoginInfo());

            request.setAttribute("dataList",dataList.getItems());
            request.setAttribute("recordCount",dataList.getPage().getTotalRowsAmount());

            request.setAttribute("userName",this.getLoginInfo().getUserName());
            request.setAttribute("dataCode", this.getLoginInfo().getDataCompetence()); //用户数据权限

            return SUCCESS;
        }catch (Exception e){
            logger.error("AllotingLoanAction % allotingLoanListQuery",e);
            return ERROR;
        }
    }

    /**
     * 选择撤销原因弹窗
     * @return
     */
    public String cancelLoanPage(){
        try{
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 贷款拒绝原因选择...");
            //查询撤销选择原因列表
            List<LnCancelReason> lnCancelReasonList = lnCancelReasonService.getCancelReasonList();
            request.setAttribute("lnCancelReasonList", lnCancelReasonList);
            return SUCCESS;
        }catch (Exception e){
            logger.error("AllotingLoanAction % cancelLoanPage", e);
            return ERROR;
        }

    }

    /**
     * 撤销贷款
     */
    public void cancelLoan(){
    	//TODO: 修改
    	/*
        try {
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 开始拒绝贷款...");
            String strLoanId = request.getParameter("loanId");
            String cancelReasonOther = request.getParameter("cancelReasonOther");
            String cancelReasonId = request.getParameter("cancelReasonId");
            String cancelReasonName = request.getParameter("cancelReasonName");
            String beforeStatusIdStr = request.getParameter("beforeStatusId");
            String operate="0";
            if("1".equals(beforeStatusIdStr)){
                operate ="1";
            }else
            if("2".equals(beforeStatusIdStr)){
                 operate="2";
            }else
            if("3".equals(beforeStatusIdStr)){
                 operate="3";
            }
            String commApprovePersonName = request.getParameter("commApprovePersonName");
            String remark = request.getParameter("remark");
            Integer beforeStatusId = null;
            if (StringUtils.isNotEmpty(beforeStatusIdStr)){
                beforeStatusId = Integer.parseInt(beforeStatusIdStr) ;
            }

            Integer userId = this.getLoginInfo().getUserId();
            //更改当前贷款状态为8(待撤销)
            Map<String,Object> paramMap = new HashMap<String, Object>();

            LnOpHistory lnOpHistory = new LnOpHistory();
            lnOpHistory.setUserId(userId);
            lnOpHistory.setOpHistoryDate(new Date());
            lnOpHistory.setBeforeStatusId(beforeStatusId);  //拒绝前状态
            lnOpHistory.setAfterStatusId(LoanConstants.LOAN_AFTER_REFUSE_STATUS[beforeStatusId - 1]);   //拒绝后状态
            lnOpHistory.setContent(LoanConstants.LOAN_REFUSE_CONTENT[beforeStatusId - 1]);
            if (beforeStatusId.equals(LoanConstants.LOAN_APPROVAL_STATUS)){
                //审批拒绝。历史记录内容稍有不同，贷款审批，审批人xxx，共同审批人yyy（xxx为审批用户姓名，yyy为共同审批人姓名）
                String approvePersonName = this.getLoginInfo().getUserName();
                String hisContent = "贷款审批，审批人"+approvePersonName+"，共同审批人"+commApprovePersonName;
                lnOpHistory.setContent(hisContent);
            }
            lnOpHistory.setLoanId(Integer.parseInt(strLoanId.trim()));
            if(cancelReasonOther != null){
                //点击了其他撤销原因
                paramMap.put("cancelReasonOther",cancelReasonOther);
                if (StringUtils.isNotEmpty(remark)){
                    lnOpHistory.setRemark(remark+"|"+cancelReasonOther);
                }else {
                    lnOpHistory.setRemark(cancelReasonOther);
                }
            }else if(cancelReasonOther == null){
                //没有点击其他撤销原因
                paramMap.put("cancelDate",new Date());
                if(StringUtils.isNotEmpty(cancelReasonId)){
                    paramMap.put("cancelReasonId",Integer.parseInt(cancelReasonId));
                }
                if (StringUtils.isNotEmpty(remark)){
                    lnOpHistory.setRemark(remark+"|"+cancelReasonName);
                }else {
                    lnOpHistory.setRemark(cancelReasonName);
                }
            }
            paramMap.put("cancelUserId",userId);
            paramMap.put("loanStatusId", LoanConstants.LOAN_AFTER_REFUSE_STATUS[beforeStatusId - 1]);
            paramMap.put("loanId",Integer.parseInt(strLoanId.trim()));

            //以下几个属性主要是为了审批拒绝所用(因为审批拒绝里包含了原先的审批失败)
            if("3".equals(operate)){
            String approvePerson2Id = request.getParameter("approvePerson2Id");
            String approvePerson1Date = request.getParameter("approvePerson1Date");
            String approveRemark = request.getParameter("approveRemark");
            if (StringUtils.isNotEmpty(approvePerson2Id)){
                paramMap.put("approveUserId2",Integer.parseInt(approvePerson2Id));
                paramMap.put("approveUserId1",userId);
            }
            if (StringUtils.isNotEmpty(approvePerson1Date)){
                //主审批人审批失败时间
//            paramMap.put("approveFailDate1",approvePerson1Date);
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Date approvalUserDate = format.parse(approvePerson1Date);
                if (approvalUserDate != null){
                    paramMap.put("approveFailDate1",approvalUserDate);
                }
                //共同审批人审批失败时间
                paramMap.put("approveFailDate2",new Date());
            }
            if (StringUtils.isNotEmpty(approveRemark)){
                paramMap.put("approveRemark",approveRemark);
            }
            }
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 当前贷款id为"+strLoanId+",状态为"+beforeStatusIdStr);
            //插入贷款操作历史记录

            //申请贷款撤销
            String customerId = request.getParameter("customerId");
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 开始拒绝贷款以及插入历史记录...");
            lnLoanService.cancelLoan(paramMap,lnOpHistory);
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 拒绝贷款完成!");
            if(!"0".equals(operate)){
                //调用接口往信贷发送
                Map<String,Object> map=new HashMap<String, Object>();
                LnLoan lnLoan=lnLoanService.getLnLoanById(Integer.parseInt(strLoanId));
                map.put("loanId",lnLoan.getLoanId());
                List<LnLoanInfo> loanInfos=lnLoanInfoService.selectLoanInfoList(map);
                if(loanInfos==null)
                    return;
                LnLoanInfo loanInfo=loanInfos.get(0);
                String djSerno=loanInfo.getSerialNumber();
                //sendMessageService.refuseLoan(djSerno,lnLoan,operate);
            }
        }catch (Exception e){
            logger.error("AllotingLoanAction % cancelLoan",e);
        }
    */}

    //切换到分配贷款页面
    public String allotLoanPage(){
        try {
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 进入分配贷款页面...");
            
            String loanId = request.getParameter("loanId");
            if(loanId != null && !loanId.trim().equals("")){
            	LnLoan lnLoan = lnLoanService.selectLoanById(Integer.parseInt(loanId));
                request.setAttribute("applyUserId", lnLoan.getApplyUserId());
            }
            
            
            String loans = request.getParameter("loans");
            String belongDeptId = this.getLoginInfo().getDeptId().toString();
            String belongUserId = this.getLoginInfo().getUserId().toString();
            String belongUserName = this.getLoginInfo().getUserName();

            //贷款id和客户id
            String[] loanIdAndCusIdArray = loans.split(",");
            if(loans != null && !loans.equals("")){
	            String applyUserId = "";
	            int i =0;
	            for (String loanIdCursId : loanIdAndCusIdArray) {
	            	LnLoan lnLoan = lnLoanService.selectLoanById(Integer.parseInt(loanIdCursId.split("@")[0]));
	            	if(i == 0){
	            		applyUserId += lnLoan.getApplyUserId();
	            		i = 1;
	            	}else{
	            		applyUserId += "," + lnLoan.getApplyUserId();
	            	}
	               
				}
	            request.setAttribute("applyUserId", applyUserId);
            }
            
            
            List<SysDept> list = deptFacadeService.getBusinessManagerInCharegeDeptTreeList();
            
            JSONArray deptArray = this.toTreeJson(list, "insert", "");

            if(loanIdAndCusIdArray != null){
                request.setAttribute("loanCount",loanIdAndCusIdArray.length);
            }
            request.setAttribute("belongDeptId", belongDeptId);
            request.setAttribute("belongUserId", belongUserId);
            request.setAttribute("belongUserName", belongUserName);
            request.setAttribute("belongUserAccount",this.getLoginInfo().getAccount());
            request.setAttribute("deptArray", deptArray);

            return SUCCESS;
        }catch (Exception e){
        	e.printStackTrace();
            logger.error("AllotingLoanAction % allotLoanPage",e);
            return ERROR;
        }
    }

    /**
     * 分配贷款
     */
    public void allotLoan(){
        try {
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 贷款分配...");
            String loans = request.getParameter("loans");
            String belongDeptId = request.getParameter("belongDeptId");
            String belongUserId = request.getParameter("belongUserId");
            String belongUserName = request.getParameter("belongUserName");
            String assignRemark = request.getParameter("assignRemark");
            List<Map<String,Integer>> idList = this.stringToMapList(loans);

            if(idList != null && idList.size() > 0){
                if (idList.size() == 1){
                    Map<String,Integer> idMap = idList.get(0);
                    Integer loanId = idMap.get("loanId");
                    Integer cusId = idMap.get("cusId");
                    logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 贷款id为"+loanId+",贷款客户id为"+cusId+",分配给用户”"+belongUserName+"“");
                    //更改贷款主表，将贷款状态及其他信息改为待调查
                    Map<String,Object> loanParamMap = new HashMap<String, Object>();
                    loanParamMap.put("loanId",loanId);
                    //分配者用户id,当前登录用户
                    loanParamMap.put("assignUserId",this.getLoginInfo().getUserId());
                    //被分配者用户id,谁调查的id
                    loanParamMap.put("surveyUserId",Integer.parseInt(belongUserId));
                    loanParamMap.put("assignRemark",assignRemark);
                    loanParamMap.put("assignSubmitDate",new Date());
                    loanParamMap.put("loanStatusId",3);
                    loanParamMap.put("eventId", 3);
                    //在将待分配贷款改为待调查后，要将LAST_CLIENT_QUERY_DATE这个字段的值设为NULL
                    //更改客户表，将该贷款所关联的客户归属到被分配者
                    CrmCustomer crmCustomer = new CrmCustomer();
                    crmCustomer.setBelongUserId(Integer.parseInt(belongUserId));
                    crmCustomer.setBelongDeptId(Integer.parseInt(belongDeptId));
                    crmCustomer.setCustomerId(cusId);

                    //插入贷款历史操作记录
                    LnOpHistory lnOpHistory = new LnOpHistory();
                    lnOpHistory.setUserId(this.getLoginInfo().getUserId());
                    lnOpHistory.setOpHistoryDate(new Date());
                    lnOpHistory.setBeforeStatusId(2);  //待分配
                    lnOpHistory.setAfterStatusId(3);   //待调查
                    lnOpHistory.setContent("贷款分配给"+belongUserName);
                    lnOpHistory.setRemark(assignRemark);   //历史记录备注
                    lnOpHistory.setLoanId(loanId);
                    logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 单个贷款开始分配...");
                    //单个分配
                    lnLoanService.allotLoanSingle(loanParamMap,crmCustomer,lnOpHistory);
                    //调用接口往信贷发送
                   /* LnLoan lnLoan1=lnLoanService.getLnLoanById(loanId);
                    Map<String,Object> map=new HashMap<String, Object>();
                    map.put("loanId",loanId);
                    LnLoanInfo loanInfo=lnLoanInfoService.selectLoanInfoList(map).get(0);
                    String djSerno=loanInfo.getSerialNumber();*/
                    //sendMessageService.assignLoan(djSerno,lnLoan1,lnLoan1.getAssignUserId(),lnLoan1.getSurveyUserId());
                    logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 单个贷款分配完成");

                }else {

                    List<Map<String,Object>> loanParamList = new ArrayList<Map<String, Object>>();
                    List<CrmCustomer> cusList = new ArrayList<CrmCustomer>();
                    List<LnOpHistory> lnOpHistoryList = new ArrayList<LnOpHistory>();

                    Map<String,String> cusParamMap = new HashMap<String, String>();
                    cusParamMap.put("belongDeptId",belongDeptId);
                    cusParamMap.put("belongUserId",belongUserId);
                    logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 多笔贷款同时分配,分配给用户”"+belongUserName+"“");
                    StringBuilder cusIdsBuf = new StringBuilder();
                    for (Map<String,Integer> idMap : idList){
                        Integer loanId = idMap.get("loanId");
                        Integer cusId = idMap.get("cusId");

                        //更改贷款主表，将贷款状态及其他信息改为待调查
                        Map<String,Object> loanParamMap = new HashMap<String, Object>();
                        loanParamMap.put("loanId",loanId);
                        //分配者用户id,当前登录用户
                        loanParamMap.put("assignUserId",this.getLoginInfo().getUserId());
                        //被分配者用户id,谁调查的id
                        loanParamMap.put("surveyUserId",Integer.parseInt(belongUserId));
                        loanParamMap.put("assignRemark",assignRemark);
                        loanParamMap.put("assignSubmitDate",new Date());
                        loanParamMap.put("loanStatusId",3);
                        loanParamMap.put("eventId", 3);
                        //在将待分配贷款改为待调查后，要将LAST_CLIENT_QUERY_DATE这个字段的值设为NULL
                        loanParamMap.put("warningLoan",1);

                        loanParamList.add(loanParamMap);

                        //更改客户表，将该贷款所关联的客户归属到被分配者
                        CrmCustomer crmCustomer = new CrmCustomer();
                        crmCustomer.setCustomerId(cusId);
                        crmCustomer.setBelongDeptId(Integer.parseInt(belongDeptId));
                        crmCustomer.setBelongUserId(Integer.parseInt(belongUserId));

                        cusList.add(crmCustomer);

                        cusIdsBuf.append(String.valueOf(cusId) + ",");

                        //插入贷款历史操作记录
                        LnOpHistory lnOpHistory = new LnOpHistory();
                        lnOpHistory.setUserId(this.getLoginInfo().getUserId());
                        lnOpHistory.setOpHistoryDate(new Date());
                        lnOpHistory.setBeforeStatusId(2);  //待分配
                        lnOpHistory.setAfterStatusId(3);   //待调查
                        lnOpHistory.setContent("贷款分配");
                        lnOpHistory.setRemark(assignRemark);   //历史记录备注
                        lnOpHistory.setLoanId(loanId);

                        lnOpHistoryList.add(lnOpHistory);

                    }

                    String cusIds = cusIdsBuf.substring(0,cusIdsBuf.length() - 1);
                    cusParamMap.put("cusIds",cusIds);
                    logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 多笔贷款开始分配...");
                    //开始分配贷款
                    lnLoanService.allotLoanBatch(loanParamList,cusParamMap,lnOpHistoryList);

                    //调用接口往信贷发送
                    /*for(Map<String,Object> map1:loanParamList){
                    Integer loanId=(Integer)map1.get("loanId");
                    LnLoan lnLoan1=lnLoanService.getLnLoanById(loanId);
                    Map<String,Object> map=new HashMap<String, Object>();
                    map.put("loanId",loanId);
                    LnLoanInfo loanInfo=lnLoanInfoService.selectLoanInfoList(map).get(0);
                    String djSerno=loanInfo.getSerialNumber();
                    //sendMessageService.assignLoan(djSerno,lnLoan1,lnLoan1.getAssignUserId(),lnLoan1.getSurveyUserId());
                    }*/
                    logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 多笔贷款分配完成");
                }
            }
        }catch (Exception e){
        	e.printStackTrace();
            logger.error("AllotingLoanAction % allotLoan",e);
        }
    }
    
    //保存分配备注
    public void saveAllotLoanNote(){
    	PrintWriter out = null;
    	try{
    		
    		out = this.getResponse().getWriter();
    		Integer loanId = Integer.parseInt(request.getParameter("loanId"));
    		String assignRemark = request.getParameter("assignRemark");
    		
    		LnLoanInfo lnLoanInfo = new LnLoanInfo();
    		lnLoanInfo.setLoanId(loanId);
    		lnLoanInfo.setAssignRemark(assignRemark);
    		
    		lnLoanInfoService.updateLnLoanInfo(lnLoanInfo);
    		
    		
    		out.print("保存成功！");
    	}catch(Exception e){
    		e.printStackTrace();
    		out.print("保存备注异常，请刷新后重试或联系管理员！");
    	}
    		
    }
    
    
    //查询用户
    public String searchUser(){
        try{
        	 List<SysUser> sysUserList = sysUserService.getUserListBelongToSysTeamByUserId(this.getLoginInfo().getUserId());
             request.setAttribute("userList", sysUserList);      
            return SUCCESS;
        }catch (Exception e) {
            return ERROR;
        }
    }

    public void queryLoanStatusById(){
        String loanIdStr = request.getParameter("loanId");
        String loanStatusIdStr = request.getParameter("loanStatusId");
        if(StringUtils.isNotEmpty(loanIdStr) && StringUtils.isNotEmpty(loanStatusIdStr)){
            Integer loanId = Integer.parseInt(loanIdStr);
            Map<String,Object> paramMap = new HashMap<String,Object>();
            paramMap.put("loanId",loanId);

            Integer cusLoanStatusId = Integer.parseInt(loanStatusIdStr);

            //查看贷款状态
            Integer loanStatusId = lnLoanService.getLoanStatusById(paramMap);
            HttpServletResponse response = this.getResponse();
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = null;
            try {
                out = response.getWriter();
                if(loanStatusId != null && loanStatusId.equals(cusLoanStatusId)){
                    out.print("true");
                }else {
                    out.print("false");
                }
                out.flush();
            } catch (IOException e) {
                logger.error("AllotingLoanAction % queryLoanStatusById",e);
                if (out != null)
                    out.close();
            }finally {
                if (out != null)
                    out.close();
            }
        }
    }

    /**
     * 查看变化后的贷款的数量
     */
    public void queryChangedLoanStatusCount(){
        try {
            String loans = request.getParameter("loans");
            List<Map<String,Integer>> mapList = this.stringToMapList(loans);
            if(mapList != null && mapList.size() > 0){
                List<Integer> loanIds = new ArrayList<Integer>();
                for (Map<String,Integer> strInt : mapList){
                    loanIds.add(strInt.get("loanId"));
                }
                Map<String,Object> paramMap = new HashMap<String, Object>();
                paramMap.put("loanStatusId",2);
                paramMap.put("loanIds",loanIds);

                Integer changedCount = lnLoanService.queryChangedLoanStatusCount(paramMap);

                HttpServletResponse response = this.getResponse();
                response.setContentType("text/html;charset=utf-8");

                PrintWriter out = response.getWriter();
                out.print(changedCount);
                out.flush();
                out.close();
            }
        }catch (Exception e){
            logger.error("AllotingLoanAction % queryChangedLoanStatusCount",e);
        }
    }

    /**
     * 编辑待分配贷款
     * @return
     */
    public String editUnAllotLoan(){
        try{
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 进入编辑分配贷款页面...");
            String loanId = request.getParameter("loanId");
            String customerId = request.getParameter("customerId");
            String loanStatusId = request.getParameter("loanStatusId");
            
            // 贷款基本信息
	        Map<String,Object> param = new HashMap<String, Object>();
	        param.put("loanId", loanId);
	        LnLoanInfo lnLoanInfo = lnLoanInfoService.selectLoanInfoList(param).get(0);
            
	        Map<String,Object> param2 = new HashMap<String,Object>();
	        param2.put("dictionaryName", "DKLX");
	        param2.put("dictionaryKey", lnLoanInfo.getAppLoanTypeId());
	        LnLoanInfoDictionary lnLoanInfoDictionary = lnLoanInfoDictionaryService.selectLoanInfoDictionaryList(param2).get(0);
            request.setAttribute("loanId",loanId);
            request.setAttribute("customerId",customerId);
            request.setAttribute("loanStatusId",loanStatusId);
            request.setAttribute("mobilePhone",lnLoanInfo.getCusMobilePhone());
            request.setAttribute("lnLoanInfo",lnLoanInfo);
            request.setAttribute("appLoanTypeName", lnLoanInfoDictionary.getDictionaryValue());
            request.setAttribute("isLoan","true");
            return SUCCESS;
        }catch (Exception e){
            logger.error("AllotingLoanAction % editUnAllotLoan",e);
            return ERROR;
        }
    }

    /**
     * 拆分loans为元素为Map的List
     * @param loans
     * @return
     */
    private List<Map<String,Integer>> stringToMapList(String loans){
        List<Map<String,Integer>> idList = new ArrayList<Map<String, Integer>>();
        //贷款id和客户id
        if(StringUtils.isNotEmpty(loans)){
            String[] loanIdAndCusIdArray = loans.split(",");
            for (String cusStr : loanIdAndCusIdArray){
                String[] ids = cusStr.split("@");
                Integer loanId = Integer.parseInt(ids[0]);
                Integer cusId = Integer.parseInt(ids[1]);

                Map<String,Integer> loanIdCusId = new HashMap<String, Integer>();
                loanIdCusId.put("loanId",loanId);
                loanIdCusId.put("cusId",cusId);

                idList.add(loanIdCusId);
            }
        }
        return idList;
    }

    /**
     * 当前用户的下属用户
     * @return
     */
    private List<Integer> getSysUserIds(){
        //当前用户的下属用户
//        List<SysUser> sysUsers = deptFacadeService.getBusinessManagerInCharegeOfUsers();
        List<Integer> applyUserIds = new ArrayList<Integer>();
        Boolean isInChargeOf = deptFacadeService.isInChargeOfDepartment();
        if (isInChargeOf){
            String belongUserIds = deptFacadeService.getUserIdsBelongToManager(this.getLoginInfo().getRoles(),"loanInfo");
            if (StringUtils.isNotEmpty(belongUserIds)){
                String[] belongUserIdArr = belongUserIds.split(",");
                if (belongUserIdArr != null && belongUserIdArr.length > 0){
                    for (String belongUserId : belongUserIdArr){
                        applyUserIds.add(Integer.parseInt(belongUserId));
                    }
                }
            }
        }else {
            applyUserIds.add(this.getLoginInfo().getUserId());
        }
        return applyUserIds;
    }

    /**
     * 获取查询条件时的申请人id(提交人id)列表
     * @param applyUser
     * @return
     */
    private List<Integer> stringToList(String applyUser){
        if(StringUtils.isNotEmpty(applyUser)){
            String[] paramArray = applyUser.split(",");
            List<Integer> resultList = new ArrayList<Integer>();
            for (String str : paramArray){
                resultList.add(Integer.parseInt(str));
            }
            return resultList;
        }
        return null;
    }

    private JSONArray toTreeJson( List<SysDept> depts, String flag, String belongDeptId){
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
//                    map.put("icon", "../images/deptTreeImage/1.png");
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

    //退回审批
    public void rollBackApprove(){
        try{
            PrintWriter out = this.getResponse().getWriter();
            String loanId = this.request.getParameter("loanId");
            LnLoan lnLoan = new LnLoan();
            lnLoan.setLoanId(Integer.valueOf(loanId));
            lnLoan.setLoanStatusId(4);
            lnLoanService.updateLoanByLoanId(lnLoan);
            out.print("success");

        }catch (Exception e){
            logger.error("退回审批出错!",e);
        }
    }


    public LnLoanService getLnLoanService() {
        return lnLoanService;
    }

    public void setLnLoanService(LnLoanService lnLoanService) {
        this.lnLoanService = lnLoanService;
    }

    public DeptFacadeService getDeptFacadeService() {
        return deptFacadeService;
    }

    public void setDeptFacadeService(DeptFacadeService deptFacadeService) {
        this.deptFacadeService = deptFacadeService;
    }

    public LnLoanTypeService getLnLoanTypeService() {
        return lnLoanTypeService;
    }

    public void setLnLoanTypeService(LnLoanTypeService lnLoanTypeService) {
        this.lnLoanTypeService = lnLoanTypeService;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public LnCancelReasonService getLnCancelReasonService() {
        return lnCancelReasonService;
    }

    public void setLnCancelReasonService(LnCancelReasonService lnCancelReasonService) {
        this.lnCancelReasonService = lnCancelReasonService;
    }

    public LnOpHistoryService getLnOpHistoryService() {
        return lnOpHistoryService;
    }

    public void setLnOpHistoryService(LnOpHistoryService lnOpHistoryService) {
        this.lnOpHistoryService = lnOpHistoryService;
    }

    public CustomerDataService getCustomerDataService() {
        return customerDataService;
    }

    public void setCustomerDataService(CustomerDataService customerDataService) {
        this.customerDataService = customerDataService;
    }

    public SpecialDataAuthService getSpecialDataAuthService() {
        return specialDataAuthService;
    }

    public void setSpecialDataAuthService(SpecialDataAuthService specialDataAuthService) {
        this.specialDataAuthService = specialDataAuthService;
    }

    public void setLnLoanInfoService(LnLoanInfoService lnLoanInfoService) {
        this.lnLoanInfoService = lnLoanInfoService;
    }

	public void setLnLoanInfoDictionaryService(
			LnLoanInfoDictionaryService lnLoanInfoDictionaryService) {
		this.lnLoanInfoDictionaryService = lnLoanInfoDictionaryService;
	}

	public void setSysUserService(SysUserService sysUserService) {
		this.sysUserService = sysUserService;
	}
    
    
}
