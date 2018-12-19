/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :导入数据验证
 * Author     :yangy
 * Create Date:2012-10-31
 */
package com.banger.mobile.importUtil;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.banger.mobile.domain.Enum.customer.EnumCustomer;
import com.banger.mobile.domain.collection.DataRow;
import com.banger.mobile.domain.collection.DataTable;
import com.banger.mobile.domain.model.base.customer.BaseCrmCustomer;
import com.banger.mobile.domain.model.base.customer.BaseFamilyName;
import com.banger.mobile.domain.model.customer.CrmCustomer;
import com.banger.mobile.domain.model.customer.CrmCustomerTransfer;
import com.banger.mobile.domain.model.dept.SysDept;
import com.banger.mobile.domain.model.pdtProduct.BuyCustomerBean;
import com.banger.mobile.domain.model.user.IUserInfo;
import com.banger.mobile.domain.model.user.SysUser;
import com.banger.mobile.util.IDCardUtil;
import com.banger.mobile.util.PhoneUtil;
import com.banger.mobile.util.StringUtil;

/**
 * @author yangyang
 * @version $Id: ImportDataValidator.java,v 0.1 2012-10-31 上午10:48:17 yangyong Exp $
 */
public class DataValidator {

	private static final Logger logger = Logger.getLogger(DataValidator.class);
    /**
     * 业务逻辑判断，生成保存/更新实体
     * @param dt
     * @param cct
     */
    public void validatorCustomer(DataTable dt, CrmCustomerTransfer cct) {
        HttpServletRequest request = org.apache.struts2.ServletActionContext.getRequest();
        List<CrmCustomer> addCustomerList = new ArrayList<CrmCustomer>();
        List<CrmCustomer> updateCustomerList = new ArrayList<CrmCustomer>();
        DataPackage cidp = new DataPackage();
        String coverRecord = request.getParameter("coverRecord");//记录是否覆盖
        CrmCustomer crmCustomer = null;
        String failDescriptionStr = "";
        for (int i = 1; i < dt.rowSize(); i++) {
            DataRow dataRow = dt.getRow(i);
            if (StringUtil.isEmpty(ImportUtil.checkStrNull(dataRow.get("errorMsg")))) {
                try {
                    crmCustomer = new CrmCustomer();
                    String rowNo="" + dataRow.get("rowNo");
                    cidp.getCustomerNo(dataRow, crmCustomer, coverRecord);
                    cidp.getCustomerId(dataRow, crmCustomer, coverRecord);
                    changeData(crmCustomer, cct, true,rowNo);
                    cidp.getCustomerName(dataRow, crmCustomer, coverRecord);
                    cidp.getAddress(dataRow, crmCustomer, coverRecord);
                    cidp.getBelongUserId(dataRow, crmCustomer, cct, coverRecord);
                    cidp.getBirthday(dataRow, crmCustomer, coverRecord);
                    cidp.getCity(dataRow, crmCustomer, cct, coverRecord);
                    cidp.getCompany(dataRow, crmCustomer, coverRecord);
                    cidp.getCustomerIndustryId(dataRow, crmCustomer, cct, coverRecord);
                    cidp.getCustomerTypeId(dataRow, crmCustomer, cct, coverRecord);
                    cidp.getSex(dataRow, crmCustomer, coverRecord);
                    cidp.getIdCard(dataRow, crmCustomer, coverRecord);
                    cidp.getCustomerTitle(dataRow, crmCustomer, coverRecord);
                    cidp.getFax(dataRow, crmCustomer, coverRecord);
                    cidp.getEmail(dataRow, crmCustomer, coverRecord);
                    cidp.getFaxExt(dataRow, crmCustomer, coverRecord);
                    cidp.getPhone(dataRow, crmCustomer, coverRecord);
                    cidp.getPhoneExt(dataRow, crmCustomer, coverRecord);
                    cidp.getProvince(dataRow, crmCustomer, cct, coverRecord);
                    cidp.getMobilePhone1(dataRow, crmCustomer, coverRecord);
                    cidp.getRemark(dataRow, crmCustomer, coverRecord);
                    cidp.getMobilePhone2(dataRow, crmCustomer, coverRecord);
                    if (crmCustomer.getCustomerId() == null)
                        cidp.getTemplateIds(dataRow, crmCustomer, coverRecord);
                    cidp.getBelongDeptId(dataRow, crmCustomer, cct, coverRecord);
                    changeData(crmCustomer, cct, false,rowNo);
                    String regex = "^[0-9#*]*$";
                    if (!StringUtil.isEmpty(crmCustomer.getPhoneExt())) {//分机格式允许输入数字、#、*，长度不能超过10
                        if (!crmCustomer.getPhoneExt().matches(regex)) {
                            failDescriptionStr = EnumCustomer.IMPORT_FORMATFIELD_EXT.getValue();
                            dataRow.set("errorMsg", failDescriptionStr);
                            continue;
                        }
                    }
                    if (!StringUtil.isEmpty(crmCustomer.getFaxExt())) {
                        if (!crmCustomer.getFaxExt().matches(regex)) {
                            failDescriptionStr = EnumCustomer.IMPORT_FORMATFIELD_EXT.getValue();
                            dataRow.set("errorMsg", failDescriptionStr);
                            continue;
                        }
                    }
                    if (crmCustomer.getCustomerName() == null) {//客户姓名不能够为空
                        failDescriptionStr = EnumCustomer.IMPORT_CRMCUSTOMER_NAME_NOT_NULL
                            .getValue();
                        dataRow.set("errorMsg", failDescriptionStr);
                        continue;
                    }
                    if (crmCustomer.getBelongDeptId() == null
                        && crmCustomer.getBelongUserId() == null) {//机构，人员都为空
                    	if(crmCustomer.getCustomerId()==null){
	                        failDescriptionStr = EnumCustomer.IMPORT_USER_DEPT_NOT_NULL.getValue();
	                        dataRow.set("errorMsg", failDescriptionStr);
	                        continue;
                    	}
                    }
                    if (crmCustomer.getBelongDeptId() != null
                        && crmCustomer.getBelongDeptId().equals(9999)) {//归属机构在系统中不存在时，并且不为空的情况下
                    	if(crmCustomer.getCustomerId()!=null){
                    		crmCustomer.setBelongDeptId(null);
                    	}else{
	                        failDescriptionStr = EnumCustomer.IMPORT_DEPT_NOT_SYSTEM.getValue();
	                        dataRow.set("errorMsg", failDescriptionStr);
	                        continue;
                    	}
                    }
                    if (crmCustomer.getBelongUserId() != null
                        && crmCustomer.getBelongUserId().equals(9999)) {//归属人员在系统中不存在时，并且不为空的情况下
                    	if(crmCustomer.getCustomerId()!=null){
                    		crmCustomer.setBelongUserId(null);
                    	}else{
	                        failDescriptionStr = EnumCustomer.IMPORT_USER_NOT_SYSTEM.getValue();
	                        dataRow.set("errorMsg", failDescriptionStr);
	                        continue;
                    	}
                    }
                    SysUser user = null;
                    if (crmCustomer.getBelongUserId() != null)
                        user = cct.getUserMap().get(cct.getAccountMap().get(rowNo));
                    SysDept sysDept = null;
                    if (crmCustomer.getBelongDeptId() != null) {
                        sysDept = cct.getDeptMap().get(cct.getDeptCodeMap().get(rowNo));
                    }
                    if (cct.getAdminAndDepartmentMap().get(crmCustomer.getBelongUserId()) != null) {//如果归属人员为admin或者只是机构系统管理员时
//                    	if(crmCustomer.getCustomerId() == null){
//							failDescriptionStr = EnumCustomer.IMPORT_CRMCUSTOMER_BELONGUSERID.getValue()
//									+ cct.getAccountMap().get(rowNo) + EnumCustomer.IMPORT_USER_ADMIN_AND.getValue();
//	                        dataRow.set("errorMsg", failDescriptionStr);
//	                        continue;
//                    	}
                        
                        /** liyb 2013-11-06 update **/
                    	if(crmCustomer.getCustomerId() == null){
                            failDescriptionStr = EnumCustomer.IMPORT_CRMCUSTOMER_BELONGUSERID.getValue()
                                    + cct.getAccountMap().get(rowNo) + EnumCustomer.IMPORT_USER_ADMIN_AND.getValue();
                            dataRow.set("errorMsg", failDescriptionStr);
                            continue;
                        }
                    }
                    if (coverRecord == null) {//未选择覆盖
                        if (crmCustomer != null && crmCustomer.getCustomerId() != null) {//已存在相同客户编号
                            failDescriptionStr = EnumCustomer.IMPORT_CRMCUSTOMER_NOT_NULL
                                .getValue() + crmCustomer.getCustomerName() + "\"!";
                            dataRow.set("errorMsg", failDescriptionStr);
                            continue;
                        }
                    } else {//选择覆盖，执行以下逻辑
                        if (crmCustomer != null)
                            crmCustomer.setCustomerId(crmCustomer.getCustomerId());
                    }
                    if (crmCustomer.getBelongDeptId() == null
                        && crmCustomer.getBelongUserId() != null) {//机构为空，用户名不空的情况
                        if (!cct.isInChargeOfDepartment()) {
                            if (!crmCustomer.getBelongUserId().equals(
                                getUserLoginInfo().getUserId())) {
                                failDescriptionStr = EnumCustomer.IMPORT_FILD_USER.getValue()+ cct.getAccountMap().get(rowNo)
                                                     + EnumCustomer.IMPORT_NOT_YOU_SCOPE.getValue();
                                dataRow.set("errorMsg", failDescriptionStr);
                                continue;
                            }
                        } else {
                            if (!ImportUtil.selectArr(cct.getInChargeOfDeptUserIds(),
                                crmCustomer.getBelongUserId()) && !crmCustomer.getBelongUserId().equals(9999)) {
                                failDescriptionStr = EnumCustomer.IMPORT_FILD_USER.getValue()
                                                     + cct.getAccountMap().get(rowNo) + EnumCustomer.IMPORT_NOT_YOU_SCOPE.getValue();
                                dataRow.set("errorMsg", failDescriptionStr);
                                continue;
                            }
                        }
                        if(user!=null && crmCustomer.getCustomerId()!=null){
                        	crmCustomer.setBelongDeptId(user.getDeptId());
                        }                    
                    }
                    if (user != null && crmCustomer.getBelongDeptId() != null) {//归属人员搜索出来的机构与归属机构不一致时，此记录导入失败
                    	if(crmCustomer.getCustomerId()!=null && cct.getAdminAndDepartmentMap().get(crmCustomer.getBelongUserId()) == null){
                    		crmCustomer.setBelongDeptId(user.getDeptId());
                        } 
                        if (!crmCustomer.getBelongDeptId().equals(0)) {
                            if (!crmCustomer.getBelongDeptId().equals(user.getDeptId())) {
                            	if(crmCustomer.getCustomerId()!=null && cct.getAdminAndDepartmentMap().get(crmCustomer.getBelongUserId()) != null){
                            		crmCustomer.setBelongUserId(0);
                            	}else{
	                                failDescriptionStr = EnumCustomer.IMPORT_FILD_USER.getValue()+ cct.getAccountMap().get(rowNo)
	                                                     + EnumCustomer.IMPORT_NOT_NO_DEPT.getValue()+ cct.getDeptCodeMap().get(rowNo)
	                                                     + EnumCustomer.IMPORT_UNDER.getValue();
	                                dataRow.set("errorMsg", failDescriptionStr);
	                                continue;
                            	}
                            }
                        }
                    }
                    
                    if (crmCustomer.getBelongDeptId() != null
                        && crmCustomer.getBelongUserId() == null) {//机构不空，用户名为空的情况
                    	if(crmCustomer.getCustomerId()!=null){
                    		crmCustomer.setBelongUserId(0);
                    	}else{
	                        if (!cct.isInChargeOfDepartment()) {
	                            failDescriptionStr = EnumCustomer.IMPORT_FILD_DEPT.getValue()
	                                                 +cct.getDeptCodeMap().get(rowNo)
	                                                 + EnumCustomer.IMPORT_NOT_YOU_SCOPE.getValue();
	                            dataRow.set("errorMsg", failDescriptionStr);
	                            continue;
	                        } else {
	                            if (!ImportUtil.selectArr(cct.getInChargeOfDeptIds(),
	                                crmCustomer.getBelongDeptId())) {
	                                failDescriptionStr = EnumCustomer.IMPORT_FILD_DEPT.getValue()
	                                                     + cct.getDeptCodeMap().get(rowNo)
	                                                     + EnumCustomer.IMPORT_NOT_YOU_SCOPE.getValue();
	                                dataRow.set("errorMsg", failDescriptionStr);
	                                continue;
	                            }
	                        }
                    	}
                    }
                    if (crmCustomer.getBelongDeptId() != null
                        && crmCustomer.getBelongUserId() != null&&!crmCustomer.getBelongUserId().equals(0)) {//两个都不为空的情况
                    	if(crmCustomer.getCustomerId()!=null){
                    		if(cct.getAdminAndDepartmentMap().get(crmCustomer.getBelongUserId()) != null){
                    			crmCustomer.setBelongUserId(0);
                    		}
                    	}
                        if (cct.isInChargeOfDepartment()) {
                            if (!ImportUtil.selectArr(cct.getInChargeOfDeptUserIds(),
                                crmCustomer.getBelongUserId())) {//用户不在你的管理范畴
                                failDescriptionStr = EnumCustomer.IMPORT_FILD_USER.getValue()
                                                     + cct.getAccountMap().get(rowNo)
                                                     + EnumCustomer.IMPORT_NOT_YOU_SCOPE.getValue();
                                dataRow.set("errorMsg", failDescriptionStr);
                                continue;
                            }
                            if (sysDept != null && user != null) {
                                if (!ImportUtil.selectArr(cct.getInChargeOfDeptIds(),
                                    crmCustomer.getBelongDeptId())) {//部门不在你的管理范畴
                                    failDescriptionStr = EnumCustomer.IMPORT_FILD_DEPT.getValue()
                                                         + cct.getDeptCodeMap().get(rowNo)
                                                         + EnumCustomer.IMPORT_NOT_YOU_SCOPE
                                                             .getValue();
                                    dataRow.set("errorMsg", failDescriptionStr);
                                    continue;
                                }
                            }
                        } else {
                            if (!crmCustomer.getBelongUserId().equals(
                                getUserLoginInfo().getUserId())) {
                                failDescriptionStr = EnumCustomer.IMPORT_FILD_USER.getValue()
                                                     + cct.getAccountMap().get(rowNo)
                                                     + EnumCustomer.IMPORT_NOT_YOU_SCOPE.getValue();
                                dataRow.set("errorMsg", failDescriptionStr);
                                continue;
                            }
                        }
                    }
                    if(crmCustomer.getBelongUserId()==null){
                        crmCustomer.setBelongUserId(0);
                    }
                    if(crmCustomer.getBelongDeptId()==null){
                        crmCustomer.setBelongDeptId(0);
                    }
                    crmCustomer.setIsDel(0);
                    crmCustomer.setIsTrash(0);
                    crmCustomer.setIsVisit(0);
                    crmCustomer.setIsReceiveSms(1);
                    crmCustomer.setCreateUser(getUserLoginInfo().getUserId());
                    crmCustomer.setUpdateUser(getUserLoginInfo().getUserId());
                    crmCustomer.setLineNumber("" + dataRow.get("rowNo"));
                    if (crmCustomer.getCustomerId() != null && !crmCustomer.getCustomerId().equals("")) {
                        updateCustomerList.add(crmCustomer);
                    } else {
                        addCustomerList.add(crmCustomer);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    String stackTraceStr ="";
                    for(StackTraceElement traceElement : e.getStackTrace()){
                    	stackTraceStr =stackTraceStr+traceElement.toString()+ "\r\n";
             		}
                    dataRow.set("errorMsg", e.getClass().toString()+":"+e.getMessage()+"\r\n" + stackTraceStr);  
                }
            }
        }
        cct.setAddCrmCustomerList(addCustomerList);
        cct.setUpdateCrmCustomerList(updateCustomerList);
    }

    /**
     * 根据部门ID返回部门编号
     * @param cct
     * @param deptId
     * @return
     */
    public static String getDeptMapByNo(CrmCustomerTransfer cct, Integer deptId) {
        Set<String> array = cct.getDeptMap().keySet();
        String temp = "";
        for (String string : array) {
            if (cct.getDeptMap().get(string).getDeptId().equals(deptId)) {
                temp = cct.getDeptMap().get(string).getDeptCode();
            }
        }
        return temp;
    }

    //获得登录信息
    private static IUserInfo getUserLoginInfo() {
        HttpServletRequest req = org.apache.struts2.ServletActionContext.getRequest();
        HttpSession session = req.getSession();
        return (IUserInfo) session.getAttribute("LoginInfo");
    }

    /**
     * 获取客户称谓
     * @throws java.io.IOException
     */
    public String getNickName(String cusName, String cusSex, List<BaseFamilyName> familyNameList) {
        String tempNickName = "";
        if ((!StringUtil.isEmpty(cusName)) && (!StringUtil.isEmpty(cusSex))) {
            String familyNameTemp = cusName;
            for (BaseFamilyName familyName : familyNameList) {
                if (cusName.indexOf(familyName.getFamilyName()) == 0) {
                    familyNameTemp = familyName.getFamilyName();
                    break;
                }
            }
            if (cusSex.equals("男")) {
                tempNickName = familyNameTemp + "先生";
            }
            if (cusSex.equals("女")) {
                tempNickName = familyNameTemp + "女士";
            }
        }
        return tempNickName;
    }

    /**
     * 客户业务字段赋值
     * @param crm
     * @return
     */
    public void changeData(BaseCrmCustomer crm, CrmCustomerTransfer cct, Boolean flag,String rowNo) {
        if (flag) {
            CrmCustomer customer = cct.getRepeatCustomerBeanMap().get(crm.getCustomerNo());
            if (customer != null) {
                try {
                    BeanUtils.copyProperties(crm, customer);
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error("changeData  BeanUtils.copyProperties error:"+e.getMessage());
                    for(StackTraceElement trace: e.getStackTrace()){
                    	logger.error(trace.toString());
                    }
                }
            }
        } else {
            if (crm.getBelongDeptId() == null && crm.getBelongUserId() != null) {// 如果只有归属用户时，则要查找归属用户的归属机构后，在导入
                SysUser user =cct.getUserMap().get(cct.getAccountMap().get(rowNo));
                if (user != null)
                    crm.setBelongDeptId(user.getDeptId());
            }
            if (StringUtil.isNullOrEmpty(crm.getPhone())) {
                crm.setPhoneExt("");
            }
            if (StringUtil.isNullOrEmpty(crm.getFax())) {
                crm.setFaxExt("");
            }
            if (StringUtil.isNullOrEmpty(crm.getFax()) && StringUtil.isNullOrEmpty(crm.getPhone())
                && StringUtil.isNullOrEmpty(crm.getMobilePhone1())
                && StringUtil.isNullOrEmpty(crm.getMobilePhone2())) {
                crm.setPhone("00000000");
                crm.setDefaultPhoneType(3);
            }
            if (!StringUtil.isNullOrEmpty(crm.getFax())) {
                crm.setDefaultPhoneType(4);
            }
            if (!StringUtil.isNullOrEmpty(crm.getPhone())) {
                crm.setDefaultPhoneType(3);
            }
            if (!StringUtil.isNullOrEmpty(crm.getMobilePhone2())) {
                crm.setDefaultPhoneType(2);
            }
            if (!StringUtil.isNullOrEmpty(crm.getMobilePhone1())) {
                if (!crm.getMobilePhone1().equals("")) {
                    if (ImportUtil.isPhoneOrMobilePhone(crm.getMobilePhone1()) == 1)
                        crm.setDefaultPhoneType(1);
                }
            }
            if (!StringUtil.isNullOrEmpty(crm.getPhone())) {
                crm.setPhone(crm.getPhone().replaceAll("-", "").replaceAll("－", ""));
            }
            if (!StringUtil.isNullOrEmpty(crm.getFax())) {
                crm.setFax(crm.getFax().replaceAll("-", "").replaceAll("－", ""));
            }
            if (!StringUtil.isNullOrEmpty(crm.getCustomerName())) {//姓名不为空，得到pinyin
                crm.setCustomerNamePinyin(ImportUtil.getPinYinHeadChar(crm.getCustomerName()));
            }
            if (StringUtil.isNullOrEmpty(crm.getCustomerTitle())) {//如果称谓为空，则默认附上称谓
                crm.setCustomerTitle(getNickName(crm.getCustomerName(), crm.getSex(), cct.getFamilyNameList()));//称谓的处理】
            }
            if (crm.getIdCard() != null && crm.getBirthday() == null && IDCardUtil.validateCard(crm.getIdCard()) ) {//生日的特殊处理。
                crm.setBirthday(ImportUtil.parseStringToDateBeforeToday(IDCardUtil.getBirthByIdCard(crm.getIdCard())));
            }
            if (crm.getBirthday() != null && ImportUtil.getAge(crm.getBirthday()) <= 200 ) {//有生日，则按“生日”生成“年龄”（周岁计算）
            	//年龄如果超过200，则年龄自动置空
                crm.setAge(ImportUtil.getAge(crm.getBirthday()));
            }
            if (crm.getCity() != null && crm.getProvince() == null) {//城市不为空时，如果省份为空时，则置空导入
            	crm.setCity("");
            }
            if (crm.getMobilePhone1() != null && !crm.getMobilePhone1().equals("")) {
                crm.setMobilePhone1Regular(PhoneUtil.getPhoneRule(crm.getMobilePhone1()));
            }
            if (crm.getMobilePhone2() != null && !crm.getMobilePhone2().equals("")) {
                crm.setMobilePhone2Regular(PhoneUtil.getPhoneRule(crm.getMobilePhone2()));
            }
        }
        
    }

    /**
     * 购买客户业务字段处理
     * @param crm
     * @return
     */
    public static void  changeBuyCustomer(CrmCustomer crm, BuyCustomerBean buyCustomer,
                                         CrmCustomerTransfer cct) {
        if (crm.getCustomerId() != null) {//客户ID不为空时为更新
            //客户姓名不相同时，更新客户姓名并修改拼音
            crm.setCustomerName(buyCustomer.getCustomerName());
            crm.setCustomerNamePinyin(ImportUtil.getPinYinHeadChar(crm.getCustomerName()));
            //修改称谓
            crm.setCustomerTitle(crm.getCustomerTitle());
            
            if (buyCustomer.getIdCard()!=null && !crm.getIdCard().equals(buyCustomer.getIdCard())) {//身份证
                crm.setIdCard(buyCustomer.getIdCard());
                if (IDCardUtil.validateCard(crm.getIdCard())) {
                    if (crm.getBirthday().equals(""))
                        crm.setBirthday(ImportUtil.getBirthday(crm.getIdCard()));
                    if (crm.getAge().equals(""))
                        crm.setAge(IDCardUtil.getAgeByIdCard(crm.getIdCard()));
                }
            }
            if (buyCustomer.getPhone() != null && !buyCustomer.getPhone().equals("")) {
                if (ImportUtil.isPhoneOrMobilePhone(buyCustomer.getPhone()) == 1) {// 如果是手机
                    if (crm.getMobilePhone1() == null || crm.getMobilePhone1().equals("")) {//如果手机1为空，则插入到手机1，设置手机1为默认号码
                        crm.setMobilePhone1(buyCustomer.getPhone());
                        crm.setDefaultPhoneType(1);
                    } else if (crm.getMobilePhone2() == null || crm.getMobilePhone2().equals("")) {//如果手机1不为空，手机2为空，则插入到手机2，设置手机2为默认号码。
                        crm.setMobilePhone2(buyCustomer.getPhone());
                        crm.setDefaultPhoneType(2);
                    } else if (crm.getMobilePhone1() != null && !crm.getMobilePhone1().equals("")
                               && crm.getMobilePhone2() != null
                               && !crm.getMobilePhone2().equals("")) {//如果手机1不为空，手机2不为空，则更新手机1的号码，并且随着手机1为默认号码。
                        crm.setMobilePhone1(buyCustomer.getPhone());
                        crm.setDefaultPhoneType(1);
                    }
                } else if (ImportUtil.isPhoneOrMobilePhone(buyCustomer.getPhone()) == 2) {//如果是固话，如果固话为空，则插入到固话。如果固话不为空，则更新固话。
                    crm.setPhone(buyCustomer.getPhone());
                }
            }
        } else {//新增
            if (!buyCustomer.getCustomerName().equals(""))
                crm.setCustomerName(buyCustomer.getCustomerName());
            if (crm.getCustomerName() != "" && crm.getCustomerName() != null) { //拼音处理
                crm.setCustomerNamePinyin(ImportUtil.getPinYinHeadChar(crm
                    .getCustomerName()));
            }
            if (buyCustomer.getIdCard() != null) { //生日的特殊处理。
                crm.setIdCard(buyCustomer.getIdCard());
                if (IDCardUtil.validateCard(crm.getIdCard())) {//如果满足15位或18位，并且满足身份证格式，解析出生日，自动计算出年龄（周岁计算）
                    crm.setBirthday(ImportUtil.parseStringToDateBeforeToday(IDCardUtil
                        .getBirthByIdCard(crm.getIdCard())));
                    crm.setAge(IDCardUtil.getAgeByIdCard(crm.getIdCard()));
                }
            }
            if (buyCustomer.getPhone() != null) {//联系电话不空
                if (ImportUtil.isPhoneOrMobilePhone(buyCustomer.getPhone()) == 1) {
                    crm.setMobilePhone1(buyCustomer.getPhone());
                    crm.setDefaultPhoneType(1);
                }
                if (ImportUtil.isPhoneOrMobilePhone(buyCustomer.getPhone()) == 2) {
                    crm.setPhone(buyCustomer.getPhone());
                    crm.setDefaultPhoneType(3);
                }
            }
            if (buyCustomer.getAccount() != null) {//如果此营销人员为系统用户，则归属机构为此营销人员的机构，归属人员为此营销人员
                SysUser sysUser = cct.getUserMap().get(buyCustomer.getAccount());
                if (sysUser != null) {
                    crm.setBelongUserId(sysUser.getUserId());
                    crm.setBelongDeptId(sysUser.getDeptId());
                } else {
                    crm.setBelongUserId(getUserLoginInfo().getUserId());
                    crm.setBelongDeptId(getUserLoginInfo().getDeptId());
                }
            }
        }
    }
    
}
