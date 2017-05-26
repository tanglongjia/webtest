package com.tonyj.myweb.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tonyj.frame.plugin.Page;
import com.tonyj.myweb.annotation.SystemLogService;
import com.tonyj.myweb.dao.BsUserDao;
import com.tonyj.myweb.po.BsUser;

@Service
public class BsUserService {
	
	@Autowired
	private BsUserDao bsUserDao;
	
	public List<BsUser> getAllUserInfo(){
		return bsUserDao.getAll();
	}
	@SystemLogService(description = "查询用户列表")
	public Page selectPage(BsUser user,Page page){
		return bsUserDao.selectPage(user,page);
	}
	
	@SystemLogService(description = "保存用户信息")
	public void saveUser(BsUser userInfo){
		int a = 1/0;
		bsUserDao.insertUser(userInfo);
	}
	
	@SystemLogService(description = "查询登录用户")
	public List<BsUser> getUserByLogin(Map map){
		return bsUserDao.getUserByLogin(map);
	}
	
	@SystemLogService(description = "根据用户id查询用户")
	public BsUser getUserById(Integer id){
		return bsUserDao.getUserById(id);
	}
	
	@SystemLogService(description = "更新用户信息")
	public void updateUser(BsUser user){
		 bsUserDao.update(user);
	}
	
	@SystemLogService(description = "根据用户Id删除用户")
	public void deleteUser(Integer id){
		bsUserDao.delete(id);
	}
	@SystemLogService(description = "更新用户密码")
	public void updatePwd(Map map){
		bsUserDao.updatePwd(map);
	}
}
