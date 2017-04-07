package com.tonyj.myweb.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tonyj.frame.orm.MyBatisDao;
import com.tonyj.frame.plugin.Page;
import com.tonyj.myweb.constant.TreeBean;
import com.tonyj.myweb.po.BsResource;

@Repository
public class BsResourceDao extends MyBatisDao<BsResource> {

	public List<BsResource> getBsResourceByParentId(Map<String,Integer> paramMap){
		return this.getSqlSession().selectList("getBsResourceByParentId",paramMap);
	}
	
	public List<TreeBean> getTree(Map paramMap){
		return this.getSqlSession().selectList("getTree",paramMap);
	}
	
	public Page selectListPage(BsResource bsRes,Page page){
		return super.selectPage(bsRes, page, "selectPage");
	}
}
