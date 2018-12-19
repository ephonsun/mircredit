/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :产品类型
 * Author     :yujh
 * Create Date:Aug 15, 2012
 */
package com.banger.mobile.facade.system;

import java.util.List;

import com.banger.mobile.domain.model.system.ProfitType;


/**
 * @author yujh
 * @version $Id: ProductTypeService.java,v 0.1 Aug 15, 2012 3:04:07 PM Administrator Exp $
 */
public interface ProfitTypeService {
    /**
     * 获取所有收益类型
     * @return
     */
    public List<ProfitType> getAllProfitType();

    /**
     * 根据Id删除
     * @param id
     */
    public void deleteProfitType(int id);

    /**
     * 添加单位
     * @param 
     */
    public void addProfitType(ProfitType profitType);

    /**
     * id查询
     * @return
     */
    public ProfitType getProfitTypeById(int id);

    /**
     * 修改
     * @param 
     */
    public void updateProfitType(ProfitType profitType);

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
    public List<ProfitType> getProfitTypeBySameName(ProfitType profitType);
}
