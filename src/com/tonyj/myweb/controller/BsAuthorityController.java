package com.tonyj.myweb.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.tonyj.frame.plugin.Page;
import com.tonyj.frame.util.MessageStreamResult;
import com.tonyj.frame.web.BaseController;
import com.tonyj.myweb.annotation.SystemLogBeforeController;
import com.tonyj.myweb.constant.Constant;
import com.tonyj.myweb.constant.TreeBean;
import com.tonyj.myweb.po.BsRole;
import com.tonyj.myweb.po.BsUser;
import com.tonyj.myweb.service.BsAuthorityService;
import com.tonyj.myweb.service.BsResourceService;
import com.tonyj.myweb.service.BsRoleService;
import com.tonyj.myweb.service.BsUserService;

@Controller
@RequestMapping("bsAuthority")
public class BsAuthorityController extends BaseController {
	
	@Autowired
	private BsUserService bsUserService;
	
	@Autowired
	private BsRoleService bsRoleService;
	
	@Autowired
	private BsResourceService bsResourceService;
	@Autowired
	private BsAuthorityService bsAuthorityService;
	
	@RequestMapping(value="/index",method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response,ModelMap model) throws Exception{
		return new ModelAndView(Constant.PAGE_AUT_PATH+"authIndex", model) ;
	}
	
	@RequestMapping(value="/userRoleIndex",method = RequestMethod.GET)
	@SystemLogBeforeController(description = "初始化用户角色管理页面")
	public ModelAndView userRoleindex(HttpServletRequest request, HttpServletResponse response,ModelMap model) throws Exception{
		List<BsRole> roleList = bsRoleService.getAll();
		model.put("roleList", roleList);
		return new ModelAndView(Constant.PAGE_AUT_PATH+"userRoleIndex", model) ;
	}
	
