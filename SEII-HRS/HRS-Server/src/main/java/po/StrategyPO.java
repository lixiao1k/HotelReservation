package po;

import java.util.Set;

import info.StrategyRoom;

public class StrategyPO {
	private long id;
	private String name;
	private String description;
	private HotelPO hotel;
	private Set<StrategyRoom> rooms;
	private StrategyType type;
	
	public long getId(){
		return this.id;
	}
	public String getName(){
		return this.name;
	}
	public String getDescription(){
		return this.description;
	}
	public HotelPO getHotel(){
		return this.hotel;
	}
	public StrategyType getStrategyType(){
		return this.getStrategyType();
	}
	public void setStrategyType(StrategyType type){
		this.type = type;
	}
	public void setHotel(HotelPO hotel){
		this.hotel = hotel;
	}
	public void setId(long id){
		this.id = id;
	}
	public void setName(String name){
		this.name = name;
	}
}
