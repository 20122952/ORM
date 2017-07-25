package org.demo.sorm.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.demo.sorm.bean.ColumnInfo;
import org.demo.sorm.bean.JavaFieldGetSet;
import org.demo.sorm.bean.TableInfo;
import org.demo.sorm.core.DBManager;
import org.demo.sorm.core.MySqlTypeConvertor;
import org.demo.sorm.core.TableContext;
import org.demo.sorm.core.TypeConvertor;


/**
 * 封装了生成Java文件(源代码)常用的操作 类名称 类描述
 * 
 * @Description TODO
 * @author zhefeng.ning
 * @time 2017年7月9日 下午8:55:44
 */
public class JavaFileUtils {
	/**
	 * 根据字段信息生成java属性信息。如：varchar username-->private String
	 * username;以及相应的set和get方法源码
	 * 
	 * @param column
	 *            字段信息
	 * @param convertor
	 *            类型转化器
	 * @return java属性和set/get方法源码
	 */
	public static JavaFieldGetSet createFieldGetSetRC(ColumnInfo column, TypeConvertor convertor) {
		JavaFieldGetSet jfgs = new JavaFieldGetSet();
		String JavaFieldType = convertor.databaseType2JavaType(column.getDateTypoe());
		jfgs.setFieldInfo("\tprivate " + JavaFieldType + " " + column.getName() + ";\n");

		StringBuilder getSrc = new StringBuilder();
		getSrc.append(
				"\tpublic " + JavaFieldType + " get" + StringUtils.firstChar2UpperCase(column.getName()) + "(){\n");
		getSrc.append("\t\treturn " + column.getName() + ";\n");
		getSrc.append("\t}\n");
		jfgs.setGetInfo(getSrc.toString());
		// public void setUsername(String username){this.username=username;}
		// 生成set方法的源代码
		StringBuilder setSrc = new StringBuilder();
		setSrc.append("\tpublic void set" + StringUtils.firstChar2UpperCase(column.getName()) + "(");
		setSrc.append(JavaFieldType + " " + column.getName() + "){\n");
		setSrc.append("\t\tthis." + column.getName() + "=" + column.getName() + ";\n");
		setSrc.append("\t}\n");
		jfgs.setSetInfo(setSrc.toString());
		return jfgs;
	}

	public static String createJavaSrc(TableInfo tableInfo, TypeConvertor convertor) {
		Map<String, ColumnInfo> columns = tableInfo.getColumns();
		List<JavaFieldGetSet> javaFields = new ArrayList<JavaFieldGetSet>();
		for (ColumnInfo column : columns.values()) {
			javaFields.add(createFieldGetSetRC(column, convertor));
		}
		StringBuilder src = new StringBuilder();
		// 生成package语句
		src.append("package " + DBManager.getConf().getPoPackage() + ";\n\n");
		// 生成import语句
		src.append("import java.sql.*;\n");
		src.append("import java.util.*;\n\n");
		// 生成类声明语句
		src.append("public class " + StringUtils.firstChar2UpperCase(tableInfo.getName()) + " {\n\n");

		for (JavaFieldGetSet f : javaFields) {
			src.append(f.getFieldInfo());
		}

		src.append("\n\n");
		for (JavaFieldGetSet f : javaFields) {
			src.append(f.getGetInfo());
		}
		// 生成set方法列表
		for (JavaFieldGetSet f : javaFields) {
			src.append(f.getSetInfo());
		}

		// 生成类结束
		src.append("}\n");
		return src.toString();

	}
	
	public static void createJavaPOFile(TableInfo tableInfo,TypeConvertor convertor){
		String src = createJavaSrc(tableInfo,convertor);
		
		String srcPath = DBManager.getConf().getSrcPath()+"\\";
		String packagePath = DBManager.getConf().getPoPackage().replaceAll("\\.", "/");
		
		File f = new File(srcPath+packagePath);
		
		if(!f.exists()){  //如果指定目录不存在，则帮助用户建立
			f.mkdirs();
		}
		
		BufferedWriter bw = null;
		
		try {
			bw = new BufferedWriter(new FileWriter(f.getAbsoluteFile()+"/"+StringUtils.firstChar2UpperCase(tableInfo.getName())+".java"));
			bw.write(src);
			System.out.println("建立表"+tableInfo.getName()+
					"对应的java类："+StringUtils.firstChar2UpperCase(tableInfo.getName())+".java");
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(bw!=null){
					bw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	public static void main(String[] args) {
		Map<String, TableInfo> tables = TableContext.tables;
		for(TableInfo t:tables.values()){
			createJavaPOFile(t, new MySqlTypeConvertor());
		}
	}
	
}
