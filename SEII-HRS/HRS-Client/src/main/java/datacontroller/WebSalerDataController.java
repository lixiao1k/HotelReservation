package datacontroller;

public class WebSalerDataController {
	private long userid;
	private static WebSalerDataController instance;
	static{
		instance = new WebSalerDataController();
	}
	public static WebSalerDataController getInstance(){
		return instance;
	}
	public void setUserId(long userId){
		this.userid = userId;
	}
	public long getUserId(){
		return userid;
	}
}
