package nh.ui.antomation.test.cases;

import org.testng.annotations.Test;

import com.codeborne.selenide.Selenide;

public class test1 {
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
