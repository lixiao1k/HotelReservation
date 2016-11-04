package vo;

import java.util.List;

import businesslogic.StrategyBL.StrategyList;

public class HotelVO {
	long id;
	String name;
	String circle;
	String address;
	String shoutcut;
	List<String> equipment;
	List<String> service;
	int star;
	long score;
	RoomVO roomvo;
	StrategyList strategylist;
	
	public long getid(){
		return id;
	}
	
	public StrategyList getStrategyList(){
		return strategylist;
	}
	
	public HotelVO(long id,String name,String circle,String address,String shoutcut,List<String> equipment,List<String> service,int star,long score,StrategyList strategylist){
		this.id = id;
		this.circle = circle;
		this.score = score;
		this.address = address;
		this.shoutcut = shoutcut;
		this.equipment = equipment;
		this.service = service;
		this.star=star;
		this.score=score;
		this.roomvo=roomvo;
		this.strategylist=strategylist;
	}
	public HotelVO() {
		// TODO Auto-generated constructor stub
	}
	public void setRoomPO(RoomVO roomvo){ this.roomvo = roomvo;}
	public RoomVO getRoomPO(){return roomvo;}
}
