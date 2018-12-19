package com.banger.mobile.facade.loan;


import java.util.List;
import java.util.Map;

import com.banger.mobile.domain.model.loan.BaseSDic;

/**
 * Created by thinkpad on 2014/10/22.
 */
public interface SDicService {

    /**
     * 查询贷款信息字典
     * @param param
     * @return
     */
    public List<BaseSDic> queryDicByType(Map<String ,Object> param);

}
