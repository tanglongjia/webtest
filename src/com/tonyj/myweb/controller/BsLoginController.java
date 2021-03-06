package com.tonyj.myweb.controller;

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

import com.tonyj.frame.util.MessageStreamResult;
import com.tonyj.frame.web.BaseController;
import com.tonyj.myweb.annotation.SystemLogAfterController;
import com.tonyj.myweb.annotation.SystemLogBeforeController;
import com.tonyj.myweb.po.BsUser;
import com.tonyj.myweb.service.BsUserService;

@Controller
@RequestMapping("/bsLogin")
public class BsLoginController extends BaseController {

	@Autowired
	private BsUserService bsUserService;
	
	@RequestMapping(value="/doLogin",method = RequestMethod.POST)
	@SystemLogAfterController(description = "用户登录系统")  
	public ModelAndView doLogin(HttpServletRequest request, HttpServletResponse response,ModelMap model){
		Map paramMap = new HashMap();
		paramMap.put("loginName", request.getParameter("username"));
		paramMap.put("passWord", request.getParameter("password"));
		List<BsUser> userList = null;
		try {
			userList = bsUserService.getUserByLogin(paramMap);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if(userList ==null || userList.size() == 0){
			try {
				MessageStreamResult.msgStreamResult(response, "0");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			BsUser bsUser = userList.get(0);
			if(bsUser.getStatus() == 0){
				try {
					MessageStreamResult.msgStreamResult(response, "1");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				//将登录信息放到session中
				request.getSession().setAttribute("bsUser", bsUser);
				try {
					MessageStreamResult.msgStreamResult(response, "2");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	@RequestMapping(value="/logout")
	@SystemLogBeforeController(description = "用户注销系统")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response,ModelMap model){
		request.getSession().setAttribute("bsUser", null);
		return new ModelAndView("redirect:/login.jsp");
	}
	
	@RequestMapping(value="/index")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response,ModelMap model){
		return new ModelAndView("index");
	}
	
	@RequestMapping(value="/updatePwd")
	@SystemLogBeforeController(description = "用户更新登录密码")
	public ModelAndView updatePwd(HttpServletRequest request, HttpServletResponse response,ModelMap model){
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
		Map paramMap = new HashMap();
		paramMap.put("newPassWord", request.getParameter("newPassWord"));
		paramMap.put("oldPassWord", request.getParameter("oldPassWord"));
		paramMap.put("id", bsUser.getId());
		try {
			bsUserService.updatePwd(paramMap);
			MessageStreamResult.msgStreamResult(response, "0");
			request.getSession().setAttribute("bsUser", null);
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
