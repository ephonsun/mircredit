/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yuanme
 * Create Date:2012-12-6
 */
package com.banger.mobile.facade.impl.points;

import com.banger.mobile.constants.TransportConstants;
import com.banger.mobile.domain.model.base.customer.BaseCrmCustomer;
import com.banger.mobile.domain.model.data.CustomerData;
import com.banger.mobile.domain.model.data.Event;
import com.banger.mobile.domain.model.data.Photo;
import com.banger.mobile.domain.model.points.JfOrderPhoto;
import com.banger.mobile.domain.model.user.SysUser;
import com.banger.mobile.facade.customer.CrmCustomerService;
import com.banger.mobile.facade.data.CaseHelperService;
import com.banger.mobile.facade.data.CustomerDataService;
import com.banger.mobile.facade.data.DataPhotoService;
import com.banger.mobile.facade.param.SysParamService;
import com.banger.mobile.facade.points.JfDataXmlParserService;
import com.banger.mobile.facade.points.JfOrderPhotoService;
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
import java.util.Calendar;

/**
 * @author yuanme
 * @version $Id: JfDataXmlParserServiceImpl.java,v 0.1 2012-12-6 上午9:44:16
 *          Administrator Exp $
 */
public class JfDataXmlParserServiceImpl implements JfDataXmlParserService {
    private static final Logger      logger = Logger.getLogger(JfDataXmlParserServiceImpl.class);

    private SysUserService           sysUserService;
    private SysParamService          sysParamService;
    private CrmCustomerService       crmCustomerService;
    private CustomerDataService      customerDataService;
    private DataPhotoService         dataPhotoService;
    private SysUploadFileService     sysUploadFileService;
    private CaseHelperService        caseHelperService;
    private JfOrderPhotoService      jfOrderPhotoService;

    /**
     * 从xml文件中提取照片信息
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
                Photo photo = getPhoto(f);

                processPad(f, dir, data, photo);

                logger.info("JfDataXmlParserServiceImpl doJob info: " + f.getName());
            } catch (Exception e) {
                logger.error("JfDataXmlParserServiceImpl doJob  error:", e);
            }
        }
    }

    /**
     * 处理pad
     * 
     * @param f
     * @param recordDir
     * @throws Exception
     */
    private void processPad(File f, String recordDir, CustomerData data, Photo photo)
                                                                                     throws Exception {
        // xml 文件名 无后缀
        String xmlFileName = f.getName();
        xmlFileName = xmlFileName.replace(TransportConstants.RECORD_INFO_EXTENSION + "."
                                          + TransportConstants.VALIDATED_FILE_OK, "");
        // 对应的照片文件
        //String recordFileName = xmlFileName + TransportConstants.COMPRESS_FILE_EXTENSION + "."
        //                        + TransportConstants.VALIDATED_FILE_OK;
        String recordFileName = xmlFileName + TransportConstants.PHOTO_FILE_EXTENSION + "."
                                + TransportConstants.VALIDATED_FILE_OK;
        File recordFile = new File(recordDir + "/" + recordFileName);
        // 如果xml对应的照片文件存在，则做入库操作
        if (recordFile.exists()) {
            // 照片文件操作
            // 解压
            //CompressUtil.decompress(recordFile);
            //String fname = recordFile.getName().substring(0, recordFile.getName().indexOf(".", 0));
            //File deFile = new File(recordDir + "/" + fname
            //                       + TransportConstants.ENTRYPTD_FILE_EXTENSION);

            // 解密
            //File enFile = new File(recordDir + "/" + fname
            //                       + TransportConstants.PHOTO_FILE_EXTENSION);
            //EncryptionUtil.decryptFile(deFile, enFile);
            FileUtil.createDir(recordDir + "/temp");
            File enFile = new File(recordDir + "/temp/"
                                   + FilenameUtils.getBaseName(recordFile.getName()));
            FileUtils.copyFile(recordFile, enFile, true);

            // 移动到存储点
            String caseNo = caseHelperService.getCaseNo(data);
            int fileId = sysUploadFileService.saveFile(enFile, enFile.getName(), data
                .getUploadUserId(), false, caseNo,data);
            photo.setFileId(fileId);

            // 将照片文件存放路径更新至db
            JfOrderPhoto orderPhoto = new JfOrderPhoto();
            orderPhoto.setFileId(photo.getFileId());
            orderPhoto.setOrderNo(photo.getOrderNo());
            orderPhoto.setTakeDate(photo.getRecordDate());
            orderPhoto.setUserId(photo.getCreateUser());
            jfOrderPhotoService.insertJfOrderPhoto(orderPhoto);

            FileUtils.forceDelete(f);// 删除xml文件

            // 删除压缩文件和解密文件
            FileUtils.forceDelete(recordFile);
            //FileUtils.forceDelete(deFile);
            FileUtils.forceDelete(enFile);
        }
    }

