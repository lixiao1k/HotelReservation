package vo;

public class ManageWEBSalerVO {
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
