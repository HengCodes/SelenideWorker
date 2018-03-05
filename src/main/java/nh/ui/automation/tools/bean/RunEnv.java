package nh.ui.automation.tools.bean;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.csv.CSVRecord;

import nh.ui.automation.tools.utils.ConfigLoadHelper;
import nh.ui.automation.tools.utils.CsvUtil;

/**
 * 项目 ：UI自动化测试 Selenide 类描述：
 * 
 * @author Eric
 * @date 2017年2月9日 nh.ui.automation.tools.bean
 */
public class RunEnv {

	public static ResourceBundle bundle = null;
	public static boolean runRemote = false;
	public static String browerName = "";
	public static String browerVersion = "";
	public static String className = "";
	public static String env = "";
	public static String host = "", user = "", password = "";
	public static String dburl = "", dbuser = "", dbpassword = "";

	/**
	 * 本地默认配置加载运行
	 */
	public static void setLoalEnvInfo() {
		bundle = ConfigLoadHelper.configLoader();

		browerName = bundle.getString("run.brName");
		browerVersion = bundle.getString("run.brVer");
		env = bundle.getString("run.env");
		System.out.println("1");
		parsingHost(env);
		System.out.println("本地运行配置：环境-->" + env + ",浏览器-->" + browerName + "版本-->" + browerVersion);
	}

	/**
	 * 执行机运行时加载
	 */

	public static void initRunArgs(String[] args) {
		bundle = ConfigLoadHelper.configLoader();
		if (args.length > 0) {
			className = args[0];
			if (args.length > 1) {
				System.out.println("1");
				parsingHost(args[1]);
				System.out.println("2");
				browerName = args[2];
				//browerVersion = args[3];
			}
			runRemote = true;
			System.out.println("运行环境为 : 浏览器类型-->" + browerName);
		}
	}

	public static void parsingHost(String env) {
		String userDir = System.getProperty("user.dir") + "./data/EnvInfo.csv";
		// String dataPath = userDir.replace("QA\\Selenium", "Internal
		// Projects\\ATP\\WebApp\\static\\data\\Params.csv");
		List<CSVRecord> list = CsvUtil.getData(userDir);
		for (CSVRecord csvRecord : list) {
			System.out.println(csvRecord);
			String csvEnv = csvRecord.get("Env").trim();
			if (env.toUpperCase().equals(csvEnv.toUpperCase())) {
				// 环境信息
				System.out.println(csvRecord.get("Env"));

				host = csvRecord.get("Host");
				user = csvRecord.get("User");
				password = csvRecord.get("Password");

				// 数据库信息 dburl="",dbuser="",dbpassword="";
				dburl = csvRecord.get("DB_Url");
				dbuser = csvRecord.get("DB_User");
				dbpassword = csvRecord.get("DB_Password");
				break;
			}
		}

		if (host.isEmpty()) {
			throw new RuntimeException("Wrong env name:" + env + ", please specify a correct env name!");
		}
	}
}
