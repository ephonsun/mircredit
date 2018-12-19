/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :录音操作Action
 * Author     :xuhj
 * Version    :1.0
 * Create Date:May 3, 2012
 */
package com.banger.mobile.webapp.action.customer;

import hwano.util.lang.StringUtil;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.Enum.customer.EnumCustomer;
import com.banger.mobile.domain.model.customer.CrmCustomer;
import com.banger.mobile.domain.model.customer.MayDiffCustomerName;
import com.banger.mobile.domain.model.fieldCodeData.CrmFieldCodeData;
import com.banger.mobile.domain.model.system.CrmCustomerType;
import com.banger.mobile.domain.model.template.CrmTemplate;
import com.banger.mobile.domain.model.templateField.CrmTemplateField;
import com.banger.mobile.domain.model.user.IUserInfo;
import com.banger.mobile.facade.customer.CrmCustomerService;
import com.banger.mobile.facade.customer.MayDiffCustomerService;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.loan.LnLoanService;
import com.banger.mobile.facade.log.OpeventLogService;
import com.banger.mobile.facade.log.OpeventLoginLogService;
import com.banger.mobile.facade.microTask.TskScheduleService;
import com.banger.mobile.facade.system.CdSystemService;
import com.banger.mobile.facade.system.CrmCustomerTypeService;
import com.banger.mobile.facade.template.CrmTemplateService;
import com.banger.mobile.facade.templateField.CrmTemplateFieldService;
import com.banger.mobile.util.DateUtil;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * 
 * @author yuanme
 * @version MayDiffCustomerAction.java,v 0.1 2012-8-16 下午3:21:40
 */
public class MergeCustomerAction extends BaseAction {
    private static final long             serialVersionUID = 1571949684153996822L;

    private DeptFacadeService             deptFacadeService;                      //机构Service
    private OpeventLoginLogService        opeventLoginLogService;
    private OpeventLogService             opeventLogService;                      //操作日志service
    private CrmCustomerTypeService        crmCustomerTypeService;                 //客户类型Service  
    private CrmCustomerService            crmCustomerService;                     //客户Service
    private MayDiffCustomerService        mayDiffCustomerService;                 //客户Service  
    private LnLoanService                 lnLoanService;                          //贷款service
    private TskScheduleService            tskScheduleService;                     //日程service
    private CdSystemService               codetableService;                       //代码表
    private PageUtil<MayDiffCustomerName> mayDiffCustomerNameList;                //客户列表  
    private List<CrmCustomer>             mayDiffCustomerList;                    //客户列表  
    private List<CrmCustomerType>         cusTypelist;                            //客户类型集合
    private Map<String, Object>           parameterMap;                           //客户查询MAP
    private Boolean                       isInChargeof;                           //是否是业务主管
    private String                        customerIds;
    private CrmTemplateFieldService       crmTemplateFieldService;
    private CrmTemplateService            crmTemplateService;
    private List<CrmTemplateField>        fieldList1;
    private Map<String, Object>           fieldValueMap1;
    private List<CrmTemplateField>        fieldList2;
    private Map<String, Object>           fieldValueMap2;
    private List<CrmTemplateField>        fieldList2Diff;
    private Map<String, String>           diffColorMap;
    private Map<String, Object>           fieldValueMap3;
    private CrmCustomer                   customer1;
    private CrmCustomer                   customer2;
    private String                        provinceName1;
    private String                        provinceName2;
    private String                        cityName1;
    private String                        cityName2;
    private CrmCustomer                   crmCustomer;
    private int                           recNum1;
    private int                           recNum2;
    private int                           recNum3;
    private int                           tskNum1;
    private int                           tskNum2;
    private int                           tskNum3;
    private int                           relNum1;
    private int                           relNum2;
    private int                           relNum3;
    private int                           pdtNum1;
    private int                           pdtNum2;
    private int                           pdtNum3;
    private Integer                       currentUserId;                          //当前登录用户id
    private String                        actionType;                             //新增、保存并关闭、保存并继续 
    
    private int                           loanNum1;
    private int                           loanNum2;
    private int                           loanNum3;
    private int                           scheduleNum1;
    private int                           scheduleNum2;
    private int                           scheduleNum3;

    /**
     * 构造函数
     */
    public MergeCustomerAction() {
        this.crmCustomer = new CrmCustomer();
        this.parameterMap = new HashMap<String, Object>();
    }

    /**
     * 合并客户
     * @return
     */
    public String mergeCustomer() {
        try {
            // 合并后客户自定义字段
            Map<String, Object> extFiledMap = new HashMap<String, Object>();
            for (Iterator iter = request.getParameterMap().entrySet().iterator(); iter.hasNext();) {
                Entry element = (Entry) iter.next();
                String key = (String) element.getKey();
                String[] value = (String[]) element.getValue();

                if (key.contains("cusFiled_")) {
                    if (key.split("cusFiled_").length > 1 && value.length > 0) {
                        extFiledMap.put(key.split("cusFiled_")[1], value[0]);
                    }
                }
            }

            // 合并客户
            if (customer1 != null && customer2 != null && crmCustomer != null) {
                IUserInfo user = getLoginInfo();
                if (user != null) {
                    crmCustomer.setUpdateUser(user.getUserId());
                }

                //设置拼音
                if (crmCustomer.getCustomerName() != null) {
                    crmCustomer.setCustomerNamePinyin(crmCustomerService
                        .getPinYinHeadChar(crmCustomer.getCustomerName()));
                }

                // 设置最近联系时间
                if (crmCustomer.getLastContactDateStr() != null
                    && crmCustomer.getLastContactDateStr().length() > 0) {
                    crmCustomer.setLastContactDate(DateUtil.strToDate(
                        crmCustomer.getLastContactDateStr(), "yyyy-MM-dd HH:mm:ss"));
                }

                // 手机号码规则

                mayDiffCustomerService
                    .mergeCustomer(customer1, customer2, crmCustomer, extFiledMap);
            }

            // 日志
            opeventLoginLogService.addLog(1, EnumCustomer.MODEL_ACTION_MERGE.getValue(), 1, 1);

            // 返回结果json
            this.getResponse().setContentType("text/html;charset=utf-8");
            PrintWriter out = this.getResponse().getWriter();
            out.write("1");
            return null;
        } catch (Exception e) {
            log.error("合并客户... ", e);
            opeventLoginLogService.addLog(1, EnumCustomer.MODEL_ACTION_MERGE.getValue(), 1, 0);
            return null;
        }
    }

