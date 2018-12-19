package com.banger.mobile.facade.feedBack.impl;

import com.banger.mobile.dao.inform.InformDao;
import com.banger.mobile.domain.model.inform.Inform;
import com.banger.mobile.facade.feedBack.InformService;

/**
 * Created by BH-TCL on 15-7-13.
 */
public class InformServiceImlp implements InformService{

    private InformDao informDao;

    public void setInformDao(InformDao informDao) {
        this.informDao = informDao;
    }

    @Override
    public void updateInform(Inform inform) {
        informDao.updateInform(inform);
    }

    @Override
    public Inform getInform(Integer id) {
        return informDao.getInform(id);
    }
}
