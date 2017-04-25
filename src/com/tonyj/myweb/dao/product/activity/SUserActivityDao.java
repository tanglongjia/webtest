package com.tonyj.myweb.dao.product.activity;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tonyj.frame.orm.MyBatisDao;
import com.tonyj.frame.plugin.Page;
import com.tonyj.myweb.po.product.activity.SUserActivity;

@Repository
public class SUserActivityDao extends MyBatisDao<SUserActivity> {

	public Page selectPage(SUserActivity activity,Page page)throws Exception{
		return this.selectPage(activity, page,"selectPage");
	}
	
	public SUserActivity mbIsExits(Map parmMap)throws Exception{
		return this.getSqlSession().selectOne("mbIsExits", parmMap);
	}
	
	public void saveSUserActivity(SUserActivity activity)throws Exception{
		this.insert(activity);
	}
}
