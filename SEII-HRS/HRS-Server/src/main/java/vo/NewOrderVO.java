package vo;

import java.util.Date;

import info.Room;

public class NewOrderVO {
	private long userId;
	private long hotelId;
	private boolean child;
	private int people;
	private String contactWay;
	private String contactName;
	private Date checkInTime;
	private Date checkOutTime;
	private Room room;
	private int roomNum;
	private double roomPrice;
	private long strategyId;
	private double strategyOff;
	//for test
	public NewOrderVO(long userId,long hotelId,boolean child,int people,String contactWay,String contactName,Date checkInTime,Date checkOutTime,Room room,int roomNum,double roomPrice,long strategy,double strategyOff){
		this.userId = userId;
		this.hotelId = hotelId;
		this.child = child;
		this.people = people;
		this.contactWay = contactWay;
		this.checkInTime = checkInTime;
		this.checkOutTime = checkOutTime;
		this.contactName = contactName;
		this.room = room;
		this.roomNum = roomNum;
		this.roomPrice = roomPrice;
		this.strategyId = strategy;
		this.strategyOff = strategyOff;
	}
	public String getContactWay(){
		return contactWay;
	}
	public String getContactName(){
		return contactName;
	}
	public void setContactWay(String contactWay){
		this.contactWay = contactWay;
	}
	public void setContactName(String contactName){
		this.contactName = contactName;
	}
	public Date getCheckInTime(){
		return this.checkInTime;
	}
	public long getHotelId(){
		return this.hotelId;
	}
	public long getUserId(){
		return this.userId;
	}
	public Date getCheckOutTime(){
		return this.checkOutTime;
	}
	public Room getRoom(){
		return room;
	}
	public int getRoomNum(){
		return roomNum;
	}
	public double getRoomPrice(){
		return roomPrice;
	}
	public long getStrategyId(){
		return strategyId;
	}
	public double getStrategyOff(){
		return strategyOff;
	}
	public boolean getChild(){
		return this.child;
	}
	public int getPeople(){
		return this.people;
	}
	public void setPeople(int people){
		this.people = people;
	}
	public void setChild(boolean child){
		this.child = child;
	}
	public void setCheckOutTime(Date checkOutTime){
		this.checkOutTime = checkOutTime;
	}
	public void setCheckInTime(Date checkInTime){
		this.checkInTime = checkInTime;
	}
	public void setRoom(Room room){
		this.room = room;
	}
	public void setRoomNum(int roomNum){
		this.roomNum =roomNum;
	}
	public void setRoomPrice(double roomPrice){
		this.roomPrice = roomPrice;
	}
	public void setStrategy(long strategy){
		this.strategyId = strategy;
	}
	public void setStrategyOff(double strategyOff){
		this.strategyOff = strategyOff;
	}
	public void setHotelId(long hotelId){
		this.hotelId = hotelId;
	}
	public void setUserId(long userId){
		this.userId = userId;
	}
}
