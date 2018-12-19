package com.banger.mobile.facade.webservice;

import javax.jws.WebService;

@WebService
public interface ProductWebService {
    /**
     * 所有产品
     */
    public String getAllProductMicro(String account, String input, Integer pageNumber);

    /**
     * 所有产品总数
     */
    public Integer getAllProductMicroCount(String account, String input);

    /**
     * 产品购买客户列表
     */
    public String getProductCustomerMicro(String account, Integer productId, String input,
                                          Integer pageCount);

    /**
     * 根据产品id查询购买总数
     */
    public Integer getProductCustomerMicroCount(String account, String input, Integer productId);

    /**
     * 产品添加意向客户
     * 
     * @param productId
     * @param customerJson
     * @return
     */
    public String addProductCustomer(Integer productId, String customerJson);

    /**
     * 意向客户添加日程
     */
    public String addSchedule(String account, Integer scheduleId, Integer customerId,
                               Integer contactWayType, String contactTime, String remark);

    /**
     * 发送产品信息邮件
     */
    public boolean sendProductInfo(Integer customerId, Integer productId, String email,
                                   String remark);

    /** 通知列表 */
    public String getInformList(String account, Integer isRead, String name, Integer pageNumber);

    /**
     * 通知列表总数
     */
    public Integer getInformListCount(String account, String name, Integer isRead);

    /**
     * 批量设置通知为已读
     */
    public boolean setAllInformRead(String account, String informIdJson);

    /**
     * 批量设置通知为未读
     */
    public boolean setAllInformUnread(String account, String informIdJson);

    /**
     * 知识库列表
     */
    public String getAllLibrary(Integer parentId, String input, Integer allSearch, Integer pageNumber);

    //3.46	添加意向客户-搜索客户（客户姓名）
    public String queryCustomerByName(String account, String customerName);

    //3.46	添加意向客户-搜索客户（联系电话）
    public String queryCustomerByPhone(String account, String phone);

    //3.47	添加意向客户-搜索客户（地址）
    public String queryCustomerByAddress(String account, String address);

    String checkIntention(String account, Integer customerId, Integer productId);
}
