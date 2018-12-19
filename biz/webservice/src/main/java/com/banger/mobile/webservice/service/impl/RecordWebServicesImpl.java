package com.banger.mobile.webservice.service.impl;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.constants.Constants;
import com.banger.mobile.constants.TransportConstants;
import com.banger.mobile.dao.padManagement.PadInfoDao;
import com.banger.mobile.dao.padManagement.SysPadLogDao;
import com.banger.mobile.dao.padManagement.SysPadUploadLogDao;
import com.banger.mobile.domain.Enum.record.EnumRecord;
import com.banger.mobile.domain.model.base.customer.BaseCrmCustomer;
import com.banger.mobile.domain.model.data.CmDownloadUrl;
import com.banger.mobile.domain.model.data.CustomerData;
import com.banger.mobile.domain.model.dept.SysDept;
import com.banger.mobile.domain.model.map.MapUserGps;
import com.banger.mobile.domain.model.map.MapUserGpsLog;
import com.banger.mobile.domain.model.pad.AppVersion;
import com.banger.mobile.domain.model.padManagement.PadInfo;
import com.banger.mobile.domain.model.padManagement.PadInfoCopy;
import com.banger.mobile.domain.model.padManagement.SysPadLog;
import com.banger.mobile.domain.model.padManagement.SysPadUploadLog;
import com.banger.mobile.domain.model.phoneConfig.PhoneConfig;
import com.banger.mobile.domain.model.recbistype.RecbizType;
import com.banger.mobile.domain.model.record.PhoneRecordBean;
import com.banger.mobile.domain.model.record.RecordInfoBean;
import com.banger.mobile.domain.model.system.CommProgress;
import com.banger.mobile.domain.model.uploadFile.SysUploadFile;
import com.banger.mobile.domain.model.user.SysUser;
import com.banger.mobile.domain.model.visitRecord.VisitRecord;
import com.banger.mobile.facade.customer.CrmCustomerService;
import com.banger.mobile.facade.data.*;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.dept.DeptService;
import com.banger.mobile.facade.impl.transport.RecordInfoXmlParserJobImpl;
import com.banger.mobile.facade.map.MapUserGpsLogService;
import com.banger.mobile.facade.map.MapUserGpsService;
import com.banger.mobile.facade.padManagement.PadInfoService;
import com.banger.mobile.facade.param.SysParamService;
import com.banger.mobile.facade.phoneConfig.PhoneConfigService;
import com.banger.mobile.facade.points.JfDataXmlParserService;
import com.banger.mobile.facade.recbiztype.RecbizTypeService;
import com.banger.mobile.facade.record.RecordInfoService;
import com.banger.mobile.facade.system.CommProgressService;
import com.banger.mobile.facade.talk.TelephoneTalkService;
import com.banger.mobile.facade.unreadMessage.MessageNoticeService;
import com.banger.mobile.facade.uploadFile.SysUploadFileService;
import com.banger.mobile.facade.user.SysDeptAuthService;
import com.banger.mobile.facade.user.SysUserService;

import com.banger.mobile.facade.visitRecord.VisitRecordService;
import com.banger.mobile.facade.webservice.RecordWebService;
import com.banger.mobile.transport.TransportUtil;
import com.banger.mobile.util.*;
import com.banger.mobile.webservice.domain.CustomerRecordInfo;
import com.banger.mobile.webservice.domain.CustomerRecordPage;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.xfire.transport.http.XFireServletController;

import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@WebService(serviceName = "BangerCrmService", endpointInterface = "com.banger.mobile.facade.webservice.RecordWebService")
public class RecordWebServicesImpl implements RecordWebService {
    private final String APP_FILE_TYPE = "apk";
    private final String APP_DIR = "pad-app";
    private static final Logger logger = Logger.getLogger(RecordWebServicesImpl.class);

    private RecbizTypeService recbizTypeService;
    private SysUserService sysUserService;

    private CommProgressService commProgressService;
    private RecordInfoService recordInfoService;
    private SysParamService sysParamService;
    private TelephoneTalkService telephoneTalkService;
    private MessageNoticeService messageNoticeService;
    private PhoneConfigService phoneConfigService;
    private VisitRecordService visitRecordService;
    private CrmCustomerService crmCustomerService;
    private MapUserGpsService mapUserGpsService;
    private DeptFacadeService deptFacadeService;                                            // 用户部门service
    private MapUserGpsLogService mapUserGpsLogService;
    private SysUploadFileService sysUploadFileService;
    private CaseHelperService caseHelperService;
    private PadInfoService   padInfoService;
    private DeptService deptService;

    private PadInfoDao padInfoDao;
    private SysPadLogDao sysPadLogDao;
    private SysPadUploadLogDao sysPadUploadLogDao;

    public DeptService getDeptService() {
        return deptService;
    }

    public void setDeptService(DeptService deptService) {
        this.deptService = deptService;
    }

    public PadInfoService getPadInfoService() {
        return padInfoService;
    }

    public void setPadInfoService(PadInfoService padInfoService) {
        this.padInfoService = padInfoService;
    }

    public void setCaseHelperService(CaseHelperService caseHelperService) {
        this.caseHelperService = caseHelperService;
    }

    public void setSysUploadFileService(SysUploadFileService sysUploadFileService) {
        this.sysUploadFileService = sysUploadFileService;
    }

    public void setMapUserGpsLogService(MapUserGpsLogService mapUserGpsLogService) {
        this.mapUserGpsLogService = mapUserGpsLogService;
    }

    public void setDeptFacadeService(DeptFacadeService deptFacadeService) {
        this.deptFacadeService = deptFacadeService;
    }

    public void setMapUserGpsService(MapUserGpsService mapUserGpsService) {
        this.mapUserGpsService = mapUserGpsService;
    }

    public void setCrmCustomerService(CrmCustomerService crmCustomerService) {
        this.crmCustomerService = crmCustomerService;
    }

    public void setVisitRecordService(VisitRecordService visitRecordService) {
        this.visitRecordService = visitRecordService;
    }

    public MessageNoticeService getMessageNoticeService() {
        return messageNoticeService;
    }

    public void setMessageNoticeService(MessageNoticeService messageNoticeService) {
        this.messageNoticeService = messageNoticeService;
    }

    public void setTelephoneTalkService(TelephoneTalkService telephoneTalkService) {
        this.telephoneTalkService = telephoneTalkService;
    }

    public void setSysParamService(SysParamService sysParamService) {
        this.sysParamService = sysParamService;
    }

    public void setRecordInfoService(RecordInfoService recordInfoService) {
        this.recordInfoService = recordInfoService;
    }

    public void setRecbizTypeService(RecbizTypeService recbizTypeService) {
        this.recbizTypeService = recbizTypeService;
    }

    public void setCommProgressService(CommProgressService commProgressService) {
        this.commProgressService = commProgressService;
    }

