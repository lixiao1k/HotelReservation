package vo;

import java.util.Set;

public class ExtraHotelVO {
	private Set<HotelCommentVO> comments;
	private Set<OrderVO> bookedOrders;
	public Set<OrderVO> getBookedOrders(){
		return bookedOrders;
	}
	public Set<HotelCommentVO> getComments(){
		return comments;
	}
	public void setBookedOrders(Set<OrderVO> bookedOrders){
		this.bookedOrders = bookedOrders;
	}
	public void setComments(Set<HotelCommentVO> comments){
		this.comments = comments;
	}
}
