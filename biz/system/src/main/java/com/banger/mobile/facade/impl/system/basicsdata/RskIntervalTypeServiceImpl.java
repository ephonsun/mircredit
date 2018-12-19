/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :投资偏好serviceImpl
 * Author     :yujh
 * Create Date:May 21, 2012
 */
package com.banger.mobile.facade.impl.system.basicsdata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.dao.rskIntervalType.RskIntervalTypeDao;
import com.banger.mobile.domain.model.rskIntervalType.RskIntervalType;
import com.banger.mobile.facade.rskIntervalType.RskIntervalTypeService;

/**
 * @author yujh
 * @version $Id: CrmCustomerTypeServiceImpl.java,v 0.1 May 21, 2012 2:33:31 PM QianJie Exp $
 */
public class RskIntervalTypeServiceImpl implements RskIntervalTypeService {
    private RskIntervalTypeDao rskIntervalDao;

    public void setRskIntervalDao(RskIntervalTypeDao rskIntervalDao) {
        this.rskIntervalDao = rskIntervalDao;
    }

    /**
     * 添加
     */
    public void addRskIntervalType(RskIntervalType rskIntervalType) {
        RskIntervalType minType = this.rskIntervalDao.getMinSortNoRskIntervalType();
        if (minType == null) {
            rskIntervalType.setSortNo(1);
        } else {
            rskIntervalType.setSortNo(minType.getSortNo() + 1);
        }
        this.rskIntervalDao.addRskIntervalType(rskIntervalType);
    }

    /**
     * 删除
     */
    public void deleteRskIntervalrType(int id) {
        this.rskIntervalDao.deleteRskIntervalType(id);
    }

    /**
     * 获取所有类型
     */
    public List<RskIntervalType> getAllRskIntervalType() {
        return this.rskIntervalDao.getAllRskIntervalType();
    }
    
    /**
     * 获取所有投资偏好类型集合
     */
    public List<RskIntervalType> getAllRskIntervalTypes() {
        return this.rskIntervalDao.getAllRskIntervalTypes();
    }

    /**
     * 通过id查询
     */
    public RskIntervalType getRskIntervalTypeById(int id) {
        return this.rskIntervalDao.getTypeById(id);
    }

    /**
     * 移动
     */
    public String moveTypeItems(int id, String moveType) {
        RskIntervalType rskIntervalType = this.rskIntervalDao.getTypeById(id);
        if (rskIntervalType != null) {
            int TypeNo = rskIntervalType.getSortNo();
            if (moveType.equals("Up")) {
                RskIntervalType maxType = this.rskIntervalDao.getMaxSortNoRskIntervalType();
                if (maxType.getSortNo() != TypeNo) {
                    Map<String, Object> paramaterMap = new HashMap<String, Object>();
                    paramaterMap.put("moveType", "Up");
                    paramaterMap.put("sortNo", TypeNo);
                    RskIntervalType needMove = this.rskIntervalDao
                        .getNeedToMoveRskIntervalType(paramaterMap);
                    rskIntervalType.setSortNo(needMove.getSortNo());
                    needMove.setSortNo(TypeNo);
                    this.rskIntervalDao.updateRskIntervalType(rskIntervalType);
                    this.rskIntervalDao.updateRskIntervalType(needMove);
                    return "SUCCESS";
                } else {
                    return "ERROR";
                }
            } else {
                //下移
                //如果是最底层则返回SUCCESS
                RskIntervalType minType = this.rskIntervalDao.getMinSortNoRskIntervalType();
                if (minType.getSortNo() != TypeNo) {
                    Map<String, Object> parameterMap = new HashMap<String, Object>();
                    parameterMap.put("moveType", "Down");
                    parameterMap.put("sortNo", TypeNo);
                    RskIntervalType needMove = this.rskIntervalDao
                        .getNeedToMoveRskIntervalType(parameterMap);
                    //移动
                    rskIntervalType.setSortNo(needMove.getSortNo());
                    needMove.setSortNo(TypeNo);
                    //数据持久化
                    this.rskIntervalDao.updateRskIntervalType(rskIntervalType);
                    this.rskIntervalDao.updateRskIntervalType(needMove);
                    return "SUCCESS";
                } else {
                    return "ERROR";
                }
            }
        } else {
            return "SUCCESS";
        }
    }

    /**
     * 更新
     */
    public void updateRskIntervalType(RskIntervalType rskIntervalType) {
        this.rskIntervalDao.updateRskIntervalType(rskIntervalType);
    }

    /**
     * 获取相同名称的对象
     */
    public List<RskIntervalType> getRskIntervalTypeBySameName(RskIntervalType rskIntervalType) {
        return this.rskIntervalDao.getRskIntervalTypeBySameName(rskIntervalType);
    }

    /**
     * 改变状态
     * @param rskIntervalType
     * @see com.banger.mobile.facade.rskIntervalType.RskIntervalTypeService#changeState(com.banger.mobile.domain.model.rskIntervalType.RskIntervalType)
     */
    public void changeState(RskIntervalType rskIntervalType) {
        this.rskIntervalDao.changeState(rskIntervalType);
    }

}
