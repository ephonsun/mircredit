/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :录音信息业务实现类
 * Author     :huangkai
 * Version    :1.0
 * Create Date:May 3, 2012
 */
package com.banger.mobile.facade.impl.record;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

import banger.util.BDate;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.record.RecordInfoDao;
import com.banger.mobile.dao.user.SysUserDao;
import com.banger.mobile.domain.Enum.record.EnumRecord;
import com.banger.mobile.domain.model.base.customer.BaseCrmCustomer;
import com.banger.mobile.domain.model.customer.CrmCustomer;
import com.banger.mobile.domain.model.customer.CustomerExportBar;
import com.banger.mobile.domain.model.microTask.TskMicroTaskTarget;
import com.banger.mobile.domain.model.phoneConfig.PhoneConfig;
import com.banger.mobile.domain.model.record.PhoneRecordBean;
import com.banger.mobile.domain.model.record.RecordDetail;
import com.banger.mobile.domain.model.record.RecordExportBean;
import com.banger.mobile.domain.model.record.RecordInfo;
import com.banger.mobile.domain.model.record.RecordInfoBean;
import com.banger.mobile.domain.model.record.RecordQueryBean;
import com.banger.mobile.domain.model.record.RecordTemp;
import com.banger.mobile.domain.model.record.TalkRecordInfo;
import com.banger.mobile.domain.model.templateField.CrmTemplateField;
import com.banger.mobile.domain.model.user.IUserInfo;
import com.banger.mobile.domain.model.user.SysUser;
import com.banger.mobile.facade.crmModuleExport.CrmModuleExportService;
import com.banger.mobile.facade.customer.CrmCustomerService;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.phoneConfig.PhoneConfigService;
import com.banger.mobile.facade.record.RecordInfoService;
import com.banger.mobile.facade.system.CdSystemService;
import com.banger.mobile.facade.template.CrmTemplateService;
import com.banger.mobile.util.DateUtil;
import com.banger.mobile.util.ExcelUtil;
import com.banger.mobile.util.ReflectorUtil;
import com.banger.mobile.util.StringUtil;
import com.banger.mobile.util.TypeUtil;

/**
 * @author huangkai
 * @version $Id: RecordInfoServiceImpl.java,v 0.1 May 3, 2012 1:44:07 PM huangkai Exp $
 */
public class RecordInfoServiceImpl implements RecordInfoService {

    protected final Log              log = LogFactory.getLog(getClass());

    private RecordInfoDao            recordInfoDao;                                    //录音信息dao

    SimpleDateFormat                 df  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat df0=new SimpleDateFormat("yyyy-MM-dd 00:00:00");
    SimpleDateFormat df1=new SimpleDateFormat("yyyy-MM-dd 23:59:59");
    private  SysUserDao sysUserDao;                   //用户
    private DeptFacadeService        deptFacadeService;
    private CrmTemplateService crmTemplateService;    //模板
    private CdSystemService codetableService ;        //省份 城市 
    private CrmCustomerService crmCustomerService;    //客户service
    private PhoneConfigService phoneConfigService;  //获取当前用户的电话区号
    
    private PageUtil<RecordInfoBean> recordInfoList;
    private List<RecordExportBean>   recordExportList;
    DateFormat df4=new SimpleDateFormat("yyyyMMdd");
 

    public DeptFacadeService getDeptFacadeService() {
        return deptFacadeService;
    }

    public void setSysUserDao(SysUserDao sysUserDao) {
        this.sysUserDao = sysUserDao;
    }

    public void setCrmTemplateService(CrmTemplateService crmTemplateService) {
        this.crmTemplateService = crmTemplateService;
    }

    public void setCodetableService(CdSystemService codetableService) {
        this.codetableService = codetableService;
    }

    public void setDeptFacadeService(DeptFacadeService deptFacadeService) {
        this.deptFacadeService = deptFacadeService;
    }
    private static Map<Integer,CustomerExportBar> customerExportBar;
    
    public static Map<Integer, CustomerExportBar> getCustomerExportBar() {
        if(customerExportBar == null) customerExportBar = new HashMap<Integer,CustomerExportBar>();
        return customerExportBar;
    }
    /**
     * 添加一条录音信息
     * @param recordInfo
     * @see com.banger.mobile.facade.record.RecordInfoService#addRecordInfo(com.banger.mobile.domain.model.record.RecordInfo)
     */
    public void addRecordInfo(RecordInfo recordInfo) {
        Integer id = newTalkId();
        if (recordInfo.getRecordInfoId() == null) {
            recordInfo.setRecordInfoId(id);
        }
        recordInfoDao.addRecordInfo(recordInfo);
    }

    /**
     * 删除录音信息
     * @param id
     * @see com.banger.mobile.facade.record.RecordInfoService#deleteRecordInfo(int)
     */
    public void deleteRecordInfo(int id) {
        recordInfoDao.deleteRecordInfo(id);
    }

    /**
     * 根据id得到录音信息
     * @param id
     * @return
     * @see com.banger.mobile.facade.record.RecordInfoService#getRecordInfoById(int)
     */
    public RecordDetail getRecordInfoById(int id) {
        RecordDetail rd = recordInfoDao.getRecordInfoById(id);
        if (rd.getCallTime() != null) {
            int l = Integer.parseInt(rd.getCallTime());
            String H = "" + l / 3600;
            if (H.length() < 2)
                H = "0" + H;
            l = l % 3600;
            String M = "" + l / 60;
            if (M.length() < 2)
                M = "0" + M;
            l = l % 60;
            String S = "" + l;
            if (S.length() < 2)
                S = "0" + S;
            rd.setCallTime(H + ":" + M + ":" + S);
        }
        return rd;
    }

