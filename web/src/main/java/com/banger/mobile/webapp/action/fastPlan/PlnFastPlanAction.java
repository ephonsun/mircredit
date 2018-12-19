/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :快速规划action
 * Author     :yujh
 * Create Date:Jul 18, 2012
 */
package com.banger.mobile.webapp.action.fastPlan;

import hwano.util.lang.StringUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.cardType.CardType;
import com.banger.mobile.domain.model.customer.CrmCustomer;
import com.banger.mobile.domain.model.customer.CrmCustomerEva;
import com.banger.mobile.domain.model.customer.CusShareUserBean;
import com.banger.mobile.domain.model.dept.SysDept;
import com.banger.mobile.domain.model.plnFastPlan.PlnFastPlan;
import com.banger.mobile.domain.model.plnFastPlan.PlnFastPlanInfo;
import com.banger.mobile.facade.CardType.CardTypeService;
import com.banger.mobile.facade.customer.CrmCustomerService;
import com.banger.mobile.facade.customer.CrmCustomerShareService;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.plnFastPlan.PlnFastPlanService;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * @author yujh
 * @version $Id: PlnFastPlanAction.java,v 0.1 Jul 18, 2012 1:32:45 PM Administrator Exp $
 */
public class PlnFastPlanAction extends BaseAction {

    private static final long         serialVersionUID = 9122921984472785252L;
    private PlnFastPlanService        plnFastPlanService;
    private PlnFastPlanInfo           plnFastPlanInfo;
    private PageUtil<PlnFastPlanInfo> plnFastPlanInfoList;
    private Map<String, Object>       map;
    private Integer                   count;
    private Integer                   type;                                   //客户类型，是否是在行客户
    private CrmCustomerService        crmCustomerService;                     //客户Service
    private PageUtil<CrmCustomerEva>     customerList;                           //客户列表   
    private Integer                   customerId;                             //客户id
    private CrmCustomer               customer;                               //客户实体
    private CardTypeService           cardTypeService;                        //证件类型service
    private List<CardType>            cardTypeList;                           //证件类型list
    private PlnFastPlan               plnFastPlan;                            //快速理财规划实体
    private DeptFacadeService                deptFacadeService;      //机构Service
    private Map<String, Object>       parameterMap=new HashMap<String, Object>();     //客户查询MAP
    private Boolean                   isInChargeof;     //是否是业务主管 
    private CrmCustomerShareService   crmCustomerShareService;//共享客户service
    /**
     * 快速规划列表
     * @return
     */
    public String getAllPlnFastPlanInfo() {
        try {
            Map<String, Object> parameterMap = new HashMap<String, Object>();
            if(plnFastPlanInfo!=null){
                parameterMap.put("customerNo", com.banger.mobile.util.StringUtil.ReplaceSQLChar(plnFastPlanInfo.getCustomerNo().trim()));
                parameterMap.put("customerName", com.banger.mobile.util.StringUtil.ReplaceSQLChar(plnFastPlanInfo.getCustomerName().trim()));
                parameterMap.put("planName", com.banger.mobile.util.StringUtil.ReplaceSQLChar(plnFastPlanInfo.getPlanName().trim()));
            }
           
            parameterMap.put("startDate", com.banger.mobile.util.StringUtil.ReplaceSQLChar(request.getParameter("startTime")));
            parameterMap.put("endDate", com.banger.mobile.util.StringUtil.ReplaceSQLChar(request.getParameter("endTime")));
            
            parameterMap.put("userId", this.getLoginInfo().getUserId());
            request.setAttribute("startTime", request.getParameter("startTime"));
            request.setAttribute("endTime", request.getParameter("endTime"));
            request.setAttribute("customerNo", request.getParameter("customerNumber"));
            request.setAttribute("customerName", request.getParameter("customerName"));
            request.setAttribute("planName", request.getParameter("planName"));
            plnFastPlanInfoList = this.plnFastPlanService.getAllPlnFastPlanInfo(parameterMap,
                this.getPage());
            count = this.getPage().getTotalRowsAmount();
            return SUCCESS;
        } catch (Exception e) {
            //modify by zhangxiang 2012-08-09
            log.error("getAllPlnFastPlanInfo action error:" + e.getMessage());
            return ERROR;
        }
    }
    /**
     * 获得所有理财规划
     * @return
     */
    public String getAllPlnFastPlanInfoForQuery() {
        try {
            Map<String, Object> parameterMap = new HashMap<String, Object>();
            if(plnFastPlanInfo!=null){
                parameterMap.put("customerNo", com.banger.mobile.util.StringUtil.ReplaceSQLChar(plnFastPlanInfo.getCustomerNo().trim()));
                parameterMap.put("customerName", com.banger.mobile.util.StringUtil.ReplaceSQLChar(plnFastPlanInfo.getCustomerName().trim()));
                parameterMap.put("planName", com.banger.mobile.util.StringUtil.ReplaceSQLChar(plnFastPlanInfo.getPlanName().trim()));
            }
           
            parameterMap.put("startDate", com.banger.mobile.util.StringUtil.ReplaceSQLChar(request.getParameter("startTime")));
            parameterMap.put("endDate", com.banger.mobile.util.StringUtil.ReplaceSQLChar(request.getParameter("endTime")));
            
            parameterMap.put("userId", this.getLoginInfo().getUserId());
            request.setAttribute("startTime", request.getParameter("startTime"));
            request.setAttribute("endTime", request.getParameter("endTime"));
            request.setAttribute("customerNo", request.getParameter("customerNumber"));
            request.setAttribute("customerName", request.getParameter("customerName"));
            request.setAttribute("planName", request.getParameter("planName"));
            plnFastPlanInfoList = this.plnFastPlanService.getAllPlnFastPlanInfo(parameterMap,
                this.getPage());
            count = this.getPage().getTotalRowsAmount();
            return SUCCESS;
        } catch (Exception e) {
            //modify by zhangxiang 2012-08-09
            log.error("getAllPlnFastPlanInfo action error:" + e.getMessage());
            return ERROR;
        }
    }

