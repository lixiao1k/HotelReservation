package datacontroller;

public class ClientDataController {
	private long userid;
	private static ClientDataController instance;
	static{
		instance = new ClientDataController();
	}
	public static ClientDataController getInstance(){
		return instance;
	}
	public void setUserId(long userId){
		this.userid = userId;
	}
	public long getUserId(){
		return userid;
	}
}
