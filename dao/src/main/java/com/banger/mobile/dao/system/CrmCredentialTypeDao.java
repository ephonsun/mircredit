package com.banger.mobile.dao.system;

import java.util.List;
import java.util.Map;

import com.banger.mobile.domain.model.credentialType.CredentialType;

public interface CrmCredentialTypeDao {
	/**
     * 添加一种证件类型
     * @param credentialType
     */
    public void addCrmCredentialType(CredentialType credentialType);
    
    /**
     * 修改证件类型
     * @param credentialType
     */
    public void updateCrmCredentialType(CredentialType credentialType);
    
    /**
     * 删除一种证件类型
     * @param id
     */
    public void deleteCrmCredentialType(int id);
    
    /**
     * 根据Id获取证件类型
     * @return
     */
    public CredentialType getCrmCredentialTypeById(int id);
    
    /**
     * 获取所有未删除的证件类型
     * @return
     */
    public List<CredentialType> getAllCrmCredentialType();
    
    /**
     * 根据证件类型称获取拥有相同证件类型名称的数据
     * @param crmCustomerType
     * @return
     */
    public List<CredentialType> getSameCrmCredentialTypeByName(CredentialType credentialType);
    
    /**
     * 获取现有证件类型数据按SORTNO排序最大的
     * @return
     */
    public CredentialType getMaxSortNoCrmCredentialType();
    
    /**
     * 获取现有证件类型数据按SORTNO排序最小的
     * @return
     */
    public CredentialType getMinSortNoCredentialType();
    
    /**
     * 获取要移动的证件类型对象
     * @return
     */
    public CredentialType getNeedMoveCrmCredentialType(Map<String, Object> parameters);
}
