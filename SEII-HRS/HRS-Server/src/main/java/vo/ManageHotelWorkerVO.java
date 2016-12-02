package vo;

public class ManageHotelWorkerVO {
	private long hotelid;
	private String name;
	private String password;
	public ManageHotelWorkerVO(long hotelid, String name, String password) {
		super();
		this.hotelid = hotelid;
		this.name = name;
		this.password = password;
	}
	public long getHotelid() {
		return hotelid;
	}
	public void setHotelid(long hotelid) {
		this.hotelid = hotelid;
	}
	public String getName() {
		return name;
	}
	@Override
	public String toString() {
		return "ManageHotelWorkerVO [hotelid=" + hotelid + ", name=" + name + ", password=" + password + "]";
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
