package com.banger.mobile.facade.clusters;

import com.banger.mobile.domain.model.system.ClustersConfig;
import com.banger.mobile.domain.model.transport.TransportConfig;

public interface ClustersConfigService {
	/**
	 * 通到传输配置
	 * @return
	 */
	public TransportConfig getTransportConfig();
	
	/**
	 * 得到tomcat集群配置
	 * @return
	 */
	public ClustersConfig getClustersConfig();
	
}
