package nh.ui.automation.tools.report;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

/**
 */
public class ExtentTestNGITestListener implements ITestListener {

	private static ExtentReports extent = ExtentManager.createInstance("extent.html");
	private static ThreadLocal<Object> parentTest = new ThreadLocal<Object>();
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

	@SuppressWarnings("unused")
	@Override
	public synchronized void onStart(ITestContext context) {
		ExtentTest parent = extent.createTest(getClass().getName());
		System.err.println("onStart");
	}

	@Override
	public synchronized void onFinish(ITestContext context) {
		extent.flush();
		System.err.println("onFinish");
	}

	@Override
	public synchronized void onTestStart(ITestResult result) {
		ExtentTest child = ((ExtentTest) parentTest.get()).createNode(result.getMethod().getMethodName());
		test.set(child);
		System.err.println("onTestStart");
	}

	@Override
	public synchronized void onTestSuccess(ITestResult result) {
		((ExtentTest) test.get()).pass("Test passed");
		System.err.println("onTestSuccess");
	}

	@Override
	public synchronized void onTestFailure(ITestResult result) {
		((ExtentTest) test.get()).fail(result.getThrowable());
		System.err.println("onTestFailure");
	}

	@Override
	public synchronized void onTestSkipped(ITestResult result) {
		((ExtentTest) test.get()).skip(result.getThrowable());
		System.err.println("onTestSkipped");
	}

	@Override
	public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}
}