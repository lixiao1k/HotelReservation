package po;

public class HotelWorkerPO extends MemberPO{
	private String name;
	private HotelPO hotel;
	public void setName(String name){
		this.name = name;
	}
	public void setHotel(HotelPO hotel){
		this.hotel = hotel;
	}
	public HotelPO getHotel(){
		return hotel;
	}
	public String getName(){
		return name;
	}
}
