package vo;

public class ManageHotelVO {
	private String hotelname;
	private String address;
	private String bussinesscity;
	private String bussinesscircle;
	private long hotelid;
	private long userid;
	private String username;
	private String password;
	private String name;
	public String getHotelname() {
		return hotelname;
	}
	public void setHotelname(String hotelname) {
		this.hotelname = hotelname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getBussinesscity() {
		return bussinesscity;
	}
	public void setBussinesscity(String bussinesscity) {
		this.bussinesscity = bussinesscity;
	}
	public String getBussinesscircle() {
		return bussinesscircle;
	}
	public void setBussinesscircle(String bussinesscircle) {
		this.bussinesscircle = bussinesscircle;
	}
	public long getHotelid() {
		return hotelid;
	}
	public void setHotelid(long hotelid) {
		this.hotelid = hotelid;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ManageHotelVO(String hotelname, String address, String bussinesscity, String bussinesscircle, long hotelid,
			long userid, String username, String password, String name) {
		super();
		this.hotelname = hotelname;
		this.address = address;
		this.bussinesscity = bussinesscity;
		this.bussinesscircle = bussinesscircle;
		this.hotelid = hotelid;
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.name = name;
	}
}
