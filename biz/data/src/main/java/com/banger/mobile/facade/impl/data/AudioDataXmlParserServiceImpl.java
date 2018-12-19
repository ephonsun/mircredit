/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yuanme
 * Create Date:2012-12-6
 */
package com.banger.mobile.facade.impl.data;

import com.banger.mobile.constants.TransportConstants;
import com.banger.mobile.domain.model.base.customer.BaseCrmCustomer;
import com.banger.mobile.domain.model.data.Audio;
import com.banger.mobile.domain.model.data.CustomerData;
import com.banger.mobile.domain.model.data.Event;
import com.banger.mobile.domain.model.loan.LnDunLog;
import com.banger.mobile.domain.model.loan.LnExceptionDunLog;
import com.banger.mobile.domain.model.record.RecordInfo;
import com.banger.mobile.domain.model.user.SysUser;
import com.banger.mobile.facade.customer.CrmCustomerService;
import com.banger.mobile.facade.data.AudioDataXmlParserService;
import com.banger.mobile.facade.data.CustomerDataService;
import com.banger.mobile.facade.data.DataAudioService;
import com.banger.mobile.facade.loan.LnDunLogService;
import com.banger.mobile.facade.loan.LnExceptionDunLogService;
import com.banger.mobile.facade.microTask.TskMicroTaskAutoFinishService;
import com.banger.mobile.facade.param.SysParamService;
import com.banger.mobile.facade.record.RecordInfoService;
import com.banger.mobile.facade.uploadFile.SysUploadFileService;
import com.banger.mobile.facade.user.SysUserService;
import com.banger.mobile.transport.TransportUtil;
import com.banger.mobile.util.DateUtil;
import com.banger.mobile.util.FileUtil;
import com.banger.mobile.util.StringUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import java.io.File;
import java.util.*;

/**
 * @author yuanme
 * @version $Id: AudioDataXmlParserServiceImpl.java,v 0.1 2012-12-6 上午9:44:16
 *          Administrator Exp $
 */
public class AudioDataXmlParserServiceImpl implements AudioDataXmlParserService {
    private static final Logger           logger = Logger
                                                     .getLogger(AudioDataXmlParserServiceImpl.class);

    private SysUserService                sysUserService;
    private SysParamService               sysParamService;
    private CrmCustomerService            crmCustomerService;
    private CustomerDataService           customerDataService;
    private DataAudioService              dataAudioService;
    private RecordInfoService             recordInfoService;
    private TskMicroTaskAutoFinishService tskMicroTaskAutoFinishService;
    private LnDunLogService               lnDunLogService;
    private LnExceptionDunLogService      lnExceptionDunLogService;
    private SysUploadFileService          sysUploadFileService;
    private CaseHelperServiceImpl         caseHelperService;

    /**
     * 从xml文件中提取录音信息
     */
    public synchronized void doJob(String fileName) {
        String dir = TransportUtil.getRecordPath(sysParamService)
                     + TransportConstants.UPLOAD_TEMP_DIR;
        FileUtil.createDir(dir);
        File f = new File(dir + File.separator + fileName + "."
                          + TransportConstants.VALIDATED_FILE_XML_OK);
        if (f.exists()) {
            try {
                // 解析xml文件得到data主表信息
                CustomerData data = getData(f);

                // 解析xml文件得到photo子表信息
                Audio audio = getAudio(f);

                // 解析xml文件得到recrodInfo表信息
                RecordInfo info = getRecord(f);

                processPad(f, dir, data, audio, info);

                logger.info("AudioDataXmlParserServiceImpl doJob info: " + f.getName());
            } catch (Exception e) {
                logger.error("AudioDataXmlParserServiceImpl doJob  error:", e);
            }
        }
    }

    public LnExceptionDunLogService getLnExceptionDunLogService() {
        return lnExceptionDunLogService;
    }

    public void setLnExceptionDunLogService(LnExceptionDunLogService lnExceptionDunLogService) {
        this.lnExceptionDunLogService = lnExceptionDunLogService;
    }

