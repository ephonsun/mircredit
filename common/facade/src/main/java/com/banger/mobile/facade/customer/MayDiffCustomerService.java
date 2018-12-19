/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :录音信息业务接口
 * Author     :zhangxiang
 * Version    :1.0
 * Create Date:May 3, 2012
 */
package com.banger.mobile.facade.customer;

import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.customer.CrmCustomer;
import com.banger.mobile.domain.model.customer.MayDiffCustomerName;
import com.banger.mobile.domain.model.fieldCodeData.CrmFieldCodeData;
import com.banger.mobile.domain.model.templateField.CrmTemplateField;

public interface MayDiffCustomerService {

    /**
     * 姓名相同    分页查询 
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<MayDiffCustomerName> getMayDiffCustomerNamePage(Map<String, Object> parameters,
                                                                    Page page);

    /**
     * 取得相同客户
     * @param parameterMap
     * @return
     */
    public List<CrmCustomer> getSameCustomerList(Map<String, Object> parameterMap);

    /**
     * 合并客户
     * @param customer1
     * @param customer2
     * @param crmCustomer
     * @param extFiledMap
     */
    public void mergeCustomer(CrmCustomer customer1, CrmCustomer customer2,
                              CrmCustomer crmCustomer, Map<String, Object> extFiledMap);

    /**
     * 手机相同    分页查询 
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<MayDiffCustomerName> getMayDiffCustomerPhonePage(Map<String, Object> parameterMap,
                                                                     Page page);

    /**
     * 身份证相同    分页查询 
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<MayDiffCustomerName> getMayDiffCustomerIdcardPage(Map<String, Object> parameterMap,
                                                                      Page page);

    /**
     * 姓名固话相同    分页查询 
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<MayDiffCustomerName> getMayDiffCustomerNamePhonePage(Map<String, Object> parameterMap,
                                                                         Page page);

    /**
     * 姓名单位相同    分页查询 
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<MayDiffCustomerName> getMayDiffCustomerNameCompanyPage(Map<String, Object> parameterMap,
                                                                           Page page);

    /**
     * 联系记录数
     * @param customerId
     * @return
     */
    public int countRecordInfo(Integer customerId);

    /**
     * 任务数
     * @param customerId
     * @return
     */
    public int countTask(Integer customerId);

    /**
     * 亲友数
     * @param customerId
     * @return
     */
    public int countRelative(Integer customerId);

    /**
     * 产品数
     * @param customerId
     * @return
     */
    public int countProduct(Integer customerId);

    /**
     * 相同姓名数量
     * @param parameterMap
     * @return
     */
    public int getMayDiffCustomerNameNumber(Map<String, Object> parameterMap);

    /**
     * 相同姓名数量
     * @param parameterMap
     * @return
     */
    public int getMayDiffCustomerPhoneNumber(Map<String, Object> parameterMap);

    /**
     * 相同身份证数量
     * @param parameterMap
     * @return
     */
    public int getMayDiffCustomerIdCardNumber(Map<String, Object> parameterMap);

    /**
     * 相同姓名固话数量
     * @param parameterMap
     * @return
     */
    public int getMayDiffCustomerNamePhoneNumber(Map<String, Object> parameterMap);

    /**
     * 相同姓名公司数量
     * @param parameterMap
     * @return
     */
    public int getMayDiffCustomerNameCompanyNumber(Map<String, Object> parameterMap);

    /**
     * 得到自定义字段内容和类型
     * @param customerId
     * @param fieldList
     * @return
     */
    public List<Map<String, Object>> getFieldValue(Integer customerId,
                                                   List<CrmTemplateField> fieldList);
    /**
     * 获得模版字段code
     * @return
     */
    public Map<String, List<CrmFieldCodeData>> getTemplateFieldCodes();
}
