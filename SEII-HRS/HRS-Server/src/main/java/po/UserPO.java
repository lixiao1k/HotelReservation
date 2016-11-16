package po;

public class UserPO {
	private long userId;
	private String username;
	private String password;
	private Role role;
	public UserPO(String username,String password){
		this.username = username;
		this.password = password;
	}
	public Role getRole(){
		return this.role;
	}
	public void setRole(Role role){
		this.role = role;
	}
	public String getUsername(){
		return this.username;
	}
	public String getPassword(){
		return this.password;
	}
	public long getUserId(){
		return this.userId;
	}
	public void setUsername(String username){
		this.username = username;
	}
	public void setPassword(String password){
		this.password = password;
	}
	/*
	 * 这里userId为代理主键，由于hibernate能通过反射机制访问private，设立private来防止view和logic层的误操作
	 */
	private void setUserId(long userId){
		this.userId = userId;
	}
}
