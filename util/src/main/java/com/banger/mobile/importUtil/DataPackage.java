/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :导入数据组装
 * Author     :yangy
 * Create Date:2012-10-31
 */
package com.banger.mobile.importUtil;

import java.text.DecimalFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.banger.mobile.domain.collection.DataRow;
import com.banger.mobile.domain.model.customer.CrmCustomer;
import com.banger.mobile.domain.model.customer.CrmCustomerExtBean;
import com.banger.mobile.domain.model.customer.CrmCustomerTransfer;
import com.banger.mobile.domain.model.dept.SysDept;
import com.banger.mobile.domain.model.pdtProduct.BuyCustomerBean;
import com.banger.mobile.domain.model.pdtProduct.PdtProduct;
import com.banger.mobile.domain.model.pdtProduct.PdtProductField;
import com.banger.mobile.domain.model.pdtProduct.PdtTemplateField;
import com.banger.mobile.domain.model.system.CrmCustomerIndustry;
import com.banger.mobile.domain.model.system.CrmCustomerType;
import com.banger.mobile.domain.model.templateField.CrmTemplateField;
import com.banger.mobile.domain.model.tskContact.TskImportBean;
import com.banger.mobile.domain.model.user.SysUser;
import com.banger.mobile.util.StringUtil;

/**
 * @author yangyang
 * @version $Id: ImportDatePackage.java,v 0.1 2012-10-31 上午10:46:24 yangyong Exp $
 */
public class DataPackage {



    //设置客户ID
    public void getCustomerId(DataRow dataRow, CrmCustomer crmCustomer) {
        String value = ImportUtil.checkStrNull(dataRow.get("customerId"));
        if(!StringUtil.isNullOrEmpty(value))
            crmCustomer.setCustomerId(Integer.parseInt(value));
    }
    //设置客户姓名
    public void getCustomerName(DataRow dataRow, CrmCustomer crmCustomer) {
        String value = ImportUtil.checkStrNull(dataRow.get("customerName"));
        crmCustomer.setCustomerName(value);
    }

    //设置客户编号
    public void getCustomerNo(DataRow dataRow, CrmCustomer crmCustomer) {
        String value = ImportUtil.checkStrNull(dataRow.get("customerNo"));
        crmCustomer.setCustomerNo(value);
    }


    public void getIdCard(DataRow dataRow, CrmCustomer crmCustomer) {
        String value = ImportUtil.checkStrNull(dataRow.get("idCard"));
        crmCustomer.setIdCard(value);
    }

    //设置客户ID
    public void getCustomerId(DataRow dataRow, CrmCustomer crmCustomer, String coverRecord) {
        String value = ImportUtil.checkStrNull(dataRow.get("customerId"));
            if (!StringUtil.isNullOrEmpty(value))
                crmCustomer.setCustomerId(Integer.parseInt(value));

    }

    //设置客户姓名
    public void getCustomerName(DataRow dataRow, CrmCustomer crmCustomer, String coverRecord) {
        String value = ImportUtil.checkStrNull(dataRow.get("customerName"));
        if (coverRecord == null) {
            crmCustomer.setCustomerName(value.split(",")[0]);
        } else {
            if(crmCustomer.getCustomerId()==null){
                crmCustomer.setCustomerName(value.split(",")[0]);
            }else if (!StringUtil.isNullOrEmpty(value.split(",")[0]) && value.split(",")[1].equals("yes"))
                crmCustomer.setCustomerName(value.split(",")[0]);
        }
    }

    //拼音处理
    public void getCustomerNamePinyin(DataRow dataRow, CrmCustomer crmCustomer, String coverRecord) {
        String value = ImportUtil.checkStrNull(dataRow.get("customerNamePinyin"));
        if (coverRecord == null) {
            crmCustomer.setCustomerNamePinyin(value.split(",")[0]);
        } else {
            if(crmCustomer.getCustomerId()==null){
                crmCustomer.setCustomerNamePinyin(value.split(",")[0]);
            }else if (!StringUtil.isNullOrEmpty(value.split(",")[0]) && value.split(",")[1].equals("yes"))
                crmCustomer.setCustomerNamePinyin(value.split(",")[0]);
        }
    }

    //设置客户编号
    public void getCustomerNo(DataRow dataRow, CrmCustomer crmCustomer, String coverRecord) {
        String value = ImportUtil.checkStrNull(dataRow.get("customerNo"));
        if (coverRecord == null) {
            crmCustomer.setCustomerNo(value.split(",")[0]);
        } else {
            if(crmCustomer.getCustomerId()==null){
                crmCustomer.setCustomerNo(value.split(",")[0]);
            }else if (!StringUtil.isNullOrEmpty(value.split(",")[0]) && value.split(",")[1].equals("yes"))
                crmCustomer.setCustomerNo(value.split(",")[0]);
        }
    }