    /**
     * 录音信息分页
     * @param map
     * @param page
     * @return
     * @see com.banger.mobile.facade.record.RecordInfoService#getRecordInfoPage(java.util.Map, com.banger.mobile.Page)
     */
    public PageUtil<RecordInfoBean> getRecordInfoPage(Map<String, Object> map, Page page, int type,
                                                      RecordQueryBean queryBean,String callTypeCodes) {
        if (queryBean != null) {
            if (queryBean.getCustomerTypeId()!=null&&queryBean.getCustomerTypeId() == 3)
            map.put("isKnowCustomer", 1);
            if(queryBean.getBizType()!=null&&!(queryBean.getBizType()+"").equals("0")){
                map.put("bizType", queryBean.getBizType());
            }
            if(queryBean.getCustomer()!=null&&!queryBean.getCustomer().equals("")){
                map.put("customer", StringUtil.ReplaceSQLChar(queryBean.getCustomer().trim()));
            }
            if(queryBean.getStartDate()!=null&&!queryBean.getStartDate().equals("")){
                map.put("startDate", df0.format(queryBean.getStartDate()));
            }
            if(queryBean.getEndDate()!=null&&!queryBean.getEndDate().equals("")){
                map.put("endDate", df1.format(queryBean.getEndDate()));
            }
            if(queryBean.getCommProgressId()!=null){
                map.put("commProgressId", queryBean.getCommProgressId());
            }
            if(queryBean.getIsCanceled()!=null&&queryBean.getIsCanceled()!=2){
            	map.put("isCanceled", queryBean.getIsCanceled());
            }
            if(queryBean.getRecordType()!=null){//根据录音条件查询
                if (queryBean.getRecordType() == 1)
                    map.put("notExistRecord", 1);//查询无录音
                else if (queryBean.getRecordType() == 2)
                    map.put("existRecord", 1);//查询有录音
                else if (queryBean.getRecordType() == 3) {//查询0~5
                    map.put("callTime1", 0);
                    map.put("callTime2", 60 * 5);
                } else if (queryBean.getRecordType() == 4) {//查询5~15
                    map.put("callTime1", 60 * 5);
                    map.put("callTime2", 60 * 15);
                } else if (queryBean.getRecordType() == 5) {//查询15~30
                    map.put("callTime1", 60 * 15);
                    map.put("callTime2", 60 * 30);
                } else if (queryBean.getRecordType() == 6) {//查询30~60
                    map.put("callTime1", 60 * 30);
                    map.put("callTime2", 60 * 60);
                } else if (queryBean.getRecordType() == 7) {//查询一小时以上
                    map.put("callTime1", 60 * 60);
                    map.put("callTime2", Integer.MAX_VALUE);
                }
            }
            map.put("remark", StringUtil.ReplaceSQLChar(queryBean.getRemark()));
        }
        if(queryBean != null && queryBean.getCallType()!=null && !"".equals(queryBean.getCallType())){//联系类型转换成id
            if(queryBean.getCallType().contains("呼入")){
            	callTypeCodes+="1,3,6,";
            }
            if(queryBean.getCallType().contains("已接")){
                callTypeCodes+="1,";
            }
            if(queryBean.getCallType().contains("呼出")||queryBean.getCallType().contains("已拨")){
                callTypeCodes+="2,";
            }
            if(queryBean.getCallType().contains("未接")){
                callTypeCodes+="3,6,";
            }
            if(queryBean.getCallType().contains("座谈")){
                callTypeCodes+="4,";
            }
            if(queryBean.getCallType().contains("拜访")){
                callTypeCodes+="5,";
            }
            if(queryBean.getCallType().contains("短信")){
                callTypeCodes+="7,8,";
            }
            if(queryBean.getCallType().contains("彩信")){
                callTypeCodes+="9,";
            }
            if(callTypeCodes.length()>0)//祛逗号
                callTypeCodes=callTypeCodes.substring(0, callTypeCodes.length()-1);
        }
        
        if (type == 0) {//所有记录
            if(!"".equals(callTypeCodes)){
                map.put("callTypeId", callTypeCodes);
            }else{
                map.put("callTypeId", "1,2,3,4,5,6,7,8,9");
            }
        } else if (type == 1) {//通话记录
            if(!"".equals(callTypeCodes)){
                map.put("callTypeId", callTypeCodes);
            }else{
                map.put("callTypeId", "1,2,3,6");
            }
        } else if (type == 2) {//座谈记录
            if(!"".equals(callTypeCodes)){
                map.put("callTypeId", callTypeCodes);
            }else{
                map.put("callTypeId", "4");
            }
        } else if (type == 3) {//拜访
            map.put("callTypeId", "5");

        } else if (type == 4) {//短信记录
            if(!"".equals(callTypeCodes)){
                map.put("callTypeId", queryBean.getCallTypeId());
            }else if(queryBean!=null&&queryBean.getCallTypeId()!=0){
                map.put("callTypeId", queryBean.getCallTypeId());
            }else{
                map.put("callTypeId", "7,8");
            }
            if(queryBean!=null&&queryBean.getContent()!=null){
                map.put("content", StringUtil.ReplaceSQLChar(queryBean.getContent().trim()));
            }
        } else if (type == 5) {//彩信记录
            map.put("callTypeId", "9");
            if(queryBean!=null&&queryBean.getStatus()!=null&&!queryBean.getStatus().equals("无")){
                map.put("status", queryBean.getStatus());
            }
            if(queryBean!=null&&queryBean.getMmsTitle()!=null){
                map.put("mmsTitle", StringUtil.ReplaceSQLChar(queryBean.getMmsTitle().trim()));
            }
        }

        recordInfoList = recordInfoDao.getRecordInfoPage(map, page);
        if(recordInfoList.getPage().getTotalPages()<recordInfoList.getPage().getCurrentPage()){
            recordInfoList.getPage().setCurrentPage(recordInfoList.getPage().getTotalPages());
        }
        
        if (type == 0) {//判断当前type是什么，如果是所有记录就把type=呼入，呼出，未接换成通话记录
            int nowPageSize = 0;
            for (int i = 0; i < recordInfoList.getPage().getPageSize(); i++) {
                nowPageSize++;
                if (recordInfoList.getPage().getTotalRowsAmount() < ((recordInfoList.getPage().getCurrentPage() - 1)* recordInfoList.getPage().getPageSize() + nowPageSize)) {
                    break;
                }
                if (recordInfoList.getItems()!=null&&recordInfoList.getItems().size()!=0&&recordInfoList.getItems().get(i).getCallTime() != null&& recordInfoList.getItems().get(i).getCallTime().length() != 0) {
                    recordInfoList.getItems().get(i).setCallTime(changTime(recordInfoList.getItems().get(i).getCallTime()));//修改时长格式
                }
//                if (recordInfoList.getItems()!=null&&recordInfoList.getItems().size()!=0&&recordInfoList.getItems().get(i).getCallTypeName() != null){
//                    if(recordInfoList.getItems().get(i).getCallTypeName().equals(EnumRecord.MISSED_CALLS.getValue())|| recordInfoList.getItems().get(i).getCallTypeName().equals(EnumRecord.UNREAD_MESSAGE.getValue())||recordInfoList.getItems().get(i).getCallTypeName().equals(EnumRecord.CALL_IN.getValue())||recordInfoList.getItems().get(i).getCallTypeName().equals(EnumRecord.CALL_OUT.getValue())) {
//                        recordInfoList.getItems().get(i).setCallTypeName(EnumRecord.CALL_INFO.getValue());
//                    }
//                }
//                if (recordInfoList.getItems()!=null&&recordInfoList.getItems().size()!=0&&recordInfoList.getItems().get(i).getCallTypeName() != null){
//                    if(recordInfoList.getItems().get(i).getCallTypeName().equals(EnumRecord.RECEIVE_SMS.getValue())|| recordInfoList.getItems().get(i).getCallTypeName().equals(EnumRecord.SEND_SMS.getValue())){
//                        recordInfoList.getItems().get(i).setCallTypeName(EnumRecord.SMS.getValue());
//                    }
//                }
            }
        } else if(type == 1){
            int nowPageSize = 0;
            for (int i = 0; i < recordInfoList.getPage().getPageSize(); i++) {
                nowPageSize++;
                if (recordInfoList.getPage().getTotalRowsAmount() < ((recordInfoList.getPage().getCurrentPage() - 1)* recordInfoList.getPage().getPageSize() + nowPageSize)) {
                    break;
                }
                if (recordInfoList.getItems()!=null&&recordInfoList.getItems().size()!=0){
                    if(recordInfoList.getItems().get(i).getCallTime() != null&& recordInfoList.getItems().get(i).getCallTime().length() != 0){
                        recordInfoList.getItems().get(i).setCallTime(changTime(recordInfoList.getItems().get(i).getCallTime()));//修改时长格式
                    }
//                    if(recordInfoList.getItems().get(i).getCallTypeName() != null){
//                        if(recordInfoList.getItems().get(i).getCallTypeName().equals(EnumRecord.CALL_IN.getValue())) {
//                            recordInfoList.getItems().get(i).setCallTypeName(EnumRecord.ALREADY_CALLS.getValue());
//                        }else if(recordInfoList.getItems().get(i).getCallTypeName().equals(EnumRecord.CALL_OUT.getValue())) {
//                            recordInfoList.getItems().get(i).setCallTypeName(EnumRecord.DIAL_CALLS.getValue());
//                        }else if(recordInfoList.getItems().get(i).getCallTypeName().equals(EnumRecord.UNREAD_MESSAGE.getValue())) {
//                            recordInfoList.getItems().get(i).setCallTypeName(EnumRecord.MISSED_CALLS.getValue());
//                        }
//                    }
                }
                
            }
        }else if(type==2||type==3){//修改时长格式
            int nowPageSize = 0;
            for (int i = 0; i < recordInfoList.getPage().getPageSize(); i++) {
                nowPageSize++;
                if (recordInfoList.getPage().getTotalRowsAmount() < ((recordInfoList.getPage().getCurrentPage() - 1)* recordInfoList.getPage().getPageSize() + nowPageSize)) {
                    break;
                }
                if (recordInfoList.getItems()!=null&&recordInfoList.getItems().size()!=0&&recordInfoList.getItems().get(i).getCallTime() != null
                    && recordInfoList.getItems().get(i).getCallTime().length() != 0) {
                    recordInfoList.getItems().get(i).setCallTime(
                        changTime(recordInfoList.getItems().get(i).getCallTime()));//修改时长格式
                }
            }
        }
        return recordInfoList;
    }

    /**
     * 修改录音信息
     * @param recordInfo
     * @see com.banger.mobile.facade.record.RecordInfoService#updateRecordInfo(com.banger.mobile.domain.model.record.RecordInfo)
     */
    public void updateRecordInfo(RecordInfo recordInfo) {
        recordInfoDao.updateRecordInfo(recordInfo);
    }

    /**
     * 修改录音是否有效
     * @param recordInfo
     * @see com.banger.mobile.facade.record.RecordInfoService#updateRecordInfo(com.banger.mobile.domain.model.record.RecordInfo)
     */
    public void updateRecordCanceled(int id, int isCanceled) {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("id", id);
        m.put("isCanceled", isCanceled);
        recordInfoDao.updateRecordCanceled(m);
    }

    /**
     * 归档录音信息分页
     * @param parameters
     * @param page
     * @return
     * @see com.banger.mobile.facade.record.RecordInfoService#getArchiveRecordInfoPage(java.util.Map, com.banger.mobile.Page)
     */
    public PageUtil<RecordInfoBean> getArchiveRecordInfoPage(Map<String, Object> parameters,
                                                             Page page) {
        return recordInfoDao.getArchiveRecordInfoPage(parameters, page);

    }

    /**
     * 根据沟通进度的ID删除沟通进度ID值 
     * @param Id
     * @return boolean
     */
    public boolean deleteCommProgressId(int CommProgressId) {
        return recordInfoDao.deleteCommProgressId(CommProgressId);
    }

    /**
     * 客户联系记录信息接口
     * @param CustomerId
     * @return list
     */
    public PageUtil<RecordInfoBean> getCustomerRecord(Map<String, Object> parameterMap, Page page) {
        return recordInfoDao.queryCustomerCallInfoByCusId(parameterMap,page);
    };
    
