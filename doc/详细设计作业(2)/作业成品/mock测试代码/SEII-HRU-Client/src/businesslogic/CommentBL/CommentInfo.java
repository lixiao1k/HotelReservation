package businesslogic.CommentBL;

import java.time.LocalDate;

import vo.CommentVO;

public class CommentInfo {
	private int grade;
	private String comment;
	private LocalDate date;
	private long userid;
	private long hotelId;
    public CommentInfo(int grade,String comment,long userid,long hotelId){
    	//get time;
    	date = LocalDate.now();
    	this.userid = userid;
    	this.grade = grade;
    	this.comment = comment;
    	this.hotelId = hotelId;
    }
    public CommentInfo(CommentVO vo){
    	grade = vo.getGrade();
    	comment = vo.getComment();
    	date = vo.getDate();
    	userid = vo.getUserId();
    	hotelId = vo.getHotelId();
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
    public String toString(){
    	return date + " " + "∆¿¬€»À:"+userid+" ∆¿¬€æ∆µÍ:"+hotelId+" comment:"+comment+" grade:"+grade;
    }
}
