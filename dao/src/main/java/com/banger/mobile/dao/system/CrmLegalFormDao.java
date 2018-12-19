package com.banger.mobile.dao.system;

import java.util.List;
import java.util.Map;
import com.banger.mobile.domain.model.legalForm.LegalForm;

public interface CrmLegalFormDao {
	/**
     * 添加一种法律形式
     * @param legalForm
     */
    public void addCrmLegalForm(LegalForm legalForm);
    
    /**
     * 修改法律形式
     * @param legalForm
     */
    public void updateCrmLegalForm(LegalForm legalForm);
    
    /**
     * 删除一种法律形式
     * @param id
     */
    public void deleteCrmLegalForm(int id);
    
    /**
     * 根据Id获取法律形式
     * @return
     */
    public LegalForm getCrmLegalFormById(int id);
    
    /**
     * 获取所有未删除的法律形式
     * @return
     */
    public List<LegalForm> getAllCrmLegalForm();
    
    /**
     * 根据法律形式称获取拥有相同法律形式名称的数据
     * @param legalForm
     * @return
     */
    public List<LegalForm> getSameCrmLegalFormByName(LegalForm legalForm);
    
    /**
     * 获取现有法律形式数据按SORTNO排序最大的
     * @return
     */
    public LegalForm getMaxSortNoCrmLegalForm();
    
    /**
     * 获取现有法律形式数据按SORTNO排序最小的
     * @return
     */
    public LegalForm getMinSortNoCrmLegalForm();
    
    /**
     * 获取要移动的法律形式对象
     * @return
     */
    public LegalForm getNeedMoveCrmLegalForm(Map<String, Object> parameters);
}
