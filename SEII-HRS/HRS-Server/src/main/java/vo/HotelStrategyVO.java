package vo;

import java.io.Serializable;
import java.util.Set;

import info.StrategyType;

public class HotelStrategyVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6677036065608327336L;
	private long id;
	private StrategyType type;
	private String name;
	private long hotelId;
	private Set<StrategyItemVO> items;
	private String extraInfo;
	private double off;
	public void setExtraInfo(String extraInfo){
		this.extraInfo = extraInfo;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setHotelId(long hotelId){
		this.hotelId = hotelId;
	}
	public void setType(StrategyType type){
		this.type = type;
	}
	public void setItems(Set<StrategyItemVO> items){
		this.items = items;
	}
	public Set<StrategyItemVO> getItems(){
		return items;
	}
	public long getHotelId(){
		return hotelId;
	}
	public String getName(){
		return name;
	}
	public StrategyType getType(){
		return type;
	}
	public String getExtraInfo(){
		return extraInfo;
	}
	public long getId(){
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public double getOff() {
		return off;
	}
	public void setOff(double off) {
		this.off = off;
	}
}
