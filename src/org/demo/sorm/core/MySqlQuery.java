package org.demo.sorm.core;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import org.demo.po.Emp;
import org.demo.sorm.bean.TableInfo;
import org.demo.sorm.utils.JDBCUtils;
import org.demo.sorm.utils.ReflectUtils;




public class MySqlQuery implements Query{

	public static void main(String[] args) {
		Emp e = new Emp();
		e.setEmpname("lily");
		e.setBirthday(new java.sql.Date(System.currentTimeMillis()));
		e.setAge(30);
		e.setSalary(3000.8);
		e.setId(1);
//		new MySqlQuery().delete(e);
		new MySqlQuery().insert(e);
//		new MySqlQuery().update(e,new String[]{"empname","age","salary"});
	}
	
	
	@Override
	public int executeDML(String sql, Object[] params) {
		Connection conn = DBManager.getConn();
		int count = 0; 
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			//¸øsqlÉè²Î
			JDBCUtils.handleParams(ps, params);
			System.out.println(ps);
			count  = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBManager.close(ps, conn);
		}
		
		return count;
	}

	@Override
	public void insert(Object obj) {
		Class c = obj.getClass();
		List<Object> params = new ArrayList<Object>();
		TableInfo tableInfo = TableContext.poClassTableMap.get(c);
		StringBuilder sql = new StringBuilder("insert into "+tableInfo.getName()+" (");
		int countNotNullField = 0;
		Field[] fs = c.getDeclaredFields();
		for(Field f:fs){
			String fieldName = f.getName();
			Object fieldValue = ReflectUtils.invokeGet(fieldName, obj);
			if(fieldValue!= null){
				countNotNullField++;
				sql.append(fieldName+",");
				params.add(fieldValue);
			}
		}
		sql.setCharAt(sql.length()-1, ')');
		sql.append(" values (");
		for(int i=0;i<countNotNullField;i++){
			sql.append("?,");
		}
		sql.setCharAt(sql.length()-1, ')');
		
		executeDML(sql.toString(), params.toArray());
		
	}

	@Override
	public void delete(Class clazz, Object id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Object obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int update(Object obj, String[] fieldNames) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List queryRows(String sql, Class clazz, Object[] params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object queryUniqueRow(String sql, Class clazz, Object[] params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object queryValue(String sql, Object[] params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Number queryNumber(String sql, Object[] params) {
		// TODO Auto-generated method stub
		return null;
	}

}
