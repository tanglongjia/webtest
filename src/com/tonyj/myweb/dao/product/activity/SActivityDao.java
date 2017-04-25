package com.tonyj.myweb.dao.product.activity;

import org.springframework.stereotype.Repository;

import com.tonyj.frame.orm.MyBatisDao;
import com.tonyj.frame.plugin.Page;
import com.tonyj.myweb.po.product.activity.SActivity;

@Repository
public class SActivityDao extends MyBatisDao<SActivity> {

	public Page selectPage(SActivity activity,Page page)throws Exception{
		return this.selectPage(activity,page,"selectPage");
	}
	
	public void saveActivity(SActivity activity)throws Exception{
		this.insert(activity);
	}
	
	public SActivity getActivityById(Integer id)throws Exception{
		return this.selectSingle(id);
	}
	
	public void updateActivity(SActivity activity)throws Exception{
		this.update(activity);
	}
	
	public void deleteActivity(Integer id)throws Exception{
		this.delete(id);
	}
	
}
