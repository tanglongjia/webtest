package com.tonyj.myweb.controller;

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
import com.tonyj.myweb.annotation.SystemLogBeforeController;
import com.tonyj.myweb.constant.Constant;
import com.tonyj.myweb.po.BsUser;
import com.tonyj.myweb.service.BsUserService;


@Controller
@RequestMapping("bsUser")
public class BsUserController extends BaseController {

	@Autowired
	private BsUserService bsUserService;
	
	@RequestMapping(value="/index",method = RequestMethod.GET)
	public String userIndex(BsUser user,Page page){
		return Constant.PAGE_USER_PATH+"userIndex";
	}
    
    @RequestMapping(value="/userData")
    @SystemLogBeforeController(description = "查询用户列表信息")  
    public ModelAndView getUserData(HttpServletRequest request, HttpServletResponse response,BsUser user,Page page,ModelMap model){
		page = bsUserService.selectPage(user,page);
    	model.put("page", page);
    	return new ModelAndView(Constant.PAGE_USER_PATH+"userData", model);
    }
    
    /**
     * @param request
     * @param response
     * @param user
     * @param page
     * @param model
     * @return 保存操作
     */
    @RequestMapping(value="/saveUser")
    @SystemLogBeforeController(description = "保存用户")  
    public ModelAndView saveUser(HttpServletRequest request, HttpServletResponse response,BsUser user,Page page,ModelMap model){
    	try {
			bsUserService.saveUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    /**
     * @param request
     * @param response
     * @param model
     * @return 根据id获取用户信息
     */
    @RequestMapping(value="/getUserById")
    @SystemLogBeforeController(description = "根据用户id查询用户")  
    public ModelAndView getUserById(HttpServletRequest request, HttpServletResponse response,ModelMap model){
    	String id = request.getParameter("id");
    	BsUser bsUser = null;
    	if(!"".equals(id)){
    		try {
				bsUser = bsUserService.getUserById(new Integer(id));
				String jsonStr = JSON.toJSONString(bsUser);
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
     * @param user
     * @param model
     * @return 更新用户信息
     */
    @RequestMapping(value="/updateUser")
    @SystemLogBeforeController(description = "更新用户信息")  
    public ModelAndView updateUser(HttpServletRequest request, HttpServletResponse response,BsUser user,ModelMap model){
    	try {
			bsUserService.updateUser(user);
			MessageStreamResult.msgStreamResult(response,"0");
		} catch (Exception e) {
			e.printStackTrace();
			try {
				MessageStreamResult.msgStreamResult(response,"1");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
    	return null;
    }
    
    /**
     * @param request
     * @param response
     * @param user
     * @param model
     * @return 删除用户 根据id
     */
    @RequestMapping(value="/deleteUser")
    @SystemLogBeforeController(description = "删除用户信息")
    public ModelAndView deleteUser(HttpServletRequest request, HttpServletResponse response,ModelMap model){
    	String id = request.getParameter("id");
    	if(!"".equals(id)){
    		try {
				bsUserService.deleteUser(new Integer(id));
				MessageStreamResult.msgStreamResult(response, "0");
			} catch (NumberFormatException e) {
				e.printStackTrace();
				try {
					MessageStreamResult.msgStreamResult(response, "1");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
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
    
}
