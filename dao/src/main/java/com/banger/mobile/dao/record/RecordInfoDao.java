/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :录音记录实体数据接口
 * Author     :zhangxiang
 * Version    :1.0
 * Create Date:May 2, 2012
 */
package com.banger.mobile.dao.record;

import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.base.customer.BaseCrmCustomer;
import com.banger.mobile.domain.model.base.record.BaseRecordInfo;
import com.banger.mobile.domain.model.record.PhoneRecordBean;
import com.banger.mobile.domain.model.record.RecordDetail;
import com.banger.mobile.domain.model.record.RecordExportBean;
import com.banger.mobile.domain.model.record.RecordInfo;
import com.banger.mobile.domain.model.record.RecordInfoBean;
import com.banger.mobile.domain.model.record.RecordTemp;
import com.banger.mobile.domain.model.record.TalkRecordInfo;

/**
 * @author zhangxiang
 * @version $Id: RecordInfoDao.java,v 0.1 May 2, 2012 2:17:22 PM zhangxiang Exp $
 */
public interface RecordInfoDao {
    
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
     * @param parameters
     */
    public void updateRecordCanceled(Map<String, Object> parameters);

    /**
     * 根据id得到录音信息
     * @param id
     * @return
     */
    public RecordDetail getRecordInfoById(int id);

    /**
     * 录音信息及分页
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<RecordInfoBean> getRecordInfoPage(Map<String, Object> parameters, Page page);
    
    /**
     * 归档录音信息及分页
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<RecordInfoBean> getArchiveRecordInfoPage(Map<String, Object> parameters, Page page);

    /**
     * 根据沟通进度的ID删除沟通进度ID值
     * @param CommProgressId
     * @return
     */
    public boolean deleteCommProgressId(int CommProgressId);
    
    /**
     * 客户联系记录信息接口
     * @param CustomerId
     * @return list
     */
    public PageUtil<RecordInfoBean> queryCustomerCallInfoByCusId(Map<String, Object> parameterMap, Page page);
    
    /**
     * 客户联系记录总数接口
     * @param CustomerId
     */
    public int queryCustomerCallInfoCountByCusId(int customerId);
    
    /**
     * 根据归档时间查询可归档的录音
     * @param parameters
     * @return
     */
    public List<RecordInfo> queryRecordToGd(Map<String, Object> parameters);
    
    /**
     * 根据id查询全字段信息
     * @param Id
     * @return
     */
    public RecordInfo queryAllById(int Id);
    
    /**
     * 根据recordInfo修改
     * @param Id
     */
    public void updateRec(int Id);
    
    /**
     * 修改record编辑信息
     * @param recordDetail
     */
    public void updateRecordConnect(RecordDetail recordDetail);
    
    /**
     * 按条件返回结果
     * @param parameters
     * @return
     */
    public List<RecordInfoBean> getRecordInfo(Map<String, Object> parameters);
    
    /**
     * 按条件返回归档结果
     * @param parameters
     * @return
     */
    public List<RecordInfoBean> getRecordArchiveInfo(Map<String, Object> parameters);
    
    /**
     * 得到一个新的通话ID
     * @return
     */
    public Integer newTalkId();
    
    /**
     * 当前通话ID是否存在
     * @param talkId
     * @return
     */
    public boolean isExistRecord(Integer talkId);
    
    /**
     * 更新通话记录
     * @param record
     */
    public void updateTalkRecord(BaseRecordInfo record);
    
    /**
     * 新增通记录
     * @param record
     */
    public void insertTalkRecord(BaseRecordInfo record);
    
    /**
     * 当前通话流水号是否存在
     * @param recordNo
     * @return
     */
    public boolean isExistRecordByNo(String recordNo);
    
    /**
     * 通过流水号更新通话记录
     * @param record
     */
    public void updateTalkRecordByNo(BaseRecordInfo record);
    
    /**
     * 新增通记录,带流水号
     * @param record
     */
    public void insertTalkRecordByNo(BaseRecordInfo record);
    
    /**
     * 根据客户id查询联系记录
     * @param customerid
     * @return
     */
    public List<RecordInfoBean> getLastListbyCustomerId(int customerid);
    
    /**
     * 根据记录id修改客户id
     * @param parameters
     */
    public void updateCustomerIdByRecId(Map<String, Object> parameters);
    
    /**
     * 根据客户id取消关联
     */
    public void updateCustomerIdByCusId(Map<String, Object> parameters);
    
