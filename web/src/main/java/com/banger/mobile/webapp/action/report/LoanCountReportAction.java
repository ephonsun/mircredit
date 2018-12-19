package com.banger.mobile.webapp.action.report;

import java.io.*;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.*;


import com.banger.mobile.domain.model.report.LoanTypeTotalReportBean;
import com.banger.mobile.util.FileUtil;
import com.banger.mobile.util.ServerRealPathUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.banger.mobile.domain.model.base.system.BaseSysTeam;
import com.banger.mobile.domain.model.dept.SysDept;
import com.banger.mobile.domain.model.report.LoanCountReportBean;
import com.banger.mobile.domain.model.report.LoanStatusCountReportBean;
import com.banger.mobile.domain.model.user.SysUser;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.dept.DeptService;
import com.banger.mobile.facade.loan.LnLoanService;
import com.banger.mobile.facade.map.MapCustomerGpsService;
import com.banger.mobile.facade.system.team.SysTeamService;
import com.banger.mobile.facade.user.SysUserService;
import com.banger.mobile.util.DateUtil;
import com.banger.mobile.util.TimeWrapper;
import com.banger.mobile.webapp.action.BaseAction;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import javax.servlet.ServletOutputStream;

/**
 * @author zhangfp
 * @version $Id: LoanCountReportAction v 0.1 ${} 下午2:56 zhangfp Exp $
 *
 * 贷款量统计表
 */
public class LoanCountReportAction extends BaseAction {

    private static Logger logger = Logger.getLogger(LoanCountReportAction.class);

    private DeptFacadeService deptFacadeService;                             // 部门组织结构service
    private LnLoanService       lnLoanService;                  //贷款service

    private Boolean hasBelongTo;
    private String userName;						//制表人
    private Integer userId;							//用户Id
    private Date reportTime;						//制表时间
    private Date startDate;                         //开始日期
    private Date endDate;                           //结束日期
    private String userIds;                         //下属userId集合
    private String deptIds;                         //下属机构deptId集合
    private String BelongToType;                    //当前统计对象类型
    private SysTeamService sysTeamService;
    private DeptService deptService;
    private SysUserService sysUserService;
    private MapCustomerGpsService mapCustomerGpsService;
    private List<LoanTypeTotalReportBean> jydList;//经营贷
    private List<LoanTypeTotalReportBean> xfdList;//消费贷
    private BigDecimal jydLoanBal;
    private BigDecimal xfdLoanbal;
    LoanTypeTotalReportBean jydBean;
    LoanTypeTotalReportBean xfdBean;
    
    
    /**
     * 报表中心页面加载
     * @return
     */
    public String getFirstLoanCountReportPage(){
        try {
        	//TODO:测试，需要修改
        	
			//加载默认客户经理
			//权限判断
        	
        	System.out.println("-----getFirstLoanCountReportPage----");
			String[] roles = this.getLoginInfo().getRoles();//角色id
    		Integer userId = this.getLoginInfo().getUserId();//用户id
			
			List<SysUser> sysUserList=new ArrayList<SysUser>();
			for (String roleId : roles) {
				if (roleId.equals("5")) {//5:团队主管,6:后台人员  ||roleId.equals("6")
					Map<String, Object> param = new HashMap<String, Object>();
					param.put("userId", userId);
					param.put("roleId", "5,6,7");
					List<SysUser> list = sysUserService.getManagerByUserIdAndRoleId(param);
					sysUserList.addAll(list);
					
				}else if (roleId.equals("7")) {//客户经理
					SysUser sys =new SysUser();
					sys.setAccount(this.getLoginInfo().getAccount());
					sysUserList.add(sys);
				}
				
				if (roleId.equals("5")) {//团队主管					
					request.setAttribute("role", "5");
				}else if (roleId.equals("7")) {//客户经理
					request.setAttribute("role", "7");
				}else if (roleId.equals("6")){//后台人员					
					request.setAttribute("role", "6");					
				}else{
					request.setAttribute("role", "1");
				}
			}
			request.setAttribute("sysUserList", sysUserList);
			//加载默认团队
			List<BaseSysTeam> sysTeamList =new ArrayList<BaseSysTeam>();
			for (String roleId : roles) {
				if (roleId.equals("5")||roleId.equals("7")){
					BaseSysTeam baseSysTeam = sysTeamService.getSysTeamByUserId(userId);
					sysTeamList.add(baseSysTeam);
				}
			}
			
			request.setAttribute("sysTeamList", sysTeamList);
			
			request.setAttribute("reportUrl",getReportUrl());
        	//获取当前时间前一天
			request.setAttribute("beforeDay",DateUtil.convertDateToString("yyyyMMdd", DateUtil.BeforeDay(new Date())));
			return "test";        	
            
        }catch (Exception e){
            logger.error("LoanCountReportAction % getFirstLoanCountReportPage",e);
            return ERROR;
        }
    }
    
