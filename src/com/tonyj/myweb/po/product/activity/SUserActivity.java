package com.tonyj.myweb.po.product.activity;


import com.tonyj.frame.orm.BaseEntity;

public class SUserActivity extends BaseEntity{

    /**
	 * 
	 */
	private static final long serialVersionUID = -614188002431196118L;

	private Integer userid;

    private String username;

    private Integer activityid;

    private String activityname;

    private Integer compete;

    private String activitydesc;


    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Integer getActivityid() {
        return activityid;
    }

    public void setActivityid(Integer activityid) {
        this.activityid = activityid;
    }

    public String getActivityname() {
        return activityname;
    }

    public void setActivityname(String activityname) {
        this.activityname = activityname == null ? null : activityname.trim();
    }

    public Integer getCompete() {
        return compete;
    }

    public void setCompete(Integer compete) {
        this.compete = compete;
    }

    public String getActivitydesc() {
        return activitydesc;
    }

    public void setActivitydesc(String activitydesc) {
        this.activitydesc = activitydesc == null ? null : activitydesc.trim();
    }
}