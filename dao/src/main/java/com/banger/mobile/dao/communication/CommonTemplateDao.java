package com.banger.mobile.dao.communication;

import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.communication.CommTemplate;

public interface CommonTemplateDao {
	
	/**
	 * 插入版块
	 * 
	 * @param CommTemplate
	 * @return
	 */
	Integer insertTemplate(CommTemplate commTemplate);

	/**
	 * 编辑版块
	 * 
	 * @param CommTemplate
	 * @return
	 */
	Boolean updateTemplate(CommTemplate commTemplate);

	/**
	 * 删除版块
	 * 
	 * @param CommTemplate
	 * @return
	 */
	Boolean deleteTemplate(CommTemplate commTemplate);

	
	/**
	 * 获取版块
	 * @param id
	 * @return
	 */
	CommTemplate getCommTemplate(Integer id);
	
	/**
	 * 获取版块通过名称 
	 * @param id
	 * @return
	 */
	Integer getCommTemplateByTitle(Map<String, Object> map);
	
	/**
	 * 获取交流区下的所有版块
	 * @return
	 */
	PageUtil<CommTemplate> getCommTemplateList(Map<String, Object> parameters, Page page);
	
	/**
	 * 取上移下移的版块
	 * @param map
	 * @return
	 */
	CommTemplate getDesCommTemplate(Map<String,Object> map);
	
	/**
	 * 通过分区ID取版块
	 * @param pId
	 * @return
	 */
	List<CommTemplate> getCommTemplateListByPid(Integer pId);
	
	/**
	 * 通过分区ID取版块总数
	 * @param pId
	 * @return
	 */
	List<CommTemplate> getCommTemplateListCountByPid(Integer pId);
	
}
