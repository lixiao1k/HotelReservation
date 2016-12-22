package vo;

import java.io.Serializable;

public class AddRoomVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6030083402959330763L;
	private long hotelId;
	private String roomType;
	private int num;
	private double price;
	public long getHotelId() {
		return hotelId;
	}
	public void setHotelId(long hotelId) {
		this.hotelId = hotelId;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}
