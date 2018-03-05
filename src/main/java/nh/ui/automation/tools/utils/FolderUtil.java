package nh.ui.automation.tools.utils;

import java.io.File;

import nh.ui.automation.tools.bean.RunEnv;

public class FolderUtil {
	public static String createResultDir(String path) {
		String resultPath = "";

		// #run model
		String runSingleClass = ConfigLoadHelper.getValue("runSingleClass");
		if (runSingleClass.equalsIgnoreCase("true")) {
			resultPath = path + ConfigLoadHelper.getValue("report.output.single");
		} else {
			resultPath = path + ConfigLoadHelper.getValue("report.output.all");
		}

		//
		File file = new File(System.getProperty("user.dir") + "/" + resultPath);
		deleteFolder(file);

		if (!runSingleClass.equalsIgnoreCase("true")) {
			resultPath = getResultPath(resultPath);
		} else {
			resultPath = System.getProperty("user.dir") + resultPath;
		}
		createDir(resultPath);
		return resultPath;

	}

	/**
	 * 删除文件或者文件夹包括字目录
	 */
	public static void deleteFolder(File file) {
		File[] listFiles = file.listFiles();
		if (listFiles != null) {
			for (File f : listFiles) {
				if (f.isDirectory()) {
					deleteFolder(f);
				} else {
					f.delete();
				}
			}
		}
		file.delete();
	}

	public static void createDir(String dir) {
		File file = new File(dir);
		if (file.exists()) {
			deleteFolder(file);
		} else {
			boolean result = file.mkdirs();
			String txt = result ? "Success" : "Fail";
			System.out.println(txt + ", create " + file.getAbsolutePath());
		}
	}

	public static String getResultPath(String path) {
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append(System.getProperty("user.dir"));
		sBuffer.append(path);
		sBuffer.append(System.getProperty("file.separator"));
		sBuffer.append(RunEnv.browerName);
		sBuffer.append("_");
		sBuffer.append(RunEnv.browerVersion);
		return sBuffer.toString();
	}
}
