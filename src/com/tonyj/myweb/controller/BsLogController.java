package com.tonyj.myweb.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tonyj.frame.plugin.Page;
import com.tonyj.frame.web.BaseController;
import com.tonyj.myweb.annotation.SystemLogBeforeController;
import com.tonyj.myweb.constant.Constant;
import com.tonyj.myweb.po.BsLog;
import com.tonyj.myweb.service.BsLogService;

/**
 * @author TonyJ
 *
 */
@Controller
@RequestMapping("/bsLog")
public class BsLogController extends BaseController {

	@Autowired
	private BsLogService bsLogService;
	
	@RequestMapping(value="/index",method = RequestMethod.GET)
	@SystemLogBeforeController(description = "初始化日志页面") 
	public ModelAndView logIndex(HttpServletRequest request, HttpServletResponse response,ModelMap model){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(new Date());
		model.put("defaultDate", date);
		return new ModelAndView(Constant.PAGE_LOG_PATH+"logIndex",model);
	}
	
	@RequestMapping(value="/logData")
    @SystemLogBeforeController(description = "分页查询日志信息")  
    public ModelAndView logData(HttpServletRequest request, HttpServletResponse response,BsLog bsLog,Page page,ModelMap model){
		page = bsLogService.selectPage(bsLog,page);
    	model.put("page", page);
    	if(bsLog.getLogType() == 0){
    		return new ModelAndView(Constant.PAGE_LOG_PATH+"operLogData", model);
    	}else{
    		return new ModelAndView(Constant.PAGE_LOG_PATH+"execpLogData", model);
    	}
    }
}
