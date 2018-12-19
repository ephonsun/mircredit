package com.banger.mobile.domain.model.system;

import com.banger.mobile.domain.model.transport.TransportConfig;

public class ClustersConfig {
	private String address;			//tomcat地址
	private int port;				//tomcat端口
	private TransportConfig transport;
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public TransportConfig getTransport() {
		return transport;
	}
	public void setTransport(TransportConfig transport) {
		this.transport = transport;
	}
}
