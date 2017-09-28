package vo;

import info.Room;

public class OrderCommentVO {
	private long userId;
	private String orderNum;
	private long hotelId;
	private double score;
	private String content;
	private Room room;
	private boolean hide;
	public void setUserId(long userId){
		this.userId = userId;
	}
	public void setOrderNum(String orderNum){
		this.orderNum = orderNum;
	}
	public void setHotelId(long hotelId){
		this.hotelId = hotelId;
	}
	public void setScore(double score){
		this.score = score;
	}
	public void setContent(String content){
		this.content = content;
	}
	public void setRoom(Room room){
		this.room = room;
	}
	public void setHide(boolean hide){
		this.hide = hide;
	}
	public long getUserId(){
		return userId;
	}
	public String getOrderNum(){
		return orderNum;
	}
	public long getHotelId(){
		return hotelId;
	}
	public double getScore(){
		return score;
	}
	public String getContent(){
		return content;
	}
	public Room getRoom(){
		return room;
	}
	public boolean isHide(){
		return hide;
	}
}
