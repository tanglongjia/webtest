package com.tonyj.myweb.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.tonyj.frame.plugin.Page;
import com.tonyj.myweb.dao.BsUserDao;
import com.tonyj.myweb.po.BsUser;

@Service
public class BsUserService {
	
	@Autowired
	private BsUserDao bsUserDao;
	
	public List<BsUser> getAllUserInfo(){
		return bsUserDao.getAll();
	}
	
	public Page selectPage(BsUser user,Page page){
		return bsUserDao.selectPage(user,page);
	}
	
	public String loginSystem(BsUser user,HttpServletRequest request, Model model){
		List<BsUser> userList = bsUserDao.findByUser(user);
		if(CollectionUtils.isEmpty(userList)){
			model.addAttribute("msg","用户名或者密码错误!");
			return "login";
		}else{
			return "index";
		}
	}
	
	public void saveUser(BsUser userInfo)throws Exception{
		bsUserDao.insertUser(userInfo);
	}
	
	public List<BsUser> getUserByLogin(Map map){
		return bsUserDao.getUserByLogin(map);
	}
}
