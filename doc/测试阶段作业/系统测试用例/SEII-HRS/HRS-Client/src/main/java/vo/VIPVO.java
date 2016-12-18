package vo;

import java.time.LocalDate;

public class VIPVO {
	private int type;
	private LocalDate birthday;
	private long userId;
	private String companyName;
	public VIPVO(int type, LocalDate birthday, long userId, String companyName) {
		this.type=type;
		this.birthday=birthday;
		this.userId=userId;
		this.companyName=companyName;
	}
	public void setType(int type){
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
	public int getType(){
		return type;
	}
	
}
