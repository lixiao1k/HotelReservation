package po;

import java.util.Date;

import info.Room;

public class CommentPO {
	private long id;
	private int grade;
	private String comment;
	private Date date;
	private MemberPO member;
	private OrderPO order;
	private HotelPO hotel;
	private Room room;
	public OrderPO getOrder(){
		return order;
	}
	public long getId(){
		return id;
	}
	public Room getRoom(){
		return room;
	}
    public String getComment(){
    	return comment;
    }
    public Date getDate(){
    	return date;
    }
    public MemberPO getMember(){
    	return member;
    }
    public HotelPO getHotel(){
    	return hotel;
    }
    public int getGrade(){
    	return grade;
    }
    public void setHotel(HotelPO hotel){
    	this.hotel = hotel;
    }
    public void setMember(MemberPO member){
    	this.member = member;
    }
    public void setDate(Date date){
    	this.date = date;
    }
    public void setComment(String comment){
    	this.comment = comment;
    }
    public void setGrade(int grade){
    	this.grade = grade;
    }
    public void setRoom(Room room){
    	this.room = room;
    }
    private void setId(long id){
    	this.id = id;
    }
    public void setOrder(OrderPO order){
    	this.order = order;
    }
}
