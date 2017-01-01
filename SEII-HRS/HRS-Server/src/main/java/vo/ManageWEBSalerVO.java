package vo;

import java.io.Serializable;

public class ManageWEBSalerVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6045785147494329406L;
	private long userid;
	private String username;
	private String password;
	private String name;
	public ManageWEBSalerVO(long userid, String username, String password, String name) {
		super();
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.name = name;
	}
	public ManageWEBSalerVO() {
		// TODO Auto-generated constructor stub
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
	@Override
	public String toString() {
		return "ManageWEBSalerVO [userid=" + userid + ", username=" + username + ", password=" + password + ", name="
				+ name + "]";
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
