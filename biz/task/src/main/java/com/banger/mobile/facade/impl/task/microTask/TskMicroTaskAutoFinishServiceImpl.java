package com.banger.mobile.facade.impl.task.microTask;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.constants.TransportConstants;
import com.banger.mobile.dao.microTask.TskMicroTaskTargetDao;
import com.banger.mobile.domain.model.data.CustomerData;
import com.banger.mobile.domain.model.microTask.TskMicroTaskTarget;
import com.banger.mobile.domain.model.record.RecordInfo;
import com.banger.mobile.facade.loan.LnLoanService;
import com.banger.mobile.facade.microTask.TskMicroTaskAutoFinishService;

/**
 * Created with IntelliJ IDEA. User: yuanme Date: 13-3-29 Time: 下午1:31 To change
 * this template use File | Settings | File Templates.
 */
public class TskMicroTaskAutoFinishServiceImpl implements
		TskMicroTaskAutoFinishService {
	private TskMicroTaskTargetDao tskMicroTaskTargetDao;
	private LnLoanService lnLoanService;

	// 录音文件上传的时候自动完成电话营销任务

	public void autoFinish(RecordInfo info, CustomerData data) {
		if (info != null) {
			// 判断录音来源：pad或者话机
			if (info.getRecordSource() != null
					&& info.getRecordSource() == TransportConstants.RECORD_SOURCE_PAD) {
				// 如果是pad
				if (info.getCustomerId() != null && info.getCustomerId() > 0) {
					// 如果是在行客户
					Map<String, Object> paras = new HashMap<String, Object>();
					paras.put("taskId", data.getTaskId());
					paras.put("userId", info.getUserId());
					paras.put("customerId", info.getCustomerId());
					paras.put("currentDate", Calendar.getInstance().getTime());
					paras.put("recordInfoId", info.getRecordInfoId());
					paras.put("callDate", info.getStartDate());
					paras.put("callTime", info.getCallTime());
					List<TskMicroTaskTarget> list = tskMicroTaskTargetDao
							.getMicroTaskTargetByParameterMap(paras);
					if (list.size() > 0) {
						// 如果有记录，表示已经添加的任务执行目标
						tskMicroTaskTargetDao.autoFinishInSystem(paras);
					} else {
						// 如果没有记录，先判断姓名和电话号码是否存在，
					    // 如果存在表示，web端添加的时候是非在行，pad执行的时候新建了客户，需要覆盖这一笔
					    Map<String, Object> paras2 = new HashMap<String, Object>();
					    paras2.put("taskId", data.getTaskId());
					    paras2.put("userId", info.getUserId());
					    paras2.put("phoneNumber", info.getRemotePhone());
					    paras2.put("recordInfoId", info.getRecordInfoId());
					    paras2.put("callDate", info.getStartDate());
					    paras2.put("callTime", info.getCallTime());
					    paras2.put("currentDate", Calendar.getInstance().getTime());
	                    List<TskMicroTaskTarget> list2 = tskMicroTaskTargetDao
	                            .getMicroTaskTargetByParameterMap(paras2);
	                    if (list2.size() > 0) {
	                        paras2.put("customerId", info.getCustomerId());
	                        tskMicroTaskTargetDao.autoFinishOutSystemOverride(paras2);
	                    } else {
	                      //则新增一笔
	                        TskMicroTaskTarget tskMicroTaskTarget = new TskMicroTaskTarget();
	                        tskMicroTaskTarget.setTaskId(data.getTaskId());
	                        tskMicroTaskTarget.setUserId(info.getUserId());
	                        tskMicroTaskTarget.setCustomerId(info.getCustomerId());
	                        tskMicroTaskTarget.setIsFinish(1);
	                        tskMicroTaskTarget.setCallDate(info.getStartDate());
	                        tskMicroTaskTarget.setCallTime(info.getCallTime());
	                        tskMicroTaskTarget.setRemark(info.getRemark());
	                        tskMicroTaskTarget.setRecordInfoId(info
	                                .getRecordInfoId());
	                        tskMicroTaskTarget.setCreateDate(Calendar.getInstance()
	                                .getTime());
	                        tskMicroTaskTarget.setCreateUser(info.getUserId());
	                        Integer count = lnLoanService.getLoanCountByCusId(info
	                                .getCustomerId());
	                        if (count != null && count > 0) {
	                            tskMicroTaskTarget.setIsOldCustomer(1);
	                        }
	                        tskMicroTaskTargetDao.addTaskTarget(tskMicroTaskTarget);
	                    }
					}
				} else {
					// 如果是非在行客户
					Map<String, Object> paras = new HashMap<String, Object>();
					paras.put("taskId", data.getTaskId());
					paras.put("userId", info.getUserId());
					paras.put("customerName", info.getCustomerName());
					paras.put("phoneNumber", info.getRemotePhone());
					paras.put("recordInfoId", info.getRecordInfoId());
					paras.put("callDate", info.getStartDate());
					paras.put("callTime", info.getCallTime());
					paras.put("currentDate", Calendar.getInstance().getTime());
					List<TskMicroTaskTarget> list = tskMicroTaskTargetDao
							.getMicroTaskTargetByParameterMap(paras);
					if (list.size() > 0) {
						// 如果有记录，表示已经添加的任务执行目标
						tskMicroTaskTargetDao.autoFinishOutSystem(paras);
					} else {
						// 如果没有记录，则新增一笔
						TskMicroTaskTarget tskMicroTaskTarget = new TskMicroTaskTarget();
						tskMicroTaskTarget.setTaskId(data.getTaskId());
						tskMicroTaskTarget.setUserId(info.getUserId());
						tskMicroTaskTarget.setCustomerName(info
								.getCustomerName());
						tskMicroTaskTarget
								.setPhoneNumber(info.getRemotePhone());
						tskMicroTaskTarget.setIsFinish(1);
						tskMicroTaskTarget.setCallDate(info.getStartDate());
						tskMicroTaskTarget.setCallTime(info.getCallTime());
						tskMicroTaskTarget.setRemark(info.getRemark());
						tskMicroTaskTarget.setRecordInfoId(info
								.getRecordInfoId());
						tskMicroTaskTarget.setCreateDate(Calendar.getInstance()
								.getTime());
						tskMicroTaskTarget.setCreateUser(info.getUserId());
						tskMicroTaskTarget.setIsOldCustomer(0);
						tskMicroTaskTargetDao.addTaskTarget(tskMicroTaskTarget);
					}
				}
			}
			if (info.getRecordSource() != null
					&& info.getRecordSource() == TransportConstants.RECORD_SOURCE_PHONE && info.getTaskId() != null && info.getTaskId() > 0) {
				// 如果是话机
				// String phoneNumber = info.getRemotePhone();
				// Integer userId = info.getUserId();
				// Integer customerId = info.getCustomerId();
				// Date currentDate = Calendar.getInstance().getTime();
				// 任务的开始时间和结束时间精确到天
				// DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Map<String, Object> params = new HashMap<String, Object>();
				// // 判断是否在行客户
				// if (customerId != null && customerId > 0) {
				// params.put("customerId", customerId);
				// } else {
				// params.put("phoneNumber", phoneNumber);
				// }
				// params.put("userId", userId);
				// params.put("currentDate", dateFormat.format(new Date()));
			    params.put("recordInfoId", info.getRecordInfoId());
				params.put("taskTargetId", info.getTaskId());
				tskMicroTaskTargetDao.autoFinish(params);
			}
		}
	}

	// 录音文件上传的时候自动完成实地营销任务
	public void autoFinishFoot(RecordInfo info, Integer taskId) {
		if (info != null) {
			Integer userId = info.getUserId();
			Integer customerId = info.getCustomerId();
			Date currentDate = Calendar.getInstance().getTime();

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("taskId", taskId);
			params.put("userId", userId);
			params.put("customerId", customerId);
			params.put("currentDate", currentDate);
			params.put("recordDate", info.getStartDate());
			params.put("recordInfoId", info.getRecordInfoId());
			tskMicroTaskTargetDao.autoFinishFoot(params);

		}
	}

	/** getter & setter **/
	public void setTskMicroTaskTargetDao(
			TskMicroTaskTargetDao tskMicroTaskTargetDao) {
		this.tskMicroTaskTargetDao = tskMicroTaskTargetDao;
	}

	public void setLnLoanService(LnLoanService lnLoanService) {
		this.lnLoanService = lnLoanService;
	}

}
