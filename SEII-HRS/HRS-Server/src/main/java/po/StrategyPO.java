package po;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import info.StrategyItem;
import info.StrategyType;
import po.strategies.StrategyRule;


public class StrategyPO {
	private long id;
	private String name;
	private HotelPO hotel;
	private StrategyType type;
	private Set<StrategyItem> items = new HashSet<StrategyItem>();
	private StrategyRule rule;
	public long getId(){
		return this.id;
	}
	public String getName(){
		return this.name;
	}
	public HotelPO getHotel(){
		return this.hotel;
	}
	public StrategyRule getRule(){
		return rule;
	}
	private Set<StrategyItem> getItems(){
		return items;
	}
	public Iterator<StrategyItem> getStrategyRoom(){
		return items.iterator();
	}
	public StrategyType getStrategyType(){
		return this.type;
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
	public void setStrategyRule(StrategyRule rule){
		this.rule = rule;
	}
	public void setItems(Set<StrategyItem> items){
		this.items = items;
	}
}
