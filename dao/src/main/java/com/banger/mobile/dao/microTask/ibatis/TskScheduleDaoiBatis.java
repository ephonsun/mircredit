package com.banger.mobile.dao.microTask.ibatis;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.microTask.TskScheduleDao;
import com.banger.mobile.domain.model.microProduct.PdtProductCustomerBean;
import com.banger.mobile.domain.model.microTask.TskSchedule;
import com.banger.mobile.ibatis.GenericDaoiBatis;
import com.banger.mobile.util.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TskScheduleDaoiBatis extends GenericDaoiBatis implements TskScheduleDao{

    public TskScheduleDaoiBatis(){
        super(TskSchedule.class);
    }
    public TskScheduleDaoiBatis(Class persistentClass) {
        super(persistentClass);
    }
    /**
     * 查看日程详情
     * @param scheduleId
     * @return
     * @see com.banger.mobile.dao.microTask.TskScheduleDao#getScheduleById(java.lang.Integer)
     */
    public TskSchedule getScheduleById(Integer scheduleId) {
        return (TskSchedule) this.getSqlMapClientTemplate().queryForObject("getScheduleById" ,scheduleId);
    }
    /**
     * 添加新日程
     * @param tskSchedule
     * @see com.banger.mobile.dao.microTask.TskScheduleDao#addNewSchedule(com.banger.mobile.domain.model.microTask.TskSchedule)
     */
    public void addNewSchedule(TskSchedule tskSchedule) {
        this.getSqlMapClientTemplate().insert("addNewScheduleForCustomer", tskSchedule);
    }
    /**
     * 编辑
     * @param tskSchedule
     * @see com.banger.mobile.dao.microTask.TskScheduleDao#updateSchedule(com.banger.mobile.domain.model.microTask.TskSchedule)
     */
    public void updateSchedule(TskSchedule tskSchedule) {
        this.getSqlMapClientTemplate().update("updateScheduleById", tskSchedule);
    }
    /**
     * 获取日程列表
     * @param parameterMap
     * @param page
     * @return
     * @see com.banger.mobile.dao.microTask.TskScheduleDao#getScheduleListByPage(java.util.Map, com.banger.mobile.Page)
     */
    public List<TskSchedule> getScheduleListByPage(Map<String , Object> parameterMap,Page page) {
        for(Map.Entry<String, Object> entry : parameterMap.entrySet())
        {
            if(entry.getValue() instanceof String){
                String result = StringUtil.ReplaceSQLEscapeChar(StringUtil.ReplaceSQLChar(entry.getValue().toString()));
                parameterMap.put(entry.getKey(), result);
            }
        }
        return this.findQueryPage("getScheduleWithCustomerInfo",
                "getScheduleWithCustomerInfoCount", parameterMap, page);
    }

    /**
     * 得到7天内未完成的日程安排列表(分页查询)
     *
     * @param parameterMap
     * @param page
     * @return
     */
    @Override
    public List<TskSchedule> getInTimeScheduleListByPage(Map<String, Object> parameterMap, Page page) {
        for(Map.Entry<String, Object> entry : parameterMap.entrySet())
        {
            if(entry.getValue() instanceof String){
                String result = StringUtil.ReplaceSQLEscapeChar(StringUtil.ReplaceSQLChar(entry.getValue().toString()));
                parameterMap.put(entry.getKey(), result);
            }
        }
        return this.findQueryPage("getInTimeScheduleList","getInTimeScheduleCount",parameterMap,page);
    }

    public Integer getCount(Map<String , Object> parameterMap){
        return (Integer)this.getSqlMapClientTemplate().queryForObject("getScheduleWithCustomerInfoCount",parameterMap);
    }

    /**
     * 获取完成进度
     * @return
     * @see com.banger.mobile.dao.microTask.TskScheduleDao#getAllCommProgress()
     */
    public List<Map> getAllCommProgress() {
        return this.getSqlMapClientTemplate().queryForList("getCrmCommProgressInfo");
    }

    /**
     * 得到第一条沟通进度的comm_progress_id
     *
     * @return
     */
    @Override
    public Integer getNo1CommProgressId(){
        return (Integer)this.getSqlMapClientTemplate().queryForObject("getNo1CommProgressId");
    }
    /**
     * 判断当前用户的客户日程数
     * @param map
     * @return
     * @see com.banger.mobile.dao.microTask.TskScheduleDao#getCustomerScheduleCount(java.util.Map)
     */
    public Integer getCustomerScheduleCount(Map<String, Object> map) {
        return (Integer)this.getSqlMapClientTemplate().queryForObject("getCustomerScheduleCount",map);
    }

    @Override
    public Integer judgeScheduleByCustomerId(Map<String, Object> paramMap) {
        return (Integer)this.getSqlMapClientTemplate().queryForObject("judgeScheduleByCustomerId",paramMap);
    }

    /**
     * 查询客户列表
     * @param map
     * @return
     * @see com.banger.mobile.dao.microTask.TskScheduleDao#getCustomerList(java.util.Map,com.banger.mobile.Page)
     */
    public List<TskSchedule> getCustomerList(Map<String, Object> map,Page page) {
        for(Map.Entry<String, Object> entry : map.entrySet())
        {
            if(entry.getValue() instanceof String){
                String result = StringUtil.ReplaceSQLEscapeChar(StringUtil.ReplaceSQLChar(entry.getValue().toString()));
                map.put(entry.getKey(), result);
            }
        }
        return this.findQueryPage("getCustomerList",
                "getCustomerListCount", map, page);
    }
    /**
     * 客户类型列表
     * @return
     * @see com.banger.mobile.dao.microTask.TskScheduleDao#getCustomerTypeList()
     */
    public List<Map> getCustomerTypeList() {
        return this.getSqlMapClientTemplate().queryForList("getCustomerTypeList");
    }
    /**
     * 获取用户详细信息
     * @param map
     * @return
     * @see com.banger.mobile.dao.microTask.TskScheduleDao#getCustomerDetail(java.util.Map)
     */
    public TskSchedule getCustomerDetail(Map<String, Object> map) {
        return (TskSchedule)this.getSqlMapClientTemplate().queryForObject("getCustomerDetail", map);
    }

    /**
     * 获取用户意向产品信息
     * @param parameterMap
     * @param page
     * @return
     */
    public PageUtil<TskSchedule> getCustomerPDTProductList(Map<String,Object> parameterMap,Page page){
//        return (List<TskSchedule>)this.getSqlMapClientTemplate().queryForList("getCustomerPDTProductList",map);
        Map<String,Object> fConds = new HashMap<String, Object>();
        for(Map.Entry<String, Object> entry : parameterMap.entrySet())
        {
            if(entry.getValue() instanceof String){
                fConds.put(entry.getKey(), StringUtil.ReplaceSQLEscapeChar(StringUtil.ReplaceSQLChar(entry.getValue().toString())));
            }else{
                fConds.put(entry.getKey(), entry.getValue());
            }
        }
        List<TskSchedule> tskSchedules = this.findQueryPage("getCustomerPDTProductList","getCustomerPDTProductCount", fConds, page);
        return new PageUtil<TskSchedule>(tskSchedules,page);
    }

    /**
     * 获取用户意向产品数量
     * @param paramMap
     * @return
     */
    public Integer getCustomerPDTProductCount(Map<String,Object> paramMap){
        return (Integer)this.getSqlMapClientTemplate().queryForObject("getCustomerPDTProductCount",paramMap);
    }

    /**
     * 批量插入用户日程
     * @param list
     */
    public void insertTskScheduleBatch(List<TskSchedule> list){
        this.exectuteBatchInsert("addNewScheduleForCustomer",list);
    }

    @Override
    public List<PdtProductCustomerBean> getCusPdtProductListForPad(Map<String, Object> paramMap, Page page) {
        return this.findQueryPage("getCustomerPDTProductList",
                "getCustomerPDTProductCount", paramMap, page);
    }

    /**
     * 小页卡--日程安排
     *
     * @param paramMap
     * @param page
     * @return
     */
    @Override
    public PageUtil<TskSchedule> getTskScheduleCard(Map<String,Object> paramMap,Page page){
        Map<String,Object> fConds = new HashMap<String, Object>();
        for (Map.Entry<String,Object> entry : paramMap.entrySet()){
            if(entry.getValue() instanceof String){
                fConds.put(entry.getKey(), StringUtil.ReplaceSQLChar(entry.getValue().toString()));
            }else {
                fConds.put(entry.getKey(),entry.getValue());
            }
        }
        List<TskSchedule> list = (List<TskSchedule>)this.findQueryPage("getTskScheduleCard","getTskScheduleCountCard",fConds,page);
        return new PageUtil<TskSchedule>(list,page);
    }

    /**
     * 根据客户ID集，得到所有客户相关的日程信息
     *
     * @param paramMap
     * @return
     */
    public List<TskSchedule> getScheduleByCusIds(Map<String,Object> paramMap){
        return this.getSqlMapClientTemplate().queryForList("getTskScheduleByCusIds",paramMap);
    }

    /**
     * 根据客户ID删除相关的日程
     *
     * @param paramMap
     */
    @Override
    public void deleteScheduleByCusIds(Map<String,Object> paramMap){
        this.getSqlMapClientTemplate().delete("deleteScheduleByCusId",paramMap);
    }

    /**
     * 合并客户日程
     *
     * @param paramMap
     */
    @Override
    public void mergeCusSchedule(Map<String,Object> paramMap){
        this.getSqlMapClientTemplate().update("mergeCusSchedule",paramMap);
    }

    /**
     * 得到有贷款的客户列表
     *
     * @param paramMap
     * @return
     */
    @Override
    public List<Integer> getScheduleCusIds(Map<String,Object> paramMap){
        return (List<Integer>)this.getSqlMapClientTemplate().queryForList("getScheduleCusIds",paramMap);
    }

    /**
     * 得到7天内未完成的日程安排数量
     *
     * @param paramMap
     * @return
     */
    @Override
    public Integer getInTimeScheduleCount(Map<String, Object> paramMap){
        return (Integer)this.getSqlMapClientTemplate().queryForObject("getInTimeScheduleCount",paramMap);
    }

    /**
     * 得到7天内未完成的日程安排列表
     *
     * @param paramMap
     * @return
     */
    @Override
    public List<TskSchedule> getInTimeScheduleList(Map<String,Object> paramMap){
        return (List<TskSchedule>)this.getSqlMapClientTemplate().queryForList("getInTimeScheduleList",paramMap);
    }

    /**
     * 查出用户未完成的日程(如果出现多条，则返回最近的一条)
     *
     * @param paramMap
     * @return
     */
    @Override
    public TskSchedule getUnFinishedSchedule(Map<String,Object> paramMap){
        return (TskSchedule)this.getSqlMapClientTemplate().queryForObject("getUnFinishedSchedule",paramMap);
    }

    /**
     * 得到下一个scheduleId
     *
     * @return
     */
    @Override
    public Integer getNextScheduleId(){
        return (Integer)this.getSqlMapClientTemplate().queryForObject("getNextScheduleId");
    }

    /**
     * 添加新的日程
     *
     * @param tskSchedule
     */
    @Override
    public void insertTskSchedule(TskSchedule tskSchedule){
        this.getSqlMapClientTemplate().insert("addNewSchedule",tskSchedule);
    }

    /**
     * 获得某个客户的日程数量
     *
     * @param paramMap
     * @return
     */
    @Override
    public Integer getCusScheduleCount(Map<String,Object> paramMap){
        return (Integer)this.getSqlMapClientTemplate().queryForObject("getCusScheduleCount",paramMap);
    }

    /**
     * @param paras
     * @see com.banger.mobile.dao.microTask.TskScheduleDao#updateTaskSchedule(java.util.Map)
     */
    public void updateTaskSchedule(Map<String, Object> paras) {
        this.getSqlMapClientTemplate().update("updateTaskSchedule",paras);
    }

    @Override
    public Integer getTskScheduleCountCard(Map<String, Object> map) {
        return (Integer) this.getSqlMapClientTemplate().queryForObject("getTskScheduleCountCard",map);
    }
}
