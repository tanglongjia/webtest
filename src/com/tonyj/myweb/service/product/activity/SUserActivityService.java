package com.tonyj.myweb.service.product.activity;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tonyj.frame.plugin.Page;
import com.tonyj.myweb.dao.product.activity.SUserActivityDao;
import com.tonyj.myweb.po.product.activity.SUserActivity;

@Service
public class SUserActivityService {

	@Autowired
	private SUserActivityDao sUserActivityDao;
	
	
	public Page selectPage(SUserActivity activity,Page page)throws Exception{
		return sUserActivityDao.selectPage(activity,page);
	}
	
	public SUserActivity mbIsExits(Map parmMap)throws Exception{
		return sUserActivityDao.mbIsExits(parmMap);
	}
	
	public void saveSUserActivity(SUserActivity activity)throws Exception{
		sUserActivityDao.saveSUserActivity(activity);
	}
}
