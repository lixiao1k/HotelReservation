package vo;

import java.io.Serializable;
import java.util.Date;
import info.Room;

public class HotelCommentVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2664637905746488183L;
	private String name;
	private Date date;
	private int grade;
	private String comment;
	private Room room;
	private long hotelId;
	private boolean hide;
	public void setName(String name){
		this.name = name;
	}
	public void setDate(Date date){
		this.date = date;
	}
	public void setGrade(int grade){
		this.grade = grade;
	}
	public void setComment(String comment){
		this.comment = comment;
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
	public int getGrade(){
		return grade;
	}
	public String getComment(){
		return comment;
	}
	public Room getRoom(){
		return room;
	}
	public boolean isHide(){
		return hide;
	}
	public long getHotelId() {
		return hotelId;
	}
	public void setHotelId(long hotelId) {
		this.hotelId = hotelId;
	}
	public String getName() {
		return name;
	}
}
