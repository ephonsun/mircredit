/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :文件传输集群端口配置
 * Author     :zsw
 * Version    :
 * Create Date:May 3, 2012
 */
package com.banger.mobile.facade.impl.system.clusters;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import com.banger.mobile.domain.model.system.ClustersConfig;
import com.banger.mobile.domain.model.transport.TransportConfig;
import com.banger.mobile.facade.clusters.ClustersConfigService;
import com.banger.mobile.util.RandomUtil;
import com.banger.mobile.util.StringUtil;

public class ClustersConfigServiceImpl implements ClustersConfigService {
	private String index;						//服务器索引
	private String enable;						//是否开启集群
	private String list;						//文件传输端口列表
	private String servers;						//服务器列表
	private List<TransportConfig> configList;	//文件传输配置对像列表
	private List<ClustersConfig> clustersList;	//服务器配置对像列表
	private TransportConfig defaultConfig;		//当没开启集群配置时的默认配置
	
	public ClustersConfigServiceImpl()
	{
		defaultConfig = new TransportConfig();
		defaultConfig.setEnable(true);
		defaultConfig.setPort(7878);
	}
	
	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public String getList() {
		return list;
	}
	
	public void setList(String list) {
		this.list = list;
	}
	
	public String getServers() {
		return servers;
	}

	public void setServers(String servers) {
		this.servers = servers;
	}

	/**
	 * 获得配置列表
	 * @return
	 */
	public List<TransportConfig> getConfigList(){
		if(this.configList==null)
		{
			this.configList=new ArrayList<TransportConfig>();
			if(!StringUtil.isNullOrEmpty(this.list)){
				try{
					String[] servers = this.list.split(",");
					for(String server : servers){
						String[] parts = server.split(":");
						TransportConfig config = new TransportConfig();
						config.setEnable(parts[1].equalsIgnoreCase("open"));
						config.setPort(Integer.parseInt(parts[0]));
						this.configList.add(config);
					}
				}
				catch(Exception e)
				{
					throw new RuntimeException("集群配置参数出错  talk.serverConfig.list="+this.list);
				}
			}
		}
		return this.configList;
	}
	
	/**
	 * 获得集群列表
	 * @return
	 */
	public List<ClustersConfig> getClustersList(){
		if(this.clustersList==null)
		{
			this.clustersList=new ArrayList<ClustersConfig>();
			if(!StringUtil.isNullOrEmpty(this.servers)){
				try{
					String[] ss = this.servers.split(",");
					for(String s : ss){
						String[] parts = s.split(":");
						ClustersConfig config = new ClustersConfig();
						config.setAddress(parts[0]);
						config.setPort(Integer.parseInt(parts[1]));
						this.clustersList.add(config);
					}
				}
				catch(Exception e)
				{
					throw new RuntimeException("集群配置参数出错  talk.serverConfig.servers="+this.servers);
				}
			}
		}
		return this.clustersList;
	}

	/**
	 * 通到传输配置
	 */
	public TransportConfig getTransportConfig() {
		if("true".equalsIgnoreCase(this.enable)){
			try{
				int configIndex = Integer.parseInt(this.index);
				return getTransportConfigByIndex(configIndex-1);
			}catch(Exception e){
				throw new RuntimeException("集群配置参数   talk.serverConfig.index="+this.index+" 不是数字");
			}
		}
		else
		{
			return defaultConfig;
		}
	}
	
	private TransportConfig getTransportConfigByIndex(int index){
		if(index<this.getConfigList().size()){
			return this.getConfigList().get(index);
		}else throw new RuntimeException("集群配置参数   talk.serverConfig.index="+(index+1)+" 超出范围");
	}

	/**
	 * 得到tomcat集群配置
	 */
	public ClustersConfig getClustersConfig() {
		if("true".equalsIgnoreCase(this.enable)){
			int configIndex = Integer.parseInt(this.index);
			return getClustersConfigByIndex(configIndex-1);
		}
		else
		{
			HttpServletRequest request = org.apache.struts2.ServletActionContext.getRequest();
			ClustersConfig config = new ClustersConfig();
			config.setAddress(request.getServerName());
			config.setPort(request.getServerPort());
			config.setTransport(defaultConfig);
			return config;
		}
	}
	
	private ClustersConfig getClustersConfigByIndex(int index){
		if(index<this.getClustersList().size()){
			TransportConfig currentTransportConfig = this.getTransportConfigByIndex(index);
			if(currentTransportConfig.getEnable())
			{
				ClustersConfig config = this.getClustersList().get(index);
				config.setTransport(currentTransportConfig);
				return config;
			}
			else
			{
				Integer[] enableIndexs = getEnableConfigIndex();
				if(enableIndexs.length>0){
					 int r = RandomUtil.seedIntBetween(0,enableIndexs.length-1);
					 return this.getClustersConfigByIndex(enableIndexs[r]);
				}
				else throw new RuntimeException("集群配置参数出错  没有开启可用的文件传输服务");
			}
		}else throw new RuntimeException("集群配置参数   talk.serverConfig.index="+(index+1)+" 超出范围");
	}
	
	/**
	 * 得到开启传输的服务器配置列表
	 * @return
	 */
	private Integer[] getEnableConfigIndex(){
		List<Integer> indexs=new ArrayList<Integer>();
		if(this.configList!=null)
		{
			for(int i=0;i<this.configList.size();i++){
				if(this.configList.get(i).getEnable()){
					indexs.add(i);
				}
			}
		}
		return indexs.toArray(new Integer[0]);
	}
}
