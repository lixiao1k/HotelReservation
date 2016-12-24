package vo;

import info.BusinessCircle;
import info.BusinessCity;

public class ManageHotelVO {
	private String hotelname;
	private String address;
	private BusinessCity bussinesscity;
	private BusinessCircle bussinesscircle;
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
	@Override
	public String toString() {
		return "ManageHotelVO [hotelname=" + hotelname + ", address=" + address + ", bussinesscity=" + bussinesscity
				+ ", bussinesscircle=" + bussinesscircle + ", hotelid=" + hotelid + ", userid=" + userid + ", username="
				+ username + ", password=" + password + ", name=" + name + "]";
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public BusinessCity getBussinesscity() {
		return bussinesscity;
	}
	public void setBussinesscity(BusinessCity bussinesscity) {
		this.bussinesscity = bussinesscity;
	}
	public BusinessCircle getBussinesscircle() {
		return bussinesscircle;
	}
	public void setBussinesscircle(BusinessCircle bussinesscircle) {
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
	public ManageHotelVO(String hotelname, String address, BusinessCity bussinesscity, BusinessCircle bussinesscircle, long hotelid,
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
	public ManageHotelVO() {
		// TODO Auto-generated constructor stub
	}
}
