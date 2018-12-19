/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :营销任务执行action
 * Author     :yuanme
 * Version    :1.0
 * Create Date:May 3, 2012
 */
package com.banger.mobile.webapp.action.tskMarketing;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.pdtProduct.PdtProduct;
import com.banger.mobile.domain.model.system.TaskGrade;
import com.banger.mobile.domain.model.tskMarketing.TskMarketing;
import com.banger.mobile.domain.model.tskMarketing.TskMarketingBean;
import com.banger.mobile.domain.model.tskMarketing.TskMarketingPlan;
import com.banger.mobile.domain.model.tskMarketing.TskMarketingPlanDetail;
import com.banger.mobile.domain.model.user.SysUser;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.pdtProduct.PdtProductService;
import com.banger.mobile.facade.system.TaskGradeService;
import com.banger.mobile.facade.tskMarketing.TskMarketingPlanDetailService;
import com.banger.mobile.facade.tskMarketing.TskMarketingPlanService;
import com.banger.mobile.facade.tskMarketing.TskMarketingService;
import com.banger.mobile.facade.user.SysUserService;
import com.banger.mobile.webapp.action.BaseAction;
import com.hundsun.common.lang.StringUtil;

public class TskMarketingPlanAction extends BaseAction {
    private static final long             serialVersionUID = 1589326398928860527L;

    private TskMarketingPlanDetailService tskMarketingPlanDetailService;
    private TskMarketingPlanService       tskMarketingPlanService;
    private TaskGradeService              taskGradeService;
    private TskMarketingService           tskMarketingService;
    private DeptFacadeService             deptFacadeService;
    private SysUserService                sysUserService;
    private PdtProductService             pdtProductService;
    private PdtProduct                    pdtProduct;

    //查询字段
    private Integer                       marketingId;
    private Integer                       firstMarketingId;
    private Boolean                       isInChargeof;                           //是否是业务主管
    private Integer                       detailId;                               //单个计划详情id
    private String                        selectedUserIds;                        //用户勾选下属集合
    private Date                          planDateFrom;
    private Date                          planDateTo;
    private Integer                       planId;                                 //计划主表id
    private String 	                      gradeId;                                //任务等级ID
    //保存字段
    private Integer[]                     planDetailId;
    private BigDecimal[]                  planDetailTarget;
    private String[]                      remark;
    private Integer[]                     productId;
    
    private String[] productIdMaiketingId;

