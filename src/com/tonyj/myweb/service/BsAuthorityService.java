package com.tonyj.myweb.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tonyj.myweb.annotation.SystemLogService;
import com.tonyj.myweb.dao.BsAuthorityDao;


@Service
public class BsAuthorityService {

	@Autowired
	private BsAuthorityDao bsAuthorityDao;
	
	@SystemLogService(description = "保存角色菜单信息")
	public void savePz(Map map){
		List roleMenuList = (List) map.get("roleMenu");
		bsAuthorityDao.deleteRoleMenu(map);
		bsAuthorityDao.saveRoleMenu(roleMenuList);
	}
	
	@SystemLogService(description = "保存用户角色信息")
	public void saveUserRole(Map map){
		List roleMenuList = (List) map.get("roleMenu");
		bsAuthorityDao.deleteUserRole(map);
		bsAuthorityDao.saveUserRole(roleMenuList);
	}
	
	@SystemLogService(description = "查询角色信息列表")
	public List selectRole(Map map){
		return bsAuthorityDao.selectRole(map);
	}
	
	@SystemLogService(description = "查询菜单信息列表")
	public List selectMenu(Map map){
		return bsAuthorityDao.selectMenu(map);
	}
}