    /**
     * 客户联系记录总数接口
     * @param CustomerId
     */
    public int queryCustomerCallInfoCountByCusId(int customerId){
        return recordInfoDao.queryCustomerCallInfoCountByCusId(customerId);
    }

    /**
     * 根据归档时间查询可归档的录音
     */
    public List<RecordInfo> queryRecordToGd(Map<String, Object> parameters) {
        return recordInfoDao.queryRecordToGd(parameters);
    }

    /**
     * 根据id查询全字段信息
     * @param Id 
     */
    public RecordInfo queryAllById(int Id) {
        return recordInfoDao.queryAllById(Id);
    }

    public List<RecordInfo> queryAllByNo(String no) {
        return recordInfoDao.queryAllByNo(no);
    }

    /**
     * 根据id修改录音是否已读
     * @param Id
     */
    public void updateRecIsRead(int id) {
        recordInfoDao.updateRec(id);
    }

    /**
     * 修改record编辑信息
     * @param updateRecordConnect
     */
    public void updateRecordConnect(RecordDetail recordDetail) {
        recordInfoDao.updateRecordConnect(recordDetail);
    }

    /**
     * 按条件返回结果
     * @param map
     */
    public List<RecordInfoBean> getRecordInfo(Map<String, Object> parameters) {
        return recordInfoDao.getRecordInfo(parameters);
    }

    /**
     * 按条件返回归档结果
     * @param map
     */
    public List<RecordInfoBean> getRecordArchiveInfo(Map<String, Object> parameters) {
        return recordInfoDao.getRecordArchiveInfo(parameters);
    }

    /**
     * 导出Excel
     * @param recordInfoDao
     */
    public HSSFWorkbook RecordReportDownExcel(List<RecordInfoBean> rlist) {
        HSSFWorkbook wk = new HSSFWorkbook();
        HSSFSheet sheet = wk.createSheet();
        sheet.setDefaultColumnWidth((short) 15);//设置默认宽度
        String bean[] = null;
        try {
            ExcelUtil.writeExcelHeader(sheet, new String[] { EnumRecord.EXCEL_RECORDNO.getValue(),
                    EnumRecord.EXCEL_CUSTOMER.getValue(), EnumRecord.EXCEL_CALLTYPE.getValue(),
                    EnumRecord.EXCEL_STARTDATE.getValue(), EnumRecord.EXCEL_CALLTIME.getValue(),
                    EnumRecord.EXCEL_USER.getValue(), EnumRecord.EXCEL_BIZTYPE.getValue(),
                    EnumRecord.EXCEL_RECORDSOURCE.getValue(), EnumRecord.EXCEL_STATE.getValue(),
                    EnumRecord.EXCEL_REMARK.getValue() }, 0);
            //            Pattern p=Pattern.compile("^\\d+(\\.\\d+)?$");
            //            Matcher matcher=null;
            if (rlist != null) {
                int rowNum = 1;
                for (RecordInfoBean recordInfo : rlist) {
                    bean = new String[10];
                    bean[0] = recordInfo.getRecordNo();
                    if (StringUtil.isNotEmpty(recordInfo.getCustomerId())&& !recordInfo.getCustomerId().equals("0")) {
                        if (StringUtil.isNotEmpty(recordInfo.getCustomerName())) {
                            bean[1] = recordInfo.getCustomerName();
                            if (StringUtil.isNotEmpty(recordInfo.getRemotePhone())) {
                                bean[1] += "(" + recordInfo.getRemotePhone() + ")";
                            }
                        } else if (StringUtil.isNotEmpty(recordInfo.getRemotePhone())) {
                            bean[1] += recordInfo.getRemotePhone();
                        }
                    } else if (StringUtil.isEmpty(recordInfo.getRemotePhone())) {
                        bean[1] = EnumRecord.EXCEL_KNOWCUSTOMER.getValue();
                    } else {
                        bean[1] = EnumRecord.EXCEL_KNOWCUSTOMER.getValue() + "("
                                  + recordInfo.getRemotePhone() + ")";
                    }
                    bean[2] = recordInfo.getCallTypeName();
                    bean[3] = df.format(recordInfo.getStartDate());
                    bean[4] = recordInfo.getCallTime();
                    bean[5] = recordInfo.getUserName();
                    bean[6] = recordInfo.getBizType();
                    bean[7] = recordInfo.getRecordSource();
                    bean[8] = recordInfo.getState();
                    bean[9] = recordInfo.getRemark();
                    HSSFRow row = sheet.createRow(rowNum);

                    if (bean != null && bean.length > 0) {
                        for (int i = 0; i < bean.length; i++) {
                            HSSFCell cell = row.createCell((short) i);
                            //                            matcher=p.matcher(bean[i]);
                            //                            if(matcher.matches()){
                            ////                                是数字当作double处理
                            //                                cell.setCellValue(Double.parseDouble(bean[i]));
                            //                            }else
                            cell.setCellValue(bean[i]);
                        }
                    }
                    rowNum++;
                }
            }
        } catch (Exception e) {
            log.error(e);
        }
        return wk;
    }

    public void setRecordInfoDao(RecordInfoDao recordInfoDao) {
        this.recordInfoDao = recordInfoDao;
    }

    /**
     * 得到一个新的通话ID
     * @return
     */
    public Integer newTalkId() {
        return this.recordInfoDao.newTalkId();
    }

    /**
     * 保存通话记录
     * @param record
     */
    public void saveTalkRecord(PhoneRecordBean record) {
        Integer talkId = record.getRecordInfoId();
        if (this.recordInfoDao.isExistRecord(talkId)) //是否存在记录
        {
            this.recordInfoDao.updateTalkRecord(record);
        } else //存在客户
        {
            this.recordInfoDao.insertTalkRecord(record);
        }
    }

    /**
     * 保存通话记录
     * @param record
     */
    public void saveTalkRecordByNo(PhoneRecordBean record) {
        String recordNo = record.getRecordNo();
        if (this.recordInfoDao.isExistRecordByNo(recordNo)) //是否存在记录
        {
            this.recordInfoDao.updateTalkRecordByNo(record);
        } else //存在客户
        {
            this.recordInfoDao.insertTalkRecordByNo(record);
        }
    }

    /**
     * 取得联系记录流水号
     * @return
     * @see com.banger.mobile.facade.record.RecordInfoService#genRecrodInfoNo()
     */
    public String genRecrodInfoNo() {
        String result = "";
        result = UUID.randomUUID().toString();
        return result;
    }

