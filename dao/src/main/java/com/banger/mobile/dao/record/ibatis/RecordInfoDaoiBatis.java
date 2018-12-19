/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :录音记录实体数据实现类
 * Author     :huangk
 * Version    :1.0
 * Create Date:May 2, 2012
 */
package com.banger.mobile.dao.record.ibatis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.customer.CrmCustomerDao;
import com.banger.mobile.dao.record.RecordInfoDao;
import com.banger.mobile.domain.model.base.customer.BaseCrmCustomer;
import com.banger.mobile.domain.model.base.record.BaseRecordInfo;
import com.banger.mobile.domain.model.customer.CrmCustomer;
import com.banger.mobile.domain.model.microTask.TskMicroTaskTarget;
import com.banger.mobile.domain.model.record.PhoneRecordBean;
import com.banger.mobile.domain.model.record.RecordDetail;
import com.banger.mobile.domain.model.record.RecordExportBean;
import com.banger.mobile.domain.model.record.RecordInfo;
import com.banger.mobile.domain.model.record.RecordInfoBean;
import com.banger.mobile.domain.model.record.RecordTemp;
import com.banger.mobile.domain.model.record.TalkRecordInfo;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author huangk
 * @version $Id: RecordInfoDaoiBatis.java,v 0.1 May 2, 2012 2:25:27 PM huangk Exp $
 */
public class RecordInfoDaoiBatis extends GenericDaoiBatis implements RecordInfoDao {
    private CrmCustomerDao crmCustomerDao;
    private CrmCustomer crmCustomer;

    public RecordInfoDaoiBatis() {
        super(RecordInfo.class);
    }

    /**
     *
     * @param persistentClass
     */
    public RecordInfoDaoiBatis(Class persistentClass) {
        super(RecordInfo.class);
    }

    /**
     * 添加一条录音信息
     * @param recordInfo
     * @see com.banger.mobile.dao.record.RecordInfoDao#addRecordInfo(com.banger.mobile.domain.model.record.RecordInfo)
     */
    public void addRecordInfo(RecordInfo recordInfo) {//0
        recordInfo.setCustomerStr(this.addCustomerStr(recordInfo));
        this.getSqlMapClientTemplate().insert("addRecordInfo", recordInfo);
    }

    /**
     * 批处理添加联系记录
     */
    public void addRecordInfoBatch(List<RecordInfo> list){
//        for(int i=0;i<list.size();i++){
//            list.get(i).setCustomerStr(this.addCustomerStr(list.get(i)));//批量添加时循环插入customerStr冗余字段
//        }
        this.exectuteBatchInsert("addRecordInfoForSeq", list);
    }

    /**
     * 删除录音信息
     * @param id
     * @see com.banger.mobile.dao.record.RecordInfoDao#deleteRecordInfo(int)
     */
    public void deleteRecordInfo(int id) {
        this.getSqlMapClientTemplate().update("deleteRecordInfoById", id);
    }

    /**
     * 修改录音信息
     * @param recordInfo
     * @see com.banger.mobile.facade.record.RecordInfoService#updateRecordInfo(com.banger.mobile.domain.model.record.RecordInfo)
     */
    public void updateRecordInfo(RecordInfo recordInfo){//1
        recordInfo.setCustomerStr(this.addCustomerStr(recordInfo));
        this.getSqlMapClientTemplate().update("updateRecordInfo", recordInfo);
    }


    /**
     * 修改录音是否有效
     * @param recordInfo
     */
    public void updateRecordCanceled(Map<String, Object> parameters) {
        this.getSqlMapClientTemplate().update("canceledRecordInfoById",parameters);
    }


    /**
     * 根据id得到录音信息
     * @param id
     * @return
     * @see com.banger.mobile.dao.record.RecordInfoDao#getRecordInfoById(int)
     */
    public RecordDetail getRecordInfoById(int id) {
        return (RecordDetail) this.getSqlMapClientTemplate().queryForObject("getRecordInfoById", id);
    }

