package com.banger.mobile.dao.inform;

import com.banger.mobile.domain.model.inform.Inform;

/**
 * Created by BH-TCL on 15-7-13.
 */
public interface InformDao {

    void updateInform(Inform inform);
    Inform getInform(Integer id);
}
