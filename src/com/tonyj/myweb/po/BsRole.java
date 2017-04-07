package com.tonyj.myweb.po;

import com.tonyj.frame.orm.BaseEntity;

public class BsRole extends BaseEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 444269204038055658L;

	private String rolename;

    private String roledesc;


    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
    }

    public String getRoledesc() {
        return roledesc;
    }

    public void setRoledesc(String roledesc) {
        this.roledesc = roledesc == null ? null : roledesc.trim();
    }

}