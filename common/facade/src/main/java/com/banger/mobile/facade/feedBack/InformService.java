package com.banger.mobile.facade.feedBack;

import com.banger.mobile.domain.model.inform.Inform;

/**
 * Created by BH-TCL on 15-7-13.
 */
public interface InformService {

    void updateInform(Inform inform);
    Inform getInform(Integer id);
}
