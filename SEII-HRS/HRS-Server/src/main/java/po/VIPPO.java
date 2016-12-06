package po;

import java.util.Date;

import info.VIPType;

public class VIPPO {
	private long id;
	private VIPType type;
	private Date birthday;
	private String companyName;
	public void setCompanyName(String companyName){
		this.companyName = companyName;
	}
	public void setBirthday(Date birthday){
		this.birthday = birthday;
	}
	public void setType(VIPType type){
		this.type = type;
	}
	private void setId(long id){
		this.id = id;
	}
	public VIPType getType(){
		return type;
	}
	public Date getBirthday(){
		return birthday;
	}
	public String getCompanyName(){
		return companyName;
	}
	public long getId(){
		return id;
	}
}
