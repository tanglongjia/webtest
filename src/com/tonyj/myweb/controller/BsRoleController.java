package com.tonyj.myweb.controller;

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

import com.alibaba.fastjson.JSONArray;
import com.tonyj.frame.plugin.Page;
import com.tonyj.frame.util.MessageStreamResult;
import com.tonyj.frame.web.BaseController;
import com.tonyj.myweb.constant.Constant;
import com.tonyj.myweb.po.BsRole;
import com.tonyj.myweb.service.BsRoleService;

@Controller
@RequestMapping("bsRole")
public class BsRoleController extends BaseController {

	@Autowired
	private BsRoleService bsRoleService;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(BsRole role, Page page) {
		return Constant.PAGE_ROLE_PATH + "roleIndex";
	}

	@RequestMapping(value = "/roleData")
	public ModelAndView roleData(HttpServletRequest request,
			HttpServletResponse response,BsRole bsRole, Page page, ModelMap model) {
		try {
			page = bsRoleService.selectListPage(bsRole, page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.put("page", page);
		return new ModelAndView(Constant.PAGE_ROLE_PATH + "roleData", model);
	}
	
	@RequestMapping(value="/saveRole")
	public ModelAndView saveRole(HttpServletRequest request, HttpServletResponse response,ModelMap model){
		BsRole bsRole = new BsRole();
		String rolename = request.getParameter("rolename");
		String roledesc = request.getParameter("roledesc");
		bsRole.setRolename(rolename);
		bsRole.setRoledesc(roledesc);
		bsRole.setStatus(1);
		try {
			bsRoleService.saveRole(bsRole);
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
	
	@RequestMapping(value="/getRoleByPk")
	public ModelAndView getRoleByPk(HttpServletRequest request, HttpServletResponse response,ModelMap model){
		String id = request.getParameter("id");
		try {
			BsRole role = bsRoleService.selectBsRoleByPk(new Integer(id));
			String jsonStr = JSONArray.toJSONString(role);
			MessageStreamResult.msgStreamResult(response, jsonStr);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="/updateRole")
	public ModelAndView updateRole(HttpServletRequest request, HttpServletResponse response,ModelMap model){
		Map map = new HashMap();
		String rolename = request.getParameter("rolename");
		String roledesc = request.getParameter("roledesc");
		String status = request.getParameter("status");
		String id = request.getParameter("id");
		map.put("rolename", rolename);
		map.put("roledesc", roledesc);
		map.put("status", new Integer(status));
		map.put("id", new Integer(id));
		try {
			bsRoleService.updateRole(map);
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
