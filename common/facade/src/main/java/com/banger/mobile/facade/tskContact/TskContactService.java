package com.banger.mobile.facade.tskContact;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.Region;

import net.sf.json.JSONArray;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.Enum.task.EnumTask;
import com.banger.mobile.domain.model.tskContact.CusRelatedTskContactBean;
import com.banger.mobile.domain.model.tskContact.TalkTask;
import com.banger.mobile.domain.model.tskContact.CustModelTaskDetail;
import com.banger.mobile.domain.model.tskContact.TskContact;
import com.banger.mobile.domain.model.tskContact.TskContactReportBean;
import com.banger.mobile.domain.model.tskContact.TskTaskListBean;


/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：Dec 17, 2012 11:00:02 AM
 * 类说明
 */
public interface TskContactService {
	

	/**
    * 任务全景展示
    * @param map
    * @return
    */
    public List<TskTaskListBean> showTskViewList(Map<String, Object> map);
   
	
	/**
	 * 联系任务列表
	 */
	public PageUtil<TskTaskListBean> getTskTaskPage(Map<String, Object> parameters, Page page);
	
	/**
     * 任务所对应的联系量(业务主管)
     * isfinish=true 为已完成
     */
    public Integer getTaskConnactCount(int taskId,boolean isfinish);
    
    /**
     * 任务所对应的联系量(客户经理)
     * isfinish=true 为已完成
     */
    public Integer getTaskConnactCount(int taskId,boolean isfinish,int userId);
	
	/**
	 * 新建联系任务
	 * @param tskContact
	 * @return
	 */
	public Integer insertTskContact(TskContact tskContact);
	
	/**
	 * 编辑联系任务
	 * @param tskContact
	 */
	public void updateTskContact(TskContact tskContact);
	
	/**
	 * 删除联系任务
	 * @param contactId
	 */
	public void deleteTskContact(Integer contactId);
	
	/**
	 * 彻底删除联系任务
	 * @param contactId:任务ID  isComplete:是否彻底删除
	 */
	public void deleteTskContact(Integer contactId, boolean isComplete);
	
	/**
	 * 中止/重启任务
	 * @param tskContact
	 */
	public void  suspendTskContact(TskContact tskContact);
	
	/**
	 * 确认新建任务
	 * @param contactId
	 */
	public void confirmAddTask(Integer contactId);

	/**
	 * 根据任务ID获取联系任务实体
	 * @param contactId
	 * @return
	 */
	public TskContact getTskContactById(Integer contactId);
	
	/**
	 * 根据任务ID获取联系任务实体
	 * @param contactId
	 * @return
	 */
	public TskContact getTskContactById(Integer contactId,boolean isdel);
	
    /**
     * 过期的任务总数（已不用）
     * @param int userId
     * @return int 总数
     */
    public int getExpiredTaskCount(int userId);
    
    /**
     * 工作台任务提醒
     * @param userId
     * @return
     */
    public int getMainTskTaskCount(int userId);
    /**
     * 通话窗口新建下次联系任务
     */
    public void saveTaskInTalk(TalkTask ts,int customerId);
    /**
     * 完成任务(内部客户)
     */
    public void finishTaskInTalk(Integer customerId);
    /**
     * 完成任务(外部客户)
     */
    public void finishTaskInTalk(String phoneNo);    
    
    /**
     * 关联任务的分页
     * @param map
     * @param page
     * @return
     */
    public PageUtil<TskTaskListBean> getCusRelatedTskContact(Map<String, Object> map,Page page);

    /**
     * 客户管理 任务分页
     */
    public PageUtil<CustModelTaskDetail> getTaskListForCustomerModel(int customerId, Page page);
    /**
     * 查询组织机构以及用户树
     * @return
     */
    public JSONArray getCustomerAttrPluginList(Map<String, Object> parameters);
    
    /**
     * 任务完成统计报表
     * @param parameters
     * @return
     */
    public List<TskContactReportBean> getTaskReportList(Map<String, Object> parameters,Integer searchType);

    /**
     * 任务完成统计报表明细列表
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<CusRelatedTskContactBean> getTaskReportDetailPage(Map<String, Object> parameters,String flag,Integer searchType,Page page);
    /**
     * 数据表转化为Excel格式
     * @param list
     * @param userName
     * @return
     */
    public HSSFWorkbook exportTaskReportExcel(List<TskContactReportBean> list, String userName);
    /**
     * 编辑拜访记录查询下次联系任务
     */
    public TskContact getNextTaskForRecord(Integer customerId,Integer userId);
    /**
     * 联系记录
     * 完成任务(内部客户)
     * recordDate:联系记录 yyyy-MM-dd
     */
    public void finishTaskInTalk(Integer customerId,String recordDate,int userId);
    /**
     * 联系记录
     * 完成任务(外部客户)
     * recordDate:联系记录 yyyy-MM-dd
     */
    public void finishTaskInTalk(String phoneNo,String recordDate,int userId);
}



