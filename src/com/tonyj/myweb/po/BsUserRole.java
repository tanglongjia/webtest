package com.tonyj.myweb.po;

import com.tonyj.frame.orm.BaseEntity;

public class BsUserRole extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2108840441623496080L;
	
	private int userId;
	
	private int roleId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

}
