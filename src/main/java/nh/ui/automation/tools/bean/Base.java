package nh.ui.automation.tools.bean;

import java.util.ResourceBundle;

import com.aventstack.extentreports.ExtentReports;

import nh.ui.automation.tools.utils.ConfigLoadHelper;

/**
 * 项目 ：UI自动化测试 Selenide 类描述：
 * 
 * @author Eric
 * @date 2017年2月9日 nh.ui.automation.tools.bean
 */
public class Base {
	public static ResourceBundle bundle = null;
	public static String outputDir = null;

	public static ExtentReports extent;
	public static ThreadLocal<Object> parentTest = new ThreadLocal<Object>();
	public static ThreadLocal<Object> test = new ThreadLocal<Object>();
	
	public void beforeSuite() {
		bundle = ConfigLoadHelper.configLoader();
		System.out.println("bundle");

		if (!RunEnv.runRemote) {
			RunEnv.setLoalEnvInfo();
		}
	}
}
