package com.banger.mobile;

import java.util.List;

public class PageUtil<T> {

	private List<T> items;

	private Page page;

	public PageUtil(List<T> items,Page page){
		this.items = items;
		this.page = page;
	}

	public List<T> getItems() {
		return items;
	}

	public Page getPage() {
		return page;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}



}
