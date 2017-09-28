package info;


import java.io.Serializable;
import java.util.Date;

public class OrderStrategy implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8118008012122407612L;
	private long userId;
	private long hotelId;
	private String companyName;
	private Date birthday;
	private Date checkInTime;
	private Date checkOutTime;
	private BusinessCircle bcir;
	private boolean isVIP;
	private int roomNum;
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
	public BusinessCircle getBcir() {
		return bcir;
	}
	public void setBcir(BusinessCircle bcir) {
		this.bcir = bcir;
	}
	public boolean isVIP() {
		return isVIP;
	}
	public void setVIP(boolean isVIP) {
		this.isVIP = isVIP;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}
	public Date getCheckOutTime() {
		return checkOutTime;
	}
	public void setCheckOutTime(Date checkOutTime) {
		this.checkOutTime = checkOutTime;
	}
}
