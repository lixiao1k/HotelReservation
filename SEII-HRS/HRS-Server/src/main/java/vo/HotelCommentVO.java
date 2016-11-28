package vo;

import java.util.Date;

import info.Room;

public class HotelCommentVO {
	private String name;
	private Date date;
	private double score;
	private String content;
	private Room room;
	private boolean hide;
	public void setName(String name){
		this.name = name;
	}
	public void setDate(Date date){
		this.date = date;
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
	public String getName(String name){
		return name;
	}
	public Date getDate(){
		return date;
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
