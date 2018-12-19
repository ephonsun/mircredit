package com.banger.mobile.dao.tskContact.iBatis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.tskContact.TskContactDao;
import com.banger.mobile.domain.model.base.tskContact.BaseTskContact;
import com.banger.mobile.domain.model.tskContact.CusRelatedTskContactBean;
import com.banger.mobile.domain.model.tskContact.CustomerAttrPlugin;
import com.banger.mobile.domain.model.tskContact.CustModelTaskDetail;
import com.banger.mobile.domain.model.tskContact.TskContact;
import com.banger.mobile.domain.model.tskContact.TskContactReportBean;
import com.banger.mobile.domain.model.tskContact.TskTaskListBean;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：Dec 17, 2012 10:47:55 AM
 * 类说明
 */
public class TskContactiBatis extends GenericDaoiBatis implements TskContactDao{

    public TskContactiBatis(Class persistentClass) {
        super(TskContact.class);
        // TODO Auto-generated constructor stub
    }

    public TskContactiBatis() {
        super(TskContact.class);
        // TODO Auto-generated constructor stub
    }


    /**
     * 任务全景展示
     * @param map
     * @return
     */
    public List<TskTaskListBean> showTskViewList(Map<String, Object> map){
        return this.getSqlMapClientTemplate().queryForList("getTaskContactPageMap",map);
    }

    /**
     * 任务管理列表分页
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<TskTaskListBean> getTskTaskPage(Map<String, Object> parameters, Page page) {
        ArrayList<TskTaskListBean> list = (ArrayList<TskTaskListBean>) this.findQueryPage(
                "getTaskContactPageMap", "getTaskContactPageCount", parameters, page);
        if (list == null) {
            list = new ArrayList<TskTaskListBean>();
        }
        return new PageUtil<TskTaskListBean>(list, page);
    }

    /**
     * 任务所对应的联系量
     */
    public Integer getTaskConnactCount(Map<String, Object> parameters) {
        return (Integer) this.getSqlMapClientTemplate().queryForObject("getTaskConnactCount", parameters);
    }

    /**
     * 新建联系任务
     * @param tskContact
     * @return
     */
    public Integer insertTskContact(TskContact tskContact){
        return (Integer)this.getSqlMapClientTemplate().insert("insertTskContact",tskContact);
    }

    /**
     * 编辑联系任务
     * @param tskContact
     */
    public void updateTskContact(TskContact tskContact){
        this.getSqlMapClientTemplate().update("UpdateTskContact",tskContact);
    }

    /**
     * 删除联系任务
     * @param contactId
     */
    public void deleteTskContact(Integer contactId){
        this.getSqlMapClientTemplate().update("DeleteTskContact",contactId);
    }

    /**
     * 彻底删除联系任务
     * @param contactId:任务ID  isComplete:是否彻底删除
     */
    public void deleteTskContact(Integer contactId, boolean isComplete){
        this.getSqlMapClientTemplate().delete("DeleteTskContactComplete",contactId);
    }

    /**
     * 中止/重启任务
     * @param tskContact
     */
    public void  suspendTskContact(TskContact tskContact){
        this.getSqlMapClientTemplate().update("suspendTskContact",tskContact);
    }

    /**
     * 确认新建任务
     * @param contactId
     */
    public void comfirmAddTask(Integer contactId){
        this.getSqlMapClientTemplate().update("confirmAddTask",contactId);
    }



    /**
     * 根据任务ID获取联系任务实体
     * @param contactId
     * @return
     */
    public TskContact getTskContact(Map<String, Object> map){
        return (TskContact)this.getSqlMapClientTemplate().queryForObject("GetTskContactById", map);
    }

    /**
     * 查询指定的任务量
     */
    public Integer getTskContactCount(Map<String, Object> map) {
        return (Integer) this.getSqlMapClientTemplate().queryForObject("getTaskContactPageCount",map);
    }

    /**
     * 联系计划选择客户 任务树
     */
    public List<BaseTskContact> getPlanCustTaskTree(Map<String,Object> map){
        return this.getSqlMapClientTemplate().queryForList("getPlanCustTaskTree",map);
    }

    /**
     * 关联任务的分页
     * @param map
     * @param page
     * @return
     */
    public PageUtil<TskTaskListBean> getCusRelatedTskContact(Map<String, Object> map,Page page){
        List<TskTaskListBean> list = (ArrayList<TskTaskListBean>)this.findQueryPage(
                "getCusRelatedTskContact", "getCusRelatedTskContactCount", map, page);
        if(list==null){
            list = new ArrayList<TskTaskListBean>();
        }
        return new PageUtil<TskTaskListBean>(list,page);
    }

    /**
     * 客户管理 任务分页
     */
    public PageUtil<CustModelTaskDetail> getTaskListForCustomerModel(Map<String, Object> parameters, Page page) {
        ArrayList<CustModelTaskDetail> list = (ArrayList<CustModelTaskDetail>) this.findQueryPage(
                "getTaskListForCustomerModel", "getTaskCountForCustomerModel", parameters, page);
        if (list == null) {
            list = new ArrayList<CustModelTaskDetail>();
        }
        return new PageUtil<CustModelTaskDetail>(list, page);
    }
    /**
     * 查询组织机构以及用户树
     * @return
     */
    public List<CustomerAttrPlugin> getCustomerAttrPluginList(Map<String, Object> parameters){
        return this.getSqlMapClientTemplate().queryForList("GetCustomerAttrPluginList",parameters);
    }

    /**
     * 任务完成统计报表(分配者)
     * @param parameters
     * @return
     */
    public List<TskContactReportBean> getTaskReportAssignList(Map<String, Object> parameters){
        return this.getSqlMapClientTemplate().queryForList("GetTaskReportAssignList",parameters);
    }
    /**
     * 任务完成统计报表
     * @param parameters
     * @return
     */
    public List<TskContactReportBean> getTaskReportList(Map<String, Object> parameters){
        return this.getSqlMapClientTemplate().queryForList("GetTaskReportList",parameters);
    }

    /**
     * 任务完成统计报表明细列表
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<CusRelatedTskContactBean> getTaskReportDetailPage(Map<String, Object> parameters, Page page,Integer searchType){
        ArrayList<CusRelatedTskContactBean> list = null;
        if(searchType==1){//执行者
            list=(ArrayList<CusRelatedTskContactBean>) this.findQueryPage(
                    "GetTaskReportDetailPageMap", "GetTaskReportDetailCount", parameters, page);
        }else{//分配者
            list=(ArrayList<CusRelatedTskContactBean>) this.findQueryPage(
                    "GetTaskReportAssaginDetailPageMap", "GetTaskReportAssaginDetailCount", parameters, page);
        }
        if (list == null) {
            list = new ArrayList<CusRelatedTskContactBean>();
        }
        return new PageUtil<CusRelatedTskContactBean>(list, page);
    }
    /**
     * 编辑拜访记录查询下次联系任务
     */
    public TskContact getNextTaskForRecord(Map<String,Object> map){
        return (TskContact) this.getSqlMapClientTemplate().queryForObject("getNextTaskForRecord", map);
    }
}



