package po;

import java.time.LocalDate;

import info.CommentInfo;

public class CommentPO {
	private int grade;
	private String comment;
	private LocalDate date;
	private long userid;
	private long hotelId;
    public CommentPO(int grade,String comment,long userid,long hotelId){
    	//get time;
    	date = LocalDate.now();
    	this.userid = userid;
    	this.grade = grade;
    	this.comment = comment;
    	this.hotelId = hotelId;
    }
    public int getGrade(){
    	return grade;
    }
    public String getComment(){
    	return comment;
    }
    public LocalDate getDate(){
    	return date;
    }
    public long getUserId(){
    	return userid;
    }
    public long getHotelId(){
    	return hotelId;
    }
}
