package nh.ui.automation.tools.common;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import nh.ui.automation.tools.bean.WebDriverServerBase;

public class WebDriverCommon extends WebDriverServerBase {

	/**
	 * 获取元素
	 * 
	 * @param objName
	 * @return
	 */
	public static WebElement getWebElement(final String objName) {
		 WebElement el =  webDriverWait.until(new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver e) {
				return e.findElement(ByType.getBy(objName));
			}
		});
		return el;
	}

	/**
	 * 获取元素
	 * 
	 * @param objName
	 * @return
	 */
	public static List<WebElement> getWebElements(final String objName) {
		List<WebElement> elements = webDriverWait.until(new ExpectedCondition<List<WebElement>>() {
			@Override
			public List<WebElement> apply(WebDriver e) {
				return e.findElements(ByType.getBy(objName));
			}
		});
		return elements;
	}

	/**
	 * 获取元素
	 * 
	 * @param objName
	 * @return
	 */
	public static List<WebElement> getWebElements(final By byElement) {
		List<WebElement> elements = webDriverWait.until(new ExpectedCondition<List<WebElement>>() {
			@Override
			public List<WebElement> apply(WebDriver e) {
				return e.findElements(byElement);
			}
		});
		return elements;
	}

	/**
	 * 获取元素
	 * 
	 * @param objName
	 * @return
	 */
	public static WebElement getWebElement(final By byElement) {
		WebElement element = webDriverWait.until(new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver e) {
				return e.findElement(byElement);
			}
		});
		return element;
	}

	/**
	 * 
	 * @return
	 */
	public static WebElement getClickableWebElement(final String objName) {
		element = getWebElement(objName);
		webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
		return element;
	}

	/**
	 * 
	 * @return
	 */
	public static WebElement getSelectedWebElement(final String objName) {
		element = getWebElement(objName);
		webDriverWait.until(ExpectedConditions.elementToBeSelected(element));
		return element;
	}

	/**
	 * 
	 * @return
	 */
	public static WebElement getVisiableWebElement(final String objName) {
		elements = getWebElements(objName);
		for (WebElement e : elements) {
			if (e.isDisplayed()) {
				element = e;
				break;
			}
		}
		return element;
	}

	/**
	 * 通过获取第一个选项或者判断selected属性，具体要看前端实现的方式
	 * 
	 * @param objName
	 * @return
	 */
	public static String getSelectedOptionsInSelect(final String objName) {
		Select select = new Select(WebDriverCommon.getWebElement(objName));
		element = select.getFirstSelectedOption();
		return element.getText();
	}
	
	/**
	 * 获取下拉列表所有数据
	 * @param objName
	 * @return
	 */
	public static List<String> getOptionsInSelect(final String objName) {
		Select select = new Select(WebDriverCommon.getWebElement(objName));
		elements = select.getOptions();
		List<String> optionsText = new ArrayList<>();
		for (WebElement e : elements) {
			optionsText.add(e.getAttribute("textContent"));
		}
		return optionsText;
	}
}
