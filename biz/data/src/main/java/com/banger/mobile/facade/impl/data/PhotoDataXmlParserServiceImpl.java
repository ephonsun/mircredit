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
import com.banger.mobile.domain.model.data.Photo;
import com.banger.mobile.domain.model.uploadFile.SysUploadFile;
import com.banger.mobile.domain.model.user.SysUser;
import com.banger.mobile.facade.customer.CrmCustomerService;
import com.banger.mobile.facade.data.CaseHelperService;
import com.banger.mobile.facade.data.CustomerDataService;
import com.banger.mobile.facade.data.DataPhotoService;
import com.banger.mobile.facade.data.PhotoDataXmlParserService;
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
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yuanme
 * @version $Id: PhotoDataXmlParserServiceImpl.java,v 0.1 2012-12-6 上午9:44:16
 *          Administrator Exp $
 */
public class PhotoDataXmlParserServiceImpl implements PhotoDataXmlParserService {
    private static final Logger      logger = Logger.getLogger(PhotoDataXmlParserServiceImpl.class);

    private SysUserService           sysUserService;
    private SysParamService          sysParamService;
    private CrmCustomerService       crmCustomerService;
    private CustomerDataService      customerDataService;
    private DataPhotoService         dataPhotoService;
    private LnDunLogService          lnDunLogService;
    private LnExceptionDunLogService lnExceptionDunLogService;
    private SysUploadFileService     sysUploadFileService;
    private CaseHelperService        caseHelperService;

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

