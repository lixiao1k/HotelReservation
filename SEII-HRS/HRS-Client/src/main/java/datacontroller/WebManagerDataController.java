package datacontroller;

public class WebManagerDataController {
	private long userid;
	private static WebManagerDataController instance;
	static{
		instance = new WebManagerDataController();
	}
	public static WebManagerDataController getInstance(){
		return instance;
	}
	public void setUserId(long userId){
		this.userid = userId;
	}
	public long getUserId(){
		return userid;
	}
}
