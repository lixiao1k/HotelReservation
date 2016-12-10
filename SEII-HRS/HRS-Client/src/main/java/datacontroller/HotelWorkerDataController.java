package datacontroller;

public class HotelWorkerDataController {
	private long hotelId;
	private long hotelworkid;
	private static HotelWorkerDataController instance;
	static{
		instance = new HotelWorkerDataController();
	}
	public static HotelWorkerDataController getInstance(){
		return instance;
	}
	public long getHotelId() {
		return hotelId;
	}
	public void setHotelId(long hotelId) {
		this.hotelId = hotelId;
	}
	public long getHotelworkid() {
		return hotelworkid;
	}
	public void setHotelworkid(long hotelworkid) {
		this.hotelworkid = hotelworkid;
	}
}
