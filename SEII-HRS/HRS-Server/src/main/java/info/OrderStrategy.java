package info;

import java.util.Date;

public class OrderStrategy {
	private long userId;
	private long hotelId;
	private String companyName;
	private Date birthday;
	private Date checkInTime;
	public void setBirthday(Date birthday){
		this.birthday = birthday;
	}
	public void setCompanyName(String companyName){
		this.companyName = companyName;
	}
	public void setUserId(long userId){
		this.userId = userId;
	}
	public void setHotelId(long hotelId){
		this.hotelId = hotelId;
	}
	public void setCheckInTime(Date checkInTime){
		this.checkInTime = checkInTime;
	}
	public Date getBirthday(){
		return birthday;
	}
	public String getCompanyName(){
		return companyName;
	}
	public Date getCheckInTime(){
		return checkInTime;
	}
	public long getHotelId(){
		return hotelId;
	}
	public long getUserId(){
		return userId;
	}
}
