package vo;

import java.util.List;

public class HotelVO {
	long ID;
	String name;
	String circle;
	String address;
	String shoutcut;
	List<String> equipment;
	List<String> service;
	int star;
	long score;
	RoomVO roomvo;
	
	public HotelVO(long id,String name,String circle,String address,String shoutcut,List<String> equipment,List<String> service,int star,long score){
		this.ID = id;
		this.circle = circle;
		this.score = score;
		this.address = address;
		this.shoutcut = shoutcut;
		this.equipment = equipment;
		this.service = service;
	}
	public HotelVO() {
		// TODO Auto-generated constructor stub
	}
	public void setRoomPO(RoomVO roomvo){ this.roomvo = roomvo;}
}
