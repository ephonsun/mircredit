package com.banger.mobile.dao.loan;

import java.util.List;
import java.util.Map;

import com.banger.mobile.domain.model.loan.BaseSDic;

/**
 * @author zhangfp
 * @version $Id: SDicDao v 0.1 ${} 下午4:31 zhangfp Exp $
 */
public interface SDicDao {
    /**
     * 查询贷款信息字典
     * @param param
     * @return
     */
    public List<BaseSDic> queryDicByType(Map<String ,Object> param);
}
