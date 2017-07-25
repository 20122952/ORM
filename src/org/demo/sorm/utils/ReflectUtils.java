package org.demo.sorm.utils;

import java.lang.reflect.Method;

/**
 * 封装了反射常用的操作
 * 类名称
 * 类描述
 * @Description TODO
 * @author zhefeng.ning
 * @time 2017年7月9日 下午9:41:56
 */
public class ReflectUtils {

	public static Object invokeGet(String fieldName,Object obj){
		try {
			Class c = obj.getClass();
			Method method = c.getDeclaredMethod("get"+StringUtils.firstChar2UpperCase(fieldName), null);
			return method.invoke(obj, null);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void invokeSet(Object obj,String columnName,Object columnValue){
		try {
			Class c = obj.getClass();
			Method method = c.getDeclaredMethod("set"+StringUtils.firstChar2UpperCase(columnName),
					columnValue.getClass());
			method.invoke(obj, columnValue);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
}
