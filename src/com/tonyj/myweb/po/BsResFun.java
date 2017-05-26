package com.tonyj.myweb.po;

import com.tonyj.frame.orm.BaseEntity;

public class BsResFun extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4353650093420842169L;

	private int resourceId;
	
	private int funcId;

	public int getResourceId() {
		return resourceId;
	}

	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}

	public int getFuncId() {
		return funcId;
	}

	public void setFuncId(int funcId) {
		this.funcId = funcId;
	}
	
}
