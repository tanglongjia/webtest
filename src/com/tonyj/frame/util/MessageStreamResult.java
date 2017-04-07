package com.tonyj.frame.util;

import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

public class MessageStreamResult {

	public static void msgStreamResult( HttpServletResponse response,String returnValue) throws Exception{
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setHeader("Content-Type", "text/html;charset=utf-8");
		OutputStream os = response.getOutputStream();
		if (returnValue == null) returnValue = "";
		os.write(returnValue.getBytes("utf-8"));
		os.flush();
		os.close();
	}
}
