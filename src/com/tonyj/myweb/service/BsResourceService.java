package com.tonyj.myweb.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tonyj.frame.plugin.Page;
import com.tonyj.myweb.constant.TreeBean;
import com.tonyj.myweb.dao.BsResourceDao;
import com.tonyj.myweb.po.BsResource;

@Service
public class BsResourceService {

	@Autowired
	private BsResourceDao bsResourceDao;
	
	
	public List<BsResource> getLeftMenu(Map<String,Integer> paramMap){
		return bsResourceDao.getBsResourceByParentId(paramMap);
	}
	
	public List<TreeBean> getTree(Map map){
		return bsResourceDao.getTree(map);
	}
	
	public Page selectListPage(BsResource bsRes,Page page){
		return bsResourceDao.selectListPage(bsRes,page);
	}

}