    //设置性别
    public void getSex(DataRow dataRow, CrmCustomer crmCustomer, String coverRecord) {
        String valueStr = ImportUtil.checkStrNull(dataRow.get("sex"));
        String value = valueStr.split(",")[0];
        if(value.equals("男")||value.equalsIgnoreCase("male")||value.equalsIgnoreCase("m")){
            value="男";
        }else if(value.equals("女")||value.equalsIgnoreCase("female")||value.equalsIgnoreCase("f")){
            value="女";
        }else
            value="";
        if (coverRecord == null) {
            crmCustomer.setSex(value);
        } else {
            if(crmCustomer.getCustomerId()==null){
                crmCustomer.setSex(value);
            }else if (!StringUtil.isNullOrEmpty(value) && valueStr.split(",")[1].equals("yes"))
                crmCustomer.setSex(value);
        }
    }

    //设置称谓
    public void getCustomerTitle(DataRow dataRow, CrmCustomer crmCustomer, String coverRecord) {
        String value = ImportUtil.checkStrNull(dataRow.get("customerTitle"));

        if (coverRecord == null) {
            crmCustomer.setCustomerTitle(value.split(",")[0]);
        } else {
            if(crmCustomer.getCustomerId()==null){
                crmCustomer.setCustomerTitle(value.split(",")[0]);
            }else if (!StringUtil.isNullOrEmpty(value.split(",")[0]) && value.split(",")[1].equals("yes"))
                crmCustomer.setCustomerTitle(value.split(",")[0]);
        }
    }

    //生日
    public void getBirthday(DataRow dataRow, CrmCustomer crmCustomer, String coverRecord) {
        String value = ImportUtil.checkStrNull(dataRow.get("birthday"));
        if (coverRecord == null) {
            if (!StringUtil.isNullOrEmpty(value.split(",")[0]))
                crmCustomer.setBirthday(ImportUtil.parseAllStringToDate(value.split(",")[0]));
        } else {
            if(crmCustomer.getCustomerId()==null){
                crmCustomer.setBirthday(ImportUtil.parseAllStringToDate(value.split(",")[0]));
            }else if (!StringUtil.isNullOrEmpty(value.split(",")[0]) && value.split(",")[1].equals("yes"))
                crmCustomer.setBirthday(ImportUtil.parseAllStringToDate(value.split(",")[0]));
        }
    }

    public void getIdCard(DataRow dataRow, CrmCustomer crmCustomer, String coverRecord) {
        String value = ImportUtil.checkStrNull(dataRow.get("idCard"));
        if (coverRecord == null) {
            crmCustomer.setIdCard(value.split(",")[0]);
        } else {
            if(crmCustomer.getCustomerId()==null){
                if(!StringUtil.isNullOrEmpty(value.split(",")[0]))
                    crmCustomer.setIdCard(value.split(",")[0]);
            }else if (!StringUtil.isNullOrEmpty(value.split(",")[0]) && value.split(",")[1].equals("yes"))
                crmCustomer.setIdCard(value.split(",")[0]);
        }
    }

    public void getCompany(DataRow dataRow, CrmCustomer crmCustomer, String coverRecord) {
        String value = ImportUtil.checkStrNull(dataRow.get("company"));
        if (coverRecord == null) {
            crmCustomer.setCompany(value.split(",")[0]);
        } else {
            if(crmCustomer.getCustomerId()==null){
                crmCustomer.setCompany(value.split(",")[0]);
            }else if (!StringUtil.isNullOrEmpty(value.split(",")[0]) && value.split(",")[1].equals("yes"))
                crmCustomer.setCompany(value.split(",")[0]);
        }
    }

    public void getRemark(DataRow dataRow, CrmCustomer crmCustomer, String coverRecord) {
        String value = ImportUtil.checkStrNull(dataRow.get("remark"));
        if (coverRecord == null) {
            crmCustomer.setRemark(value.split(",")[0]);
        } else {
            if(crmCustomer.getCustomerId()==null){
                crmCustomer.setRemark(value.split(",")[0]);
            }else if (!StringUtil.isNullOrEmpty(value.split(",")[0]) && value.split(",")[1].equals("yes"))
                crmCustomer.setRemark(value.split(",")[0]);
        }
    }

