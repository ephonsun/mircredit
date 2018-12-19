/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :基础数据-沟通进度 的action类
 * Author     :wumh
 * Create Date:May 22, 2012
 */
package com.banger.mobile.webapp.action.system;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.banger.mobile.domain.Enum.system.EnumSystem;
import com.banger.mobile.domain.model.system.CommProgress;
import com.banger.mobile.facade.log.OpeventLogService;
import com.banger.mobile.facade.record.RecordInfoService;
import com.banger.mobile.facade.system.CommProgressService;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：May 22, 2012 1:10:41 PM 类说明
 */
public class CommProgressAction extends BaseAction {

	private static final long serialVersionUID = -7144290291898296296L;

	private CommProgressService commProgressService;
	private RecordInfoService recordInfoService;
	private OpeventLogService opeventLogService;

	private List<CommProgress> commProList;
	private String commProgressName;
	private CommProgress commProgress;
	private int commProgressId;
	private int id;
	private String moveType;
	private String oldName;

	public void setCommProgressService(CommProgressService commProgressService) {
		this.commProgressService = commProgressService;
	}

	public List<CommProgress> getCommProList() {
		return commProList;
	}

	public void setCommProList(List<CommProgress> commProList) {
		this.commProList = commProList;
	}

	public String getCommProgressName() {
		return commProgressName;
	}

	public void setCommProgressName(String commProgressName) {
		this.commProgressName = commProgressName;
	}

	/**
	 * 显示沟通进度列表
	 * 
	 * @return
	 */
	public String showCommProListPage() {
		try {
			commProList = commProgressService.getCommProgressList();
			return SUCCESS;
		} catch (Exception e) {
			log.error("showCommProListage action error:" + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 删除沟通记录
	 * 
	 * @return
	 */
	public String deleteCommProgress() {
		try {
			CommProgress commProgress = new CommProgress();
			int id = Integer.parseInt(request.getParameter("id"));
			int userId = this.getLoginInfo().getUserId();
			commProgress.setCommProgressId(id);
			commProgress.setUpdateUser(userId);
			commProgressService.deleteCommProgress(commProgress);
			recordInfoService.deleteCommProgressId(id);
			return SUCCESS;
		} catch (Exception e) {
			log.error("deleteCommProgress action error:" + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 新增沟通进度
	 * 
	 * @return
	 */
	public String addCommProgress() {
		try {
			CommProgress commProgress = new CommProgress();
			commProgress.setCommProgressName(commProgressName.trim());
			commProgress.setIsDel(0);
			int userId = this.getLoginInfo().getUserId();
			commProgress.setCreateUser(userId);
			commProgress.setUpdateUser(userId);
			commProgressService.insertCommProgress(commProgress);
			return SUCCESS;
		} catch (Exception e) {
			log.error("addCommProgress action error:" + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 新增沟通进度的校验
	 */
	public void validateAddCommProgress() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		try {
			PrintWriter out = response.getWriter();
			boolean isExistName = commProgressService
					.isExistCommProName(commProgressName);
			if (isExistName) {
				out.print(EnumSystem.COMMPROGRESSNAME_REPEAT.getValue());
			}
		} catch (Exception e) {
		}
	}

	/**
	 * 上移下移沟通进度
	 * 
	 * @return
	 */
	public void moveCommProgress() {
		try {
			boolean result;
			PrintWriter out = this.getResponse().getWriter();
			if (moveType.equals("up")) {
				result = commProgressService.moveUpCommProgress(id);
			} else {
				result = commProgressService.moveDownCommProgress(id);
			}
			if (result) {
				out.write("SUCCESS");
			} else {
				out.write("");
			}
		} catch (Exception e) {
			log.error("moveCommProgress action error:" + e.getMessage());
		}
	}

	public void setRecordInfoService(RecordInfoService recordInfoService) {
		this.recordInfoService = recordInfoService;
	}

	/**
	 * 获取要修改的沟通进度
	 * 
	 * @param id
	 * @return
	 */
	public String getUpdateCommProgress() {
		try {
			commProgress = commProgressService.getCommProgressById(id);
			return SUCCESS;
		} catch (Exception e) {
			log.error("getUpdateCommProgress action error:" + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 修改沟通进度
	 * 
	 * @return
	 * @throws IOException 
	 */
	public void updateCommProgress() {	
		try {						
			int userId = this.getLoginInfo().getUserId();
			CommProgress commPro = new CommProgress();
			commPro.setUpdateUser(userId);
			commPro.setCommProgressId(commProgressId);
			commPro.setCommProgressName(commProgressName.trim());
            String result="";
			boolean isExistName = commProgressService.isExistCommProName(commProgressName.trim());
			if ((!oldName.trim().equals(commProgressName.trim()))
					&& isExistName) {
				result = EnumSystem.COMMPROGRESSNAME_REPEAT.getValue();
			}else{
				commProgressService.updateCommProgress(commPro);
				result = "SUCCESS";
			}
            PrintWriter out = this.getResponse().getWriter();
            out.write(result);
			
		} catch (Exception e) {
			log.error("updateCommProgress action error:" + e.getMessage());	
		}
	}

	public CommProgress getCommProgress() {
		return commProgress;
	}

	public void setCommProgress(CommProgress commProgress) {
		this.commProgress = commProgress;
	}

	public int getCommProgressId() {
		return commProgressId;
	}

	public void setCommProgressId(int commProgressId) {
		this.commProgressId = commProgressId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMoveType() {
		return moveType;
	}

	public void setMoveType(String moveType) {
		this.moveType = moveType;
	}

	public String getOldName() {
		return oldName;
	}

	public void setOldName(String oldName) {
		this.oldName = oldName;
	}

	public void setOpeventLogService(OpeventLogService opeventLogService) {
		this.opeventLogService = opeventLogService;
	}

}
