package com.tonyj.myweb.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tonyj.frame.orm.MyBatisDao;
import com.tonyj.frame.plugin.Page;
import com.tonyj.myweb.po.BsRole;

@Repository
public class BsRoleDao extends MyBatisDao<BsRole> {

	public List<BsRole> getAll(){
		return this.getSqlSession().selectList("com.tonyj.myweb.po.BsRole.getAll");
	}
	
	public Page selectListPage(BsRole bsRole,Page page){
		return super.selectPage(bsRole, page, "selectPage");
	}
	
	public int deleteRoleByPk(Map map){
		return this.getSqlSession().delete("com.tonyj.myweb.po.BsRole.deleteByPrimaryKey",map);
	}
	
	public void saveRole(BsRole bsRole){
		this.getSqlSession().insert("com.tonyj.myweb.po.BsRole.insert",bsRole);
	}
	
	public void updateRole(Map map){
		this.getSqlSession().update("com.tonyj.myweb.po.BsRole.updateByPrimaryKeySelective",map);
	}
	
	public  BsRole selectBsRoleByPk(Integer id){
		return this.getSqlSession().selectOne("com.tonyj.myweb.po.BsRole.selectByPrimaryKey", id);
	}
}
