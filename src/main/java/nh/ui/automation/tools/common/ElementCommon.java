package nh.ui.automation.tools.common;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import nh.ui.automation.tools.bean.WebDriverServerBase;


public class ElementCommon extends WebDriverServerBase {
	/**
	 * 
	 * @param element
	 * @param id
	 */
	public static void assignIdToElement(WebElement element,String id){
		System.out.println(String.format("Excute js on browser assign %s id to %s", id,element));
		((JavascriptExecutor) driver).executeScript("arguments[0].id='"+id+"';", element);
	}
	
	/**
	 * 
	 * @param objName
	 * @param property
	 * @return
	 */
	public static String getAttribute(String objName,String property){
		System.out.println("获取元素属性 ："+Action.objInfo(objName));
		return WebDriverCommon.getWebElement(objName).getAttribute(property);
	}
	
	
	public static WebElement getElement(String objName){
		System.out.println("获取元素属性 ："+Action.objInfo(objName));
		return WebDriverCommon.getWebElement(objName);
	}
	
	public static List<WebElement> getElements(String objName){
		System.out.println("获取元素属性 ："+Action.objInfo(objName));
		return WebDriverCommon.getWebElements(objName);
	}
	
	
	public static String getElementValueByJs(WebElement element){
		String id  = "id"+new Random().nextInt(1000);
		assignIdToElement(element, id);
		return Action.excuteJsReuslt("return document.getElmentById('"+id+"').value");
	}
	
}
