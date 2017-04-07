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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.tonyj.frame.plugin.Page;
import com.tonyj.frame.util.MessageStreamResult;
import com.tonyj.frame.web.BaseController;
import com.tonyj.myweb.constant.Constant;
import com.tonyj.myweb.po.BsUser;
import com.tonyj.myweb.service.BsUserService;


@Controller
@RequestMapping("bsUser")
public class BsUserController extends BaseController {

	@Autowired
	private BsUserService BsUserService;
	
	@RequestMapping(value="/showUser",method = RequestMethod.GET)
	public String userIndex(BsUser user,Page page){
		return Constant.PAGE_USER_PATH+"userIndex";
	}
	
    @RequestMapping(value="/userList",method = RequestMethod.GET)
    public ModelAndView getAllUser(HttpServletRequest request, HttpServletResponse response,BsUser user,Page page){
    	page = BsUserService.selectPage(user,page);
    	Map<String,String> dictMap = new HashMap<String,String>();
    	dictMap.put("0", "女");
    	dictMap.put("1", "男");
    	page.setDictMap(dictMap);
    	String jsonStr = JSON.toJSONString(page.getResultList());
    	try {
			MessageStreamResult.msgStreamResult(response, jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    @RequestMapping(value="/userData")
    public ModelAndView getUserData(HttpServletRequest request, HttpServletResponse response,BsUser user,Page page,ModelMap model){
    	page = BsUserService.selectPage(user,page);
    	Map<String,String> dictMap = new HashMap<String,String>();
    	dictMap.put("0", "女");
    	dictMap.put("1", "男");
    	page.setDictMap(dictMap);
    	model.put("page", page);
    	return new ModelAndView(Constant.PAGE_USER_PATH+"userData", model);
    }
    
    @RequestMapping("/userAdd")
    @ResponseBody
    public String userAdd(BsUser user){
    	user.setLoginName("test");
    	user.setPassWord("test");
    	BsUserService.addUser(user);
    	return "";
    }
    
}
