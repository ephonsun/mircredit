package com.banger.mobile.dao.communication.iBatis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.communication.CommonTemplateDao;
import com.banger.mobile.domain.model.communication.CommTemplate;
import com.banger.mobile.ibatis.GenericDaoiBatis;

public class CommonTemplateDaoiBatis extends GenericDaoiBatis implements
		CommonTemplateDao {

	public CommonTemplateDaoiBatis(Class persistentClass) {
		super(persistentClass);
	}

	public CommonTemplateDaoiBatis() {
		super(CommonTemplateDaoiBatis.class);
	}

	public Integer insertTemplate(CommTemplate commTemplate) {
		return (Integer) this.getSqlMapClientTemplate().insert(
				"addCommTemplate", commTemplate);
	}

	public Boolean updateTemplate(CommTemplate commTemplate) {
		this.getSqlMapClientTemplate().update("updateCommTemplate", commTemplate);
		return true;
	}

	public Boolean deleteTemplate(CommTemplate commTemplate) {
		this.getSqlMapClientTemplate().delete("deleteCommTemplate",commTemplate);
		return true;
	}

	public CommTemplate getCommTemplate(Integer id) {
		return (CommTemplate) this.getSqlMapClientTemplate().queryForObject("getCommTemplateById",id);
	}

	public PageUtil<CommTemplate> getCommTemplateList(Map<String, Object> parameters, Page page) {
		ArrayList<CommTemplate> list = (ArrayList<CommTemplate>) this.findQueryPage("getCommTemplateList",
				"getCommTemplateListCount", parameters, page);
		if (list == null) {
			list = new ArrayList<CommTemplate>();
		}
		return new PageUtil<CommTemplate>(list, page);
	}

	public CommTemplate getDesCommTemplate(Map<String, Object> map) {
		return (CommTemplate) this.getSqlMapClientTemplate().queryForObject("getDesCommTemplate",map);
	}

	public Integer getCommTemplateByTitle(Map<String, Object> map) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("getCommTemplateByTitle",map);
	}

	public List<CommTemplate> getCommTemplateListByPid(Integer pId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("partitionId", pId);
		map.put("templateState", 0);
		return this.getSqlMapClientTemplate().queryForList("getCommTemplateListByPid",map);
	}

	public List<CommTemplate> getCommTemplateListCountByPid(Integer pId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("partitionId", pId);
		return this.getSqlMapClientTemplate().queryForList("getCommTemplateListByPid",map);
	}

}
