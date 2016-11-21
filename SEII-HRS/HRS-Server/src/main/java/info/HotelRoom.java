package info;

import po.HotelPO;

public class HotelRoom {
	private long hrId;
	private HotelPO hotel;
	private Room room;
	public HotelRoom(){
		
	}
	private long getHrId(){
		return this.hrId;
	}
	public HotelPO getHotel(){
		return this.hotel;
	}
	public Room getRoom(){
		return this.room;
	}
	public void setRoom(Room room){
		this.room = room;
	}
	public void setHotel(HotelPO hotel){
		this.hotel = hotel;
	}
	private void setHrId(long hrId){
		this.hrId = hrId;
	}
}
