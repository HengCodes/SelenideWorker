package nh.ui.antomation.test.cases;

import org.testng.annotations.Test;

import nh.ui.automation.tools.bean.Case;
import nh.ui.automation.tools.common.Action;

/**
 * 项目    ：UI自动化测试 Selenide
 * 类描述：
 * @author Eric
 * @date 2017年2月9日
 * nh.ui.antomation.test.cases
 */
public class Case2 extends Case{
	@Override
	public void init() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Test
	public void baidu_search() {
		Action.openUrl("http://www.baidu.com");
		//$("bdSearchInput").setValue("Selenide").pressEnter();
		Action.sendkey("bdSearchInput", "Selenide");
		Action.think(3);
		String imgPath ="F:\\baisearch.png";
		Action.clickByImg(imgPath);
		Action.think(30000);
//		Action.click("bdSubmitbtn");
//		Action.think(30000);
	}
}
