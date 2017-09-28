package vo;

import java.time.LocalDate;

import info.VIPType;

public class VIPVO {
	private VIPType type;
	private LocalDate birthday;
	private long userId;
	private String companyName;
	public VIPVO(VIPType type, LocalDate birthday, long userId, String companyName) {
		this.type=type;
		this.birthday=birthday;
		this.userId=userId;
		this.companyName=companyName;
	}
	public void setType(VIPType type){
		this.type = type;
	}
	public void setBirthDay(LocalDate birthday){
		this.birthday = birthday;
	}
	public void setUserId(long userId){
		this.userId = userId;
	}
	public void setCompanyName(String companyName){
		this.companyName = companyName;
	}
	public String getCompanyName(){
		return companyName;
	}
	public long getUserId(){
		return userId;
	}
	public LocalDate getBirthday(){
		return birthday;
	}
	public VIPType getType(){
		return type;
	}
	
}
