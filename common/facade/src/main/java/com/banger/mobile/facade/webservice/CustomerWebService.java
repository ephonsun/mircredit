/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :xuhj
 * Create Date:2013-3-14
 */
package com.banger.mobile.facade.webservice;

import java.util.Date;

import javax.jws.WebService;

/**
 * @author xuhj
 * @version $Id: CustomerWebService.java,v 0.1 2013-3-14 下午1:59:03 xuhj Exp $
 */
@WebService
public interface CustomerWebService {
    /**
     * 保存客户全部信息
     * @param account               当前用户账号
     * @param crmCustomerJson       客户实体json
     */
    public String saveCustomer(String account, String crmCustomerJson);

    String delCustomer(String account, Integer customerId);

    /**
     * 我的客户列表
     * @param account       当前登录账号
     * @param condition     查询条件
     * @param page          分页
     * @return
     */
    public String getCustomerList(String account, String condition, Integer page, String belongToType);
    
    /**
     * 查询客户亲友列表
     * @param account       当前登录账号
     * @param cusId         客户ID
     * @param page          分页
     * @return
     */
    public String getRelativesCustomerList(String account, String cusId, Integer page);
    
    /**
     * PAD 根据客户ID得到实体
     * @param customerId
     * @return json string
     */
    public String getCustomerInfo(Integer customerId);
    
    /**
     * 更新最近联系时间
     */
    public void updateLastContactDate(Integer customerId, Date lastContactDate,
                                      String lastContactType);
    /**
     * 客户自定义业务模板信息
     * @param customerId
     * @param templateId
     * @return
     */
    public String getCustomerTemplateInfo(Integer customerId, Integer templateId);
    
    /**
     * 业务模板信息 
     * @return
     */
    public String getTemplateList();
    
    /**
     * 客户资料接口
     * @param customerId
     * @param eventId 1.营销 2.申请 3.调查 4.审批 5.落实 6.催收
     * @param type 1.录音  2.照片 3.视频 4.短信 5.彩信 6.资料表
     * @param page 页数
     * @param crmCustomerService
     */
    public String getCustomerData(Integer customerId,Integer eventId,Integer type,Integer pageCount);


    public String getCrmSMKInfo(String account,String crmIdCard);

    public String getKHYWInfo(String account,Integer customerId);

    public String getCrmKHCPInfo(String account,String idCard);

    public String checkSFHC(String account,String customerName,String idCard);

    public String getInformInfo(String account);

    public String getCredentailTypeCode();



}
