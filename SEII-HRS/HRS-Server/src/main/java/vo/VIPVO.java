package vo;

import java.util.Date;

public class VIPVO {
	private int type;
	private Date birthday;
	private long userId;
	private String companyName;
	public void setType(int type){
		this.type = type;
	}
	public void setBirthDay(Date birthday){
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
	public Date getBirthday(){
		return birthday;
	}
	public int getType(){
		return type;
	}
	
}
