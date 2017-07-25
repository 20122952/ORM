package org.demo.sorm.bean;

/**
 * 封装表中一个字段的信息
 * 类名称
 * 类描述
 * @Description TODO
 * @author zhefeng.ning
 * @time 2017年7月9日 下午8:00:35
 */
public class ColumnInfo {

	/**
	 * 字段名称
	 */
	private String name;
	
	/**
	 * 字段的数据类型
	 */
	private String dateTypoe;
	
	/**
	 * 字段的键类型(0：普通键，1：主键 2：外键)
	 */
	private int keyType;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDateTypoe() {
		return dateTypoe;
	}

	public void setDateTypoe(String dateTypoe) {
		this.dateTypoe = dateTypoe;
	}

	public int getKeyType() {
		return keyType;
	}

	public void setKeyType(int keyType) {
		this.keyType = keyType;
	}

	public ColumnInfo() {
		
	}

	public ColumnInfo(String name, String dateTypoe, int keyType) {
		super();
		this.name = name;
		this.dateTypoe = dateTypoe;
		this.keyType = keyType;
	}
	
	
}
