package com.tonyj.myweb.constant;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TreeBean {

	private Integer id;
	
	private Integer parentId;
	
	private String text;
	
	private Map state;
	
	private String selected;
	
	private List<TreeBean> children=new ArrayList<TreeBean>();

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<TreeBean> getChildren() {
		return children;
	}

	public void setChildren(List<TreeBean> children) {
		this.children = children;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Map getState() {
		return state;
	}

	public void setState(Map state) {
		this.state = state;
	}

	public String getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}
	
}
