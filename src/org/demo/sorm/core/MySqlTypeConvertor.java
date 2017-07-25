package org.demo.sorm.core;

/**
 * mysql数据类型和java数据类型的转换
 * 类名称
 * 类描述
 * @Description TODO
 * @author zhefeng.ning
 * @time 2017年7月9日 下午8:21:05
 */
public class MySqlTypeConvertor implements TypeConvertor{

	@Override
	public String databaseType2JavaType(String columnType) {
		if("varchar".equalsIgnoreCase(columnType)||
				"char".equalsIgnoreCase(columnType)){
			return "String";
		}else if("int".equalsIgnoreCase(columnType)||
				"tinyint".equalsIgnoreCase(columnType)||
				"smallint".equalsIgnoreCase(columnType)||
				"integer".equalsIgnoreCase(columnType)){
			return "Integer";
		}else if("bigint".equalsIgnoreCase(columnType)){
			return "Long";
		}else if("double".equalsIgnoreCase(columnType)||
				"float".equalsIgnoreCase(columnType)){
			return "Double";
		}else if("clob".equalsIgnoreCase(columnType)){
			return "java.sql.Clob";
		}else if("blob".equalsIgnoreCase(columnType)){
			return "java.sql.Blob";
		}else if("date".equalsIgnoreCase(columnType)){
			return "java.sql.Date";
		}else if("time".equalsIgnoreCase(columnType)){
			return "java.sql.Time";
		}else if("timestamp".equalsIgnoreCase(columnType)){
			return "java.sql.Timestamp";
		}
		return null;
	}

	@Override
	public String javaType2DatabaseType(String javaDataType) {
		return null;
	}

}
