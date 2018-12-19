package com.banger.mobile.domain.model.adsync;

import java.util.ArrayList;
import java.util.List;

public class SyncAdNode {
	private String fullName;			//AD节点全名称
	private String name;				//AD节点显示名称
	private String account;				//AD用户节点帐号
	private String parentName;			//AD父节点全名称
	private Integer targetId;			//数据库对应的ID
	private SyncAdNode parent;			//父节点
	private List<SyncAdNode> children;	//子节点
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public List<SyncAdNode> getChildren() {
		return children;
	}
	public void setChildren(List<SyncAdNode> children) {
		this.children = children;
	}
	public Integer getTargetId() {
		return targetId;
	}
	public void setTargetId(Integer targetId) {
		this.targetId = targetId;
	}
	public SyncAdNode getParent() {
		return parent;
	}
	public void setParent(SyncAdNode parent) {
		this.parent = parent;
	}
	public void add(SyncAdNode node){
		if(this.children==null)this.children=new ArrayList<SyncAdNode>();
		this.children.add(node);
	}
	
}
