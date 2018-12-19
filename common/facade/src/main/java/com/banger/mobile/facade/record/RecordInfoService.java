/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :录音信息业务接口
 * Author     :zhangxiang
 * Version    :1.0
 * Create Date:May 3, 2012
 */
package com.banger.mobile.facade.record;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.base.customer.BaseCrmCustomer;
import com.banger.mobile.domain.model.customer.CrmCustomer;
import com.banger.mobile.domain.model.phoneConfig.PhoneConfig;
import com.banger.mobile.domain.model.record.PhoneRecordBean;
import com.banger.mobile.domain.model.record.RecordDetail;
import com.banger.mobile.domain.model.record.RecordExportBean;
import com.banger.mobile.domain.model.record.RecordInfo;
import com.banger.mobile.domain.model.record.RecordInfoBean;
import com.banger.mobile.domain.model.record.RecordQueryBean;
import com.banger.mobile.domain.model.record.TalkRecordInfo;

/**
 * @author huangkai
 * @version $Id: RecordService.java,v 0.1 May 3, 2012 1:39:05 PM huangkai Exp $
 */
public interface RecordInfoService {
    
    /**
     * 添加一条录音信息
     * @param recordInfo
     */
    public void addRecordInfo(RecordInfo recordInfo);

    /**
     * 删除录音信息
     * @param id
     */
    public void deleteRecordInfo(int id);
    
    /**
     * 修改录音信息
     * @param recordInfo
     */
    public void updateRecordInfo(RecordInfo recordInfo);
    
    /**
     * 修改录音是否有效
     * @param recordInfo
     */
    public void updateRecordCanceled(int id,int isCanceled);

    /**
     * 根据id得到录音信息
     * @param id
     * @return
     */
    public RecordDetail getRecordInfoById(int id);

    /**
     * 录音信息分页
     * @param parameters
     * @param currentPage
     * @param pageSize
     * @return
     */
    public PageUtil<RecordInfoBean> getRecordInfoPage(Map<String, Object> map, Page page ,int type,RecordQueryBean queryBean,String callTypeCodes);

    /**
     * 归档录音信息分页
     * @param parameters
     * @param currentPage
     * @param pageSize
     * @return
     */
    public PageUtil<RecordInfoBean> getArchiveRecordInfoPage(Map<String, Object> parameters, Page page);
    
    /**
     * 根据沟通进度的ID删除沟通进度ID值 
     * @param Id
     * @return boolean
     */
    public boolean deleteCommProgressId(int CommProgressId);
    
    /**
     * 客户联系记录信息接口
     * @param CustomerId
     * @return list
     */
    public PageUtil<RecordInfoBean> getCustomerRecord(Map<String, Object> parameterMap, Page page);
    
    /**
     * 客户联系记录总数接口
     * @param CustomerId
     */
    public int queryCustomerCallInfoCountByCusId(int customerId);
    
    /**
     * 根据归档时间查询可归档的录音
     */
    public List<RecordInfo> queryRecordToGd(Map<String, Object> parameters);
    
    /**
     * 根据id查询全字段信息
     * @param Id 
     */
    public RecordInfo queryAllById(int Id);
    
    /**
     * 根据流水号record_no查询全字段信息
     * @param no 
     */
    public List<RecordInfo> queryAllByNo(String no);
    
    /**
     * 根据id修改录音是否已读
     * @param Id
     */
    public void updateRecIsRead(int id);
    
    /**
     * 修改record编辑信息
     * @param updateRecordConnect
     */
    public void updateRecordConnect(RecordDetail recordDetail);
    
    /**
     * 导出Excel
     * @param recordInfoDao
     */
    public HSSFWorkbook RecordReportDownExcel(List<RecordInfoBean> rlist);
    
    /**
     * 按条件返回结果
     * @param map
     */
    public List<RecordInfoBean> getRecordInfo(Map<String, Object> parameters);
    
    /**
     * 按条件返回归档结果
     * @param map
     */
    public List<RecordInfoBean> getRecordArchiveInfo(Map<String, Object> parameters);
    
    /**
     * 通过码得到对像
     * @param recordNo
     * @return
     */
    public PhoneRecordBean getRecordByNo(String recordNo);
    
    /**
     * 当前通话流水号是否存在
     */
    public boolean isExistRecordByNo(String recordNo);
    
    /**
     * 得到一个新的通话ID
     * @return
     */
    public Integer newTalkId();
    
    /**
     * 保存通话记录
     * @param record
     */
    public void saveTalkRecord(PhoneRecordBean record);
    
    /**
     * 保存通话记录
     * @param record
     */
    public void saveTalkRecordByNo(PhoneRecordBean record);
    
    /**
     * 取得联系记录流水号
     * @return
     */
    public String genRecrodInfoNo();
    
    /**
     * 根据客户id查询最近一次的联系类型
     * @return list<RecordInfoBean>
     * @param int customerId
     */
    public RecordInfoBean getLastListbyCustomerId(int customerId);
    
    /**
     * 根据记录id修改客户id
     * @param id 记录id
     * @param customerId 客户id
     * @param String customerName 客户姓名
     */
    public void updateCustomerIdByRecId(int id,int customerId,String customerName,String phone);
    
