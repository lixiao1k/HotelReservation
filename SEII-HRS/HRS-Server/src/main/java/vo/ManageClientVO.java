package vo;

import java.io.Serializable;
import java.util.Date;

public class ManageClientVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8256851516544145211L;
	private long userid;
	private Date birthday;
	private String companyname;
	private String phone;
	public ManageClientVO(long userid, Date birthday, String companyname,
			String phonenumber) {
		super();
		this.userid = userid;
		this.birthday = birthday;
		this.companyname = companyname;
		this.phone = phonenumber;
	}
	public ManageClientVO(){
		
	}
	public String getPhonenumber() {
		return phone;
	}
	public void setPhonenumber(String phonenumber) {
		this.phone = phonenumber;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
}
