package nh.ui.automation.tools.Selenide;

import java.util.Arrays;

import org.testng.TestNG;

import nh.ui.automation.tools.bean.RunEnv;
import nh.ui.automation.tools.utils.ConfigLoadHelper;

public class AppService {

	public static void ngCase() {
		TestNG ng = new TestNG();
		initNgTestClasses(ng);
		ng.run();
	}

	private static void initNgTestClasses(TestNG ng) {
		boolean runSingleClass = ConfigLoadHelper.getValue("runSingleClass").equalsIgnoreCase("true");

		if (runSingleClass) {
			Class<?> runclz = null;
			// Class listenerclz = null;
			try {
				runclz = Class.forName(RunEnv.className);
				// listenerclz =
				// Class.forName("com.moodys.atom.listener.CustomListener");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				System.out.println("***********" + RunEnv.className);
			}
			Class<?>[] classNames = { runclz };
			// Class[] listenerClzs = { listenerclz };
			ng.setTestClasses(classNames);
			ng.setDefaultSuiteName(RunEnv.className.substring(0, RunEnv.className.lastIndexOf(".")));
			ng.setDefaultTestName(
					RunEnv.className.substring(RunEnv.className.lastIndexOf(".") + 1, RunEnv.className.length()));
			// ng.setListenerClasses(Arrays.asList(listenerClzs));
		} else {
			ng.setTestSuites(Arrays.asList(ConfigLoadHelper.getValue("testng.xml.path")));
		}
	}
}
