package org.demo.sorm.utils;

/**
 * 封装了字符串常用的操作
 * 类名称
 * 类描述
 * @Description TODO
 * @author zhefeng.ning
 * @time 2017年7月9日 下午9:03:45
 */
public class StringUtils {

	public static String firstChar2UpperCase(String str){
		return str.toUpperCase().substring(0, 1)+str.substring(1);
	}
}
