package com.banger.mobile.service;

import java.util.HashMap;
import java.util.Map;
import com.banger.mobile.facade.BaseServiceTestCase;
import com.banger.mobile.facade.finance.FeColumnService;

public class FeColumnServiceTest extends BaseServiceTestCase{
    private FeColumnService feColumnService;

    public FeColumnService getFeColumnService() {
        return feColumnService;
    }


    public void setFeColumnService(FeColumnService feColumnService) {
        this.feColumnService = feColumnService;
    }


    public void testGetAllColumn(){
        Map map = new HashMap();
        feColumnService.getAllColumnList(map);
    }
}
