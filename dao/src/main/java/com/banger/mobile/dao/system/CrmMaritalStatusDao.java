package com.banger.mobile.dao.system;

import java.util.List;
import java.util.Map;
import com.banger.mobile.domain.model.maritalStatus.MaritalStatus;

public interface CrmMaritalStatusDao {
	/**
     * 添加一种婚姻状况
     * @param maritalStatus
     */
    public void addCrmMaritalStatus(MaritalStatus maritalStatus);
    
    /**
     * 修改婚姻状况
     * @param maritalStatus
     */
    public void updateCrmMaritalStatus(MaritalStatus maritalStatus);
    
    /**
     * 删除一种婚姻状况
     * @param id
     */
    public void deleteCrmMaritalStatus(int id);
    
    /**
     * 根据Id获取婚姻状况
     * @return
     */
    public MaritalStatus getCrmMaritalStatusById(int id);
    
    /**
     * 获取所有未删除的婚姻状况
     * @return
     */
    public List<MaritalStatus> getAllCrmMaritalStatus();
    
    /**
     * 根据婚姻状况称获取拥有相同婚姻状况名称的数据
     * @param maritalStatus
     * @return
     */
    public List<MaritalStatus> getSameCrmMaritalStatusByName(MaritalStatus maritalStatus);
    
    /**
     * 获取现有婚姻状况数据按SORTNO排序最大的
     * @return
     */
    public MaritalStatus getMaxSortNoCrmMaritalStatus();
    
    /**
     * 获取现有婚姻状况数据按SORTNO排序最小的
     * @return
     */
    public MaritalStatus getMinSortNoCrmMaritalStatus();
    
    /**
     * 获取要移动的婚姻状况对象
     * @return
     */
    public MaritalStatus getNeedMoveCrmMaritalStatus(Map<String, Object> parameters);
}
