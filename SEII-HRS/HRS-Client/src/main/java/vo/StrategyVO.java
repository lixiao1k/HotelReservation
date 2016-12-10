package vo;

import java.util.Set;

import info.StrategyType;

public class StrategyVO {
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
	public void setStrategyType(StrategyType type){
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
	public double getOff() {
		return off;
	}
	public void setOff(double off) {
		this.off = off;
	}
	@Override
	public String toString() {
		return name + "   æ∆µÍ£∫" + hotelId + "   ’€ø€£∫" + off;
	}
}
