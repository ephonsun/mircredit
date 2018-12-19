package com.banger.mobile.facade.microTask;

import com.banger.mobile.domain.model.data.CustomerData;
import com.banger.mobile.domain.model.record.RecordInfo;

/**
 * Created with IntelliJ IDEA. User: yuanme Date: 13-3-29 Time: 下午1:30 To change
 * this template use File | Settings | File Templates.
 */
public interface TskMicroTaskAutoFinishService {
	void autoFinish(RecordInfo info, CustomerData data);

	void autoFinishFoot(RecordInfo info, Integer taskId);
}
