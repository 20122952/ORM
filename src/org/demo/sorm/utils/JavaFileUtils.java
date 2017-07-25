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
 * ��װ������Java�ļ�(Դ����)���õĲ��� ������ ������
 * 
 * @Description TODO
 * @author zhefeng.ning
 * @time 2017��7��9�� ����8:55:44
 */
public class JavaFileUtils {
	/**
	 * �����ֶ���Ϣ����java������Ϣ���磺varchar username-->private String
	 * username;�Լ���Ӧ��set��get����Դ��
	 * 
	 * @param column
	 *            �ֶ���Ϣ
	 * @param convertor
	 *            ����ת����
	 * @return java���Ժ�set/get����Դ��
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
		// ����set������Դ����
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
		// ����package���
		src.append("package " + DBManager.getConf().getPoPackage() + ";\n\n");
		// ����import���
		src.append("import java.sql.*;\n");
		src.append("import java.util.*;\n\n");
		// �������������
		src.append("public class " + StringUtils.firstChar2UpperCase(tableInfo.getName()) + " {\n\n");

		for (JavaFieldGetSet f : javaFields) {
			src.append(f.getFieldInfo());
		}

		src.append("\n\n");
		for (JavaFieldGetSet f : javaFields) {
			src.append(f.getGetInfo());
		}
		// ����set�����б�
		for (JavaFieldGetSet f : javaFields) {
			src.append(f.getSetInfo());
		}

		// ���������
		src.append("}\n");
		return src.toString();

	}
	
	public static void createJavaPOFile(TableInfo tableInfo,TypeConvertor convertor){
		String src = createJavaSrc(tableInfo,convertor);
		
		String srcPath = DBManager.getConf().getSrcPath()+"\\";
		String packagePath = DBManager.getConf().getPoPackage().replaceAll("\\.", "/");
		
		File f = new File(srcPath+packagePath);
		
		if(!f.exists()){  //���ָ��Ŀ¼�����ڣ�������û�����
			f.mkdirs();
		}
		
		BufferedWriter bw = null;
		
		try {
			bw = new BufferedWriter(new FileWriter(f.getAbsoluteFile()+"/"+StringUtils.firstChar2UpperCase(tableInfo.getName())+".java"));
			bw.write(src);
			System.out.println("������"+tableInfo.getName()+
					"��Ӧ��java�ࣺ"+StringUtils.firstChar2UpperCase(tableInfo.getName())+".java");
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