    //当天执行的营销任务
    public String planDetailList() {
        try {
        	//判断是否是客户经理
            if(deptFacadeService.isCommon()){
            	request.setAttribute("isManager", 1);
            }
            Integer userId = this.getLoginInfo().getUserId();
            isInChargeof = deptFacadeService.isInChargeOfDepartment();

            Map<String, Object> parameterMap = new HashMap<String, Object>();
            if (isInChargeof) {
                String userIds = deptFacadeService.getInChargeOfDeptUserIds(userId, 0);
                parameterMap.put("userIds", userIds);
            } else {
                parameterMap.put("userIds", userId);
            }

            Calendar calFrom = Calendar.getInstance();
            calFrom.set(Calendar.HOUR_OF_DAY, 0);
            calFrom.set(Calendar.MINUTE, 0);
            calFrom.set(Calendar.SECOND, 0);
            calFrom.set(Calendar.MILLISECOND, 0);
            parameterMap.put("planDateFrom", calFrom.getTime());

            Calendar calTo = Calendar.getInstance();
            calTo.set(Calendar.HOUR_OF_DAY, 23);
            calTo.set(Calendar.MINUTE, 59);
            calTo.set(Calendar.SECOND, 59);
            calTo.set(Calendar.MILLISECOND, 999);
            parameterMap.put("planDateTo", calTo.getTime());

            List<TskMarketingPlanDetail> dataList = tskMarketingPlanDetailService
                .getMarketingPlanDetail(parameterMap);

            BigDecimal recordTotal = new BigDecimal(0);
            int recordCount = 0; //产品个数
            Map<Integer, Integer> map = new HashMap<Integer, Integer>();
            Map<String, BigDecimal> mapToday = new HashMap<String, BigDecimal>();
            Map<String, TskMarketingPlanDetail> mapTips = new HashMap<String, TskMarketingPlanDetail>();
            for (TskMarketingPlanDetail d : dataList) {
                if (d.getPlanDetailTarget() != null) {
                    recordTotal = recordTotal.add(d.getPlanDetailTarget());//计划营销额总额
                }
                if (map.get(d.getProductId()) == null) {//第一次取得
                    recordCount++;//过滤相同产品

                    map.put(d.getProductId(), 1);
                }

                if (mapToday.get(d.getProductId() + "_" + d.getExecuteUserId()) == null) {//第一次取得
                    // 实际销售额
                    BigDecimal todaySaleMoney = tskMarketingPlanDetailService.getTodaySaleMoney(
                        d.getProductId(), d.getExecuteUserId());
                    d.setTodaySaleMoney(todaySaleMoney);

                    mapToday.put(d.getProductId() + "_" + d.getExecuteUserId(), todaySaleMoney);
                } else {
                    d.setTodaySaleMoney(mapToday.get(d.getProductId() + "_" + d.getExecuteUserId()));
                }

                //提示相关字段
                if (mapTips.get(d.getExecuteUserId() + "_" + d.getMarketingId()+"_"+d.getProductId()) == null) {//第一次取得
                    //任务提示各字段service
                    TskMarketingPlanDetail detailTip = tskMarketingPlanDetailService.getTipsByMarketing(d);
                    d = detailTip;
                    mapTips.put(d.getExecuteUserId() + "_" + d.getMarketingId()+"_"+d.getProductId(), d);
                } else {
                    //如果相同任务则直接从map中取得
                    TskMarketingPlanDetail temp = mapTips.get(d.getExecuteUserId() + "_"
                                                              + d.getMarketingId()+"_"+d.getProductId());
                    d.setProductTarget(temp.getProductTarget());
                    // 营销目标
                    d.setMarketingTarget(temp.getMarketingTarget());
                    // 已销售额
                    d.setSaleMoney(temp.getSaleMoney());
                    // 差额
                    d.setRemainTarget(temp.getRemainTarget());
                    // 日均剩余营销额=剩余营销目标/剩余日期
                    d.setDaysRemainTarget(temp.getDaysRemainTarget());
                    // 拟营销额
                    d.setPlanedTarget(temp.getPlanedTarget());
                    // 未计划营销额=差额—拟营销额
                    d.setNotPlanedTarget(temp.getNotPlanedTarget());
                }
            }

            request.setAttribute("userId", userId);
            request.setAttribute("dataList", dataList);
            request.setAttribute("recordCount", recordCount);
            request.setAttribute("recordTotal", recordTotal);
            return SUCCESS;
        } catch (Exception e) {
            log.error("planDetailList error", e);
            return ERROR;
        }
    }

    //保存计划
    public void planDetailAdd() {
        try {
            if (planDetailId != null) {
                List<TskMarketingPlanDetail> list = new ArrayList<TskMarketingPlanDetail>();
                for (int i = 0; i < planDetailId.length; i++) {
                    TskMarketingPlanDetail detail = new TskMarketingPlanDetail();
                    detail.setPlanDetailId(planDetailId[i]);
                    detail.setPlanDetailTarget(planDetailTarget[i]);
                    detail.setRemark(remark[i]);

                    list.add(detail);
                }

                tskMarketingPlanDetailService.batchUpdate(list);
            }
            //return SUCCESS;
        } catch (Exception e) {
            log.error("planDetailAdd error", e);
            //return ERROR;
        }
    }

