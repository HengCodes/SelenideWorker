package nh.ui.automation.tools.entity;

/**
 * 项目 ：UI自动化测试 Selenide 类描述：
 * 
 * @author Eric
 * @date 2017年2月9日 nh.ui.automation.tools.entity
 */
public class TestResultInfo {
	private String classname = "";
	private String result = "";
	private String detail = "";

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
}