    public String checkMergeCustomer(){
        String customerIds = request.getParameter("customerIds");
        String noLoanCusIds = lnLoanService.getNoLoanUserList(customerIds);
        String noScheduleCusIds = tskScheduleService.getNoScheduleCusIds(customerIds);
        boolean hasLoan = false;            //选择的客户是否有贷款
        boolean hasSchedule = false;        //选择的客户是否有日程
        
        if(noLoanCusIds==null||!noLoanCusIds.equals(customerIds)){
            hasLoan = true;
        }
        if(noScheduleCusIds==null||!noScheduleCusIds.equals(customerIds)){
            hasSchedule = true;
        }
        String backMsg = "您确定这两条记录是同一个客户吗？合并后数据不能够恢复，请谨慎操作!";
        if(hasLoan&&!hasSchedule){
            backMsg = "客户有关联的贷款信息，确定要合并客户吗？合并后贷款信息将与合并后客户关联!";
        }
        if(hasSchedule&&!hasLoan){
            backMsg = "客户有关联的日程安排，确定要合并客户吗？合并后日程信息将与合并后客户关联!";
        }
        if(hasLoan&&hasSchedule){
            backMsg = "客户有关联的贷款信息和日程安排，确定要合并客户吗？合并后贷款和日程将与合并后客户关联!";
        }
        if(!hasLoan&&!hasSchedule){
            backMsg = "您确定这两条记录是同一个客户吗？合并后数据不能够恢复，请谨慎操作!";
        }
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");
        try {
            PrintWriter out = response.getWriter();
            out.print(backMsg);
        } catch (Exception e) {
        }
        return null;
    }
    
