package info;

import vo.HotelVO;

public class HotelItem {
	private long hiId;
	private HotelVO hotel;
	private Room room;
	private int num;
	private double price;
	public long getHiId(){
		return this.hiId;
	}
	public HotelVO getHotel(){
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
	public void setHotel(HotelVO hotel){
		this.hotel = hotel;
	}
	private void setHiId(long hiId){
		this.hiId = hiId;
	}
}
