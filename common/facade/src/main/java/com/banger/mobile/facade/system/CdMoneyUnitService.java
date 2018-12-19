/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :销售单位service
 * Author     :yujh
 * Create Date:Jul 17, 2012
 */
package com.banger.mobile.facade.system;

import java.util.List;

import com.banger.mobile.domain.model.system.CdMoneyUnit;


/**
 * @author yujh
 * @version $Id: RskIntervalTypeService.java,v 0.1 Jul 17, 2012 9:55:31 AM Administrator Exp $
 */
public interface CdMoneyUnitService {
    /**
     * 获取所有销售单位
     * @return
     */
    public List<CdMoneyUnit> getAllMoneyUnit();

    /**
     * 根据Id删除
     * @param id
     */
    public void deleteMoneyUnit(int id);

    /**
     * 添加单位
     * @param 
     */
    public void addMoneyUnit(CdMoneyUnit moneyUnit);

    /**
     * id查询
     * @return
     */
    public CdMoneyUnit getMoneyUnitById(int id);

    /**
     * 修改销售单位
     * @param 
     */
    public void updateMoneyUnit(CdMoneyUnit moneyUnit);

    /**
     * 上移或下移
     * @param id
     * @param moveType
     */
    public String moveTypeItems(int id, String moveType);
    /**
     * 获取相同名称的对象
     * @param rskIntervalType
     * @return
     */
    public List<CdMoneyUnit> getMoneyUnitBySameName(CdMoneyUnit moneyUnit);
    
    /**
     * 查询是否是最后一条募集单位
     */
    public Integer queryLaseMoneyUnit();
}
