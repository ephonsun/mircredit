/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :客户类型类...
 * Author     :QianJie
 * Create Date:May 21, 2012
 */
package com.banger.mobile.facade.impl.system.basicsdata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.dao.system.CrmCustomerTypeDao;
import com.banger.mobile.domain.model.system.CrmCustomerType;
import com.banger.mobile.facade.system.CrmCustomerTypeService;

/**
 * @author QianJie
 * @version $Id: CrmCustomerTypeServiceImpl.java,v 0.1 May 21, 2012 2:33:31 PM QianJie Exp $
 */
public class CrmCustomerTypeServiceImpl implements CrmCustomerTypeService {

    private CrmCustomerTypeDao crmCustomerTypeDao;

    public void setCrmCustomerTypeDao(CrmCustomerTypeDao crmCustomerTypeDao) {
        this.crmCustomerTypeDao = crmCustomerTypeDao;
    }

    /**
     * 获取所有未删除的客户类型
     * @return
     */
    public List<CrmCustomerType> getAllCrmCustomerType() {
        return crmCustomerTypeDao.getAllCrmCustomerType();
    }

    /**
     * 根据Id删除客户类型
     * @param id
     */
    public void deleteCrmCustomerType(int id) {
        crmCustomerTypeDao.deleteCrmCustomerType(id);
    }

    /**
     * 添加客户类型
     * @param crmCustomerType
     */
    public void addCrmCustomerType(CrmCustomerType crmCustomerType) {
        CrmCustomerType minType = crmCustomerTypeDao.getMinSortNoCrmCustomerType();
        if(minType==null){
            crmCustomerType.setSortNo(1);//排序+1
        }else{
        crmCustomerType.setSortNo(minType.getSortNo() + 1);//排序+1
        }
        crmCustomerTypeDao.AddCrmCustomerType(crmCustomerType);
    }

    /**
     * 根据客户类型名称获取拥有相同客户类型名称的数据
     * @param crmCustomerType
     * @return
     * @see com.banger.mobile.facade.system.CrmCustomerTypeService#getSameCrmCustomerTypeByName(com.banger.mobile.domain.model.system.CrmCustomerType)
     */
    public List<CrmCustomerType> getSameCrmCustomerTypeByName(CrmCustomerType crmCustomerType) {
        return crmCustomerTypeDao.getSameCrmCustomerTypeByName(crmCustomerType);
    }

    /**
     * 根据Id获取客户类型
     * @param id
     * @return
     * @see com.banger.mobile.facade.system.CrmCustomerTypeService#getCrmCustomerTypeById(int)
     */
    public CrmCustomerType getCrmCustomerTypeById(int id) {
        return crmCustomerTypeDao.getCrmCustomerTypeById(id);
    }

    /**
     * 修改客户类型
     * @param crmCustomerType
     * @see com.banger.mobile.facade.system.CrmCustomerTypeService#updateCrmCustomerType(com.banger.mobile.domain.model.system.CrmCustomerType)
     */
    public void updateCrmCustomerType(CrmCustomerType crmCustomerType) {
        crmCustomerTypeDao.updateCrmCustomerType(crmCustomerType);
    }

    /**
     * 上移或下移客户类型
     * @param id
     * @param moveType
     * @return
     * @see com.banger.mobile.facade.system.CrmCustomerTypeService#moveTypeItems(int, java.lang.String)
     */
    public String moveTypeItems(int id, String moveType) {
        CrmCustomerType crmType = crmCustomerTypeDao.getCrmCustomerTypeById(id);
        if (crmType != null) {
            int curSortNo = crmType.getSortNo();
            //上移
            if (moveType.equals("Up")) {
                //如果是最顶层则返回SUCCESS
                CrmCustomerType maxType = crmCustomerTypeDao.getMaxSortNoCrmCustomerType();
                if (maxType.getSortNo() != crmType.getSortNo()) {
                    Map<String, Object> parameterMap = new HashMap<String, Object>();
                    parameterMap.put("moveType", "Up");
                    parameterMap.put("sortNo", curSortNo);
                    CrmCustomerType needMove = crmCustomerTypeDao
                        .getNeedMoveCrmCustomerType(parameterMap);
                    //移动
                    crmType.setSortNo(needMove.getSortNo());
                    needMove.setSortNo(curSortNo);
                    //数据持久化
                    crmCustomerTypeDao.updateCrmCustomerType(crmType);
                    crmCustomerTypeDao.updateCrmCustomerType(needMove);
                    return "SUCCESS";
                } else {
                    return "ERROR";
                }
            } else {
                //下移
                //如果是最底层则返回SUCCESS
                CrmCustomerType minType = crmCustomerTypeDao.getMinSortNoCrmCustomerType();
                if (minType.getSortNo() != crmType.getSortNo()) {
                    Map<String, Object> parameterMap = new HashMap<String, Object>();
                    parameterMap.put("moveType", "Down");
                    parameterMap.put("sortNo", curSortNo);
                    CrmCustomerType needMove = crmCustomerTypeDao
                        .getNeedMoveCrmCustomerType(parameterMap);
                    //移动
                    crmType.setSortNo(needMove.getSortNo());
                    needMove.setSortNo(curSortNo);
                    //数据持久化
                    crmCustomerTypeDao.updateCrmCustomerType(crmType);
                    crmCustomerTypeDao.updateCrmCustomerType(needMove);
                    return "SUCCESS";
                } else {
                    return "ERROR";
                }
            }
        } else {
            return "SUCCESS";
        }
    }

}