    //选择产品，保存计划
    public void productAdd() {
        try {
            if (productIdMaiketingId != null) {
            	Map<String, Object> parameterMap = new HashMap<String, Object>();
                Integer userId = this.getLoginInfo().getUserId();
                Calendar calFrom = Calendar.getInstance();
                calFrom.set(Calendar.HOUR_OF_DAY, 0);
                calFrom.set(Calendar.MINUTE, 0);
                calFrom.set(Calendar.SECOND, 0);
                calFrom.set(Calendar.MILLISECOND, 0);

                parameterMap.put("planDate", calFrom.getTime());
                parameterMap.put("executeUserId", userId);
                
            	for (int a = 0; a < productIdMaiketingId.length; a++) {
                	String pmId=productIdMaiketingId[a];
                	System.err.println(pmId.split("="));
                	parameterMap.put("marketingId", pmId.split("=")[1]);
                	
                	List<TskMarketingPlan> planList = tskMarketingPlanService.getTskMarketingPlanListByMap(parameterMap);
                    TskMarketingPlan plan = null;
                    if (planList.size() > 0) {
                        //计划主表已经存在，则只新增计划字表
                        plan = planList.get(0);
                    } else {
                        //计划主表不存在，则主，字表都要新增
                        plan = new TskMarketingPlan();
                        plan.setMarketingId(Integer.parseInt(pmId.split("=")[1]));
                        plan.setPlanDate(calFrom.getTime());
                        plan.setExecuteUserId(userId);
                        tskMarketingPlanService.addTskMarketingPlan(plan);
                    }

                    // 新增字表任务目标
                    if (plan != null) {
                        List<TskMarketingPlanDetail> list = new ArrayList<TskMarketingPlanDetail>();
//                        for (int i = 0; i < productIdMaiketingId.length; i++) {
                        	Integer count=tskMarketingPlanService.getTaskPlanProductCount(plan.getPlanId(), Integer.parseInt(pmId.split("=")[0]));
                            if(count==0){
                            	TskMarketingPlanDetail detail = new TskMarketingPlanDetail();
                                detail.setPlanId(plan.getPlanId());
                                detail.setMarketingId(Integer.parseInt(pmId.split("=")[1]));
                                detail.setProductId(Integer.parseInt(pmId.split("=")[0]));
                                detail.setPlanDetailTarget(new BigDecimal(0));
                                detail.setRemark("");
                                list.add(detail);
                            }
//                        }
                        tskMarketingPlanDetailService.batchInsert(list);
                    }
				}
            }
        } catch (Exception e) {
            log.error("productAdd error", e);
        }
    }

    //选择产品
    public String selectProduct() {
        try {
            //获取任务等级
            List<TaskGrade> gList = taskGradeService.getTaskGradeList();

            //获取当前登录用户所有执行的任务
            Integer userId = this.getLoginInfo().getUserId();
            Map<String, Object> parameterMap = new HashMap<String, Object>();
            parameterMap.put("executeUserId", userId);
            parameterMap.put("Expired", 0);
            List<TskMarketing> tList = tskMarketingService.getAllTskMarketingByConds(parameterMap);

            //过滤没有的任务等级
            gList = filterGrade(tList, gList);

            //构建任务树
            Map<String, Object> map = new HashMap<String, Object>();
            JSONArray jsonArray = new JSONArray();
            //根节点
            map.put("id", "0");
            map.put("pId", "-1");
            map.put("open", true);
            map.put("name", "所有");
            map.put("type", "A");
            jsonArray.add(map);
            for (TaskGrade g : gList) {
                map.put("id", g.getMarketingGradeId());
                map.put("pId", "0");
                map.put("open", true);
                map.put("name", g.getMarketingGradeName());
                map.put("type", "G");
                jsonArray.add(map);
            }
            int i = 0;
            for (TskMarketing t : tList) {
            	//只显示执行中的任务
            	if(t.getIsSuspend() == 1){
            		continue;
            	}
                if (i == 0) {
                    firstMarketingId = t.getMarketingId();
                }
                map.put("id", t.getMarketingId());
                map.put("pId", t.getGradeId());
                map.put("open", false);
                map.put("name", t.getMarketingTitle());
                map.put("type", "P");
                jsonArray.add(map);
                i++;
            }

            request.setAttribute("taskTree", jsonArray);
            return SUCCESS;
        } catch (Exception e) {
            log.error("selectProduct error", e);
            return ERROR;
        }
    }

