package com.tonyj.frame.util;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;


public class MapUtils {
	
	/**
	 * 对象转换为map
	 */
	public static HashMap<String, Object> objectToMap(Object obj){
		if(obj==null){
			return null;
		}
		HashMap<String, Object> map = new HashMap<String, Object>(); 
		Field[] fields = obj.getClass().getDeclaredFields();  
		for(int i=0;i<fields.length;i++){
			String varName=fields[i].getName();
			try {  
                // 获取原来的访问控制权限  
                boolean accessFlag = fields[i].isAccessible();  
                // 修改访问控制权限  
                fields[i].setAccessible(true);  
                // 获取在对象f中属性fields[i]对应的对象中的变量  
                Object o = fields[i].get(obj);  
                if (o != null)  
                    map.put(varName, o);  
                // System.out.println("传入的对象中包含一个如下的变量：" + varName + " = " + o);  
                // 恢复访问控制权限  
                fields[i].setAccessible(accessFlag);  
            } catch (IllegalArgumentException ex) {  
                ex.printStackTrace();  
            } catch (IllegalAccessException ex) {  
                ex.printStackTrace();  
            }
		}
		
		return map;
	}
	
	/**
	 * 过滤value为null或者""的单元
	 * @param param
	 * @return
	 */
	public static Map<String,String> filterMap(Map<String,String> param){
		if(param==null || param.isEmpty()){
			return null;
		}
		
		Map<String,String> result = new HashMap<String, String>();
		
		Set<Map.Entry<String, String>> set =  param.entrySet();
		Iterator<Map.Entry<String, String>> iter = set.iterator();
		while(iter.hasNext()){
			Map.Entry<String, String> entry = iter.next();
			if(!StringUtils.isEmpty(entry.getValue())){
				result.put(entry.getKey(), entry.getValue());
			}
		}
		return result;
	} 
	
	/** 
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     * @param param 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
	public static String createLinkString(Map<String,String> param){
		if(param==null || param.isEmpty()){
			return null;
		}
		
		List<String> keys = new ArrayList<String>(param.keySet());
        Collections.sort(keys);
        
        StringBuffer sbuf = new StringBuffer();
		for(String index:keys){
			sbuf.append(index).append("=").append(param.get(index)).append("&");
		}
		
		String str = sbuf.substring(0,sbuf.length()-1);
        
		return str;
	}
	
	/** 
     * 把数组所有元素排序，并按照name1value1name2value2拼接成字符串
     * @param param 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
	public static String createSignString(Map<String,String> param){
		if(param==null || param.isEmpty()){
			return null;
		}
		
		List<String> keys = new ArrayList<String>(param.keySet());
        Collections.sort(keys);
        
        StringBuffer sbuf = new StringBuffer();
		for(String index:keys){
			sbuf.append(index).append(param.get(index));
		}
		
		return sbuf.toString();
	}
	
	/** 
     * 把数组所有元素排序，并按照json的格式拼接成字符串：{"key":"value","key":"value"}
     * @param param 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
	public static String createJsonString(Map<String,String> param){
		if(param==null || param.isEmpty()){
			return null;
		}
		
		List<String> keys = new ArrayList<String>(param.keySet());
        Collections.sort(keys);
        
        StringBuffer sbuf = new StringBuffer();
		for(String index:keys){
			sbuf.append("\"").append(index).append("\"").append(":").append("\"").append(param.get(index)).append("\"").append(",");
		}
		
		String str ="{" + sbuf.substring(0,sbuf.length()-1) + "}";
        
		return str;
	}
	/**
	 * 将普通map转换为有排序的TreeMap
	 * @param param
	 * @return
	 */
	public static TreeMap<String,String> mapToTreeMap(Map<String,String> param){
		TreeMap<String,String> result = new TreeMap<String,String>();
		
		Set<Map.Entry<String, String>> set =  param.entrySet();
		Iterator<Map.Entry<String, String>> iter = set.iterator();
		while(iter.hasNext()){
			Map.Entry<String, String> entry = iter.next();
			result.put(entry.getKey(), entry.getValue());
		}
		
		return result;
	}

}
