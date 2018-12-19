/*
 * banger Inc.
 * Copyright (c) 2009-2013 All Rights Reserved.
 * ToDo       :积分商城业务类...
 * Author     :yangy
 * Create Date:2013-3-14
 */
package com.banger.mobile.webservice.service.impl;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.points.JfMyCustomerDao;
import com.banger.mobile.domain.model.points.JfMyCustomer;
import com.banger.mobile.domain.model.points.JfOrderPhoto;
import com.banger.mobile.domain.model.uploadFile.SysUploadFile;
import com.banger.mobile.domain.model.user.SysUser;
import com.banger.mobile.facade.points.JfOrderPhotoService;
import com.banger.mobile.facade.transport.ClientSocketCreditsMallService;
import com.banger.mobile.facade.user.SysUserService;
import com.banger.mobile.facade.webservice.CreditsMallWebServices;
import com.banger.mobile.util.DateUtil;
import com.banger.mobile.util.JsonDateValueProcessor;
import com.banger.mobile.util.RandomUtil;
import com.banger.mobile.webservice.domain.JfMyCustomerPage;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.jws.WebService;
import java.util.*;

/**
 * User: yangy
 * Date: 13-8-13
 * Time: 下午5:19
 * To change this template use File | Settings | File Templates.
 */

@WebService(serviceName = "BangerCrmCreditsMallService", endpointInterface = "com.banger.mobile.facade.webservice.CreditsMallWebServices")
public class CreditsMallServicesImpl implements CreditsMallWebServices {

    private static final Logger logger = Logger.getLogger(CreditsMallServicesImpl.class);

    private ClientSocketCreditsMallService clientSocketCreditsMallService;
    private JfMyCustomerDao jfMyCustomerDao;
    private SysUserService sysUserService;
    private JfOrderPhotoService jfOrderPhotoService;

    private Map<String, Object> headMap;

    public JfOrderPhotoService getJfOrderPhotoService() {
        return jfOrderPhotoService;
    }

    public void setJfOrderPhotoService(JfOrderPhotoService jfOrderPhotoService) {
        this.jfOrderPhotoService = jfOrderPhotoService;
    }