    public void getProvince(DataRow dataRow, CrmCustomer crmCustomer, CrmCustomerTransfer cct,
                            String coverRecord) {
        String value = ImportUtil.checkStrNull(dataRow.get("province"));
        if (coverRecord == null) {
            if (cct.getProvMap().containsKey(value.split(",")[0])) {
                String code = cct.getProvMap().get(value.split(",")[0]);
                crmCustomer.setProvince(code);
            } else if (cct.getProvMap().containsKey(value.split(",")[0] + "省")) {
                String code = cct.getProvMap().get(value.split(",")[0] + "省");
                crmCustomer.setProvince(code);
            }
        } else {
            if (!value.equals("")&&value.split(",")[1].equals("yes")){
                if (cct.getProvMap().containsKey(value.split(",")[0])) {
                    String code = cct.getProvMap().get(value.split(",")[0]);
                    crmCustomer.setProvince(code);
                } else if (cct.getProvMap().containsKey(value.split(",")[0] + "省")) {
                    String code = cct.getProvMap().get(value.split(",")[0] + "省");
                    crmCustomer.setProvince(code);
                }
            }else if(!value.equals("")&&value.split(",")[1].equals("no")&&crmCustomer.getCustomerId()==null){
                if (cct.getProvMap().containsKey(value.split(",")[0])) {
                    String code = cct.getProvMap().get(value.split(",")[0]);
                    crmCustomer.setProvince(code);
                } else if (cct.getProvMap().containsKey(value.split(",")[0] + "省")) {
                    String code = cct.getProvMap().get(value.split(",")[0] + "省");
                    crmCustomer.setProvince(code);
                }
            }
        }
    }

    public void getCity(DataRow dataRow, CrmCustomer crmCustomer, CrmCustomerTransfer cct,
                        String coverRecord) {
        String value = ImportUtil.checkStrNull(dataRow.get("city"));
        if (coverRecord == null) {
            if (cct.getCityMap().containsKey(value.split(",")[0])) {
                String code = cct.getCityMap().get(value.split(",")[0]);
                crmCustomer.setCity(code);
            } else if (cct.getCityMap().containsKey(value.split(",")[0] + "市")) {
                String code = cct.getCityMap().get(value.split(",")[0] + "市");
                crmCustomer.setCity(code);
            }
        } else {
            if (!value.equals("")&&value.split(",")[1].equals("yes")){
                if (cct.getCityMap().containsKey(value.split(",")[0])) {
                    String code = cct.getCityMap().get(value.split(",")[0]);
                    crmCustomer.setCity(code);
                } else if (cct.getCityMap().containsKey(value.split(",")[0] + "市")) {
                    String code = cct.getCityMap().get(value.split(",")[0] + "市");
                    crmCustomer.setCity(code);
                }
            }else if(!value.equals("")&&value.split(",")[1].equals("no")&&crmCustomer.getCustomerId()==null){
                if (cct.getCityMap().containsKey(value.split(",")[0])) {
                    String code = cct.getCityMap().get(value.split(",")[0]);
                    crmCustomer.setCity(code);
                } else if (cct.getCityMap().containsKey(value.split(",")[0] + "市")) {
                    String code = cct.getCityMap().get(value.split(",")[0] + "市");
                    crmCustomer.setCity(code);
                }
            }
        }
    }

    public void getAddress(DataRow dataRow, CrmCustomer crmCustomer, String coverRecord) {
        String value = ImportUtil.checkStrNull(dataRow.get("address"));
        if (coverRecord == null) {
            crmCustomer.setAddress(value.split(",")[0]);
        } else {
            if(crmCustomer.getCustomerId()==null){
                crmCustomer.setAddress(value.split(",")[0]);
            }else if (!StringUtil.isNullOrEmpty(value.split(",")[0]) && value.split(",")[1].equals("yes"))
                crmCustomer.setAddress(value.split(",")[0]);
        }
    }

    public void getMobilePhone1(DataRow dataRow, CrmCustomer crmCustomer, String coverRecord) {
        String value = ImportUtil.checkStrNull(dataRow.get("mobilePhone1"));
        if (coverRecord == null) {
            crmCustomer.setMobilePhone1(value.split(",")[0]);
        } else {
            if(crmCustomer.getCustomerId()==null){
                crmCustomer.setMobilePhone1(value.split(",")[0]);
            }else if (!StringUtil.isNullOrEmpty(value.split(",")[0]) && value.split(",")[1].equals("yes"))
                crmCustomer.setMobilePhone1(value.split(",")[0]);
        }
    }

    public void getMobilePhone2(DataRow dataRow, CrmCustomer crmCustomer, String coverRecord) {
        String value = ImportUtil.checkStrNull(dataRow.get("mobilePhone2"));
        if (coverRecord == null) {
            crmCustomer.setMobilePhone2(value.split(",")[0]);
        } else {
            if(crmCustomer.getCustomerId()==null){
                crmCustomer.setMobilePhone2(value.split(",")[0]);
            }else if (!StringUtil.isNullOrEmpty(value.split(",")[0]) && value.split(",")[1].equals("yes"))
                crmCustomer.setMobilePhone2(value.split(",")[0]);
        }

    }

