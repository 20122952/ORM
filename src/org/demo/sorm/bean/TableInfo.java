package org.demo.sorm.bean;

import java.util.List;
import java.util.Map;

/**
 * �洢��ṹ����Ϣ ������ ������
 * 
 * @Description TODO
 * @author zhefeng.ning
 * @time 2017��7��9�� ����8:03:25
 */
public class TableInfo {

	/**
	 * ����
	 */
	private String name;
	/**
	 * �����ֶε���Ϣ
	 */
	private Map<String, ColumnInfo> columns;
	/**
	 * Ψһ����(Ŀǰ����ֻ�ܴ����������ֻ��һ�����������)
	 */
	private ColumnInfo onlyPriKey;

	/**
	 * ���������������������洢
	 */
	private List<ColumnInfo> prikeys;

	public List<ColumnInfo> getPrikeys() {
		return prikeys;
	}

	public void setPrikeys(List<ColumnInfo> prikeys) {
		this.prikeys = prikeys;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, ColumnInfo> getColumns() {
		return columns;
	}

	public void setColumns(Map<String, ColumnInfo> columns) {
		this.columns = columns;
	}

	public ColumnInfo getOnlyPriKey() {
		return onlyPriKey;
	}

	public void setOnlyPriKey(ColumnInfo onlyPriKey) {
		this.onlyPriKey = onlyPriKey;
	}

	public TableInfo() {
	
	}

	public TableInfo(String name, Map<String, ColumnInfo> columns, ColumnInfo onlyPriKey) {
		super();
		this.name = name;
		this.columns = columns;
		this.onlyPriKey = onlyPriKey;
	}

	public TableInfo(String name, List<ColumnInfo> prikeys,Map<String, ColumnInfo> columns) {
		super();
		this.name = name;
		this.columns = columns;
		this.prikeys = prikeys;
	}
	
	

}
