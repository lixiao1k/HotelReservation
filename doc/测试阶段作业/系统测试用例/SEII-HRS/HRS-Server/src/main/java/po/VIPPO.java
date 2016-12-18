package po;

import java.time.LocalDate;

import info.VIPType;

public class VIPPO {
	private long id;
	private VIPType type;
	private LocalDate birthday;
	private String companyName;
	public void setCompanyName(String companyName){
		this.companyName = companyName;
	}
	public void setBirthday(LocalDate birthday){
		this.birthday = birthday;
	}
	public void setType(VIPType type){
		this.type = type;
	}
	private void setId(long id){
		this.id = id;
	}
	public VIPPO(long id, VIPType type, LocalDate birthday, String companyName) {
		super();
		this.id = id;
		this.type = type;
		this.birthday = birthday;
		this.companyName = companyName;
	}
	public VIPType getType(){
		return type;
	}
	public LocalDate getBirthday(){
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
