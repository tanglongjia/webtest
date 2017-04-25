package com.tonyj.myweb.controller.product.activity;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tonyj.frame.web.BaseController;
import com.tonyj.myweb.po.BsUser;
import com.tonyj.myweb.service.BsUserService;

@Controller
@RequestMapping("/mbInfo")
public class SMemberController extends BaseController {

	@Autowired
	private BsUserService bsUserService;
	
	@RequestMapping(value="/init",method = RequestMethod.GET)
	public ModelAndView activityIndex(HttpServletRequest request, HttpServletResponse response,ModelMap model){
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
		model.put("bsUser", bsUser);
		return new ModelAndView("pages/product_service/mb_info/mbInfo",model);
	}
}
