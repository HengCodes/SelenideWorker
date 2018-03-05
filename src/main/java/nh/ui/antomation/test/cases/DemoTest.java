package nh.ui.antomation.test.cases;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.*;
import nh.ui.automation.tools.bean.Case;
import nh.ui.automation.tools.common.Action;

/**
 * 项目    ：UI自动化测试 Selenide
 * 类描述：
 * @author Eric
 * @date 2017年2月9日
 * nh.ui.antomation.test.cases
 */
public class DemoTest extends Case{
	@Override
	public void init() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Test
 	public void testBorwserSize(){
       	//指定Driver 的存放目录
       	System.setProperty("webdriver.chrome.driver","c:/driver/chromedriver.exe");
       	//指定需要启动的浏览器类型
       	System.setProperty("selenide.browser", "Chrome");
       	//指定浏览器大小
       	System.setProperty("selenide.browserSize", "400x800");
       	//打开测试地址
       	Selenide.open("https://www.baidu.com/");
       	//设置等待时间
       	Selenide.sleep(1000);
 	}
}
