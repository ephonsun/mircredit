/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :ThinkPad
 * Create Date:2013-6-29
 */
package com.banger.mobile.facade.impl.data;

import com.banger.mobile.domain.model.customer.CrmCustomer;
import com.banger.mobile.domain.model.data.CmDownloadUrl;
import com.banger.mobile.domain.model.data.CustomerData;
import com.banger.mobile.domain.model.user.SysUser;
import com.banger.mobile.facade.customer.CrmCustomerService;
import com.banger.mobile.facade.data.CaseHelperService;
import com.banger.mobile.facade.user.SysUserService;
import net.sf.json.JSONArray;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.util.Assert;

import javax.xml.namespace.QName;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.*;

/**
 * @author ThinkPad
 * @version $Id: CaseHelper.java,v 0.1 2013-6-29 下午10:01:35 ThinkPad Exp $
 */
public class CaseHelperServiceImpl implements CaseHelperService {
    private static final Logger logger = Logger.getLogger(CaseHelperServiceImpl.class);

    private SysUserService      sysUserService;                                        // 系统用户
    private CrmCustomerService  crmCustomerService;                                    // 客户service

    private String              interfaceUrl;                                          // 影像管理接口url(主要包括上传资料)
    private String              fileServerHost;
    private int                 fileServerPort;
    private String              storageType;

    private String              appInterfaceUrl;                                       // 影像应用接口url(包括创建案卷号、下载或查看资料)
    private String              localHostIp;                                           // 有效应用ip(本地机器ip地址，根据不同的ip确定不同的分行或总行)
    private String              interfaceUserId;                                       // 影像接口所用到的用户id
    private String              deptId;                                                // 影像接口所用到的部门id
    private String              audioType;                                             // 音频资料类型
    private String              audioTypeName;                                         // 音频资料类型名
    private String              audioFileType;                                         // 音频资料所支持的文件类型
    private String              videoType;                                             // 视频资料类型
    private String              videoTypeName;                                         // 视频资料类型名
    private String              videoFileType;                                         // 视频资料所支持的文件类型

    private String              nodeName;                                              // 节点名
    private Integer             support;                                               // 影像是否支持自定义的用户和部门传参数
    private Integer             supportHostIp;                                         // 影像是否支持自定义的本地服务器ip的传参数

    private String              loginToken;                                            // userId和deptId的MD5码
    private String              roleId;                                                // 移动贷款系统用户角色，60009表示角色为影像主管
    private String              bizStep;                                               // 不区分业务阶段就传空
    private String              dataType;                                              // 案卷类型
                                                                                        // yddk
    private String              busiType;                                              // 业务类型
    private String              appId;                                                 // 应用ID固定为YDDK
    private String              setting;

    public String getSetting() {
        return setting;
    }