    /**
     * 报表中心选择客户经理，团队，机构
     * @return
     */
	public String getTeam(){
    	try {
    		String[] roles = this.getLoginInfo().getRoles();//角色id
    		Integer userId = this.getLoginInfo().getUserId();//用户id
			String type = request.getParameter("type");
			if ("2".equals(type)) {
				List<SysDept> sysDeptList = deptService.getAllDept();
				request.setAttribute("sysDeptList", sysDeptList);
			}else if("3".equals(type)){
				//权限判断				

				List<SysUser> sysUserList=new ArrayList<SysUser>();
				for (String roleId : roles) {
					if (roleId.equals("5")||roleId.equals("6")) {//5:团队主管,6:后台人员 ||roleId.equals("6")
						Map<String, Object> param = new HashMap<String, Object>();
						param.put("userId", userId);
						param.put("roleId", "5,6,7");
						List<SysUser> list = sysUserService.getManagerByUserIdAndRoleId(param);
						sysUserList.addAll(list);
						type = "3";
					}

					if (roleId.equals("7")) {//客户经理
						String userName = this.getLoginInfo().getUserName();
						SysUser sys =new SysUser();
						sys.setUserName(userName);
						sysUserList.add(sys);
						type = "3";
					}
				}
				request.setAttribute("sysUserList", sysUserList);
			} else {
				type = "1";
				List<BaseSysTeam> sysTeamList =new ArrayList<BaseSysTeam>();
				for (String roleId : roles) {
					if (roleId.equals("5")||roleId.equals("7")) {//5:团队主管,7:客户经理
						BaseSysTeam baseSysTeam = sysTeamService.getSysTeamByUserId(userId);
						sysTeamList.add(baseSysTeam);
					}else{
						
						List<BaseSysTeam> lsit = sysTeamService.getBaseSysTeamList();
						sysTeamList.addAll(lsit);
					}
				}
				
				request.setAttribute("sysTeamList", sysTeamList);
			}
			request.setAttribute("type", type);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return SUCCESS;
    }
    
	private String getReportUrl(){		
		try {	

			Enumeration enu=request.getHeaderNames();
			while(enu.hasMoreElements()){
				String headerName=(String)enu.nextElement();
				String headerValue=request.getHeader(headerName);//取出头信息内容
				if(headerValue==null){headerValue="null";};
				logger.error("request.getHeader("+headerName+")="+headerValue);
			}
			String ip = request.getHeader("Host");
			logger.error("getReportUrl % userName:"+this.getLoginInfo().getUserName()+";getIdAddr:"+ip);
			if(ip.startsWith("155")){
				return mapCustomerGpsService.getReportUrl155();
			}else{
				return mapCustomerGpsService.getReportUrl154();
			}				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return mapCustomerGpsService.getReportUrl154();
		}
	
	}
	public String queryLoanCountListReportPage(){
        try {
            logger.info("LoanCountReportAction % queryLoanCountListReportPage,当前登录用户："+this.getLoginInfo().getAccount());
            userName = this.getLoginInfo().getUserName();
            reportTime = new TimeWrapper(new Date().getTime());
            Map<String,Object> paramMap = new HashMap<String, Object>();
            paramMap.put("startDate",startDate);
            endDate = lnLoanService.addSecondsForDate(endDate,60*60*24);
            paramMap.put("endDate",endDate);
            Map<String,List<Integer>> father2SubMap = null;
            if (StringUtils.isNotEmpty(BelongToType)){
                if (BelongToType.equals("my")){
                    userIds = String.valueOf(this.getLoginInfo().getUserId());
                    paramMap.put("userIds",userIds);
                }else if(BelongToType.equals("sub")){
                    paramMap.put("userIds",userIds);
                }else if (BelongToType.equals("dept")){
                    String containSub = request.getParameter("containSub");
                    if (containSub.equals("1")){
                        List<Integer> deptIdList = new ArrayList<Integer>();
                        father2SubMap = this.getDeptIdsByContainSub(deptIds,deptIdList);
                        String curDeptIds = this.list2String(deptIdList);
                        paramMap.put("deptIds",curDeptIds);
                    }else {
                        paramMap.put("deptIds",deptIds);
                    }
                    request.setAttribute("isDept",1);
                }
            }
            logger.info("LoanCountReportAction % queryLoanCountListReportPage,当前登录用户："+this.getLoginInfo().getAccount()+
            ",开始搜索各状态下的贷款数量情况...");
            List<LoanCountReportBean> loanCountReportBeanList = lnLoanService.selectLoanCountReportByStatus(paramMap);
            List<LoanStatusCountReportBean> loanStatusCountReportBeanList = new ArrayList<LoanStatusCountReportBean>();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String timeBucket = null;
            if (startDate != null && endDate != null){
                timeBucket = format.format(startDate)+"至"+format.format(endDate)+"：";
            }else if (startDate != null && endDate == null){
                timeBucket = format.format(startDate)+"之后：";
            }else if (startDate == null && endDate != null){
                timeBucket = "截至"+format.format(endDate)+"：";
            }
            request.setAttribute("timeBucket",timeBucket);
            if (loanCountReportBeanList != null && loanCountReportBeanList.size() > 0){
                Map<Integer,LoanStatusCountReportBean> statusCountReportBeanMap = new HashMap<Integer, LoanStatusCountReportBean>();
                for (LoanCountReportBean loanCountReportBean : loanCountReportBeanList){
                    if (BelongToType.equals("dept")){
                        processDeptsLoanCountReport(timeBucket, statusCountReportBeanMap, loanCountReportBean);
                    }else {
                        processUsersLoanCountReport(timeBucket, statusCountReportBeanMap, loanCountReportBean);

                    }
                }
                if (!statusCountReportBeanMap.isEmpty()){
                    if (BelongToType.equals("dept")) {
                        if (father2SubMap != null){
                            loanStatusCountReportBeanList = countLoanByDeptsSub(father2SubMap, loanStatusCountReportBeanList, statusCountReportBeanMap);
                            //点击了“查询子机构”功能
                        }else {
                            this.countLoanByDeptsNoSub(loanStatusCountReportBeanList, statusCountReportBeanMap);
                            String[] deptIdArr = deptIds.split(",");
                            for (String deptId : deptIdArr){
                                if (!statusCountReportBeanMap.containsKey(Integer.parseInt(deptId))){
                                    String deptName = deptFacadeService.getSysDeptMap().get(Integer.parseInt(deptId)).getDeptName();
                                    LoanStatusCountReportBean loanStatusCountReportBean = new LoanStatusCountReportBean();
                                    loanStatusCountReportBean.setDeptName(deptName);
                                    loanStatusCountReportBean.setAllLoanCount(0);
                                    loanStatusCountReportBean.setApplyLoanCount(0);
                                    loanStatusCountReportBean.setAssignLoanCount(0);
                                    loanStatusCountReportBean.setExamLoanCount(0);
                                    loanStatusCountReportBean.setApproveLoanCount(0);
                                    loanStatusCountReportBean.setMakeLoanCount(0);
                                    loanStatusCountReportBean.setLoanedLoanCount(0);
                                    loanStatusCountReportBean.setSuccessLoanCount(0);
                                    loanStatusCountReportBean.setApproveRefusedLoanCount(0);
                                    loanStatusCountReportBean.setOtherRefusedLoanCount(0);
                                    loanStatusCountReportBeanList.add(loanStatusCountReportBean);

                                    loanStatusCountReportBeanList.add(loanStatusCountReportBean);

                                }
                            }
                        }
                    }else {
                        this.loanCountByUsers(loanStatusCountReportBeanList, statusCountReportBeanMap);
                        String[] userIdArr = userIds.split(",");
                        for (String userId : userIdArr){
                            if (!statusCountReportBeanMap.containsKey(Integer.parseInt(userId))){
                                String curUserName = deptFacadeService.getSysUserMap().get(Integer.parseInt(userId)).getUserName();
                                LoanStatusCountReportBean loanStatusCountReportBean = new LoanStatusCountReportBean();
                                loanStatusCountReportBean.setUserName(curUserName);
                                loanStatusCountReportBean.setAllLoanCount(0);
                                loanStatusCountReportBean.setApplyLoanCount(0);
                                loanStatusCountReportBean.setAssignLoanCount(0);
                                loanStatusCountReportBean.setExamLoanCount(0);
                                loanStatusCountReportBean.setApproveLoanCount(0);
                                loanStatusCountReportBean.setMakeLoanCount(0);
                                loanStatusCountReportBean.setLoanedLoanCount(0);
                                loanStatusCountReportBean.setSuccessLoanCount(0);
                                loanStatusCountReportBean.setApproveRefusedLoanCount(0);
                                loanStatusCountReportBean.setOtherRefusedLoanCount(0);

                                loanStatusCountReportBeanList.add(loanStatusCountReportBean);
                            }
                        }
                    }
                }
            }else {
                //没有搜索到任务统计贷款资料
                if (BelongToType.equals("dept")) {
                    if (father2SubMap != null){
                        //点击了“查询子机构”功能
                        for (String deptId : father2SubMap.keySet()){
                            String deptName = deptFacadeService.getSysDeptMap().get(Integer.parseInt(deptId)).getDeptName();
                            LoanStatusCountReportBean loanStatusCountReportBean = new LoanStatusCountReportBean();
                            loanStatusCountReportBean.setDeptName(deptName);
                            loanStatusCountReportBean.setAllLoanCount(0);
                            loanStatusCountReportBean.setApplyLoanCount(0);
                            loanStatusCountReportBean.setAssignLoanCount(0);
                            loanStatusCountReportBean.setExamLoanCount(0);
                            loanStatusCountReportBean.setApproveLoanCount(0);
                            loanStatusCountReportBean.setMakeLoanCount(0);
                            loanStatusCountReportBean.setLoanedLoanCount(0);
                            loanStatusCountReportBean.setSuccessLoanCount(0);
                            loanStatusCountReportBean.setApproveRefusedLoanCount(0);
                            loanStatusCountReportBean.setOtherRefusedLoanCount(0);
                            loanStatusCountReportBeanList.add(loanStatusCountReportBean);
                        }
                    }else {
                        String[] deptIdArr = deptIds.split(",");
                        for (String deptId : deptIdArr){
                            String deptName = deptFacadeService.getSysDeptMap().get(Integer.parseInt(deptId)).getDeptName();
                            LoanStatusCountReportBean loanStatusCountReportBean = new LoanStatusCountReportBean();
                            loanStatusCountReportBean.setDeptName(deptName);
                            loanStatusCountReportBean.setAllLoanCount(0);
                            loanStatusCountReportBean.setApplyLoanCount(0);
                            loanStatusCountReportBean.setAssignLoanCount(0);
                            loanStatusCountReportBean.setExamLoanCount(0);
                            loanStatusCountReportBean.setApproveLoanCount(0);
                            loanStatusCountReportBean.setMakeLoanCount(0);
                            loanStatusCountReportBean.setLoanedLoanCount(0);
                            loanStatusCountReportBean.setSuccessLoanCount(0);
                            loanStatusCountReportBean.setApproveRefusedLoanCount(0);
                            loanStatusCountReportBean.setOtherRefusedLoanCount(0);
                            loanStatusCountReportBeanList.add(loanStatusCountReportBean);
                        }
                    }
                }else {
                    String[] userIdArr = userIds.split(",");
                    for (String userId : userIdArr){
                        String curUserName = deptFacadeService.getSysUserMap().get(Integer.parseInt(userId)).getUserName();
                        LoanStatusCountReportBean loanStatusCountReportBean = new LoanStatusCountReportBean();
                        loanStatusCountReportBean.setUserName(curUserName);
                        loanStatusCountReportBean.setAllLoanCount(0);
                        loanStatusCountReportBean.setApplyLoanCount(0);
                        loanStatusCountReportBean.setAssignLoanCount(0);
                        loanStatusCountReportBean.setExamLoanCount(0);
                        loanStatusCountReportBean.setApproveLoanCount(0);
                        loanStatusCountReportBean.setMakeLoanCount(0);
                        loanStatusCountReportBean.setLoanedLoanCount(0);
                        loanStatusCountReportBean.setSuccessLoanCount(0);
                        loanStatusCountReportBean.setApproveRefusedLoanCount(0);
                        loanStatusCountReportBean.setOtherRefusedLoanCount(0);
                        loanStatusCountReportBeanList.add(loanStatusCountReportBean);
                    }
                }
            }
            logger.info("LoanCountReportAction % queryLoanCountListReportPage,当前登录用户："+this.getLoginInfo().getAccount()+
                    ",搜索完成");
            request.setAttribute("loanStatusCountReportList",loanStatusCountReportBeanList);
            return SUCCESS;
        }catch (Exception e){
            logger.error("LoanCountReportAction % queryLoanCountListReportPage",e);
            return ERROR;
        }
    }

    private List<LoanStatusCountReportBean> countLoanByDeptsSub(Map<String, List<Integer>> father2SubMap, List<LoanStatusCountReportBean> loanStatusCountReportBeanList, Map<Integer, LoanStatusCountReportBean> statusCountReportBeanMap) {
        List<LoanStatusCountReportBean> reportBeanList = new ArrayList<LoanStatusCountReportBean>();
        countLoanByDeptsNoSub(loanStatusCountReportBeanList, statusCountReportBeanMap);
        for (String deptId : father2SubMap.keySet()){
            List<Integer> deptIdList = father2SubMap.get(deptId);
            String deptName = deptFacadeService.getSysDeptMap().get(Integer.parseInt(deptId)).getDeptName();
            LoanStatusCountReportBean loanStatusCountReportBean = new LoanStatusCountReportBean();
            loanStatusCountReportBean.setTimeBucket("");
            loanStatusCountReportBean.setDeptName(deptName);
            loanStatusCountReportBean.setAllLoanCount(0);
            loanStatusCountReportBean.setApplyLoanCount(0);
            loanStatusCountReportBean.setAssignLoanCount(0);
            loanStatusCountReportBean.setExamLoanCount(0);
            loanStatusCountReportBean.setApproveLoanCount(0);
            loanStatusCountReportBean.setMakeLoanCount(0);
            loanStatusCountReportBean.setLoanedLoanCount(0);
            loanStatusCountReportBean.setSuccessLoanCount(0);
            loanStatusCountReportBean.setApproveRefusedLoanCount(0);
            loanStatusCountReportBean.setOtherRefusedLoanCount(0);
            for (LoanStatusCountReportBean curBean : loanStatusCountReportBeanList){
                if (deptIdList.contains(curBean.getDeptId())){
                    loanStatusCountReportBean.setAllLoanCount((curBean.getAllLoanCount() == null ? 0 : curBean.getAllLoanCount()) + loanStatusCountReportBean.getAllLoanCount());
                    loanStatusCountReportBean.setApplyLoanCount((curBean.getApplyLoanCount() == null ? 0 : curBean.getApplyLoanCount()) + loanStatusCountReportBean.getApplyLoanCount());
                    loanStatusCountReportBean.setAssignLoanCount((curBean.getAssignLoanCount() == null ? 0 : curBean.getAssignLoanCount()) + loanStatusCountReportBean.getAssignLoanCount());
                    loanStatusCountReportBean.setExamLoanCount((curBean.getExamLoanCount() == null ? 0 : curBean.getExamLoanCount()) + loanStatusCountReportBean.getExamLoanCount());
                    loanStatusCountReportBean.setApproveLoanCount((curBean.getApproveLoanCount() == null ? 0 : curBean.getApproveLoanCount()) + loanStatusCountReportBean.getApproveLoanCount());
                    loanStatusCountReportBean.setMakeLoanCount((curBean.getMakeLoanCount() == null ? 0 : curBean.getMakeLoanCount()) + loanStatusCountReportBean.getMakeLoanCount());
                    loanStatusCountReportBean.setLoanedLoanCount((curBean.getLoanedLoanCount() == null ? 0 : curBean.getLoanedLoanCount()) + loanStatusCountReportBean.getLoanedLoanCount());
                    loanStatusCountReportBean.setSuccessLoanCount((curBean.getSuccessLoanCount() == null ? 0 : curBean.getSuccessLoanCount()) + loanStatusCountReportBean.getSuccessLoanCount());
                    loanStatusCountReportBean.setApproveRefusedLoanCount((curBean.getApproveRefusedLoanCount() == null ? 0 : curBean.getApproveRefusedLoanCount()) + loanStatusCountReportBean.getApproveRefusedLoanCount());
                    loanStatusCountReportBean.setOtherRefusedLoanCount((curBean.getOtherRefusedLoanCount() == null ? 0 : curBean.getOtherRefusedLoanCount()) + loanStatusCountReportBean.getOtherRefusedLoanCount());
                }
            }
            reportBeanList.add(loanStatusCountReportBean);
        }
        loanStatusCountReportBeanList = reportBeanList;
        return loanStatusCountReportBeanList;
    }

    private void loanCountByUsers(List<LoanStatusCountReportBean> loanStatusCountReportBeanList, Map<Integer, LoanStatusCountReportBean> statusCountReportBeanMap) {
        for (Integer userId : statusCountReportBeanMap.keySet()){
            LoanStatusCountReportBean curStatusCountReportBean = statusCountReportBeanMap.get(userId);
            if (curStatusCountReportBean.getApplyLoanCount() != null){
                curStatusCountReportBean.setAllLoanCount(curStatusCountReportBean.getAllLoanCount() == null ? curStatusCountReportBean.getApplyLoanCount():
                        curStatusCountReportBean.getAllLoanCount()+curStatusCountReportBean.getApplyLoanCount());
            }
            if (curStatusCountReportBean.getAssignLoanCount() != null){
                curStatusCountReportBean.setAllLoanCount(curStatusCountReportBean.getAllLoanCount() == null ? curStatusCountReportBean.getAssignLoanCount():
                        curStatusCountReportBean.getAllLoanCount()+curStatusCountReportBean.getAssignLoanCount());
            }
            if (curStatusCountReportBean.getExamLoanCount() != null){
                curStatusCountReportBean.setAllLoanCount(curStatusCountReportBean.getAllLoanCount() == null ? curStatusCountReportBean.getExamLoanCount():
                        curStatusCountReportBean.getAllLoanCount()+curStatusCountReportBean.getExamLoanCount());
            }
            if (curStatusCountReportBean.getApproveLoanCount() != null){
                curStatusCountReportBean.setAllLoanCount(curStatusCountReportBean.getAllLoanCount() == null ? curStatusCountReportBean.getApproveLoanCount():
                        curStatusCountReportBean.getAllLoanCount()+curStatusCountReportBean.getApproveLoanCount());
            }
            if (curStatusCountReportBean.getMakeLoanCount() != null){
                curStatusCountReportBean.setAllLoanCount(curStatusCountReportBean.getAllLoanCount() == null ? curStatusCountReportBean.getMakeLoanCount():
                        curStatusCountReportBean.getAllLoanCount()+curStatusCountReportBean.getMakeLoanCount());
            }
            if (curStatusCountReportBean.getLoanedLoanCount() != null){
                curStatusCountReportBean.setAllLoanCount(curStatusCountReportBean.getAllLoanCount() == null ? curStatusCountReportBean.getLoanedLoanCount():
                        curStatusCountReportBean.getAllLoanCount()+curStatusCountReportBean.getLoanedLoanCount());
            }
            if (curStatusCountReportBean.getSuccessLoanCount() != null){
                curStatusCountReportBean.setAllLoanCount(curStatusCountReportBean.getAllLoanCount() == null ? curStatusCountReportBean.getSuccessLoanCount():
                        curStatusCountReportBean.getAllLoanCount()+curStatusCountReportBean.getSuccessLoanCount());
            }
            if (curStatusCountReportBean.getApproveRefusedLoanCount() != null){
                curStatusCountReportBean.setAllLoanCount(curStatusCountReportBean.getAllLoanCount() == null ? curStatusCountReportBean.getApproveRefusedLoanCount():
                        curStatusCountReportBean.getAllLoanCount()+curStatusCountReportBean.getApproveRefusedLoanCount());
            }
            if (curStatusCountReportBean.getOtherRefusedLoanCount() != null){
                curStatusCountReportBean.setAllLoanCount(curStatusCountReportBean.getAllLoanCount() == null ? curStatusCountReportBean.getOtherRefusedLoanCount():
                        curStatusCountReportBean.getAllLoanCount()+curStatusCountReportBean.getOtherRefusedLoanCount());
            }
            loanStatusCountReportBeanList.add(curStatusCountReportBean);
        }
    }

    private void countLoanByDeptsNoSub(List<LoanStatusCountReportBean> loanStatusCountReportBeanList, Map<Integer, LoanStatusCountReportBean> statusCountReportBeanMap) {
        for (Integer deptId : statusCountReportBeanMap.keySet()){
            LoanStatusCountReportBean curStatusCountReportBean = statusCountReportBeanMap.get(deptId);
            if (curStatusCountReportBean.getApplyLoanCount() != null){
                curStatusCountReportBean.setAllLoanCount(curStatusCountReportBean.getAllLoanCount() == null ? curStatusCountReportBean.getApplyLoanCount():
                        curStatusCountReportBean.getAllLoanCount()+curStatusCountReportBean.getApplyLoanCount());
            }
            if (curStatusCountReportBean.getAssignLoanCount() != null){
                curStatusCountReportBean.setAllLoanCount(curStatusCountReportBean.getAllLoanCount() == null ? curStatusCountReportBean.getAssignLoanCount():
                        curStatusCountReportBean.getAllLoanCount()+curStatusCountReportBean.getAssignLoanCount());
            }
            if (curStatusCountReportBean.getExamLoanCount() != null){
                curStatusCountReportBean.setAllLoanCount(curStatusCountReportBean.getAllLoanCount() == null ? curStatusCountReportBean.getExamLoanCount():
                        curStatusCountReportBean.getAllLoanCount()+curStatusCountReportBean.getExamLoanCount());
            }
            if (curStatusCountReportBean.getApproveLoanCount() != null){
                curStatusCountReportBean.setAllLoanCount(curStatusCountReportBean.getAllLoanCount() == null ? curStatusCountReportBean.getApproveLoanCount():
                        curStatusCountReportBean.getAllLoanCount()+curStatusCountReportBean.getApproveLoanCount());
            }
            if (curStatusCountReportBean.getMakeLoanCount() != null){
                curStatusCountReportBean.setAllLoanCount(curStatusCountReportBean.getAllLoanCount() == null ? curStatusCountReportBean.getMakeLoanCount():
                        curStatusCountReportBean.getAllLoanCount()+curStatusCountReportBean.getMakeLoanCount());
            }
            if (curStatusCountReportBean.getLoanedLoanCount() != null){
                curStatusCountReportBean.setAllLoanCount(curStatusCountReportBean.getAllLoanCount() == null ? curStatusCountReportBean.getLoanedLoanCount():
                        curStatusCountReportBean.getAllLoanCount()+curStatusCountReportBean.getLoanedLoanCount());
            }
            if (curStatusCountReportBean.getSuccessLoanCount() != null){
                curStatusCountReportBean.setAllLoanCount(curStatusCountReportBean.getAllLoanCount() == null ? curStatusCountReportBean.getSuccessLoanCount():
                        curStatusCountReportBean.getAllLoanCount()+curStatusCountReportBean.getSuccessLoanCount());
            }
            if (curStatusCountReportBean.getApproveRefusedLoanCount() != null){
                curStatusCountReportBean.setAllLoanCount(curStatusCountReportBean.getAllLoanCount() == null ? curStatusCountReportBean.getApproveRefusedLoanCount():
                        curStatusCountReportBean.getAllLoanCount()+curStatusCountReportBean.getApproveRefusedLoanCount());
            }
            if (curStatusCountReportBean.getOtherRefusedLoanCount() != null){
                curStatusCountReportBean.setAllLoanCount(curStatusCountReportBean.getAllLoanCount() == null ? curStatusCountReportBean.getOtherRefusedLoanCount():
                        curStatusCountReportBean.getAllLoanCount()+curStatusCountReportBean.getOtherRefusedLoanCount());
            }
            loanStatusCountReportBeanList.add(curStatusCountReportBean);
        }
    }

    private void processUsersLoanCountReport(String timeBucket, Map<Integer, LoanStatusCountReportBean> statusCountReportBeanMap, LoanCountReportBean loanCountReportBean) {
        LoanStatusCountReportBean loanStatusCountReportBean;//取的是“我的”或“下属的”
        if (loanCountReportBean.getLoanStatusId() <= 2 || loanCountReportBean.getLoanStatusId() == 11 ||
                loanCountReportBean.getLoanStatusId() == 12){
            String curUserName = deptFacadeService.getSysUserMap().get(loanCountReportBean.getApplyUserId()).getUserName();
            //分配前贷款
            if (!statusCountReportBeanMap.containsKey(loanCountReportBean.getApplyUserId())){
                loanStatusCountReportBean = new LoanStatusCountReportBean();
                loanStatusCountReportBean.setTimeBucket(timeBucket);
                loanStatusCountReportBean.setUserName(curUserName);
                loanStatusCountReportBean.setAllLoanCount(0);
                loanStatusCountReportBean.setApplyLoanCount(0);
                loanStatusCountReportBean.setAssignLoanCount(0);
                loanStatusCountReportBean.setExamLoanCount(0);
                loanStatusCountReportBean.setApproveLoanCount(0);
                loanStatusCountReportBean.setMakeLoanCount(0);
                loanStatusCountReportBean.setLoanedLoanCount(0);
                loanStatusCountReportBean.setSuccessLoanCount(0);
                loanStatusCountReportBean.setApproveRefusedLoanCount(0);
                loanStatusCountReportBean.setOtherRefusedLoanCount(0);
                if (loanCountReportBean.getLoanStatusId() == 1){
                    //申请贷款
                    loanStatusCountReportBean.setApplyLoanCount(loanCountReportBean.getStatusCount());
                }else if (loanCountReportBean.getLoanStatusId() == 2){
                    //分配贷款
                    loanStatusCountReportBean.setAssignLoanCount(loanCountReportBean.getStatusCount());
                }else if (loanCountReportBean.getLoanStatusId() == 11 || loanCountReportBean.getLoanStatusId() == 12){
                    //其他拒绝贷款
                    loanStatusCountReportBean.setOtherRefusedLoanCount(loanCountReportBean.getStatusCount());
                }
                statusCountReportBeanMap.put(loanCountReportBean.getApplyUserId(),loanStatusCountReportBean);
            }else {
                loanStatusCountReportBean = statusCountReportBeanMap.get(loanCountReportBean.getApplyUserId());
                loanStatusCountReportBean.setTimeBucket(timeBucket);
                loanStatusCountReportBean.setUserName(curUserName);
                if (loanCountReportBean.getLoanStatusId() == 1){
                    //申请贷款
                    loanStatusCountReportBean.setApplyLoanCount(loanStatusCountReportBean.getApplyLoanCount() + loanCountReportBean.getStatusCount());
                }else if (loanCountReportBean.getLoanStatusId() == 2){
                    //分配贷款
                    loanStatusCountReportBean.setAssignLoanCount(loanStatusCountReportBean.getAssignLoanCount()+loanCountReportBean.getStatusCount());
                }else if (loanCountReportBean.getLoanStatusId() == 11 || loanCountReportBean.getLoanStatusId() == 12){
                    //其他拒绝贷款
                    loanStatusCountReportBean.setOtherRefusedLoanCount(loanStatusCountReportBean.getOtherRefusedLoanCount()+loanCountReportBean.getStatusCount());
                }
            }
        }else {
            String curUserName = deptFacadeService.getSysUserMap().get(loanCountReportBean.getSurveyUserId()).getUserName();
            //分配后贷款
            if (!statusCountReportBeanMap.containsKey(loanCountReportBean.getSurveyUserId())){
                loanStatusCountReportBean = new LoanStatusCountReportBean();
                loanStatusCountReportBean.setTimeBucket(timeBucket);
                loanStatusCountReportBean.setUserName(curUserName);
                loanStatusCountReportBean.setAllLoanCount(0);
                loanStatusCountReportBean.setApplyLoanCount(0);
                loanStatusCountReportBean.setAssignLoanCount(0);
                loanStatusCountReportBean.setExamLoanCount(0);
                loanStatusCountReportBean.setApproveLoanCount(0);
                loanStatusCountReportBean.setMakeLoanCount(0);
                loanStatusCountReportBean.setLoanedLoanCount(0);
                loanStatusCountReportBean.setSuccessLoanCount(0);
                loanStatusCountReportBean.setApproveRefusedLoanCount(0);
                loanStatusCountReportBean.setOtherRefusedLoanCount(0);
                if (loanCountReportBean.getLoanStatusId() == 3){
                    //调查贷款
                    loanStatusCountReportBean.setExamLoanCount(loanCountReportBean.getStatusCount());
                }else if (loanCountReportBean.getLoanStatusId() == 4){
                    //审批贷款数量
                    loanStatusCountReportBean.setApproveLoanCount(loanCountReportBean.getStatusCount());
                }else if (loanCountReportBean.getLoanStatusId() == 5){
                    //放贷贷款数量
                    loanStatusCountReportBean.setMakeLoanCount(loanCountReportBean.getStatusCount());
                }else if (loanCountReportBean.getLoanStatusId() == 6){
                    //贷后贷款数量
                    loanStatusCountReportBean.setLoanedLoanCount(loanCountReportBean.getStatusCount());
                }else if (loanCountReportBean.getLoanStatusId() == 7){
                    //已结清贷款数量
                    loanStatusCountReportBean.setSuccessLoanCount(loanCountReportBean.getStatusCount());
                }else if (loanCountReportBean.getLoanStatusId() == 13){
                    //其他拒绝贷款数量
                    loanStatusCountReportBean.setOtherRefusedLoanCount(loanCountReportBean.getStatusCount());
                }else if (loanCountReportBean.getLoanStatusId() == 14){
                    //审批拒绝贷款数量
                    loanStatusCountReportBean.setApproveRefusedLoanCount(loanCountReportBean.getStatusCount());
                }
                statusCountReportBeanMap.put(loanCountReportBean.getSurveyUserId(),loanStatusCountReportBean);
            }else {
                loanStatusCountReportBean = statusCountReportBeanMap.get(loanCountReportBean.getSurveyUserId());
                loanStatusCountReportBean.setTimeBucket(timeBucket);
                loanStatusCountReportBean.setUserName(curUserName);
                if (loanCountReportBean.getLoanStatusId() == 3){
                    //调查贷款
                    loanStatusCountReportBean.setExamLoanCount(loanStatusCountReportBean.getExamLoanCount() + loanCountReportBean.getStatusCount());
                }else if (loanCountReportBean.getLoanStatusId() == 4){
                    //审批贷款数量
                    loanStatusCountReportBean.setApproveLoanCount(loanStatusCountReportBean.getApproveLoanCount() + loanCountReportBean.getStatusCount());
                }else if (loanCountReportBean.getLoanStatusId() == 5){
                    //放贷贷款数量
                    loanStatusCountReportBean.setMakeLoanCount(loanStatusCountReportBean.getMakeLoanCount() + loanCountReportBean.getStatusCount());
                }else if (loanCountReportBean.getLoanStatusId() == 6){
                    //贷后贷款数量
                    loanStatusCountReportBean.setLoanedLoanCount(loanStatusCountReportBean.getLoanedLoanCount() + loanCountReportBean.getStatusCount());
                }else if (loanCountReportBean.getLoanStatusId() == 7){
                    //已结清贷款数量
                    loanStatusCountReportBean.setSuccessLoanCount(loanStatusCountReportBean.getSuccessLoanCount() + loanCountReportBean.getStatusCount());
                }else if (loanCountReportBean.getLoanStatusId() == 13){
                    //其他拒绝贷款数量
                    loanStatusCountReportBean.setOtherRefusedLoanCount(loanStatusCountReportBean.getOtherRefusedLoanCount()+loanCountReportBean.getStatusCount());
                }else if (loanCountReportBean.getLoanStatusId() == 14){
                    //审批拒绝贷款数量
                    loanStatusCountReportBean.setApproveRefusedLoanCount(loanStatusCountReportBean.getApproveRefusedLoanCount() + loanCountReportBean.getStatusCount());
                }
            }
        }
    }

    private void processDeptsLoanCountReport(String timeBucket, Map<Integer, LoanStatusCountReportBean> statusCountReportBeanMap, LoanCountReportBean loanCountReportBean) {
        LoanStatusCountReportBean loanStatusCountReportBean;//取的是“机构的”
        if (loanCountReportBean.getLoanStatusId() <= 2 || loanCountReportBean.getLoanStatusId() == 11 ||
                loanCountReportBean.getLoanStatusId() == 12){
            String deptName = deptFacadeService.getSysDeptMap().get(loanCountReportBean.getBeforeAssignDeptId()).getDeptName();
            //分配前贷款
            if (!statusCountReportBeanMap.containsKey(loanCountReportBean.getBeforeAssignDeptId())){
                loanStatusCountReportBean = new LoanStatusCountReportBean();
                loanStatusCountReportBean.setDeptId(loanCountReportBean.getBeforeAssignDeptId());
                loanStatusCountReportBean.setTimeBucket(timeBucket);
                loanStatusCountReportBean.setDeptName(deptName);
                loanStatusCountReportBean.setAllLoanCount(0);
                loanStatusCountReportBean.setApplyLoanCount(0);
                loanStatusCountReportBean.setAssignLoanCount(0);
                loanStatusCountReportBean.setExamLoanCount(0);
                loanStatusCountReportBean.setApproveLoanCount(0);
                loanStatusCountReportBean.setMakeLoanCount(0);
                loanStatusCountReportBean.setLoanedLoanCount(0);
                loanStatusCountReportBean.setSuccessLoanCount(0);
                loanStatusCountReportBean.setApproveRefusedLoanCount(0);
                loanStatusCountReportBean.setOtherRefusedLoanCount(0);
                if (loanCountReportBean.getLoanStatusId() == 1){
                    //申请贷款
                    loanStatusCountReportBean.setApplyLoanCount(loanCountReportBean.getStatusCount());
                }else if (loanCountReportBean.getLoanStatusId() == 2){
                    //分配贷款
                    loanStatusCountReportBean.setAssignLoanCount(loanCountReportBean.getStatusCount());
                }else if (loanCountReportBean.getLoanStatusId() == 11 || loanCountReportBean.getLoanStatusId() == 12){
                    //其他拒绝贷款
                    loanStatusCountReportBean.setOtherRefusedLoanCount(loanCountReportBean.getStatusCount());
                }
                statusCountReportBeanMap.put(loanCountReportBean.getBeforeAssignDeptId(),loanStatusCountReportBean);
            }else {
                loanStatusCountReportBean = statusCountReportBeanMap.get(loanCountReportBean.getBeforeAssignDeptId());
                loanStatusCountReportBean.setTimeBucket(timeBucket);
                loanStatusCountReportBean.setDeptName(deptName);
                if (loanCountReportBean.getLoanStatusId() == 1){
                    //申请贷款
                    loanStatusCountReportBean.setApplyLoanCount(loanStatusCountReportBean.getApplyLoanCount() + loanCountReportBean.getStatusCount());
                }else if (loanCountReportBean.getLoanStatusId() == 2){
                    //分配贷款
                    loanStatusCountReportBean.setAssignLoanCount(loanStatusCountReportBean.getAssignLoanCount()+loanCountReportBean.getStatusCount());
                }else if (loanCountReportBean.getLoanStatusId() == 11 || loanCountReportBean.getLoanStatusId() == 12){
                    //其他拒绝贷款
                    loanStatusCountReportBean.setOtherRefusedLoanCount(loanStatusCountReportBean.getOtherRefusedLoanCount()+loanCountReportBean.getStatusCount());
                }
            }
        }else {
            //分配后贷款
            String deptName = deptFacadeService.getSysDeptMap().get(loanCountReportBean.getAfterAssignDepthId()).getDeptName();
            if (!statusCountReportBeanMap.containsKey(loanCountReportBean.getAfterAssignDepthId())){
                loanStatusCountReportBean = new LoanStatusCountReportBean();
                loanStatusCountReportBean.setDeptId(loanCountReportBean.getAfterAssignDepthId());
                loanStatusCountReportBean.setTimeBucket(timeBucket);
                loanStatusCountReportBean.setDeptName(deptName);
                loanStatusCountReportBean.setAllLoanCount(0);
                loanStatusCountReportBean.setApplyLoanCount(0);
                loanStatusCountReportBean.setAssignLoanCount(0);
                loanStatusCountReportBean.setExamLoanCount(0);
                loanStatusCountReportBean.setApproveLoanCount(0);
                loanStatusCountReportBean.setMakeLoanCount(0);
                loanStatusCountReportBean.setLoanedLoanCount(0);
                loanStatusCountReportBean.setSuccessLoanCount(0);
                loanStatusCountReportBean.setApproveRefusedLoanCount(0);
                loanStatusCountReportBean.setOtherRefusedLoanCount(0);
                if (loanCountReportBean.getLoanStatusId() == 3){
                    //调查贷款
                    loanStatusCountReportBean.setExamLoanCount(loanCountReportBean.getStatusCount());
                }else if (loanCountReportBean.getLoanStatusId() == 4){
                    //审批贷款数量
                    loanStatusCountReportBean.setApproveLoanCount(loanCountReportBean.getStatusCount());
                }else if (loanCountReportBean.getLoanStatusId() == 5){
                    //放贷贷款数量
                    loanStatusCountReportBean.setMakeLoanCount(loanCountReportBean.getStatusCount());
                }else if (loanCountReportBean.getLoanStatusId() == 6){
                    //贷后贷款数量
                    loanStatusCountReportBean.setLoanedLoanCount(loanCountReportBean.getStatusCount());
                }else if (loanCountReportBean.getLoanStatusId() == 7){
                    //已结清贷款数量
                    loanStatusCountReportBean.setSuccessLoanCount(loanCountReportBean.getStatusCount());
                }else if (loanCountReportBean.getLoanStatusId() == 13){
                    //其他拒绝贷款数量
                    loanStatusCountReportBean.setOtherRefusedLoanCount(loanCountReportBean.getStatusCount());
                }else if (loanCountReportBean.getLoanStatusId() == 14){
                    //审批拒绝贷款数量
                    loanStatusCountReportBean.setApproveRefusedLoanCount(loanCountReportBean.getStatusCount());
                }
                statusCountReportBeanMap.put(loanCountReportBean.getAfterAssignDepthId(),loanStatusCountReportBean);
            }else {
                loanStatusCountReportBean = statusCountReportBeanMap.get(loanCountReportBean.getAfterAssignDepthId());
                loanStatusCountReportBean.setTimeBucket(timeBucket);
                loanStatusCountReportBean.setDeptName(deptName);
                if (loanCountReportBean.getLoanStatusId() == 3){
                    //调查贷款
                    loanStatusCountReportBean.setExamLoanCount(loanStatusCountReportBean.getExamLoanCount() + loanCountReportBean.getStatusCount());
                }else if (loanCountReportBean.getLoanStatusId() == 4){
                    //审批贷款数量
                    loanStatusCountReportBean.setApproveLoanCount(loanStatusCountReportBean.getApproveLoanCount() + loanCountReportBean.getStatusCount());
                }else if (loanCountReportBean.getLoanStatusId() == 5){
                    //放贷贷款数量
                    loanStatusCountReportBean.setMakeLoanCount(loanStatusCountReportBean.getMakeLoanCount() + loanCountReportBean.getStatusCount());
                }else if (loanCountReportBean.getLoanStatusId() == 6){
                    //贷后贷款数量
                    loanStatusCountReportBean.setLoanedLoanCount(loanStatusCountReportBean.getLoanedLoanCount() + loanCountReportBean.getStatusCount());
                }else if (loanCountReportBean.getLoanStatusId() == 7){
                    //已结清贷款数量
                    loanStatusCountReportBean.setSuccessLoanCount(loanStatusCountReportBean.getSuccessLoanCount() + loanCountReportBean.getStatusCount());
                }else if (loanCountReportBean.getLoanStatusId() == 13){
                    //其他拒绝贷款数量
                    loanStatusCountReportBean.setOtherRefusedLoanCount(loanStatusCountReportBean.getOtherRefusedLoanCount()+loanCountReportBean.getStatusCount());
                }else if (loanCountReportBean.getLoanStatusId() == 14){
                    //审批拒绝贷款数量
                    loanStatusCountReportBean.setApproveRefusedLoanCount(loanStatusCountReportBean.getApproveRefusedLoanCount() + loanCountReportBean.getStatusCount());
                }
            }
        }
    }

    /**
     * 组装新的DeptIds和选中的机构list
     * @param oldDeptIds
     * @return
     */
    private Map<String,List<Integer>> getDeptIdsByContainSub(String oldDeptIds,List<Integer> allSubDeptIds){
        Map<Integer,List<SysDept>> allDeptMap = deptFacadeService.getAllDeptForMap();
        Map<String,List<Integer>> selectedSubDeptMap = new HashMap<String, List<Integer>>();
        String[] oldDeptIdArr = oldDeptIds.split(",");
        for (String oldDeptId : oldDeptIdArr){
            if (allDeptMap.containsKey(Integer.parseInt(oldDeptId))){
                List<SysDept> subDeptList = allDeptMap.get(Integer.parseInt(oldDeptId));
                if (subDeptList != null && subDeptList.size() > 0){
                    List<Integer> subDeptIdList = getDeptIdList(subDeptList);
                    allSubDeptIds.addAll(subDeptIdList);
                    if (!selectedSubDeptMap.containsKey(oldDeptId)){
                        selectedSubDeptMap.put(oldDeptId,subDeptIdList);
                    }
                }
            }
        }
        return selectedSubDeptMap;
    }

    private String list2String(List<Integer> deptIdList){
        StringBuilder sb = new StringBuilder();
        for (Integer deptId : deptIdList){
            sb.append(deptId+",");
        }
        if (sb.length() > 0){
            return sb.substring(0,sb.length() - 1);
        }
        return null;
    }

    private List<Integer> getDeptIdList(List<SysDept> sysDepts){
        List<Integer> deptIdList = new ArrayList<Integer>();
        for (SysDept sysDept : sysDepts){
            deptIdList.add(sysDept.getDeptId());
        }
        return deptIdList;
    }
    //经营消费贷统计表
    public String getLoanInfoStatisticsTable(){

        getLoanInfoStatisticsTableData();
        request.setAttribute("jydList",jydList);
        request.setAttribute("xfdList",xfdList);
        request.setAttribute("jydLoanBal",jydLoanBal);
        request.setAttribute("xfdLoanbal",xfdLoanbal);
        request.setAttribute("jydBean",jydBean);
        request.setAttribute("xfdBean",xfdBean);
        return SUCCESS;
    }

    public void getLoanInfoStatisticsTableData(){
        jydList = new ArrayList<LoanTypeTotalReportBean>();
        xfdList = new ArrayList<LoanTypeTotalReportBean>();
        Date dNow = new Date();   //当前时间
        Date dBefore = new Date();
        Calendar c1 = Calendar.getInstance(); //得到日历
//        Calendar c2 = Calendar.getInstance(); //得到日历
        c1.setTime(dNow);//把当前时间赋给日历
//        c2.setTime(dNow);

        //一年以内
//        c2.add(c2.YEAR, -1);
//        c2.add(c2.DAY_OF_MONTH,-1);
//        c1.add(c1.DAY_OF_MONTH,1);
//
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
//        String start = sdf.format(c2.getTime());    //格式化前3月的时间
//        String end = sdf.format(c1.getTime()); //格式化当前时间
        jydList.add(getReprotBean(0, 13, 1, null));
        xfdList.add(getReprotBean(0, 13, 2, null));

        //一年以上(含)三年以下
//        c2.add(c2.YEAR, -2);
//        c1.add(c1.DAY_OF_MONTH,-1);
//        c1.add(c1.YEAR,-1);
//        end = sdf.format(c1.getTime());
//        start = sdf.format(c2.getTime());    //格式化前3月的时间
        jydList.add(getReprotBean(12, 37, 1, null));
        xfdList.add(getReprotBean(12, 37, 2, null));
//        //三年以上
//        c2.add(c2.DAY_OF_MONTH,1);
//        end = sdf.format(c2.getTime());
        jydList.add(getReprotBean(36, null, 1, null));
        xfdList.add(getReprotBean(36, null, 2, null));
        //月末金额(万元)
        //笔数和总额
//        c1.setTime(dNow);
        c1.set(Calendar.DAY_OF_MONTH, c1.getActualMinimum(Calendar.DAY_OF_MONTH));

        String end =sdf.format(c1.getTime());

        jydList.add(getReprotBean(null, null, 1, end));//经营贷
        xfdList.add(getReprotBean(null, null, 2, end));//消费贷
        //总计
//        c1.setTime(dNow);
//        c1.add(c1.DAY_OF_MONTH, 1);
//        end = sdf.format(c1.getTime());
//        jydList.add(getReprotBean(null, end, 1));//经营贷
//        xfdList.add(getReprotBean(null, end, 2));//消费贷
        //月底总余额
//        LoanTypeTotalReportBean jydBean = lnLoanService.getAllMonthLoanBal(null, end, 1, null);
        jydLoanBal = lnLoanService.getAllMonthLoanBal(null, end, 1, null).getLonAmount().add(lnLoanService.getKDBMonthLoanBal(null, end, 1,"1").getTotalAmount());

//        LoanTypeTotalReportBean xfdBean = lnLoanService.getAllMonthLoanBal(null, end, 2, null);
        xfdLoanbal = lnLoanService.getAllMonthLoanBal(null, end, 2, null).getLonAmount().add(lnLoanService.getKDBMonthLoanBal(null, end, 2,"1").getTotalAmount());
        //卡贷宝数据
        jydBean = lnLoanService.getAllMonthLoanBal(null, end, 1, 5);
        jydBean.setLonAmount(lnLoanService.getKDBMonthLoanBal(null, end, 1, "1").getTotalAmount().add(jydBean.getLonAmount()));
        jydBean.setTotalAmount(lnLoanService.getKDBMonthLoanBal(null, end, 1, null).getTotalAmount());

        xfdBean = lnLoanService.getAllMonthLoanBal(null, end, 2, 5);
        xfdBean.setLonAmount(lnLoanService.getKDBMonthLoanBal(null, end, 2, "1").getTotalAmount().add(xfdBean.getLonAmount()));
        xfdBean.setTotalAmount(lnLoanService.getKDBMonthLoanBal(null, end, 2, null).getTotalAmount());

    }

    public LoanTypeTotalReportBean getReprotBean(Integer start,Integer end,Integer loanType,String endDate){
        Map<String,Object> map = new HashMap<String, Object>();
        if(start != null){
            map.put("start",start);
        }
        if(end != null){
            map.put("end",end);
        }
        if(loanType != null) {
            map.put("loanTypeId", loanType);
        }
        if(StringUtils.isNotBlank(endDate)){
            map.put("endDate",endDate);
        }
        LoanTypeTotalReportBean loanTypeTotalReportBean = lnLoanService.getLoanTypeTotalSum(map);
        return loanTypeTotalReportBean;
    }
    /*
    导出经营/消费性贷统计表
     */
    public void downLoanInfoStatisticsTable(){
        try {
            String timePath = new SimpleDateFormat("yyyyMMddHmsS").format(new Date());
            String path = FileUtil.contact(this.getSession().getServletContext().getRealPath("/"), "temp_file/export/经营消费性贷统计表" + timePath + ".xls");
            String dir = path.substring(0, path.lastIndexOf("/")); // 取出目录
            File file = new File(dir);
            // 检查文件是否存在
            File obj = new File(path);
            if (!file.exists()) { // 目录不存在，则创建相应的目录
                file.mkdirs();
                if (!obj.exists()) // 接下来创建具体文件
                    obj.createNewFile(); // 就是在这个点抛出异常
            }
            downLoanInfoStatisticsExcel(path);
            // 写流文件到前端浏览器
            ServletOutputStream out = this.getResponse().getOutputStream();
            this.getResponse().setHeader("Content-Disposition",
                    "attachment; filename=" + java.net.URLEncoder.encode("经营消费性贷统计表.xls", "UTF-8"));
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;

            bis = new BufferedInputStream(new FileInputStream(path));
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
            bis.close();
            bos.close();
            obj.delete();
        } catch (Exception e) {
            log.error("导出经营/消费性贷统计表失败", e);
        }
    }

    public void downLoanInfoStatisticsExcel(String path){
        try {
            String rtefPath = request.getSession().getServletContext().getResource("/ini").getFile();
            if (!FileUtil.isExistFilePath(rtefPath)) {
                rtefPath = ServerRealPathUtil.getServerRealPath() + File.separator + "ini";
            }
            rtefPath = rtefPath + File.separator + "report.xls";
            POIFSFileSystem fs=new POIFSFileSystem(new FileInputStream(rtefPath));
            HSSFWorkbook book = new HSSFWorkbook(fs);
            getLoanInfoStatisticsTableData();
            //填充第一个工作表
            HSSFSheet sheet = book.getSheetAt(0);
            //填充第5行数据
            HSSFRow row5 = sheet.getRow(5);//经营贷
            writeLoanInfoStatisticsTableData(row5,jydList,jydLoanBal);
            //填充第6行数据
            HSSFRow row6 = sheet.getRow(6);//经营贷
            writeLoanInfoStatisticsTableData(row6,xfdList,xfdLoanbal);

            FileOutputStream os = new FileOutputStream(new File(path));
            book.write(os);
            os.close();


        }catch (Exception e){
            logger.error("导出经营/消费性贷统计表失败",e);
        }
    }

    public void writeLoanInfoStatisticsTableData(HSSFRow row,List<LoanTypeTotalReportBean> list,BigDecimal loanBal){
        int i=1;
        int j=2;
        for(LoanTypeTotalReportBean bean:list){

            row.getCell(i).setCellValue(bean.getLoanTotal());
            if(bean.getTotalAmount()!=null){
                row.getCell(j).setCellValue(bean.getTotalAmount().toString());
            }else{
                row.getCell(j).setCellValue(0);
            }
            i+=2;
            j+=2;
        }
        if(loanBal!=null){
            row.getCell(i).setCellValue(loanBal.toString());
        }else{
            row.getCell(i).setCellValue(0);
        }
    }

    public void downKDBStatisticsTable(){
        try {
            String timePath = new SimpleDateFormat("yyyyMMddHmsS").format(new Date());
            String path = FileUtil.contact(this.getSession().getServletContext().getRealPath("/"), "temp_file/export/卡贷宝统计表" + timePath + ".xls");
            String dir = path.substring(0, path.lastIndexOf("/")); // 取出目录
            File file = new File(dir);
            // 检查文件是否存在
            File obj = new File(path);
            if (!file.exists()) { // 目录不存在，则创建相应的目录
                file.mkdirs();
                if (!obj.exists()) // 接下来创建具体文件
                    obj.createNewFile(); // 就是在这个点抛出异常
            }
            downKDBStatisticsExcel(path);
            // 写流文件到前端浏览器
            ServletOutputStream out = this.getResponse().getOutputStream();
            this.getResponse().setHeader("Content-Disposition",
                    "attachment; filename=" + java.net.URLEncoder.encode("卡贷宝统计表.xls", "UTF-8"));
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;

            bis = new BufferedInputStream(new FileInputStream(path));
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
            bis.close();
            bos.close();
            obj.delete();
        }catch (Exception e){
            logger.error("导出卡贷宝失败",e);
        }
    }

    public void downKDBStatisticsExcel(String path){
        try {
            String rtefPath = request.getSession().getServletContext().getResource("/ini").getFile();
            if (!FileUtil.isExistFilePath(rtefPath)) {
                rtefPath = ServerRealPathUtil.getServerRealPath() + File.separator + "ini";
            }
            rtefPath = rtefPath + File.separator + "report1.xls";
            POIFSFileSystem fs=new POIFSFileSystem(new FileInputStream(rtefPath));
            HSSFWorkbook book = new HSSFWorkbook(fs);
            getLoanInfoStatisticsTableData();
            //填充第一个工作表
            HSSFSheet sheet = book.getSheetAt(0);
            //填充第5行数据
            HSSFRow row5 = sheet.getRow(4);//经营贷
            writeKDBStatisticsTableData(row5,jydBean);
            //填充第6行数据
            HSSFRow row6 = sheet.getRow(5);//经营贷
            writeKDBStatisticsTableData(row6, xfdBean);

            FileOutputStream os = new FileOutputStream(new File(path));
            book.write(os);
            os.close();


        }catch (Exception e){
            logger.error("导出卡贷宝表失败",e);
        }
    }
    public void writeKDBStatisticsTableData(HSSFRow row,LoanTypeTotalReportBean bean){
        row.getCell(2).setCellValue(bean.getLoanTotal());
        row.getCell(4).setCellValue(bean.getTotalAmount().toString());
        row.getCell(6).setCellValue(bean.getLonAmount().toString());
    }

    public String getCustomerContactInfoTable(){

        return SUCCESS;

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

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    public String getDeptIds() {
        return deptIds;
    }

    public void setDeptIds(String deptIds) {
        this.deptIds = deptIds;
    }

    public String getBelongToType() {
        return BelongToType;
    }

    public void setBelongToType(String belongToType) {
        BelongToType = belongToType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getReportTime() {
        return reportTime;
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }

    public Boolean getHasBelongTo() {
        return hasBelongTo;
    }

    public void setHasBelongTo(Boolean hasBelongTo) {
        this.hasBelongTo = hasBelongTo;
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

    public SysTeamService getSysTeamService() {
		return sysTeamService;
	}
	public void setSysTeamService(SysTeamService sysTeamService) {
		this.sysTeamService = sysTeamService;
	}
	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}
	public void setSysUserService(SysUserService sysUserService) {
		this.sysUserService = sysUserService;
	}
	public SysUserService getSysUserService() {
		return sysUserService;
	}

	public MapCustomerGpsService getMapCustomerGpsService() {
		return mapCustomerGpsService;
	}

	public void setMapCustomerGpsService(MapCustomerGpsService mapCustomerGpsService) {
		this.mapCustomerGpsService = mapCustomerGpsService;
	}
	
	
}
