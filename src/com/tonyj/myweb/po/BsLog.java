package com.tonyj.myweb.po;

import com.tonyj.frame.orm.BaseEntity;

public class BsLog extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -512074888818287362L;
	private String description;
	private String method;
	private long logType;
	private String requestIp;
	private String excepCode;
	private String excepDetail;
	private String params;
	private int operatorId;
	private String operatorName;
	private String beginTime;
	private String endTime;
	private String createDate;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public long getLogType() {
		return logType;
	}

	public void setLogType(long logType) {
		this.logType = logType;
	}

	public String getRequestIp() {
		return requestIp;
	}

	public void setRequestIp(String requestIp) {
		this.requestIp = requestIp;
	}

	public String getExcepCode() {
		return excepCode;
	}

	public void setExcepCode(String excepCode) {
		this.excepCode = excepCode;
	}

	public String getExcepDetail() {
		return excepDetail;
	}

	public void setExcepDetail(String excepDetail) {
		this.excepDetail = excepDetail;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public int getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(int operatorId) {
		this.operatorId = operatorId;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
