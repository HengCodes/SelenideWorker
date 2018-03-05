package nh.ui.automation.tools.utils;

import java.util.ResourceBundle;

/**
 * 项目    ：UI自动化测试 Selenide
 * 类描述：
 * @author Eric
 * @date 2017年2月9日
 * nh.ui.automation.tools.utils
 */
public class ConfigLoadHelper {

    private final static String CONFIG_PROPERTIES = "config";
 
    public static ResourceBundle bundle = null;
 

    /**
     *
     * @param keyString
     * @return
     */
    public static String getValue(String keyString) {
        return configLoader().getString(keyString);
    }
	/**
	 * @return
	 */
	public static ResourceBundle configLoader() {
		  if (bundle == null) {
	            bundle = ResourceBundle.getBundle(CONFIG_PROPERTIES);
	        }
	        return bundle;
	}
}
