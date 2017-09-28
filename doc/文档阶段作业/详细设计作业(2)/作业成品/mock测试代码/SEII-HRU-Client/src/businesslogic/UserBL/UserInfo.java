package businesslogic.UserBL;

import java.util.Random;

public class UserInfo {
	private String username;
	private String password;
	private int status;
	private long userId;
	public UserInfo(String username,String password){
		this.username = username;
		this.password = password;
		this.status = 0;
		Random rnd = new Random();
		userId = rnd.nextLong();
	}
	public long getId(){ return this.userId; }
	public int getStatus(){ return this.status; }
	public void setStatus(int status){
		this.status = status;
	}
	public String getUsername(){ return this.username; }
	public String getPassword(){ return this.password; }
}
