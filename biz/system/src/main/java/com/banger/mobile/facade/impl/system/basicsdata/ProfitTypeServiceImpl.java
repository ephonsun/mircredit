/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :收益类型service实现类
 * Author     :yujh
 * Create Date:Aug 15, 2012
 */
package com.banger.mobile.facade.impl.system.basicsdata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.dao.system.ProfitTypeDao;
import com.banger.mobile.domain.model.system.ProfitType;
import com.banger.mobile.facade.system.ProfitTypeService;

/**
 * @author yujh
 * @version $Id: ProfitType.java,v 0.1 Aug 15, 2012 3:38:54 PM Administrator Exp $
 */
public class ProfitTypeServiceImpl implements ProfitTypeService {
    private ProfitTypeDao profitTypeDao;

    public void setProfitTypeDao(ProfitTypeDao profitTypeDao) {
        this.profitTypeDao = profitTypeDao;
    }

    /**
     * 添加单位
     */
    public void addProfitType(ProfitType profitType) {
        ProfitType minNo = this.profitTypeDao.getMinSortNoType();
        if (minNo == null) {
            profitType.setSortNo(1);
        } else {
            profitType.setSortNo(minNo.getSortNo() + 1);
        }
        this.profitTypeDao.addProfitType(profitType);
    }

    /**
     * 根据Id删除
     */
    public void deleteProfitType(int id) {
        this.profitTypeDao.deleteProfitType(id);
    }

    /**
     * 获取所有收益类型
     */
    public List<ProfitType> getAllProfitType() {
        return this.profitTypeDao.getAllProfitType();
    }

    /**
     * id查询
     */
    public ProfitType getProfitTypeById(int id) {
        return this.profitTypeDao.getTypeById(id);
    }

    /**
     * 获取相同名称的对象
     */
    public List<ProfitType> getProfitTypeBySameName(ProfitType profitType) {
        return this.profitTypeDao.getProfitTypeBySameName(profitType);
    }

    /**
     * 上移或下移
     */
    public String moveTypeItems(int id, String moveType) {
        ProfitType profitType = this.profitTypeDao.getTypeById(id);
        if (profitType != null) {
            int typeNo = profitType.getSortNo();
            if (moveType.equals("Up")) {
                ProfitType maxNo = this.profitTypeDao.getMaxSortNoType();
                if (typeNo != maxNo.getSortNo()) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("moveType", "Up");
                    map.put("sortNo", typeNo);
                    ProfitType needMove = this.profitTypeDao.getNeedToMoveProfitType(map);
                    profitType.setSortNo(needMove.getSortNo());
                    needMove.setSortNo(typeNo);
                    this.profitTypeDao.updateProfitType(profitType);
                    this.profitTypeDao.updateProfitType(needMove);
                    return "SUCCESS";
                } else {
                    return "ERROR";
                }
            } else {
                ProfitType minNo = this.profitTypeDao.getMinSortNoType();
                if (typeNo != minNo.getSortNo()) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("moveType", "Down");
                    map.put("sortNo", typeNo);
                    ProfitType needMove = this.profitTypeDao.getNeedToMoveProfitType(map);
                    profitType.setSortNo(needMove.getSortNo());
                    needMove.setSortNo(typeNo);
                    this.profitTypeDao.updateProfitType(profitType);
                    this.profitTypeDao.updateProfitType(needMove);
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
     * 修改
     */
    public void updateProfitType(ProfitType profitType) {
        this.profitTypeDao.updateProfitType(profitType);
    }

}
