package com.banger.mobile.facade.impl.data;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.banger.mobile.constants.TransportConstants;
import com.banger.mobile.domain.model.base.customer.BaseCrmCustomer;
import com.banger.mobile.domain.model.crRequest.CrRequest;
import com.banger.mobile.domain.model.data.CustomerData;
import com.banger.mobile.domain.model.data.Event;
import com.banger.mobile.domain.model.data.Photo;
import com.banger.mobile.domain.model.loan.LnLoanInfo;
import com.banger.mobile.domain.model.user.SysUser;
import com.banger.mobile.facade.crRequest.CrRequestService;
import com.banger.mobile.facade.customer.CrmCustomerService;
import com.banger.mobile.facade.data.CustomerDataService;
import com.banger.mobile.facade.data.DataPhotoService;
import com.banger.mobile.facade.data.ZxDataXmlParserService;
import com.banger.mobile.facade.loan.LnLoanInfoService;
import com.banger.mobile.facade.param.SysParamService;
import com.banger.mobile.facade.uploadFile.SysUploadFileService;
import com.banger.mobile.facade.user.SysUserService;
import com.banger.mobile.transport.TransportUtil;
import com.banger.mobile.util.DateUtil;
import com.banger.mobile.util.FileUtil;

/*
 * @author xiall
 * @version $Id: ZxDataXmlParserServiceImpl.java,v 0.1 2016-13-4 15:44:16
 */
public class ZxDataXmlParserServiceImpl implements ZxDataXmlParserService {

    private static final Logger logger = Logger.getLogger(ZxDataXmlParserServiceImpl.class);

    private SysParamService    sysParamService;
    private SysUserService     sysUserService;
    private SysUploadFileService sysUploadFileService;    
    private CrRequestService crRequestService;
    private CrmCustomerService       crmCustomerService;
    private CustomerDataService      customerDataService;
    private DataPhotoService         dataPhotoService;
    private LnLoanInfoService lnLoanInfoService;

    
    
	public LnLoanInfoService getLnLoanInfoService() {
		return lnLoanInfoService;
	}
	public void setLnLoanInfoService(LnLoanInfoService lnLoanInfoService) {
		this.lnLoanInfoService = lnLoanInfoService;
	}
	public DataPhotoService getDataPhotoService() {
		return dataPhotoService;
	}
	public void setDataPhotoService(DataPhotoService dataPhotoService) {
		this.dataPhotoService = dataPhotoService;
	}
	public CustomerDataService getCustomerDataService() {
		return customerDataService;
	}
	public void setCustomerDataService(CustomerDataService customerDataService) {
		this.customerDataService = customerDataService;
	}
	public CrmCustomerService getCrmCustomerService() {
		return crmCustomerService;
	}
	public void setCrmCustomerService(CrmCustomerService crmCustomerService) {
		this.crmCustomerService = crmCustomerService;
	}
	public SysParamService getSysParamService() {
		return sysParamService;
	}
	public void setSysParamService(SysParamService sysParamService) {
		this.sysParamService = sysParamService;
	}

	public SysUserService getSysUserService() {
		return sysUserService;
	}
	public void setSysUserService(SysUserService sysUserService) {
		this.sysUserService = sysUserService;
	}		
	public SysUploadFileService getSysUploadFileService() {
		return sysUploadFileService;
	}
	public void setSysUploadFileService(SysUploadFileService sysUploadFileService) {
		this.sysUploadFileService = sysUploadFileService;
	}
	public CrRequestService getCrRequestService() {
		return crRequestService;
	}
	public void setCrRequestService(CrRequestService crRequestService) {
		this.crRequestService = crRequestService;
	}
	
