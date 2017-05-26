package com.tonyj.myweb.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.tonyj.frame.plugin.Page;
import com.tonyj.frame.util.MessageStreamResult;
import com.tonyj.frame.web.BaseController;
import com.tonyj.myweb.annotation.SystemLogBeforeController;
import com.tonyj.myweb.constant.Constant;
import com.tonyj.myweb.constant.TreeBean;
import com.tonyj.myweb.po.BsResource;
import com.tonyj.myweb.po.BsUser;
import com.tonyj.myweb.service.BsResourceService;

@Controller
@RequestMapping("bsRes")
public class BsResourceController extends BaseController {

	
	@Autowired
	private BsResourceService bsResourceService;
	/**
	 * 生成左侧菜单树
	 * @return
	 */
	@RequestMapping(value="/getLeftMenu", method=RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    @SystemLogBeforeController(description = "生成左侧菜单树")
    public String getLeftMenu(HttpServletRequest request, HttpServletResponse response){
		//从session中获取userid
		BsUser bsUser = null;
		if(request.getSession().getAttribute("bsUser") == null){
			String contextPath = request.getContextPath();
			try {
				response.sendRedirect(contextPath+"/login.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			bsUser = (BsUser) request.getSession().getAttribute("bsUser");
		}
		Map<String,Integer> paramMap= new HashMap<String,Integer>();
		paramMap.put("parentId", 0);
		paramMap.put("userId", bsUser.getId());
		List<BsResource> bsResouceList = bsResourceService.getLeftMenu(paramMap);
		if(!bsResouceList.isEmpty()){
			for (BsResource bsResource : bsResouceList) {
					Map<String,Integer> pMap = new HashMap<String,Integer>();
					pMap.put("parentId", bsResource.getId());
					pMap.put("userId", bsUser.getId());
					List<BsResource> childList = bsResourceService.getLeftMenu(pMap);
					bsResource.setChildList(childList);
			}
		}
    	String jsonStr = JSON.toJSONString(bsResouceList);
    	return jsonStr;
    }
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(HttpServletRequest request, HttpServletResponse response) {
		return Constant.PAGE_RES_PATH + "resIndex";
	}
	
	@RequestMapping(value="/getTree")
	@SystemLogBeforeController(description = "生成菜单页面的左侧树")
	public ModelAndView getTree(HttpServletRequest request, HttpServletResponse response,ModelMap model){
		Map<String,Integer> paramMap= new HashMap<String,Integer>();
		paramMap.put("parentId", 0);
		List<TreeBean> treeList = bsResourceService.getTree(paramMap);
		if(!treeList.isEmpty()){
			for (TreeBean tb : treeList) {
					Map<String,Integer> pMap = new HashMap<String,Integer>();
					pMap.put("parentId", tb.getId());
					List<TreeBean> childList = bsResourceService.getTree(pMap);
					tb.setChildren(childList);
			}
		}
		//构建顶层
		TreeBean tb = new TreeBean();
		tb.setId(0);
		tb.setParentId(-1);
		tb.setChildren(treeList);
		Map state = new HashMap();
		state.put("opened", true);
		tb.setState(state);
		tb.setText("全部");
		String returnValue = JSONArray.toJSONString(tb);
		try {
			MessageStreamResult.msgStreamResult(response, returnValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@RequestMapping(value = "/getData")
	@SystemLogBeforeController(description = "生成菜单页面的右侧table资源列表")
	public ModelAndView getData(HttpServletRequest request,
			HttpServletResponse response, Page page, ModelMap model) {
		BsResource bsResource = new BsResource();
		String id = request.getParameter("id");
		if("".equals(id) || !StringUtils.isNumeric(id)){
			bsResource.setId(-1);
		}else{
			bsResource.setId(new Integer(id));
		}
		bsResource.setPagination(page);
		try {
			page = bsResourceService.selectListPage(bsResource, page);
			model.put("page", page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(Constant.PAGE_RES_PATH + "resData",model);
	}
	
	@RequestMapping(value = "/getResByPk")
	@SystemLogBeforeController(description = "根据菜单id查询菜单信息")
	public ModelAndView getResByPk(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String id = request.getParameter("id");
		if(!"".equals(id) && id!=null){
			BsResource bsRes = bsResourceService.getResByPk(new Integer(id));
			String returnValue = JSONArray.toJSONString(bsRes);
			try {
				MessageStreamResult.msgStreamResult(response, returnValue);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	@RequestMapping(value = "/updateResByPk")
	@SystemLogBeforeController(description = "根据菜单的id更新资源信息")
	public ModelAndView updateResByPk(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		Map map = new HashMap();
		map.put("id", new Integer(request.getParameter("id")));
		map.put("menuName", request.getParameter("menuName"));
		map.put("menuImgPath", request.getParameter("menuImgPath"));
		map.put("menuUrl", request.getParameter("menuUrl"));
		map.put("status", request.getParameter("status"));
		bsResourceService.updateResByPk(map);
		return null;
	}

	@RequestMapping(value = "/saveAddRes")
	@SystemLogBeforeController(description = "保存菜单信息")
	public ModelAndView saveAddRes(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		//分装信息并保存
		BsResource bsRes = new BsResource();
		bsRes.setMenuCode(request.getParameter("menuCode"));
		bsRes.setIsLeafNode(new Integer(request.getParameter("isLeafNode")));
		bsRes.setMenuImgPath(request.getParameter("menuImgPath"));
		bsRes.setMenuName(request.getParameter("menuName"));
		bsRes.setMenuUrl(request.getParameter("menuUrl"));
		bsRes.setStatus(new Integer(request.getParameter("status")));
		
		String parentId = request.getParameter("parentId");
		if(!StringUtils.isNumeric(parentId)){
			bsRes.setParentId(0);
			bsRes.setMenuLevel(1);
		}else{
			bsRes.setParentId(new Integer(parentId));
			//根据parentId得到等级
			BsResource res = bsResourceService.getResByPk(new Integer(parentId));
			bsRes.setMenuLevel(res.getMenuLevel()+1);
		}
		bsResourceService.saveRes(bsRes);
		return null;
	}
	
		
}