                logger.info("PhotoDataXmlParserServiceImpl doJob info: " + f.getName());
            } catch (Exception e) {
                logger.error("PhotoDataXmlParserServiceImpl doJob  error:", e);
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
            int fileId = sysUploadFileService.saveFile(enFile, enFile.getName(), data .getUploadUserId(), false, caseNo,data);
            photo.setFileId(fileId);
            
            // 将照片文件存放路径更新至db
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
                photo.setCustomerDataId(d.getCustomerDataId());
                Photo photo1=dataPhotoService.getPhotoByUUID(photo);//是否已经有入库记录
                if (photo1!=null){//有则删除原有记录在新增
                    dataPhotoService.deletePhoto(photo1.getDatUuid());
                }
                dataPhotoService.addNewPhoto(photo);// 入库
            } else {
                data = customerDataService.addNewCustomerData(data);

                // 保存照片数据
                photo.setCustomerDataId(data.getCustomerDataId());
                Photo photo1=dataPhotoService.getPhotoByUUID(photo);//是否已经有入库记录
                if (photo1!=null){//有则删除原有记录在新增
                    dataPhotoService.deletePhoto(photo1.getDatUuid());
                }
                dataPhotoService.addNewPhoto(photo);// 入库
            }

            // 照片类型如果是99表示客户头像 TODO
            if (photo.getPhotoTypeId() != null && photo.getPhotoTypeId() == 99) {
                BaseCrmCustomer crm = crmCustomerService.getCrmCustomerById(data
                    .getCustomerId());
                if (crm != null) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("customerId", data.getCustomerId());
                    SysUploadFile upfile = sysUploadFileService.getSysUploadFileById(photo
                        .getFileId());
                    map.put("headshow", upfile.getFilePath() + "/" + upfile.getFileName());
                    crmCustomerService.updateCrmCustomerHeadShow(map);
                }
            }
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
                        photo.setCustomerDataId(log.getCustomerDataId());
                        Photo photo1=dataPhotoService.getPhotoByUUID(photo);//是否已经有入库记录
                        if (photo1!=null){//有则删除原有记录在新增
                          dataPhotoService.deletePhoto(photo1.getDatUuid());
                         }
                        dataPhotoService.addNewPhoto(photo);// 入库
                    } else {
                        // 么有，新增
                        customerDataService.addNewCustomerData(data);
                        // 保存照片数据
                        photo.setCustomerDataId(data.getCustomerDataId());
                        Photo photo1=dataPhotoService.getPhotoByUUID(photo);//是否已经有入库记录
                        if (photo1!=null){//有则删除原有记录在新增
                            dataPhotoService.deletePhoto(photo1.getDatUuid());
                        }
                        dataPhotoService.addNewPhoto(photo);// 入库
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
                        photo.setCustomerDataId(log.getCustomerDataId());
                        Photo photo1=dataPhotoService.getPhotoByUUID(photo);//是否已经有入库记录
                        if (photo1!=null){//有则删除原有记录在新增
                            dataPhotoService.deletePhoto(photo1.getDatUuid());
                        }
                        dataPhotoService.addNewPhoto(photo);// 入库
                    } else {
                        // 么有，新增
                        customerDataService.addNewCustomerData(data);
                        // 保存照片数据
                        photo.setCustomerDataId(data.getCustomerDataId());
                        Photo photo1=dataPhotoService.getPhotoByUUID(photo);//是否已经有入库记录
                        if (photo1!=null){//有则删除原有记录在新增
                            dataPhotoService.deletePhoto(photo1.getDatUuid());
                        }
                        dataPhotoService.addNewPhoto(photo);// 入库
                        lnExceptionDunLogService.addLnExceptionDunLogFromPad(data);
                    }
                }
            } else {
                // 将照片文件存放路径更新至db
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
                    photo.setCustomerDataId(d.getCustomerDataId());
                    Photo photo1=dataPhotoService.getPhotoByUUID(photo);//是否已经有入库记录
                    if (photo1!=null){//有则删除原有记录在新增
                        dataPhotoService.deletePhoto(photo1.getDatUuid());
                    }
                    dataPhotoService.addNewPhoto(photo);// 入库
                } else {
                    customerDataService.addNewCustomerData(data);

                    // 保存照片数据
                    photo.setCustomerDataId(data.getCustomerDataId());
                    Photo photo1=dataPhotoService.getPhotoByUUID(photo);//是否已经有入库记录
                    if (photo1!=null){//有则删除原有记录在新增
                        dataPhotoService.deletePhoto(photo1.getDatUuid());
                    }
                    dataPhotoService.addNewPhoto(photo);// 入库
                }

                // 照片类型如果是99表示客户头像 TODO
                if (photo.getPhotoTypeId() != null && photo.getPhotoTypeId() == 99) {
                    BaseCrmCustomer crm = crmCustomerService.getCrmCustomerById(data
                        .getCustomerId());
                    if (crm != null) {
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("customerId", data.getCustomerId());
                        SysUploadFile upfile = sysUploadFileService.getSysUploadFileById(photo
                            .getFileId());
                        map.put("headshow", upfile.getFilePath() + "/" + upfile.getFileName());
                        crmCustomerService.updateCrmCustomerHeadShow(map);

                    }
                }
            }
            */
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
        if(eventId.equals("2")||eventId.equals("0")||eventId.equals("-1")){eventId="1";}
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
                logger.error("PhotoDataXmlParserServiceImpl error:", e);
            }
        }
        if (StringUtils.isNotEmpty(customerId)) {
            try {
                info.setCustomerId(Integer.valueOf(customerId));
            } catch (Exception e) {
                logger.error("PhotoDataXmlParserServiceImpl error:", e);
            }
        }
        if (StringUtils.isNotEmpty(eventId)) {
            try {
                if(Integer.valueOf(eventId)==5){
                    info.setEventId(Integer.valueOf(eventId)+1);
                }else {
                    info.setEventId(Integer.valueOf(eventId));
                }
            } catch (Exception e) {
                logger.error("PhotoDataXmlParserServiceImpl error:", e);
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
        String gpsLng = root.getChildTextTrim("gpsLng");//GPS坐标
        String gpsLat =root.getChildTextTrim("gpsLat");//GPS坐标
        if (StringUtils.isNotEmpty(gpsLng)&&!"null".equals(gpsLng)){
            info.setGpsLng(gpsLng);
        }
        if (StringUtils.isNotEmpty(gpsLat)&&!"null".equals(gpsLat)){
            info.setGpsLat(gpsLat);
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
                logger.error("PhotoDataXmlParserServiceImpl error:", e);
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
                logger.error("PhotoDataXmlParserServiceImpl error:", e);
            }
        }else{
        	photoName += "申请_";
        }
        if (StringUtils.isNotEmpty(typeId)) {
            info.setPhotoTypeId(Integer.valueOf(typeId));
            photoName += getPhoteType(typeId) + "_";
        }
        if (StringUtils.isNotEmpty(startDate)) {
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
        String result = "";
        if (typeId != null) {
        	if (typeId.equals("1")) {
                result = "身份证复印件及个人征信";
            }else if (typeId.equals("2")) {
                result = "配偶身份证复印件及个人征信";
            }else if (typeId.equals("3")) {
                result = "主借款人身份信息核查";
            }else if (typeId.equals("4")) {
                result = "婚姻证明";
            }else if (typeId.equals("5")) {
                result = "营业执照";
            }else if (typeId.equals("6")) {
                result = "组织机构代码证";
            }else if (typeId.equals("7")) {
                result = "公司章程";
            }else if (typeId.equals("8")) {
                result = "经营许可证";
            }else if (typeId.equals("9")) {
                result = "税务登记证";
            }else if (typeId.equals("10")) {
                result = "纳税申报表近一年";
            }else if (typeId.equals("11")) {
                result = "个人银行流水";
            }else if (typeId.equals("12")) {
                result = "公司银行流水";
            }else if (typeId.equals("13")) {
                result = "调查底稿";
            }else if (typeId.equals("14")) {
                result = "经营场所、仓库租赁合同";
            }else if (typeId.equals("15")) {
                result = "居住场所租赁合同";
            }else if (typeId.equals("16")) {
                result = "家访照片";
            }else if (typeId.equals("17")) {
                result = "合影照片";
            }else if (typeId.equals("18")) {
                result = "共同借款人_收入证明/工作证明";
            }else if (typeId.equals("19")) {
                result = "共同借款人_身份证复印及个人征信";
            }else if (typeId.equals("20")) {
                result = "共同借款人_面谈记录";
            }else if (typeId.equals("21")) {
                result = "担保人_收入证明/工作证明";
            }else if (typeId.equals("22")) {
                result = "担保人_身份证复印及个人征信";
            }else if (typeId.equals("23")) {
                result = "担保人_身份证复印及个人征信";
            }else if (typeId.equals("23")) {
                result = "担保人_面谈记录";                
            }else if (typeId.equals("24")) {
                result = "公司征信";
            }else if (typeId.equals("25")) {
                result = "经营许可证或行业许可证";
            }else if (typeId.equals("26")) {
                result = "调查原表";
            }else if (typeId.equals("27")) {
                result = "决议表";
            }else if (typeId.equals("28")) {
                result = "利率优惠凭证";
            }else if (typeId.equals("29")) {
                result = "资产证明照片";
            }else if (typeId.equals("30")) {
                result = "关联人信息表";
            }else if (typeId.equals("31")) {
                result = "当前逾期还清证明";
            }else if (typeId.equals("32")) {
                result = "行驶证";
            }else if (typeId.equals("33")) {
                result = "房产证明";
            }else if (typeId.equals("34")) {
                result = "抵质押物资料";
            }else if (typeId.equals("35")) {
                result = "抵质押物评估资料";
            }else if (typeId.equals("36")) {
                result = "借款人工作、收入证明";
            }else if (typeId.equals("37")) {
                result = "网龄";
            }else if (typeId.equals("38")) {
                result = "社保、公积金缴纳照片";
            }else if (typeId.equals("39")) {
                result = "工作场所照片";
            }else if (typeId.equals("40")) {
                result = "其他资产证明";
            }else if (typeId.equals("41")) {
                result = "情况说明";
            }else if (typeId.equals("42")) {
                result = "其他";
            }else if (typeId.equals("43")) {
                result = "应收照片";
            }else if (typeId.equals("44")) {
                result = "存货照片";
            }else if (typeId.equals("45")) {
                result = "设备照片";
            }else if (typeId.equals("46")) {
                result = "销售额照片";
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
