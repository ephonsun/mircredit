package com.banger.mobile.facade.crmVasSetting;

import java.util.List;

import com.banger.mobile.domain.model.crmVasSetting.CrmVasSetting;

public interface CrmVasSettingService {
    /**
     * 获取所有增值设置
     * @return
     */
    public List<CrmVasSetting> getAllCrmVasSetting();
    /**
     * 根据userId获取对象
     * @param userId
     * @return
     */
    public CrmVasSetting getCrmVasSettingByUserId(int userId);
    
    /**
     * 新增增值业务配置
     * @param crmVasSetting
     */
    public void insertCrmVasSetting(CrmVasSetting crmVasSetting);
    
    /**
     * 删除用户的增值业务配置
     * @param crmVasSetting
     */
    public void deleteCrmVasSetting(Integer userId);

    /**
     * 修改增值业务配置
     * @param crmVasSetting
     */
    public void updateCrmVasSetting(CrmVasSetting crmVasSetting);
    
    /**
     * 验证增值业务帐号
     * @param crmVasSetting
     * @return
     */
    public List<CrmVasSetting> validateAccount(CrmVasSetting crmVasSetting);
}
