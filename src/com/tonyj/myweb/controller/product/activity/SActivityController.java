package com.tonyj.myweb.controller.product.activity;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
import com.tonyj.frame.plugin.Page;
import com.tonyj.frame.util.MessageStreamResult;
import com.tonyj.frame.web.BaseController;
import com.tonyj.myweb.constant.Constant;
import com.tonyj.myweb.po.BsUser;
import com.tonyj.myweb.po.product.activity.SActivity;
import com.tonyj.myweb.po.product.activity.SUserActivity;
import com.tonyj.myweb.service.product.activity.SActivityService;
import com.tonyj.myweb.service.product.activity.SUserActivityService;

@Controller
@RequestMapping("sActivity")
public class SActivityController extends BaseController {

	@Autowired
	private SActivityService sactivityService;
	
	@Autowired
	private SUserActivityService sUserActivityService;
	
	@RequestMapping(value="/index",method = RequestMethod.GET)
	public ModelAndView activityIndex(HttpServletRequest request, HttpServletResponse response,ModelMap model){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String currDate = sdf.format(new Date());
		model.put("currDate", currDate);
		return new ModelAndView(Constant.PAGE_ACTIVITY_PATH+"activityIndex",model);
	}
	
	/**
	 * @param request
	 * @param response
	 * @param activity
	 * @param page
	 * @param model
	 * @return 查询活动信息
	 */
	@RequestMapping(value="/activityData")
	public ModelAndView activityData(HttpServletRequest request, HttpServletResponse response,SActivity activity,Page page,ModelMap model){
		try {
			page = sactivityService.selectPage(activity,page);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	model.put("page", page);
    	return new ModelAndView(Constant.PAGE_ACTIVITY_PATH+"activityData", model);
	}
	
	
	/**
	 * @param request
	 * @param response
	 * @param activity
	 * @param model
	 * @return 保存活动信息
	 */
	@RequestMapping(value="/saveActivity")
	public ModelAndView saveActivity(HttpServletRequest request, HttpServletResponse response,SActivity activity,ModelMap model){
		try {
			sactivityService.saveActivity(activity);
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
	
	/**
	 * @param request
	 * @param response
	 * @param model
	 * @return 根据主键获取活动信息
	 */
	@RequestMapping(value="/getActivityById")
	public ModelAndView getActivityById(HttpServletRequest request, HttpServletResponse response,ModelMap model){
		String id = request.getParameter("id");
		if(!"".equals(id) && id !=null){
			try {
				SActivity activity = sactivityService.getActivityById(new Integer(id));
				String jsonStr = JSON.toJSONString(activity);
				MessageStreamResult.msgStreamResult(response, jsonStr);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
    	return null;
	}
	
	
	/**
	 * @param request
	 * @param response
	 * @param activity
	 * @param model
	 * @return 更新活动信息 根据id
	 */
	@RequestMapping(value="/updateActivity")
	public ModelAndView updateActivity(HttpServletRequest request, HttpServletResponse response,SActivity activity,ModelMap model){
		try {
			sactivityService.updateActivity(activity);
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
	
	
	@RequestMapping(value="/deleteActivity")
	public ModelAndView deleteActivity(HttpServletRequest request, HttpServletResponse response,ModelMap model){
		String id = request.getParameter("id");
		if(!"".equals(id) && id !=null){
			try {
				sactivityService.deleteActivity(new Integer(id));
				MessageStreamResult.msgStreamResult(response, "0");
			} catch (Exception e) {
				e.printStackTrace();
				try {
					MessageStreamResult.msgStreamResult(response, "1");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
		return null;
	}
	
	
	@RequestMapping(value="/mbInit",method = RequestMethod.GET)
	public ModelAndView mbInit(HttpServletRequest request, HttpServletResponse response,ModelMap model){
		return new ModelAndView(Constant.PAGE_MB_ACTIVITY_PATH+"activityIndex",model);
	}
	
	@RequestMapping(value="/mbActivityData")
	public ModelAndView mbActivityData(HttpServletRequest request, HttpServletResponse response,SActivity activity,Page page,ModelMap model){
		try {
			page = sactivityService.selectPage(activity,page);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	model.put("page", page);
    	return new ModelAndView(Constant.PAGE_MB_ACTIVITY_PATH+"activityData", model);
	}
	
	
	@RequestMapping(value="/mbIsExits")
	public ModelAndView mbIsExits(HttpServletRequest request, HttpServletResponse response,ModelMap model){
		String activityId = request.getParameter("activityId");
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
		Map paramMap = new HashMap();
		paramMap.put("activityId", new Integer(activityId));
		paramMap.put("userId", userId);
		SUserActivity sUserActivity = null;
		try {
			sUserActivity = sUserActivityService.mbIsExits(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(sUserActivity == null){
			try {
				MessageStreamResult.msgStreamResult(response, "0");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			try {
				MessageStreamResult.msgStreamResult(response, "1");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
    	return null;
	}
	
	@RequestMapping(value="/chooseActivity")
	public ModelAndView chooseActivity(HttpServletRequest request, HttpServletResponse response,ModelMap model){
		String activityId = request.getParameter("activityId");
		String activityName = request.getParameter("activityname");
		String activitycontent = request.getParameter("activitycontent");
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
		//封装信息
		SUserActivity suserActivity = new SUserActivity();
		suserActivity.setActivityid(new Integer(activityId));
		suserActivity.setActivityname(activityName);
		suserActivity.setUserid(bsUser.getId());
		suserActivity.setUsername(bsUser.getTrueName());
		suserActivity.setActivitydesc(activitycontent);
		suserActivity.setCompete(0);
		suserActivity.setStatus(1);
		try {
			sUserActivityService.saveSUserActivity(suserActivity);
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
