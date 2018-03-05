package nh.ui.automation.tools.common;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.Assert;
import org.testng.Reporter;

import nh.ui.automation.tools.bean.WebDriverServerBase;

/**
 * 项目 ：UI自动化测试 Selenide 类描述：
 * 
 * @author Eric
 * @date 2017年2月9日 nh.ui.automation.tools.common
 */
public class Action extends WebDriverServerBase {
	public static void openUrl(String url) {
		System.out.println("打开测试页面 :" + url);
		open(url);
	}

	public static void sleepTime(long time) {
		System.out.println("等待时间 :" + time);
		sleep(time);
	}

	/**
	 * highlight element,and click it
	 * 
	 * @param pageElement
	 */
	public static void click(WebElement element) {
		try {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("arguments[0].setAttribute('style',arguments[1]);", element);
		} catch (Exception e) {
			System.out.println("js error");
		} finally {
			element.click();
		}
	}

	/**
	 * move to element with point,and click it
	 * 
	 * @param pageElement
	 */
	public static void click(WebElement element, int x, int y) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element, x, y).click().build().perform();
	}

	public static void click(final String objName) {
		try {
			click(objName, 0);
		} catch (Exception e) {
			System.out.println("不可电机的对象");
		}
	}

	public static void click(final String objName, int waitTime) {
		try {
			System.out.println(objInfo(objName) + ";等待时间 ：" + waitTime + "秒");
			if (waitTime > 0) {
				Utils.sleep(waitTime);
			}
			WebElement element = WebDriverCommon.getClickableWebElement(objName);
			click(element);
		} catch (Exception e) {
			System.out.println("不可电机的对象");
		}
	}

	/**
	 * 
	 * @param objName
	 * @return
	 */
	public static String objInfo(final String objName) {
		return String.format("%s(\"%s=%s\")", objName, ByType.getByType(objName), ByType.getValue(objName));
	}

	/**
	 * 
	 */
	public static void excuteJs(String jsString) {
		((JavascriptExecutor) driver).executeScript(jsString);
	}

	/**
	 * 
	 * @param jsString
	 * @return
	 */
	public static String excuteJsReuslt(String jsString) {
		return ((JavascriptExecutor) driver).executeScript(jsString).toString();
	}

	public static void forceClick(String objName) {
		Action.sendkey(objName, Keys.ENTER);
	}

	public static void sendkey(String objName, Keys key) {
		element = ElementCommon.getElement(objName);
		element.sendKeys(key);
	}

	public static void sendkey(String objName, String value) {
		element = ElementCommon.getElement(objName);
		element.click();
		element.clear();
		element.sendKeys(value);

	}

	public static void think(int waitTime) {
		Utils.sleep(waitTime);
	}

	public static void uploadFile(String objName, String fileName) {
		element = WebDriverCommon.getWebElement(objName);
		Action.think(3);
		fileName = System.getProperty("user.dir")+"\\uploadFile\\"+fileName;
		element.sendKeys(fileName);
		Action.think(3);
	}

	public static void uploadFile(String fileName) {
		fileName = System.getProperty("user.dir")+"\\uploadFile\\"+fileName;
		Robot robot = null;
		StringSelection selection  = new StringSelection(fileName);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
		
		try {
			robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			
			Action.think(2);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		} catch (AWTException e) {
			e.printStackTrace();
		}
		Action.think(3);
	}

	public static void newWindowTab() {
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
		driver.manage().window().maximize();
	}

	public static void scrollToTop() {
		String jsString = "document.body.scrollTop=0";
		excuteJs(jsString);
	}

	public static void info(String message) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss,SSS");
		message = new Date() + " INFO " + message;
		Reporter.log(simpleDateFormat.format(message));
		System.out.println(message);
	}

	public static void editFrameText(String objName, String text) {
		element = ElementCommon.getElement(objName);
		try {
			driver.switchTo().frame(element);
			driver.switchTo().activeElement().sendKeys(text);
		} catch (Exception e) {
			Assert.fail("fail");
		}
	}
	
	public static void clickByImg(String imgPath){
		Screen screen = new Screen();
		Pattern pattern = new Pattern(imgPath);
		try {
			screen.doubleClick(pattern);
		} catch (Exception e) {
			Action.info("h");
		}
	}

}
