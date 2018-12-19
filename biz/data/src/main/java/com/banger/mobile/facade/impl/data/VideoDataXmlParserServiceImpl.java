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
import com.banger.mobile.domain.model.data.CustomerData;
import com.banger.mobile.domain.model.data.Event;
import com.banger.mobile.domain.model.data.Video;
import com.banger.mobile.domain.model.loan.LnDunLog;
import com.banger.mobile.domain.model.loan.LnExceptionDunLog;
import com.banger.mobile.domain.model.user.SysUser;
import com.banger.mobile.facade.customer.CrmCustomerService;
import com.banger.mobile.facade.data.CaseHelperService;
import com.banger.mobile.facade.data.CustomerDataService;
import com.banger.mobile.facade.data.DataVideoService;
import com.banger.mobile.facade.data.VideoDataXmlParserService;
import com.banger.mobile.facade.loan.LnDunLogService;
import com.banger.mobile.facade.loan.LnExceptionDunLogService;
import com.banger.mobile.facade.param.SysParamService;
import com.banger.mobile.facade.uploadFile.SysUploadFileService;
import com.banger.mobile.facade.user.SysUserService;
import com.banger.mobile.transport.TransportUtil;
import com.banger.mobile.util.DateUtil;
import com.banger.mobile.util.FileUtil;
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
 * @version $Id: VideoDataXmlParserServiceImpl.java,v 0.1 2012-12-6 上午9:44:16
 *          Administrator Exp $
 */
public class VideoDataXmlParserServiceImpl implements VideoDataXmlParserService {
    private static final Logger      logger = Logger.getLogger(VideoDataXmlParserServiceImpl.class);

    private SysUserService           sysUserService;
    private SysParamService          sysParamService;
    private CrmCustomerService       crmCustomerService;
    private CustomerDataService      customerDataService;
    private DataVideoService         dataVideoService;
    private LnDunLogService          lnDunLogService;
    private LnExceptionDunLogService lnExceptionDunLogService;
    private SysUploadFileService     sysUploadFileService;
    private CaseHelperService        caseHelperService;

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
                Video video = getVideo(f);

                processPad(f, dir, data, video);

