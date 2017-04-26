package com.tonyj.myweb.controller.product.activity;

import java.io.IOException;
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

import com.tonyj.frame.plugin.Page;
import com.tonyj.frame.util.MessageStreamResult;
import com.tonyj.frame.web.BaseController;
import com.tonyj.myweb.constant.Constant;
import com.tonyj.myweb.po.BsUser;
import com.tonyj.myweb.po.product.activity.SUserActivity;
import com.tonyj.myweb.service.product.activity.SUserActivityService;

@Controller
@RequestMapping("sUserActivity")
public class SUserActivityController extends BaseController {

	@Autowired
	private SUserActivityService sUserActivityService;
	

	@RequestMapping(value="/index",method = RequestMethod.GET)
	public ModelAndView activityIndex(HttpServletRequest request, HttpServletResponse response,ModelMap model){
		return new ModelAndView(Constant.PAGE_USER_ACTIVITY_PATH+"userActivityIndex",model);
	}
	
	@RequestMapping(value="/userActivityData")
	public ModelAndView userActivityData(HttpServletRequest request, HttpServletResponse response,SUserActivity activity,Page page,ModelMap model){
		try {
			page = sUserActivityService.selectPage(activity,page);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	model.put("page", page);
		return new ModelAndView(Constant.PAGE_USER_ACTIVITY_PATH+"userActivityData",model);
	}
	
	@RequestMapping(value="/mbInit",method = RequestMethod.GET)
	public ModelAndView mbInit(HttpServletRequest request, HttpServletResponse response,ModelMap model){
		return new ModelAndView(Constant.PAGE_MB_USER_ACTIVITY_PATH+"userActivityIndex",model);
	}
	
	@RequestMapping(value="/mbUserActivityData")
	public ModelAndView mbUserActivityData(HttpServletRequest request, HttpServletResponse response,SUserActivity activity,ModelMap model){
		List<SUserActivity> activityList = null;
		try {
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
			int userId = bsUser.getId();
			activity.setUserid(userId);
			activityList = sUserActivityService.selectAll(activity);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	model.put("activityList", activityList);
		return new ModelAndView(Constant.PAGE_MB_USER_ACTIVITY_PATH+"userActivityData",model);
	}
	
	@RequestMapping(value="/updateComete")
	public ModelAndView updateComete(HttpServletRequest request, HttpServletResponse response,ModelMap model){
		String compete = request.getParameter("value");
		String id = request.getParameter("pk");
		Map paramMap = new HashMap();
		paramMap.put("id", id);
		paramMap.put("compete", compete);
		try {
			sUserActivityService.updateCompete(paramMap);
			MessageStreamResult.msgStreamResult(response, "0");
		} catch (Exception e) {
			e.printStackTrace();
			try {
				MessageStreamResult.msgStreamResult(response, "1");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}
}
