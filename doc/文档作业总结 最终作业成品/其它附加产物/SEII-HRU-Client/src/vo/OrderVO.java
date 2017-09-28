package vo;

public class OrderVO{
	long id;
	OrderStatus status;
	String begintime;
	String endtime;
	String actualBeginTime;
	int expectedpeople;
	int actualPeople;
	Boolean actualHaveChild;
	Boolean isHaveChild;
	long hotelid;
	RoomVO room;
	public OrderVO(String beginTime,String endTime,int expectedpeople,boolean isHaveChild,long hotelid,RoomVO room){
		this.begintime = beginTime;
		this.endtime = endTime;
		this.expectedpeople = expectedpeople;
		this.isHaveChild = isHaveChild;
		this.hotelid = hotelid;
		this.room = room;
		}
	public OrderVO() {
		// TODO Auto-generated constructor stub
	}
}