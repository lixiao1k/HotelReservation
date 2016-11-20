package po;

public class UserPO {
	private long uid;
	private String username;
	private String password;
	private Role role;
	public UserPO(){
		
	}
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
	public long getUid(){
		return this.uid;
	}
	public void setUsername(String username){
		this.username = username;
	}
	public void setPassword(String password){
		this.password = password;
	}
	/*
	 * ����userIdΪ��������������hibernate��ͨ��������Ʒ���private������private����ֹview��logic��������
	 */
	private void setUid(long uid){
		this.uid = uid;
	}
}