    /**
     * 处理pad
     * 
     * @param f
     * @param recordDir
     * @throws Exception
     */
    private void processPad(File f, String recordDir, CustomerData data, Audio audio,
                            RecordInfo info) throws Exception {
        // xml 文件名 无后缀
        String xmlFileName = f.getName();
        xmlFileName = xmlFileName.replace(TransportConstants.RECORD_INFO_EXTENSION + "."
                                          + TransportConstants.VALIDATED_FILE_OK, "");
        // 对应的录音文件
        //String recordFileName = xmlFileName + TransportConstants.COMPRESS_FILE_EXTENSION + "."
        //                        + TransportConstants.VALIDATED_FILE_OK;
        //不采用压缩加密的文件名
        String recordFileName = xmlFileName + TransportConstants.AUDIO_FILE_EXTENSION + "."
                                + TransportConstants.VALIDATED_FILE_OK;
        File recordFile = new File(recordDir + "/" + recordFileName);
        // 如果xml对应的录音文件存在，则做入库操作
        if (recordFile.exists()) {
            // 录音文件操作
            // 解压
            //CompressUtil.decompress(recordFile);
            //String fname = recordFile.getName().substring(0, recordFile.getName().indexOf(".", 0));
            //File deFile = new File(recordDir + "/" + fname
            //                       + TransportConstants.ENTRYPTD_FILE_EXTENSION);

            // 解密
            //File enFile = new File(recordDir + "/" + fname
            //                       + TransportConstants.AUDIO_FILE_EXTENSION);
            //EncryptionUtil.decryptFile(deFile, enFile);
            FileUtil.createDir(recordDir + "/temp");
            File enFile = new File(recordDir + "/temp/"
                                   + FilenameUtils.getBaseName(recordFile.getName()));
            FileUtils.copyFile(recordFile, enFile, true);

            String today = DateUtil.getDateTime("yyyyMMdd", Calendar.getInstance().getTime());
            String targetDir = TransportUtil.getRecordPath(sysParamService) + "/" + today;
            FileUtil.createDir(targetDir);           

            // 移动到存储点
            String caseNo = caseHelperService.getCaseNo(data);
            int fileId = sysUploadFileService.saveFile(enFile, enFile.getName(), data
                .getUploadUserId(), false, caseNo,data);
            audio.setFileId(fileId);

            // 将录音文件存放路径更新
            info.setFileId(fileId);
            /*
            // 如果是催收记录
            if (data.getEventId() != null && data.getEventId() == 6) {
                //判断是否是异常催收还是普通催收
                if (data.getDunLogId() != null && data.getDunLogId() > 0) {
                    // 普通催收
                    // 是否有催收记录
                    LnDunLog log = lnDunLogService.getDunLogById(data.getDunLogId());
                    if (log != null) {
                        // 有的话
                        // 保存录音数据
                    	if (log.getCustomerDataId() == null) {
                    		customerDataService.addNewCustomerData(data);
                    		log.setCustomerDataId(data.getCustomerDataId());
                    		Map<String, Object> paramMap = new HashMap<String, Object>();
                    		paramMap.put("exceptionDunLogId", log.getDunLogId());
                    		paramMap.put("customerDataId", data.getCustomerDataId());
                    		lnDunLogService.updateDunLogById(paramMap);
                    	}
                        audio.setCustomerDataId(log.getCustomerDataId());
                        dataAudioService.addNewAudio(audio);// 入库
                    } else {
                        // 么有，新增
                        customerDataService.addNewCustomerData(data);
                        // 保存录音数据
                        audio.setCustomerDataId(data.getCustomerDataId());
                        dataAudioService.addNewAudio(audio);// 入库
                        lnDunLogService.addLnDunLogFromPad(data);
                    }
                }
                if (data.getExceptionDunLogId() != null && data.getExceptionDunLogId() > 0) {
                    // 异常催收
                    // 是否有催收记录
                    LnExceptionDunLog log = lnExceptionDunLogService.getExceptionDunLogById(data
                        .getExceptionDunLogId());
                    if (log != null) {
                        // 有的话
                        // 保存录音数据
                    	if (log.getCustomerDataId() == null) {
                    		customerDataService.addNewCustomerData(data);
                    		log.setCustomerDataId(data.getCustomerDataId());
                    		Map<String, Object> paramMap = new HashMap<String, Object>();
                    		paramMap.put("exceptionDunLogId", log.getExceptionDunLogId());
                    		paramMap.put("customerDataId", data.getCustomerDataId());
                    		lnExceptionDunLogService.updateExceptionDunLogById(paramMap);
                    	}
                        audio.setCustomerDataId(log.getCustomerDataId());
                        dataAudioService.addNewAudio(audio);// 入库
                    } else {
                        // 么有，新增
                        customerDataService.addNewCustomerData(data);
                        // 保存录音数据
                        audio.setCustomerDataId(data.getCustomerDataId());
                        dataAudioService.addNewAudio(audio);// 入库
                        lnExceptionDunLogService.addLnExceptionDunLogFromPad(data);
                    }
                }
            } else {
                
            }*/

            
         // 将录音文件存放路径更新至db
            // 如果有客户id
            if (data.getCustomerId() != null && data.getCustomerId() > 0) {
                Map<String, Object> parameterMap = new HashMap<String, Object>();
                parameterMap.put("loanId", data.getLoanId());
                parameterMap.put("customerId", data.getCustomerId());
                parameterMap.put("eventId", data.getEventId());
                List<CustomerData> datas = customerDataService
                    .getCustomerDataByParameterMap(parameterMap);
                if (datas.size() > 0) {
                    // 主资料已经存在
                    CustomerData d = datas.get(0);

                    // 保存录音数据
                    audio.setCustomerDataId(d.getCustomerDataId());
                    dataAudioService.addNewAudio(audio);// 入库

                    // 如果是实地营销任务的录音：往联系记录增加记录 同时更新实地营销任务完成情况
                    if (data.getTaskId() != null && data.getTaskId() > 0) {
                        recordInfoService.addRecordInfo(info);// 入库

                        Integer recordInfoId = info.getRecordInfoId();
                        if (recordInfoId != null && recordInfoId > 0) {
                            tskMicroTaskAutoFinishService
                                .autoFinishFoot(info, data.getTaskId());
                        }
                    }
                } else {
                    customerDataService.addNewCustomerData(data);

                    // 保存录音数据
                    audio.setCustomerDataId(data.getCustomerDataId());
                    dataAudioService.addNewAudio(audio);// 入库

                    // 如果是实地营销任务的录音: 往联系记录增加记录 同时更新实地营销任务完成情况
                    if (data.getTaskId() != null && data.getTaskId() > 0) {
                        recordInfoService.addRecordInfo(info);// 入库

                        Integer recordInfoId = info.getRecordInfoId();
                        if (recordInfoId != null && recordInfoId > 0) {
                            tskMicroTaskAutoFinishService
                                .autoFinishFoot(info, data.getTaskId());
                        }
                    }
                }
            } else {
                // 如果没有客户id
                // 往联系记录增加记录 同时更新实地营销任务完成情况
                if (data.getTaskId() != null && data.getTaskId() > 0) {
                    recordInfoService.addRecordInfo(info);// 入库

                    Integer recordInfoId = info.getRecordInfoId();
                    if (recordInfoId != null && recordInfoId > 0) {
                        tskMicroTaskAutoFinishService.autoFinishFoot(info, data.getTaskId());
                    }
                }
            }
            
            FileUtils.forceDelete(f);// 删除xml文件

            // 删除压缩文件和解密文件
            FileUtils.forceDelete(recordFile);
            //FileUtils.forceDelete(deFile);
            FileUtils.forceDelete(enFile);
        }
    }