    /**
     * 录音信息及分页
     * @param parameters
     * @param page
     * @return
     * @see com.banger.mobile.dao.record.RecordInfoDao#getRecordInfoPage(java.util.Map, com.banger.mobile.Page)
     */
    public PageUtil<RecordInfoBean> getRecordInfoPage(Map<String, Object> parameters, Page page) {
        ArrayList<RecordInfoBean> list = (ArrayList<RecordInfoBean>) this.findQueryPage(
                "getRecordInfoParameterPageMap", "getRecordInfoCount", parameters, page);
        if (list == null) {
            list = new ArrayList<RecordInfoBean>();
        }
        return new PageUtil<RecordInfoBean>(list, page);
    }

    /**
     * 归档录音信息及分页
     * @param parameters
     * @param page
     * @return
     * @see com.banger.mobile.dao.record.RecordInfoDao#getArchiveRecordInfoPage(java.util.Map, com.banger.mobile.Page)
     */
    @Override
    public PageUtil<RecordInfoBean> getArchiveRecordInfoPage(Map<String, Object> parameters, Page page) {
        ArrayList<RecordInfoBean> list = (ArrayList<RecordInfoBean>) this.findQueryPage(
                "getArchiveRecordInfoParameterPageMap", "getArchiveRecordInfoCount", parameters, page);
        if (list == null) {
            list = new ArrayList<RecordInfoBean>();
        }
        return new PageUtil<RecordInfoBean>(list, page);
    }

