/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :录音处理类...
 * Author     :yuanme
 * Create Date:2012-3-31
 */
package com.banger.mobile.facade.impl.transport;

import com.banger.mobile.constants.CallTypeConstants;
import com.banger.mobile.constants.TransportConstants;
import com.banger.mobile.domain.model.base.customer.BaseCrmCustomer;
import com.banger.mobile.domain.model.customer.CrmCustomer;
import com.banger.mobile.domain.model.data.Audio;
import com.banger.mobile.domain.model.data.CustomerData;
import com.banger.mobile.domain.model.data.Event;
import com.banger.mobile.domain.model.loan.LnDunLog;
import com.banger.mobile.domain.model.loan.LnExceptionDunLog;
import com.banger.mobile.domain.model.phoneConfig.PhoneConfig;
import com.banger.mobile.domain.model.record.RecordInfo;
import com.banger.mobile.domain.model.user.SysUser;
import com.banger.mobile.facade.customer.CrmCustomerService;
import com.banger.mobile.facade.data.CaseHelperService;
import com.banger.mobile.facade.data.CustomerDataService;
import com.banger.mobile.facade.data.DataAudioService;
import com.banger.mobile.facade.loan.LnDunLogService;
import com.banger.mobile.facade.loan.LnExceptionDunLogService;
import com.banger.mobile.facade.microTask.TskMicroTaskAutoFinishService;
import com.banger.mobile.facade.microTask.TskScheduleService;
import com.banger.mobile.facade.param.SysParamService;
import com.banger.mobile.facade.phoneConfig.PhoneConfigService;
import com.banger.mobile.facade.record.RecordInfoService;
import com.banger.mobile.facade.sysWhiteList.SysWhiteListService;
import com.banger.mobile.facade.talk.TelephoneTalkService;
import com.banger.mobile.facade.uploadFile.SysUploadFileService;
import com.banger.mobile.facade.user.SysUserService;
import com.banger.mobile.transport.TransportUtil;
import com.banger.mobile.util.*;
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
 * @version RecordInfoXmlParserJob.java,v 0.1 2012-3-31 下午2:15:25
 */
public class RecordInfoXmlParserJobImpl {

    private static final Logger           logger = Logger
                                                     .getLogger(RecordInfoXmlParserJobImpl.class);

    private RecordInfoService             recordInfoService;
    private SysUserService                sysUserService;
    private SysParamService               sysParamService;
    private CrmCustomerService            crmCustomerService;
    private PhoneConfigService            phoneConfigService;
    private TelephoneTalkService          telephoneTalkService;
    private SysWhiteListService           sysWhiteListService;
    private TskMicroTaskAutoFinishService tskMicroTaskAutoFinishService;
    private LnDunLogService               lnDunLogService;
    private LnExceptionDunLogService      lnExceptionDunLogService;
    private SysUploadFileService          sysUploadFileService;
    private CaseHelperService             caseHelperService;
    private CustomerDataService           customerDataService;
    private DataAudioService              dataAudioService;
    private TskScheduleService tskScheduleService;

    public void setTskScheduleService(TskScheduleService tskScheduleService) {
        this.tskScheduleService = tskScheduleService;
    }

    public void setLnDunLogService(LnDunLogService lnDunLogService) {
        this.lnDunLogService = lnDunLogService;
    }

    public void setLnExceptionDunLogService(LnExceptionDunLogService lnExceptionDunLogService) {
        this.lnExceptionDunLogService = lnExceptionDunLogService;
    }

    public void setSysUploadFileService(SysUploadFileService sysUploadFileService) {
        this.sysUploadFileService = sysUploadFileService;
    }

    public void setCaseHelperService(CaseHelperService caseHelperService) {
        this.caseHelperService = caseHelperService;
    }

    public void setCustomerDataService(CustomerDataService customerDataService) {
        this.customerDataService = customerDataService;
    }

    public void setDataAudioService(DataAudioService dataAudioService) {
        this.dataAudioService = dataAudioService;
    }

    public void setTskMicroTaskAutoFinishService(
                                                 TskMicroTaskAutoFinishService tskMicroTaskAutoFinishService) {
        this.tskMicroTaskAutoFinishService = tskMicroTaskAutoFinishService;
    }

    public void setTelephoneTalkService(TelephoneTalkService telephoneTalkService) {
        this.telephoneTalkService = telephoneTalkService;
    }

    public void setPhoneConfigService(PhoneConfigService phoneConfigService) {
        this.phoneConfigService = phoneConfigService;
    }