    public void getPhone(DataRow dataRow, CrmCustomer crmCustomer, String coverRecord) {
        String value = ImportUtil.checkStrNull(dataRow.get("phone"));
        if (coverRecord == null) {
            crmCustomer.setPhone(value.split(",")[0]);
        } else {
            if(crmCustomer.getCustomerId()==null){
                crmCustomer.setPhone(value.split(",")[0]);
            }else  if (!StringUtil.isNullOrEmpty(value.split(",")[0]) && value.split(",")[1].equals("yes"))
                crmCustomer.setPhone(value.split(",")[0]);
        }
    }

    public void getPhoneExt(DataRow dataRow, CrmCustomer crmCustomer, String coverRecord) {
        String value = ImportUtil.checkStrNull(dataRow.get("phoneExt"));
        if (coverRecord == null) {
            crmCustomer.setPhoneExt(value.split(",")[0]);
        } else {
            if(crmCustomer.getCustomerId()==null){
                crmCustomer.setPhoneExt(value.split(",")[0]);
            }else if (!StringUtil.isNullOrEmpty(value.split(",")[0]) && value.split(",")[1].equals("yes"))
                crmCustomer.setPhoneExt(value.split(",")[0]);
        }
    }

    public void getFax(DataRow dataRow, CrmCustomer crmCustomer, String coverRecord) {
        String value = ImportUtil.checkStrNull(dataRow.get("fax"));
        if (coverRecord == null) {
            crmCustomer.setFax(value.split(",")[0]);
        } else {
            if(crmCustomer.getCustomerId()==null){
                crmCustomer.setFax(value.split(",")[0]);
            }else  if (!StringUtil.isNullOrEmpty(value.split(",")[0]) && value.split(",")[1].equals("yes"))
                crmCustomer.setFax(value.split(",")[0]);
        }
    }

    public void getFaxExt(DataRow dataRow, CrmCustomer crmCustomer, String coverRecord) {
        String value = ImportUtil.checkStrNull(dataRow.get("faxExt"));
        if (coverRecord == null) {
            crmCustomer.setFaxExt(value.split(",")[0]);
        } else {
            if(crmCustomer.getCustomerId()==null){
                crmCustomer.setFaxExt(value.split(",")[0]);
            }else if (!StringUtil.isNullOrEmpty(value.split(",")[0]) && value.split(",")[1].equals("yes"))
                crmCustomer.setFaxExt(value.split(",")[0]);
        }
    }

    public void getEmail(DataRow dataRow, CrmCustomer crmCustomer, String coverRecord) {
        String value = ImportUtil.checkStrNull(dataRow.get("email"));
        if (coverRecord == null) {
            crmCustomer.setEmail(value.split(",")[0]);
        } else {
            if(crmCustomer.getCustomerId()==null){
                crmCustomer.setEmail(value.split(",")[0]);
            }else  if (!StringUtil.isNullOrEmpty(value.split(",")[0]) && value.split(",")[1].equals("yes"))
                crmCustomer.setEmail(value.split(",")[0]);
        }
    }

    public void getBelongDeptId(DataRow dataRow, CrmCustomer crmCustomer, CrmCustomerTransfer cct,
                                String coverRecord) {
        String value = ImportUtil.checkStrNull(dataRow.get("belongDeptId"));
        if(StringUtil.isNullOrEmpty(value)){
            return;
        }
        String deptCode = value.split(",")[0];
        boolean isCover = value.split(",")[1].equals("yes");
        SysDept syspt = cct.getDeptMap().get(deptCode);
        if (coverRecord == null) {// 覆盖标志
            if (!StringUtil.isEmpty(deptCode)) {
                if (syspt != null) {
                    crmCustomer.setBelongDeptId(syspt.getDeptId());
                } else
                    crmCustomer.setBelongDeptId(9999);
            } else
                crmCustomer.setBelongDeptId(null);
        } else {
            if (crmCustomer.getCustomerId() == null) {// 是否是系统存在的客户
                if (syspt != null) {
                    crmCustomer.setBelongDeptId(syspt.getDeptId());
                } else {
                    if (StringUtil.isEmpty(deptCode))
                        crmCustomer.setBelongDeptId(null);
                    else
                        crmCustomer.setBelongDeptId(9999);
                }
            } else{
                if(isCover &&!StringUtil.isNullOrEmpty(deptCode)){
                    if(syspt!=null){
                        crmCustomer.setBelongDeptId(syspt.getDeptId());
                    }else{
                        crmCustomer.setBelongDeptId(9999);
                    }
                }else{
                    crmCustomer.setBelongDeptId(null);
                }

            }
        }

    }