    //任务产品列表
    public String productList() {
        try {
        	// 任务对于产品list
            Map<String, Object> parameterMap = new HashMap<String, Object>();
            if (marketingId != null) {
                Integer userId = this.getLoginInfo().getUserId();
                if(!StringUtil.isBlank(gradeId)){
                	if(gradeId.equals("P")){
                		TskMarketing t = tskMarketingService.getTskMarketingById(marketingId);
                        //产品指标
                        String productTarget = tskMarketingPlanDetailService.getProductTarget(t);
                        request.setAttribute("productTarget", productTarget);
                        // 营销目标
                        BigDecimal marketingTarget = tskMarketingPlanDetailService.getMarketingTarget(userId, marketingId);
                        request.setAttribute("marketingTarget", marketingTarget);
                        // 日均剩余营销额=剩余营销目标/剩余日期
                        BigDecimal daysRemainTarget = tskMarketingPlanDetailService.getDaysRemainTarget(userId, marketingTarget, t, null);
                        request.setAttribute("daysRemainTarget", daysRemainTarget);
                        parameterMap.put("productId", t.getProductId());
                        parameterMap.put("subTemplateName", t.getSubTemplateName());
                        parameterMap.put("templateId", t.getTemplateId());
                        parameterMap.put("tskGrade", t.getGradeId());
                        parameterMap.put("marketingId", marketingId);
                	}else if(gradeId.equals("G")){
                		parameterMap.put("gradeId", marketingId);
                	}
                }
                parameterMap.put("userId", userId);
            }
            PageUtil<PdtProduct> list = tskMarketingPlanDetailService.getMarketingPlanProductPage(parameterMap, this.getPage());
            request.setAttribute("dataList", list.getItems());
            return SUCCESS;
        } catch (Exception e) {
            log.error("productList error", e);
            return ERROR;
        }
    }

    // 任务营销计划
    public String planList() {
        try {
            Integer userId = this.getLoginInfo().getUserId();

            // 任务对于产品list
            Map<String, Object> parameterMap = new HashMap<String, Object>();
            isInChargeof = deptFacadeService.isInChargeOfDepartment();
            if (isInChargeof) {
                String userIds = deptFacadeService.getInChargeOfDeptUserIds(userId, 0);
                parameterMap.put("userIds", userIds);
            } else {
                parameterMap.put("userIds", userId);
            }
            parameterMap.put("marketingId", marketingId);

            PageUtil<TskMarketingPlan> list = tskMarketingPlanService.getMarketingPlanPage(
                parameterMap, this.getPage());

            //所有产品数量
            Integer productCount = tskMarketingPlanService.getProductCountByMap(parameterMap);

            request.setAttribute("dataList", list.getItems());
            request.setAttribute("productCount", productCount);

            return SUCCESS;
        } catch (Exception e) {
            log.error("planList error", e);
            return ERROR;
        }
    }

    // 查询任务营销计划
    public String queryPlanList() {
        try {
            Integer userId = this.getLoginInfo().getUserId();

            // 任务对于产品list
            Map<String, Object> parameterMap = new HashMap<String, Object>();
            if (StringUtils.isNotEmpty(selectedUserIds)) {
                parameterMap.put("userIds", selectedUserIds);
            } else {
                isInChargeof = deptFacadeService.isInChargeOfDepartment();
                if (isInChargeof) {
                    String userIds = deptFacadeService.getInChargeOfDeptUserIds(userId, 0);
                    parameterMap.put("userIds", userIds);
                } else {
                    parameterMap.put("userIds", userId);
                }
            }
            parameterMap.put("planDateFrom", planDateFrom);
            parameterMap.put("planDateTo", planDateTo);
            parameterMap.put("marketingId", marketingId);

            PageUtil<TskMarketingPlan> list = tskMarketingPlanService.getMarketingPlanPage(
                parameterMap, this.getPage());

            //所有产品数量
//            Integer productCount = tskMarketingPlanService.getProductCountByMap(parameterMap);

            request.setAttribute("dataList", list.getItems());
//            request.setAttribute("productCount", productCount);

            return SUCCESS;
        } catch (Exception e) {
            log.error("planList error", e);
            return ERROR;
        }
    }

