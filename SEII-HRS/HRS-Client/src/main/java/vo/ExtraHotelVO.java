package vo;

import java.util.Set;

import javafx.scene.image.Image;

public class ExtraHotelVO {
	private Set<HotelCommentVO> comments;
	private Set<OrderVO> bookedOrders;
	private Image image;
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
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
}
