package com.tonyj.myweb.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tonyj.frame.plugin.Page;
import com.tonyj.myweb.dao.BsRoleDao;
import com.tonyj.myweb.po.BsRole;

@Service
public class BsRoleService {

	@Autowired
	private BsRoleDao bsRoleDao;
	
	public Page selectListPage(BsRole bsRole,Page page)throws Exception{
		return bsRoleDao.selectListPage(bsRole,page);
	}
	
	public BsRole selectBsRoleByPk(Integer id)throws Exception{
		return bsRoleDao.selectBsRoleByPk(id);
	}
	
	public int deleteRoleByPk(Map map)throws Exception{
		return bsRoleDao.deleteRoleByPk(map);
	}
	
	public void saveRole(BsRole bsRole)throws Exception{
		bsRoleDao.saveRole(bsRole);
	}
	
	public void updateRole(Map map)throws Exception{
		bsRoleDao.updateRole(map);
	}
}
