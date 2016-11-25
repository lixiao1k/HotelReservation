package info;

import vo.HotelVO;

public class HotelRoom {
	private long hrId;
	private HotelVO hotel;
	private Room room;
	public HotelRoom(){
		
	}
	private long getHrId(){
		return this.hrId;
	}
	public HotelVO getHotel(){
		return this.hotel;
	}
	public Room getRoom(){
		return this.room;
	}
	public void setRoom(Room room){
		this.room = room;
	}
	public void setHotel(HotelVO hotel){
		this.hotel = hotel;
	}
	private void setHrId(long hrId){
		this.hrId = hrId;
	}
}
