package com.tonyj.myweb.controller;

import java.util.ArrayList;
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

import com.alibaba.fastjson.JSON;
import com.tonyj.frame.plugin.Page;
import com.tonyj.frame.util.MessageStreamResult;
import com.tonyj.frame.web.BaseController;
import com.tonyj.myweb.constant.Constant;
import com.tonyj.myweb.po.BsDictionary;
import com.tonyj.myweb.service.BsDictionaryService;

@Controller
@RequestMapping("bsDict")
public class BsDictionaryController extends BaseController {

	@Autowired
	private BsDictionaryService bsDictionaryService;
	
	@RequestMapping(value="/index",method = RequestMethod.GET)
	public String dictIndex(BsDictionary dict,Page page){
		return Constant.PAGE_DICT_PATH+"dictIndex";
	}
	
	@RequestMapping(value="/dictData")
	public ModelAndView dictData(HttpServletRequest request, HttpServletResponse response,ModelMap model){
		Map map = new HashMap();
		map.put("dictName", request.getParameter("dictName"));
		List<BsDictionary> dictList = bsDictionaryService.getDictList(map);
		model.put("dataList", dictList);
		return new ModelAndView(Constant.PAGE_DICT_PATH+"dictData",model);
	}
	
	@RequestMapping(value="/showField")
	public ModelAndView showField(HttpServletRequest request, HttpServletResponse response,ModelMap model){
		Map map = new HashMap();
		map.put("dictCode", request.getParameter("dictCode"));
		List<Map> fieldList = bsDictionaryService.getFieldList(map);
		String jsonStr = JSON.toJSONString(fieldList);
    	try {
			MessageStreamResult.msgStreamResult(response, jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="/saveDict")
	public ModelAndView saveDict(HttpServletRequest request, HttpServletResponse response,ModelMap model){
		List<BsDictionary> bsDictList = new ArrayList<BsDictionary>();
		//封装参数
		String addDicName = request.getParameter("addDicName");
		String addDicCode = request.getParameter("addDicCode");
		String addDicNum = request.getParameter("addDicNum");
		String addStatus = request.getParameter("addStatus");
		String dicKeyStr = request.getParameter("addDicKey");
		String dicValueStr = request.getParameter("addDicValue");
		String[] dicKey = dicKeyStr.split(",");
		String[] dicValue = dicValueStr.split(",");
		BsDictionary dict = null;
		int i = 0;
		for (String dic : dicValue) {
			dict = new BsDictionary();
			dict.setDiccode(addDicCode);
			dict.setDicname(addDicName);
			dict.setDicnum(addDicNum);
			dict.setStatus(new Integer(addStatus));
			dict.setDicvalue(dic);
			dict.setDickey(dicKey[i]);
			bsDictList.add(dict);
			i++;
		}
		try {
			bsDictionaryService.batchSaveDict(bsDictList);
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
	
	@RequestMapping(value="/delDict")
	public ModelAndView delDict(HttpServletRequest request, HttpServletResponse response,ModelMap model){
		Map map = new HashMap();
		map.put("dicCode", request.getParameter("dicCode"));
		try {
			bsDictionaryService.delDict(map);
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
	

	@RequestMapping(value="/updateDict")
	public ModelAndView updateDict(HttpServletRequest request, HttpServletResponse response,ModelMap model){
		Map map = new HashMap();
		String name = request.getParameter("name");
		String value= request.getParameter("value");
		String pk = request.getParameter("pk");
		map.put("dicCode", pk);
		if("dicname".equals(name)){
			map.put("dicname", value);
		}
		if("dickey".equals(name)){
			map.put("dickey", value);
		}
		if("dicvalue".equals(name)){
			map.put("dicvalue", value);
		}
		try {
			bsDictionaryService.updateDict(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(Constant.PAGE_DICT_PATH+"dictData",model);
	}
}