    public void setSysUserService(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    public void setJfMyCustomerDao(JfMyCustomerDao jfMyCustomerDao) {
        this.jfMyCustomerDao = jfMyCustomerDao;
    }

    public void setClientSocketCreditsMallService(ClientSocketCreditsMallService clientSocketCreditsMallService) {
        this.clientSocketCreditsMallService = clientSocketCreditsMallService;
    }


    public void initParameter(String functionId) {
        headMap = new LinkedHashMap<String, Object>();
        headMap.put("Version", "10");      //报文版本
        headMap.put("ApplicationID", "");   //应用ID
        headMap.put("FunctionID", functionId);     //功能ID
        headMap.put("messageID", "");           //消息ID
        headMap.put("MsgPriority", "0");        //报文优先级
        headMap.put("Reserver", "");            //保留域
        headMap.put("Brc", "101001");           //当前操作机构
        headMap.put("Teller", "WEBBNK");        //当前操作网点
        headMap.put("terminalno", "");          //当前终端号
        headMap.put("authtellerno", "");        //授权柜员
        headMap.put("ChannelId", "48");         //渠道标志
        headMap.put("hostserno", RandomUtil.seedString123(13));   //上送主机流水号(上送渠道交易流水或者报文标识号，必须唯一)
        headMap.put("TermDate", DateUtil.getDateTime("yyyyMMdd", new Date())); //日期(上送渠道交易日期)
        headMap.put("TermTime", DateUtil.getDateTime("HHmmss", new Date()));   //时间(上送渠道交易时间)
    }

    /**
     * @return
     */
    public Integer getIsMyCustomerFlag(String account, String customerCode) {
        Map<String,Object> map=new HashMap<String, Object>();
        SysUser user = sysUserService.getAllUserByAccount(account);
        map.put("userId",user.getUserId());
        map.put("customerCode",customerCode);
        Integer count=jfMyCustomerDao.getMyCustomerByCustomerCode(map);
        return count;
    }

    /**
     * 客户查询接口
     *
     * @param account      用户名
     * @param customerName 客户名称
     * @param idCard       身份证号码
     * @return
     */
    public String queryCustomer(String account, String customerName, String idCard) {
        logger.info("pad端积分接口queryCustomer开始,account:"+account+",customerName:"+customerName+",idCard:"+idCard);
        long startTime = System.currentTimeMillis();//获取开始时间
        initParameter("chl215");
        Map<String, Object> bodyMap = new LinkedHashMap<String, Object>();
        bodyMap.put("interfaceCode", "jf001");   //交易码
        bodyMap.put("account", account);        //客户经理登录账号
        bodyMap.put("customerName", customerName); //客户姓名
        bodyMap.put("idCard", idCard);             //客户身份证号码
        String str = clientSocketCreditsMallService.getMessage(headMap, bodyMap, null);
        JSONObject json = JSONObject.fromObject(str);
        if(!json.getString("errorcode").equals("000000")){
            logger.info("pad端积分接口queryCustomer完成,account:"+account+",customerName:"+customerName+",idCard:"+idCard);
            return  json.toString();
        }else{
            String customerCode=json.getString("customerCode");
            Integer flag=getIsMyCustomerFlag(account,customerCode);
            json.put("isMyCustomer",""+flag);
            long endTime = System.currentTimeMillis(); //获取结束时间
            logger.info("客户查询接口执行花费时间：..."  + (endTime - startTime) + "ms");
            logger.info("pad端积分接口queryCustomer完成,account:"+account+",customerName:"+customerName+",idCard:"+idCard);
            return json.toString();
        }
    }

    /**
     * 客户详情查询接口
     *
     * @param account
     * @param customerCode
     * @return
     */
    public String getCustomerDetail(String account, String customerCode) {
        logger.info("pad端积分接口getCustomerDetail开始,account:"+account+",customerCode:"+customerCode);
        long startTime = System.currentTimeMillis();//获取开始时间
        initParameter("chl216");
        Map<String, Object> bodyMap = new LinkedHashMap<String, Object>();
        bodyMap.put("interfaceCode", "jf002");
        bodyMap.put("customerCode", customerCode);
        String str = clientSocketCreditsMallService.getMessage(headMap, bodyMap, null);
        JSONObject json = JSONObject.fromObject(str);
        if(!json.getString("errorcode").equals("000000")){
            logger.info("pad端积分接口getCustomerDetail完成,account:"+account+",customerCode:"+customerCode);
            return  json.toString();
        }else{
            Integer flag=getIsMyCustomerFlag(account,customerCode);
            json.put("isMyCustomer",""+flag);
            long endTime = System.currentTimeMillis(); //获取结束时间
            logger.info("客户详情接口执行花费时间：..."  + (endTime - startTime) + "ms");
            logger.info("pad端积分接口getCustomerDetail完成,account:"+account+",customerCode:"+customerCode);
            return json.toString();
        }
    }

    /**
     * 客户AUM信息接口
     *
     * @param account      用户名
     * @param customerCode 客户编号
     * @return
     */
    public String getCustomerAum(String account, String customerCode) {
        logger.info("pad端积分接口getCustomerAum开始,account:"+account+",customerCode:"+customerCode);
        long startTime = System.currentTimeMillis();//获取开始时间
        initParameter("chl217");
        Map<String, Object> bodyMap = new LinkedHashMap<String, Object>();
        bodyMap.put("interfaceCode", "jf003");
        bodyMap.put("customerCode", customerCode);
        String str = clientSocketCreditsMallService.getMessage(headMap, bodyMap, null);
        long endTime = System.currentTimeMillis(); //获取结束时间
        logger.info("客户AUM信息接口执行花费时间：..."  + (endTime - startTime) + "ms");
        logger.info("pad端积分接口getCustomerAum完成,account:"+account+",customerCode:"+customerCode);
        return str;
    }

    /**
     * 礼品信息查询接口
     *
     * @param queryType 礼品类型
     * @param keyword   关键字
     * @param orderType 排序字段
     * @param pageCount
     * @param jumpPages
     * @return
     */
    public String queryGift(String queryType, String keyword, String orderType, String pageCount, String jumpPages) {
        logger.info("pad端积分接口queryGift开始,queryType:"+queryType+",keyword:"+keyword+",orderType:"+orderType+",pageCount:"+pageCount+",jumpPages:"+jumpPages);
        long startTime = System.currentTimeMillis();//获取开始时间
        initParameter("chl105");
        Map<String, Object> bodyMap = new LinkedHashMap<String, Object>();
        bodyMap.put("TRANCODE", "JF0001");
        bodyMap.put("giftType", "02");
        bodyMap.put("queryType", queryType);
        bodyMap.put("searchFlag", keyword.length()>0?"1":"0");
        bodyMap.put("pointAreaMax", "");
        bodyMap.put("pointAreaMin", "");
        bodyMap.put("keyWord", keyword);
        bodyMap.put("giftNo", "");
        bodyMap.put("pageCount", pageCount);
        bodyMap.put("jumpPages", jumpPages);
        bodyMap.put("status", "0");
        bodyMap.put("sendType", "");
        bodyMap.put("orderType", orderType);
        String str = clientSocketCreditsMallService.getMessage(headMap, bodyMap, null);
        long endTime = System.currentTimeMillis(); //获取结束时间
        logger.info("礼品信息查询接口执行花费时间：..."  + (endTime - startTime) + "ms");
        logger.info("pad端积分接口queryGift完成,queryType:"+queryType+",keyword:"+keyword+",orderType:"+orderType+",pageCount:"+pageCount+",jumpPages:"+jumpPages);
        return str;
    }
    
    /**
     * 礼品编号查询接口
     *
     * @param giftNo 礼品编号
     * @return
     */
    public String queryGiftNo(String giftNo) {
        logger.info("pad端积分接口queryGiftNo开始,giftNo:"+giftNo);
        long startTime = System.currentTimeMillis();//获取开始时间
        initParameter("chl105");
        Map<String, Object> bodyMap = new LinkedHashMap<String, Object>();
        bodyMap.put("TRANCODE", "JF0001");
        bodyMap.put("giftType", "01");
        bodyMap.put("queryType", "");
        bodyMap.put("searchFlag", "1");
        bodyMap.put("pointAreaMax", "");
        bodyMap.put("pointAreaMin", "");
        bodyMap.put("keyWord", "");
        bodyMap.put("giftNo", giftNo);
        bodyMap.put("pageCount", 1);
        bodyMap.put("jumpPages", 1);
        bodyMap.put("status", "0");
        bodyMap.put("sendType", "");
        bodyMap.put("orderType", "");
        String str = clientSocketCreditsMallService.getMessage(headMap, bodyMap, null);
        long endTime = System.currentTimeMillis(); //获取结束时间
        logger.info("礼品编号信息查询接口执行花费时间：..."  + (endTime - startTime) + "ms");
        logger.info("pad端积分接口queryGiftNo完成,giftNo:"+giftNo);
        return str;
    }

    /**
     * 礼品积分范围查询接口
     *
     * @param pointAreaMin 积分下限
     * @param pointAreaMax 积分上限
     * @return
     */
    public String queryGiftMinMax(String pointAreaMin, String pointAreaMax, String queryType, String orderType, String pageCount,String jumpPages) {
        logger.info("pad端积分接口queryGiftMinMax开始,pointAreaMin:"+pointAreaMin+",pointAreaMax:"+pointAreaMax+"" +
                ",queryType:"+queryType+",orderType:"+orderType+",pageCount:"+pageCount+",jumpPages:"+jumpPages);
        long startTime = System.currentTimeMillis();//获取开始时间
        initParameter("chl105");
        Map<String, Object> bodyMap = new LinkedHashMap<String, Object>();
        bodyMap.put("TRANCODE", "JF0001");
        bodyMap.put("giftType", "03");
        bodyMap.put("queryType", queryType);
        bodyMap.put("searchFlag", "1");
        bodyMap.put("pointAreaMax", pointAreaMax);
        bodyMap.put("pointAreaMin", pointAreaMin);
        bodyMap.put("keyWord", "");
        bodyMap.put("giftNo", "");
        bodyMap.put("pageCount", pageCount);
        bodyMap.put("jumpPages", jumpPages);
        bodyMap.put("status", "0");
        bodyMap.put("sendType", "");
        bodyMap.put("orderType", orderType);
        String str = clientSocketCreditsMallService.getMessage(headMap, bodyMap, null);
        long endTime = System.currentTimeMillis(); //获取结束时间
        logger.info("礼品积分范围查询接口执行花费时间：..."  + (endTime - startTime) + "ms");
        logger.info("pad端积分接口queryGiftMinMax完成,pointAreaMin:"+pointAreaMin+",pointAreaMax:"+pointAreaMax+"" +
                ",queryType:"+queryType+",orderType:"+orderType+",pageCount:"+pageCount+",jumpPages:"+jumpPages);
        return str;
    }

    /**
     * 积分兑换交易接口
     *
     * @param exchangeJson
     * @return
     */
    public String exchangeGift(String exchangeJson) {
        logger.info("pad端积分接口exchangeGift开始,exchangeJson:"+exchangeJson);
        long startTime = System.currentTimeMillis();//获取开始时间
        JSONObject json = JSONObject.fromObject(exchangeJson);
        initParameter("chl106");
        Map<String, Object> bodyMap = new LinkedHashMap<String, Object>();
        bodyMap.put("TRANCODE", "JF0002");
        bodyMap.put("hostNo", json.getString("hostNo"));
        try{
        bodyMap.put("receiveAddress", json.getString("receiveAddress"));
        bodyMap.put("receiveName", json.getString("receiveName"));
        bodyMap.put("receivePhone", "");
        String receivePostCode="";
        if(json.containsKey("receivePostCode"))
            receivePostCode =json.getString("receivePostCode");
        bodyMap.put("receivePostCode",receivePostCode);
        bodyMap.put("receiveMobile", json.getString("receiveMobile"));
        }catch (Exception e){
        }
        bodyMap.put("sendAddress", "");
        bodyMap.put("sendName", "");
        bodyMap.put("sendPhone", "");
        bodyMap.put("sendMobile", "");
        bodyMap.put("sendPostCode", "");
        JSONArray jsonArray = json.getJSONArray("row");
        List<Map<String, Object>> row = new ArrayList<Map<String, Object>>();
        Map<String, Object> rMap = null;
        int count = 0;
        for (Iterator iter = jsonArray.iterator(); iter.hasNext(); ) {
            rMap = new LinkedHashMap<String, Object>();
            JSONObject rowBean = (JSONObject) iter.next();
            rMap.put("JOD_INFONO", rowBean.getString("JOD_INFONO"));
            rMap.put("JOD_NUM", rowBean.getString("JOD_NUM"));
            row.add(rMap);
            count++;
        }
        bodyMap.put("Count", count);
        String str = clientSocketCreditsMallService.getMessage(headMap, bodyMap, row);
        long endTime = System.currentTimeMillis(); //获取结束时间
        logger.info("积分兑换交易接口执行花费时间：..."  + (endTime - startTime) + "ms");
        logger.info("pad端积分接口exchangeGift完成,exchangeJson:"+exchangeJson);
        return str;
    }


    /**
     * 订单查询接口
     *
     * @param hostNo
     * @param pageCount
     * @param jumpPages
     * @return
     */
    public String queryOrder(String hostNo,String beginDate,String endDate, String pageCount, String jumpPages) {
        logger.info("pad端积分接口queryOrder开始,hostNo:"+hostNo+",beginDate:"+beginDate+",endDate:"+endDate+",pageCount:"+pageCount+",jumpPages:"+jumpPages);
        long startTime = System.currentTimeMillis();//获取开始时间
        initParameter("chl107");
        Map<String, Object> bodyMap = new LinkedHashMap<String, Object>();
        bodyMap.put("TRANCODE", "JF0003");
        bodyMap.put("hostNo", hostNo);
        bodyMap.put("beginDate", beginDate);
        bodyMap.put("endDate", endDate);
        bodyMap.put("pageCount", pageCount);
        bodyMap.put("jumpPages", jumpPages);
        bodyMap.put("orderState", "");
        String str = clientSocketCreditsMallService.getMessage(headMap, bodyMap, null);
        long endTime = System.currentTimeMillis(); //获取结束时间
        logger.info("订单查询接口执行花费时间：..."  + (endTime - startTime) + "ms");
        logger.info("pad端积分接口queryOrder完成,hostNo:"+hostNo+",beginDate:"+beginDate+",endDate:"+endDate+",pageCount:"+pageCount+",jumpPages:"+jumpPages);
        return str;
    }

    /**
     * 订单详细查询接口
     *
     * @param orderNo
     * @return
     */
    public String queryOrderDetail(String orderNo) {
        logger.info("pad端积分接口queryOrderDetail开始,orderNo:"+orderNo);
        long startTime = System.currentTimeMillis();//获取开始时间
        initParameter("chl108");
        Map<String, Object> bodyMap = new LinkedHashMap<String, Object>();
        bodyMap.put("TRANCODE", "JF0004");
        bodyMap.put("orderNo", orderNo);
        String str = clientSocketCreditsMallService.getMessage(headMap, bodyMap, null);
        long endTime = System.currentTimeMillis(); //获取结束时间
        logger.info("订单详细查询接口执行花费时间：..."  + (endTime - startTime) + "ms");
        logger.info("pad端积分接口queryOrderDetail完成,orderNo:"+orderNo);
        return str;
    }

    /**
     * 礼品订单撤单接口
     *
     * @param orderNo
     * @param hostNo
     * @return
     */
    public String cancelOrder(String orderNo, String hostNo) {
        logger.info("pad端积分接口cancelOrder开始,orderNo:"+orderNo+",hostNo:"+hostNo);
        long startTime = System.currentTimeMillis();//获取开始时间
        initParameter("chl109");
        Map<String, Object> bodyMap = new LinkedHashMap<String, Object>();
        bodyMap.put("TRANCODE", "JF0005");
        bodyMap.put("orderNo", orderNo);
        bodyMap.put("hostNo", hostNo);
        String str = clientSocketCreditsMallService.getMessage(headMap, bodyMap, null);
        long endTime = System.currentTimeMillis(); //获取结束时间
        logger.info("礼品订单撤单接口执行花费时间：..."  + (endTime - startTime) + "ms");
        logger.info("pad端积分接口cancelOrder完成,orderNo:"+orderNo+",hostNo:"+hostNo);
        return str;
    }

    /**
     * 积分查询接口
     *
     * @param hostNo
     * @return
     */
    public String queryCustomerPoint(String hostNo) {
        logger.info("pad端积分接口queryCustomerPoint开始,hostNo:"+hostNo);
        long startTime = System.currentTimeMillis();//获取开始时间
        initParameter("chl110");
        Map<String, Object> bodyMap = new LinkedHashMap<String, Object>();
        bodyMap.put("TRANCODE", "JF0006");
        bodyMap.put("hostNo", hostNo);
        String str = clientSocketCreditsMallService.getMessage(headMap, bodyMap, null);
        long endTime = System.currentTimeMillis(); //获取结束时间
        logger.info("积分查询接口执行花费时间：..."  + (endTime - startTime) + "ms");
        logger.info("pad端积分接口queryCustomerPoint完成,hostNo:"+hostNo);
        return str;
    }

    /**
     * 积分明细查询
     *
     * @param hostNo
     * @param pageCount
     * @param jumpPages
     * @return
     */
    public String queryCustomerPointDetail(String hostNo, String pageCount, String jumpPages) {
        logger.info("pad端积分接口queryCustomerPointDetail开始,hostNo:"+hostNo+",pageCount:"+pageCount+",jumpPages:"+jumpPages);
        long startTime = System.currentTimeMillis();//获取开始时间
        initParameter("chl111");
        Map<String, Object> bodyMap = new LinkedHashMap<String, Object>();
        bodyMap.put("TRANCODE", "JF0010");
        bodyMap.put("hostNo", hostNo);
        bodyMap.put("pageCount", pageCount);
        bodyMap.put("jumpPages", jumpPages);
        String str = clientSocketCreditsMallService.getMessage(headMap, bodyMap, null);
        long endTime = System.currentTimeMillis(); //获取结束时间
        logger.info("积分明细查询执行花费时间：..."  + (endTime - startTime) + "ms");
        logger.info("pad端积分接口queryCustomerPointDetail完成,hostNo:"+hostNo+",pageCount:"+pageCount+",jumpPages:"+jumpPages);
        return str;
    }

    /**
     * 搜索我的积分客户
     *
     * @param account
     * @param input
     * @return
     */
    public String searchMyCustomer(String account, String input,Integer page){
        logger.info("pad端积分接口searchMyCustomer开始,account:"+account+",input:"+input+",page:"+page);
        long startTime = System.currentTimeMillis();//获取开始时间
        JSONObject jsonObject = new JSONObject();
        JsonConfig jsonConfig = new JsonConfig();
        Page pageObj = new Page();
        pageObj.setCurrentPage(page);
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
        SysUser user = sysUserService.getAllUserByAccount(account);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("input", input);
        map.put("userId", user.getUserId());
        PageUtil<JfMyCustomer> plist = jfMyCustomerDao.searchMyCustomer(map,pageObj);
        JfMyCustomerPage myPage = new JfMyCustomerPage();
        myPage.setDataList(plist.getItems());
        myPage.setTotalCount(pageObj.getTotalRowsAmount());
        String result = jsonObject.fromObject(myPage, jsonConfig).toString();
        long endTime = System.currentTimeMillis(); //获取结束时间
        logger.info("搜索我的积分客户执行花费时间：..."  + (endTime - startTime) + "ms");
        logger.info("pad端积分接口searchMyCustomer完成,account:"+account+",input:"+input+",page:"+page);
        return result;
    }

    /**
     * 删除我的积分客户
     *
     * @param account
     * @param customerCodeArray
     * @return
     */
    public String removeMyCustomer(String account, String customerCodeArray) {
        try {
            logger.info("pad端积分接口removeMyCustomer开始,account:"+account+",customerCodeArray:"+customerCodeArray);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("customerCodeArray", customerCodeArray);
            jfMyCustomerDao.removeMyCustomer(map);
            logger.info("pad端积分接口removeMyCustomer完成,account:"+account+",customerCodeArray:"+customerCodeArray);
            return "true";
        } catch (Exception e) {
            logger.error("CreditsMallServicesImpl % removeMyCustomer",e);
            return "false";
        }
    }

    /**
     * 添加我的积分客户
     *
     * @param account
     * @param customerCode
     * @param customerName
     * @param belongToDept
     * @param idCard
     * @param phoneNumber1
     * @return
     */
    public String addMyCustomer(String account, String customerCode, String customerName, String belongToDept, String idCard, String phoneNumber1, String aumValue) {
        try {
            logger.info("pad端积分接口addMyCustomer开始,account:"+account+",customerCode:"+customerCode+",customerName:" +
                    ""+customerName+",belongToDept:"+belongToDept+",idCard:"+idCard+",phoneNumber1:"+phoneNumber1+",aumValue:"+aumValue);
            SysUser user = sysUserService.getAllUserByAccount(account);
            Integer flag=getIsMyCustomerFlag(account,customerCode);
            if (flag == 0) {
                JfMyCustomer crm = new JfMyCustomer();
                crm.setBelongToDept(belongToDept);
                crm.setCustomerCode(customerCode);
                crm.setCustomerName(customerName);
                crm.setIdCard(idCard);
                crm.setUserId(user.getUserId());
                crm.setPhoneNumber1(phoneNumber1);
                crm.setAumValue(aumValue);
                jfMyCustomerDao.addMyCustomer(crm);
                logger.info("pad端积分接口addMyCustomer完成,account:"+account+",customerCode:"+customerCode+",customerName:" +
                        ""+customerName+",belongToDept:"+belongToDept+",idCard:"+idCard+",phoneNumber1:"+phoneNumber1+",aumValue:"+aumValue);
            } else {
                return "false";
            }
        } catch (Exception es) {
            return "false";
        }
        return "true";
    }

    /**
     * 3.14 订单现场兑换图片
     * 
     * @param account
     * @param orderNo
     * @return
     */
    public String getOrderPhoto(String account, String orderNo){
        try {
            logger.info("pad端积分接口getOrderPhoto开始,account:"+account+",orderNo:"+orderNo);
            if (StringUtils.isNotEmpty(orderNo)){
                Map<String,Object> paramMap = new HashMap<String, Object>();
                paramMap.put("orderNo",orderNo);
                List<JfOrderPhoto> jfOrderPhotoList = jfOrderPhotoService.getJfOrderPhotoListByOrderNo(paramMap);
                if (jfOrderPhotoList != null && jfOrderPhotoList.size() > 0){
                    JSONArray jsonArray = new JSONArray();
                    JsonConfig jsonConfig = new JsonConfig();
                    jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
                    for (JfOrderPhoto jfOrderPhoto : jfOrderPhotoList){
                        SysUploadFile sysUploadFile = jfOrderPhoto.getSysUploadFile();
                        if (sysUploadFile != null){
                            jfOrderPhoto.setUrl(sysUploadFile.getFileId() + "&fullPath=" + sysUploadFile.getFilePath()+"/"+sysUploadFile.getFileName());
                            jfOrderPhoto.setFileName(sysUploadFile.getFileName());
                        }
                    }
                    String result = jsonArray.fromObject(jfOrderPhotoList, jsonConfig).toString();
                    logger.info("pad端积分接口getOrderPhoto完成,account:"+account+",orderNo:"+orderNo);
                    return result;
                }
            }

            return null;
        }catch (Exception e){
            logger.error("CreditsMallServicesImpl % getOrderPhoto",e);
            return null;
        }
    }

}
