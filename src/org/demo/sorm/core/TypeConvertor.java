package org.demo.sorm.core;

/**
 * ����java�������ͺ����ݿ��������͵Ļ���ת��
 * ������
 * ������
 * @Description TODO
 * @author zhefeng.ning
 * @time 2017��7��9�� ����8:11:52
 */
public interface TypeConvertor {
	/**
	 * �����ݿ���������ת����java����������
	 * @param columnType ���ݿ��ֶε���������
	 * @return java����������
	 */
	public String databaseType2JavaType(String columnType);
	/**
	 * ��java��������ת�������ݿ���������
	 * @param javaDataType java��������
	 * @return ���ݿ�����
	 */
	public String javaType2DatabaseType(String javaDataType);

}
