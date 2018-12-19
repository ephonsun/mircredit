package com.banger.mobile.webapp.action.system;

import java.util.List;

import net.sf.json.JSONArray;

import banger.json.JsonArray;

import com.banger.mobile.domain.model.pad.QuestionAnswer;
import com.banger.mobile.domain.model.system.SysTalklimitConfig;
import com.banger.mobile.facade.system.SysTalkLimitConfigService;
import com.banger.mobile.webapp.action.BaseAction;

public class SysTalklimitConfigAction extends BaseAction {

	/**
	 * 有效通话量设置
	 */
	private static final long serialVersionUID = 9010956063319615188L;

	private SysTalkLimitConfigService sysTalkLimitConfigService;

	private List<SysTalklimitConfig> sysTalklimitConfigList;

	private String configList;
	
	private String delConfigList;

	public String getDelConfigList() {
		return delConfigList;
	}

	public void setDelConfigList(String delConfigList) {
		this.delConfigList = delConfigList;
	}

	public String getConfigList() {
		return configList;
	}

	public void setConfigList(String configList) {
		this.configList = configList;
	}

	private boolean openTalkLimitConfig;

	
	

	public boolean isOpenTalkLimitConfig() {
		return openTalkLimitConfig;
	}

	public void setOpenTalkLimitConfig(boolean openTalkLimitConfig) {
		this.openTalkLimitConfig = openTalkLimitConfig;
	}

	public List<SysTalklimitConfig> getSysTalklimitConfigList() {
		return sysTalklimitConfigList;
	}

	public void setSysTalklimitConfigList(
			List<SysTalklimitConfig> sysTalklimitConfigList) {
		this.sysTalklimitConfigList = sysTalklimitConfigList;
	}

	public SysTalkLimitConfigService getSysTalkLimitConfigService() {
		return sysTalkLimitConfigService;
	}

	public void setSysTalkLimitConfigService(
			SysTalkLimitConfigService sysTalkLimitConfigService) {
		this.sysTalkLimitConfigService = sysTalkLimitConfigService;
	}

	/**
	 * 显示列表页面
	 * 
	 * @return
	 */
	public String showTalklimitList() {
		try {
			sysTalklimitConfigList = sysTalkLimitConfigService
					.getTalkLimitConfigs();
			openTalkLimitConfig = sysTalkLimitConfigService.getIsTalklimitConfigOpen();
			return SUCCESS;
		} catch (Exception e) {
			log.error("showTalklimitList action error:" + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 设置有效通话量 开关
	 * 
	 * @param isOpen
	 */
	public void setTalklimitConfig() {
		sysTalkLimitConfigService.setIsTalklimitConfigRemind(openTalkLimitConfig);
	}
	
	/**
	 * 获取有效通话量开关
	 * @return
	 */
	public boolean getTalklimitConfigSwitch(){
		return sysTalkLimitConfigService.getIsTalklimitConfigOpen();
	}

	/**
	 * 保存有效通话量列表
	 */
	public void saveTalklimitConfigList() {
		JSONArray jsonArray = JSONArray.fromObject(configList);
		List<SysTalklimitConfig> configList = jsonArray.toList(jsonArray,
				SysTalklimitConfig.class);
		
//		JSONArray jsonArray2 = JSONArray.fromObject(delConfigList);
//		List<SysTalklimitConfig> delConfigList = jsonArray.toList(jsonArray2,
//				SysTalklimitConfig.class);
//		sysTalkLimitConfigService.deleteTalklimitConfig(delConfigList);
		sysTalkLimitConfigService.saveTalklimitConfig(configList);
		renderText("0");
	}

	
}
