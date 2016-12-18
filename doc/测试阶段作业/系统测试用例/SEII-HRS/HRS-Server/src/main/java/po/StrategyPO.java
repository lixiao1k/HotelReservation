package po;

import java.sql.Blob;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import info.StrategyItem;
import info.StrategyType;
import po.strategies.StrategyRule;
import util.SerializeUtil;


public class StrategyPO {
	private long id;
	private String name;
	private HotelPO hotel;
	private StrategyType type;
	private Set<StrategyItem> items = new HashSet<StrategyItem>();
	private Blob rule;
	private boolean status;
	private double off;
	public long getId(){
		return this.id;
	}
	public String getName(){
		return this.name;
	}
	public HotelPO getHotel(){
		return this.hotel;
	}
	public Blob getRule(){
		return rule;
	}
	public Set<StrategyItem> getItems(){
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
	public void setRule(Blob rule){
		this.rule = rule;
	}
	public void setItems(Set<StrategyItem> items){
		this.items = items;
	}
	public StrategyType getType() {
		return type;
	}
	public void setType(StrategyType type) {
		this.type = type;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public double getOff() {
		return off;
	}
	public void setOff(double off) {
		this.off = off;
	}
}
