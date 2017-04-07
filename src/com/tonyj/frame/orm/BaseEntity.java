package com.tonyj.frame.orm;

import java.io.Serializable;
import java.util.Date;

import com.tonyj.frame.plugin.Page;

/**
 *@author TonyJ
 *@time 2015-1-31 下午03:37:46
 *@email tanglongjia@126.com
 */
public class BaseEntity implements Serializable,Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4904656875584790988L;

	/**
	 *标识Id 
	 */
	private int id;
	
    /**
     * 创建时间
     */
    private Date created;
    
    /**
     * 创建人
     */
    private int createdby;
    
    /**
     * 更新时间
     */
    private Date updated;
    
    /**
     * 更新人
     */
    private int updatedby;
    
    /**
     * 状态 0停用 1启用 2无效
     */
    private int status;
    
	 /** 分页属性 */
    private Page pagination;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public int getCreatedby() {
		return createdby;
	}

	public void setCreatedby(int createdby) {
		this.createdby = createdby;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public int getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(int updatedby) {
		this.updatedby = updatedby;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Page getPagination() {
		return pagination;
	}

	public void setPagination(Page pagination) {
		this.pagination = pagination;
	}

	
}
