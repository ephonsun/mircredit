package banger.mobile.webapp.action.action;

import com.banger.mobile.dao.microTask.TskScheduleDao;
import com.banger.mobile.dao.microTask.ibatis.TskScheduleDaoiBatis;
import com.banger.mobile.facade.microTask.TskScheduleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Zhangfp
 * Date: 13-1-31
 * Time: 下午3:50
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {})
public class TskScheduleTest {
    private TskScheduleService tskScheduleService;

    public TskScheduleService getTskScheduleService() {
        return tskScheduleService;
    }

    public void setTskScheduleService(TskScheduleService tskScheduleService) {
        this.tskScheduleService = tskScheduleService;
    }

    @Test
    public void testJudgeScheduleByCustomerId(){
        Map<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userId",11);
        List<Integer> customerIds = new ArrayList<Integer>();
        customerIds.add(1);
        customerIds.add(2);
        customerIds.add(3);
        paramMap.put("customerIds",customerIds);

        Integer count = tskScheduleService.judgeScheduleByCustomerId(paramMap);
        System.out.println(count);
    }
}
