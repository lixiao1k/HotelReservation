package info;

import po.StrategyPO;

public class StrategyItem {
	private long id;
	private StrategyPO strategy;
	private Room room;
	private double off;
	private void setId(long id){
		this.id = id;
	}
	public void setStrategy(StrategyPO strategy){
		this.strategy = strategy;
	}
	public void setRoom(Room room){
		this.room = room;
	}
	public void setOff(double off){
		this.off = off;
	}
	public double getOff(){
		return off;
	}
	public StrategyPO getStrategy(){
		return strategy;
	}
	public long getId(){
		return id;
	}
	public Room getRoom(){
		return room;
	}
}
