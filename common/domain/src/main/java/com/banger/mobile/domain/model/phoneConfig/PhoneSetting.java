/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :话机总配置信息
 * Author     :zsw
 * Create Date:Jun 1, 2012
 */
package com.banger.mobile.domain.model.phoneConfig;

import com.banger.mobile.domain.model.answerConfig.AnswerConfig;
import com.banger.mobile.domain.model.crmRecordRemind.CrmRecordRemind;
import com.banger.mobile.domain.model.crmRecordSetting.CrmRecordSetting;
import com.banger.mobile.domain.model.crmRingSetting.CrmRingSetting;
import com.banger.mobile.domain.model.system.ClustersConfig;
import com.banger.mobile.domain.model.transport.TransportConfig;

public class PhoneSetting {
	private AnswerConfig answer;
	private CrmRingSetting ring;
	private CrmRecordRemind remind;
	private CrmRecordSetting record;
	private PhoneConfig config;
	private TransportConfig transport;
	private ClustersConfig clusters;
	
	public AnswerConfig getAnswer() {
		return answer;
	}
	public void setAnswer(AnswerConfig answer) {
		this.answer = answer;
	}
	public CrmRingSetting getRing() {
		return ring;
	}
	public void setRing(CrmRingSetting ring) {
		this.ring = ring;
	}
	public CrmRecordRemind getRemind() {
		return remind;
	}
	public void setRemind(CrmRecordRemind remind) {
		this.remind = remind;
	}
	public CrmRecordSetting getRecord() {
		return record;
	}
	public void setRecord(CrmRecordSetting record) {
		this.record = record;
	}
	public PhoneConfig getConfig() {
		return config;
	}
	public void setConfig(PhoneConfig config) {
		this.config = config;
	}
	public TransportConfig getTransport() {
		return transport;
	}
	public void setTransport(TransportConfig transport) {
		this.transport = transport;
	}
	public ClustersConfig getClusters() {
		return clusters;
	}
	public void setClusters(ClustersConfig clusters) {
		this.clusters = clusters;
	}
	
	
}
