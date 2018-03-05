package nh.ui.automation.tools.bean;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.codeborne.selenide.Configuration;

import nh.ui.automation.tools.constant.Constants;
import nh.ui.automation.tools.entity.TestObj;
import nh.ui.automation.tools.report.ExtentManager;
import nh.ui.automation.tools.utils.JsonPluginsUtil;
import nh.ui.automation.tools.utils.JsonUtils;

/**
 * 项目 ：UI自动化测试 Selenide 类描述：
 * 
 * @author Eric
 * @date 2017年2月9日 nh.ui.automation.tools.bean
 */
public class WebDriverServerBase extends Base {
	public static WebDriver driver = null;
	//public static RemoteWebDriver driver = null;
	public static WebDriverWait webDriverWait  = null;
	public static WebElement element = null;
	public static List<WebElement> elements = null;
	public static Map<String, TestObj> objApiData =  new HashMap<String, TestObj>();
	public static boolean reuseBrowser = false;

	@BeforeSuite
	public void beforeSuite() {
		super.beforeSuite();
		extent = ExtentManager.createInstance("extent.html");
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent.html");
		extent.attachReporter(htmlReporter);
		try {
			initDriver();
			initWaitTimeOut();
			cachedObjData();
		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("浏览器初始化启动失败。。。");
		}
	}

	/**
	 * get host info
	 */
	@BeforeClass
	public void indexPage() {
		ExtentTest parent = extent.createTest(getClass().getName());
		parentTest.set(parent);

		switch (RunEnv.browerName) {
		case "iphone":
			driver.manage().window().setSize(new Dimension(414, 736));
			break;
		case "ipad":
			driver.manage().window().setSize(new Dimension(1024, 768));
			break;
		default:
			driver.manage().window().maximize();
			break;
		}

		if (!reuseBrowser) {
			driver.get(RunEnv.host);
		}

	}

	/**
	 * Init driver
	 * 
	 * @throws MalformedURLException
	 */
	public void initDriver() throws Exception {
		String browser = RunEnv.browerName;
		if (StringUtils.equalsIgnoreCase(browser, "chrome")) {
			Configuration.browser = "chrome";
			System.setProperty("webdriver.chrome.driver", Constants.GETCHROMEDRIVERPATH_STRING);
			System.setProperty("selenide.browser", "Chrome");
			driver = getWebDriver();
		} else if (StringUtils.equalsIgnoreCase(browser, "ie")) {
			System.setProperty("webdriver.ie.driver", Constants.GETIEIVERPATH_STRING);
			System.setProperty("selenide.browser", "Ie");
			driver = getWebDriver();
		} else if (StringUtils.equalsIgnoreCase(browser, "ff")) {
			driver = getWebDriver();
		}

	}
	
	private void initWaitTimeOut() {
		webDriverWait  = new WebDriverWait(driver, 60);
	}

	@BeforeMethod
	public synchronized void beforeMethod(Method method) {
		ExtentTest child = ((ExtentTest) parentTest.get()).createNode(method.getName());
		test.set(child);
	}

	@AfterMethod
	public synchronized void afterMethod(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			((ExtentTest) test.get()).fail(result.getThrowable());
		} else if (result.getStatus() == ITestResult.SKIP) {
			((ExtentTest) test.get()).skip(result.getThrowable());
		} else {
			((ExtentTest) test.get()).pass("Test Passed");
		}
		extent.flush();
	}
	
	
	private void cachedObjData() throws Exception {
		System.out.println("Getting test objects form api...");
		String testObjAPi = bundle.getString("testObjAPi");
		JSONArray jsonObject  =  JsonUtils.readJsonArrFromUrl(testObjAPi);
			List<TestObj> testObj = JsonPluginsUtil.jsonToBeanList(jsonObject.toString(), TestObj.class);
			for(TestObj t : testObj){
				objApiData.put(t.getPageobjname(), t);
			}
		System.out.println("load finish..");
		if (objApiData.size() < 1) {
			throw new Exception("not found testobj from url");
		}
	}

}