    /**
     * 取得相同客户列表
     * @return
     */
    public String getMergeCustomerList() {
        try {
            isInChargeof = deptFacadeService.isInChargeOfDepartment();

            if (customerIds != null && customerIds.length() > 0) {
                String[] idsArray = customerIds.split(",");
                if (idsArray.length >= 2) {
                    customer1 = crmCustomerService.getCustomerInfo(Integer.valueOf(idsArray[0]));
                    customer2 = crmCustomerService.getCustomerInfo(Integer.valueOf(idsArray[1]));
                    crmCustomer = new CrmCustomer();
                    diffColorMap = new HashMap<String, String>();
                    // 归属机构
                    String temp1 = getNotNullValue(customer1.getBelongDeptId());
                    String temp2 = getNotNullValue(customer2.getBelongDeptId());
                    if (!temp1.equals(temp2)) {
                        if (!temp1.equals("") && temp2.equals("")) {
                            crmCustomer.setBelongDeptId(customer1.getBelongDeptId());
                            crmCustomer.setDeptName(customer1.getDeptName());
                        } else if (temp1.equals("") && !temp2.equals("")) {
                            crmCustomer.setBelongDeptId(customer2.getBelongDeptId());
                            crmCustomer.setDeptName(customer2.getDeptName());
                        } else {
                            crmCustomer.setBelongDeptId(0);
                        }
                        if (!temp1.equals("") && !temp2.equals("")) {
                            diffColorMap.put("BelongUserId", "bgcolor");
                        }
                    } else {
                        crmCustomer.setBelongDeptId(customer1.getBelongDeptId());
                        crmCustomer.setDeptName(customer1.getDeptName());
                    }

                    // 人员
                    temp1 = getNotNullValue(customer1.getBelongUserId());
                    temp2 = getNotNullValue(customer2.getBelongUserId());
                    if (!temp1.equals(temp2)) {
                        if (!temp1.equals("") && temp2.equals("")) {
                            crmCustomer.setBelongUserId(customer1.getBelongUserId());
                            crmCustomer.setUserName(customer1.getUserName());
                        } else if (temp1.equals("") && !temp2.equals("")) {
                            crmCustomer.setBelongUserId(customer2.getBelongUserId());
                            crmCustomer.setUserName(customer2.getUserName());
                        }

                        if (!temp1.equals("") && !temp2.equals("")) {
                            diffColorMap.put("BelongUserId", "bgcolor");
                        }
                    } else {
                        crmCustomer.setBelongUserId(customer1.getBelongUserId());
                        crmCustomer.setUserName(customer1.getUserName());
                    }
                    currentUserId = this.getLoginInfo().getUserId();
                    parameterMap.put("currentUserId", currentUserId);
                    parameterMap.put("currentUserName", this.getLoginInfo().getUserName());
                    parameterMap.put("currentDeptId", this.getLoginInfo().getDeptId());
                    parameterMap.put("currentDeptName", this.getLoginInfo().getDeptname());
                    String belongTo = getCustomerBelongTo(crmCustomer);
                    parameterMap.put("BelongTo", belongTo);
                    actionType = "modify";

                    // 姓名
                    temp1 = getNotNullValue(customer1.getCustomerName());
                    temp2 = getNotNullValue(customer2.getCustomerName());
                    if (!temp1.equals(temp2)) {
                        if (!temp1.equals("") && temp2.equals("")) {
                            crmCustomer.setCustomerName(customer1.getCustomerName());
                        } else if (temp1.equals("") && !temp2.equals("")) {
                            crmCustomer.setCustomerName(customer2.getCustomerName());
                        }

                        if (!temp1.equals("") && !temp2.equals("")) {
                            diffColorMap.put("CustomerName", "bgcolor");
                        }
                    } else {
                        crmCustomer.setCustomerName(customer1.getCustomerName());
                    }

                    // 性别
                    temp1 = getNotNullValue(customer1.getSex());
                    temp2 = getNotNullValue(customer2.getSex());
                    if (!temp1.equals(temp2)) {
                        if (!temp1.equals("") && temp2.equals("")) {
                            crmCustomer.setSex(customer1.getSex());
                        } else if (temp1.equals("") && !temp2.equals("")) {
                            crmCustomer.setSex(customer2.getSex());
                        }

                        if (!temp1.equals("") && !temp2.equals("")) {
                            diffColorMap.put("Sex", "bgcolor");
                        }
                    } else {
                        crmCustomer.setSex(customer1.getSex());
                    }

                    // 称谓
                    temp1 = getNotNullValue(customer1.getCustomerTitle());
                    temp2 = getNotNullValue(customer2.getCustomerTitle());
                    if (!temp1.equals(temp2)) {
                        if (!temp1.equals("") && temp2.equals("")) {
                            crmCustomer.setCustomerTitle(customer1.getCustomerTitle());
                        } else if (temp1.equals("") && !temp2.equals("")) {
                            crmCustomer.setCustomerTitle(customer2.getCustomerTitle());
                        }

                        if (!temp1.equals("") && !temp2.equals("")) {
                            diffColorMap.put("CustomerTitle", "bgcolor");
                        }
                    } else {
                        crmCustomer.setCustomerTitle(customer1.getCustomerTitle());
                    }

                    // 客户编号
                    temp1 = getNotNullValue(customer1.getCustomerNo());
                    temp2 = getNotNullValue(customer2.getCustomerNo());
                    if (!temp1.equals(temp2)) {
                        if (!temp1.equals("") && temp2.equals("")) {
                            crmCustomer.setCustomerNo(customer1.getCustomerNo());
                        } else if (temp1.equals("") && !temp2.equals("")) {
                            crmCustomer.setCustomerNo(customer2.getCustomerNo());
                        }

                        if (!temp1.equals("") && !temp2.equals("")) {
                            diffColorMap.put("CustomerNo", "bgcolor");
                        }
                    } else {
                        crmCustomer.setCustomerNo(customer1.getCustomerNo());
                    }

                    // 客户类型
                    temp1 = getNotNullValue(customer1.getCustomerTypeName());
                    temp2 = getNotNullValue(customer2.getCustomerTypeName());
                    if (!temp1.equals(temp2)) {
                        if (!temp1.equals("") && temp2.equals("")) {
                            crmCustomer.setCustomerTypeName(customer1.getCustomerTypeName());
                            crmCustomer.setCustomerTypeId(customer1.getCustomerTypeId());
                        } else if (temp1.equals("") && !temp2.equals("")) {
                            crmCustomer.setCustomerTypeName(customer2.getCustomerTypeName());
                            crmCustomer.setCustomerTypeId(customer2.getCustomerTypeId());
                        }

                        if (!temp1.equals("") && !temp2.equals("")) {
                            diffColorMap.put("CustomerTypeName", "bgcolor");
                        }
                    } else {
                        crmCustomer.setCustomerTypeName(customer1.getCustomerTypeName());
                        crmCustomer.setCustomerTypeId(customer1.getCustomerTypeId());
                    }

                    // 所处行业
                    temp1 = getNotNullValue(customer1.getCustomerIndustryName());
                    temp2 = getNotNullValue(customer2.getCustomerIndustryName());
                    if (!temp1.equals(temp2)) {
                        if (!temp1.equals("") && temp2.equals("")) {
                            crmCustomer
                                .setCustomerIndustryName(customer1.getCustomerIndustryName());
                            crmCustomer.setCustomerIndustryId(customer1.getCustomerIndustryId());
                        } else if (temp1.equals("") && !temp2.equals("")) {
                            crmCustomer
                                .setCustomerIndustryName(customer2.getCustomerIndustryName());
                            crmCustomer.setCustomerIndustryId(customer2.getCustomerIndustryId());
                        }

                        if (!temp1.equals("") && !temp2.equals("")) {
                            diffColorMap.put("CustomerIndustryName", "bgcolor");
                        }
                    } else {
                        crmCustomer.setCustomerIndustryName(customer1.getCustomerIndustryName());
                        crmCustomer.setCustomerIndustryId(customer1.getCustomerIndustryId());
                    }

                    // 身份证
                    temp1 = getNotNullValue(customer1.getIdCard());
                    temp2 = getNotNullValue(customer2.getIdCard());
                    if (!temp1.equals(temp2)) {
                        if (!temp1.equals("") && temp2.equals("")) {
                            crmCustomer.setIdCard(customer1.getIdCard());
                        } else if (temp1.equals("") && !temp2.equals("")) {
                            crmCustomer.setIdCard(customer2.getIdCard());
                        }

                        if (!temp1.equals("") && !temp2.equals("")) {
                            diffColorMap.put("IdCard", "bgcolor");
                        }
                    } else {
                        crmCustomer.setIdCard(customer1.getIdCard());
                    }

                    // 生日
                    temp1 = getNotNullValue(customer1.getBirthday());
                    temp2 = getNotNullValue(customer2.getBirthday());
                    if (!temp1.equals(temp2)) {
                        if (!temp1.equals("") && temp2.equals("")) {
                            crmCustomer.setBirthday(customer1.getBirthday());
                        } else if (temp1.equals("") && !temp2.equals("")) {
                            crmCustomer.setBirthday(customer2.getBirthday());
                        }

                        if (!temp1.equals("") && !temp2.equals("")) {
                            diffColorMap.put("Birthday", "bgcolor");
                        }
                    } else {
                        crmCustomer.setBirthday(customer1.getBirthday());
                    }

                    // 单位 
                    temp1 = getNotNullValue(customer1.getCompany());
                    temp2 = getNotNullValue(customer2.getCompany());
                    if (!temp1.equals(temp2)) {
                        if (!temp1.equals("") && temp2.equals("")) {
                            crmCustomer.setCompany(customer1.getCompany());
                        } else if (temp1.equals("") && !temp2.equals("")) {
                            crmCustomer.setCompany(customer2.getCompany());
                        }

                        if (!temp1.equals("") && !temp2.equals("")) {
                            diffColorMap.put("Company", "bgcolor");
                        }
                    } else {
                        crmCustomer.setCompany(customer1.getCompany());
                    }

                    // 年龄  
                    temp1 = getNotNullValue(customer1.getAge());
                    temp2 = getNotNullValue(customer2.getAge());
                    if (!temp1.equals(temp2)) {
                        if (!temp1.equals("") && temp2.equals("")) {
                            crmCustomer.setAge(customer1.getAge());
                        } else if (temp1.equals("") && !temp2.equals("")) {
                            crmCustomer.setAge(customer2.getAge());
                        }

                        if (!temp1.equals("") && !temp2.equals("")) {
                            diffColorMap.put("Age", "bgcolor");
                        }
                    } else {
                        crmCustomer.setAge(customer1.getAge());
                    }

                    // 客户简介
                    temp1 = getNotNullValue(customer1.getRemark());
                    temp2 = getNotNullValue(customer2.getRemark());
                    if (!temp1.equals(temp2)) {
                        if (!temp1.equals("") && temp2.equals("")) {
                            crmCustomer.setRemark(customer1.getRemark());
                        } else if (temp1.equals("") && !temp2.equals("")) {
                            crmCustomer.setRemark(customer2.getRemark());
                        }

                        if (!temp1.equals("") && !temp2.equals("")) {
                            diffColorMap.put("Remark", "bgcolor");
                        }
                    } else {
                        crmCustomer.setRemark(customer1.getRemark());
                    }

                    // 省份
                    temp1 = getNotNullValue(customer1.getProvince());
                    temp2 = getNotNullValue(customer2.getProvince());
                    if (!temp1.equals(temp2)) {
                        if (!temp1.equals("") && temp2.equals("")) {
                            crmCustomer.setProvince(customer1.getProvince());
                        } else if (temp1.equals("") && !temp2.equals("")) {
                            crmCustomer.setProvince(customer2.getProvince());
                        }

                        if (!temp1.equals("") && !temp2.equals("")) {
                            diffColorMap.put("Province", "bgcolor");
                        }
                    } else {
                        crmCustomer.setProvince(customer1.getProvince());
                    }
                    if (temp1.length() > 0) {
                        provinceName1 = codetableService.getProvinceName(customer1.getProvince());
                    }
                    if (temp2.length() > 0) {
                        provinceName2 = codetableService.getProvinceName(customer2.getProvince());
                    }

                    // 城市
                    temp1 = getNotNullValue(customer1.getCity());
                    temp2 = getNotNullValue(customer2.getCity());
                    if (!temp1.equals(temp2)) {
                        if (!temp1.equals("") && temp2.equals("")) {
                            crmCustomer.setCity(customer1.getCity());
                        } else if (temp1.equals("") && !temp2.equals("")) {
                            crmCustomer.setCity(customer2.getCity());
                        }

                        if (!temp1.equals("") && !temp2.equals("")) {
                            diffColorMap.put("City", "bgcolor");
                        }
                    } else {
                        crmCustomer.setCity(customer1.getCity());
                    }
                    if (temp1.length() > 0) {
                        cityName1 = codetableService.getCityName(customer1.getCity());
                    }
                    if (temp2.length() > 0) {
                        cityName2 = codetableService.getCityName(customer2.getCity());
                    }

                    // 联系地址 
                    temp1 = getNotNullValue(customer1.getAddress());
                    temp2 = getNotNullValue(customer2.getAddress());
                    if (!temp1.equals(temp2)) {
                        if (!temp1.equals("") && temp2.equals("")) {
                            crmCustomer.setAddress(customer1.getAddress());
                        } else if (temp1.equals("") && !temp2.equals("")) {
                            crmCustomer.setAddress(customer2.getAddress());
                        }

                        if (!temp1.equals("") && !temp2.equals("")) {
                            diffColorMap.put("Address", "bgcolor");
                        }
                    } else {
                        crmCustomer.setAddress(customer1.getAddress());
                    }

                    // 默认号码
                    temp1 = getNotNullValue(customer1.getDefaultPhoneType());
                    temp2 = getNotNullValue(customer2.getDefaultPhoneType());
                    crmCustomer.setDefaultPhoneType(0);
                    if (!temp1.equals(temp2)) {
                        if (!temp1.equals("") && temp2.equals("")) {
                            crmCustomer.setDefaultPhoneType(customer1.getDefaultPhoneType());
                        } else if (temp1.equals("") && !temp2.equals("")) {
                            crmCustomer.setDefaultPhoneType(customer2.getDefaultPhoneType());
                        }

                        if (!temp1.equals("") && !temp2.equals("")) {
                            diffColorMap.put("DefaultPhoneType", "bgcolor");
                        }
                    } else {
                        crmCustomer.setDefaultPhoneType(customer1.getDefaultPhoneType());
                    }

                    // 手机一
                    temp1 = getNotNullValue(customer1.getMobilePhone1());
                    temp2 = getNotNullValue(customer2.getMobilePhone1());
                    if (!temp1.equals(temp2)) {
                        if (!temp1.equals("") && temp2.equals("")) {
                            crmCustomer.setMobilePhone1(customer1.getMobilePhone1());
                        } else if (temp1.equals("") && !temp2.equals("")) {
                            crmCustomer.setMobilePhone1(customer2.getMobilePhone1());
                        }

                        if (!temp1.equals("") && !temp2.equals("")) {
                            diffColorMap.put("MobilePhone1", "bgcolor");
                        }
                    } else {
                        crmCustomer.setMobilePhone1(customer1.getMobilePhone1());
                    }

                    // 手机一备注
                    temp1 = getNotNullValue(customer1.getMobilePhone1Remark());
                    temp2 = getNotNullValue(customer2.getMobilePhone1Remark());
                    if (!temp1.equals(temp2)) {
                        if (!temp1.equals("") && temp2.equals("")) {
                            crmCustomer.setMobilePhone1Remark(customer1.getMobilePhone1Remark());
                        } else if (temp1.equals("") && !temp2.equals("")) {
                            crmCustomer.setMobilePhone1Remark(customer2.getMobilePhone1Remark());
                        }

                        if (!temp1.equals("") && !temp2.equals("")) {
                            diffColorMap.put("MobilePhone1Remark", "bgcolor");
                        }
                    } else {
                        crmCustomer.setMobilePhone1Remark(customer1.getMobilePhone1Remark());
                    }

                    // 手机二
                    temp1 = getNotNullValue(customer1.getMobilePhone2());
                    temp2 = getNotNullValue(customer2.getMobilePhone2());
                    if (!temp1.equals(temp2)) {
                        if (!temp1.equals("") && temp2.equals("")) {
                            crmCustomer.setMobilePhone2(customer1.getMobilePhone2());
                        } else if (temp1.equals("") && !temp2.equals("")) {
                            crmCustomer.setMobilePhone2(customer2.getMobilePhone2());
                        }

                        if (!temp1.equals("") && !temp2.equals("")) {
                            diffColorMap.put("MobilePhone2", "bgcolor");
                        }
                    } else {
                        crmCustomer.setMobilePhone2(customer1.getMobilePhone2());
                    }

                    // 手机二备注
                    temp1 = getNotNullValue(customer1.getMobilePhone2Remark());
                    temp2 = getNotNullValue(customer2.getMobilePhone2Remark());
                    if (!temp1.equals(temp2)) {
                        if (!temp1.equals("") && temp2.equals("")) {
                            crmCustomer.setMobilePhone2Remark(customer1.getMobilePhone2Remark());
                        } else if (temp1.equals("") && !temp2.equals("")) {
                            crmCustomer.setMobilePhone2Remark(customer2.getMobilePhone2Remark());
                        }

                        if (!temp1.equals("") && !temp2.equals("")) {
                            diffColorMap.put("MobilePhone2Remark", "bgcolor");
                        }
                    } else {
                        crmCustomer.setMobilePhone2Remark(customer1.getMobilePhone2Remark());
                    }

                    // 固话
                    temp1 = getNotNullValue(customer1.getPhone());
                    temp2 = getNotNullValue(customer2.getPhone());
                    if (!temp1.equals(temp2)) {
                        if (!temp1.equals("") && temp2.equals("")) {
                            crmCustomer.setPhone(customer1.getPhone());
                        } else if (temp1.equals("") && !temp2.equals("")) {
                            crmCustomer.setPhone(customer2.getPhone());
                        }

                        if (!temp1.equals("") && !temp2.equals("")) {
                            diffColorMap.put("Phone", "bgcolor");
                        }
                    } else {
                        crmCustomer.setPhone(customer1.getPhone());
                    }

                    // 固话分机
                    temp1 = getNotNullValue(customer1.getPhoneExt());
                    temp2 = getNotNullValue(customer2.getPhoneExt());
                    if (!temp1.equals(temp2)) {
                        if (!temp1.equals("") && temp2.equals("")) {
                            crmCustomer.setPhoneExt(customer1.getPhoneExt());
                        } else if (temp1.equals("") && !temp2.equals("")) {
                            crmCustomer.setPhoneExt(customer2.getPhoneExt());
                        }

                        if (!temp1.equals("") && !temp2.equals("")) {
                            diffColorMap.put("PhoneExt", "bgcolor");
                        }
                    } else {
                        crmCustomer.setPhoneExt(customer1.getPhoneExt());
                    }

                    // 传真
                    temp1 = getNotNullValue(customer1.getFax());
                    temp2 = getNotNullValue(customer2.getFax());
                    if (!temp1.equals(temp2)) {
                        if (!temp1.equals("") && temp2.equals("")) {
                            crmCustomer.setFax(customer1.getFax());
                        } else if (temp1.equals("") && !temp2.equals("")) {
                            crmCustomer.setFax(customer2.getFax());
                        }

                        if (!temp1.equals("") && !temp2.equals("")) {
                            diffColorMap.put("Fax", "bgcolor");
                        }
                    } else {
                        crmCustomer.setFax(customer1.getFax());
                    }

                    // 传真分机
                    temp1 = getNotNullValue(customer1.getFaxExt());
                    temp2 = getNotNullValue(customer2.getFaxExt());
                    if (!temp1.equals(temp2)) {
                        if (!temp1.equals("") && temp2.equals("")) {
                            crmCustomer.setFaxExt(customer1.getFaxExt());
                        } else if (temp1.equals("") && !temp2.equals("")) {
                            crmCustomer.setFaxExt(customer2.getFaxExt());
                        }

                        if (!temp1.equals("") && !temp2.equals("")) {
                            diffColorMap.put("FaxExt", "bgcolor");
                        }
                    } else {
                        crmCustomer.setFaxExt(customer1.getFaxExt());
                    }

                    // 处理如果默认号码类型相同，但是号码不同的情况
                    if (crmCustomer.getDefaultPhoneType() == 1) {
                        if (crmCustomer.getMobilePhone1() == null
                            || crmCustomer.getMobilePhone1().length() <= 0) {
                            crmCustomer.setDefaultPhoneType(0);
                        }
                    }
                    if (crmCustomer.getDefaultPhoneType() == 2) {
                        if (crmCustomer.getMobilePhone2() == null
                            || crmCustomer.getMobilePhone2().length() <= 0) {
                            crmCustomer.setDefaultPhoneType(0);
                        }
                    }
                    if (crmCustomer.getDefaultPhoneType() == 3) {
                        if (crmCustomer.getPhone() == null || crmCustomer.getPhone().length() <= 0) {
                            crmCustomer.setDefaultPhoneType(0);
                        }
                    }
                    if (crmCustomer.getDefaultPhoneType() == 4) {
                        if (crmCustomer.getFax() == null || crmCustomer.getFax().length() <= 0) {
                            crmCustomer.setDefaultPhoneType(0);
                        }
                    }

                    // 邮箱
                    temp1 = getNotNullValue(customer1.getEmail());
                    temp2 = getNotNullValue(customer2.getEmail());
                    if (!temp1.equals(temp2)) {
                        if (!temp1.equals("") && temp2.equals("")) {
                            crmCustomer.setEmail(customer1.getEmail());
                        } else if (temp1.equals("") && !temp2.equals("")) {
                            crmCustomer.setEmail(customer2.getEmail());
                        }

                        if (!temp1.equals("") && !temp2.equals("")) {
                            diffColorMap.put("Email", "bgcolor");
                        }
                    } else {
                        crmCustomer.setEmail(customer1.getEmail());
                    }

                    // 最近联系时间
                    temp1 = getDateNotNullValue(customer1.getLastContactDate());
                    temp2 = getDateNotNullValue(customer2.getLastContactDate());
                    if (!temp1.equals(temp2)) {
                        if (temp1.compareTo(temp2) > 0) {
                            crmCustomer.setLastContactDate(customer1.getLastContactDate());
                            crmCustomer.setLastContactDateStr(temp1);
                        } else if (temp1.equals("") && !temp2.equals("")) {
                            crmCustomer.setLastContactDate(customer2.getLastContactDate());
                            crmCustomer.setLastContactDateStr(temp2);
                        }

                        if (!temp1.equals("") && !temp2.equals("")) {
                            diffColorMap.put("LastContactDate", "bgcolor");
                        }
                    } else {
                        crmCustomer.setLastContactDate(customer1.getLastContactDate());
                        crmCustomer.setLastContactDateStr(temp1);
                    }

                    // 是否愿意接收短信/彩信
                    temp1 = getNotNullValue(customer1.getIsReceiveSms());
                    temp2 = getNotNullValue(customer2.getIsReceiveSms());
                    if (!temp1.equals(temp2)) {
                        if (!temp1.equals("") && temp2.equals("")) {
                            crmCustomer.setIsReceiveSms(customer1.getIsReceiveSms());
                        } else if (temp1.equals("") && !temp2.equals("")) {
                            crmCustomer.setIsReceiveSms(customer2.getIsReceiveSms());
                        }

                        if (!temp1.equals("") && !temp2.equals("")) {
                            diffColorMap.put("IsReceiveSms", "bgcolor");
                        }
                    } else {
                        crmCustomer.setIsReceiveSms(customer1.getIsReceiveSms());
                    }

                    Map<String, List<CrmFieldCodeData>> cMap = mayDiffCustomerService
                        .getTemplateFieldCodes();
                    //客户1所有自定义模版
                    fieldList1 = new ArrayList<CrmTemplateField>();
                    fieldValueMap1 = new HashMap<String, Object>();
                    Map<String, Object> fieldTypeMap = new HashMap<String, Object>();
                    String tmpIds1 = customer1.getTemplateIds();
                    if (tmpIds1 != null && tmpIds1.length() > 0) {
                        tmpIds1 = tmpIds1.trim();
                    } else {
                        tmpIds1 = "1";
                    }
                    String[] tmpIds1Array = tmpIds1.split(",");
                    for (String tmpId : tmpIds1Array) {
                        if (StringUtil.isEmpty(tmpId)) {
                            continue;
                        }
                        List<CrmTemplateField> fieldList = crmTemplateFieldService
                            .getCrmTemplateFieldListByTemplateId(Integer.valueOf(tmpId));
                        fieldList1.addAll(fieldList);
                        fieldValueMap1.putAll(mayDiffCustomerService.getFieldValue(
                            customer1.getCustomerId(), fieldList).get(0));
                        fieldTypeMap.putAll(mayDiffCustomerService.getFieldValue(
                            customer1.getCustomerId(), fieldList).get(1));
                    }
                    for (CrmTemplateField field : fieldList1) {
                        if (field.getTemplateFieldType().equalsIgnoreCase("Select")
                            || field.getTemplateFieldType().equalsIgnoreCase("MultipleSelect")) {
                            String fId = String.valueOf(field.getTemplateFieldId());
                            if (cMap.containsKey(fId))
                                field.setCodes(cMap.get(fId));
                        }
                    }

                    //客户2所有自定义模版
                    fieldList2 = new ArrayList<CrmTemplateField>();
                    fieldValueMap2 = new HashMap<String, Object>();
                    String tmpIds2 = customer2.getTemplateIds();
                    if (tmpIds2 != null && tmpIds2.length() > 0) {
                        tmpIds2 = tmpIds2.trim();
                    } else {
                        tmpIds2 = "1";
                    }
                    String[] tmpIds2Array = tmpIds2.split(",");
                    for (String tmpId : tmpIds2Array) {
                        if (StringUtil.isEmpty(tmpId)) {
                            continue;
                        }
                        List<CrmTemplateField> fieldList = crmTemplateFieldService
                            .getCrmTemplateFieldListByTemplateId(Integer.valueOf(tmpId));
                        fieldList2.addAll(fieldList);
                        fieldValueMap2.putAll(mayDiffCustomerService.getFieldValue(
                            customer2.getCustomerId(), fieldList).get(0));
                    }
                    for (CrmTemplateField field : fieldList2) {
                        if (field.getTemplateFieldType().equalsIgnoreCase("Select")
                            || field.getTemplateFieldType().equalsIgnoreCase("MultipleSelect")) {
                            String fId = String.valueOf(field.getTemplateFieldId());
                            if (cMap.containsKey(fId))
                                field.setCodes(cMap.get(fId));
                        }
                    }

                    // 取得自定义字段高亮颜色
                    fieldValueMap3 = new HashMap<String, Object>();
                    for (String key : fieldValueMap1.keySet()) {
                        Object value1 = fieldValueMap1.get(key);
                        Object value2 = fieldValueMap2.get(key);
                        temp1 = getNotNullValue(value1);
                        temp2 = getNotNullValue(value2);
                        if (!temp1.equals(temp2)) {
                            if (fieldTypeMap.get(key) != null
                                && fieldTypeMap.get(key).equals("MultipleSelect")) {
                                fieldValueMap3.put(key, mergeStringDot(temp1, temp2));
                            } else {
                                if (!temp1.equals("") && temp2.equals("")) {
                                    fieldValueMap3.put(key, temp1);
                                } else if (temp1.equals("") && !temp2.equals("")) {
                                    fieldValueMap3.put(key, temp2);
                                } else {
                                    fieldValueMap3.put(key, "");
                                }
                            }
                            if (!temp1.equals("") && !temp2.equals("")) {
                                diffColorMap.put(key, "bgcolor");
                            }
                        } else {
                            fieldValueMap3.put(key, temp1);
                        }
                    }

                    //得出客户二不同于客户一的自定义字段
                    Map<String, Object> tempMap = new HashMap<String, Object>();
                    for (CrmTemplateField field : fieldList1) {
                        tempMap.put(field.getTemplateFieldId() + "", 1);
                    }
                    fieldList2Diff = new ArrayList<CrmTemplateField>();
                    for (CrmTemplateField field : fieldList2) {
                        Object value = tempMap.get(field.getTemplateFieldId() + "");
                        if (value == null) {
                            fieldList2Diff.add(field);
                        }
                    }

                    //确保fieldmap3都有默认值
                    for (CrmTemplateField field : fieldList1) {
                        if (fieldValueMap3.get(field.getTemplateFieldId() + "") == null) {
                            fieldValueMap3.put(field.getTemplateFieldId() + "", "");
                        }
                    }

                    //确保fieldmap2都有默认值
                    for (CrmTemplateField field : fieldList2Diff) {
                        if (fieldValueMap2.get(field.getTemplateFieldId() + "") == null) {
                            fieldValueMap2.put(field.getTemplateFieldId() + "", "");
                        }
                    }

                    // 合并客户页卡，业务下存在相同字段，显示方式应为：业务_字段
                    Map<String, List<CrmTemplateField>> map = new HashMap<String, List<CrmTemplateField>>();
                    for (CrmTemplateField field : fieldList1) {
                        List<CrmTemplateField> list = map.get(field.getTemplateFieldName());
                        if (list != null) {
                            list.add(field);
                        } else {
                            list = new ArrayList<CrmTemplateField>();
                            list.add(field);
                        }
                        map.put(field.getTemplateFieldName(), list);
                    }
                    for (CrmTemplateField field : fieldList2Diff) {
                        List<CrmTemplateField> list = map.get(field.getTemplateFieldName());
                        if (list != null) {
                            list.add(field);
                        } else {
                            list = new ArrayList<CrmTemplateField>();
                            list.add(field);
                        }
                        map.put(field.getTemplateFieldName(), list);
                    }
                    //得到所有模版map
                    List<CrmTemplate> allTemplateList = crmTemplateService.getAllCrmTemplate();
                    Map<String, CrmTemplate> allTemplateMap = new HashMap<String, CrmTemplate>();
                    for (CrmTemplate t : allTemplateList) {
                        allTemplateMap.put(t.getTemplateId() + "", t);
                    }
                    for (CrmTemplateField field : fieldList1) {
                        List<CrmTemplateField> list = map.get(field.getTemplateFieldName());
                        if (list != null && list.size() > 1) {
                            field.setTemplateFieldName(allTemplateMap.get(
                                field.getTemplateId() + "").getTemplateName()
                                                       + "_" + field.getTemplateFieldName());
                        }
                    }
                    for (CrmTemplateField field : fieldList2Diff) {
                        List<CrmTemplateField> list = map.get(field.getTemplateFieldName());
                        if (list != null && list.size() > 1) {
                            field.setTemplateFieldName(allTemplateMap.get(
                                field.getTemplateId() + "").getTemplateName()
                                                       + "_" + field.getTemplateFieldName());
                        }
                    }

                    //其他信息
                    //联系记录数量
                    recNum1 = mayDiffCustomerService.countRecordInfo(customer1.getCustomerId());
                    recNum2 = mayDiffCustomerService.countRecordInfo(customer2.getCustomerId());
                    recNum3 = recNum1 + recNum2;

                    //任务安排
                    tskNum1 = mayDiffCustomerService.countTask(customer1.getCustomerId());
                    tskNum2 = mayDiffCustomerService.countTask(customer2.getCustomerId());
                    tskNum3 = tskNum1 + tskNum2;

                    //亲友信息
                    relNum1 = mayDiffCustomerService.countRelative(customer1.getCustomerId());
                    relNum2 = mayDiffCustomerService.countRelative(customer2.getCustomerId());
                    relNum3 = relNum1 + relNum2;

                    //理财产品
                    pdtNum1 = mayDiffCustomerService.countProduct(customer1.getCustomerId());
                    pdtNum2 = mayDiffCustomerService.countProduct(customer2.getCustomerId());
                    pdtNum3 = pdtNum1 + pdtNum2;
                    
                    //贷款
                    loanNum1=lnLoanService.getCustomerLoanCount(customer1.getCustomerId());
                    loanNum2=lnLoanService.getCustomerLoanCount(customer2.getCustomerId());
                    loanNum3=loanNum1+loanNum2;
                    
                    //日程
                    scheduleNum1=tskScheduleService.getCusScheduleCount(customer1.getCustomerId());
                    scheduleNum2=tskScheduleService.getCusScheduleCount(customer2.getCustomerId());
                    scheduleNum3=scheduleNum1+scheduleNum2;
                }
            }

            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("取得相同客户列表错误... ", e);
            return ERROR;
        }
    }