    public void getBelongUserId(DataRow dataRow, CrmCustomer crmCustomer, CrmCustomerTransfer cct,
                                String coverRecord) {
        String value = ImportUtil.checkStrNull(dataRow.get("belongUserId"));
        if (StringUtil.isNullOrEmpty(value)) {
            return;
        }
        String userCode = value.split(",")[0];
        boolean isCover = value.split(",")[1].equals("yes");
        if (coverRecord == null) {
            if (!StringUtil.isEmpty(userCode)) {
                SysUser sysUser = cct.getUserMap().get(userCode);
                if (null != sysUser) {
                    crmCustomer.setBelongUserId(sysUser.getUserId());
                } else
                    crmCustomer.setBelongUserId(9999);
            } else
                crmCustomer.setBelongUserId(null);
        } else {
            SysUser sysUser = cct.getUserMap().get(userCode);

            if (crmCustomer.getCustomerId() == null) {
                if (sysUser != null) {
                    crmCustomer.setBelongUserId(sysUser.getUserId());
                } else {
                    if (StringUtil.isEmpty(userCode))
                        crmCustomer.setBelongUserId(null);
                    else
                        crmCustomer.setBelongUserId(9999);
                }
            } else {
                if (isCover && !StringUtil.isNullOrEmpty(userCode)) {
                    if (sysUser != null) {
                        crmCustomer.setBelongUserId(sysUser.getUserId());
                    } else {
                        crmCustomer.setBelongUserId(9999);
                    }
                } else {
//                    crmCustomer.setBelongUserId(null);
                    /** liyb 2013-11-06 update **/
                    crmCustomer.setBelongUserId(sysUser.getUserId());
                }
            }
        }
    }

    //所处行业ID
    public void getCustomerIndustryId(DataRow dataRow, CrmCustomer crmCustomer,
                                      CrmCustomerTransfer cct, String coverRecord) {
        String value = ImportUtil.checkStrNull(dataRow.get("customerIndustryId"));
        CrmCustomerIndustry cci = cct.getCustomerIndustryMap().get(value.split(",")[0]);
        if (coverRecord == null) {
            if (null != cci) {
                crmCustomer.setCustomerIndustryId(cci.getCustomerIndustryId());
            }
        } else {
            if(crmCustomer.getCustomerId()==null&&null != cci){
                crmCustomer.setCustomerIndustryId(cci.getCustomerIndustryId());
            }else if (!StringUtil.isNullOrEmpty(value.split(",")[0]) && value.split(",")[1].equals("yes")) {
                if(cci!=null)
                crmCustomer.setCustomerIndustryId(cci.getCustomerIndustryId());
            }
        }
    }

    //客户类型ID
    public void getCustomerTypeId(DataRow dataRow, CrmCustomer crmCustomer,
                                  CrmCustomerTransfer cct, String coverRecord) {
        String value = ImportUtil.checkStrNull(dataRow.get("customerTypeId"));
        CrmCustomerType cc = cct.getCustomerTypeMap().get(value.split(",")[0]);
        if (cc != null) {
            if (coverRecord == null) {
                    crmCustomer.setCustomerTypeId(cc.getCustomerTypeId());
            } else {
                if (crmCustomer.getCustomerId() == null ) {
                    crmCustomer.setCustomerTypeId(cc.getCustomerTypeId());
                } else if (!StringUtil.isNullOrEmpty(value.split(",")[0]) && value.split(",")[1].equals("yes"))
                    crmCustomer.setCustomerTypeId(cc.getCustomerTypeId());
            }
        }
    }

    //客户业务类型模块
    public void getTemplateIds(DataRow dataRow, CrmCustomer crmCustomer, String coverRecord) {
        String value = ImportUtil.checkStrNull(dataRow.get("templateIds"));
        crmCustomer.setTemplateIds(ImportUtil.delString(value));
    }

    ////////////////////////产品客户//////////////////////////////
    public void getBankAccount(DataRow dataRow, BuyCustomerBean buyCustomer) {
        String value = ImportUtil.checkStrNull(dataRow.get("bankAccount"));
        buyCustomer.setBankAccount(value);
    }

    public void getBuyMoney(DataRow dataRow, BuyCustomerBean buyCustomer) {
        String value = ImportUtil.checkStrNull(dataRow.get("buyMoney"));
        if (!StringUtil.isNullOrEmpty(value)) {
            DecimalFormat df = new DecimalFormat("#.0000");
            double f = Double.parseDouble(value);
            buyCustomer.setBuyMoney(Double.parseDouble(df.format(f)));
        }
    }

