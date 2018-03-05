package nh.ui.automation.tools.entity;

public class TestObj {
	
	public String id ="";
	public String pageobjname ="";
	public String bytype ="";
	public String byvalue ="";
	public String expectvalue ="";
	
	public String project ="";
	public String module ="";
	public String updateuser ="";
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getUpdateuser() {
		return updateuser;
	}
	public void setUpdateuser(String updateuser) {
		this.updateuser = updateuser;
	}
	public String getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}
	public String updatedate ="";

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPageobjname() {
		return pageobjname;
	}
	public void setPageobjname(String pageobjname) {
		this.pageobjname = pageobjname;
	}
	public String getBytype() {
		return bytype;
	}
	public void setBytype(String bytype) {
		this.bytype = bytype;
	}
	public String getByvalue() {
		return byvalue;
	}
	public void setByvalue(String byvalue) {
		this.byvalue = byvalue;
	}
	public String getExpectvalue() {
		return expectvalue;
	}
	public void setExpectvalue(String expectvalue) {
		this.expectvalue = expectvalue;
	}
	@Override
	public String toString() {
		return "TestObj [id=" + id + ", pageobjname=" + pageobjname + ", bytype=" + bytype + ", byvalue=" + byvalue
				+ ", expectvalue=" + expectvalue + "]";
	}
}