    public void setCrmCustomerService(CrmCustomerService crmCustomerService) {
        this.crmCustomerService = crmCustomerService;
    }

    public void setRecordInfoService(RecordInfoService recordInfoService) {
        this.recordInfoService = recordInfoService;
    }

    public void setSysUserService(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    public void setSysParamService(SysParamService sysParamService) {
        this.sysParamService = sysParamService;
    }

    /**
     * 来源是话机
     * @param f
     * @param recordDir
     * @param info
     * @throws Exception
     */
    private void processLocalPhone(File f, String recordDir, RecordInfo info, CustomerData data, Audio audio) throws Exception {
        // xml 文件名 无后缀
        String xmlFileName = f.getName();
        xmlFileName = xmlFileName.replace(TransportConstants.RECORD_INFO_EXTENSION + "."
                                          + TransportConstants.VALIDATED_FILE_OK, "");
        // 对应的录音文件
        String recordFileName = xmlFileName + TransportConstants.RECORD_FILE_EXTENSION_LOCAL + "."
                                + TransportConstants.VALIDATED_FILE_OK;
        File recordFile = new File(recordDir + "/" + recordFileName);
        // 如果xml对应的录音文件存在，则做入库操作
        if (recordFile.exists()) {
            // 录音文件操作
            File wavFile = new File(recordDir + "/" + xmlFileName
                                    + TransportConstants.RECORD_FILE_EXTENSION_LOCAL);
            recordFile.renameTo(wavFile);

            // 移动到存储点
            String caseNo = caseHelperService.getCaseNo(data);
            int fileId = sysUploadFileService.saveFile(wavFile, wavFile.getName(), data
                .getUploadUserId(), false, caseNo,data);
            info.setFileId(fileId);
            audio.setFileId(fileId);
            
            //处理号码
            formatRemotePhone(info);
            
            DebugUtil.write("开始处理脱机和并机");
            info = processSameRecordInfo(info);
            DebugUtil.write("结束处理脱机和并机");

            DebugUtil.write("录音文件存在，处理联系记录上传");
            insertOrUpdateRecordInfo(info);

            DebugUtil.write("开始催收日志");
            if (info.getLoanId() != null && info.getLoanId() > 0) {
                data.setLoanId(info.getLoanId());
                data.setEventId(6);
                if (info.getExceptionDun() != null && info.getExceptionDun() == 0) {
                    // 普通催收
                    customerDataService.addNewCustomerData(data);
                    // 保存录音数据
                    audio.setCustomerDataId(data.getCustomerDataId());
                    audio.setRecordDate(null); //web端拨打的催收录音，不应有创建时间
                    dataAudioService.addNewAudio(audio);// 入库
                    lnDunLogService.addLnDunLogFromLocalPhone(data, info);
                } else {
                    // 异常催收
                    customerDataService.addNewCustomerData(data);
                    // 保存录音数据
                    audio.setCustomerDataId(data.getCustomerDataId());
                    audio.setRecordDate(null); //web端拨打的催收录音，不应有创建时间
                    dataAudioService.addNewAudio(audio);// 入库
                    lnExceptionDunLogService.addLnExceptionDunLogFromLocalPhone(data, info);
                }

            }
            DebugUtil.write("结束催收日志");

            FileUtils.forceDelete(f);// 删除xml文件
            FileUtils.forceDelete(wavFile);
        } else {
            // 如果录音文件不存在，则要去判断该录音信息文件的文件名为空
            if (info.getFileName() == null || "".equals(info.getFileName())) {
            	//处理号码
                formatRemotePhone(info);
                
                info = processSameRecordInfo(info);
                DebugUtil.write("录音文件不存在，处理联系记录上传");
                insertOrUpdateRecordInfo(info);
                FileUtils.forceDelete(f);
            }
        }
    }

    /**
     * 处理pad
     * @param f
     * @param recordDir
     * @param info
     * @throws Exception
     */
    private void processPad(File f, String recordDir, RecordInfo info, CustomerData data,
                            Audio audio) throws Exception {
        // xml 文件名 无后缀
        String xmlFileName = f.getName();
        xmlFileName = xmlFileName.replace(TransportConstants.RECORD_INFO_EXTENSION + "."
                                          + TransportConstants.VALIDATED_FILE_OK, "");
        // 对应的录音文件
        //String recordFileName = xmlFileName + TransportConstants.COMPRESS_FILE_EXTENSION + "."
        //                        + TransportConstants.VALIDATED_FILE_OK;
        String recordFileName = xmlFileName + TransportConstants.RECORD_FILE_EXTENSION + "."
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
            //                       + TransportConstants.RECORD_FILE_EXTENSION);
            //EncryptionUtil.decryptFile(deFile, enFile);
            FileUtil.createDir(recordDir + "/temp");
            File enFile = new File(recordDir + "/temp/"
                                   + FilenameUtils.getBaseName(recordFile.getName()));
            FileUtils.copyFile(recordFile, enFile, true);

            // 移动到存储点
            String caseNo = caseHelperService.getCaseNo(data);
            int fileId = sysUploadFileService.saveFile(enFile, enFile.getName(), data
                .getUploadUserId(), false, caseNo,data);
            audio.setFileId(fileId);
            info.setFileId(fileId);

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
                        audio.setCustomerDataId(log.getCustomerDataId());
                        dataAudioService.addNewAudio(audio);// 入库
                    } else {
                        // 么有，新增
                        customerDataService.addNewCustomerData(data);
                        // 保存录音数据
                        audio.setCustomerDataId(data.getCustomerDataId());
                        dataAudioService.addNewAudio(audio);// 入库
                        lnDunLogService.addLnDunLogFromPad(data, true);
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
                        audio.setCustomerDataId(log.getCustomerDataId());
                        dataAudioService.addNewAudio(audio);// 入库
                    } else {
                        // 么有，新增
                        customerDataService.addNewCustomerData(data);
                        // 保存录音数据
                        audio.setCustomerDataId(data.getCustomerDataId());
                        dataAudioService.addNewAudio(audio);// 入库
                        lnExceptionDunLogService.addLnExceptionDunLogFromPad(data, true);
                    }
                }
            }
            
            //白名单
            info = filterWhiteList(info);

            recordInfoService.addRecordInfo(info);//入库

            //同步之前未知客户且有号码的记录
            if (info.getCustomerId() != null && info.getCustomerId() > 0) {
                BaseCrmCustomer baseCrmCustomer = crmCustomerService.getCrmCustomerById(info.getCustomerId());
                PhoneConfig phoneConfig = phoneConfigService.query(info.getUserId());

                Map<String, Object> map = new HashMap<String, Object>();
                map.put("customerId",info.getCustomerId());
                map.put("customerName",baseCrmCustomer.getCustomerName());
                if(StringUtil.isNotEmpty(baseCrmCustomer.getMobilePhone1()))
                    map.put("phone1", baseCrmCustomer.getMobilePhone1());
                if(StringUtil.isNotEmpty(baseCrmCustomer.getMobilePhone2()))
                    map.put("phone2", baseCrmCustomer.getMobilePhone2());
                if(StringUtil.isNotEmpty(baseCrmCustomer.getPhone()))
                    map.put("phone3", baseCrmCustomer.getPhone());
                if (phoneConfig!=null){//空判断
                    map.put("temphone", phoneConfig.getCityCode()+""+baseCrmCustomer.getPhone());
                    map.put("temphone1", recordInfoService.splitPhoneByConfig(baseCrmCustomer.getPhone(), phoneConfig));
                }
                if(StringUtil.isNotEmpty(baseCrmCustomer.getFax()))
                    map.put("phone4", baseCrmCustomer.getFax());
                recordInfoService.updateRecordByPhones(map);
            }

            //更新客户最近联系时间和类型 
            String lastContactType = StringUtil.getNotNullValue(CallTypeConstants.callTypeMap
                .get(info.getCallType() + ""));
            crmCustomerService.updateLastContactDate(info.getCustomerId(), info.getStartDate(),
                lastContactType);

            DebugUtil.write("开始自动完成任务");
            if (data.getTaskId() != null && data.getTaskId() > 0) {
                //任务产生的录音（实地录音和通话录音） 客户资料-营销事件
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
                } else {
                    customerDataService.addNewCustomerData(data);
                    // 保存录音数据
                    audio.setCustomerDataId(data.getCustomerDataId());
                    dataAudioService.addNewAudio(audio);// 入库
                }

                // 自动完成任务
                tskMicroTaskAutoFinishService.autoFinish(info, data);
            }
            DebugUtil.write("结束自动完成任务");

            // 删除压缩文件和解密文件
            FileUtils.forceDelete(f);// 删除xml文件
            FileUtils.forceDelete(recordFile);
            FileUtils.forceDelete(enFile);
        } else {
            // 如果录音文件不存在，则要去判断该录音信息文件是否是拜访记录
            // 获取xml信息
            if (info.getCallType() != null && info.getCallType() == 5) {
                //白名单
                info = filterWhiteList(info);

                recordInfoService.addRecordInfo(info);//入库
                FileUtils.forceDelete(f);
            }
        }
    }

    /**
     * 新增或更新
     * @param info
     */
    private void insertOrUpdateRecordInfo(RecordInfo info) {
        //话机端对方电话需要处理下
        if (info == null) {
            return;
        }

        DebugUtil.write("开始处理通话号码");
        if (info.getRemotePhone() != null && info.getUserId() != null) {

            PhoneConfig config = phoneConfigService.query(info.getUserId());
            if (config != null) {
                // 根据来电和去电调用不同的方法
                if (info.getCallType() != null) {
                    if (info.getCallType() == TransportConstants.CALL_TYPE_IN
                        || info.getCallType() == TransportConstants.CALL_TYPE_NO_ANSWER
                        || info.getCallType() == TransportConstants.CALL_TYPE_NO_READ_MESSAGE) {
                        String formatedNumber = recordInfoService.formatIncomingNumber(info
                            .getRemotePhone(), config);
                        info.setRemotePhone(formatedNumber);
                    }
                    if (info.getCallType() == TransportConstants.CALL_TYPE_OUT) {
                        String formatedNumber = recordInfoService.formatOutNumber(info
                            .getRemotePhone(), config);
                        info.setRemotePhone(formatedNumber);
                    }
                }
            }
        }
        DebugUtil.write("结束处理通话号码");

        // 话机端通话标识号
        // 如果id已经存在，则更新，如果不存在，则新增
        // 标识号暂时保存在recordNo字段
        if (info.getRecordNo() != null) {
            DebugUtil.write("开始查找通话记录");
            List<RecordInfo> oldList = recordInfoService.queryAllByNo(info.getRecordNo());
            DebugUtil.write("结束查找通话记录");

            if (oldList.size() > 0) {
                RecordInfo old = oldList.get(0);
                if (old.getBizType() != null && old.getBizType() > 0) {
                    info.setBizType(old.getBizType());
                }
                if (old.getCustomerId() != null && old.getCustomerId() > 0) {
                    info.setCustomerId(old.getCustomerId());
                } else {
                    // 如果客户id不存在，根据对方号码去判断客户id
                    // 根据电话号码得到客户id,如果是一个，就赋值，多的不处理
                    DebugUtil.write("开始匹配客户");
                    if (info.getRemotePhone() != null && info.getRemotePhone().length() > 0) {
                        List<CrmCustomer> list = telephoneTalkService.getCrmCustomerByTel(info
                            .getRemotePhone());
                        if (list != null && list.size() == 1) {
                            info.setCustomerId(list.get(0).getCustomerId());
                        }
                    }
                    DebugUtil.write("结束匹配客户");
                }

                //2012-09-12 如果有用户id存在，则不覆盖
                if (old.getUserId() != null && old.getUserId() > 0) {
                    info.setUserId(old.getUserId());
                }

                info.setCustomerName("");
                if (old.getCustomerName() != null && old.getCustomerName().length() > 0) {
                    info.setCustomerName(old.getCustomerName());
                }

                info.setRemark("");
                if (old.getRemark() != null && old.getRemark().length() > 0) {
                    info.setRemark(old.getRemark());
                }

                info.setCommProgressId(0);
                if (old.getCommProgressId() != null && old.getCommProgressId() > 0) {
                    info.setCommProgressId(old.getCommProgressId());
                }

                DebugUtil.write("开始处理通话白名单");
                // 通话白名单处理
                info = filterWhiteList(info);
                DebugUtil.write("结束处理通话白名单");

                info.setRecordInfoId(Integer.valueOf(old.getRecordInfoId()));

                //判断如果记录有电话号码 则不覆盖
                if (StringUtil.isNotEmpty(oldList.get(0).getRemotePhone())) {
                    if (StringUtil.isNullOrEmpty(info.getRemotePhone())) {
                        info.setRemotePhone(oldList.get(0).getRemotePhone());
                    }
                }

                DebugUtil.write("开始更新联系记录");
                recordInfoService.updateRecordInfo(info); // update
                DebugUtil.write("结束更新联系记录");

                DebugUtil.write("开始自动完成任务");
                if (info.getTaskId() != null && info.getTaskId() > 0) {
                    //任务产生的录音（实地录音和通话录音） 客户资料-营销事件
                    Audio audio = new Audio();
                    String photoName = "";
                    try {
                        BaseCrmCustomer cus = crmCustomerService
                                .getCrmCustomerById(Integer.valueOf(info.getCustomerId()));
                        if (cus != null) {
                            if (StringUtils.isNotEmpty(cus.getCustomerName())) {
                                photoName += cus.getCustomerName() + "_";
                            }
                        }
                    } catch (Exception e) {
                        logger.error("error:", e);
                    }
                    try {
                        Event event = customerDataService.getEventTypeListById(1);
                        if (event != null) {
                            if (StringUtils.isNotEmpty(event.getEventName())) {
                                photoName += event.getEventName() + "_";
                            }
                        }
                    } catch (Exception e) {
                        logger.error("error:", e);
                    }
                    //audio.setRecordDate(info.getStartDate());   //web端上传的资料都不需要创建时间
                    if (info.getStartDate() != null) {
                        photoName += DateUtil.convertDateToString("yyyyMMdd(HHmmss)",
                                info.getStartDate());
                    }
                    audio.setAudioName(photoName);
                    // 时长
                    audio.setRecordLength(info.getCallTime());
                    // 取得用户id
                    audio.setCreateUser(info.getUserId());
                    audio.setUpdateUser(info.getUserId());
                    audio.setRemark(info.getRemark());
                    audio.setFileId(info.getFileId());
                    audio.setCreateDate(info.getCreateDate());
                    audio.setUpdateDate(info.getCreateDate());
                    UUID uuid = UUID.randomUUID();
                    audio.setDatUuid(uuid.toString());
                    audio.setRecordNo(info.getRecordNo());
                    Map<String, Object> parameterMap = new HashMap<String, Object>();
                    parameterMap.put("loanId", 0);
                    if (info.getCustomerId() == null || info.getCustomerId() <= 0) {
                        parameterMap.put("customerId", 0 - info.getRecordInfoId());
                    } else {
                        parameterMap.put("customerId", info.getCustomerId());
                    }
                    parameterMap.put("eventId", 1);
                    List<CustomerData> datas = customerDataService
                            .getCustomerDataByParameterMap(parameterMap);
                    if (datas.size() > 0) {
                        // 主资料已经存在
                        CustomerData d = datas.get(0);
                        // 保存录音数据
                        audio.setCustomerDataId(d.getCustomerDataId());
                        dataAudioService.addNewAudio(audio);// 入库
                    } else {
                        CustomerData data = new CustomerData();
                        if (info.getCustomerId() == null || info.getCustomerId() <= 0) {
                            data.setCustomerId(0 - info.getRecordInfoId());
                        } else {
                            data.setCustomerId(info.getCustomerId());
                        }
                        data.setLoanId(0);
                        data.setEventId(1);
                        data.setCreateUser(info.getUserId());
                        data.setCreateDate(Calendar.getInstance().getTime());
                        customerDataService.addNewCustomerData(data);
                        // 保存录音数据
                        audio.setCustomerDataId(data.getCustomerDataId());
                        dataAudioService.addNewAudio(audio);// 入库
                    }

                    // 自动完成任务
                    tskMicroTaskAutoFinishService.autoFinish(info, null);
                }
                DebugUtil.write("结束自动完成任务");
            } else {
                // 如果客户id不存在，根据对方号码去判断客户id
                // 根据电话号码得到客户id,如果是一个，就赋值，多的不处理
                DebugUtil.write("开始匹配客户");
                if (info.getRemotePhone() != null && info.getRemotePhone().length() > 0) {
                    List<CrmCustomer> list = telephoneTalkService.getCrmCustomerByTel(info
                        .getRemotePhone());
                    if (list != null && list.size() == 1) {
                        info.setCustomerId(list.get(0).getCustomerId());
                        info.setCustomerName(list.get(0).getCustomerName());
                    }
                }
                DebugUtil.write("结束匹配客户");

                DebugUtil.write("开始处理通话白名单");
                // 通话白名单处理
                info = filterWhiteList(info);
                DebugUtil.write("结束处理通话白名单");

                DebugUtil.write("开始插入联系记录");
                recordInfoService.addRecordInfo(info); // insert
                DebugUtil.write("开始插入联系记录");

                DebugUtil.write("开始自动完成任务");
                tskMicroTaskAutoFinishService.autoFinish(info, null);
                DebugUtil.write("结束自动完成任务");
            }
            //更新客户最近联系时间和类型 
            String lastContactType = StringUtil.getNotNullValue(CallTypeConstants.callTypeMap
                .get(info.getCallType() + ""));
            crmCustomerService.updateLastContactDate(info.getCustomerId(), info.getStartDate(),
                lastContactType);
        }
    }
    
    /**
     * 从xml文件中提取录音信息
     */
    public synchronized Boolean doJob(String fileName) {
        Boolean result = false;
        String dir = TransportUtil.getRecordPath(sysParamService)
                     + TransportConstants.UPLOAD_TEMP_DIR;
        FileUtil.createDir(dir);
        File f = new File(dir + File.separator + fileName + "."
                          + TransportConstants.VALIDATED_FILE_XML_OK);
        if (f.exists()) {
            try {
                // 解析xml文件得到data主表信息
                CustomerData data = getData(f);

                // 解析xml文件得到audio子表信息
                Audio audio = getAudio(f);

                // 解析xml文件
                DebugUtil.write("开始解析xml");
                RecordInfo info = getRecord(f);
                DebugUtil.write("结束解析xml");
                // 先判断录音来源
                if (info != null && info.getRecordSource() != null
                    && info.getRecordSource() == TransportConstants.RECORD_SOURCE_PHONE) {
                    // 如果是固话
                    processLocalPhone(f, dir, info, data, audio);
                } else if (info != null && info.getRecordSource() != null
                           && info.getRecordSource() == TransportConstants.RECORD_SOURCE_PAD) {
                    // 如果是PAD
                    processPad(f, dir, info, data, audio);
                }
                logger.info("RecordInfoXmlParserJobImpl doJob info: " + f.getName());
                result = true;
            } catch (Exception e) {
                result = false;
                logger.error("RecordInfoXmlParserJobImpl doJob  error:", e);
            }
        }
        return result;
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

        if(StringUtil.isNotEmpty(root.getChildTextTrim("taskId"))){
            //web端电话催收设置事件为6
            if (root.getChildTextTrim("taskId").startsWith("000")) {
                eventId = "6";
            }
        }

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
        if(eventId.equals("2")){eventId="1";}
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
                Integer tempLoanId = Integer.valueOf(loanId);
                if (tempLoanId < 0)
                    tempLoanId = 0;
                info.setLoanId(Integer.valueOf(tempLoanId));
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
     * @param f
     * @return
     * @throws Exception
     */
    private RecordInfo getRecord(File f) throws Exception {
        RecordInfo info = new RecordInfo();
        //读取xml信息
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
        
        //当完成任务的营销任务时要选取taskId
        if(StringUtil.isNotEmpty(root.getChildTextTrim("taskId"))){
            if (root.getChildTextTrim("taskId").startsWith("0000")) {
                //表示是异常催收的电话
                info.setLoanId(Integer.parseInt(root.getChildTextTrim("taskId")));
                info.setExceptionDun(1);
            } else if (root.getChildTextTrim("taskId").startsWith("000")) {
                //表示是催收的电话
                info.setLoanId(Integer.parseInt(root.getChildTextTrim("taskId")));
                info.setExceptionDun(0);
            } else {
                info.setTaskId(Integer.parseInt(root.getChildTextTrim("taskId")));
            }
        }
        //取得用户id
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
        if (StringUtils.isNotEmpty(startDate)) {
            info.setStartDate(DateUtil.strToDate(startDate, "yyyy-MM-dd HH:mm:ss"));
        }
        if (StringUtils.isNotEmpty(endDate)) {
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

        //录音流水号
        info.setRecordNo("P" + recordInfoService.genRecrodInfoNo());
        if (info.getRecordSource() != null
            && info.getRecordSource() == TransportConstants.RECORD_SOURCE_PHONE) {
            if (info.getCallType() != null) {
                if (info.getCallType() == TransportConstants.CALL_TYPE_IN
                    || info.getCallType() == TransportConstants.CALL_TYPE_OUT
                    || info.getCallType() == TransportConstants.CALL_TYPE_NO_ANSWER
                    || info.getCallType() == TransportConstants.CALL_TYPE_NO_READ_MESSAGE) {//通话记录
                    if (info.getRemotePhone() != null && !info.getRemotePhone().equals("")) {
                        info.setRecordNo(DateUtil.getNowTimeToString() + info.getRemotePhone());
                    } else {
                        int ran = (int) (Math.random() * 1000);
                        info.setRecordNo(DateUtil.getNowTimeToString() + ran);
                    }
                } else if (info.getCallType() == TransportConstants.CALL_TYPE_LIVING
                           || info.getCallType() == TransportConstants.CALL_TYPE_VISIT) {//座谈记录、拜访记录 guid
                    info.setRecordNo(recordInfoService.genRecrodInfoNo());
                } else
                    info.setRecordNo(null);
            }
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

        //时长
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
            //add by zhangxiang at 2012-08-03 增加日志打印信息
            logger.error("RecordInfoXmlParserJobImpl getRecord 时长计算失败: " + f.getName() + ":"
                         + e.getMessage());

        }

        //沟通进度
        info.setCommProgressId(0);
        if (commProgressId != null && !commProgressId.equals("")) {
            info.setCommProgressId(Integer.valueOf(commProgressId));
        }
        info.setCustomerId(0);
        if (customerId != null && !customerId.equals("")) {
            try {
                info.setCustomerId(Integer.valueOf(customerId));
            } catch (Exception e) {
                //add by zhangxiang at 2012-08-03 增加日志打印信息
                logger.error("RecordInfoXmlParserJobImpl getRecord  error:" + e.getMessage());
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

        //新增字段
        info.setSplitCount(0);
        return info;
    }

    /**
     * 处理相同的记录，包括并机和脱机
     * 脱机记录则去匹配数据库中有无相同的记录，有则更新，无则新增
     * @param recordInfo
     * @return RecordInfo
     */
    private RecordInfo processSameRecordInfo(RecordInfo recordInfo) {
        if (recordInfo.getRingTime() == null) {
            //处理脱机
            return processOffLineRecord(recordInfo);
        }
        int callType = recordInfo.getCallType();
        switch (callType) {
            case TransportConstants.CALL_TYPE_IN:
            case TransportConstants.CALL_TYPE_NO_ANSWER:
            case TransportConstants.CALL_TYPE_NO_READ_MESSAGE:
                //处理并机
                return processParallelRecord(recordInfo);
            default:
                return recordInfo;
        }
    }

    /**
     * 处理脱机上传的记录，如果数据库中不存在这条记录，则返回该条，
     * 若存在，则取出存在的那条，将上传的记录的信息赋值给原来的，再去更新
     * 判断条件是同一号码+通话开始时间相差5秒+同一通话类型+相同userId
     * @param recordInfo
     * @return RecordInfo
     */
    private RecordInfo processOffLineRecord(RecordInfo recordInfo) {
        List<RecordInfo> recordInfoList = recordInfoService
            .getSameRecodInfoList(getMapForSameOffLineRecord(recordInfo));
        //如果存在，则将原来记录路的一些信息取出来，赋值给新上传的记录返回
        if (recordInfoList.size() > 0) {
            RecordInfo oldRecordInfo = recordInfoList.get(0);
            recordInfo.setRecordInfoId(oldRecordInfo.getRecordInfoId());
            recordInfo.setBizType(oldRecordInfo.getBizType());
            recordInfo.setCustomerId((oldRecordInfo.getCustomerId() == null) ? 0 : oldRecordInfo
                .getCustomerId());
            recordInfo.setCustomerName((oldRecordInfo.getCustomerName() == null) ? ""
                : oldRecordInfo.getCustomerName());
            recordInfo.setCommProgressId((oldRecordInfo.getCommProgressId() == null) ? 0
                : oldRecordInfo.getCommProgressId());
            recordInfo.setContent(oldRecordInfo.getContent());
            recordInfo
                .setRemark(oldRecordInfo.getRemark() == null ? "" : oldRecordInfo.getRemark());
            recordInfo.setRecordNo(oldRecordInfo.getRecordNo());
            recordInfo.setBizType((oldRecordInfo.getBizType() == null) ? 0 : oldRecordInfo
                .getBizType());
            recordInfo.setCreditCard(oldRecordInfo.getCreditCard() == null ? "" : oldRecordInfo
                .getCreditCard());
            recordInfo.setRingTime(oldRecordInfo.getRingTime());
            DebugUtil.write("脱机记录存在");
            DebugUtil.write(SerializeUtil.toXML(recordInfo));
            return recordInfo;
        } else {
            DebugUtil.write("脱机记录不存在");
            DebugUtil.write(SerializeUtil.toXML(recordInfo));
            return recordInfo;
        }
    }

    /**
     * 并机记录查询条件
     * @param recordInfo
     * @return
     */
    private Map<String, Object> getMapForParallelRecord(RecordInfo recordInfo) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("number", recordInfo.getRemotePhone());
        Date ringTime1 = new TimeWrapper(recordInfo.getRingTime().getTime() - 5000);
        Date ringTime2 = new TimeWrapper(recordInfo.getRingTime().getTime() + 5000);
        map.put("ringTime1", ringTime1);
        map.put("ringTime2", ringTime2);
        //排除自己这条记录
        map.put("selfRecordNo", recordInfo.getRecordNo());
        return map;
    }

    /**
     * 脱机记录查询条件
     * @param recordInfo
     * @return
     */
    private Map<String, Object> getMapForSameOffLineRecord(RecordInfo recordInfo) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("number", recordInfo.getRemotePhone());
        Date startdate1 = new TimeWrapper(recordInfo.getStartDate().getTime() - 5000);
        Date startdate2 = new TimeWrapper(recordInfo.getStartDate().getTime() + 5000);
        map.put("startDate1", startdate1);
        map.put("startDate2", startdate2);
        map.put("userId", recordInfo.getUserId());
        map.put("callType", recordInfo.getCallType());
        return map;
    }

    /**
     * 处理并机，主要功能为两台电话联机时，一个已接，一个未接，则忽略未接来电
     * @param recordInfo
     * @return RecordInfo
     */
    private RecordInfo processParallelRecord(RecordInfo recordInfo) {
        int callType = recordInfo.getCallType();
        List<RecordInfo> recordInfoList = recordInfoService
            .getSameRecodInfoList(getMapForParallelRecord(recordInfo));
        if (recordInfoList.size() > 0) {
            DebugUtil.write("有并机纪录");
            //有并机纪录
            RecordInfo oldRecordInfo = recordInfoList.get(0);
            if (oldRecordInfo.getRingTime() == null) {
                //老记录脱机，则新记录直接插入
                return recordInfo;
            }
            if (callType == TransportConstants.CALL_TYPE_NO_ANSWER) {
                //新纪录为未接
                if (oldRecordInfo.getCallType() == TransportConstants.CALL_TYPE_IN) {
                    //新纪录为未接，老记录为已接，则新记录直接丢掉，否则新记录插入
                    DebugUtil.write("新纪录为未接，老记录为已接，则新记录直接丢掉，否则新记录插入 return null");
                    return null;
                } else {
                    //新记录未接，老记录不是已接，则新记录保留
                    return recordInfo;
                }
            } else {
                if (oldRecordInfo.getCallType() == TransportConstants.CALL_TYPE_NO_ANSWER
                    && callType != TransportConstants.CALL_TYPE_NO_READ_MESSAGE) {
                    //新记录不是未接,而存在另一条未接记录
                    //一般不可能出现联机时，未接来电先上传
                    recordInfo.setRecordInfoId(oldRecordInfo.getRecordInfoId());
                    recordInfo.setRecordNo(oldRecordInfo.getRecordNo());
                    oldRecordInfo = recordInfo;
                    DebugUtil.write(SerializeUtil.toXML(oldRecordInfo));
                    return oldRecordInfo;
                } else {
                    //新记录不是未接,老记录也不是未接，则新记录保留
                    return recordInfo;
                }
            }
        } else {
            //没有并机记录
            DebugUtil.write("没有并机记录");
            return recordInfo;
        }
    }

    /**
     * 过滤白名单
     * @param info
     * @return
     */
    private RecordInfo filterWhiteList(RecordInfo info) {
        if (!StringUtil.isEmpty(info.getRemotePhone())) {
            boolean isWhite = sysWhiteListService.queryByPhoneNo(info.getRemotePhone());
            if (isWhite) {
                info.setFileId(null);
            }
        }
        return info;
    }

    public void setSysWhiteListService(SysWhiteListService sysWhiteListService) {
        this.sysWhiteListService = sysWhiteListService;
    }
    
    /**
     * 处理电话号码
     */
    private void formatRemotePhone(RecordInfo info){
    	DebugUtil.write("开始处理通话号码recordNo="+info.getRecordNo());
        if (info.getRemotePhone() != null && info.getUserId() != null) {
        	
            PhoneConfig config = phoneConfigService.query(info.getUserId());
            if (config != null) {
                // 根据来电和去电调用不同的方法
                if (info.getCallType() != null) {
                    if (info.getCallType() == TransportConstants.CALL_TYPE_IN
                        || info.getCallType() == TransportConstants.CALL_TYPE_NO_ANSWER
                        || info.getCallType() == TransportConstants.CALL_TYPE_NO_READ_MESSAGE) {
                        String formatedNumber = recordInfoService.formatIncomingNumber(
                            info.getRemotePhone(), config);
                        info.setRemotePhone(formatedNumber);
                    }
                    if (info.getCallType() == TransportConstants.CALL_TYPE_OUT) {
                        String formatedNumber = recordInfoService.formatOutNumber(
                            info.getRemotePhone(), config);
                        info.setRemotePhone(formatedNumber);
                    }
                }
            }
        }
        DebugUtil.write("结束处理通话号码recordNo="+info.getRecordNo());
    }
}
