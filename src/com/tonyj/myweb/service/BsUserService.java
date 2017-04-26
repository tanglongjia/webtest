package com.tonyj.myweb.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tonyj.frame.plugin.Page;
import com.tonyj.myweb.dao.BsUserDao;
import com.tonyj.myweb.po.BsUser;

@Service
public class BsUserService {
	
	@Autowired
	private BsUserDao bsUserDao;
	
	public List<BsUser> getAllUserInfo()throws Exception{
		return bsUserDao.getAll();
	}
	
	public Page selectPage(BsUser user,Page page)throws Exception{
		return bsUserDao.selectPage(user,page);
	}
	
	public void saveUser(BsUser userInfo)throws Exception{
		bsUserDao.insertUser(userInfo);
	}
	
	public List<BsUser> getUserByLogin(Map map)throws Exception{
		return bsUserDao.getUserByLogin(map);
	}
	
	public BsUser getUserById(Integer id)throws Exception{
		return bsUserDao.getUserById(id);
	}
	
	public void updateUser(BsUser user)throws Exception{
		 bsUserDao.update(user);
	}
	
	public void deleteUser(Integer id)throws Exception{
		bsUserDao.delete(id);
	}
	
	public void updatePwd(Map map)throws Exception{
		bsUserDao.updatePwd(map);
	}
}