    /**
     * 合并带逗号字符串
     * @param tmp1
     * @param tmp2
     * @return
     */
    public String mergeStringDot(String tmp1, String tmp2) {
        String[] tmp1Array = tmp1.split(",");
        String[] tmp2Array = tmp2.split(",");
        String[] mergeArray = merge(tmp1Array, tmp2Array);
        String ids = "";
        for (String s : mergeArray) {
            if (s != null && s.length() > 0) {
                ids += s + ",";
            }
        }
        if (ids.length() > 0) {
            ids = ids.substring(0, ids.length() - 1);
        }
        return ids;
    }

    /**
     * 合并字符串数组
     * @param arg1
     * @param arg2
     * @return
     */
    public String[] merge(Comparable[] arg1, Comparable[] arg2) {
        //convert arrays to collections (lists)
        Collection coll1 = Arrays.asList(arg1);
        Collection coll2 = Arrays.asList(arg2);

        //Create a SortedSet from the first collection
        SortedSet sorter = new TreeSet(coll1);

        //Add the second collection
        sorter.addAll(coll2);

        //Create an array to hold the results
        String[] merged = new String[sorter.size()];
        Iterator itSorted = sorter.iterator();

        for (int i = 0; itSorted.hasNext(); i++) {
            merged[i] = (String) itSorted.next();
        }

        //return the SortedSet as an array
        return (merged);
    }