                logger.info("VideoDataXmlParserServiceImpl doJob info: " + f.getName());
            } catch (Exception e) {
                logger.error("VideoDataXmlParserServiceImpl doJob  error:", e);
            }
        }
    }

    /**
     * 处理pad
     * 
     * @param f
     * @param recordDir
     * @param video
     * @throws Exception
     */
    private void processPad(File f, String recordDir, CustomerData data, Video video)
                                                                                     throws Exception {
        // xml 文件名 无后缀
        String xmlFileName = f.getName();
        xmlFileName = xmlFileName.replace(TransportConstants.RECORD_INFO_EXTENSION + "."
                                          + TransportConstants.VALIDATED_FILE_OK, "");
        // 对应的录音文件
        //String recordFileName = xmlFileName + TransportConstants.COMPRESS_FILE_EXTENSION + "."
        //                        + TransportConstants.VALIDATED_FILE_OK;
        String recordFileName = xmlFileName + TransportConstants.VEDIO_FILE_EXTENSION + "."
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
            //                       + TransportConstants.VEDIO_FILE_EXTENSION);
            //EncryptionUtil.decryptFile(deFile, enFile);
            FileUtil.createDir(recordDir + "/temp");
            File enFile = new File(recordDir + "/temp/"
                                   + FilenameUtils.getBaseName(recordFile.getName()));
            FileUtils.copyFile(recordFile, enFile, true);

            // 移动到存储点
            String caseNo = caseHelperService.getCaseNo(data);
            int fileId = sysUploadFileService.saveFile(enFile, enFile.getName(), data
                .getUploadUserId(), false, caseNo,data);
            video.setFileId(fileId);

            /*
            // 如果是催收记录
            if (data.getEventId() != null && data.getEventId() == 6) {
                //判断是否是异常催收还是普通催收
                if (data.getDunLogId() != null && data.getDunLogId() > 0) {
                    // 普通
                    // 是否有催收记录
                    LnDunLog log = lnDunLogService.getDunLogById(data.getDunLogId());
                    if (log != null) {
                        // 有的话
                        // 保存照片数据
                    	if (log.getCustomerDataId() == null) {
                    		customerDataService.addNewCustomerData(data);
                    		log.setCustomerDataId(data.getCustomerDataId());
                    		Map<String, Object> paramMap = new HashMap<String, Object>();
                    		paramMap.put("exceptionDunLogId", log.getDunLogId());
                    		paramMap.put("customerDataId", data.getCustomerDataId());
                    		lnDunLogService.updateDunLogById(paramMap);
                    	}
                        video.setCustomerDataId(log.getCustomerDataId());
                        dataVideoService.addNewVideo(video);// 入库
                    } else {
                        // 么有，新增
                        customerDataService.addNewCustomerData(data);
                        // 保存照片数据
                        video.setCustomerDataId(data.getCustomerDataId());
                        dataVideoService.addNewVideo(video);// 入库
                        lnDunLogService.addLnDunLogFromPad(data);
                    }
                }
                if (data.getExceptionDunLogId() != null && data.getExceptionDunLogId() > 0) {
                    // 异常
                    // 是否有催收记录
                    LnExceptionDunLog log = lnExceptionDunLogService.getExceptionDunLogById(data
                        .getExceptionDunLogId());
                    if (log != null) {
                        // 有的话
                        // 保存照片数据
                    	if (log.getCustomerDataId() == null) {
                    		customerDataService.addNewCustomerData(data);
                    		log.setCustomerDataId(data.getCustomerDataId());
                    		Map<String, Object> paramMap = new HashMap<String, Object>();
                    		paramMap.put("exceptionDunLogId", log.getExceptionDunLogId());
                    		paramMap.put("customerDataId", data.getCustomerDataId());
                    		lnExceptionDunLogService.updateExceptionDunLogById(paramMap);
                    	}
                        video.setCustomerDataId(log.getCustomerDataId());
                        dataVideoService.addNewVideo(video);// 入库
                    } else {
                        // 么有，新增
                        customerDataService.addNewCustomerData(data);
                        // 保存照片数据
                        video.setCustomerDataId(data.getCustomerDataId());
                        dataVideoService.addNewVideo(video);// 入库
                        lnExceptionDunLogService.addLnExceptionDunLogFromPad(data);
                    }
                }
            } else {
                
            }*/

         // 将录音文件存放路径更新至db
            Map<String, Object> parameterMap = new HashMap<String, Object>();
            parameterMap.put("loanId", data.getLoanId());
            parameterMap.put("customerId", data.getCustomerId());
            parameterMap.put("eventId", data.getEventId());
            List<CustomerData> datas = customerDataService
                .getCustomerDataByParameterMap(parameterMap);
            if (datas.size() > 0) {
                // 主资料已经存在
                CustomerData d = datas.get(0);

                // 保存照片数据
                video.setCustomerDataId(d.getCustomerDataId());
                dataVideoService.addNewVideo(video);// 入库
            } else {
                customerDataService.addNewCustomerData(data);

                // 保存照片数据
                video.setCustomerDataId(data.getCustomerDataId());
                dataVideoService.addNewVideo(video);// 入库
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
        if(eventId.equals("2")||eventId.equals("0")||eventId.equals("-1")){eventId ="1";}
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
        if (StringUtils.isNotEmpty(loanId)) {
            try {
                info.setLoanId(Integer.valueOf(loanId));
            } catch (Exception e) {
                logger.error("VideoDataXmlParserServiceImpl error:", e);
            }
        }
        if (StringUtils.isNotEmpty(customerId)) {
            try {
                info.setCustomerId(Integer.valueOf(customerId));
            } catch (Exception e) {
                logger.error("VideoDataXmlParserServiceImpl error:", e);
            }
        }
        if (StringUtils.isNotEmpty(eventId)) {
            try {
                info.setEventId(Integer.valueOf(eventId));
            } catch (Exception e) {
                logger.error("VideoDataXmlParserServiceImpl error:", e);
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
    private Video getVideo(File f) throws Exception {
        Video info = new Video();
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
                BaseCrmCustomer cus = crmCustomerService.getCrmCustomerById(Integer
                    .valueOf(customerId));
                if (cus != null) {
                    if (StringUtils.isNotEmpty(cus.getCustomerName())) {
                        photoName += cus.getCustomerName() + "_";
                    }
                }
            } catch (Exception e) {
                logger.error("VideoDataXmlParserServiceImpl error:", e);
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
                logger.error("VideoDataXmlParserServiceImpl error:", e);
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
        info.setVideoName(photoName);

        // 时长
        try {
            if (StringUtils.isNotEmpty(startDate) && StringUtils.isNotEmpty(endDate)) {
                Date date1 = DateUtil.strToDate(startDate, "yyyy-MM-dd HH:mm:ss");
                Date date2 = DateUtil.strToDate(endDate, "yyyy-MM-dd HH:mm:ss");
                long start = date1.getTime();
                long end = date2.getTime();
                if (end > start) {
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

    public void setDataVideoService(DataVideoService dataVideoService) {
        this.dataVideoService = dataVideoService;
    }

    public void setLnDunLogService(LnDunLogService lnDunLogService) {
        this.lnDunLogService = lnDunLogService;
    }

    public void setSysUploadFileService(SysUploadFileService sysUploadFileService) {
        this.sysUploadFileService = sysUploadFileService;
    }

    public void setLnExceptionDunLogService(LnExceptionDunLogService lnExceptionDunLogService) {
        this.lnExceptionDunLogService = lnExceptionDunLogService;
    }

    public void setCaseHelperService(CaseHelperService caseHelperService) {
        this.caseHelperService = caseHelperService;
    }

}
