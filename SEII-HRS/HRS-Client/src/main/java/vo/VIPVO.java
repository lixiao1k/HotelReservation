package vo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import info.VIPType;

public class VIPVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2375349133518351815L;
	private VIPType type;
	private Date birthday;
	private long userId;
	private String companyName;
	public VIPVO(VIPType type, Date birthday, long userId, String companyName) {
		this.type=type;
		this.birthday=birthday;
		this.userId=userId;
		this.companyName=companyName;
	}
	public void setType(VIPType type){
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
	public VIPType getType(){
		return type;
	}
	
}