    /**
     * 获得客户的归属类型-----编辑客户
     * @param crmCustomer
     * @return
     */
    private String getCustomerBelongTo(CrmCustomer crmCustomer) {
        if (crmCustomer == null) {
            return "brMine";
        }
        if (crmCustomer.getBelongUserId() == null || crmCustomer.getBelongUserId().equals(0)) {
            //归属用户id为空 或0   则表示是归属机构的客户
            return "brDept";
        } else if (crmCustomer.getBelongUserId().equals(this.getLoginInfo().getUserId())) {
            //归属用户id为当前用户   则表示是归属我的客户
            return "brMine";
        } else {
            return "brUser";
        }
    }

    public void setCrmCustomerService(CrmCustomerService rmCustomerService) {
        this.crmCustomerService = rmCustomerService;
    }

    public List<CrmCustomer> getMayDiffCustomerList() {
        return mayDiffCustomerList;
    }

    public void setMayDiffCustomerList(List<CrmCustomer> mayDiffCustomerList) {
        this.mayDiffCustomerList = mayDiffCustomerList;
    }

    public PageUtil<MayDiffCustomerName> getMayDiffCustomerNameList() {
        return mayDiffCustomerNameList;
    }

    public void setMayDiffCustomerNameList(PageUtil<MayDiffCustomerName> mayDiffCustomerNameList) {
        this.mayDiffCustomerNameList = mayDiffCustomerNameList;
    }