	@Override
	public void doJob(String fileName) {
		// TODO Auto-generated method stub
		String dir = TransportUtil.getRecordPath(sysParamService) + TransportConstants.UPLOAD_TEMP_DIR;
		FileUtil.createDir(dir);
		File f = new File(dir + File.separator + fileName + "." + TransportConstants.VALIDATED_FILE_XML_OK);
		if (f.exists()) {
            try {
                // 解析xml文件得到CrRequest主表信息
            	CrRequest crRequest = getCrRequestData(f);

            	String photoType = crRequest.getPhotoType(); //正面up,反面down,授权书auth。

            	CustomerData cData = getData(f);
            	
            	Photo photo = getPhoto(f);
            	
                processPad(f, dir, crRequest, photoType,cData,photo);

                logger.info("ZxDataXmlParserServiceImpl doJob info: " + f.getName());
            } catch (Exception e) {
                logger.error("ZxDataXmlParserServiceImpl doJob  error:", e);
            }
        }
	}
	
	private void processPad(File f, String recordDir, CrRequest crRequest, String photoType,CustomerData cData,Photo photo) throws Exception{
        // xml 文件名 无后缀
        String xmlFileName = f.getName();
        xmlFileName = xmlFileName.replace(TransportConstants.RECORD_INFO_EXTENSION + "."
                                          + TransportConstants.VALIDATED_FILE_OK, "");
        // 对应的照片文件
        String recordFileName = xmlFileName + TransportConstants.PHOTO_FILE_EXTENSION + "."
                                + TransportConstants.VALIDATED_FILE_OK;
        File recordFile = new File(recordDir + "/" + recordFileName);
        // 如果xml对应的照片文件存在，则做入库操作
        if (recordFile.exists()) {
            FileUtil.createDir(recordDir + "/temp");
            File enFile = new File(recordDir + "/temp/" + FilenameUtils.getBaseName(recordFile.getName()));
            FileUtils.copyFile(recordFile, enFile, true);

            // 移动到存储点
            
            int fileId = sysUploadFileService.saveFile(enFile, enFile.getName(), crRequest.getRequestUser(), false);
           
            if(photoType.equals("up")){
            	crRequest.setUpFileId(fileId);
            }else if(photoType.equals("down")){
            	crRequest.setDownFileId(fileId);
            }else{
            	crRequest.setAuthFileId(fileId);
            }
            crRequestService.saveCrRequest(crRequest);
            
            
            Map<String, Object> parameterMap = new HashMap<String, Object>();
            parameterMap.put("loanId", cData.getLoanId());
            parameterMap.put("customerId", cData.getCustomerId());
            parameterMap.put("eventId", cData.getEventId());
            List<CustomerData> datas = customerDataService.getCustomerDataByParameterMap(parameterMap);
            if (datas.size() > 0) {
                // 主资料已经存在
                CustomerData d = datas.get(0);

                // 保存照片数据
                photo.setCustomerDataId(d.getCustomerDataId());
                photo.setFileId(fileId);
                Photo photo1=dataPhotoService.getPhotoByUUID(photo);//是否已经有入库记录
                if (photo1!=null){//有则删除原有记录在新增
                    dataPhotoService.deletePhoto(photo1.getDatUuid());
                }
                dataPhotoService.addNewPhoto(photo);// 入库
            } else {
                customerDataService.addNewCustomerData(cData);

                // 保存照片数据
                photo.setCustomerDataId(cData.getCustomerDataId());
                photo.setFileId(fileId);
                Photo photo1=dataPhotoService.getPhotoByUUID(photo);//是否已经有入库记录
                if (photo1!=null){//有则删除原有记录在新增
                    dataPhotoService.deletePhoto(photo1.getDatUuid());
                }
                dataPhotoService.addNewPhoto(photo);// 入库
            }
            
            
            FileUtils.forceDelete(f);// 删除xml文件
            // 删除压缩文件和解密文件
            FileUtils.forceDelete(recordFile);
            //FileUtils.forceDelete(deFile);
            FileUtils.forceDelete(enFile);
        }
        
	}
	
