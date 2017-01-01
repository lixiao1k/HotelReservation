package vo;

import java.io.Serializable;

public class HotelWorkerVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6891098430540733974L;
	private String username;
	private String password;
	public String getUsername(){
		return username;
	}
	public String getPassword(){
		return password;
	}
	public HotelWorkerVO(String username,String password){
		this.username = username;
		this.password = password;
	}
}
