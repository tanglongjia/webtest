package com.tonyj.myweb.po;

import com.tonyj.frame.orm.BaseEntity;

public class BsFunction extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5384664949429914279L;
	
	private String funName;
	
	private String funCode;
	
	private String funDesc;

	public String getFunName() {
		return funName;
	}

	public void setFunName(String funName) {
		this.funName = funName;
	}

	public String getFunCode() {
		return funCode;
	}

	public void setFunCode(String funCode) {
		this.funCode = funCode;
	}

	public String getFunDesc() {
		return funDesc;
	}

	public void setFunDesc(String funDesc) {
		this.funDesc = funDesc;
	}
	
	

}
