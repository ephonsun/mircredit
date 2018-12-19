package com.banger.mobile.facade.impl;


import com.banger.mobile.dao.loan.SDicDao;
import com.banger.mobile.dao.loan.ibatis.SDicDaoiBatis;
import com.banger.mobile.domain.model.loan.BaseSDic;
import com.banger.mobile.facade.loan.SDicService;

import java.util.List;
import java.util.Map;

/**
 * Created by thinkpad on 2014/10/22.
 */
public class SDicServiceImpl implements SDicService{
    private SDicDao sDicDao;

    public void setsDicDao(SDicDaoiBatis sDicDao) {
        this.sDicDao = sDicDao;
    }

    /**
     *  map.put("type",value)
     * @param param
     * @return
     */
    public List<BaseSDic> queryDicByType(Map<String, Object> param) {
        return sDicDao.queryDicByType(param);
    }

}
