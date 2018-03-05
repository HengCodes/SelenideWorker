package nh.ui.automation.tools.report;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.testng.ITestResult;

import nh.ui.automation.tools.constant.Constants;
import nh.ui.automation.tools.entity.TestResultInfo;
import nh.ui.automation.tools.utils.CsvUtil;

public class TestResult {
	private static final Object[] FILE_HEADER = { "TC_Name", "Result", "Detail" };
	private static List<TestResultInfo> resultInfos = new ArrayList<TestResultInfo>();

	public static void generateCvsReport() {
		String file = "";
		List<TestResultInfo> result = combineResult(resultInfos);
		CsvUtil.writeData(FILE_HEADER, result, file);
	}

	private static List<TestResultInfo> combineResult(List<TestResultInfo> ts) {
		List<TestResultInfo> result = new ArrayList<TestResultInfo>();
		Set<String> classNameSet = new HashSet<>();
		for (TestResultInfo testResultInfo : ts) {
			classNameSet.add(testResultInfo.getClassname());
		}

		Iterator<String> classNameIterator = classNameSet.iterator();
		while (classNameIterator.hasNext()) {
			TestResultInfo testResultInfo = new TestResultInfo();
			String className = classNameIterator.next();
			testResultInfo.setClassname(className);

			List<String> resultL = new ArrayList<>();
			List<String> detailL = new ArrayList<>();
			for (TestResultInfo t : ts) {
				if (className.equals(t.getClassname())) {
					resultL.add(t.getResult());
					if (!"".equals(t.getDetail())) {
						detailL.add(t.getDetail());
					}
				}
			}

			if (resultL.contains(Constants.FAILED)) {
				testResultInfo.setResult(Constants.FAILED);
			} else {
				testResultInfo.setResult(Constants.SUCCESS);
			}

			testResultInfo.setDetail(detailL.toString());
			result.add(testResultInfo);
		}
		return result;
	}

	public static void add(ITestResult iTestResult) {
		TestResultInfo testResultInfo = new TestResultInfo();

		String className = iTestResult.getTestClass().getRealClass().getSimpleName();

		testResultInfo.setClassname(className);

		int status = iTestResult.getStatus();
		String statu = Constants.FAILED;
		switch (status) {
		case ITestResult.FAILURE:
			statu = Constants.FAILED;
			break;

		case ITestResult.SKIP:
			statu = Constants.FAILED;
			break;

		case ITestResult.SUCCESS:
			statu = Constants.SUCCESS;
			break;

		default:
			statu = Constants.FAILED;
			break;
		}

		testResultInfo.setResult(statu);
		if (iTestResult.getThrowable() != null) {
			testResultInfo.setDetail("(" + iTestResult.getName() + ")" + iTestResult.getThrowable().getMessage());
		}
		resultInfos.add(testResultInfo);
	}

}
