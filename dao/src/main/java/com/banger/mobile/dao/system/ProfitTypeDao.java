/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :收益类型dao
 * Author     :yujh
 * Create Date:Aug 14, 2012
 */
package com.banger.mobile.dao.system;

import java.util.List;
import java.util.Map;

import com.banger.mobile.domain.model.system.ProfitType;


/**
 * @author yujh
 * @version $Id: ProfitTypeDao.java,v 0.1 Aug 14, 2012 3:41:10 PM Administrator Exp $
 */
public interface ProfitTypeDao {
	/**
	 * 列表
	 * @return
	 */
    public List<ProfitType> getAllProfitType();

    /**
     * 添加
     * @param profitType
     */
    public void addProfitType(ProfitType profitType);

    /**
     * 删除
     * @param id
     */
    public void deleteProfitType(int id);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public ProfitType getTypeById(int id);

    /**
     * 更新
     * @param profitType
     */
    public void updateProfitType(ProfitType profitType);

    /**
     * 查询排序号最大的
     * @return
     */
    public ProfitType getMaxSortNoType();

    /**
     * 查询排序号最小的
     * @return
     */
    public ProfitType getMinSortNoType();

    /**
     * 查询需要移动的对象
     * @param parameters
     * @return
     */
    public ProfitType getNeedToMoveProfitType(Map<String, Object> parameters);
    
    /**
     * 查询名字相同的对象
     * @param profitType
     * @return
     */
    public List<ProfitType> getProfitTypeBySameName(ProfitType profitType);
}