    /**
     * 根据电话修改所有客户信息
     */
    public void updateCustomerIdByPhone(String phone, int customerId, String customerName);
    
    /**
     * 取消关联
     * @param customerId 客户id
     * @param phone 电话
     */
    public void updateCustomerIdByCusId(int customerId,String phone);
    
    /**
     * 根据记录id取消关联
     */
    public void updateCustomerIdByRecIdForRelation(int recordId);
    
    /**
     * 根据记录id修改归档内容
     * @param map
     */
    public void updateArchiveById(int id,String filePath);
    
    /**
     * 处理去电号码
     * @param number
     * @return
     */
    public String formatOutNumber(String number,PhoneConfig config);
    
    /**
     * 处理来电号码
     * @param number
     * @return
     */
    public String formatIncomingNumber(String number,PhoneConfig config);

    /**
     * 未读留言总数
     * @param int userId
     * @return int 总数
     */
    public int getUnreadCount(int userId);
    
    /**
     * 未接电话总数
     * @param int userId
     * @return int 总数
     */
    public int getMissedCount(int userId);
    
    /**
     * 未读座谈总数
     *@param int userid 
     *@return int 总数
     */
    public int getUnreadZTCount(int userId);
    
    /**
     * 未读短信总数
     *@param int userid 
     *@return int 总数
     */
    public int getMissedSMSCount(int userId);
    
    /**
     * 批处理添加联系记录
     */
    public void addRecordInfoBatch(List<RecordInfo> list);
    
    
    /**
     * 查询待导出的联系记录
     */
    public List<RecordExportBean> queryRecordInfosByMap(Map<String, Object> map,int type,RecordQueryBean queryBean,int startRow,int endRow);

    /**
     * 导出录音
     * @param str
     * @return 
     */
    public String exportFile(List<RecordExportBean> list,String data);
    /**
     * 导出基础字段
     * @return
     */
    public Map<String, String> initParameter();
    
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
    public List<RecordInfo> getSameRecodInfoList(Map<String, Object> map);
    
    /**
     * 得到最近10条联系记录
     * @param userId
     * @return
     */
    public List<TalkRecordInfo> getRecentlyTalkRecord(Integer userId);
    
    /** 
     * 新建或修改客户 关联记录
     * @param recordId 记录id
     * @param customerId 客户id
     * @param flag  是否是座谈记录 true:是 false:否 
     */
    public void relationRecord(Integer recordId,Integer customerId,Boolean flag);
    
    /** 
     * 导入客户 关联记录
     * @param customerId 客户id
     * @param phoneConfig 区号
     */
    public void relationRecord(BaseCrmCustomer baseCrmCustomer,PhoneConfig phoneConfig);
    
    /** 
     * 根据客户信息 关联记录
     * @param baseCrmCustomer 
     * @param recordId 记录id
     * @param phoneConfig 区号
     * @param customerId 客户id
     * @param flag  是否是座谈记录 true:是 false:否 
     */
    public void updateRelationRecord(BaseCrmCustomer baseCrmCustomer,PhoneConfig phoneConfig,Integer recordId,Integer customerId,Boolean flag);
    
    /**
     * 根据电话号码查询相匹配的记录并关联
     */
    public void updateRecordByPhones(Map<String, Object> map);
    
    /**
     * 根据客户id查询最近的一条联系记录
     */
    public RecordDetail getRecordInfoByCustomerId(Integer customerId);
    
    /**
     * 根据phone查询最近的一条联系记录
     */
    public RecordDetail getRecordInfoByCustomerPhone(String phone);
    
    /**
     * 根据recordID查询该条记录是否有权限 
     * @param arr[] 部门id集合 null 表示客户经理 
     * @param recordId 录音id
     * @return true 有 false 无
     */
    public Boolean getRecordPermissionById(Integer recordId,Integer[] arr);
    
    /**
     * 查询归属客户Id集合
     */
    public String getIdsByBelongUserId(Integer userId);
     
    /**
     * 查询包含客户Id集合
     */
    public String getIdsByCustomer(Map<String, Object> parameters);
    
    /**
     * 查询共享客户Id集合
     */
    public String getIdsShareUerId(Map<String, Object> parameters);
    
    /**
     * 关联联系记录前查询待关联的所有联系记录
     */
    public void relatRec(List<CrmCustomer> cuslist);
    
    /**
     * 根据客户id拼接客户冗余字段
     * @param customerId
     * @param recCustomerName
     * @param recRemotePhone
     * @return
     */
    public String getCustomerStr(Integer customerId,String recCustomerName,String recRemotePhone);
    
    /**
     * 录音信息及分页(任务管理模块点击进入)
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<RecordInfoBean> getTskRecordInfoPage(Map<String, Object> parameters, Page page);

    /**
     * 将Integer类型的录音/视频时长转换为HH:mm:ss形式
     * 
     * @param str
     * @return
     */
    public String changTime(String str);
    
    /**
     * 根据配置组装号码
     * @param inNumber
     * @param config
     * @return
     */
    public String splitPhoneByConfig(String inNumber,PhoneConfig config);

    Integer getRecentlyRecordForSchedule(Map<String, Object> paramMap);

    Integer getRecordCountByCustomerId(Integer customerId);//根据客户id获取所有联系记录
}