    /**
     * 根据记录id取消关联
     */
    public void updateCustomerIdByRecIdForRelation(Map<String, Object> parameters);
    
    /**
     * 根据电话修改所有客户信息
     */
    public void updateCustomerIdByPhone(Map<String, Object> parameters);
    
    /**
     * 根据记录id修改归档内容
     * @param parameters
     */
    public void updateArchiveById(Map<String, Object> parameters);
    
    /**
     * 未读留言总数
     * @param parameters
     * @return
     */
    public int getUnreadCount(Map<String, Object> parameters);
    
    /**
     * 未接电话总数
     * @param parameters
     * @return
     */
    public int getMissedCount(Map<String, Object> parameters);

    /**
     * 查询流水号的list
     * @param no
     * @return
     */
    public List<RecordInfo> queryAllByNo(String no);
    
    /**
     * 批处理添加联系记录
     * @param list
     */
    public void addRecordInfoBatch(List<RecordInfo> list);
    
    /**
     * 得到最近10条联系记录
     * @param condition
     * @return
     */
    public List<TalkRecordInfo> getRecentlyTalkRecord(Map<String, Object> condition);
    
    /**
     * 查询待导出的联系记录
     * @param parameters
     * @return
     */
    public List<RecordExportBean> queryRecordInfosByMap(Map<String, Object> parameters);

    public PhoneRecordBean getRecordInfoByNo(String recordNo);
    
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
    public List<RecordInfo> getSameRecodInfoList(Map<String, Object> parameters);
    
    /**
     * 根据电话号码查询相匹配的记录并关联
     */
    public void updateRecordByPhones(Map<String, Object> map);
    
    
    /**
     * 根据客户id查询最近的一条联系记录
     */
    public RecordDetail getRecordInfoByCustomerId(Map<String, Object> map);
    
    /**
     * 根据phone查询最近的一条联系记录
     */
    public RecordDetail getRecordInfoByCustomerPhone(Map<String, Object> map);
    
    /**
     * 根据recordId判断该记录是否有权限查看
     */
    public Boolean getRecordPermissionById(Map<String, Object> parameters);
    
    /**
     * 查询归属客户Id集合
     */
    public String getIdsByBelongUserId(Map<String, Object> parameters);
     
    /**
     * 查询包含客户Id集合
     */
    public String getIdsByCustomer(Map<String, Object> parameters);
    
    /**
     * 查询共享客户Id集合
     */
    public List<BaseCrmCustomer> getIdsShareUerId(Map<String, Object> parameters);
    
    /**
     * 根据联系记录id 修改CUSTOMER_STR 冗余字段的值
     * @param map  customer-customerStr ,recId-recordId
     */
    public void updateCustomerStrByRecId(Map<String, Object> map);
    
    /**
     * 根据联系记录id 修改CUSTOMER_STR 冗余字段的值
     */
    public void updateCustomerStrByCusId(Map<String, Object> map);
    
    /**
     * 填充CUSTOMER_STR 冗余字段
     */
    public String addCustomerStr(RecordInfo recordInfo);
    /**
     * 填充CUSTOMER_STR 冗余字段
     */
    public String addCustomerStr(RecordDetail recordDetail);
    
    /**
     * 填充CUSTOMER_STR 冗余字段
     */
    public String addCustomerStr(Integer recordId);
    
    /**
     * 填充CUSTOMER_STR 冗余字段
     */
    public String addCustomerStrByCusId(Integer cusId);
    
    /**
     * 关联联系记录前查询待关联的所有联系记录
     */
    public List<RecordTemp> getRecByPhone(String phones);
    
    /**
     * 批量关联 执行批量sql 联系记录和客户共用
     */
    public void updateRecord(List<String> slist);
    
    /**
     * 批量关联 执行批量sql 联系记录和客户共用
     */
    public void updateCustomer(List<String> slist);
    
    /**
     * 根据客户id查询最近的一条联系记录
     */
    public RecordTemp queryLastRecByCusId(Integer customerId);
    
    /**
     * 录音信息及分页(任务管理模块点击进入)
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<RecordInfoBean> getTskRecordInfoPage(Map<String, Object> parameters, Page page);

    Integer getRecentlyRecordForSchedule(Map<String, Object> paramMap);


    Integer getRecordCountByCustomerId(Integer customerId);//根据客户id获取所有联系记录
}
