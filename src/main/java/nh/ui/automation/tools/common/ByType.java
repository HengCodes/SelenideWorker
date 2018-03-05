package nh.ui.automation.tools.common;


import org.openqa.selenium.By;
import org.testng.Assert;

import nh.ui.automation.tools.bean.WebDriverServerBase;

public class ByType {
	
	
	public static By getBy(String objName){
		String type = getByType(objName).toUpperCase();
		String value  = getValue(objName);
		System.out.println("hahahahahha " +type +"  ----- "+value);
		switch (type) {
		case "ID":
			return By.id(value);
		case "NAME":
			return By.name(value);
		case "XPATH":
			return By.xpath(value);
		case "CLASS_NAME":
			return By.className(value);
		case "CSS_SELECTOR":
			return By.cssSelector(value);
			
		default:
			return null;
		}
	}
	/**
	 * 获取元素的定位方式的值
	 * @param objName
	 * @return id,name,xpath 等定位方式
	 */
	public static String getValue(String objName){
		try {
			return WebDriverServerBase.objApiData.get(objName).getByvalue();
		} catch (Exception e) {
			Assert.fail("没有找到测试对象,对象名称["+objName+"]!");
			return null;
		}
	}
	
	
	/**
	 * 获取元素的定位方式
	 * @param objName
	 * @return id,name,xpath 等定位方式
	 */
	public static String getByType(String objName){
		try {
			return WebDriverServerBase.objApiData.get(objName).getBytype();
		} catch (Exception e) {
			Assert.fail("没有找到测试对象,对象名称["+objName+"]!");
			return null;
		}
	}
	/**
	 * 获取元素的预期结果
	 * @param objName
	 * @return
	 */
	public static String getExpectValue(String objName){
		return WebDriverServerBase.objApiData.get(objName).getExpectvalue();
	}
	
	/**
	 * 获取元素基本信息
	 * @param objName
	 * @return
	 */
	public static String getObjInfo(String objName){
		return WebDriverServerBase.objApiData.get(objName).getBytype()+"=["+WebDriverServerBase.objApiData.get(objName).getByvalue()+"],测试对象：["+objName+"]";
	}
}
