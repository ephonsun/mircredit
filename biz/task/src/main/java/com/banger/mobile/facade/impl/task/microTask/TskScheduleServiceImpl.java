package com.banger.mobile.facade.impl.task.microTask;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.microTask.TskScheduleDao;
import com.banger.mobile.domain.model.microProduct.PdtProductCustomerBean;
import com.banger.mobile.domain.model.microTask.TskSchedule;
import com.banger.mobile.facade.microTask.TskScheduleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

public class TskScheduleServiceImpl implements TskScheduleService{
	private TskScheduleDao tskScheduleDao;

	//setter 
	public void setTskScheduleDao(TskScheduleDao tskScheduleDao) {
		this.tskScheduleDao = tskScheduleDao;
	}


	public List<TskSchedule> getScheduleListByPage(
			Map<String, Object> parameterMap , Page page) {
		return tskScheduleDao.getScheduleListByPage(parameterMap, page);
	}


	public List<Map> getAllCommProgress() {
		return tskScheduleDao.getAllCommProgress();
	}

    /**
     * 得到第一条沟通进度的comm_progress_id
     *
     * @return
     */
    public Integer getNo1CommProgressId(){
        return tskScheduleDao.getNo1CommProgressId();
    }

	public void addNewSchedule(TskSchedule tskSchedule) {
		tskScheduleDao.addNewSchedule(tskSchedule);
	}


	public void updateSchedule(TskSchedule tskSchedule) {
		tskScheduleDao.updateSchedule(tskSchedule);
	}


	public TskSchedule getScheduleById(int scheduleId) {
		return tskScheduleDao.getScheduleById(scheduleId);
	}


    public Integer judgeCustomerSchedule(Map<String, Object> map) {
        return tskScheduleDao.getCustomerScheduleCount(map);
    }

    public Integer judgeScheduleByCustomerId(Map<String, Object> paramMap) {
        return tskScheduleDao.judgeScheduleByCustomerId(paramMap);
    }


    public List<TskSchedule> getCustomerList(Map<String, Object> parameterMap, Page page) {
        return tskScheduleDao.getCustomerList(parameterMap, page);
    }


    public List<Map> getCustomerTypeList() {
        return tskScheduleDao.getCustomerTypeList();
    }


    public TskSchedule getCustomerDetail(Map<String, Object> map) {
        return tskScheduleDao.getCustomerDetail(map);
    }

    /**
     * 获取用户意向产品信息
     * @param paramMap
     * @param page
     * @return
     */
    public List<TskSchedule> getCustomerPDTProductList(Map<String, Object> paramMap,Page page) {
        paramMap.put("endDate",new Date());
        Calendar curCal = Calendar.getInstance();
        curCal.add(Calendar.MONTH,-6);
        paramMap.put("startDate",curCal.getTime());
        return tskScheduleDao.getCustomerPDTProductList(paramMap, page).getItems();
    }

    /**
     * 获取日程总数
     * @param paramMap
     * @return
     */
    public Integer getCount(Map<String, Object> paramMap){
        return tskScheduleDao.getCount(paramMap);
    }

    public Integer getCustomerPDTProductCount(Map<String, Object> paramMap) {
        return tskScheduleDao.getCustomerPDTProductCount(paramMap);
    }

    /**
     * 批量插入用户日程
     * @param list
     */
    @Transactional(readOnly = false,propagation = Propagation.REQUIRES_NEW)
    public void insertTskScheduleBatch(List<TskSchedule> list) {
        tskScheduleDao.insertTskScheduleBatch(list);
    }

    public List<PdtProductCustomerBean> getCusPdtProductListForPad(Map<String, Object> paramMap, Page page) {
        return tskScheduleDao.getCusPdtProductListForPad(paramMap,page);
    }

    /**
     * 小页卡--日程安排
     * 
     * @param paramMap
     * @param page
     * @return
     */
    public PageUtil<TskSchedule> getTskScheduleCard(Map<String, Object> paramMap, Page page){
        return tskScheduleDao.getTskScheduleCard(paramMap, page);
    }

