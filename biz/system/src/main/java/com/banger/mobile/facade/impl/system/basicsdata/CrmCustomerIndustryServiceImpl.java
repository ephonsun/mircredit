/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :客户所处行业...
 * Author     :QianJie
 * Create Date:May 25, 2012
 */
package com.banger.mobile.facade.impl.system.basicsdata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.dao.system.CrmCustomerIndustryDao;
import com.banger.mobile.domain.model.system.CrmCustomerIndustry;
import com.banger.mobile.facade.system.CrmCustomerIndustryService;

/**
 * @author QianJie
 * @version $Id: CrmCustomerIndustryServiceImpl.java,v 0.1 May 25, 2012 11:03:21 AM QianJie Exp $
 */
public class CrmCustomerIndustryServiceImpl implements CrmCustomerIndustryService{
    private CrmCustomerIndustryDao crmCustomerIndustryDao;

    public void setCrmCustomerIndustryDao(CrmCustomerIndustryDao crmCustomerIndustryDao) {
        this.crmCustomerIndustryDao = crmCustomerIndustryDao;
    }

    /**
     * 获取所有未删除的行业类型
     * @return
     */
    public List<CrmCustomerIndustry> getAllCrmCustomerIndustry() {
        return crmCustomerIndustryDao.getAllCrmCustomerIndustry();
    }

    /**
     * 根据Id删除行业类型
     * @param id
     */
    public void deleteCrmCustomerIndustry(int id) {
        crmCustomerIndustryDao.deleteCrmCustomerIndustry(id);
    }

    /**
     * 添加行业类型
     * @param crmCustomerIndustry
     */
    public void addCrmCustomerIndustry(CrmCustomerIndustry crmCustomerIndustry) {
        CrmCustomerIndustry minIndustry = crmCustomerIndustryDao.getMinSortNoCrmCustomerIndustry();
        if(minIndustry==null){
            crmCustomerIndustry.setSortNo(1);//排序+1
        }else{
            crmCustomerIndustry.setSortNo(minIndustry.getSortNo() + 1);//排序+1
        }
        crmCustomerIndustryDao.AddCrmCustomerIndustry(crmCustomerIndustry);
    }

    /**
     * 根据行业类型名称获取拥有相同行业类型名称的数据
     * @param crmCustomerIndustry
     * @return
     * @see com.banger.mobile.facade.system.CrmCustomerIndustryService#getSameCrmCustomerIndustryByName(com.banger.mobile.domain.model.system.CrmCustomerIndustry)
     */
    public List<CrmCustomerIndustry> getSameCrmCustomerIndustryByName(CrmCustomerIndustry crmCustomerIndustry) {
        return crmCustomerIndustryDao.getSameCrmCustomerIndustryByName(crmCustomerIndustry);
    }

    /**
     * 根据Id获取行业类型
     * @param id
     * @return
     * @see com.banger.mobile.facade.system.CrmCustomerIndustryService#getCrmCustomerIndustryById(int)
     */
    public CrmCustomerIndustry getCrmCustomerIndustryById(int id) {
        return crmCustomerIndustryDao.getCrmCustomerIndustryById(id);
    }

    /**
     * 修改行业类型
     * @param crmCustomerIndustry
     * @see com.banger.mobile.facade.system.CrmCustomerIndustryService#updateCrmCustomerIndustry(com.banger.mobile.domain.model.system.CrmCustomerIndustry)
     */
    public void updateCrmCustomerIndustry(CrmCustomerIndustry crmCustomerIndustry) {
        crmCustomerIndustryDao.updateCrmCustomerIndustry(crmCustomerIndustry);
    }

    /**
     * 上移或下移行业类型
     * @param id
     * @param moveType
     * @return
     * @see com.banger.mobile.facade.system.CrmCustomerIndustryService#moveTypeItems(int, java.lang.String)
     */
    public String moveTypeItems(int id, String moveType) {
        CrmCustomerIndustry crmIndustry = crmCustomerIndustryDao.getCrmCustomerIndustryById(id);
        if (crmIndustry != null) {
            int curSortNo = crmIndustry.getSortNo();
            //上移
            if (moveType.equals("Up")) {
                //如果是最顶层则返回SUCCESS
                CrmCustomerIndustry maxType = crmCustomerIndustryDao
                    .getMaxSortNoCrmCustomerIndustry();
                if (maxType.getSortNo() != crmIndustry.getSortNo()) {
                    Map<String, Object> parameterMap = new HashMap<String, Object>();
                    parameterMap.put("moveType", "Up");
                    parameterMap.put("sortNo", curSortNo);
                    CrmCustomerIndustry needMove = crmCustomerIndustryDao
                        .getNeedMoveCrmCustomerIndustry(parameterMap);
                    //移动
                    crmIndustry.setSortNo(needMove.getSortNo());
                    needMove.setSortNo(curSortNo);
                    //数据持久化
                    crmCustomerIndustryDao.updateCrmCustomerIndustry(crmIndustry);
                    crmCustomerIndustryDao.updateCrmCustomerIndustry(needMove);
                    return "SUCCESS";
                } else {
                    return "ERROR";
                }
            } else {
                //下移
                //如果是最底层则返回SUCCESS
                CrmCustomerIndustry minType = crmCustomerIndustryDao
                    .getMinSortNoCrmCustomerIndustry();
                if (minType.getSortNo() != crmIndustry.getSortNo()) {
                    Map<String, Object> parameterMap = new HashMap<String, Object>();
                    parameterMap.put("moveType", "Down");
                    parameterMap.put("sortNo", curSortNo);
                    CrmCustomerIndustry needMove = crmCustomerIndustryDao
                        .getNeedMoveCrmCustomerIndustry(parameterMap);
                    //移动
                    crmIndustry.setSortNo(needMove.getSortNo());
                    needMove.setSortNo(curSortNo);
                    //数据持久化
                    crmCustomerIndustryDao.updateCrmCustomerIndustry(crmIndustry);
                    crmCustomerIndustryDao.updateCrmCustomerIndustry(needMove);
                    return "SUCCESS";
                } else {
                    return "ERROR";
                }
            }
        }else{
            return "SUCCESS";
        }
    }


}