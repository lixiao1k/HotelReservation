package po;

import info.UserType;

public class MemberPO {
	private String name;
	private UserType type;
	private UserPO user;
	private long mid;
	public MemberPO(){
	}
	public long getMid(){
		return mid;
	}
	public UserType getUserType(){
		return type;
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setUserType(UserType type){
		this.type = type;
	}
	public void setMid(long mid){
		this.mid = mid;
	}
	public UserType getType() {
		return type;
	}
	public void setType(UserType type) {
		this.type = type;
	}
	public UserPO getUser() {
		return user;
	}
	public void setUser(UserPO user) {
		this.user = user;
	}

}

