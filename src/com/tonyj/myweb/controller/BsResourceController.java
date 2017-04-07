package com.tonyj.myweb.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.tonyj.frame.plugin.Page;
import com.tonyj.frame.util.MessageStreamResult;
import com.tonyj.frame.web.BaseController;
import com.tonyj.myweb.constant.Constant;
import com.tonyj.myweb.constant.TreeBean;
import com.tonyj.myweb.po.BsResource;
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
    public String getLeftMenu(){
		Map<String,Integer> paramMap= new HashMap<String,Integer>();
		paramMap.put("parentId", 0);
		List<BsResource> bsResouceList = bsResourceService.getLeftMenu(paramMap);
		if(!bsResouceList.isEmpty()){
			for (BsResource bsResource : bsResouceList) {
					Map<String,Integer> pMap = new HashMap<String,Integer>();
					pMap.put("parentId", bsResource.getId());
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
		/*//构建顶层
		TreeBean tb = new TreeBean();
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
	
	
	@RequestMapping(value = "/getData")
	public ModelAndView getData(HttpServletRequest request,
			HttpServletResponse response,BsResource bsResource, Page page, ModelMap model) {
		try {
			page = bsResourceService.selectListPage(bsResource, page);
			model.put("page", page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(Constant.PAGE_RES_PATH + "resData",model);
	}
		
}
