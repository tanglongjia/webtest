package com.tonyj.myweb.service.product.activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tonyj.frame.plugin.Page;
import com.tonyj.myweb.dao.product.activity.SActivityDao;
import com.tonyj.myweb.po.product.activity.SActivity;

@Service
public class SActivityService {

	@Autowired
	private SActivityDao sactivityDao;
	
	public Page selectPage(SActivity activity,Page page)throws Exception{
		return sactivityDao.selectPage(activity,page);
	}
	
	public void saveActivity(SActivity activity)throws Exception{
		sactivityDao.saveActivity(activity);
	}
	
	public SActivity getActivityById(Integer id)throws Exception{
		return sactivityDao.getActivityById(id);
	}
	
	public void updateActivity(SActivity activity)throws Exception{
		sactivityDao.updateActivity(activity);
	}
	
	public void deleteActivity(Integer id)throws Exception{
		sactivityDao.deleteActivity(id);
	}
	
}
