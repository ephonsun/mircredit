/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :业务类型实体数据接口
 * Author     :liyb
 * Create Date:2012-5-17
 */
package com.banger.mobile.dao.recbiztype;

import java.util.List;
import com.banger.mobile.domain.model.recbistype.RecbizType;

/**
 * @author liyb
 * @version $Id: RecbizTypeDao.java,v 0.1 2012-5-17 下午03:54:13 liyb Exp $
 */
public interface RecbizTypeDao {
    
    /**
     * 判断业务类型是否在使用
     */
    public Integer isTypeUse(Integer bizTypeId);
    
    /**
     * 修改启用/停用状态
     * @param recbizType
     */
    public void updateActived(RecbizType recbizType);
    
    /**
     * 根据业务编号删除(单个删除)
     * @param bizTypeCode
     */
    public void deleteRecbizTypeById(Integer bizTypeId);

    /**
     * 添加业务类型
     * @param recbizType
     */
    public void insertRecbiztype(RecbizType recbizType);
    
    /**
     * 判断业务类型编号和名称是否存在
     */
    public Integer validation(String sqlId,RecbizType recbizType);
    
    /**
     * 返回业务类型信息
     * @param recbizType
     * @return RecbizType实体
     */
    public RecbizType getRecbizTypeById(RecbizType recbizType);
    
    
    /**
     * 编辑业务类型
     * @param recbizType
     */
    public void updateRecbiztype(RecbizType recbizType);
    
    /**
     * 返回所有未删除的业务类型
     * @return
     */
    public List<RecbizType> getRecbizTypeList();
    
    /**
     * PAD端返回未删除已启用的业务类型
     */
    public List<RecbizType> getRecbizTypeForPad();
}