    public void getBuyDate(DataRow dataRow, BuyCustomerBean buyCustomer) {
        String value = ImportUtil.checkStrNull(dataRow.get("buyDate"));
        if (!StringUtil.isNullOrEmpty(value))
            buyCustomer.setBuyDate(ImportUtil.parseAllStringToDate(value));
    }

    public void getAccount(DataRow dataRow, BuyCustomerBean buyCustomer) {
        String value = ImportUtil.checkStrNull(dataRow.get("account"));
        buyCustomer.setAccount(value);
    }

    public void getCustomerNo(DataRow dataRow, BuyCustomerBean buyCustomer) {
        String value = ImportUtil.checkStrNull(dataRow.get("customerNo"));
        buyCustomer.setCustomerNo(value);
    }

    public void getCustomerName(DataRow dataRow, BuyCustomerBean buyCustomer) {
        String value = ImportUtil.checkStrNull(dataRow.get("customerName"));
        buyCustomer.setCustomerName(value);
    }

    public void getPhone(DataRow dataRow, BuyCustomerBean buyCustomer) {
        String value = ImportUtil.checkStrNull(dataRow.get("phone"));
        buyCustomer.setPhone(value);
    }

    public void getIdCard(DataRow dataRow, BuyCustomerBean buyCustomer) {
        String value = ImportUtil.checkStrNull(dataRow.get("idCard"));
        buyCustomer.setIdCard(value);
    }

    ////////////////////////////客户扩展字段///////////////////////////////////////////////////////

    ////////////////////////产品客户//////////////////////////////
    public static void getBankAccount(String val, BuyCustomerBean buyCustomer) {
        String value = ImportUtil.checkStrNull(val);
        buyCustomer.setBankAccount(value);
    }

    public static void getBuyMoney(String val, BuyCustomerBean buyCustomer) {
        String value = ImportUtil.checkStrNull(val);
        buyCustomer.setBankAccount(value);
        if (!StringUtil.isNullOrEmpty(value)) {
            DecimalFormat df = new DecimalFormat("#.0000");
            double f = Double.parseDouble(value);
            buyCustomer.setBuyMoney(Double.parseDouble(df.format(f)));
        }
    }

    public static void getBuyDate(String val, BuyCustomerBean buyCustomer) {
        String value = ImportUtil.checkStrNull(val);
        if (!StringUtil.isNullOrEmpty(value))
            buyCustomer.setBuyDate(ImportUtil.parseAllStringToDate(value));
    }

    public static void getAccount(String val, BuyCustomerBean buyCustomer) {
        String value = ImportUtil.checkStrNull(val);
        buyCustomer.setAccount(value);
    }

    public static void getCustomerNo(String val, BuyCustomerBean buyCustomer) {
        String value = ImportUtil.checkStrNull(val);
        buyCustomer.setCustomerNo(value);
    }

    public static void getCustomerName(String val, BuyCustomerBean buyCustomer) {
        String value = ImportUtil.checkStrNull(val);
        buyCustomer.setCustomerName(value);
    }

    public static void getPhone(String val, BuyCustomerBean buyCustomer) {
        String value = ImportUtil.checkStrNull(val);
        buyCustomer.setPhone(value);
    }

    public static void getIdCard(String val, BuyCustomerBean buyCustomer) {
        String value = ImportUtil.checkStrNull(val);
        buyCustomer.setIdCard(value);
    }

