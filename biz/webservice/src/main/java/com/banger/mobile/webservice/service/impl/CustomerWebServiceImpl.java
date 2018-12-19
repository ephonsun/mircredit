/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :xuhj
 * Create Date:2013-3-14
 */
package com.banger.mobile.webservice.service.impl;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.base.customer.BaseCrmCustomer;
import com.banger.mobile.domain.model.credentialType.CredentialType;
import com.banger.mobile.domain.model.customer.CrmCustomer;
import com.banger.mobile.domain.model.customer.CrmCustomerRelatives;
import com.banger.mobile.domain.model.data.*;
import com.banger.mobile.domain.model.inform.Inform;
import com.banger.mobile.domain.model.message.MessageResult;
import com.banger.mobile.domain.model.template.CrmTemplate;
import com.banger.mobile.domain.model.templateField.CrmTemplateField;
import com.banger.mobile.domain.model.user.SysUser;
import com.banger.mobile.facade.customer.CrmCustomerRelativesService;
import com.banger.mobile.facade.customer.CrmCustomerService;
import com.banger.mobile.facade.data.*;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.feedBack.InformService;
import com.banger.mobile.facade.loan.LnLoanService;
import com.banger.mobile.facade.microTask.TskMicroTaskTargetService;
import com.banger.mobile.facade.microTask.TskScheduleService;
import com.banger.mobile.facade.record.RecordInfoService;
import com.banger.mobile.facade.system.CdSystemService;
import com.banger.mobile.facade.template.CrmTemplateService;
import com.banger.mobile.facade.templateField.CrmTemplateFieldService;
import com.banger.mobile.facade.user.SysUserService;
import com.banger.mobile.facade.webservice.CustomerWebService;
import com.banger.mobile.importUtil.ImportUtil;
import com.banger.mobile.util.IDCardUtil;
import com.banger.mobile.util.JsonDateValueProcessor;
import com.banger.mobile.util.StringUtil;
import com.banger.mobile.util.TypeUtil;
import com.banger.mobile.webservice.domain.CustomerDataInfo;
import com.banger.mobile.webservice.domain.CustomerDataPage;
import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.JSONUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.xfire.transport.http.XFireServletController;
import org.springframework.util.CollectionUtils;


import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author xuhj
 * @version $Id: WebCustomerServiceImpl.java,v 0.1 2013-3-14 下午2:06:33 xuhj Exp $
 */
@WebService(serviceName = "BangerCrmCustomerService", endpointInterface = "com.banger.mobile.facade.webservice.CustomerWebService")
public class CustomerWebServiceImpl implements CustomerWebService {

    private CrmCustomerService crmCustomerService;


    private SysUserService sysUserService;
    private CdSystemService codetableService;
    private CrmCustomerRelativesService crmCustomerRelativesService;
    private CrmTemplateFieldService crmTemplateFieldService;
    private CrmTemplateService crmTemplateService;
    private LnLoanService lnLoanService;
    private TskScheduleService tskScheduleService;

    private DataAudioService dataAudioService;
    private DataPhotoService dataPhotoService;
    private DataVideoService dataVideoService;
    private DataSmsService dataSmsService;
    private DataMmsService dataMmsService;
    private DataFormService dataFormService;
    private DeptFacadeService deptFacadeService;      //机构Service
    private TskMicroTaskTargetService tskMicroTaskTargetService;//任务目标客户
    private RecordInfoService recordInfoService;
    private InformService informService;//通知service
    private static final Logger logger = Logger.getLogger(CustomerWebServiceImpl.class);

