package vo;

import java.io.Serializable;
import java.util.Date;

import info.Room;

public class HotelItemVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5340516535212934424L;
	private Room room;
	private int num;
	private int total;
	private double price;
	private Date date;
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
