package po;

import info.UserStatus;
import info.UserType;

public class UserPO {
	private long uid;
	private String username;
	private String password;
	private UserType type;
	private UserStatus status;
	public UserPO(){
		
	}
	public UserPO(String username,String password){
		this.username = username;
		this.password = password;
		setStatus(UserStatus.ONLINE);
	}
	public UserPO(String username,String password, UserType type){
		this.username = username;
		this.password = password;
		this.type=type;
		setStatus(UserStatus.ONLINE);
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
	public UserStatus getStatus() {
		return status;
	}
	public void setStatus(UserStatus status) {
		this.status = status;
	}
	public UserType getType() {
		return type;
	}
	public void setType(UserType type) {
		this.type = type;
	}
}
