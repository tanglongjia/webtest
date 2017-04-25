package com.tonyj.myweb.po.product.activity;

import com.tonyj.frame.orm.BaseEntity;

public class SActivity extends BaseEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3972706403757225092L;

    private String activityname;

    private String activitycontent;

    private String activitydate;

    private Integer end;

    public String getActivityname() {
        return activityname;
    }

    public void setActivityname(String activityname) {
        this.activityname = activityname == null ? null : activityname.trim();
    }

    public String getActivitycontent() {
        return activitycontent;
    }

    public void setActivitycontent(String activitycontent) {
        this.activitycontent = activitycontent == null ? null : activitycontent.trim();
    }


    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

	public String getActivitydate() {
		return activitydate;
	}

	public void setActivitydate(String activitydate) {
		this.activitydate = activitydate;
	}

}