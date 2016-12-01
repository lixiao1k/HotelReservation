package vo;

import java.time.LocalDate;

public class ManageClientVO {
	private long userid;
	private String username;
	private LocalDate birthday;
	private String companyname;
	public ManageClientVO(long userid, String username, LocalDate birthday, String companyname) {
		this.userid=userid;
		this.username=username;
		this.birthday=birthday;
		this.companyname=companyname;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public LocalDate getBirthday() {
		return birthday;
	}
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
}
