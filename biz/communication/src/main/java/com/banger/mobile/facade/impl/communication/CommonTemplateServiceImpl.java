package com.banger.mobile.facade.impl.communication;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.communication.CommonTemplateDao;
import com.banger.mobile.domain.model.communication.CommTemplate;
import com.banger.mobile.facade.communication.CommonTemplateService;

public class CommonTemplateServiceImpl implements CommonTemplateService {

	private CommonTemplateDao commonTemplateDao;

	public void setCommonTemplateDao(CommonTemplateDao commonTemplateDao) {
		this.commonTemplateDao = commonTemplateDao;
	}

	public Integer insertTemplate(CommTemplate commTemplate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("templateName", commTemplate.getTemplateName());
		map.put("partitionId", commTemplate.getPartitionId());
		
		List<CommTemplate> list = commonTemplateDao
				.getCommTemplateListCountByPid(commTemplate.getPartitionId());
		if (list.size() >= 8) {
			return -3;
		} else if (commonTemplateDao.getCommTemplateByTitle(map) > 0) {
			return -2;
		} else {
			return commonTemplateDao.insertTemplate(commTemplate);
		}
	}

	public Integer updateTemplate(CommTemplate commTemplate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("templateName", commTemplate.getTemplateName());
		map.put("templateId", commTemplate.getTemplateId());
		map.put("partitionId", commTemplate.getPartitionId());
		if (commonTemplateDao.getCommTemplateByTitle(map) > 0) {
			return -2;
		} else {
			commonTemplateDao.updateTemplate(commTemplate);
			return 0;
		}
	}

	public Boolean deleteTemplate(CommTemplate commTemplate) {
		return commonTemplateDao.deleteTemplate(commTemplate);
	}

	public Boolean moveUpTemplate(CommTemplate commTemplate) {
		return moveTemplate(commTemplate.getTemplateId(), "up");
	}

	public Boolean moveDownTemplate(CommTemplate commTemplate) {
		return moveTemplate(commTemplate.getTemplateId(), "down");
	}

	public CommTemplate getCommTemplate(Integer id) {
		return commonTemplateDao.getCommTemplate(id);
	}

	public PageUtil<CommTemplate> getCommTemplateList(
			Map<String, Object> parameters, Page page) {
		return commonTemplateDao.getCommTemplateList(parameters, page);
	}

	private boolean moveTemplate(Integer id, String type) {
		try {
			CommTemplate commTemplate = this.getCommTemplate(id);
			commTemplate.setUpdateDate(new Timestamp(new Date().getTime()));
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("type", type);
			map.put("orderId", commTemplate.getTemplateOrder());
			CommTemplate baseCommTemplate = commonTemplateDao
					.getDesCommTemplate(map);
			baseCommTemplate.setUpdateDate(new Timestamp(new Date().getTime()));
			int orderId = baseCommTemplate.getTemplateOrder();
			baseCommTemplate.setTemplateOrder(commTemplate.getTemplateOrder());
			commTemplate.setTemplateOrder(orderId);
			commonTemplateDao.updateTemplate(commTemplate);
			commonTemplateDao.updateTemplate(baseCommTemplate);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<CommTemplate> getCommTemplateListByPid(Integer pId) {
		return commonTemplateDao.getCommTemplateListByPid(pId);
	}

}
