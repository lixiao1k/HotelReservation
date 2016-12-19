package po;

import java.time.LocalDate;
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
	public VIPPO(VIPType type, Date birthday, String companyName) {
		super();
		this.type = type;
		this.birthday = birthday;
		this.companyName = companyName;
	}
	public VIPPO(){
		
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
	public String toString(){
		if(type==VIPType.PERSON)
			return birthday.toString();
		else
			return companyName;
		
	}
}
