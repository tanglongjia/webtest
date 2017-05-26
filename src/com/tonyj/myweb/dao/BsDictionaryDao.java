package com.tonyj.myweb.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tonyj.frame.orm.MyBatisDao;
import com.tonyj.frame.plugin.Page;
import com.tonyj.myweb.po.BsDictionary;

@Repository
public class BsDictionaryDao extends MyBatisDao<BsDictionary> {

	public List<BsDictionary> getAll(Map map){
		return this.getSqlSession().selectList("com.tonyj.myweb.dao.BsDictionary.getAll",map);
	}
	
	public Page selectPage(BsDictionary dict,Page page){
		return this.selectPage(dict,page,"selectPage");
	}
	
	public List<Map> getFieldList(Map map){
		return this.getSqlSession().selectList("com.tonyj.myweb.dao.BsDictionary.getField",map);
	}
	
	public void batchSaveDict(List<BsDictionary> list){
		this.getSqlSession().insert("com.tonyj.myweb.dao.BsDictionary.batchSaveDict", list);
	}
	
	public int delDict(Map map){
		return this.getSqlSession().delete("com.tonyj.myweb.dao.BsDictionary.delDict", map);
	}
	
	public void updateDict(Map map){
		 this.getSqlSession().update("com.tonyj.myweb.dao.BsDictionary.updateDict", map);
	}
}