    /**
     * 产品营销计划详情
     * @return
     */
    public String planListDetail() {
        try {
            TskMarketingPlan p = tskMarketingPlanService.getTskMarketingPlanById(planId);
            SysUser user = sysUserService.getSysUserById(p.getExecuteUserId());

            Map<String, Object> parameterMap = new HashMap<String, Object>();
            parameterMap.put("planId", planId);
            List<TskMarketingPlanDetail> dataList = tskMarketingPlanDetailService
                .getMarketingPlanDetail(parameterMap);

            Map<String, BigDecimal> mapToday = new HashMap<String, BigDecimal>();
            for (TskMarketingPlanDetail d : dataList) {
                if (mapToday.get(d.getProductId() + "_" + d.getExecuteUserId()) == null) {
                    // 实际销售额
//                    BigDecimal todaySaleMoney = tskMarketingPlanDetailService.getTodaySaleMoney(d.getProductId(), d.getExecuteUserId());
                	BigDecimal todaySaleMoney = tskMarketingPlanDetailService.getTodaySaleMoney(d.getProductId(), d.getExecuteUserId(),p.getPlanDate());
                	d.setTodaySaleMoney(todaySaleMoney);

                    mapToday.put(d.getProductId() + "_" + d.getExecuteUserId(), todaySaleMoney);
                } else {
                    d.setTodaySaleMoney(mapToday.get(d.getProductId() + "_" + d.getExecuteUserId()));
                }
            }

            request.setAttribute("dataList", dataList);
            request.setAttribute("userName", user.getUserName());
            request.setAttribute("planDate", p.getPlanDate());
            return SUCCESS;
        } catch (Exception e) {
            log.error("planListDetail error", e);
            return ERROR;
        }
    }

    //计划报表关联任务
    public String relaReportMarketing() {
        try {
            //得到关联任务ID
            String executeUserId = request.getParameter("executeUserId");
            String productTarget = request.getParameter("productTarget");
            String startDate = request.getParameter("startDate");
            String endDate = request.getParameter("endDate");
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

            Map<String, Object> parameterMap = new HashMap<String, Object>();
            if (StringUtils.isNotEmpty(executeUserId)) {
                parameterMap.put("executeUserId", Integer.valueOf(executeUserId));
            }
            if (StringUtils.isNotEmpty(productTarget)) {
                parameterMap.put("productTarget", URLDecoder.decode(productTarget, "UTF-8"));
            }
            if (StringUtils.isNotEmpty(startDate)) {
                parameterMap.put("planDateFrom", df.parse(startDate));
            }
            if (StringUtils.isNotEmpty(endDate)) {
                parameterMap.put("planDateTo", df.parse(endDate));
            }

            List<TskMarketing> list = tskMarketingPlanService
                .getReportRelativeMarketing(parameterMap);
            String marketingIds = "";//任务ids
            for (TskMarketing m : list) {
                marketingIds += m.getMarketingId() + ",";
            }
            if (marketingIds.length() > 0) {
                marketingIds = marketingIds.substring(0, marketingIds.length() - 1);
            }

            String inChargeUserIds = "", inChargeUserIdsMark = "", inChargeDepts = "";
            Integer[] inChargeDeptsIntegers = deptFacadeService.getInChargeOfDeptIds();
            if(inChargeDeptsIntegers!=null){
            	for (Integer deptId : inChargeDeptsIntegers) {
                    inChargeDepts += deptId.intValue() + ","; //负责的机构
                }
                if (inChargeDepts.length() > 0) {
                    inChargeDepts = inChargeDepts.substring(0, (inChargeDepts.length() - 1));
                }
            }else{
            	inChargeDepts=getLoginInfo().getDeptId().toString();
            }

            Integer[] inChargeUserIdsIntegers = deptFacadeService.getInChargeOfDeptUserIds();
            if(inChargeUserIdsIntegers!=null){
            	for (Integer userId : inChargeUserIdsIntegers) {
                    inChargeUserIds += userId.intValue() + ","; //负责的机构人员
                    inChargeUserIdsMark += "[" + userId.intValue() + "],";
                }
                if (inChargeUserIds.length() > 0) {
                    inChargeUserIds = inChargeUserIds.substring(0, (inChargeUserIds.length() - 1));
                    inChargeUserIdsMark = inChargeUserIdsMark.substring(0,
                        (inChargeUserIdsMark.length() - 1));
                }
            }else{
            	inChargeUserIds = getLoginInfo().getUserId().toString(); //负责的机构人员
                inChargeUserIdsMark += "[" + getLoginInfo().getUserId() + "]";
            }
            

            Map<String, Object> conds = new HashMap<String, Object>();
            conds.put("userId", this.getLoginInfo().getUserId());
            conds.put("inChargeUserIds", inChargeUserIds);
            conds.put("inChargeUserIdsMark", inChargeUserIdsMark);
            conds.put("inChargeDepts", inChargeDepts);
            conds.put("marketingIds", marketingIds);

            PageUtil<TskMarketingBean> tskMarketingBeanPage = tskMarketingService
                .GetAllTskMarketingPageByConds(conds, this.getPage());
            int count = this.getPage().getTotalRowsAmount();
            request.setAttribute("tskMarketingBeanPage", tskMarketingBeanPage);
            request.setAttribute("count", String.valueOf(count));
            request.setAttribute("executeUserId", executeUserId);
            request.setAttribute("productTarget", productTarget);
            request.setAttribute("startDate", startDate);
            request.setAttribute("endDate", endDate);

            return SUCCESS;
        } catch (Exception e) {
            log.error("allTskMarketingList error", e);
            return ERROR;
        }
    }

