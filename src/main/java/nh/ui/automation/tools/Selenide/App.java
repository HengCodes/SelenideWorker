package nh.ui.automation.tools.Selenide;

import nh.ui.automation.tools.bean.Base;
import nh.ui.automation.tools.bean.RunEnv;
import nh.ui.automation.tools.report.TestResult;
import nh.ui.automation.tools.report.XsltReport;
import nh.ui.automation.tools.utils.ConfigLoadHelper;
import nh.ui.automation.tools.utils.FolderUtil;

/**
 * 程序Main方法
 *
 */
public class App extends Base {

	static String browserName = "";

	public static void main(String[] args) {
		outputDir = FolderUtil.createResultDir(ConfigLoadHelper.getValue("report.output"));
		RunEnv.initRunArgs(args);
		AppService.ngCase();
		generateReport();
	}

	private static void generateReport() {
		XsltReport.transformXslt(ConfigLoadHelper.getValue("testng_result_xml_path"),
				ConfigLoadHelper.getValue("testngXslt_path"));
		TestResult.generateCvsReport();
	}
}
