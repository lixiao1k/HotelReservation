package info;

import po.HotelPO;

public class HotelItem {
	private long hiId;
	private HotelPO hotel;
	private Room room;
	private int num;
	private double price;
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
}