    /**
     * 保存客户全部信息
     *
     * @param account         当前用户账号
     * @param crmCustomerJson 客户实体json
     */
    public String saveCustomer(String account, String crmCustomerJson) {
        try {
            logger.info("pad端客户接口saveCustomer开始,account:" + account + ",crmCustomerJson:" + crmCustomerJson);
            JSONObject jsonObject = JSONObject.fromObject(crmCustomerJson);
            String[] dateFormats = new String[]{"yyyy-MM-dd HH:mm:ss"};
            JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(dateFormats));

            SysUser user = sysUserService.getUserByAccount(account);
            CrmCustomer crmCustomer = (CrmCustomer) JSONObject.toBean(jsonObject, CrmCustomer.class);
            if (crmCustomer != null) {
                if (StringUtils.isNotEmpty(crmCustomer.getCustomerName())) {
                    String toName = crmCustomer.getCustomerName().toLowerCase();
                    if (toName.equals("\"null\"")) {
                        crmCustomer.setCustomerName("null");
                    }
                }
                if (StringUtils.isNotEmpty(crmCustomer.getAddress())) {
                    String toName = crmCustomer.getAddress().toLowerCase();
                    if (toName.equals("\"null\"")) {
                        crmCustomer.setAddress("null");
                    }
                }
                if (StringUtils.isNotEmpty(crmCustomer.getIdCard())) {
                    String toName = crmCustomer.getIdCard().toLowerCase();
                    if (toName.equals("\"null\"")) {
                        crmCustomer.setIdCard("null");
                    }
                }
                if (StringUtils.isNotEmpty(crmCustomer.getRemark())) {
                    String toName = crmCustomer.getRemark().toLowerCase();
                    if (toName.equals("\"null\"")) {
                        crmCustomer.setRemark("null");
                    }
                }

                crmCustomer.setCreateDate(new Date());
                crmCustomer.setUpdateDate(new Date());
                if (crmCustomer.getCustomerId() != null && crmCustomer.getCustomerId() > 0) {
                    CrmCustomer customer = crmCustomerService.getCustomerById(crmCustomer.getCustomerId());
                    this.matchCustomerInfo(customer, crmCustomer);
                    //                    crmCustomerService.updateImpCrmCustomer(crmCustomer);
                    crmCustomerService.updateCustomerRandom(customer);
                } else {

                    if(null!=crmCustomer.getCredentialTypeId()){
                        Map<String,Object> map = new HashMap<String, Object>();
                        map.put("idCard",crmCustomer.getIdCard());
                        map.put("credentialTypeId",crmCustomer.getCredentialTypeId());
                        List<BaseCrmCustomer> cuslist = crmCustomerService.getCrmCustomerByIdCard(map);
                        if(!CollectionUtils.isEmpty(cuslist)){
                            BaseCrmCustomer customer = cuslist.get(0);
                            if(!user.getUserId().equals(customer.getBelongUserId())){
                               // 客户证件号码已存在，请检查!
                                return "-1";
                            }else{

                               return customer.getCustomerId().toString();
                            }
                        }
                    }


                    if (crmCustomer.getIsCustomerEdit() != null && crmCustomer.getIsCustomerEdit().equals(0)) {
                        //除客户模块外的其他地方新建的客户
                        String phone = crmCustomer.getMobilePhone1();
                        if (StringUtils.isNotEmpty(phone)) {
                            if (phone.length() == 11 && phone.startsWith("1")) {
                                //如果手机1为空，则插入到手机1，设置手机1为默认号码
                                crmCustomer.setMobilePhone1(phone);
                                crmCustomer.setDefaultPhoneType(1);
                            } else {
                                crmCustomer.setPhone(phone);
                                crmCustomer.setDefaultPhoneType(3);
                            }
                        }
                    }
                    crmCustomer.setBelongUserId(user.getUserId());
                    crmCustomer.setBelongDeptId(user.getDeptId());
                    crmCustomer.setCreateUser(user.getUserId());
                    crmCustomer.setUpdateUser(user.getUserId());
                    crmCustomer.setTemplateIds("");	
                    crmCustomer.setIsDel(0);
                    crmCustomer.setIsTrash(0);
                    crmCustomer.setIsVisit(0);
                    if (StringUtils.isNotEmpty(crmCustomer.getCustomerName())) {
                        crmCustomer.setCustomerNamePinyin(ImportUtil.getPinYinHeadChar(crmCustomer.getCustomerName()));
                        crmCustomerService.setCustomerNickName(crmCustomer);
//                        if (StringUtils.isNotEmpty(crmCustomer.getSex())){
//                            if (crmCustomer.getSex().equals("男")){
//                                crmCustomer.setCustomerTitle(crmCustomer.getCustomerName()+"先生");
//                            }else if (crmCustomer.getSex().equals("女")){
//                                crmCustomer.setCustomerTitle(crmCustomer.getCustomerName()+"女士");
//                            }
//                        }
                    }
                    if (StringUtils.isNotEmpty(crmCustomer.getIdCard())) {
                        if (IDCardUtil.validateCard(crmCustomer.getIdCard())) {//生日的特殊处理。
                            crmCustomer.setBirthday(ImportUtil.parseStringToDateBeforeToday(IDCardUtil.getBirthByIdCard(crmCustomer.getIdCard())));
                        }
                    }
                    Map<String, String> parameters = new HashMap<String, String>();
                    crmCustomerService.addCrmCustomer(crmCustomer);
                    //保存自定义字段
                    parameters.put("customerId", crmCustomer.getCustomerId().toString());
                    crmCustomerService.addCustomizedField(parameters);
                }
                logger.info("pad端客户接口saveCustomer完成,account:" + account + ",crmCustomerJson:" + crmCustomerJson);
                return crmCustomer.getCustomerId().toString();
            } else {
                return "0";
            }
        } catch (Exception e) {
        	e.printStackTrace();
            logger.error("CustomerWebServiceImpl % saveCustomer", e);
            return "0";
        }
    }

    /**
     * 删除客户至垃圾箱
     *
     * @param account         当前用户账号
     * @param customerId customerId
     */
    @Override
    public String delCustomer(String account, Integer customerId) {
        try {
            logger.info("pad端客户接口saveCustomer开始,account:" + account + ",customerId:" + customerId);

//            SysUser user = sysUserService.getUserByAccount(account);

            if (null!=customerId) {
                String customerIds = lnLoanService.getNoLoanUserList(String.valueOf(customerId));
                if(StringUtils.isNotBlank(customerIds)){
                    crmCustomerService.delCustomerByCustomerIds(customerIds);
                }else{//客户存在贷款信息 不允许删除
                    return "-1";
                }
            }else{
                return "0";
            }

            logger.info("pad端客户接口delCustomer完成,account:" + account + ",customerId:" + customerId);
            return "1";

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("CustomerWebServiceImpl % delCustomer", e);
            return "0";
        }
    }

    /**
     * 当客户基本信息被更改时，对客户的拼音、性别、称呼、年龄、生日等信息，做一些匹配
     *
     * @param crmCustomer 原客户
     * @param customer    要更改的客户
     */
    private void matchCustomerInfo(BaseCrmCustomer crmCustomer, CrmCustomer customer) {
        if (customer.getIsCustomerEdit() != null && customer.getIsCustomerEdit().equals(1)) {
            //客户管理模块的编辑
            crmCustomer.setSex(customer.getSex());
            crmCustomer.setCredentialTypeId(customer.getCredentialTypeId());
        }
        if (StringUtils.isNotEmpty(customer.getCustomerName()) && !customer.getCustomerName().trim().equals("")) {
            //如果名字改变了，就要自动生成拼音，并把性别、称呼置空
            if (!customer.getCustomerName().equals(crmCustomer.getCustomerName())) {
                crmCustomer.setCustomerName(customer.getCustomerName());
                crmCustomer.setCustomerNamePinyin(ImportUtil.getPinYinHeadChar(crmCustomer.getCustomerName()));
                crmCustomerService.setCustomerNickName(crmCustomer);
            }
        }
        if (customer.getIsCustomerEdit() != null && customer.getIsCustomerEdit().equals(1)) {
            //客户管理模块的编辑
            crmCustomer.setCustomerTitle(customer.getCustomerTitle());
        }
        if (StringUtils.isNotEmpty(customer.getIdCard()) && !customer.getIdCard().trim().equals("")) {
            if (crmCustomer.getIdCard() == null || !customer.getIdCard().equals(crmCustomer.getIdCard())) {
                if (IDCardUtil.validateCard(customer.getIdCard())) {//生日的特殊处理。
                    crmCustomer.setBirthday(ImportUtil.parseStringToDateBeforeToday(IDCardUtil.getBirthByIdCard(customer.getIdCard())));
                }
                crmCustomer.setIdCard(customer.getIdCard());
            }
        }
//        crmCustomer.setSex(customer.getSex());
        crmCustomer.setAddress(customer.getAddress());
        crmCustomer.setRemark(customer.getRemark());
        if (customer.getIsCustomerEdit() != null && customer.getIsCustomerEdit().equals(1)) {
            //是客户管理模块的编辑客户
            crmCustomer.setDefaultPhoneType(customer.getDefaultPhoneType());
            crmCustomer.setMobilePhone1(customer.getMobilePhone1());
            crmCustomer.setMobilePhone2(customer.getMobilePhone2());
            crmCustomer.setPhone(customer.getPhone());
        } else {
            //其他模块的编辑客户(这个另做处理，因为与客户管理模块的编辑客户不一样的)
            Integer defaultPhoneType = customer.getDefaultPhoneType();
            String phone = "";
            if (defaultPhoneType != null && defaultPhoneType == 1) {
                phone = customer.getMobilePhone1();
            } else if (defaultPhoneType != null && defaultPhoneType == 2) {
                phone = customer.getMobilePhone2();
            } else if (defaultPhoneType != null && defaultPhoneType == 3) {
                phone = customer.getPhone();
            }
            if (StringUtils.isNotEmpty(phone)) {
                String mobilePhone1 = crmCustomer.getMobilePhone1();
                String mobilePhone2 = crmCustomer.getMobilePhone2();
                String fixedPhone = crmCustomer.getPhone();
                String fax = crmCustomer.getFax();
                if ((mobilePhone1 == null || !mobilePhone1.equals(phone)) && (mobilePhone2 == null || !mobilePhone2.equals(phone))
                        && (fixedPhone == null || !fixedPhone.equals(phone))
                        && (fax == null || !fax.equals(phone))) {
                    //不等于客户原联系方式中的任何一个
                    if (phone.length() == 11 && phone.startsWith("1")) {
                        //手机
                        if (mobilePhone1 == null || mobilePhone1.trim().equals("")) {
                            //如果手机1为空，则插入到手机1，设置手机1为默认号码
                            crmCustomer.setMobilePhone1(phone);
                            crmCustomer.setDefaultPhoneType(1);
                        } else if (mobilePhone2 == null || mobilePhone2.trim().equals("")) {
                            //如果手机2为空，则插入到手机2，设置手机2为默认号码
                            crmCustomer.setMobilePhone2(phone);
                            crmCustomer.setDefaultPhoneType(2);
                        } else {
                            //如果手机1为空，手机2不为空，则更新手机1的号码，并且随着手机1为默认号码
                            crmCustomer.setMobilePhone1(phone);
                            crmCustomer.setDefaultPhoneType(1);
                        }
                    } else {
                        //固话
                        if (fixedPhone == null || fixedPhone.trim().equals("")) {
                            //如果固话为空，则插入到固话
                            crmCustomer.setPhone(phone);
                        } else {
                            //如果固话不为空，则更新固话
                            crmCustomer.setPhone(phone);
                        }
                    }
                }
            }
        }
    }

    /**
     * 我的客户列表
     *
     * @param account   当前登录账号
     * @param condition 查询条件
     * @param page      分页
     * @return
     */
    public String getCustomerList(String account, String condition, Integer page, String belongToType) {
        logger.info("pad端客户接口getCustomerList开始,account:" + account + ",condition:" + condition + ",page:" + page + ",belongToType:" + belongToType);
        SysUser user = sysUserService.getUserByAccount(account);

        belongToType = "brMine";
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        parameterMap.put("BelongTo", belongToType);
        String inChargeOfUserIds = "";
        String inChargeOfDeptIds = "";
        boolean isInChargeof = false;
        if (isInChargeof) {
            inChargeOfDeptIds = getCurrentUserInChargeOfDeptIds(user.getUserId());
            if (StringUtil.isEmpty(inChargeOfDeptIds)) {
                inChargeOfDeptIds = "-1";
            }
            inChargeOfUserIds = getCurrentInChargeUserIds(user.getUserId());
        } else {
            inChargeOfDeptIds = "-1";
            inChargeOfUserIds = user.getUserId().toString();
        }
        if (belongToType.equals("brMine")) {
            inChargeOfUserIds = user.getUserId().toString();
            parameterMap.put("QueryUserIds", user.getUserId().toString());
        }
        parameterMap.put("InChargeOfUserIds", inChargeOfUserIds);
        parameterMap.put("InChargeOfDeptIds", inChargeOfDeptIds);
        parameterMap.put("UserId", user.getUserId().toString());
        parameterMap.put("isTrash", 0);
        if (!StringUtil.isEmpty(condition)) {
            parameterMap.put("padInput", condition);
        }

        Page pageObj = new Page();
        pageObj.setCurrentPage(page);
        PageUtil<CrmCustomer> cusList = crmCustomerService.getCrmCustomerPage(parameterMap, pageObj);

        setCusListForPad(cusList);

        JSONArray jsonArray = new JSONArray();
        if (cusList.getItems().size() > 0) {
            for (CrmCustomer cus : cusList.getItems()) {
                JSONObject cusJson = getCustomerMap(cus, user.getUserId());
                jsonArray.add(cusJson);
            }
        }
        JSONObject resultData = new JSONObject();
        resultData.put("dataList", jsonArray.toString());
        resultData.put("totalCount", pageObj.getTotalRowsAmount());
        logger.info("pad端客户接口getCustomerList完成,account:" + account + ",condition:" + condition + ",page:" + page + ",belongToType:" + belongToType);
        return resultData.toString();
    }

    /**
     * 当前用户有权限的      机构ids
     *
     * @return
     */
    private String getCurrentUserInChargeOfDeptIds(Integer userid) {
        String deptIds = "";
        Integer[] arr = deptFacadeService.getInChargeOfDeptIds(userid);
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

    /**
     * 获取当前用户有权限的      用户ids
     *
     * @return
     */
    private String getCurrentInChargeUserIds(Integer userid) {
        String userIds = "";
        Integer[] arr = deptFacadeService.getInChargeOfDeptUserIds(userid);
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                if (userIds.equals(""))
                    userIds = arr[i].toString();
                else
                    userIds = userIds + "," + arr[i];
            }
        }
        if (userIds.equals("")) {
            userIds = userid.toString();
        } else {
            userIds = userIds + "," + userid.toString();
        }
        return userIds;
    }

    private void setCusListForPad(PageUtil<CrmCustomer> cusList) {
    	//TODO:修改
    	/*
        //追加贷款信息和日程
        String cusIds = "";

        if (cusList.getItems().size() > 0) {
            for (CrmCustomer cus : cusList.getItems()) {
                if (cusIds.equals("")) {
                    cusIds = cus.getCustomerId().toString();
                } else {
                    cusIds = cusIds + "," + cus.getCustomerId().toString();
                }
            }
        }

        List<LnLoan> loanList = lnLoanService.getAllLoanByCusIds(cusIds);
        Map<String, LnLoan> loanMap = new HashMap<String, LnLoan>();
        if (loanList != null) {
            for (LnLoan loan : loanList) {
                loan.setDiffTime(loan.getCreateDate().getTime() - new Date().getTime());
            }

            for (LnLoan loan : loanList) {
                String key = loan.getCustomerId().toString();
                if (loanMap.containsKey(key)) {
                    LnLoan oldLoan = loanMap.get(key);
                    if (oldLoan.getDiffTime() < 0) {
                        oldLoan.setDiffTime(-oldLoan.getDiffTime());
                    }
                    if (loan.getDiffTime() < 0) {
                        loan.setDiffTime(-loan.getDiffTime());
                    }
                    if (oldLoan.getDiffTime() - loan.getDiffTime() < 0) {
                        loanMap.put(key, oldLoan);
                    } else {
                        loanMap.put(key, loan);
                    }
                } else {
                    loanMap.put(key, loan);
                }
            }
        }

        List<TskSchedule> scheduleList = tskScheduleService.getScheduleByCusIds(cusIds);
        Map<String, TskSchedule> scheduleMap = new HashMap<String, TskSchedule>();
        if (scheduleList != null) {
            for (TskSchedule schedule : scheduleList) {
                schedule.setDiffTime(schedule.getContactDate().getTime() - new Date().getTime());
            }

            for (TskSchedule schedule : scheduleList) {
                String key = schedule.getCustomerId().toString();
                if (scheduleMap.containsKey(key)) {
                    TskSchedule oldSchedule = scheduleMap.get(key);
                    if (oldSchedule.getDiffTime() < 0) {
                        oldSchedule.setDiffTime(-oldSchedule.getDiffTime());
                    }
                    if (schedule.getDiffTime() < 0) {
                        schedule.setDiffTime(-schedule.getDiffTime());
                    }
                    Long oldDiffTime = oldSchedule.getDiffTime();
                    Long curDiffTime = schedule.getDiffTime();
                    Long diffTime = oldDiffTime - curDiffTime;
                    if (diffTime < 0) {
                        scheduleMap.put(key, oldSchedule);
                    } else {
                        scheduleMap.put(key, schedule);
                    }
                } else {
                    scheduleMap.put(key, schedule);
                }
            }
        }
        String cid = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        if (cusList.getItems().size() > 0) {
            for (CrmCustomer cus : cusList.getItems()) {
                cid = cus.getCustomerId().toString();
                if (loanMap.containsKey(cid)) {
                    cus.setLoanStatus(loanMap.get(cid).getLnLoanStatus().getLoanStatusName());
                }
                if (scheduleMap.containsKey(cid)) {
                    String dataStr = scheduleMap.get(cid).getContactDate() == null ? "" : sdf.format(scheduleMap.get(cid).getContactDate());
                    String visitType = scheduleMap.get(cid).getContactTypeName() == null ? "" : scheduleMap.get(cid).getContactTypeName();
                    cus.setScheduleInfo(dataStr + "  " + visitType);
                }
            }
        }
        */
    }

    /**
     * 查询客户亲友列表
     *
     * @param account 当前登录账号
     * @param cusId   客户ID
     * @param page    分页
     * @return
     */
    public String getRelativesCustomerList(String account, String cusId, Integer page) {
        logger.info("pad端客户接口getRelativesCustomerList开始,account:" + account + ",cusId:" + cusId + ",page:" + page);
        SysUser user = sysUserService.getUserByAccount(account);
        Map<String, String> map = new HashMap<String, String>();
        map.put("customerId", cusId);
        map.put("userIds", user.getUserId().toString());
        map.put("UserId", user.getUserId().toString());
        Page pageObj = new Page();
        pageObj.setCurrentPage(page);
        PageUtil<CrmCustomerRelatives> cusList = crmCustomerRelativesService
                .getCustomerRelativesPad(map, pageObj, user.getUserId().toString());

        JSONArray jsonArray = new JSONArray();
        if (cusList.getItems().size() > 0) {
            for (CrmCustomerRelatives cus : cusList.getItems()) {
                JsonConfig jsonConfig = new JsonConfig();
                jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
                JSONObject cusJson = JSONObject.fromObject(cus, jsonConfig);
                jsonArray.add(cusJson);
            }
        }
        JSONObject resultData = new JSONObject();
        resultData.put("dataList", jsonArray.toString());
        resultData.put("totalCount", pageObj.getTotalRowsAmount());
        logger.info("pad端客户接口getRelativesCustomerList完成,account:" + account + ",cusId:" + cusId + ",page:" + page);
        return JSONObject.fromObject(resultData).toString();
    }

    /**
     * PAD 根据客户ID得到基本信息实体
     *
     * @param customerId
     * @return json string
     */
    public String getCustomerInfo(Integer customerId) {
        logger.info("pad端客户接口getCustomerInfo开始,customerId:" + customerId);
        JSONObject map = new JSONObject();
        CrmCustomer crmCustomer = crmCustomerService.getCustomerInfo(customerId);
        if (crmCustomer != null) {
            map = getCustomerMap(crmCustomer, 0);
        }
        //LnLoan lnLoan = null;
        //if (lnLoan != null) {
        //    map.put("loanStatus", lnLoan.getLoanStatusName());
        //}
        logger.info("pad端客户接口getCustomerInfo完成,customerId:" + customerId);
        return map.toString();
    }

    /**
     * 更新最近联系时间
     */
    public void updateLastContactDate(Integer customerId, Date lastContactDate,
                                      String lastContactType) {
        logger.info("pad端客户接口updateLastContactDate开始,customerId:" + customerId + ",lastContactDate:" + lastContactDate + ",lastContactType:" + lastContactType);
        crmCustomerService.updateLastContactDate(customerId, lastContactDate, lastContactType);
        logger.info("pad端客户接口updateLastContactDate完成,customerId:" + customerId + ",lastContactDate:" + lastContactDate + ",lastContactType:" + lastContactType);
    }

    /**
     * get customer object to map
     *
     * @param crmCustomer
     * @return
     */
    private JSONObject getCustomerMap(CrmCustomer crmCustomer, Integer userid) {
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
        JSONObject map = JSONObject.fromObject(crmCustomer, jsonConfig);

        HttpServletRequest request = XFireServletController.getRequest();
        map.put("headshow", crmCustomer.getHeadshow());

        // 取得省市名称
        String provCode = crmCustomer.getProvince();
        if (provCode != null && provCode.length() > 0) {
            try {
                String provName = codetableService.getProvinceName(provCode);
                if (provName != null) {
                    map.put("provinceName", provName);
                }
            } catch (Exception e) {
                logger.error("getCustomerMap", e);
            }
        }
        String cityCode = crmCustomer.getCity();
        if (cityCode != null && cityCode.length() > 0) {
            try {
                String cityName = codetableService.getCityName(cityCode);
                if (cityName != null) {
                    map.put("cityName", cityName);
                }
            } catch (Exception e) {
                logger.error("getCustomerMap", e);
            }
        }
        map.put("isReceiveSms", crmCustomer.getIsReceiveSms() != null ? crmCustomer
                .getIsReceiveSms().toString() : "1");

        //判断是否有权限
        if (userid != 0) {
            boolean isInChargeof = deptFacadeService.isInChargeOfDepartment(userid.toString());
            Integer[] arr = isInChargeof ? deptFacadeService.getInChargeOfDeptIds(userid) : null;
            Integer hasPerssion = checkPermission(crmCustomer, arr, userid);
            map.put("isLimited", hasPerssion);
        }
        return map;
    }

    /**
     * 是否有该客户的权限
     * 0:无权限  1：有权限
     */
    public Integer checkPermission(CrmCustomer crmCustomer, Integer[] arr, Integer userid) {
        if (userid.equals(crmCustomer.getBelongUserId())) {
            return 1;
        } else if (isInChargeOfDepartment(arr, crmCustomer)) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * 是否是业务主管
     *
     * @param arr
     * @param crmCustomer
     * @return true 是  False 否
     */
    private Boolean isInChargeOfDepartment(Integer[] arr, CrmCustomer crmCustomer) {
        Integer flag = 0;
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i].equals(crmCustomer.getBelongDeptId())) {
                    flag = 1;
                    break;
                } else
                    flag = 0;
            }
            if (flag.equals(1)) {
                return true;
            } else
                return false;
        } else
            return false;
    }

    /**
     * 客户自定义业务模板信息
     *
     * @param customerId
     * @param templateId
     * @return
     */
    public String getCustomerTemplateInfo(Integer customerId, Integer templateId) {
        try {
            logger.info("pad端客户接口getCustomerTemplateInfo开始,customerId:" + customerId + ",templateId:" + templateId);
            Map<String, Object> parameters = new HashMap<String, Object>();

            List<CrmTemplateField> fieldList = crmTemplateFieldService
                    .getCrmTemplateFieldListByTemplateId(templateId.intValue());
            String queryColumn = "";
            for (CrmTemplateField crmTemplateField : fieldList) {
                if (crmTemplateField.getTemplateFieldType().equalsIgnoreCase("MultipleSelect")
                        || crmTemplateField.getTemplateFieldType().equalsIgnoreCase("Select")
                        || crmTemplateField.getTemplateFieldType().equalsIgnoreCase("TextArea")) {
                    queryColumn += "VARCHAR(" + crmTemplateField.getExtFieldName() + ") as "
                            + crmTemplateField.getExtFieldName() + ",";
                } else {
                    queryColumn += crmTemplateField.getExtFieldName() + ",";
                }
            }
            if (queryColumn.equals("")) {
                return null;
            } else {
                queryColumn = queryColumn.substring(0, queryColumn.length() - 1);
                parameters.put("queryColumn", queryColumn);
                parameters.put("customerId", customerId.intValue());
                List list = crmTemplateFieldService.getCrmCustomerExtFieldByCustomerId(parameters);
                Map fieldValue = (Map) list.get(0);
                JSONArray jsonArray = new JSONArray();
                for (CrmTemplateField crmJson : fieldList) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    if (crmJson.getTemplateFieldType().equals("Date")) {
                        if (fieldValue.get(crmJson.getExtFieldName()) != null) {
                            map.put("fieldName", crmJson.getTemplateFieldName());
                            map.put("fieldValue", (new java.text.SimpleDateFormat("yyyy-MM-dd"))
                                    .format(fieldValue.get(crmJson.getExtFieldName())));
                        } else {
                            map.put("fieldName", crmJson.getTemplateFieldName());
                            map.put("fieldValue", null);
                        }
                    } else if (crmJson.getTemplateFieldType().equals("Number")) {
                        if (fieldValue.get(crmJson.getExtFieldName()) != null) {
                            map.put("fieldName", crmJson.getTemplateFieldName());
                            map.put(
                                    "fieldValue",
                                    TypeUtil.changeType(fieldValue.get(crmJson.getExtFieldName()),
                                            String.class) + crmJson.getMeasurement());
                        } else {
                            map.put("fieldName", crmJson.getTemplateFieldName());
                            map.put("fieldValue", null);
                        }
                    } else if (crmJson.getTemplateFieldType().equals("YesNo")) {
                        map.put("fieldName", crmJson.getTemplateFieldName());
                        Object value = fieldValue.get(crmJson.getExtFieldName());
                        String convertValue = "";
                        if (value != null) {
                            if (value.toString().equalsIgnoreCase("yes")) {
                                convertValue = "是";
                            } else {
                                convertValue = "否";
                            }
                        }
                        map.put("fieldValue", convertValue);
                    } else {
                        map.put("fieldName", crmJson.getTemplateFieldName());
                        map.put("fieldValue", fieldValue.get(crmJson.getExtFieldName()));
                    }
                    jsonArray.add(map);
                }
                logger.info("pad端客户接口getCustomerTemplateInfo完成,customerId:" + customerId + ",templateId:" + templateId);
                return jsonArray.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 业务模板信息
     *
     * @return
     */
    public String getTemplateList() {
        try {
            logger.info("pad端客户接口getTemplateList开始");
            Map<String, Object> map = new HashMap<String, Object>();
            JSONArray jsonArray = new JSONArray();
            List<CrmTemplate> crmTemplateList = crmTemplateService.getAllCrmTemplate();
            if (crmTemplateList.size() > 0) {
                for (CrmTemplate crmTemplate : crmTemplateList) {
                    map.put("templateId", crmTemplate.getTemplateId());
                    map.put("templateName", crmTemplate.getTemplateName());
                    jsonArray.add(map);
                }
            }
            logger.info("pad端客户接口getTemplateList完成");
            return jsonArray.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 客户资料接口
     *
     * @param customerId
     * @param eventId    1.营销 2.申请 3.调查 4.审批 5.落实 6.催收
     * @param type       1.录音  2.照片 3.视频 4.短信 5.彩信 6.资料表
     */
    public String getCustomerData(Integer customerId, Integer eventId, Integer type, Integer pageCount) {
        try {
            logger.info("pad端客户接口getCustomerData开始,customerId:" + customerId + ",eventId:" + eventId + ",type:" + type + ",pageCount:" + pageCount);
            JSONObject jsonObject = new JSONObject();
            JsonConfig jsonConfig = new JsonConfig();
            jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Page page = new Page();
            page.setCurrentPage(pageCount);
            List<CustomerDataInfo> clist = new ArrayList<CustomerDataInfo>();
            Integer rowsAmount = 0;
            Map<String, Object> parameterMap = new HashMap<String, Object>();
            parameterMap.put("customerId", customerId);
            parameterMap.put("eventId", eventId);
            switch (type) {
                case 1:
                    PageUtil<Audio> audioList = dataAudioService.getCustomerAudioDataPage(parameterMap, page, null);
                    rowsAmount = audioList.getPage().getTotalRowsAmount();
                    for (Audio a : audioList.getItems()) {
                        CustomerDataInfo cd = new CustomerDataInfo();
                        cd.setFile(a.getFilePath() + "/" + a.getFileName());
                        cd.setName(a.getAudioName());
                        cd.setRemark(a.getRemark());
                        cd.setSubmitTime(df.format(a.getUploadDate()));
                        cd.setDuration(a.getRecordLength());
                        clist.add(cd);
                    }
                    break;
                case 2:
                    PageUtil<Photo> photoList = dataPhotoService.getCustomerPhotoDataPage(parameterMap, page, null);
                    rowsAmount = photoList.getPage().getTotalRowsAmount();
                    for (Photo a : photoList.getItems()) {
                        CustomerDataInfo cd = new CustomerDataInfo();
                        cd.setFile(a.getFilePath() + "/" + a.getFileName());
                        cd.setName(a.getPhotoName());
                        cd.setRemark(a.getRemark());
                        cd.setSubmitTime(df.format(a.getUploadDate()));
                        clist.add(cd);
                    }
                    break;
                case 3:
                    PageUtil<Video> videoList = dataVideoService.getCustomerVideoDataPage(parameterMap, page, null);
                    rowsAmount = videoList.getPage().getTotalRowsAmount();
                    for (Video a : videoList.getItems()) {
                        CustomerDataInfo cd = new CustomerDataInfo();
                        cd.setFile(a.getFilePath() + "/" + a.getFileName());
                        cd.setName(a.getVideoName());
                        cd.setRemark(a.getRemark());
                        cd.setSubmitTime(df.format(a.getUploadDate()));
                        cd.setDuration(a.getRecordLength());
                        clist.add(cd);
                    }
                    break;
                case 4:
                    PageUtil<Sms> smsList = dataSmsService.getCustomerSmsDataPage(parameterMap, page, null);
                    rowsAmount = smsList.getPage().getTotalRowsAmount();
                    for (Sms a : smsList.getItems()) {
                        CustomerDataInfo cd = new CustomerDataInfo();
                        cd.setRemark(a.getRemark());
                        cd.setSubmitTime(df.format(a.getSendDate()));
                        cd.setSmsContent(a.getSmsContent());
                        cd.setSmsType(a.getSmsType());
                        clist.add(cd);
                    }
                    break;
                case 5:
                    PageUtil<Mms> mmsList = dataMmsService.getCustomerMmsDataPage(parameterMap, page, null);
                    rowsAmount = mmsList.getPage().getTotalRowsAmount();
                    for (Mms a : mmsList.getItems()) {
                        CustomerDataInfo cd = new CustomerDataInfo();
                        cd.setRemark(a.getRemark());
                        cd.setSubmitTime(df.format(a.getSendDate()));
                        cd.setMmsContent(a.getMmsContent());
                        cd.setMmsType(a.getMmsType());
                        cd.setMmsTitle(a.getMmsTitle());
                        clist.add(cd);
                    }
                    break;
                case 6:
                    PageUtil<Form> formList = dataFormService.getCustomerFormDataPage(parameterMap, page, null);
                    rowsAmount = formList.getPage().getTotalRowsAmount();
                    for (Form a : formList.getItems()) {

                        CustomerDataInfo cd = new CustomerDataInfo();
                        cd.setFile(a.getFilePath() + "/" + a.getFileName());
                        cd.setName(a.getFormName());
                        cd.setRemark(a.getRemark());
                        cd.setSubmitTime(df.format(a.getUploadDate()));
                        clist.add(cd);
                    }
                    break;
            }
            CustomerDataPage customerDataPage = new CustomerDataPage();
            customerDataPage.setDataList(clist);
            customerDataPage.setTotalCount(rowsAmount);

            String result = jsonObject.fromObject(customerDataPage, jsonConfig).toString();
            logger.info("pad端客户接口getCustomerData完成,customerId:" + customerId + ",eventId:" + eventId + ",type:" + type + ",pageCount:" + pageCount);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 取市民卡信息
     *
     * @param account
     * @param crmIdCard
     * @return
     */
    @Override
    public String getCrmSMKInfo(String account, String crmIdCard) {
        logger.info("getCrmSMKInfo，account：" + account + ",crmIdCard：" + crmIdCard);
        try {
            JSONArray jsonArray = new JSONArray();
            JsonConfig jsonConfig = new JsonConfig();
            jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
            SysUser sysUser = sysUserService.getAllUserByAccount(account);
            if (sysUser == null) {
                logger.error("没找到此用户");
                return null;
            }
            return null;
        } catch (Exception e) {
            logger.error("LoanWebServiceImpl % checkCustomer", e);
            return null;
        }
    }

    /**
     * 获取客户业务基本信息
     *
     * @param account
     * @param customerId
     * @return
     */
    @Override
    public String getKHYWInfo(String account, Integer customerId) {
        logger.info("getKHYWInfo，account：" + account + ",customerId：" + customerId);
        try {
            JSONArray jsonArray = new JSONArray();
            JsonConfig jsonConfig = new JsonConfig();
            jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
            Map<String, Object> map1 = new HashMap<String, Object>();
            SysUser sysUser = sysUserService.getAllUserByAccount(account);
            if (sysUser == null) {
                logger.error(" getKHYWInfo没有此用户");
                return null;
            }
            BaseCrmCustomer crmCustomer = crmCustomerService.getCrmCustomerById(customerId);
            if (crmCustomer == null) {
                logger.error(" getKHYWInfo未找到此客户");
                return null;
            }

            map1.put("AUM","");
            
            map1.put("SMK", '0');//无市民卡
  
            Integer recordCount = recordInfoService.getRecordCountByCustomerId(customerId);
            map1.put("LXJL", recordCount);//通话记录总数

            Integer rcount = crmCustomerRelativesService.selectRelativesByCustomerId(customerId);
            map1.put("QYXX", rcount);//亲友总数
            Map<String, Object> map2 = new HashMap<String, Object>();
            map2.put("userId", sysUser.getUserId());
            map2.put("customerId", customerId);
            Integer tcount = tskMicroTaskTargetService.getCustomerTaskCount(map2);
            map1.put("RWAP", tcount);//任务安排

            Integer tscount = tskScheduleService.getCusScheduleCount(customerId);
            map1.put("RCAP", tscount);//日常安排

            Integer pcount = tskScheduleService.getCustomerPDTProductCount(map2);
            map1.put("KHYX", pcount);//客户意向产品数
            String isCould = jsonArray.fromObject(map1, jsonConfig).toString();
            logger.info("getKHYWInfo 返回数据" + isCould);
            return isCould;
        } catch (Exception e) {
            logger.error("LoanWebServiceImpl % checkCustomer", e);
            return null;
        }
    }

    /**
     * 客户在我行产品信息
     *
     * @param account
     * @param idCard
     * @return
     */
    @Override
    public String getCrmKHCPInfo(String account, String idCard) {
        logger.info("getCrmKHCPInfo，account：" + account + ",idCard：" + idCard);
        try {
            JSONObject jsonObject = new JSONObject();
            JsonConfig jsonConfig = new JsonConfig();
            jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
            SysUser sysUser = sysUserService.getAllUserByAccount(account);
            if (sysUser == null) {
                logger.error("未找到此用户");
                return null;
            }
            
            int sum = 0;//累加拥有产品数
            Map<String, Map<String, Object>> map2 = new HashMap<String, Map<String, Object>>();
            Map<String, Object> aumMap = new HashMap<String, Object>();
            map2.put("AUM", aumMap);
            aumMap.put("aum", "");
            aumMap.put("crmCkyrj", "");
            aumMap.put("crmCknyj", "");
            aumMap.put("crmSnmcknrj", "");

            Map<String, Object> cxlMap = new HashMap<String, Object>();
            
            Map<String, Object> dklMap = new HashMap<String, Object>();           


            Map<String, Object> lcjjMap = new HashMap<String, Object>();

            
            Map<String, Object> gjsMap = new HashMap<String, Object>();

            Map<String, Object> dllMap = new HashMap<String, Object>();

            Map<String, Object> dflMap = new HashMap<String, Object>();

            Map<String, Object> dsdfMap = new HashMap<String, Object>();

            Map<String, Object> klMap = new HashMap<String, Object>();

            Map<String, Object> dzqdlMap = new HashMap<String, Object>();

            Map<String, Object> qtMap = new HashMap<String, Object>();

            
            aumMap.put("jcxsl", sum + "/38");//拥有产品/所有产品

            if (cxlMap.size() > 0)
                map2.put("储蓄类", cxlMap);
            if (qtMap.size() > 0)
                map2.put("其他", qtMap);
            if (dzqdlMap.size() > 0)
                map2.put("电子渠道类", dzqdlMap);
            if (klMap.size() > 0)
                map2.put("卡类", klMap);
            if (dsdfMap.size() > 0)
                map2.put("代收代付类", dsdfMap);
            if (dflMap.size() > 0)
                map2.put("代发类", dflMap);
            if (dllMap.size() > 0)
                map2.put("代理类", dllMap);
            if (gjsMap.size() > 0)
                map2.put("贵金属", gjsMap);
            if (lcjjMap.size() > 0)
                map2.put("理财、基金类", lcjjMap);
            if (dklMap.size() > 0)
                map2.put("贷款类", dklMap);
            String product = jsonObject.fromObject(map2, jsonConfig).toString();
            logger.info("getCrmKHCPInfo 返回数据" + product);
            return product;
        } catch (Exception e) {
            logger.error("LoanWebServiceImpl % checkCustomer", e);
            return null;
        }
    }

    /**
     * 身份核查
     * @param account
     * @param customerName
     * @param idCard
     * @return
     */
    @Override
    public String checkSFHC(String account, String customerName, String idCard) {
        logger.info("checkSFHC，account：" + account +"customerName:"+customerName+ ",idCard：" + idCard);
        try {
            JSONObject jsonObject = new JSONObject();
            JsonConfig jsonConfig = new JsonConfig();
            jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
            SysUser sysUser = sysUserService.getAllUserByAccount(account);
            if (sysUser == null) {
                logger.error("没找到此用户");
                return "false";
            }
            MessageResult messageResult=null;
            if (messageResult==null){
                logger.info("checkSFHC：实时接口失败");
                return "false";
            }
            Map<String,String> map= new HashMap<String, String>();
            map.put("hcjg",messageResult.getHcjg());
            map.put("hctp",messageResult.getHctp());
            String result = jsonObject.fromObject(map, jsonConfig).toString();
            logger.info("checkSFHC 返回报文" + result);
            return result;
        } catch (Exception e) {
            logger.error("LoanWebServiceImpl % checkSFHC", e);
            return "false";
        }
    }

    /**
     * 获取通告
     * @param account
     * @return
     */
    @Override
    public String getInformInfo(String account) {
        try{
            logger.info("getInformInfo 获取通告"+account);
            SysUser sysUser = sysUserService.getAllUserByAccount(account);
            if (sysUser == null) {
                logger.error("没找到此用户");
                return "false";
            }
            Inform  inform=informService.getInform(1);
            if (null!=inform.getIsOpen()&&inform.getIsOpen()==0)
                return "false";
            return inform.getText();
        }catch (Exception e){
            logger.error("getInformInfo 获取通告失败",e);
            return "false";
        }
    }


    @Override
    public String getCredentailTypeCode() {
        try {
            logger.info("pad端记录接口getCredentailTypeCode开始");
            Map<String, Object> map;
            JSONArray jsonArray = new JSONArray();
            List<CredentialType> credentialTypeList = codetableService.getCredentialType();
            if(!CollectionUtils.isEmpty(credentialTypeList)){
                for (int i = 0; i < credentialTypeList.size(); i++) {

                    map = new HashMap<String, Object>();
                    map.put("sortno", (i+1));
                    map.put("credentail_type_code", credentialTypeList.get(i).getCredentialTypeId());
                    map.put("credentail_type_name",credentialTypeList.get(i).getCredentialTypeName());
                    jsonArray.add(map);
                }
            }

            logger.info("pad端记录接口getCredentailTypeCode完成");
            return jsonArray.toString();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "false";
        }
    }









    public void setCrmCustomerService(CrmCustomerService crmCustomerService) {
        this.crmCustomerService = crmCustomerService;
    }

    public void setSysUserService(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    public void setCodetableService(CdSystemService codetableService) {
        this.codetableService = codetableService;
    }

    public void setCrmCustomerRelativesService(CrmCustomerRelativesService crmCustomerRelativesService) {
        this.crmCustomerRelativesService = crmCustomerRelativesService;
    }

    public void setCrmTemplateFieldService(CrmTemplateFieldService crmTemplateFieldService) {
        this.crmTemplateFieldService = crmTemplateFieldService;
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

    public void setDataAudioService(DataAudioService dataAudioService) {
        this.dataAudioService = dataAudioService;
    }

    public void setDataPhotoService(DataPhotoService dataPhotoService) {
        this.dataPhotoService = dataPhotoService;
    }

    public void setDataVideoService(DataVideoService dataVideoService) {
        this.dataVideoService = dataVideoService;
    }

    public void setDataSmsService(DataSmsService dataSmsService) {
        this.dataSmsService = dataSmsService;
    }

    public void setDataMmsService(DataMmsService dataMmsService) {
        this.dataMmsService = dataMmsService;
    }

    public void setDataFormService(DataFormService dataFormService) {
        this.dataFormService = dataFormService;
    }

    public void setDeptFacadeService(DeptFacadeService deptFacadeService) {
        this.deptFacadeService = deptFacadeService;
    }

    public void setTskMicroTaskTargetService(TskMicroTaskTargetService tskMicroTaskTargetService) {
        this.tskMicroTaskTargetService = tskMicroTaskTargetService;
    }

    public void setRecordInfoService(RecordInfoService recordInfoService) {
        this.recordInfoService = recordInfoService;
    }

    public void setInformService(InformService informService) {
        this.informService = informService;
    }
}
