package com.tonyj.myweb.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tonyj.frame.orm.MyBatisDao;
import com.tonyj.frame.plugin.Page;
import com.tonyj.myweb.po.BsUser;

@Repository
public class BsUserDao extends MyBatisDao<BsUser> {

	public List<BsUser> getAll()throws Exception{
		return this.getSqlSession().selectList("getAll");
	}
	
	public List<BsUser> findByUser(BsUser userInfo)throws Exception{
		return this.getSqlSession().selectList("findByUser",userInfo);
	}
	
	public Page selectPage(BsUser userInfo,Page page)throws Exception{
		return this.selectPage(userInfo,page,"selectPage");
	}
	
	public List<BsUser> getUserByLogin(Map map)throws Exception{
		return this.getSqlSession().selectList("getUserByLogin",map);
	}
	
	public void insertUser(BsUser user)throws Exception{
		this.insert(user);
	}
	
	public BsUser getUserById(Integer id)throws Exception{
		return this.selectSingle(id);
	}
}
