package vo;

import java.util.List;


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
	
	public long getid(){
		return id;
	}
	
	
	public HotelVO(long id,String name,String circle,String address,String shoutcut,List<String> equipment,List<String> service,int star,long score){
		this.id = id;
		this.circle = circle;
		this.score = score;
		this.address = address;
		this.shoutcut = shoutcut;
		this.equipment = equipment;
		this.service = service;
		this.star=star;
		this.score=score;
	}
	public HotelVO() {
		// TODO Auto-generated constructor stub
	}
}
