/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :销售单位service
 * Author     :yujh
 * Create Date:Aug 14, 2012
 */
package com.banger.mobile.facade.impl.system.basicsdata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.dao.system.CdMoneyUnitDao;
import com.banger.mobile.domain.model.system.CdMoneyUnit;
import com.banger.mobile.facade.system.CdMoneyUnitService;

/**
 * @author yujh
 * @version $Id: CdMoneyUnitServiceImpl.java,v 0.1 Aug 14, 2012 4:58:11 PM Administrator Exp $
 */
public class CdMoneyUnitServiceImpl implements CdMoneyUnitService {
    private  CdMoneyUnitDao  moneyUnitDao;

    public void setMoneyUnitDao(CdMoneyUnitDao moneyUnitDao) {
        this.moneyUnitDao = moneyUnitDao;
    }

    /**
     * 添加单位
     */
    public void addMoneyUnit(CdMoneyUnit moneyUnit) {
        CdMoneyUnit minUnit = this.moneyUnitDao.getMinSortNo();
        if(minUnit==null){
            moneyUnit.setSortNo(1);
        }else{
            moneyUnit.setSortNo(minUnit.getSortNo()+1);
        }
        this.moneyUnitDao.addMoneyUnit(moneyUnit);
    }

    /**
     * 根据Id删除
     */
    public void deleteMoneyUnit(int id) {
        this.moneyUnitDao.deleteMoneyUnit(id);
    }

    /**
     * 获取所有销售单位
     */
    public List<CdMoneyUnit> getAllMoneyUnit() {
        return this.moneyUnitDao.getAllMoneyUnit();
    }

    /**
     * id查询
     */
    public CdMoneyUnit getMoneyUnitById(int id) {
        return this.moneyUnitDao.getMoneyUnitById(id);
    }

    /**
     * 获取相同名称的对象
     */
    public List<CdMoneyUnit> getMoneyUnitBySameName(CdMoneyUnit moneyUnit) {
        return this.moneyUnitDao.getMoneyUnitBySameName(moneyUnit);
    }

    /**
     * 上移或下移
     */
    public String moveTypeItems(int id, String moveType) {
        CdMoneyUnit cdMoneyUnit = this.moneyUnitDao.getMoneyUnitById(id);
        if (cdMoneyUnit != null) {
            int TypeNo = cdMoneyUnit.getSortNo();
            if (moveType.equals("Up")) {
                CdMoneyUnit maxType = this.moneyUnitDao.getMaxSortNo();
                if (maxType.getSortNo() != TypeNo) {
                    Map<String, Object> paramaterMap = new HashMap<String, Object>();
                    paramaterMap.put("moveType", "Up");
                    paramaterMap.put("sortNo", TypeNo);
                    CdMoneyUnit needMove = this.moneyUnitDao.getNeedToMoveMoneyUnit(paramaterMap);
                    cdMoneyUnit.setSortNo(needMove.getSortNo());
                    needMove.setSortNo(TypeNo);
                    this.moneyUnitDao.updateMoneyUnit(cdMoneyUnit);
                    this.moneyUnitDao.updateMoneyUnit(needMove);
                    return "SUCCESS";
                } else {
                    return "ERROR";
                }
            } else {
                //下移
                //如果是最底层则返回SUCCESS
                CdMoneyUnit minType = this.moneyUnitDao.getMinSortNo();
                if (minType.getSortNo() != TypeNo) {
                    Map<String, Object> parameterMap = new HashMap<String, Object>();
                    parameterMap.put("moveType", "Down");
                    parameterMap.put("sortNo", TypeNo);
                    CdMoneyUnit needMove = this.moneyUnitDao.getNeedToMoveMoneyUnit(parameterMap);
                    //移动
                    cdMoneyUnit.setSortNo(needMove.getSortNo());
                    needMove.setSortNo(TypeNo);
                    //数据持久化
                    this.moneyUnitDao.updateMoneyUnit(cdMoneyUnit);
                    this.moneyUnitDao.updateMoneyUnit(needMove);
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
     * 修改销售单位
     */
    public void updateMoneyUnit(CdMoneyUnit moneyUnit) {
        this.moneyUnitDao.updateMoneyUnit(moneyUnit);
    }

    /**
     * 查询是否是最后一条募集单位
     */
    public Integer queryLaseMoneyUnit(){
        return this.moneyUnitDao.queryLaseMoneyUnit();
    }
}
