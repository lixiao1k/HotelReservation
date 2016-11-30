package vo;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import info.OrderStatus;
import info.Room;


public class OrderVO{
	private String orderNum;
	private String name;
	private OrderStatus status;
	private String hotelName;
	private Date checkInTime;
	private Date checkOutTime;
	private Date actualCheckInTime;
	private Date actualCheckOutTime;
	private Room room;
	private int roomNum;
	private double roomPrice;
	private String strategy;
	private double strategyOff;
	private double price;
	private double priceAfterStrategy;
	private boolean child;
	private int people;
	private String contactWay;
	private String contactName;
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
	public String getName(){
		return this.name;
	}
	public String getHotelName(){
		return this.hotelName;
	}
	public Date getCheckOutTime(){
		return this.checkOutTime;
	}
	public String getOrderNum(){
		return orderNum;
	}
	public OrderStatus getStatus(){
		return status;
	}
	public Date getActualCheckInTime(){
		return actualCheckInTime;
	}
	public Date getActualCheckOutTime(){
		return actualCheckOutTime;
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
	public double getPrice(){
		return price;
	}
	public double getPriceAfterStrategy(){
		return priceAfterStrategy;
	}
	public String getStrategy(){
		return strategy;
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
	public void setActualCheckInTime(Date actualCheckInTime){
		this.actualCheckInTime = actualCheckInTime;
	}
	public void setActualCheckOutTime(Date actualCheckOutTime){
		this.actualCheckOutTime = actualCheckOutTime;
	}
	public void setOrderNum(String orderNum){
		this.orderNum = orderNum;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setRoom(Room room){
		this.room = room;
	}
	public void setPrice(double price){
		this.price = price;
	}
	public void setRoomNum(int roomNum){
		this.roomNum =roomNum;
	}
	public void setRoomPrice(double roomPrice){
		this.roomPrice = roomPrice;
	}
	public void setPriceAfterStrategy(double priceAfterStrategy){
		this.priceAfterStrategy = priceAfterStrategy;
	}
	public void setStatus(OrderStatus status){
		this.status = status;
	}
	public void setStrategy(String strategy){
		this.strategy = strategy;
	}
	public void setStrategyOff(double strategyOff){
		this.strategyOff = strategyOff;
	}
	public void setHotelName(String hotelName){
		this.hotelName = hotelName;
	}
}