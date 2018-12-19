package com.banger.mobile.facade.webservice;

import javax.jws.WebService;

/**
 * User: yangy
 * Date: 13-8-13
 * Time: 下午5:24
 * To change this template use File | Settings | File Templates.
 */
@WebService
public interface CreditsMallWebServices {

    /**
     * 客户查询接口
     *
     * @param account      用户名
     * @param customerName 客户姓名
     * @param idCard       客户身价证
     * @return
     */
    public String queryCustomer(String account, String customerName, String idCard);

    /**
     * 客户详情接口
     *
     * @param account      用户名
     * @param customerCode 客户编号
     * @return
     */
    public String getCustomerDetail(String account, String customerCode);

    /**
     * 客户AUM信息接口
     *
     * @param account      用户名
     * @param customerCode 客户编号
     * @return
     */
    public String getCustomerAum(String account, String customerCode);


    /**
     * 礼品信息查询接口
     *
     * @param queryType 商品种类
     * @param keyword   商品名称
     * @param orderType 排序类型
     * @param pageCount 每页条数
     * @param jumpPages 第几页
     * @return
     */
    public String queryGift(String queryType, String keyword, String orderType, String pageCount, String jumpPages);
    
    /**
     * 礼品编号查询接口
     *
     * @param giftNo 礼品编号
     * @return
     */
    public String queryGiftNo(String giftNo);

    /**
     * 礼品积分范围查询接口
     *
     * @param pointAreaMin 积分下限
     * @param pointAreaMax 积分上限
     * @return
     */
    public String queryGiftMinMax(String pointAreaMin, String pointAreaMax, String queryType, String orderType, String pageCount,String jumpPages);

    /**
     * 积分兑换交易接口
     *
     * @param exchangeJson 交易JSON
     * @return
     */
    public String exchangeGift(String exchangeJson);

    /**
     * 订单查询接口
     *
     * @param hostNo    核心客户号
     * @param pageCount 每页条数
     * @param jumpPages 跳转页数
     * @return
     */
    public String queryOrder(String hostNo,String beginDate,String endDate, String pageCount, String jumpPages);

    /**
     * 订单详细查询接口
     *
     * @param orderNo  核心客户号
     * @return
     */
    public String queryOrderDetail(String orderNo);

    /**
     * 礼品订单撤单接口
     *
     * @param orderNo    订单号
     * @param hostNo      核心客户号
     * @return
     */
    public String cancelOrder(String orderNo, String hostNo);

    /**
     * 积分查询接口
     *
     * @param hostNo
     * @return
     */
    public String queryCustomerPoint(String hostNo);

    /**
     * 积分明细接口
     *
     * @param hostNo
     * @param pageCount
     * @param jumpPages
     * @return
     */
    public String queryCustomerPointDetail(String hostNo, String pageCount, String jumpPages);


    /**
     * 查询我的客户接口
     * @param account
     * @param input
     * @return
     */
    public String searchMyCustomer(String account, String input,Integer page);

    /**
     * 移除我的客户接口
     * @param account
     * @param customerCodeArray
     * @return
     */
    public String removeMyCustomer(String account, String customerCodeArray);

    /**
     * 添加我的客户接口
     * @param account
     * @param customerCode
     * @param customerName
     * @param belongToDept
     * @param idCard
     * @param phoneNumber1
     * @return
     */
    public String addMyCustomer(String account, String customerCode, String customerName, String belongToDept, String idCard, String phoneNumber1, String aumValue);

    String getOrderPhoto(String account, String orderNo);
}
