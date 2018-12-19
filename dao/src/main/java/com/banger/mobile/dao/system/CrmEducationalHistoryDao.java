package com.banger.mobile.dao.system;

import java.util.List;
import java.util.Map;
import com.banger.mobile.domain.model.educationalHistory.EducationalHistory;

public interface CrmEducationalHistoryDao {

	/**
     * 添加一种教育程度
     * @param educationalHistory
     */
    public void addCrmEducationalHistory(EducationalHistory educationalHistory);
    
    /**
     * 修改教育程度
     * @param educationalHistory
     */
    public void updateCrmEducationalHistory(EducationalHistory educationalHistory);
    
    /**
     * 删除一种教育程度
     * @param id
     */
    public void deleteCrmEducationalHistory(int id);
    
    /**
     * 根据Id获取教育程度
     * @return
     */
    public EducationalHistory getCrmEducationalHistoryById(int id);
    
    /**
     * 获取所有未删除的教育程度
     * @return
     */
    public List<EducationalHistory> getAllCrmEducationalHistory();
    
    /**
     * 根据教育程度称获取拥有相同教育程度名称的数据
     * @param crmCustomerType
     * @return
     */
    public List<EducationalHistory> getSameCrmEducationalHistoryByName(EducationalHistory educationalHistory);
    
    /**
     * 获取现有教育程度数据按SORTNO排序最大的
     * @return
     */
    public EducationalHistory getMaxSortNoCrmEducationalHistory();
    
    /**
     * 获取现有教育程度数据按SORTNO排序最小的
     * @return
     */
    public EducationalHistory getMinSortNoCrmEducationalHistory();
    
    /**
     * 获取要移动的教育程度对象
     * @return
     */
    public EducationalHistory getNeedMoveCrmEducationalHistory(Map<String, Object> parameters);
    
}
