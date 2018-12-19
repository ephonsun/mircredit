package com.banger.mobile.facade.communication;

import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.communication.CommTemplate;

/**
 * 交流区分区版块
 * @author huyb
 *
 */
public interface CommonTemplateService {
	
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
	Integer updateTemplate(CommTemplate commTemplate);

	/**
	 * 删除版块
	 * 
	 * @param CommTemplate
	 * @return
	 */
	Boolean deleteTemplate(CommTemplate commTemplate);

	/**
	 * 上移版块
	 * 
	 * @param CommTemplate
	 * @return
	 */
	Boolean moveUpTemplate(CommTemplate commTemplate);

	/**
	 * 下移版块
	 * 
	 * @param CommTemplate
	 * @return
	 */
	Boolean moveDownTemplate(CommTemplate commTemplate);
	
	/**
	 * 获取版块
	 * @param id
	 * @return
	 */
	CommTemplate getCommTemplate(Integer id);
	
	/**
	 * 通过分区ID取版块
	 * @param pId
	 * @return
	 */
	List<CommTemplate> getCommTemplateListByPid(Integer pId);
	
	/**
	 * 获取交流区下的所有版块 分页
	 * @return
	 */
	PageUtil<CommTemplate> getCommTemplateList(Map<String, Object> parameters, Page page);
}
