/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :录音信息业务实现类
 * Author     :zhangxiang
 * Version    :1.0
 * Create Date:May 3, 2012
 */
package com.banger.mobile.facade.impl.customer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.transaction.annotation.Transactional;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.customer.CrmCustomerDao;
import com.banger.mobile.dao.customer.CustomizedFieldDao;
import com.banger.mobile.dao.customer.MayDiffCustomerDao;
import com.banger.mobile.dao.fieldCodeData.CrmFieldCodeDataDao;
import com.banger.mobile.domain.model.customer.CrmCustomer;
import com.banger.mobile.domain.model.customer.MayDiffCustomerName;
import com.banger.mobile.domain.model.fieldCodeData.CrmFieldCodeData;
import com.banger.mobile.domain.model.templateField.CrmTemplateField;
import com.banger.mobile.facade.customer.MayDiffCustomerService;
import com.banger.mobile.facade.loan.LnLoanService;
import com.banger.mobile.facade.microTask.TskScheduleService;
import com.banger.mobile.facade.templateField.CrmTemplateFieldService;
import com.banger.mobile.util.StringUtil;
import com.ibatis.common.logging.Log;
import com.ibatis.common.logging.LogFactory;

/**
 * 
 * @author yuanme
 * @version MayDiffCustomerServiceImpl.java,v 0.1 2012-8-16 下午3:16:35
 */
public class MayDiffCustomerServiceImpl implements MayDiffCustomerService {

    protected final Log             log = LogFactory.getLog(getClass());
    private CrmCustomerDao          crmCustomerDao;
    private MayDiffCustomerDao      mayDiffCustomerDao;
    private CustomizedFieldDao      customizedFieldDao;
    private CrmFieldCodeDataDao     crmFieldCodeDataDao;
    private CrmTemplateFieldService crmTemplateFieldService;
    private LnLoanService                 lnLoanService;                          //贷款service
    private TskScheduleService            tskScheduleService;                     //日程service

    public void setCrmTemplateFieldService(CrmTemplateFieldService crmTemplateFieldService) {
        this.crmTemplateFieldService = crmTemplateFieldService;
    }

    public void setCustomizedFieldDao(CustomizedFieldDao customizedFieldDao) {
        this.customizedFieldDao = customizedFieldDao;
    }

    public void setCrmFieldCodeDataDao(CrmFieldCodeDataDao crmFieldCodeDataDao) {
        this.crmFieldCodeDataDao = crmFieldCodeDataDao;
    }

    public void setMayDiffCustomerDao(MayDiffCustomerDao mayDiffCustomerDao) {
        this.mayDiffCustomerDao = mayDiffCustomerDao;
    }

    public void setCrmCustomerDao(CrmCustomerDao crmCustomerDao) {
        this.crmCustomerDao = crmCustomerDao;
    }

    /**
     * 分页查询
     */
    public PageUtil<MayDiffCustomerName> getMayDiffCustomerNamePage(Map<String, Object> parameters,
                                                                    Page page) {
        return mayDiffCustomerDao.getMayDiffCustomerNamePage(parameters, page);
    }

    /**
     * 
     * @param parameterMap
     * @return
     * @see com.banger.mobile.facade.customer.MayDiffCustomerService#getSameCustomerList(java.util.Map)
     */
    public List<CrmCustomer> getSameCustomerList(Map<String, Object> parameters) {
        return mayDiffCustomerDao.getSameCustomerList(parameters);
    }