    //查询
    public String queryRelaReportMarketing() {
        try {
            //得到关联任务ID
            String executeUserId = request.getParameter("executeUserId");
            String productTarget = request.getParameter("productTarget");
            String startDate = request.getParameter("startDate");
            String endDate = request.getParameter("endDate");
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

            Map<String, Object> parameterMap = new HashMap<String, Object>();
            if (StringUtils.isNotEmpty(executeUserId)) {
                parameterMap.put("executeUserId", Integer.valueOf(executeUserId));
            }
            if (StringUtils.isNotEmpty(productTarget)) {
                parameterMap.put("productTarget", URLDecoder.decode(productTarget, "UTF-8"));
            }
            if (StringUtils.isNotEmpty(startDate)) {
                parameterMap.put("planDateFrom", df.parse(startDate));
            }
            if (StringUtils.isNotEmpty(endDate)) {
                parameterMap.put("planDateTo", df.parse(endDate));
            }

            List<TskMarketing> list = tskMarketingPlanService
                .getReportRelativeMarketing(parameterMap);
            String marketingIds = "";//任务ids
            for (TskMarketing m : list) {
                marketingIds += m.getMarketingId() + ",";
            }
            if (marketingIds.length() > 0) {
                marketingIds = marketingIds.substring(0, marketingIds.length() - 1);
            }

            String inChargeUserIds = "", inChargeUserIdsMark = "", inChargeDepts = "";
            Integer[] inChargeDeptsIntegers = deptFacadeService.getInChargeOfDeptIds();
            for (Integer deptId : inChargeDeptsIntegers) {
                inChargeDepts += deptId.intValue() + ","; //负责的机构
            }
            if (inChargeDepts.length() > 0) {
                inChargeDepts = inChargeDepts.substring(0, (inChargeDepts.length() - 1));
            }

            Integer[] inChargeUserIdsIntegers = deptFacadeService.getInChargeOfDeptUserIds();
            for (Integer userId : inChargeUserIdsIntegers) {
                inChargeUserIds += userId.intValue() + ","; //负责的机构人员
                inChargeUserIdsMark += "[" + userId.intValue() + "],";
            }
            if (inChargeUserIds.length() > 0) {
                inChargeUserIds = inChargeUserIds.substring(0, (inChargeUserIds.length() - 1));
                inChargeUserIdsMark = inChargeUserIdsMark.substring(0,
                    (inChargeUserIdsMark.length() - 1));
            }

            Map<String, Object> conds = new HashMap<String, Object>();
            conds.put("userId", this.getLoginInfo().getUserId());
            conds.put("inChargeUserIds", inChargeUserIds);
            conds.put("inChargeUserIdsMark", inChargeUserIdsMark);
            conds.put("inChargeDepts", inChargeDepts);
            conds.put("marketingIds", marketingIds);

            PageUtil<TskMarketingBean> tskMarketingBeanPage = tskMarketingService
                .GetAllTskMarketingPageByConds(conds, this.getPage());
            int count = this.getPage().getTotalRowsAmount();
            request.setAttribute("tskMarketingBeanPage", tskMarketingBeanPage);
            request.setAttribute("count", String.valueOf(count));
            return SUCCESS;
        } catch (Exception e) {
            log.error("queryRelaReportMarketing error", e);
            return ERROR;
        }
    }