	@RequestMapping(value="/getUserData")
	@SystemLogBeforeController(description = "查询用户角色管理页面的用户信息")
    public ModelAndView getUserData(HttpServletRequest request, HttpServletResponse response,BsUser bsUser,Page page,ModelMap model){
    	try {
			try {
				page = bsUserService.selectPage(bsUser, page);
			} catch (Exception e) {
				e.printStackTrace();
			}
			String jsonStr = JSON.toJSONString(page.getResultList());
			long total = page.getTotalResult();
			jsonStr = "{\"total\":"+total+",\"rows\":"+jsonStr+"}";
			System.out.println("jsonStr:-->"+jsonStr);
			MessageStreamResult.msgStreamResult(response, jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
    }
	
	/**
	 * 保存角色菜单
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/saveUserRole")
	@SystemLogBeforeController(description = "保存用户角色设置")
    public ModelAndView saveUserRole(HttpServletRequest request, HttpServletResponse response){
		int userId = new Integer(request.getParameter("userId"));
		String[] roles = request.getParameterValues("roleId");
		//角色权限
		List<Map> roleMenuList = new ArrayList<Map>();
		Map roleMenuMap = null;
		if(roles!=null && roles.length > 0){
			for(String role:roles){
					roleMenuMap = new HashMap();
					roleMenuMap.put("roleId", new Integer(role));
					roleMenuMap.put("userId", userId);
					roleMenuMap.put("createdby", 0);
					roleMenuList.add(roleMenuMap);
			}
		}
		Map map = new HashMap();
		map.put("roleMenu", roleMenuList);
		try {
			bsAuthorityService.saveUserRole(map);
			MessageStreamResult.msgStreamResult(response, "0");
		} catch (Exception e) {
			try {
				MessageStreamResult.msgStreamResult(response, "1");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
    	return null;
    }
	
	@RequestMapping(value="/getRoleData")
	@SystemLogBeforeController(description = "查询角色菜单页面的角色信息")
    public ModelAndView getRoleData(HttpServletRequest request, HttpServletResponse response,BsRole bsRole,Page page,ModelMap model){
    	try {
			try {
				page = bsRoleService.selectListPage(bsRole, page);
			} catch (Exception e) {
				e.printStackTrace();
			}
			String jsonStr = JSON.toJSONString(page.getResultList());
			long total = page.getTotalResult();
			jsonStr = "{\"total\":"+total+",\"rows\":"+jsonStr+"}";
			System.out.println("jsonStr:-->"+jsonStr);
			MessageStreamResult.msgStreamResult(response, jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
    }
	
	/**
	 * 保存配置
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/savePz")
	@SystemLogBeforeController(description = "保存角色菜单页面的配置")
    public ModelAndView savePz(HttpServletRequest request, HttpServletResponse response){
		int roleId = new Integer(request.getParameter("roleId"));
		String[] menuIds = request.getParameterValues("menuId");
		//角色权限
		List<Map> roleMenuList = new ArrayList<Map>();
		Map roleMenuMap = null;
		if(menuIds!=null && menuIds.length > 0){
			for(String menu:menuIds){
					roleMenuMap = new HashMap();
					roleMenuMap.put("roleId", roleId);
					roleMenuMap.put("resourceId", new Integer(menu));
					roleMenuMap.put("createdby", 0);
					roleMenuList.add(roleMenuMap);
			}
		}
		Map map = new HashMap();
		map.put("roleMenu", roleMenuList);
		try {
			bsAuthorityService.savePz(map);
			MessageStreamResult.msgStreamResult(response, "0");
		} catch (Exception e) {
			try {
				MessageStreamResult.msgStreamResult(response, "1");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
    	return null;
    }
	
	@RequestMapping(value="/getResTree")
	@SystemLogBeforeController(description = "查询角色菜单页面的菜单树")
    public ModelAndView getResTree(HttpServletRequest request, HttpServletResponse response,BsUser user,Page page,ModelMap model){
		Map<String,Integer> paramMap= new HashMap<String,Integer>();
		paramMap.put("parentId", 0);
		List<TreeBean> treeList = bsResourceService.getTree(paramMap);
		if(!treeList.isEmpty()){
			for (TreeBean tb : treeList) {
					Map<String,Integer> pMap = new HashMap<String,Integer>();
					pMap.put("parentId", tb.getId());
					List<TreeBean> childList = bsResourceService.getTree(pMap);
					for (TreeBean treeBean : childList) {
						Map state = new HashMap();
						state.put("opened", true);
						//state.put("selected",true);
						treeBean.setState(state);
					}
					tb.setChildren(childList);
					Map state = new HashMap();
					state.put("opened", true);
					tb.setState(state);
			}
		}
		//构建顶层
		/*TreeBean tb = new TreeBean();
		tb.setId(0);
		tb.setParentId(-1);
		tb.setChildren(treeList);
		tb.setText("全部");*/
		String returnValue = JSONArray.toJSONString(treeList);
		try {
			MessageStreamResult.msgStreamResult(response, returnValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
    }
	
	
	/**
	 * 选中角色
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/selectRole")
	@SystemLogBeforeController(description = "用户角色页面查询该用户的角色")
    public ModelAndView selectRole(HttpServletRequest request, HttpServletResponse response){
		int userId = new Integer(request.getParameter("userId"));
		Map map = new HashMap();
		map.put("userId", userId);
		List roleList = null;
		try {
			 roleList = bsAuthorityService.selectRole(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String returnValue = JSONArray.toJSONString(roleList);
		try {
			MessageStreamResult.msgStreamResult(response, returnValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 选中角色
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/selectMenu")
	@SystemLogBeforeController(description = "角色菜单页面查询该角色对应的菜单")
    public ModelAndView selectMenu(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String roleId = request.getParameter("roleId");
		Map<String,Integer> paramMap= new HashMap<String,Integer>();
		paramMap.put("parentId", 0);
		if(roleId !=null && roleId !=""){
			paramMap.put("roleId", new Integer(roleId));
		}else{
			paramMap.put("roleId", null);
		}
		List<TreeBean> treeList = bsAuthorityService.selectMenu(paramMap);
		if(!treeList.isEmpty()){
			for (TreeBean tb : treeList) {
					Map<String,Integer> pMap = new HashMap<String,Integer>();
					pMap.put("parentId", tb.getId());
					if(roleId !=null && roleId !=""){
						pMap.put("roleId", new Integer(roleId));
					}else{
						pMap.put("roleId", null);
					}
					List<TreeBean> childList = bsAuthorityService.selectMenu(pMap);
					for (TreeBean treeBean : childList) {
						Map state = new HashMap();
						state.put("opened", true);
						if("selected".equals(treeBean.getSelected())){
							state.put("selected",true);
						}else{
							state.put("selected",false);
						}
						treeBean.setState(state);
					}
					tb.setChildren(childList);
					Map state = new HashMap();
					state.put("opened", true);
					/*if("selected".equals(tb.getSelected())){
						state.put("selected",true);
					}*/
					tb.setState(state);
			}
		}
		String returnValue = JSONArray.toJSONString(treeList);
		try {
			MessageStreamResult.msgStreamResult(response, returnValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
