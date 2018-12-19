package com.banger.mobile.dao.system;

import java.util.List;
import java.util.Map;
import com.banger.mobile.domain.model.orgType.OrgType;

public interface CrmOrgTypeDao {
	/**
     * 添加一种组织形式
     * @param orgType
     */
    public void addCrmOrgType(OrgType orgType);
    
    /**
     * 修改组织形式
     * @param orgType
     */
    public void updateCrmOrgType(OrgType orgType);
    
    /**
     * 删除一种组织形式
     * @param id
     */
    public void deleteCrmOrgType(int id);
    
    /**
     * 根据Id获取组织形式
     * @return
     */
    public OrgType getCrmOrgTypeById(int id);
    
    /**
     * 获取所有未删除的组织形式
     * @return
     */
    public List<OrgType> getAllCrmOrgType();
    
    /**
     * 根据组织形式称获取拥有相同组织形式名称的数据
     * @param orgType
     * @return
     */
    public List<OrgType> getSameCrmOrgTypeByName(OrgType orgType);
    
    /**
     * 获取现有组织形式数据按SORTNO排序最大的
     * @return
     */
    public OrgType getMaxSortNoCrmOrgType();
    
    /**
     * 获取现有组织形式数据按SORTNO排序最小的
     * @return
     */
    public OrgType getMinSortNoCrmOrgType();
    
    /**
     * 获取要移动的组织形式对象
     * @return
     */
    public OrgType getNeedMoveCrmOrgType(Map<String, Object> parameters);
}