    ////////////////////////////客户扩展字段///////////////////////////////////////////////////////
    public void getCustomizedFields(String value, CrmCustomerExtBean extBean, String cols,
                                    CrmCustomerTransfer cct) {
        String fieldstr = "", valuestr = "", templateIds = "1", updatestr = "";
        if (cct.getCustomizedFields().get(cols) != null) {
            CrmTemplateField field = cct.getCustomizedFields().get(cols);
            String type = field.getTemplateFieldType();
            value = value.replaceAll("，", ",");
            String[] arry = value.split(",");
            if (arry.length >= 1) {
                String sb = "";
                for (String string : arry) {
                    String s = cct.getCodeMap().get(string);
                    if (s != null)
                        sb += s + ",";
                }
                if (sb.equals("")) {
                    if (field.getTemplateFieldType().equalsIgnoreCase("MultipleSelect")
                        || field.getTemplateFieldType().equalsIgnoreCase("Select"))
                        value = sb;
                } else
                    value = sb.substring(0, sb.length() - 1);
            }
            //数据库字段名
            String colName = field.getExtFieldName();
            if ((type != null) && (!StringUtil.isEmpty(type.toString()))) {
                String val = "";
                if (type.equalsIgnoreCase("YesNo")) {
                    if (value.equals("是") || value.equalsIgnoreCase("y")
                        || value.equalsIgnoreCase("yes"))
                        val = "'yes'";
                    else
                        val = "''";

                } else if (type.equalsIgnoreCase("Date")) {
                    if (!"".equals(value)) {
                        Date date = ImportUtil.parseAllStringToDate(value);
                        if (date != null) {
                            Calendar c= Calendar.getInstance();
                            c.setTime(date);
                            String dateStr=c.get(Calendar.YEAR)+"-"+c.get(Calendar.MONTH)+"-"+c.get(Calendar.DATE);
                            val = "TO_DATE('" + dateStr + "','yyyy-MM-dd')";
                        }
                    }
                } else if (type.equalsIgnoreCase("Number")) { //数字类型
                    if (ImportUtil.isFloat(value)) {
                        if (value.indexOf('.') > 0) {
                            boolean bool=true;
                            String lstr = value.substring(0, value.indexOf('.'));
                            String rstr = value.substring(value.indexOf('.') + 1);
                            if(lstr.length()>13)
                                bool=false;
                            if(rstr.length()>4)
                                bool=false;
                            if(bool)
                                val = value;
                        }else{
                            if(value.length()<=13)
                                val = value;
                        }
                    }
                }else if(type.equalsIgnoreCase("Text") && value.length() > 100){
                    val = "''";
                }else if(type.equalsIgnoreCase("TextArea")&& value.length() > 400){
                    val = "''";
                }else{
                    val = "'" + value + "'";
                }

                if (StringUtil.isEmpty(extBean.getFieldstr())) {
                    fieldstr = colName;
                    valuestr = val;
                } else {
                    if (!val.equals("") && !val.equals("''")) {
                        fieldstr = fieldstr + "," + colName;
                        valuestr = valuestr + "," + val;
                    }
                }

                if (StringUtil.isEmpty(extBean.getFieldstr())) {
                    if (!val.equals("''") && !val.equals("")) {
                        updatestr = colName + "=" + val;
                    }
                } else {
                    if (!StringUtil.isNullOrEmpty(val)) {
                        if (extBean.getUpdatestr().equals(""))
                            updatestr = colName + "=" + val;
                        else
                            updatestr = updatestr + "," + colName + "=" + val;
                    }
                }
            } else {
                if (StringUtil.isEmpty(extBean.getFieldstr())) {
                    fieldstr = colName;
                    valuestr = "null";
                } else {
                    fieldstr = fieldstr + "," + colName;
                    valuestr = valuestr + "," + "null";
                }
                if (StringUtil.isEmpty(extBean.getUpdatestr())) {
                    updatestr = colName + "=" + "null";
                } else {
                    updatestr = updatestr + "," + colName + "=" + "null";
                }
            }
            if (StringUtil.isEmpty(extBean.getTemplateIds())) {
                templateIds = String.valueOf(field.getTemplateId());
            } else {
                if (!extBean.getTemplateIds().equals(field.getTemplateId()))
                    templateIds = templateIds + "," + String.valueOf(field.getTemplateId());
            }

        }
        if (extBean.getTemplateIds() != null) {
            if (!templateIds.equals("1")) {
                if (!ImportUtil.DelRepetStr(extBean.getTemplateIds(), templateIds)) {
                    extBean.setTemplateIds(extBean.getTemplateIds() + "," + templateIds);
                }
            }
        } else {
            if (!templateIds.equals("1"))
                extBean.setTemplateIds(templateIds);
        }
        if (extBean.getFieldstr() != null)
            extBean.setFieldstr(extBean.getFieldstr() + fieldstr);
        else
            extBean.setFieldstr(fieldstr);
        if (extBean.getValuestr() != null)
            extBean.setValuestr(extBean.getValuestr() + valuestr);
        else
            extBean.setValuestr(valuestr);
        if (extBean.getUpdatestr() != null)
            extBean.setUpdatestr(extBean.getUpdatestr() + updatestr);
        else
            extBean.setUpdatestr(updatestr);
    }

    ////////////////////////任务客户导入//////////////////////////////
    public void getPhoneNo(DataRow dataRow, TskImportBean tskCustomer) {
        String value = ImportUtil.checkStrNull(dataRow.get("phoneNo"));
        tskCustomer.setPhoneNo(value);
    }

    public void getCustomerName(DataRow dataRow, TskImportBean tskCustomer) {
        String value = ImportUtil.checkStrNull(dataRow.get("customerName"));
        tskCustomer.setCustomerName(value);
    }

    public void getAccount(DataRow dataRow, TskImportBean tskCustomer) {
        String value = ImportUtil.checkStrNull(dataRow.get("account"));
        tskCustomer.setAccount(value);
    }

