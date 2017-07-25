package org.demo.sorm.utils;

import java.lang.reflect.Method;

/**
 * ��װ�˷��䳣�õĲ���
 * ������
 * ������
 * @Description TODO
 * @author zhefeng.ning
 * @time 2017��7��9�� ����9:41:56
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
