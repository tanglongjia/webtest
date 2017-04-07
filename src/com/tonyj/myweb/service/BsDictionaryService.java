package com.tonyj.myweb.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tonyj.frame.plugin.Page;
import com.tonyj.myweb.dao.BsDictionaryDao;
import com.tonyj.myweb.po.BsDictionary;

@Service
public class BsDictionaryService {

	@Autowired
	private BsDictionaryDao bsDictionaryDao;
	
	public List<BsDictionary> getDictList(Map map){
		return bsDictionaryDao.getAll(map);
	}
	
	public Page selectPage(BsDictionary dict,Page page){
		return bsDictionaryDao.selectPage(dict,page);
	}
	
	public List<Map> getFieldList(Map map){
		return bsDictionaryDao.getFieldList(map);
	}
	
	public void batchSaveDict(List<BsDictionary> list)throws Exception{
			bsDictionaryDao.batchSaveDict(list);
	}
	
	public int delDict(Map map)throws Exception{
		return bsDictionaryDao.delDict(map);
	}
	
	public void updateDict(Map map)throws Exception{
		bsDictionaryDao.updateDict(map);
	}
	
}
