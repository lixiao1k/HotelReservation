package vo;

import java.io.Serializable;

public class ManageHotelWorkerVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1450062278500466774L;
	private long hotelid;
	private long userid;
	private String username;
	private String name;
	private String password;
	public ManageHotelWorkerVO(long hotelid, long userid, String username, String name, String password) {
		super();
		this.hotelid = hotelid;
		this.userid = userid;
		this.username = username;
		this.name = name;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
		return "ManageHotelWorkerVO [hotelid=" + hotelid + ", username=" + username + ", name=" + name + ", password="
				+ password + "]";
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
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
}
