package org.demo.sorm.bean;

/**
 * ��װ����һ���ֶε���Ϣ
 * ������
 * ������
 * @Description TODO
 * @author zhefeng.ning
 * @time 2017��7��9�� ����8:00:35
 */
public class ColumnInfo {

	/**
	 * �ֶ�����
	 */
	private String name;
	
	/**
	 * �ֶε���������
	 */
	private String dateTypoe;
	
	/**
	 * �ֶεļ�����(0����ͨ����1������ 2�����)
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
