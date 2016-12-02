package vo;

import java.time.LocalDate;
import java.util.List;

public class ManageClientVO {
	private long userid;
	private String username;
	private LocalDate birthday;
	private String companyname;
	private List<String> phonenumber;
	public ManageClientVO(long userid, String username, LocalDate birthday, String companyname,
			List<String> phonenumber) {
		super();
		this.userid = userid;
		this.username = username;
		this.birthday = birthday;
		this.companyname = companyname;
		this.phonenumber = phonenumber;
	}
	public List<String> getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(List<String> phonenumber) {
		this.phonenumber = phonenumber;
	}
	public void addPhonenumber(String number){
		phonenumber.add(number);
	}
	public void deletePhonenumber(String number){
		phonenumber.remove(number);
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
