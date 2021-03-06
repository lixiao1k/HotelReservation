package vo;

import java.io.Serializable;
import java.util.Date;

import info.Room;

public class CheckInRoomInfoVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5691485624570091344L;
	private long hotelId;
	private Room room;
	private int roomNum;
	private Date checkInTime;
	public void setRoom(Room room){
		this.room = room;
	}
	public void setHotelId(long hotelId){
		this.hotelId = hotelId;
	}
	public void setRoomNum(int roomNum){
		this.roomNum = roomNum;
	}
	public void setCheckInTime(Date checkInTime){
		this.checkInTime = checkInTime;
	}
	public long getHotelId(){
		return hotelId;
	}
	public Room getRoom(){
		return room;
	}
	public int getRoomNum(){
		return roomNum;
	}
	public Date getCheckInTime(){
		return checkInTime;
	}
}
