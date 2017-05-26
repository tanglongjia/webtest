package com.tonyj.myweb.po;

import com.tonyj.frame.orm.BaseEntity;

public class BsRoleRes extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4440864838984813503L;

	
	private int roleId;
	
	private int resourceId;

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getResourceId() {
		return resourceId;
	}

	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}
}