    public void setMayDiffCustomerService(MayDiffCustomerService mayDiffCustomerService) {
        this.mayDiffCustomerService = mayDiffCustomerService;
    }

    public OpeventLogService getOpeventLogService() {
        return opeventLogService;
    }

    public void setOpeventLogService(OpeventLogService opeventLogService) {
        this.opeventLogService = opeventLogService;
    }

    public Boolean getIsInChargeof() {
        return isInChargeof;
    }

    public void setIsInChargeof(Boolean isInChargeof) {
        this.isInChargeof = isInChargeof;
    }

    public CrmCustomerTypeService getCrmCustomerTypeService() {
        return crmCustomerTypeService;
    }

    public List<CrmCustomerType> getCusTypelist() {
        return cusTypelist;
    }

    public void setCusTypelist(List<CrmCustomerType> cusTypelist) {
        this.cusTypelist = cusTypelist;
    }

    public void setCrmCustomerTypeService(CrmCustomerTypeService crmCustomerTypeService) {
        this.crmCustomerTypeService = crmCustomerTypeService;
    }

    public DeptFacadeService getDeptFacadeService() {
        return deptFacadeService;
    }

    public void setDeptFacadeService(DeptFacadeService deptFacadeService) {
        this.deptFacadeService = deptFacadeService;
    }

