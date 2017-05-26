package com.tonyj.myweb.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tonyj.frame.plugin.Page;
import com.tonyj.myweb.annotation.SystemLogService;
import com.tonyj.myweb.constant.TreeBean;
import com.tonyj.myweb.dao.BsResourceDao;
import com.tonyj.myweb.po.BsResource;

@Service
public class BsResourceService {

	@Autowired
	private BsResourceDao bsResourceDao;
	
	@SystemLogService(description = "根据父id查询下级菜单")
	public List<BsResource> getLeftMenu(Map<String,Integer> paramMap){
		return bsResourceDao.getBsResourceByParentId(paramMap);
	}
	@SystemLogService(description = "获取菜单树")
	public List<TreeBean> getTree(Map map){
		return bsResourceDao.getTree(map);
	}
	@SystemLogService(description = "分页查询菜单信息")
	public Page selectListPage(BsResource bsRes,Page page){
		return bsResourceDao.selectListPage(bsRes,page);
	}
	@SystemLogService(description = "根据Id查询菜单信息")
	public BsResource getResByPk(Integer id){
		return bsResourceDao.getResByPk(id);
	}
	@SystemLogService(description = "根据Id更新菜单信息")
	public void updateResByPk(Map map){
		 bsResourceDao.updateResByPk(map);
	}
	@SystemLogService(description = "保存菜单信息")
	public void saveRes(BsResource bsRes){
		 bsResourceDao.savaRes(bsRes);
	}
}
