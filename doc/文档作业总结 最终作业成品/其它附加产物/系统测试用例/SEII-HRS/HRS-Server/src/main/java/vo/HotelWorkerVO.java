package vo;

public class HotelWorkerVO {
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