    /**
     * 根据客户id查询最近一次的联系类型
     * @return list<RecordInfoBean>
     * @param int customerId
     */
    public RecordInfoBean getLastListbyCustomerId(int customerId) {
        if (String.valueOf(customerId).length() != 0) {
            List<RecordInfoBean> rlist = recordInfoDao.getLastListbyCustomerId(customerId);
            if (rlist.size() != 0) {
                return rlist.get(0);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * 根据记录id修改归档内容
     * @param map
     */
    public void updateArchiveById(int id, String filePath) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id", id);
        parameters.put("filePath", filePath);
        parameters.put("archiveDate", df.format(new Date()));
        recordInfoDao.updateArchiveById(parameters);
    }

    /**
     * 处理去电号码
     * @param number
     * @return
     */
//    public String formatOutNumber2(String number, PhoneConfig config) {
//        if (number != null && !"".equals(number)) {
//            String outNumber = "";
//            boolean inline = false; //是否为内线
//            if (number.length() > 6) {
//                outNumber = number;
//            } else {
//                int inLen = 0;
//                if (config.getInsiseExtLength() != null && !"".equals(config.getInsiseExtLength()))
//                    inLen = Integer.parseInt(config.getInsiseExtLength());
//                if (number.length() == inLen)
//                    inline = true;
//                outNumber = number;
//            }
//
//            if (inline) //是否拨分机
//            {
//                return outNumber;
//            } else {
//                if (config.getOutsideCallCode() != null && !"".equals(config.getOutsideCallCode())) {
//                    int outLen = config.getOutsideCallCode().length();
//                    if (outNumber.length()>outLen && outNumber.substring(0, outLen).equals(config.getOutsideCallCode())) {
//                        outNumber = outNumber.substring(outLen);
//                    }
//                }
//                if (config.getIsIpCall() != null && config.getIsIpCall().intValue() > 0) {
//                    if (config.getIpNumber() != null && !"".equals(config.getIpNumber())) {
//                        int ipLen = config.getIpNumber().length();
//                        if (outNumber.length()>ipLen && outNumber.substring(0, ipLen).equals(config.getIpNumber())) {
//                            outNumber = outNumber.substring(ipLen);
//                        }
//                    }
//                }
//                if (this.isMobileNumber(outNumber)) //判断是否为手机号码
//                {
//                    outNumber = (outNumber.length() == 12) ? outNumber.substring(1) : outNumber;
//                }
//
//            }
//            return outNumber;
//        }
//        return "";
//    }

    public String formatOutNumber(String number, PhoneConfig config)
	{
		if (number != null && !"".equals(number))
		{
			number = number.replace("#", "").replace("*", "");
			String outNumber = number;
			boolean inline = false; // 是否为内线

			if (config.getOutsideCallCode() != null
					&& !"".equals(config.getOutsideCallCode()))
			{
				int outLen = config.getOutsideCallCode().length();
				if (number.length() > outLen
						&& number.substring(0, outLen).equals(
								config.getOutsideCallCode()))
				{
					outNumber = number.substring(outLen);
				}
			}

			if (outNumber.length() < 7)
			{
				int inLen = 0;
				if (config.getInsiseExtLength() != null
						&& !"".equals(config.getInsiseExtLength()))
					inLen = Integer.parseInt(config.getInsiseExtLength());
				if (outNumber.length() == inLen)
					inline = true;
			}

			if (inline) // 是否拨分机
			{
				return outNumber;
			} else
			{
				if (config.getIsIpCall() != null
						&& config.getIsIpCall().intValue() > 0)
				{
					if (config.getIpNumber() != null
							&& !"".equals(config.getIpNumber()))
					{
						int ipLen = config.getIpNumber().length();
						if (outNumber.length() > ipLen
								&& outNumber.substring(0, ipLen).equals(
										config.getIpNumber()))
						{
							outNumber = outNumber.substring(ipLen);
						}
					}
				}
				if (this.isMobileNumber(outNumber)) // 判断是否为手机号码
				{
					outNumber = (outNumber.length() == 12) ? outNumber
							.substring(1) : outNumber;
				} else if (outNumber.length() > 6)
				{
					if (config.getCityCode() != null
							&& !"".equals(config.getCityCode()))
					{
						int cityLen = config.getCityCode().length();
						if (outNumber.length() > cityLen
								&& outNumber.substring(0, cityLen).equals(
										config.getCityCode()))
						{
							outNumber = outNumber.substring(cityLen);
						}
					}
				}

			}
			return outNumber;
		}
		return "";
	}
    
    /**
     * 处理电话号码
     * @param number
     * @return
     */
    public String formatIncomingNumber(String number, PhoneConfig config) {
        if (number != null && !"".equals(number)) {
            String outNumber = (config.getOutNumber() != null) ? config.getOutNumber() : "";
            int outLen = outNumber.length();
            String inNumber = (outLen > 0 && number.substring(0, outLen).equals(outNumber)) //移除来电出局号
            ? number.substring(outLen)
                : number;
            if (this.isMobileNumber(inNumber)) //判断是否为手机号码
            {
                return (inNumber.length() == 12) ? inNumber.substring(1) : inNumber;
            } else if (inNumber.length() > 6) //是否为固话
            {
                if (config.getCityCode() != null && !"".equals(config.getCityCode())) //判断是否为本地固话
                {
                    int cityLen = config.getCityCode().length();
                    if (inNumber.substring(0, cityLen).equals(config.getCityCode()))
                        return inNumber.substring(cityLen);
                }
                return inNumber;
            } else {
                return inNumber;
            }
        }
        return "";
    }

    /**
     * 判断是否为手机号码
     * @param number
     * @return
     */
    private boolean isMobileNumber(String number) {
        if (number != null) {
            if ((number.length() == 12 && number.charAt(0) == '0') || number.length() == 11) {
                String str = (number.length() == 12) ? number.substring(1, 12) : number;
                if (str.charAt(0) == '1' && str.charAt(1) != '0')
                    return true;
            }
        }
        return false;
    }

    /**
     * 未读留言总数
     *@param int userid 
     *@return int 总数
     */
    public int getUnreadCount(int userId) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("calltype", 6);
        parameters.put("permission", userId);
        parameters.put("isRead", 0);
        parameters.put("isNotMissed", 1);
        return recordInfoDao.getUnreadCount(parameters);
    }

    /**
     * 未接电话总数
     *@param int userid 
     *@return int 总数
     */
    public int getMissedCount(int userId) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("calltype", 3);
        parameters.put("isMissed", 1);
        parameters.put("permission", userId);
        parameters.put("isRead", 0);
        return recordInfoDao.getMissedCount(parameters);
    }
    
