package com.banger.mobile.facade.webservice;

import java.util.Date;

import javax.jws.WebService;

@WebService
public interface RecordWebService {
    /**
     * 取得业务类型码表
     * @return json string
     */
    public String getRecTypeCode();

    /**
     * PAD用户登录
     * @param account
     * @param password
     * @return
     */
    public String userLogin(String account, String password);

    /**
     * savePadInfo
     * @return
     */
    public String savePadInfo(String brandType, String brandSubType, String serialNumber, Integer userId,Integer status);

    /**
     * PAD用户退出
     * @param account
     * @return
     */
    public boolean userLogout(String account);

    /**
     * 取得沟通进度
     * @return json string
     */
    String getCommProgressList();

    /**
     * PAD客户端更新接口
     * @return app version & donwload url in json string
     */
    public String checkUpdate(String maxVersion);

    /**
     * PAD录音文件校验
     * @param filename 文件名
     * @param md5 文件md5码
     * @return 是否上传成功
     */
    public boolean fileValidate(String filename, String md5);
    
    /**
     * PAD录音文件校验
     * @param filename
     * @param filesize
     * @return
     */
    public boolean fileValidateBySize(String filename,Long filesize);

    /**
     * PAD XML文件收取
     * @param xmlString
     * @param xmlLength
     * @return
     */
    public boolean receiveXmlFile(String filename, String xmlString,Long xmlLength);

    /**
     * 客户联系记录信息接口
     * @param customerId
     * @return String
     */
    public String getCustomerRecord(int customerId, int pageCount);

    /**
     * 客户联系记录总数接口
     * @param customerId
     */
    public int getCustomerRecordCount(int customerId);


    /**
     * 话机 查询未读留言,未接来电
     * @param account 登入账号
     * @return
     */
    public String[] getMessageCounts(String account);

    /**
     * 清空通话窗口的Session
     */
    public void talkLogout();

    /**
     * 得到当前时间
     * @return
     */
    public String getNowTime();

    /**
     * 录音文件路么是否有效
     * @return
     */
    public Boolean isValidRecordFilePath();

    /**
     * 视频文件路径是否有效
     * @return
     */
    public Boolean isValidVideoFilePath();

    /**
     * 托盘，通话，现场录音时，调用，先插入记录
     * @param talkId
     * @param account
     * @param custId
     * @param number
     * @param ringTime
     * @param talkBeginTime
     * @param talkType
     * @return
     */
    public String uploadTalkInfo(String talkId, String account, String custId, String number,
                                 String ringTime, String talkBeginTime, String talkType);

    // 用户登录上传地理位置信息
    public String uploadGpsInfo(String account, String lat, String lng);
    
    /**
     * PAD新建/修改拜访
     * @param recordInfoId
     * @param customerId
     * @param customerName
     * @param commprogress
     * @param visitRemark
     * @param visitDate
     * @param String account
     * @return
     */
    public String saveVisit(Integer recordInfoId, Integer customerId, String customerName, Integer commProgress,String visitRemark,String visitDate,String account, String bizTypeCode);
    
    /**
     * PAD管理-禁用停用(登录验证)
     * @param account 账号
     * @param serialNumber 序列号
     * @return 1:启用(正常使用) 2:禁用(不允许登陆和连接系统服务端)，直接退出 3:停用(不允许登陆和连接系统服务端)，直接退出
     */
    public Integer  getPadUseStatus(String account, String serialNumber);
    
    /**
     * PAD管理-使用记录
     * @param account 账号
     * @param serialNumber 序列号
     * @param logType 1登录2登出
     * @return
     */
    public String  useLog(String account, String serialNumber, Integer logType);
    
    /**
     * PAD管理-流量记录
     * @param account 账号
     * @param serialNumber 序列号
     * @param upload 上传总量(k)
     * @param download 下载总量(k)
     * @param bootTime PAD开机时间
     * @return
     */
    public String  networkFlowLog(String account, String serialNumber, Integer  upload, Integer download, String bootTime);

    /**
     * 根据文件地址查询录音文件是否存在
     */
    public String queryFileByUrl(String url);
    
    /**
     * PAD上传文件名和序列号
     * @param account 帐号
     * @param filename 上传文件名
     * @param serialNumber 序列号
     * @return
     */
    public String uploadFileName(String account,String filename,String serialNumber);
    
    public String userLoginWithoutPwd(String account);
    
}
