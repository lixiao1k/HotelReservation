package vo;

import java.util.Set;

import info.StrategyItem;
import info.StrategyType;

public class StrategyVO {
	private StrategyType type;
	private String name;
	private long hotelId;
	private Set<StrategyItem> items;
	private String extraInfo;
	public void setExtraInfo(String extraInfo){
		this.extraInfo = extraInfo;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setHotelId(long hotelId){
		this.hotelId = hotelId;
	}
	public void setStrategyType(StrategyType type){
		this.type = type;
	}
	public void setItems(Set<StrategyItem> items){
		this.items = items;
	}
	public Set<StrategyItem> getItems(){
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
}
