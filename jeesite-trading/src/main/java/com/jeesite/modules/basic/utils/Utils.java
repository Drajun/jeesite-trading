package com.jeesite.modules.basic.utils;

import java.lang.reflect.Field;

/**
 * Utils
 * 常用工具类
 * 创建人： longlou.d@foxmail.com
 * 创建日期：2019年3月19日
 * 修改人：
 * 修改日期
 * 版本：1.0
 * 版权所有：Copyright 2017 by KinnSoft
 */
public class Utils {
	
	public static String getJsonFromObject(Object obj){
		StringBuilder jsonObject = new StringBuilder();
		jsonObject.append("{");
		Field[] fields = obj.getClass().getDeclaredFields();
		for(Field f : fields){
			f.setAccessible(true);
			jsonObject.append(f.getName());
			jsonObject.append(":");
			try {
				jsonObject.append(f.get(obj));
			} catch (Exception e) {
				jsonObject.append("null");
			}
			jsonObject.append(",");
		}
		jsonObject.deleteCharAt(jsonObject.lastIndexOf(","));
		jsonObject.append("}");
		return jsonObject.toString();
	}
	
}