	private CrRequest getCrRequestData(File f ) throws Exception {
		
		SAXBuilder builder = new SAXBuilder();
        Document document = builder.build(f);
        Element root = document.getRootElement();
        
        String loanId = root.getChildTextTrim("loanId");				//贷款ID
        String customerId = root.getChildTextTrim("customerId");		//客户ID
        String account = root.getChildTextTrim("account");				//登陆账户
        String photoType = root.getChildTextTrim("photoType");			//正面up,反面down,授权书auth。
        
        CrRequest crRequest = new CrRequest();
        
        if (StringUtils.isNotEmpty(loanId)) {
            try {
            	crRequest.setLoanId(Integer.valueOf(loanId));
            } catch (Exception e) {
                logger.error("ZxDataXmlParserServiceImpl error:", e);
            }
        }
        
        if (StringUtils.isNotEmpty(customerId)) {
            try {
            	crRequest.setCustomerId(Integer.valueOf(customerId));
            } catch (Exception e) {
                logger.error("ZxDataXmlParserServiceImpl error:", e);
            }
        }
        
        if (StringUtils.isNotEmpty(account)) {
        	SysUser user = sysUserService.getUserByAccount(account);
            if (user != null) {
            	crRequest.setRequestUser(user.getUserId());
            	crRequest.setCreateUser(user.getUserId());
            	crRequest.setUpdateUser(user.getUserId());
            }
        }
        
        if (StringUtils.isNotEmpty(photoType)) {
            try {
            	crRequest.setPhotoType(photoType);
            } catch (Exception e) {
                logger.error("ZxDataXmlParserServiceImpl error:", e);
            }
        }
        
        crRequest.setRequestStatus(1);
        crRequest.setCreateDate(Calendar.getInstance().getTime());
        crRequest.setUpdateDate(Calendar.getInstance().getTime());   
        
		return crRequest;
	}		

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



        if (StringUtils.isNotEmpty(loanId)) {
            try {
                info.setLoanId(Integer.valueOf(loanId));
                
                Map<String,Object> param = new HashMap<String, Object>();
    	        param.put("loanId", loanId);
    	        LnLoanInfo lnLoanInfo = lnLoanInfoService.selectLoanInfoList(param).get(0);
    	        
    	        info.setCustomerId(lnLoanInfo.getCustomerId());
    	        
            } catch (Exception e) {
                logger.error("ZxDataXmlParserServiceImpl error:", e);
            }
        }
        
        
        
        if (StringUtils.isNotEmpty(eventId)) {
            try {
                info.setEventId(Integer.valueOf(eventId));
            } catch (Exception e) {
                logger.error("ZxDataXmlParserServiceImpl error:", e);
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
        String uuid = root.getChildTextTrim("uuid");
        String account = root.getChildTextTrim("account");
        String fileName = root.getChildTextTrim("fileName");
        String customerId = root.getChildTextTrim("customerId");
        String eventId = root.getChildTextTrim("eventId");
        if(eventId.equals("2")){eventId="1";}


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
                logger.error("ZxDataXmlParserServiceImpl error:", e);
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
                logger.error("ZxDataXmlParserServiceImpl error:", e);
            }
        }else{
        	 photoName += "申请_";
        }
        
        photoName += "身份证复印件及个人征信" + "_"+DateUtil.convertDateToString("yyyyMMdd(HHmmss)", Calendar.getInstance().getTime());
                   
        info.setPhotoName(photoName);
        info.setPhotoTypeId(1);
        info.setRecordDate(Calendar.getInstance().getTime());
        info.setRemark("");
        // 取得用户id
        SysUser user = sysUserService.getUserByAccount(account);
        if (user != null) {
            info.setUploadUserId(user.getUserId());
            info.setCreateUser(user.getUserId());
            info.setUpdateUser(user.getUserId());
        }

        info.setFileName(fileName);
        info.setFilePath("");
        info.setUploadDate(Calendar.getInstance().getTime());
        info.setCreateDate(Calendar.getInstance().getTime());
        info.setUpdateDate(Calendar.getInstance().getTime());

        return info;
    }
}