    /**
     * 保存图片记录信息
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
        String dunLogId = root.getChildTextTrim("dunLogId");
        String exceptionDunLogId = root.getChildTextTrim("exceptionDunLogId");

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
                logger.error("JfDataXmlParserServiceImpl error:", e);
            }
        }
        if (StringUtils.isNotEmpty(customerId)) {
            try {
                info.setCustomerId(Integer.valueOf(customerId));
            } catch (Exception e) {
                logger.error("JfDataXmlParserServiceImpl error:", e);
            }
        }
        if (StringUtils.isNotEmpty(eventId)) {
            try {
                info.setEventId(Integer.valueOf(eventId));
            } catch (Exception e) {
                logger.error("JfDataXmlParserServiceImpl error:", e);
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
     * 保存照片记录信息
     * 
     * @param f
     * @return
     * @throws Exception
     */
    private Photo getPhoto(File f) throws Exception {
        Photo info = new Photo();
        // 读取xml信息
        SAXBuilder builder = new SAXBuilder();
        Document document = builder.build(f);
        Element root = document.getRootElement();
        String account = root.getChildTextTrim("account");
        String startDate = root.getChildTextTrim("startDate");
        String remark = root.getChildTextTrim("remark");
        String fileName = root.getChildTextTrim("fileName");
        String customerId = root.getChildTextTrim("customerId");
        String eventId = root.getChildTextTrim("eventId");
        String typeId = root.getChildTextTrim("typeId");
        String uuid = root.getChildTextTrim("uuid");
        String rename = root.getChildTextTrim("rename"); // 用户自定义文件名
        String orderNo = root.getChildTextTrim("orderNo"); // 用户自定义文件名

        if (StringUtils.isNotEmpty(orderNo)) {
            info.setOrderNo(orderNo);
        }

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
                logger.error("JfDataXmlParserServiceImpl error:", e);
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
                logger.error("JfDataXmlParserServiceImpl error:", e);
            }
        }else{
        	photoName +="申请_";
        }
        if (StringUtils.isNotEmpty(typeId)) {
            info.setPhotoTypeId(Integer.valueOf(typeId));
            photoName += getPhoteType(typeId) + "_";
        }
        if (startDate != null) {
            info.setRecordDate(DateUtil.strToDate(startDate, "yyyy-MM-dd HH:mm:ss"));
        }
        if (info.getRecordDate() != null) {
            photoName += DateUtil.convertDateToString("yyyyMMdd(HHmmss)", info.getRecordDate());
        }
        // 处理有改名字的图片:pic_cusId_time_重命名.jpg这样的
        if (StringUtils.isNotEmpty(rename)) {
            photoName = rename;
        }
        info.setPhotoName(photoName);

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

    private String getPhoteType(String typeId) {
        // 1 家庭环境
        // 2 经营环境
        // 3 权属
        // 4 个人资产
        // 5 个人基本信息
        // 6 其他
        String result = "";
        if (typeId != null) {
            if (typeId.equals("1")) {
                result = "家庭环境";
            } else if (typeId.equals("2")) {
                result = "经营环境";
            } else if (typeId.equals("3")) {
                result = "权属";
            } else if (typeId.equals("4")) {
                result = "个人资产";
            } else if (typeId.equals("5")) {
                result = "个人基本信息";
            } else if (typeId.equals("6")) {
                result = "其他";
            }
        }
        return result;
    }

    public DataPhotoService getDataPhotoService() {
        return dataPhotoService;
    }

    public void setDataPhotoService(DataPhotoService dataPhotoService) {
        this.dataPhotoService = dataPhotoService;
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

    public void setSysUploadFileService(SysUploadFileService sysUploadFileService) {
        this.sysUploadFileService = sysUploadFileService;
    }

    public void setCaseHelperService(CaseHelperService caseHelperService) {
        this.caseHelperService = caseHelperService;
    }

    public void setJfOrderPhotoService(JfOrderPhotoService jfOrderPhotoService) {
        this.jfOrderPhotoService = jfOrderPhotoService;
    }
}