    /**
     * 过滤任务等级
     * @param tList
     * @param gList
     * @return
     */
    private List<TaskGrade> filterGrade(List<TskMarketing> tList, List<TaskGrade> gList) {
        List<TaskGrade> result = new ArrayList<TaskGrade>();
        for (TaskGrade g : gList) {
            for (TskMarketing t : tList) {
                if (g.getMarketingGradeId() == t.getGradeId()) {
                    result.add(g);
                    break;
                }
            }
        }
        return result;
    }
    
    /**
     * 判断产品是否删除
     */
    public void validateProductIsDel(){
    	HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter out = response.getWriter();
            pdtProduct=pdtProductService.getPdtProduct(pdtProduct.getProductId());
            if(pdtProduct!=null){
            	out.print(1);
            }else out.print(0);
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

    /* getter and setter */
    public void setTskMarketingPlanDetailService(TskMarketingPlanDetailService tskMarketingPlanDetailService) {
        this.tskMarketingPlanDetailService = tskMarketingPlanDetailService;
    }

    public Integer[] getPlanDetailId() {
        return planDetailId;
    }

    public void setPlanDetailId(Integer[] planDetailId) {
        this.planDetailId = planDetailId;
    }

    public BigDecimal[] getPlanDetailTarget() {
        return planDetailTarget;
    }

    public void setPlanDetailTarget(BigDecimal[] planDetailTarget) {
        this.planDetailTarget = planDetailTarget;
    }

    public String[] getRemark() {
        return remark;
    }

    public void setRemark(String[] remark) {
        this.remark = remark;
    }

    public void setTaskGradeService(TaskGradeService taskGradeService) {
        this.taskGradeService = taskGradeService;
    }

    public void setTskMarketingService(TskMarketingService tskMarketingService) {
        this.tskMarketingService = tskMarketingService;
    }

    public Integer getMarketingId() {
        return marketingId;
    }

    public void setMarketingId(Integer marketingId) {
        this.marketingId = marketingId;
    }

    public Integer getFirstMarketingId() {
        return firstMarketingId;
    }

    public void setFirstMarketingId(Integer firstMarketingId) {
        this.firstMarketingId = firstMarketingId;
    }

    public Integer[] getProductId() {
        return productId;
    }

    public void setProductId(Integer[] productId) {
        this.productId = productId;
    }

    public void setTskMarketingPlanService(TskMarketingPlanService tskMarketingPlanService) {
        this.tskMarketingPlanService = tskMarketingPlanService;
    }

    public Boolean getIsInChargeof() {
        return isInChargeof;
    }

    public void setIsInChargeof(Boolean isInChargeof) {
        this.isInChargeof = isInChargeof;
    }

    public void setDeptFacadeService(DeptFacadeService deptFacadeService) {
        this.deptFacadeService = deptFacadeService;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    public Integer getDetailId() {
        return detailId;
    }

    public String getSelectedUserIds() {
        return selectedUserIds;
    }

    public void setSelectedUserIds(String selectedUserIds) {
        this.selectedUserIds = selectedUserIds;
    }

    public Date getPlanDateFrom() {
        return planDateFrom;
    }

    public void setPlanDateFrom(Date planDateFrom) {
        this.planDateFrom = planDateFrom;
    }

    public Date getPlanDateTo() {
        return planDateTo;
    }

    public void setPlanDateTo(Date planDateTo) {
        this.planDateTo = planDateTo;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setSysUserService(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}

	public String[] getProductIdMaiketingId() {
		return productIdMaiketingId;
	}

	public void setProductIdMaiketingId(String[] productIdMaiketingId) {
		this.productIdMaiketingId = productIdMaiketingId;
	}

	public PdtProduct getPdtProduct() {
		return pdtProduct;
	}

	public void setPdtProduct(PdtProduct pdtProduct) {
		this.pdtProduct = pdtProduct;
	}

	public void setPdtProductService(PdtProductService pdtProductService) {
		this.pdtProductService = pdtProductService;
	}
}