    /**
     * 未读座谈总数
     *@param int userid 
     *@return int 总数
     */
    public int getUnreadZTCount(int userId) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("calltype", 4);
        parameters.put("permission", userId);
        parameters.put("isRead", 0);
        return recordInfoDao.getMissedCount(parameters);
    }
    
    /**
     * 未读短信总数
     *@param int userid 
     *@return int 总数
     */
    public int getMissedSMSCount(int userId) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("calltype", "7,8");
        parameters.put("permission", userId);
        parameters.put("isRead", 0);
        return recordInfoDao.getMissedCount(parameters);
    }

    /**
     * 批量添加联系记录
     */
    public void addRecordInfoBatch(List<RecordInfo> list) {
        recordInfoDao.addRecordInfoBatch(list);
    }

    /**
     * 查询待导出的联系记录
     */
    public List<RecordExportBean> queryRecordInfosByMap(Map<String, Object> map, int type,
                                                        RecordQueryBean queryBean, int startRow,
                                                        int endRow) {
        if (queryBean != null) {
            if (queryBean.getCustomerTypeId() == 3)
            map.put("isKnowCustomer", 1);
            if(!(queryBean.getBizType()+"").equals("0")){
                map.put("bizType", queryBean.getBizType());
            }
            if(queryBean.getCustomer()!=null&&!queryBean.getCustomer().equals("")){
                map.put("customer", StringUtil.ReplaceSQLChar(queryBean.getCustomer().trim()));
            }
            if(queryBean.getStartDate()!=null&&!queryBean.getStartDate().equals("")){
                map.put("startDate", df0.format(queryBean.getStartDate()));
            }
            if(queryBean.getEndDate()!=null&&!queryBean.getEndDate().equals("")){
                map.put("endDate", df1.format(queryBean.getEndDate()));
            }
            if(queryBean.getCommProgressId()!=null){
                map.put("commProgressId", queryBean.getCommProgressId());
            }
            if(queryBean.getIsCanceled()!=null&&queryBean.getIsCanceled()!=2){
            	map.put("isCanceled", queryBean.getIsCanceled());
            }
            if(queryBean.getRecordType()!=null){//根据录音条件查询
                if (queryBean.getRecordType() == 1)
                    map.put("notExistRecord", 1);//查询无录音
                else if (queryBean.getRecordType() == 2)
                    map.put("existRecord", 1);//查询有录音
                else if (queryBean.getRecordType() == 3) {//查询0~5
                    map.put("callTime1", 0);
                    map.put("callTime2", 60 * 5);
                } else if (queryBean.getRecordType() == 4) {//查询5~15
                    map.put("callTime1", 60 * 5);
                    map.put("callTime2", 60 * 15);
                } else if (queryBean.getRecordType() == 5) {//查询15~30
                    map.put("callTime1", 60 * 15);
                    map.put("callTime2", 60 * 30);
                } else if (queryBean.getRecordType() == 6) {//查询30~60
                    map.put("callTime1", 60 * 30);
                    map.put("callTime2", 60 * 60);
                } else if (queryBean.getRecordType() == 7) {//查询一小时以上
                    map.put("callTime1", 60 * 60);
                    map.put("callTime2", Integer.MAX_VALUE);
                }
            }
            map.put("remark", StringUtil.ReplaceSQLChar(queryBean.getRemark()));
        }
        
        String callTypeCodes="";
        if(queryBean != null && queryBean.getCallType()!=null && !"".equals(queryBean.getCallType())){//联系类型转换成id
        	if(queryBean.getCallType().contains("呼入")){
                callTypeCodes+="1,3,6,";
            }
            if(queryBean.getCallType().contains("已接")){
                callTypeCodes+="1,";
            }
            if(queryBean.getCallType().contains("呼出")||queryBean.getCallType().contains("已拨")){
                callTypeCodes+="2,";
            }
            if(queryBean.getCallType().contains("未接")){
                callTypeCodes+="3,6,";
            }
            if(queryBean.getCallType().contains("座谈")){
                callTypeCodes+="4,";
            }
            if(queryBean.getCallType().contains("拜访")){
                callTypeCodes+="5,";
            }
            if(queryBean.getCallType().contains("短信")){
                callTypeCodes+="7,8,";
            }
            if(queryBean.getCallType().contains("彩信")){
                callTypeCodes+="9,";
            }
            if(callTypeCodes.length()>0)//祛逗号
                callTypeCodes=callTypeCodes.substring(0, callTypeCodes.length()-1);
        }
        
        if (type == 0) {//所有记录
            if(!"".equals(callTypeCodes)){
                map.put("callTypeId", callTypeCodes);
            }else{
                map.put("callTypeId", "1,2,3,4,5,6,7,8,9");
            }
        } else if (type == 1) {//通话记录
            if(!"".equals(callTypeCodes)){
                map.put("callTypeId", callTypeCodes);
            }else{
                map.put("callTypeId", "1,2,3,6");
            }
        } else if (type == 2) {//座谈记录
            if(!"".equals(callTypeCodes)){
                map.put("callTypeId", callTypeCodes);
            }else{
                map.put("callTypeId", "4");
            }
        } else if (type == 3) {//拜访
            map.put("callTypeId", "5");

        } else if (type == 4) {//短信记录
            if(!"".equals(callTypeCodes)){
                map.put("callTypeId", queryBean.getCallTypeId());
            }else if(queryBean!=null&&queryBean.getCallTypeId()!=0){
                map.put("callTypeId", queryBean.getCallTypeId());
            }else{
                map.put("callTypeId", "7,8");
            }
            if(queryBean!=null&&queryBean.getContent()!=null){
                map.put("content", StringUtil.ReplaceSQLChar(queryBean.getContent().trim()));
            }
        } else if (type == 5) {//彩信记录
            map.put("callTypeId", "9");
            if(queryBean!=null&&queryBean.getStatus()!=null&&!queryBean.getStatus().equals("无")){
                map.put("status", queryBean.getStatus());
            }
            if(queryBean!=null&&queryBean.getMmsTitle()!=null){
                map.put("mmsTitle", StringUtil.ReplaceSQLChar(queryBean.getMmsTitle().trim()));
            }
        }
        map.put("startRow", startRow);
        map.put("endRow", endRow);
        recordExportList = recordInfoDao.queryRecordInfosByMap(map);

        if (type == 0) {//判断当前type是什么，如果是所有记录就把type=呼入，呼出，未接换成通话记录
            int nowPageSize = 0;
            for (int i = 0; i < recordExportList.size(); i++) {
                nowPageSize++;
                if (recordExportList.get(i).getCallTime() != null&& recordExportList.get(i).getCallTime() != 0) {
                    recordExportList.get(i).setStrCallTime(changTime(recordExportList.get(i).getCallTime() + ""));//修改时长格式
                }
//                if (recordExportList.get(i).getCallTypeName() != null) {
//                    if(recordExportList.get(i).getCallTypeName().equals(EnumRecord.MISSED_CALLS.getValue())|| recordExportList.get(i).getCallTypeName().equals(EnumRecord.UNREAD_MESSAGE.getValue())){
//                        recordExportList.get(i).setCallTypeName(EnumRecord.CALL_IN.getValue());
//                    }
//                } else if (recordExportList.get(i).getCallTypeName() != null){
//                    if(recordExportList.get(i).getCallTypeName().equals(EnumRecord.RECEIVE_SMS.getValue())|| recordExportList.get(i).getCallTypeName().equals(EnumRecord.SEND_SMS.getValue())) {
//                        recordExportList.get(i).setCallTypeName(EnumRecord.SMS.getValue());
//                    }
//                }
            }
        } else {//修改时长格式
            for (int i = 0; i < recordExportList.size(); i++) {
                if (recordExportList.get(i).getCallTime() != null&& recordExportList.get(i).getCallTime() != 0) {
                    recordExportList.get(i).setStrCallTime(changTime(recordExportList.get(i).getCallTime() + ""));//修改时长格式
                }
            }
        }
        return recordExportList;
    }
    
    /**
     * 导出录音
     * @param str
     * @return 
     */
    public String exportFile(List<RecordExportBean> list,String data){
        try {
        HttpServletRequest req = ServletActionContext.getRequest();
        int isfileCount=list.size();//记录有效文件
        int isexportCount=0;//记录成功条数
        String ExportfileName="";//设置导出zip名
        String[] s = new String[list.size()];
        String[] zfileNames = new String[list.size()];//存放导出文件名
        Map<String,CrmTemplateField> feildMap = getAllFeildType(); //所有的模板字段
        Map<Integer,String> userNameMap = getAllUserName(); //所有的用户名
        
        ExportfileName=req.getRealPath("/")+"Records/"+df4.format(new Date())+this.getUserLoginInfo().getUserId()+".zip";
        for(int i=0;i<list.size();i++){
            Integer userId = getUserLoginInfo().getUserId();
            CustomerExportBar bar;
            if(customerExportBar.containsKey(userId)){
                bar = customerExportBar.get(userId);
            }else{
                bar = new CustomerExportBar();
                bar.setIsRun(1);
                bar.setIsStop(0);
            }
            if(bar.getIsRun().equals(1) && bar.getIsStop().equals(0)){
                bar.setCuurRow(i+1);  //当前执行的行数
            }else{
                bar.setCuurRow(0);
                break;
            }
            customerExportBar.put(userId, bar);
            
          
            if(list.get(i).getFileName()==null||list.get(i).getFileName().equals("")&&list.get(i).getFilePath()==null||list.get(i).getFilePath().equals("")){//文件名和文件路径
                isfileCount-=1;
                continue;
            }else if(list.get(i).getFileSize()==null||list.get(i).getFileSize().equals("0")){//如果大小等于空或者等于0则是错误的录音记录，不导出
                isfileCount-=1;
                continue;
            }
                s[i]=list.get(i).getFilePath()+"/"+list.get(i).getFileName();
                zfileNames[i]="";//设置文件名
                if(data !=null && data !=""){
                    String[] codes = data.split(":");
                    for (String code : codes) {
                        if(!code.contains(","))  {//基础字段
                            if(code.equals("age")){
                                Integer age = (Integer)ReflectorUtil.getPropertyValue(list.get(i), "age");
                                if(age != null){
                                    zfileNames[i] +=  age+"_";
                                }
                            }else if(code.equals("province")){//省份
                                String pro = (String)ReflectorUtil.getPropertyValue(list.get(i), code);
                                if(pro != null && !pro.equals("")){
                                    zfileNames[i] += codetableService.getProvinceByCode(pro).getShortName()+"_";
                                }
                            }else if(code.equals("city")){//城市
                                String city = (String)ReflectorUtil.getPropertyValue(list.get(i), code);
                                if(city != null && !city.equals("")){
                                    zfileNames[i] += codetableService.getCityByCode(city).getShortName()+"_";
                                }
                            }else if("birthday,lastContactDate,custCreateDate,custUpdateDate".contains(code)){//Date类型
                                Date date =(Date)ReflectorUtil.getPropertyValue(list.get(i), code);
                                if(date != null){
                                    zfileNames[i] += DateUtil.getDateToString(date)+"_";
                                }
                            }else if(code.equals("isReceiveSms")){//是否愿意接收短信
                                Integer flag = (Integer)ReflectorUtil.getPropertyValue(list.get(i), "isReceiveSms");
                                if(flag != null){
                                    if(flag.equals(1)) zfileNames[i] += "是"+"_";
                                    else zfileNames[i] +=  "否"+"_";
                                }else{
                                    zfileNames[i] += "是"+"_";
                                }
                            }else if("custCreateUser,custUpdateUser".contains(code)){//新建者、修改者
                                Integer id = (Integer)ReflectorUtil.getPropertyValue(list.get(i), code);
                                if(id != null) {
                                    if(userNameMap.containsKey(id))  zfileNames[i] += userNameMap.get(id);
                                }
                            }else{
                                String content = (String)ReflectorUtil.getPropertyValue(list.get(i), code);
                                if(content != null && !content.equals("")) zfileNames[i] += content+"_";
                            }
                        }
                        else{
                            String[] cd = code.split(",");
                            int n = Integer.valueOf(cd[0]);
                            if(n<1) {//自定义字段
                                zfileNames[i] = changeType(zfileNames[i],cd[3],list.get(i),feildMap);
                            }else{//扩展字段
                                zfileNames[i] = changeType(zfileNames[i],cd[2],list.get(i),feildMap);
                            }
                        }
                    }
                   
                    
                }
                if(StringUtil.isNotEmpty(list.get(i).getFileName())){
                    zfileNames[i]+=list.get(i).getFileName().substring(0, list.get(i).getFileName().lastIndexOf("."))+list.get(i).getFileName().substring(list.get(i).getFileName().lastIndexOf("."), list.get(i).getFileName().length());
                }
                
               
        }
        OutputStream os = new BufferedOutputStream( new FileOutputStream( ExportfileName ) );
        ZipOutputStream zos = new ZipOutputStream( os );
        
        byte[] buf = new byte[8192];
        int len;
        int goout = 0;
        for(String filename:s){
            if(goout==s.length){
                break;
            }
            goout++;
            if(StringUtil.isEmpty(filename)){
                continue;
            }
            File file=new File(filename);
            if(!file.isFile()){
                continue;
            }
            ZipEntry ze = new ZipEntry(zfileNames[goout-1] );
            
            zos.putNextEntry(ze);
            BufferedInputStream bis = new BufferedInputStream( new FileInputStream( file ) );
            while((len = bis.read(buf))>0){
                zos.write(buf,0,len);
            }
            zos.setEncoding("gbk");
            zos.closeEntry();
            isexportCount+=1;
        }
        zos.close();
        return isfileCount+","+isexportCount;
        //opeventLogService.addOpeventLog(EnumRecord.RECORD_NAME.getValue(),EnumRecord.EXPORT_RECS.getValue(), 1, "");
        } catch (Exception e) {
            //opeventLogService.addOpeventLog(EnumRecord.RECORD_NAME.getValue(),EnumRecord.EXPORT_RECS.getValue(), 0, "");
            log.error("RecordInfoAction exportRecs action error:"+e.getMessage(), e);
            return null;
        }
    }
    
    /**
     * 切换类型
     * @param contents
     * @param wd
     * @param crmCustomer
     * @param feildMap
     */
    public String changeType(String contents,String wd,RecordExportBean crmCustomer,Map<String,CrmTemplateField> feildMap){
        for(String feildName : feildMap.keySet()){
            if(feildName.equalsIgnoreCase(wd)){
                CrmTemplateField feild = feildMap.get(feildName);
                String feildType = feild.getTemplateFieldType();
                if("MultipleSelect,Select,Text,TextArea".contains(feildType)){
                    String tt = (String)ReflectorUtil.getPropertyValue(crmCustomer, wd);
                    if(tt!=null && !tt.equals("")){
                        contents += tt+"_";
                    }
                }else if(feildType.equals("YesNo")){
                    String tt = (String)ReflectorUtil.getPropertyValue(crmCustomer, wd);
                    if(tt!=null && !tt.equals("")){
                        if(tt.equalsIgnoreCase("yes")) contents += "是"+"_";
                        else contents += "否"+"_";
                    }else contents += "否"+"_";
                }else if(feildType.equals("Date")){
                    Date date = (Date)ReflectorUtil.getPropertyValue(crmCustomer, wd);
                    if(date != null){
                        contents += DateUtil.getDateToString(date)+"_";
                    }
                }else if(feildType.equals("Number")){
                   Double d = (Double)ReflectorUtil.getPropertyValue(crmCustomer, wd);
                   if(d != null){
                       String ss = (String)TypeUtil.changeType(d,String.class);
                       if(ss.endsWith(".0")) contents += ss.substring(0,ss.length()-2)+"_";
                       else contents += ss+"_";
                   }
                }
            }
        }
        return contents;
    }
    
    /** 
     * 所有用户名
    */
    public Map<Integer,String> getAllUserName(){
        Map<Integer,String> userMap = new HashMap<Integer,String>();
        List<SysUser> users = sysUserDao.getAllData(true);
        if(users != null && users.size()>0){
            for (SysUser sysUser : users) {
                userMap.put(sysUser.getUserId(), sysUser.getUserName());
                }
            }
        return userMap;
    }
    
   /**
     * 导出客户基础字段
     * @return
     * @see com.banger.mobile.facade.record.RecordInfoService#initParameter()
     */
    public Map<String, String> initParameter(){
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("customerName", "客户姓名");
        map.put("sex", "性别");
        map.put("customerTitle", "称谓");
        map.put("customerNo", "客户编号");
        map.put("customerTypeName", "客户类型");
        map.put("customerIndustryName", "所处行业");
        map.put("custIdCard", "身份证");
        map.put("birthday", "生日");
        map.put("company", "单位");
        map.put("age", "年龄");
        map.put("custRemark", "客户简介");
        map.put("province", "省份");
        map.put("city", "城市");
        map.put("address", "联系地址");
        map.put("mobilePhone1", "手机1");
        map.put("mobilePhone1Remark", "手机1备注");
        map.put("mobilePhone2", "手机2");
        map.put("mobilePhone2Remark", "手机2备注");
        map.put("phone", "固话");
        map.put("phoneExt", "固话分机");
        map.put("fax", "传真");
        map.put("faxExt", "传真分机");
        map.put("email", "邮箱");
        map.put("lastContactDate", "最后联系时间");
//        map.put("isReceiveSms", "是否愿意接收短信");
        map.put("custCreateUser", "新建者");
        map.put("custCreateDate", "新建时间");
        map.put("custUpdateUser", "最后修改者");
        map.put("custUpdateDate", "修改时间");
        return map;
    }
    
    /**
     * 获得所有的自定义字段
     * @return
     */
    public Map<String,CrmTemplateField> getAllFeildType(){
        Map<String,CrmTemplateField> fcMap= new HashMap<String,CrmTemplateField>();
        List<CrmTemplateField> feilds = crmTemplateService.getAllTemplateFields();
        if(feilds !=null && feilds.size()>0){
            for (CrmTemplateField field : feilds) {
                fcMap.put(field.getFieldName(), field);
            }
        }
        return fcMap;
    } 
    
    /**
    * 获得登录信息
    */
    private IUserInfo getUserLoginInfo() {
        HttpServletRequest req = ServletActionContext.getRequest();
        HttpSession session = req.getSession();
        return (IUserInfo) session.getAttribute("LoginInfo");
    }

    /**
     * 时长转换成HH:mm:ss
     * @param str
     * @return
     */
    public String changTime(String str) {
        int l = Integer.parseInt(str);
        String H = "" + l / 3600;
        if (H.length() < 2) H = "0" + H;l = l % 3600;
        String M = "" + l / 60;
        if (M.length() < 2) M = "0" + M;l = l % 60;
        String S = "" + l;
        if (S.length() < 2) S = "0" + S;
        return H + ":" + M + ":" + S;
    }

    public PageUtil<RecordInfoBean> getRecordInfoList() {
        return recordInfoList;
    }

    public void setRecordInfoList(PageUtil<RecordInfoBean> recordInfoList) {
        this.recordInfoList = recordInfoList;
    }

    public List<RecordExportBean> getRecordExportList() {
        return recordExportList;
    }

    public void setRecordExportList(List<RecordExportBean> recordExportList) {
        this.recordExportList = recordExportList;
    }

    public void setCrmCustomerService(CrmCustomerService crmCustomerService) {
        this.crmCustomerService = crmCustomerService;
    }

    /**
     * 通过码得到对像
     * @param recordNo
     * @return
     */
    public PhoneRecordBean getRecordByNo(String recordNo){
    	return this.recordInfoDao.getRecordInfoByNo(recordNo);
    }
    
    public boolean isExistRecordByNo(String recordNo)
    {
    	return this.recordInfoDao.isExistRecordByNo(recordNo);
    }
    /**
     * 查询重复的通话记录
     * @param parameters
     * 键值说明（键为空则不查）
     * 用户ID：userId  
     * 开始时间区间：startDate1 and startDate2
     * 振铃时间区间：ringTime1 and ringTime2
     * 通话类型： callType
     * 号码：number
     * @return
     */
    public List<RecordInfo> getSameRecodInfoList(Map<String, Object> map){
    	 return this.recordInfoDao.getSameRecodInfoList(map);
    }
    
    /**
     * 得到最近10条联系记录
     * @param userId
     * @return
     */
    public List<TalkRecordInfo> getRecentlyTalkRecord(Integer userId){
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("userId",userId);
    	return this.recordInfoDao.getRecentlyTalkRecord(map);
    }
    
    /** 
     * 新建或修改客户 关联记录
     * @param recordId 记录id
     * @param customerId 客户id
     * @param flag  是否是座谈记录 true:是 false:否 
     */
    public void relationRecord(Integer recordId,Integer customerId,Boolean flag){
        BaseCrmCustomer baseCrmCustomer=new BaseCrmCustomer();
        baseCrmCustomer=crmCustomerService.getCrmCustomerById(customerId);
        PhoneConfig phoneConfig = phoneConfigService.query(this.getUserLoginInfo().getUserId());
        this.updateRelationRecord(baseCrmCustomer, phoneConfig, recordId, customerId, flag);
    }
    
    /** 
     * 导入客户 关联记录  (暂时没用)
     * @param customerId 客户id
     * @param phoneConfig 区号
     */
    public void relationRecord(BaseCrmCustomer baseCrmCustomer,PhoneConfig phoneConfig){
        this.updateRelationRecord(baseCrmCustomer, phoneConfig, 0, baseCrmCustomer.getCustomerId(), false);
    }
    
    /** 
     * 根据客户信息 关联记录
     * @param baseCrmCustomer 
     * @param recordId 记录id
     * @param phoneConfig 区号
     * @param customerId 客户id
     * @param flag  是否是座谈记录 true:是 false:否 
     */
    public void updateRelationRecord(BaseCrmCustomer baseCrmCustomer,PhoneConfig phoneConfig,Integer recordId,Integer customerId,Boolean flag){
        Map<String, Object> map=new HashMap<String, Object>();
        String phone="";
        if(baseCrmCustomer!=null&&baseCrmCustomer.getDefaultPhoneType().equals(1)){
            phone+=baseCrmCustomer.getMobilePhone1();
        }else if(baseCrmCustomer!=null&&baseCrmCustomer.getDefaultPhoneType().equals(2)){
            phone+=baseCrmCustomer.getMobilePhone2();
        }else if(baseCrmCustomer!=null&&baseCrmCustomer.getDefaultPhoneType().equals(3)){
            phone+=baseCrmCustomer.getPhone();
        }else if(baseCrmCustomer!=null&&baseCrmCustomer.getDefaultPhoneType().equals(4)){
            phone+=baseCrmCustomer.getFax();
        }
        if(flag){//根据客户电话 匹配记录 并关联
            if(recordId==0){
                map.put("customerId",customerId);
                map.put("customerName",baseCrmCustomer.getCustomerName());
                if(StringUtil.isNotEmpty(baseCrmCustomer.getMobilePhone1()))
                map.put("phone1", baseCrmCustomer.getMobilePhone1());
                if(StringUtil.isNotEmpty(baseCrmCustomer.getMobilePhone2()))
                map.put("phone2", baseCrmCustomer.getMobilePhone2());
                if(StringUtil.isNotEmpty(baseCrmCustomer.getPhone()))
                map.put("phone3", baseCrmCustomer.getPhone());
                map.put("temphone", phoneConfig.getCityCode()+""+baseCrmCustomer.getPhone());
                map.put("temphone1", this.splitPhoneByConfig(baseCrmCustomer.getPhone(), phoneConfig));
                if(StringUtil.isNotEmpty(baseCrmCustomer.getFax()))
                map.put("phone4", baseCrmCustomer.getFax());
                this.updateRecordByPhones(map);
            }else{
                this.updateCustomerIdByRecId(recordId, customerId, baseCrmCustomer.getCustomerName(),phone);
            }
        }else if(!flag){
            if(recordId==0){
                map.put("customerId",customerId);
                map.put("customerName",baseCrmCustomer.getCustomerName());
                if(StringUtil.isNotEmpty(baseCrmCustomer.getMobilePhone1()))
                map.put("phone1", baseCrmCustomer.getMobilePhone1());
                if(StringUtil.isNotEmpty(baseCrmCustomer.getMobilePhone2()))
                map.put("phone2", baseCrmCustomer.getMobilePhone2());
                if(StringUtil.isNotEmpty(baseCrmCustomer.getPhone()))
                map.put("phone3", baseCrmCustomer.getPhone());
                map.put("temphone", phoneConfig.getCityCode()+""+baseCrmCustomer.getPhone());
                map.put("temphone1", this.splitPhoneByConfig(baseCrmCustomer.getPhone(), phoneConfig));
                if(StringUtil.isNotEmpty(baseCrmCustomer.getFax()))
                map.put("phone4", baseCrmCustomer.getFax());
                this.updateRecordByPhones(map);
            }else{
                this.updateCustomerIdByPhone(phone,customerId,baseCrmCustomer.getCustomerName());
            }
        }
        baseCrmCustomer=crmCustomerService.getCrmCustomerById(customerId);
        //更新客户最近联系时间
        this.updateCusLastDate(customerId);
    }
    
    private void updateCusLastDate(int customerId){
        //根据客户id查询最近的一条联系记录
        RecordTemp recordTemp = this.recordInfoDao.queryLastRecByCusId(customerId);
        if(recordTemp!=null){
            this.crmCustomerService.updateLastContactDate(customerId, recordTemp.getStartDate(), recordTemp.getCallTypeName());
        }else{
            this.crmCustomerService.updateLastContactDate(customerId, null, "");
        }
    }
    
    /**
     * 根据电话号码查询相匹配的记录并关联
     */
    public void updateRecordByPhones(Map<String, Object> map){
        recordInfoDao.updateRecordByPhones(map);
    }
    
    /**
     * 根据记录id修改客户id
     * @param id 记录id
     * @param customerId 客户id
     */
    public void updateCustomerIdByRecId(int id, int customerId, String customerName,String phone) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id", id);
        parameters.put("customerId", customerId);
        parameters.put("phone", phone);
        if (customerName != null) {
            parameters.put("customerName", customerName);
        }
        recordInfoDao.updateCustomerIdByRecId(parameters);
    }
    
    //-------------以下是所有涉及关联的
    
    /**
     * 取消关联
     * @param customerId 客户id
     */
    public void updateCustomerIdByCusId(int customerId,String phone) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("customerId", customerId);
        parameters.put("phone", phone);
        recordInfoDao.updateCustomerIdByCusId(parameters);
        this.updateCusLastDate(customerId);
    }
    
    /**
     * 根据记录id取消关联
     */
    public void updateCustomerIdByRecIdForRelation(int recordId){
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("recordId", recordId);
        RecordDetail recordDetail = this.recordInfoDao.getRecordInfoById(recordId);
        recordInfoDao.updateCustomerIdByRecIdForRelation(parameters);
        this.updateCusLastDate(recordDetail.getCustomerId());
    }

    /**
     * 根据电话修改所有客户信息
     */
    public void updateCustomerIdByPhone(String phone, int customerId, String customerName){
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("phone", phone);
        parameters.put("customerId", customerId);
        if (customerName != null) {
            parameters.put("customerName", customerName);
        }
        recordInfoDao.updateCustomerIdByPhone(parameters);
        this.updateCusLastDate(customerId);
    }
    
    
    /**
     * 关联联系记录前查询待关联的所有联系记录
     */
    public void relatRec(List<CrmCustomer> cuslist){
        String phones = "";
        
        Set<String> tels =new HashSet();
        for(CrmCustomer customer:cuslist){//循环客户 拼接号码1
            if(StringUtil.isNotEmpty(customer.getMobilePhone1())){
                if(tels.contains(customer.getMobilePhone1())){
                    tels.add(customer.getMobilePhone1());
                }
                tels.add(customer.getMobilePhone1());
            }
                
            if(StringUtil.isNotEmpty(customer.getMobilePhone2())){//循环客户 拼接号码2
                if(tels.contains(customer.getMobilePhone2())){
                    tels.add(customer.getMobilePhone2());
                }
                tels.add(customer.getMobilePhone2());
            }
            
            if(StringUtil.isNotEmpty(customer.getPhone())){//循环客户 拼接固话
                if(tels.contains(customer.getPhone())){
                    tels.add(customer.getPhone());
                }
                tels.add(customer.getPhone());
            }
            
            if(StringUtil.isNotEmpty(customer.getFax())){//循环客户 拼接传真
                if(tels.contains(customer.getFax())){
                    tels.add(customer.getFax());
                }
                tels.add(customer.getFax());
            }
        }
        
        for(String t:tels){ //将号码集合循环拼接成字符串
            phones += "'"+t+"',";
        } 
        
        //去除最后一个逗号
        if(phones.length()>0)
            phones=phones.substring(0, phones.length()-1);
        //根据所有号码字符串查询相关联系记录
        List<RecordTemp> templist = this.recordInfoDao.getRecByPhone(phones);
        Map<String, List<RecordTemp>> map = new HashMap<String, List<RecordTemp>>(); 
        for(RecordTemp temp:templist){
            String key = temp.getRemotePhone();
            if(!map.containsKey(key))
                map.put(key, new ArrayList<RecordTemp>());
            map.get(key).add(temp);
        }
        this.relateForUpdate(cuslist,map);
        
    }
    
    /**
     * 批量关联 更新客户和联系记录
     */
    private void relateForUpdate(List<CrmCustomer> cuslist, Map<String, List<RecordTemp>> map){
        for(CrmCustomer customer:cuslist){
            if(map.containsKey(customer.getMobilePhone1())){
                List<RecordTemp> tlist = map.get(customer.getMobilePhone1());
                setUpdateRecSQL(tlist,customer,map);
            }
            if(map.containsKey(customer.getMobilePhone2())){
                List<RecordTemp> tlist = map.get(customer.getMobilePhone2());
                setUpdateRecSQL(tlist,customer,map);
            }
            if(map.containsKey(customer.getPhone())){
                List<RecordTemp> tlist = map.get(customer.getPhone());
                setUpdateRecSQL(tlist,customer,map);
            }
            if(map.containsKey(customer.getFax())){
                List<RecordTemp> tlist = map.get(customer.getFax());
                setUpdateRecSQL(tlist,customer,map);
            }
        }
        
        
    }
    
    private void setUpdateRecSQL(List<RecordTemp> tlist,CrmCustomer customer,Map<String, List<RecordTemp>> map){
        String rsql = "";
        String csql = "";
        List<String> slit = new ArrayList<String>();
        List<String> rlit = new ArrayList<String>();
        RecordTemp oldLastTem = new RecordTemp();
        for(RecordTemp temp : tlist){
            rsql = "";
            rsql += "CUSTOMER_ID = "+customer.getCustomerId()+",";
            rsql += "CUSTOMER_STR = '";
            if(!StringUtil.isEmpty(customer.getCustomerName())){
                rsql += customer.getCustomerName()+"_";
            }
            if(!StringUtil.isEmpty(customer.getCustomerNamePinyin())){
                rsql += customer.getCustomerNamePinyin()+"_";
            }
            if(!StringUtil.isEmpty(customer.getMobilePhone1())){
                rsql += customer.getMobilePhone1()+"_";
            }
            if(!StringUtil.isEmpty(customer.getMobilePhone2())){
                rsql += customer.getMobilePhone2()+"_";
            }
            if(!StringUtil.isEmpty(customer.getPhone())){
                rsql += customer.getPhone()+"_";
            }
            if(!StringUtil.isEmpty(customer.getFax())){
                rsql += customer.getFax()+"_";
            }
            rsql += "' WHERE RECORD_INFO_ID = "+temp.getRecordInfoId();
            
            slit.add(rsql);
            
            if(oldLastTem.getStartDate()==null&&temp!=null){
                oldLastTem = temp;
            }else if(temp.getStartDate().after(oldLastTem.getStartDate())){
                oldLastTem = temp;
            }
        }
        
        csql="";
        if(customer.getLastContactDate()==null&&oldLastTem!=null){//当新增客户的最近联系时间为空 且 联系记录不为空时
            csql += "LAST_CONTACT_DATE = '"+df.format(oldLastTem.getStartDate())+"',LAST_CONTACT_TYPE = '"+oldLastTem.getCallTypeName()+"' WHERE CUSTOMER_ID = "+customer.getCustomerId();
        }else{//当新增客户的最近联系时间为空 且 联系记录也为空时
            csql += "LAST_CONTACT_DATE = '',LAST_CONTACT_TYPE = '' WHERE CUSTOMER_ID = "+customer.getCustomerId();
        }
        
        this.recordInfoDao.updateRecord(slit);//执行上述拼接的修改联系记录的sql
        this.recordInfoDao.updateCustomer(rlit);//执行上述拼接的修改客户
    }
    
    
    //------------ 以上是关联联系记录
    
    
    

    /**
     * 根据客户id查询最近的一条联系记录
     */
    public RecordDetail getRecordInfoByCustomerId(Integer customerId){
        Map<String, Object> map = new HashMap<String, Object>();
        RecordDetail recordDetail=new RecordDetail();
        map.put("customerId", customerId);
        recordDetail=recordInfoDao.getRecordInfoByCustomerId(map);
        if(recordDetail!=null&&recordDetail.getCallTime()!=null){
            recordDetail.setCallTime(changTime(recordDetail.getCallTime()));
        }
        return recordDetail;
    }
    
    /**
     * 根据phone查询最近的一条联系记录
     */
    public RecordDetail getRecordInfoByCustomerPhone(String phone){
        Map<String, Object> map = new HashMap<String, Object>();
        RecordDetail recordDetail=new RecordDetail();
        map.put("phone", phone);
        recordDetail=recordInfoDao.getRecordInfoByCustomerId(map);
        if(recordDetail!=null&&recordDetail.getCallTime()!=null){
            recordDetail.setCallTime(changTime(recordDetail.getCallTime()));
        }
        return recordDetail;
    }
    
    /**
     * 根据recordID查询该条记录是否有权限 
     * @param arr[] 部门id集合 null 表示客户经理 
     * @param recordId 录音id
     * @return true 有 false 无
     */
    public Boolean getRecordPermissionById(Integer recordId,Integer[] arr){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("recordId",recordId);
        if(arr!=null){
            Integer[] userIds =deptFacadeService.getInChargeOfDeptUserIds(this.getUserLoginInfo().getUserId());
            Set<Integer> set = new HashSet<Integer>();
            String str="";
            set.add(this.getUserLoginInfo().getUserId());
            for (int k : userIds) {
                set.add(k);
            }
            Integer[] Ids = new Integer[set.size()];
            Iterator<Integer> it = set.iterator();
            int j = 0;
            while (it.hasNext()) {
                Ids[j] = it.next();
                j++;
            }
            for (int i = 0; i < Ids.length; i++) {
                if (i < Ids.length - 1)
                    str += Ids[i] + ",";
                else
                    str += Ids[i];
            }
            map.put("userIds",str);
        }else{
            map.put("userIds", this.getUserLoginInfo().getUserId());
        }
        return recordInfoDao.getRecordPermissionById(map);
    }
    
    /**
     * 根据配置组装号码
     * @param inNumber
     * @param config
     * @return
     */
    public String splitPhoneByConfig(String inNumber,PhoneConfig config){
        if (inNumber != null) {
            if (this.isMobileNumber(inNumber)) //判断是否为手机号码
            {
                return inNumber;
            } else if (inNumber.length() > 6){ //是否为固话
                if (config.getCityCode() != null && !"".equals(config.getCityCode())) //判断是否为本地固话
                {
                    int cityLen = config.getCityCode().length();
                    if (inNumber.substring(0, cityLen).equals(config.getCityCode()))
                        return inNumber.substring(cityLen);
                }
                return inNumber;
            }
            return inNumber;
        } else {
            return inNumber;
        }
    }
    
    /**
     * 查询归属客户Id集合
     */
    public String getIdsByBelongUserId(Integer userId){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("belongUserId", userId);
        return recordInfoDao.getIdsByBelongUserId(map);
    }
     
    /**
     * 查询包含客户Id集合
     */
    public String getIdsByCustomer(Map<String, Object> parameters){
        return recordInfoDao.getIdsByCustomer(parameters);
    }
    
    /**
     * 查询共享客户Id集合
     */
    public String getIdsShareUerId(Map<String, Object> parameters){
        List<BaseCrmCustomer> baseCrmCustomer = new ArrayList<BaseCrmCustomer>();
        baseCrmCustomer = recordInfoDao.getIdsShareUerId(parameters);
        String ids="";
        for(int i=0;i<baseCrmCustomer.size();i++){
            if(i == baseCrmCustomer.size()-1){
                ids+=baseCrmCustomer.get(i).getCustomerId();
            }else{
                ids+=baseCrmCustomer.get(i).getCustomerId()+",";
            }
        }
        return ids;
    }
    

    /**
     * 根据客户id拼接客户冗余字段
     * @param customerId
     * @param recCustomerName
     * @param recRemotePhone
     * @return
     */
    public String getCustomerStr(Integer customerId,String recCustomerName,String recRemotePhone){
        String customerStr = "";
        if(StringUtil.isNotEmpty(recCustomerName)){
            customerStr += recCustomerName+"_";
        }
        if(StringUtil.isNotEmpty(recRemotePhone)){
            customerStr += recRemotePhone;
        }
        if(customerId != 0){
            BaseCrmCustomer baseCrmCustomer = new BaseCrmCustomer();
            baseCrmCustomer = crmCustomerService.getCrmCustomerById(customerId);
            if(baseCrmCustomer!=null){
                if(!StringUtil.isEmpty(baseCrmCustomer.getCustomerName())){
                    customerStr += baseCrmCustomer.getCustomerName()+"_";
                }
                if(!StringUtil.isEmpty(baseCrmCustomer.getCustomerNamePinyin())){
                    customerStr += baseCrmCustomer.getCustomerNamePinyin()+"_";
                }
                if(!StringUtil.isEmpty(baseCrmCustomer.getMobilePhone1())){
                    customerStr += baseCrmCustomer.getMobilePhone1()+"_";
                }
                if(!StringUtil.isEmpty(baseCrmCustomer.getMobilePhone2())){
                    customerStr += baseCrmCustomer.getMobilePhone2()+"_";
                }
                if(!StringUtil.isEmpty(baseCrmCustomer.getPhone())){
                    customerStr += baseCrmCustomer.getPhone()+"_";
                }
                if(!StringUtil.isEmpty(baseCrmCustomer.getFax())){
                    customerStr += baseCrmCustomer.getFax()+"_";
                }
            }
        }
        return customerStr;
    }

    public PhoneConfigService getPhoneConfigService() {
        return phoneConfigService;
    }

    public void setPhoneConfigService(PhoneConfigService phoneConfigService) {
        this.phoneConfigService = phoneConfigService;
    }    
    
    
    /**
     * 录音信息及分页(任务管理模块点击进入)
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<RecordInfoBean> getTskRecordInfoPage(Map<String, Object> parameters, Page page){
    	if(deptFacadeService.isInChargeOfDepartment()){
    		parameters.put("userIds", deptFacadeService.getUserIdsBelongToManager());    		
    	}else{
    		parameters.put("userIds", String.valueOf(this.getUserLoginInfo().getUserId()));
    	}    	
    	recordInfoList = recordInfoDao.getTskRecordInfoPage(parameters, page);
        
    	if(recordInfoList.getItems()!=null&&recordInfoList.getItems().size()!=0){ 
	        for(int i = 0;i < recordInfoList.getItems().size();i++){
	        	String callTime = recordInfoList.getItems().get(i).getCallTime();
	        	if (callTime != null&& callTime.length() != 0) {
	                recordInfoList.getItems().get(i).setCallTime(changTime(callTime));//修改时长格式
	            }
	        }
    	}
        return recordInfoList;
    }

    /**
     * 获得某一条日程的电话联系记录当天最近的记录
     * @param paramMap
     * @return
     */
    public Integer getRecentlyRecordForSchedule(Map<String, Object> paramMap){
        return recordInfoDao.getRecentlyRecordForSchedule(paramMap);
    }

    /**
     * 获取通话记录数
     * @param customerId
     * @return
     */
    @Override
    public Integer getRecordCountByCustomerId(Integer customerId) {
        return recordInfoDao.getRecordCountByCustomerId(customerId);
    }
}