    /**
     * 根据客户ID集，得到所有客户相关的日程信息
     * 
     * @param cusIds
     * @return
     */
    public List<TskSchedule> getScheduleByCusIds(String cusIds){
        if(StringUtils.isNotEmpty(cusIds)) {
            Map<String,Object> paramMap = new HashMap<String, Object>();
            paramMap.put("customerIds",cusIds);
            List<TskSchedule> list = tskScheduleDao.getScheduleByCusIds(paramMap);
            for (TskSchedule tskSchedule : list){
                Integer contactType = tskSchedule.getContactType();
                if(contactType != null && contactType == 1){
                    tskSchedule.setContactTypeName("电话联系");
                }else if(contactType != null && contactType == 2){
                    tskSchedule.setContactTypeName("上门拜访");
                }
            }
            return list;
        }
        return null;
    }
    /**
     * 根据客户ID集，得到所有客户相关的日程信息
     *
     * @param paramMap
     * @return
     */
    public List<TskSchedule> getScheduleByCusIds(Map<String, Object> paramMap){
        List<TskSchedule> list = tskScheduleDao.getScheduleByCusIds(paramMap);
        for (TskSchedule tskSchedule : list){
            Integer contactType = tskSchedule.getContactType();
            if(contactType != null && contactType == 1){
                tskSchedule.setContactTypeName("电话联系");
            }else if(contactType != null && contactType == 2){
                tskSchedule.setContactTypeName("上门拜访");
            }
        }
        return list;
    }

    /**
     * 根据传入的客户ID集合，得到当中没有日程的客户ID集合
     * 
     * @param customerIds
     *            传入的客户ID集合
     * @return
     */
    public String getNoScheduleCusIds(String customerIds){
        if(StringUtils.isNotEmpty(customerIds)){
            String[] cusIdArr = customerIds.split(",");
            List<Integer> cusIdList = null;
            if(cusIdArr != null){
                cusIdList = new ArrayList<Integer>();
                for (String cusId : cusIdArr){
                    cusIdList.add(Integer.parseInt(cusId));
                }
            }
            if(cusIdList != null){
                Map<String,Object> paramMap = new HashMap<String, Object>();
                paramMap.put("customerIds",customerIds);
                List<Integer> scheduleCusIdList = tskScheduleDao.getScheduleCusIds(paramMap);

                if (scheduleCusIdList != null && scheduleCusIdList.size() == 0){
                    //当前传入的用户没有相关日程
                    StringBuilder sb = new StringBuilder();
                    for (Integer cusId : cusIdList){
                        sb.append(cusId+",");
                    }
                    if(sb.length() > 0){
                        String cusIds = sb.substring(0,sb.length() - 1);
                        return cusIds;
                    }
                }else {
                    boolean isRemoved = cusIdList.removeAll(scheduleCusIdList);
                    if(isRemoved){
                        StringBuilder sb = new StringBuilder();
                        for (Integer cusId : cusIdList){
                            sb.append(cusId+",");
                        }
                        if(sb.length() > 0){
                            String cusIds = sb.substring(0,sb.length() - 1);
                            return cusIds;
                        }
                    }
                }

            }
        }

        return null;
    }

    /**
     * 合并客户的日程
     * 
     * @param needMergeCusIds
     *            需要合并的客户ID集合
     * @param mergeEndCusId
     *            合并后的客户ID集合
     */
    public void mergeCusSchedule(String needMergeCusIds, String mergeEndCusId){
        if(StringUtils.isNotEmpty(needMergeCusIds) && StringUtils.isNotEmpty(mergeEndCusId)){
            String[] cusIdArr = needMergeCusIds.split(",");
            List<Integer> cusIdList = null;
            if(cusIdArr != null){
                cusIdList = new ArrayList<Integer>();
                for (String cusId : cusIdArr){
                    cusIdList.add(Integer.parseInt(cusId));
                }
            }
            Map<String,Object> paramMap = new HashMap<String, Object>();
            paramMap.put("beforeMergeCusIds",cusIdList);
            paramMap.put("afterMergeCusId",Integer.parseInt(mergeEndCusId));
            tskScheduleDao.mergeCusSchedule(paramMap);
        }
    }

