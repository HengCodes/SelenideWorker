package nh.ui.automation.tools.common;

import java.io.IOException;

public class ProcessUtils {
	/**
	 * 关闭所有webdriver启动的进程
	 * 如果要关闭所有浏览器进行，则可以杀掉如下进程
	 * chrome.exe/firefox.exe iexplore.exe
	 */
	public static void killAllWebDriverProcesses(){
		try {
			Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
			Runtime.getRuntime().exec("taskkill /F /IM MicrosoftWebDriver.exe");
			Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