    public CrmCustomer getCrmCustomer() {
        return crmCustomer;
    }

    public Map<String, Object> getParameterMap() {
        return parameterMap;
    }

    public void setParameterMap(Map<String, Object> parameterMap) {
        this.parameterMap = parameterMap;
    }

    public void setCrmCustomer(CrmCustomer crmCustomer) {
        this.crmCustomer = crmCustomer;
    }

    public String FirstLoad() {
        return SUCCESS;
    }

    public int getRecNum1() {
        return recNum1;
    }

    public void setRecNum1(int recNum1) {
        this.recNum1 = recNum1;
    }

    public int getRecNum2() {
        return recNum2;
    }

    public void setRecNum2(int recNum2) {
        this.recNum2 = recNum2;
    }

    public int getRecNum3() {
        return recNum3;
    }

    public void setRecNum3(int recNum3) {
        this.recNum3 = recNum3;
    }

    public int getTskNum1() {
        return tskNum1;
    }

    public void setTskNum1(int tskNum1) {
        this.tskNum1 = tskNum1;
    }

    public int getTskNum2() {
        return tskNum2;
    }

    public void setTskNum2(int tskNum2) {
        this.tskNum2 = tskNum2;
    }

    public int getTskNum3() {
        return tskNum3;
    }

    public void setTskNum3(int tskNum3) {
        this.tskNum3 = tskNum3;
    }