    public String[] merge(Comparable[] arg1, Comparable[] arg2) {
        Collection coll1 = Arrays.asList(arg1);
        Collection coll2 = Arrays.asList(arg2);

        SortedSet sorter = new TreeSet(coll1);

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
     * 
     * @param customer1
     * @param customer2
     * @param crmCustomer
     * @param extFiledMap
     * @see com.banger.mobile.facade.customer.MayDiffCustomerService#mergeCustomer(com.banger.mobile.domain.model.customer.CrmCustomer, com.banger.mobile.domain.model.customer.CrmCustomer, com.banger.mobile.domain.model.customer.CrmCustomer, java.util.Map)
     */
    @Transactional
    public void mergeCustomer(CrmCustomer customer1, CrmCustomer customer2,
                              CrmCustomer crmCustomer, Map<String, Object> extFiledMap) {
        //更新客户1作为 主客户信息
        crmCustomer.setCustomerId(customer1.getCustomerId());
        //合并模版
        String tmp1 = StringUtil.getNotNullValue(customer1.getTemplateIds());
        String[] tmp1Array = tmp1.split(",");
        String tmp2 = StringUtil.getNotNullValue(customer2.getTemplateIds());
        String[] tmp2Array = tmp2.split(",");
        String[] mergeArray = merge(tmp1Array, tmp2Array);
        String ids = "";
        for (String s : mergeArray) {
            ids += s + ",";
        }
        if (ids.length() > 0) {
            ids = ids.substring(0, ids.length() - 1);
        }
        crmCustomer.setTemplateIds(ids);
        if(crmCustomer.getBelongUserId()==null){
        	crmCustomer.setBelongUserId(0);
        }
        crmCustomerDao.updateCrmCustomer(crmCustomer);

        //删除客户二
        mayDiffCustomerDao.deleteCrmCustomer(customer2.getCustomerId());

        //更新客户1 自定义字段
        for (String key : extFiledMap.keySet()) {
            Map<String, String> parameters = new HashMap<String, String>();
            parameters.put("customerId", customer1.getCustomerId().toString());
            String updatestr = StringUtil.getNotNullValue(extFiledMap.get(key));
            if (updatestr.equals("")) {
                parameters.put("updatestr",
                    key + "=NULL ");
            } else {
                parameters.put("updatestr",
                    key + "='" + updatestr + "'");
            }
            
            customizedFieldDao.updateCustomizedField(parameters);
        }

        //联系记录
        Map<String, Object> p1 = new HashMap<String, Object>();
        p1.put("customer1", customer1.getCustomerId());
        p1.put("customer2", customer2.getCustomerId());
        mayDiffCustomerDao.mergeRecordInfo(p1);

        //任务安排
        Map<String, Object> p2 = new HashMap<String, Object>();
        p2.put("customer1", customer1.getCustomerId());
        p2.put("customer2", customer2.getCustomerId());
        mayDiffCustomerDao.mergeTask(p2);
        mayDiffCustomerDao.mergePlan(p2);

        // 亲友信息
        Map<String, Object> p3 = new HashMap<String, Object>();
        p3.put("customer1", customer1.getCustomerId());
        p3.put("customer2", customer2.getCustomerId());
        mayDiffCustomerDao.mergeFriends(p3);

        //短信
        Map<String, Object> p4 = new HashMap<String, Object>();
        p4.put("customer1", customer1.getCustomerId());
        p4.put("customer2", customer2.getCustomerId());
        mayDiffCustomerDao.mergeSMS(p4);

        // 彩信
        Map<String, Object> p5 = new HashMap<String, Object>();
        p5.put("customer1", customer1.getCustomerId());
        p5.put("customer2", customer2.getCustomerId());
        mayDiffCustomerDao.mergeMMS(p5);

        //产品购买记录
        //Map<String, Object> p6 = new HashMap<String, Object>();
        //p6.put("customer1", customer1.getCustomerId());
        //p6.put("customer2", customer2.getCustomerId());
        //mayDiffCustomerDao.mergeProductCustomer(p6);

        //取消共享记录
        //Map<String, Object> p7 = new HashMap<String, Object>();
        //p7.put("customer1", customer1.getCustomerId());
        //p7.put("customer2", customer2.getCustomerId());
        //mayDiffCustomerDao.deleteCustomerSharedInfo(p7);

        //合并贷款和日程
        String needMergeCusIds = customer1.getCustomerId().toString() + ","
                + customer2.getCustomerId().toString();
        lnLoanService.mergeCusLoan(needMergeCusIds, crmCustomer.getCustomerId().toString());
        tskScheduleService.mergeCusSchedule(needMergeCusIds, crmCustomer.getCustomerId().toString());
    }

    /**
     * 手机相同    分页查询 
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<MayDiffCustomerName> getMayDiffCustomerPhonePage(Map<String, Object> parameters,
                                                                     Page page) {
        return mayDiffCustomerDao.getMayDiffCustomerPhonePage(parameters, page);
    }

    /**
     * 身份证相同    分页查询 
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<MayDiffCustomerName> getMayDiffCustomerIdcardPage(Map<String, Object> parameters,
                                                                      Page page) {
        return mayDiffCustomerDao.getMayDiffCustomerIdcardPage(parameters, page);
    }

    /**
     * 姓名固话相同    分页查询 
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<MayDiffCustomerName> getMayDiffCustomerNamePhonePage(Map<String, Object> parameters,
                                                                         Page page) {
        return mayDiffCustomerDao.getMayDiffCustomerNamePhonePage(parameters, page);
    }

    /**
     * 姓名单位相同    分页查询 
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<MayDiffCustomerName> getMayDiffCustomerNameCompanyPage(Map<String, Object> parameters,
                                                                           Page page) {
        return mayDiffCustomerDao.getMayDiffCustomerNameCompanyPage(parameters, page);
    }

    /**
     * 联系记录数
     * @param customerId
     * @return
     */
    public int countRecordInfo(Integer customerId) {
        return mayDiffCustomerDao.countRecordInfo(customerId);
    }

    /**
     * 任务数量
     * @param customerId
     * @return
     */
    public int countTask(Integer customerId) {
        return mayDiffCustomerDao.countTask(customerId);
    }

    /**
     * 亲友数量
     * @param customerId
     * @return
     */
    public int countRelative(Integer customerId) {
        return mayDiffCustomerDao.countRelative(customerId);
    }

    /**
     * 产品数量
     * @param customerId
     * @return
     */
    public int countProduct(Integer customerId) {
        return mayDiffCustomerDao.countProduct(customerId);
    }

    /**
     * 相同姓名数量
     * @param parameterMap
     * @return
     */
    public int getMayDiffCustomerNameNumber(Map<String, Object> parameterMap) {
        return mayDiffCustomerDao.getMayDiffCustomerNameNumber(parameterMap);
    }

    /**
     * 相同姓名数量
     * @param parameterMap
     * @return
     */
    public int getMayDiffCustomerPhoneNumber(Map<String, Object> parameterMap) {
        return mayDiffCustomerDao.getMayDiffCustomerPhoneNumber(parameterMap);
    }

    /**
     * 相同身份证数量
     * @param parameterMap
     * @return
     */
    public int getMayDiffCustomerIdCardNumber(Map<String, Object> parameterMap) {
        return mayDiffCustomerDao.getMayDiffCustomerIdCardNumber(parameterMap);
    }

    /**
     * 相同姓名固话数量
     * @param parameterMap
     * @return
     */
    public int getMayDiffCustomerNamePhoneNumber(Map<String, Object> parameterMap) {
        return mayDiffCustomerDao.getMayDiffCustomerNamePhoneNumber(parameterMap);
    }

    /**
     * 相同姓名公司数量
     * @param parameterMap
     * @return
     */
    public int getMayDiffCustomerNameCompanyNumber(Map<String, Object> parameterMap) {
        return mayDiffCustomerDao.getMayDiffCustomerNameCompanyNumber(parameterMap);
    }

    /**
     * 
     * @param customerId
     * @param fieldList
     * @return
     * @see com.banger.mobile.facade.customer.MayDiffCustomerService#getFieldValue(java.lang.Integer, java.util.List)
     */
    public List<Map<String, Object>> getFieldValue(Integer customerId,
                                                   List<CrmTemplateField> fieldList) {
        String queryColumn = "";
        Map<String, Object> parameters = new HashMap<String, Object>();
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> result2 = new HashMap<String, Object>();

        for (CrmTemplateField crmTemplateField : fieldList) {
            if (crmTemplateField.getTemplateFieldType().equalsIgnoreCase("MultipleSelect")
                    || crmTemplateField.getTemplateFieldType().equalsIgnoreCase("TextArea")) {
                queryColumn += "VARCHAR(" + crmTemplateField.getExtFieldName() + ") as "
                        + crmTemplateField.getExtFieldName() + ",";
            } else {
                queryColumn += crmTemplateField.getExtFieldName() + ",";
            }
        }
        if (!queryColumn.equals("")) {
            queryColumn = queryColumn.substring(0, queryColumn.length() - 1);
            parameters.put("queryColumn", queryColumn);
            parameters.put("customerId", customerId);
            List list = crmTemplateFieldService.getCrmCustomerExtFieldByCustomerId(parameters);
            if (list != null && list.size() > 0) {
                Map fieldValue = (Map) list.get(0);

                for (CrmTemplateField crmJson : fieldList) {
                    result2.put(crmJson.getTemplateFieldId() + "", crmJson.getTemplateFieldType());
                    if (crmJson.getTemplateFieldType().equals("Date")) {
                        if (fieldValue.get(crmJson.getExtFieldName()) != null) {
                            result.put(crmJson.getTemplateFieldId() + "",
                                (new java.text.SimpleDateFormat("yyyy-MM-dd")).format(fieldValue
                                    .get(crmJson.getExtFieldName())));
                        } else {
                            result.put(crmJson.getTemplateFieldId() + "", "");
                        }
                    } else if (crmJson.getTemplateFieldType().equals("Number")) {
                        if (fieldValue.get(crmJson.getExtFieldName()) != null) {
                            result.put(crmJson.getTemplateFieldId() + "",
                                subZeroAndDot(fieldValue.get(crmJson.getExtFieldName())));
                        } else {
                            result.put(crmJson.getTemplateFieldId() + "", "");
                        }
                    } else if (crmJson.getTemplateFieldType().equals("YesNo")) {
                        Object value = fieldValue.get(crmJson.getExtFieldName());
                        String convertValue = "";
                        if (value != null) {
                            if (value.toString().equalsIgnoreCase("yes")) {
                                convertValue = "是";
                            } else {
                                convertValue = "否";
                            }
                        }
                        result.put(crmJson.getTemplateFieldId() + "", convertValue);
                    } else {
                        result.put(crmJson.getTemplateFieldId() + "",
                            fieldValue.get(crmJson.getExtFieldName()));
                    }
                }
            }
        }

        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        resultList.add(result);
        resultList.add(result2);
        return resultList;
    }

    /**
     * 
     * @param s
     * @return
     */
    private String subZeroAndDot(Object s) {
        if (s == null) {
            return "";
        }

        BigDecimal original = new BigDecimal((Double) s);
        BigDecimal dd = original.setScale(2, BigDecimal.ROUND_HALF_DOWN);
        String result = dd.toString();
        if (result.indexOf(".") > 0) {
            result = result.replaceAll("0+?$", "");//去掉多余的0  
            result = result.replaceAll("[.]$", "");//如最后一位是.则去掉  
        }
        return result;
    }

    /**
     * 得到自定义下框拉字段的代码表
     * @return
     */
    public Map<String, List<CrmFieldCodeData>> getTemplateFieldCodes() {
        List<CrmFieldCodeData> codes = crmFieldCodeDataDao.getAllCrmFieldCodeData();
        Map<String, List<CrmFieldCodeData>> cMap = new HashMap<String, List<CrmFieldCodeData>>();
        for (CrmFieldCodeData code : codes) {
            String fId = String.valueOf(code.getFieldId());
            if (!cMap.containsKey(fId))
                cMap.put(fId, new ArrayList<CrmFieldCodeData>());
            List<CrmFieldCodeData> fList = cMap.get(fId);
            fList.add(code);
        }
        return cMap;
    }

    public void setLnLoanService(LnLoanService lnLoanService) {
        this.lnLoanService = lnLoanService;
    }

    public void setTskScheduleService(TskScheduleService tskScheduleService) {
        this.tskScheduleService = tskScheduleService;
    }
    
    
}