    /**
     * 删除快速规划
     * @return
     */
    public String deletePlnFastPlan() {
        try {
            String id = this.getRequest().getParameter("plnFastPlanId");
            this.plnFastPlanService.deletePlnFastPlan(id);
            return null;
        } catch (Exception e) {
          //modify by zhangxiang 2012-08-09
            log.error("deletePlnFastPlan action error:" + e.getMessage());
            return ERROR;
        }
    }

    /**
     * 选择客户类型
     * @return
     */
    public String chooseCusType() {

        return SUCCESS;
    }

    /**
     * 第二步
     * @return
     */
    public String nextPage() {
        try {
            if (type == 1) {
                return "online";
            } else {
                cardTypeList = this.cardTypeService.getCardTypes();
                return "unline";
            }
        } catch (Exception e) {
            //modify by zhangxiang 2012-08-09
            log.error("nextPage action error:" + e.getMessage());
            return ERROR;
        }
    }

    /**
     * 查询在行客户
     * @return
     */
    public String queryOnlineCustomer() {
        String customerNo=com.banger.mobile.util.StringUtil.ReplaceSQLChar(request.getParameter("customerNo"));
        String customerName=com.banger.mobile.util.StringUtil.ReplaceSQLChar(request.getParameter("customerName"));
        String templateNo =com.banger.mobile.util.StringUtil.ReplaceSQLChar(request.getParameter("templateNo"));
        
        request.setAttribute("customerNo", customerNo);
        request.setAttribute("customerName", customerName);
        request.setAttribute("templateNo", templateNo);
        if(!customerNo.equals(""))
            parameterMap.put("customerNo", customerNo);
        if(!customerName.equals(""))
            parameterMap.put("customerName", customerName);
        if(templateNo!=null)
            parameterMap.put("templateNo", templateNo);
        Page pag=this.getPage();
        pag.setPageSize(5);
        isInChargeof = deptFacadeService.isInChargeOfDepartment();
        beforeQueryEntityList();
        customerList=crmCustomerService.getCustomerPage(parameterMap,pag);
        return SUCCESS;
    }
    /**
     * 查询之前
     */
    private void beforeQueryEntityList(){
        String inChargeOfUserIds = "";
        String inChargeOfDeptIds = "";

            parameterMap.put("isTrash", 0);
            //客户归属
            String belongToType = "brAll";
            if(!StringUtil.isEmpty(belongToType)){
                if(isInChargeof){
                    inChargeOfDeptIds = getCurrentUserInChargeOfDeptIds();
                    if(StringUtil.isEmpty(inChargeOfDeptIds)){
                        inChargeOfDeptIds = "-1";
                    }
                    inChargeOfUserIds = getCurrentInChargeUserIds();
                    belongToType = "brAll";
                    parameterMap.put("BelongTo", belongToType);
                }else{
                    inChargeOfDeptIds = "-1";
                    inChargeOfUserIds = this.getLoginInfo().getUserId().toString();
                    belongToType = "brMine";
                    parameterMap.put("BelongTo", "brMine");
                }
                
                if(belongToType.equals("brAll")){                                       //所有
                    //inChargeOfUserIds = this.getLoginInfo().getUserId().toString();
                    parameterMap.put("ContainsShare", "ContainsShare");
                }else if(belongToType.equals("brMine")){                                //我的
                    inChargeOfUserIds = this.getLoginInfo().getUserId().toString();
                    parameterMap.put("ContainsShare", "ContainsShare");
                    //处理共享给别人的客户
                    parameterMap.put("QueryUserIds", inChargeOfUserIds);
                }else if(belongToType.equals("brUser")){                                //下属
                    inChargeOfUserIds = request.getParameter("userIds");
                    if(StringUtil.isEmpty(inChargeOfUserIds)){                          //没有选择人员的时候
                        parameterMap.put("BelongTo", "brAll");
                        inChargeOfDeptIds = getCurrentUserInChargeOfDeptIds();
                        if(StringUtil.isEmpty(inChargeOfDeptIds)){
                            inChargeOfDeptIds = "-1";
                        }
                        inChargeOfUserIds = this.getLoginInfo().getUserId().toString();
                    }
                    parameterMap.put("ContainsShare", "ContainsShare");
                    //处理共享给别人的客户
                    parameterMap.put("QueryUserIds", inChargeOfUserIds);
                }else if(belongToType.equals("brDept")){                                //机构
                    inChargeOfDeptIds = request.getParameter("deptIds");
                    if(StringUtil.isEmpty(inChargeOfDeptIds)){                          //没有选择机构的时候
                        parameterMap.put("BelongTo", "brAll");
                        inChargeOfDeptIds = getCurrentUserInChargeOfDeptIds();
                        if(StringUtil.isEmpty(inChargeOfDeptIds)){
                            inChargeOfDeptIds = "-1";
                        }
                        inChargeOfUserIds = this.getLoginInfo().getUserId().toString();
                    }
                    String containSub = request.getParameter("containSub");
                    if(!StringUtil.isEmpty(containSub)&&containSub.equals("1")){
                        inChargeOfDeptIds = getContainSubDeptIds(inChargeOfDeptIds);
                    }
                    parameterMap.put("ContainsShare", "ContainsShare");
                    //处理共享给别人的客户
                    parameterMap.put("QueryDeptIds", inChargeOfDeptIds);
                }else if(belongToType.equals("brUnAllocate")){                          //待分配
                    inChargeOfDeptIds = getCurrentUserInChargeOfDeptIds();
                    inChargeOfUserIds = "0";
                    //处理共享给别人的客户
                    parameterMap.put("QueryUserIds", "0");
                    parameterMap.put("QueryDeptIds", inChargeOfDeptIds);
                }
                parameterMap.put("InChargeOfUserIds", inChargeOfUserIds);
                parameterMap.put("InChargeOfDeptIds", inChargeOfDeptIds);
                parameterMap.put("UserId", this.getLoginInfo().getUserId());
            }
            //共享
            String shareFlag = request.getParameter("shareFlag");
            if(!StringUtil.isEmpty(shareFlag)){
                    parameterMap.put("BelongTo", "ShareToMe");
                    parameterMap.put("InChargeOfUserIds", getCurrentInChargeUserIds());
                    parameterMap.put("UserId", this.getLoginInfo().getUserId().toString());
                    
                    List<CusShareUserBean> shareDropUsers = 
                                crmCustomerShareService.selectShareToMeUser(getCurrentInChargeUserIds(), this.getLoginInfo().getUserId().toString());
                    request.setAttribute("shareDropUsers", shareDropUsers);
                    
                    String shareUserId = request.getParameter("shareUserId");
                    if(!StringUtil.isEmpty(shareUserId))
                        parameterMap.put("SharePresentUserId", shareUserId);
            }
    }
    /**
     * 包含本身选中的
     * @param deptids
     * @return 下属机构id集合
     */
    private String getContainSubDeptIds(String deptids){
        List<SysDept> deptList = deptFacadeService.getContainDeptListByDeptIds(deptids);
        String newDeptIds = "";
        for(SysDept dept: deptList){
            if(newDeptIds.equals("")){
                newDeptIds = dept.getDeptId().toString();
            }else{
                newDeptIds = newDeptIds + "," + dept.getDeptId().toString();
            }
        }
        return newDeptIds;
    }
    /**
     * 当前用户有权限的      用户ids
     * @return
     */
    private String getCurrentInChargeUserIds(){
        String userIds = "";
        Integer[] arr = deptFacadeService.getInChargeOfDeptUserIds();
        if(arr!=null){
            for(int i=0; i<arr.length; i++){
                if(userIds.equals(""))
                    userIds = arr[i].toString();
                else
                    userIds = userIds + "," + arr[i];
            }
        }
        if(userIds.equals("")){
            userIds = this.getLoginInfo().getUserId().toString();
        }else{
            userIds = userIds + "," +this.getLoginInfo().getUserId(); 
        }
        return userIds;
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
     * 第三步
     * @return
     */
    public String doFastPlan() {
        try {
            cardTypeList = this.cardTypeService.getCardTypes();
            customer = (CrmCustomer) this.crmCustomerService.getCrmCustomerById(customerId);
            request.setAttribute("flag", 1);
            return SUCCESS;
        } catch (Exception e) {
            //modify by zhangxiang 2012-08-09
            log.error("doFastPlan action error:" + e.getMessage());
            return ERROR;
        }
    }

    /**
     * 新建快速规划
     * @return
     */
    public void addPlnFastPlan() {
        try {
            HttpServletResponse response = ServletActionContext.getResponse();
            PrintWriter out = response.getWriter();
            plnFastPlan.setPlanDate(new Date());
            plnFastPlan.setUserId(this.getLoginInfo().getUserId());
            int callBackId = this.plnFastPlanService.insertFastPlanFromWeb(plnFastPlan);
            out.print(callBackId);
        } catch (Exception e) {
          //modify by zhangxiang 2012-08-09
            log.error("addPlnFastPlan action error:" + e.getMessage());
        }
    }

    /**
     * 编辑页面
     * @return
     */
    public String goToUpdatePage() {
        try {
            cardTypeList = this.cardTypeService.getCardTypes();
            plnFastPlan = this.plnFastPlanService.getPlnFastPlanById(id);
            return SUCCESS;
        } catch (Exception e) {
            //modify by zhangxiang 2012-08-09
            log.error("goToUpdatePage action error:" + e.getMessage());
            return ERROR;
        }
    }

    /**
     * 更新快速理财规划
     */
    public void updateFastPlan() {
        try {
            plnFastPlan.setPlanDate(new Date());
            this.plnFastPlanService.updateFastPlanByWeb(plnFastPlan);
        } catch (Exception e) {
            //modify by zhangxiang 2012-08-09
            log.error("updateFastPlan action error:" + e.getMessage());
        }
    }

    public PlnFastPlanInfo getPlnFastPlanInfo() {
        return plnFastPlanInfo;
    }

    public void setPlnFastPlanInfo(PlnFastPlanInfo plnFastPlanInfo) {
        this.plnFastPlanInfo = plnFastPlanInfo;
    }

    public void setPlnFastPlanService(PlnFastPlanService plnFastPlanService) {
        this.plnFastPlanService = plnFastPlanService;
    }

    public PageUtil<PlnFastPlanInfo> getPlnFastPlanInfoList() {
        return plnFastPlanInfoList;
    }

    public void setPlnFastPlanInfoList(PageUtil<PlnFastPlanInfo> plnFastPlanInfoList) {
        this.plnFastPlanInfoList = plnFastPlanInfoList;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setCrmCustomerService(CrmCustomerService crmCustomerService) {
        this.crmCustomerService = crmCustomerService;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public CrmCustomer getCustomer() {
        return customer;
    }

    public void setCustomer(CrmCustomer customer) {
        this.customer = customer;
    }

    public void setCardTypeService(CardTypeService cardTypeService) {
        this.cardTypeService = cardTypeService;
    }

    public List<CardType> getCardTypeList() {
        return cardTypeList;
    }

    public void setCardTypeList(List<CardType> cardTypeList) {
        this.cardTypeList = cardTypeList;
    }

    public PlnFastPlan getPlnFastPlan() {
        return plnFastPlan;
    }

    public void setPlnFastPlan(PlnFastPlan plnFastPlan) {
        this.plnFastPlan = plnFastPlan;
    }

    public PageUtil<CrmCustomerEva> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(PageUtil<CrmCustomerEva> customerList) {
        this.customerList = customerList;
    }

    public Map<String, Object> getParameterMap() {
        return parameterMap;
    }

    public void setParameterMap(Map<String, Object> parameterMap) {
        this.parameterMap = parameterMap;
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

    public void setCrmCustomerShareService(CrmCustomerShareService crmCustomerShareService) {
        this.crmCustomerShareService = crmCustomerShareService;
    }
    
}
