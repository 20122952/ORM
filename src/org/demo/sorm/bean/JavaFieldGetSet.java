package org.demo.sorm.bean;

/**
 * ��װ��java���Ժ�get��set������Դ���� ������ ������
 * 
 * @Description TODO
 * @author zhefeng.ning
 * @time 2017��7��9�� ����8:08:47
 */
public class JavaFieldGetSet {
	private String fieldInfo;
	private String getInfo;
	private String setInfo;

	public String getFieldInfo() {
		return fieldInfo;
	}

	public void setFieldInfo(String fieldInfo) {
		this.fieldInfo = fieldInfo;
	}

	public String getGetInfo() {
		return getInfo;
	}

	public void setGetInfo(String getInfo) {
		this.getInfo = getInfo;
	}

	public String getSetInfo() {
		return setInfo;
	}

	public void setSetInfo(String setInfo) {
		this.setInfo = setInfo;
	}

	@Override
	public String toString() {
		System.out.println(fieldInfo);
		System.out.println(getInfo);
		System.out.println(setInfo);
		return super.toString();
	}

	public JavaFieldGetSet(String fieldInfo, String getInfo, String setInfo) {
		super();
		this.fieldInfo = fieldInfo;
		this.getInfo = getInfo;
		this.setInfo = setInfo;
	}

	public JavaFieldGetSet() {

	}

}
