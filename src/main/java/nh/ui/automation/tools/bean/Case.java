package nh.ui.automation.tools.bean;

import nh.ui.automation.tools.common.Action;

/**
 * 项目    ：UI自动化测试 Selenide
 * 类描述：
 * @author Eric
 * @date 2017年2月9日
 * nh.ui.automation.tools.bean
 */
public abstract class Case extends Action{
	public abstract void init() throws Exception;
}