    public void getCustomerNo(DataRow dataRow, TskImportBean tskCustomer) {
        String value = ImportUtil.checkStrNull(dataRow.get("customerNo"));
        tskCustomer.setCustomerNo(value);
    }

    ////////////////////////产品导入//////////////////////////////
    public void getTemplateName(DataRow dataRow, PdtProduct pdtProduct) {
        String value = ImportUtil.checkStrNull(dataRow.get("templateId"));
        pdtProduct.setTemplateName(value);
    }

    public void getProductName(DataRow dataRow, PdtProduct pdtProduct) {
        String value = ImportUtil.checkStrNull(dataRow.get("productName"));
        pdtProduct.setProductName(value);
    }

    public void getProductCode(DataRow dataRow, PdtProduct pdtProduct) {
        String value = ImportUtil.checkStrNull(dataRow.get("productCode"));
        pdtProduct.setProductCode(value);
    }

    public void getSaleStartDate(DataRow dataRow, PdtProduct pdtProduct) {
        String value = ImportUtil.checkStrNull(dataRow.get("saleStartDate"));
        pdtProduct.setSaleStartDate(ImportUtil.parseAllStringToDate(value));
    }

    public void getSaleEndDate(DataRow dataRow, PdtProduct pdtProduct) {
        String value = ImportUtil.checkStrNull(dataRow.get("saleEndDate"));
        pdtProduct.setSaleEndDate(ImportUtil.parseAllStringToDate(value));
    }

    /////////////////////////产品导入 自定义字段//////////////////////////
    /**
     * @param value 单元格值
     * @param extBean 自定义字段实体
     * @param cols 字段名称
     * @param cct
     */
    public void getProductFields(String value, PdtProductField extBean, String cols,
                                 CrmCustomerTransfer cct) {
        HttpServletRequest request = org.apache.struts2.ServletActionContext.getRequest();
        Integer templateId = Integer.parseInt("" + request.getSession().getAttribute("templateId")); //模板Id
        List<PdtTemplateField> feilds = cct.getFeildMap().get(templateId);//模板下所有的字段
        Map<String, PdtTemplateField> typeMap = new HashMap<String, PdtTemplateField>();
        if (feilds != null) {
            for (PdtTemplateField pt : feilds) {
                typeMap.put(pt.getTemplateFieldName(), pt);
            }
        }

        /****自定义字段*****/
        if (typeMap.containsKey(cols)) {
            PdtTemplateField field = (PdtTemplateField) typeMap.get(cols);
            String type = field.getTemplateFieldType();//字段类型
            if (value == null)
                value = "";
            value = value.trim();
            if (type.equalsIgnoreCase("MultipleSelect")) {//多选下拉
                List<String> codedatas = cct.getCodedataMap().get(field.getTemplateFieldId());//字段下拉选择项
                if (codedatas != null) {
                    value = value.replaceAll("，", ",");
                    String[] arry = value.split(",");
                    if (arry.length >= 1) {
                        boolean fb = false;
                        for (String code : arry) {
                            if (!codedatas.contains(code)) {//单元格下拉项不存在
                                fb = true;
                                break;
                            }
                        }
                        if (!fb) {
                            extBean.setFieldValueString(value);
                        }
                    }
                }
            } else if (type.equalsIgnoreCase("Select")) {//单选下拉
                List<String> codedatas = cct.getCodedataMap().get(field.getTemplateFieldId());//字段下拉选择项
                if (codedatas != null && codedatas.contains(value))
                    extBean.setFieldValueString(value);
            } else if (type.equalsIgnoreCase("YesNo")) {
                if (value.equals("是") || value.equalsIgnoreCase("y")
                    || value.equalsIgnoreCase("yes"))
                    extBean.setFieldValueBoolean(1);
                else if (value.equals("否") || value.equalsIgnoreCase("n")
                         || value.equalsIgnoreCase("no"))
                    extBean.setFieldValueBoolean(0);
            } else if (type.equalsIgnoreCase("Date")) {
                if (!"".equals(value)) {
                    Date date = ImportUtil.parseAllStringToDate(value);
                    if (date != null && !date.toLocaleString().contains("1970")) {
                        extBean.setFieldValueDatetime(date);
                    }
                }
            } else if (type.equalsIgnoreCase("Number")) {
                if (ImportUtil.isDouble(value)) {
                    extBean.setFieldValueNumeric(Double.parseDouble(value));
                }
            } else if (type.equalsIgnoreCase("Text") || type.equalsIgnoreCase("TextArea")) {
                extBean.setFieldValueString(value);
            }
            extBean.setTemplateId(templateId);
            extBean.setTemplateFieldId(field.getTemplateFieldId());
            extBean.setTemplateFieldType(field.getTemplateFieldType());
        }

    }
}
