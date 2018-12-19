package com.banger.mobile.dao.system;

import java.util.List;
import java.util.Map;
import com.banger.mobile.domain.model.livingCondition.LivingCondition;

public interface CrmLivingConditionDao {
	/**
     * 添加一种居住状况
     * @param livingCondition
     */
    public void addCrmLivingCondition(LivingCondition livingCondition);
    
    /**
     * 修改居住状况
     * @param livingCondition
     */
    public void updateCrmLivingCondition(LivingCondition livingCondition);
    
    /**
     * 删除一种居住状况
     * @param id
     */
    public void deleteCrmLivingCondition(int id);
    
    /**
     * 根据Id获取居住状况
     * @return
     */
    public LivingCondition getCrmLivingConditionById(int id);
    
    /**
     * 获取所有未删除的居住状况
     * @return
     */
    public List<LivingCondition> getAllCrmLivingCondition();
    
    /**
     * 根据居住状况称获取拥有相同居住状况名称的数据
     * @param livingCondition
     * @return
     */
    public List<LivingCondition> getSameCrmLivingConditionByName(LivingCondition livingCondition);
    
    /**
     * 获取现有居住状况数据按SORTNO排序最大的
     * @return
     */
    public LivingCondition getMaxSortNoCrmLivingCondition();
    
    /**
     * 获取现有居住状况数据按SORTNO排序最小的
     * @return
     */
    public LivingCondition getMinSortNoCrmLivingCondition();
    
    /**
     * 获取要移动的居住状况对象
     * @return
     */
    public LivingCondition getNeedMoveCrmLivingCondition(Map<String, Object> parameters);
}
