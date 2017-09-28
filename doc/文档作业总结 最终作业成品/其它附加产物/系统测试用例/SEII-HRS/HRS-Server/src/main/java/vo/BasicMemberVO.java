package vo;

import java.io.Serializable;

public class BasicMemberVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -801467949483609715L;
	private long userid;
	private String phonenumber;
	private String name;
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public BasicMemberVO(long userid, String phonenumber, String name) {
		super();
		this.userid = userid;
		this.phonenumber = phonenumber;
		this.name = name;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
