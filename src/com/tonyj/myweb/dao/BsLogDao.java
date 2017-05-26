package com.tonyj.myweb.dao;

import org.springframework.stereotype.Repository;

import com.tonyj.frame.orm.MyBatisDao;
import com.tonyj.frame.plugin.Page;
import com.tonyj.myweb.po.BsLog;

@Repository
public class BsLogDao extends MyBatisDao<BsLog>{

	public Page selectPage(BsLog bsLog,Page page){
		return this.selectPage(bsLog,page,"selectPage");
	}
	
	public void saveLog(BsLog bsLog){
		this.insert(bsLog);
	}
}
