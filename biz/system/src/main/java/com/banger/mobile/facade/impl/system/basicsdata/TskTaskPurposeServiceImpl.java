/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :联系目的service
 * Author     :yujh
 * Create Date:Nov 7, 2012
 */
package com.banger.mobile.facade.impl.system.basicsdata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.dao.tskTaskPurpose.TskTaskPurposeDao;
import com.banger.mobile.domain.model.tskTaskPurpose.TskTaskPurpose;
import com.banger.mobile.facade.tskTaskPurpose.TskTaskPurposeService;

/**
 * @author yujh
 * @version $Id: TskTaskPurposeImpl.java,v 0.1 Nov 7, 2012 5:17:01 PM Administrator Exp $
 */
public class TskTaskPurposeServiceImpl implements TskTaskPurposeService{
    private TskTaskPurposeDao tskTaskPurposeDao;
    

    public void setTskTaskPurposeDao(TskTaskPurposeDao tskTaskPurposeDao) {
        this.tskTaskPurposeDao = tskTaskPurposeDao;
    }

    //添加
    public void addTskTaskPurpose(TskTaskPurpose tskTaskPurpose) {
        TskTaskPurpose minSortNo= this.tskTaskPurposeDao.getMinSortNoTskTaskPurpose();
        if(minSortNo==null){
            tskTaskPurpose.setSortNo(1);
        }else{
            tskTaskPurpose.setSortNo(minSortNo.getSortNo()+1);
        }
        this.tskTaskPurposeDao.addTskTaskPurpose(tskTaskPurpose);
    }

    //改变状态
    public void changeState(TskTaskPurpose tskTaskPurpose) {
        this.tskTaskPurposeDao.changeState(tskTaskPurpose);
    }

    //删除联系目的
    public void deleteTskTaskPose(int id) {
        this.tskTaskPurposeDao.deleteTskTaskPose(id);
    }

    //查询所有联系目的(不包含停用)
    public List<TskTaskPurpose> getAllTskTaskPurpose() {
        return this.tskTaskPurposeDao.getAllTskTaskPurpose(false);
    }
    
  //查询所有联系目的(参数isContainStop 包含停用)
    public List<TskTaskPurpose> getAllTskTaskPurpose(boolean isContainStop){
    	return this.tskTaskPurposeDao.getAllTskTaskPurpose(isContainStop);
    }


    //需要移动的对象
    public TskTaskPurpose getNeedToMoveTskTaskPurpose(Map<String, Object> parameters) {
        return this.tskTaskPurposeDao.getNeedToMoveTskTaskPurpose(parameters);
    }

    //通过id查询
    public TskTaskPurpose getTskTaskPurposeById(int id) {
        return this.tskTaskPurposeDao.getTskTaskPurposeById(id);
    }

    //得到相同名称的对象
    public List<TskTaskPurpose> getTskTaskPurposeBySameName(TskTaskPurpose tskTaskPurpose) {
        return this.tskTaskPurposeDao.getTskTaskPurposeBySameName(tskTaskPurpose);
    }

    //更新
    public void updateTskTaskPurpose(TskTaskPurpose tskTaskPurpose) {
        this.tskTaskPurposeDao.updateTskTaskPurpose(tskTaskPurpose);
    }

    //上移下移
    public String moveTypeItems(int id, String moveType) {
        TskTaskPurpose tskTaskPurpose =this.tskTaskPurposeDao.getTskTaskPurposeById(id);
        if(tskTaskPurpose!=null){
            int sortNo=tskTaskPurpose.getSortNo();
            if(moveType.equals("Up")){//上移
                TskTaskPurpose Max=this.tskTaskPurposeDao.getMaxSortNoTskTaskPurpose();
                if(sortNo!=Max.getSortNo()){
                    Map<String ,Object> map=new HashMap<String,Object>();
                    map.put("moveType", moveType);
                    map.put("sortNo",sortNo );
                    TskTaskPurpose needMove=this.tskTaskPurposeDao.getNeedToMoveTskTaskPurpose(map);
                    tskTaskPurpose.setSortNo(needMove.getSortNo());
                    needMove.setSortNo(sortNo);
                    this.tskTaskPurposeDao.updateTskTaskPurpose(tskTaskPurpose);
                    this.tskTaskPurposeDao.updateTskTaskPurpose(needMove);
                    return "SUCCESS";
                }else{
                    return "ERROR";
                }
            }else{
                TskTaskPurpose Min=this.tskTaskPurposeDao.getMinSortNoTskTaskPurpose();
                if(Min.getSortNo()!=sortNo){
                    Map<String ,Object>map= new HashMap<String,Object>();
                    map.put("moveType", moveType);
                    map.put("sortNo", sortNo);     
                    TskTaskPurpose needMove =this.tskTaskPurposeDao.getNeedToMoveTskTaskPurpose(map);
                    tskTaskPurpose.setSortNo(needMove.getSortNo());
                    needMove.setSortNo(sortNo);
                    this.tskTaskPurposeDao.updateTskTaskPurpose(tskTaskPurpose);
                    this.tskTaskPurposeDao.updateTskTaskPurpose(needMove);
                    return "SUCCESS";
                }else{
                    return "ERROR";
                }
            }
        }else{
            return "SUCCESS";
        }
    }

}
