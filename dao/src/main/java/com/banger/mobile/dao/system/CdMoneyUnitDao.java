/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :金融单位
 * Author     :yujh
 * Create Date:Aug 14, 2012
 */
package com.banger.mobile.dao.system;

import java.util.List;
import java.util.Map;

import com.banger.mobile.domain.model.system.CdMoneyUnit;


/**
 * @author Administrator
 * @version $Id: CdMoneyUnitDao.java,v 0.1 Aug 14, 2012 3:44:23 PM Administrator Exp $
 */
public interface CdMoneyUnitDao {
	/**
	 * 列表
	 * @return
	 */
    public List<CdMoneyUnit> getAllMoneyUnit();

    /**
     * 添加
     * @param moneyUnit
     */
    public void addMoneyUnit(CdMoneyUnit moneyUnit);

    /**
     * 删除
     * @param id
     */
    public void deleteMoneyUnit(int id);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public CdMoneyUnit getMoneyUnitById(int id);

    /**
     * 更新
     * @param moneyUnit
     */
    public void updateMoneyUnit(CdMoneyUnit moneyUnit);

    /**
     * 查询排序号最大的
     * @return
     */
    public CdMoneyUnit getMaxSortNo();

    /**
     * 查询排序号最小的
     * @return
     */
    public CdMoneyUnit getMinSortNo();

    /**
     * 查询需要移动的对象
     * @param parameters
     * @return
     */
    public CdMoneyUnit getNeedToMoveMoneyUnit(Map<String, Object> parameters);
    
    /**
     * 查询名字相同的对象
     * @param moneyUnit
     * @return
     */
    public List<CdMoneyUnit> getMoneyUnitBySameName(CdMoneyUnit moneyUnit);
    
    /**
     * 查询是否是最后一条募集单位
     */
    public Integer queryLaseMoneyUnit();
}