    /**
     * 根据沟通进度的ID删除沟通进度ID值 
     * @param Id
     * @return boolean
     */
    public boolean deleteCommProgressId(int CommProgressId){
        if(this.getSqlMapClientTemplate().update("deleteCommIdById", CommProgressId)==1){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 客户联系记录信息接口
     * @param CustomerId
     * @return list
     */
    public PageUtil<RecordInfoBean> queryCustomerCallInfoByCusId(Map<String, Object> parameterMap, Page page){
        List<RecordInfoBean> list = this.findQueryPage(
                "queryCustomerCallInfoByCusId", "queryCustomerCallInfoCountByCusId",
                parameterMap, page);
        return new PageUtil<RecordInfoBean>(list, page);
    }

    /**
     * 客户联系记录总数接口
     * @param CustomerId
     */
    public int queryCustomerCallInfoCountByCusId(int customerId){
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("customerId", customerId);
        return (Integer)getSqlMapClientTemplate().queryForObject("queryCustomerCallInfoCountByCusId",map);
    }


    /**
     * 根据归档时间查询可归档的录音
     */
    public List<RecordInfo> queryRecordToGd(Map<String, Object> parameters){
        return (List<RecordInfo>)this.getSqlMapClientTemplate().queryForList("queryRecordByArchiveDate",parameters);
    }

    /**
     * 根据id查询全字段信息
     * @param Id
     */
    public RecordInfo queryAllById(int Id){
        return (RecordInfo)this.getSqlMapClientTemplate().queryForObject("queryAllById",Id);
    }

    /**
     * 根据recordInfo修改
     * @param recordInfo
     */
    public void updateRec(int Id){
        this.getSqlMapClientTemplate().update("updateRec",Id);
    }

    /**
     * 修改record编辑信息
     * @param updateRecordConnect
     */
    public void updateRecordConnect(RecordDetail recordDetail){
        this.getSqlMapClientTemplate().update("updateRecordConnect",recordDetail);
    }

    /**
     * 按条件返回结果
     * @param map
     */
    public List<RecordInfoBean> getRecordInfo(Map<String, Object> parameters) {
        return this.getSqlMapClientTemplate().queryForList("getRecordInfoParameterPageMap", parameters);
    }

    /**
     * 按条件返回归档结果
     * @param map
     */
    public List<RecordInfoBean> getRecordArchiveInfo(Map<String, Object> parameters){
        return this.getSqlMapClientTemplate().queryForList("getArchiveRecordInfoParameterPageMap", parameters);
    }

    /**
     * 得到一个新的通话ID
     * @return
     */
    public Integer newTalkId(){
        return (Integer)getSqlMapClientTemplate().queryForObject("getNextTalkId");
    }

    /**
     * 当前通话ID是否存在
     * @param talkId
     * @return
     */
    public boolean isExistRecord(Integer talkId){
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("getIsExistTalkId",talkId);
        return count.intValue()>0;
    }

    /**
     * 当前通话流水号是否存在
     */
    public boolean isExistRecordByNo(String recordNo){
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("getIsExistTalkNo",recordNo);
        return count.intValue()>0;
    }

    /**
     * 更新通话记录,暂时不用
     * @param record
     */
    public void updateTalkRecord(BaseRecordInfo record)
    {
        this.getSqlMapClientTemplate().update("updateTalkRecord",record);
    }

    /**
     * 通过流水号更新通话记录
     * @param record
     */
    public void updateTalkRecordByNo(BaseRecordInfo record)
    {
        this.getSqlMapClientTemplate().update("updateTalkRecordByNo",record);
    }

    /**
     * 新增通记录,暂时不用
     * @param record
     */
    public void insertTalkRecord(BaseRecordInfo record)
    {
        getSqlMapClientTemplate().insert("insertTalkRecord",record);
    }

    /**
     * 新增通记录,带流水号
     * @param record
     */
    public void insertTalkRecordByNo(BaseRecordInfo record)
    {
        getSqlMapClientTemplate().insert("insertTalkRecordByNo",record);
    }

    /**
     * 根据客户id查询联系记录
     * @return list<>
     * @param customer
     */
    public List<RecordInfoBean> getLastListbyCustomerId(int customerid){
        return this.getSqlMapClientTemplate().queryForList("getLastListbyCustomerId", customerid);
    }

    /**
     * 根据记录id修改客户id
     */
    public void updateCustomerIdByRecId(Map<String, Object> parameters){//2
        this.getSqlMapClientTemplate().update("updateCustomerIdByRecId",parameters);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("customerStr", this.addCustomerStr(Integer.parseInt(parameters.get("id").toString())));
        map.put("recId", Integer.parseInt(parameters.get("id").toString()));
        this.updateCustomerStrByRecId(map);
    }

    /**
     * 根据电话修改所有客户信息
     */
    public void updateCustomerIdByPhone(Map<String, Object> parameters){//3
        this.getSqlMapClientTemplate().update("updateCustomerIdByPhone",parameters);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("customerStr", this.addCustomerStrByCusId(Integer.parseInt(parameters.get("customerId").toString())));
        map.put("cusId", Integer.parseInt(parameters.get("customerId").toString()));
        this.updateCustomerStrByCusId(map);
    }

    /**
     * 根据客户id取消关联
     */
    public void updateCustomerIdByCusId(Map<String, Object> parameters){//4
        this.getSqlMapClientTemplate().update("updateCustomerIdByCusId",parameters);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("customerStr", this.addCustomerStrByCusId(Integer.parseInt(parameters.get("customerId").toString())));
        map.put("cusId", Integer.parseInt(parameters.get("customerId").toString()));
        this.updateCustomerStrByCusId(map);
    }

    /**
     * 根据记录id取消关联
     */
    public void updateCustomerIdByRecIdForRelation(Map<String, Object> parameters){//5
        this.getSqlMapClientTemplate().update("updateCustomerIdByRecIdForRelation",parameters);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("customerStr", this.addCustomerStr(Integer.parseInt(parameters.get("recordId").toString())));
        map.put("recId", Integer.parseInt(parameters.get("recordId").toString()));
        this.updateCustomerStrByRecId(map);
    }

    /**
     * 根据记录id修改归档内容
     * @param map
     */
    public void updateArchiveById(Map<String, Object> parameters){
        this.getSqlMapClientTemplate().update("updateArchiveById",parameters);
    }

    /**
     * 未读留言总数
     * @param map
     */
    public int getUnreadCount(Map<String, Object> parameters){
        return Integer.parseInt(this.getSqlMapClientTemplate().queryForObject("getUnreadOrMissedCount",parameters).toString());
    }

    /**
     * 未接电话总数
     * @param map
     */
    public int getMissedCount(Map<String, Object> parameters){
        return Integer.parseInt(this.getSqlMapClientTemplate().queryForObject("getUnreadOrMissedCount",parameters).toString());
    }

    /**
     * 查询流水号的list
     * @param no
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<RecordInfo> queryAllByNo(String no) {
        return (List<RecordInfo>) this.getSqlMapClientTemplate().queryForList("queryAllByNo", no);
    }


    /**
     * 查询待导出的联系记录
     */
    public List<RecordExportBean> queryRecordInfosByMap(Map<String, Object> parameters) {
        return this.getSqlMapClientTemplate().queryForList("queryRecordInfosByMap",parameters);

    }

    public PhoneRecordBean getRecordInfoByNo(String recordNo){
        return (PhoneRecordBean)this.getSqlMapClientTemplate().queryForObject("getRecordInfoByNo",recordNo);
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
    public List<RecordInfo> getSameRecodInfoList(Map<String, Object> parameters){
        return (List<RecordInfo>)this.getSqlMapClientTemplate().queryForList("querySameRecInfoByMap", parameters);
    }

    /**
     * 得到最近10条联系记录
     * @param condition
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<TalkRecordInfo> getRecentlyTalkRecord(Map<String, Object> condition){
        return this.getSqlMapClientTemplate().queryForList("getRecentlyTalkRecord",condition);
    }

    /**
     * 根据电话号码查询相匹配的记录并关联
     */
    public void updateRecordByPhones(Map<String, Object> map){//6
        this.getSqlMapClientTemplate().update("updateRecordByPhones",map);
        HashMap<String, Object> map1 = new HashMap<String, Object>();
        map1.put("customerStr", this.addCustomerStrByCusId(Integer.parseInt(map.get("customerId").toString())));
        map1.put("cusId", Integer.parseInt(map.get("customerId").toString()));
        this.updateCustomerStrByCusId(map1);
    }

    /**
     * 根据客户id查询最近的一条联系记录
     */
    public RecordDetail getRecordInfoByCustomerId(Map<String, Object> map){
        return (RecordDetail)this.getSqlMapClientTemplate().queryForObject("getRecordInfoByCustomer",map);
    }

    /**
     * 根据phone查询最近的一条联系记录
     */
    public RecordDetail getRecordInfoByCustomerPhone(Map<String, Object> map){
        return (RecordDetail)this.getSqlMapClientTemplate().queryForObject("getRecordInfoByCustomer",map);
    }

    /**
     * 根据recordId判断该记录是否有权限查看
     */
    public Boolean getRecordPermissionById(Map<String, Object> parameters){
        if((Integer)getSqlMapClientTemplate().queryForObject("getRecordPermissionById",parameters)>0)return true;
        else return false;
    }

    /**
     * 查询归属客户Id集合
     */
    public String getIdsByBelongUserId(Map<String, Object> parameters){
        return this.getSqlMapClientTemplate().queryForObject("getIdsByBelongUserId",parameters).toString();
    }

    /**
     * 查询包含客户Id集合
     */
    public String getIdsByCustomer(Map<String, Object> parameters){
        return this.getSqlMapClientTemplate().queryForObject("getIdsByCustomer",parameters).toString();
    }

    /**
     * 查询共享客户Id集合
     */
    public List<BaseCrmCustomer> getIdsShareUerId(Map<String, Object> parameters){
        return (List<BaseCrmCustomer>)this.getSqlMapClientTemplate().queryForList("getIdsShareUerId",parameters);
    }

    /**
     * 根据联系记录id 修改CUSTOMER_STR 冗余字段的值
     */
    public void updateCustomerStrByRecId(Map<String, Object> map){
        this.getSqlMapClientTemplate().update("updateCustomerStrByRecId",map);
    }

    /**
     * 根据客户id 修改CUSTOMER_STR 冗余字段的值
     */
    public void updateCustomerStrByCusId(Map<String, Object> map){
        this.getSqlMapClientTemplate().update("updateCustomerStrByCusId",map);
    }

    /**
     * 填充CUSTOMER_STR 冗余字段
     */
    public String addCustomerStr(RecordInfo recordInfo){
        String customerStr = "";
        if(recordInfo!=null){
            if(recordInfo.getCustomerName() != null)customerStr += recordInfo.getCustomerName()+"_";
            if(recordInfo.getRemotePhone() != null)customerStr += recordInfo.getRemotePhone()+"_";
            if(recordInfo.getCustomerId() != null&&recordInfo.getCustomerId()!=0){//添加客户表里的六个字段
                crmCustomer = crmCustomerDao.getCrmCustomerById(recordInfo.getCustomerId());
                if(crmCustomer.getCustomerName() != null)customerStr += crmCustomer.getCustomerName()+"_";
                if(crmCustomer.getCustomerNamePinyin() != null)customerStr += crmCustomer.getCustomerNamePinyin()+"_";
                if(crmCustomer.getMobilePhone1() != null)customerStr += crmCustomer.getMobilePhone1()+"_";
                if(crmCustomer.getMobilePhone2() != null)customerStr += crmCustomer.getMobilePhone2()+"_";
                if(crmCustomer.getPhone() != null)customerStr += crmCustomer.getPhone()+"_";
                if(crmCustomer.getFax() != null)customerStr += crmCustomer.getFax()+"_";
            }
        }
        return customerStr;
    }
    /**
     * 填充CUSTOMER_STR 冗余字段
     */
    public String addCustomerStr(RecordDetail recordDetail){
        String customerStr = "";
        if(recordDetail!=null){
            if(recordDetail.getCustomerName()!=null)customerStr += recordDetail.getCustomerName()+"_";
            if(recordDetail.getRemotePhone()!=null)customerStr += recordDetail.getRemotePhone()+"_";
            if(recordDetail.getCustomerId()!=null&&recordDetail.getCustomerId()!=0){//添加客户表里的六个字段
                crmCustomer = crmCustomerDao.getCrmCustomerById(recordDetail.getCustomerId());
                if(crmCustomer.getCustomerName() != null)customerStr += crmCustomer.getCustomerName()+"_";
                if(crmCustomer.getCustomerNamePinyin() != null)customerStr += crmCustomer.getCustomerNamePinyin()+"_";
                if(crmCustomer.getMobilePhone1() != null)customerStr += crmCustomer.getMobilePhone1()+"_";
                if(crmCustomer.getMobilePhone2() != null)customerStr += crmCustomer.getMobilePhone2()+"_";
                if(crmCustomer.getPhone() != null)customerStr += crmCustomer.getPhone()+"_";
                if(crmCustomer.getFax() != null)customerStr += crmCustomer.getFax()+"_";
            }
        }
        return customerStr;
    }

    /**
     * 填充CUSTOMER_STR 冗余字段
     */
    public String addCustomerStr(Integer recordId){
        String customerStr = "";
        //根据联系记录id查询联系记录
        RecordDetail recordDetail = this.getRecordInfoById(recordId);
        if(recordDetail!=null){
            //if(recordDetail.getCustomerName()!=null)customerStr += recordDetail.getCustomerName()+"_";
            if(recordDetail.getRemotePhone()!=null)customerStr += recordDetail.getRemotePhone()+"_";
            if(recordDetail.getCustomerId()!=null&&recordDetail.getCustomerId()!=0){//添加客户表里的六个字段
                crmCustomer = crmCustomerDao.getCrmCustomerById(recordDetail.getCustomerId());
                if(crmCustomer.getCustomerName() != null)customerStr += crmCustomer.getCustomerName()+"_";
                if(crmCustomer.getCustomerNamePinyin() != null)customerStr += crmCustomer.getCustomerNamePinyin()+"_";
                if(crmCustomer.getMobilePhone1() != null)customerStr += crmCustomer.getMobilePhone1()+"_";
                if(crmCustomer.getMobilePhone2() != null)customerStr += crmCustomer.getMobilePhone2()+"_";
                if(crmCustomer.getPhone() != null)customerStr += crmCustomer.getPhone()+"_";
                if(crmCustomer.getFax() != null)customerStr += crmCustomer.getFax()+"_";
            }
        }
        return customerStr;
    }

    /**
     * 填充CUSTOMER_STR 冗余字段
     */
    public String addCustomerStrByCusId(Integer cusId){
        String customerStr = "";
        //根据联系记录id查询联系记录
        HashMap<String, Object> map =new HashMap<String, Object>();
        map.put("customerId", cusId);
        RecordDetail recordDetail = this.getRecordInfoByCustomerId(map);
        if(recordDetail!=null){
            if(recordDetail.getCustomerName()!=null)customerStr += recordDetail.getCustomerName()+"_";
            if(recordDetail.getRemotePhone()!=null)customerStr += recordDetail.getRemotePhone()+"_";
            if(recordDetail!=null&&recordDetail.getCustomerId()!=null&&recordDetail.getCustomerId()!=0){//添加客户表里的六个字段
                crmCustomer = crmCustomerDao.getCrmCustomerById(recordDetail.getCustomerId());
                if(crmCustomer.getCustomerName() != null)customerStr += crmCustomer.getCustomerName()+"_";
                if(crmCustomer.getCustomerNamePinyin() != null)customerStr += crmCustomer.getCustomerNamePinyin()+"_";
                if(crmCustomer.getMobilePhone1() != null)customerStr += crmCustomer.getMobilePhone1()+"_";
                if(crmCustomer.getMobilePhone2() != null)customerStr += crmCustomer.getMobilePhone2()+"_";
                if(crmCustomer.getPhone() != null)customerStr += crmCustomer.getPhone()+"_";
                if(crmCustomer.getFax() != null)customerStr += crmCustomer.getFax()+"_";
            }
        }
        return customerStr;
    }

    public CrmCustomerDao getCrmCustomerDao() {
        return crmCustomerDao;
    }

    public void setCrmCustomerDao(CrmCustomerDao crmCustomerDao) {
        this.crmCustomerDao = crmCustomerDao;
    }

    public CrmCustomer getCrmCustomer() {
        return crmCustomer;
    }

    public void setCrmCustomer(CrmCustomer crmCustomer) {
        this.crmCustomer = crmCustomer;
    }

    /**
     * 关联联系记录前查询待关联的所有联系记录
     */
    public List<RecordTemp> getRecByPhone(String phones){
        return (List<RecordTemp>)this.getSqlMapClientTemplate().queryForList("getRecByPhone",phones);
    }

    /**
     * 批量关联 执行批量sql 联系记录和客户共用
     */
    public void updateRecord(List<String> value){
        this.executeBatchUpdate("updateRecord",value);
    }

    /**
     * 批量关联 执行批量sql 联系记录和客户共用
     */
    public void updateCustomer(List<String> value){
        this.executeBatchUpdate("updateCustomer",value);
    }

    /**
     * 根据客户id查询最近的一条联系记录
     */
    public RecordTemp queryLastRecByCusId(Integer customerId){
        return (RecordTemp)this.getSqlMapClientTemplate().queryForObject("getLastRecByCusId",customerId);
    }

    /**
     * 录音信息及分页(任务管理模块点击进入)
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<RecordInfoBean> getTskRecordInfoPage(Map<String, Object> parameters, Page page){
        ArrayList<RecordInfoBean> list = (ArrayList<RecordInfoBean>) this.findQueryPage(
                "getRecordInfoParameterPageMap", "getRecordInfoCount", parameters, page);
        if (list == null) {
            list = new ArrayList<RecordInfoBean>();
        }
        return new PageUtil<RecordInfoBean>(list, page);
    }

    /**
     * 获得某一条日程的电话联系记录当天最近的记录
     * @param paramMap
     * @return
     */
    public Integer getRecentlyRecordForSchedule(Map<String,Object> paramMap){
        return (Integer)this.getSqlMapClientTemplate().queryForObject("getRecentlyRecordForSchedule",paramMap);
    }


    @Override
    public Integer getRecordCountByCustomerId(Integer customerId) {
        return (Integer)this.getSqlMapClientTemplate().queryForObject("getRecordCountByCustomerId",customerId);
    }
}
