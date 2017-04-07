package com.tonyj.frame.util;


import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;


public class DataUtils {
	
	private static Logger logger = Logger.getLogger(DataUtils.class);
	
	private static final String HTTP_ENCODING = "UTF-8";
	private static final String HTTP_CONTENT_TYPE_OC_STREAM = "application/json";

	/**
	 * 得到请求报文
	 * 
	 * @param request
	 * @return String
	 */
	public static String getInputData(HttpServletRequest request) {
		try {
			if (request == null || 0 == request.getContentLength()
					|| request.getContentLength() < 0) {
				return null;
			}
			ServletInputStream inputStream = request.getInputStream();
			byte[] bytes = new byte[request.getContentLength()];
			inputStream.read(bytes);
			
			String result = new String(bytes,Charset.forName(HTTP_ENCODING));
			logger.info("DataUtils result:"+result);
			return result;
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}

	}
	
	/**
	 * 返回报文
	 * @param content
	 * @param response
	 * @param isFinal
	 */
	public static void write(String content, HttpServletResponse response,
			boolean isFinal) {
		logger.info("DataUtils content:"+content);
		setTextHeader(response);
		OutputStream out = null;
		try {
			response.setContentLength(content.getBytes(HTTP_ENCODING).length);
			out = response.getOutputStream();
			out.write(content.getBytes(HTTP_ENCODING));
			out.flush();
		} catch (IOException e) {
			logger.error(e.getMessage());
		} finally {
			if (isFinal) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}
	}

	/**
	 * 设置响应头为 application/octet-stream 格式
	 * 
	 * @param response
	 */
	private static void setTextHeader(HttpServletResponse response) {
		response.setContentType(HTTP_CONTENT_TYPE_OC_STREAM);
		response.setCharacterEncoding(HTTP_ENCODING);
	}
}
