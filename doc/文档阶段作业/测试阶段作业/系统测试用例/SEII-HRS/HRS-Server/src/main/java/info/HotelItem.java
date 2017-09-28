package info;

import java.util.Date;

import po.HotelPO;

public class HotelItem {
	private long hiId;
	private HotelPO hotel;
	private Room room;
	private int num;
	private int total;
	private double price;
	private Date date;
	public long getHiId(){
		return this.hiId;
	}
	public HotelPO getHotel(){
		return this.hotel;
	}
	public Room getRoom(){
		return this.room;
	}
	public int getNum(){
		return this.num;
	}
	public double getPrice(){
		return this.price;
	}
	public Date getDate(){
		return date;
	}
	public void setPrice(double price){
		this.price = price;
	}
	public void setNum(int num){
		this.num = num;
	}
	public void setRoom(Room room){
		this.room = room;
	}
	public void setHotel(HotelPO hotel){
		this.hotel = hotel;
	}
	private void setHiId(long hiId){
		this.hiId = hiId;
	}
	public void setDate(Date date){
		this.date = date;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
}
