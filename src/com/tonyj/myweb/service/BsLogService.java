package com.tonyj.myweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tonyj.frame.plugin.Page;
import com.tonyj.myweb.annotation.SystemLogService;
import com.tonyj.myweb.dao.BsLogDao;
import com.tonyj.myweb.po.BsLog;

@Service
public class BsLogService {

	@Autowired
	private BsLogDao bsLogDao;
	
	@SystemLogService(description = "分页查询日志信息")
	public Page selectPage(BsLog bsLog,Page page){
		return bsLogDao.selectPage(bsLog, page);
	}
	@SystemLogService(description = "保存日志信息")
	public void saveLog(BsLog bsLog){
		bsLogDao.saveLog(bsLog);
	}
}
