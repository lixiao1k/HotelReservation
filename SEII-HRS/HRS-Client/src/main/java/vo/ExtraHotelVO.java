package vo;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Set;

import javax.imageio.ImageIO;


public class ExtraHotelVO {
	private Set<HotelCommentVO> comments;
	private Set<OrderVO> bookedOrders;
	private transient Image image;
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
	private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        ImageIO.write((BufferedImage) image, "png", out);
    }
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
	    in.defaultReadObject();
	    image=ImageIO.read(in);    
	}
}
