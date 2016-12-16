package vo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class ManageClientVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8256851516544145211L;
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
	public ManageClientVO(){
		
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
	@Override
	public String toString() {
		return "ManageClientVO [userid=" + userid + ", username=" + username + ", birthday=" + birthday
				+ ", companyname=" + companyname + ", phonenumber=" + phonenumber + "]";
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