    public void setSetting(String setting) {
        this.setting = setting;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getBizStep() {
        return bizStep;
    }

    public void setBizStep(String bizStep) {
        this.bizStep = bizStep;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getBusiType() {
        return busiType;
    }

    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }

    public Integer getSupportHostIp() {
        return supportHostIp;
    }

    public void setSupportHostIp(Integer supportHostIp) {
        this.supportHostIp = supportHostIp;
    }

    public SysUserService getSysUserService() {
        return sysUserService;
    }

    public void setSysUserService(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    public CrmCustomerService getCrmCustomerService() {
        return crmCustomerService;
    }

    public void setCrmCustomerService(CrmCustomerService crmCustomerService) {
        this.crmCustomerService = crmCustomerService;
    }

    public Integer getSupport() {
        return support;
    }

    public void setSupport(Integer support) {
        this.support = support;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getInterfaceUserId() {
        return interfaceUserId;
    }

    public void setInterfaceUserId(String interfaceUserId) {
        this.interfaceUserId = interfaceUserId;
    }

    public String getAudioFileType() {
        return audioFileType;
    }

    public void setAudioFileType(String audioFileType) {
        this.audioFileType = audioFileType;
    }

    public String getVideoFileType() {
        return videoFileType;
    }

    public void setVideoFileType(String videoFileType) {
        this.videoFileType = videoFileType;
    }

    public String getAudioTypeName() {
        return audioTypeName;
    }

    public void setAudioTypeName(String audioTypeName) {
        this.audioTypeName = audioTypeName;
    }

    public String getVideoTypeName() {
        return videoTypeName;
    }

    public void setVideoTypeName(String videoTypeName) {
        this.videoTypeName = videoTypeName;
    }

    public String getAudioType() {
        return audioType;
    }

    public void setAudioType(String audioType) {
        this.audioType = audioType;
    }

    public String getVideoType() {
        return videoType;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }

    public String getAppInterfaceUrl() {
        return appInterfaceUrl;
    }

    public void setAppInterfaceUrl(String appInterfaceUrl) {
        this.appInterfaceUrl = appInterfaceUrl;
    }

    public String getLocalHostIp() {
        return localHostIp;
    }

    public void setLocalHostIp(String localHostIp) {
        this.localHostIp = localHostIp;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    /**
     * 得到案卷号
     * 
     * @param data
     * @return
     */
    public String getCaseNoOther(CustomerData data) {
        String result = "other_file";
        createCase(data, result);

        return result;
    }

    /**
     * 得到案卷号
     * 
     * @param data
     * @return
     */
    public String getCaseNo(CustomerData data) {
        String result = "";

        if (data != null) {
            if (data.getLoanId() != null && data.getLoanId() > 0) {
                result = "loan_" + data.getLoanId() + "_" + data.getEventId();
            } else if (data.getCustomerId() != null && data.getCustomerId() > 0) {
                result = "customer_" + data.getCustomerId();
            } else if (data.getUploadUserId() != null && data.getUploadUserId() > 0) {
                result = "user_" + data.getUploadUserId();
            } else {
                result = "other_file";
            }
        }

        // createCase(data.getCreateUser(), result);
        createCase(data, result);

        return result;
    }

    // 创建案卷
    private void createCase(CustomerData data, String caseNo) {
        // 调用第三放接口创建案卷
        if (storageType != null && storageType.equalsIgnoreCase("cm") && setting != null
            && setting.equalsIgnoreCase("2")) {
            try {
                Document doc = DocumentHelper.createDocument();

                Element rootElement = doc.addElement("Service");
                rootElement.addAttribute("name", "CreateCaseService");
                rootElement.addAttribute("url", appInterfaceUrl);

                Element userElement = rootElement.addElement("User");
                if (getSupport().equals(1)) {
                    // 支持自定义用户和部门等参数
                    userElement.addAttribute("userId", String.valueOf(data.getCreateUser()));
                    SysUser sysUser = sysUserService.getSysUserById(data.getCreateUser());
                    userElement.addAttribute("deptId", String.valueOf(sysUser.getDeptId()));
                    userElement.addAttribute("userName", sysUser.getUserName());
                    String curLoginToken = DigestUtils
                        .md5Hex(String.valueOf(data.getCreateUser())
                                + String.valueOf(sysUser.getDeptId()));
                    userElement.addAttribute("loginToken", curLoginToken);
                } else if (getSupport().equals(0)) {
                    userElement.addAttribute("userId", interfaceUserId);
                    userElement.addAttribute("deptId", deptId);
                    userElement.addAttribute("userName", "000395"); // TODO
                    userElement.addAttribute("loginToken", loginToken);
                }

                userElement.addAttribute("roleId", roleId);
                userElement.addAttribute("bizStep", bizStep);

                Element appElement = rootElement.addElement("Application");
                appElement.addAttribute("appId", appId);

                Element inElement = rootElement.addElement("In");
                inElement.addAttribute("dataType", dataType);
                if (this.getSupport().equals(1)) {
                    if (data.getCustomerId() != null) {
                        inElement.addAttribute("customerId", String.valueOf(data.getCustomerId()));
                        CrmCustomer customer = crmCustomerService.getCustomerById(data
                            .getCustomerId());
                        inElement.addAttribute("customerName", customer.getCustomerName());
                    } else {
                        // 在产品等模块，上传资料时，不存在与客户相关，故客户id为空
                        inElement.addAttribute("customerId", "");
                        inElement.addAttribute("customerName", "");
                    }
                } else {
                    inElement.addAttribute("customerId", "test");
                    inElement.addAttribute("customerName", "test");
                }

                Element parameterElement = inElement.addElement("Parameter");
                parameterElement.addAttribute("dataType", dataType);
                parameterElement.addAttribute("id", caseNo); // 案卷号
                parameterElement.addAttribute("busiType", busiType);
                parameterElement.addAttribute("busiName", "移动贷款测试");

                logger.info("创建案卷发送报文：" + doc.asXML());

                String namespace = "http://www.topcheer.com/";
                String operationName = "processRequest";
                Service service = new Service();
                Call call = (Call) service.createCall();
                call.setTargetEndpointAddress(appInterfaceUrl);
                call.setOperationName(new QName(namespace, operationName));
                String message = (String) call.invoke(new Object[] { doc.asXML() });

                logger.info("创建案卷接收报文：" + caseNo + "#####" + message);
                logger.info(caseNo + "创建案卷成功");
            } catch (Exception e) {
                logger.error(caseNo + "创建案卷失败", e);
            }
        }
    }

    public boolean uploadImage(String caseNo, String filePath, CustomerData data) {
        String[] fileTypes = audioFileType.split(",");
        // 默认将所有资料都设置为视频资料
        String docType = videoType;
        String docTypeName = videoTypeName;
        for (String fileType : fileTypes) {
            if (filePath.endsWith(fileType)) {
                // 音频资料
                docType = audioType;
                docTypeName = audioTypeName;
                break;
            }
        }
        String cusUserId = "";
        String cusUserName = "";
        String cusDeptId = "";
        String curLoginToken = "";
        if (this.getSupport().equals(1)) {
            cusUserId = String.valueOf(data.getCreateUser());
            SysUser sysUser = sysUserService.getSysUserById(data.getCreateUser());
            cusDeptId = String.valueOf(sysUser.getDeptId());
            cusUserName = sysUser.getUserName();
            curLoginToken = DigestUtils.md5Hex(cusUserId + cusDeptId);
        } else {
            cusUserId = interfaceUserId;
            cusDeptId = deptId;
            cusUserName = "影像主管";
            curLoginToken = loginToken;
        }
        String curLocalHostIp = "";
        if (this.getSupportHostIp().equals(1)) {
            // 影像是否支持自定义的本地服务器ip的传参数
            curLocalHostIp = this.getFirstNoLoopbackAddress();
        } else {
            curLocalHostIp = localHostIp;
        }
        String docString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Service name=\"CreateImageDocumentInfoService\">"
                           + "<Application appId=\""
                           + appId
                           + "\"/><User userId=\""
                           + cusUserId
                           + "\" userName=\""
                           + cusUserName
                           + "\" loginToken=\""
                           + curLoginToken
                           + "\" deptId=\""
                           + cusDeptId
                           + "\" roleId=\""
                           + roleId
                           + "\" bizStep=\""
                           + bizStep
                           + "\" token=\"\""
                           + "/><Transfer nodeName=\""
                           + nodeName
                           + "\" host=\""
                           + curLocalHostIp
                           + "\" branchLevel1=\"01999\" branchLevel2=\"\"/><In isUploding=\"1\" taskId=\"-1\" isTaskScan=\"0\">"
                           + "<Parameter type=\"doc\" isDocType=\"0\" docId=\"D_0001\" docName=\""
                           + docTypeName
                           + "\" catalog=\"\" parentDirId=\"20e4e43541eb726101437761c99b0004\" "
                           + "docNo=\"\" docType=\""
                           + docType
                           + "\" docTypeName=\""
                           + docTypeName
                           + "\" caseNo=\""
                           + caseNo
                           + "\" caseType=\"yddk\" sourceType=\"1\" imageDpi=\"0\" imageAngle=\"0\" ownerDept=\"\" operatorSystem=\"\" sort=\"\" pageCount=\"1\" fileSource=\"1\"/>"
                           + "<Parameter type=\"file\" docId=\"D_0001\" docName=\""
                           + docTypeName
                           + "\" docType=\""
                           + docType
                           + "\" docTypeName=\""
                           + docTypeName
                           + "\" fileName=\""
                           + FilenameUtils.getName(filePath)
                           + "\" filePath=\""
                           + filePath
                           + "\" "
                           + "fileIndex=\"1\" imageDpi=\"300\" imageAngle=\"0\"/></In></Service>";
        // 2ae5e43f3f5174c0013f5178be1d0003 --> 20e4e43541eb726101437761c99b0004
        boolean result = false;
        try {
            logger.info("CreateImageDocumentInfoService发送报文：" + docString);
            Service service = new Service();
            Call call = (Call) service.createCall();
            call.setTargetEndpointAddress(interfaceUrl);

            call.setOperationName(new QName("processRequest"));
            String msg = (String) call.invoke(new Object[] { docString });
            logger.info("CreateImageDocumentInfoService收到报文：" + caseNo + "####" + msg);

            if (msg != null && msg.contains("status=\"0\"")) {
                result = true;
            }
        } catch (Exception e) {
            logger.error("CreateImageDocumentInfoService出错：", e);
        }
        return result;
    }

    /**
     * @param caseNo
     * @return
     */
    public List<CmDownloadUrl> getCaseDownloadUrl(String caseNo, CustomerData data) {
        String cusUserId = "";
        String cusUserName = "";
        String cusDeptId = "";
        String curLoginToken = "";
        if (this.getSupport().equals(1)) {
            cusUserId = String.valueOf(data.getCreateUser());
            SysUser sysUser = sysUserService.getSysUserById(data.getCreateUser());
            cusDeptId = String.valueOf(sysUser.getDeptId());
            cusUserName = sysUser.getUserName();
            curLoginToken = DigestUtils.md5Hex(cusUserId + cusDeptId);
        } else {
            cusUserId = interfaceUserId;
            cusDeptId = deptId;
            cusUserName = "孙佳";
            curLoginToken = loginToken;
        }
        String curLocalHostIp = "";
        if (this.getSupportHostIp().equals(1)) {
            // 影像是否支持自定义的本地服务器ip的传参数
            curLocalHostIp = this.getFirstNoLoopbackAddress();
        } else {
            curLocalHostIp = localHostIp;
        }
        String getRouteInfoXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><"
                                 + "Service name=\"DownloadImageService\"><Application appId=\""
                                 + appId
                                 + "\"/><"
                                 + "User userId=\""
                                 + cusUserId
                                 + "\" userName=\""
                                 + cusUserName
                                 + "\" loginToken=\""
                                 + curLoginToken
                                 + "\" deptId=\""
                                 + cusDeptId
                                 + "\" roleId=\""
                                 + roleId
                                 + "\" bizStep=\""
                                 + bizStep
                                 + "\" token=\"\"/><"
                                 + "Transfer nodeName=\""
                                 + nodeName
                                 + "\" host=\""
                                 + curLocalHostIp
                                 + "\" branchLevel1=\"99999\" branchLevel2=\"\"/><"
                                 + "In><Parameter caseType=\"yddk\" caseNo=\""
                                 + caseNo
                                 + "\" docId=\"\"/></In></Service>";
        List<CmDownloadUrl> result = new ArrayList<CmDownloadUrl>();
        try {
            logger.info("DownloadImageService发送报文：" + getRouteInfoXml);
            Document doc = DocumentHelper.parseText(getRouteInfoXml);
            Service service = new Service();

            Call call = (Call) service.createCall();
            call.setTargetEndpointAddress(appInterfaceUrl);
            call.setOperationName(new QName("processRequest"));
            String json = (String) call.invoke(new Object[] { doc.asXML() });
            logger.info("DownloadImageService接收报文：" + json);

            JSONArray jsonArray = JSONArray.fromObject(json);
            result = jsonArray.toList(jsonArray, CmDownloadUrl.class);
        } catch (Exception e) {
            logger.error("DownloadImageService出错：", e);
        }
        return result;
    }

    private Collection<InetAddress> getAllHostAddress() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface
                .getNetworkInterfaces();
            Collection<InetAddress> addresses = new ArrayList<InetAddress>();

            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = inetAddresses.nextElement();
                    addresses.add(inetAddress);
                }
            }

            return addresses;
        } catch (SocketException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private Collection<String> getAllNoLoopbackAddresses() {
        Collection<String> noLoopbackAddresses = new ArrayList<String>();
        Collection<InetAddress> allInetAddresses = getAllHostAddress();

        for (InetAddress address : allInetAddresses) {
            if (!address.isLoopbackAddress()) {
                noLoopbackAddresses.add(address.getHostAddress());
            }
        }

        return noLoopbackAddresses;
    }

    private String getFirstNoLoopbackAddress() {
        Collection<String> allNoLoopbackAddresses = getAllNoLoopbackAddresses();
        Assert.isTrue(!allNoLoopbackAddresses.isEmpty(),
            " Sorry, seems you don't have a network card :( ");
        Iterator<String> its = allNoLoopbackAddresses.iterator();
        int i = 0;
        String hostIp = "";
        while (its.hasNext()) {
            i++;
            if (i == 2) {
                hostIp = its.next();
                break;
            }
            its.next();
        }
        return hostIp;
    }

    public String getInterfaceUrl() {
        return interfaceUrl;
    }

    public void setInterfaceUrl(String interfaceUrl) {
        this.interfaceUrl = interfaceUrl;
    }

    public String getFileServerHost() {
        return fileServerHost;
    }

    public void setFileServerHost(String fileServerHost) {
        this.fileServerHost = fileServerHost;
    }

    public int getFileServerPort() {
        return fileServerPort;
    }

    public void setFileServerPort(int fileServerPort) {
        this.fileServerPort = fileServerPort;
    }

    public String getStorageType() {
        return storageType;
    }

    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }

}
