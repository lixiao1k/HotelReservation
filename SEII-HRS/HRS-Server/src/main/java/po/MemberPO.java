package po;

import info.UserType;

public class MemberPO {
	private String name;
	private UserType type;
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
	private void setMid(long mid){
		this.mid = mid;
	}
	public UserType getType() {
		return type;
	}
	public void setType(UserType type) {
		this.type = type;
	}

}