    public void setSysUserService(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    public void setPhoneConfigService(PhoneConfigService phoneConfigService) {
        this.phoneConfigService = phoneConfigService;
    }

    public void setPadInfoDao(PadInfoDao padInfoDao) {
        this.padInfoDao = padInfoDao;
    }

    public void setSysPadLogDao(SysPadLogDao sysPadLogDao) {
        this.sysPadLogDao = sysPadLogDao;
    }

    public void setSysPadUploadLogDao(SysPadUploadLogDao sysPadUploadLogDao) {
        this.sysPadUploadLogDao = sysPadUploadLogDao;
    }

    /**
     * 取得业务类型
     *
     * @return
     */
    public String getRecTypeCode() {
        try {
            logger.info("pad端记录接口getRecTypeCode开始");
            Map<String, String> map = new HashMap<String, String>();
            JSONArray jsonArray = new JSONArray();
            List<RecbizType> rectype = recbizTypeService.getRecbizTypeForPad();
            if (rectype.size() > 0) {
                for (RecbizType recBizType : rectype) {
                    map.put("SORTNO", recBizType.getSortno().toString());
                    map.put("BIZ_TYPE_CODE", recBizType.getBizTypeId().toString());
                    map.put("BIZ_TYPE_NAME", recBizType.getBizTypeName());
                    jsonArray.add(map);
                }
            }
            logger.info("pad端记录接口getRecTypeCode完成");
            return jsonArray.toString();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "false";
        }
    }

    /**
     * 录音文件路么是否有效
     *
     * @return
     */
    public Boolean isValidRecordFilePath() {
        logger.info("pad端记录接口isValidRecordFilePath开始");
        Boolean flag = sysParamService.isPathExist();
        logger.info("pad端记录接口isValidRecordFilePath完成");
        return flag;
    }

    /**
     * 视频文件路径是否有效
     *
     * @return
     */
    public Boolean isValidVideoFilePath() {
        // 风控版本同步方法，小微这里没有视频功能，暂时返回flase
        // Boolean flag = sysParamService.isVideoPathExist();
        // return flag;
        return false;
    }

    /**
     * 取得沟通进度
     *
     * @return json string
     */
    public String getCommProgressList() {
        try {
            logger.info("pad端记录接口getCommProgressList开始");
            Map<String, Object> map = new HashMap<String, Object>();
            JSONArray jsonArray = new JSONArray();
            List<CommProgress> commProList = commProgressService.getCommProgressList();
            if (commProList.size() > 0) {
                for (CommProgress commProgress : commProList) {
                    map.put("commProgressId", commProgress.getCommProgressId());
                    map.put("commProgressName", commProgress.getCommProgressName());
                    jsonArray.add(map);
                }
            }
            logger.info("pad端记录接口getCommProgressList完成");
            return jsonArray.toString();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "false";
        }
    }

    /**
     * PAD用户登录
     *
     * @param account
     * @param password
     * @return
     */
    public String userLogin(String account, String password) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            logger.info("pad端记录接口userLogin开始，account:" + account);
            SysUser user = sysUserService.getUserByAccount(account.trim()); // 得到系统用户信息
            String flag = "";
            // 首先验证输入的用户名是否存在
            if (user == null) {
                // 用户名不存在
                // 提示“用户“XXX”，不存在，请重新输入
                map.put("flag", "2");
            } else if (user.getIsActived().intValue() == 0) {
                // 用户当前被禁用
                // 提示“您已被禁止登录，请联系系统管理员”
                map.put("flag", "3");
            } else {
                String pw = user.getPassword();
                String encryptPass = Md5Encrypt.md5(password.trim());
                if (pw.equals(encryptPass)) {
                    // 验证正确
                    sysUserService.login(account, password, getClientIp());
                    boolean isInCharge = deptFacadeService.isInChargeOfDepartment(String
                            .valueOf(user.getUserId()));
                    map.put("account", user.getAccount());
                    map.put("password", user.getPassword());
                    map.put("userName", user.getUserName());

                    SysDept sysDept= deptService.getDeptById(user.getDeptId());
                    map.put("deptName", sysDept.getDeptName());
                    map.put("deptCode", sysDept.getDeptCode());

//                    1系统管理员
//                    2机构系统管理员
//                    3业务主管
//                    4审批人员
//                    5团队主管
//                    6后台人员
//                    7客户经理
//                    8综合统计岗
//                    9业务管理岗
                    map.put("roleId", sysUserService.getRoleByUserId(user.getUserId()));

//                    Map<String,Map<String,String>> jgmap=sysUserService.getUserJGINFO(account);
//                    Map<String,String> jg=new HashMap<String, String>();
//                    for(String key:jgmap.keySet()){
//                        jg=jgmap.get(key);
//                    }
//                    map.put("JGM",jg.get("JGM"));
//                    map.put("CNNAME",jg.get("CNNAME"));
                    map.put("flag", "1");
                    if (isInCharge) {
                        map.put("isManager", 1);
                    } else {
                        map.put("isManager", 0);
                    }
                    map.put("userId", user.getUserId());
                } else {
                    // 提示“密码不正确，请输入正确密码”
                    map.put("flag", "4"); // 验证失败
                }
            }
            logger.info("pad端记录接口userLogin完成，account:" + account);
            return JSONObject.fromObject(map).toString();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            map.put("flag", "0");
            return JSONObject.fromObject(map).toString();
        }
    }


    public String savePadInfo(String brandType, String brandSubType, String serialNumber, Integer userId ,Integer status){
        //判断 serialNumber userId
        //根据serialNumber 查询数据库 如果存在则更新 如果不存在则新建
//            1、更新 更新 登陆时间 更新登陆人员 UPDATE_USER UPDATE_DATE
//            2、新建  默认 status=1 CREATE_USER CREATE_DATE
                Map<String,Object> paramMap=new HashMap<String, Object>();

                if(serialNumber!="" ||  userId!=null){
                    PadInfoCopy padInfoCopy= padInfoService.queryOneSrialNumber(serialNumber);
                    if (padInfoCopy !=null){
                        paramMap.put("userId",userId);
                        paramMap.put("updateUser",userId);
                        paramMap.put("serialNumber",serialNumber);
                        paramMap.put("status",status);
                        if (status==0){
                            paramMap.put("loginDate",padInfoCopy.getLoginDate());
                        }
                        if (status==1) {
                            paramMap.put("loginDate",new Date());
                        }
                        paramMap.put("updateDate", new Date());
                        padInfoService.updatePadInfoCopy(paramMap);
                        return "已修改";
                    }
                    paramMap.put("status",status);
                    paramMap.put("createDate",new Date());
                    paramMap.put("createUser",userId);
                    paramMap.put("serialNumber",serialNumber);
                    paramMap.put("userId",userId);
                    paramMap.put("brandType",brandType);
                    paramMap.put("brandSubType",brandSubType);
                    paramMap.put("loginDate",new Date());
                    padInfoService.insertPadInfoCopy(paramMap);

                    return"已添加 ";
                }
                    return "无操作";


    }


    public String userLoginWithoutPwd(String account) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            logger.info("pad端记录接口userLogin开始，account:" + account);
            SysUser user = sysUserService.getUserByAccount(account.trim()); // 得到系统用户信息
            String flag = "";
            // 首先验证输入的用户名是否存在
            if (user == null) {
                // 用户名不存在
                // 提示“用户“XXX”，不存在，请重新输入
                map.put("flag", "2");
            } else if (user.getIsActived().intValue() == 0) {
                // 用户当前被禁用
                // 提示“您已被禁止登录，请联系系统管理员”
                map.put("flag", "3");
            } else {

                // 验证正确
                //sysUserService.login(account, password, getClientIp());
                boolean isInCharge = deptFacadeService.isInChargeOfDepartment(String
                        .valueOf(user.getUserId()));
                map.put("account", user.getAccount());
                map.put("password", user.getPassword());
                map.put("userName", user.getUserName());
                Map<String,Map<String,String>> jgmap=sysUserService.getUserJGINFO(account);
                Map<String,String> jg=new HashMap<String, String>();
                for(String key:jgmap.keySet()){
                    jg=jgmap.get(key);
                }
                map.put("JGM",jg.get("JGM"));
                map.put("CNNAME",jg.get("CNNAME"));
                map.put("flag", "1");
                if (isInCharge) {
                    map.put("isManager", 1);
                } else {
                    map.put("isManager", 0);
                }
                map.put("userId", user.getUserId());
   
            }
            logger.info("pad端记录接口userLogin完成，account:" + account);
            return JSONObject.fromObject(map).toString();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            map.put("flag", "0");
            return JSONObject.fromObject(map).toString();
        }
    }
    
    /**
     * PAD用户退出
     *
     * @param account
     * @return
     */
    public boolean userLogout(String account) {
        try {
            logger.info("pad端记录接口userLogout开始，account:" + account);
            sysUserService.logout(account, getClientIp());
            logger.info("pad端记录接口userLogout完成，account:" + account);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 取客户端IP
     *
     * @return
     */
    public String getClientIp() {
        try {
            HttpServletRequest request = XFireServletController.getRequest();
            return request.getRemoteAddr();
        } catch (Exception e) {
            return "";
        }

    }

    /**
     * 客户联系记录信息接口
     *
     * @param customerId
     * @param pageCount
     * @return String
     */
    public String getCustomerRecord(int customerId, int pageCount) {
        try {
            logger.info("pad端记录接口getCustomerRecord开始，customerId:" + customerId + ",pageCount:" + pageCount);
            JSONObject jsonObject = new JSONObject();
            JsonConfig jsonConfig = new JsonConfig();
            jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Page page = new Page();
            page.setCurrentPage(pageCount);
            Map<String, Object> parameterMap = new HashMap<String, Object>();
            parameterMap.put("customerId", customerId);
            PageUtil<RecordInfoBean> list = recordInfoService.getCustomerRecord(parameterMap, page);

            List<CustomerRecordInfo> plist = new ArrayList<CustomerRecordInfo>();
            for (RecordInfoBean t : list.getItems()) {
                CustomerRecordInfo info = new CustomerRecordInfo();
                info.setRecordInfoId(t.getRecordInfoId());
                info.setCustomerName(t.getCustomerName());
                info.setRemotePhone(t.getRemotePhone());
                info.setStartDate(t.getStartDate() == null ? null : df.format(t.getStartDate()));
                info.setCallType(t.getCallTypeName());
                info.setBizType(t.getBizType());
                info.setRemark(t.getRemark());
                if (StringUtil.isNotEmpty(t.getFilePath())
                        && StringUtil.isNotEmpty(t.getFileName())) {
                    info.setRecordUrl(t.getFilePath() + "/" + t.getFileName());
                } else {
                    info.setRecordUrl("");
                }
                info.setDuration(t.getCallTime());
                info.setCommProgressId(t.getCommProgressId());
                info.setCustomerId(t.getCustomerId());
                info.setRecordSource(t.getRecordSource());
                if (t.getIsCanceled() != null) {
                    info.setIsCanceled(0);
                } else {
                    info.setIsCanceled(t.getIsCanceled());
                }

                info.setUser(t.getUserName());
                plist.add(info);
            }

            CustomerRecordPage customerRecordPage = new CustomerRecordPage();
            customerRecordPage.setDataList(plist);
            customerRecordPage.setTotalCount(list.getPage().getTotalRowsAmount());
            logger.info("pad端记录接口getCustomerRecord完成，customerId:" + customerId + ",pageCount:" + pageCount);
            String result = jsonObject.fromObject(customerRecordPage, jsonConfig).toString();
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "false";
        }
    }

    /**
     * 客户联系记录总数接口
     *
     * @param customerId
     */
    public int getCustomerRecordCount(int customerId) {
        try {
            logger.info("pad端记录接口getCustomerRecordCount开始，customerId:" + customerId);
            int res = recordInfoService.queryCustomerCallInfoCountByCusId(customerId);
            logger.info("pad端记录接口getCustomerRecordCount完成，customerId:" + customerId);
            return res;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return 0;
        }
    }

    /**
     * PAD客户端更新接口
     *
     * @return json string
     */
    @Override
    public String checkUpdate(String maxVersion) {
        logger.info("pad端记录接口checkUpdate开始");
        String result = "";
        String version = "";
        String curVersion = maxVersion;
        String url = "";
        AppVersion v = new AppVersion();
        try {
            // 取得webapp下pad-app里面最新版apk文件
            HttpServletRequest request = XFireServletController.getRequest();
            String classPath = request.getSession().getServletContext().getRealPath("");
            String appDir = classPath + File.separator + APP_DIR;
            File appDirFile = new File(appDir);
            if(!appDirFile.exists()){
                appDirFile.mkdirs();
            }
            String[] extensions = {APP_FILE_TYPE};
            Collection<File> colls = FileUtils.listFiles(appDirFile, extensions, false);
            String suffix = "." + APP_FILE_TYPE;
            if (colls != null && colls.size() > 0) {
                ArrayList<File> list = new ArrayList<File>();
                list.addAll(colls);
                //取所有里面版本最高的apk
                for (int i = 0; i < list.size(); i++) {
                    version = list.get(i).getName();
                    if (version != null && version.contains(suffix)) {
                        version = version.replace(suffix, "");
                        String[] strs = version.split("\\.");
                        String[] maxStrs = maxVersion.split("\\.");
                        //先判断第一个数取 最大的
                        if (Integer.parseInt(maxStrs[0]) < Integer.parseInt(strs[0])) {
                            maxVersion = version;
                            continue;
                        } else if (Integer.parseInt(maxStrs[0]) == Integer.parseInt(strs[0])) {
                            //如果第一位相同  开始判断第二位取最大
                            if (Integer.parseInt(maxStrs[1]) < Integer.parseInt(strs[1])) {
                                maxVersion = version;
                                continue;
                            } else if (Integer.parseInt(maxStrs[1]) == Integer.parseInt(strs[1])) {
                                //第二位相同 看第三位
                                if (Integer.parseInt(maxStrs[2]) <= Integer.parseInt(strs[2])) {
                                    maxVersion = version;
                                    continue;
                                }
                            }
                        }
                    }
                }
                v.setVersion(maxVersion);
            }
            logger.info(maxVersion);
            // 取得 apk download url
            if(!maxVersion.equals(curVersion)){
                url = "/" + APP_DIR + "/" + maxVersion + suffix;
            }
            v.setUrl(url);
            logger.info("pad端记录接口checkUpdate完成");
            result = JSONObject.fromObject(v).toString();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "false";
        }
        return result;
    }

    /**
     * 得到文件md5
     *
     * @param f 目标文件
     * @return 目标文件md5
     */
    private String getFileMD5(File f) {
        String result = "";
        FileInputStream fis = null;
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            fis = new FileInputStream(f);
            int bSize = (f.length() > 1024 * 1024 * 4) ? 1024 * 1024 * 4 : Integer.parseInt(String
                    .valueOf(f.length()));
            byte[] buffer = new byte[bSize];
            int length;
            while ((length = fis.read(buffer)) != -1) {
                md.update(buffer, 0, length);
            }
            result = new String(Hex.encodeHex(md.digest()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                if (fis != null)
                    fis.close();
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
        return result;
    }

    /**
     * @param s
     * @return
     */
    private String subZeroAndDot(Object s) {
        if (s == null) {
            return "";
        }
        String result = s.toString();
        if (result.indexOf(".") > 0) {
            result = result.replaceAll("0+?$", "");// 去掉多余的0
            result = result.replaceAll("[.]$", "");// 如最后一位是.则去掉
        }
        return result;
    }

    /**
     * 话机 查询未读留言,未接来电
     *
     * @param account 登入账号
     * @return
     */
    public String[] getMessageCounts(String account) {
        logger.info("pad端记录接口getMessageCounts开始,account:" + account);
        Map<String, Integer> map = messageNoticeService.getMessageCounts(account);
        int count = 0;
        for (Integer c : map.values()) {
            if (c > 0)
                count++;
        }
        JSONObject jo = new JSONObject();
        jo.putAll(map);
        logger.info("pad端记录接口getMessageCounts完成,account:" + account);
        return new String[]{String.valueOf(count), jo.toString()};
    }

    /**
     * 清空通话窗口的Session
     */
    public void talkLogout() {
        logger.info("pad端记录接口talkLogout开始");
        HttpServletRequest request = XFireServletController.getRequest();
        request.getSession().setAttribute(Constants.SESSION_LOGININFO, null);
        logger.info("pad端记录接口talkLogout完成");
    }

    /**
     * 话机 查询未读留言,未接来电
     *
     * @param account
     *
     *            public int getUnreadMessage(String account) { int total = 0;
     *            //提醒总数 int isCall = 0; int isMess = 0; int isVisitRemind = 0;
     *            int isSmsRemind = 0; int isOverDueTask = 0; int isAssignTask =
     *            0; int isSendSms = 0; int isSendMms = 0; int isVerifySms = 0;
     *            int isSendBackSms = 0; int isVerifyMms = 0; int isSendBackMms
     *            = 0; RemindConfig cm = null; try { SysUser sysUser =
     *            sysUserService.getUserByAccount(account); String roleIds =
     *            sysUserService.getRoleIds(sysUser.getUserId()); String[] ids =
     *            roleIds.split(","); boolean flag = false; for (String roleId :
     *            ids) { if (roleId.equals("3") || roleId.equals("4")) { flag =
     *            true; break; } } if (sysUser != null && flag) { isCall =
     *            recordInfoService.getMissedCount(sysUser.getUserId());//未接来电
     *            isMess =
     *            recordInfoService.getUnreadCount(sysUser.getUserId());//未读留言
     *            isVisitRemind =
     *            recordInfoService.getUnreadZTCount(sysUser.getUserId());//未读座谈
     *            isSmsRemind =
     *            smsReceiveLogService.GetAllSmsReceiveLogCountByUserId(sysUser
     *            .getUserId()); //未读短信 isOverDueTask =
     *            tskTaskService.getExpiredTaskCount(sysUser.getUserId());//过期任务
     *            isAssignTask =
     *            tskTaskService.getUnallocatedMarketCount(sysUser
     *            .getUserId());//营销任务 isSendSms =
     *            smsSendHistoryService.GetAllSmsSendHistoryCountByUserId
     *            (sysUser .getUserId());//已发短信 isSendMms =
     *            mmsPopupService.getSendedMmsCount(sysUser.getUserId());//已发彩信
     *            isVerifySms =
     *            smsBasicInfoService.GetAllNeedVerifySmsBasicInfoCountByConds
     *            (sysUser .getUserId());//待审核短信 isSendBackSms =
     *            smsBasicInfoService
     *            .GetAllVerifyFailSmsBasicInfoCountByConds(sysUser
     *            .getUserId());//被退回短信 isVerifyMms =
     *            mmsPopupService.getNeedVerifyMmsCount
     *            (sysUser.getUserId());//待审核彩信 isSendBackMms =
     *            mmsPopupService.getVerifyFailMmsCount
     *            (sysUser.getUserId());//被退回彩信 cm =
     *            remindConfigService.queryRemindConfig(sysUser.getUserId()); }
     *            } catch (Exception e) { logger.error(e.getMessage(), e); } if (cm != null)
     *            { if (isCall > 0 && cm.getIsCallRemind() > 0) total++; if
     *            (isMess > 0 && cm.getIsMessageRemind() > 0) total++; if
     *            (isVisitRemind > 0 && cm.getIsVisitRemind() > 0) total++; if
     *            (isOverDueTask > 0 && cm.getIsOverDueTask() > 0) total++; if
     *            (isAssignTask > 0 && cm.getIsAssignTask() > 0) total++; if
     *            (isSmsRemind > 0 && cm.getIsSmsRemind() > 0) total++; if
     *            (isSendSms > 0 && cm.getIsSendSms() > 0) total++; if
     *            (isSendMms > 0 && cm.getIsSendMms() > 0) total++; if
     *            (isVerifySms > 0 && cm.getIsVerifySms() > 0) total++; if
     *            (isSendBackSms > 0 && cm.getIsSendBackSms() > 0) total++; if
     *            (isVerifyMms > 0 && cm.getIsVerifyMms() > 0) total++; if
     *            (isSendBackMms > 0 && cm.getIsSendBackMms() > 0) total++; }
     *            return total; }
     */

    /**
     * 得到当前时间
     *
     * @return
     */
    public String getNowTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        TimeZone timeZoneChina = TimeZone.getTimeZone("Asia/Shanghai");
        format.setTimeZone(timeZoneChina);
        String timeStr = format.format(Calendar.getInstance().getTime());
        return timeStr;
    }

    /**
     * 根据帐号和电话查询客户
     *
     * @param account
     * @param telNum
     * @return
     */
    public String[] getRecentlyCustomer(String account, String telNum) {
        JSONArray ja = this.telephoneTalkService.getRecentlyCustomer(account, telNum);
        return new String[]{String.valueOf(ja.size()), ja.toString()};
    }

    /**
     * 通话开始上传通话信息
     *
     * @param talkId
     * @param account
     * @param custId
     * @return
     */
    public String uploadTalkInfo(String talkId, String account, String custId, String number,
                                 String ringTime, String talkBeginTime, String talkType) {
        try {
            logger.info("pad端记录接口uploadTalkInfo开始,account:" + account + ",talkId:" + talkId + ",custId:" + custId + ",number:" + number + "," +
                    "ringTime:" + ringTime + ",talkBeginTime:" + talkBeginTime + ",talkType:" + talkType);
            SysUser user = sysUserService.getUserByAccount(account);

            if (user != null) {
                PhoneRecordBean record;
                if (this.recordInfoService.isExistRecordByNo(talkId)) {
                    record = recordInfoService.getRecordByNo(talkId);
                } else {
                    record = new PhoneRecordBean();
                }
                if (custId != null && !"".equals(custId))
                    record.setCustomerId(Integer.parseInt(custId));
                record.setSplitCount(0);
                record.setRecordNo(talkId);
                record.setIsDel(1);
                record.setUserId(user.getUserId());
                record.setCreateDate(new Date());
                record.setUpdateDate(new Date());
                record.setCreateUser(user.getUserId());
                record.setUpdateUser(user.getUserId());
                record.setIsArchived(0);
                record.setIsRead(0);

                int callType = Integer.parseInt(talkType);
                if (callType == 1) {
                    PhoneConfig config = phoneConfigService.query(user.getUserId());
                    String telNum = this.recordInfoService.formatIncomingNumber(number, config);
                    record.setRemotePhone(telNum);
                } else if (callType == 2) {
                    PhoneConfig config = phoneConfigService.query(user.getUserId());
                    String telNum = this.recordInfoService.formatOutNumber(number, config);
                    record.setRemotePhone(telNum);
                } else {
                    record.setRemotePhone(number);
                }

                record.setRingTime(DateUtil.strToDate(ringTime, "yyyy-MM-dd HH:mm:ss"));
                record.setStartDate(DateUtil.strToDate(talkBeginTime, "yyyy-MM-dd HH:mm:ss"));
                record.setCallType(callType);
                record.setRecordInfoId(recordInfoService.newTalkId());

                // this.repairPhoneNumber(record, user); // 处理号码规则
                DebugUtil.write("---------------------------连机通话开始-----------------------------------");
                this.recordInfoService.saveTalkRecordByNo(record);
            }
            logger.info("pad端记录接口uploadTalkInfo完成,account:" + account + ",talkId:" + talkId + ",custId:" + custId + ",number:" + number + "," +
                    "ringTime:" + ringTime + ",talkBeginTime:" + talkBeginTime + ",talkType:" + talkType);
            return "success";
        } catch (Exception e) {
            logger.error("RecordWebServicesImpl % uploadTalkInfo", e);
            return "false";
        }
    }

    private void repairPhoneNumber(PhoneRecordBean record, SysUser user) {
        if (record.getCallType() != null) {
            PhoneConfig config = phoneConfigService.query(user.getUserId());
            if (record.getCallType() == TransportConstants.CALL_TYPE_IN
                    || record.getCallType() == TransportConstants.CALL_TYPE_NO_ANSWER
                    || record.getCallType() == TransportConstants.CALL_TYPE_NO_READ_MESSAGE) {
                String formatedNumber = recordInfoService.formatIncomingNumber(
                        record.getRemotePhone(), config);
                record.setRemotePhone(formatedNumber);
            }
            if (record.getCallType() == TransportConstants.CALL_TYPE_OUT) {
                String formatedNumber = recordInfoService.formatOutNumber(record.getRemotePhone(),
                        config);
                record.setRemotePhone(formatedNumber);
            }
        }
    }

    public boolean fileValidateBySize(String filename, Long filesize) {
        try {
            logger.info("pad端记录接口fileValidateBySize开始,filename:" + filename + ",filesize:" + filesize);
            if (filename == null || filesize == null) {
                return false;
            }

            boolean result = false;
            String dir = TransportUtil.getRecordPath(sysParamService)
                    + TransportConstants.UPLOAD_TEMP_DIR;
            File file = new File(dir + "/" + filename);
            if (file.exists()) {
                // 得到服务器端文件大小
                if (file.length() == filesize.longValue()) {
                    result = true;
                    File dest = new File(file.getAbsoluteFile() + "."
                            + TransportConstants.VALIDATED_FILE_OK);
                    FileUtils.copyFile(file, dest, true);
                    FileUtils.forceDelete(file);
                }
            }

            // 如果连续n次校验不成功，则删除文件重新传输
            if (!result) {
                if (TransportConstants.validateFileMap.size() > 2000) {
                    TransportConstants.validateFileMap.clear();
                }
                Integer validateNum = TransportConstants.validateFileMap.get(filename);
                if (validateNum != null) {
                    TransportConstants.validateFileMap.put(filename, validateNum + 1);
                } else {
                    TransportConstants.validateFileMap.put(filename, 1);
                }
                validateNum = TransportConstants.validateFileMap.get(filename);
                if (validateNum != null && validateNum > 20) {
                    String errorDir = dir + "/error";
                    FileUtil.createDir(errorDir);
                    FileUtil.moveFile2Dir(file, errorDir);
                    TransportConstants.validateFileMap.remove(filename);
                    logger.info("file validate fail: " + file.getName());
                }
            } else {
                // 成功
                if (filename.startsWith("pic_")) {// 照片
                    PhotoDataXmlParserService job = (PhotoDataXmlParserService) SpringContextUtil
                            .getBean("photoDataXmlParserService");
                    job.doJob(FilenameUtils.getBaseName(FilenameUtils.getBaseName(filename)));
                } else if (filename.startsWith("aud_")) {// 录音
                    AudioDataXmlParserService job = (AudioDataXmlParserService) SpringContextUtil
                            .getBean("audioDataXmlParserService");
                    job.doJob(FilenameUtils.getBaseName(FilenameUtils.getBaseName(filename)));
                } else if (filename.startsWith("mov_")) {// 视频
                    VideoDataXmlParserService job = (VideoDataXmlParserService) SpringContextUtil
                            .getBean("videoDataXmlParserService");
                    job.doJob(FilenameUtils.getBaseName(FilenameUtils.getBaseName(filename)));
                } else if (filename.startsWith("jf_")) {// 照片
                    JfDataXmlParserService job = (JfDataXmlParserService) SpringContextUtil
                            .getBean("jfDataXmlParserService");
                    job.doJob(FilenameUtils.getBaseName(FilenameUtils.getBaseName(filename)));
                } else if(filename.startsWith("zx_")){//征信照片
                	ZxDataXmlParserService job = (ZxDataXmlParserService) SpringContextUtil
                            .getBean("zxDataXmlParserService");
                    job.doJob(FilenameUtils.getBaseName(FilenameUtils.getBaseName(filename)));
                    
                } else if (filename.startsWith("dh_")){//贷后检查照片
                	DhDataXmlParserService job=(DhDataXmlParserService) SpringContextUtil
                			.getBean("dhDataXmlParserService");
                	job.doJob(FilenameUtils.getBaseName(FilenameUtils.getBaseName(filename)));
                } else {
                    RecordInfoXmlParserJobImpl job = (RecordInfoXmlParserJobImpl) SpringContextUtil
                            .getBean("autoRecordInfoXmlParserTask");// 核心系统录音文件
                    job.doJob(FilenameUtils.getBaseName(filename));
                }
            }
            logger.info("pad端记录接口fileValidateBySize完成,filename:" + filename + ",filesize:" + filesize);
            logger.info("fileValidateBySize: " + filename);
            return result;
        } catch (Exception e) {
            logger.error("fileValidate", e);
            return false;
        }
    }

    /**
     * PAD XML文件收取
     *
     * @param xmlString
     * @param xmlLength
     * @return
     */
    public boolean receiveXmlFile(String filename, String xmlString, Long xmlLength) {
        if (StringUtils.isEmpty(xmlString)) {
            return false;
        }

        boolean result = false;
        try {
            logger.info("pad端记录接口receiveXmlFile开始,filename:" + filename + ",xmlString:" + xmlString + ",xmlLength:" + xmlLength);
            if (xmlString.length() == xmlLength) {
                String dir = TransportUtil.getRecordPath(sysParamService)
                        + TransportConstants.UPLOAD_TEMP_DIR;
                File file = new File(dir + "/" + filename + "."
                        + TransportConstants.VALIDATED_FILE_OK);
                FileUtils.writeStringToFile(file, xmlString, "utf-8");

                // 成功
                if (filename.startsWith("pic_")) {// 照片
                    PhotoDataXmlParserService job = (PhotoDataXmlParserService) SpringContextUtil
                            .getBean("photoDataXmlParserService");
                    job.doJob(FilenameUtils.getBaseName(FilenameUtils.getBaseName(filename)));
                } else if (filename.startsWith("aud_")) {// 录音
                    AudioDataXmlParserService job = (AudioDataXmlParserService) SpringContextUtil
                            .getBean("audioDataXmlParserService");
                    job.doJob(FilenameUtils.getBaseName(FilenameUtils.getBaseName(filename)));
                } else if (filename.startsWith("mov_")) {// 视频
                    VideoDataXmlParserService job = (VideoDataXmlParserService) SpringContextUtil
                            .getBean("videoDataXmlParserService");
                    job.doJob(FilenameUtils.getBaseName(FilenameUtils.getBaseName(filename)));
                } else if (filename.startsWith("jf_")) {// 照片
                    JfDataXmlParserService job = (JfDataXmlParserService) SpringContextUtil
                            .getBean("jfDataXmlParserService");
                    job.doJob(FilenameUtils.getBaseName(FilenameUtils.getBaseName(filename)));                    
                } else if(filename.startsWith("zx_")){//征信照片
                	ZxDataXmlParserService job = (ZxDataXmlParserService) SpringContextUtil
                            .getBean("zxDataXmlParserService");
                    job.doJob(FilenameUtils.getBaseName(FilenameUtils.getBaseName(filename)));
                } else if (filename.startsWith("dh_")){//贷后检查照片
                	DhDataXmlParserService job=(DhDataXmlParserService) SpringContextUtil
                			.getBean("dhDataXmlParserService");
                	job.doJob(FilenameUtils.getBaseName(FilenameUtils.getBaseName(filename)));
                }else {
                    RecordInfoXmlParserJobImpl job = (RecordInfoXmlParserJobImpl) SpringContextUtil
                            .getBean("autoRecordInfoXmlParserTask");// 核心系统录音文件
                    job.doJob(FilenameUtils.getBaseName(filename));
                }
                logger.info("pad端记录接口receiveXmlFile完成,filename:" + filename + ",xmlString:" + xmlString + ",xmlLength:" + xmlLength);
                result = true;
                logger.info("receiveXmlFile: " + filename);
            }
        } catch (Exception e) {
            logger.error("receiveXmlFile", e);
            return false;
        }

        return result;
    }

    /**
     * PAD录音文件校验
     *
     * @param filename 文件名
     * @param md5      文件md5码
     * @return 是否上传成功
     */
    public boolean fileValidate(String filename, String md5) {
        try {
            logger.info("pad端记录接口fileValidate开始,filename:" + filename + ",md5:" + md5);
            if (filename == null || md5 == null) {
                return false;
            }

            boolean result = false;
            String dir = TransportUtil.getRecordPath(sysParamService)
                    + TransportConstants.UPLOAD_TEMP_DIR;
            File file = new File(dir + "/" + filename);
            if (file.exists()) {
                // 得到服务器端文件的md5码
                String serverMd5 = getFileMD5(file);
                if (md5.equalsIgnoreCase(serverMd5)) {
                    result = true;
                    File dest = new File(file.getAbsoluteFile() + "."
                            + TransportConstants.VALIDATED_FILE_OK);
                    FileUtils.copyFile(file, dest, true);
                    FileUtils.forceDelete(file);
                }
            }

            // 如果连续n次校验不成功，则删除文件重新传输
            if (!result) {
                if (TransportConstants.validateFileMap.size() > 2000) {
                    TransportConstants.validateFileMap.clear();
                }
                Integer validateNum = TransportConstants.validateFileMap.get(filename);
                if (validateNum != null) {
                    TransportConstants.validateFileMap.put(filename, validateNum + 1);
                } else {
                    TransportConstants.validateFileMap.put(filename, 1);
                }
                validateNum = TransportConstants.validateFileMap.get(filename);
                if (validateNum != null && validateNum > 20) {
                    String errorDir = dir + "/error";
                    FileUtil.createDir(errorDir);
                    FileUtil.moveFile2Dir(file, errorDir);
                    TransportConstants.validateFileMap.remove(filename);
                    logger.info("file validate fail: " + file.getName());
                }
            } else {
                // 成功
                if (filename.startsWith("pic_")) {// 照片
                    PhotoDataXmlParserService job = (PhotoDataXmlParserService) SpringContextUtil
                            .getBean("photoDataXmlParserService");
                    job.doJob(FilenameUtils.getBaseName(FilenameUtils.getBaseName(filename)));
                } else if (filename.startsWith("aud_")) {// 录音
                    AudioDataXmlParserService job = (AudioDataXmlParserService) SpringContextUtil
                            .getBean("audioDataXmlParserService");
                    job.doJob(FilenameUtils.getBaseName(FilenameUtils.getBaseName(filename)));
                } else if (filename.startsWith("mov_")) {// 视频
                    VideoDataXmlParserService job = (VideoDataXmlParserService) SpringContextUtil
                            .getBean("videoDataXmlParserService");
                    job.doJob(FilenameUtils.getBaseName(FilenameUtils.getBaseName(filename)));
                } else if(filename.startsWith("zx_")){//征信照片
                	ZxDataXmlParserService job = (ZxDataXmlParserService) SpringContextUtil
                            .getBean("zxDataXmlParserService");
                    job.doJob(FilenameUtils.getBaseName(FilenameUtils.getBaseName(filename)));
                    
                } else if (filename.startsWith("dh_")){//贷后检查照片
                	DhDataXmlParserService job=(DhDataXmlParserService) SpringContextUtil
                			.getBean("dhDataXmlParserService");
                	job.doJob(FilenameUtils.getBaseName(FilenameUtils.getBaseName(filename)));
                } else {
                    RecordInfoXmlParserJobImpl job = (RecordInfoXmlParserJobImpl) SpringContextUtil
                            .getBean("autoRecordInfoXmlParserTask");// 核心系统录音文件
                    job.doJob(FilenameUtils.getBaseName(filename));
                }
            }
            logger.info("pad端记录接口fileValidate完成,filename:" + filename + ",md5:" + md5);
            return result;
        } catch (Exception e) {
            logger.error("fileValidate", e);
            return false;
        }
    }

    // 用户登录上传地理位置信息
    public String uploadGpsInfo(String account, String lat, String lng) {
        try {
            logger.info("pad端记录接口uploadGpsInfo开始,account:" + account + ",lat:" + lat + ",lng:" + lng);
            String latS = String.valueOf(lat);
            String lngS = String.valueOf(lng);

            SysUser user = sysUserService.getUserByAccount(account);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("userId", user.getUserId());
            List<MapUserGps> list = mapUserGpsService.getUserGpsByCondition(map);
            if (list != null && list.size() > 0) {
                // 更新
                MapUserGps gps = list.get(0);
                gps.setGpsLat(latS);
                gps.setGpsLng(lngS);
                gps.setUpdateDate(Calendar.getInstance().getTime());
                mapUserGpsService.updateMapUserGps(gps);

                MapUserGpsLog bean = new MapUserGpsLog();
                bean.setGpsLat(latS);
                bean.setGpsLng(lngS);
                bean.setRemark("");
                bean.setUserGpsId(gps.getUserGpsId());
                mapUserGpsLogService.addMapUserGpsLog(bean);
                mapUserGpsLogService.delMapUserGpsLog(gps.getUserGpsId());//删除一个月以前的数据

            } else {
                // 新增
                MapUserGps gps = new MapUserGps();
                gps.setUserId(user.getUserId());
                gps.setGpsLat(latS);
                gps.setGpsLng(lngS);
                gps.setUpdateDate(Calendar.getInstance().getTime());
                gps.setRemark("");
                mapUserGpsService.addMapUserGps(gps);

                MapUserGpsLog bean = new MapUserGpsLog();
                bean.setGpsLat(latS);
                bean.setGpsLng(lngS);
                bean.setRemark("");
                bean.setUserGpsId(gps.getUserGpsId());
                mapUserGpsLogService.addMapUserGpsLog(bean);

            }
            logger.info("pad端记录接口uploadGpsInfo完成,account:" + account + ",lat:" + lat + ",lng:" + lng);
            return "true";
        } catch (Exception e) {
            return "false";
        }
    }

    /**
     * PAD新建/修改拜访
     *
     * @param recordInfoId
     * @param customerId
     * @param customerName
     * @param commProgress
     * @param visitRemark
     * @param visitDate
     * @param account
     * @return
     */
    public String saveVisit(Integer recordInfoId, Integer customerId, String customerName,
                            Integer commProgress, String visitRemark, String visitDate,
                            String account, String bizTypeCode) {
        try {
            logger.info("pad端记录接口saveVisit开始,account:" + account + ",recordInfoId:" + recordInfoId + ",customerId:" + customerId + "," +
                    "customerName:" + customerName + ",commProgress:" + commProgress + ",visitRemark:" + visitRemark + ",visitDate:" + visitDate + "" +
                    ",bizTypeCode：" + bizTypeCode);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SysUser sysuser = sysUserService.getUserByAccount(account);
            VisitRecord visitRecord = new VisitRecord();
            visitRecord.setRecordInfoId(recordInfoId);
            visitRecord.setUserId(sysuser.getUserId());
            visitRecord.setCustomerId(customerId);
            visitRecord.setCustomerName(customerName);
            visitRecord.setCommProgress(commProgress);
            visitRecord.setRemark(visitRemark);
            visitRecord.setStartDate(df.parse(visitDate + ":00"));
            visitRecord.setCallType(5);

            BaseCrmCustomer customer = new BaseCrmCustomer();
            customer = this.crmCustomerService.getCrmCustomerById(customerId);
            if (customer != null) {
                int phoneType = customer.getDefaultPhoneType();
                if (phoneType == 1) {// 根据默认号码类型设置号码
                    visitRecord.setRemotePhone(customer.getMobilePhone1());
                } else if (phoneType == 2) {
                    visitRecord.setRemotePhone(customer.getMobilePhone2());
                } else if (phoneType == 3) {
                    visitRecord.setRemotePhone(customer.getPhone());
                } else {
                    visitRecord.setRemotePhone(customer.getFax());
                }

                // 组装客户冗余字段
                String cusStr = "";
                if (!StringUtil.isEmpty(customer.getCustomerName())) {
                    cusStr += customer.getCustomerName() + "_";
                }
                if (!StringUtil.isEmpty(customer.getCustomerNamePinyin())) {
                    cusStr += customer.getCustomerNamePinyin() + "_";
                }
                if (!StringUtil.isEmpty(customer.getMobilePhone1())) {
                    cusStr += customer.getMobilePhone1() + "_";
                }
                if (!StringUtil.isEmpty(customer.getMobilePhone2())) {
                    cusStr += customer.getMobilePhone2() + "_";
                }
                if (!StringUtil.isEmpty(customer.getPhone())) {
                    cusStr += customer.getPhone() + "_";
                }
                if (!StringUtil.isEmpty(customer.getFax())) {
                    cusStr += customer.getFax() + "_";
                }
                visitRecord.setCustomerStr(cusStr);

                // 更新客户上次联系时间
                if (customer.getLastContactDate() == null
                        || visitRecord.getStartDate().after(customer.getLastContactDate()))
                    crmCustomerService.updateLastContactDate(visitRecord.getCustomerId(),
                            visitRecord.getStartDate(), EnumRecord.VISIT_INFO.getValue());

            }
            visitRecord.setUserId(sysuser.getUserId());
            visitRecord.setRecordNo(String.valueOf(System.currentTimeMillis()));
            visitRecord.setCreatUser(sysuser.getUserId());
            if (!StringUtil.isNullOrEmpty(bizTypeCode)) {
                visitRecord.setBizType(Integer.parseInt(bizTypeCode));
            }

            if (recordInfoId == -1) {// recordInfoId -1 添加
                this.visitRecordService.addVisitRecord(visitRecord);
            } else {
                this.visitRecordService.updateVisitRecord(visitRecord);
            }
            logger.info("pad端记录接口saveVisit完成,account:" + account + ",recordInfoId:" + recordInfoId + ",customerId:" + customerId + "," +
                    "customerName:" + customerName + ",commProgress:" + commProgress + ",visitRemark:" + visitRemark + ",visitDate:" + visitDate + "" +
                    ",bizTypeCode：" + bizTypeCode);
            return "true";
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "false";
        }
    }

    // /**
    // *
    // * @return
    // * @see
    // com.banger.mobile.facade.webservice.RecordWebService#getAllIdType()
    // */
    // public String getAllIdType() {
    // try {
    // JSONArray jsonArray = new JSONArray();
    // List<CardType> list = cardTypeService.getCardTypes();
    // String result = jsonArray.fromObject(list).toString();
    // return result;
    // } catch (Exception e) {
    // logger.error(e.getMessage(), e);
    // return null;
    // }
    // }

    /**
     * PAD管理-禁用停用(登录验证)
     *
     * @param account      账号
     * @param serialNumber 序列号
     * @return 1:启用(正常使用) 2:禁用(不允许登陆和连接系统服务端)，直接退出 3:停用(不允许登陆和连接系统服务端)，直接退出
     * 4:pad使用人员已改变，直接退出
     */
   public Integer getPadUseStatus(String account, String serialNumber) {
        try {
            logger.info("pad端记录接口getPadUseStatus开始,account:" + account + ",serialNumber:" + serialNumber);
            int status = 3;
            PadInfo info = padInfoDao.getPadUseStatus(account, serialNumber);
            if (info != null) {
                if (info.getUseStatus() == 1) {
                    if (!info.getUsePersonnel().equals(account)) {
                        status = 4;
                    } else {
                        status = info.getUseStatus();
                    }
                } else {
                    status = info.getUseStatus();
                }
            }
            logger.info("pad端记录接口getPadUseStatus完成,account:" + account + ",serialNumber:" + serialNumber);
            return status;
        } catch (Exception e) {
            logger.error("RecordWebServicesImpl % getPadUseStatus", e);
            return null;
        }
    }

    /**
     * PAD管理-使用记录
     *
     * @param account      账号
     * @param serialNumber 序列号
     * @param logType      1登录2登出
     * @return 返回结果“true”表示可以登录,”false”表示不能登录（比如账号和序列号不匹配，还没有被分配人员）
     */
    public String useLog(String account, String serialNumber, Integer logType) {
        String bool = "false";
        try {
            logger.info("pad端记录接口useLog开始,account:" + account + ",serialNumber:" + serialNumber + ",logType:" + logType);
            SysUser user = sysUserService.getAllUserByAccount(account);
            PadInfo info = new PadInfo();
            info.setSerialNumber(serialNumber);
            info = padInfoDao.getPadInfoBySerialNumber(info);
            if (user != null && info != null) {
                if (info.getUserId() != null) {
                    if (info.getUserId().equals(user.getUserId())) {
                        bool = "true";
                        SysPadLog log = new SysPadLog();
                        log.setPadInfoId(info.getPadInfoId());
                        log.setUserId(user.getUserId());
                        log.setLoginType(logType);
                        log.setCreateUser(user.getUserId());
                        sysPadLogDao.addPadLog(log);
                    }
                }
            }
            logger.info("pad端记录接口useLog完成,account:" + account + ",serialNumber:" + serialNumber + ",logType:" + logType);
            return bool;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "false";
        }
    }

    /**
     * PAD管理-流量记录
     *
     * @param account      账号
     * @param serialNumber 序列号
     * @param upload       上传总量(k)
     * @param download     下载总量(k)
     * @param bootTime     PAD开机时间
     * @return
     */
    public String networkFlowLog(String account, String serialNumber, Integer upload,
                                 Integer download, String bootTime) {
        try {
            logger.info("pad端记录接口networkFlowLog开始,account:" + account + ",serialNumber:" + serialNumber + ",upload:" + upload + "" +
                    ",download:" + download + ",bootTime");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            PadInfo info = new PadInfo();
            info.setSerialNumber(serialNumber);
            info = padInfoDao.getPadInfoBySerialNumber(info);
            if (info != null) {
                SysPadUploadLog uploadLog = new SysPadUploadLog();
                uploadLog.setPadInfoId(info.getPadInfoId());
                uploadLog.setCountUpload(new BigDecimal(upload));
                uploadLog.setCountDownLoad(new BigDecimal(download));
                Date date = format.parse(bootTime);
                uploadLog.setUpdateDate(date);
                sysPadUploadLogDao.addPadUploadLog(uploadLog);
            }
            logger.info("pad端记录接口networkFlowLog完成,account:" + account + ",serialNumber:" + serialNumber + ",upload:" + upload + "" +
                    ",download:" + download + ",bootTime");
        } catch (Exception e) {
        }
        return "false";
    }

    /**
     * 根据文件地址查询录音文件是否存在
     */
    public String queryFileByUrl(String url) {
        try {
            logger.info("pad端记录接口queryFileByUrl开始,url:" + url);
            String flag = "true";
            if (StringUtil.isEmpty(url)) {
                return "false";
            }

            File f = new File(url);
            if (!f.exists()) {
                flag = "false";

                String fileName = FilenameUtils.getName(url);
                SysUploadFile sysUploadFile = sysUploadFileService.queryByFileName(fileName);
                if (sysUploadFile != null) {
                    if (sysUploadFile.getStorageType().equalsIgnoreCase("cm")) {
                        // 保存在第三方接口CM
                        String caseNo = sysUploadFile.getCaseNo();
                        String realFileUrl = "";
                        if (StringUtils.isNotEmpty(caseNo)) {
                            CustomerData data = new CustomerData();
                            data.setCreateUser(sysUploadFile.getUploadUserId());
                            List<CmDownloadUrl> list = caseHelperService.getCaseDownloadUrl(caseNo,
                                    data);
                            for (CmDownloadUrl c : list) {
                                if (c.getFileName().equals(sysUploadFile.getFileName())) {
                                    realFileUrl = c.getDownloadUrl();
                                    break;
                                }
                            }
                        }
                        if (StringUtils.isNotEmpty(realFileUrl)) {
                            flag = "true";
                        }
                    }
                }
            }
            logger.info("pad端记录接口queryFileByUrl完成,url:" + url);
            return flag;
        } catch (Exception e) {
            logger.error("queryFileByUrl", e);
            return "false";
        }
    }

    /**
     * PAD上传文件名和序列号
     *
     * @param account      帐号
     * @param filename     上传文件名
     * @param serialNumber 序列号
     * @return
     */
    public String uploadFileName(String account, String filename, String serialNumber) {
        logger.info("用户" + account + " PAD上传文件名：" + filename + "       序列号：" + serialNumber);
        return "true";
    }


}
