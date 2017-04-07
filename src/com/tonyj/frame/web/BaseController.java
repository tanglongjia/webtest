package com.tonyj.frame.web;

import org.springframework.stereotype.Controller;

import com.tonyj.frame.plugin.Page;

@Controller
public class BaseController {
	/**
	 * 分页组件
	 */
	protected Page page;

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
}