    /**
     * 保存录音记录信息
     * 
     * @param f
     * @return
     * @throws Exception
     */
    private RecordInfo getRecord(File f) throws Exception {
        RecordInfo info = new RecordInfo();
        // 读取xml信息
        SAXBuilder builder = new SAXBuilder();
        Document document = builder.build(f);
        Element root = document.getRootElement();
        String account = root.getChildTextTrim("account");
        String callType = root.getChildTextTrim("callType");
        String localPhone = root.getChildTextTrim("localPhone");
        String remotePhone = root.getChildTextTrim("remotePhone");
        if (remotePhone != null && remotePhone.length() > 30) {
            remotePhone = remotePhone.substring(0, 30);
        }
        String startDate = root.getChildTextTrim("startDate");
        String endDate = root.getChildTextTrim("endDate");
        String customerName = root.getChildTextTrim("customerName");
        String bizType = root.getChildTextTrim("bizType");
        String idCard = root.getChildTextTrim("idCard");
        String creditCard = root.getChildTextTrim("creditCard");
        String remark = root.getChildTextTrim("remark");
        String fileName = root.getChildTextTrim("fileName");
        String isCanceled = root.getChildTextTrim("isCanceled");
        String ringTime = root.getChildTextTrim("ringTime");
        // String fileMD5 = root.getChildTextTrim("fileMD5");
        // 沟通进度
        String commProgressId = root.getChildTextTrim("commProgressId");
        String customerId = root.getChildTextTrim("customerId");
        // 录音来源
        String recordSource = root.getChildTextTrim("recordSource");

        String recordInfoId = root.getChildTextTrim("recordInfoId");

        // 取得用户id
        SysUser user = sysUserService.getUserByAccount(account);
        info.setUserId(0);
        info.setCreateUser(99999999);
        info.setUpdateUser(99999999);
        if (user != null) {
            info.setUserId(user.getUserId());
            info.setCreateUser(user.getUserId());
            info.setUpdateUser(user.getUserId());
        }
        if (callType != null && StringUtil.isNumber(callType)) {
            info.setCallType(Integer.valueOf(callType));
        }
        info.setLocalPhone(localPhone);
        if (remotePhone != null) {
            info.setRemotePhone(remotePhone.replace("#", "").replace("*", ""));
        }
        if (startDate != null) {
            info.setStartDate(DateUtil.strToDate(startDate, "yyyy-MM-dd HH:mm:ss"));
        }
        if (endDate != null) {
            info.setEndDate(DateUtil.strToDate(endDate, "yyyy-MM-dd HH:mm:ss"));
        }

        info.setIsCanceled(0);
        if (isCanceled != null && !isCanceled.equals("")) {
            info.setIsCanceled(Integer.valueOf(isCanceled));
        }
        info.setCustomerName(customerName);
        info.setBizType(0);
        if (bizType != null && bizType.length() > 0) {
            info.setBizType(Integer.valueOf(bizType));
        }
        info.setIdCard(idCard);
        info.setCreditCard(creditCard);
        info.setRemark(remark);
        info.setFileName(fileName);
        info.setCreateDate(Calendar.getInstance().getTime());
        info.setIsDel(0);
        info.setIsArchived(0);

        // 录音来源
        info.setRecordSource(TransportConstants.RECORD_SOURCE_PAD);
        if (recordSource != null) {
            try {
                info.setRecordSource(Integer.valueOf(recordSource));
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }

        // 录音流水号
        info.setRecordNo("P" + recordInfoService.genRecrodInfoNo());
        if (info.getRecordSource() != null
            && info.getRecordSource() == TransportConstants.RECORD_SOURCE_PHONE) {
            info.setRecordNo("L" + recordInfoService.genRecrodInfoNo());
        }

        // 话机端通话标识号，用作脱机等处理，暂时保存在recordNo这个字段
        // 后续可能需要增加字段
        if (recordInfoId != null && !"".equals(recordInfoId)) {
            try {
                info.setRecordNo(recordInfoId);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }

        // 时长
        info.setCallTime(0);
        try {
            if (info.getStartDate() != null && info.getEndDate() != null) {
                long start = info.getStartDate().getTime();
                long end = info.getEndDate().getTime();
                if (end >= start) {
                    Long callTime = Long.valueOf((end - start) / 1000);
                    info.setCallTime(callTime.intValue());
                }
            }
        } catch (Exception e) {
            logger.error("RecordInfoXmlParserJobImpl getRecord 时长计算失败: " + f.getName(), e);
        }

        // 沟通进度
        info.setCommProgressId(0);
        if (commProgressId != null && !commProgressId.equals("")) {
            info.setCommProgressId(Integer.valueOf(commProgressId));
        }
        info.setCustomerId(0);
        if (customerId != null && !customerId.equals("")) {
            try {
                info.setCustomerId(Integer.valueOf(customerId));
            } catch (Exception e) {
                logger.error("RecordInfoXmlParserJobImpl getRecord  error", e);
            }
        }
        info.setFilePath("");
        info.setFileSize(new Long(0));
        info.setFileMd5("");
        info.setUploadDate(Calendar.getInstance().getTime());
        info.setUpdateDate(Calendar.getInstance().getTime());
        info.setArchiveDate(Calendar.getInstance().getTime());

        if (ringTime != null && !ringTime.equalsIgnoreCase("")) {
            info.setRingTime(DateUtil.strToDate(ringTime, "yyyy-MM-dd HH:mm:ss"));
        }

        if (info.getCallType() != null
            && (info.getCallType() == TransportConstants.CALL_TYPE_NO_ANSWER || info.getCallType() == TransportConstants.CALL_TYPE_NO_READ_MESSAGE)) {
            info.setIsRead(0);
        } else {
            info.setIsRead(1);
        }

        // 新增字段
        info.setSplitCount(0);
        return info;
    }

    /**
     * 保存录音记录信息
     * 
     * @param f
     * @return
     * @throws Exception
     */
    private CustomerData getData(File f) throws Exception {
        CustomerData info = new CustomerData();
        // 读取xml信息
        SAXBuilder builder = new SAXBuilder();
        Document document = builder.build(f);
        Element root = document.getRootElement();
        String account = root.getChildTextTrim("account");
        String customerId = root.getChildTextTrim("customerId");
        String loanId = root.getChildTextTrim("loanId");
        String eventId = root.getChildTextTrim("eventId");
        if(eventId.equals("2")||eventId.equals("0")||eventId.equals("-1")) {eventId ="1";}//特殊处理
        String taskId = root.getChildTextTrim("taskId");
        String dunLogId = root.getChildTextTrim("dunLogId");
        String exceptionDunLogId = root.getChildTextTrim("exceptionDunLogId");
        String remark = root.getChildTextTrim("remark");

        if (StringUtils.isNotEmpty(remark)) {
            info.setRemark(remark);
        }
        
        if (StringUtils.isNotEmpty(exceptionDunLogId)) {
            try {
                info.setExceptionDunLogId(Integer.valueOf(exceptionDunLogId));
            } catch (Exception e) {
                logger.error("AudioDataXmlParserServiceImpl error:", e);
            }
        }

        if (StringUtils.isNotEmpty(dunLogId)) {
            try {
                info.setDunLogId(Integer.valueOf(dunLogId));
            } catch (Exception e) {
                logger.error("AudioDataXmlParserServiceImpl error:", e);
            }
        }

        if (StringUtils.isNotEmpty(taskId)) {
            try {
                info.setTaskId(Integer.valueOf(taskId));
            } catch (Exception e) {
                logger.error("AudioDataXmlParserServiceImpl error:", e);
            }
        }

        if (StringUtils.isNotEmpty(loanId)) {
            try {
                info.setLoanId(Integer.valueOf(loanId));
            } catch (Exception e) {
                logger.error("AudioDataXmlParserServiceImpl error:", e);
            }
        }
        if (StringUtils.isNotEmpty(customerId)) {
            try {
                info.setCustomerId(Integer.valueOf(customerId));
            } catch (Exception e) {
                logger.error("AudioDataXmlParserServiceImpl error:", e);
            }
        }
        if (StringUtils.isNotEmpty(eventId)) {
            try {
                info.setEventId(Integer.valueOf(eventId));
            } catch (Exception e) {
                logger.error("AudioDataXmlParserServiceImpl error:", e);
            }
        }else{
        	info.setEventId(1);
        }

        // 取得用户id
        SysUser user = sysUserService.getUserByAccount(account);
        if (user != null) {
            info.setUploadUserId(user.getUserId());
            info.setCreateUser(user.getUserId());
            info.setUpdateUser(user.getUserId());
        }

        info.setCreateDate(Calendar.getInstance().getTime());
        info.setUpdateDate(Calendar.getInstance().getTime());

        return info;
    }

    /**
     * 保存录音记录信息
     * 
     * @param f
     * @return
     * @throws Exception
     */
    private Audio getAudio(File f) throws Exception {
        Audio info = new Audio();
        // 读取xml信息
        SAXBuilder builder = new SAXBuilder();
        Document document = builder.build(f);
        Element root = document.getRootElement();
        String account = root.getChildTextTrim("account");
        String startDate = root.getChildTextTrim("startDate");
        String endDate = root.getChildTextTrim("endDate");
        String remark = root.getChildTextTrim("remark");
        String fileName = root.getChildTextTrim("fileName");
        String customerId = root.getChildTextTrim("customerId");
        String eventId = root.getChildTextTrim("eventId");
        String uuid = root.getChildTextTrim("uuid");

        if (StringUtils.isNotEmpty(uuid)) {
            info.setDatUuid(uuid);
        }

        String photoName = "";
        if (StringUtils.isNotEmpty(customerId)) {
            try {
                if (Integer.valueOf(customerId) > 0) {
                    BaseCrmCustomer cus = crmCustomerService.getCrmCustomerById(Integer
                        .valueOf(customerId));
                    if (cus != null) {
                        if (StringUtils.isNotEmpty(cus.getCustomerName())) {
                            photoName += cus.getCustomerName() + "_";
                        }
                    }
                }
            } catch (Exception e) {
                logger.error("AudioDataXmlParserServiceImpl error:", e);
            }
        }
        if (StringUtils.isNotEmpty(eventId)) {
            try {
                Event event = customerDataService.getEventTypeListById(Integer.valueOf(eventId));
                if (event != null) {
                    if (StringUtils.isNotEmpty(event.getEventName())) {
                        photoName += event.getEventName() + "_";
                    }
                }
            } catch (Exception e) {
                logger.error("AudioDataXmlParserServiceImpl error:", e);
            }
        }else{
        	 photoName += "申请_";
        }

        if (startDate != null) {
            info.setRecordDate(DateUtil.strToDate(startDate, "yyyy-MM-dd HH:mm:ss"));
        }
        if (info.getRecordDate() != null) {
            photoName += DateUtil.convertDateToString("yyyyMMdd(HHmmss)", info.getRecordDate());
        }
        info.setAudioName(photoName);

        // 时长
        try {
            if (StringUtils.isNotEmpty(startDate) && StringUtils.isNotEmpty(endDate)) {
                Date date1 = DateUtil.strToDate(startDate, "yyyy-MM-dd HH:mm:ss");
                Date date2 = DateUtil.strToDate(endDate, "yyyy-MM-dd HH:mm:ss");
                long start = date1.getTime();
                long end = date2.getTime();
                if (end >= start) {
                    Long callTime = Long.valueOf((end - start) / 1000);
                    info.setRecordLength(callTime.intValue());
                }
            }
        } catch (Exception e) {
            logger.error("时长计算失败: ", e);
        }

        // 取得用户id
        SysUser user = sysUserService.getUserByAccount(account);
        if (user != null) {
            info.setUploadUserId(user.getUserId());
            info.setCreateUser(user.getUserId());
            info.setUpdateUser(user.getUserId());
        }

        info.setRemark(remark);
        info.setFileName(fileName);
        info.setFilePath("");
        info.setUploadDate(Calendar.getInstance().getTime());
        info.setCreateDate(Calendar.getInstance().getTime());
        info.setUpdateDate(Calendar.getInstance().getTime());

        return info;
    }

    public void setSysUserService(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    public void setSysParamService(SysParamService sysParamService) {
        this.sysParamService = sysParamService;
    }

    public void setCrmCustomerService(CrmCustomerService crmCustomerService) {
        this.crmCustomerService = crmCustomerService;
    }

    public void setCustomerDataService(CustomerDataService customerDataService) {
        this.customerDataService = customerDataService;
    }

    public void setDataAudioService(DataAudioService dataAudioService) {
        this.dataAudioService = dataAudioService;
    }

    public void setRecordInfoService(RecordInfoService recordInfoService) {
        this.recordInfoService = recordInfoService;
    }

    public void setTskMicroTaskAutoFinishService(
                                                 TskMicroTaskAutoFinishService tskMicroTaskAutoFinishService) {
        this.tskMicroTaskAutoFinishService = tskMicroTaskAutoFinishService;
    }

    public void setLnDunLogService(LnDunLogService lnDunLogService) {
        this.lnDunLogService = lnDunLogService;
    }

    public void setSysUploadFileService(SysUploadFileService sysUploadFileService) {
        this.sysUploadFileService = sysUploadFileService;
    }

    public void setCaseHelperService(CaseHelperServiceImpl caseHelperService) {
        this.caseHelperService = caseHelperService;
    }

}
