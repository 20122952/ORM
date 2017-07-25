package org.demo.sorm.bean;

import java.util.List;
import java.util.Map;

/**
 * 存储表结构的信息 类名称 类描述
 * 
 * @Description TODO
 * @author zhefeng.ning
 * @time 2017年7月9日 下午8:03:25
 */
public class TableInfo {

	/**
	 * 表名
	 */
	private String name;
	/**
	 * 所有字段的信息
	 */
	private Map<String, ColumnInfo> columns;
	/**
	 * 唯一主键(目前我们只能处理表中有且只有一个主键的情况)
	 */
	private ColumnInfo onlyPriKey;

	/**
	 * 如果联合主键，则在这里存储
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
