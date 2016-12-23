package vo;

import java.io.Serializable;

import info.Room;

public class StrategyItemVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4707894610414142254L;
	private Room room;
	private double priceBefore;
	private double priceAfter;
	private double off;
	public void setOff(double off){
		this.off = off;
	}
	public void setRoom(Room room){
		this.room = room;
	}
	public void setPriceBefore(double priceBefore){
		this.priceBefore = priceBefore;
	}
	public void setPriceAfter(double priceAfter){
		this.priceAfter = priceAfter;
	}
	public double getPriceBefore(){
		return priceBefore;
	}
	public double getOff(){
		return off;
	}
	public double getPriceAfter(){
		return priceAfter;
	}
	public Room getRoom(){
		return room;
	}
}