    /**
     * 删除所传入的客户ID的相关日程
     * 
     * @param customerIds
     */
    public void delCusSchedule(String customerIds){
        if(StringUtils.isNotEmpty(customerIds)) {
            String[] cusIdArr = customerIds.split(",");
            List<Integer> cusIdList = null;
            if(cusIdArr != null){
                cusIdList = new ArrayList<Integer>();
                for (String cusId : cusIdArr){
                    cusIdList.add(Integer.parseInt(cusId));
                }
            }
            Map<String,Object> paramMap = new HashMap<String, Object>();
            paramMap.put("customerIds",cusIdList);
            tskScheduleDao.deleteScheduleByCusIds(paramMap);
        }
    }

    /**
     * 得到7天内未完成的日程安排数量
     *
     * @param paramMap
     * @return
     */
    public Integer getInTimeScheduleCount(Map<String, Object> paramMap){
        Calendar curCal = Calendar.getInstance();
        paramMap.put("startDate",curCal.getTime());
        curCal.add(Calendar.DAY_OF_MONTH,7);
        paramMap.put("endDate",curCal.getTime());
        return tskScheduleDao.getInTimeScheduleCount(paramMap);
    }

    /**
     * 得到7天内未完成的日程安排列表(前10条)
     *
     * @param paramMap
     * @return
     */
    public List<TskSchedule> getInTimeScheduleList(Map<String, Object> paramMap){
        paramMap.put("startRow",1);
        paramMap.put("endRow",10);

        Calendar curCal = Calendar.getInstance();
        paramMap.put("startDate",curCal.getTime());
        curCal.add(Calendar.DAY_OF_MONTH,7);
        paramMap.put("endDate",curCal.getTime());
        return tskScheduleDao.getInTimeScheduleList(paramMap);
    }

    /**
     * 得到7天内未完成的日程安排列表(分页查询)
     * 
     * @param parameterMap
     * @param page
     * @return
     */
    public List<TskSchedule> getInTimeScheduleListByPage(
            Map<String, Object> parameterMap, Page page) {
        return tskScheduleDao.getInTimeScheduleListByPage(parameterMap, page);
    }

    /**
     * 查出用户未完成的日程(如果出现多条，则返回最近的一条)
     *
     * @param paramMap
     * @return
     */
    public TskSchedule getUnFinishedSchedule(Map<String, Object> paramMap){
        return tskScheduleDao.getUnFinishedSchedule(paramMap);
    }

    /**
     * 得到下一个scheduleId
     * 
     * @return
     */
    public Integer getNextScheduleId(){
        return tskScheduleDao.getNextScheduleId();
    }

    /**
     * 添加新的日程
     *
     * @param tskSchedule
     */
    public void insertTskSchedule(TskSchedule tskSchedule){
        tskScheduleDao.insertTskSchedule(tskSchedule);
    }

    /**
     * 获得某个客户的日程数量
     *
     * @param customerId
     * @return
     */
    public Integer getCusScheduleCount(Integer customerId){
        Map<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("customerId",customerId);
        return tskScheduleDao.getCusScheduleCount(paramMap);
    }


    /**
     * @param paras
     * @see com.banger.mobile.facade.microTask.TskScheduleService#updateTaskSchedule(java.util.Map)
     */
    public void updateTaskSchedule(Map<String, Object> paras) {
        tskScheduleDao.updateTaskSchedule(paras);
    }


    @Override
    public Integer getTskScheduleCountCard(Map<String, Object> map) {
        return tskScheduleDao.getTskScheduleCountCard(map);
    }
}
