package com.tonyj.myweb.po;

import com.tonyj.frame.orm.BaseEntity;

public class BsDepartment extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7714035094175956537L;

	/**
	 * 部门名称
	 */
	private String departName;
	
	/**
	 * 部门编码
	 */
	private String departCode;
	
	/**
	 * 部门等级
	 */
	private int departLevel; 
	
	
	/**
	 * 是否叶子节点 0否1是
	 */
	private int isLeafNode;


	public String getDepartName() {
		return departName;
	}


	public void setDepartName(String departName) {
		this.departName = departName;
	}


	public String getDepartCode() {
		return departCode;
	}


	public void setDepartCode(String departCode) {
		this.departCode = departCode;
	}


	public int getDepartLevel() {
		return departLevel;
	}


	public void setDepartLevel(int departLevel) {
		this.departLevel = departLevel;
	}


	public int getIsLeafNode() {
		return isLeafNode;
	}


	public void setIsLeafNode(int isLeafNode) {
		this.isLeafNode = isLeafNode;
	} 
	
	
	
}
