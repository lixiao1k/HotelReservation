package vo;

public class UserVO {
	String username;
	String password;
	long userid;
	Role role;
	public UserVO(String username,String password){
		this.password = password;
		this.username = username;
	}
}	
