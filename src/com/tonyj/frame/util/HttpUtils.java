package com.tonyj.frame.util;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;


public class HttpUtils {
	
	private static Logger logger = Logger.getLogger(HttpUtils.class);
	
	public static String URLPost(String strUrl, Map map) throws IOException {
		
		logger.info("strUrl:"+strUrl);
		
		String content = null;
		if(map!=null && !map.isEmpty()){
			content = MapUtils.createLinkString(map);
		}
		
		URL url = new URL(strUrl);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestProperty("accept", "*/*");
		con.setRequestProperty("connection", "Keep-Alive");
		
		if(!StringUtils.isEmpty(content)){
			logger.info("Post request");
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setAllowUserInteraction(false);
			con.setUseCaches(false);
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded;charset=UTF-8");
			BufferedWriter bout = new BufferedWriter(new OutputStreamWriter(
					con.getOutputStream()));
			bout.write(content);
			bout.flush();
			bout.close();
		}else{
			logger.info("Get request");
			con.setRequestMethod("GET");
			con.connect();
		}
		
		BufferedReader bin = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String lines = "";
		
		StringBuffer sbuf = new StringBuffer();
		
		while ((lines = bin.readLine()) != null) {
			sbuf.append(lines);
		}
		bin.close();
		con.disconnect();
		//System.out.println("sbuf:"+sbuf.toString());
		return sbuf.toString();
	}
	
	public static String post(String strUrl,String content){
		String result = null;
		try {
			result = URLPostContent(strUrl, content);
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		}
		return result;
	}
	
	public static String URLPostContent(String strUrl, String content) throws IOException {
		
		logger.info("strUrl:"+strUrl);
		
		URL url = new URL(strUrl);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestProperty("accept", "*/*");
		con.setRequestProperty("connection", "Keep-Alive");
		
		if(!StringUtils.isEmpty(content)){
			logger.info("Post request");
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setAllowUserInteraction(false);
			con.setUseCaches(false);
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded;charset=UTF-8");
			BufferedWriter bout = new BufferedWriter(new OutputStreamWriter(
					con.getOutputStream()));
			bout.write(content);
			bout.flush();
			bout.close();
		}else{
			logger.info("Get request");
			con.setRequestMethod("GET");
			con.connect();
		}
		
		BufferedReader bin = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String lines = "";
		
		StringBuffer sbuf = new StringBuffer();
		
		while ((lines = bin.readLine()) != null) {
			sbuf.append(lines);
		}
		bin.close();
		con.disconnect();
		//System.out.println("sbuf:"+sbuf.toString());
		return sbuf.toString();
	}
	
	public static void main(String args[]) throws IOException{
		System.out.println(URLPostContent("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxf7eada91d02985a3&secret=c73e56b6f16c69f0f2107fbb144197e5", null));
	}

}
