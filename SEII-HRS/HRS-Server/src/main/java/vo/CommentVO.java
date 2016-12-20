package vo;

import java.io.Serializable;
import java.util.Date;


public class CommentVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5856041160505967577L;
	private int grade;
	private String comment;
	private Date date;
	private long orderId;
	private long userid;
	private long hotelId;
	private boolean hide;
    public CommentVO(int grade,String comment,long userid,long hotelId,long orderId){
    	//get time;
    	date = new Date();
    	this.userid = userid;
    	this.orderId = orderId;
    	this.grade = grade;
    	this.comment = comment;
    	this.hotelId = hotelId;
    }
    public CommentVO(){
    	
    }
    public int getGrade(){
    	return grade;
    }
    public String getComment(){
    	return comment;
    }
    public Date getDate(){
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
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void setHotelId(long hotelId) {
		this.hotelId = hotelId;
	}
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public boolean isHide() {
		return hide;
	}
	public void setHide(boolean hide) {
		this.hide = hide;
	}
}
