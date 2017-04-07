package com.tonyj.myweb.po;

import java.util.ArrayList;
import java.util.List;

import com.tonyj.frame.orm.BaseEntity;

public class BsResource extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2667532054573965407L;
	
	private String menuCode;
	
	private String menuName;
	
	private String menuUrl;
	
	private int menuLevel;
	
	private int isLeafNode;
	
	private String menuImgPath;
	
	private String menuDesc;
	
	private List<BsResource> childList=new ArrayList<BsResource>();

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public int getMenuLevel() {
		return menuLevel;
	}

	public void setMenuLevel(int menuLevel) {
		this.menuLevel = menuLevel;
	}

	public int getIsLeafNode() {
		return isLeafNode;
	}

	public void setIsLeafNode(int isLeafNode) {
		this.isLeafNode = isLeafNode;
	}

	public String getMenuImgPath() {
		return menuImgPath;
	}

	public void setMenuImgPath(String menuImgPath) {
		this.menuImgPath = menuImgPath;
	}

	public String getMenuDesc() {
		return menuDesc;
	}

	public void setMenuDesc(String menuDesc) {
		this.menuDesc = menuDesc;
	}

	public List<BsResource> getChildList() {
		return childList;
	}

	public void setChildList(List<BsResource> childList) {
		this.childList = childList;
	}
	
	

}
