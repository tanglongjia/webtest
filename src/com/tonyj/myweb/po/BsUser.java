package com.tonyj.myweb.po;

import java.util.Date;

import com.tonyj.frame.orm.BaseEntity;

public class BsUser extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -140573316950084819L;

	private String loginName;
	
	private String trueName;
	
	private String passWord;
	
	private int sex;
	
	private int age;
	
	private String email;
	
	private String telephone;
	
	private Date lastLoginTime;
	
	private String remark;
	
	/**
	 * 所属部门
	 */
	private BsDepartment bsDepart;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public BsDepartment getBsDepart() {
		return bsDepart;
	}

	public void setBsDepart(BsDepartment bsDepart) {
		this.bsDepart = bsDepart;
	}
	
	
	
}