    public int getRelNum1() {
        return relNum1;
    }

    public void setRelNum1(int relNum1) {
        this.relNum1 = relNum1;
    }

    public int getRelNum2() {
        return relNum2;
    }

    public void setRelNum2(int relNum2) {
        this.relNum2 = relNum2;
    }

    public int getRelNum3() {
        return relNum3;
    }

    public void setRelNum3(int relNum3) {
        this.relNum3 = relNum3;
    }

    public int getPdtNum1() {
        return pdtNum1;
    }

    public void setPdtNum1(int pdtNum1) {
        this.pdtNum1 = pdtNum1;
    }

    public int getPdtNum2() {
        return pdtNum2;
    }

    public void setPdtNum2(int pdtNum2) {
        this.pdtNum2 = pdtNum2;
    }

    public int getPdtNum3() {
        return pdtNum3;
    }

    public void setPdtNum3(int pdtNum3) {
        this.pdtNum3 = pdtNum3;
    }

    public Map<String, Object> getFieldValueMap3() {
        return fieldValueMap3;
    }

    public void setFieldValueMap3(Map<String, Object> fieldValueMap3) {
        this.fieldValueMap3 = fieldValueMap3;
    }

    public Map<String, String> getDiffColorMap() {
        return diffColorMap;
    }

    public void setDiffColorMap(Map<String, String> diffColorMap) {
        this.diffColorMap = diffColorMap;
    }

    public List<CrmTemplateField> getFieldList2Diff() {
        return fieldList2Diff;
    }

    public void setFieldList2Diff(List<CrmTemplateField> fieldList2Diff) {
        this.fieldList2Diff = fieldList2Diff;
    }

    public CrmCustomer getCustomer1() {
        return customer1;
    }

    public void setCustomer1(CrmCustomer customer1) {
        this.customer1 = customer1;
    }

    public CrmCustomer getCustomer2() {
        return customer2;
    }

    public void setCustomer2(CrmCustomer customer2) {
        this.customer2 = customer2;
    }

    public List<CrmTemplateField> getFieldList1() {
        return fieldList1;
    }

    public void setFieldList1(List<CrmTemplateField> fieldList1) {
        this.fieldList1 = fieldList1;
    }

    public Map<String, Object> getFieldValueMap1() {
        return fieldValueMap1;
    }

    public void setFieldValueMap1(Map<String, Object> fieldValueMap1) {
        this.fieldValueMap1 = fieldValueMap1;
    }

    public List<CrmTemplateField> getFieldList2() {
        return fieldList2;
    }

    public void setFieldList2(List<CrmTemplateField> fieldList2) {
        this.fieldList2 = fieldList2;
    }

    public Map<String, Object> getFieldValueMap2() {
        return fieldValueMap2;
    }

    public void setFieldValueMap2(Map<String, Object> fieldValueMap2) {
        this.fieldValueMap2 = fieldValueMap2;
    }

    public void setCrmTemplateFieldService(CrmTemplateFieldService crmTemplateFieldService) {
        this.crmTemplateFieldService = crmTemplateFieldService;
    }

    public String getCustomerIds() {
        return customerIds;
    }

    public void setCustomerIds(String customerIds) {
        this.customerIds = customerIds;
    }

    private String getNotNullValue(Object s) {
        return s == null ? "" : s.toString();
    }

    private String getDateNotNullValue(Date s) {
        if (s == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(s);
    }

    public void setCodetableService(CdSystemService codetableService) {
        this.codetableService = codetableService;
    }

    public CdSystemService getCodetableService() {
        return codetableService;
    }

    public Integer getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(Integer currentUserId) {
        this.currentUserId = currentUserId;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getProvinceName1() {
        return provinceName1;
    }

    public void setProvinceName1(String provinceName1) {
        this.provinceName1 = provinceName1;
    }

    public String getProvinceName2() {
        return provinceName2;
    }

    public void setProvinceName2(String provinceName2) {
        this.provinceName2 = provinceName2;
    }

    public String getCityName1() {
        return cityName1;
    }

    public void setCityName1(String cityName1) {
        this.cityName1 = cityName1;
    }

    public String getCityName2() {
        return cityName2;
    }

    public void setCityName2(String cityName2) {
        this.cityName2 = cityName2;
    }

    public void setOpeventLoginLogService(OpeventLoginLogService opeventLoginLogService) {
        this.opeventLoginLogService = opeventLoginLogService;
    }

    public void setCrmTemplateService(CrmTemplateService crmTemplateService) {
        this.crmTemplateService = crmTemplateService;
    }

    public void setLnLoanService(LnLoanService lnLoanService) {
        this.lnLoanService = lnLoanService;
    }

    public void setTskScheduleService(TskScheduleService tskScheduleService) {
        this.tskScheduleService = tskScheduleService;
    }

    public int getLoanNum1() {
        return loanNum1;
    }

    public void setLoanNum1(int loanNum1) {
        this.loanNum1 = loanNum1;
    }

    public int getLoanNum2() {
        return loanNum2;
    }

    public void setLoanNum2(int loanNum2) {
        this.loanNum2 = loanNum2;
    }

    public int getLoanNum3() {
        return loanNum3;
    }

    public void setLoanNum3(int loanNum3) {
        this.loanNum3 = loanNum3;
    }

    public int getScheduleNum1() {
        return scheduleNum1;
    }

    public void setScheduleNum1(int scheduleNum1) {
        this.scheduleNum1 = scheduleNum1;
    }

    public int getScheduleNum2() {
        return scheduleNum2;
    }

    public void setScheduleNum2(int scheduleNum2) {
        this.scheduleNum2 = scheduleNum2;
    }

    public int getScheduleNum3() {
        return scheduleNum3;
    }

    public void setScheduleNum3(int scheduleNum3) {
        this.scheduleNum3 = scheduleNum3;
    }
    
}
