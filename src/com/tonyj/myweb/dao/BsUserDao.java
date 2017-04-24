package com.tonyj.myweb.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tonyj.frame.orm.MyBatisDao;
import com.tonyj.frame.plugin.Page;
import com.tonyj.myweb.po.BsUser;

@Repository
public class BsUserDao extends MyBatisDao<BsUser> {

	public List<BsUser> getAll(){
		return this.getSqlSession().selectList("getAll");
	}
	
	public List<BsUser> findByUser(BsUser userInfo){
		return this.getSqlSession().selectList("findByUser",userInfo);
	}
	
	public Page selectPage(BsUser userInfo,Page page){
		return this.selectPage(userInfo,page,"selectPage");
	}
	
	public List<BsUser> getUserByLogin(Map map){
		return this.getSqlSession().selectList("getUserByLogin",map);
	}
	
	public void insertUser(BsUser user)throws Exception{
		this.insert(user);
	}
}
